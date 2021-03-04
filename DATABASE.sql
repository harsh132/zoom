-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Mar 04, 2021 at 09:10 PM
-- Server version: 10.4.17-MariaDB
-- PHP Version: 8.0.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `zoom`
--

-- --------------------------------------------------------

--
-- Table structure for table `cars`
--

CREATE TABLE `cars` (
  `cid` int(11) NOT NULL,
  `rent` mediumint(9) DEFAULT NULL,
  `capacity` tinyint(4) DEFAULT NULL,
  `bootspace` tinyint(1) DEFAULT NULL,
  `model` varchar(50) DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  `owner` int(11) NOT NULL,
  `city` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `cars`
--

INSERT INTO `cars` (`cid`, `rent`, `capacity`, `bootspace`, `model`, `status`, `owner`, `city`) VALUES
(1, 5000, 4, 1, 'Tata Nano', 1, 1, 'Mumbai'),
(2, 10000, 5, 1, 'Renault Duster', 1, 1, 'Mumbai'),
(3, 20000, 5, 1, 'Jaguar A50', 0, 1, 'Mumbai'),
(4, 50000, 5, 0, 'Rolls Royce Phantom 350', 1, 1, 'Mumbai'),
(5, 50000, 2, 0, 'Bugatti Chevron', 1, 1, 'Mumbai'),
(6, 500, 3, 0, 'Rickshaw', 1, 1, 'Mumbai'),
(7, 100, 2, 0, 'Piaggio Ape', 1, 1, 'Patna');

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `oid` int(11) NOT NULL,
  `type` varchar(10) NOT NULL,
  `expense` mediumint(9) NOT NULL,
  `car_id` int(11) NOT NULL,
  `renter` int(11) NOT NULL,
  `leaser` int(11) NOT NULL,
  `city` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`oid`, `type`, `expense`, `car_id`, `renter`, `leaser`, `city`) VALUES
(1, 'rent', 20000, 3, 1, 1, 'Mumbai');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `uid` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`uid`, `name`, `email`, `password`) VALUES
(1, 'Jayesh', 'jayeshbhole123@gmail.com', 'jayesh'),
(2, 'admin', 'admin', 'admin');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cars`
--
ALTER TABLE `cars`
  ADD PRIMARY KEY (`cid`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`oid`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`uid`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `cars`
--
ALTER TABLE `cars`
  MODIFY `cid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `oid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `uid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
