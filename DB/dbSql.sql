-- MySQL Workbench Forward Engineering
USE `project_show`;

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema project_show
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema project_show
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `project_show` DEFAULT CHARACTER SET utf8 ;
-- -----------------------------------------------------
-- Schema project_show
-- -----------------------------------------------------
USE `project_show` ;

-- -----------------------------------------------------
-- Table `project_show`.`country`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `project_show`.`country` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `question` VARCHAR(100) NULL,
  `answer` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `project_show`.`number`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `project_show`.`number` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `question` VARCHAR(100) NULL,
  `answer` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `project_show`.`exercise`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `project_show`.`exercise` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `question` VARCHAR(100) NULL,
  `answer` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `project_show`.`person`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `project_show`.`person` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `question` VARCHAR(100) NULL,
  `answer` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `project_show`.`etc`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `project_show`.`etc` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `question` VARCHAR(100) NULL,
  `answer` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `project_show`.`users`
-- -----------------------------------------------------
-- DROP table `users`;
-- DROP table `userdata`;
CREATE TABLE IF NOT EXISTS project_show.users (
  userId VARCHAR(45) NOT NULL,
  password VARCHAR(255) NOT NULL,
  userName VARCHAR(45) NOT NULL,
  nickName VARCHAR(45) NOT NULL,
  regDate DATETIME NOT NULL,
  PRIMARY KEY (userId))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;

-- -----------------------------------------------------
-- Table `project_show`.`userdata`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS project_show.userdata (
    id INT NOT NULL AUTO_INCREMENT,
  userId VARCHAR(45) NULL DEFAULT NULL,
  nickName VARCHAR(45) NOT NULL,
  userName VARCHAR(45) NOT NULL,
  score VARCHAR(45) NULL DEFAULT NULL,
  solveDate DATETIME NULL DEFAULT NULL,
  INDEX nickname_idx (userId ASC) VISIBLE,
  PRIMARY KEY (id),
  CONSTRAINT userId
    FOREIGN KEY (userId)
    REFERENCES project_show.users (userId))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

SELECT * FROM esports;
SELECT * FROM kbo;
SELECT * FROM kleague;
SELECT * FROM champs;
SELECT * FROM basic;
SELECT * FROM users;
SELECT * FROM userdata;


ALTER TABLE userdata ADD COLUMN id VARCHAR(45) NOT NULL FIRST;
ALTER TABLE userdata ADD COLUMN userName VARCHAR(45) NOT NULL AFTER nickname;
ALTER TABLE userdata CHANGE COLUMN id userId VARCHAR(45);
ALTER TABLE users CHANGE COLUMN id userId VARCHAR(45);