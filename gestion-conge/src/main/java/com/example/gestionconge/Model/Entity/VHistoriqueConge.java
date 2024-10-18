package com.example.gestionconge.Model.Entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "v_historique_conge")
public class VHistoriqueConge {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "num_ligne")
	Long numLigne;
	@Column(name = "id_utilisateur")
	String idUtilisateur;
	@Column(name = "nom_utilisateur")
	String nomUtilisateur;
	@Column(name = "prenom_utilisateur")
	String prenomUtilisateur;
	@Column(name = "id_type_conge")
	String idTypeConge;
	@Column(name = "type_conge")
	String typeConge;
	@Column(name = "solde_disponible")
	Double soldeDisponible;
	@Column(name = "nombre_jours_pris")
	BigDecimal nombreJoursPris;
	@Column(name = "solde_restant")
	Double soldeRestant;

    //SETTERS AND GETTERS

	public Long getNumLigne(){
		return this.numLigne;
	}
	public void setNumLigne(Long numLigne){
		this.numLigne = numLigne;
	}
	public String getIdUtilisateur(){
		return this.idUtilisateur;
	}
	public void setIdUtilisateur(String idUtilisateur){
		this.idUtilisateur = idUtilisateur;
	}
	public String getNomUtilisateur(){
		return this.nomUtilisateur;
	}
	public void setNomUtilisateur(String nomUtilisateur){
		this.nomUtilisateur = nomUtilisateur;
	}
	public String getPrenomUtilisateur(){
		return this.prenomUtilisateur;
	}
	public void setPrenomUtilisateur(String prenomUtilisateur){
		this.prenomUtilisateur = prenomUtilisateur;
	}
	public String getIdTypeConge(){
		return this.idTypeConge;
	}
	public void setIdTypeConge(String idTypeConge){
		this.idTypeConge = idTypeConge;
	}
	public String getTypeConge(){
		return this.typeConge;
	}
	public void setTypeConge(String typeConge){
		this.typeConge = typeConge;
	}
	public Double getSoldeDisponible(){
		return this.soldeDisponible;
	}
	public void setSoldeDisponible(Double soldeDisponible){
		this.soldeDisponible = soldeDisponible;
	}
	public BigDecimal getNombreJoursPris(){
		return this.nombreJoursPris;
	}
	public void setNombreJoursPris(BigDecimal nombreJoursPris){
		this.nombreJoursPris = nombreJoursPris;
	}
	public Double getSoldeRestant(){
		return this.soldeRestant;
	}
	public void setSoldeRestant(Double soldeRestant){
		this.soldeRestant = soldeRestant;
	}

    //CONSTRUCTORS

 	public VHistoriqueConge(){}
	public VHistoriqueConge(Long numLigne, String idUtilisateur, String nomUtilisateur, String prenomUtilisateur, String idTypeConge, String typeConge, Double soldeDisponible, BigDecimal nombreJoursPris, Double soldeRestant){
		setNumLigne(numLigne);
		setIdUtilisateur(idUtilisateur);
		setNomUtilisateur(nomUtilisateur);
		setPrenomUtilisateur(prenomUtilisateur);
		setIdTypeConge(idTypeConge);
		setTypeConge(typeConge);
		setSoldeDisponible(soldeDisponible);
		setNombreJoursPris(nombreJoursPris);
		setSoldeRestant(soldeRestant);
	}

}
