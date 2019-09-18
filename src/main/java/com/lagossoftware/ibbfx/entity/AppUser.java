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

/**
 * App User entity
 * 
 * @author Cem Ikta
 */
@Entity
@Table(name = "app_user", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"username"})})
@NamedQueries({
    @NamedQuery(name = AppUser.FIND_ALL,
            query = "SELECT a FROM AppUser a ORDER BY a.username"),
    @NamedQuery(name = AppUser.FIND_BY_USERNAME_AND_NAME,
            query = "SELECT a FROM AppUser a WHERE LOWER(a.username) "
            + "LIKE LOWER(:name) OR LOWER(a.name) LIKE LOWER(:name) "
                    + "ORDER BY a.username"),
    @NamedQuery(name = AppUser.FIND_ONE_BY_USERNAME,
            query = "SELECT a FROM AppUser a WHERE a.username = :username")
})
@AttributeOverride(name = "id", column = @Column(name = "app_user_id", nullable = false))
public class AppUser extends BaseEntity {
    
    public static final String FIND_ALL = "AppUser.findAll";
    public static final String FIND_BY_USERNAME_AND_NAME = "AppUser.findByUsernameAndName";
    public static final String FIND_ONE_BY_USERNAME = "AppUser.findOneByUsername";
    
    @Basic(optional = false)
    @Column(name = "username", nullable = false, length = 20)
    private String username;

    @Basic(optional = false)
    @Column(name = "password", nullable = false, length = 20)
    private String password;

    @Basic(optional = false)
    @Column(name = "name", nullable = false, length = 100)
    private String name;
    
    @Basic(optional = false)
    @Column(name = "app_user_role", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private AppUserRole appUserRole;

    @JoinColumn(name = "app_user_group_id", referencedColumnName = "app_user_group_id")
    @ManyToOne
    private AppUserGroup appUserGroup;

    @Basic(optional = false)
    @Column(name = "active", nullable = false)
    private boolean active;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public AppUserRole getAppUserRole() {
        return appUserRole;
    }

    public void setAppUserRole(AppUserRole appUserRole) {
        this.appUserRole = appUserRole;
    }

    public AppUserGroup getAppUserGroup() {
        return appUserGroup;
    }

    public void setAppUserGroup(AppUserGroup appUserGroup) {
        this.appUserGroup = appUserGroup;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return this.username + " - " + this.name + " - " + this.appUserRole; 
    }
    
}
