
-- phpMyAdmin SQL Dump
-- version 3.5.2.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Apr 03, 2016 at 07:50 PM
-- Server version: 10.0.20-MariaDB
-- PHP Version: 5.2.17

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `u186878343_pknrs`
--

-- --------------------------------------------------------

--
-- Table structure for table `BloodBankData`
--

CREATE TABLE IF NOT EXISTS `BloodBankData` (
  `Bank_ID` bigint(255) NOT NULL AUTO_INCREMENT,
  `Bank_Name` varchar(1000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Owner_Name` varchar(1000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Address` varchar(1000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Email` varchar(1000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `User_Name` varchar(1000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Phone` varchar(1000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Password` varchar(1000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Ap` varchar(1000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Am` varchar(1000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Bp` varchar(1000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Bm` varchar(1000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Op` varchar(1000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Om` varchar(1000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ABp` varchar(1000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ABm` varchar(1000) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`Bank_ID`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=4 ;

--
-- Dumping data for table `BloodBankData`
--

INSERT INTO `BloodBankData` (`Bank_ID`, `Bank_Name`, `Owner_Name`, `Address`, `Email`, `User_Name`, `Phone`, `Password`, `Ap`, `Am`, `Bp`, `Bm`, `Op`, `Om`, `ABp`, `ABm`) VALUES
(1, 'Akash Blood Bank', 'Akash Giri', '48,Babupara,Kolkata-700093', 'akx.sonu@gmail.com', 'akash', '9804945122', '12345', '0', '6', '0', '4', '0', '5', '0', '8'),
(3, 'Anam', 'Anam', 'kolkata', 'akx.sonu@gmail.com', 'iamanam', '9804945122', 'anam1234', '3', '60', '3', '0', '2', '0', '3', '2');

-- --------------------------------------------------------

--
-- Table structure for table `BloodRequest`
--

CREATE TABLE IF NOT EXISTS `BloodRequest` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(1000) COLLATE utf8_unicode_ci NOT NULL,
  `Hospital` varchar(1000) COLLATE utf8_unicode_ci NOT NULL,
  `Address` varchar(1000) COLLATE utf8_unicode_ci NOT NULL,
  `BloodGroup` varchar(1000) COLLATE utf8_unicode_ci NOT NULL,
  `Contact` varchar(1000) COLLATE utf8_unicode_ci NOT NULL,
  `Message` varchar(1000) COLLATE utf8_unicode_ci NOT NULL,
  `Date` varchar(1000) COLLATE utf8_unicode_ci NOT NULL,
  `BloodBankName` varchar(500) COLLATE utf8_unicode_ci NOT NULL,
  `Accept` int(11) NOT NULL DEFAULT '0',
  `CustEmail` varchar(500) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=23 ;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `Username` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `Gender` char(1) COLLATE utf8_unicode_ci NOT NULL,
  `Email` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `GLink` varchar(500) COLLATE utf8_unicode_ci NOT NULL,
  UNIQUE KEY `Email` (`Email`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`Username`, `Gender`, `Email`, `GLink`) VALUES
('AkashGiri', 'M', 'akx.sonu@gmail.com', 'https://plus.google.com/+AkashGirisonu');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
