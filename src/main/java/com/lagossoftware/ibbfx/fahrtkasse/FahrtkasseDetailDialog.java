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
import com.lagossoftware.ibbfx.entity.FahrtkasseDetail;
import com.lagossoftware.ibbfx.entity.FahrtkasseWaehrung;
import com.lagossoftware.ibbfx.entity.Konto;
import com.lagossoftware.ibbfx.entity.Waehrung;
import com.lagossoftware.ibbfx.service.KontoService;
import com.lagossoftware.ibbfx.service.WaehrungService;
import com.lagossoftware.lagosfx.common.ViewHelpers;
import com.lagossoftware.lagosfx.control.IntegerField;
import com.lagossoftware.lagosfx.control.NumberField;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
 * FahrtkasseDetail dialog
 *
 * @author Cem Ikta
 */
public class FahrtkasseDetailDialog extends Dialog<FahrtkasseDetail> {

    private FahrtkasseDetail fahrtkasseDetail = null;
    private List<FahrtkasseWaehrung> fahrtkasseWaehrungList;
    private WaehrungService waehrungService;
    private KontoService kontoService;

    private IntegerField nfNummer;
    private TextField tfEmpfaenger;
    private TextField tfZweck;
    private NumberField nfBetrag;
    private ComboBox<Waehrung> cbWaehrung;
    private NumberField nfBetragInEur;
    private ComboBox<Konto> cbKonto;
    private ValidationSupport validationSupport;

    public FahrtkasseDetailDialog(FahrtkasseDetail fahrtkasseDetail,
                                  List<FahrtkasseWaehrung> fahrtkasseWaehrungList,
                                  WaehrungService waehrungService, KontoService kontoService) {
        this.fahrtkasseDetail = fahrtkasseDetail;
        this.fahrtkasseWaehrungList = fahrtkasseWaehrungList;
        this.waehrungService = waehrungService;
        this.kontoService = kontoService;
        buildView();
    }

