package com.example.gestionconge.Model.Entity;

import jakarta.persistence.*;
import java.sql.Date;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "jour_ferie")
public class JourFerie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	String id;
	@Column(name = "date")
	Date date;
	@Column(name = "description")
	String description;

    //SETTERS AND GETTERS

	public String getId(){
		return this.id;
	}
	public void setId(String id){
		this.id = id;
	}
	public Date getDate(){
		return this.date;
	}
	public void setDate(Date date){
		this.date = date;
	}
	public String getDescription(){
		return this.description;
	}
	public void setDescription(String description){
		this.description = description;
	}

    //CONSTRUCTORS

 	public JourFerie(){}
	public JourFerie(String id, Date date, String description){
		setId(id);
		setDate(date);
		setDescription(description);
	}

}
