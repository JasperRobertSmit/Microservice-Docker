-- phpMyAdmin SQL Dump
-- version 4.4.15.5
-- http://www.phpmyadmin.net
--
-- Host: localhost:8889
-- Generation Time: Jul 02, 2016 at 11:15 PM
-- Server version: 5.5.49-log
-- PHP Version: 7.0.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `newchatdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `chatroom`
--

CREATE TABLE IF NOT EXISTS `chatroom` (
  `crid` int(255) NOT NULL,
  `uid` int(255) NOT NULL,
  `roomnumber` int(255) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `chatroom`
--

INSERT INTO `chatroom` (`crid`, `uid`, `roomnumber`) VALUES
(1, 1, 0),
(2, 2, 0),
(3, 3, 2),
(4, 2, 2),
(5, 4, 3),
(6, 3, 3),
(7, 2, 991),
(8, 6, 3),
(9, 2, 3),
(10, 1, 2),
(11, 1, 991);

-- --------------------------------------------------------

--
-- Table structure for table `messages`
--

CREATE TABLE IF NOT EXISTS `messages` (
  `uid` int(11) NOT NULL,
  `message` varchar(255) NOT NULL,
  `roomnumber` int(255) NOT NULL,
  `dateadded` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `messages`
--

INSERT INTO `messages` (`uid`, `message`, `roomnumber`, `dateadded`) VALUES
(2, 'Message 1', 2, '2016-07-02 14:58:51'),
(2, 'Message 2', 2, '2016-07-02 14:58:51'),
(2, 'Message 3', 2, '2016-07-02 14:59:17'),
(1, 'Message 4', 2, '2016-07-02 14:59:17'),
(2, 'Message 6', 3, '2016-07-02 15:25:50'),
(3, 'Message 7', 3, '2016-07-02 15:25:50'),
(2, 'Debug test bericht', 2, '2016-07-02 17:16:27'),
(2, 'Nieuw bericht', 2, '2016-07-02 18:31:33'),
(2, 'hallo', 2, '2016-07-02 19:00:06'),
(2, 'asd', 2, '2016-07-02 19:01:13'),
(2, 'TODO username toevoegen', 2, '2016-07-02 19:03:29'),
(2, 'TODO username toevoegen', 2, '2016-07-02 19:03:30'),
(1, 'Eerste bericht?', 0, '2016-07-02 19:04:26'),
(6, 'What is up', 3, '2016-07-02 20:25:15'),
(2, 'Hows it hangin', 3, '2016-07-02 20:25:48'),
(6, 'Hallo', 3, '2016-07-02 20:26:50'),
(2, 'Hey', 3, '2016-07-02 20:29:12'),
(6, 'Hoest', 3, '2016-07-02 20:29:20'),
(2, 'ff meer chats', 3, '2016-07-02 20:30:07'),
(2, 'erin', 3, '2016-07-02 20:30:10'),
(2, 'zodat', 3, '2016-07-02 20:30:14'),
(6, 'het ', 3, '2016-07-02 20:33:27'),
(6, 'scrollen', 3, '2016-07-02 20:33:30'),
(6, 'aangepast kan wordne', 3, '2016-07-02 20:33:35'),
(6, 'lal', 3, '2016-07-02 20:33:36'),
(6, 'allalal', 3, '2016-07-02 20:33:38'),
(2, 'Eerste bericht in deze chatroom', 991, '2016-07-02 20:35:42'),
(1, 'Tweede bericht', 0, '2016-07-02 21:21:24'),
(1, 'Tweede bericht ', 991, '2016-07-02 21:21:49');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `uid` int(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`uid`, `username`, `password`) VALUES
(1, 'jasper', 'wachtwoord'),
(2, 'piet', 'piet'),
(3, 'henk', 'henk'),
(4, 'piet', 'piet'),
(5, 'henk', 'henk'),
(6, 'partner', 'partner');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `chatroom`
--
ALTER TABLE `chatroom`
  ADD PRIMARY KEY (`crid`),
  ADD KEY `uid` (`uid`),
  ADD KEY `roomnumber` (`roomnumber`);

--
-- Indexes for table `messages`
--
ALTER TABLE `messages`
  ADD KEY `roomnumber` (`roomnumber`),
  ADD KEY `uid` (`uid`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`uid`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `chatroom`
--
ALTER TABLE `chatroom`
  MODIFY `crid` int(255) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=12;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `uid` int(255) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=7;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `chatroom`
--
ALTER TABLE `chatroom`
  ADD CONSTRAINT `chatroom_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `users` (`uid`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `messages`
--
ALTER TABLE `messages`
  ADD CONSTRAINT `messages_ibfk_2` FOREIGN KEY (`uid`) REFERENCES `users` (`uid`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `messages_ibfk_1` FOREIGN KEY (`roomnumber`) REFERENCES `chatroom` (`roomnumber`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
