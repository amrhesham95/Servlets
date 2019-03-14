-- MySQL dump 10.13  Distrib 8.0.13, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: chatdb
-- ------------------------------------------------------
-- Server version	8.0.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `friends`
--

DROP TABLE IF EXISTS `friends`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `friends` (
  `User` varchar(11) NOT NULL,
  `Friend` varchar(11) NOT NULL,
  PRIMARY KEY (`User`,`Friend`),
  KEY `Friend` (`Friend`),
  CONSTRAINT `friends_ibfk_1` FOREIGN KEY (`User`) REFERENCES `user` (`phonenum`),
  CONSTRAINT `friends_ibfk_2` FOREIGN KEY (`Friend`) REFERENCES `user` (`phonenum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `friends`
--

LOCK TABLES `friends` WRITE;
/*!40000 ALTER TABLE `friends` DISABLE KEYS */;
INSERT INTO `friends` VALUES ('12345','00000000'),('12345','01234567891'),('00000000','12345'),('01234567891','12345');
/*!40000 ALTER TABLE `friends` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `requests`
--

DROP TABLE IF EXISTS `requests`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `requests` (
  `Sender` varchar(11) NOT NULL,
  `Receiver` varchar(11) NOT NULL,
  PRIMARY KEY (`Sender`,`Receiver`),
  KEY `Receiver` (`Receiver`),
  CONSTRAINT `requests_ibfk_1` FOREIGN KEY (`Sender`) REFERENCES `user` (`phonenum`),
  CONSTRAINT `requests_ibfk_2` FOREIGN KEY (`Receiver`) REFERENCES `user` (`phonenum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `requests`
--

LOCK TABLES `requests` WRITE;
/*!40000 ALTER TABLE `requests` DISABLE KEYS */;
INSERT INTO `requests` VALUES ('12345','01234568795');
/*!40000 ALTER TABLE `requests` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user` (
  `Name` varchar(50) NOT NULL,
  `PhoneNum` varchar(11) NOT NULL,
  `Gender` enum('F','M') NOT NULL,
  `Country` varchar(50) NOT NULL,
  `DOB` date NOT NULL,
  `Picture` blob,
  `Password` varchar(50) NOT NULL,
  `Status` varchar(10) NOT NULL,
  `ChatBotStatus` tinyint(4) NOT NULL,
  `Email` varchar(100) NOT NULL,
  `BIO` varchar(200) NOT NULL,
  `Mode` enum('available','busy','away') NOT NULL,
  `EntryTimes` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`PhoneNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('amrr','00000000','M','Egypt','1996-08-08',NULL,'0000','offline',1,'amr@gmail.com','heloooooooo','available',2),('amr','0111','M','USA','1999-02-02',NULL,'0000','offline',1,'amrhesham@yahoo.com','hello','available',5),('Amr Hesham','01234567891','M','KSA','1995-02-16',NULL,'123456789','Offline',0,'AmrHesham@gmail.com','Hello','available',0),('Sahar','01234568795','F','Egypt','1996-07-08',NULL,'213456879','Offline',0,'SaharHany@gmail.com','Hiii','available',1),('amr','12345','M','Egypt','1999-02-02',NULL,'0000','online',1,'amrhesham@yahoo.com','hello','available',9);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-02-16 17:29:41
