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

import com.lagossoftware.ibbfx.entity.Arbeitsaufwand;
import com.lagossoftware.ibbfx.entity.Fachbereich;
import com.lagossoftware.ibbfx.entity.Geschaeftsbereich;
import com.lagossoftware.ibbfx.entity.Zgfaktor;
import com.lagossoftware.lagosfx.control.IntegerField;
import com.lagossoftware.lagosfx.control.NumberField;
import com.lagossoftware.lagosfx.mvp.TabView;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.controlsfx.validation.ValidationSupport;

/**
 * Planung Rahmendaten view interface
 *
 * @author Cem Ikta
 */
public interface PlanungRahmendatenView extends TabView {

    IntegerField getNfPlanungNr();

    TextField getTfZusatz();

    TextField getTfTitel();

    TextField getTfVeranstaltungOrt();

    DatePicker getDpVeranstaltungBeginn();

    DatePicker getDpVeranstaltungEnde();

    TextField getTfVorbereitungstreffenOrt();

    DatePicker getDpVorbereitungstreffenBeginn();

    DatePicker getDpVorbereitungstreffenEnde();

    TextField getTfNachbereitungstreffenOrt();

    DatePicker getDpNachbereitungstreffenBeginn();

    DatePicker getDpNachbereitungstreffenEnde();

    ComboBox<Fachbereich> getCbFachbereich();

    ComboBox<Geschaeftsbereich> getCbGeschaeftsbereich();

    ComboBox<Arbeitsaufwand> getCbArbeitsaufwand();

    NumberField getNfArbeitsaufwandAufschlag();

    IntegerField getNfTageNachWbg();

    ComboBox<Zgfaktor> getCbZgfaktor();

    NumberField getNfZgfaktorAufschlag();

    ValidationSupport getValidationSupport();

}
