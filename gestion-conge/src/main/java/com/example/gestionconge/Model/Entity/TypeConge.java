package com.example.gestionconge.Model.Entity;

import jakarta.persistence.*;
import java.util.List;
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
	@Column(name = "nombre_annuel")
	Double nombreAnnuel;
	@JsonIgnore
	@OneToMany(mappedBy = "type_conge", cascade = CascadeType.ALL)
	List<DemandeConge> demandeConges;
	@JsonIgnore
	@OneToMany(mappedBy = "type_conge", cascade = CascadeType.ALL)
	List<SoldeConge> soldeConges;

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
	public Double getNombreAnnuel(){
		return this.nombreAnnuel;
	}
	public void setNombreAnnuel(Double nombreAnnuel){
		this.nombreAnnuel = nombreAnnuel;
	}
	public List<DemandeConge> getDemandeConges(){
		return this.demandeConges;
	}
	public void setDemandeConges(List<DemandeConge> demandeConges){
		this.demandeConges = demandeConges;
	}
	public List<SoldeConge> getSoldeConges(){
		return this.soldeConges;
	}
	public void setSoldeConges(List<SoldeConge> soldeConges){
		this.soldeConges = soldeConges;
	}

    //CONSTRUCTORS

 	public TypeConge(){}
	public TypeConge(String id, String nom, Double nombreAnnuel, List<DemandeConge> demandeConges, List<SoldeConge> soldeConges){
		setId(id);
		setNom(nom);
		setNombreAnnuel(nombreAnnuel);
		setDemandeConges(demandeConges);
		setSoldeConges(soldeConges);
	}

}
