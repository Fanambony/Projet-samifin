package com.example.gestionrh.Context;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.gestionrh.Model.Entity.NotificationDestinataire;

public interface NotificationDestinataireRepository extends JpaRepository<NotificationDestinataire, Object> {
    int countByDestinataireAndLuFalse(String destinataire);
    
    // @Query("SELECT nd FROM NotificationDestinataire nd JOIN nd.notification n WHERE nd.destinataire = :userId AND nd.lu = false ORDER BY n.dateCreation DESC")
    
    @Query("SELECT nd FROM NotificationDestinataire nd JOIN nd.notification n WHERE nd.destinataire = :userId AND nd.lu = false ORDER BY n.dateCreation DESC")
    List<NotificationDestinataire> findByDestinataireOrderByDateCreationDescAndNonLu(@Param("userId") String userId);

    @Query("SELECT nd FROM NotificationDestinataire nd JOIN nd.notification n WHERE nd.destinataire = :userId ORDER BY n.dateCreation DESC")
    Page<NotificationDestinataire> findByDestinataireOrderByDateCreationDesc(@Param("userId") String userId, Pageable pageable);
}