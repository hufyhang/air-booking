# MySQL-Front 5.1  (Build 4.2)

/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE */;
/*!40101 SET SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES */;
/*!40103 SET SQL_NOTES='ON' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS */;
/*!40014 SET FOREIGN_KEY_CHECKS=0 */;


# Host: localhost    Database: airtickets
# ------------------------------------------------------
# Server version 5.1.38-community

DROP DATABASE IF EXISTS `airtickets`;
CREATE DATABASE `airtickets` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `airtickets`;

#
# Source for table airports
#

DROP TABLE IF EXISTS `airports`;
CREATE TABLE `airports` (
  `airport` varchar(11) NOT NULL DEFAULT 'N/A',
  `tax` double(4,2) unsigned NOT NULL DEFAULT '0.00',
  `gst` double(4,2) unsigned NOT NULL DEFAULT '0.00',
  PRIMARY KEY (`airport`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Dumping data for table airports
#

LOCK TABLES `airports` WRITE;
/*!40000 ALTER TABLE `airports` DISABLE KEYS */;
INSERT INTO `airports` VALUES ('JiNan',0.3,0.1);
INSERT INTO `airports` VALUES ('NanJing',0.5,0.3);
INSERT INTO `airports` VALUES ('ZhengZhou',0.6,0.8);
/*!40000 ALTER TABLE `airports` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table sellings
#

DROP TABLE IF EXISTS `sellings`;
CREATE TABLE `sellings` (
  `flightID` varchar(15) NOT NULL DEFAULT 'N/A',
  `class` varchar(30) NOT NULL DEFAULT 'N/A',
  `tickets` int(10) unsigned NOT NULL DEFAULT '0',
  `cost` double(12,2) unsigned NOT NULL DEFAULT '0.00'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Dumping data for table sellings
#

LOCK TABLES `sellings` WRITE;
/*!40000 ALTER TABLE `sellings` DISABLE KEYS */;
INSERT INTO `sellings` VALUES ('JC123','Economy Class',1,93.8);
INSERT INTO `sellings` VALUES ('JC123','First Class',1,1246);
INSERT INTO `sellings` VALUES ('JC123','First Class',2,2492);
/*!40000 ALTER TABLE `sellings` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table tickets
#

DROP TABLE IF EXISTS `tickets`;
CREATE TABLE `tickets` (
  `id` varchar(10) NOT NULL DEFAULT 'N/A',
  `departure` varchar(255) NOT NULL DEFAULT 'N/A',
  `destination` varchar(255) NOT NULL DEFAULT 'N/A',
  `date` varchar(255) NOT NULL DEFAULT 'N/A',
  `firstClassFee` double(6,2) unsigned NOT NULL DEFAULT '0.00',
  `bizClassFee` double(6,2) unsigned NOT NULL DEFAULT '0.00',
  `econClassFee` double(6,2) unsigned NOT NULL DEFAULT '0.00',
  `promotion` int(10) DEFAULT NULL,
  `discount` double(3,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Dumping data for table tickets
#

LOCK TABLES `tickets` WRITE;
/*!40000 ALTER TABLE `tickets` DISABLE KEYS */;
INSERT INTO `tickets` VALUES ('CA969','Beijing','Singapore','2009/10/25 15:00',500,300,200,999,1);
INSERT INTO `tickets` VALUES ('CA976','Singapore','Beijing','2009/12/25 15:00',500,300,200,999,1);
INSERT INTO `tickets` VALUES ('CA979','Singapore','Beijing','2010/1/15 15:00',500,300,200,999,1);
INSERT INTO `tickets` VALUES ('JC123','JiNan','ZhengZhou','2009/10/12',890,78,67,999,1);
INSERT INTO `tickets` VALUES ('JC125','JiNan','ZhengZhou','2009/10/13',890,78,67,999,1);
INSERT INTO `tickets` VALUES ('JC500','ZhengZhou','NanJing','2009/10/13',890,78,67,999,1);
/*!40000 ALTER TABLE `tickets` ENABLE KEYS */;
UNLOCK TABLES;

#
# Source for table users
#

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `username` varchar(10) NOT NULL DEFAULT '',
  `password` varchar(10) NOT NULL DEFAULT '',
  `email` varchar(255) DEFAULT NULL,
  `gender` varchar(6) NOT NULL DEFAULT 'male',
  `role` varchar(20) NOT NULL DEFAULT 'user'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Dumping data for table users
#

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('daniel','daniel','daniel@daniel.com','male','administrator');
INSERT INTO `users` VALUES ('user0','user0','user@user.com','male','user');
INSERT INTO `users` VALUES ('manager','manager','manager@manager.com','male','manager');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
