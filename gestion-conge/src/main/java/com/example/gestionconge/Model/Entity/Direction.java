package com.example.gestionconge.Model.Entity;

import jakarta.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "direction")
public class Direction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	String id;
	@Column(name = "nom")
	String nom;
	@JsonIgnore
	@OneToMany(mappedBy = "direction", cascade = CascadeType.ALL)
	List<Fonction> fonctions;

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
	public List<Fonction> getFonctions(){
		return this.fonctions;
	}
	public void setFonctions(List<Fonction> fonctions){
		this.fonctions = fonctions;
	}

    //CONSTRUCTORS

 	public Direction(){}
	public Direction(String id, String nom, List<Fonction> fonctions){
		setId(id);
		setNom(nom);
		setFonctions(fonctions);
	}

}
