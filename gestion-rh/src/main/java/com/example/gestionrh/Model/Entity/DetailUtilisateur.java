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

 	public DetailUtilisateur(){}
	 public DetailUtilisateur(String id, String matricule, String email, String mdp, String telephone, Date dateEntre, String idFonction, String idUtilisateur, String qualite, String categorie, String corpsAppartenance, String indice, String serviceEmployeur, String localiteService, String numeroDecision, Boolean mdpProvisoir, Fonction fonction, Utilisateur utilisateur){
		setId(id);
		setMatricule(matricule);
		setEmail(email);
		setMdp(mdp);
		setTelephone(telephone);
		setDateEntre(dateEntre);
		setIdFonction(idFonction);
		setIdUtilisateur(idUtilisateur);
		setQualite(qualite);
		setCategorie(categorie);
		setCorpsAppartenance(corpsAppartenance);
		setIndice(indice);
		setServiceEmployeur(serviceEmployeur);
		setLocaliteService(localiteService);
		setNumeroDecision(numeroDecision);
		setMdpProvisoir(mdpProvisoir);
		setFonction(fonction);
		setUtilisateur(utilisateur);
	}
}