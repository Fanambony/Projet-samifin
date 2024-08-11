CREATE OR REPLACE FUNCTION public.custom_seq(text_prefix text, sequence_name text, nombre integer)
 RETURNS text
 LANGUAGE plpgsql
AS $function$
DECLARE
  seq_value INTEGER;
  custom_sequence TEXT;
BEGIN
  -- Obtention du prochain numéro de séquence de la séquence spécifiée
  EXECUTE format('SELECT nextval(''%I'')', sequence_name) INTO seq_value;
  -- Formatage du numéro de séquence en utilisant le préfixe spécifié
  custom_sequence := text_prefix || LPAD(seq_value::TEXT, nombre, '0');
  RETURN custom_sequence;
END;
$function$
;

CREATE SEQUENCE "public".direction_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE "public".fonction_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE "public".utilisateur_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE "public".type_conge_id_seq START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE "public".demande_conge_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE "public".etat_demande_conge_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE "public".etat_id_seq START WITH 1 INCREMENT BY 1;

custom_seq('DIR'::character varying, 'direction_id_seq'::character varying, 3)
custom_seq('FON'::character varying, 'fonction_id_seq'::character varying, 3)
custom_seq('USR'::character varying, 'utilisateur_id_seq'::character varying, 6)
custom_seq('TCG'::character varying, 'type_conge_id_seq'::character varying, 3)

custom_seq('DEM'::character varying, 'demande_conge_id_seq'::character varying, 6)
custom_seq('EDC'::character varying, 'etat_demande_conge_id_seq'::character varying, 3)
custom_seq('ETA'::character varying, 'etat_id_seq'::character varying, 3)

select string_agg(substring('gzhjen1112555dhgffhhgc!/?()_|' FROM floor(random() * 73 + 1)::int FOR 1), '') FROM generate_series(0, 10);

INSERT INTO "public".fonction
	( id, nom, id_direction, type) VALUES 
( default, 'Directeur Administratif et Financier', 'DIR001', 5),
( default, 'Chef de service personnel et logistique', 'DIR001', 10),
( default, 'Chef de Service Logistique', 'DIR001', 10),
( default, 'Directeur des Etudes et Stratégies', 'DIR002', 5),
( default, 'Conseiller Technique', 'DIR002', 10),
( default, 'Chef de service études', 'DIR002', 10),
( default, 'Directeur Général', 'DIR003', 1),
( default, 'Assistante de direction', 'DIR003', 10),
( default, 'Traductrice et anthropologue', 'DIR003', 10)

INSERT INTO "public".utilisateur
	( id, matricule, nom, prenom, date_naissance, email, mdp, date_entre, id_fonction) VALUES 
( default, 311396, 'RAKOTOMALALA', 'Faly Andrianaivo', '15-10-1990', 'rakotomalala@samifin.mg', 'rakotomalala', '08-06-2011', 'FON001'),
( default, 317654, 'RAZAFIMIARANTSOA', 'Andonirina', '15-10-1990', 'razafimiarantsoa@samifin.mg', 'razafimiarantsoa', '16-04-2008', 'FON002'),
( default, 352829, 'ANDRIANANDRASANA', 'Fifaliantsoa', '15-10-1990', 'rakotomalala@samifin.mg', 'rakotomalala', '03-03-2014', 'FON003'),
( default, 296269, 'RAZOARIHOLY', 'Noroseheno', '15-10-1990', 'razoariholy@samifin.mg', 'razoariholy', '08-12-2023', 'FON004'),
( default, 319715, 'RABEMANANJARA', 'Emilson', '15-10-1990', 'rabemananjara@samifin.mg', 'rabemananjara', '17-11-2008', 'FON005'),
( default, 423074, 'RATSIMBA', 'Herivelo Miharijao', '15-10-1990', 'ratsimba@samifin.mg', 'ratsimba', '28-04-2018', 'FON006'),
( default, 351633, 'RAJAONARISON', 'Mamitiana', '15-10-1990', 'rajaonarison@samifin.mg', 'rajaonarison', '08-06-2010', 'FON007'),
( default, 317655, 'RAOLIMALALA', 'José Nathalie', '15-10-1990', 'raolimalala@samifin.mg', 'raolimalala', '08-06-2011', 'FON008'),
( default, 403373, 'RAZANAMANANA', 'Haja Lalao', '15-10-1990', 'razanamanana@samifin.mg', 'razanamanana', '08-06-2024', 'FON009')