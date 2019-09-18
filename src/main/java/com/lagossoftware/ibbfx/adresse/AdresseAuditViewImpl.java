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
import javafx.scene.layout.GridPane;

/**
 * Adresse Audit view implementation
 *
 * @author Cem Ikta
 */
public class AdresseAuditViewImpl extends GridPane implements AdresseAuditView {

    private Label lblCreatedBy;
    private Label lblCreatedAt;
    private Label lblUpdatedBy;
    private Label lblUpdatedAt;

    public AdresseAuditViewImpl() {
        buildView();
    }

    private void buildView() {
        setPadding(new Insets(2, 10, 10, 50));
        setHgap(10);
        setVgap(5);

        lblCreatedBy = new Label();
        lblCreatedAt = new Label();
        lblUpdatedBy = new Label();
        lblUpdatedAt = new Label();

        add(new TitledSeparator(I18n.COMMON.getString("common.audit")), 0, 0, 3, 1);
        add(new Label(I18n.COMMON.getString("common.createdAt")), 0, 1);
        add(lblCreatedAt, 1, 1);

        add(new Label(I18n.COMMON.getString("common.createdBy")), 0, 2);
        add(lblCreatedBy, 1, 2);

        add(new Label(I18n.COMMON.getString("common.updatedAt")), 0, 3);
        add(lblUpdatedAt, 1, 3);

        add(new Label(I18n.COMMON.getString("common.updatedBy")), 0, 4);
        add(lblUpdatedBy, 1, 4);

    }

    @Override
    public String getTitle() {
        return I18n.ADRESSE.getString("adresse.form.tabAudit");
    }

    @Override
    public String getIconPath() {
        return Images.AUDIT_26;
    }

    @Override
    public Label getLblCreatedBy() {
        return lblCreatedBy;
    }

    @Override
    public Label getLblCreatedAt() {
        return lblCreatedAt;
    }

    @Override
    public Label getLblUpdatedBy() {
        return lblUpdatedBy;
    }

    @Override
    public Label getLblUpdatedAt() {
        return lblUpdatedAt;
    }

    @Override
    public Node asNode() {
        return this;
    }

}
