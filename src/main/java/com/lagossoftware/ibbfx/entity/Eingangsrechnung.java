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
import java.util.Date;

/**
 * Eingangsrechnung entity
 *
 * @author Cem Ikta
 */
@Entity
@Table(name = "eingangsrechnung")
@NamedQueries({
    @NamedQuery(name = Eingangsrechnung.FIND_ALL,
            query = "SELECT e FROM Eingangsrechnung e ORDER BY e.belegNr, e.belegdatum"),
    @NamedQuery(name = Eingangsrechnung.FIND_BY_FIELDS,
            query = "SELECT e FROM Eingangsrechnung e " +
                "LEFT JOIN e.adresse ad " +
                "WHERE " +
                "(ad.id IS NOT NULL AND LOWER(ad.vorname) LIKE LOWER(:search)) OR " + 
                "(ad.id IS NOT NULL AND LOWER(ad.nachname) LIKE LOWER(:search)) OR " +
                "LOWER(e.zahlungsstatus) LIKE LOWER(:search) OR " +
                "str(e.belegNr) LIKE LOWER(:search) OR " +
                "str(e.belegdatum) LIKE (:search) OR " +
                "str(e.rechnungsNr) LIKE LOWER(:search) OR " +
                "str(e.rechnungsdatum) LIKE LOWER(:search) OR " + 
                "LOWER(e.lieferant) LIKE LOWER(:search) OR " + 
                "LOWER(e.gegenstand) LIKE LOWER(:search) OR " + 
                "str(e.betrag) LIKE LOWER(:search) " + 
                "ORDER BY e.belegNr, e.belegdatum")
})
@AttributeOverride(name = "id", column = @Column(name = "eingangsrechnung_id", nullable = false))
public class Eingangsrechnung extends BaseEntity {
    
    public static final String FIND_ALL = "Eingangsrechnung.findAll";
    public static final String FIND_BY_FIELDS = "Eingangsrechnung.findByFields";

    @Column(name = "beleg_nr")
    private Long belegNr;

    @Column(name = "belegdatum", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date belegdatum;
    
    @Basic(optional = false)
    @Column(name = "rechnungs_nr", nullable = false, length = 50)
    private String rechnungsNr;

    @Column(name = "rechnungsdatum", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date rechnungsdatum;

    @Basic(optional = false)
    @Column(name = "lieferant", nullable = false, length = 100)
    private String lieferant;

    @Basic(optional = false)
    @Column(name = "gegenstand", nullable = false, length = 100)
    private String gegenstand;
    
    @Column(name = "betrag", precision = 15, scale = 2, nullable = false)
    private BigDecimal betrag;

    @JoinColumn(name = "waehrung_id", referencedColumnName = "waehrung_id", nullable = false)
    @ManyToOne(optional = false)
    private Waehrung waehrung;
    
    @JoinColumn(name = "adresse_id", referencedColumnName = "adresse_id")
    @ManyToOne
    private Adresse adresse;

    @Column(name = "zahlungsstatus")
    @Enumerated(EnumType.STRING)
    private ZahlungsstatusType zahlungsstatus;

    @Column(name = "notizen", length = 2147483647)
    private String notizen;

    public Long getBelegNr() {
        return belegNr;
    }

    public void setBelegNr(Long belegNr) {
        this.belegNr = belegNr;
    }

    public Date getBelegdatum() {
        return belegdatum;
    }

    public void setBelegdatum(Date belegdatum) {
        this.belegdatum = belegdatum;
    }

    public String getRechnungsNr() {
        return rechnungsNr;
    }

    public void setRechnungsNr(String rechnungsNr) {
        this.rechnungsNr = rechnungsNr;
    }

    public Date getRechnungsdatum() {
        return rechnungsdatum;
    }

    public void setRechnungsdatum(Date rechnungsdatum) {
        this.rechnungsdatum = rechnungsdatum;
    }

    public String getLieferant() {
        return lieferant;
    }

    public void setLieferant(String lieferant) {
        this.lieferant = lieferant;
    }

    public String getGegenstand() {
        return gegenstand;
    }

    public void setGegenstand(String gegenstand) {
        this.gegenstand = gegenstand;
    }

    public BigDecimal getBetrag() {
        return betrag;
    }

    public void setBetrag(BigDecimal betrag) {
        this.betrag = betrag;
    }

    public Waehrung getWaehrung() {
        return waehrung;
    }

    public void setWaehrung(Waehrung waehrung) {
        this.waehrung = waehrung;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public ZahlungsstatusType getZahlungsstatus() {
        return zahlungsstatus;
    }

    public void setZahlungsstatus(ZahlungsstatusType zahlungsstatus) {
        this.zahlungsstatus = zahlungsstatus;
    }

    public String getNotizen() {
        return notizen;
    }

    public void setNotizen(String notizen) {
        this.notizen = notizen;
    }

    @Override
    public String toString() {
        return "entity.Eingangsrechnung[ eingangsrechnungId=" + getId() + " ]";
    }
    
}
