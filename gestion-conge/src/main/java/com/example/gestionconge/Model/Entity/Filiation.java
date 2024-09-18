package com.example.gestionconge.Model.Entity;

import jakarta.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "filiation")
public class Filiation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	String id;
	@Column(name = "filiation")
	String filiation;
	@JsonIgnore
	@OneToMany(mappedBy = "filiation", cascade = CascadeType.ALL)
	List<Famille> familles;

    //SETTERS AND GETTERS

	public String getId(){
		return this.id;
	}
	public void setId(String id){
		this.id = id;
	}
	public String getFiliation(){
		return this.filiation;
	}
	public void setFiliation(String filiation){
		this.filiation = filiation;
	}
	public List<Famille> getFamilles(){
		return this.familles;
	}
	public void setFamilles(List<Famille> familles){
		this.familles = familles;
	}

    //CONSTRUCTORS

 	public Filiation(){}
	public Filiation(String id, String filiation, List<Famille> familles){
		setId(id);
		setFiliation(filiation);
		setFamilles(familles);
	}

}
