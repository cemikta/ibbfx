-- ibb management postgresql schema script
-- version: 1.0
-- author: cem ikta

-- in terminal
-- sudo -u postgres psql
-- \i ibb-management-schema.sql

SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

--
-- create db
--
CREATE DATABASE ibbmanagement WITH TEMPLATE = template0 ENCODING = 'UTF8';
ALTER DATABASE ibbmanagement OWNER TO postgres;

\connect ibbmanagement

SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

COMMENT ON SCHEMA public IS 'Standard public schema';

SET search_path = public, pg_catalog;
SET default_tablespace = '';
SET default_with_oids = false;

------------------------------------------------------
-- grundlagen module
------------------------------------------------------

--
-- anrede sequence
--
DROP SEQUENCE IF EXISTS anrede_anrede_id_seq CASCADE;
CREATE SEQUENCE anrede_anrede_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;

ALTER TABLE public.anrede_anrede_id_seq OWNER TO postgres;

--
-- anrede table
--
DROP TABLE IF EXISTS anrede CASCADE;
CREATE TABLE anrede (
    anrede_id bigint DEFAULT nextval('anrede_anrede_id_seq'::regclass) NOT NULL,
    bezeichnung character varying(100) NOT NULL,
    created_by bigint,
    created_at timestamp without time zone DEFAULT now(),
    updated_by bigint,
    updated_at timestamp without time zone
);

ALTER TABLE public.anrede OWNER TO postgres;

--
-- titel sequence
--
DROP SEQUENCE IF EXISTS titel_titel_id_seq CASCADE;
CREATE SEQUENCE titel_titel_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;

ALTER TABLE public.titel_titel_id_seq OWNER TO postgres;

--
-- titel table
--
DROP TABLE IF EXISTS titel CASCADE;
CREATE TABLE titel (
    titel_id bigint DEFAULT nextval('titel_titel_id_seq'::regclass) NOT NULL,
    bezeichnung character varying(100) NOT NULL,
    created_by bigint,
    created_at timestamp without time zone DEFAULT now(),
    updated_by bigint,
    updated_at timestamp without time zone
);

ALTER TABLE public.titel OWNER TO postgres;

--
-- land sequence
--
DROP SEQUENCE IF EXISTS land_land_id_seq CASCADE;
CREATE SEQUENCE land_land_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;

ALTER TABLE public.land_land_id_seq OWNER TO postgres;

--
-- land table
--
DROP TABLE IF EXISTS land CASCADE;
CREATE TABLE land (
    land_id bigint DEFAULT nextval('land_land_id_seq'::regclass) NOT NULL,
    isocode2 character varying(02) NOT NULL,
    isocode3 character varying(03) NOT NULL,
    name character varying(100) NOT NULL,
    created_by bigint,
    created_at timestamp without time zone DEFAULT now(),
    updated_by bigint,
    updated_at timestamp without time zone
);

ALTER TABLE public.land OWNER TO postgres;

--
-- bundesland sequence
--
DROP SEQUENCE IF EXISTS bundesland_bundesland_id_seq CASCADE;
CREATE SEQUENCE bundesland_bundesland_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;

ALTER TABLE public.bundesland_bundesland_id_seq OWNER TO postgres;

--
-- bundesland table
--
DROP TABLE IF EXISTS bundesland CASCADE;
CREATE TABLE bundesland (
    bundesland_id bigint DEFAULT nextval('bundesland_bundesland_id_seq'::regclass) NOT NULL,
    name character varying(100) NOT NULL,
    land_id bigint NOT NULL,
    created_by bigint,
    created_at timestamp without time zone DEFAULT now(),
    updated_by bigint,
    updated_at timestamp without time zone
);

ALTER TABLE public.bundesland OWNER TO postgres;

--
-- fachbereich sequence
--
DROP SEQUENCE IF EXISTS fachbereich_fachbereich_id_seq CASCADE;
CREATE SEQUENCE fachbereich_fachbereich_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;

ALTER TABLE public.fachbereich_fachbereich_id_seq OWNER TO postgres;

--
-- fachbereich table
--
DROP TABLE IF EXISTS fachbereich CASCADE;
CREATE TABLE fachbereich (
    fachbereich_id bigint DEFAULT nextval('fachbereich_fachbereich_id_seq'::regclass) NOT NULL,
    abkuerzung character varying(10) NOT NULL,
    name character varying(100) NOT NULL,
    created_by bigint,
    created_at timestamp without time zone DEFAULT now(),
    updated_by bigint,
    updated_at timestamp without time zone
);

ALTER TABLE public.fachbereich OWNER TO postgres;

--
-- geschaeftsbereich sequence
--
DROP SEQUENCE IF EXISTS geschaeftsbereich_geschaeftsbereich_id_seq CASCADE;
CREATE SEQUENCE geschaeftsbereich_geschaeftsbereich_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;

ALTER TABLE public.geschaeftsbereich_geschaeftsbereich_id_seq OWNER TO postgres;

--
-- geschaeftsbereich table
--
DROP TABLE IF EXISTS geschaeftsbereich CASCADE;
CREATE TABLE geschaeftsbereich (
    geschaeftsbereich_id bigint DEFAULT nextval('geschaeftsbereich_geschaeftsbereich_id_seq'::regclass) NOT NULL,
    abkuerzung character varying(10) NOT NULL,
    name character varying(100) NOT NULL,
    created_by bigint,
    created_at timestamp without time zone DEFAULT now(),
    updated_by bigint,
    updated_at timestamp without time zone
);

ALTER TABLE public.geschaeftsbereich OWNER TO postgres;

--
-- arbeitsaufwand sequence
--
DROP SEQUENCE IF EXISTS arbeitsaufwand_arbeitsaufwand_id_seq CASCADE;
CREATE SEQUENCE arbeitsaufwand_arbeitsaufwand_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;

ALTER TABLE public.arbeitsaufwand_arbeitsaufwand_id_seq OWNER TO postgres;

--
-- arbeitsaufwand table
--
DROP TABLE IF EXISTS arbeitsaufwand CASCADE;
CREATE TABLE arbeitsaufwand (
    arbeitsaufwand_id bigint DEFAULT nextval('arbeitsaufwand_arbeitsaufwand_id_seq'::regclass) NOT NULL,
	  bezeichnung character varying(100) NOT NULL,
    aufschlag decimal(5,2),
    created_by bigint,
    created_at timestamp without time zone DEFAULT now(),
    updated_by bigint,
    updated_at timestamp without time zone
);

ALTER TABLE public.arbeitsaufwand OWNER TO postgres;

