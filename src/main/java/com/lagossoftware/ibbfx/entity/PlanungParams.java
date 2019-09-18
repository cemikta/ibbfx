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

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * Konto entity 
 * 
 * @author Cem Ikta
 */
@Entity
@Table(name = "planung_params")
@AttributeOverride(name = "id", column = @Column(name = "planung_params_id", nullable = false))
public class PlanungParams extends BaseEntity {

    @Column(name = "honorare_fuer_ibb_begleitung", precision = 15, scale = 2, nullable = false)
    private BigDecimal honorareFuerIbbBegleitung;

    @Column(name = "ibb_strukturkosten_pro_tn_pro_tag", precision = 15, scale = 2, nullable = false)
    private BigDecimal ibbStrukturkostenProTNProTag;

    @Column(name = "ibb_erloes", precision = 5, scale = 2, nullable = false)
    private BigDecimal ibbErloes;

    @Column(name = "haftpflicht_unfall_versicherung_pro_person_tag", precision = 5, scale = 2, nullable = false)
    private BigDecimal haftpflichtUnfallVersicherungProPersonTag;

    @Column(name = "auslandsreisekranken_versicherung_pro_person_tag", precision = 5, scale = 2, nullable = false)
    private BigDecimal auslandsreisekrankenVersicherungProPersonTag;

    @Column(name = "krankenversicherung_fuer_ausl_tn_pro_person_tag", precision = 5, scale = 2, nullable = false)
    private BigDecimal krankenversicherungFuerAuslTNProPersonTag;

    @Column(name = "regressversicherung_va_in_de_bis_8_tage", precision = 5, scale = 2, nullable = false)
    private BigDecimal regressversicherungVaInDeBis8Tage;

    @Column(name = "regressversicherung_va_in_de_bis_22_tage", precision = 5, scale = 2, nullable = false)
    private BigDecimal regressversicherungVaInDeBis22Tage;

    @Column(name = "regressversicherung_va_in_de_bis_42_tage", precision = 5, scale = 2, nullable = false)
    private BigDecimal regressversicherungVaInDeBis42Tage;

    @Column(name = "regressversicherung_va_im_ausland_bis_8_tage", precision = 5, scale = 2, nullable = false)
    private BigDecimal regressversicherungVaImAuslandBis8Tage;

    @Column(name = "regressversicherung_va_im_ausland_bis_22_tage", precision = 5, scale = 2, nullable = false)
    private BigDecimal regressversicherungVaImAuslandBis22Tage;

    @Column(name = "regressversicherung_va_im_ausland_bis_42_tage", precision = 5, scale = 2, nullable = false)
    private BigDecimal regressversicherungVaImAuslandBis42Tage;

    @Column(name = "rechtschutzversicherung_va_bis_8_tage", precision = 5, scale = 2, nullable = false)
    private BigDecimal rechtschutzversicherungVaBis8Tage;

    @Column(name = "rechtschutzversicherung_va_bis_14_tage", precision = 5, scale = 2, nullable = false)
    private BigDecimal rechtschutzversicherungVaBis14Tage;

    @Column(name = "rechtschutzversicherung_va_bis_22_tage", precision = 5, scale = 2, nullable = false)
    private BigDecimal rechtschutzversicherungVaBis22Tage;

    @Column(name = "reisepreissicherung_pro_person_tag", precision = 5, scale = 2, nullable = false)
    private BigDecimal reisepreissicherungProPersonTag;

    public BigDecimal getHonorareFuerIbbBegleitung() {
        return honorareFuerIbbBegleitung;
    }

    public void setHonorareFuerIbbBegleitung(BigDecimal honorareFuerIbbBegleitung) {
        this.honorareFuerIbbBegleitung = honorareFuerIbbBegleitung;
    }

    public BigDecimal getIbbStrukturkostenProTNProTag() {
        return ibbStrukturkostenProTNProTag;
    }

    public void setIbbStrukturkostenProTNProTag(BigDecimal ibbStrukturkostenProTNProTag) {
        this.ibbStrukturkostenProTNProTag = ibbStrukturkostenProTNProTag;
    }

    public BigDecimal getIbbErloes() {
        return ibbErloes;
    }

    public void setIbbErloes(BigDecimal ibbErloes) {
        this.ibbErloes = ibbErloes;
    }

    public BigDecimal getHaftpflichtUnfallVersicherungProPersonTag() {
        return haftpflichtUnfallVersicherungProPersonTag;
    }

    public void setHaftpflichtUnfallVersicherungProPersonTag(BigDecimal haftpflichtUnfallVersicherungProPersonTag) {
        this.haftpflichtUnfallVersicherungProPersonTag = haftpflichtUnfallVersicherungProPersonTag;
    }

