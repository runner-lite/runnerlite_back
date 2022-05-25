-- MySQL dump 10.13  Distrib 8.0.25, for macos11 (x86_64)
--
-- Host: localhost    Database: runner_lite
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
-- Table structure for table `REF_VOLUNTEERS_SAMPLES`
--

DROP TABLE IF EXISTS `REF_VOLUNTEERS_SAMPLES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `REF_VOLUNTEERS_SAMPLES` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `TEAMS_ID` int NOT NULL COMMENT 'Беговая команда',
  `REF_VOLUNTEERS_POSITION_ID` int NOT NULL COMMENT 'Тип позиции волонтёра',
  `QUANTITY` int NOT NULL COMMENT 'Количество человек на одну позицию волонтёра',
  `SURELY_QTY` int DEFAULT NULL COMMENT 'Флаг обязательной позиции (null/0 - не обязательная позиция; 1 - обязательная позиция без совмещения; 2 - обязательная позиция с возможным совмещением)',
  PRIMARY KEY (`ID`),
  KEY `FK_REF_VOLUNTEERS_SAMPL_REF_VOLUNTEERS_POSITION_ID_idx` (`REF_VOLUNTEERS_POSITION_ID`),
  KEY `FK_REF_VOLUNTEERS_SAMPL_TEAMS_idx` (`TEAMS_ID`),
  CONSTRAINT `FK_REF_VOLUNTEERS_SAMPL_REF_VOLUNTEERS_POSITION_ID` FOREIGN KEY (`REF_VOLUNTEERS_POSITION_ID`) REFERENCES `REF_VOLUNTEERS_POSITION` (`ID`),
  CONSTRAINT `FK_REF_VOLUNTEERS_SAMPL_TEAMS` FOREIGN KEY (`TEAMS_ID`) REFERENCES `teams` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `REF_VOLUNTEERS_SAMPLES`
--

LOCK TABLES `REF_VOLUNTEERS_SAMPLES` WRITE;
/*!40000 ALTER TABLE `REF_VOLUNTEERS_SAMPLES` DISABLE KEYS */;
INSERT INTO `REF_VOLUNTEERS_SAMPLES` VALUES (1,1,1,2,1),(2,1,2,1,1),(3,1,3,1,2),(4,1,4,1,NULL),(5,1,5,1,NULL),(6,1,6,1,2),(7,1,7,1,2),(8,1,8,1,2),(9,2,1,1,1),(10,2,2,1,1),(11,2,3,1,2),(12,2,4,1,NULL),(13,2,5,1,NULL),(14,2,6,1,2),(15,2,7,1,NULL),(16,2,8,1,2),(17,3,1,1,1),(18,3,2,1,1),(19,3,3,1,NULL),(20,3,4,1,NULL),(21,3,5,1,1),(22,3,6,1,2),(23,3,7,1,NULL),(24,3,8,1,2);
/*!40000 ALTER TABLE `REF_VOLUNTEERS_SAMPLES` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-23 14:38:10