-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: india_gov_voters_db
-- ------------------------------------------------------
-- Server version	5.7.10-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `address` (
  `address_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `ward_id_fk` int(10) unsigned DEFAULT NULL,
  `zone_id_fk` int(10) unsigned DEFAULT NULL,
  `landmark` varchar(45) DEFAULT NULL,
  `location` varchar(45) DEFAULT NULL,
  `vtc` varchar(45) DEFAULT NULL,
  `po` varchar(45) DEFAULT NULL,
  `district` varchar(45) DEFAULT NULL,
  `sub_district` varchar(45) DEFAULT NULL,
  `pincode` char(6) NOT NULL,
  `pin_code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`address_id`),
  KEY `ward_fk` (`ward_id_fk`),
  KEY `zone_fk` (`zone_id_fk`),
  CONSTRAINT `zone_fk` FOREIGN KEY (`zone_id_fk`) REFERENCES `zone` (`zone_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (1,1,1,'Near Bhadange House','Old Kailash Nagar','Ayodhya Nagar','Ayodhya Nagar','Nagpur','Nagpur','440024',NULL),(2,3,1,NULL,'25, Chakradhar Nagar, Gopal Nagar Road','Sai Nagar S.O','Sai Nagar','Amravati','Amravati','444607',NULL),(3,5,3,'STUDY CIRCLE','Narendra nagar','Vivekanand Nagar','Vivekanand Nagar','Nagpur','Nagpur','440015',NULL),(4,6,6,'Near Chandrapur co.op bank','Shiksahak Colony','Madheli','Madheli','Chandrapur','Warora','442907',NULL),(5,7,9,NULL,'Ram Nagar',NULL,'Akola','Agra','Agra','283102',NULL),(6,8,7,'dubey coloni','dubey coloni','Guna','Guna','Guna','Guna','473001',NULL),(7,9,9,NULL,'969-970 sector-3F','VAISHALI','vaishali','Gaziabad','Gaziabad','201010',NULL),(8,10,1,'Maithari Bhavan','Kalladakkavu Road','Vennala','Vennala','Ernakulam','Ernakulam','682028',NULL);
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-12-19 14:54:39
