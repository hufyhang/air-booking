SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

--
-- Database: `airtickets`
--

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `username` varchar(10) NOT NULL,
  `password` varchar(10) NOT NULL,
  `gender` varchar(5) NOT NULL,
  `email` varchar(100),
  `role` varchar(20) NOT NULL,
  PRIMARY KEY  (`username`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`username`, `password`, `gender`, `email`, `role`) VALUES
('cameron', 'cameron', 'male', 'cameron@email.com', 'user'),
('michael', 'michael', 'male', 'michael@email.com', 'manager'),
('dmitry', 'secret', 'female', 'dmitry@email.com', 'administrator');
