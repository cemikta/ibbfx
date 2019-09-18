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

import com.lagossoftware.ibbfx.IbbManagement;
import com.lagossoftware.ibbfx.app.I18n;
import com.lagossoftware.ibbfx.app.Images;
import com.lagossoftware.ibbfx.entity.FahrtkasseWaehrung;
import com.lagossoftware.ibbfx.entity.Waehrung;
import com.lagossoftware.ibbfx.service.WaehrungService;
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
import java.util.List;

/**
 * FahrtkasseWaehrung dialog
 *
 * @author Cem Ikta
 */
public class FahrtkasseWaehrungDialog extends Dialog<FahrtkasseWaehrung> {

    private FahrtkasseWaehrung fahrtkasseWaehrung = null;
    private WaehrungService service;

    private NumberField nfKurs;
    private ComboBox<Waehrung> cbWaehrung;
    private ValidationSupport validationSupport;

    public FahrtkasseWaehrungDialog(FahrtkasseWaehrung fahrtkasseWaehrung, WaehrungService service) {
        this.fahrtkasseWaehrung = fahrtkasseWaehrung;
        this.service = service;
        buildView();
    }

    private void buildView() {
        setTitle(I18n.FAHRTKASSE.getString("waehrungDialog.title"));
        setHeaderText(I18n.FAHRTKASSE.getString("waehrungDialog.header"));
        setGraphic(ViewHelpers.createImageView(Images.WAEHRUNG_HEADER_32));
        ButtonType btnTypeApply = new ButtonType(I18n.COMMON.getString("action.apply"),
                ButtonBar.ButtonData.OK_DONE);
        getDialogPane().getButtonTypes().addAll(btnTypeApply, ButtonType.CANCEL);

        validationSupport = new ValidationSupport();
        validationSupport.setValidationDecorator(new GraphicValidationDecoration());

        nfKurs = new NumberField(new DecimalFormat("#,###,###,##0.00"));
        nfKurs.setPromptText(I18n.FAHRTKASSE.getString("waehrungDialog.kurs"));
        nfKurs.setMinWidth(150);
        nfKurs.setPrefWidth(150);
        nfKurs.setMaxWidth(150);
        validationSupport.registerValidator(nfKurs,
                Validator.createEmptyValidator(I18n.COMMON.getString("validation.required")));

        cbWaehrung = new ComboBox<>();
        cbWaehrung.setPrefWidth(100);
        validationSupport.registerValidator(cbWaehrung,
                Validator.createEmptyValidator(I18n.COMMON.getString("validation.required")));
        List<Waehrung> waehrungList = service.getListWithNamedQuery(Waehrung.FIND_ALL);
        cbWaehrung.getItems().addAll(waehrungList);

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20, 100, 10, 10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        gridPane.add(new Label(I18n.FAHRTKASSE.getString("waehrungDialog.euro")), 0, 0);
        gridPane.add(nfKurs, 1, 0);
        gridPane.add(cbWaehrung, 2, 0);

        final Button btnApply = (Button) getDialogPane().lookupButton(btnTypeApply);
        btnApply.addEventFilter(ActionEvent.ACTION, (event) -> {
            if (validationSupport.isInvalid()) {
                event.consume();
            }
        });

        getDialogPane().setContent(gridPane);

        Platform.runLater(() -> nfKurs.requestFocus());

        setResultConverter(dialogButton -> {
            if (dialogButton == btnTypeApply) {
                if (!validationSupport.isInvalid()) {
                    push();
                    return fahrtkasseWaehrung;
                }
            }
            return null;
        });
    }

    public void pop() {
        nfKurs.setValue(fahrtkasseWaehrung.getKurs() != null ? fahrtkasseWaehrung.getKurs() : BigDecimal.ZERO);
        cbWaehrung.getSelectionModel().select(fahrtkasseWaehrung.getWaehrung());
    }

    public void push() {
        fahrtkasseWaehrung.setMultiplyEur("1 EUR");
        fahrtkasseWaehrung.setWaehrung(cbWaehrung.getSelectionModel().getSelectedItem());
        fahrtkasseWaehrung.setKurs(nfKurs.getValue());

        if (fahrtkasseWaehrung.getId() == null) {
            fahrtkasseWaehrung.setCreatedBy(IbbManagement.get().getLoggedUser().getId());
        } else {
            fahrtkasseWaehrung.setUpdatedBy(IbbManagement.get().getLoggedUser().getId());
        }
    }

}
