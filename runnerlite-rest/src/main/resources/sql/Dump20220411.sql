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
  `SEC_USERS_ID` int NOT NULL COMMENT 'Бегун',
  `REF_ACHIEVEMENTS_TYPE_ID` int NOT NULL COMMENT 'ID достижения',
  `RUNNING_RESULTS_ID` int NOT NULL COMMENT 'Результат забега',
  PRIMARY KEY (`ID`),
  KEY `FK_ACHIEVEMENTS_SEC_USERS_ID_idx` (`SEC_USERS_ID`),
  KEY `FK_ACHIEVEMENTS_REF_ACHIEVEMENTS_TYPE_ID_idx` (`REF_ACHIEVEMENTS_TYPE_ID`),
  KEY `FK_ACHIEVEMENTS_RUNNING_RESULTS_ID_idx` (`RUNNING_RESULTS_ID`),
  CONSTRAINT `FK_ACHIEVEMENTS_REF_ACHIEVEMENTS_TYPE_ID` FOREIGN KEY (`REF_ACHIEVEMENTS_TYPE_ID`) REFERENCES `REF_ACHIEVEMENTS_TYPE` (`ID`),
  CONSTRAINT `FK_ACHIEVEMENTS_RUNNING_RESULTS_ID` FOREIGN KEY (`RUNNING_RESULTS_ID`) REFERENCES `RUNNING_RESULTS` (`ID`),
  CONSTRAINT `FK_ACHIEVEMENTS_SEC_USERS_ID` FOREIGN KEY (`SEC_USERS_ID`) REFERENCES `SEC_USERS` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACHIEVEMENTS`
--

LOCK TABLES `ACHIEVEMENTS` WRITE;
/*!40000 ALTER TABLE `ACHIEVEMENTS` DISABLE KEYS */;
INSERT INTO `ACHIEVEMENTS` VALUES (1,1,1,1);
/*!40000 ALTER TABLE `ACHIEVEMENTS` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `EMERGENCY_CONTACT`
--

LOCK TABLES `EMERGENCY_CONTACT` WRITE;
/*!40000 ALTER TABLE `EMERGENCY_CONTACT` DISABLE KEYS */;
INSERT INTO `EMERGENCY_CONTACT` VALUES (1,'Скай Уокер','+12353453434','Ребёнок',4,1);
/*!40000 ALTER TABLE `EMERGENCY_CONTACT` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MAILING_LIST`
--

LOCK TABLES `MAILING_LIST` WRITE;
/*!40000 ALTER TABLE `MAILING_LIST` DISABLE KEYS */;
INSERT INTO `MAILING_LIST` VALUES (1,1,1,1),(2,1,1,4),(3,1,2,1);
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
  `TYPE` enum('Сотовый','Домашний','Рабочий') NOT NULL COMMENT 'Тип номера телефона',
  PRIMARY KEY (`ID`),
  KEY `FK_PHONE_NUMBERS_SEC_USERS_ID_idx` (`SEC_USERS_ID`),
  CONSTRAINT `FK_PHONE_NUMBERS_SEC_USERS_ID` FOREIGN KEY (`SEC_USERS_ID`) REFERENCES `SEC_USERS` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PHONE_NUMBERS`
--

LOCK TABLES `PHONE_NUMBERS` WRITE;
/*!40000 ALTER TABLE `PHONE_NUMBERS` DISABLE KEYS */;
INSERT INTO `PHONE_NUMBERS` VALUES (1,1,'+79992223355','Сотовый'),(2,1,'+74954325656','Домашний');
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `REF_AGES_CATEGORY`
--

LOCK TABLES `REF_AGES_CATEGORY` WRITE;
/*!40000 ALTER TABLE `REF_AGES_CATEGORY` DISABLE KEYS */;
INSERT INTO `REF_AGES_CATEGORY` VALUES (1,'VM45-49','Мужская категория от 45 до 49 лет (включительно)',45,49,'Мужской'),(2,'VW43-47','Женская категория от 43 до 47 лет включительно',43,47,'Женский');
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
  `REF_DISTRICTS` int NOT NULL COMMENT 'Район города',
  `NAME` varchar(255) NOT NULL COMMENT 'Наименование города',
  PRIMARY KEY (`ID`),
  KEY `FK_REF_CITIES_REF_DISTRICTS_idx` (`REF_DISTRICTS`),
  CONSTRAINT `FK_REF_CITIES_REF_DISTRICTS` FOREIGN KEY (`REF_DISTRICTS`) REFERENCES `REF_DISTRICTS` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `REF_CITIES`
--

LOCK TABLES `REF_CITIES` WRITE;
/*!40000 ALTER TABLE `REF_CITIES` DISABLE KEYS */;
INSERT INTO `REF_CITIES` VALUES (1,1,'Москва'),(2,2,'Москва');
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
  `REF_REGIONS` int NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_REF_DISTRICTS_REF_REGIONS_idx` (`REF_REGIONS`),
  CONSTRAINT `FK_REF_DISTRICTS_REF_REGIONS` FOREIGN KEY (`REF_REGIONS`) REFERENCES `REF_REGIONS` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `REF_DISTRICTS`
