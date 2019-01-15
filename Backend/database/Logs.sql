--
-- Table structure for table `Logs`
--

CREATE TABLE `Logs` (
  `Id` int(11) NOT NULL,
  `Content` varchar(3000) DEFAULT NULL,
  `UserId` int(11) DEFAULT NULL,
  `Agent` varchar(10) DEFAULT 'DATABASE',
  `Connection` varchar(1000) DEFAULT NULL,
  `Event` datetime DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Logs`
--
ALTER TABLE `Logs`
  ADD PRIMARY KEY (`Id`);


ALTER TABLE `Logs`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT;
COMMIT;
