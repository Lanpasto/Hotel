-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: hotel
-- ------------------------------------------------------
-- Server version	8.0.31

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
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
                          `id` int NOT NULL AUTO_INCREMENT,
                          `userId` int NOT NULL,
                          `roomId` int NOT NULL,
                          `dateOfSettlement` date NOT NULL,
                          `dateOfOut` date NOT NULL,
                          `totalPrice` int NOT NULL,
                          `status` varchar(45) NOT NULL,
                          `dateOfCreateOrder` date NOT NULL,
                          PRIMARY KEY (`id`),
                          KEY `FK_room_idx` (`roomId`),
                          KEY `FK_user_idx` (`userId`),
                          CONSTRAINT `FK_room` FOREIGN KEY (`roomId`) REFERENCES `room` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
                          CONSTRAINT `FK_user` FOREIGN KEY (`userId`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders_request`
--

DROP TABLE IF EXISTS `orders_request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders_request` (
                                  `id` int NOT NULL AUTO_INCREMENT,
                                  `type_of_room` varchar(45) NOT NULL,
                                  `dateOfSettlement` date NOT NULL,
                                  `dateOfOut` date NOT NULL,
                                  `userId` int NOT NULL,
                                  `dateOfCreateRequest` date NOT NULL,
                                  `guests` int NOT NULL,
                                  `status` varchar(45) NOT NULL,
                                  PRIMARY KEY (`id`),
                                  KEY `FK_users_idx` (`userId`),
                                  CONSTRAINT `FK_users` FOREIGN KEY (`userId`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders_request`
--

LOCK TABLES `orders_request` WRITE;
/*!40000 ALTER TABLE `orders_request` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders_request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room` (
                        `id` int NOT NULL AUTO_INCREMENT,
                        `guests` int NOT NULL,
                        `typeId` int NOT NULL,
                        `price` int NOT NULL,
                        `status` varchar(45) NOT NULL,
                        `image` varchar(45) NOT NULL,
                        PRIMARY KEY (`id`),
                        KEY `FK_type_idx` (`typeId`),
                        CONSTRAINT `FK_type` FOREIGN KEY (`typeId`) REFERENCES `type_of_room` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
INSERT INTO `room` VALUES (2,1,1,2000,'booked','/images/one_room_apartment.jpg'),(3,1,1,2000,'booked','/images/one_room_apartment.jpg'),(4,1,2,1500,'booked','/images/one_room_apartment_2.jpg'),(5,1,3,1200,'available','/images/one_room_apartment_3.jpg'),(7,2,2,1400,'available','/images/two_room_apartment_2.jpg'),(8,3,1,100,'available','/images/hilton-port-moresby.jpg'),(9,4,2,2200,'available','/images/four_room_apartment.jpg'),(10,1,3,1200,'available','/images/one_room_apartment_3.jpg'),(11,1,1,2000,'available','/images/one_room_apartment.jpg'),(12,1,2,1500,'available','/images/one_room_apartment_2.jpg'),(13,1,3,1200,'available','/images/one_room_apartment_3.jpg'),(14,1,1,2000,'available','/images/one_room_apartment.jpg'),(15,1,1,2000,'unavailable','/images/one_room_apartment.jpg'),(16,1,1,2000,'unavailable','/images/one_room_apartment.jpg');
/*!40000 ALTER TABLE `room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `type_of_room`
--

DROP TABLE IF EXISTS `type_of_room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `type_of_room` (
                                `id` int NOT NULL,
                                `type_of_room` varchar(45) NOT NULL,
                                `guests` varchar(45) NOT NULL,
                                `image` varchar(45) NOT NULL,
                                PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `type_of_room`
--

LOCK TABLES `type_of_room` WRITE;
/*!40000 ALTER TABLE `type_of_room` DISABLE KEYS */;
INSERT INTO `type_of_room` VALUES (1,'Luxury','1-2','/images/hilton-port-moresby.jpg'),(2,'Basics','2-4','/images/room1.jpg'),(3,'Economy','1-3','/images/the-shady-rest-hotel.jpg');
/*!40000 ALTER TABLE `type_of_room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
                         `id` int NOT NULL AUTO_INCREMENT,
                         `email` varchar(45) NOT NULL,
                         `password` varchar(45) NOT NULL,
                         `roleId` int NOT NULL,
                         `first_name` varchar(45) NOT NULL,
                         `last_name` varchar(45) NOT NULL,
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'1@2','1',1,'Artem','Sfiks'),(17,'2@2','2',2,'Oll','Peget'),(18,'1@1','1',1,'Danial','Bucefal'),(19,'11@11','2121',1,'Vladik','Grim'),(20,'qwer@123','1',1,'NeAlex','Orban'),(21,'3@2','qwer12',1,'dasdasdasd','dsdas'),(22,'levc4kvlad@gmail.com','1234',1,'Danylo','Dog'),(23,'222@gmail.com','1234',1,'Art','Ola');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-02-19 15:44:46