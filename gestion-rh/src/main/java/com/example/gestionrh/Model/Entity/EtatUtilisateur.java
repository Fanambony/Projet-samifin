package com.example.gestionrh.Model.Entity;

import jakarta.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "etat_utilisateur")
public class EtatUtilisateur {

	@Column(name = "libelle")
	String libelle;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "etat")
	Integer etat;
	@JsonIgnore
	@OneToMany(mappedBy = "etat_utilisateur", cascade = CascadeType.ALL)
	List<Utilisateur> utilisateurs;

	public String getLibelle(){
		return this.libelle;
	}
	public void setLibelle(String libelle){
		this.libelle = libelle;
	}
	public Integer getEtat(){
		return this.etat;
	}
	public void setEtat(Integer etat){
		this.etat = etat;
	}
	public List<Utilisateur> getUtilisateurs(){
		return this.utilisateurs;
	}
	public void setUtilisateurs(List<Utilisateur> utilisateurs){
		this.utilisateurs = utilisateurs;
	}

 	public EtatUtilisateur(){}
	public EtatUtilisateur(String libelle, Integer etat, List<Utilisateur> utilisateurs){
		setLibelle(libelle);
		setEtat(etat);
		setUtilisateurs(utilisateurs);
	}
}