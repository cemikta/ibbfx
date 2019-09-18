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
package com.lagossoftware.ibbfx.planung;

import com.lagossoftware.ibbfx.app.I18n;
import com.lagossoftware.ibbfx.app.Images;
import com.lagossoftware.ibbfx.entity.Zuschuss;
import com.lagossoftware.lagosfx.common.ViewHelpers;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * PlanungZuschuss List Cell
 *
 * @author Cem Ikta
 */
public class PlanungZuschussListCell extends ListCell<Zuschuss> {

    private GridPane contentBox;

    private Label lblBezeichnung;
    private Label lblBewilligt;
    private Label lblProDeTn;
    private Label lblProDeTnGesamt;
    private Label lblRemove;
    private Label lblProAuslTn;
    private Label lblProAuslTnGesamt;
    private Label lblManuellBerechnet;
    private Label lblGesamt;
    private PlanungZuschussListCellCallback callback;

    public PlanungZuschussListCell(PlanungZuschussListCellCallback callback) {
        super();
        this.callback = callback;

        lblBezeichnung = new Label();
        lblBezeichnung.setMinWidth(200);
        lblBezeichnung.setPrefWidth(200);
        lblBezeichnung.setMaxWidth(200);

        lblBewilligt = new Label();
        lblBewilligt.setMinWidth(200);
        lblBewilligt.setPrefWidth(200);
        lblBewilligt.setMaxWidth(200);

        lblProDeTn = new Label();
        lblProDeTn.setAlignment(Pos.CENTER_RIGHT);
        lblProDeTn.setMinWidth(150);
        lblProDeTn.setPrefWidth(150);
        lblProDeTn.setMaxWidth(150);

        lblProDeTnGesamt = new Label();
        lblProDeTnGesamt.setAlignment(Pos.CENTER_RIGHT);
        lblProDeTnGesamt.setMinWidth(150);
        lblProDeTnGesamt.setPrefWidth(150);
        lblProDeTnGesamt.setMaxWidth(150);

        lblRemove = new Label();
        lblRemove.setGraphic(ViewHelpers.createImageView(Images.REMOVE_16));
        lblRemove.setTooltip(new Tooltip(I18n.COMMON.getString("action.remove")));
        lblRemove.setOnMouseClicked((MouseEvent event) -> {
            callRemoveCallback(getItem());
        });

        lblProAuslTn = new Label();
        lblProAuslTn.setAlignment(Pos.CENTER_RIGHT);
        lblProAuslTn.setMinWidth(150);
        lblProAuslTn.setPrefWidth(150);
        lblProAuslTn.setMaxWidth(150);

        lblProAuslTnGesamt = new Label();
        lblProAuslTnGesamt.setAlignment(Pos.CENTER_RIGHT);
        lblProAuslTnGesamt.setMinWidth(150);
        lblProAuslTnGesamt.setPrefWidth(150);
        lblProAuslTnGesamt.setMaxWidth(150);

        lblManuellBerechnet = new Label();
        lblManuellBerechnet.setAlignment(Pos.CENTER_RIGHT);
        lblManuellBerechnet.setMinWidth(150);
        lblManuellBerechnet.setPrefWidth(150);
        lblManuellBerechnet.setMaxWidth(150);

        lblGesamt = new Label();
        lblGesamt.setAlignment(Pos.CENTER_RIGHT);
        lblGesamt.setMinWidth(150);
        lblGesamt.setPrefWidth(150);
        lblGesamt.setMaxWidth(150);

        contentBox = new GridPane();
        contentBox.setPadding(new Insets(2, 2, 2, 2));
        contentBox.setHgap(10);
        contentBox.setVgap(5);

        contentBox.add(lblBezeichnung, 0, 0);
        contentBox.add(new Label(I18n.PLANUNG.getString("zuschussListCell.proDeTn")), 1, 0);
        contentBox.add(lblProDeTn, 2, 0);
        contentBox.add(lblProDeTnGesamt, 3, 0);
        contentBox.add(lblRemove, 4, 0);

        contentBox.add(lblBewilligt, 0, 1);
        contentBox.add(new Label(I18n.PLANUNG.getString("zuschussListCell.proAuslTn")), 1, 1);
        contentBox.add(lblProAuslTn, 2, 1);
        contentBox.add(lblProAuslTnGesamt, 3, 1);

        contentBox.add(new Label(I18n.PLANUNG.getString("zuschussListCell.manuellBerechnet")), 1, 2);
        contentBox.add(lblManuellBerechnet, 3, 2);

        contentBox.add(new Label(I18n.PLANUNG.getString("zuschussListCell.gesamt")), 1, 3);
        contentBox.add(lblGesamt, 3, 3);

        setText(null);
    }

    @Override
    public void updateItem(Zuschuss item, boolean empty) {
        super.updateItem(item, empty);
        setEditable(false);
        
        if (item != null) {
            NumberFormat numberFormat = new DecimalFormat("#,###,###,##0.00");
            lblBezeichnung.setText(item.getBezeichnung());
            if (item.getBewilligt()) {
                lblBewilligt.setText("Bewilligt: Ja");
            } else {
                lblBewilligt.setText("Bewilligt: Nein");
            }
            lblProDeTn.setText(numberFormat.format(item.getProDeTn()));
            lblProDeTnGesamt.setText(numberFormat.format(item.getProDeTnGesamt()));
            lblProAuslTn.setText(numberFormat.format(item.getProAuslTn()));
            lblProAuslTnGesamt.setText(numberFormat.format(item.getProAuslTnGesamt()));
            lblManuellBerechnet.setText(numberFormat.format(item.getManuellBerechnet()));
            lblGesamt.setText(numberFormat.format(item.getGesamt()));
            setGraphic(contentBox);
        } else {
            setGraphic(null);
        }
    }
    
    private void callRemoveCallback(Zuschuss zuschuss) {
        if (callback != null) {
            callback.onRemove(zuschuss);
        }
    }
    
}
