SELECT
`Orders`.Id as Id,
CONCAT(`Factors`.`Submit`, ' ', `Users`.`Firstname`, ' ', `Users`.`Lastname`, ' (', `Users`.`Id`, ')') as Factor,
`Foods`.`Name` as Food,
`Branches`.`Name` as Branch,
`Count` as `Count`,
`PriceScalar`,
`PriceOff`
FROM `Orders`

INNER JOIN `Factors` ON `Orders`.`FactorId` = `Factors`.`Id`
LEFT JOIN `Users` ON `Factors`.`UserId` = `Users`.`Id`
LEFT OUTER JOIN `Foods` ON `Orders`.`FoodId` = `Foods`.`Id`
LEFT OUTER JOIN `Branches` ON `Orders`.`BranchId` = `Branches`.`Id`


WHERE `FactorId`=@FactorId