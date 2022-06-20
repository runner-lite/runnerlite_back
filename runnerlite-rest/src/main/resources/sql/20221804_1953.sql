CREATE TABLE `runner_lite`.`cytyes_districts` (
  `city_id` INT NOT NULL,
  `district_id` INT NOT NULL,
  PRIMARY KEY (`city_id`, `district_id`),
  INDEX `fk_district_id_idx` (`district_id` ASC) VISIBLE,
  CONSTRAINT `fk_ref_city_id`
    FOREIGN KEY (`city_id`)
    REFERENCES `runner_lite`.`ref_cities` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_district_id`
    FOREIGN KEY (`district_id`)
    REFERENCES `runner_lite`.`ref_districts` (`ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

ALTER TABLE `runner_lite`.`teams_locations`
DROP FOREIGN KEY `FK_TEAMS_LOCATIONS_TEAMS_ID`;
ALTER TABLE `runner_lite`.`teams_locations`
DROP COLUMN `TEAMS_ID`,
DROP INDEX `FK_TEAMS_LOCATIONS_TEAMS_ID_idx`;
;

ALTER TABLE `runner_lite`.`teams_locations`
RENAME TO  `runner_lite`.`locations` ;

ALTER TABLE `runner_lite`.`teams`
ADD COLUMN `LOCATION` INT NULL AFTER `ACTIVE`;
ALTER TABLE `runner_lite`.`teams`
ADD CONSTRAINT `fk_teams_location`
  FOREIGN KEY (`ID`)
  REFERENCES `runner_lite`.`locations` (`ID`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

UPDATE teams SET LOCATION = '1' WHERE (`ID` = '1');
UPDATE teams SET LOCATION = '3' WHERE (`ID` = '2');
UPDATE teams SET LOCATION = '2' WHERE (`ID` = '3');
UPDATE teams SET LOCATION = '4' WHERE (`ID` = '4');
UPDATE teams SET LOCATION = '7' WHERE (`ID` = '6');
UPDATE teams SET LOCATION = '6' WHERE (`ID` = '7');
UPDATE teams SET LOCATION = '5' WHERE (`ID` = '5');