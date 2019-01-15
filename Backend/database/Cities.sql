CREATE TABLE `Cities` (
  `Id` int(11) NOT NULL,
  `Name` varchar(70) NOT NULL,
  `Country` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `Cities`
  ADD PRIMARY KEY (`Id`);