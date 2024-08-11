package com.example.gestionconge.Model.Entity;

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
	@Column(name = "etat")
	String etat;
	@JsonIgnore
	@OneToMany(mappedBy = "fonction", cascade = CascadeType.ALL)
	List<DetailUtilisateur> detailUtilisateurs;
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
	public String getEtat(){
		return this.etat;
	}
	public void setEtat(String etat){
		this.etat = etat;
	}
	public List<DetailUtilisateur> getDetailUtilisateurs(){
		return this.detailUtilisateurs;
	}
	public void setDetailUtilisateurs(List<DetailUtilisateur> detailUtilisateurs){
		this.detailUtilisateurs = detailUtilisateurs;
	}
	public Direction getDirection(){
		return this.direction;
	}
	public void setDirection(Direction direction){
		this.direction = direction;
	}

    //CONSTRUCTORS

 	public Fonction(){}
	public Fonction(String id, String nom, String idDirection, String etat, List<DetailUtilisateur> detailUtilisateurs, Direction direction){
		setId(id);
		setNom(nom);
		setIdDirection(idDirection);
		setEtat(etat);
		setDetailUtilisateurs(detailUtilisateurs);
		setDirection(direction);
	}

}
