package com.revplay.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.revplay.model.ListeningHistory;
import com.revplay.util.DBConnection;

import java.sql.Connection;

public class ListeningHistoryDAO {

    // INSERT WHEN USER PLAYS A SONG
    public void addHistory(int userId, int songId) {
        String sql = "INSERT INTO listening_history (user_id, song_id) VALUES (?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ps.setInt(2, songId);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // FETCH LISTENING HISTORY FOR USER
    public List<ListeningHistory> getUserHistory(int userId) {

        List<ListeningHistory> history = new ArrayList<>();

        String sql = """
            SELECT lh.history_id, lh.user_id, lh.song_id,
                   s.title, a.artist_name, lh.listened_at
            FROM listening_history lh
            JOIN songs s ON lh.song_id = s.song_id
            JOIN artists a ON s.artist_id = a.artist_id
            WHERE lh.user_id = ?
            ORDER BY lh.listened_at DESC
        """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                history.add(new ListeningHistory(
                        rs.getInt("history_id"),
                        rs.getInt("user_id"),
                        rs.getInt("song_id"),
                        rs.getString("title"),
                        rs.getString("artist_name"),
                        rs.getTimestamp("listened_at")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return history;
    }
}
