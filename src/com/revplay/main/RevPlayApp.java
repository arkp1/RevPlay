	package com.revplay.main;
	
	import java.util.Scanner;
	import com.revplay.service.ListeningHistoryService;
	import com.revplay.model.ListeningHistory;
	import java.util.List;

	
import com.revplay.model.UserType;
import com.revplay.service.*;
import com.revplay.util.ValidationUtil;
	
	public class RevPlayApp {
	
	    private static UserType askUserType(Scanner sc) {
	    	while(true) {
	   		 System.out.println("Select type:");
	   	        System.out.println("1. User");
	   	        System.out.println("2. Artist");
	   	        System.out.print("Choice: ");
	   	        
	   	        if(!sc.hasNextInt()) {
	   	        	System.out.println("Invalid Choice!");
	   	        	sc.nextLine();
	   	        	continue;
	   	        }
	   	        
	   	        int choice = sc.nextInt();
	   	        sc.nextLine();
	   	        if (choice == 1) return UserType.USER;
	   	        if (choice == 2) return UserType.ARTIST;
	   	        System.out.println("Invalid Choice!");
	   	    }
	
	    }
	    
	    public static void main(String[] args) {
	
	        Scanner sc = new Scanner(System.in);
	
	        AuthService authService = new AuthService();
	        SearchService searchService = new SearchService();
	        PlayerService playerService = new PlayerService();
	        PlaylistService playlistService = new PlaylistService();
	        ListeningHistoryService listeningHistoryService = new ListeningHistoryService();
	
	        // Artist services
	        MusicService musicService = new MusicService();
	        ArtistAlbumService albumService = new ArtistAlbumService();
	        ArtistProfileService profileService = new ArtistProfileService();
	        ArtistSongService songService = new ArtistSongService();
	
	        boolean isLoggedIn = false;
	        UserType loggedInType = null;
	
	        Integer loggedInUserId = null;
	        Integer loggedInArtistId = null;
	
	        while (true) {
	
	            /* ================= BEFORE LOGIN ================= */
	            if (!isLoggedIn) {
	
	                System.out.println("\n██████╗ ███████╗██╗   ██╗██████╗ ██╗      █████╗ ██╗   ██╗\n"
	                					+"██╔══██╗██╔════╝██║   ██║██╔══██╗██║     ██╔══██╗╚██╗ ██╔╝\n"
	                					+"██████╔╝█████╗  ██║   ██║██████╔╝██║     ███████║ ╚████╔╝\n"
	                					+"██║  ██║███████╗ ╚████╔╝ ██║     ███████╗██║  ██║   ██║\n"
	                					+"╚═╝  ╚═╝╚══════╝  ╚═══╝  ╚═╝     ╚══════╝╚═╝  ╚═╝   ╚═╝\n");
	                System.out.println("1. Register");
	                System.out.println("2. Login");
	                System.out.println("3. Forgot Password");
	                System.out.println("4. Exit");
	                System.out.print("Choose option: ");
	
	                int choice = sc.nextInt();
	                sc.nextLine();
	
	
	                switch (choice) {
	
	                    case 1:
	                        UserType regType = askUserType(sc);
	
	                        System.out.print("Name: ");
	                        String name = sc.nextLine();
	
	                        System.out.print("Email: ");
	                        String email = sc.nextLine();
	                        if(!ValidationUtil.isValidEmail(email)) {
	                        	System.out.println("Invalid Mail format!");
	                        	return;
	                        }
	
	                        System.out.print("Password: ");
	                        String password = sc.nextLine();
	                        
	                        if(!ValidationUtil.isStrongPassword(password)) {
	                        	System.out.println("❌ Password must contain least 8 characters, 1 uppercase letter, 1 lowercase letter, 1 digit, 1 special character!");
	                        	return;
	                        }
	                       	                        
	                        
	                        System.out.println("Choose a security question:");
	                        System.out.println("1. What is your favorite color?");
	                        System.out.println("2. What is your first school name?");
	                        System.out.println("3. What is your pet's name?");
	                       
	                        
	                        int securityChoice = -1;

	                        while (true) {
	                            String input = sc.nextLine().trim();
	                            if (input.matches("[1-3]")) {
	                                securityChoice = Integer.parseInt(input);
	                                break;
	                            }
	                            System.out.print("❌ Invalid choice! Enter 1, 2, or 3 ");
	                        }

	                        String securityQuestion; 
	
	                        switch (securityChoice) {
	                            case 1:
	                                securityQuestion = "What is your favorite color?";
	                                break;
	                            case 2:
	                                securityQuestion = "What is your first school name?";
	                                break;
	                            case 3:
	                                securityQuestion = "What is your pet's name?";
	                                break;
	                            default:
	                                securityQuestion = "What is your favorite color?";
	                        }
	
	                        System.out.print("Enter your security answer: ");
	                        String securityAnswer = sc.nextLine();             
	                        String bio = null;
	                        String genre = null;
	                        String instagramLink = null;
	                        String youtubeLink = null;
	
	                        if (regType == UserType.ARTIST) {
	                            System.out.print("Bio: ");
	                            bio = sc.nextLine();
	                            System.out.print("Genre: ");
	                            genre = sc.nextLine();
	                            //new
	                            System.out.println("Instagram Link:");
	                            instagramLink = sc.nextLine();
	                            System.out.println("Youtube Link:");
	                            youtubeLink= sc.nextLine();
	                            
	                        }
	
	                        authService.register(
	                        	    regType,
	                        	    name,
	                        	    email,
	                        	    password,
	                        	    bio,
	                        	    genre,
	                        	    instagramLink, 
	                        	    youtubeLink,
	                        	    securityQuestion,
	                        	    securityAnswer
	                        	);
	
	                       
	                        	break;
	
	
	                    case 2:
	                        UserType loginType = askUserType(sc);
	
	                        System.out.print("Email: ");
	                        email = sc.nextLine();
	
	                        System.out.print("Password: ");
	                        password = sc.nextLine();
	
	                        Integer id = authService.login(loginType, email, password);
	
	                        if (id != null) {
	                            isLoggedIn = true;
	                            loggedInType = loginType;
	
	                            if (loginType == UserType.USER) {
	                                loggedInUserId = id;
	                            } else {
	                                loggedInArtistId = id;
	                            }
	                        }
	                        break;
	                        
	                    case 3:
	                        handleForgotPassword(sc, authService);
	                        break;
	
	                    case 4:
	                        System.out.println("Thank you for using RevPlay!");
	                        System.exit(0);
	
	                    default:
	                        System.out.println("❌ Invalid choice!");
	                }
	            }
	
	            /* ================= AFTER LOGIN ================= */
	            else {
	
	                /* -------- USER MENU -------- */
	                if (loggedInType == UserType.USER) {
	
	                    System.out.println("\n===== REVPLAY (USER) =====");
	                    System.out.println("1. Search Song by Keyword");
	                    System.out.println("2. Browse Albums by Artist");
	                    System.out.println("3. Play Song");
	                    System.out.println("4. Create Playlist");
	                    System.out.println("5. Manage My Playlists");
	                    System.out.println("6. View My Listening History");
	                    System.out.println("7. Logout");
	                    System.out.print("Choose option: ");

	                    int choice = sc.nextInt();
	                    sc.nextLine();
	
	                    switch (choice) {
	
	                        case 1:
	                            System.out.print("Enter keyword: ");
	                            String keyword = sc.nextLine();
	                            searchService.searchSongByKeyword(keyword);
	                            break;
	
	                        case 2:
	                            System.out.print("Enter artist name: ");
	                            String artistName = sc.nextLine();
	                            searchService.browseAlbumsByArtist(artistName);
	                            break;
	
	                        case 3:
	                            playerService.startPlayer(loggedInUserId, sc);
	                            break;
	
	                        case 4:
	                            playlistService.createPlaylist(loggedInUserId, sc);
	                            break;
	
	                        case 5:
	                            playlistService.managePlaylists(loggedInUserId, sc);
	                            break;
	                        
	                        case 6:
	                            List<ListeningHistory> history =
	                                    listeningHistoryService.viewHistory(loggedInUserId);

	                            if (history.isEmpty()) {
	                                System.out.println("No listening history found.");
	                            } else {
	                                System.out.println("\n===== MY LISTENING HISTORY =====");
	                                System.out.printf("%-5s %-25s %-20s %-20s%n",
	                                        "ID", "Song", "Artist", "Played At");
	                                System.out.println("---------------------------------------------------------------");

	                                for (ListeningHistory h : history) {
	                                    System.out.printf("%-5d %-25s %-20s %-20s%n",
	                                            h.getHistoryId(),
	                                            h.getSongTitle(),
	                                            h.getArtistName(),
	                                            h.getListenedAt()
	                                    );
	                                }
	                            }
	                            break;

	
	                        case 7:
	                            isLoggedIn = false;
	                            loggedInType = null;
	                            loggedInUserId = null;
	                            System.out.println("✅ Logged out successfully!");
	                            break;
	
	                        default:
	                            System.out.println("❌ Invalid choice!");
	                    }
	                }
	
	                /* -------- ARTIST MENU -------- */
	                else {
	
	                    System.out.println("\n ▶ REVPLAY (ARTIST) ◀");
	                    System.out.println("1. Create Album");
	                    System.out.println("2. Upload Song");
	                    System.out.println("3. View My Songs");
	                    System.out.println("4. View My Albums");
	                    System.out.println("5. See / Edit Profile");
	                    System.out.println("6. Logout");
	                    System.out.print("Choose option: ");
	//
	                    int choice = sc.nextInt();
	                    sc.nextLine();
	
	
	                    switch (choice) {
	
	                        case 1:
	                            System.out.print("Album Name: ");
	                            String albumName = sc.nextLine();
	
	                            System.out.print("Release Date (yyyy-mm-dd): ");
	                            String dateStr = sc.nextLine();
	
	                            System.out.print("Genre: ");
	                            String albumGenre = sc.nextLine();
	
	                            albumService.createAlbum(
	                                    loggedInArtistId,
	                                    albumName,
	                                    java.sql.Date.valueOf(dateStr),
	                                    albumGenre
	                            );
	                            break;
	
	                        case 2:
	                            System.out.print("Song Title: ");
	                            String title = sc.nextLine();
	                            
	                            System.out.print("Song Path: ");
	                            String filepath = sc.nextLine();
	                            
	                            System.out.print("Genre: ");
	                            String songGenre = sc.nextLine();
	
	                            System.out.print("Duration (seconds): ");
	                            int duration = sc.nextInt();
	                            sc.nextLine();
	
	                            System.out.print("Release Date (yyyy-mm-dd): ");
	                            String songDate = sc.nextLine();
	
	                            System.out.print("Album ID (0 if none): ");
	                            int albumId = sc.nextInt();
	                            sc.nextLine();
	
	                            musicService.uploadSong(
	                                    loggedInArtistId,
	                                    (albumId == 0) ? null : albumId,
	                                    title,
	                                    songGenre,
	                                    duration,
	                                    java.sql.Date.valueOf(songDate),
	                                    filepath
	                            );
	                            break;
	
	                        case 3:
	                            songService.viewMySongs(loggedInArtistId, sc);
	                            break;
	
	                        case 4:
	                            albumService.viewMyAlbums(loggedInArtistId);
	                            break;
	
	                        case 5:
	                            profileService.viewAndEditProfile(loggedInArtistId, sc);
	                            break;
	
	                        case 6:
	                            isLoggedIn = false;
	                            loggedInType = null;
	                            loggedInArtistId = null;
	                            System.out.println("✅ Logged out successfully!");
	                            break;
	
	                        default:
	                            System.out.println("❌ Invalid choice!");
	                    }
	                }
	            }
	        }
	    }
	
	    private static void handleForgotPassword(Scanner sc, AuthService authService) {
	
	        System.out.println("\n--- Forgot Password ---");
	
	        UserType type = askUserType(sc);
	
	        System.out.print("Enter your registered email: ");
	        String email = sc.nextLine();
	
	        // security question
	        String question = authService.getSecurityQuestion(type, email);
	
	        if (question == null) {
	            System.out.println("❌ Email not found!");
	            return;
	        }
	
	        System.out.println("Security Question:");
	        System.out.println(question);
	
	        System.out.print("Enter your answer: ");
	        String answer = sc.nextLine();
	
	        //verify answer
	        boolean isValid = authService.verifySecurityAnswer(type, email, answer);
	
	        if (!isValid) {
	            System.out.println("❌ Incorrect security answer!");
	            return;
	        }
	
	        System.out.print("Enter new password: ");
	        String newPassword = sc.nextLine();
	    	
	    	if(!ValidationUtil.isStrongPassword(newPassword)) {
	    		System.out.println("❌ Password must contain least 8 characters, 1 uppercase letter, 1 lowercase letter, 1 digit, 1 special character!");
	    		return;
	    	}
	
	        authService.resetPassword(type, email, newPassword);
	        System.out.println("✅ Password reset successful!");
	
	    }
	}