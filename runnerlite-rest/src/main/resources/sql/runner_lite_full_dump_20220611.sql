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
-- Table structure for table `ACHIEVEMENTS`
--

DROP TABLE IF EXISTS `ACHIEVEMENTS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ACHIEVEMENTS` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `REF_ACHIEVEMENTS_TYPE_ID` int NOT NULL COMMENT 'ID достижения',
  `RUNNING_RESULTS_ID` int NOT NULL COMMENT 'Результат забега',
  PRIMARY KEY (`ID`),
  KEY `FK_ACHIEVEMENTS_REF_ACHIEVEMENTS_TYPE_ID_idx` (`REF_ACHIEVEMENTS_TYPE_ID`),
  KEY `FK_ACHIEVEMENTS_RUNNING_RESULTS_ID_idx` (`RUNNING_RESULTS_ID`),
  CONSTRAINT `FK_ACHIEVEMENTS_REF_ACHIEVEMENTS_TYPE_ID` FOREIGN KEY (`REF_ACHIEVEMENTS_TYPE_ID`) REFERENCES `REF_ACHIEVEMENTS_TYPE` (`ID`),
  CONSTRAINT `FK_ACHIEVEMENTS_RUNNING_RESULTS_ID` FOREIGN KEY (`RUNNING_RESULTS_ID`) REFERENCES `RUNNING_RESULTS` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACHIEVEMENTS`
--

LOCK TABLES `ACHIEVEMENTS` WRITE;
/*!40000 ALTER TABLE `ACHIEVEMENTS` DISABLE KEYS */;
INSERT INTO `ACHIEVEMENTS` VALUES (1,1,1),(2,2,2),(3,3,3),(4,6,2),(5,1,3),(6,1,6),(7,2,6),(8,1,9),(9,1,5),(10,1,6),(11,5,6),(12,1,11),(13,1,12),(14,1,13),(15,1,14),(16,6,11);
/*!40000 ALTER TABLE `ACHIEVEMENTS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cytyes_districts`
--

DROP TABLE IF EXISTS `cytyes_districts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cytyes_districts` (
  `city_id` int NOT NULL,
  `district_id` int NOT NULL,
  PRIMARY KEY (`city_id`,`district_id`),
  KEY `fk_district_id_idx` (`district_id`),
  CONSTRAINT `fk_district_id` FOREIGN KEY (`district_id`) REFERENCES `ref_districts` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_ref_city_id` FOREIGN KEY (`city_id`) REFERENCES `ref_cities` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cytyes_districts`
--

LOCK TABLES `cytyes_districts` WRITE;
/*!40000 ALTER TABLE `cytyes_districts` DISABLE KEYS */;
/*!40000 ALTER TABLE `cytyes_districts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `EMERGENCY_CONTACT`
--

DROP TABLE IF EXISTS `EMERGENCY_CONTACT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `EMERGENCY_CONTACT` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) NOT NULL COMMENT 'Имя контакта',
  `PHONE_NUMBER` varchar(20) NOT NULL COMMENT 'Номер телефона контакта',
  `RELATIVE_TYPE` enum('Супруг','Ребёнок','Другое') NOT NULL COMMENT 'Родство',
  `SEC_USERS_ID` int NOT NULL COMMENT 'Бегун',
  `MAIN_CONTACT` tinyint(1) NOT NULL DEFAULT '1' COMMENT 'Флаг главного контакта на случай ЧП',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `NAME_UNIQUE` (`NAME`),
  UNIQUE KEY `SEC_USERS_ID_UNIQUE` (`SEC_USERS_ID`),
  CONSTRAINT `FK_EMERGENCY_CONTACT_SEC_USERS_ID` FOREIGN KEY (`SEC_USERS_ID`) REFERENCES `SEC_USERS` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `EMERGENCY_CONTACT`
--

LOCK TABLES `EMERGENCY_CONTACT` WRITE;
/*!40000 ALTER TABLE `EMERGENCY_CONTACT` DISABLE KEYS */;
INSERT INTO `EMERGENCY_CONTACT` VALUES (1,'Скай Уокер','+12353453434','Ребёнок',4,1),(2,'Терсинцева Дарья','9165553272','Супруг',7,1),(8,'Ханина ХХ','9129129129','Супруг',9,1),(9,'Сибгатуллина СС','9119119191','Супруг',8,1);
/*!40000 ALTER TABLE `EMERGENCY_CONTACT` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `locations`
--

DROP TABLE IF EXISTS `locations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `locations` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `REF_DISTRICTS_ID` int NOT NULL COMMENT 'ID города команды',
  `ADDRESS` varchar(255) DEFAULT NULL COMMENT 'Официальный адрес места проведения забегов (не место сбора)',
  `ALTERNATE_LOCATIONS_ID` int DEFAULT NULL COMMENT 'Ссылка на альтернативную локацию (на будущую разработку, если будем получать адреса из какого-то источника)',
  `USE_ALTLOCATIONS` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'Если установлено «0» - используется текущая локация',
  PRIMARY KEY (`ID`),
  KEY `FK_TEAMS_LOCATIONS_REF_DISTRICTS_ID_idx` (`REF_DISTRICTS_ID`),
  CONSTRAINT `FK_TEAMS_LOCATIONS_REF_DISTRICTS_ID` FOREIGN KEY (`REF_DISTRICTS_ID`) REFERENCES `REF_DISTRICTS` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `locations`
--

