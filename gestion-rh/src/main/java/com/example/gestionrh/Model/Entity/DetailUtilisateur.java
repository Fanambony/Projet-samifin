package com.example.gestionrh.Model.Entity;

import jakarta.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "detail_utilisateur")
public class DetailUtilisateur {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	String id;
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
	@Column(name = "id_fonction")
	String idFonction;
	@Column(name = "id_utilisateur")
	String idUtilisateur;
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
	@Column(name = "numero_decision")
	String numeroDecision;
	@Column(name = "mdp_provisoir")
	Boolean mdpProvisoir;
	@ManyToOne
	@JoinColumn(name = "id_fonction", insertable = false, updatable = false)
	Fonction fonction;
	@ManyToOne
	@JoinColumn(name = "id_utilisateur", insertable = false, updatable = false)
	Utilisateur utilisateur;
	@ManyToOne
	@JoinColumn(name = "id_corps_appartenance", insertable = false, updatable = false)
	CorpsAppartenance corps_appartenance;
	@ManyToOne
	@JoinColumn(name = "id_localite_service", insertable = false, updatable = false)
	LocaliteService localite_service;
	@ManyToOne
	@JoinColumn(name = "id_qualite", insertable = false, updatable = false)
	Qualite qualite;
	@ManyToOne
	@JoinColumn(name = "id_service_employeur", insertable = false, updatable = false)
	ServiceEmployeur service_employeur;
	@ManyToOne
	@JoinColumn(name = "id_categorie", insertable = false, updatable = false)
	Categorie categorie;
	@ManyToOne
	@JoinColumn(name = "id_indice", insertable = false, updatable = false)
	Indice indice;

    //SETTERS AND GETTERS

	public String getId(){
		return this.id;
	}
	public void setId(String id){
		this.id = id;
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
	public String getIdFonction(){
		return this.idFonction;
	}
	public void setIdFonction(String idFonction){
		this.idFonction = idFonction;
	}
	public String getIdUtilisateur(){
		return this.idUtilisateur;
	}
	public void setIdUtilisateur(String idUtilisateur){
		this.idUtilisateur = idUtilisateur;
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
	public String getNumeroDecision(){
		return this.numeroDecision;
	}
	public void setNumeroDecision(String numeroDecision){
		this.numeroDecision = numeroDecision;
	}
	public Boolean getMdpProvisoir(){
		return this.mdpProvisoir;
	}
	public void setMdpProvisoir(Boolean mdpProvisoir){
		this.mdpProvisoir = mdpProvisoir;
	}
	public Fonction getFonction(){
		return this.fonction;
	}
	public void setFonction(Fonction fonction){
		this.fonction = fonction;
	}
	public Utilisateur getUtilisateur(){
		return this.utilisateur;
	}
	public void setUtilisateur(Utilisateur utilisateur){
		this.utilisateur = utilisateur;
	}
	public CorpsAppartenance getCorps_appartenance(){
		return this.corps_appartenance;
	}
	public void setCorps_appartenance(CorpsAppartenance corps_appartenance){
		this.corps_appartenance = corps_appartenance;
	}
	public LocaliteService getLocalite_service(){
		return this.localite_service;
	}
	public void setLocalite_service(LocaliteService localite_service){
		this.localite_service = localite_service;
	}
	public Qualite getQualite(){
		return this.qualite;
	}
	public void setQualite(Qualite qualite){
		this.qualite = qualite;
	}
	public ServiceEmployeur getService_employeur(){
		return this.service_employeur;
	}
	public void setService_employeur(ServiceEmployeur service_employeur){
		this.service_employeur = service_employeur;
	}
	public Categorie getCategorie(){
		return this.categorie;
	}
	public void setCategorie(Categorie categorie){
		this.categorie = categorie;
	}
	public Indice getIndice(){
		return this.indice;
	}
	public void setIndice(Indice indice){
		this.indice = indice;
	}

    //CONSTRUCTORS

 	public DetailUtilisateur(){}
	public DetailUtilisateur(String id, String matricule, String email, String mdp, String telephone, Date dateEntre, String idFonction, String idUtilisateur, String idQualite, String idCategorie, String idCorpsAppartenance, String idIndice, String idServiceEmployeur, String idLocaliteService, String numeroDecision, Boolean mdpProvisoir, Fonction fonction, Utilisateur utilisateur, CorpsAppartenance corps_appartenance, LocaliteService localite_service, Qualite qualite, ServiceEmployeur service_employeur, Categorie categorie, Indice indice){
		setId(id);
		setMatricule(matricule);
		setEmail(email);
		setMdp(mdp);
		setTelephone(telephone);
		setDateEntre(dateEntre);
		setIdFonction(idFonction);
		setIdUtilisateur(idUtilisateur);
		setIdQualite(idQualite);
		setIdCategorie(idCategorie);
		setIdCorpsAppartenance(idCorpsAppartenance);
		setIdIndice(idIndice);
		setIdServiceEmployeur(idServiceEmployeur);
		setIdLocaliteService(idLocaliteService);
		setNumeroDecision(numeroDecision);
		setMdpProvisoir(mdpProvisoir);
		setFonction(fonction);
		setUtilisateur(utilisateur);
		setCorps_appartenance(corps_appartenance);
		setLocalite_service(localite_service);
		setQualite(qualite);
		setService_employeur(service_employeur);
		setCategorie(categorie);
		setIndice(indice);
	}
	public DetailUtilisateur(String matricule, String email, String mdp, String telephone, Date dateEntre, String idFonction, String qualite, String categorie, String corpsAppartenance, String indice, String serviceEmployeur, String localiteService, String numeroDecision, Boolean mdpProvisoir){
		setMatricule(matricule);
		setEmail(email);
		setMdp(mdp);
		setTelephone(telephone);
		setDateEntre(dateEntre);
		setIdFonction(idFonction);
		setIdUtilisateur(idUtilisateur);
		setIdQualite(qualite);
		setIdCategorie(categorie);
		setIdCorpsAppartenance(corpsAppartenance);
		setIdIndice(indice);
		setIdServiceEmployeur(serviceEmployeur);
		setIdLocaliteService(localiteService);
		setNumeroDecision(numeroDecision);
		setMdpProvisoir(mdpProvisoir);
	}
}
