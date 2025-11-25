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

-- Table structure for table `Ratings`
-- rating_id, movie_id, user_id, rating_value

CREATE TABLE Ratings (
  rating_id INT AUTO_INCREMENT,
  movie_id INT,
  user_id INT,
  rating_value INT,
  PRIMARY KEY (rating_id),
  KEY idx_fk_movie_id (movie_id),
  KEY idx_fk_user_id (user_id),
  CONSTRAINT fk_ratings_movie_id
    FOREIGN KEY (movie_id)
    REFERENCES Movies(movie_id),
  CONSTRAINT fk_ratings_user_id
    FOREIGN KEY (user_id)
    REFERENCES Users(user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Table structure for table `Wish List`
-- id, movie_id, user_id

CREATE TABLE Wish_List_Relation (
  id INT AUTO_INCREMENT,
  movie_id INT,
  user_id INT,
  PRIMARY KEY (id),
  KEY idx_fk_user_id (user_id),
  KEY idx_fk_movie_id (movie_id),
  CONSTRAINT fk_wishlist_movie_id
      FOREIGN KEY (movie_id)
      REFERENCES Movies(movie_id),
  CONSTRAINT fk_wishlist_user_id
      FOREIGN KEY (user_id)
      REFERENCES Users(user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Table structure for table `Watched List Relation
-- id, movie_id, user_id

CREATE TABLE Watched_List_Relation (
  id INT AUTO_INCREMENT,
  movie_id INT NOT NUll,
  user_id INT Not Null,
  PRIMARY KEY (id),
  KEY idx_fk_user_id (user_id),
  KEY idx_fk_movie_id (movie_id),
  CONSTRAINT fk_watched_movie_id
      FOREIGN KEY (movie_id)
      REFERENCES Movies(movie_id),
  CONSTRAINT fk_watched_user_id
      FOREIGN KEY (user_id)
      REFERENCES Users(user_id)
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
  KEY idx_fk_actor_id (actor_id),
  CONSTRAINT fk_actor_movie_id
      FOREIGN KEY (movie_id)
      REFERENCES Movies(movie_id),
  CONSTRAINT fk_movie_actor_id
      FOREIGN KEY (actor_id)
      REFERENCES Actors(actor_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

# INSERTION

INSERT INTO Movies (title, genre, avg_percentage_rating, parental_rating, date_released, description)
VALUES
('The Shawshank Redemption', 'Drama', 93, 'R', '1994-09-22', 'Two imprisoned men bond over a number of years.'),
('The Godfather', 'Crime', 92, 'R', '1972-03-24', 'The aging patriarch of an organized crime dynasty transfers control to his reluctant son.'),
('Inception', 'Sci-Fi', 88, 'PG-13', '2010-07-16', 'A thief who steals corporate secrets through dream-sharing technology.'),
('Toy Story', 'Animation', 90, 'G', '1995-11-22', 'A cowboy doll is profoundly threatened when a new spaceman figure supplants him as top toy.'),
('Avengers: Endgame', 'Action', 85, 'PG-13', '2019-04-26', 'After the devastating events of Infinity War, the Avengers assemble again.'),
('Pulp Fiction', 'Crime', 89, 'R', '1994-10-14', 'The lives of two mob hitmen, a boxer, and others intertwine in four tales of violence and redemption.'),
('The Dark Knight', 'Action', 94, 'PG-13', '2008-07-18', 'Batman sets out to dismantle organized crime in Gotham.'),
('Forrest Gump', 'Drama', 91, 'PG-13', '1994-07-06', 'The presidencies of Kennedy and Johnson, the Vietnam War, and more through the eyes of Forrest Gump.'),
('Gladiator', 'Action', 87, 'R', '2000-05-05', 'A former Roman General sets out to exact vengeance against the corrupt emperor.'),
('Titanic', 'Romance', 89, 'PG-13', '1997-12-19', 'A seventeen-year-old aristocrat falls in love with a kind but poor artist aboard the Titanic.'),
('Jurassic Park', 'Adventure', 86, 'PG-13', '1993-06-11', 'During a preview tour, a theme park suffers a major power breakdown that allows its cloned dinosaurs to run amok.'),
('The Matrix', 'Sci-Fi', 88, 'R', '1999-03-31', 'A computer hacker learns about the true nature of his reality and his role in the war against its controllers.'),
('Star Wars: A New Hope', 'Sci-Fi', 92, 'PG', '1977-05-25', 'Luke Skywalker joins forces to save the galaxy from the evil Empire.'),
('The Lion King', 'Animation', 90, 'G', '1994-06-24', 'A young lion prince flees his kingdom only to learn the true meaning of responsibility and bravery.'),
('Spider-Man: No Way Home', 'Action', 87, 'PG-13', '1994-06-24', 'Peter Parker seeks Doctor Strange help to make the world forget his identity as Spider-Man.');


INSERT INTO Users (first_name, last_name, email)
VALUES
('Alice', 'Smith', 'alice@example.com'),
('Bob', 'Johnson', 'bob@example.com'),
('Charlie', 'Brown', 'charlie@example.com'),
('Diana', 'Prince', 'diana@example.com'),
('Ethan', 'Hunt', 'ethan@example.com'),
('Fiona', 'Gallagher', 'fiona@example.com'),
('George', 'Miller', 'george@example.com'),
('Hannah', 'Montana', 'hannah@example.com'),
('Ian', 'Fleming', 'ian@example.com'),
('Julia', 'Roberts', 'julia@example.com'),
('Kevin', 'Hart', 'kevin@example.com'),
('Laura', 'Croft', 'laura@example.com'),
('Michael', 'Jordan', 'michael@example.com'),
('Natalie', 'Portman', 'natalie@example.com'),
('Oscar', 'Isaac', 'oscar@example.com');


INSERT INTO Ratings (movie_id, user_id, rating_value)
VALUES
(1, 1, 95),
(1, 2, 90),
(2, 3, 92),
(3, 4, 88),
(4, 5, 89),
(5, 1, 85),
(5, 2, 87),
(6, 6, 90),
(7, 7, 94),
(8, 8, 91),
(9, 9, 87),
(10, 10, 89),
(11, 11, 86),
(12, 12, 88),
(13, 13, 92);


INSERT INTO Wish_List_Relation (movie_id, user_id)
VALUES
(3, 1),
(4, 2),
(5, 3),
(1, 4),
(2, 5),
(6, 6),
(7, 7),
(8, 8),
(9, 9),
(10, 10),
(11, 11),
(12, 12),
(13, 13),
(14, 14),
(15, 15);


INSERT INTO Watched_List_Relation (movie_id, user_id)
VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6),
(7, 7),
(8, 8),
(9, 9),
(10, 10),
(11, 11),
(12, 12),
(13, 13),
(14, 14),
(15, 15);


INSERT INTO Actors (first_name, last_name)
VALUES
('Morgan', 'Freeman'),
('Marlon', 'Brando'),
('Leonardo', 'DiCaprio'),
('Tom', 'Hanks'),
('Robert', 'Downey Jr.'),
('Samuel', 'Jackson'),
('Christian', 'Bale'),
('Jennifer', 'Lawrence'),
('Brad', 'Pitt'),
('Emma', 'Watson'),
('Harrison', 'Ford'),
('Elijah', 'Wood'),
('Scarlett', 'Johansson'),
('Mark', 'Ruffalo'),
('Gal', 'Gadot');


INSERT INTO Actors_to_Movies_Relation (movie_id, actor_id)
VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6),
(7, 7),
(8, 8),
(9, 9),
(10, 10),
(11, 11),
(12, 12),
(13, 13),
(14, 14),
(15, 15);








