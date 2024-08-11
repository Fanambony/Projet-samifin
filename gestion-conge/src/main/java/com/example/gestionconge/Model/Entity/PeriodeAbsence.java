package com.example.gestionconge.Model.Entity;

import jakarta.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "periode_absence")
public class PeriodeAbsence {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	String id;
	@Column(name = "valeur")
	String valeur;
	@JsonIgnore
	@OneToMany(mappedBy = "periode_absence", cascade = CascadeType.ALL)
	List<DemandeConge> demandeConges;

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
	public List<DemandeConge> getDemandeConges(){
		return this.demandeConges;
	}
	public void setDemandeConges(List<DemandeConge> demandeConges){
		this.demandeConges = demandeConges;
	}

    //CONSTRUCTORS

 	public PeriodeAbsence(){}
	public PeriodeAbsence(String id, String valeur, List<DemandeConge> demandeConges){
		setId(id);
		setValeur(valeur);
		setDemandeConges(demandeConges);
	}

}
