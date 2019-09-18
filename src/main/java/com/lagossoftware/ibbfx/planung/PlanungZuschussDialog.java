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

import com.lagossoftware.ibbfx.IbbManagement;
import com.lagossoftware.ibbfx.app.I18n;
import com.lagossoftware.ibbfx.app.Images;
import com.lagossoftware.ibbfx.entity.Zuschuss;
import com.lagossoftware.lagosfx.common.ViewHelpers;
import com.lagossoftware.lagosfx.control.NumberField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import org.controlsfx.validation.decoration.GraphicValidationDecoration;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * PlanungZuschuss dialog
 *
 * @author Cem Ikta
 */
public class PlanungZuschussDialog extends Dialog<Zuschuss> {

    private Zuschuss zuschuss = null;

    private TextField tfBezeichnung;
    private NumberField nfProDeTn;
    private NumberField nfProDeTnGesamt;
    private NumberField nfProAuslTn;
    private NumberField nfProAuslTnGesamt;
    private NumberField nfManuellBerechnet;
    private NumberField nfGesamt;
    private CheckBox chbBewilligt;
    private ValidationSupport validationSupport;

    public PlanungZuschussDialog(Zuschuss zuschuss) {
        this.zuschuss = zuschuss;
        buildView();
    }

    private void buildView() {
        setTitle(I18n.PLANUNG.getString("zuschussDialog.title"));
        setHeaderText(I18n.PLANUNG.getString("zuschussDialog.header"));
        setGraphic(ViewHelpers.createImageView(Images.ZUSCHUSS_HEADER_32));
        ButtonType btnTypeApply = new ButtonType(I18n.COMMON.getString("action.apply"),
                ButtonBar.ButtonData.OK_DONE);
        getDialogPane().getButtonTypes().addAll(btnTypeApply, ButtonType.CANCEL);

        validationSupport = new ValidationSupport();
        validationSupport.setValidationDecorator(new GraphicValidationDecoration());

        tfBezeichnung = new TextField();
        tfBezeichnung.setPrefWidth(300);
        validationSupport.registerValidator(tfBezeichnung,
                Validator.createEmptyValidator(I18n.COMMON.getString("validation.required")));

        nfProDeTn = new NumberField(new DecimalFormat("#,###,###,##0.00"));
        nfProDeTn.setMinWidth(150);
        nfProDeTn.setMaxWidth(150);
        nfProDeTn.setPrefWidth(150);

        nfProDeTnGesamt = new NumberField(new DecimalFormat("#,###,###,##0.00"));
        nfProDeTnGesamt.setEditable(false);
        nfProDeTnGesamt.setMinWidth(150);
        nfProDeTnGesamt.setMaxWidth(150);
        nfProDeTnGesamt.setPrefWidth(150);

        nfProAuslTn = new NumberField(new DecimalFormat("#,###,###,##0.00"));
        nfProAuslTn.setMinWidth(150);
        nfProAuslTn.setMaxWidth(150);
        nfProAuslTn.setPrefWidth(150);

        nfProAuslTnGesamt = new NumberField(new DecimalFormat("#,###,###,##0.00"));
        nfProAuslTnGesamt.setEditable(false);
        nfProAuslTnGesamt.setMinWidth(150);
        nfProAuslTnGesamt.setMaxWidth(150);
        nfProAuslTnGesamt.setPrefWidth(150);

        nfManuellBerechnet = new NumberField(new DecimalFormat("#,###,###,##0.00"));
        nfManuellBerechnet.setMinWidth(150);
        nfManuellBerechnet.setMaxWidth(150);
        nfManuellBerechnet.setPrefWidth(150);

        nfGesamt = new NumberField(new DecimalFormat("#,###,###,##0.00"));
        nfGesamt.setEditable(false);
        nfGesamt.setMinWidth(150);
        nfGesamt.setMaxWidth(150);
        nfGesamt.setPrefWidth(150);

        chbBewilligt = new CheckBox(I18n.PLANUNG.getString("zuschussDialog.bewilligt"));

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20, 50, 20, 20));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        gridPane.add(new Label(I18n.PLANUNG.getString("zuschussDialog.bezeichnung")), 0, 0);
        gridPane.add(tfBezeichnung, 1, 0, 2, 1);

        gridPane.add(new Label(I18n.PLANUNG.getString("zuschussDialog.proDeTn")), 0, 1);
        gridPane.add(nfProDeTn, 1, 1);
        gridPane.add(nfProDeTnGesamt, 2, 1);

        gridPane.add(new Label(I18n.PLANUNG.getString("zuschussDialog.proAuslTn")), 0, 2);
        gridPane.add(nfProAuslTn, 1, 2);
        gridPane.add(nfProAuslTnGesamt, 2, 2);

        gridPane.add(new Label(I18n.PLANUNG.getString("zuschussDialog.manuellBerechnet")), 0, 3);
        gridPane.add(nfManuellBerechnet, 2, 3);

        gridPane.add(new Label(I18n.PLANUNG.getString("zuschussDialog.gesamt")), 0, 4);
        gridPane.add(nfGesamt, 2, 4);

        gridPane.add(chbBewilligt, 2, 5);

        final Button btnApply = (Button) getDialogPane().lookupButton(btnTypeApply);
        btnApply.addEventFilter(ActionEvent.ACTION, (event) -> {
            if (validationSupport.isInvalid()) {
                event.consume();
            }
        });

        getDialogPane().setContent(gridPane);

        Platform.runLater(() -> tfBezeichnung.requestFocus());

        setResultConverter(dialogButton -> {
            if (dialogButton == btnTypeApply) {
                if (!validationSupport.isInvalid()) {
                    push();
                    return zuschuss;
                }
            }
            return null;
        });
    }

    private void computeBetragInEur() {

    }

    public void pop() {
        tfBezeichnung.setText(zuschuss.getBezeichnung());
        nfProDeTn.setValue(zuschuss.getProDeTn() != null ? zuschuss.getProDeTn() : BigDecimal.ZERO);
        nfProDeTnGesamt.setValue(zuschuss.getProDeTnGesamt() != null ?
                zuschuss.getProDeTnGesamt() : BigDecimal.ZERO);
        nfProAuslTn.setValue(zuschuss.getProAuslTn() != null ? zuschuss.getProAuslTn() : BigDecimal.ZERO);
        nfProAuslTnGesamt.setValue(zuschuss.getProAuslTnGesamt() != null ?
                zuschuss.getProAuslTnGesamt() : BigDecimal.ZERO);
        nfManuellBerechnet.setValue(zuschuss.getManuellBerechnet() != null ?
                zuschuss.getManuellBerechnet() : BigDecimal.ZERO);
        nfGesamt.setValue(zuschuss.getGesamt() != null ? zuschuss.getGesamt() : BigDecimal.ZERO);
        chbBewilligt.setSelected(zuschuss.getBewilligt() != null ? zuschuss.getBewilligt() : false);
    }

    public void push() {
        zuschuss.setBezeichnung(tfBezeichnung.getText());
        zuschuss.setProDeTn(nfProDeTn.getValue());
        zuschuss.setProDeTnGesamt(nfProDeTnGesamt.getValue());
        zuschuss.setProAuslTn(nfProAuslTn.getValue());
        zuschuss.setProAuslTnGesamt(nfProAuslTnGesamt.getValue());
        zuschuss.setManuellBerechnet(nfManuellBerechnet.getValue());
        zuschuss.setGesamt(nfGesamt.getValue());
        zuschuss.setBewilligt(chbBewilligt.isSelected());

        if (zuschuss.getId() == null) {
            zuschuss.setCreatedBy(IbbManagement.get().getLoggedUser().getId());
        } else {
            zuschuss.setUpdatedBy(IbbManagement.get().getLoggedUser().getId());
        }
    }

}
