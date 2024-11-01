package com.example.gestionrh.Model.Entity;

import jakarta.persistence.*;
import java.sql.Date;

import java.util.List;

@Entity
@Table(name = "v_utilisateur_detailler")
public class VUtilisateurDetailler {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "num_ligne")
	Long numLigne;
	@Column(name = "id_utilisateur")
	String idUtilisateur;
	@Column(name = "etat_genre")
	Integer etatGenre;
	@Column(name = "etat_type_utilisateur")
	Integer etatTypeUtilisateur;
	@Column(name = "etat_utilisateur")
	Integer etatUtilisateur;
	@Column(name = "id_detail_utilisateur")
	String idDetailUtilisateur;
	@Column(name = "id_fonction")
	String idFonction;
	@Column(name = "id_direction")
	String idDirection;
	@Column(name = "id_qualite")
	String idQualite;
	@Column(name = "id_categorie")
	String idCategorie;
	@Column(name = "id_corps_appartenance")
	String idCorpsAppartenance;
	@Column(name = "id_indice")
	String idIndice;
	@Column(name = "id_service_employeur")
	String idServiceEmployeur;
	@Column(name = "id_localite_service")
	String idLocaliteService;
	@Column(name = "nom")
	String nom;
	@Column(name = "prenom")
	String prenom;
	@Column(name = "date_naissance")
	Date dateNaissance;
	@Column(name = "genre")
	String genre;
	@Column(name = "type_utilisateur")
	String typeUtilisateur;
	@Column(name = "etat")
	String etat;
	@Column(name = "image")
	byte[] image;
	@Column(name = "matricule")
	String matricule;
	@Column(name = "email")
	String email;
	@Column(name = "mdp")
	String mdp;
	@Column(name = "telephone")
	String telephone;
	@Column(name = "date_entre")
	Date dateEntre;
	@Column(name = "numero_decision")
	String numeroDecision;
	@Column(name = "fonction")
	String fonction;
	@Column(name = "direction")
	String direction;
	@Column(name = "mdp_provisoir")
	Boolean mdpProvisoir;
	@Column(name = "qualite")
	String qualite;
	@Column(name = "categorie")
	String categorie;
	@Column(name = "corps_appartenance")
	String corpsAppartenance;
	@Column(name = "indice")
	String indice;
	@Column(name = "service_employeur")
	String serviceEmployeur;
	@Column(name = "localite_service")
	String localiteService;

	@Transient
	List<VHistoriqueConge> historiqueConges;

    //SETTERS AND GETTERS

	public Long getNumLigne(){
		return this.numLigne;
	}
	public void setNumLigne(Long numLigne){
		this.numLigne = numLigne;
	}
	public String getIdUtilisateur(){
		return this.idUtilisateur;
	}
	public void setIdUtilisateur(String idUtilisateur){
		this.idUtilisateur = idUtilisateur;
	}
	public Integer getEtatGenre(){
		return this.etatGenre;
	}
	public void setEtatGenre(Integer etatGenre){
		this.etatGenre = etatGenre;
	}
	public Integer getEtatTypeUtilisateur(){
		return this.etatTypeUtilisateur;
	}
	public void setEtatTypeUtilisateur(Integer etatTypeUtilisateur){
		this.etatTypeUtilisateur = etatTypeUtilisateur;
	}
	public Integer getEtatUtilisateur(){
		return this.etatUtilisateur;
	}
	public void setEtatUtilisateur(Integer etatUtilisateur){
		this.etatUtilisateur = etatUtilisateur;
	}
	public String getIdDetailUtilisateur(){
		return this.idDetailUtilisateur;
	}
	public void setIdDetailUtilisateur(String idDetailUtilisateur){
		this.idDetailUtilisateur = idDetailUtilisateur;
	}
	public String getIdFonction(){
		return this.idFonction;
	}
	public void setIdFonction(String idFonction){
		this.idFonction = idFonction;
	}
	public String getIdDirection(){
		return this.idDirection;
	}
	public void setIdDirection(String idDirection){
		this.idDirection = idDirection;
	}
	public String getIdQualite(){
		return this.idQualite;
	}
	public void setIdQualite(String idQualite){
		this.idQualite = idQualite;
	}
	public String getIdCategorie(){
		return this.idCategorie;
	}
	public void setIdCategorie(String idCategorie){
		this.idCategorie = idCategorie;
	}
	public String getIdCorpsAppartenance(){
		return this.idCorpsAppartenance;
	}
	public void setIdCorpsAppartenance(String idCorpsAppartenance){
		this.idCorpsAppartenance = idCorpsAppartenance;
	}
	public String getIdIndice(){
		return this.idIndice;
	}
	public void setIdIndice(String idIndice){
		this.idIndice = idIndice;
	}
	public String getIdServiceEmployeur(){
		return this.idServiceEmployeur;
	}
	public void setIdServiceEmployeur(String idServiceEmployeur){
		this.idServiceEmployeur = idServiceEmployeur;
	}
	public String getIdLocaliteService(){
		return this.idLocaliteService;
	}
	public void setIdLocaliteService(String idLocaliteService){
		this.idLocaliteService = idLocaliteService;
	}
	public String getNom(){
		return this.nom;
	}
	public void setNom(String nom){
		this.nom = nom;
	}
	public String getPrenom(){
		return this.prenom;
	}
	public void setPrenom(String prenom){
		this.prenom = prenom;
	}
	public Date getDateNaissance(){
		return this.dateNaissance;
	}
	public void setDateNaissance(Date dateNaissance){
		this.dateNaissance = dateNaissance;
	}
	public String getGenre(){
		return this.genre;
	}
	public void setGenre(String genre){
		this.genre = genre;
	}
	public String getTypeUtilisateur(){
		return this.typeUtilisateur;
	}
	public void setTypeUtilisateur(String typeUtilisateur){
		this.typeUtilisateur = typeUtilisateur;
	}
	public String getEtat(){
		return this.etat;
	}
	public void setEtat(String etat){
		this.etat = etat;
	}
	public byte[] getImage(){
		return this.image;
	}
	public void setImage(byte[] image){
		this.image = image;
	}
	public String getMatricule(){
		return this.matricule;
	}
	public void setMatricule(String matricule){
		this.matricule = matricule;
	}
	public String getEmail(){
		return this.email;
	}
	public void setEmail(String email){
		this.email = email;
	}
	public String getMdp(){
		return this.mdp;
	}
	public void setMdp(String mdp){
		this.mdp = mdp;
	}
	public String getTelephone(){
		return this.telephone;
	}
	public void setTelephone(String telephone){
		this.telephone = telephone;
	}
	public Date getDateEntre(){
		return this.dateEntre;
	}
	public void setDateEntre(Date dateEntre){
		this.dateEntre = dateEntre;
	}
	public String getNumeroDecision(){
		return this.numeroDecision;
	}
	public void setNumeroDecision(String numeroDecision){
		this.numeroDecision = numeroDecision;
	}
	public String getFonction(){
		return this.fonction;
	}
	public void setFonction(String fonction){
		this.fonction = fonction;
	}
	public String getDirection(){
		return this.direction;
	}
	public void setDirection(String direction){
		this.direction = direction;
	}
	public Boolean getMdpProvisoir(){
		return this.mdpProvisoir;
	}
	public void setMdpProvisoir(Boolean mdpProvisoir){
		this.mdpProvisoir = mdpProvisoir;
	}
	public String getQualite(){
		return this.qualite;
	}
	public void setQualite(String qualite){
		this.qualite = qualite;
	}
	public String getCategorie(){
		return this.categorie;
	}
	public void setCategorie(String categorie){
		this.categorie = categorie;
	}
	public String getCorpsAppartenance(){
		return this.corpsAppartenance;
	}
	public void setCorpsAppartenance(String corpsAppartenance){
		this.corpsAppartenance = corpsAppartenance;
	}
	public String getIndice(){
		return this.indice;
	}
	public void setIndice(String indice){
		this.indice = indice;
	}
	public String getServiceEmployeur(){
		return this.serviceEmployeur;
	}
	public void setServiceEmployeur(String serviceEmployeur){
		this.serviceEmployeur = serviceEmployeur;
	}
	public String getLocaliteService(){
		return this.localiteService;
	}
	public void setLocaliteService(String localiteService){
		this.localiteService = localiteService;
	}

	public List<VHistoriqueConge> getHistoriqueConges() {
        return historiqueConges;
    }
    public void setHistoriqueConges(List<VHistoriqueConge> historiqueConges) {
        this.historiqueConges = historiqueConges;
    }

    //CONSTRUCTORS

 	public VUtilisateurDetailler(){}
	public VUtilisateurDetailler(Long numLigne, String idUtilisateur, Integer etatGenre, Integer etatTypeUtilisateur, Integer etatUtilisateur, String idDetailUtilisateur, String idFonction, String idDirection, String idQualite, String idCategorie, String idCorpsAppartenance, String idIndice, String idServiceEmployeur, String idLocaliteService, String nom, String prenom, Date dateNaissance, String genre, String typeUtilisateur, String etat, byte[] image, String matricule, String email, String mdp, String telephone, Date dateEntre, String numeroDecision, String fonction, String direction, Boolean mdpProvisoir, String qualite, String categorie, String corpsAppartenance, String indice, String serviceEmployeur, String localiteService){
		setNumLigne(numLigne);
		setIdUtilisateur(idUtilisateur);
		setEtatGenre(etatGenre);
		setEtatTypeUtilisateur(etatTypeUtilisateur);
		setEtatUtilisateur(etatUtilisateur);
		setIdDetailUtilisateur(idDetailUtilisateur);
		setIdFonction(idFonction);
		setIdDirection(idDirection);
		setIdQualite(idQualite);
		setIdCategorie(idCategorie);
		setIdCorpsAppartenance(idCorpsAppartenance);
		setIdIndice(idIndice);
		setIdServiceEmployeur(idServiceEmployeur);
		setIdLocaliteService(idLocaliteService);
		setNom(nom);
		setPrenom(prenom);
		setDateNaissance(dateNaissance);
		setGenre(genre);
		setTypeUtilisateur(typeUtilisateur);
		setEtat(etat);
		setImage(image);
		setMatricule(matricule);
		setEmail(email);
		setMdp(mdp);
		setTelephone(telephone);
		setDateEntre(dateEntre);
		setNumeroDecision(numeroDecision);
		setFonction(fonction);
		setDirection(direction);
		setMdpProvisoir(mdpProvisoir);
		setQualite(qualite);
		setCategorie(categorie);
		setCorpsAppartenance(corpsAppartenance);
		setIndice(indice);
		setServiceEmployeur(serviceEmployeur);
		setLocaliteService(localiteService);
	}

}
