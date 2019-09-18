/*
 * Copyright (C) 2016 IBB Management Project, Cem Ikta
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.lagossoftware.ibbfx;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.persist.PersistService;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.lagossoftware.ibbfx.app.AppPlaces;
import com.lagossoftware.ibbfx.app.AppRightType;
import com.lagossoftware.ibbfx.app.I18n;
import com.lagossoftware.ibbfx.entity.AppRight;
import com.lagossoftware.ibbfx.entity.AppUser;
import com.lagossoftware.ibbfx.entity.AppUserGroup;
import com.lagossoftware.ibbfx.entity.AppUserRole;
import com.lagossoftware.ibbfx.guice.AppInjector;
import com.lagossoftware.ibbfx.guice.AppJpaInitializer;
import com.lagossoftware.lagosfx.control.MessageBox;
import com.lagossoftware.lagosfx.control.Splash;
import com.lagossoftware.lagosfx.mvp.AppLayout;
import com.lagossoftware.lagosfx.mvp.Place;
import com.lagossoftware.lagosfx.navigation.NavigationManager;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.util.Locale;
import java.util.Optional;
import java.util.logging.Logger;

/**
 * IBB management main application
 *
 * @author Cem Ikta
 */
public class IbbManagement extends Application {

    private static final Logger logger = Logger.getLogger(IbbManagement.class.getName());

    private final static double WIDTH = 1024;
    private final static double HEIGHT = 768;
    private final static String APP_STYLESHEET = "/styles/styles.css";
    private final static String APP_PERSISTENCE_UNIT = "ibbManagementPU";
    private static IbbManagement ibbManagement;
    private Injector injector;
    private NavigationManager navigationManager;
    private Stage mainStage;
    private Scene mainScene;
    private AppUser loggedUser;

    private static final String SPLASH_IMAGE = "/images/splash.png";
    private Splash splash;

    @Override
    public void init() throws Exception {
        // TODO set up app log file!
        logger.info("init application");
        splash = new Splash(SPLASH_IMAGE);

        injector = Guice.createInjector(new AppInjector(), new JpaPersistModule(APP_PERSISTENCE_UNIT));
        injector.getInstance(AppJpaInitializer.class);

        navigationManager = injector.getInstance(NavigationManager.class);
        navigationManager.setInjector(injector);
    }

    @Override
    public void start(Stage initStage) throws Exception {
        logger.info("start application");
        ibbManagement = this;

        final Task<ObservableList<String>> moduleTask = new Task<ObservableList<String>>() {
            @Override
            protected ObservableList<String> call() throws InterruptedException {
                ObservableList<String> moduleList = FXCollections.observableArrayList(
                        I18n.COMMON.getString("appModule.adresse"),
                        I18n.COMMON.getString("appModule.eingangsrechnung"),
                        I18n.COMMON.getString("appModule.planung"),
                        I18n.COMMON.getString("appModule.fahrtkasse"),
                        I18n.COMMON.getString("appModule.grundlagen"),
                        I18n.COMMON.getString("appModule.reports"),
                        I18n.COMMON.getString("appModule.settings")
                );

                updateMessage("Lade Module...");
                for (int i = 0; i < moduleList.size(); i++) {
                    Thread.sleep(100);
                    updateProgress(i + 1, moduleList.size());
                    String next = moduleList.get(i);
                    updateMessage("Lade Module... gefunden " + next);
                }
                Thread.sleep(100);
                updateMessage("Alle Module erfolgreich geladen");

                return moduleList;
            }
        };

        showSplash(initStage, moduleTask, () -> {
            showMainStage();
        });
        new Thread(moduleTask).start();
    }

    private void showSplash(final Stage initStage, Task<?> task,
                            InitCompletionHandler initCompletionHandler) {
        splash.getProgressText().textProperty().bind(task.messageProperty());
        splash.getLoadProgress().progressProperty().bind(task.progressProperty());
        task.stateProperty().addListener((observableValue, oldState, newState) -> {
            if (newState == Worker.State.SUCCEEDED) {
                splash.getLoadProgress().progressProperty().unbind();
                splash.getLoadProgress().setProgress(1);
                initStage.toFront();
                FadeTransition fadeSplash = new FadeTransition(Duration.seconds(1.2), splash);
                fadeSplash.setFromValue(1.0);
                fadeSplash.setToValue(0.0);
                fadeSplash.setOnFinished(actionEvent -> initStage.hide());
                fadeSplash.play();

                initCompletionHandler.complete();
            }
        });

        Scene splashScene = new Scene(splash);
        splashScene.getStylesheets().add(APP_STYLESHEET);
        initStage.initStyle(StageStyle.UNDECORATED);
        initStage.setScene(splashScene);
        initStage.show();
    }

