--
-- Table structure for table `AccessLevels`
--

CREATE TABLE `AccessLevels` (
  `Id` int(11) NOT NULL,
  `InitKey` varchar(30) NOT NULL,
  `InitValue` varchar(30) NOT NULL,
  `FinalKey` varchar(30) NOT NULL,
  `FinalValue` varchar(30) NOT NULL,
  `IsOnline` bit(1) NOT NULL DEFAULT b'1'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for table `AccessLevels`
--
ALTER TABLE `AccessLevels`
  ADD PRIMARY KEY (`Id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `AccessLevels`
--
ALTER TABLE `AccessLevels`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT;
COMMIT;
