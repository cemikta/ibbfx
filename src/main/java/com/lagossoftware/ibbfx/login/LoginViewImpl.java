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

import com.lagossoftware.ibbfx.app.I18n;
import com.lagossoftware.ibbfx.app.Images;
import com.lagossoftware.lagosfx.common.ViewHelpers;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;

/**
 * Login view implementation
 *
 * @author Cem Ikta
 */
public class LoginViewImpl extends FlowPane implements LoginView {

    private TextField tfUsername;
    private PasswordField pfPassword;
    private Button btnLogin;

    private final static String STYLE = "-fx-font-size:16px;";


    public LoginViewImpl() {
        buildView();
    }

    private void buildView() {
        setHgap(10);
        setVgap(10);
        setPadding(new Insets(100, 0, 5, 0));
        setMinWidth(300);
        setMaxWidth(300);
        setPrefWidth(300);
        setPrefWrapLength(300);

        GridPane formPane = new GridPane();
        formPane.setHgap(10);
        formPane.setVgap(10);

        ImageView imgLogin = ViewHelpers.createImageView(Images.LOGIN);
        tfUsername = new TextField();
        tfUsername.setPromptText(I18n.COMMON.getString("login.username"));
        tfUsername.setStyle(STYLE);
        tfUsername.setPrefSize(250, 44);
        pfPassword = new PasswordField();
        pfPassword.setPromptText(I18n.COMMON.getString("login.password"));
        pfPassword.setStyle(STYLE);
        pfPassword.setPrefSize(250, 44);
        btnLogin = new Button(I18n.COMMON.getString("action.login"));
        btnLogin.setPrefSize(250, 36);
        btnLogin.setStyle(STYLE);

        formPane.add(imgLogin, 0, 0);
        GridPane.setHalignment(imgLogin, HPos.CENTER);
        formPane.add(tfUsername, 0, 2);
        formPane.add(pfPassword, 0, 3);
        formPane.add(btnLogin, 0, 5);
        GridPane.setHalignment(btnLogin, HPos.RIGHT);

        getChildren().add(formPane);
    }

    @Override
    public String getHeaderTitle() {
        return I18n.COMMON.getString("login.title");
    }

    @Override
    public TextField getTfUsername() {
        return tfUsername;
    }

    @Override
    public PasswordField getPfPassword() {
        return pfPassword;
    }

    @Override
    public Button getBtnLogin() {
        return btnLogin;
    }

    @Override
    public Node asNode() {
        return this;
    }

}
