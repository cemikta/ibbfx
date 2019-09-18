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
package com.lagossoftware.ibbfx.guice;

import com.google.inject.AbstractModule;
import com.lagossoftware.ibbfx.adresse.*;
import com.lagossoftware.ibbfx.dashboard.DashboardView;
import com.lagossoftware.ibbfx.dashboard.DashboardViewImpl;
import com.lagossoftware.ibbfx.eingangsrechnung.EingangsrechnungFormView;
import com.lagossoftware.ibbfx.eingangsrechnung.EingangsrechnungFormViewImpl;
import com.lagossoftware.ibbfx.eingangsrechnung.EingangsrechnungView;
import com.lagossoftware.ibbfx.eingangsrechnung.EingangsrechnungViewImpl;
import com.lagossoftware.ibbfx.fahrtkasse.*;
import com.lagossoftware.ibbfx.grundlagen.*;
import com.lagossoftware.ibbfx.login.LoginView;
import com.lagossoftware.ibbfx.login.LoginViewImpl;
import com.lagossoftware.ibbfx.planung.*;
import com.lagossoftware.ibbfx.settings.*;
import com.lagossoftware.lagosfx.mvp.AppLayout;
import com.lagossoftware.lagosfx.mvp.AppLayoutImpl;
import com.lagossoftware.lagosfx.navigation.NavigationManager;
import com.lagossoftware.lagosfx.navigation.NavigationManagerImpl;

/**
 * Application Guice injector
 *
 * @author Cem Ikta
 */
public class AppInjector extends AbstractModule {
    
    @Override
    protected void configure() {
        bind(NavigationManager.class).to(NavigationManagerImpl.class);
        bind(AppLayout.class).to(AppLayoutImpl.class);

        // login
        bind(LoginView.class).to(LoginViewImpl.class);

        // dashboard
        bind(DashboardView.class).to(DashboardViewImpl.class);

        // adresse
        bind(AdresseView.class).to(AdresseViewImpl.class);
        bind(AdressePreviewView.class).to(AdressePreviewViewImpl.class);
        bind(AdresseFormView.class).to(AdresseFormViewImpl.class);
        bind(AdresseGrunddatenView.class).to(AdresseGrunddatenViewImpl.class);
        bind(AdresseKommunikationView.class).to(AdresseKommunikationViewImpl.class);
        bind(AdresseBankView.class).to(AdresseBankViewImpl.class);
        bind(AdressePassView.class).to(AdressePassViewImpl.class);
        bind(AdresseStichwortView.class).to(AdresseStichwortViewImpl.class);
        bind(AdresseNotizenView.class).to(AdresseNotizenViewImpl.class);
        bind(AdresseAuditView.class).to(AdresseAuditViewImpl.class);

        // eingangsrechnung
        bind(EingangsrechnungView.class).to(EingangsrechnungViewImpl.class);
        bind(EingangsrechnungFormView.class).to(EingangsrechnungFormViewImpl.class);

        // planung
        bind(PlanungView.class).to(PlanungViewImpl.class);
        bind(PlanungFormView.class).to(PlanungFormViewImpl.class);
        bind(PlanungRahmendatenView.class).to(PlanungRahmendatenViewImpl.class);
        bind(PlanungPersonView.class).to(PlanungPersonViewImpl.class);
        bind(PlanungKostenView.class).to(PlanungKostenViewImpl.class);
        bind(PlanungReisekostenView.class).to(PlanungReisekostenViewImpl.class);
        bind(PlanungZuschussView.class).to(PlanungZuschussViewImpl.class);
        bind(PlanungNamenView.class).to(PlanungNamenViewImpl.class);
        bind(PlanungNotizenView.class).to(PlanungNotizenViewImpl.class);

        // fahrtkasse
        bind(FahrtkasseView.class).to(FahrtkasseViewImpl.class);
        bind(FahrtkasseFormView.class).to(FahrtkasseFormViewImpl.class);
        bind(FahrtkasseErstellenView.class).to(FahrtkasseErstellenViewImpl.class);
        bind(FahrtkasseAbrechnenView.class).to(FahrtkasseAbrechnenViewImpl.class);
        bind(FahrtkasseNotizenView.class).to(FahrtkasseNotizenViewImpl.class);

        // grundlagen
        bind(GrundlagenView.class).to(GrundlagenViewImpl.class);
        bind(AnredeView.class).to(AnredeViewImpl.class);
        bind(ArbeitsaufwandView.class).to(ArbeitsaufwandViewImpl.class);
        bind(BundeslandView.class).to(BundeslandViewImpl.class);
        bind(FachbereichView.class).to(FachbereichViewImpl.class);
        bind(GeschaeftsbereichView.class).to(GeschaeftsbereichViewImpl.class);
        bind(KontoView.class).to(KontoViewImpl.class);
        bind(LandView.class).to(LandViewImpl.class);
        bind(StichwortView.class).to(StichwortViewImpl.class);
        bind(TitelView.class).to(TitelViewImpl.class);
        bind(WaehrungView.class).to(WaehrungViewImpl.class);
        bind(ZgfaktorView.class).to(ZgfaktorViewImpl.class);
        
        // settings
        bind(SettingsView.class).to(SettingsViewImpl.class);
        bind(AppUserView.class).to(AppUserViewImpl.class);
        bind(AppUserGroupView.class).to(AppUserGroupViewImpl.class);
        bind(AppSettingsView.class).to(AppSettingsViewImpl.class);
    }

}
