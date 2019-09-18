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

import com.lagossoftware.ibbfx.entity.Adresse;
import com.lagossoftware.ibbfx.entity.Planung;
import com.lagossoftware.ibbfx.entity.ZahlungsartType;
import com.lagossoftware.lagosfx.control.IntegerField;
import com.lagossoftware.lagosfx.control.NumberField;
import com.lagossoftware.lagosfx.mvp.TabView;
import javafx.scene.control.*;
import org.controlsfx.validation.ValidationSupport;

/**
 * Fahrtkasse Erstellen view interface
 *
 * @author Cem Ikta
 */
public interface FahrtkasseErstellenView extends TabView {

    TextField getTfFahrtkasseNr();

    DatePicker getDpBelegdatum();

    ComboBox<Planung> getCbPlanung();

    Label getLblPlanungTitle();

    ComboBox<Adresse> getCbAdresse();

    NumberField getNfBetrag();

    ComboBox<ZahlungsartType> getCbZahlungsart();

    DatePicker getDpZahlenAm();

    CheckBox getChbAbgerechnet();

    TextField getTfStrasse();

    TextField getTfPlz();

    TextField getTfOrt();

    TextField getTfIban();

    TextField getTfBic();

    TextField getTfBank();

    TextField getTfKontoNr();

    TextField getTfBlz();

    ValidationSupport getValidationSupport();

}
