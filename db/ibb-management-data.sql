-- ibb management postgresql data script
-- version: 1.0
-- author: cem ikta

-- in terminal
-- sudo -u postgres psql
-- \i ibb-management-data.sql

SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;
SET search_path = public, pg_catalog;

\connect ibbmanagement

SELECT pg_catalog.setval('anrede_anrede_id_seq', 3, true);
SELECT pg_catalog.setval('titel_titel_id_seq', 3, true);
SELECT pg_catalog.setval('land_land_id_seq', 3, true);
SELECT pg_catalog.setval('bundesland_bundesland_id_seq', 16, true);
SELECT pg_catalog.setval('fachbereich_fachbereich_id_seq', 3, true);
SELECT pg_catalog.setval('geschaeftsbereich_geschaeftsbereich_id_seq', 3, true);
SELECT pg_catalog.setval('arbeitsaufwand_arbeitsaufwand_id_seq', 3, true);
SELECT pg_catalog.setval('waehrung_waehrung_id_seq', 2, true);
SELECT pg_catalog.setval('zgfaktor_zgfaktor_id_seq', 4, true);
SELECT pg_catalog.setval('konto_konto_id_seq', 8, true);
SELECT pg_catalog.setval('stichwort_stichwort_id_seq', 3, true);
SELECT pg_catalog.setval('app_user_app_user_id_seq', 1, true);
SELECT pg_catalog.setval('params_params_id_seq', 1, true);
SELECT pg_catalog.setval('adresse_adresse_id_seq', 3, true);

--
-- data for anrede table
--
INSERT INTO anrede (anrede_id, bezeichnung, created_by, created_at) VALUES 
(1, 'Herr', 1, '2014-07-11 01:34:34'),
(2, 'Frau', 1, '2014-07-11 01:34:35'),
(3, 'Firma', 1, '2014-07-11 01:34:36');

--
-- data for titel table
--
INSERT INTO titel (titel_id, bezeichnung, created_by, created_at) VALUES 
(1, 'Prof.', 1, '2014-07-11 01:34:34'),
(2, 'Prof. Dr.', 1, '2014-07-11 01:34:35'),
(3, 'Dr.', 1, '2014-07-11 01:34:36');

--
-- data for land table
--
INSERT INTO land (land_id, isocode2, isocode3, name, created_by, created_at) VALUES 
(1, 'DE', 'DEU', 'Deutschland', 1, '2014-07-08 21:34:34'),
(2, 'GB', 'GBR', 'England', 1, '2014-07-08 21:34:35'),
(3, 'TR', 'TUR', 'Turkey', 1, '2014-07-08 21:34:36');

--
-- data for bundesland table
--
INSERT INTO bundesland (bundesland_id, name, land_id, created_by, created_at) VALUES 
 (1, 'Baden-Württemberg', 1, 1, '2014-07-08 00:34:36'),
 (2, 'Bayern', 1, 1, '2014-07-08 00:34:36'),
 (3, 'Berlin', 1, 1, '2014-07-08 00:34:36'),
 (4, 'Brandenburg', 1, 1, '2014-07-08 00:34:36'),
 (5, 'Bremen', 1, 1, '2014-07-08 00:34:36'),
 (6, 'Hamburg', 1, 1, '2014-07-08 00:34:36'),
 (7, 'Hessen', 1, 1, '2014-07-08 00:34:36'),
 (8, 'Mecklenburg-Vorpommern', 1, 1, '2014-07-08 00:34:36'),
 (9, 'Niedersachsen', 1, 1, '2014-07-08 00:34:36'),
 (10, 'Nordrhein-Westfalen', 1, 1, '2014-07-08 00:34:36'),
 (11, 'Rheinland-Pfalz', 1, 1, '2014-07-08 00:34:36'),
 (12, 'Saarland', 1, 1, '2014-07-08 00:34:36'),
 (13, 'Sachsen', 1, 1, '2014-07-08 00:34:36'),
 (14, 'Sachsen-Anhalt', 1, 1, '2014-07-08 00:34:36'),
 (15, 'Schleswig-Holstein', 1, 1, '2014-07-08 00:34:36'),
 (16, 'Thüringen', 1, 1, '2014-07-08 00:34:36');

--
-- data for fachbereich table
--
INSERT INTO fachbereich (fachbereich_id, abkuerzung, name, created_by, created_at) VALUES
(1, 'JS', 'Jugend und Schule', 1, '2014-07-08 00:34:34'),
(2, 'WU', 'Weiterbildung unterwegs', 1, '2014-07-08 00:34:35'),
(3, 'BI', 'Beruf International', 1, '2014-07-08 00:34:36');

