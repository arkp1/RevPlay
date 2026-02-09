package com.revplay.dao;

import java.sql.*;
import java.util.*;
import com.revplay.util.DBConnection;

public class PlaylistDAO {

    public void createPlaylist(int userId, String name, String desc, boolean isPublic) {

        String sql = "INSERT INTO playlists (user_id, name, description, is_public) VALUES (?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ps.setString(2, name);
            ps.setString(3, desc);
            ps.setBoolean(4, isPublic);

            ps.executeUpdate();
            System.out.println("✅ Playlist created!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<String> getUserPlaylists(int userId) {

        List<String> list = new ArrayList<>();
        String sql = "SELECT playlist_id, name FROM playlists WHERE user_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(rs.getInt("playlist_id") + " - " + rs.getString("name"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void addSongToPlaylist(int playlistId, int songId) {

        String sql = "INSERT INTO playlist_songs (playlist_id, song_id) VALUES (?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, playlistId);
            ps.setInt(2, songId);
            ps.executeUpdate();

            System.out.println("🎵 Song added to playlist!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeSongFromPlaylist(int playlistId, int songId) {

        String sql = "DELETE FROM playlist_songs WHERE playlist_id=? AND song_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, playlistId);
            ps.setInt(2, songId);
            ps.executeUpdate();

            System.out.println("🗑 Song removed!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void viewSongsInPlaylist(int playlistId) {
        String sql = """
                SELECT s.song_id, s.title
                FROM playlist_songs ps
                JOIN songs s ON ps.song_id = s.song_id
                WHERE ps.playlist_id = ?
            """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, playlistId);
            ResultSet rs = ps.executeQuery();
            
            boolean songsFound = false;
            
            System.out.println("Songs in this playlist:"
            		+ "");
            
            while (rs.next()) {
            	songsFound = true;
            	System.out.println(rs.getInt("song_id") + " - " 
            			+ rs.getString("title"));
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