--
-- waehrung sequence
--
DROP SEQUENCE IF EXISTS waehrung_waehrung_id_seq CASCADE;
CREATE SEQUENCE waehrung_waehrung_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;

ALTER TABLE public.waehrung_waehrung_id_seq OWNER TO postgres;

--
-- waehrung table
--
DROP TABLE IF EXISTS waehrung CASCADE;
CREATE TABLE waehrung (
    waehrung_id bigint DEFAULT nextval('waehrung_waehrung_id_seq'::regclass) NOT NULL,
	  code character varying(3) NOT NULL,
	  bezeichnung character varying(100) NOT NULL,
    created_by bigint,
    created_at timestamp without time zone DEFAULT now(),
    updated_by bigint,
    updated_at timestamp without time zone
);

ALTER TABLE public.waehrung OWNER TO postgres;

--
-- zgfaktor sequence
--
DROP SEQUENCE IF EXISTS zgfaktor_zgfaktor_id_seq CASCADE;
CREATE SEQUENCE zgfaktor_zgfaktor_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;

ALTER TABLE public.zgfaktor_zgfaktor_id_seq OWNER TO postgres;

--
-- zgfaktor table
--
DROP TABLE IF EXISTS zgfaktor CASCADE;
CREATE TABLE zgfaktor (
    zgfaktor_id bigint DEFAULT nextval('zgfaktor_zgfaktor_id_seq'::regclass) NOT NULL,
	  bezeichnung character varying(100) NOT NULL,
  	bedingung1 smallint,
  	bedingung2 smallint,
  	aufschlag decimal(5,2),
  	created_by bigint,
    created_at timestamp without time zone DEFAULT now(),
    updated_by bigint,
    updated_at timestamp without time zone
);

ALTER TABLE public.zgfaktor OWNER TO postgres;

--
-- konto sequence
--
DROP SEQUENCE IF EXISTS konto_konto_id_seq CASCADE;
CREATE SEQUENCE konto_konto_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;

ALTER TABLE public.konto_konto_id_seq OWNER TO postgres;

--
-- konto table
--
DROP TABLE IF EXISTS konto CASCADE;
CREATE TABLE konto (
    konto_id bigint DEFAULT nextval('konto_konto_id_seq'::regclass) NOT NULL,
  	konto_nr character varying(10) NOT NULL,
	  name character varying(100) NOT NULL,
	  klasse character varying(50) NOT NULL,
	  konto_type smallint, -- 0 ausgaben 1 einnahmen
	  created_by bigint,
    created_at timestamp without time zone DEFAULT now(),
    updated_by bigint,
    updated_at timestamp without time zone
);

ALTER TABLE public.konto OWNER TO postgres;

--
-- stichwort sequence
--
DROP SEQUENCE IF EXISTS stichwort_stichwort_id_seq CASCADE;
CREATE SEQUENCE stichwort_stichwort_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;

ALTER TABLE public.stichwort_stichwort_id_seq OWNER TO postgres;

--
-- stichwort table
--
DROP TABLE IF EXISTS stichwort CASCADE;
CREATE TABLE stichwort (
    stichwort_id bigint DEFAULT nextval('stichwort_stichwort_id_seq'::regclass) NOT NULL,
	  bezeichnung character varying(100) NOT NULL,
    created_by bigint,
    created_at timestamp without time zone DEFAULT now(),
    updated_by bigint,
    updated_at timestamp without time zone
);

ALTER TABLE public.stichwort OWNER TO postgres;

------------------------------------------------------
-- settings module
------------------------------------------------------

--
-- app_user_group sequence
--
DROP SEQUENCE IF EXISTS app_user_group_app_user_group_id_seq CASCADE;
CREATE SEQUENCE app_user_group_app_user_group_id_seq
INCREMENT BY 1
NO MAXVALUE
NO MINVALUE
CACHE 1;

ALTER TABLE public.app_user_group_app_user_group_id_seq OWNER TO postgres;

--
-- app_user_group table
--
DROP TABLE IF EXISTS app_user_group CASCADE;
CREATE TABLE app_user_group (
    app_user_group_id bigint DEFAULT nextval('app_user_group_app_user_group_id_seq'::regclass) NOT NULL,
    name character varying(50) NOT NULL,
    created_by bigint,
    created_at timestamp without time zone DEFAULT now(),
    updated_by bigint,
    updated_at timestamp without time zone
);

ALTER TABLE public.app_user_group OWNER TO postgres;

-- --
-- app_right sequence
--
DROP SEQUENCE IF EXISTS app_right_app_right_id_seq CASCADE;
CREATE SEQUENCE app_right_app_right_id_seq
INCREMENT BY 1
NO MAXVALUE
NO MINVALUE
CACHE 1;

ALTER TABLE public.app_right_app_right_id_seq OWNER TO postgres;

--
-- app_right table
--
DROP TABLE IF EXISTS app_right CASCADE;
CREATE TABLE app_right (
    app_right_id bigint DEFAULT nextval('app_right_app_right_id_seq'::regclass) NOT NULL,
    app_user_group_id bigint NOT NULL,
    module_name character varying(50) NOT NULL,
    object_name1 character varying(50) NOT NULL,
    object_name2 character varying(50),
    name character varying(50) NOT NULL,
    allow_read boolean,
    allow_create boolean,
    allow_edit boolean,
    allow_delete boolean,
    created_by bigint,
    created_at timestamp without time zone DEFAULT now(),
    updated_by bigint,
    updated_at timestamp without time zone
);

ALTER TABLE public.app_right OWNER TO postgres;

--
-- app_user sequence
--
DROP SEQUENCE IF EXISTS app_user_app_user_id_seq CASCADE;
CREATE SEQUENCE app_user_app_user_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;

ALTER TABLE public.app_user_app_user_id_seq OWNER TO postgres;

--
-- app_user table
--
DROP TABLE IF EXISTS app_user CASCADE;
CREATE TABLE app_user (
    app_user_id bigint DEFAULT nextval('app_user_app_user_id_seq'::regclass) NOT NULL,
	  username character varying(20) NOT NULL,
	  password character varying(20) NOT NULL,
	  name character varying(100) NOT NULL,
    app_user_role smallint NOT NULL,
    app_user_group_id bigint,
    active boolean DEFAULT true NOT NULL,
    created_by bigint,
    created_at timestamp without time zone DEFAULT now(),
    updated_by bigint,
    updated_at timestamp without time zone
);

ALTER TABLE public.app_user OWNER TO postgres;

--
-- params sequence
--
DROP SEQUENCE IF EXISTS params_params_id_seq CASCADE;
CREATE SEQUENCE params_params_id_seq
INCREMENT BY 1
NO MAXVALUE
NO MINVALUE
CACHE 1;

