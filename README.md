# 🎵 RevPlay

## 📌 Application Overview
RevPlay is a **console-based music streaming application** that allows users to access a library of songs, artists, playlists, albums, and podcasts. Users can search content, mark favorites, manage playlists, and simulate music playback 🎧. Musicians/artists can create profiles to showcase their music 🎤.  
The application follows a **modular architecture** and is designed to be extended into a **microservices-based web/mobile application** in future phases 🚀.

---

## ⚙️ Core Functional Requirements

### 👤 User Features
- 📝 Register and create an account  
- 🔐 Login to account  
- 🔎 Search songs, artists, playlists, and albums by keywords  
- 🗂️ Browse content by category (genre, artist, album)  
- ⭐ Mark songs as favorites  
- ❤️ View favorite songs  
- ▶️ Simulate music player controls (play, pause, skip, repeat)  
- 📂 Create private or public playlists (name & description)  
- ➕ Add songs to playlists  
- ➖ Remove songs from playlists  
- 📃 View all created playlists  
- ✏️ Update playlist details (name, description, privacy)  
- 🗑️ Delete own playlists  
- 🌍 View public playlists from other users  
- ⏮️ View recently played songs  
- 📊 View listening history  

---

### 🎶 Musician / Artist Features
- 📝 Register as an artist with email, password, and details like bio, genre etc.  
- 🔐 Login to artist account  
- 👤 Manage artist profile (bio, genre, social links)  
- ⬆️ Upload songs with metadata (title, album, genre, duration, release date)  
- 💿 Create albums and add songs  
- 📃 View uploaded songs and albums  
- ✏️ Update song/album information  
- 🗑️ Delete songs and albums  
- 📈 View play count and song statistics  
- ⭐ See users who added songs to favorites
- 🔄 Change password  
- ❓ Recover forgotten password using security questions  

---


## Folder Structure [to be added]
com.revplay
├── dao          # Data Access Objects (DB interactions)
├── model        # POJOs (User, Song, Artist, etc.)
├── service      # Business Logic (Player, Auth, Song Management)
├── ui           # Console Interface (Menus, Input Handling)
└── util         # Untilites (DBConnection, InputValidator)

---

## Application Architecture Diagram

+-----------------------------------------------------------+
|                    PRESENTATION LAYER                     |
|-----------------------------------------------------------|
|  RevPlayApp (Entry Point)                                 |
+-------------------------------+---------------------------+
                                |
                                v
+-----------------------------------------------------------+
|                    SERVICE LAYER                          |
|-----------------------------------------------------------|
|  AuthService                                              |
|  UserService                                              |
|  MusicService                                             |
|  PlaylistService                                          |
|  SearchService                                            |
|  PlayerService                                            |
|  ArtistService                                            |
|  ListeningHistoryService                                  |
+-------------------------------+---------------------------+
                                |
                                v
+-----------------------------------------------------------+
|                      DAO LAYER                            |
|-----------------------------------------------------------|
|  UserDAO                                                  |
|  SongDAO                                                  |
|  AlbumDAO                                                 |
|  ArtistDAO                                                |
|  PlaylistDAO                                              |
|  ListeningHistoryDAO                                      |
+-------------------------------+---------------------------+
                                |
                                v
+-----------------------------------------------------------+
|                      DATABASE                             |
|-----------------------------------------------------------|
|  MySQL                                                    |
+-----------------------------------------------------------+


+-----------------------------------------------------------+
|                      MODEL LAYER                          |
|-----------------------------------------------------------|
|  User                                                     |
|  Artist                                                   |
|  Album                                                    |
|  Song                                                     |
|  ListeningHistory                                         |
|  UserType (Enum)                                          |
+-----------------------------------------------------------+


                 (Cross-Cutting Utility Layer)

+-----------------------------------------------------------+
|                      UTILITY LAYER                        |
|-----------------------------------------------------------|
|  DBConnection                                             |
|  HashPasswordUtil                                         |
|  ValidationUtil                                           |
|  MusicPlayer                                              |
+-----------------------------------------------------------+

---

## Application UML diagram
![](/Docs/ERD_Image.png)


---

## 🛠️ Technologies Used
- ☕ Java  
- 🔌 JDBC  
- 🗄️ MySQL  
- 🌱 Git  
- 🧪 JUnit  
