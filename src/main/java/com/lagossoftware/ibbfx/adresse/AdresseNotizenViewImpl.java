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
import com.lagossoftware.ibbfx.app.Images;
import com.lagossoftware.lagosfx.control.TitledSeparator;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 * Adresse Notizen view implementation
 *
 * @author Cem Ikta
 */
public class AdresseNotizenViewImpl extends GridPane implements AdresseNotizenView {

    private TextField tfFreiesFeld1;
    private TextField tfFreiesFeld2;
    private TextField tfFreiesFeld3;
    private TextArea tfNotizen;

    public AdresseNotizenViewImpl() {
        buildView();
    }

    private void buildView() {
        setPadding(new Insets(2, 10, 10, 50));
        setHgap(10);
        setVgap(5);

        tfFreiesFeld1 = new TextField();
        tfFreiesFeld1.setPrefWidth(300);
        tfFreiesFeld2 = new TextField();
        tfFreiesFeld2.setPrefWidth(300);
        tfFreiesFeld3 = new TextField();
        tfFreiesFeld3.setPrefWidth(300);
        tfNotizen = new TextArea();
        tfNotizen.setPrefWidth(300);
        
        add(new Label(I18n.ADRESSE.getString("adresse.form.freiesFeld1")), 0, 0);
        add(tfFreiesFeld1, 1, 0);
        add(new Label(I18n.ADRESSE.getString("adresse.form.freiesFeld2")), 0, 1);
        add(tfFreiesFeld2, 1, 1);
        add(new Label(I18n.ADRESSE.getString("adresse.form.freiesFeld3")), 0, 2);
        add(tfFreiesFeld3, 1, 2);
        
        add(new TitledSeparator(I18n.ADRESSE.getString("adresse.form.notizen")), 0, 3, 3, 1);
        add(tfNotizen, 0, 4, 2, 1);
    }

    @Override
    public String getTitle() {
        return I18n.ADRESSE.getString("adresse.form.tabNotizen");
    }

    @Override
    public String getIconPath() {
        return Images.NOTES_26;
    }
    
    @Override
    public TextField getTfFreiesFeld1() {
        return tfFreiesFeld1;
    }

    @Override
    public TextField getTfFreiesFeld2() {
        return tfFreiesFeld2;
    }
    
    @Override
    public TextField getTfFreiesFeld3() {
        return tfFreiesFeld3;
    }

    @Override
    public TextArea getTfNotizen() {
        return tfNotizen;
    }

    @Override
    public Node asNode() {
        return this;
    }

}
