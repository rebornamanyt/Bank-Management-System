CREATE DATABASE  IF NOT EXISTS `sss` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `sss`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: sss
-- ------------------------------------------------------
-- Server version	5.7.21-log

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
-- Table structure for table `loanpayment`
--

DROP TABLE IF EXISTS `loanpayment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `loanpayment` (
  `id` int(11) NOT NULL,
  `acno` int(11) NOT NULL,
  `loanacno` int(11) NOT NULL,
  `particular` varchar(45) DEFAULT NULL,
  `cheque` varchar(45) DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `time` time DEFAULT NULL,
  `amountpaid` int(11) DEFAULT NULL,
  `doneby` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loanpayment`
--

LOCK TABLES `loanpayment` WRITE;
/*!40000 ALTER TABLE `loanpayment` DISABLE KEYS */;
INSERT INTO `loanpayment` VALUES (12,100003,0,'By Cash','',10,'2019-05-05',NULL,NULL,NULL),(13,100003,0,'By Cheque','342er',20,'2019-05-05',NULL,NULL,NULL),(14,100003,0,'By Cheque','fgf',20,'2019-05-05',NULL,NULL,NULL),(15,100003,0,'By Cash','',40,'2019-05-05',NULL,13090,NULL),(16,100002,0,'By Cash',NULL,70,'2019-05-05',NULL,89,NULL),(17,100005,100,'By Cash','',56,'2019-05-08',NULL,156,NULL),(18,100002,100,'By Cash','',10,'2019-05-20',NULL,110,'null'),(19,100002,100,'By Cash','',10,'2019-05-20','21:25:38',130,'null'),(20,100011,104,'By Cash','',2000,'2019-05-22','10:58:15',2000,'santosh'),(106,100003,101,'By Cash','',50,'2019-05-05',NULL,NULL,NULL);
/*!40000 ALTER TABLE `loanpayment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loantaking`
--

DROP TABLE IF EXISTS `loantaking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `loantaking` (
  `id` int(11) NOT NULL,
  `acno` varchar(45) NOT NULL,
  `date` date NOT NULL,
  `time` time NOT NULL,
  `loanamount` int(11) NOT NULL,
  `duration` int(11) DEFAULT NULL,
  `totalamount` int(11) DEFAULT NULL,
  `totalinterest` int(11) DEFAULT NULL,
  `interest` int(11) DEFAULT NULL,
  `installment` float DEFAULT NULL,
  `paidtill` varchar(45) DEFAULT NULL,
  `purposeofloan` varchar(80) DEFAULT NULL,
  `granteracno` int(11) DEFAULT NULL,
  `incometype` varchar(45) DEFAULT NULL,
  `totalincome` int(11) DEFAULT NULL,
  `doneby` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `totalamountpaid` int(11) DEFAULT '0',
  `satelmentdate` date DEFAULT NULL,
  `satelmenttime` time DEFAULT NULL,
  `remark` varchar(45) DEFAULT NULL,
  `flag` int(11) DEFAULT '0',
  PRIMARY KEY (`acno`),
  UNIQUE KEY `acno_UNIQUE` (`acno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loantaking`
--

LOCK TABLES `loantaking` WRITE;
/*!40000 ALTER TABLE `loantaking` DISABLE KEYS */;
INSERT INTO `loantaking` VALUES (100,'100002','2019-05-04','00:00:00',1000,3,1060,60,20,353,'04/9/2019','dfsa',342,'gda',343,'rakesh','Good',130,'2019-05-21','20:04:35','',0),(101,'100003','2019-05-04','00:00:00',100,20,140,40,2,NULL,'null','kljlkjlk',79,'7987',97,NULL,'Good',140,'2019-05-20',NULL,'kjkjlj',0),(102,'100004','2019-05-20','00:00:00',10000,4,10800,800,200,2700,'05/9/2019','dfasf',453,'543',4534,NULL,'Good',10800,'2019-05-21','20:09:20','klj',1),(104,'100011','2019-05-22','10:56:08',10000,12,12400,2400,200,1033,'22/5/2020','kljsasklfjdsa',100002,'farmer',100000,'santosh',NULL,2000,NULL,NULL,NULL,0);
/*!40000 ALTER TABLE `loantaking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `login`
--

DROP TABLE IF EXISTS `login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `login` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `type` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `mobileno` int(10) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login`
--

LOCK TABLES `login` WRITE;
/*!40000 ALTER TABLE `login` DISABLE KEYS */;
INSERT INTO `login` VALUES (1,'rakesh','rakesh','rakesh','Admin','adalpur,hajipur',70787,NULL),(2,'dff','kj','kljl','Admin','kl',NULL,'2019-05-06 23:14:10'),(3,'jfd','dff','jlkj','Employee','lkj',NULL,'2019-05-06 23:34:58'),(5,'dsf','dff','kj','Admin','ljklj',NULL,'2019-05-06 23:35:57'),(24,'dsf','lk','kj','Employee','k',NULL,'2019-05-07 08:05:04'),(25,'jkl','jklkj','a','Employee','jkl',NULL,'2019-05-07 08:36:18'),(26,'gf','lka','q','Employee','lkj',NULL,'2019-05-07 08:36:37'),(27,'df','lkjkl','k','Employee','kjl',NULL,'2019-05-07 08:39:34'),(28,'f','klj','j','Employee','ljk',NULL,'2019-05-07 08:40:07'),(29,'gfk','klj','jk','Employee','ljk',NULL,'2019-05-07 08:45:09'),(30,'fdkl','lkjlkj','k','Employee','lkj',NULL,'2019-05-07 08:46:39'),(36,'santosh','santosh','santosh','Admin','agamkua',0,'2019-05-09 22:22:02'),(37,'anand','anand','anand','Admin','daudbigha',82988953,'2019-05-22 11:01:07');
/*!40000 ALTER TABLE `login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `memberdetail`
--

DROP TABLE IF EXISTS `memberdetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `memberdetail` (
  `acno` int(6) NOT NULL AUTO_INCREMENT,
  `fileno` int(6) DEFAULT NULL,
  `fname` varchar(15) NOT NULL,
  `mname` varchar(15) DEFAULT NULL,
  `lname` varchar(15) NOT NULL,
  `gtype` varchar(15) NOT NULL,
  `gname` varchar(45) NOT NULL,
  `email` varchar(45) DEFAULT NULL,
  `maritalstatus` varchar(10) NOT NULL,
  `dob` date NOT NULL,
  `gender` varchar(10) NOT NULL,
  `category` varchar(5) NOT NULL,
  `line1` varchar(45) DEFAULT NULL,
  `line2` varchar(45) DEFAULT NULL,
  `mobileno` varchar(10) DEFAULT NULL,
  `landmark` varchar(45) DEFAULT NULL,
  `city` varchar(20) DEFAULT NULL,
  `distric` varchar(20) DEFAULT NULL,
  `state` varchar(20) DEFAULT NULL,
  `pincode` varchar(10) DEFAULT NULL,
  `line1p` varchar(45) DEFAULT NULL,
  `line2p` varchar(45) DEFAULT NULL,
  `mobilenop` varchar(15) DEFAULT NULL,
  `landmarkp` varchar(45) DEFAULT NULL,
  `cityp` varchar(20) DEFAULT NULL,
  `districp` varchar(20) DEFAULT NULL,
  `statep` varchar(25) DEFAULT NULL,
  `pincodep` varchar(10) DEFAULT NULL,
  `introac1` int(11) DEFAULT NULL,
  `introac2` int(11) DEFAULT NULL,
  `fnamen` varchar(15) DEFAULT NULL,
  `mnamen` varchar(15) DEFAULT NULL,
  `lnamen` varchar(15) DEFAULT NULL,
  `relation` varchar(15) DEFAULT NULL,
  `dobn` varchar(15) DEFAULT NULL,
  `addressn` varchar(45) DEFAULT NULL,
  `gendern` varchar(45) DEFAULT NULL,
  `openedby` varchar(15) DEFAULT NULL,
  `openeddate` datetime DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `deactivateddate` datetime DEFAULT NULL,
  `deactivatedreason` varchar(60) DEFAULT NULL,
  `amount` int(11) DEFAULT '0',
  PRIMARY KEY (`acno`),
  UNIQUE KEY `acno_UNIQUE` (`acno`),
  UNIQUE KEY `fileno_UNIQUE` (`fileno`)
) ENGINE=InnoDB AUTO_INCREMENT=100014 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `memberdetail`
--

LOCK TABLES `memberdetail` WRITE;
/*!40000 ALTER TABLE `memberdetail` DISABLE KEYS */;
INSERT INTO `memberdetail` VALUES (100001,101,'Rakesh','','Kumar','Father','Jai Prakash Pd. Singh','rakeshkr7253@gmail.com','Unmarried','1999-08-10','Male','OBC',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'Active',NULL,NULL,1700),(100002,342,'rakafd','','dsfads','Father','dfas','dsfa','Unmarried','2019-01-01','Male','GEN','fdas','fdsa','765','dfsa','asfda','dfa','fdsa','34','fdas','fdsa','765','dfsa','asfda','dfa','fdsa','34',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'Active','2019-05-21 11:17:07','',228),(100003,41324,'dfa','','dfa','Father','dfasad','dfaads','Unmarried','2019-01-01','Male','GEN','fafds','dfadf','32423','das','fdsaf','dfsa','dsfa','dfa','fafds','dfadf','32423','das','fdsaf','dfsa','dsfa','dfa',44544,452,'erwr','ewqr','erq','req','2019/01/01','egft3t43t34','Male',NULL,'2019-03-28 19:53:52','Deactivate','2019-05-08 08:18:17','',0),(100004,324,'rakesh',' ','kumar','Father','dfafsd','gdasdf','Married','2019-01-03','Male','GEN','fdsa','ssdfa','dfsa','fdas','dfas','fd','dsfa','dfa','fdsa','ssdfa','dfsa','fdas','dfas','fd','dsfa','dfa',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'Active',NULL,NULL,3),(100005,102,'ramesh','fdas','kumar','Father','lkj','email','Married','2018-01-01','Female','OBC','faffdsa','cd','dfafdsa','dfa','fda','dfagdsa','fda','fads','faf','cd','dfa','dfa','fda','dfa','fdafdsa','fads',3243,342,'fdsa','fdsa','fdsa','fasd','2019/01/01','fdsadsfasdfa fdas\r\nf dsa','Male',NULL,'2019-04-05 20:27:40','Active',NULL,NULL,0),(100006,10,'rakesh','','kumar','Father','kjl','kjlkj','Married','2019-01-01','Male','GEN','kj','ljlj','lkjl','ljlkjl','ljklj','ljlk','ljlj','lkjl','kj','ljlj','lkjl','ljlkjl','ljklj','ljlk','ljlj','lkjl',10003,19384,'rakesh','','kuamr','lkj','2019/01/01','	kljl','Male','null','2019-05-09 21:46:10','Active',NULL,NULL,0),(100007,8789,'fdsa','jkhj','khjk','Father','jkh','jlk','Married','2019-01-01','Male','GEN',';k;l','lk;','klj','kjl','lkj','kk','klj','jklhk',';k;l','lk;','klj','kjl','lkj','kk','klj','jklhk',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'null',NULL,NULL,NULL,NULL,0),(100008,3487,'LKJJL','KLJLKJL','LKJkl','Father','jlkj','lkjlkjl','Married','2019-01-01','Male','GEN','kl','klj','kjl','kjl','kllj','klj','l;k','mmn','kl','klj','kjl','kjl','kllj','klj','l;k','mmn',100002,100004,'lkj','lkj','lkjl','lkjl','2019/01/01','ljljlk','Male','null','2019-05-09 22:01:40','Active',NULL,NULL,0),(100009,383,'santosh','raj','prabhakar','Father','kjljlkjlkj','lkjkljkl','Married','2019-01-01','Male','GEN','ljl','kjhkj','hkjh','hjh','kjh','khkj','kjhkj','khkj','ljl','kjhkj','hkjh','hjh','kjh','khkj','kjhkj','khkj',100004,100008,'jlkjJLK','klj','lkjl','jl','2019/01/01','ljkl		','Male','santosh','2019-05-10 09:25:14','Active',NULL,NULL,0),(100010,656,'kjklj','klj','lkjlk','Father','fmn,m',';l.,k;l,/.,','Married','2019-01-01','Male','GEN','fdsa','dfa','df','fa','dfasd','dfa','dfas','dfa','fdsa','dfa','df','fa','dfasd','dfa','dfas','dfa',100002,100004,'dfs','dsfa','fd','ds','2019/01/01','fd','Male','rakesh','2019-05-14 14:44:07','Active',NULL,NULL,0),(100011,343,'cjjkl','','lkjlk','Father','kljlkj','lkjlkjlk','Married','2019-01-01','Male','GEN','lk;l','kj','3878372345','345','453','435','3453','4566','lk;l','kj','3878372345','345','453','435','3453','4566',100002,100004,'kjkl','kjlk','kljlk','lkjlk','2019/01/01','jkjkl','Male','santosh','2019-05-22 10:52:24','Active',NULL,NULL,9732),(100012,1091,'neeraj','','aggrwal','Father','kjdlf','xyz@gmail.com','Married','1980-10-22','Male','GEN','broing road','dfa','9876543210','dfsa','dfa','patna','bihar','800026','broing road','dfa','9876543210','dfsa','dfa','patna','bihar','800026',100002,100004,'rakesh','','kumar','dfdfd','2019/01/01','hajipur','Male','rakesh','2019-05-22 12:30:08','Active',NULL,NULL,0);
/*!40000 ALTER TABLE `memberdetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transaction` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `acno` int(6) NOT NULL,
  `particular` varchar(45) NOT NULL,
  `chequeno` varchar(45) DEFAULT NULL,
  `amount` int(11) NOT NULL,
  `date` date DEFAULT NULL,
  `time` time DEFAULT NULL,
  `curentbalance` varchar(45) DEFAULT NULL,
  `doneby` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
INSERT INTO `transaction` VALUES (1,100003,'By Cash',NULL,576,'2019-04-06',NULL,'576',NULL),(2,100003,'By Cash','',100,'2019-04-06',NULL,'576',NULL),(3,100003,'By Cash','',200,'2019-04-06',NULL,'876',NULL),(4,100003,'To Cash','',100,'2019-04-06',NULL,'576',NULL),(5,100003,'To Cash','',100,'2019-04-06',NULL,'476',NULL),(6,100003,'By Cash','',200,'2019-04-06',NULL,'676',NULL),(7,100003,'By Cash','',100,'2019-04-09',NULL,'776',NULL),(8,100001,'By Cash','',700,'2019-05-03',NULL,'700',NULL),(9,100002,'To Cash','',100,'2019-05-03',NULL,'-100',NULL),(10,100001,'By Cash','',1000,'2019-05-03',NULL,'1700',NULL),(11,100007,'By Cash','',400,'2019-05-08',NULL,'400',NULL),(12,100007,'By Cash','',1000,'2019-05-08',NULL,'1000',NULL),(13,100004,'By Cash','',500,'2019-05-18',NULL,'500',NULL),(14,100002,'To Cash','',5,'2019-05-18',NULL,'-5',NULL),(15,100002,'By Cash','',3,'2019-05-18',NULL,'-2',NULL),(16,100004,'By Cash','',600,'2019-05-18',NULL,'1100',NULL),(17,100004,'To Cash','',1200,'2019-05-18',NULL,'-100',NULL),(18,100002,'By Cash','',5,'2019-05-18',NULL,'3',NULL),(19,100002,'By Cheque','',45,'2019-05-18',NULL,'48',NULL),(20,100002,'By Cash','',10,'2019-05-18',NULL,'58',NULL),(21,100002,'By Cheque','',20,'2019-05-18',NULL,'78',NULL),(22,100004,'By Cheque','',23,'2019-05-18',NULL,'-77',NULL),(23,100004,'By Cheque','',80,'2019-05-18',NULL,'3',NULL),(25,100002,'By Cash','',100,'2019-05-20',NULL,'178','null'),(26,100002,'By Cash','',100,'2019-05-20','21:34:15','278','null'),(27,100002,'To Cash','',50,'2019-05-20','21:37:55','228','null'),(28,100011,'By Cash','',9832,'2019-05-22','10:54:05','9832','santosh'),(29,100011,'To Cash','',100,'2019-05-22','10:54:40','9732','santosh'),(30,100012,'By Cash','',5000,'2019-05-22','12:30:54','5000','rakesh'),(31,100012,'To Cash','',5000,'2019-05-22','12:31:25','0','rakesh');
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-07-03  8:14:51