ALTER TABLE public.params_params_id_seq OWNER TO postgres;

--
-- params table
--
DROP TABLE IF EXISTS params CASCADE;
CREATE TABLE params (
    params_id bigint DEFAULT nextval('params_params_id_seq'::regclass) NOT NULL,
    last_adresse_nr bigint NOT NULL,
    last_eingangsrechnung_beleg_nr bigint NOT NULL,
    last_fahrtkasse_nr bigint NOT NULL,
    -- planung
    honorare_fuer_ibb_begleitung numeric(15,2) NOT NULL,
    ibb_strukturkosten_pro_tn_pro_tag numeric(15,2) NOT NULL,
    ibb_erloes decimal(5,2) NOT NULL,
    -- versicherung
    haftpflicht_unfall_versicherung_pro_person_tag decimal(5,2) NOT NULL,
    auslandsreisekranken_versicherung_pro_person_tag decimal(5,2) NOT NULL,
    krankenversicherung_fuer_ausl_tn_pro_person_tag decimal(5,2) NOT NULL,
    regressversicherung_va_in_de_bis_8_tage decimal(5,2) NOT NULL,
    regressversicherung_va_in_de_bis_22_tage decimal(5,2) NOT NULL,
    regressversicherung_va_in_de_bis_42_tage decimal(5,2) NOT NULL,
    regressversicherung_va_im_ausland_bis_8_tage decimal(5,2) NOT NULL,
    regressversicherung_va_im_ausland_bis_22_tage decimal(5,2) NOT NULL,
    regressversicherung_va_im_ausland_bis_42_tage decimal(5,2) NOT NULL,
    rechtschutzversicherung_va_bis_8_tage decimal(5,2) NOT NULL,
    rechtschutzversicherung_va_bis_14_tage decimal(5,2) NOT NULL,
    rechtschutzversicherung_va_bis_22_tage decimal(5,2) NOT NULL,
    reisepreissicherung_pro_person_tag decimal(5,2) NOT NULL,
    created_by bigint,
    created_at timestamp without time zone DEFAULT now(),
    updated_by bigint,
    updated_at timestamp without time zone
);

ALTER TABLE public.params OWNER TO postgres;

------------------------------------------------------
-- adresse module
------------------------------------------------------

--
-- adresse sequence
--
DROP SEQUENCE IF EXISTS adresse_adresse_id_seq CASCADE;
CREATE SEQUENCE adresse_adresse_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;

ALTER TABLE public.adresse_adresse_id_seq OWNER TO postgres;

--
-- adresse table
--
DROP TABLE IF EXISTS adresse CASCADE;
CREATE TABLE adresse (
    adresse_id bigint DEFAULT nextval('adresse_adresse_id_seq'::regclass) NOT NULL,
    adresse_nr bigint,
	  anrede_id bigint NOT NULL,
  	titel_id bigint,
  	vorname character varying(50),
	  nachname character varying(50),
	  firma1 character varying(50),
	  firma2 character varying(50),
  	firma3 character varying(50),
	  firma4 character varying(50),
	  kennzeichen1 character varying(10),
	  kennzeichen2 character varying(50),
	  -- anschrift
	  postfach_plz character varying(10),
	  postfach character varying(50),
	  strasse character varying(50),
	  plz character varying(10),
	  ort character varying(50),
  	land_id bigint NOT NULL,
	  bundesland_id bigint,
	  anrede_brief character varying(100),
	  -- kommunikation
	  mobiltelefon character varying(50),
	  telefon_privat character varying(50),
	  telefon_dienst character varying(50),
	  fax_privat character varying(50),
	  fax_dienst character varying(50),
	  email character varying(100),
	  homepage character varying(100),
	  -- social media
	  skype character varying(100),
	  facebook character varying(100),
	  twitter character varying(100),
	  xing character varying(100),
	  linkedin character varying(100),
	  google_plus character varying(100),
	  -- pass
	  beruf character varying(50),
	  geburtsdatum timestamp without time zone,
	  geburtsort character varying(50),
	  staatsangehoerigkeit character varying(50),
	  pass_nr character varying(50),
	  ausstellungsdatum timestamp without time zone,
	  ausstellungsort character varying(50),
	  -- mitarbeiter
	  kostenstelle integer,
	  -- bank
	  konto_inhaber character varying(50),
	  iban character varying(50),
	  bic character varying(50),
	  bank character varying(50),
	  konto_nr character varying(50),
	  blz character varying(50),
	  -- spenden
	  letzte_spende_am timestamp without time zone,
    betrag numeric(15,2),
    spendensumme numeric(15,2),
	  -- notizen
	  freies_feld1 character varying(50),
	  freies_feld2 character varying(50),
	  freies_feld3 character varying(50),
  	notizen text,
	  created_by bigint,
    created_at timestamp without time zone DEFAULT now(),
    updated_by bigint,
    updated_at timestamp without time zone
);

ALTER TABLE public.adresse OWNER TO postgres;

--
-- adresse_stichwort sequence
--
DROP SEQUENCE IF EXISTS adresse_stichwort_adresse_stichwort_id_seq CASCADE;
CREATE SEQUENCE adresse_stichwort_adresse_stichwort_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;

ALTER TABLE public.adresse_stichwort_adresse_stichwort_id_seq OWNER TO postgres;

--
-- adresse_stichwort table
--
DROP TABLE IF EXISTS adresse_stichwort CASCADE;
CREATE TABLE adresse_stichwort (
    adresse_stichwort_id bigint DEFAULT nextval('adresse_stichwort_adresse_stichwort_id_seq'::regclass) NOT NULL,
	  adresse_id bigint,
	  stichwort_id bigint,
    created_by bigint,
    created_at timestamp without time zone DEFAULT now(),
    updated_by bigint,
    updated_at timestamp without time zone
);

ALTER TABLE public.adresse_stichwort OWNER TO postgres;

--
-- adresse get_last_adresse_nr function
--
CREATE OR REPLACE FUNCTION get_last_adresse_nr()
    RETURNS TRIGGER AS
    $BODY$
DECLARE
    v_last_adresse_nr bigint;
BEGIN
  SELECT last_adresse_nr INTO v_last_adresse_nr FROM params WHERE params_id = 1;
  UPDATE params set last_adresse_nr = v_last_adresse_nr + 1 WHERE params_id = 1;
  NEW.adresse_nr = v_last_adresse_nr + 1;
  RETURN NEW;
END $BODY$
LANGUAGE plpgsql;

ALTER FUNCTION public.get_last_adresse_nr() OWNER TO postgres;

