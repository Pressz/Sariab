CREATE TABLE `Users` (
  `Id` int(11) NOT NULL,
  `Username` varchar(13) NOT NULL,
  `Firstname` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `Lastname` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `NationalCode` varchar(45) DEFAULT NULL,
  `BinImage` longblob,
  `Type` varchar(10) DEFAULT 'USER',
  `IsActive` bit(1) DEFAULT b'0',
  `HashPassword` varchar(400) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `Users`
  ADD PRIMARY KEY (`Id`),
  ADD UNIQUE KEY `Username` (`Username`);


ALTER TABLE `Users`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT;
COMMIT;

ALTER TABLE `Users` ADD `Register` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP AFTER `HashPassword`;

ALTER TABLE `Users` CHANGE `Register` `Register` DATETIME NULL DEFAULT CURRENT_TIMESTAMP;

ALTER TABLE `Users`
ADD `CityId` INT NOT NULL AFTER `Register`,
ADD `Age` TINYINT NOT NULL AFTER `CityId`,
ADD `Sex` VARCHAR(10) NOT NULL AFTER `Age`;