    private void showMainStage() {
        mainStage = new Stage(StageStyle.DECORATED);
        mainStage.setTitle(I18n.COMMON.getString("app.title"));
        mainStage.getIcons().add(new Image("/icons32/app.png"));
        mainStage.setMaximized(true);
        mainScene = new Scene((Parent) injector.getInstance(AppLayout.class), WIDTH, HEIGHT);
        setStylesheets();
        mainStage.setScene(mainScene);
        mainStage.setOnCloseRequest((WindowEvent we) -> {
            Optional<ButtonType> result = MessageBox.get()
                    .contentText(I18n.COMMON.getString("dialog.ask.appExit"))
                    .showConfirmation();
            if (result.get() == ButtonType.YES){
                injector.getInstance(PersistService.class).stop();
                Platform.exit();
            } else {
                we.consume();
            }
        });

        navigationManager.goTo(new Place(AppPlaces.LOGIN));
        mainStage.show();
    }

    public interface InitCompletionHandler {

        public void complete();
    }

    private void setStylesheets() {
        mainScene.getStylesheets().add(APP_STYLESHEET);
    }

    /**
     * Gets guice injector of IbbManagement
     *
     * @return guice injector instance
     */
    public Injector getInjector() {
        return injector;
    }

    /**
     * Get the singleton instance of IbbManagement
     *
     * @return The singleton instance
     */
    public static IbbManagement get() {
        return ibbManagement;
    }

    public AppUser getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(AppUser loggedUser) {
        this.loggedUser = loggedUser;
    }

    public boolean isLoggedUserAdmin() {
        return (AppUserRole.ADMIN == loggedUser.getAppUserRole());
    }

    public boolean isReadAllowed(AppPlaces place) {
        if (isAdmin()) {
            return true;
        }

        AppRight appRight  = findAppRight(place);
        if (appRight != null && appRight.isAllowRead()) {
            return true;
        } else {
            showAppRightNotification(AppRightType.READ);
            return false;
        }
    }

    public boolean isCreateAllowed(AppPlaces place) {
        if (isAdmin()) {
            return true;
        }

        AppRight appRight  = findAppRight(place);
        if (appRight != null && appRight.isAllowCreate()) {
            return true;
        } else {
            showAppRightNotification(AppRightType.CREATE);
            return false;
        }
    }

    public boolean isEditAllowed(AppPlaces place) {
        if (isAdmin()) {
            return true;
        }

        AppRight appRight  = findAppRight(place);
        if (appRight != null && appRight.isAllowEdit()) {
            return true;
        } else {
            showAppRightNotification(AppRightType.EDIT);
            return false;
        }
    }

    public boolean isDeleteAllowed(AppPlaces place) {
        if (isAdmin()) {
            return true;
        }

        AppRight appRight  = findAppRight(place);
        if (appRight != null && appRight.isAllowDelete()) {
            return true;
        } else {
            showAppRightNotification(AppRightType.DELETE);
            return false;
        }
    }

    private boolean isAdmin() {
        return (AppUserRole.ADMIN == loggedUser.getAppUserRole());
    }

    private AppRight findAppRight(AppPlaces place) {
        logger.info("Find app right with place: " + place);

        AppUserGroup group = loggedUser.getAppUserGroup();

        for (AppRight appRight: group.getAppRightList()) {
            if (place == appRight.getObjectName1() || place == appRight.getObjectName2()){
                return appRight;
            }
        }

        return null;
    }

    public void showAppRightNotification(AppRightType appRightType) {
        switch (appRightType) {
            case READ:
                Notifications.create()
                        .text(I18n.COMMON.getString("app.readNotAllowed"))
                        .position(Pos.TOP_RIGHT).showInformation();
                break;
            case CREATE:
                Notifications.create()
                        .text(I18n.COMMON.getString("app.createNotAllowed"))
                        .position(Pos.TOP_RIGHT).showInformation();
                break;
            case EDIT:
                Notifications.create()
                        .text(I18n.COMMON.getString("app.editNotAllowed"))
                        .position(Pos.TOP_RIGHT).showInformation();
                break;
            case DELETE:
                Notifications.create()
                        .text(I18n.COMMON.getString("app.deleteNotAllowed"))
                        .position(Pos.TOP_RIGHT).showInformation();
                break;
        }
    }

    public static void main(String[] args) {
        Locale.setDefault(new Locale("de", "DE"));
        launch(args);
    }

}
