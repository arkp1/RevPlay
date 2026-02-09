package com.revplay.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.revplay.model.Artist;
import com.revplay.util.DBConnection;

public class ArtistDAO {

    /* ===== REGISTER ARTIST ===== */
    public boolean registerArtist(Artist artist) {

        String sql = "INSERT INTO artists (artist_name, email, password, bio, genre, instagram_link, youtube_link, security_question, security_answer) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, artist.getArtistName());
            ps.setString(2, artist.getEmail());
            ps.setString(3, artist.getPassword());
            ps.setString(4, artist.getBio());
            ps.setString(5, artist.getGenre());
            ps.setString(6, artist.getInstagramLink());
            ps.setString(7, artist.getYoutubeLink());
            ps.setString(8, artist.getSecurityQuestion());
            ps.setString(9, artist.getSecurityAnswer());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /* ===== LOGIN ARTIST ===== */
    public Integer loginArtist(String email, String password) {

        String sql = "SELECT artist_id FROM artists WHERE email=? AND password=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("artist_id");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    
    
    public boolean resetArtistPassword(String newPassword, String email) {
    	String sql = "UPDATE artists SET password=? WHERE email=?";
    	
    	try {
    		Connection con = DBConnection.getConnection();
    		PreparedStatement ps = con.prepareStatement(sql);
    		
    		ps.setString(1, newPassword);
    		ps.setString(2, email);
    		
    		int rows = ps.executeUpdate();
    		return rows > 0;
    	}
    	
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	return false;
                 
    }	

    /* ===== FETCH PROFILE ===== */
    public Artist getArtistById(int artistId) {

        String sql = "SELECT * FROM artists WHERE artist_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, artistId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Artist artist = new Artist();
                artist.setArtistId(rs.getInt("artist_id"));
                artist.setArtistName(rs.getString("artist_name"));
                artist.setEmail(rs.getString("email"));
                artist.setBio(rs.getString("bio"));
                artist.setGenre(rs.getString("genre"));
                artist.setInstagramLink(rs.getString("instagram_link"));
                artist.setYoutubeLink(rs.getString("youtube_link"));
                return artist;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /* ===== UPDATE PROFILE ===== */
    public void updateArtistProfile(int id, String bio, String genre, String insta, String yt) {

        String sql = "UPDATE artists SET bio=?, genre=?, instagram_link=?, youtube_link=? WHERE artist_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, bio);
            ps.setString(2, genre);
            ps.setString(3, insta);
            ps.setString(4, yt);
            ps.setInt(5, id);

            ps.executeUpdate();
            System.out.println("✅ Profile updated!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    public String getSecurityQuestionByEmail(String email) {
		// TODO Auto-generated method stub

		String sql = "SELECT security_question FROM artists WHERE email = ?";

		try (Connection con = DBConnection.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return rs.getString("security_question");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public boolean verifySecurityAnswer(String email, String answer) {
	    String sql = "SELECT 1 FROM artists WHERE email = ? AND security_answer = ?";

	    try (Connection con = DBConnection.getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {

	        ps.setString(1, email);
	        ps.setString(2, answer);

	        ResultSet rs = ps.executeQuery();
	        return rs.next();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return false;
	}

	public void resetPassword(String email, String newPassword) {
	    String sql = "UPDATE artists SET password = ? WHERE email = ?";

	    try (Connection con = DBConnection.getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {

	        ps.setString(1, newPassword);
	        ps.setString(2, email);
	        ps.executeUpdate();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		
	}
}
