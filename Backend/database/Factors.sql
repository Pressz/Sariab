CREATE TABLE `Factors` (
  `Id` int(11) NOT NULL,
  `Submit` datetime NOT NULL,
  `UserId` int(11) NOT NULL,
  `Price` double NOT NULL,
  `OffPrice` double NOT NULL,
  `BranchId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `Factors`
  ADD PRIMARY KEY (`Id`);

ALTER TABLE `Factors`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT;
COMMIT;

ALTER TABLE `Factors` ADD `Status` VARCHAR(50) NOT NULL AFTER `BranchId`;

ALTER TABLE `Factors` CHANGE `CouponId` `CouponCode` VARCHAR(20) NOT NULL;