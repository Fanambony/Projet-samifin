package com.example.gestionrh.Model.Entity;

import jakarta.persistence.*;
import java.sql.Date;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "utilisateur")
public class Utilisateur {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	String id;
	@Column(name = "nom")
	String nom;
	@Column(name = "prenom")
	String prenom;
	@Column(name = "date_naissance")
	Date dateNaissance;
	@Column(name = "genre")
	Integer idGenre;
	@Column(name = "type_utilisateur")
	Integer typeUtilisateur;
	@Column(name = "etat")
	Integer etat;
	@Column(name = "image")
	byte[] image;
	@JsonIgnore
	@OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL)
	List<DetailUtilisateur> detailUtilisateurs;
	@JsonIgnore
	@OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL)
	List<SoldeUtilisateur> soldeUtilisateurs;
	@JsonIgnore
	@OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL)
	List<DemandeConge> demandeConges;
	@JsonIgnore
	@OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL)
	List<Famille> familles;
	@ManyToOne
	@JoinColumn(name = "type_utilisateur", insertable = false, updatable = false)
	TypeUtilisateur type_utilisateur;
	@ManyToOne
	@JoinColumn(name = "etat", insertable = false, updatable = false)
	EtatUtilisateur etat_utilisateur;
	@ManyToOne
	@JoinColumn(name = "genre", insertable = false, updatable = false)
	Genre genre;

	@Transient
	List<VHistoriqueConge> historiqueConges;

    //SETTERS AND GETTERS

	public String getId(){
		return this.id;
	}
	public void setId(String id){
		this.id = id;
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
	public Integer getIdGenre(){
		return this.idGenre;
	}
	public void setIdGenre(Integer idGenre){
		this.idGenre = idGenre;
	}
	public Integer getTypeUtilisateur(){
		return this.typeUtilisateur;
	}
	public void setTypeUtilisateur(Integer typeUtilisateur){
		this.typeUtilisateur = typeUtilisateur;
	}
	public Integer getEtat(){
		return this.etat;
	}
	public void setEtat(Integer etat){
		this.etat = etat;
	}
	public byte[] getImage(){
		return this.image;
	}
	public void setImage(byte[] image){
		this.image = image;
	}
	public List<DetailUtilisateur> getDetailUtilisateurs(){
		return this.detailUtilisateurs;
	}
	public void setDetailUtilisateurs(List<DetailUtilisateur> detailUtilisateurs){
		this.detailUtilisateurs = detailUtilisateurs;
	}
	public List<SoldeUtilisateur> getSoldeUtilisateurs(){
		return this.soldeUtilisateurs;
	}
	public void setSoldeUtilisateurs(List<SoldeUtilisateur> soldeUtilisateurs){
		this.soldeUtilisateurs = soldeUtilisateurs;
	}
	public List<DemandeConge> getDemandeConges(){
		return this.demandeConges;
	}
	public void setDemandeConges(List<DemandeConge> demandeConges){
		this.demandeConges = demandeConges;
	}
	public List<Famille> getFamilles(){
		return this.familles;
	}
	public void setFamilles(List<Famille> familles){
		this.familles = familles;
	}
	public TypeUtilisateur getType_utilisateur(){
		return this.type_utilisateur;
	}
	public void setType_utilisateur(TypeUtilisateur type_utilisateur){
		this.type_utilisateur = type_utilisateur;
	}
	public EtatUtilisateur getEtat_utilisateur(){
		return this.etat_utilisateur;
	}
	public void setEtat_utilisateur(EtatUtilisateur etat_utilisateur){
		this.etat_utilisateur = etat_utilisateur;
	}
	public Genre getGenre(){
		return this.genre;
	}
	public void setGenre(Genre genre){
		this.genre = genre;
	}

    public List<VHistoriqueConge> getHistoriqueConges() {
        return historiqueConges;
    }
    public void setHistoriqueConges(List<VHistoriqueConge> historiqueConges) {
        this.historiqueConges = historiqueConges;
    }

    //CONSTRUCTORS

 	public Utilisateur(){}
	public Utilisateur(String id, String nom, String prenom, Date dateNaissance, Integer idGenre, Integer typeUtilisateur, Integer etat, byte[] image, List<DetailUtilisateur> detailUtilisateurs, List<SoldeUtilisateur> soldeUtilisateurs, List<DemandeConge> demandeConges, List<Famille> familles, TypeUtilisateur type_utilisateur, EtatUtilisateur etat_utilisateur, Genre genre){
		setId(id);
		setNom(nom);
		setPrenom(prenom);
		setDateNaissance(dateNaissance);
		setIdGenre(idGenre);
		setTypeUtilisateur(typeUtilisateur);
		setEtat(etat);
		setImage(image);
		setDetailUtilisateurs(detailUtilisateurs);
		setSoldeUtilisateurs(soldeUtilisateurs);
		setFamilles(familles);
		setDemandeConges(demandeConges);
		setType_utilisateur(type_utilisateur);
		setEtat_utilisateur(etat_utilisateur);
		setGenre(genre);
	}
	public Utilisateur(String nom, String prenom, Date dateNaissance, Integer idGenre, Integer typeUtilisateur, Integer etat){
		setNom(nom);
		setPrenom(prenom);
		setDateNaissance(dateNaissance);
		setIdGenre(idGenre);
		setTypeUtilisateur(typeUtilisateur);
		setEtat(etat);
	}
}