LOCK TABLES `locations` WRITE;
/*!40000 ALTER TABLE `locations` DISABLE KEYS */;
INSERT INTO `locations` VALUES (1,1,'ул. Вучетича, рядом с Лесной опытной дачей (д. 46/с1)',NULL,0),(2,8,'Ногинский центральный городской парк культуры и отдыха',NULL,0),(3,10,'Флотская 1А',NULL,0),(4,9,'Парк Сосновка',NULL,0),(5,4,'улица в г. Пушкин',NULL,0),(6,11,'Заречная, 4А',NULL,0),(7,13,'Лавочки у зеленого моста через Яузу напротив школы №10',NULL,0);
/*!40000 ALTER TABLE `locations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MAILING_LIST`
--

DROP TABLE IF EXISTS `MAILING_LIST`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `MAILING_LIST` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `SEC_USERS_ID` int NOT NULL COMMENT 'Бегун',
  `TEAMS_ID` int NOT NULL COMMENT 'Команда, на которую подписан',
  `REF_SUBSCRIBE_TYPE_ID` int NOT NULL COMMENT 'Тип email расссылки',
  PRIMARY KEY (`ID`),
  KEY `FK_MAILING_LIST_SEC_USERS_ID_idx` (`SEC_USERS_ID`),
  KEY `FK_MAILING_LIST_TEAMS_ID_idx` (`TEAMS_ID`),
  KEY `FK_MAILING_LIST_REF_SUBSCRIBE_TYPE_ID_idx` (`REF_SUBSCRIBE_TYPE_ID`),
  CONSTRAINT `FK_MAILING_LIST_REF_SUBSCRIBE_TYPE_ID` FOREIGN KEY (`REF_SUBSCRIBE_TYPE_ID`) REFERENCES `REF_SUBSCRIBE_TYPE` (`ID`),
  CONSTRAINT `FK_MAILING_LIST_SEC_USERS_ID` FOREIGN KEY (`SEC_USERS_ID`) REFERENCES `SEC_USERS` (`ID`),
  CONSTRAINT `FK_MAILING_LIST_TEAMS_ID` FOREIGN KEY (`TEAMS_ID`) REFERENCES `TEAMS` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MAILING_LIST`
--

LOCK TABLES `MAILING_LIST` WRITE;
/*!40000 ALTER TABLE `MAILING_LIST` DISABLE KEYS */;
INSERT INTO `MAILING_LIST` VALUES (1,7,1,1),(2,7,1,4),(3,7,1,3),(4,7,1,2),(5,8,2,1),(6,8,2,3),(7,8,2,4),(8,9,2,1),(9,9,2,3),(10,9,2,2),(11,9,2,4),(12,10,2,1),(13,10,2,3),(14,10,2,4),(15,11,1,1),(16,11,1,3),(17,11,1,4),(18,12,4,1),(19,12,4,3),(20,12,4,4);
/*!40000 ALTER TABLE `MAILING_LIST` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PHONE_NUMBERS`
--

DROP TABLE IF EXISTS `PHONE_NUMBERS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `PHONE_NUMBERS` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `SEC_USERS_ID` int NOT NULL COMMENT 'Бегун',
  `PHONE_NUMBER` varchar(20) NOT NULL COMMENT 'Номер телефона',
  `TYPE` enum('Сотовый','Домашний','Рабочий') NOT NULL DEFAULT 'Сотовый' COMMENT 'Тип номера телефона',
  PRIMARY KEY (`ID`),
  KEY `FK_PHONE_NUMBERS_SEC_USERS_ID_idx` (`SEC_USERS_ID`),
  CONSTRAINT `FK_PHONE_NUMBERS_SEC_USERS_ID` FOREIGN KEY (`SEC_USERS_ID`) REFERENCES `SEC_USERS` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PHONE_NUMBERS`
--

LOCK TABLES `PHONE_NUMBERS` WRITE;
/*!40000 ALTER TABLE `PHONE_NUMBERS` DISABLE KEYS */;
INSERT INTO `PHONE_NUMBERS` VALUES (1,1,'+79992223355','Сотовый'),(2,1,'+74954325656','Домашний'),(3,5,'9165553272','Сотовый'),(4,7,'9165553272','Сотовый'),(5,8,'9119119191','Сотовый'),(6,9,'9129129129','Сотовый'),(7,10,'9139139139','Сотовый'),(8,11,'9149149149','Сотовый'),(9,12,'9999999999','Сотовый');
/*!40000 ALTER TABLE `PHONE_NUMBERS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `REF_ACHIEVEMENTS_TYPE`
--

DROP TABLE IF EXISTS `REF_ACHIEVEMENTS_TYPE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `REF_ACHIEVEMENTS_TYPE` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `CODE` varchar(10) NOT NULL COMMENT 'Наименование достижения',
  `DESCRIPTION` varchar(255) NOT NULL COMMENT 'Описание достижения',
  `PICTURE_PATH` varchar(255) DEFAULT NULL COMMENT 'Изображение достижения',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `NAME_UNIQUE` (`CODE`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `REF_ACHIEVEMENTS_TYPE`
--

LOCK TABLES `REF_ACHIEVEMENTS_TYPE` WRITE;
/*!40000 ALTER TABLE `REF_ACHIEVEMENTS_TYPE` DISABLE KEYS */;
INSERT INTO `REF_ACHIEVEMENTS_TYPE` VALUES (1,'ЛР','Личный рекорд',NULL),(2,'КР','Командный рекорд',NULL),(3,'ВКМР','Рекорд в мужской возрастной категории',NULL),(4,'ВКЖР','Рекорд в женской возрастной категории',NULL),(5,'ЛВРЗ','Лучшее время забега',NULL),(6,'П','Победитель',NULL);
/*!40000 ALTER TABLE `REF_ACHIEVEMENTS_TYPE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `REF_AGES_CATEGORY`
--

DROP TABLE IF EXISTS `REF_AGES_CATEGORY`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `REF_AGES_CATEGORY` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `CODE` varchar(7) NOT NULL COMMENT 'Наименование возрастной категории',
  `DESCRIPTION` varchar(200) NOT NULL COMMENT 'Описание возрастной категории',
  `AGE_FROM` tinyint NOT NULL COMMENT 'Диапазон возраста «от»',
  `AGE_TO` tinyint NOT NULL COMMENT 'Диапазон возраста «до»',
  `SEX` enum('Мужской','Женский') NOT NULL COMMENT 'Принадлежность категории к виду пола',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `NAME_UNIQUE` (`CODE`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `REF_AGES_CATEGORY`
--

LOCK TABLES `REF_AGES_CATEGORY` WRITE;
/*!40000 ALTER TABLE `REF_AGES_CATEGORY` DISABLE KEYS */;
INSERT INTO `REF_AGES_CATEGORY` VALUES (1,'M45-49','Мужская категория от 45 до 49 лет включительно',45,49,'Мужской'),(2,'W45-49','Женская категория от 45 до 49 лет включительно',45,49,'Женский'),(35,'M15-19','Мужская категория от 15 до 19 лет включительно',15,19,'Мужской'),(36,'M20-24','Мужская категория от 20 до 24 лет включительно',20,24,'Мужской'),(37,'M25-29','Мужская категория от 25 до 29 лет включительно',25,29,'Мужской'),(38,'M30-34','Мужская категория от 30 до 34 лет включительно',30,34,'Мужской'),(39,'M35-39','Мужская категория от 35 до 39 лет включительно',35,39,'Мужской'),(40,'M40-44','Мужская категория от 40 до 44 лет включительно',40,44,'Мужской'),(41,'M50-54','Мужская категория от 50 до 54 лет включительно',50,54,'Мужской'),(42,'M55-59','Мужская категория от 55 до 59 лет включительно',55,59,'Мужской'),(43,'M60-64','Мужская категория от 60 до 64 лет включительно',60,64,'Мужской'),(44,'M65-69','Мужская категория от 65 до 69 лет включительно',65,69,'Мужской'),(45,'M70-74','Мужская категория от 70 до 74 лет включительно',70,74,'Мужской'),(46,'M75-79','Мужская категория от 75 до 79 лет включительно',75,79,'Мужской'),(47,'M80-84','Мужская категория от 80 до 84 лет включительно',80,84,'Мужской'),(48,'M85-89','Мужская категория от 85 до 89 лет включительно',85,89,'Мужской'),(49,'M90-94','Мужская категория от 90 до 94 лет включительно',90,94,'Мужской'),(50,'M95-99','Мужская категория от 95 до 99 лет включительно',95,99,'Мужской'),(51,'W15-19','Женская категория от 15 до 19 лет включительно',15,19,'Женский'),(52,'W20-24','Женская категория от 20 до 24 лет включительно',20,24,'Женский'),(53,'W25-29','Женская категория от 25 до 29 лет включительно',25,29,'Женский'),(54,'W30-34','Женская категория от 30 до 34 лет включительно',30,34,'Женский'),(55,'W35-39','Женская категория от 35 до 39 лет включительно',35,39,'Женский'),(56,'W40-44','Женская категория от 40 до 44 лет включительно',40,44,'Женский'),(57,'W50-54','Женская категория от 50 до 54 лет включительно',50,54,'Женский'),(58,'W55-59','Женская категория от 55 до 59 лет включительно',55,59,'Женский'),(59,'W60-64','Женская категория от 60 до 64 лет включительно',60,64,'Женский'),(60,'W65-69','Женская категория от 65 до 69 лет включительно',65,69,'Женский'),(61,'W70-74','Женская категория от 70 до 74 лет включительно',70,74,'Женский'),(62,'W75-79','Женская категория от 75 до 79 лет включительно',75,79,'Женский'),(63,'W80-84','Женская категория от 80 до 84 лет включительно',80,84,'Женский'),(64,'W85-89','Женская категория от 85 до 89 лет включительно',85,89,'Женский'),(65,'W90-94','Женская категория от 90 до 94 лет включительно',90,94,'Женский'),(66,'W95-99','Женская категория от 95 до 99 лет включительно',95,99,'Женский');
/*!40000 ALTER TABLE `REF_AGES_CATEGORY` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `REF_CITIES`
--

DROP TABLE IF EXISTS `REF_CITIES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `REF_CITIES` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) NOT NULL COMMENT 'Наименование города',
  `REF_REGIONS_ID` int NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_REF_CITIES_REF_REGIONS_ID_idx` (`REF_REGIONS_ID`),
  CONSTRAINT `FK_REF_CITIES_REF_REGIONS_ID` FOREIGN KEY (`REF_REGIONS_ID`) REFERENCES `REF_REGIONS` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `REF_CITIES`
--

LOCK TABLES `REF_CITIES` WRITE;
/*!40000 ALTER TABLE `REF_CITIES` DISABLE KEYS */;
INSERT INTO `REF_CITIES` VALUES (1,'Москва',1),(2,'Пушкин',5),(3,'Санкт-Петербург',5),(4,'Мытищи',2),(5,'Балашиха',2),(6,'Ногинск',2);
/*!40000 ALTER TABLE `REF_CITIES` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `REF_COUNTRIES`
--

DROP TABLE IF EXISTS `REF_COUNTRIES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `REF_COUNTRIES` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) NOT NULL COMMENT 'Наименование страны',
  `CODE` varchar(3) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `NAME_UNIQUE` (`NAME`),
  UNIQUE KEY `CODE_UNIQUE` (`CODE`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `REF_COUNTRIES`
--

LOCK TABLES `REF_COUNTRIES` WRITE;
/*!40000 ALTER TABLE `REF_COUNTRIES` DISABLE KEYS */;
INSERT INTO `REF_COUNTRIES` VALUES (1,'Россия','RUS'),(2,'Беларусь','BY'),(3,'Казахстан','KZ'),(4,'Украина','UA');
/*!40000 ALTER TABLE `REF_COUNTRIES` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `REF_DISTRICTS`
--

DROP TABLE IF EXISTS `REF_DISTRICTS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `REF_DISTRICTS` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) NOT NULL COMMENT 'Наименование района',
  `REF_CITIES_ID` int NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_REF_DISTRICTS_REF_CITIES_ID_idx` (`REF_CITIES_ID`),
  CONSTRAINT `FK_REF_DISTRICTS_REF_CITIES_ID` FOREIGN KEY (`REF_CITIES_ID`) REFERENCES `REF_CITIES` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `REF_DISTRICTS`
--

LOCK TABLES `REF_DISTRICTS` WRITE;
/*!40000 ALTER TABLE `REF_DISTRICTS` DISABLE KEYS */;
INSERT INTO `REF_DISTRICTS` VALUES (1,'Тверской район, ЦАО',1),(2,'Тимирязевский район',1),(3,'Марьина Роща район',1),(4,'Пушкинский район',3),(5,'Калининский район',3),(6,'Головинский район',1),(7,'Северное Тушино',1),(8,'Богородский городской округ',6),(9,'Выборгский район',3),(10,'Левобережный район, САО',1),(11,'Балашиха',5),(12,'Балашиха городской округ',5),(13,'Мытищи',4);
/*!40000 ALTER TABLE `REF_DISTRICTS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `REF_EMAIL_SAMPLES`
--

DROP TABLE IF EXISTS `REF_EMAIL_SAMPLES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `REF_EMAIL_SAMPLES` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `NAME` varchar(45) NOT NULL COMMENT 'Наименование шаблона',
  `CONTENT` varchar(45) NOT NULL COMMENT 'Содержимое шаблона',
  `REF_SUBSCRIBE_TYPE_ID` int NOT NULL COMMENT 'Тип рассылки',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `SUBSCRIBE_TYPE_UNIQUE` (`REF_SUBSCRIBE_TYPE_ID`),
  UNIQUE KEY `NAME_UNIQUE` (`NAME`),
  CONSTRAINT `FK_REF_EMAIL_SAMPLES_REF_SUBSCRIBE_TYPE_ID` FOREIGN KEY (`REF_SUBSCRIBE_TYPE_ID`) REFERENCES `REF_SUBSCRIBE_TYPE` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `REF_EMAIL_SAMPLES`
--

LOCK TABLES `REF_EMAIL_SAMPLES` WRITE;
/*!40000 ALTER TABLE `REF_EMAIL_SAMPLES` DISABLE KEYS */;
INSERT INTO `REF_EMAIL_SAMPLES` VALUES (1,'RESULT','Вы пробежали с результатом:',3),(2,'TEAM INFO','На следующий забег № требуются волонтёры',1),(3,'VOLUNTEER INFO','Волонтёрам забега № прибыть ко времени',4);
/*!40000 ALTER TABLE `REF_EMAIL_SAMPLES` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `REF_IDENTITY_RUNSYSTEMS`
--

DROP TABLE IF EXISTS `REF_IDENTITY_RUNSYSTEMS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `REF_IDENTITY_RUNSYSTEMS` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `NAME` varchar(45) NOT NULL COMMENT 'Наименование системы ID',
  `DESCRIPTION` varchar(255) DEFAULT NULL COMMENT 'Описание системы',
  `PREFIX` varchar(45) NOT NULL COMMENT 'Префикс кода для системы',
  `ACTIVE` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'Флаг возможности использования системы',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `NAME_UNIQUE` (`NAME`),
  UNIQUE KEY `PREFIX_UNIQUE` (`PREFIX`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `REF_IDENTITY_RUNSYSTEMS`
--

LOCK TABLES `REF_IDENTITY_RUNSYSTEMS` WRITE;
/*!40000 ALTER TABLE `REF_IDENTITY_RUNSYSTEMS` DISABLE KEYS */;
INSERT INTO `REF_IDENTITY_RUNSYSTEMS` VALUES (1,'RUNNER LITE','Встроенная система идентификации бегуна','RRL',1),(2,'PARK RUN','Система \"parkrun\"','A',1);
/*!40000 ALTER TABLE `REF_IDENTITY_RUNSYSTEMS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `REF_MANAGER_TYPE`
--

DROP TABLE IF EXISTS `REF_MANAGER_TYPE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `REF_MANAGER_TYPE` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) NOT NULL COMMENT 'Наименование позиции',
  `DESCRIPTION` varchar(512) NOT NULL COMMENT 'Описание позиции',
  `ACTIVE` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `NAME_UNIQUE` (`NAME`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `REF_MANAGER_TYPE`
--

LOCK TABLES `REF_MANAGER_TYPE` WRITE;
/*!40000 ALTER TABLE `REF_MANAGER_TYPE` DISABLE KEYS */;
INSERT INTO `REF_MANAGER_TYPE` VALUES (1,'Директор команды','Выполняет ',1),(2,'Руководитель забегов','Организует команду волонтёров',1);
/*!40000 ALTER TABLE `REF_MANAGER_TYPE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `REF_REGIONS`
--

DROP TABLE IF EXISTS `REF_REGIONS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `REF_REGIONS` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `NAME` varchar(512) NOT NULL COMMENT 'Наименование региона',
  `REF_COUNTRY_ID` int NOT NULL COMMENT 'Страна',
  `CODE` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `CODE_UNIQUE` (`CODE`),
  UNIQUE KEY `NAME_UNIQUE` (`NAME`),
  KEY `FK_REF_REGIONS_COUNTRY_idx` (`REF_COUNTRY_ID`),
  CONSTRAINT `FK_REF_REGIONS_REF_COUNTRY_ID` FOREIGN KEY (`REF_COUNTRY_ID`) REFERENCES `REF_COUNTRIES` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `REF_REGIONS`
--

LOCK TABLES `REF_REGIONS` WRITE;
/*!40000 ALTER TABLE `REF_REGIONS` DISABLE KEYS */;
INSERT INTO `REF_REGIONS` VALUES (1,'Москва',1,'MSK'),(2,'Московская область',1,'MO'),(3,'Тверская область',1,'TVO'),(4,'Тамбовская область',1,'TMB'),(5,'Санкт-Петербург',1,'SPB'),(6,'Ленинградская область',1,'SPBO');
/*!40000 ALTER TABLE `REF_REGIONS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `REF_SUBSCRIBE_TYPE`
--

DROP TABLE IF EXISTS `REF_SUBSCRIBE_TYPE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `REF_SUBSCRIBE_TYPE` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `CODE` varchar(45) NOT NULL COMMENT 'Наименование подписки',
  `DESCRIPTION` varchar(512) NOT NULL COMMENT 'Описание тематики рассылки',
  `ACTIVE` tinyint(1) NOT NULL DEFAULT '1' COMMENT 'Статус работы рассылки',
  `SEC_GROUPS_ID` int NOT NULL COMMENT 'Группа, для которой доступна рассылка',
  `TEAMS_ID` int NOT NULL COMMENT 'Команда, для которой подписка доступна',
  PRIMARY KEY (`ID`),
  KEY `FK_REF_SUBSCRIBE_TYPE_SEC_GROUPS_ID_idx` (`SEC_GROUPS_ID`),
  KEY `FK_REF_SUBSCRIBE_TYPE_TEAMS_ID_idx` (`TEAMS_ID`),
  CONSTRAINT `FK_REF_SUBSCRIBE_TYPE_SEC_GROUPS_ID` FOREIGN KEY (`SEC_GROUPS_ID`) REFERENCES `SEC_GROUPS` (`ID`),
  CONSTRAINT `FK_REF_SUBSCRIBE_TYPE_TEAMS_ID` FOREIGN KEY (`TEAMS_ID`) REFERENCES `TEAMS` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `REF_SUBSCRIBE_TYPE`
--

LOCK TABLES `REF_SUBSCRIBE_TYPE` WRITE;
/*!40000 ALTER TABLE `REF_SUBSCRIBE_TYPE` DISABLE KEYS */;
INSERT INTO `REF_SUBSCRIBE_TYPE` VALUES (1,'УВЕДОМЛЕНИЕ КОМАНДЫ','Уведомление о событиях команды для всех бегунов команды',1,3,1),(2,'УВЕДОМЛЕНИЕ МЕНЕДЖЕРА','Уведомление о событиях команды для менеджмента команды',1,4,1),(3,'РЕЗУЛЬТАТ','Письмо с результатами забега',1,3,1),(4,'УВЕДОМЛЕНИЕ ВОЛОНТЁРА','Уведомление для волонтёра',1,3,1);
/*!40000 ALTER TABLE `REF_SUBSCRIBE_TYPE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `REF_VOLUNTEERS_POSITION`
--

DROP TABLE IF EXISTS `REF_VOLUNTEERS_POSITION`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `REF_VOLUNTEERS_POSITION` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) NOT NULL COMMENT 'Наименование позиции волонтёра',
  `DESCRIPTION` varchar(512) NOT NULL COMMENT 'Описание позиции волонтёра',
  `ACTIVE` tinyint(1) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `NAME_UNIQUE` (`NAME`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `REF_VOLUNTEERS_POSITION`
--

LOCK TABLES `REF_VOLUNTEERS_POSITION` WRITE;
/*!40000 ALTER TABLE `REF_VOLUNTEERS_POSITION` DISABLE KEYS */;
INSERT INTO `REF_VOLUNTEERS_POSITION` VALUES (1,'Секундомер','Замеряет время бегуна',1),(2,'Сканер','Сканирует карточки',1),(3,'Раздача карточек позиций','Раздаёт карточки на финише',1),(4,'Маршал','Помогает бегунам на трассе',1),(5,'Фотограф','Делает фоточки',1),(6,'Замыкающий','Не даёт потерятся черепашкам',1),(7,'Разминка','Проводит разминку перед стартом',1),(8,'Разметка трассы','Размечает дорожку',1);
/*!40000 ALTER TABLE `REF_VOLUNTEERS_POSITION` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `RUNNER_COUNT`
--

DROP TABLE IF EXISTS `RUNNER_COUNT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `RUNNER_COUNT` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `SEC_USERS_ID` int NOT NULL COMMENT 'Бегун',
  `TEAMS_RUNNING_COUNT_ID` int NOT NULL COMMENT 'Номер забега команды',
  `STATUS` int DEFAULT NULL COMMENT 'Статус отношения бегуна к забегу (null - записан на забег, 0 - участие в качестве бегуна отменено; 1 - забег выполнен)',
  PRIMARY KEY (`ID`),
  KEY `FK_RUNNER_COUNT_SEC_USERS_ID_idx` (`SEC_USERS_ID`),
  KEY `FK_RUNNER_COUNT_TEAMS_RUNNING_COUNT_ID_idx` (`TEAMS_RUNNING_COUNT_ID`),
  CONSTRAINT `FK_RUNNER_COUNT_SEC_USERS_ID` FOREIGN KEY (`SEC_USERS_ID`) REFERENCES `SEC_USERS` (`ID`),
  CONSTRAINT `FK_RUNNER_COUNT_TEAMS_RUNNING_COUNT_ID` FOREIGN KEY (`TEAMS_RUNNING_COUNT_ID`) REFERENCES `TEAMS_RUNNING_COUNT` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `RUNNER_COUNT`
--

LOCK TABLES `RUNNER_COUNT` WRITE;
/*!40000 ALTER TABLE `RUNNER_COUNT` DISABLE KEYS */;
INSERT INTO `RUNNER_COUNT` VALUES (1,1,1,1),(7,7,1,1),(8,7,3,1),(9,7,9,1),(10,8,9,1),(11,8,2,1),(12,9,2,1),(13,9,4,1),(14,9,9,1),(15,10,2,1),(16,10,4,1),(17,11,1,1),(18,11,3,1),(19,7,12,NULL),(20,7,13,NULL),(21,7,14,0),(22,10,15,0),(23,9,15,0);
/*!40000 ALTER TABLE `RUNNER_COUNT` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `running_results`
--

DROP TABLE IF EXISTS `running_results`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `running_results` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `SEC_USERS_ID` int DEFAULT NULL COMMENT 'Бегун',
  `RESULT` int NOT NULL COMMENT 'Результат забега в секундах',
  `TEAMS_RUNNING_COUNT_ID` int NOT NULL COMMENT 'Номер забега',
  `FINISH_PLACE` int NOT NULL COMMENT 'Номер карточки места на финише',
  PRIMARY KEY (`ID`),
  KEY `FK_RUNNING_RESULTS_SEC_USERS_ID_idx` (`SEC_USERS_ID`),
  KEY `FK_RUNNING_RESULTS_TEAMS_RUNNING_COUNT_ID_idx` (`TEAMS_RUNNING_COUNT_ID`),
  CONSTRAINT `FK_RUNNING_RESULTS_SEC_USERS_ID` FOREIGN KEY (`SEC_USERS_ID`) REFERENCES `SEC_USERS` (`ID`),
  CONSTRAINT `FK_RUNNING_RESULTS_TEAMS_RUNNING_COUNT_ID` FOREIGN KEY (`TEAMS_RUNNING_COUNT_ID`) REFERENCES `TEAMS_RUNNING_COUNT` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `running_results`
--

LOCK TABLES `running_results` WRITE;
/*!40000 ALTER TABLE `running_results` DISABLE KEYS */;
INSERT INTO `running_results` VALUES (1,1,1740,1,10),(2,3,900,1,1),(3,4,1501,1,11),(4,7,1555,1,3),(5,7,1445,3,4),(6,7,1495,9,2),(7,8,1670,2,15),(8,8,1500,9,12),(9,9,1450,2,1),(10,9,1440,4,2),(11,9,1490,9,5),(12,10,1640,2,10),(13,10,1590,4,5),(14,11,1446,1,8),(15,11,1460,3,6),(16,NULL,1404,12,1),(17,NULL,1499,12,2),(18,NULL,1580,12,3),(19,NULL,1670,12,4),(20,NULL,1499,15,1),(21,NULL,1634,15,2),(22,NULL,1780,15,3);
/*!40000 ALTER TABLE `running_results` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SEC_GROUPS`
--

DROP TABLE IF EXISTS `SEC_GROUPS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `SEC_GROUPS` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) NOT NULL,
  `ACTIVE` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'Признак доступности группы',
  `DESCRIPTION` varchar(512) DEFAULT NULL COMMENT 'Описание группы',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `name_UNIQUE` (`NAME`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SEC_GROUPS`
--

LOCK TABLES `SEC_GROUPS` WRITE;
/*!40000 ALTER TABLE `SEC_GROUPS` DISABLE KEYS */;
INSERT INTO `SEC_GROUPS` VALUES (1,'Global_Admin',1,'Администратор глобальный'),(2,'Admin',1,'Администратор команды'),(3,'Runner',1,'Бегун'),(4,'Manager',1,'Менеджер команды'),(5,'Director',1,'Директор команды'),(6,'Rundirector',1,'Рандиректор');
/*!40000 ALTER TABLE `SEC_GROUPS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SEC_IDENTITY`
--

DROP TABLE IF EXISTS `SEC_IDENTITY`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `SEC_IDENTITY` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `SEC_USERS_ID` int NOT NULL COMMENT 'Бегун',
  `SEC_IDENTITY_CODE_ID` int NOT NULL COMMENT 'Тип ID бегуна',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `SEC_USERS_ID_UNIQUE` (`SEC_USERS_ID`),
  KEY `FK_SEC_IDENTITY_SEC_USERS_ID_idx` (`SEC_USERS_ID`),
  KEY `FK_SEC_IDENTITY_REF_IDENTITY_TYPE_ID_idx` (`SEC_IDENTITY_CODE_ID`),
  CONSTRAINT `FK_SEC_IDENTITY_SEC_IDENTITY_CODE_ID` FOREIGN KEY (`SEC_IDENTITY_CODE_ID`) REFERENCES `SEC_IDENTITY_CODE` (`ID`),
  CONSTRAINT `FK_SEC_IDENTITY_SEC_USERS_ID` FOREIGN KEY (`SEC_USERS_ID`) REFERENCES `SEC_USERS` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SEC_IDENTITY`
--

LOCK TABLES `SEC_IDENTITY` WRITE;
/*!40000 ALTER TABLE `SEC_IDENTITY` DISABLE KEYS */;
INSERT INTO `SEC_IDENTITY` VALUES (1,1,1),(2,2,2);
/*!40000 ALTER TABLE `SEC_IDENTITY` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SEC_IDENTITY_CODE`
--

DROP TABLE IF EXISTS `SEC_IDENTITY_CODE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `SEC_IDENTITY_CODE` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `CODE` varchar(10) NOT NULL COMMENT 'ID бегуна',
  `ACTIVE` varchar(45) NOT NULL DEFAULT '0' COMMENT 'Флаг активной карты',
  `REF_IDENTITY_RUNSYSTEMS_ID` int NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `IDENTITY_CODE_UNIQUE` (`CODE`),
  KEY `FK_REF_IDENTITY_TYPE_REF_IDETITY_RUNSYSTEM_ID_idx` (`REF_IDENTITY_RUNSYSTEMS_ID`),
  CONSTRAINT `FK_SEC_IDENTITY_CODE_REF_IDENTITY_RUNSYSTEMS_ID` FOREIGN KEY (`REF_IDENTITY_RUNSYSTEMS_ID`) REFERENCES `REF_IDENTITY_RUNSYSTEMS` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SEC_IDENTITY_CODE`
--

LOCK TABLES `SEC_IDENTITY_CODE` WRITE;
/*!40000 ALTER TABLE `SEC_IDENTITY_CODE` DISABLE KEYS */;
INSERT INTO `SEC_IDENTITY_CODE` VALUES (1,'RRL3456789','0',1),(2,'A3063044','0',2);
/*!40000 ALTER TABLE `SEC_IDENTITY_CODE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SEC_USERGROUPS_MEMBER`
--

DROP TABLE IF EXISTS `SEC_USERGROUPS_MEMBER`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `SEC_USERGROUPS_MEMBER` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `SEC_USERS_ID` int NOT NULL COMMENT 'Бегун',
  `SEC_GROUPS_ID` int NOT NULL COMMENT 'Участие в группе',
  `SEC_TEAMS_ID` int NOT NULL COMMENT 'Участие в команде',
  PRIMARY KEY (`ID`),
  KEY `FK_SEC_USERGROUPS_MEMBER_SEC_USERS_idx` (`SEC_USERS_ID`),
  KEY `FK_SEC_USERGROUPS_MEMBER_TEAMS_ID_idx` (`SEC_TEAMS_ID`),
  KEY `FK_SEC_USERGROUPS_MEMBER_GROUPS_ID_idx` (`SEC_GROUPS_ID`),
  CONSTRAINT `FK_SEC_USERGROUPS_MEMBER_GROUPS_ID` FOREIGN KEY (`SEC_GROUPS_ID`) REFERENCES `SEC_GROUPS` (`ID`),
  CONSTRAINT `FK_SEC_USERGROUPS_MEMBER_SEC_USERS` FOREIGN KEY (`SEC_USERS_ID`) REFERENCES `SEC_USERS` (`ID`),
  CONSTRAINT `FK_SEC_USERGROUPS_MEMBER_TEAMS_ID` FOREIGN KEY (`SEC_TEAMS_ID`) REFERENCES `TEAMS` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SEC_USERGROUPS_MEMBER`
--

LOCK TABLES `SEC_USERGROUPS_MEMBER` WRITE;
/*!40000 ALTER TABLE `SEC_USERGROUPS_MEMBER` DISABLE KEYS */;
INSERT INTO `SEC_USERGROUPS_MEMBER` VALUES (1,1,3,1),(2,2,5,3),(3,2,6,3),(4,5,3,1),(5,7,3,1),(6,8,3,2),(7,9,3,2),(8,10,3,2),(9,11,3,1),(10,12,3,4),(11,7,6,1),(12,9,6,2),(13,2,3,3);
/*!40000 ALTER TABLE `SEC_USERGROUPS_MEMBER` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SEC_USERS`
--

DROP TABLE IF EXISTS `SEC_USERS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `SEC_USERS` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `LOGIN_EMAIL` varchar(255) NOT NULL COMMENT 'Логин (используется e-mail)',
  `FULLNAME` varchar(255) NOT NULL COMMENT 'Полное имя (ФИО)',
  `PASSWORD` varchar(100) NOT NULL COMMENT 'Пароль',
  `NICK_NAME` varchar(100) DEFAULT NULL COMMENT 'Альтернативное короткое имя, псевдоним',
  `ACTIVE` tinyint(1) NOT NULL DEFAULT '1' COMMENT 'Флаг активности пользователя',
  `USE_NICK` tinyint(1) DEFAULT '0' COMMENT 'Флаг отображения ника в таблице с результатами',
  `TEAMS_ID` int NOT NULL COMMENT 'ID домашней команды',
  `BIRTHDAY` datetime NOT NULL COMMENT 'Возраст',
  `SEX` enum('Мужской','Женский') NOT NULL COMMENT 'Пол пользователя',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `NAME_UNIQUE` (`LOGIN_EMAIL`),
  UNIQUE KEY `NICK_NAME_UNIQUE` (`NICK_NAME`),
  KEY `FK_SEC_USERS_TEAMS_ID_idx` (`TEAMS_ID`),
  CONSTRAINT `FK_SEC_USERS_TEAMS_ID` FOREIGN KEY (`TEAMS_ID`) REFERENCES `TEAMS` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SEC_USERS`
--

LOCK TABLES `SEC_USERS` WRITE;
/*!40000 ALTER TABLE `SEC_USERS` DISABLE KEYS */;
INSERT INTO `SEC_USERS` VALUES (1,'new_runner@runnerlite.ru','Новый Совсем Бегун','$2a$10$mgq5U4dBS80yZ3BWeVkOeOwQT2mgdiwHNNG.ftlZYIoiXwX/87v.u','NEW Runner',1,1,1,'2001-01-20 01:00:00','Мужской'),(2,'director@runnerlite.ru','Иванов Иван Иваныч','$2a$10$mgq5U4dBS80yZ3BWeVkOeOwQT2mgdiwHNNG.ftlZYIoiXwX/87v.u','direct',1,1,3,'1978-03-13 00:00:00','Мужской'),(3,'subzero@runnerlite.ru','Попова Екатерина Викторовна','$2a$10$mgq5U4dBS80yZ3BWeVkOeOwQT2mgdiwHNNG.ftlZYIoiXwX/87v.u','wewewe',1,1,3,'1999-05-18 00:00:00','Женский'),(4,'wader@runnerlite.ru','Дарт Вейдер','$2a$10$mgq5U4dBS80yZ3BWeVkOeOwQT2mgdiwHNNG.ftlZYIoiXwX/87v.u','DarthWader',1,1,3,'1222-02-24 00:00:00','Мужской'),(5,'new_run@new.run','Ivan','$2a$10$mgq5U4dBS80yZ3BWeVkOeOwQT2mgdiwHNNG.ftlZYIoiXwX/87v.u','new_run',1,0,1,'1975-11-01 00:00:00','Мужской'),(7,'ivan.tersintsev@gmail.com','Терсинцев Иван Анатольевич','$2a$10$mgq5U4dBS80yZ3BWeVkOeOwQT2mgdiwHNNG.ftlZYIoiXwX/87v.u','iTIA',1,1,1,'1975-11-25 00:00:00','Мужской'),(8,'danilsibgatyllin@gmail.com','Сибгатуллин Даниль','$2a$10$x4F5LECTIMvjxKv/9Zn7FuQ1vQJF1OmKJxYG6EziHEq1v8IXHjoT.','Danil',1,0,2,'2000-01-01 00:00:00','Мужской'),(9,'pkhanyn@gmail.com','Ханин Павел','$2a$10$Yl2/obrug4x9d.bvQY25aelZMwyndkf.l056sVOb0XzKQ/QK68m9S','pkhanyn',1,1,2,'1980-01-01 00:00:00','Мужской'),(10,'miromaxel@yandex.ru','Таджибаев Максим','$2a$10$n3Y.mVbI5/VYw1/FBXUmue6kaOys4UT75kIuRpvz5BCkNyU.inHkW','miromaxel',1,0,2,'1999-01-01 00:00:00','Мужской'),(11,'ptichkinm@mail.ru','Михаил Воробьев','$2a$10$eA28OJ5ieHMTmKAl630fYuFOPRf2hRMSRyNj5KCJ4o4dASq.Ld6AG','ptichkinm',1,0,1,'2000-01-01 00:00:00','Мужской'),(12,'findbook@mail.ru','Капустина Светлана','$2a$10$b6sgt249Q/aU956HLu4uxuktZ7J6yigRUrJMyLVc9T5Lhl7ynBtL6','kapusta',1,0,4,'2001-06-01 00:00:00','Женский');
/*!40000 ALTER TABLE `SEC_USERS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teams`
--

DROP TABLE IF EXISTS `teams`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `teams` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) NOT NULL COMMENT 'Наименование беговой команды',
  `DESCRIPTION` varchar(512) NOT NULL COMMENT 'Описание беговой команды',
  `GEO_LAT` float(10,6) DEFAULT NULL COMMENT 'Координата «широта» геолокации места сбора',
  `GEO_LNG` float(10,6) DEFAULT NULL COMMENT 'Координата «долгота» геолокации места сбора',
  `GEO_DESCRIPTION` varchar(255) DEFAULT NULL COMMENT 'Описание геолокации места сбора',
  `ACTIVE` tinyint(1) NOT NULL COMMENT 'Флаг доступности команды',
  `LOCATION` int DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `NAME_UNIQUE` (`NAME`),
  CONSTRAINT `fk_teams_location` FOREIGN KEY (`ID`) REFERENCES `locations` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teams`
--

LOCK TABLES `teams` WRITE;
/*!40000 ALTER TABLE `teams` DISABLE KEYS */;
INSERT INTO `teams` VALUES (1,'ТИМКА','Опытная дача Тимирязевского парка',55.817986,37.556271,'Зона старта располагается недалеко от входа в парк со стороны ул. Вучетича, рядом с Лесной опытной дачей (дом 46c1)',1,1),(2,'ДРУЖБА','Россия, Москва, Флотская 1А',55.853680,37.486137,'Вход со стороны ул. Флотская 1А',1,3),(3,'BGO.RUN','Ногинский центральный городской парк культуры и отдыха',55.849098,38.429501,'Арочная аллея',1,2),(4,'СОСНОВКА','Парк Сосновка',60.018299,30.340401,'Стадион',1,4),(5,'Нижний Парк','Сбор у Колонистского пруда',59.712299,30.420601,'У пруда',1,5),(6,'Mytischi Be Good','Лавочки у зеленого моста через Яузу',55.898399,37.713001,'напротив школы №10',1,7),(7,'Балашиха Заречная','Вход в парк Заречная',55.814602,37.960300,'Заречная',1,6);
/*!40000 ALTER TABLE `teams` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TEAMS_MANAGEMENT`
--

DROP TABLE IF EXISTS `TEAMS_MANAGEMENT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `TEAMS_MANAGEMENT` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `REF_MANAGER_TYPE_ID` int NOT NULL COMMENT 'Наименование руководителя',
  `SEC_USERS_ID` int NOT NULL COMMENT 'Пользователь системы',
  `TEAMS_ID` int NOT NULL COMMENT 'Беговая команда',
  PRIMARY KEY (`ID`),
  KEY `FK_TEAMS_MANAGEMENT_TEAMS_ID_idx` (`TEAMS_ID`),
  KEY `FK_TEAMS_MANAGEMENT_SEC_USERS_ID_idx` (`SEC_USERS_ID`),
  KEY `FK_TEAMS_MANAGEMENT_REF_MANAGER_TYPE_ID_idx` (`REF_MANAGER_TYPE_ID`),
  CONSTRAINT `FK_TEAMS_MANAGEMENT_REF_MANAGER_TYPE_ID` FOREIGN KEY (`REF_MANAGER_TYPE_ID`) REFERENCES `REF_MANAGER_TYPE` (`ID`),
  CONSTRAINT `FK_TEAMS_MANAGEMENT_SEC_USERS_ID` FOREIGN KEY (`SEC_USERS_ID`) REFERENCES `SEC_USERS` (`ID`),
  CONSTRAINT `FK_TEAMS_MANAGEMENT_TEAMS_ID` FOREIGN KEY (`TEAMS_ID`) REFERENCES `TEAMS` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TEAMS_MANAGEMENT`
--

LOCK TABLES `TEAMS_MANAGEMENT` WRITE;
/*!40000 ALTER TABLE `TEAMS_MANAGEMENT` DISABLE KEYS */;
INSERT INTO `TEAMS_MANAGEMENT` VALUES (1,1,2,1),(2,1,2,2),(3,2,3,1);
/*!40000 ALTER TABLE `TEAMS_MANAGEMENT` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TEAMS_RUNNING_COUNT`
--

DROP TABLE IF EXISTS `TEAMS_RUNNING_COUNT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `TEAMS_RUNNING_COUNT` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `TEAMS_ID` int NOT NULL COMMENT 'Беговая команда',
  `RUNNING_DATE` datetime NOT NULL COMMENT 'Дата забега',
  `NUMBER` int NOT NULL COMMENT 'Номер забега',
  `STATUS` enum('Запланирован','Выполнен','Отменён','Перенесён') NOT NULL DEFAULT 'Запланирован',
  PRIMARY KEY (`ID`),
  KEY `FK_TEAMS_RUNNING_COUNT_TEAMS_ID_idx` (`TEAMS_ID`),
  CONSTRAINT `FK_TEAMS_RUNNING_COUNT_TEAMS_ID` FOREIGN KEY (`TEAMS_ID`) REFERENCES `TEAMS` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TEAMS_RUNNING_COUNT`
--

LOCK TABLES `TEAMS_RUNNING_COUNT` WRITE;
/*!40000 ALTER TABLE `TEAMS_RUNNING_COUNT` DISABLE KEYS */;
INSERT INTO `TEAMS_RUNNING_COUNT` VALUES (1,1,'2022-04-09 00:00:00',25,'Выполнен'),(2,2,'2022-04-16 00:00:00',11,'Выполнен'),(3,1,'2022-04-23 00:00:00',26,'Перенесён'),(4,2,'2022-04-23 00:00:00',12,'Выполнен'),(5,1,'2022-04-16 00:00:00',26,'Выполнен'),(6,3,'2022-04-09 00:00:00',8,'Перенесён'),(7,3,'2022-04-16 00:00:00',8,'Выполнен'),(8,3,'2022-04-23 00:00:00',9,'Выполнен'),(9,1,'2022-05-28 00:00:00',28,'Выполнен'),(10,3,'2022-05-28 00:00:00',10,'Выполнен'),(11,2,'2022-05-28 00:00:00',13,'Отменён'),(12,1,'2022-06-11 00:00:00',29,'Запланирован'),(13,1,'2022-06-18 00:00:00',30,'Запланирован'),(14,1,'2022-06-25 00:00:00',31,'Запланирован'),(15,2,'2022-06-11 00:00:00',14,'Запланирован'),(16,2,'2022-06-18 00:00:00',15,'Запланирован'),(17,2,'2022-06-25 00:00:00',16,'Перенесён'),(18,4,'2022-06-11 00:00:00',1,'Запланирован'),(19,4,'2022-06-18 00:00:00',2,'Запланирован'),(20,3,'2022-06-11 00:00:00',11,'Отменён'),(21,3,'2022-06-18 00:00:00',12,'Запланирован'),(22,3,'2022-06-25 00:00:00',13,'Перенесён');
/*!40000 ALTER TABLE `TEAMS_RUNNING_COUNT` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TEAMS_VOLUNTEERS`
--

DROP TABLE IF EXISTS `TEAMS_VOLUNTEERS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `TEAMS_VOLUNTEERS` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `TEAMS_RUNNING_COUNT_ID` int NOT NULL COMMENT 'Номер забега',
  `REF_VOLUNTEERS_POSITION_ID` int NOT NULL COMMENT 'Позиция волонтёра',
  `NEED_VOLUNTEER_QTY` int NOT NULL DEFAULT '1' COMMENT 'Требуемое количество волонтёров',
  `MIN_VOLUNTEER_QTY` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`),
  KEY `FK_TEAMS_VOLONTEERS_TEAMS_RUNNING_COUNT_ID_idx` (`TEAMS_RUNNING_COUNT_ID`),
  KEY `FK_TEAMS_VOLONTEERS_REF_VOLONTEERS_POSITION_ID_idx` (`REF_VOLUNTEERS_POSITION_ID`),
  CONSTRAINT `FK_TEAMS_VOLUNTEERS_REF_VOLUNTEERS_POSITION_ID` FOREIGN KEY (`REF_VOLUNTEERS_POSITION_ID`) REFERENCES `REF_VOLUNTEERS_POSITION` (`ID`),
  CONSTRAINT `FK_TEAMS_VOLUNTEERS_TEAMS_RUNNING_COUNT_ID` FOREIGN KEY (`TEAMS_RUNNING_COUNT_ID`) REFERENCES `TEAMS_RUNNING_COUNT` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TEAMS_VOLUNTEERS`
--

LOCK TABLES `TEAMS_VOLUNTEERS` WRITE;
/*!40000 ALTER TABLE `TEAMS_VOLUNTEERS` DISABLE KEYS */;
INSERT INTO `TEAMS_VOLUNTEERS` VALUES (1,1,1,2,1),(2,1,2,1,1),(3,1,3,1,0),(4,1,5,2,0),(5,1,6,1,1),(6,1,7,1,0),(7,1,8,2,1),(8,2,1,1,1),(9,2,6,1,1),(10,7,1,1,1),(11,7,6,1,1),(12,7,2,1,1),(13,4,1,2,2),(14,9,1,2,2),(15,9,2,1,1),(16,9,3,1,1),(17,9,4,1,1),(18,9,5,1,1),(19,9,6,1,1),(20,9,7,1,1),(21,9,8,1,1),(22,5,1,1,1),(23,5,6,1,1),(24,5,7,1,1),(25,5,8,1,1),(26,5,2,1,1),(27,12,1,2,1),(28,12,2,1,1),(29,12,6,1,1),(30,12,3,1,0),(31,12,5,2,0),(32,12,7,1,0),(33,12,8,2,1),(34,15,1,1,1),(35,15,6,1,1),(36,15,2,1,1),(37,15,3,1,1),(38,15,7,1,1),(39,13,1,2,2),(40,13,2,1,1),(41,13,3,2,1),(42,13,4,2,0),(43,13,5,2,1),(44,13,6,1,1),(45,13,7,1,1),(46,13,8,1,1),(47,14,1,2,1),(48,14,6,1,1),(49,14,8,1,1),(50,16,1,2,2),(51,16,6,1,1),(52,16,2,1,1),(53,16,3,1,1),(54,16,7,1,1),(55,16,8,1,1);
/*!40000 ALTER TABLE `TEAMS_VOLUNTEERS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TEAMS_VOLUNTEERS_TEMPLATE`
--

DROP TABLE IF EXISTS `TEAMS_VOLUNTEERS_TEMPLATE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `TEAMS_VOLUNTEERS_TEMPLATE` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `TEAMS_ID` int NOT NULL COMMENT 'Беговая команда',
  `REF_VOLUNTEERS_POSITION_ID` int NOT NULL COMMENT 'Тип позиции волонтёра',
  `QTY` int NOT NULL COMMENT 'Количество человек на одну позицию волонтёра',
  `SURELY_QTY` int DEFAULT NULL COMMENT 'Флаг обязательной позиции (null/0 - не обязательная позиция; 1 - обязательная позиция без совмещения; 2 - обязательная позиция с возможным совмещением)',
  PRIMARY KEY (`ID`),
  KEY `fk_TEAMS_VOLUNTEERS_TEMPLATE_1_idx` (`TEAMS_ID`),
  KEY `fk_TEAMS_VOLUNTEERS_TEMPLATE_2_idx` (`REF_VOLUNTEERS_POSITION_ID`),
  CONSTRAINT `fk_TEAMS_VOLUNTEERS_TEMPLATE_1` FOREIGN KEY (`TEAMS_ID`) REFERENCES `TEAMS` (`ID`),
  CONSTRAINT `fk_TEAMS_VOLUNTEERS_TEMPLATE_2` FOREIGN KEY (`REF_VOLUNTEERS_POSITION_ID`) REFERENCES `REF_VOLUNTEERS_POSITION` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TEAMS_VOLUNTEERS_TEMPLATE`
--

LOCK TABLES `TEAMS_VOLUNTEERS_TEMPLATE` WRITE;
/*!40000 ALTER TABLE `TEAMS_VOLUNTEERS_TEMPLATE` DISABLE KEYS */;
INSERT INTO `TEAMS_VOLUNTEERS_TEMPLATE` VALUES (1,1,1,2,1),(2,1,2,1,1),(3,1,3,1,2),(4,1,4,1,NULL),(5,1,5,1,NULL),(6,1,6,1,2),(7,1,7,1,2),(8,1,8,1,2),(9,2,1,1,1),(10,2,2,1,1),(11,2,3,1,2),(12,2,4,1,2),(13,2,5,1,NULL),(14,2,6,1,2),(15,2,7,1,NULL),(16,2,8,1,2),(17,3,1,1,1),(18,3,2,1,1),(19,3,3,1,NULL),(20,3,4,1,NULL),(21,3,5,1,1),(22,3,6,1,2),(23,3,7,1,NULL),(24,3,8,1,2),(25,4,1,1,1);
/*!40000 ALTER TABLE `TEAMS_VOLUNTEERS_TEMPLATE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `VOLUNTEERS`
--

DROP TABLE IF EXISTS `VOLUNTEERS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `VOLUNTEERS` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `SEC_USERS_ID` int NOT NULL COMMENT 'Бегун',
  `STATUS` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'Статус запроса (0 - запрос /1 - принято/ 2 - отказано)',
  `REF_VOLUNTEERS_POSITION_ID` int NOT NULL COMMENT 'Тип позиции волонтёра',
  `TEAMS_RUNNING_COUNT_ID` int NOT NULL COMMENT 'Номер забега, на который подана заявка для волонтёрства',
  PRIMARY KEY (`ID`),
  KEY `FK_VOLUNTEERS_SEC_USERS_ID_idx` (`SEC_USERS_ID`),
  KEY `FK_VOLUNTEERS_TEAMS_RUNNING_COUNT_ID_idx` (`TEAMS_RUNNING_COUNT_ID`),
  KEY `FK_VOLUNTEERS_REF_VOLUNTEERS_POSITION_ID_idx` (`REF_VOLUNTEERS_POSITION_ID`),
  CONSTRAINT `FK_VOLUNTEERS_REF_VOLUNTEERS_POSITION_ID` FOREIGN KEY (`REF_VOLUNTEERS_POSITION_ID`) REFERENCES `REF_VOLUNTEERS_POSITION` (`ID`),
  CONSTRAINT `FK_VOLUNTEERS_SEC_USERS_ID` FOREIGN KEY (`SEC_USERS_ID`) REFERENCES `SEC_USERS` (`ID`),
  CONSTRAINT `FK_VOLUNTEERS_TEAMS_RUNNING_COUNT_ID` FOREIGN KEY (`TEAMS_RUNNING_COUNT_ID`) REFERENCES `TEAMS_RUNNING_COUNT` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `VOLUNTEERS`
--

LOCK TABLES `VOLUNTEERS` WRITE;
/*!40000 ALTER TABLE `VOLUNTEERS` DISABLE KEYS */;
INSERT INTO `VOLUNTEERS` VALUES (1,1,0,6,1),(2,4,0,4,1),(3,3,1,6,7),(11,7,0,1,14),(12,9,0,6,15),(13,10,0,1,15);
/*!40000 ALTER TABLE `VOLUNTEERS` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-11 11:26:14
