CREATE DATABASE  IF NOT EXISTS `ccs_db` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `ccs_db`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win64 (x86_64)
--
-- Host: localhost    Database: ccs_db
-- ------------------------------------------------------
-- Server version	5.5.44

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
-- Table structure for table `currency`
--

DROP TABLE IF EXISTS `currency`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `currency` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `usd_rate` float DEFAULT NULL,
  `lkr_rate` float DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `currency`
--

LOCK TABLES `currency` WRITE;
/*!40000 ALTER TABLE `currency` DISABLE KEYS */;
INSERT INTO `currency` VALUES (1,0.00768,130.224);
/*!40000 ALTER TABLE `currency` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cus_id` varchar(45) NOT NULL,
  `cus_title` varchar(45) DEFAULT NULL,
  `cus_name` varchar(300) DEFAULT NULL,
  `cus_address` varchar(300) DEFAULT NULL,
  `cus_type` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`cus_id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (11,'CUS0001','Ms.','Isper Catering Equpment & supplys (Pvt) Ltd','No 477, Nawala RD, Rajagiriya','456546565'),(12,'CUS0002','Mr.','nimal','colombo','Item 2'),(13,'CUS0003','Mr.','kamal','hatton','Vehicle');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer_email`
--

DROP TABLE IF EXISTS `customer_email`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer_email` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cus_id` varchar(45) NOT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `cus` (`cus_id`),
  CONSTRAINT `cus` FOREIGN KEY (`cus_id`) REFERENCES `customer` (`cus_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_email`
--

LOCK TABLES `customer_email` WRITE;
/*!40000 ALTER TABLE `customer_email` DISABLE KEYS */;
INSERT INTO `customer_email` VALUES (22,'CUS0001','Niresh@gmail.com'),(23,'CUS0002','miren2002@gmail.com');
/*!40000 ALTER TABLE `customer_email` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer_mobile`
--

DROP TABLE IF EXISTS `customer_mobile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer_mobile` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cus_id` varchar(45) NOT NULL,
  `mob_no` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `cus4` (`cus_id`),
  CONSTRAINT `cus4` FOREIGN KEY (`cus_id`) REFERENCES `customer` (`cus_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=123 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_mobile`
--

LOCK TABLES `customer_mobile` WRITE;
/*!40000 ALTER TABLE `customer_mobile` DISABLE KEYS */;
INSERT INTO `customer_mobile` VALUES (120,'CUS0001','0778620956'),(121,'CUS0001','0775412845'),(122,'CUS0001','0312245741');
/*!40000 ALTER TABLE `customer_mobile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer_tel`
--

DROP TABLE IF EXISTS `customer_tel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer_tel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cus_id` varchar(45) NOT NULL,
  `tel_no` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `cus6` (`cus_id`),
  CONSTRAINT `cus6` FOREIGN KEY (`cus_id`) REFERENCES `customer` (`cus_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=129 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_tel`
--

LOCK TABLES `customer_tel` WRITE;
/*!40000 ALTER TABLE `customer_tel` DISABLE KEYS */;
INSERT INTO `customer_tel` VALUES (124,'CUS0001','0778620956'),(125,'CUS0001','0778458167'),(126,'CUS0001','0312245741'),(127,'CUS0002','0312279041'),(128,'CUS0003','0312279041');
/*!40000 ALTER TABLE `customer_tel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer_type`
--

DROP TABLE IF EXISTS `customer_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_type` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_type`
--

LOCK TABLES `customer_type` WRITE;
/*!40000 ALTER TABLE `customer_type` DISABLE KEYS */;
INSERT INTO `customer_type` VALUES (1,'Vehicle');
/*!40000 ALTER TABLE `customer_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer_vehicle_no`
--

DROP TABLE IF EXISTS `customer_vehicle_no`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer_vehicle_no` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cus_id` varchar(45) NOT NULL,
  `vehicle_no` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `cus2` (`cus_id`),
  CONSTRAINT `cus2` FOREIGN KEY (`cus_id`) REFERENCES `customer` (`cus_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_vehicle_no`
--

LOCK TABLES `customer_vehicle_no` WRITE;
/*!40000 ALTER TABLE `customer_vehicle_no` DISABLE KEYS */;
INSERT INTO `customer_vehicle_no` VALUES (12,'CUS0001','0112506500'),(13,'CUS0003','KD-3845');
/*!40000 ALTER TABLE `customer_vehicle_no` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `external_grn`
--

DROP TABLE IF EXISTS `external_grn`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `external_grn` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `external_grn_id` varchar(45) NOT NULL,
  `purchase_order_id` varchar(45) NOT NULL,
  `date` date DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `is_approved` int(11) DEFAULT '0',
  `status` int(11) DEFAULT '0',
  `user_id` varchar(45) NOT NULL,
  `approve_user_id` varchar(45) DEFAULT NULL,
  `approve_date` date DEFAULT NULL,
  PRIMARY KEY (`external_grn_id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `external_grn_fk2` (`user_id`),
  KEY `external_grn_fk3` (`user_id`),
  KEY `external_grn_fk1` (`purchase_order_id`),
  CONSTRAINT `external_grn_fk1` FOREIGN KEY (`purchase_order_id`) REFERENCES `purchase_order` (`purchase_order_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `external_grn_fk2` FOREIGN KEY (`user_id`) REFERENCES `user` (`EID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `external_grn_fk3` FOREIGN KEY (`user_id`) REFERENCES `user` (`EID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `external_grn`
--

LOCK TABLES `external_grn` WRITE;
/*!40000 ALTER TABLE `external_grn` DISABLE KEYS */;
/*!40000 ALTER TABLE `external_grn` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `external_grn_item`
--

DROP TABLE IF EXISTS `external_grn_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `external_grn_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `external_grn_id` varchar(45) NOT NULL,
  `item_id` varchar(45) NOT NULL,
  `batch_no` varchar(45) DEFAULT NULL,
  `description` varchar(400) DEFAULT NULL,
  `qty` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `external_grn_item_fk1` (`external_grn_id`),
  KEY `external_grn_item_fk2` (`item_id`),
  CONSTRAINT `external_grn_item_fk1` FOREIGN KEY (`external_grn_id`) REFERENCES `external_grn` (`external_grn_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `external_grn_item_fk2` FOREIGN KEY (`item_id`) REFERENCES `item` (`item_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `external_grn_item`
--

LOCK TABLES `external_grn_item` WRITE;
/*!40000 ALTER TABLE `external_grn_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `external_grn_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `external_return_note`
--

DROP TABLE IF EXISTS `external_return_note`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `external_return_note` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `external_return_note_id` varchar(45) NOT NULL,
  `return_note_type` varchar(50) DEFAULT NULL,
  `return_note_date` date DEFAULT NULL,
  `description` varchar(400) DEFAULT NULL,
  `is_approved` int(11) DEFAULT '0',
  `user_id` varchar(45) NOT NULL,
  `approve_user_id` varchar(45) DEFAULT NULL,
  `approve_date` date DEFAULT NULL,
  `status` int(11) DEFAULT '0',
  `time_stamp` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `supplier_id` varchar(45) NOT NULL,
  PRIMARY KEY (`external_return_note_id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `external_reurn_note_fk1` (`user_id`),
  KEY `external_reurn_note_fk2` (`approve_user_id`),
  KEY `external_reurn_note_fk3_idx` (`supplier_id`),
  CONSTRAINT `external_reurn_note_fk1` FOREIGN KEY (`user_id`) REFERENCES `user` (`EID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `external_reurn_note_fk2` FOREIGN KEY (`approve_user_id`) REFERENCES `user` (`EID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `external_reurn_note_fk3` FOREIGN KEY (`supplier_id`) REFERENCES `supplier` (`sid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `external_return_note`
--

LOCK TABLES `external_return_note` WRITE;
/*!40000 ALTER TABLE `external_return_note` DISABLE KEYS */;
/*!40000 ALTER TABLE `external_return_note` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `external_return_note_item`
--

DROP TABLE IF EXISTS `external_return_note_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `external_return_note_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `external_return_note_id` varchar(45) NOT NULL,
  `item_id` varchar(45) NOT NULL,
  `batch_no` varchar(45) DEFAULT NULL,
  `description` varchar(400) DEFAULT NULL,
  `qty` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `external_return_note_item_fk1` (`external_return_note_id`),
  KEY `external_return_note_item_fk2` (`item_id`),
  CONSTRAINT `external_return_note_item_fk1` FOREIGN KEY (`external_return_note_id`) REFERENCES `external_return_note` (`external_return_note_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `external_return_note_item_fk2` FOREIGN KEY (`item_id`) REFERENCES `item` (`item_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `external_return_note_item`
--

LOCK TABLES `external_return_note_item` WRITE;
/*!40000 ALTER TABLE `external_return_note_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `external_return_note_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `general_log`
--

DROP TABLE IF EXISTS `general_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `general_log` (
  `event_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `user_host` mediumtext NOT NULL,
  `thread_id` bigint(21) unsigned NOT NULL,
  `server_id` int(10) unsigned NOT NULL,
  `command_type` varchar(64) NOT NULL,
  `argument` mediumtext NOT NULL
) ENGINE=CSV DEFAULT CHARSET=utf8 COMMENT='General log';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `general_log`
--

LOCK TABLES `general_log` WRITE;
/*!40000 ALTER TABLE `general_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `general_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invoice`
--

DROP TABLE IF EXISTS `invoice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `invoice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `inv_no` varchar(45) NOT NULL,
  `date` date DEFAULT NULL,
  `salse_executive` varchar(45) DEFAULT NULL,
  `cus_type` varchar(100) DEFAULT NULL,
  `cus_id` varchar(45) NOT NULL,
  `vehicle_no` varchar(100) DEFAULT NULL,
  `total_discount` decimal(10,0) DEFAULT NULL,
  `payment_term` varchar(45) DEFAULT NULL,
  `total` double DEFAULT NULL,
  `net_amount` double DEFAULT NULL,
  `net_amount_word` varchar(500) DEFAULT NULL,
  `user_id` varchar(45) NOT NULL,
  `status` int(11) DEFAULT '0',
  `time_stamp` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`inv_no`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `invoice_fk1_idx` (`user_id`),
  KEY `invoice_fk2` (`cus_id`),
  CONSTRAINT `invoice_fk1` FOREIGN KEY (`user_id`) REFERENCES `user` (`EID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `invoice_fk2` FOREIGN KEY (`cus_id`) REFERENCES `customer` (`cus_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoice`
--

LOCK TABLES `invoice` WRITE;
/*!40000 ALTER TABLE `invoice` DISABLE KEYS */;
INSERT INTO `invoice` VALUES (5,'INT0001','2015-04-20','',NULL,'CUS0001',NULL,80,'Cash',19720,22326.98,'twenty two thousand three hundred twenty six','EM0004',0,NULL),(6,'INT0002','2015-06-08','',NULL,'CUS0001',NULL,0,'Cash',25000,28560,'twenty eight thousand five hundred sixty','EM0004',0,NULL);
/*!40000 ALTER TABLE `invoice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invoice_item`
--

DROP TABLE IF EXISTS `invoice_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `invoice_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `inv_no` varchar(45) NOT NULL,
  `item_id` varchar(45) NOT NULL,
  `part_no` varchar(45) DEFAULT NULL,
  `batch_no` varchar(45) NOT NULL,
  `unit` varchar(45) DEFAULT NULL,
  `unit_qty` int(11) DEFAULT NULL,
  `description` varchar(450) DEFAULT NULL,
  `qty` double DEFAULT NULL,
  `price` double DEFAULT NULL,
  `net_price` double DEFAULT NULL,
  `discount` double DEFAULT NULL,
  `discount_rate` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `invoice_item_fk1_idx` (`inv_no`),
  KEY `invoice_item_fk2_idx` (`item_id`,`batch_no`),
  CONSTRAINT `invoice_item_fk1` FOREIGN KEY (`inv_no`) REFERENCES `invoice` (`inv_no`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `invoice_item_fk2` FOREIGN KEY (`item_id`, `batch_no`) REFERENCES `item_sub` (`item_id`, `batch_no`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoice_item`
--

LOCK TABLES `invoice_item` WRITE;
/*!40000 ALTER TABLE `invoice_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `invoice_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `item_id` varchar(45) NOT NULL,
  `item_name` varchar(200) DEFAULT NULL,
  `qty` double DEFAULT '0',
  `user_id` varchar(45) NOT NULL,
  `item_description` varchar(300) DEFAULT NULL,
  `part_no` varchar(200) DEFAULT NULL,
  `item_main_category` int(11) NOT NULL,
  `item_sub_category` int(11) NOT NULL,
  PRIMARY KEY (`item_id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `item_fk2` (`user_id`),
  KEY `item_fk4_idx` (`item_sub_category`),
  KEY `item_fk3_idx` (`item_main_category`),
  CONSTRAINT `item_fk2` FOREIGN KEY (`user_id`) REFERENCES `user` (`EID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `item_fk3` FOREIGN KEY (`item_main_category`) REFERENCES `item_main_category` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `item_fk4` FOREIGN KEY (`item_sub_category`) REFERENCES `item_sub_category` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item`
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` VALUES (1,'ITM0001','gear oil',5,'EM0004','gear oil test','XC123',2,3),(2,'ITM0002','mm',6,'EM0004','ss','DF456',2,4),(4,'ITM0004','ty',6,'EM0004','rt','YSD4584',2,4);
/*!40000 ALTER TABLE `item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item_main_category`
--

DROP TABLE IF EXISTS `item_main_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item_main_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_main_category`
--

LOCK TABLES `item_main_category` WRITE;
/*!40000 ALTER TABLE `item_main_category` DISABLE KEYS */;
INSERT INTO `item_main_category` VALUES (2,'gear oil'),(4,'hyt'),(5,'nn');
/*!40000 ALTER TABLE `item_main_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item_sub`
--

DROP TABLE IF EXISTS `item_sub`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item_sub` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `item_id` varchar(45) NOT NULL,
  `batch_no` varchar(45) NOT NULL,
  `qty` double DEFAULT '0',
  `buying_price` double DEFAULT NULL,
  `reorder_level` double NOT NULL DEFAULT '0',
  `selling_price` double DEFAULT NULL,
  `unit` int(11) NOT NULL,
  PRIMARY KEY (`item_id`,`batch_no`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `item_sub_fk1` (`item_id`),
  KEY `item_sub_fk2_idx` (`unit`),
  CONSTRAINT `item_sub_fk1` FOREIGN KEY (`item_id`) REFERENCES `item` (`item_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `item_sub_fk2` FOREIGN KEY (`unit`) REFERENCES `item_unit_value` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_sub`
--

LOCK TABLES `item_sub` WRITE;
/*!40000 ALTER TABLE `item_sub` DISABLE KEYS */;
INSERT INTO `item_sub` VALUES (1,'ITM0001','BAT0001',5,600,10,700,1),(3,'ITM0002','BAT0001',6,500,10,600,1),(5,'ITM0004','BAT0001',6,800,10,900,1);
/*!40000 ALTER TABLE `item_sub` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item_sub_category`
--

DROP TABLE IF EXISTS `item_sub_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item_sub_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `item_Sub_Category` varchar(45) DEFAULT NULL,
  `item_main_category` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `item_sub_category_fk1_idx` (`item_main_category`),
  CONSTRAINT `item_sub_category_fk1` FOREIGN KEY (`item_main_category`) REFERENCES `item_main_category` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_sub_category`
--

LOCK TABLES `item_sub_category` WRITE;
/*!40000 ALTER TABLE `item_sub_category` DISABLE KEYS */;
INSERT INTO `item_sub_category` VALUES (3,'ioc',2),(4,'lanka oil',2),(5,'tt',2),(6,'kk',4),(7,'rte',5);
/*!40000 ALTER TABLE `item_sub_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item_unit`
--

DROP TABLE IF EXISTS `item_unit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item_unit` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `unit` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_unit`
--

LOCK TABLES `item_unit` WRITE;
/*!40000 ALTER TABLE `item_unit` DISABLE KEYS */;
INSERT INTO `item_unit` VALUES (1,'ml'),(2,'L');
/*!40000 ALTER TABLE `item_unit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item_unit_value`
--

DROP TABLE IF EXISTS `item_unit_value`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item_unit_value` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `unit_qty` varchar(45) DEFAULT NULL,
  `item_unit` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `item_unit_value_fk1_idx` (`item_unit`),
  CONSTRAINT `item_unit_value_fk1` FOREIGN KEY (`item_unit`) REFERENCES `item_unit` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_unit_value`
--

LOCK TABLES `item_unit_value` WRITE;
/*!40000 ALTER TABLE `item_unit_value` DISABLE KEYS */;
INSERT INTO `item_unit_value` VALUES (1,'4',1),(2,'1',2),(3,'2',2);
/*!40000 ALTER TABLE `item_unit_value` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notifications`
--

DROP TABLE IF EXISTS `notifications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notifications` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nid` varchar(45) NOT NULL,
  `type` varchar(45) NOT NULL,
  `description` varchar(100) NOT NULL,
  `ui` varchar(45) NOT NULL,
  `added_user` varchar(45) DEFAULT NULL,
  `date_added` date NOT NULL,
  `date_resolved` date DEFAULT NULL,
  `is_resolved` int(11) NOT NULL DEFAULT '0',
  `resolved_user` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`nid`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_notify_notify_idx` (`type`),
  CONSTRAINT `fk_notify_notify` FOREIGN KEY (`type`) REFERENCES `user_notification_type` (`type`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notifications`
--

LOCK TABLES `notifications` WRITE;
/*!40000 ALTER TABLE `notifications` DISABLE KEYS */;
/*!40000 ALTER TABLE `notifications` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment_type`
--

DROP TABLE IF EXISTS `payment_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `payment_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `payment_type` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment_type`
--

LOCK TABLES `payment_type` WRITE;
/*!40000 ALTER TABLE `payment_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `payment_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `printer`
--

DROP TABLE IF EXISTS `printer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `printer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `type` varchar(45) NOT NULL,
  `description` varchar(200) NOT NULL,
  PRIMARY KEY (`pid`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_printer_printer_type_idx` (`type`),
  CONSTRAINT `fk_printer_printer_type` FOREIGN KEY (`type`) REFERENCES `printer_type` (`type`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `printer`
--

LOCK TABLES `printer` WRITE;
/*!40000 ALTER TABLE `printer` DISABLE KEYS */;
INSERT INTO `printer` VALUES (1,'PRN0001','Microsoft XPS Document Writer','Banquet','Ultra fast Invisible printing');
/*!40000 ALTER TABLE `printer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `printer_report`
--

DROP TABLE IF EXISTS `printer_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `printer_report` (
  `pid` varchar(45) NOT NULL,
  `rid` varchar(45) NOT NULL,
  PRIMARY KEY (`pid`,`rid`),
  KEY `fk_pr_r_idx` (`rid`),
  CONSTRAINT `fk_pr_p` FOREIGN KEY (`pid`) REFERENCES `printer` (`pid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_pr_r` FOREIGN KEY (`rid`) REFERENCES `report` (`rid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `printer_report`
--

LOCK TABLES `printer_report` WRITE;
/*!40000 ALTER TABLE `printer_report` DISABLE KEYS */;
INSERT INTO `printer_report` VALUES ('PRN0001','RPT0001');
/*!40000 ALTER TABLE `printer_report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `printer_type`
--

DROP TABLE IF EXISTS `printer_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `printer_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(45) NOT NULL,
  PRIMARY KEY (`type`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `printer_type`
--

LOCK TABLES `printer_type` WRITE;
/*!40000 ALTER TABLE `printer_type` DISABLE KEYS */;
INSERT INTO `printer_type` VALUES (1,'Alacarte'),(2,'Banquet'),(3,'Room'),(4,'Stock');
/*!40000 ALTER TABLE `printer_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchase_order`
--

DROP TABLE IF EXISTS `purchase_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `purchase_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `purchase_order_id` varchar(45) NOT NULL,
  `supplier_id` varchar(45) NOT NULL,
  `date` date DEFAULT NULL,
  `status` int(11) DEFAULT '0',
  `is_approved` varchar(45) DEFAULT '0',
  `user_id` varchar(45) NOT NULL,
  `approve_user_id` varchar(45) DEFAULT NULL,
  `approve_date` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`purchase_order_id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `purchase_order_fk1` (`user_id`),
  KEY `purchase_order_fk2` (`supplier_id`),
  CONSTRAINT `purchase_order_fk1` FOREIGN KEY (`user_id`) REFERENCES `user` (`EID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `purchase_order_fk2` FOREIGN KEY (`supplier_id`) REFERENCES `supplier` (`sid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchase_order`
--

LOCK TABLES `purchase_order` WRITE;
/*!40000 ALTER TABLE `purchase_order` DISABLE KEYS */;
/*!40000 ALTER TABLE `purchase_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchase_order_item`
--

DROP TABLE IF EXISTS `purchase_order_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `purchase_order_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `purchase_order_id` varchar(45) NOT NULL,
  `item_id` varchar(45) NOT NULL,
  `batch_no` varchar(45) NOT NULL,
  `qty` double DEFAULT NULL,
  `remarks` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `purchase_order_item_fk1` (`purchase_order_id`),
  KEY `purchase_order_item_fk3` (`item_id`,`batch_no`),
  CONSTRAINT `purchase_order_item_fk1` FOREIGN KEY (`purchase_order_id`) REFERENCES `purchase_order` (`purchase_order_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `purchase_order_item_fk3` FOREIGN KEY (`item_id`, `batch_no`) REFERENCES `item_sub` (`item_id`, `batch_no`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchase_order_item`
--

LOCK TABLES `purchase_order_item` WRITE;
/*!40000 ALTER TABLE `purchase_order_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `purchase_order_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `report`
--

DROP TABLE IF EXISTS `report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `report` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rid` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `type` varchar(45) NOT NULL,
  `url` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`rid`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_repot_report_type_idx` (`type`),
  CONSTRAINT `fk_repot_report_type_idx` FOREIGN KEY (`type`) REFERENCES `report_type` (`type`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report`
--

LOCK TABLES `report` WRITE;
/*!40000 ALTER TABLE `report` DISABLE KEYS */;
INSERT INTO `report` VALUES (1,'RPT0001','Bannquet Kot Report','Banquet','.//Reports//KOTBanquet.jasper'),(32,'RPT0002','Banquet Bot Report','Banquet','.//Reports//BOTBanquet.jasper'),(33,'RPT0003','Advance Receipt','Banquet','.//Reports//AdvanceReceipt.jasper'),(34,'RPT0004','Banquet Main Bill','Banquet','.//Reports//BanquetMainBill.jasper'),(35,'RPT0005','Alacarte BOT Report','Alacarte','.//Reports//BOTAlacarte.jasper'),(36,'RPT0006','Room BOT Report','Room','.//Reports//BOTRoom.jasper'),(37,'RPT0007','External Good Received Note','Stock','.//Reports//ExternalGoodReceivedNote.jasper'),(38,'RPT0008','Function Sheet Main','Banquet','.//Reports//FunctionSheetMain.jasper'),(39,'RPT0009','Function Sheet Subreport 1','Banquet','.//Reports//FunctionSheetSub1.jasper'),(40,'RPT0010','Function Sheet Subreport 2','Banquet','.//Reports//FunctionSheetSub2.jasper'),(41,'RPT0011','Internal Good Received Note','Stock','.//Reports//InternalGoodReceivedNote.jasper'),(42,'RPT0012','Alacarte KOT Report','Alacarte','.//Reports//KOTAlacarte.jasper'),(43,'RPT0013','Room KOT Report','Room','.//Reports//KOTRoom.jasper'),(44,'RPT0014','Main Bill Alacart BOT Report','Alacarte','.//Reports//MainBillAlacartBOT.jasper'),(45,'RPT0015','Main Bill Alacart KOT Report','Alacarte','.//Reports//MainBillAlacartKOT.jasper'),(46,'RPT0016','Main Bill Banquet BOT Report','Banquet','.//Reports//MainBillBanquetBOT.jasper'),(47,'RPT0017','Main Bill Banquet KOT Report','Banquet','.//Reports//MainBillBanquetKOT.jasper'),(48,'RPT0018','Main Bill Room BOT Report','Room','.//Reports//MainBillRoomBOT.jasper'),(49,'RPT0019','Main Bill Room KOT Report','Room','.//Reports//MainBillRoomKOT.jasper'),(50,'RPT0020','Nationality','Banquet','.//Reports//Nationality.jasper'),(51,'RPT0021','Nationality by country','Banquet','.//Reports//Nationality_by_country.jasper'),(52,'RPT0022','No Show','Room','.//Reports//NoShow.jasper'),(53,'RPT0023','Purchase Order Sheet','Stock','.//Reports//PurchaseOrderSheet.jasper'),(54,'RPT0024','Registration Card','Room','.//Reports//RegistrationCard.jasper'),(55,'RPT0025','Reservation Card','Room','.//Reports//ResevationCard.jasper'),(56,'RPT0026','Room Master Bill Main Report','Room','.//Reports//RoomMasterBillMain.jasper'),(57,'RPT0027','Room Sales By Room No Report','Room','.//Reports//RoomSalseByRoomNo.jasper'),(58,'RPT0028','Stock Issue Note','Stock','.//Reports//StockIssueNote.jasper'),(59,'RPT0029','Stock Requisition Report','Stock','.//Reports//StockRequisition.jasper'),(60,'RPT0030','Sundry Bill','Room','.//Reports//SundryBill.jasper'),(61,'RPT0031','Banquet Report','Banquet','.//Reports//Banquet.jasper');
/*!40000 ALTER TABLE `report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `report_reg`
--

DROP TABLE IF EXISTS `report_reg`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `report_reg` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `report_id` varchar(45) NOT NULL,
  `printer_id` varchar(45) NOT NULL,
  `status` int(11) DEFAULT '0',
  `user_id` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `report_reg_fk1` (`report_id`),
  KEY `report_reg_fk2` (`printer_id`),
  KEY `report_reg_fk3` (`user_id`),
  CONSTRAINT `report_reg_fk1` FOREIGN KEY (`report_id`) REFERENCES `report` (`rid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `report_reg_fk2` FOREIGN KEY (`printer_id`) REFERENCES `printer` (`pid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `report_reg_fk3` FOREIGN KEY (`user_id`) REFERENCES `user` (`EID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report_reg`
--

LOCK TABLES `report_reg` WRITE;
/*!40000 ALTER TABLE `report_reg` DISABLE KEYS */;
/*!40000 ALTER TABLE `report_reg` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `report_type`
--

DROP TABLE IF EXISTS `report_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `report_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(45) NOT NULL,
  PRIMARY KEY (`type`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report_type`
--

LOCK TABLES `report_type` WRITE;
/*!40000 ALTER TABLE `report_type` DISABLE KEYS */;
INSERT INTO `report_type` VALUES (1,'Alacarte'),(2,'Banquet'),(3,'Room'),(4,'Stock');
/*!40000 ALTER TABLE `report_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `req_note`
--

DROP TABLE IF EXISTS `req_note`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `req_note` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `req_note_id` varchar(45) NOT NULL,
  `date` date DEFAULT NULL,
  `description` varchar(400) DEFAULT NULL,
  `req_note_type` varchar(45) DEFAULT NULL,
  `is_approved` int(11) DEFAULT '0',
  `status` int(11) DEFAULT '0',
  `user_id` varchar(45) NOT NULL,
  `approve_user_id` varchar(45) DEFAULT NULL,
  `approve_date` date DEFAULT NULL,
  PRIMARY KEY (`req_note_id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `req_note_fk1` (`user_id`),
  KEY `req_note_fk2` (`user_id`),
  CONSTRAINT `req_note_fk1` FOREIGN KEY (`user_id`) REFERENCES `user` (`EID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `req_note_fk2` FOREIGN KEY (`user_id`) REFERENCES `user` (`EID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `req_note`
--

LOCK TABLES `req_note` WRITE;
/*!40000 ALTER TABLE `req_note` DISABLE KEYS */;
/*!40000 ALTER TABLE `req_note` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `req_note_item`
--

DROP TABLE IF EXISTS `req_note_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `req_note_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `req_note_id` varchar(45) NOT NULL,
  `item_id` varchar(45) NOT NULL,
  `description` varchar(450) DEFAULT NULL,
  `qty` double DEFAULT NULL,
  `issue_qty` double DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `req_note_item_fk1` (`req_note_id`),
  KEY `req_note_item_fk2` (`item_id`),
  CONSTRAINT `req_note_item_fk1` FOREIGN KEY (`req_note_id`) REFERENCES `req_note` (`req_note_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `req_note_item_fk2` FOREIGN KEY (`item_id`) REFERENCES `item` (`item_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `req_note_item`
--

LOCK TABLES `req_note_item` WRITE;
/*!40000 ALTER TABLE `req_note_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `req_note_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `return_note`
--

DROP TABLE IF EXISTS `return_note`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `return_note` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `return_note_id` varchar(45) NOT NULL,
  `return_note_type` varchar(50) DEFAULT NULL,
  `return_note_date` date DEFAULT NULL,
  `description` varchar(400) DEFAULT NULL,
  `is_approved` int(11) DEFAULT '0',
  `user_id` varchar(45) NOT NULL,
  `approve_user_id` varchar(45) DEFAULT NULL,
  `approve_date` date DEFAULT NULL,
  `status` int(11) DEFAULT '0',
  PRIMARY KEY (`return_note_id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `return_note_fk1` (`user_id`),
  KEY `return_note_fk2` (`approve_user_id`),
  CONSTRAINT `return_note_fk1` FOREIGN KEY (`user_id`) REFERENCES `user` (`EID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `return_note_fk2` FOREIGN KEY (`approve_user_id`) REFERENCES `user` (`EID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `return_note`
--

LOCK TABLES `return_note` WRITE;
/*!40000 ALTER TABLE `return_note` DISABLE KEYS */;
/*!40000 ALTER TABLE `return_note` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `return_note_item`
--

DROP TABLE IF EXISTS `return_note_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `return_note_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `return_note_id` varchar(45) NOT NULL,
  `item_id` varchar(45) NOT NULL,
  `batch_no` varchar(45) DEFAULT NULL,
  `description` varchar(400) DEFAULT NULL,
  `qty` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `return_note_item_fk1` (`return_note_id`),
  KEY `return_note_item_fk2` (`item_id`),
  CONSTRAINT `return_note_item_fk1` FOREIGN KEY (`return_note_id`) REFERENCES `return_note` (`return_note_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `return_note_item_fk2` FOREIGN KEY (`item_id`) REFERENCES `item` (`item_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `return_note_item`
--

LOCK TABLES `return_note_item` WRITE;
/*!40000 ALTER TABLE `return_note_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `return_note_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `return_resolve`
--

DROP TABLE IF EXISTS `return_resolve`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `return_resolve` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `resolve_id` varchar(45) NOT NULL,
  `return_note_id` varchar(45) DEFAULT NULL,
  `return_date` date DEFAULT NULL,
  `item_id` varchar(45) NOT NULL,
  `batch_no` varchar(45) NOT NULL,
  `resolve_date` date DEFAULT NULL,
  `type` varchar(100) DEFAULT NULL,
  `qty` double DEFAULT NULL,
  `user_id` varchar(45) NOT NULL,
  PRIMARY KEY (`resolve_id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `return_resolve_fk1` (`item_id`,`batch_no`),
  KEY `return_resolve_fk2` (`user_id`),
  CONSTRAINT `return_resolve_fk1` FOREIGN KEY (`item_id`, `batch_no`) REFERENCES `item_sub` (`item_id`, `batch_no`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `return_resolve_fk2` FOREIGN KEY (`user_id`) REFERENCES `user` (`EID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `return_resolve`
--

LOCK TABLES `return_resolve` WRITE;
/*!40000 ALTER TABLE `return_resolve` DISABLE KEYS */;
/*!40000 ALTER TABLE `return_resolve` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `server_config`
--

DROP TABLE IF EXISTS `server_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `server_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ip` varchar(20) DEFAULT NULL,
  `port` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `server_config`
--

LOCK TABLES `server_config` WRITE;
/*!40000 ALTER TABLE `server_config` DISABLE KEYS */;
INSERT INTO `server_config` VALUES (1,'127.0.0.1',1099);
/*!40000 ALTER TABLE `server_config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `slow_log`
--

DROP TABLE IF EXISTS `slow_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `slow_log` (
  `start_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `user_host` mediumtext NOT NULL,
  `query_time` time NOT NULL,
  `lock_time` time NOT NULL,
  `rows_sent` int(11) NOT NULL,
  `rows_examined` int(11) NOT NULL,
  `db` varchar(512) NOT NULL,
  `last_insert_id` int(11) NOT NULL,
  `insert_id` int(11) NOT NULL,
  `server_id` int(10) unsigned NOT NULL,
  `sql_text` mediumtext NOT NULL,
  `thread_id` bigint(21) unsigned NOT NULL
) ENGINE=CSV DEFAULT CHARSET=utf8 COMMENT='Slow log';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `slow_log`
--

LOCK TABLES `slow_log` WRITE;
/*!40000 ALTER TABLE `slow_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `slow_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stock_handler`
--

DROP TABLE IF EXISTS `stock_handler`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stock_handler` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` date DEFAULT NULL,
  `category` varchar(45) DEFAULT NULL,
  `item_id` varchar(45) NOT NULL,
  `qty` varchar(45) DEFAULT NULL,
  `status` int(11) DEFAULT '0',
  `user_id` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `stock_handler_fk1` (`item_id`),
  KEY `stock_handler_fk2` (`user_id`),
  CONSTRAINT `stock_handler_fk1` FOREIGN KEY (`item_id`) REFERENCES `item` (`item_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `stock_handler_fk2` FOREIGN KEY (`user_id`) REFERENCES `user` (`EID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stock_handler`
--

LOCK TABLES `stock_handler` WRITE;
/*!40000 ALTER TABLE `stock_handler` DISABLE KEYS */;
/*!40000 ALTER TABLE `stock_handler` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplier`
--

DROP TABLE IF EXISTS `supplier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `supplier` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sid` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `reg_no` varchar(200) NOT NULL,
  `address` varchar(500) NOT NULL,
  PRIMARY KEY (`sid`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplier`
--

LOCK TABLES `supplier` WRITE;
/*!40000 ALTER TABLE `supplier` DISABLE KEYS */;
INSERT INTO `supplier` VALUES (1,'SUP0001','Saman','200000000V','Malabe');
/*!40000 ALTER TABLE `supplier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplier_item`
--

DROP TABLE IF EXISTS `supplier_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `supplier_item` (
  `item_id` varchar(45) NOT NULL,
  `sid` varchar(45) NOT NULL,
  PRIMARY KEY (`item_id`,`sid`),
  KEY `fk_ss_ss_idx` (`sid`),
  CONSTRAINT `fk_ss_item` FOREIGN KEY (`item_id`) REFERENCES `item` (`item_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_ss_ss` FOREIGN KEY (`sid`) REFERENCES `supplier` (`sid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplier_item`
--

LOCK TABLES `supplier_item` WRITE;
/*!40000 ALTER TABLE `supplier_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `supplier_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplier_tel`
--

DROP TABLE IF EXISTS `supplier_tel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `supplier_tel` (
  `tel` varchar(45) NOT NULL,
  `sid` varchar(45) NOT NULL,
  PRIMARY KEY (`tel`,`sid`),
  KEY `fk_ss_tel_fk_idx` (`sid`),
  CONSTRAINT `fk_ss_tel_fk` FOREIGN KEY (`sid`) REFERENCES `supplier` (`sid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplier_tel`
--

LOCK TABLES `supplier_tel` WRITE;
/*!40000 ALTER TABLE `supplier_tel` DISABLE KEYS */;
INSERT INTO `supplier_tel` VALUES ('0719195161','SUP0001');
/*!40000 ALTER TABLE `supplier_tel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `EID` varchar(45) NOT NULL,
  `title` varchar(10) NOT NULL,
  `name` varchar(200) NOT NULL,
  `user_name` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `spec_code` varchar(45) DEFAULT NULL,
  `flag` varchar(45) DEFAULT NULL,
  `category_type` varchar(45) NOT NULL,
  `is_canceled` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`EID`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `user_name_UNIQUE` (`user_name`),
  KEY `usertype` (`flag`),
  KEY `categoryFK_idx` (`category_type`),
  KEY `user` (`flag`),
  KEY `user_fk2` (`category_type`),
  CONSTRAINT `user_fk1` FOREIGN KEY (`flag`) REFERENCES `user_type` (`type`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `user_fk2` FOREIGN KEY (`category_type`) REFERENCES `user_sub_type` (`type`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (8,'EM0004','Mr.','Saiton','saiton','3CRO+GzZlkI=',NULL,'Manager','Administrator',0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_notification_type`
--

DROP TABLE IF EXISTS `user_notification_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_notification_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(45) NOT NULL,
  PRIMARY KEY (`type`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_notification_type`
--

LOCK TABLES `user_notification_type` WRITE;
/*!40000 ALTER TABLE `user_notification_type` DISABLE KEYS */;
INSERT INTO `user_notification_type` VALUES (8,'Stock');
/*!40000 ALTER TABLE `user_notification_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_notifications`
--

DROP TABLE IF EXISTS `user_notifications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_notifications` (
  `EID` varchar(45) NOT NULL,
  `type` varchar(45) NOT NULL,
  `show` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`EID`,`type`),
  KEY `fkusernotf2_idx` (`type`),
  CONSTRAINT `fkusernotf1` FOREIGN KEY (`EID`) REFERENCES `user` (`EID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fkusernotf2` FOREIGN KEY (`type`) REFERENCES `user_notification_type` (`type`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_notifications`
--

LOCK TABLES `user_notifications` WRITE;
/*!40000 ALTER TABLE `user_notifications` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_notifications` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_permission_type`
--

DROP TABLE IF EXISTS `user_permission_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_permission_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(45) NOT NULL,
  PRIMARY KEY (`type`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=128 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_permission_type`
--

LOCK TABLES `user_permission_type` WRITE;
/*!40000 ALTER TABLE `user_permission_type` DISABLE KEYS */;
INSERT INTO `user_permission_type` VALUES (72,'Customer Registration'),(73,'User Registration'),(78,'Invoice'),(112,'Item Registration'),(115,'Supplier Registration'),(118,'External GRN'),(119,'External GRN Overview'),(120,'External Return Note'),(121,'External Return Note Overview'),(122,'Purchase Order'),(126,'Invoice Settings'),(127,'Purchase Order Overview');
/*!40000 ALTER TABLE `user_permission_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_permissions`
--

DROP TABLE IF EXISTS `user_permissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_permissions` (
  `EID` varchar(45) NOT NULL,
  `type` varchar(45) NOT NULL,
  `allow_insert` int(11) NOT NULL DEFAULT '0',
  `allow_update` int(11) NOT NULL DEFAULT '0',
  `allow_delete` int(11) NOT NULL DEFAULT '0',
  `allow_view` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`EID`,`type`),
  KEY `fkuserperf2_idx` (`type`),
  CONSTRAINT `fkuserperf1` FOREIGN KEY (`EID`) REFERENCES `user` (`EID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fkuserperf2` FOREIGN KEY (`type`) REFERENCES `user_permission_type` (`type`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_permissions`
--

LOCK TABLES `user_permissions` WRITE;
/*!40000 ALTER TABLE `user_permissions` DISABLE KEYS */;
INSERT INTO `user_permissions` VALUES ('EM0004','Customer Registration',1,1,1,1),('EM0004','External GRN',1,1,1,1),('EM0004','External GRN Overview',1,1,1,1),('EM0004','External Return Note',1,1,1,1),('EM0004','External Return Note Overview',1,1,1,1),('EM0004','Invoice',1,1,1,1),('EM0004','Invoice Settings',1,1,1,1),('EM0004','Item Registration',1,1,1,1),('EM0004','Purchase Order',1,1,1,1),('EM0004','Purchase Order Overview',1,1,1,1),('EM0004','Supplier Registration',1,1,1,1),('EM0004','User Registration',1,1,1,1);
/*!40000 ALTER TABLE `user_permissions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_sub_type`
--

DROP TABLE IF EXISTS `user_sub_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_sub_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(45) NOT NULL,
  PRIMARY KEY (`type`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_sub_type`
--

LOCK TABLES `user_sub_type` WRITE;
/*!40000 ALTER TABLE `user_sub_type` DISABLE KEYS */;
INSERT INTO `user_sub_type` VALUES (1,'Stock'),(2,'Administrator'),(3,'Staff');
/*!40000 ALTER TABLE `user_sub_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_type`
--

DROP TABLE IF EXISTS `user_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(45) NOT NULL,
  PRIMARY KEY (`type`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_type`
--

LOCK TABLES `user_type` WRITE;
/*!40000 ALTER TABLE `user_type` DISABLE KEYS */;
INSERT INTO `user_type` VALUES (1,'Ultra User'),(2,'Super User'),(3,'Accountant'),(4,'Manager'),(5,'Walker');
/*!40000 ALTER TABLE `user_type` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-07-05 14:05:51
