package com.example.gestionconge.Context;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.gestionconge.Model.Entity.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Object> {

}