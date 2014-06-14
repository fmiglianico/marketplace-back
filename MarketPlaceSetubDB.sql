#-----------------------------------
#TABLE CREATION
#-----------------------------------
USE `jee-training`;

CREATE TABLE player (

  `login` VARCHAR(255) NOT NULL ,

  `password` VARCHAR(45) NULL ,
  
  `accountBalance` DOUBLE ,

  PRIMARY KEY (`login`) );

  
CREATE TABLE item (

  `id` INT NOT NULL AUTO_INCREMENT ,

  `name` VARCHAR(45) NULL ,

  `price` DOUBLE ,

  `imgUrl` VARCHAR(255) NULL ,
  
  PRIMARY KEY (`id`) );
  
  
CREATE TABLE armor (

  `item_id` INT NOT NULL ,

  `description` VARCHAR(255) NULL ,
  
  PRIMARY KEY (`item_id`) ,
  
  FOREIGN KEY (`item_id`) REFERENCES item (`id`));
  
  
CREATE TABLE weapon (

  `item_id` INT NOT NULL ,

  `power` INT ,
  
  PRIMARY KEY (`item_id`) ,
  
  FOREIGN KEY (`item_id`) REFERENCES item (`id`));
  
  
CREATE TABLE consumable (

  `item_id` INT NOT NULL ,

  `effect` VARCHAR(255) NULL ,
  
  PRIMARY KEY (`item_id`) ,
  
  FOREIGN KEY (`item_id`) REFERENCES item (`id`));
  
  
CREATE TABLE player_item (

  `item_id` INT NOT NULL ,

  `player_login` VARCHAR(255) NOT NULL ,

  `quantity` INT NULL ,
  
  PRIMARY KEY (`item_id`, `player_login`) ,
  
  FOREIGN KEY (`item_id`) REFERENCES item (`id`) ,
  
  FOREIGN KEY (`player_login`) REFERENCES player (`login`));
  
CREATE TABLE log (

  `id` INT NOT NULL AUTO_INCREMENT,

  `player_login` VARCHAR(255) NULL ,

  `item_id` INT NULL ,

  `item_quantity` INT NULL ,
  
  `action` VARCHAR(50) NULL ,
  
  `player_accountBalance` DOUBLE NULL ,
  
  `date` DATETIME NULL ,
  
  PRIMARY KEY (`id`));

  
