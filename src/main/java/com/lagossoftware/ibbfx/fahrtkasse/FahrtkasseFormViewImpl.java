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
package com.lagossoftware.ibbfx.fahrtkasse;

import com.lagossoftware.ibbfx.app.I18n;
import com.lagossoftware.ibbfx.app.Images;
import com.lagossoftware.ibbfx.entity.Fahrtkasse;
import com.lagossoftware.lagosfx.common.ViewHelpers;
import com.lagossoftware.lagosfx.mvp.AbstractMultiPageFormView;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;

/**
 * Fahrtkasse form view implementation
 *
 * @author Cem Ikta
 */
public class FahrtkasseFormViewImpl extends AbstractMultiPageFormView<Fahrtkasse>
        implements FahrtkasseFormView {

    private MenuItem mnuFahrtkasse;
    private MenuItem mnuBelegaufstellung;
    private MenuItem mnuKontierung;
    private MenuItem mnuBrief;
    private MenuButton btnPrintPreview;
    private MenuItem mnuFahrtkassePrint;
    private MenuItem mnuBelegaufstellungPrint;
    private MenuItem mnuKontierungPrint;
    private MenuItem mnuBriefPrint;
    private MenuButton btnPrint;

    public FahrtkasseFormViewImpl() {
        super();
        buildView();
    }

    private void buildView() {
        mnuFahrtkasse = new MenuItem(I18n.FAHRTKASSE.getString("fahrtkasse.form.printFahrtkasse"),
                ViewHelpers.createImageView(Images.ARROW_LEFT_16));
        mnuBelegaufstellung = new MenuItem(I18n.FAHRTKASSE.getString("fahrtkasse.form.printBelegaufstellung"),
                ViewHelpers.createImageView(Images.ARROW_LEFT_16));
        mnuKontierung = new MenuItem(I18n.FAHRTKASSE.getString("fahrtkasse.form.printKontierung"),
                ViewHelpers.createImageView(Images.ARROW_LEFT_16));
        mnuBrief = new MenuItem(I18n.FAHRTKASSE.getString("fahrtkasse.form.printBrief"),
                ViewHelpers.createImageView(Images.ARROW_LEFT_16));
        btnPrintPreview = ViewHelpers.createMenuButton(I18n.COMMON.getString("action.printPreview"),
                Images.PRINT_PREVIEW_24);
        btnPrintPreview.getStyleClass().add("left-pill");
        btnPrintPreview.getItems().addAll(mnuFahrtkasse, mnuBelegaufstellung, mnuKontierung, mnuBrief);

        mnuFahrtkassePrint = new MenuItem(I18n.FAHRTKASSE.getString("fahrtkasse.form.printFahrtkasse"),
                ViewHelpers.createImageView(Images.ARROW_LEFT_16));
        mnuBelegaufstellungPrint = new MenuItem(
                I18n.FAHRTKASSE.getString("fahrtkasse.form.printBelegaufstellung"),
                ViewHelpers.createImageView(Images.ARROW_LEFT_16));
        mnuKontierungPrint = new MenuItem(I18n.FAHRTKASSE.getString("fahrtkasse.form.printKontierung"),
                ViewHelpers.createImageView(Images.ARROW_LEFT_16));
        mnuBriefPrint = new MenuItem(I18n.FAHRTKASSE.getString("fahrtkasse.form.printBrief"),
                ViewHelpers.createImageView(Images.ARROW_LEFT_16));
        btnPrint = ViewHelpers.createMenuButton(I18n.COMMON.getString("action.print"), Images.PRINT_24);
        btnPrint.getStyleClass().add("right-pill");
        btnPrint.getItems().addAll(mnuFahrtkassePrint, mnuBelegaufstellungPrint, mnuKontierungPrint,
                mnuBriefPrint);
        HBox.setMargin(btnPrint, new Insets(0, 10, 0, 0));

        getToolBar().getChildren().addAll(btnPrintPreview, btnPrint);
    }
    
    @Override
    public String getHeaderTitle() {
        return I18n.FAHRTKASSE.getString("fahrtkasse.form.title");
    }

    @Override
    public MenuItem getMnuFahrtkasse() {
        return mnuFahrtkasse;
    }

    @Override
    public MenuItem getMnuBelegaufstellung() {
        return mnuBelegaufstellung;
    }

    @Override
    public MenuItem getMnuKontierung() {
        return mnuKontierung;
    }

    @Override
    public MenuItem getMnuBrief() {
        return mnuBrief;
    }

    @Override
    public MenuItem getMnuFahrtkassePrint() {
        return mnuFahrtkassePrint;
    }

    @Override
    public MenuItem getMnuBelegaufstellungPrint() {
        return mnuBelegaufstellungPrint;
    }

    @Override
    public MenuItem getMnuKontierungPrint() {
        return mnuKontierungPrint;
    }

    @Override
    public MenuItem getMnuBriefPrint() {
        return mnuBriefPrint;
    }

}
