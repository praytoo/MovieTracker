# ----------------------------------------------------------------------- #
# Target DBMS:           MySQL                                            #
# Project name:          movie_tracker                                    #
# ----------------------------------------------------------------------- #
DROP DATABASE IF EXISTS movie_tracker;

CREATE DATABASE IF NOT EXISTS movie_tracker;

USE movie_tracker;

-- Table structure for table `Movies`
-- date_released, genre, cast, title, movie_id, avg_percentage_rating, parental_rating,
-- individual_star_rating, description, wishlist_id, watched_id

CREATE TABLE Movies (
  movie_id INT AUTO_INCREMENT,
  date_released DATE,
  genre VARCHAR(12),
  cast VARCHAR(500),
  title VARCHAR(50),
  avg_percentage_rating INT,
  parental_rating VARCHAR (8),
  individual_star_rating INT,
  description VARCHAR (500),
  wishlist_id INT,
  watched_id INT,
  PRIMARY KEY (movie_id),
  KEY idx_fk_wishlist_id (wishlist_id),
  KEY idx_fk_watched_id (watched_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Table structure for table `Ratings`
-- avg_percentage_rating, movie_id, parental_rating, written_reviews, user_id, 
-- title, date_released

CREATE TABLE Ratings (
  movie_id INT,
  date_released DATE,
  title VARCHAR(50),
  avg_percentage_rating INT,
  parental_rating VARCHAR (8),
  user_id INT,
  written_reviews VARCHAR (500),
  PRIMARY KEY (movie_id),
  KEY idx_fk_movie_id (movie_id),
  KEY idx_fk_user_id (user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Table structure for table `Users`
-- individual_star_rating, user_id, date_joined, title, wishlist_id, watched_id, first_name,
-- last_name, email

CREATE TABLE Users (
  movie_id INT,
  individual_star_rating INT,
  date_joined TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  title VARCHAR(50),
  wishlist_id INT,
  watched_id INT,
  user_id INT AUTO_INCREMENT,
  first_name VARCHAR (12),
  last_name VARCHAR (12),
  email VARCHAR(50),
  PRIMARY KEY (user_id),
  KEY idx_fk_wishlist_id (wishlist_id),
  KEY idx_fk_watched_id (watched_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Table structure for table `Wish List`
-- movie_id, user_id, wishlist_id, title, description, avg_percentage_rating, parental_rating

CREATE TABLE Wish_List (
  movie_id INT,
  title VARCHAR(50),
  avg_percentage_rating INT,
  parental_rating VARCHAR (8),
  description VARCHAR (500),
  wishlist_id INT AUTO_INCREMENT,
  user_id INT,
  PRIMARY KEY (wishlist_id),
  KEY idx_fk_user_id (user_id),
  KEY idx_fk_movie_id (movie_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Table structure for table `Watched List`
-- movie_id, user_id, watched_id, title, individual_star_rating

CREATE TABLE Watched_List (
  movie_id INT,
  title VARCHAR(50),
  individual_star_rating INT,
  watched_id INT AUTO_INCREMENT,
  user_id INT,
  PRIMARY KEY (watched_id),
  KEY idx_fk_user_id (user_id),
  KEY idx_fk_movie_id (movie_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Table structure for table `My Movie Ratings`
-- movie_id, individual_star_rating, user_id, title

CREATE TABLE My_Movie_Ratings (
  movie_id INT,
  title VARCHAR(50),
  individual_star_rating INT,
  user_id INT,
  PRIMARY KEY (individual_star_rating),
  KEY idx_fk_user_id (user_id),
  KEY idx_fk_movie_id (movie_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Table structure for table `Actors`
-- first_name, last_name, actor_id, movie_id, title

CREATE TABLE Actors (
  movie_id INT,
  title VARCHAR(50),
  actor_id INT AUTO_INCREMENT,
  first_name VARCHAR (12),
  last_name VARCHAR (12),
  PRIMARY KEY (actor_id),
  KEY idx_fk_movie_id (movie_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