    public BigDecimal getAuslandsreisekrankenVersicherungProPersonTag() {
        return auslandsreisekrankenVersicherungProPersonTag;
    }

    public void setAuslandsreisekrankenVersicherungProPersonTag(BigDecimal auslandsreisekrankenVersicherungProPersonTag) {
        this.auslandsreisekrankenVersicherungProPersonTag = auslandsreisekrankenVersicherungProPersonTag;
    }

    public BigDecimal getKrankenversicherungFuerAuslTNProPersonTag() {
        return krankenversicherungFuerAuslTNProPersonTag;
    }

    public void setKrankenversicherungFuerAuslTNProPersonTag(BigDecimal krankenversicherungFuerAuslTNProPersonTag) {
        this.krankenversicherungFuerAuslTNProPersonTag = krankenversicherungFuerAuslTNProPersonTag;
    }

    public BigDecimal getRegressversicherungVaInDeBis8Tage() {
        return regressversicherungVaInDeBis8Tage;
    }

    public void setRegressversicherungVaInDeBis8Tage(BigDecimal regressversicherungVaInDeBis8Tage) {
        this.regressversicherungVaInDeBis8Tage = regressversicherungVaInDeBis8Tage;
    }

    public BigDecimal getRegressversicherungVaInDeBis22Tage() {
        return regressversicherungVaInDeBis22Tage;
    }

    public void setRegressversicherungVaInDeBis22Tage(BigDecimal regressversicherungVaInDeBis22Tage) {
        this.regressversicherungVaInDeBis22Tage = regressversicherungVaInDeBis22Tage;
    }

    public BigDecimal getRegressversicherungVaInDeBis42Tage() {
        return regressversicherungVaInDeBis42Tage;
    }

    public void setRegressversicherungVaInDeBis42Tage(BigDecimal regressversicherungVaInDeBis42Tage) {
        this.regressversicherungVaInDeBis42Tage = regressversicherungVaInDeBis42Tage;
    }

    public BigDecimal getRegressversicherungVaImAuslandBis8Tage() {
        return regressversicherungVaImAuslandBis8Tage;
    }

    public void setRegressversicherungVaImAuslandBis8Tage(BigDecimal regressversicherungVaImAuslandBis8Tage) {
        this.regressversicherungVaImAuslandBis8Tage = regressversicherungVaImAuslandBis8Tage;
    }

    public BigDecimal getRegressversicherungVaImAuslandBis22Tage() {
        return regressversicherungVaImAuslandBis22Tage;
    }

    public void setRegressversicherungVaImAuslandBis22Tage(BigDecimal regressversicherungVaImAuslandBis22Tage) {
        this.regressversicherungVaImAuslandBis22Tage = regressversicherungVaImAuslandBis22Tage;
    }

    public BigDecimal getRegressversicherungVaImAuslandBis42Tage() {
        return regressversicherungVaImAuslandBis42Tage;
    }

    public void setRegressversicherungVaImAuslandBis42Tage(BigDecimal regressversicherungVaImAuslandBis42Tage) {
        this.regressversicherungVaImAuslandBis42Tage = regressversicherungVaImAuslandBis42Tage;
    }

    public BigDecimal getRechtschutzversicherungVaBis8Tage() {
        return rechtschutzversicherungVaBis8Tage;
    }

    public void setRechtschutzversicherungVaBis8Tage(BigDecimal rechtschutzversicherungVaBis8Tage) {
        this.rechtschutzversicherungVaBis8Tage = rechtschutzversicherungVaBis8Tage;
    }

    public BigDecimal getRechtschutzversicherungVaBis14Tage() {
        return rechtschutzversicherungVaBis14Tage;
    }

    public void setRechtschutzversicherungVaBis14Tage(BigDecimal rechtschutzversicherungVaBis14Tage) {
        this.rechtschutzversicherungVaBis14Tage = rechtschutzversicherungVaBis14Tage;
    }

    public BigDecimal getRechtschutzversicherungVaBis22Tage() {
        return rechtschutzversicherungVaBis22Tage;
    }

    public void setRechtschutzversicherungVaBis22Tage(BigDecimal rechtschutzversicherungVaBis22Tage) {
        this.rechtschutzversicherungVaBis22Tage = rechtschutzversicherungVaBis22Tage;
    }

    public BigDecimal getReisepreissicherungProPersonTag() {
        return reisepreissicherungProPersonTag;
    }

    public void setReisepreissicherungProPersonTag(BigDecimal reisepreissicherungProPersonTag) {
        this.reisepreissicherungProPersonTag = reisepreissicherungProPersonTag;
    }

    @Override
    public String toString() {
        return "entity.Params[ paramsId=" + getId() + " ]";
    }

}
