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
import com.lagossoftware.ibbfx.entity.FahrtkasseDetail;
import com.lagossoftware.ibbfx.entity.FahrtkasseWaehrung;
import com.lagossoftware.ibbfx.entity.Konto;
import com.lagossoftware.ibbfx.entity.Waehrung;
import com.lagossoftware.lagosfx.common.ViewHelpers;
import com.lagossoftware.lagosfx.control.IntegerField;
import com.lagossoftware.lagosfx.control.NumberField;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * FahrtkasseDetail List Cell
 *
 * @author Cem Ikta
 */
public class FahrtkasseDetailListCell extends ListCell<FahrtkasseDetail> {

    private GridPane contentBox;
    private Label lblNr;
    private Label lblEmpfaenger;
    private Label lblBetrag;
    private Label lblRemove;
    private Label lblZweck;
    private Label lblBetragInEur;
    private Label lblKonto;
    private FahrtkasseDetailListCellCallback callback;

    public FahrtkasseDetailListCell(FahrtkasseDetailListCellCallback callback) {
        super();
        this.callback = callback;

        lblNr = new Label();
        lblNr.setMinWidth(60);
        lblNr.setPrefWidth(60);
        lblNr.setMaxWidth(60);
        lblEmpfaenger = new Label();
        lblEmpfaenger.setMinWidth(300);
        lblEmpfaenger.setPrefWidth(300);
        lblEmpfaenger.setMaxWidth(300);
        lblBetrag = new Label();
        lblBetrag.setMinWidth(120);
        lblBetrag.setPrefWidth(120);
        lblBetrag.setMaxWidth(120);
        lblZweck = new Label();
        lblZweck.setMinWidth(300);
        lblZweck.setPrefWidth(300);
        lblZweck.setMaxWidth(300);
        lblBetragInEur = new Label();
        lblBetragInEur.setMinWidth(120);
        lblBetragInEur.setPrefWidth(120);
        lblBetragInEur.setMaxWidth(120);
        lblKonto = new Label();
        lblKonto.setMinWidth(60);
        lblKonto.setPrefWidth(60);
        lblKonto.setMaxWidth(60);
        lblRemove = new Label();
        lblRemove.setGraphic(ViewHelpers.createImageView(Images.REMOVE_16));
        lblRemove.setTooltip(new Tooltip(I18n.COMMON.getString("action.remove")));
        lblRemove.setOnMouseClicked((MouseEvent event) -> {
            callRemoveCallback(getItem());
        });

        contentBox = new GridPane();
        contentBox.setPadding(new Insets(2, 2, 2, 2));
        contentBox.setHgap(10);
        contentBox.setVgap(5);
        contentBox.add(lblNr, 0, 0);
        contentBox.add(lblEmpfaenger, 1, 0);
        contentBox.add(lblBetrag, 2, 0);
        contentBox.add(lblRemove, 3, 0);
        contentBox.add(lblKonto, 0, 1);
        contentBox.add(lblZweck, 1, 1);
        contentBox.add(lblBetragInEur, 2, 1);
        setText(null);
    }

    @Override
    public void updateItem(FahrtkasseDetail item, boolean empty) {
        super.updateItem(item, empty);
        setEditable(false);
        
        if (item != null) {
            NumberFormat numberFormat = new DecimalFormat("#,###,###,##0.00");
            lblNr.setText(item.getNummer().toString());
            lblEmpfaenger.setText(item.getEmpfaenger());
            lblBetrag.setText(numberFormat.format(item.getBetrag()) + " " + item.getWaehrung());
            lblZweck.setText(item.getZweck());
            lblBetragInEur.setText(numberFormat.format(item.getBetragInEur()));
            lblKonto.setText(item.getKonto().getKontoNr());
            setGraphic(contentBox);
        } else {
            setGraphic(null);
        }
    }
    
    private void callRemoveCallback(FahrtkasseDetail fahrtkasseDetail) {
        if (callback != null) {
            callback.onRemove(fahrtkasseDetail);
        }
    }
    
}