--

LOCK TABLES `REF_DISTRICTS` WRITE;
/*!40000 ALTER TABLE `REF_DISTRICTS` DISABLE KEYS */;
INSERT INTO `REF_DISTRICTS` VALUES (1,'Тверской район',1),(2,'Тимирязевский район',1),(3,'Марьина Роща район',1);
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
  `REF_SUBSCRIBE_TYPE` int NOT NULL COMMENT 'Тип рассылки',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `SUBSCRIBE_TYPE_UNIQUE` (`REF_SUBSCRIBE_TYPE`),
  UNIQUE KEY `NAME_UNIQUE` (`NAME`),
  CONSTRAINT `FK_REF_EMAIL_SAMPLES_SUBSCRTYPE` FOREIGN KEY (`REF_SUBSCRIBE_TYPE`) REFERENCES `REF_SUBSCRIBE_TYPE` (`ID`)
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
  `REF_COUNTRY` int NOT NULL COMMENT 'Страна',
  `CODE` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_REF_REGIONS_COUNTRY_idx` (`REF_COUNTRY`),
  CONSTRAINT `FK_REF_REGIONS_COUNTRY` FOREIGN KEY (`REF_COUNTRY`) REFERENCES `REF_COUNTRIES` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `REF_REGIONS`
--

LOCK TABLES `REF_REGIONS` WRITE;
/*!40000 ALTER TABLE `REF_REGIONS` DISABLE KEYS */;
INSERT INTO `REF_REGIONS` VALUES (1,'Москва',1,'MSK'),(2,'Московская область',1,'MO'),(3,'Тверская область',1,'TVO'),(4,'Тамбовская область',1,'TMB'),(5,'Санкт-Петербург',1,'SPB');
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
  PRIMARY KEY (`ID`),
  KEY `FK_RUNNER_COUNT_SEC_USERS_ID_idx` (`SEC_USERS_ID`),
  KEY `FK_RUNNER_COUNT_TEAMS_RUNNING_COUNT_ID_idx` (`TEAMS_RUNNING_COUNT_ID`),
  CONSTRAINT `FK_RUNNER_COUNT_SEC_USERS_ID` FOREIGN KEY (`SEC_USERS_ID`) REFERENCES `SEC_USERS` (`ID`),
  CONSTRAINT `FK_RUNNER_COUNT_TEAMS_RUNNING_COUNT_ID` FOREIGN KEY (`TEAMS_RUNNING_COUNT_ID`) REFERENCES `TEAMS_RUNNING_COUNT` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `RUNNER_COUNT`
--

LOCK TABLES `RUNNER_COUNT` WRITE;
/*!40000 ALTER TABLE `RUNNER_COUNT` DISABLE KEYS */;
INSERT INTO `RUNNER_COUNT` VALUES (1,1,1);
/*!40000 ALTER TABLE `RUNNER_COUNT` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `RUNNING_RESULTS`
--

DROP TABLE IF EXISTS `RUNNING_RESULTS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `RUNNING_RESULTS` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `SEC_USERS_ID` int NOT NULL COMMENT 'Бегун',
  `RESULT` datetime NOT NULL COMMENT 'Результат забега',
  `TEAMS_RUNNING_COUNT_ID` int NOT NULL COMMENT 'Номер забега',
  `FINISH_PLACE` int NOT NULL COMMENT 'Номер карточки места на финише',
  PRIMARY KEY (`ID`),
  KEY `FK_RUNNING_RESULTS_SEC_USERS_ID_idx` (`SEC_USERS_ID`),
  KEY `FK_RUNNING_RESULTS_TEAMS_RUNNING_COUNT_ID_idx` (`TEAMS_RUNNING_COUNT_ID`),
  CONSTRAINT `FK_RUNNING_RESULTS_SEC_USERS_ID` FOREIGN KEY (`SEC_USERS_ID`) REFERENCES `SEC_USERS` (`ID`),
  CONSTRAINT `FK_RUNNING_RESULTS_TEAMS_RUNNING_COUNT_ID` FOREIGN KEY (`TEAMS_RUNNING_COUNT_ID`) REFERENCES `TEAMS_RUNNING_COUNT` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `RUNNING_RESULTS`