--
-- data for geschaeftsbereich table
--
INSERT INTO geschaeftsbereich (geschaeftsbereich_id, abkuerzung, name, created_by, created_at) VALUES
(1,'GS','Geschäftsstelle', 1, '2014-07-08 00:34:34'),
(2, 'PB','Politische Bildung', 1, '2014-07-08 00:34:35'),
(3, 'EB','Erwachsenenbildung', 1, '2014-07-08 00:34:36');

--
-- data for arbeitsaufwand table
--
INSERT INTO arbeitsaufwand (arbeitsaufwand_id, bezeichnung, aufschlag, created_by, created_at) VALUES 
(1, 'Veranstaltung mit großer Routine', 0, 1, '2014-07-08 00:34:34'),
(2, 'Veranstaltung mit Anpassung eines vorhandenen Konzepts', 0, 1, '2014-07-08 00:34:35'),
(3, 'Veranstaltungen mit Neuentwicklung eines Konzeptes', 0, 1, '2014-07-08 00:34:36');

--
-- data for waehrung table
--
INSERT INTO waehrung (waehrung_id, code, bezeichnung, created_by, created_at) VALUES 
(1, 'EUR', 'Euro', 1, '2014-07-08 00:34:36'),
(2, 'USD', 'Dollar', 1, '2014-07-08 00:34:36');

--
-- data for zgfaktor table
--
INSERT INTO zgfaktor (zgfaktor_id, bezeichnung, bedingung1, bedingung2, aufschlag, created_by, created_at) VALUES 
(1, 'Jugendliche mit WBG Zuschuss', 0, 0, 10.00, 1, '2014-07-08 00:34:36'),
(2, 'Jugendliche ohne WBG Zuschuss', 1, 1, 15.00, 1, '2014-07-08 00:34:36'),
(3, 'Erwachsene mit WBG Zuschuss', 1, 0, 20.00, 1, '2014-07-08 00:34:36'),
(4, 'Erwachsene ohne WBG Zuschuss', 1, 1, 25.00, 1, '2014-07-08 00:34:36');

--
-- data for konto table
--
INSERT INTO konto (konto_id, konto_nr, name, klasse, konto_type, created_by, created_at) VALUES 
(1, '4328', 'Honorare freie Mitarbeiter', '', 0, 1, '2014-07-08 00:34:36'),
(2, '4330', 'Versicherung', '', 0, 1, '2014-07-08 00:34:36'),
(3, '4370', 'Fahrtkosten', '', 0, 1, '2014-07-08 00:34:36'),
(4, '4372', 'Visa', '', 0, 1, '2014-07-08 00:34:36'),
(5, '4378', 'Unterkunft / Verpflegung', '', 0, 1, '2014-07-08 00:34:36'),
(6, '4390', 'Programm / Öffentlichkeitsarbeit', '', 0, 1, '2014-07-08 00:34:36'),
(7, '4399', 'Sonstige Kosten', '', 0, 1, '2014-07-08 00:34:36'),
(8, '8999', 'Einnahmen', '', 1, 1, '2014-07-08 00:34:36');

--
-- data for stichwort table
--
INSERT INTO stichwort (stichwort_id, bezeichnung, created_by, created_at) VALUES 
(1, 'Hauptberufliche Mitarbeiter', 1, '2014-07-08 00:34:36'),
(2, 'Freiberufliche Mitarbeiter', 1, '2014-07-08 00:34:36'),
(3, 'Kooperationspartner', 1, '2014-07-08 00:34:36');

--
-- data for app_user table
--
INSERT INTO app_user (app_user_id, username, password, name, app_user_role, active, created_by, created_at) VALUES 
(1, 'admin', '1', 'Admin Benutzer', 0, true, 1, '2014-07-08 00:34:36');

--
-- data for params table
--
INSERT INTO params (params_id, last_adresse_nr, last_eingangsrechnung_beleg_nr, last_fahrtkasse_nr,
                    honorare_fuer_ibb_begleitung, ibb_strukturkosten_pro_tn_pro_tag, ibb_erloes,
                    haftpflicht_unfall_versicherung_pro_person_tag,
                    auslandsreisekranken_versicherung_pro_person_tag,
                    krankenversicherung_fuer_ausl_tn_pro_person_tag,
                    regressversicherung_va_in_de_bis_8_tage,
                    regressversicherung_va_in_de_bis_22_tage,
                    regressversicherung_va_in_de_bis_42_tage,
                    regressversicherung_va_im_ausland_bis_8_tage,
                    regressversicherung_va_im_ausland_bis_22_tage,
                    regressversicherung_va_im_ausland_bis_42_tage,
                    rechtschutzversicherung_va_bis_8_tage,
                    rechtschutzversicherung_va_bis_14_tage,
                    rechtschutzversicherung_va_bis_22_tage,
                    reisepreissicherung_pro_person_tag,
                    created_by, created_at) VALUES
