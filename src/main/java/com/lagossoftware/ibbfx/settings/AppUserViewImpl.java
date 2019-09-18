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
package com.lagossoftware.ibbfx.settings;

import com.lagossoftware.ibbfx.app.I18n;
import com.lagossoftware.ibbfx.entity.AppUser;
import com.lagossoftware.ibbfx.entity.AppUserGroup;
import com.lagossoftware.ibbfx.entity.AppUserRole;
import com.lagossoftware.lagosfx.mvp.AbstractBrowserFormView;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import org.controlsfx.validation.Validator;

/**
 * App user view implementation
 *
 * @author Cem Ikta
 */
public class AppUserViewImpl extends AbstractBrowserFormView<AppUser> implements AppUserView {

    private TextField tfUsername;
    private PasswordField pfPassword;
    private TextField tfName;
    private ComboBox<AppUserRole> cbAppUserRole;
    private ComboBox<AppUserGroup> cbAppUserGroup;
    private CheckBox chbActive;
    
    public AppUserViewImpl() {
        super();
        buildView();
    }

    private void buildView() {
    }

    @Override
    public GridPane getFormPane() {
        GridPane formPane = new GridPane();
        formPane.setHgap(10);
        formPane.setVgap(10);
        
        formPane.add(new Label(I18n.SETTINGS.getString("appUser.username")), 0, 0);
        tfUsername = new TextField();
        tfUsername.setPrefWidth(300);
        getValidationSupport().registerValidator(tfUsername, 
                Validator.createEmptyValidator(I18n.COMMON.getString("validation.required")));
        formPane.add(tfUsername, 1, 0);
        
        formPane.add(new Label(I18n.SETTINGS.getString("appUser.password")), 0, 1);
        pfPassword = new PasswordField();
        pfPassword.setPrefWidth(300);
        getValidationSupport().registerValidator(pfPassword, 
                Validator.createEmptyValidator(I18n.COMMON.getString("validation.required")));
        formPane.add(pfPassword, 1, 1);

        formPane.add(new Label(I18n.SETTINGS.getString("appUser.name")), 0, 2);
        tfName = new TextField();
        tfName.setPrefWidth(300);
        getValidationSupport().registerValidator(tfName, 
                Validator.createEmptyValidator(I18n.COMMON.getString("validation.required")));
        formPane.add(tfName, 1, 2);
        
        formPane.add(new Label(I18n.SETTINGS.getString("appUser.appUserRole")), 0, 3);
        cbAppUserRole = new ComboBox<>();
        cbAppUserRole.setPrefWidth(300);
        formPane.add(cbAppUserRole, 1, 3);

        formPane.add(new Label(I18n.SETTINGS.getString("appUser.appUserGroup")), 0, 4);
        cbAppUserGroup = new ComboBox<>();
        cbAppUserGroup.setPrefWidth(300);
        formPane.add(cbAppUserGroup, 1, 4);

        formPane.add(new Label(I18n.SETTINGS.getString("appUser.active")), 0, 5);
        chbActive = new CheckBox();
        formPane.add(chbActive, 1, 5);

        return formPane;
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
    public TextField getTfName() {
        return tfName;
    }

    @Override
    public ComboBox<AppUserRole> getCbAppUserRole() {
        return cbAppUserRole;
    }

    @Override
    public ComboBox<AppUserGroup> getCbAppUserGroup() {
        return cbAppUserGroup;
    }

    @Override
    public CheckBox getChbActive() {
        return chbActive;
    }
    
    @Override
    public String getHeaderTitle() {
        return I18n.SETTINGS.getString("appUser.title");
    }

}