--
-- adresse last adresse_nr trigger
--
CREATE TRIGGER last_adresse_nr BEFORE INSERT ON adresse
FOR EACH ROW EXECUTE PROCEDURE get_last_adresse_nr();


------------------------------------------------------
-- eingangsrechnung module
------------------------------------------------------

--
-- eingangsrechnung sequence
--
DROP SEQUENCE IF EXISTS eingangsrechnung_eingangsrechnung_id_seq CASCADE;
CREATE SEQUENCE eingangsrechnung_eingangsrechnung_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;

ALTER TABLE public.eingangsrechnung_eingangsrechnung_id_seq OWNER TO postgres;

--
-- eingangsrechnung table
--
DROP TABLE IF EXISTS eingangsrechnung CASCADE;
CREATE TABLE eingangsrechnung (
    eingangsrechnung_id bigint DEFAULT nextval('eingangsrechnung_eingangsrechnung_id_seq'::regclass) NOT NULL,
  	beleg_nr bigint,
  	belegdatum timestamp without time zone NOT NULL,
  	rechnungs_nr character varying(50) NOT NULL,
  	rechnungsdatum timestamp without time zone NOT NULL,
  	lieferant character varying(100) NOT NULL,
  	gegenstand character varying(100) NOT NULL,
    betrag numeric(15,2) NOT NULL,
  	waehrung_id bigint NOT NULL,
  	adresse_id bigint,
    zahlungsstatus character varying(20) NOT NULL,
  	notizen text,
	  created_by bigint,
    created_at timestamp without time zone DEFAULT now(),
    updated_by bigint,
    updated_at timestamp without time zone
);

ALTER TABLE public.eingangsrechnung OWNER TO postgres;

--
-- eingangsrechnung get_last_eingangsrechnung_beleg_nr function
--
CREATE OR REPLACE FUNCTION get_last_eingangsrechnung_beleg_nr()
    RETURNS TRIGGER AS
$BODY$
DECLARE
    v_last_beleg_nr bigint;
BEGIN
  SELECT last_eingangsrechnung_beleg_nr INTO v_last_beleg_nr FROM params WHERE params_id = 1;
  UPDATE params set last_eingangsrechnung_beleg_nr = v_last_beleg_nr + 1 WHERE params_id = 1;
  NEW.beleg_nr = v_last_beleg_nr + 1;
  RETURN NEW;
END $BODY$
    LANGUAGE plpgsql;

ALTER FUNCTION public.get_last_eingangsrechnung_beleg_nr() OWNER TO postgres;

--
-- eingangsrechnung last beleg_nr trigger
--
CREATE TRIGGER last_eingangsrechnung_beleg_nr BEFORE INSERT ON eingangsrechnung
  FOR EACH ROW EXECUTE PROCEDURE get_last_eingangsrechnung_beleg_nr();

------------------------------------------------------
-- planung module
------------------------------------------------------

--
-- planung sequence
--
DROP SEQUENCE IF EXISTS planung_planung_id_seq CASCADE;
CREATE SEQUENCE planung_planung_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;

ALTER TABLE public.planung_planung_id_seq OWNER TO postgres;

--
-- planung table
--
DROP TABLE IF EXISTS planung CASCADE;
CREATE TABLE planung (
    planung_id bigint DEFAULT nextval('planung_planung_id_seq'::regclass) NOT NULL,
    planung_nr bigint NOT NULL,
    planung_params_id bigint NOT NULL,
    -- rahmendaten
    zusatz character varying(50) NOT NULL,
    titel character varying(100) NOT NULL,
    veranstaltung_ort character varying(100) NOT NULL,
    veranstaltung_beginn timestamp without time zone NOT NULL,
    veranstaltung_ende timestamp without time zone NOT NULL,
    vorbereitungstreffen_ort character varying(50),
    vorbereitungstreffen_beginn timestamp without time zone,
    vorbereitungstreffen_ende timestamp without time zone,
    nachbereitungstreffen_ort character varying(50),
    nachbereitungstreffen_beginn timestamp without time zone,
    nachbereitungstreffen_ende timestamp without time zone,
    fachbereich_id bigint NOT NULL,
    geschaeftsbereich_id bigint NOT NULL,
    arbeitsaufwand_id bigint NOT NULL,
    arbeitsaufwand_aufschlag decimal(5,2),
    tage_nach_wbg integer NOT NULL,
    zgfaktor_id bigint NOT NULL,
    zgfaktor_aufschlag decimal(5,2),
    fahrtkasse_kostenstelle integer,
    -- personen
    zahlende_tn_de integer,
    zahlende_tn_ausl integer,
    nicht_zahlende_tn_de integer,
    nicht_zahlende_tn_ausl integer,
    teilnehmer_nach_wgb_de integer,
    teilnehmer_nach_wgb_ausl integer,
    hauptberufliche_mitarbeiter integer,
    honorar_mitarbeiter integer,
    unbezahlte_mitarbeiter integer,
    fremde_mitarbeiter integer,
    zusaetzliche_reise_kosten numeric(15,2),
    zusaetzliche_verpflegungskosten numeric(15,2),
    weitere_ausgaben numeric(15,2),
    ausl_mitarbeiter integer,
    unterkunfstage integer,
    verpflegungstage decimal(5,2),
    honorartage integer,
    honorarsatz numeric(15,2),
    reisekosten numeric(15,2),
    --versicherung
    veranstaltung_in_de boolean,
    regress_versicherung boolean,
    reiserecht boolean,
    visagebuehr_pro_person numeric(15,2),
    visa_nebenkosten numeric(15,2),
    fuehrungen_gesamt numeric(15,2),
    externe_referent_gesamt numeric(15,2),
    externe_dolmetscher_gesamt numeric(15,2),
    materialkosten_gesamt numeric(15,2),
    eintrittsgelder_pro_de_tn numeric(15,2),
    eintrittsgelder_pro_ausl_tn numeric(15,2),
    sonstigekosten_gesamt numeric(15,2),
    sonstigekosten_pro_tn numeric(15,2),
    -- reisekosten
    bahnfahrt_pro_person numeric(15,2),
    flugkosten_pro_person numeric(15,2),
    bustransfers_gesamt numeric(15,2),
    weitere_fahrten_im_land_pro_person numeric(15,2),
    fahrtkosten_fuer_ausl_tn_pro_person numeric(15,2),
    uebernachtungskosten_pro_nacht numeric(15,2),
    aufpreis_einzelzimmer_pro_nacht numeric(15,2),
    anzahl_naechte_pro_de_tn integer,
    anzahl_naechte_pro_ausl_tn integer,
    verpflegungskosten_pro_tag numeric(15,2),
    verpflegungstage_pro_de_tn decimal(5,2),
    verpflegungstage_pro_ausl_tn decimal(5,2),
    pauschalpreis numeric(15,2),
    -- mitarbeiter und preise
    mitarbeiter1_id bigint,
    mitarbeiter2_id bigint,
    partner_id bigint,
    anmeldung_ibb boolean,
    anmeldung_partner boolean,
    festpreis numeric(15,2),
    bedingung1 character varying(50),
    bedingung2 character varying(50),
    bedingung3 character varying(50),
    bedingung4 character varying(50),
    bedingung5 character varying(50),
    bedingung6 character varying(50),
    preis1 numeric(15,2),
    preis2 numeric(15,2),
    preis3 numeric(15,2),
    preis4 numeric(15,2),
    preis5 numeric(15,2),
    preis6 numeric(15,2),
    wird_durchgefuehrt boolean,
    kurzbeschreibung_eb_plan text,
    notizen text,
    -- computed fields
    va_tage integer,
    created_by bigint,
    created_at timestamp without time zone DEFAULT now(),
    updated_by bigint,
    updated_at timestamp without time zone
);

