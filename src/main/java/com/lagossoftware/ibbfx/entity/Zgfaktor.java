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
 * Zgfaktor entity
 * 
 * @author Cem Ikta
 */
@Entity
@Table(name = "zgfaktor", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"bezeichnung"})})
@NamedQueries({
    @NamedQuery(name = Zgfaktor.FIND_ALL,
            query = "SELECT z FROM Zgfaktor z ORDER BY z.bezeichnung"),
    @NamedQuery(name = Zgfaktor.FIND_BY_BEZEICHNUNG,
            query = "SELECT z FROM Zgfaktor z WHERE LOWER(z.bezeichnung) "
            + "LIKE LOWER(:bezeichnung) ORDER BY z.bezeichnung")
})
@AttributeOverride(name = "id", column = @Column(name = "zgfaktor_id", nullable = false))
public class Zgfaktor extends BaseEntity {
    
    public static final String FIND_ALL = "Zgfaktor.findAll";
    public static final String FIND_BY_BEZEICHNUNG = "Zgfaktor.findByBezeichnung";
    
    @Basic(optional = false)
    @Column(name = "bezeichnung", nullable = false, length = 100)
    private String bezeichnung;

    @Column(name = "bedingung1")
    @Enumerated(EnumType.ORDINAL)
    private ZgfaktorBedingung1Type bedingung1;
    
    @Column(name = "bedingung2")
    @Enumerated(EnumType.ORDINAL)
    private ZgfaktorBedingung2Type bedingung2;
    
    @Column(name = "aufschlag", precision = 5, scale = 2)
    private BigDecimal aufschlag;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "zgfaktor")
    private List<Planung> planungList;

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public ZgfaktorBedingung1Type getBedingung1() {
        return bedingung1;
    }

    public void setBedingung1(ZgfaktorBedingung1Type bedingung1) {
        this.bedingung1 = bedingung1;
    }

    public ZgfaktorBedingung2Type getBedingung2() {
        return bedingung2;
    }

    public void setBedingung2(ZgfaktorBedingung2Type bedingung2) {
        this.bedingung2 = bedingung2;
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
