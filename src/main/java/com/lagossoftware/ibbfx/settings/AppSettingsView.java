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
package com.lagossoftware.ibbfx.settings;

import com.lagossoftware.lagosfx.control.IntegerField;
import com.lagossoftware.lagosfx.control.NumberField;
import com.lagossoftware.lagosfx.mvp.View;
import javafx.scene.control.Button;
import org.controlsfx.validation.ValidationSupport;

/**
 * App settings view interface
 *
 * @author Cem Ikta
 */
public interface AppSettingsView extends View {

    String getHeaderTitle();

    Button getBtnSave();

    IntegerField getNfLastAdresseNr();

    IntegerField getNfLastEingangsrechnungBelegNr();

    IntegerField getNfLastFahrtkasseNr();

    NumberField getNfHonorareFuerIbbBegleitung();

    NumberField getNfIbbStrukturkostenProTNProTag();

    NumberField getNfIbbErloes();

    NumberField getNfHaftpflichtUnfallVersicherungProPersonTag();

    NumberField getNfAuslandsreisekrankenVersicherungProPersonTag();

    NumberField getNfKrankenversicherungFuerAuslTNProPersonTag();

    NumberField getNfRegressversicherungVaInDeBis8Tage();

    NumberField getNfRegressversicherungVaInDeBis22Tage();

    NumberField getNfRegressversicherungVaInDeBis42Tage();

    NumberField getNfRegressversicherungVaImAuslandBis8Tage();

    NumberField getNfRegressversicherungVaImAuslandBis22Tage();

    NumberField getNfRegressversicherungVaImAuslandBis42Tage();

    NumberField getNfRechtschutzversicherungVaBis8Tage();

    NumberField getNfRechtschutzversicherungVaBis14Tage();

    NumberField getNfRechtschutzversicherungVaBis22Tage();

    NumberField getNfReisepreissicherungProPersonTag();

    ValidationSupport getValidationSupport();

}
