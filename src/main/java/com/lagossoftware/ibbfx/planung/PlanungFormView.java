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

import com.lagossoftware.ibbfx.entity.Planung;
import com.lagossoftware.lagosfx.mvp.MultiPageFormView;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;

/**
 * Planung form view interface
 *
 * @author Cem Ikta
 */
public interface PlanungFormView extends MultiPageFormView<Planung> {

    MenuItem getMnuMassnahmedatenblatt1();

    MenuItem getMnuMassnahmekalkulation();

    MenuItem getMnuMassnahmedatenblatt1Print();

    MenuItem getMnuMassnahmekalkulationPrint();

    Button getBtnPlanungParams();

    Label getLblPlanungNr();

    Label getLblPlanungTitle();

    Label getLblVATage();

    Label getLblZahlendeTN();

    Label getLblNichtZahlendeMitfahrer();

    Label getLblTeilnehmerabhaengigeKosten();

    Label getLblTeilnehmerunabhaengigeKosten();

    Label getLblKostenFuerAlleAuslaendischeTN();

    Label getLblIBBStrukturkosten();

    Label getLblGesamtkosten();

    Label getLblAbzZuschuesse();

    Label getLblPreisKalk();

    Label getLblVerkaufspreis();

    Label getLblIBBErloes();

}