ALTER TABLE public.planung OWNER TO postgres;

--
-- zuschuss sequence
--
DROP SEQUENCE IF EXISTS zuschuss_zuschuss_id_seq CASCADE;
CREATE SEQUENCE zuschuss_zuschuss_id_seq
INCREMENT BY 1
NO MAXVALUE
NO MINVALUE
CACHE 1;

ALTER TABLE public.zuschuss_zuschuss_id_seq OWNER TO postgres;

--
-- zuschuss table
--
DROP TABLE IF EXISTS zuschuss CASCADE;
CREATE TABLE zuschuss (
    zuschuss_id bigint DEFAULT nextval('zuschuss_zuschuss_id_seq'::regclass) NOT NULL,
    planung_id bigint NOT NULL,
    bezeichnung character varying(100),
    pro_de_tn numeric(15,2),
    pro_de_tn_gesamt numeric(15,2),
    pro_ausl_tn numeric(15,2),
    pro_ausl_tn_gesamt numeric(15,2),
    manuell_berechnet numeric(15,2),
    gesamt numeric(15,2),
    bewilligt boolean,
    created_by bigint,
    created_at timestamp without time zone DEFAULT now(),
    updated_by bigint,
    updated_at timestamp without time zone
);

ALTER TABLE public.zuschuss OWNER TO postgres;

--
-- planung_params sequence
--
DROP SEQUENCE IF EXISTS planung_params_planung_params_id_seq CASCADE;
CREATE SEQUENCE planung_params_planung_params_id_seq
INCREMENT BY 1
NO MAXVALUE
NO MINVALUE
CACHE 1;

ALTER TABLE public.planung_params_planung_params_id_seq OWNER TO postgres;

--
-- planung_params table
--
DROP TABLE IF EXISTS planung_params CASCADE;
CREATE TABLE planung_params (
    planung_params_id bigint DEFAULT nextval('planung_params_planung_params_id_seq'::regclass) NOT NULL,
    honorare_fuer_ibb_begleitung numeric(15,2) NOT NULL,
    ibb_strukturkosten_pro_tn_pro_tag numeric(15,2) NOT NULL,
    ibb_erloes decimal(5,2) NOT NULL,
    -- versicherung
    haftpflicht_unfall_versicherung_pro_person_tag decimal(5,2) NOT NULL,
    auslandsreisekranken_versicherung_pro_person_tag decimal(5,2) NOT NULL,
    krankenversicherung_fuer_ausl_tn_pro_person_tag decimal(5,2) NOT NULL,
    regressversicherung_va_in_de_bis_8_tage decimal(5,2) NOT NULL,
    regressversicherung_va_in_de_bis_22_tage decimal(5,2) NOT NULL,
    regressversicherung_va_in_de_bis_42_tage decimal(5,2) NOT NULL,
    regressversicherung_va_im_ausland_bis_8_tage decimal(5,2) NOT NULL,
    regressversicherung_va_im_ausland_bis_22_tage decimal(5,2) NOT NULL,
    regressversicherung_va_im_ausland_bis_42_tage decimal(5,2) NOT NULL,
    rechtschutzversicherung_va_bis_8_tage decimal(5,2) NOT NULL,
    rechtschutzversicherung_va_bis_14_tage decimal(5,2) NOT NULL,
    rechtschutzversicherung_va_bis_22_tage decimal(5,2) NOT NULL,
    reisepreissicherung_pro_person_tag decimal(5,2) NOT NULL,
    created_by bigint,
    created_at timestamp without time zone DEFAULT now(),
    updated_by bigint,
    updated_at timestamp without time zone
);

ALTER TABLE public.planung_params OWNER TO postgres;


------------------------------------------------------
-- farhtkasse module
------------------------------------------------------

--
-- fahrtkasse sequence
--
DROP SEQUENCE IF EXISTS fahrtkasse_fahrtkasse_id_seq CASCADE;
CREATE SEQUENCE fahrtkasse_fahrtkasse_id_seq
INCREMENT BY 1
NO MAXVALUE
NO MINVALUE
CACHE 1;

ALTER TABLE public.fahrtkasse_fahrtkasse_id_seq OWNER TO postgres;

--
-- fahrtkasse table
--
DROP TABLE IF EXISTS fahrtkasse CASCADE;
CREATE TABLE fahrtkasse (
    fahrtkasse_id bigint DEFAULT nextval('fahrtkasse_fahrtkasse_id_seq'::regclass) NOT NULL,
    fahrtkasse_nr bigint,
    belegdatum timestamp without time zone NOT NULL,
    adresse_id bigint NOT NULL,
    planung_id bigint NOT NULL,
    betrag numeric(15,2) NOT NULL,
    zahlungsart character varying(50),
    zahlen_am timestamp without time zone,
    abgerechnet boolean,
    abgerechnet_am timestamp without time zone,
    ausgaben_gesamt numeric(15,2) NOT NULL,
    einnahmen_gesamt numeric(15,2) NOT NULL,
    saldo numeric(15,2) NOT NULL,
    notizen text,
    created_by bigint,
    created_at timestamp without time zone DEFAULT now(),
    updated_by bigint,
    updated_at timestamp without time zone
);

ALTER TABLE public.fahrtkasse OWNER TO postgres;

--
-- fahrtkasse_waehrung sequence
--
DROP SEQUENCE IF EXISTS fahrtkasse_waehrung_fahrtkasse_ewaehrung_id_seq CASCADE;
CREATE SEQUENCE fahrtkasse_waehrung_fahrtkasse_waehrung_id_seq
INCREMENT BY 1
NO MAXVALUE
NO MINVALUE
CACHE 1;

