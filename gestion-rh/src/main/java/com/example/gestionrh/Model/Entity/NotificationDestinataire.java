package com.example.gestionrh.Model.Entity;

import jakarta.persistence.*;
import java.sql.Timestamp;


@Entity
@Table(name = "notification_destinataire")
public class NotificationDestinataire {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	String id;
	@Column(name = "id_notification")
	String idNotification;
	@Column(name = "destinataire")
	String destinataire;
	@Column(name = "lu")
	Boolean lu;
	@Column(name = "date_lu")
	Timestamp dateLu;
	@Column(name = "afficher")
	Boolean afficher;
	@ManyToOne
	@JoinColumn(name = "id_notification", insertable = false, updatable = false)
	Notification notification;
	@ManyToOne
	@JoinColumn(name = "destinataire", insertable = false, updatable = false)
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
	public String getDestinataire(){
		return this.destinataire;
	}
	public void setDestinataire(String destinataire){
		this.destinataire = destinataire;
	}
	public Boolean getLu(){
		return this.lu;
	}
	public void setLu(Boolean lu){
		this.lu = lu;
	}
	public Timestamp getDateLu(){
		return this.dateLu;
	}
	public void setDateLu(Timestamp dateLu){
		this.dateLu = dateLu;
	}
	public Boolean getAfficher(){
		return this.afficher;
	}
	public void setAfficher(Boolean afficher){
		this.afficher = afficher;
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

 	public NotificationDestinataire(){}
	public NotificationDestinataire(String id, String idNotification, String destinataire, Boolean lu, Timestamp dateLu, Boolean afficher, Notification notification, Utilisateur utilisateur){
		setId(id);
		setIdNotification(idNotification);
		setDestinataire(destinataire);
		setLu(lu);
		setDateLu(dateLu);
		setAfficher(afficher);
		setNotification(notification);
		setUtilisateur(utilisateur);
	}
	public NotificationDestinataire(String idNotification, String destinataire, Boolean lu){
		setIdNotification(idNotification);
		setDestinataire(destinataire);
		setLu(lu);
	}

}
