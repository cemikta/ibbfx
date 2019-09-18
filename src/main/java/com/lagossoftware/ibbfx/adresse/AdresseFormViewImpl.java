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

import com.lagossoftware.ibbfx.app.I18n;
import com.lagossoftware.ibbfx.entity.Adresse;
import com.lagossoftware.lagosfx.mvp.AbstractMultiPageFormView;

/**
 * Adresse form view implementation
 *
 * @author Cem Ikta
 */
public class AdresseFormViewImpl extends AbstractMultiPageFormView<Adresse> 
        implements AdresseFormView {
    

    public AdresseFormViewImpl() {
        super();
        buildView();
    }

    private void buildView() {
    }
    
    @Override
    public String getHeaderTitle() {
        return I18n.ADRESSE.getString("adresse.form.title");
    }
    
}
