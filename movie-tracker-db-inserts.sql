ALTER TABLE watched_list

DROP COLUMN individual_star_rating,
DROP COLUMN title;

ALTER TABLE wish_list
DROP COLUMN avg_percentage_rating,
DROP COLUMN parental_rating,
DROP COLUMN description;

ALTER TABLE my_movie_ratings
DROP COLUMN title;

ALTER TABLE ratings
DROP COLUMN date_released,
DROP COLUMN title,
DROP COLUMN parental_rating,
DROP COLUMN avg_percentage_rating;

ALTER TABLE wish_list
DROP COLUMN title;

ALTER TABLE users
DROP COLUMN wishlist_id,
DROP COLUMN watched_id;

INSERT INTO `movie_tracker`.`movies`
(`movie_id`,
`date_released`,
`genre`,
`cast`,
`title`,
`avg_percentage_rating`,
`parental_rating`,
`individual_star_rating`,
`description`,
`wishlist_id`,
`watched_id`)
VALUES
(2,'2023-07-15', 'Comedy', 'Emma Stone, Ryan Gosling', 'Crazy Nights', '85', 12, 9, 'A hilarious journey of two friends on a wild road trip', NULL, NULL),
(3,'2021-11-10', 'Sci-Fi', 'Chris Pratt, Zoe Saldana', 'Galactic Quest', '78', 13, 7, 'A team of explorers ventures into unknown galaxies', NULL, NULL);

INSERT INTO `movie_tracker`.`actors`
(`movie_id`,
`title`,
`first_name`,
`last_name`)
VALUES
(2, "Crazy Nights", "Emma", "Stone"),
(3, "Galactic Quest", "Chris", "Pratt"),
(2, "Crazy Nights", "Ryan", "Gosling");

INSERT INTO `movie_tracker`.`users`
(`first_name`, `last_name`, `email`)
VALUES
('Alice', 'Johnson', 'alice.johnson@example.com'),
('Bob', 'Smith', 'bob.smith@example.com'),
('Charlie', 'Lee', 'charlie.lee@example.com');

-- My Movie Ratings
INSERT INTO `movie_tracker`.`my_movie_ratings`
(`movie_id`, `individual_star_rating`, `user_id`)
VALUES
(2, 9, 1),  -- Alice rated "Crazy Nights"
(3, 8, 2),  -- Bob rated "Galactic Quest"
(2, 7, 3);  -- Charlie rated "Crazy Nights"

-- Watched List
INSERT INTO `movie_tracker`.`watched_list`
(`movie_id`, `watched_id`, `user_id`)
VALUES
(2, 1, 1),  -- Alice watched "Crazy Nights"
(3, 2, 2),  -- Bob watched "Galactic Quest"
(2, 3, 3);  -- Charlie watched "Crazy Nights"

-- Wish List
INSERT INTO `movie_tracker`.`wish_list`
(`movie_id`, `wishlist_id`, `user_id`)
VALUES
(3, 1, 1),  -- Alice wants to watch "Galactic Quest"
(2, 2, 2),  -- Bob wants to watch "Crazy Nights"
(3, 3, 3);  -- Charlie wants to watch "Galactic Quest"

-- Written Reviews / Ratings
INSERT INTO `movie_tracker`.`ratings`
(`movie_id`, `user_id`, `written_reviews`)
VALUES
(2, 1, 'Absolutely loved the humor in this movie!'),
(3, 2, 'Great visuals, but the plot was a bit slow.');









