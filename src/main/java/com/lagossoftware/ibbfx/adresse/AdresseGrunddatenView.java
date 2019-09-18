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
package com.lagossoftware.ibbfx.adresse;

import com.lagossoftware.ibbfx.entity.Anrede;
import com.lagossoftware.ibbfx.entity.Bundesland;
import com.lagossoftware.ibbfx.entity.Land;
import com.lagossoftware.ibbfx.entity.Titel;
import com.lagossoftware.lagosfx.mvp.TabView;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.controlsfx.validation.ValidationSupport;

/**
 * Adresse Grunddaten view interface
 *
 * @author Cem Ikta
 */
public interface AdresseGrunddatenView extends TabView {

    TextField getTfAdresseNr();

    ComboBox<Anrede> getCbAnrede();

    ComboBox<Titel> getCbTitel();

    TextField getTfVorname();

    TextField getTfNachname();

    TextField getTfFirma1();

    TextField getTfFirma2();

    TextField getTfFirma3();

    TextField getTfFirma4();

    TextField getTfKennzeichen1();

    TextField getTfKennzeichen2();

    TextField getTfPostfachPlz();

    TextField getTfPostfach();

    TextField getTfStrasse();

    TextField getTfPlz();

    TextField getTfOrt();

    ComboBox<Land> getCbLand();

    ComboBox<Bundesland> getCbBundesland();

    TextField getTfAnredeBrief();

    ValidationSupport getValidationSupport();

}
