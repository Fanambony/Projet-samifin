package com.example.gestionconge.Model.Entity;

import jakarta.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "type_utilisateur")
public class TypeUtilisateur {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	String id;
	@Column(name = "valeur")
	String valeur;
	@Column(name = "etat")
	Integer etat;
	@JsonIgnore
	@OneToMany(mappedBy = "type_utilisateur", cascade = CascadeType.ALL)
	List<Utilisateur> utilisateurs;

    //SETTERS AND GETTERS

	public String getId(){
		return this.id;
	}
	public void setId(String id){
		this.id = id;
	}
	public String getValeur(){
		return this.valeur;
	}
	public void setValeur(String valeur){
		this.valeur = valeur;
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

    //CONSTRUCTORS

 	public TypeUtilisateur(){}
	public TypeUtilisateur(String id, String valeur, Integer etat, List<Utilisateur> utilisateurs){
		setId(id);
		setValeur(valeur);
		setEtat(etat);
		setUtilisateurs(utilisateurs);
	}

}
