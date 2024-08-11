package com.example.gestionconge.Model.Entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "nombre_conge_annee")
public class NombreCongeAnnee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "nombre")
	Double nombre;

    //SETTERS AND GETTERS

	public Double getNombre(){
		return this.nombre;
	}
	public void setNombre(Double nombre){
		this.nombre = nombre;
	}

    //CONSTRUCTORS

 	public NombreCongeAnnee(){}
	public NombreCongeAnnee(Double nombre){
		setNombre(nombre);
	}

}