    private void buildView() {
        setTitle(I18n.FAHRTKASSE.getString("detailDialog.title"));
        setHeaderText(I18n.FAHRTKASSE.getString("detailDialog.header"));
        setGraphic(ViewHelpers.createImageView(Images.DETAIL_HEADER_32));
        ButtonType btnTypeApply = new ButtonType(I18n.COMMON.getString("action.apply"),
                ButtonBar.ButtonData.OK_DONE);
        getDialogPane().getButtonTypes().addAll(btnTypeApply, ButtonType.CANCEL);

        validationSupport = new ValidationSupport();
        validationSupport.setValidationDecorator(new GraphicValidationDecoration());

        nfNummer = new IntegerField();
        nfNummer.setMinWidth(150);
        nfNummer.setPrefWidth(150);
        nfNummer.setMaxWidth(150);
        validationSupport.registerValidator(nfNummer,
                Validator.createEmptyValidator(I18n.COMMON.getString("validation.required")));

        tfEmpfaenger = new TextField();
        tfEmpfaenger.setPrefWidth(300);
        validationSupport.registerValidator(tfEmpfaenger,
                Validator.createEmptyValidator(I18n.COMMON.getString("validation.required")));

        tfZweck = new TextField();
        tfZweck.setPrefWidth(300);
        validationSupport.registerValidator(tfZweck,
                Validator.createEmptyValidator(I18n.COMMON.getString("validation.required")));

        nfBetrag = new NumberField(new DecimalFormat("#,###,###,##0.00"));
        nfBetrag.setMinWidth(150);
        nfBetrag.setPrefWidth(150);
        nfBetrag.setMaxWidth(150);
        validationSupport.registerValidator(nfBetrag,
                Validator.createEmptyValidator(I18n.COMMON.getString("validation.required")));
        nfBetrag.valueProperty().addListener((observable, oldValue, newValue) -> {
            computeBetragInEur();
        });

        cbWaehrung = new ComboBox<>();
        cbWaehrung.setMinWidth(150);
        cbWaehrung.setPrefWidth(150);
        cbWaehrung.setMaxWidth(150);
        validationSupport.registerValidator(cbWaehrung,
                Validator.createEmptyValidator(I18n.COMMON.getString("validation.required")));
        List<Waehrung> waehrungList = waehrungService.getListWithNamedQuery(Waehrung.FIND_ALL);
        cbWaehrung.getItems().addAll(waehrungList);
        cbWaehrung.valueProperty().addListener((observable, oldValue, newValue) -> {
            computeBetragInEur();
        });

        nfBetragInEur = new NumberField(new DecimalFormat("#,###,###,##0.00"));
        nfBetragInEur.setMinWidth(150);
        nfBetragInEur.setPrefWidth(150);
        nfBetragInEur.setMaxWidth(150);
        nfBetragInEur.setEditable(false);
        validationSupport.registerValidator(nfBetragInEur,
                Validator.createEmptyValidator(I18n.COMMON.getString("validation.required")));

        cbKonto = new ComboBox<>();
        cbKonto.setPrefWidth(300);
        validationSupport.registerValidator(cbKonto,
                Validator.createEmptyValidator(I18n.COMMON.getString("validation.required")));
        List<Konto> kontoList = kontoService.getListWithNamedQuery(Konto.FIND_ALL);
        cbKonto.getItems().addAll(kontoList);

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20, 50, 20, 20));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        gridPane.add(new Label(I18n.FAHRTKASSE.getString("detailDialog.nummer")), 0, 0);
        gridPane.add(nfNummer, 1, 0);

        gridPane.add(new Label(I18n.FAHRTKASSE.getString("detailDialog.empfaenger")), 0, 1);
        gridPane.add(tfEmpfaenger, 1, 1, 2, 1);

        gridPane.add(new Label(I18n.FAHRTKASSE.getString("detailDialog.zweck")), 0, 2);
        gridPane.add(tfZweck, 1, 2, 2, 1);

        gridPane.add(new Label(I18n.FAHRTKASSE.getString("detailDialog.betragWaehrung")), 0, 3);
        gridPane.add(nfBetrag, 1, 3);
        gridPane.add(cbWaehrung, 2, 3);

        gridPane.add(new Label(I18n.FAHRTKASSE.getString("detailDialog.betragInEur")), 0, 4);
        gridPane.add(nfBetragInEur, 1, 4);

        gridPane.add(new Label(I18n.FAHRTKASSE.getString("detailDialog.konto")), 0, 5);
        gridPane.add(cbKonto, 1, 5, 2, 1);

        final Button btnApply = (Button) getDialogPane().lookupButton(btnTypeApply);
        btnApply.addEventFilter(ActionEvent.ACTION, (event) -> {
            if (validationSupport.isInvalid()) {
                event.consume();
            }
        });

        getDialogPane().setContent(gridPane);

        Platform.runLater(() -> nfNummer.requestFocus());

        setResultConverter(dialogButton -> {
            if (dialogButton == btnTypeApply) {
                if (!validationSupport.isInvalid()) {
                    push();
                    return fahrtkasseDetail;
                }
            }
            return null;
        });
    }

    private void computeBetragInEur() {
        BigDecimal betrag = nfBetrag.getValue();
        BigDecimal kurs = BigDecimal.ZERO;
        BigDecimal betragInEur = BigDecimal.ZERO;

        Waehrung selectedWaehrung = cbWaehrung.getSelectionModel().getSelectedItem();
        for(FahrtkasseWaehrung fahrtkasseWaehrung : fahrtkasseWaehrungList) {
            if (fahrtkasseWaehrung.getWaehrung().equals(selectedWaehrung)) {
                kurs = fahrtkasseWaehrung.getKurs();
            }
        }

        if (betrag.compareTo(BigDecimal.ZERO) > 0 ) {
            if (kurs.compareTo(BigDecimal.ZERO) > 0) {
                betragInEur = betrag.divide(kurs, BigDecimal.ROUND_HALF_UP);
            } else {
                betragInEur = betrag;
            }
        } else {
            betragInEur = BigDecimal.ZERO;
        }

        nfBetragInEur.setValue(betragInEur);
    }

    public void pop() {
        nfNummer.setValue(fahrtkasseDetail.getNummer() != null ? fahrtkasseDetail.getNummer() : 0);
        tfEmpfaenger.setText(fahrtkasseDetail.getEmpfaenger());
        tfZweck.setText(fahrtkasseDetail.getZweck());
        nfBetrag.setValue(fahrtkasseDetail.getBetrag() != null ? fahrtkasseDetail.getBetrag() :
                BigDecimal.ZERO);
        cbWaehrung.getSelectionModel().select(fahrtkasseDetail.getWaehrung());
        nfBetragInEur.setValue(fahrtkasseDetail.getBetragInEur() != null ?
                fahrtkasseDetail.getBetragInEur() : BigDecimal.ZERO);
        cbKonto.getSelectionModel().select(fahrtkasseDetail.getKonto());
    }

    public void push() {
        fahrtkasseDetail.setNummer(nfNummer.getValue());
        fahrtkasseDetail.setEmpfaenger(tfEmpfaenger.getText());
        fahrtkasseDetail.setZweck(tfZweck.getText());
        fahrtkasseDetail.setBetrag(nfBetrag.getValue());
        fahrtkasseDetail.setWaehrung(cbWaehrung.getSelectionModel().getSelectedItem());
        fahrtkasseDetail.setBetragInEur(nfBetragInEur.getValue());
        fahrtkasseDetail.setKonto(cbKonto.getSelectionModel().getSelectedItem());

        if (fahrtkasseDetail.getId() == null) {
            fahrtkasseDetail.setCreatedBy(IbbManagement.get().getLoggedUser().getId());
        } else {
            fahrtkasseDetail.setUpdatedBy(IbbManagement.get().getLoggedUser().getId());
        }
    }

}
