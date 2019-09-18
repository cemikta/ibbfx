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
package com.lagossoftware.ibbfx.grundlagen;

import com.lagossoftware.ibbfx.app.I18n;
import com.lagossoftware.ibbfx.app.Images;
import com.lagossoftware.lagosfx.common.ViewHelpers;
import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * Grundlagen view implementation
 *
 * @author Cem Ikta
 */
public class GrundlagenViewImpl extends VBox implements GrundlagenView {

    private Hyperlink anredeLink;
    private Hyperlink arbeitsaufwandLink;
    private Hyperlink bundeslandLink;
    private Hyperlink fachbereichLink;
    private Hyperlink geschaeftsbereichLink;
    private Hyperlink kontenrahmenLink;
    private Hyperlink landLink;
    private Hyperlink stichwortLink;
    private Hyperlink titelLink;
    private Hyperlink waehrungLink;
    private Hyperlink zielgruppenfaktorLink;

    public GrundlagenViewImpl() {
        super();
        buildView();
    }

    private void buildView() {
        setId("sidebar-menu");
        setSpacing(5);

        Label lblTitle = new Label(I18n.GRUNDLAGEN.getString("grundlagen.menu.title"));
        lblTitle.getStyleClass().add("menu-title");
        lblTitle.setGraphicTextGap(10);
       
        anredeLink = ViewHelpers.createIconHyperlink(
                I18n.GRUNDLAGEN.getString("grundlagen.anrede"), 
                Images.FOLDER_16);
        arbeitsaufwandLink = ViewHelpers.createIconHyperlink(
                I18n.GRUNDLAGEN.getString("grundlagen.arbeitsaufwand"), 
                Images.FOLDER_16);
        bundeslandLink = ViewHelpers.createIconHyperlink(
                I18n.GRUNDLAGEN.getString("grundlagen.bundesland"), 
                Images.FOLDER_16);
        fachbereichLink = ViewHelpers.createIconHyperlink(
                I18n.GRUNDLAGEN.getString("grundlagen.fachbereich"), 
                Images.FOLDER_16);
        geschaeftsbereichLink = ViewHelpers.createIconHyperlink(
                I18n.GRUNDLAGEN.getString("grundlagen.geschaeftsbereich"), 
                Images.FOLDER_16);
        kontenrahmenLink = ViewHelpers.createIconHyperlink(
                I18n.GRUNDLAGEN.getString("grundlagen.kontenrahmen"), 
                Images.FOLDER_16);
        landLink = ViewHelpers.createIconHyperlink(
                I18n.GRUNDLAGEN.getString("grundlagen.land"), 
                Images.FOLDER_16);
        stichwortLink = ViewHelpers.createIconHyperlink(
                I18n.GRUNDLAGEN.getString("grundlagen.stichwort"), 
                Images.FOLDER_16);
        titelLink = ViewHelpers.createIconHyperlink(
                I18n.GRUNDLAGEN.getString("grundlagen.titel"), 
                Images.FOLDER_16);
        waehrungLink = ViewHelpers.createIconHyperlink(
                I18n.GRUNDLAGEN.getString("grundlagen.waehrung"), 
                Images.FOLDER_16);
        zielgruppenfaktorLink = ViewHelpers.createIconHyperlink(
                I18n.GRUNDLAGEN.getString("grundlagen.zielgruppenfaktor"), 
                Images.FOLDER_16);

        getChildren().addAll(lblTitle, anredeLink, arbeitsaufwandLink, bundeslandLink,
                fachbereichLink, geschaeftsbereichLink, kontenrahmenLink, landLink, 
                stichwortLink, titelLink, waehrungLink, zielgruppenfaktorLink);
    }

    @Override
    public Hyperlink getAnredeLink() {
        return anredeLink;
    }

    @Override
    public Hyperlink getArbeitsaufwandLink() {
        return arbeitsaufwandLink;
    }

    @Override
    public Hyperlink getBundeslandLink() {
        return bundeslandLink;
    }

    @Override
    public Hyperlink getFachbereichLink() {
        return fachbereichLink;
    }

    @Override
    public Hyperlink getGeschaeftsbereichLink() {
        return geschaeftsbereichLink;
    }

    @Override
    public Hyperlink getKontenrahmenLink() {
        return kontenrahmenLink;
    }

    @Override
    public Hyperlink getLandLink() {
        return landLink;
    }

    @Override
    public Hyperlink getStichwortLink() {
        return stichwortLink;
    }

    @Override
    public Hyperlink getTitelLink() {
        return titelLink;
    }

    @Override
    public Hyperlink getWaehrungLink() {
        return waehrungLink;
    }

    @Override
    public Hyperlink getZielgruppenfaktorLink() {
        return zielgruppenfaktorLink;
    }

    @Override
    public Node asNode() {
        return this;
    }

}