--

LOCK TABLES `RUNNING_RESULTS` WRITE;
/*!40000 ALTER TABLE `RUNNING_RESULTS` DISABLE KEYS */;
INSERT INTO `RUNNING_RESULTS` VALUES (1,1,'1899-01-01 00:23:00',1,10);
/*!40000 ALTER TABLE `RUNNING_RESULTS` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SEC_GROUPS`
--

LOCK TABLES `SEC_GROUPS` WRITE;
/*!40000 ALTER TABLE `SEC_GROUPS` DISABLE KEYS */;
INSERT INTO `SEC_GROUPS` VALUES (1,'Global_Admin',1,'Администратор глобальный'),(2,'Admin',1,'Администратор команды'),(3,'Runner',1,'Бегун'),(4,'Manager',1,'Менеджер команды'),(5,'Директор',1,'Директор команды');
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SEC_USERGROUPS_MEMBER`
--

LOCK TABLES `SEC_USERGROUPS_MEMBER` WRITE;
/*!40000 ALTER TABLE `SEC_USERGROUPS_MEMBER` DISABLE KEYS */;
INSERT INTO `SEC_USERGROUPS_MEMBER` VALUES (1,1,3,1),(2,2,5,1),(3,2,5,2);
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
  `NAME` varchar(255) NOT NULL COMMENT 'Имя (используется e-mail)',
  `FULLNAME` varchar(255) NOT NULL COMMENT 'Полное имя',
  `PASSWORD` varchar(100) NOT NULL COMMENT 'Пароль',
  `NICK_NAME` varchar(100) DEFAULT NULL COMMENT 'Альтернативное имя',
  `ACTIVE` tinyint(1) NOT NULL DEFAULT '1' COMMENT 'Флаг активности пользователя',
  `USE_NICK` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'Флаг отображения ника в таблице с результатами',
  `TEAMS_ID` int NOT NULL COMMENT 'ID домашней команды',
  `BIRTHDAY` datetime NOT NULL COMMENT 'Возраст',
  `SEX` enum('Мужской','Женский') NOT NULL COMMENT 'Пол пользователя',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `NAME_UNIQUE` (`NAME`),
  UNIQUE KEY `NICK_NAME_UNIQUE` (`NICK_NAME`),
  KEY `FK_SEC_USERS_TEAMS_ID_idx` (`TEAMS_ID`),
  CONSTRAINT `FK_SEC_USERS_TEAMS_ID` FOREIGN KEY (`TEAMS_ID`) REFERENCES `TEAMS` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SEC_USERS`
--

