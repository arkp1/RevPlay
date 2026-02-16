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

## 📁 Folder Structure

```text
RevPlay
├── src
│   └── com.revplay
│       ├── main
│       ├── model
│       ├── dao
│       ├── service
│       └── util
├── lib
└── bin
```



---

## 🏗 Application Architecture

```text
+---------------------------------------------------+
|                PRESENTATION LAYER                 |
|---------------------------------------------------|
|  RevPlayApp (CLI Entry Point)                     |
+--------------------------+------------------------+
                           |
                           v
+---------------------------------------------------+
|                  SERVICE LAYER                    |
|---------------------------------------------------|
|  AuthService                                      |
|  UserService                                      |
|  MusicService                                     |
|  PlaylistService                                  |
|  SearchService                                    |
|  PlayerService                                    |
|  ArtistService                                    |
|  ListeningHistoryService                          |
+--------------------------+------------------------+
                           |
                           v
+---------------------------------------------------+
|                    DAO LAYER                      |
|---------------------------------------------------|
|  UserDAO                                          |
|  ArtistDAO                                        |
|  AlbumDAO                                         |
|  SongDAO                                          |
|  PlaylistDAO                                      |
|  ListeningHistoryDAO                              |
+--------------------------+------------------------+
                           |
                           v
+---------------------------------------------------+
|                     DATABASE                      |
|---------------------------------------------------|
|  MySQL                                            |
+---------------------------------------------------+

                (Shared Across Layers)

+---------------------------------------------------+
|                   MODEL LAYER                     |
|---------------------------------------------------|
|  User, Artist, Album, Song, ListeningHistory      |
+---------------------------------------------------+

+---------------------------------------------------+
|                  UTILITY LAYER                    |
|---------------------------------------------------|
|  DBConnection, HashPasswordUtil,                  |
|  ValidationUtil, MusicPlayer                      |
+---------------------------------------------------+
```


---

## Application UML diagram
![](/Docs/ERD_Image.png)


---
## ⚙️ Project Setup

### 1. Prerequisites

- Java 17+
- MySQL 8+
- MySQL Connector J
- IntelliJ / Eclipse / VS Code

---

### 2. Clone Repository

```bash
git clone https://github.com/arkp1/RevPlay.git
cd RevPlay
```

---

### 3. Create Database

```sql
CREATE DATABASE revplay;
USE revplay;
```

Create required tables for:

- User
- Artist
- Album
- Song
- Playlist
- ListeningHistory

---

### 4. Configure Database Connection

Open:

```
src/com/revplay/util/DBConnection.java
```

Update credentials:

```java
private static final String URL = "jdbc:mysql://localhost:3306/revplay";
private static final String USER = "your_username";
private static final String PASSWORD = "your_password";
```

---

### 5. Add MySQL Connector

Place the MySQL connector JAR inside:

```
lib/
```

Add it to your project build path.

---

### 6. Compile & Run

From IDE:

Run:

```
RevPlayApp.java
```

OR from terminal (Windows):

```bash
javac -cp "lib/*" -d bin src/com/revplay/**/*.java
java -cp "bin;lib/*" com.revplay.main.RevPlayApp
```

(macOS/Linux use `:` instead of `;`)

---

## 🚀 Application Flow

1. Start application  
2. Register / Login as Artist or User
3. Browse or Search Songs  
4. Play Music  
5. Manage Playlists  
6. Track Listening History  
---

## 🛠️ Technologies Used
- ☕ Java  
- 🔌 JDBC  
- 🗄️ MySQL  
- 🌱 Git  
- 🧪 JUnit  
