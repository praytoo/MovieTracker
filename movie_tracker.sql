# ----------------------------------------------------------------------- #
# Target DBMS:           MySQL                                            #
# Project name:          movie_tracker                                    #
# ----------------------------------------------------------------------- #
DROP DATABASE IF EXISTS movie_tracker;

CREATE DATABASE IF NOT EXISTS movie_tracker;

USE movie_tracker;

-- Table structure for table `Movies`
-- movie_id, title, genre, avg_percentage_rating, parental_rating, date_released, description

CREATE TABLE Movies (
  movie_id INT AUTO_INCREMENT,
  title VARCHAR(50),
  genre VARCHAR(30),
  avg_percentage_rating INT,
  parental_rating VARCHAR (8),
  date_released DATE,
  description VARCHAR (500),
  PRIMARY KEY (movie_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Table structure for table `Ratings`
-- rating_id, movie_id, user_id, rating_value

CREATE TABLE Ratings (
  rating_id INT AUTO_INCREMENT,
  movie_id INT,
  user_id INT,
  rating_value INT,
  PRIMARY KEY (rating_id),
  KEY idx_fk_movie_id (movie_id),
  KEY idx_fk_user_id (user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Table structure for table `Users`
-- user_id, first_name, last_name, email, date_joined

CREATE TABLE Users (
  user_id INT AUTO_INCREMENT,
  first_name VARCHAR (30),
  last_name VARCHAR (30),
  email VARCHAR(50) UNIQUE,
  date_joined TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Table structure for table `Wish List`
-- id, movie_id, user_id

CREATE TABLE Wish_List_Relation (
  id INT AUTO_INCREMENT,
  movie_id INT,
  user_id INT,
  PRIMARY KEY (id),
  KEY idx_fk_user_id (user_id),
  KEY idx_fk_movie_id (movie_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Table structure for table `Watched List Relation
-- id, movie_id, user_id

CREATE TABLE Watched_List_Relation (
  id INT AUTO_INCREMENT,
  movie_id INT NOT NUll,
  user_id INT Not Null,
  PRIMARY KEY (id),
  KEY idx_fk_user_id (user_id),
  KEY idx_fk_movie_id (movie_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Table structure for table `Actors`
-- first_name, last_name, actor_id

CREATE TABLE Actors (
  actor_id INT AUTO_INCREMENT,
  first_name VARCHAR (30),
  last_name VARCHAR (30),
  PRIMARY KEY (actor_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Table structure for table `Actors_to_Movies_Relation`
-- id, actor_id, movie_id

CREATE TABLE Actors_to_Movies_Relation (
  id INT AUTO_INCREMENT,
  movie_id INT NOT NULL,
  actor_id INT NOT NULL,
  PRIMARY KEY (id),
  KEY idx_fk_movie_id (movie_id),
  KEY idx_fk_actor_id (actor_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

