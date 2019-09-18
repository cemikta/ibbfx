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
package com.lagossoftware.ibbfx.settings;

import com.google.inject.Inject;
import com.lagossoftware.ibbfx.IbbManagement;
import com.lagossoftware.ibbfx.app.AppPlaces;
import com.lagossoftware.ibbfx.app.I18n;
import com.lagossoftware.ibbfx.entity.AppRight;
import com.lagossoftware.ibbfx.entity.AppUserGroup;
import com.lagossoftware.ibbfx.service.AppUserGroupService;
import com.lagossoftware.lagosfx.mvp.AbstractBrowserFormPresenter;
import com.lagossoftware.lagosfx.navigation.NavigationManager;
import com.lagossoftware.lagosfx.service.QueryParameter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.BorderPane;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * App user group presenter
 *
 * @author Cem Ikta
 */
public class AppUserGroupPresenter extends AbstractBrowserFormPresenter<AppUserGroup,
        AppUserGroupView, AppUserGroupService> {

    @Inject
    public AppUserGroupPresenter(final NavigationManager navigationManager,
                                 final Logger logger, final AppUserGroupView view,
                                 AppUserGroupService service) {
        super(navigationManager, logger, view, service);
    }

    @Override
    public void start(BorderPane container) {
        container.setCenter(getView().asNode());
        bind();
        onSearch();
        view.getSearchBox().requestFocus();
    }

    @Override
    public void bind() {
        super.bind();

        view.getBtnCheck().setOnAction((ActionEvent event) -> {
            onCheckAllOptions();
        });

        view.getBtnUncheck().setOnAction((ActionEvent event) -> {
            onUncheckAllOptions();
        });
    }

    private void onCheckAllOptions() {
        ObservableList<AppRightModel> appRightModelList = view.getAppRightTableView().getItems();
        for (AppRightModel appRightModel : appRightModelList) {
            appRightModel.setAllowRead(true);
            appRightModel.setAllowCreate(true);
            appRightModel.setAllowEdit(true);
            appRightModel.setAllowDelete(true);
        }
    }

    private void onUncheckAllOptions() {
        ObservableList<AppRightModel> appRightModelList = view.getAppRightTableView().getItems();
        for (AppRightModel appRightModel : appRightModelList) {
            if (!appRightModel.getModuleName().equals("App")) {
                appRightModel.setAllowRead(false);
                appRightModel.setAllowCreate(false);
                appRightModel.setAllowEdit(false);
                appRightModel.setAllowDelete(false);
            }
        }
    }

    @Override
    public String getNamedQuery() {
        return AppUserGroup.FIND_ALL;
    }

    @Override
    public String getNamedQueryWithFilter() {
        return AppUserGroup.FIND_BY_NAME;
    }

    @Override
    public Map<String, Object> getFilterParameters() {
        return QueryParameter.with("name", 
                "%" + view.getSearchBox().getText() + "%").parameters();
    }

    @Override
    public void requestFocusAfterAddNew() {
        view.getTfName().requestFocus();
    }
    
    @Override
    public void popFields() {
        view.getTfName().setText(entity.getName());
        if (isNewModel()) {
            view.getAppRightTableView().setItems(getDefaultAppRights());
        } else {
            ObservableList<AppRightModel> appRightModelList = FXCollections.observableArrayList();
            for (AppRight appRight: entity.getAppRightList()) {
                AppRightModel appRightModel = new AppRightModel(appRight.getId(),
                        appRight.getModuleName(), appRight.getObjectName1().toString(),
                        appRight.getObjectName2() != null ? appRight.getObjectName2().toString() : "",
                        appRight.getName(), appRight.isAllowRead(), appRight.isAllowCreate(),
                        appRight.isAllowEdit(), appRight.isAllowDelete());
                appRightModelList.add(appRightModel);
            }
            view.getAppRightTableView().setItems(appRightModelList);
        }
        view.getSortColumn().setSortType(TableColumn.SortType.ASCENDING);
        view.getAppRightTableView().getSortOrder().add(view.getSortColumn());
        view.getSortColumn().setSortable(true);
    }

    @Override
    public void pushFields() {
        entity.setName(view.getTfName().getText());

        List<AppRight> appRightList = new ArrayList<>();
        for (AppRightModel appRightModel: view.getAppRightTableView().getItems()) {
            AppRight appRight = new AppRight(entity, appRightModel.getModuleName(),
                    AppPlaces.valueOf(appRightModel.getObjectName1()),
                    StringUtils.isNotEmpty(appRightModel.getObjectName2()) ? AppPlaces.valueOf(appRightModel.getObjectName2()) : null,
                    appRightModel.getName(), appRightModel.isAllowRead(), appRightModel.isAllowCreate(),
                    appRightModel.isAllowEdit(), appRightModel.isAllowDelete());
            appRightList.add(appRight);
        }
        entity.getAppRightList().clear();
        entity.getAppRightList().addAll(appRightList);

        if (isNewModel()) {
            entity.setCreatedBy(IbbManagement.get().getLoggedUser().getId());
        } else {
            entity.setUpdatedBy(IbbManagement.get().getLoggedUser().getId());
        }
    }

    private ObservableList<AppRightModel> getDefaultAppRights() {
        ObservableList<AppRightModel> appRightData = FXCollections.observableArrayList();
        // dashboard
        appRightData.add(new AppRightModel(0L, "App", AppPlaces.DASHBOARD.toString(), null,
                I18n.COMMON.getString("dashboard.title"), true, true, true, true));

        // adresse
        appRightData.add(new AppRightModel(0L, I18n.COMMON.getString("appModule.adresse"),
                AppPlaces.ADDRESSE.toString(), AppPlaces.ADDRESSE_FORM.toString(),
                I18n.ADRESSE.getString("adresse.browser.title"),
                false, false, false, false));

        // eingangsrechnung
        appRightData.add(new AppRightModel(0L, I18n.COMMON.getString("appModule.eingangsrechnung"),
                AppPlaces.EINGANGSRECHNUNG.toString(), AppPlaces.EINGANGSRECHNUNG_FORM.toString(),
                I18n.EINGANGSRECHNUNG.getString("eingangsrechnung.browser.title"),
                false, false, false, false));

        // planung
        appRightData.add(new AppRightModel(0L, I18n.COMMON.getString("appModule.planung"),
                AppPlaces.PLANUNG.toString(), AppPlaces.PLANUNG_FORM.toString(),
                I18n.PLANUNG.getString("planung.browser.title"),
                false, false, false, false));

        // fahrtkasse
        appRightData.add(new AppRightModel(0L, I18n.COMMON.getString("appModule.fahrtkasse"),
                AppPlaces.FAHRTKASSE.toString(), AppPlaces.FAHRTKASSE_FORM.toString(),
                I18n.FAHRTKASSE.getString("fahrtkasse.browser.title"),
                false, false, false, false));

        // grundlagen
        appRightData.add(new AppRightModel(0L, I18n.COMMON.getString("appModule.grundlagen"),
                AppPlaces.ANREDE.toString(), null, I18n.GRUNDLAGEN.getString("anrede.title"),
                false, false, false, false));
        appRightData.add(new AppRightModel(0L, I18n.COMMON.getString("appModule.grundlagen"),
                AppPlaces.ARBEITSAUFWAND.toString(), null, I18n.GRUNDLAGEN.getString("arbeitsaufwand.title"),
                false, false, false, false));
        appRightData.add(new AppRightModel(0L, I18n.COMMON.getString("appModule.grundlagen"),
                AppPlaces.BUNDESLAND.toString(), null, I18n.GRUNDLAGEN.getString("bundesland.title"),
                false, false, false, false));
        appRightData.add(new AppRightModel(0L, I18n.COMMON.getString("appModule.grundlagen"),
                AppPlaces.FACHBEREICH.toString(), null, I18n.GRUNDLAGEN.getString("fachbereich.title"),
                false, false, false, false));
        appRightData.add(new AppRightModel(0L, I18n.COMMON.getString("appModule.grundlagen"),
                AppPlaces.GESCHAEFTSBEREICH.toString(), null, I18n.GRUNDLAGEN.getString("geschaeftsbereich.title"),
                false, false, false, false));
        appRightData.add(new AppRightModel(0L, I18n.COMMON.getString("appModule.grundlagen"),
                AppPlaces.KONTO.toString(), null, I18n.GRUNDLAGEN.getString("konto.title"),
                false, false, false, false));
        appRightData.add(new AppRightModel(0L, I18n.COMMON.getString("appModule.grundlagen"),
                AppPlaces.LAND.toString(), null, I18n.GRUNDLAGEN.getString("land.title"),
                false, false, false, false));
        appRightData.add(new AppRightModel(0L, I18n.COMMON.getString("appModule.grundlagen"),
                AppPlaces.STICHWORT.toString(), null, I18n.GRUNDLAGEN.getString("stichwort.title"),
                false, false, false, false));
        appRightData.add(new AppRightModel(0L, I18n.COMMON.getString("appModule.grundlagen"),
                AppPlaces.TITEL.toString(), null, I18n.GRUNDLAGEN.getString("titel.title"),
                false, false, false, false));
        appRightData.add(new AppRightModel(0L, I18n.COMMON.getString("appModule.grundlagen"),
                AppPlaces.WAEHRUNG.toString(), null, I18n.GRUNDLAGEN.getString("waehrung.title"),
                false, false, false, false));
        appRightData.add(new AppRightModel(0L, I18n.COMMON.getString("appModule.grundlagen"),
                AppPlaces.ZGFAKTOR.toString(), null, I18n.GRUNDLAGEN.getString("zgfaktor.title"),
                false, false, false, false));

        // reports TODO add rights

        // settings
        appRightData.add(new AppRightModel(0L, I18n.COMMON.getString("appModule.settings"),
                AppPlaces.APP_USER.toString(), null, I18n.SETTINGS.getString("appUser.title"),
                false, false, false, false));
        appRightData.add(new AppRightModel(0L, I18n.COMMON.getString("appModule.settings"),
                AppPlaces.APP_USER_GROUP.toString(), null, I18n.SETTINGS.getString("appUserGroup.title"),
                false, false, false, false));
        appRightData.add(new AppRightModel(0L, I18n.COMMON.getString("appModule.settings"),
                AppPlaces.APP_SETTINGS.toString(), null, I18n.SETTINGS.getString("appSettings.title"),
                false, false, false, false));

        return appRightData;
    }

    @Override
    public AppPlaces getPlaceName() {
        return AppPlaces.APP_USER_GROUP;
    }

}
