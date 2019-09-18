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
package com.lagossoftware.ibbfx.app;

import com.google.inject.Singleton;
import com.lagossoftware.ibbfx.adresse.AdresseFormPresenter;
import com.lagossoftware.ibbfx.adresse.AdressePresenter;
import com.lagossoftware.ibbfx.dashboard.DashboardPresenter;
import com.lagossoftware.ibbfx.eingangsrechnung.EingangsrechnungFormPresenter;
import com.lagossoftware.ibbfx.eingangsrechnung.EingangsrechnungPresenter;
import com.lagossoftware.ibbfx.fahrtkasse.FahrtkasseFormPresenter;
import com.lagossoftware.ibbfx.fahrtkasse.FahrtkassePresenter;
import com.lagossoftware.ibbfx.grundlagen.*;
import com.lagossoftware.ibbfx.login.LoginPresenter;
import com.lagossoftware.ibbfx.planung.PlanungFormPresenter;
import com.lagossoftware.ibbfx.planung.PlanungPresenter;
import com.lagossoftware.ibbfx.settings.AppSettingsPresenter;
import com.lagossoftware.ibbfx.settings.AppUserGroupPresenter;
import com.lagossoftware.ibbfx.settings.AppUserPresenter;
import com.lagossoftware.ibbfx.settings.SettingsPresenter;
import com.lagossoftware.lagosfx.mvp.Presenter;

import java.util.HashMap;
import java.util.Map;

/**
 * Application presenter mapper
 *
 * @author Cem Ikta
 */
@Singleton
public class PresenterMapper {

    private Map<AppPlaces, Class<? extends Presenter>> presenterMap;

    public PresenterMapper() {
        presenterMap = new HashMap<>();

        // login
        presenterMap.put(AppPlaces.LOGIN, LoginPresenter.class);

        // dashboard
        presenterMap.put(AppPlaces.DASHBOARD, DashboardPresenter.class);

        // adresse
        presenterMap.put(AppPlaces.ADDRESSE, AdressePresenter.class);
        presenterMap.put(AppPlaces.ADDRESSE_FORM, AdresseFormPresenter.class);

        // eingangsrechnung
        presenterMap.put(AppPlaces.EINGANGSRECHNUNG, EingangsrechnungPresenter.class);
        presenterMap.put(AppPlaces.EINGANGSRECHNUNG_FORM, EingangsrechnungFormPresenter.class);

        // planung
        presenterMap.put(AppPlaces.PLANUNG, PlanungPresenter.class);
        presenterMap.put(AppPlaces.PLANUNG_FORM, PlanungFormPresenter.class);

        // fahrtkasse
        presenterMap.put(AppPlaces.FAHRTKASSE, FahrtkassePresenter.class);
        presenterMap.put(AppPlaces.FAHRTKASSE_FORM, FahrtkasseFormPresenter.class);

        // grundlagen
        presenterMap.put(AppPlaces.GRUNDLAGEN, GrundlagenPresenter.class);
        presenterMap.put(AppPlaces.ANREDE, AnredePresenter.class);
        presenterMap.put(AppPlaces.ARBEITSAUFWAND, ArbeitsaufwandPresenter.class);
        presenterMap.put(AppPlaces.BUNDESLAND, BundeslandPresenter.class);
        presenterMap.put(AppPlaces.FACHBEREICH, FachbereichPresenter.class);
        presenterMap.put(AppPlaces.GESCHAEFTSBEREICH, GeschaeftsbereichPresenter.class);
        presenterMap.put(AppPlaces.KONTO, KontoPresenter.class);
        presenterMap.put(AppPlaces.LAND, LandPresenter.class);
        presenterMap.put(AppPlaces.STICHWORT, StichwortPresenter.class);
        presenterMap.put(AppPlaces.TITEL, TitelPresenter.class);
        presenterMap.put(AppPlaces.WAEHRUNG, WaehrungPresenter.class);
        presenterMap.put(AppPlaces.ZGFAKTOR, ZgfaktorPresenter.class);

        // settings
        presenterMap.put(AppPlaces.SETTINGS, SettingsPresenter.class);
        presenterMap.put(AppPlaces.APP_USER, AppUserPresenter.class);
        presenterMap.put(AppPlaces.APP_USER_GROUP, AppUserGroupPresenter.class);
        presenterMap.put(AppPlaces.APP_SETTINGS, AppSettingsPresenter.class);
    }

    public Class<? extends Presenter> findPresenter(AppPlaces place) {
        if (presenterMap.containsKey(place)) {
            return presenterMap.get(place);
        } else {
            throw new RuntimeException("Presenter not found for this place!");
        }
    }

}
