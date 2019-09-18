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

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * App User Group entity
 *
 * @author Cem Ikta
 */
@Entity
@Table(name = "app_user_group", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"name"})
})
@NamedQueries({
    @NamedQuery(name = AppUserGroup.FIND_ALL,
            query = "SELECT a FROM AppUserGroup a ORDER BY a.name"),
    @NamedQuery(name = AppUserGroup.FIND_BY_NAME,
            query = "SELECT a FROM AppUserGroup a WHERE LOWER(a.name) "
            + "LIKE LOWER(:name) ORDER BY a.name")
})
@AttributeOverride(name = "id", column = @Column(name = "app_user_group_id", nullable = false))
public class AppUserGroup extends BaseEntity {

    public static final String FIND_ALL = "AppUserGroup.findAll";
    public static final String FIND_BY_NAME = "AppUserGroup.findByName";

    @Basic(optional = false)
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "appUserGroup")
    private List<AppUser> appUserList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "appUserGroup", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<AppRight> appRightList = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public List<AppUser> getAppUserList() {
        return appUserList;
    }

    public void setAppUserList(List<AppUser> appUserList) {
        this.appUserList = appUserList;
    }

    public List<AppRight> getAppRightList() {
        return appRightList;
    }

    public void setAppRightList(List<AppRight> appRightList) {
        this.appRightList = appRightList;
    }

    @Override
    public String toString() {
        return this.name;
    }

}
