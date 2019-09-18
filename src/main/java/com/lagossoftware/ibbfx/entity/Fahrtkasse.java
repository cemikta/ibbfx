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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Fahrtkasse entity
 *
 * @author Cem Ikta
 */
@Entity
@Table(name = "fahrtkasse")
@NamedQueries({
    @NamedQuery(name = Fahrtkasse.FIND_ALL,
            query = "SELECT f FROM Fahrtkasse f ORDER BY f.fahrtkasseNr"),
    @NamedQuery(name = Fahrtkasse.FIND_BY_FIELDS,
            query = "SELECT f FROM Fahrtkasse f " +
                    "WHERE " +
                    "str(f.fahrtkasseNr) LIKE LOWER(:search) OR " +
                    "str(f.belegdatum) LIKE LOWER(:search) OR " +
                    "LOWER(f.adresse.vorname) LIKE LOWER(:search) OR " +
                    "LOWER(f.adresse.nachname) LIKE LOWER(:search) OR " +
                    "str(f.planung.planungNr) LIKE LOWER(:search) OR " +
                    "LOWER(f.planung.zusatz) LIKE LOWER(:search) OR " +
                    "str(f.betrag) LIKE LOWER(:search) OR " +
                    "str(f.zahlenAm) LIKE LOWER(:search) " +
                    "ORDER BY f.fahrtkasseNr, f.belegdatum")
})
@AttributeOverride(name = "id", column = @Column(name = "fahrtkasse_id", nullable = false))
public class Fahrtkasse extends BaseEntity {

    public static final String FIND_ALL = "Fahrtkasse.findAll";
    public static final String FIND_BY_FIELDS = "Fahrtkasse.findByFields";

    @Column(name = "fahrtkasse_nr")
    private Long fahrtkasseNr;

    @Basic(optional = false)
    @Column(name = "belegdatum", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date belegdatum;

    @JoinColumn(name = "adresse_id", referencedColumnName = "adresse_id", nullable = false)
    @ManyToOne(optional = false)
    private Adresse adresse;

    @JoinColumn(name = "planung_id", referencedColumnName = "planung_id", nullable = false)
    @ManyToOne(optional = false)
    private Planung planung;

    @Column(name = "betrag", precision = 15, scale = 2, nullable = false)
    private BigDecimal betrag;

    @Column(name = "zahlungsart")
    @Enumerated(EnumType.STRING)
    private ZahlungsartType zahlungsart;

    @Column(name = "zahlen_am")
    @Temporal(TemporalType.TIMESTAMP)
    private Date zahlenAm;

    @Column(name = "abgerechnet")
    private Boolean abgerechnet;

    @Column(name = "abgerechnet_am")
    @Temporal(TemporalType.TIMESTAMP)
    private Date abgerechnetAm;

    @Column(name = "ausgaben_gesamt", precision = 15, scale = 2, nullable = false)
    private BigDecimal ausgabenGesamt;

    @Column(name = "einnahmen_gesamt", precision = 15, scale = 2, nullable = false)
    private BigDecimal einnahmenGesamt;

    @Column(name = "saldo", precision = 15, scale = 2, nullable = false)
    private BigDecimal saldo;

    @Column(name = "notizen", length = 2147483647)
    private String notizen;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fahrtkasse", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<FahrtkasseWaehrung> fahrtkasseWaehrungList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fahrtkasse", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<FahrtkasseDetail> fahrtkasseDetailList = new ArrayList<>();

    public Long getFahrtkasseNr() {
        return fahrtkasseNr;
    }

    public void setFahrtkasseNr(Long fahrtkasseNr) {
        this.fahrtkasseNr = fahrtkasseNr;
    }

    public Date getBelegdatum() {
        return belegdatum;
    }

    public void setBelegdatum(Date belegdatum) {
        this.belegdatum = belegdatum;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public Planung getPlanung() {
        return planung;
    }

    public void setPlanung(Planung planung) {
        this.planung = planung;
    }

    public BigDecimal getBetrag() {
        return betrag;
    }

    public void setBetrag(BigDecimal betrag) {
        this.betrag = betrag;
    }

    public ZahlungsartType getZahlungsart() {
        return zahlungsart;
    }

    public void setZahlungsart(ZahlungsartType zahlungsart) {
        this.zahlungsart = zahlungsart;
    }

    public Date getZahlenAm() {
        return zahlenAm;
    }

    public void setZahlenAm(Date zahlenAm) {
        this.zahlenAm = zahlenAm;
    }

    public Boolean getAbgerechnet() {
        return abgerechnet;
    }

    public void setAbgerechnet(Boolean abgerechnet) {
        this.abgerechnet = abgerechnet;
    }

    public Date getAbgerechnetAm() {
        return abgerechnetAm;
    }

    public void setAbgerechnetAm(Date abgerechnetAm) {
        this.abgerechnetAm = abgerechnetAm;
    }

    public BigDecimal getAusgabenGesamt() {
        return ausgabenGesamt;
    }

    public void setAusgabenGesamt(BigDecimal ausgabenGesamt) {
        this.ausgabenGesamt = ausgabenGesamt;
    }

    public BigDecimal getEinnahmenGesamt() {
        return einnahmenGesamt;
    }

    public void setEinnahmenGesamt(BigDecimal einnahmenGesamt) {
        this.einnahmenGesamt = einnahmenGesamt;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public String getNotizen() {
        return notizen;
    }

    public void setNotizen(String notizen) {
        this.notizen = notizen;
    }

    public List<FahrtkasseWaehrung> getFahrtkasseWaehrungList() {
        return fahrtkasseWaehrungList;
    }

    public void setFahrtkasseWaehrungList(List<FahrtkasseWaehrung> fahrtkasseWaehrungList) {
        this.fahrtkasseWaehrungList = fahrtkasseWaehrungList;
    }

    public List<FahrtkasseDetail> getFahrtkasseDetailList() {
        return fahrtkasseDetailList;
    }

    public void setFahrtkasseDetailList(List<FahrtkasseDetail> fahrtkasseDetailList) {
        this.fahrtkasseDetailList = fahrtkasseDetailList;
    }

    @Override
    public String toString() {
        return "entity.Fahrtkasse[ fahrtkasseId=" + getId() + " ]";
    }

}
