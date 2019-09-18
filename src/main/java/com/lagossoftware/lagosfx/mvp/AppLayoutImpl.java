/*
 * LagosFX application framework
 *
 * Copyright(c) 2014 - 2016, Cem Ikta
 */
package com.lagossoftware.lagosfx.mvp;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.persist.PersistService;
import com.lagossoftware.ibbfx.IbbManagement;
import com.lagossoftware.ibbfx.app.AppPlaces;
import com.lagossoftware.ibbfx.app.I18n;
import com.lagossoftware.ibbfx.app.Images;
import com.lagossoftware.ibbfx.app.PopOverAppMenu;
import com.lagossoftware.lagosfx.common.ViewHelpers;
import com.lagossoftware.lagosfx.control.AboutDialog;
import com.lagossoftware.lagosfx.control.AppHeaderBar;
import com.lagossoftware.lagosfx.control.MessageBox;
import com.lagossoftware.lagosfx.navigation.NavigationManager;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContentDisplay;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.Optional;

/**
 * Application layout
 *
 * @author Cem Ikta
 */
@Singleton
public class AppLayoutImpl extends BorderPane implements AppLayout {

    private NavigationManager navigationManager;
    private AppHeaderBar appHeader;
    private AppHeaderBar appHeaderBar;
    private AppHeaderBar appLoginHeaderBar;

    private PopOverAppMenu popOverAppMenu;
    private BorderPane appContent;
    
    private Button btnAppHome;
    private Button btnAppModulesMenu;
    private Button btnAppAbout;
    private Button btnAppLoggedUser;
    private Button btnAppExit;

    @Inject
    public AppLayoutImpl(NavigationManager navigationManager) {
        super();
        this.navigationManager = navigationManager;

        setId("background");
        setCenter(buildAppContent());
    }

    private AppHeaderBar getAppHeaderBar() {
        if (appHeaderBar != null) {
            return appHeaderBar;
        }

        appHeaderBar = new AppHeaderBar();
        // left items
        Text appLogo = new Text("IBB");
        appLogo.getStyleClass().add("app-logo");
        appLogo.setOnMouseClicked((MouseEvent event) -> {
            navigationManager.goTo(new Place(AppPlaces.DASHBOARD));
        });
        
        HBox.setMargin(appLogo, new Insets(0, 8, 0, 0));

        btnAppHome = ViewHelpers.createIconButton(Images.HOME_24);
        btnAppHome.getStyleClass().add("first");
        btnAppHome.setOnAction((ActionEvent event) -> {
            navigationManager.goTo(new Place(AppPlaces.DASHBOARD));
        });
        
        btnAppModulesMenu = ViewHelpers.createIconButton(Images.APP_MENU_24);
        btnAppModulesMenu.getStyleClass().add("last");
        btnAppModulesMenu.setOnAction((ActionEvent event) -> {
            if (popOverAppMenu == null) {
                popOverAppMenu = new PopOverAppMenu(navigationManager);
            }
            
            if (popOverAppMenu != null && popOverAppMenu.isShowing()) {
                popOverAppMenu.hide(Duration.ZERO);
            } else {
                popOverAppMenu.show(btnAppModulesMenu);            
            }
        });
        
        HBox leftButtons = new HBox(0, appLogo, btnAppHome, btnAppModulesMenu);
        leftButtons.getStyleClass().setAll("app-header-button-bar");        

        // right items
        btnAppAbout = ViewHelpers.createIconButton(Images.ABOUT_24);
        btnAppAbout.getStyleClass().add("first");
        btnAppAbout.setOnAction((ActionEvent event) -> {
            AboutDialog.showDialog();
        });
        
        btnAppLoggedUser = ViewHelpers.createIconButton(Images.EXIT_24,
                IbbManagement.get().getLoggedUser().getName(), ContentDisplay.RIGHT);
        btnAppLoggedUser.getStyleClass().add("last");
        btnAppLoggedUser.setOnAction((ActionEvent event) -> {
            Optional<ButtonType> result = MessageBox.get()
                    .contentText(I18n.COMMON.getString("dialog.ask.appExit"))
                    .showConfirmation();
            if (result.get() == ButtonType.YES){
                IbbManagement.get().getInjector().getInstance(PersistService.class).stop();
                Platform.exit();
            }
        });
        
        HBox rightButtons = new HBox(0, btnAppAbout, btnAppLoggedUser);
        rightButtons.getStyleClass().setAll("app-header-button-bar");        
        
        appHeaderBar.addLeftItems(leftButtons);
        appHeaderBar.addRightItems(rightButtons);

        return appHeaderBar;
    }

    private AppHeaderBar getAppLoginHeaderBar() {
        if (appLoginHeaderBar != null) {
            return appLoginHeaderBar;
        }

        appLoginHeaderBar = new AppHeaderBar();
        // left items
        Text appLogo = new Text("IBB");
        appLogo.getStyleClass().add("app-logo");
        HBox.setMargin(appLogo, new Insets(0, 8, 0, 0));

        HBox leftButtons = new HBox(0, appLogo);
        leftButtons.getStyleClass().setAll("app-header-button-bar");

        // right items
        btnAppAbout = ViewHelpers.createIconButton(Images.ABOUT_24);
        btnAppAbout.getStyleClass().add("first");
        btnAppAbout.setOnAction((ActionEvent event) -> {
            AboutDialog.showDialog();
        });

        btnAppExit = ViewHelpers.createIconButton(Images.EXIT_24,
                I18n.COMMON.getString("action.appExit"), ContentDisplay.RIGHT);
        btnAppExit.getStyleClass().add("last");
        btnAppExit.setOnAction((ActionEvent event) -> {
            IbbManagement.get().getInjector().getInstance(PersistService.class).stop();
            Platform.exit();
        });

        HBox rightButtons = new HBox(0, btnAppAbout, btnAppExit);
        rightButtons.getStyleClass().setAll("app-header-button-bar");

        appLoginHeaderBar.addLeftItems(leftButtons);
        appLoginHeaderBar.addRightItems(rightButtons);

        return appLoginHeaderBar;
    }

    private BorderPane buildAppContent() {
        appContent = new BorderPane();
        appContent.getStyleClass().add("app-content");

        return appContent;
    }

    @Override
    public AppHeaderBar getAppHeader() {
        return appHeader;
    }

    @Override
    public void setAppHeader(boolean isLogin) {
        if (isLogin) {
            appHeader = getAppLoginHeaderBar();
        } else {
            appHeader = getAppHeaderBar();
        }

        setTop(appHeader);
    }

    @Override
    public BorderPane getAppContent() {
        return appContent;
    }

    @Override
    public Node asNode() {
        return this;
    }

}