LOCK TABLES `SEC_USERS` WRITE;
/*!40000 ALTER TABLE `SEC_USERS` DISABLE KEYS */;
INSERT INTO `SEC_USERS` VALUES (1,'new_runner@runnerlite.ru','Новый Совсем Бегун','123','NEW Runner',1,1,1,'2001-01-20 01:00:00','Мужской'),(2,'director@runnerlite.ru','Иванов Иван Иваныч','1','direct',1,1,1,'1978-03-13 00:00:00','Мужской'),(3,'subzero@runnerlite.ru','Попова Екатерина Викторовна','2','wewewe',1,1,1,'1999-05-18 00:00:00','Женский'),(4,'wader@runnerlite.ru','Дарт Вейдер','3','DarthWader',1,1,1,'1222-02-24 00:00:00','Мужской');
/*!40000 ALTER TABLE `SEC_USERS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TEAMS`
--

DROP TABLE IF EXISTS `TEAMS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `TEAMS` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) NOT NULL COMMENT 'Наименование беговой команды',
  `DESCRIPTION` varchar(512) NOT NULL COMMENT 'Описание беговой команды',
  `GEO_LAT` float(10,6) DEFAULT NULL COMMENT 'Координата «широта» геолокации места сбора',
  `GEO_LNG` float(10,6) DEFAULT NULL COMMENT 'Координата «долгота» геолокации места сбора',
  `GEO_DESCRIPTION` varchar(255) DEFAULT NULL COMMENT 'Описание геолокации места сбора',
  `ACTIVE` tinyint(1) NOT NULL COMMENT 'Флаг доступности команды',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `NAME_UNIQUE` (`NAME`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TEAMS`
--

LOCK TABLES `TEAMS` WRITE;
/*!40000 ALTER TABLE `TEAMS` DISABLE KEYS */;
INSERT INTO `TEAMS` VALUES (1,'ТИМКА','Россия, Москва\nЗона старта располагается недалеко от входа в парк со стороны ул. Вучетича, рядом с Лесной опытной дачей (дом 46c1)',55.817986,37.556271,'Россия, Москва Зона старта располагается недалеко от входа в парк со стороны ул. Вучетича, рядом с Лесной опытной дачей (дом 46c1)',1),(2,'ДРУЖБА','Россия, Москва, Флотская 1А',NULL,NULL,'Россия, Москва, Флотская 1А',1);
/*!40000 ALTER TABLE `TEAMS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TEAMS_LOCATIONS`
--

DROP TABLE IF EXISTS `TEAMS_LOCATIONS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `TEAMS_LOCATIONS` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `TEAMS_ID` int NOT NULL COMMENT 'ID команды',
  `REF_CITIES` int DEFAULT NULL COMMENT 'ID города команды',
  `ADDRESS` varchar(255) DEFAULT NULL COMMENT 'Официальный адрес места проведения забегов (не место сбора)',
  `ALTERNATE_LOCATIONS` int DEFAULT NULL COMMENT 'Ссылка на альтернативную локацию (в разработке)',
  `USE_ALTLOCATIONS` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'Если установлено «0» - используется текущая локация',
  PRIMARY KEY (`ID`),
  KEY `FK_TEAMS_LOCATIONS_TEAMS_ID_idx` (`TEAMS_ID`),
  KEY `FK_TEAMS_LOCATIONS_REF_CITIES_ID_idx` (`REF_CITIES`),
  CONSTRAINT `FK_TEAMS_LOCATIONS_REF_CITIES_ID` FOREIGN KEY (`REF_CITIES`) REFERENCES `REF_CITIES` (`ID`),
  CONSTRAINT `FK_TEAMS_LOCATIONS_TEAMS_ID` FOREIGN KEY (`TEAMS_ID`) REFERENCES `TEAMS` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TEAMS_LOCATIONS`
--

LOCK TABLES `TEAMS_LOCATIONS` WRITE;
/*!40000 ALTER TABLE `TEAMS_LOCATIONS` DISABLE KEYS */;
INSERT INTO `TEAMS_LOCATIONS` VALUES (1,1,1,'ул. Вучетича, рядом с Лесной опытной дачей (д. 46/с1)',NULL,0);
/*!40000 ALTER TABLE `TEAMS_LOCATIONS` ENABLE KEYS */;
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
  `STATUS` enum('Запланирован','Выполнен','Отменён') NOT NULL DEFAULT 'Запланирован',
  PRIMARY KEY (`ID`),
  KEY `FK_TEAMS_RUNNING_COUNT_TEAMS_ID_idx` (`TEAMS_ID`),
  CONSTRAINT `FK_TEAMS_RUNNING_COUNT_TEAMS_ID` FOREIGN KEY (`TEAMS_ID`) REFERENCES `TEAMS` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TEAMS_RUNNING_COUNT`
--

LOCK TABLES `TEAMS_RUNNING_COUNT` WRITE;
/*!40000 ALTER TABLE `TEAMS_RUNNING_COUNT` DISABLE KEYS */;
INSERT INTO `TEAMS_RUNNING_COUNT` VALUES (1,1,'2022-04-16 00:00:00',25,'Запланирован'),(2,2,'2022-04-16 00:00:00',11,'Запланирован'),(3,1,'2022-04-23 00:00:00',26,'Запланирован'),(4,2,'2022-04-23 00:00:00',12,'Запланирован');
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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TEAMS_VOLUNTEERS`
--

LOCK TABLES `TEAMS_VOLUNTEERS` WRITE;
/*!40000 ALTER TABLE `TEAMS_VOLUNTEERS` DISABLE KEYS */;
INSERT INTO `TEAMS_VOLUNTEERS` VALUES (1,1,1,2,1),(2,1,2,1,1),(3,1,3,1,0),(4,1,5,2,0),(5,1,6,1,1),(6,1,7,1,0),(7,1,8,2,1),(8,2,1,1,1),(9,2,6,1,1);
/*!40000 ALTER TABLE `TEAMS_VOLUNTEERS` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `VOLUNTEERS`
--

LOCK TABLES `VOLUNTEERS` WRITE;
/*!40000 ALTER TABLE `VOLUNTEERS` DISABLE KEYS */;
INSERT INTO `VOLUNTEERS` VALUES (1,1,0,6,1),(2,4,0,4,1);
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

-- Dump completed on 2022-04-11 21:51:28
