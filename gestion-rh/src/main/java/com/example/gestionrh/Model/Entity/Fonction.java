package com.example.gestionrh.Model.Entity;

import jakarta.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "fonction")
public class Fonction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	String id;
	@Column(name = "nom")
	String nom;
	@Column(name = "id_direction")
	String idDirection;
	@Column(name = "type")
	Integer type;
	@Column(name = "etat")
	Integer etat;
	@JsonIgnore
	@OneToMany(mappedBy = "fonction", cascade = CascadeType.ALL)
	List<Utilisateur> utilisateurs;
	@ManyToOne
	@JoinColumn(name = "id_direction", insertable = false, updatable = false)
	Direction direction;

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
	public String getIdDirection(){
		return this.idDirection;
	}
	public void setIdDirection(String idDirection){
		this.idDirection = idDirection;
	}
	public Integer getType(){
		return this.type;
	}
	public void setType(Integer type){
		this.type = type;
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
	public Direction getDirection(){
		return this.direction;
	}
	public void setDirection(Direction direction){
		this.direction = direction;
	}

    //CONSTRUCTORS

 	public Fonction(){}
	public Fonction(String id, String nom, String idDirection, Integer type, Integer etat, List<Utilisateur> utilisateurs, Direction direction){
		setId(id);
		setNom(nom);
		setIdDirection(idDirection);
		setType(type);
		setEtat(etat);
		setUtilisateurs(utilisateurs);
		setDirection(direction);
	}

}
