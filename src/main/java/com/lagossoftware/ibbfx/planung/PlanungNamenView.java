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
import com.lagossoftware.ibbfx.entity.Adresse;
import com.lagossoftware.lagosfx.control.NumberField;
import com.lagossoftware.lagosfx.control.TitledSeparator;
import com.lagossoftware.lagosfx.mvp.TabView;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * Planung Namen und Prise view interface
 *
 * @author Cem Ikta
 */
public interface PlanungNamenView extends TabView {

    ComboBox<Adresse> getCbMitarbeiter1();

    ComboBox<Adresse> getCbMitarbeiter2();

    ComboBox<Adresse> getCbPartner();

    TextField getTfPlz();

    TextField getTfOrt();

    TextField getTfMobiltelefon();

    TextField getTfTelefonPrivat();

    TextField getTfTelefonDienst();

    TextField getTfEmail();

    CheckBox getChbAnmeldungIbb();

    CheckBox getChbAnmeldungPartner();

    NumberField getNfFestpreis();

    TextField getTfBedingung1();

    TextField getTfBedingung2();

    TextField getTfBedingung3();

    TextField getTfBedingung4();

    TextField getTfBedingung5();

    TextField getTfBedingung6();

    NumberField getNfPreis1();

    NumberField getNfPreis2();

    NumberField getNfPreis3();

    NumberField getNfPreis4();

    NumberField getNfPreis5();

    NumberField getNfPreis6();

    CheckBox getChbWirdDurchgefuehrt();

    TitledSeparator getKurzbeschreibungEbPlanTitle();

    TextArea getTfKurzbeschreibungEbPlan();

}
