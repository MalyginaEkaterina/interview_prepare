CREATE SCHEMA `cinema` ;

CREATE TABLE `cinema`.`movies` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(256) NOT NULL,
  `duration` INT UNSIGNED NOT NULL COMMENT 'длительность фильма в секундах',
  PRIMARY KEY (`id`));

  CREATE TABLE `cinema`.`sessions` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `movie_id` INT UNSIGNED NOT NULL,
  `start_time` DATETIME NOT NULL,
  `price` DOUBLE UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_sessions_movies_movie_id_idx` (`movie_id` ASC) VISIBLE,
  CONSTRAINT `fk_sessions_movies_movie_id`
    FOREIGN KEY (`movie_id`)
    REFERENCES `cinema`.`movies` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT);

ALTER TABLE `cinema`.`sessions` 
ADD INDEX `idx_start_time` (`start_time` ASC) VISIBLE;
;

CREATE TABLE `cinema`.`tickets` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `session_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_tickets_sessions_session_id_idx` (`session_id` ASC) VISIBLE,
  CONSTRAINT `fk_tickets_sessions_session_id`
    FOREIGN KEY (`session_id`)
    REFERENCES `cinema`.`sessions` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT);


INSERT INTO `cinema`.`movies` (`name`, `duration`) VALUES ('movie one', '3600');
INSERT INTO `cinema`.`movies` (`name`, `duration`) VALUES ('movie two', '3600');
INSERT INTO `cinema`.`movies` (`name`, `duration`) VALUES ('movie three', '7200');
INSERT INTO `cinema`.`movies` (`name`, `duration`) VALUES ('movie four', '5400');
INSERT INTO `cinema`.`movies` (`name`, `duration`) VALUES ('movie five', '7200');

INSERT INTO `cinema`.`sessions` (`id`,`movie_id`, `start_time`, `price`) VALUES ('1', '1', '20210723080000', '100');
INSERT INTO `cinema`.`sessions` (`id`,`movie_id`, `start_time`, `price`) VALUES ('2', '1', '20210723100000', '100');
INSERT INTO `cinema`.`sessions` (`id`,`movie_id`, `start_time`, `price`) VALUES ('3', '2', '20210723113000', '200');
INSERT INTO `cinema`.`sessions` (`id`,`movie_id`, `start_time`, `price`) VALUES ('4', '3', '20210723122500', '300');
INSERT INTO `cinema`.`sessions` (`id`,`movie_id`, `start_time`, `price`) VALUES ('5', '4', '20210723140000', '400');
INSERT INTO `cinema`.`sessions` (`id`,`movie_id`, `start_time`, `price`) VALUES ('6', '1', '20210723154500', '150');
INSERT INTO `cinema`.`sessions` (`id`,`movie_id`, `start_time`, `price`) VALUES ('7', '2', '20210723163000', '255');
INSERT INTO `cinema`.`sessions` (`id`,`movie_id`, `start_time`, `price`) VALUES ('8', '3', '20210723173500', '333');
INSERT INTO `cinema`.`sessions` (`id`,`movie_id`, `start_time`, `price`) VALUES ('9', '4', '20210723192500', '440');
INSERT INTO `cinema`.`sessions` (`id`,`movie_id`, `start_time`, `price`) VALUES ('10', '5', '20210723214500', '550');

INSERT INTO `cinema`.`tickets` (`session_id`) VALUES ('2');
INSERT INTO `cinema`.`tickets` (`session_id`) VALUES ('2');
INSERT INTO `cinema`.`tickets` (`session_id`) VALUES ('2');
INSERT INTO `cinema`.`tickets` (`session_id`) VALUES ('3');
INSERT INTO `cinema`.`tickets` (`session_id`) VALUES ('3');
INSERT INTO `cinema`.`tickets` (`session_id`) VALUES ('4');
INSERT INTO `cinema`.`tickets` (`session_id`) VALUES ('4');
INSERT INTO `cinema`.`tickets` (`session_id`) VALUES ('4');
INSERT INTO `cinema`.`tickets` (`session_id`) VALUES ('4');
INSERT INTO `cinema`.`tickets` (`session_id`) VALUES ('5');
INSERT INTO `cinema`.`tickets` (`session_id`) VALUES ('6');
INSERT INTO `cinema`.`tickets` (`session_id`) VALUES ('6');
INSERT INTO `cinema`.`tickets` (`session_id`) VALUES ('6');
INSERT INTO `cinema`.`tickets` (`session_id`) VALUES ('7');
INSERT INTO `cinema`.`tickets` (`session_id`) VALUES ('8');
INSERT INTO `cinema`.`tickets` (`session_id`) VALUES ('8');
INSERT INTO `cinema`.`tickets` (`session_id`) VALUES ('9');
INSERT INTO `cinema`.`tickets` (`session_id`) VALUES ('9');
INSERT INTO `cinema`.`tickets` (`session_id`) VALUES ('9');
INSERT INTO `cinema`.`tickets` (`session_id`) VALUES ('9');
INSERT INTO `cinema`.`tickets` (`session_id`) VALUES ('9');
