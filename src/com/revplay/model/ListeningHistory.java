package com.revplay.model;

import java.sql.Timestamp;

public class ListeningHistory {

    private int historyId;
    private int userId;
    private int songId;
    private String songTitle;
    private String artistName;
    private Timestamp listenedAt;

    public ListeningHistory(int historyId, int userId, int songId,
                            String songTitle, String artistName, Timestamp listenedAt) {
        this.historyId = historyId;
        this.userId = userId;
        this.songId = songId;
        this.songTitle = songTitle;
        this.artistName = artistName;
        this.listenedAt = listenedAt;
    }

    public int getHistoryId() {
        return historyId;
    }

    public int getUserId() {
        return userId;
    }

    public int getSongId() {
        return songId;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public String getArtistName() {
        return artistName;
    }

    public Timestamp getListenedAt() {
        return listenedAt;
    }
}
