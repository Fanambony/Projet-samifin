package com.example.gestionrh.Context;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.gestionrh.Model.Entity.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Object> {

}