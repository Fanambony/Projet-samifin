package com.example.gestionrh.Model.Service;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class PasswordService {
    // Méthode pour chiffrer un mot de passe
    public String encryptPassword(String plainPassword) {
        // Utilise la méthode hashpw pour chiffrer le mot de passe avec un salt généré automatiquement
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }

    // Méthode pour vérifier si un mot de passe en clair correspond au mot de passe chiffré
    public boolean checkPassword(String plainPassword, String hashedPassword) {
        // Utilise la méthode checkpw pour comparer le mot de passe en clair et celui chiffré
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}
