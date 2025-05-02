-- MySQL dump 10.13  Distrib 8.0.39, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: workreportdb
-- ------------------------------------------------------
-- Server version	8.0.39

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `group_weekly_report`
--

DROP TABLE IF EXISTS `group_weekly_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `group_weekly_report` (
  `id` int NOT NULL AUTO_INCREMENT,
  `group_id` bigint DEFAULT NULL,
  `group_name` varchar(255) DEFAULT NULL,
  `report_name` varchar(255) DEFAULT NULL,
  `view_count` int DEFAULT '0',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `group_id` (`group_id`),
  CONSTRAINT `group_weekly_report_ibfk_1` FOREIGN KEY (`group_id`) REFERENCES `user_group` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_weekly_report`
--

LOCK TABLES `group_weekly_report` WRITE;
/*!40000 ALTER TABLE `group_weekly_report` DISABLE KEYS */;
INSERT INTO `group_weekly_report` VALUES (51,34,'系统研发部1','2025/3/31-2025/4/6-系统研发部1周报',0,'2025-03-31 09:54:42'),(52,35,'项目管理部1','2025/3/31-2025/4/6-项目管理部1周报',0,'2025-03-31 09:54:42'),(53,36,'后勤保障部','2025/3/31-2025/4/6-后勤保障部周报',0,'2025-03-31 09:54:42'),(54,37,'系统运营部','2025/3/31-2025/4/6-系统运营部周报',0,'2025-03-31 09:54:42'),(55,34,'系统研发部1','2025/3/31-2025/4/6-系统研发部1周报',0,'2025-03-31 14:21:19'),(56,35,'项目管理部1','2025/3/31-2025/4/6-项目管理部1周报',0,'2025-03-31 14:21:19'),(57,36,'后勤保障部','2025/3/31-2025/4/6-后勤保障部周报',0,'2025-03-31 14:21:19'),(58,37,'系统运营部','2025/3/31-2025/4/6-系统运营部周报',0,'2025-03-31 14:21:19');
/*!40000 ALTER TABLE `group_weekly_report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `user_name` varchar(50) DEFAULT NULL,
  `role` enum('ADMIN','USER','') DEFAULT 'USER',
  `enabled` tinyint(1) DEFAULT '1',
  `avatar` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`user_id`),
  UNIQUE KEY `user_pk` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','$2a$10$h0nBzzple4jRYFLBWuyaJeXf6mJszLOUOtkbcFx17Bc4FvVKQDK0O','管理员','ADMIN',1,'95c85962-187a-423f-a634-62dffbe2f008.png'),(31,'john','$2a$10$Jo.f/Poed43b7taeWVy60OueTocpOLKNRgPJ4qsjaNwgG3ar/77qe','约翰','USER',1,NULL),(32,'smith','$2a$10$mdoomJy.QTGo/OpUMPaYMuZRUUdFIceQ4BHJrfWQtvy0yTUguEKTW','斯密斯','USER',1,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_group`
--

DROP TABLE IF EXISTS `user_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_group` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `group_name` varchar(50) NOT NULL,
  `report_plan_name` varchar(250) DEFAULT NULL COMMENT '分组对应的周报计划名称',
  `start_date` date DEFAULT NULL,
  `cycle_days` int DEFAULT NULL,
  `allow_days` int DEFAULT NULL,
  `is_active` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `group_name` (`group_name`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_group`
--

LOCK TABLES `user_group` WRITE;
/*!40000 ALTER TABLE `user_group` DISABLE KEYS */;
INSERT INTO `user_group` VALUES (34,'系统研发部1','系统研发部周计划','2025-03-31',7,2,1),(35,'项目管理部1','项目管理部周报','2025-03-31',7,3,1),(36,'后勤保障部','后勤保障部周报','2025-03-31',7,2,1),(37,'系统运营部','系统运营部周报','2025-03-31',7,3,1);
/*!40000 ALTER TABLE `user_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_group_mapping`
--

DROP TABLE IF EXISTS `user_group_mapping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_group_mapping` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` varchar(50) NOT NULL,
  `group_id` bigint NOT NULL,
  `member_order` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `group_id` (`group_id`),
  CONSTRAINT `user_group_mapping_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `user_group_mapping_ibfk_2` FOREIGN KEY (`group_id`) REFERENCES `user_group` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_group_mapping`
--

LOCK TABLES `user_group_mapping` WRITE;
/*!40000 ALTER TABLE `user_group_mapping` DISABLE KEYS */;
INSERT INTO `user_group_mapping` VALUES (59,'smith',34,1),(60,'john',34,2),(61,'admin',34,3),(62,'admin',35,1),(63,'john',35,2),(64,'smith',35,3),(65,'admin',36,1),(66,'john',36,2),(67,'smith',36,3),(68,'admin',37,1),(69,'john',37,2),(70,'smith',37,3);
/*!40000 ALTER TABLE `user_group_mapping` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `weekly_report`
--

DROP TABLE IF EXISTS `weekly_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `weekly_report` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` varchar(10) NOT NULL,
  `user_name` varchar(10) NOT NULL,
  `report_name` varchar(1000) DEFAULT NULL,
  `group_id` int DEFAULT NULL COMMENT '该周报属于哪个组的周报',
  `content` text NOT NULL,
  `week_number` int NOT NULL,
  `year` int NOT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `allow_days` int DEFAULT NULL COMMENT '周报开放天数',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `status` enum('DRAFT','SUBMITTED') DEFAULT 'DRAFT',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=289 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `weekly_report`
--

LOCK TABLES `weekly_report` WRITE;
/*!40000 ALTER TABLE `weekly_report` DISABLE KEYS */;
INSERT INTO `weekly_report` VALUES (265,'smith','斯密斯','2025/3/31-2025/4/6-系统研发部1-斯密斯-周报',34,'',14,2025,'2025-03-31 09:54:42',2,'2025-03-31 09:54:42','DRAFT'),(266,'john','约翰','2025/3/31-2025/4/6-系统研发部1-约翰-周报',34,'',14,2025,'2025-03-31 09:54:42',2,'2025-03-31 09:54:42','DRAFT'),(267,'admin','管理员','2025/3/31-2025/4/6-系统研发部1-管理员-周报',34,'',14,2025,'2025-03-31 09:54:42',2,'2025-03-31 09:54:42','DRAFT'),(268,'admin','管理员','2025/3/31-2025/4/6-项目管理部1-管理员-周报',35,'',14,2025,'2025-03-31 09:54:42',3,'2025-03-31 09:54:42','DRAFT'),(269,'john','约翰','2025/3/31-2025/4/6-项目管理部1-约翰-周报',35,'',14,2025,'2025-03-31 09:54:42',3,'2025-03-31 09:54:42','DRAFT'),(270,'smith','斯密斯','2025/3/31-2025/4/6-项目管理部1-斯密斯-周报',35,'',14,2025,'2025-03-31 09:54:42',3,'2025-03-31 09:54:42','DRAFT'),(271,'admin','管理员','2025/3/31-2025/4/6-后勤保障部-管理员-周报',36,'',14,2025,'2025-03-31 09:54:42',2,'2025-03-31 09:54:42','DRAFT'),(272,'john','约翰','2025/3/31-2025/4/6-后勤保障部-约翰-周报',36,'',14,2025,'2025-03-31 09:54:42',2,'2025-03-31 09:54:42','DRAFT'),(273,'smith','斯密斯','2025/3/31-2025/4/6-后勤保障部-斯密斯-周报',36,'',14,2025,'2025-03-31 09:54:42',2,'2025-03-31 09:54:42','DRAFT'),(274,'admin','管理员','2025/3/31-2025/4/6-系统运营部-管理员-周报',37,'',14,2025,'2025-03-31 09:54:42',3,'2025-03-31 09:54:42','DRAFT'),(275,'john','约翰','2025/3/31-2025/4/6-系统运营部-约翰-周报',37,'',14,2025,'2025-03-31 09:54:42',3,'2025-03-31 09:54:42','DRAFT'),(276,'smith','斯密斯','2025/3/31-2025/4/6-系统运营部-斯密斯-周报',37,'',14,2025,'2025-03-31 09:54:42',3,'2025-03-31 09:54:42','DRAFT'),(277,'smith','斯密斯','2025/3/31-2025/4/6-系统研发部1-斯密斯-周报',34,'',14,2025,'2025-03-31 14:21:19',2,'2025-03-31 14:21:19','DRAFT'),(278,'john','约翰','2025/3/31-2025/4/6-系统研发部1-约翰-周报',34,'',14,2025,'2025-03-31 14:21:19',2,'2025-03-31 14:21:19','DRAFT'),(279,'admin','管理员','2025/3/31-2025/4/6-系统研发部1-管理员-周报',34,'',14,2025,'2025-03-31 14:21:19',2,'2025-03-31 14:21:19','DRAFT'),(280,'admin','管理员','2025/3/31-2025/4/6-项目管理部1-管理员-周报',35,'',14,2025,'2025-03-31 14:21:19',3,'2025-03-31 14:21:19','DRAFT'),(281,'john','约翰','2025/3/31-2025/4/6-项目管理部1-约翰-周报',35,'',14,2025,'2025-03-31 14:21:19',3,'2025-03-31 14:21:19','DRAFT'),(282,'smith','斯密斯','2025/3/31-2025/4/6-项目管理部1-斯密斯-周报',35,'',14,2025,'2025-03-31 14:21:19',3,'2025-03-31 14:21:19','DRAFT'),(283,'admin','管理员','2025/3/31-2025/4/6-后勤保障部-管理员-周报',36,'',14,2025,'2025-03-31 14:21:19',2,'2025-03-31 14:21:19','DRAFT'),(284,'john','约翰','2025/3/31-2025/4/6-后勤保障部-约翰-周报',36,'',14,2025,'2025-03-31 14:21:19',2,'2025-03-31 14:21:19','DRAFT'),(285,'smith','斯密斯','2025/3/31-2025/4/6-后勤保障部-斯密斯-周报',36,'',14,2025,'2025-03-31 14:21:19',2,'2025-03-31 14:21:19','DRAFT'),(286,'admin','管理员','2025/3/31-2025/4/6-系统运营部-管理员-周报',37,'',14,2025,'2025-03-31 14:21:19',3,'2025-03-31 14:21:19','DRAFT'),(287,'john','约翰','2025/3/31-2025/4/6-系统运营部-约翰-周报',37,'',14,2025,'2025-03-31 14:21:19',3,'2025-03-31 14:21:19','DRAFT'),(288,'smith','斯密斯','2025/3/31-2025/4/6-系统运营部-斯密斯-周报',37,'',14,2025,'2025-03-31 14:21:19',3,'2025-03-31 14:21:19','DRAFT');
/*!40000 ALTER TABLE `weekly_report` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-04-27 16:07:26
