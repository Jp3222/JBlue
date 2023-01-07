-- MySQL dump 10.13  Distrib 8.0.31, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: jblue
-- ------------------------------------------------------
-- Server version	8.0.31-0ubuntu2

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
-- Table structure for table `calles`
--

DROP TABLE IF EXISTS `calles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `calles` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `numero` varchar(3) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `calles`
--

LOCK TABLES `calles` WRITE;
/*!40000 ALTER TABLE `calles` DISABLE KEYS */;
INSERT INTO `calles` VALUES (13,'chimalpopoca','10'),(14,'netzahualcoyotl','10'),(15,'Calle soledad','S/N'),(16,'xdxd','32'),(17,'sd','32'),(18,'dssd','S/N'),(19,'xd','235'),(20,'QWE','12'),(21,'MALINCHE','2');
/*!40000 ALTER TABLE `calles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `consumidores`
--

DROP TABLE IF EXISTS `consumidores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `consumidores` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(30) DEFAULT NULL,
  `ap` varchar(30) DEFAULT NULL,
  `am` varchar(30) DEFAULT NULL,
  `titular` int DEFAULT NULL,
  `registro` varchar(10) DEFAULT NULL,
  `estado` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_consumidores_titular_idx` (`titular`),
  CONSTRAINT `fk_consumidores_titular` FOREIGN KEY (`titular`) REFERENCES `titulares` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `consumidores`
--

LOCK TABLES `consumidores` WRITE;
/*!40000 ALTER TABLE `consumidores` DISABLE KEYS */;
/*!40000 ALTER TABLE `consumidores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personal`
--

DROP TABLE IF EXISTS `personal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `personal` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(35) NOT NULL,
  `apellidos` varchar(70) NOT NULL,
  `cargo` int NOT NULL,
  `usuario` varchar(30) NOT NULL,
  `contra` varchar(30) NOT NULL,
  `registro` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personal`
--

LOCK TABLES `personal` WRITE;
/*!40000 ALTER TABLE `personal` DISABLE KEYS */;
INSERT INTO `personal` VALUES (1,'root','- -',1,'DYjV5v/rZTjlHOhU5GTewg==','Gn6/q0zfTijiIiUDD5bnvA==','19-12-2022');
/*!40000 ALTER TABLE `personal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_tomas`
--

DROP TABLE IF EXISTS `tipo_tomas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipo_tomas` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tipo` varchar(50) NOT NULL,
  `costo` int NOT NULL,
  `recargo` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_tomas`
--

LOCK TABLES `tipo_tomas` WRITE;
/*!40000 ALTER TABLE `tipo_tomas` DISABLE KEYS */;
INSERT INTO `tipo_tomas` VALUES (1,'DOMESTICA',70,100),(4,'COMERCIAL',500,200),(5,'RESIDENCIAL',100,1500),(6,'ESPECIAL',200,300);
/*!40000 ALTER TABLE `tipo_tomas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `titulares`
--

DROP TABLE IF EXISTS `titulares`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `titulares` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(30) NOT NULL,
  `ap` varchar(30) NOT NULL,
  `am` varchar(30) NOT NULL,
  `calle` int NOT NULL,
  `tipo_toma` int NOT NULL,
  `registro` varchar(10) NOT NULL,
  `estado` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_titulares_calle_idx` (`calle`),
  KEY `fk_titulares_1_idx` (`tipo_toma`),
  CONSTRAINT `fk_titulares_1` FOREIGN KEY (`tipo_toma`) REFERENCES `tipo_tomas` (`id`),
  CONSTRAINT `fk_titulares_calle` FOREIGN KEY (`calle`) REFERENCES `calles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `titulares`
--

LOCK TABLES `titulares` WRITE;
/*!40000 ALTER TABLE `titulares` DISABLE KEYS */;
/*!40000 ALTER TABLE `titulares` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-12-23 22:09:21
