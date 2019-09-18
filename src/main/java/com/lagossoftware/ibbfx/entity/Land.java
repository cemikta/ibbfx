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

import java.util.List;
import javax.persistence.*;

/**
 * Land entity
 * 
 * @author Cem Ikta
 */
@Entity
@Table(name = "land", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"isocode2"}),
    @UniqueConstraint(columnNames = {"isocode3"}),
    @UniqueConstraint(columnNames = {"name"})})
@NamedQueries({
    @NamedQuery(name = Land.FIND_ALL,
            query = "SELECT l FROM Land l ORDER BY l.name"),
    @NamedQuery(name = Land.FIND_BY_CODE_AND_NAME,
            query = "SELECT l FROM Land l WHERE LOWER(l.isocode2) "
            + "LIKE LOWER(:name) OR LOWER(l.isocode3) LIKE LOWER(:name) OR "
            + "LOWER(l.name) LIKE LOWER(:name) ORDER BY l.name")
})
@AttributeOverride(name = "id", column = @Column(name = "land_id", nullable = false))
public class Land extends BaseEntity {
    
    public static final String FIND_ALL = "Land.findAll";
    public static final String FIND_BY_CODE_AND_NAME = "Land.findByCodeAndName";
    
    @Basic(optional = false)
    @Column(name = "isocode2", nullable = false, length = 2)
    private String isocode2;

    @Basic(optional = false)
    @Column(name = "isocode3", nullable = false, length = 3)
    private String isocode3;
    
    @Basic(optional = false)
    @Column(name = "name", nullable = false, length = 100)
    private String name;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "land")
    private List<Bundesland> bundeslandList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "land", fetch = FetchType.LAZY)
    private List<Adresse> adresseList;
    
    public String getIsocode2() {
        return isocode2;
    }

    public void setIsocode2(String isocode2) {
        this.isocode2 = isocode2;
    }

    public String getIsocode3() {
        return isocode3;
    }

    public void setIsocode3(String isocode3) {
        this.isocode3 = isocode3;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Bundesland> getBundeslandList() {
        return bundeslandList;
    }

    public void setBundeslandList(List<Bundesland> bundeslandList) {
        this.bundeslandList = bundeslandList;
    }
    
    public List<Adresse> getAdresseList() {
        return adresseList;
    }

    public void setAdresseList(List<Adresse> adresseList) {
        this.adresseList = adresseList;
    }

    @Override
    public String toString() {
        return this.name;
    }
    
}
