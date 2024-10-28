package com.example.gestionconge.Model.Entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "notification_lue")
public class NotificationLue {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	String id;
	@Column(name = "id_notification")
	String idNotification;
	@Column(name = "id_utilisateur")
	String idUtilisateur;
	@Column(name = "supprimer")
	Boolean supprimer;
	@ManyToOne
	@JoinColumn(name = "id_notification", insertable = false, updatable = false)
	Notification notification;
	@ManyToOne
	@JoinColumn(name = "id_utilisateur", insertable = false, updatable = false)
	Utilisateur utilisateur;

    //SETTERS AND GETTERS

	public String getId(){
		return this.id;
	}
	public void setId(String id){
		this.id = id;
	}
	public String getIdNotification(){
		return this.idNotification;
	}
	public void setIdNotification(String idNotification){
		this.idNotification = idNotification;
	}
	public String getIdUtilisateur(){
		return this.idUtilisateur;
	}
	public void setIdUtilisateur(String idUtilisateur){
		this.idUtilisateur = idUtilisateur;
	}
	public Boolean getSupprimer(){
		return this.supprimer;
	}
	public void setSupprimer(Boolean supprimer){
		this.supprimer = supprimer;
	}
	public Notification getNotification(){
		return this.notification;
	}
	public void setNotification(Notification notification){
		this.notification = notification;
	}
	public Utilisateur getUtilisateur(){
		return this.utilisateur;
	}
	public void setUtilisateur(Utilisateur utilisateur){
		this.utilisateur = utilisateur;
	}

    //CONSTRUCTORS

 	public NotificationLue(){}
	public NotificationLue(String id, String idNotification, String idUtilisateur, Boolean supprimer, Notification notification, Utilisateur utilisateur){
		setId(id);
		setIdNotification(idNotification);
		setIdUtilisateur(idUtilisateur);
		setSupprimer(supprimer);
		setNotification(notification);
		setUtilisateur(utilisateur);
	}

}
