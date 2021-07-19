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
-- Table structure for table `ergebnis`
--

DROP TABLE IF EXISTS `ergebnis`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ergebnis` (
  `ergebnis_id` int NOT NULL AUTO_INCREMENT,
  `summe_teil_a` double DEFAULT NULL,
  `teil_a_im_wortlaut` varchar(45) DEFAULT NULL,
  `summe_teil_b` double DEFAULT NULL,
  `teil_b_im_wortlaut` varchar(45) DEFAULT NULL,
  `prueflings_id` int DEFAULT NULL,
  PRIMARY KEY (`ergebnis_id`),
  KEY `ergebnis_ibfk_1` (`prueflings_id`),
  CONSTRAINT `ergebnis_ibfk_1` FOREIGN KEY (`prueflings_id`) REFERENCES `umschueler` (`prueflings_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ergebnis`
--

LOCK TABLES `ergebnis` WRITE;
/*!40000 ALTER TABLE `ergebnis` DISABLE KEYS */;
INSERT INTO `ergebnis` VALUES (11,41.25,'mangelhaft',83.80000000000001,'gut',54),(13,41.25,'mangelhaft',83.80000000000001,'gut',54),(16,41.25,'mangelhaft',83.80000000000001,'gut',54),(17,41.25,'mangelhaft',83.80000000000001,'gut',54),(18,34.25,'mangelhaft',64,'ausreichend',56);
/*!40000 ALTER TABLE `ergebnis` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-07-19  9:26:16
