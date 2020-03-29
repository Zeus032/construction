# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.5.62)
# Database: construction
# Generation Time: 2020-03-29 22:08:08 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table bidder
# ------------------------------------------------------------

DROP TABLE IF EXISTS `bidder`;

CREATE TABLE `bidder` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `bidder` WRITE;
/*!40000 ALTER TABLE `bidder` DISABLE KEYS */;

INSERT INTO `bidder` (`id`, `name`)
VALUES
	(1,'SAP'),
	(2,'google'),
	(3,'MS');

/*!40000 ALTER TABLE `bidder` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table hibernate_sequence
# ------------------------------------------------------------

DROP TABLE IF EXISTS `hibernate_sequence`;

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;

INSERT INTO `hibernate_sequence` (`next_val`)
VALUES
	(12);

/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table issuer
# ------------------------------------------------------------

DROP TABLE IF EXISTS `issuer`;

CREATE TABLE `issuer` (
  `id` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `pib` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `issuer` WRITE;
/*!40000 ALTER TABLE `issuer` DISABLE KEYS */;

INSERT INTO `issuer` (`id`, `address`, `name`, `pib`)
VALUES
	(1,'Zelengorska 8','kom prom','1111'),
	(2,'avalksa 10','rajak','2222'),
	(3,'sumadijska 1','blabla','444');

/*!40000 ALTER TABLE `issuer` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table offer
# ------------------------------------------------------------

DROP TABLE IF EXISTS `offer`;

CREATE TABLE `offer` (
  `id` int(11) NOT NULL,
  `accepted` bit(1) DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  `bidder_id` int(11) DEFAULT NULL,
  `issuer_id` int(11) DEFAULT NULL,
  `tender_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `issuer_id` (`issuer_id`),
  KEY `tender_id` (`tender_id`),
  KEY `bidder_ID` (`bidder_id`),
  CONSTRAINT `bidder_ID` FOREIGN KEY (`bidder_id`) REFERENCES `bidder` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `issuer_id` FOREIGN KEY (`issuer_id`) REFERENCES `issuer` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tender_id` FOREIGN KEY (`tender_id`) REFERENCES `tender` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `offer` WRITE;
/*!40000 ALTER TABLE `offer` DISABLE KEYS */;

INSERT INTO `offer` (`id`, `accepted`, `amount`, `bidder_id`, `issuer_id`, `tender_id`)
VALUES
	(2,b'0',110,1,1,1),
	(4,b'1',200,2,1,3),
	(7,b'1',200,2,2,1),
	(9,b'0',230,1,3,8),
	(10,b'1',450,1,3,8),
	(11,b'0',123,2,3,8);

/*!40000 ALTER TABLE `offer` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table tender
# ------------------------------------------------------------

DROP TABLE IF EXISTS `tender`;

CREATE TABLE `tender` (
  `id` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `issuer_id` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `tender_issuer_id` (`issuer_id`),
  CONSTRAINT `tender_issuer_id` FOREIGN KEY (`issuer_id`) REFERENCES `issuer` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `tender` WRITE;
/*!40000 ALTER TABLE `tender` DISABLE KEYS */;

INSERT INTO `tender` (`id`, `description`, `issuer_id`, `name`)
VALUES
	(1,'tender for something',3,'tender 123'),
	(3,'tender for balls',3,'tender new'),
	(5,'tenderica',2,'tender blaaa'),
	(6,'aaccc',1,'tender daf'),
	(8,'Olmero test',3,'Test Olmero');

/*!40000 ALTER TABLE `tender` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
