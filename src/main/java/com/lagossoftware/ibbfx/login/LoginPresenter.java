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
package com.lagossoftware.ibbfx.login;

import com.google.inject.Inject;
import com.lagossoftware.ibbfx.IbbManagement;
import com.lagossoftware.ibbfx.app.AppPlaces;
import com.lagossoftware.ibbfx.app.I18n;
import com.lagossoftware.ibbfx.entity.AppUser;
import com.lagossoftware.ibbfx.service.AppUserService;
import com.lagossoftware.lagosfx.mvp.AbstractPresenter;
import com.lagossoftware.lagosfx.mvp.Place;
import com.lagossoftware.lagosfx.navigation.NavigationManager;
import com.lagossoftware.lagosfx.service.QueryParameter;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import org.apache.commons.lang3.StringUtils;
import org.controlsfx.control.Notifications;

import java.util.Objects;

/**
 * Login presenter
 *
 * @author Cem Ikta
 */
public class LoginPresenter extends AbstractPresenter<LoginView> {

    private AppUserService service;

    @Inject
    public LoginPresenter(final NavigationManager navigationManager, final LoginView view,
                          final AppUserService service) {
        super(navigationManager, view);
        this.service = service;
    }

    @Override
    public void start(BorderPane container) {
        container.setLeft(null);
        container.setRight(null);
        container.setTop(null);
        container.setCenter(getView().asNode());
        container.setBottom(null);
        bind();
        navigationManager.setCurrentPageTitle(view.getHeaderTitle());
    }

    @Override
    public void bind() {
        view.getTfUsername().setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                onLogin();
            }
        });

        view.getPfPassword().setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                onLogin();
            }
        });

        view.getBtnLogin().setOnAction((ActionEvent event) -> {
            onLogin();
        });
    }

    private void onLogin() {
        if (StringUtils.isNotEmpty(view.getTfUsername().getText()) &&
                StringUtils.isNotEmpty(view.getPfPassword().getText()) ) {

            AppUser appUser = service.findOneWithNamedQuery(AppUser.FIND_ONE_BY_USERNAME,
                    QueryParameter.with("username", view.getTfUsername().getText()).parameters());

            if (appUser == null) {
                Notifications.create()
                        .text(I18n.COMMON.getString("login.userNotFound"))
                        .position(Pos.TOP_RIGHT).showInformation();
            } else if (!appUser.isActive()) {
                    Notifications.create()
                            .text(I18n.COMMON.getString("login.userNotActive"))
                            .position(Pos.TOP_RIGHT).showInformation();
            } else {

                if (Objects.equals(view.getPfPassword().getText(), appUser.getPassword())) {
                    IbbManagement.get().setLoggedUser(appUser);
                    navigationManager.goTo(new Place(AppPlaces.DASHBOARD));
                } else {
                    Notifications.create()
                            .text(I18n.COMMON.getString("login.wrongPassword"))
                            .position(Pos.TOP_RIGHT).showInformation();
                }
            }
        }
    }

}
