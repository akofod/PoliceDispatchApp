SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema upd_dispatch
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `upd_dispatch` ;
CREATE SCHEMA IF NOT EXISTS `upd_dispatch` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `upd_dispatch` ;

-- -----------------------------------------------------
-- Table `upd_dispatch`.`contact_type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `upd_dispatch`.`contact_type` ;

CREATE TABLE IF NOT EXISTS `upd_dispatch`.`contact_type` (
  `contact_type_id` INT NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(45) NULL,
  PRIMARY KEY (`contact_type_id`),
  UNIQUE INDEX `contact_type_id_UNIQUE` (`contact_type_id` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `upd_dispatch`.`shift`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `upd_dispatch`.`shift` ;

CREATE TABLE IF NOT EXISTS `upd_dispatch`.`shift` (
  `shift_id` INT NOT NULL AUTO_INCREMENT,
  `shift` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`shift_id`),
  UNIQUE INDEX `shift_id_UNIQUE` (`shift_id` ASC),
  UNIQUE INDEX `shift_UNIQUE` (`shift` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `upd_dispatch`.`complaint`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `upd_dispatch`.`complaint` ;

CREATE TABLE IF NOT EXISTS `upd_dispatch`.`complaint` (
  `complaint_id` INT NOT NULL,
  `complaint` VARCHAR(45) NOT NULL,
  `code` VARCHAR(5) NOT NULL,
  `number_occurences` INT NOT NULL,
  PRIMARY KEY (`complaint_id`),
  UNIQUE INDEX `complaint_id_UNIQUE` (`complaint_id` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `upd_dispatch`.`person`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `upd_dispatch`.`person` ;

CREATE TABLE IF NOT EXISTS `upd_dispatch`.`person` (
  `person_id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NULL,
  `last_name` VARCHAR(45) NULL,
  `phone_number` VARCHAR(12) NULL,
  PRIMARY KEY (`person_id`),
  UNIQUE INDEX `person_id_UNIQUE` (`person_id` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `upd_dispatch`.`location`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `upd_dispatch`.`location` ;

CREATE TABLE IF NOT EXISTS `upd_dispatch`.`location` (
  `location_id` INT NOT NULL AUTO_INCREMENT,
  `address` VARCHAR(45) NOT NULL,
  `number_calls` INT NOT NULL,
  PRIMARY KEY (`location_id`),
  UNIQUE INDEX `location_id_UNIQUE` (`location_id` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `upd_dispatch`.`dispatcher`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `upd_dispatch`.`dispatcher` ;

CREATE TABLE `upd_dispatch`.`dispatcher` (
  `unit_number` varchar(3) NOT NULL,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `phone_number` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `password` varchar(50) NOT NULL,
  `salt` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`unit_number`),
  UNIQUE KEY `unit_number_UNIQUE` (`unit_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



-- -----------------------------------------------------
-- Table `upd_dispatch`.`officer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `upd_dispatch`.`officer` ;

CREATE TABLE IF NOT EXISTS `upd_dispatch`.`officer` (
  `badge_number` VARCHAR(2) NOT NULL,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `phone_number` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`badge_number`),
  UNIQUE INDEX `badge_number_UNIQUE` (`badge_number` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `upd_dispatch`.`result`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `upd_dispatch`.`result` ;

CREATE TABLE IF NOT EXISTS `upd_dispatch`.`result` (
  `result_id` INT NOT NULL AUTO_INCREMENT,
  `result` VARCHAR(45) NOT NULL,
  `number_occurences` INT NOT NULL,
  PRIMARY KEY (`result_id`),
  UNIQUE INDEX `result_id_UNIQUE` (`result_id` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `upd_dispatch`.`call_record`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `upd_dispatch`.`call_record` ;

CREATE TABLE IF NOT EXISTS `upd_dispatch`.`call_record` (
  `call_record_id` INT NOT NULL AUTO_INCREMENT,
  `manner_received` INT NULL,
  `call_date` DATE NOT NULL,
  `call_shift` INT NOT NULL,
  `time_received` TIME NOT NULL,
  `time_dispatched` TIME NULL,
  `time_arrived` TIME NULL,
  `time_cleared` TIME NULL,
  `complaint_type` INT NOT NULL,
  `caller` INT NULL,
  `location` INT NULL,
  `dispatcher` VARCHAR(3) NOT NULL,
  `officer` VARCHAR(2) NOT NULL,
  `result` INT NULL,
  `incident_number` INT NULL,
  `notes_index` INT NOT NULL,
  PRIMARY KEY (`call_record_id`),
  UNIQUE INDEX `call_record_id_UNIQUE` (`call_record_id` ASC),
  INDEX `manner_received_idx` (`manner_received` ASC),
  INDEX `shift_idx` (`call_shift` ASC),
  INDEX `complaint_type_idx` (`complaint_type` ASC),
  INDEX `caller_idx` (`caller` ASC),
  INDEX `location_idx` (`location` ASC),
  INDEX `dispatcher_idx` (`dispatcher` ASC),
  INDEX `officer_idx` (`officer` ASC),
  INDEX `result_idx` (`result` ASC),
  CONSTRAINT `manner_received`
    FOREIGN KEY (`manner_received`)
    REFERENCES `upd_dispatch`.`contact_type` (`contact_type_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `shift`
    FOREIGN KEY (`call_shift`)
    REFERENCES `upd_dispatch`.`shift` (`shift_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `complaint_type`
    FOREIGN KEY (`complaint_type`)
    REFERENCES `upd_dispatch`.`complaint` (`complaint_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `caller`
    FOREIGN KEY (`caller`)
    REFERENCES `upd_dispatch`.`person` (`person_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `location`
    FOREIGN KEY (`location`)
    REFERENCES `upd_dispatch`.`location` (`location_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `dispatcher`
    FOREIGN KEY (`dispatcher`)
    REFERENCES `upd_dispatch`.`dispatcher` (`unit_number`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `officer`
    FOREIGN KEY (`officer`)
    REFERENCES `upd_dispatch`.`officer` (`badge_number`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `result`
    FOREIGN KEY (`result`)
    REFERENCES `upd_dispatch`.`result` (`result_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `upd_dispatch`.`vehicle`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `upd_dispatch`.`vehicle` ;

CREATE TABLE IF NOT EXISTS `upd_dispatch`.`vehicle` (
  `vehicle_id` INT NOT NULL AUTO_INCREMENT,
  `license_number` VARCHAR(45) NULL,
  `owner` INT NULL,
  `year` VARCHAR(45) NULL,
  `make` VARCHAR(45) NULL,
  `model` VARCHAR(45) NULL,
  `color` VARCHAR(45) NULL,
  PRIMARY KEY (`vehicle_id`),
  UNIQUE INDEX `vehicle_id_UNIQUE` (`vehicle_id` ASC),
  INDEX `owner_idx` (`owner` ASC),
  CONSTRAINT `owner`
    FOREIGN KEY (`owner`)
    REFERENCES `upd_dispatch`.`person` (`person_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `upd_dispatch`.`traffic_stop`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `upd_dispatch`.`traffic_stop` ;

CREATE TABLE IF NOT EXISTS `upd_dispatch`.`traffic_stop` (
  `traffic_stop_id` INT NOT NULL AUTO_INCREMENT,
  `vehicle` INT NULL,
  `operator` INT NULL,
  `call_record` INT NOT NULL,
  PRIMARY KEY (`traffic_stop_id`),
  UNIQUE INDEX `traffic_stop_id_UNIQUE` (`traffic_stop_id` ASC),
  INDEX `operator_idx` (`operator` ASC),
  INDEX `vehicle_idx` (`vehicle` ASC),
  CONSTRAINT `operator`
    FOREIGN KEY (`operator`)
    REFERENCES `upd_dispatch`.`person` (`person_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `vehicle`
    FOREIGN KEY (`vehicle`)
    REFERENCES `upd_dispatch`.`vehicle` (`vehicle_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `upd_dispatch`.`notes`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `upd_dispatch`.`notes` ;

CREATE TABLE IF NOT EXISTS `upd_dispatch`.`notes` (
  `note_id` INT NOT NULL AUTO_INCREMENT,
  `call_record` INT NOT NULL,
  `note` VARCHAR(500) NOT NULL,
  PRIMARY KEY (`note_id`),
  UNIQUE INDEX `note_id_UNIQUE` (`note_id` ASC),
  INDEX `call_record_idx` (`call_record` ASC),
  CONSTRAINT `call_record`
    FOREIGN KEY (`call_record`)
    REFERENCES `upd_dispatch`.`call_record` (`call_record_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

SET SQL_MODE = '';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
