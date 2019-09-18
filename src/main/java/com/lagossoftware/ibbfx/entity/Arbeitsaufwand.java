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
import java.math.BigDecimal;
import java.util.List;

/**
 * Arbeitsaufwand entity 
 * 
 * @author Cem Ikta
 */
@Entity
@Table(name = "arbeitsaufwand", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"bezeichnung"})})
@NamedQueries({
    @NamedQuery(name = Arbeitsaufwand.FIND_ALL,
            query = "SELECT a FROM Arbeitsaufwand a ORDER BY a.bezeichnung"),
    @NamedQuery(name = Arbeitsaufwand.FIND_BY_BEZEICHNUNG,
            query = "SELECT a FROM Arbeitsaufwand a WHERE LOWER(a.bezeichnung) "
            + "LIKE LOWER(:bezeichnung) ORDER BY a.bezeichnung")
})
@AttributeOverride(name = "id", column = @Column(name = "arbeitsaufwand_id", nullable = false))
public class Arbeitsaufwand extends BaseEntity {

    public static final String FIND_ALL = "Arbeitsaufwand.findAll";
    public static final String FIND_BY_BEZEICHNUNG = "Arbeitsaufwand.findByBezeichnung";
    
    @Basic(optional = false)
    @Column(name = "bezeichnung", nullable = false, length = 100)
    private String bezeichnung;
    
    @Column(name = "aufschlag", precision = 5, scale = 2)
    private BigDecimal aufschlag;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "arbeitsaufwand")
    private List<Planung> planungList;

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public BigDecimal getAufschlag() {
        return aufschlag;
    }

    public void setAufschlag(BigDecimal aufschlag) {
        this.aufschlag = aufschlag;
    }

    public List<Planung> getPlanungList() {
        return planungList;
    }

    public void setPlanungList(List<Planung> planungList) {
        this.planungList = planungList;
    }

    @Override
    public String toString() {
        return this.bezeichnung;
    }

}
