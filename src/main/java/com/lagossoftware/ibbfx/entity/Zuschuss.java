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

/**
 * Konto entity 
 * 
 * @author Cem Ikta
 */
@Entity
@Table(name = "zuschuss")
@AttributeOverride(name = "id", column = @Column(name = "zuschuss_id", nullable = false))
public class Zuschuss extends BaseEntity {

    @JoinColumn(name = "planung_id", referencedColumnName = "planung_id", nullable = false)
    @ManyToOne(optional = false)
    private Planung planung;

    @Column(name = "bezeichnung")
    private String bezeichnung;

    @Column(name = "pro_de_tn", precision = 15, scale = 2)
    private BigDecimal proDeTn;

    @Column(name = "pro_de_tn_gesamt", precision = 15, scale = 2)
    private BigDecimal proDeTnGesamt;

    @Column(name = "pro_ausl_tn", precision = 15, scale = 2)
    private BigDecimal proAuslTn;

    @Column(name = "pro_ausl_tn_gesamt", precision = 15, scale = 2)
    private BigDecimal proAuslTnGesamt;

    @Column(name = "manuell_berechnet", precision = 15, scale = 2)
    private BigDecimal manuellBerechnet;

    @Column(name = "gesamt", precision = 15, scale = 2)
    private BigDecimal gesamt;

    @Column(name = "bewilligt")
    private Boolean bewilligt;

    public Planung getPlanung() {
        return planung;
    }

    public void setPlanung(Planung planung) {
        this.planung = planung;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public BigDecimal getProDeTn() {
        return proDeTn;
    }

    public void setProDeTn(BigDecimal proDeTn) {
        this.proDeTn = proDeTn;
    }

    public BigDecimal getProDeTnGesamt() {
        return proDeTnGesamt;
    }

    public void setProDeTnGesamt(BigDecimal proDeTnGesamt) {
        this.proDeTnGesamt = proDeTnGesamt;
    }

    public BigDecimal getProAuslTn() {
        return proAuslTn;
    }

    public void setProAuslTn(BigDecimal proAuslTn) {
        this.proAuslTn = proAuslTn;
    }

    public BigDecimal getProAuslTnGesamt() {
        return proAuslTnGesamt;
    }

    public void setProAuslTnGesamt(BigDecimal proAuslTnGesamt) {
        this.proAuslTnGesamt = proAuslTnGesamt;
    }

    public BigDecimal getManuellBerechnet() {
        return manuellBerechnet;
    }

    public void setManuellBerechnet(BigDecimal manuellBerechnet) {
        this.manuellBerechnet = manuellBerechnet;
    }

    public BigDecimal getGesamt() {
        return gesamt;
    }

    public void setGesamt(BigDecimal gesamt) {
        this.gesamt = gesamt;
    }

    public Boolean getBewilligt() {
        return bewilligt;
    }

    public void setBewilligt(Boolean bewilligt) {
        this.bewilligt = bewilligt;
    }

    @Override
    public String toString() {
        return this.bezeichnung;
    }

}
