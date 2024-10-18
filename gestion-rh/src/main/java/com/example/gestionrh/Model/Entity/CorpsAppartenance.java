package com.example.gestionrh.Model.Entity;

import jakarta.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "corps_appartenance")
public class CorpsAppartenance {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	String id;
	@Column(name = "libelle")
	String libelle;
	@JsonIgnore
	@OneToMany(mappedBy = "corps_appartenance", cascade = CascadeType.ALL)
	List<DetailUtilisateur> detailUtilisateurs;

    //SETTERS AND GETTERS

	public String getId(){
		return this.id;
	}
	public void setId(String id){
		this.id = id;
	}
	public String getLibelle(){
		return this.libelle;
	}
	public void setLibelle(String libelle){
		this.libelle = libelle;
	}
	public List<DetailUtilisateur> getDetailUtilisateurs(){
		return this.detailUtilisateurs;
	}
	public void setDetailUtilisateurs(List<DetailUtilisateur> detailUtilisateurs){
		this.detailUtilisateurs = detailUtilisateurs;
	}

    //CONSTRUCTORS

 	public CorpsAppartenance(){}
	public CorpsAppartenance(String id, String libelle, List<DetailUtilisateur> detailUtilisateurs){
		setId(id);
		setLibelle(libelle);
		setDetailUtilisateurs(detailUtilisateurs);
	}

}
