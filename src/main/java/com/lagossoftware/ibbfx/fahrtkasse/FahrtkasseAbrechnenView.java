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

import com.lagossoftware.ibbfx.entity.FahrtkasseDetail;
import com.lagossoftware.ibbfx.entity.FahrtkasseWaehrung;
import com.lagossoftware.lagosfx.control.NumberField;
import com.lagossoftware.lagosfx.mvp.TabView;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import org.controlsfx.validation.ValidationSupport;

/**
 * Fahrtkasse Abrechnen view interface
 *
 * @author Cem Ikta
 */
public interface FahrtkasseAbrechnenView extends TabView {

    Button getBtnAddNewKurs();

    Button getBtnEditKurs();

    ListView<FahrtkasseWaehrung> getWaehrungListView();

    Button getBtnAddNewDetail();

    Button getBtnEditDetail();

    ListView<FahrtkasseDetail> getDetailListView();

    NumberField getNfAnfangsBetrag();

    NumberField getNfAusgabenGesamt();

    Label getLblAusgabenBelege();

    NumberField getNfEinnahmenGesamt();

    Label getLblEinnahmenBelege();

    NumberField getNfSaldo();

    DatePicker getDpAbgerechnetAm();

    ValidationSupport getValidationSupport();

}
