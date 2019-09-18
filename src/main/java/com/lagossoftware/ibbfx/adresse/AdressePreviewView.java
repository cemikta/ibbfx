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

import com.lagossoftware.lagosfx.mvp.View;
import javafx.scene.control.Label;

/**
 * Adresse view interface
 *
 * @author Cem Ikta
 */
public interface AdressePreviewView extends View {

    Label getLblAnredeTitelName();

    Label getLblFirma1();

    Label getLblFirma2();

    Label getLblPostfachPlz();

    Label getLblPostfach();

    Label getLblStrasse();

    Label getLblPlzOrt();

    Label getLblLand();

    Label getLblMobiltelefon();

    Label getLblTelefonPrivat();

    Label getLblTelefonDienst();

    Label getLblFaxPrivat();

    Label getLblFaxDienst();

    Label getLblEmail();

    Label getLblHomepage();

    Label getLblSkype();

    Label getLblFacebook();

    Label getLblTwitter();

    Label getLblXing();

    Label getLblLinkedin();

    Label getLblGooglePlus();

}