ALTER TABLE public.fahrtkasse_waehrung_fahrtkasse_waehrung_id_seq OWNER TO postgres;

--
-- fahrtkasse_waehrung table
--
DROP TABLE IF EXISTS fahrtkasse_waehrung CASCADE;
CREATE TABLE fahrtkasse_waehrung (
    fahrtkasse_waehrung_id bigint DEFAULT nextval('fahrtkasse_waehrung_fahrtkasse_waehrung_id_seq'::regclass) NOT NULL,
    fahrtkasse_id bigint NOT NULL,
    waehrung_id bigint NOT NULL,
    kurs numeric(15,2) NOT NULL,
    multiply_eur character varying(10),
    created_by bigint,
    created_at timestamp without time zone DEFAULT now(),
    updated_by bigint,
    updated_at timestamp without time zone
);

ALTER TABLE public.fahrtkasse_waehrung OWNER TO postgres;

--
-- fahrtkasse_detail sequence
--
DROP SEQUENCE IF EXISTS fahrtkasse_detail_fahrtkasse_detail_id_seq CASCADE;
CREATE SEQUENCE fahrtkasse_detail_fahrtkasse_detail_id_seq
INCREMENT BY 1
NO MAXVALUE
NO MINVALUE
CACHE 1;

ALTER TABLE public.fahrtkasse_detail_fahrtkasse_detail_id_seq OWNER TO postgres;

--
-- fahrtkasse_detail table
--
DROP TABLE IF EXISTS fahrtkasse_detail CASCADE;
CREATE TABLE fahrtkasse_detail (
    fahrtkasse_detail_id bigint DEFAULT nextval('fahrtkasse_detail_fahrtkasse_detail_id_seq'::regclass) NOT NULL,
    fahrtkasse_id bigint NOT NULL,
    nummer int,
    empfaenger character varying(100),
    zweck character varying(100),
    betrag numeric(15,2) NOT NULL,
    waehrung_id bigint NOT NULL,
    betrag_in_eur numeric(15,2) NOT NULL,
    konto_id bigint NOT NULL,
    created_by bigint,
    created_at timestamp without time zone DEFAULT now(),
    updated_by bigint,
    updated_at timestamp without time zone
);

ALTER TABLE public.fahrtkasse_detail OWNER TO postgres;

--
-- fahrtkasse get last fahrtkasse_nr function
--
CREATE OR REPLACE FUNCTION get_last_fahrtkasse_nr()
    RETURNS TRIGGER AS
    $BODY$
DECLARE
    v_last_fahrtkasse_nr bigint;
BEGIN
  SELECT last_fahrtkasse_nr INTO v_last_fahrtkasse_nr FROM params WHERE params_id = 1;
  UPDATE params set last_fahrtkasse_nr = v_last_fahrtkasse_nr + 1 WHERE params_id = 1;
  NEW.fahrtkasse_nr = v_last_fahrtkasse_nr + 1;
  RETURN NEW;
END $BODY$
LANGUAGE plpgsql;

ALTER FUNCTION public.get_last_fahrtkasse_nr() OWNER TO postgres;

--
-- fahrtkasse last fahrtkasse_nr trigger
--
CREATE TRIGGER last_fahrtkasse_nr BEFORE INSERT ON fahrtkasse
FOR EACH ROW EXECUTE PROCEDURE get_last_fahrtkasse_nr();

--
-- primary keys
--
ALTER TABLE ONLY anrede
    ADD CONSTRAINT anrede_pkey PRIMARY KEY (anrede_id);

ALTER TABLE ONLY titel
    ADD CONSTRAINT titel_pkey PRIMARY KEY (titel_id);

ALTER TABLE ONLY land
    ADD CONSTRAINT land_pkey PRIMARY KEY (land_id);

ALTER TABLE ONLY bundesland
    ADD CONSTRAINT bundesland_pkey PRIMARY KEY (bundesland_id);

ALTER TABLE ONLY fachbereich
    ADD CONSTRAINT fachbereich_pkey PRIMARY KEY (fachbereich_id);

ALTER TABLE ONLY geschaeftsbereich
    ADD CONSTRAINT geschaeftsbereich_pkey PRIMARY KEY (geschaeftsbereich_id);

ALTER TABLE ONLY arbeitsaufwand
    ADD CONSTRAINT arbeitsaufwand_pkey PRIMARY KEY (arbeitsaufwand_id);

ALTER TABLE ONLY waehrung
    ADD CONSTRAINT waehrung_pkey PRIMARY KEY (waehrung_id);

ALTER TABLE ONLY zgfaktor
    ADD CONSTRAINT zgfaktor_pkey PRIMARY KEY (zgfaktor_id);

ALTER TABLE ONLY konto
    ADD CONSTRAINT konto_pkey PRIMARY KEY (konto_id);

ALTER TABLE ONLY stichwort
    ADD CONSTRAINT stichwort_pkey PRIMARY KEY (stichwort_id);

ALTER TABLE ONLY app_user_group
    ADD CONSTRAINT app_user_group_pkey PRIMARY KEY (app_user_group_id);

ALTER TABLE ONLY app_right
    ADD CONSTRAINT app_right_pkey PRIMARY KEY (app_right_id);

ALTER TABLE ONLY app_user
    ADD CONSTRAINT app_user_pkey PRIMARY KEY (app_user_id);

ALTER TABLE ONLY params
    ADD CONSTRAINT params_pkey PRIMARY KEY (params_id);

ALTER TABLE ONLY adresse
    ADD CONSTRAINT adresse_pkey PRIMARY KEY (adresse_id);

ALTER TABLE ONLY adresse_stichwort
    ADD CONSTRAINT adresse_stichwort_pkey PRIMARY KEY (adresse_stichwort_id);

ALTER TABLE ONLY eingangsrechnung
    ADD CONSTRAINT eingangsrechnung_pkey PRIMARY KEY (eingangsrechnung_id);

ALTER TABLE ONLY planung
    ADD CONSTRAINT planung_pkey PRIMARY KEY (planung_id);

ALTER TABLE ONLY zuschuss
    ADD CONSTRAINT zuschuss_pkey PRIMARY KEY (zuschuss_id);

ALTER TABLE ONLY planung_params
    ADD CONSTRAINT planung_params_pkey PRIMARY KEY (planung_params_id);

ALTER TABLE ONLY fahrtkasse
    ADD CONSTRAINT fahrtkasse_pkey PRIMARY KEY (fahrtkasse_id);

