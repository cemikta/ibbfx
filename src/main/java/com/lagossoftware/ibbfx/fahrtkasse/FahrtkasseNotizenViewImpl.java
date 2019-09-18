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
package com.lagossoftware.ibbfx.fahrtkasse;

import com.lagossoftware.ibbfx.app.I18n;
import com.lagossoftware.ibbfx.app.Images;
import com.lagossoftware.lagosfx.control.TitledSeparator;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;

/**
 * Fahrtkasse Notizen view implementation
 *
 * @author Cem Ikta
 */
public class FahrtkasseNotizenViewImpl extends GridPane implements FahrtkasseNotizenView {

    private TextArea tfNotizen;

    public FahrtkasseNotizenViewImpl() {
        buildView();
    }

    private void buildView() {
        setPadding(new Insets(2, 10, 10, 50));
        setHgap(10);
        setVgap(5);

        tfNotizen = new TextArea();
        tfNotizen.setPrefWidth(500);
        tfNotizen.setPrefRowCount(20);

        add(new TitledSeparator(I18n.FAHRTKASSE.getString("fahrtkasse.form.notizen")), 0, 0, 3, 1);
        add(tfNotizen, 0, 1, 2, 1);
    }

    @Override
    public String getTitle() {
        return I18n.FAHRTKASSE.getString("fahrtkasse.form.tabNotizen");
    }

    @Override
    public String getIconPath() {
        return Images.NOTES_26;
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
