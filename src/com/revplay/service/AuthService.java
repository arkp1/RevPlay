package com.revplay.service;

import com.revplay.dao.UserDAO;
import com.revplay.dao.ArtistDAO;
import com.revplay.model.Artist;
import com.revplay.model.UserType;
import com.revplay.util.HashPasswordUtil;
import com.revplay.util.ValidationUtil;

public class AuthService {

    private UserDAO userDAO = new UserDAO();
    private ArtistDAO artistDAO = new ArtistDAO();

    public void register(UserType type, String name, String email,
                         String password,
                         String bio,
                         String genre,
                         String instagramLink,
                         String youtubeLink,
                         String securityQuestion, 
                         String securityAnswer) {
    	
    	String hashedPassword = HashPasswordUtil.hashPassword(password);
    	email = email.trim().toLowerCase();
    	password = password.trim();
    	
    	if(!ValidationUtil.isValidEmail(email)) {
    		System.out.println("❌ Invalid email format! Only @gmail and @revplay can be used.");
    		return;
    	}
    	
    	if(!ValidationUtil.isStrongPassword(password)) {
    		System.out.println("❌ Password must contain least 8 characters, 1 uppercase letter, 1 lowercase letter, 1 digit, 1 special character!");
    		return;
    	}
    	
        if (type == UserType.USER) {
            userDAO.registerUser(name, email, hashedPassword, securityQuestion, securityAnswer);
            System.out.println("✅ User registered successfully!");
        } else {
            Artist artist = new Artist();
            artist.setArtistName(name);
            artist.setEmail(email);
            artist.setPassword(hashedPassword);
            artist.setBio(bio);	
            artist.setGenre(genre);
            artist.setInstagramLink(instagramLink);
            artist.setYoutubeLink(youtubeLink);
            artist.setSecurityQuestion(securityQuestion);
            artist.setSecurityAnswer(securityAnswer);

            artistDAO.registerArtist(artist);
            System.out.println("✅ Artist registered successfully!");
        }
    }

    public Integer login(UserType type, String email, String password) {
    	String hashedPassword = HashPasswordUtil.hashPassword(password);
    	email = email.trim().toLowerCase();
    	password = password.trim();
    	
        if (type == UserType.USER) {
            return userDAO.loginUser(email, hashedPassword);
        } else {
            return artistDAO.loginArtist(email, hashedPassword);
        }
    }

    public String getSecurityQuestion(UserType type, String email) {
		if (type == UserType.USER) {
			return userDAO.getSecurityQuestionByEmail(email);
		} else {
			return artistDAO.getSecurityQuestionByEmail(email);
		}
	}
	public boolean verifySecurityAnswer(UserType type, String email, String answer) {

		if (type == UserType.USER) {
			return userDAO.verifySecurityAnswer(email, answer);
		} else {
			return artistDAO.verifySecurityAnswer(email, answer);
		}
	}
	public void resetPassword(UserType type, String email, String newPassword) {
		String hashedPassword = HashPasswordUtil.hashPassword(newPassword);
    	if(!ValidationUtil.isValidEmail(email)) {
    		System.out.println("❌ Invalid email format! Only @gmail and @revplay can be used.");
    		return;
    	}
    	
    	if(!ValidationUtil.isStrongPassword(newPassword)) {
    		System.out.println("❌ Password must contain least 8 characters, 1 uppercase letter, 1 lowercase letter, 1 digit, 1 special character!");
    		return;
    	}

		if (type == UserType.USER) {
			userDAO.resetPassword(email, hashedPassword);
		} else {
			artistDAO.resetPassword(email, hashedPassword);
		}
	}
	
}
	
	
	