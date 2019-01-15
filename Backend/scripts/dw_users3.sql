select Id, CONCAT(Firstname, " ", Lastname) as Fullname,
Username, `Register` FROM `Users`
WHERE `Register` >= '@start' AND `Register` < '@end' and `Type`='USER';