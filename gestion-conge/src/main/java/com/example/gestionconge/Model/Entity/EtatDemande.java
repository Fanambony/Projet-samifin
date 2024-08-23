package com.example.gestionconge.Model.Entity;

import jakarta.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "etat_demande")
public class EtatDemande {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "libelle")
	String libelle;
	@Column(name = "etat")
	Integer etat;
	@JsonIgnore
	@OneToMany(mappedBy = "etat_demande", cascade = CascadeType.ALL)
	List<DemandeConge> demandeConges;

    //SETTERS AND GETTERS

	public String getLibelle(){
		return this.libelle;
	}
	public void setLibelle(String libelle){
		this.libelle = libelle;
	}
	public Integer getEtat(){
		return this.etat;
	}
	public void setEtat(Integer etat){
		this.etat = etat;
	}
	public List<DemandeConge> getDemandeConges(){
		return this.demandeConges;
	}
	public void setDemandeConges(List<DemandeConge> demandeConges){
		this.demandeConges = demandeConges;
	}

    //CONSTRUCTORS

 	public EtatDemande(){}
	public EtatDemande(String libelle, Integer etat, List<DemandeConge> demandeConges){
		setLibelle(libelle);
		setEtat(etat);
		setDemandeConges(demandeConges);
	}

}
