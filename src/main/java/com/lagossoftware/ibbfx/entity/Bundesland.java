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
import javax.persistence.AttributeOverride;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Bundesland entity
 * 
 * @author Cem Ikta
 */
@Entity
@Table(name = "bundesland", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"name"})})
@NamedQueries({
    @NamedQuery(name = Bundesland.FIND_ALL,
            query = "SELECT b FROM Bundesland b ORDER BY b.land.name, b.name"),
    @NamedQuery(name = Bundesland.FIND_BY_NAME_AND_LAND,
            query = "SELECT b FROM Bundesland b WHERE LOWER(b.name) "
            + "LIKE LOWER(:name) OR LOWER(b.land.name) LIKE LOWER(:name) "
                    + "ORDER BY b.land.name, b.name"),
    @NamedQuery(name = Bundesland.FIND_BY_LAND,
            query = "SELECT b FROM Bundesland b WHERE b.land = :land " 
                    + "ORDER BY b.land.name, b.name")
})
@AttributeOverride(name = "id", column = @Column(name = "bundesland_id", nullable = false))
public class Bundesland extends BaseEntity {

    public static final String FIND_ALL = "Bundesland.findAll";
    public static final String FIND_BY_NAME_AND_LAND = "Bundesland.findByNameAndLand";
    public static final String FIND_BY_LAND = "Bundesland.findByLand";
    
    @Basic(optional = false)
    @Column(name = "name", nullable = false, length = 100)
    private String name;
    
    @JoinColumn(name = "land_id", referencedColumnName = "land_id", nullable = false)
    @ManyToOne(optional = false)
    private Land land;

    @OneToMany(mappedBy = "bundesland")
    private List<Adresse> adresseList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Land getLand() {
        return land;
    }

    public void setLand(Land land) {
        this.land = land;
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
