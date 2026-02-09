package com.revplay.service;

import java.util.List;

import com.revplay.dao.ListeningHistoryDAO;
import com.revplay.model.ListeningHistory;

public class ListeningHistoryService {

    private ListeningHistoryDAO historyDAO = new ListeningHistoryDAO();


    public void recordListening(int userId, int songId) {
        historyDAO.addHistory(userId, songId);
    }

    public List<ListeningHistory> viewHistory(int userId) {
        return historyDAO.getUserHistory(userId);
    }
}
