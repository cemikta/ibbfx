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
import com.lagossoftware.ibbfx.app.Images;
import com.lagossoftware.lagosfx.common.ViewHelpers;
import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * Settings view implementation
 *
 * @author Cem Ikta
 */
public class SettingsViewImpl extends VBox implements SettingsView {

    private Hyperlink userLink;
    private Hyperlink userGroupLink;
    private Hyperlink appSettingsLink;
    
    public SettingsViewImpl() {
        super();
        buildView();
    }

    private void buildView() {
        setId("sidebar-menu");
        setSpacing(5);

        Label lblTitle = new Label(I18n.SETTINGS.getString("settings.menu.title"));
        lblTitle.getStyleClass().add("menu-title");
        lblTitle.setGraphicTextGap(10);
       
        userLink = ViewHelpers.createIconHyperlink(
                I18n.SETTINGS.getString("settings.user"), 
                Images.APP_USER_16);
        userGroupLink = ViewHelpers.createIconHyperlink(
                I18n.SETTINGS.getString("settings.userGroup"),
                Images.APP_USER_GROUP_16);
        appSettingsLink = ViewHelpers.createIconHyperlink(
                I18n.SETTINGS.getString("settings.appSettings"), 
                Images.APP_SETTINGS_16);

        getChildren().addAll(lblTitle, userLink, userGroupLink, appSettingsLink);
    }

    @Override
    public Hyperlink getUserLink() {
        return userLink;
    }

    @Override
    public Hyperlink getUserGroupLink() {
        return userGroupLink;
    }

    @Override
    public Hyperlink getAppSettingsLink() {
        return appSettingsLink;
    }

    @Override
    public Node asNode() {
        return this;
    }

}