(1, 1000, 1000, 1000, 100.00, 20.00, 5, 0.26, 0.28, 0.95, 0.41, 0.46, 0.82, 0.56, 0.62, 0.97, 0.72, 2.05, 4.10, 0.60, 1, '2014-07-08 00:34:36');

--
-- data for adresse table
-- TODO Test Data, not for production!
--
INSERT INTO adresse(adresse_id, anrede_id, titel_id, vorname, nachname, firma1, firma2, 
            firma3, firma4, kennzeichen1, kennzeichen2, postfach_plz, postfach, 
            strasse, plz, ort, land_id, bundesland_id, anrede_brief, mobiltelefon, 
            telefon_privat, telefon_dienst, fax_privat, fax_dienst, email, 
            homepage, skype, facebook, twitter, xing, linkedin, google_plus, 
            beruf, geburtsdatum, geburtsort, staatsangehoerigkeit, pass_nr, 
            ausstellungsdatum, ausstellungsort, kostenstelle, konto_inhaber, 
            iban, bic, bank, konto_nr, blz, letzte_spende_am, betrag, spendensumme, 
            freies_feld1, freies_feld2, freies_feld3, notizen, created_by, created_at) VALUES
(1, 2, 1, 'Alice', 'Wonderland', 'Wonderland AG', 'Überraschungswelt für Alle', 
'', '', '', '', '44123', 'Wonderland AG', 
'Hattingerstr. 23', '44789', 'Bochum', 1, 10, '', '176 456 78 901',
'0211 987 12 34', '0231 432 12 56', '0211 987 12 35', '0231 432 12 57', 'info@wonderlandag.com',
'www.wonderlandag.com', 'wonderlandag', 'www.facebook.com/wonderlandag', '@wonderlandag', 
'www.xing.com/wonderlandag', 'www.linkedin.com/in/wonderlandag', 'www.plus.google.com/+WonderlandAG', 
'Softwareentwickler', '1980-07-08', 'Dortmund', 'deutsch', 'De12345 765 098',
'2010-09-08', 'Dortmund', NULL, 'Alice Wonderland', 
'DE12 3456 7890 1234 9876', 'DOSP34XX', 'Sparkasse Dortmund', '', '', '2014-10-01', '60', '1200',
'', '', '', 'Monatlich Infobrief schicken', 1, '2014-07-08 00:34:37'),
(2, 1, 2, 'Max', 'Mustermensch', 'Mustermensch GmbH', 'Gut Handeln', 
'', '', '', '', '45163', 'Mustermensch GmbH', 
'Markstr. 41', '457963', 'Dortmund', 1, 10, '', '177 458 18 301',
'0212 587 16 24', '0211 452 62 76', '0221 947 15 35', '0221 482 12 58', 'info@mustermenschgmbh.de',
'www.mustermenschgmbh.de', 'mustermenschgmbh', 'www.facebook.com/mustermenschgmbh', '@mustermenschgmbh', 
'www.xing.com/mustermenschgmbh', 'www.linkedin.com/in/mustermenschgmbh', 'www.plus.google.com/+MustermenschGmbH', 
'Händler', '1982-01-08', 'Frankfurt', 'deutsch', 'De5467 1235 099',
'2011-02-08', 'Frankfurt', NULL, 'Max Mustermensch', 
'DE12 5656 5688 3926 9875', 'DOSP67XX', 'Sparkasse Frankfurt', '', '', '2014-09-01', '55', '965',
'', '', '', 'Monatlich Infobrief schicken', 1, '2014-07-08 00:34:37'),
(3, 2, 1, 'Nadia', 'Schöneberg', '', '', 
'', '', '', '', '', '', 
'Bergstr. 11', '46779', 'Lünen', 1, 10, '', '177 456 78 99',
'0221 977 34 35', '0221 345 15 56', '0221 987 54 35', '0221 431 43 55', 'nadia.schoeneberg@gmail.com',
'www.nadiaschoeneberg.com', 'nadiaschoeneberg', 'www.facebook.com/nadiaschoeneberg', '@nadiaschoeneberg', 
'www.xing.com/nadiaschoeneberg', 'www.linkedin.com/in/nadiaschoeneberg', 'www.plus.google.com/+NadiaSchoeneberg', 
'Grafikdesigner', '1984-01-09', 'Lünen', 'deutsch', 'De81243 761 101',
'2012-01-04', 'Lünen', NULL, 'Nadia Schöneberg', 
'DE12 3409 0981 2234 1276', 'DOSP11XX', 'Sparkasse Lünen', '', '', '2014-05-01', '50', '650',
'', '', '', 'Monatlich Infobrief schicken', 1, '2014-07-08 00:34:37');


