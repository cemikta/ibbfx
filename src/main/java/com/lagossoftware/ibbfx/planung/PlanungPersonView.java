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

import com.lagossoftware.lagosfx.control.IntegerField;
import com.lagossoftware.lagosfx.control.NumberField;
import com.lagossoftware.lagosfx.mvp.TabView;

/**
 * Planung Personen view interface
 *
 * @author Cem Ikta
 */
public interface PlanungPersonView extends TabView {

    IntegerField getNfZahlendeTnDe();

    IntegerField getNfZahlendeTnAusl();

    IntegerField getNfNichtZahlendeTnDe();

    IntegerField getNfNichtZahlendeTnAusl();

    IntegerField getNfTeilnehmerNachWgbDe();

    IntegerField getNfTeilnehmerNachWgbAusl();

    NumberField getNfZusaetzlicheReiseKosten();

    NumberField getNfZusaetzlicheVerpflegungskosten();

    NumberField getNfWeitereAusgaben();

    IntegerField getNfHauptberuflicheMitarbeiter();

    IntegerField getNfHonorarMitarbeiter();

    IntegerField getNfUnbezahlteMitarbeiter();

    IntegerField getNfFremdeMitarbeiter();

    IntegerField getNfAuslMitarbeiter();

    IntegerField getNfUnterkunfstage();

    NumberField getNfVerpflegungstage();

    IntegerField getNfHonorartage();

    NumberField getNfHonorarsatz();

    NumberField getNfReisekosten();

}