ALTER TABLE ONLY fahrtkasse_waehrung
    ADD CONSTRAINT fahrtkasse_waehrung_pkey PRIMARY KEY (fahrtkasse_waehrung_id);

ALTER TABLE ONLY fahrtkasse_detail
    ADD CONSTRAINT fahrtkasse_detail_pkey PRIMARY KEY (fahrtkasse_detail_id);


--
-- unique indexes
--
CREATE UNIQUE INDEX idx_unq_anrede_bezeichnung ON anrede USING btree (bezeichnung);

CREATE UNIQUE INDEX idx_unq_titel_bezeichnung ON titel USING btree (bezeichnung);

CREATE UNIQUE INDEX idx_unq_land_isocode2 ON land USING btree (isocode2);
CREATE UNIQUE INDEX idx_unq_land_isocode3 ON land USING btree (isocode3);
CREATE UNIQUE INDEX idx_unq_land_name ON land USING btree (name);

CREATE UNIQUE INDEX idx_unq_bundesland_name ON bundesland USING btree (name);

CREATE UNIQUE INDEX idx_unq_fachbereich_name ON fachbereich USING btree (name);

CREATE UNIQUE INDEX idx_unq_geschaeftsbereich_name ON geschaeftsbereich USING btree (name);

CREATE UNIQUE INDEX idx_unq_arbeitsaufwand_bezeichnung ON arbeitsaufwand USING btree (bezeichnung);

CREATE UNIQUE INDEX idx_unq_waehrung_code ON waehrung USING btree (code);
CREATE UNIQUE INDEX idx_unq_waehrung_bezeichnung ON waehrung USING btree (bezeichnung);

CREATE UNIQUE INDEX idx_unq_zgfaktor_bezeichnung ON zgfaktor USING btree (bezeichnung);

CREATE UNIQUE INDEX idx_unq_konto_konto_nr ON konto USING btree (konto_nr);
CREATE UNIQUE INDEX idx_unq_konto_name ON konto USING btree (name);

CREATE UNIQUE INDEX idx_unq_stichwort_bezeichnung ON stichwort USING btree (bezeichnung);

CREATE UNIQUE INDEX idx_unq_app_user_username ON app_user USING btree (username);

CREATE UNIQUE INDEX idx_unq_app_user_group_name ON app_user_group USING btree (name);

CREATE UNIQUE INDEX idx_unq_fahrtkasse_fahrtkasse_nr ON fahrtkasse USING btree (fahrtkasse_nr);

--
-- foreign keys
--

-- bundesland
CREATE INDEX idx_fk_bundesland_land_id ON bundesland USING btree (land_id);
ALTER TABLE ONLY bundesland
	ADD CONSTRAINT bundesland_land_id_fkey FOREIGN KEY (land_id) REFERENCES land(land_id)
	ON UPDATE CASCADE ON DELETE RESTRICT;

-- adresse
CREATE INDEX idx_fk_adresse_anrede_id ON adresse USING btree (anrede_id);
ALTER TABLE ONLY adresse
	ADD CONSTRAINT adresse_anrede_id_fkey FOREIGN KEY (anrede_id) REFERENCES anrede(anrede_id)
	ON UPDATE CASCADE ON DELETE RESTRICT;

CREATE INDEX idx_fk_adresse_titel_id ON adresse USING btree (titel_id);
ALTER TABLE ONLY adresse
	ADD CONSTRAINT adresse_titel_id_fkey FOREIGN KEY (titel_id) REFERENCES titel(titel_id)
	ON UPDATE CASCADE ON DELETE RESTRICT;

CREATE INDEX idx_fk_adresse_land_id ON adresse USING btree (land_id);
ALTER TABLE ONLY adresse
	ADD CONSTRAINT adresse_land_id_fkey FOREIGN KEY (land_id) REFERENCES land(land_id)
	ON UPDATE CASCADE ON DELETE RESTRICT;

CREATE INDEX idx_fk_adresse_bundesland_id ON adresse USING btree (bundesland_id);
ALTER TABLE ONLY adresse
	ADD CONSTRAINT adresse_bundesland_id_fkey FOREIGN KEY (bundesland_id) REFERENCES bundesland(bundesland_id)
	ON UPDATE CASCADE ON DELETE RESTRICT;

-- adresse_stichwort
CREATE INDEX idx_fk_adresse_stichwort_adresse_id ON adresse_stichwort USING btree (adresse_id);
ALTER TABLE ONLY adresse_stichwort
	ADD CONSTRAINT adresse_stichwort_adresse_id_fkey FOREIGN KEY (adresse_id) REFERENCES adresse(adresse_id)
	ON UPDATE CASCADE ON DELETE CASCADE;

CREATE INDEX idx_fk_adresse_stichwort_stichwort_id ON adresse_stichwort USING btree (stichwort_id);
ALTER TABLE ONLY adresse_stichwort
	ADD CONSTRAINT adresse_stichwort_stichwort_id_fkey FOREIGN KEY (stichwort_id) REFERENCES stichwort(stichwort_id)
	ON UPDATE CASCADE ON DELETE CASCADE;

-- eingansrechnung
CREATE INDEX idx_fk_eingangsrechnung_waehrung_id ON eingangsrechnung USING btree (waehrung_id);
ALTER TABLE ONLY eingangsrechnung
	ADD CONSTRAINT eingangsrechnung_waehrung_id_fkey FOREIGN KEY (waehrung_id) REFERENCES waehrung(waehrung_id)
	ON UPDATE CASCADE ON DELETE RESTRICT;

CREATE INDEX idx_fk_eingangsrechnung_adresse_id ON eingangsrechnung USING btree (adresse_id);
ALTER TABLE ONLY eingangsrechnung
	ADD CONSTRAINT eingangsrechnung_adresse_id_fkey FOREIGN KEY (adresse_id) REFERENCES adresse(adresse_id)
	ON UPDATE CASCADE ON DELETE RESTRICT;

-- app_right
CREATE INDEX idx_fk_app_right_app_user_group_id ON app_right USING btree (app_user_group_id);
ALTER TABLE ONLY app_right
  ADD CONSTRAINT app_right_app_user_group_id_fkey FOREIGN KEY (app_user_group_id) REFERENCES app_user_group(app_user_group_id)
  ON UPDATE CASCADE ON DELETE RESTRICT;

-- app_user
CREATE INDEX idx_fk_app_user_app_user_group_id ON app_user USING btree (app_user_group_id);
ALTER TABLE ONLY app_user
    ADD CONSTRAINT app_user_app_user_group_id_fkey FOREIGN KEY (app_user_group_id) REFERENCES app_user_group(app_user_group_id)
    ON UPDATE CASCADE ON DELETE RESTRICT;

