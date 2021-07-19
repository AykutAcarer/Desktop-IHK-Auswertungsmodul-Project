-- MySQL dump 10.13  Distrib 8.0.25, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: ihk_auswertungsmodul
-- ------------------------------------------------------
-- Server version	8.0.25

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
-- Table structure for table `kernqualifikation`
--

DROP TABLE IF EXISTS `kernqualifikation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `kernqualifikation` (
  `k_q_id` int NOT NULL AUTO_INCREMENT,
  `k_q_erreichte_punkte` double DEFAULT NULL,
  `k_q_min_30_punkte` varchar(45) DEFAULT NULL,
  `k_q_gewichtungsfaktor` double DEFAULT NULL,
  `k_q_punkte_gewichtet` varchar(45) DEFAULT NULL,
  `prueflings_id` int NOT NULL,
  PRIMARY KEY (`k_q_id`),
  KEY `kernqualifikation_ibfk_1` (`prueflings_id`),
  CONSTRAINT `kernqualifikation_ibfk_1` FOREIGN KEY (`prueflings_id`) REFERENCES `umschueler` (`prueflings_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kernqualifikation`
--

LOCK TABLES `kernqualifikation` WRITE;
/*!40000 ALTER TABLE `kernqualifikation` DISABLE KEYS */;
INSERT INTO `kernqualifikation` VALUES (10,78,'bestanden',0.4,'31.200000000000003',54),(12,75,'bestanden',0.4,'30',56);
/*!40000 ALTER TABLE `kernqualifikation` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-07-19  9:26:11
