-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: sasystem
-- ------------------------------------------------------
-- Server version	5.7.26

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `rollcall`
--

DROP TABLE IF EXISTS `rollcall`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rollcall` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `studentid` varchar(50) DEFAULT NULL,
  `firstname` varchar(50) DEFAULT NULL,
  `lastname` varchar(50) DEFAULT NULL,
  `time` varchar(50) DEFAULT NULL,
  `date` varchar(50) DEFAULT NULL,
  `subject` varchar(50) DEFAULT NULL,
  `faculty` varchar(50) DEFAULT NULL,
  `room` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=27 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rollcall`
--

LOCK TABLES `rollcall` WRITE;
/*!40000 ALTER TABLE `rollcall` DISABLE KEYS */;
INSERT INTO `rollcall` VALUES (25,'2018-1060','GIRLIE','VILLARAMA','07:38:26 PM','12-15-2019','Object Oriented Programming','Jonathan Cabrera','ComLab 1'),(23,'2018-1938','JECEL','GUINITA','03:32:50 PM','12-15-2019','Platform Technology','Arjay Sacay','ICT room 2'),(24,'2018-1941','REYMAR','SUMANGUE','03:33:07 PM','12-15-2019','Object Oriented Programming','Jonathan Cabrera','ComLab 3'),(22,'2018-1939','DOLLY FAITH','QUIMBO','03:31:16 PM','12-15-2019','Data Structures and Algorithm','Cindy Lasco','ICT room 1'),(21,'2018-1942','RACHEL ANN','LIGAD','03:30:55 PM','12-15-2019','Web Systems and Technologies','Arjay Sacay','ICT room 2'),(19,'2018-1942','RACHEL ANN','LIGAD','03:29:55 PM','12-15-2019','Object Oriented Programming','Jonathan Cabrera','ICT room 1'),(20,'2018-1969','RONIEL','MORENO','03:30:31 PM','12-15-2019','Web Systems and Technologies','Arjay Sacay','ComLab 3');
/*!40000 ALTER TABLE `rollcall` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-12-16 12:15:20
