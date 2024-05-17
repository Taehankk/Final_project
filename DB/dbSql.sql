-- MySQL Workbench Forward Engineering

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
CREATE TABLE IF NOT EXISTS `project_show`.`users` (
  `id` VARCHAR(45) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `nickname` VARCHAR(45) NOT NULL,
  `regDate` DATE NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `project_show`.`userdata`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `project_show`.`userdata` (
  `nickname` VARCHAR(45) NOT NULL,
  `score` VARCHAR(45) NULL,
  `date` DATETIME NULL,
  PRIMARY KEY (`nickname`),
  CONSTRAINT `nickname`
    FOREIGN KEY (`nickname`)
    REFERENCES `project_showcountry`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

SELECT * FROM country;
SELECT * FROM etc;
SELECT * FROM exercise;
SELECT * FROM number;
SELECT * FROM person;
SELECT * FROM users;
SELECT * FROM userdata;