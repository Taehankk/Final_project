-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema project_show
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema project_show
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `project_show` DEFAULT CHARACTER SET utf8mb3 ;
-- -----------------------------------------------------
-- Schema project_showcountry
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema project_showcountry
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `project_show` ;
USE `project_show` ;

-- -----------------------------------------------------
-- Table `project_show`.`country`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `project_show`.`country` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `question` VARCHAR(100) NULL DEFAULT NULL,
  `answer` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 37
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `project_show`.`etc`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `project_show`.`etc` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `question` VARCHAR(100) NULL DEFAULT NULL,
  `answer` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 55
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `project_show`.`exercise`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `project_show`.`exercise` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `question` VARCHAR(100) NULL DEFAULT NULL,
  `answer` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 55
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `project_show`.`number`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `project_show`.`number` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `question` VARCHAR(100) NULL DEFAULT NULL,
  `answer` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 25
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `project_show`.`person`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `project_show`.`person` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `question` VARCHAR(100) NULL DEFAULT NULL,
  `answer` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 14
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `project_show`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `project_show`.`users` (
  `userId` VARCHAR(45) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `userName` VARCHAR(45) NOT NULL,
  `nickName` VARCHAR(45) NOT NULL,
  `regDate` DATETIME NOT NULL,
  PRIMARY KEY (`userId`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `project_show`.`userdata`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `project_show`.`userdata` (
	`id` INT NOT NULL AUTO_INCREMENT,
  `userId` VARCHAR(45) NULL DEFAULT NULL,
  `nickName` VARCHAR(45) NOT NULL,
  `userName` VARCHAR(45) NOT NULL,
  `score` VARCHAR(45) NULL DEFAULT NULL,
  `solveDate` DATETIME NULL DEFAULT NULL,
  INDEX `nickname_idx` (`userId` ASC) VISIBLE,
  PRIMARY KEY (`id`),
  CONSTRAINT `userId`
    FOREIGN KEY (`userId`)
    REFERENCES `project_show`.`users` (`userId`)
    ON DELETE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;
    
-- -----------------------------------------------------
-- Table `project_show`.`arenas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `project_show`.`arenas` (
  `arenaId` INT NOT NULL AUTO_INCREMENT,
  `field` VARCHAR(100) NULL DEFAULT NULL,
  `starter` VARCHAR(45) NULL DEFAULT NULL,
  `interest` INT NOT NULL,
  `openDay` DATETIME NULL DEFAULT NULL,  
  `content` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`arenaId`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;



USE `project_show` ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
