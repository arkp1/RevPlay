package com.revplay.service;

import java.util.List;
import java.util.Scanner;
import com.revplay.dao.PlaylistDAO;

public class PlaylistService {

    private PlaylistDAO dao = new PlaylistDAO();

    public void createPlaylist(int userId, Scanner sc) {

        System.out.print("Playlist name: ");
        String name = sc.nextLine();

        System.out.print("Description: ");
        String desc = sc.nextLine();

        System.out.print("Public? (yes/no): ");
        boolean isPublic = sc.nextLine().equalsIgnoreCase("yes");

        dao.createPlaylist(userId, name, desc, isPublic);
    }

    public void managePlaylists(int userId, Scanner sc) {

        List<String> playlists = dao.getUserPlaylists(userId);

        if (playlists.isEmpty()) {
            System.out.println("❌ No playlists found.");
            return;
        }

        System.out.println("\n🎶 Your Playlists:");
        playlists.forEach(System.out::println);

        System.out.print("Enter playlist ID: ");
        int pid = sc.nextInt();
        sc.nextLine();
        
        dao.viewSongsInPlaylist(pid);
        System.out.println("1. Add Song");
        System.out.println("2. Remove Song");
        System.out.println("3. Back");
        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {
        case 1:
            System.out.print("Enter Song ID: ");
            int songId = sc.nextInt();
            dao.addSongToPlaylist(pid, songId);
            break;
        case 2:
            System.out.print("Enter Song ID: ");
            int removeSongId = sc.nextInt();
            dao.removeSongFromPlaylist(pid, removeSongId);
            break;
        case 3:
        	return;
        }
    }
}
