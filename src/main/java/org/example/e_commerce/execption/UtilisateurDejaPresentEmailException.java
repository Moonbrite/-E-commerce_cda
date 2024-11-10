package org.example.e_commerce.execption;

public class UtilisateurDejaPresentEmailException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public UtilisateurDejaPresentEmailException(String message) {
        super(message);
    }

}