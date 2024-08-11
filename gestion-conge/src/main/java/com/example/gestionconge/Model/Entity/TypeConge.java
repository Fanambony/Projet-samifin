package com.example.gestionconge.Model.Entity;

import jakarta.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "type_conge")
public class TypeConge {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	String id;
	@Column(name = "nom")
	String nom;
	@Column(name = "etat")
	String etat;
	@JsonIgnore
	@OneToMany(mappedBy = "type_conge", cascade = CascadeType.ALL)
	List<DemandeConge> demandeConges;

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
	public String getEtat(){
		return this.etat;
	}
	public void setEtat(String etat){
		this.etat = etat;
	}
	public List<DemandeConge> getDemandeConges(){
		return this.demandeConges;
	}
	public void setDemandeConges(List<DemandeConge> demandeConges){
		this.demandeConges = demandeConges;
	}

    //CONSTRUCTORS

 	public TypeConge(){}
	public TypeConge(String id, String nom, String etat, List<DemandeConge> demandeConges){
		setId(id);
		setNom(nom);
		setEtat(etat);
		setDemandeConges(demandeConges);
	}

}
