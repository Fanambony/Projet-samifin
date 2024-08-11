package com.example.gestionrh.Model.Entity;

import jakarta.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "etat_demande")
public class EtatDemande {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	String id;
	@Column(name = "valeur")
	String valeur;
	@JsonIgnore
	@OneToMany(mappedBy = "etat_demande", cascade = CascadeType.ALL)
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

 	public EtatDemande(){}
	public EtatDemande(String id, String valeur, List<DemandeConge> demandeConges){
		setId(id);
		setValeur(valeur);
		setDemandeConges(demandeConges);
	}

}
