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

import com.lagossoftware.ibbfx.adresse.StichwortListCellCallback;
import com.lagossoftware.ibbfx.app.I18n;
import com.lagossoftware.ibbfx.app.Images;
import com.lagossoftware.ibbfx.entity.AdresseStichwort;
import com.lagossoftware.ibbfx.entity.FahrtkasseWaehrung;
import com.lagossoftware.lagosfx.common.ViewHelpers;
import com.lagossoftware.lagosfx.control.NumberField;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Removal List Cell
 *
 * @author Cem Ikta
 */
public class FahrtkasseWaehrungListCell extends ListCell<FahrtkasseWaehrung> {

    private HBox contentBox;
    private Label lblItem;
    private Label lblRemove;
    private FahrtkasseWaehrungListCellCallback callback;

    public FahrtkasseWaehrungListCell(FahrtkasseWaehrungListCellCallback callback) {
        super();
        this.callback = callback;

        lblItem = new Label();
        lblItem.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(lblItem, Priority.ALWAYS);
        lblRemove = new Label();
        lblRemove.setGraphic(ViewHelpers.createImageView(Images.REMOVE_16));
        lblRemove.setTooltip(new Tooltip(I18n.COMMON.getString("action.remove")));
        lblRemove.setOnMouseClicked((MouseEvent event) -> {
            callRemoveCallback(getItem());
        });

        contentBox = new HBox(4, lblItem, lblRemove);        
        setText(null);
    }

    @Override
    public void updateItem(FahrtkasseWaehrung item, boolean empty) {
        super.updateItem(item, empty);
        setEditable(false);
        
        if (item != null) {
            NumberFormat numberFormat = new DecimalFormat("#,###,###,##0.00");
            lblItem.setText(item.getMultiplyEur() + " = " +
                    numberFormat.format(item.getKurs()) + " " + item.getWaehrung());
            setGraphic(contentBox);
        } else {
            setGraphic(null);
        }
    }
    
    private void callRemoveCallback(FahrtkasseWaehrung fahrtkasseWaehrung) {
        if (callback != null) {
            callback.onRemove(fahrtkasseWaehrung);
        }
    }
    
}