-- planung
CREATE INDEX idx_fk_planung_planung_params_id ON planung USING btree (planung_params_id);
ALTER TABLE ONLY planung
  ADD CONSTRAINT planung_planung_params_id_fkey FOREIGN KEY (planung_params_id) REFERENCES planung_params(planung_params_id)
  ON UPDATE CASCADE ON DELETE RESTRICT;

CREATE INDEX idx_fk_planung_fachbereich_id ON planung USING btree (fachbereich_id);
ALTER TABLE ONLY planung
  ADD CONSTRAINT planung_fachbereich_id_fkey FOREIGN KEY (fachbereich_id) REFERENCES fachbereich(fachbereich_id)
  ON UPDATE CASCADE ON DELETE RESTRICT;

CREATE INDEX idx_fk_planung_geschaeftsbereich_id ON planung USING btree (geschaeftsbereich_id);
ALTER TABLE ONLY planung
  ADD CONSTRAINT planung_geschaeftsbereich_id_fkey FOREIGN KEY (geschaeftsbereich_id) REFERENCES geschaeftsbereich(geschaeftsbereich_id)
  ON UPDATE CASCADE ON DELETE RESTRICT;

CREATE INDEX idx_fk_planung_arbeitsaufwand_id ON planung USING btree (arbeitsaufwand_id);
ALTER TABLE ONLY planung
  ADD CONSTRAINT planung_arbeitsaufwand_id_fkey FOREIGN KEY (arbeitsaufwand_id) REFERENCES arbeitsaufwand(arbeitsaufwand_id)
  ON UPDATE CASCADE ON DELETE RESTRICT;

CREATE INDEX idx_fk_planung_zgfaktor_id ON planung USING btree (zgfaktor_id);
ALTER TABLE ONLY planung
  ADD CONSTRAINT planung_zgfaktor_id_fkey FOREIGN KEY (zgfaktor_id) REFERENCES zgfaktor(zgfaktor_id)
  ON UPDATE CASCADE ON DELETE RESTRICT;

CREATE INDEX idx_fk_planung_mitarbeiter1_id ON planung USING btree (mitarbeiter1_id);
ALTER TABLE ONLY planung
  ADD CONSTRAINT planung_mitarbeiter1_id_fkey FOREIGN KEY (mitarbeiter1_id) REFERENCES adresse(adresse_id)
  ON UPDATE CASCADE ON DELETE RESTRICT;

CREATE INDEX idx_fk_planung_mitarbeiter2_id ON planung USING btree (mitarbeiter2_id);
ALTER TABLE ONLY planung
  ADD CONSTRAINT planung_mitarbeiter2_id_fkey FOREIGN KEY (mitarbeiter2_id) REFERENCES adresse(adresse_id)
  ON UPDATE CASCADE ON DELETE RESTRICT;

CREATE INDEX idx_fk_planung_partner_id ON planung USING btree (partner_id);
ALTER TABLE ONLY planung
  ADD CONSTRAINT planung_partner_id_fkey FOREIGN KEY (partner_id) REFERENCES adresse(adresse_id)
  ON UPDATE CASCADE ON DELETE RESTRICT;

-- zuschuss
CREATE INDEX idx_fk_zuschuss_planung_id ON zuschuss USING btree (planung_id);
ALTER TABLE ONLY zuschuss
  ADD CONSTRAINT zuschuss_planung_id_fkey FOREIGN KEY (planung_id) REFERENCES planung(planung_id)
  ON UPDATE CASCADE ON DELETE RESTRICT;

-- fahrtkasse
CREATE INDEX idx_fk_fahrtkasse_adresse_id ON fahrtkasse USING btree (adresse_id);
ALTER TABLE ONLY fahrtkasse
  ADD CONSTRAINT fahrtkasse_adresse_id_fkey FOREIGN KEY (adresse_id) REFERENCES adresse(adresse_id)
  ON UPDATE CASCADE ON DELETE RESTRICT;

CREATE INDEX idx_fk_fahrtkasse_planung_id ON fahrtkasse USING btree (planung_id);
ALTER TABLE ONLY fahrtkasse
  ADD CONSTRAINT fahrtkasse_planung_id_fkey FOREIGN KEY (planung_id) REFERENCES planung(planung_id)
  ON UPDATE CASCADE ON DELETE RESTRICT;

-- fahrtkasse_waehrung
CREATE INDEX idx_fk_fahrtkasse_waehrung_fahrtkasse_id ON fahrtkasse_waehrung USING btree (fahrtkasse_id);
ALTER TABLE ONLY fahrtkasse_waehrung
  ADD CONSTRAINT fahrtkasse_waehrung_fahrtkasse_id_fkey FOREIGN KEY (fahrtkasse_id) REFERENCES fahrtkasse(fahrtkasse_id)
  ON UPDATE CASCADE ON DELETE RESTRICT;

CREATE INDEX idx_fk_fahrtkasse_waehrung_waehrung_id ON fahrtkasse_waehrung USING btree (waehrung_id);
ALTER TABLE ONLY fahrtkasse_waehrung
  ADD CONSTRAINT fahrtkasse_waehrung_waehrung_id_fkey FOREIGN KEY (waehrung_id) REFERENCES waehrung(waehrung_id)
  ON UPDATE CASCADE ON DELETE RESTRICT;


-- fahrtkasse_detail
CREATE INDEX idx_fk_fahrtkasse_detail_fahrtkasse_id ON fahrtkasse_detail USING btree (fahrtkasse_id);
ALTER TABLE ONLY fahrtkasse_detail
  ADD CONSTRAINT fahrtkasse_detail_fahrtkasse_id_fkey FOREIGN KEY (fahrtkasse_id) REFERENCES fahrtkasse(fahrtkasse_id)
  ON UPDATE CASCADE ON DELETE RESTRICT;

CREATE INDEX idx_fk_fahrtkasse_detail_waehrung_id ON fahrtkasse_detail USING btree (waehrung_id);
ALTER TABLE ONLY fahrtkasse_detail
  ADD CONSTRAINT fahrtkasse_detail_waehrung_id_fkey FOREIGN KEY (waehrung_id) REFERENCES waehrung(waehrung_id)
  ON UPDATE CASCADE ON DELETE RESTRICT;

CREATE INDEX idx_fk_fahrtkasse_detail_konto_id ON fahrtkasse_detail USING btree (konto_id);
ALTER TABLE ONLY fahrtkasse_detail
  ADD CONSTRAINT fahrtkasse_detail_konto_id_fkey FOREIGN KEY (konto_id) REFERENCES konto(konto_id)
  ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- revoke and grant
--
REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;
