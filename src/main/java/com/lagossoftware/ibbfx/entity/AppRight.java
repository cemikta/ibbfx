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
package com.lagossoftware.ibbfx.entity;

import com.lagossoftware.ibbfx.app.AppPlaces;

import javax.persistence.*;

/**
 * App Right entity
 * 
 * @author Cem Ikta
 */
@Entity
@Table(name = "app_right")
@NamedQueries({
    @NamedQuery(name = AppRight.FIND_ALL,
            query = "SELECT a FROM AppRight a ORDER BY a.moduleName")
})
@AttributeOverride(name = "id", column = @Column(name = "app_right_id", nullable = false))
public class AppRight extends BaseEntity {

    public static final String FIND_ALL = "AppRight.findAll";

    @JoinColumn(name = "app_user_group_id", referencedColumnName = "app_user_group_id", nullable = false)
    @ManyToOne(optional = false)
    private AppUserGroup appUserGroup;

    @Basic(optional = false)
    @Column(name = "module_name", nullable = false, length = 50)
    private String moduleName;

    @Basic(optional = false)
    @Column(name = "object_name1", nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private AppPlaces objectName1;

    @Column(name = "object_name2", length = 50)
    @Enumerated(EnumType.STRING)
    private AppPlaces objectName2;

    @Basic(optional = false)
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "allow_read")
    private boolean allowRead;

    @Column(name = "allow_create")
    private boolean allowCreate;

    @Column(name = "allow_edit")
    private boolean allowEdit;

    @Column(name = "allow_delete")
    private boolean allowDelete;

    public AppRight() {
    }

    public AppRight(AppUserGroup appUserGroup, String moduleName, AppPlaces objectName1,
                    AppPlaces objectName2, String name, boolean allowRead, boolean allowCreate,
                    boolean allowEdit, boolean allowDelete) {
        this.appUserGroup = appUserGroup;
        this.moduleName = moduleName;
        this.objectName1 = objectName1;
        this.objectName2 = objectName2;
        this.name = name;
        this.allowRead = allowRead;
        this.allowCreate = allowCreate;
        this.allowEdit = allowEdit;
        this.allowDelete = allowDelete;
    }

    public AppUserGroup getAppUserGroup() {
        return appUserGroup;
    }

    public void setAppUserGroup(AppUserGroup appUserGroup) {
        this.appUserGroup = appUserGroup;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public AppPlaces getObjectName1() {
        return objectName1;
    }

    public void setObjectName1(AppPlaces objectName1) {
        this.objectName1 = objectName1;
    }

    public AppPlaces getObjectName2() {
        return objectName2;
    }

    public void setObjectName2(AppPlaces objectName2) {
        this.objectName2 = objectName2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAllowRead() {
        return allowRead;
    }

    public void setAllowRead(boolean allowRead) {
        this.allowRead = allowRead;
    }

    public boolean isAllowCreate() {
        return allowCreate;
    }

    public void setAllowCreate(boolean allowCreate) {
        this.allowCreate = allowCreate;
    }

    public boolean isAllowEdit() {
        return allowEdit;
    }

    public void setAllowEdit(boolean allowEdit) {
        this.allowEdit = allowEdit;
    }

    public boolean isAllowDelete() {
        return allowDelete;
    }

    public void setAllowDelete(boolean allowDelete) {
        this.allowDelete = allowDelete;
    }

    @Override
    public String toString() {
        return "entity.AppRight[ appRightId=" + getId() + " ]";
    }
    
}
