CREATE DATABASE  IF NOT EXISTS `bd_flavors_feast` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `bd_flavors_feast`;
-- MySQL dump 10.13  Distrib 8.0.43, for Win64 (x86_64)
--
-- Host: localhost    Database: bd_flavors_feast
-- ------------------------------------------------------
-- Server version	9.5.0

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
SET @MYSQLDUMP_TEMP_LOG_BIN = @@SESSION.SQL_LOG_BIN;
SET @@SESSION.SQL_LOG_BIN= 0;

--
-- GTID state at the beginning of the backup 
--

SET @@GLOBAL.GTID_PURGED=/*!80000 '+'*/ '73fc5b85-af8c-11f0-9cf7-74563c5a7150:1-139';

--
-- Table structure for table `tbl_category`
--

DROP TABLE IF EXISTS `tbl_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_category` (
  `id_category` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `uid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_category`),
  UNIQUE KEY `UKnxkhmhxpui8j5nnh1rbtn2cqp` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_category`
--

LOCK TABLES `tbl_category` WRITE;
/*!40000 ALTER TABLE `tbl_category` DISABLE KEYS */;
INSERT INTO `tbl_category` VALUES (1,'Sabores Costeños','a4c9e5fbb27e4f3aa7e33a2b4f74a5d8'),(2,'De la Sierra','b2f6a3c8d5e34b02a2f981c1e9a8f3c7'),(3,'Comida Selvática','c7e1b4a5d3f64e1ba4f8a9b2d1c3e5f9'),(4,'Deliciosos Postres','d9f2a1b8e3c14d9cb6f2e8a7c9a5e3b1');
/*!40000 ALTER TABLE `tbl_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_product`
--

DROP TABLE IF EXISTS `tbl_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_product` (
  `id_product` int NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `uid` varchar(255) DEFAULT NULL,
  `id_category` int DEFAULT NULL,
  PRIMARY KEY (`id_product`),
  UNIQUE KEY `UKdbwvrr6lto9oj8kvtgou2juvu` (`uid`),
  KEY `FK7kghi9mhypicu8idfmp3nbi1` (`id_category`),
  CONSTRAINT `FK7kghi9mhypicu8idfmp3nbi1` FOREIGN KEY (`id_category`) REFERENCES `tbl_category` (`id_category`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_product`
--

LOCK TABLES `tbl_product` WRITE;
/*!40000 ALTER TABLE `tbl_product` DISABLE KEYS */;
INSERT INTO `tbl_product` VALUES (1,'La causa limeña, un plato típico peruano con una historia rica y versátil.','images-products/Causa.jpg','Causa Limeña',18,'e1a2b3c4d5f64789a1b2c3d4e5f60789',1),(2,'Ají o crema espesa con pechuga de gallina deshilachada, lechuga, huevos y aceitunas.','images-products/aji-de-gallina.jpg','Ají de Gallina',31,'f2b3c4d5e6a74890b2c3d4e5f6a71890',1),(3,'Deliciosa preparación que combina el sabor y la textura del arroz con una variedad de mariscos y pescados frescos.','images-products/ArrozMariscos.jpg','Arroz con Mariscos',32,'a3c4d5e6f7b84901c3d4e5f6a7b82901',1),(4,'Mezcla de arroz frito acompañada de verduras, tortilla de huevo y carnes.','images-products/ArrozChaufa.jpg','Arroz Chaufa',33,'b4d5e6f7a8c95012d4e5f6a7b8c94012',1),(5,'Potaje indígena, preparado con papa seca sancochada y guisada con diversas carnes como pollo, gallina y chancho.','images-products/carapulcra.jpg','Carapulcra',32,'c5e6f7a8b9d05123e5f6a7b8c9d05023',1),(6,'Lomo fino salteado con cebolla, tomate, y culantro servido con arroz y papas fritas.','images-products/lomo-saltado.jpg','Lomo Saltado',39,'d6f7a8b9c0e16234f6a7b8c9d0e16034',1),(7,'Consiste en un aderezo de ajo, cebolla, cilantro, tomate, pescado y ají amarillo.','images-products/sudado-de-pescado.jpg','Sudado de Pescado',33,'e7a8b9c0d1f27345a7b8c9d0e1f27045',1),(8,'Guiso de origen árabe guisado con culantro, ají amarillo y zapallo loche.','images-products/seco-de-cabrito.jpg','Seco de cabrito',30,'f8b9c0d1e2a38456b8c9d0e1f2a38056',1),(9,'Un plato que trasciende la historia peruana y posee un exquisito sabor.','images-products/pachamanca.jpg','Pachamanca',29,'a9c0d1e2f3b49567c9d0e1f2a3b49067',2),(10,'Trucha, pez de carne blanca o color salmón, tiene un delicioso sabor.','images-products/trucha-frita.jpg','Trucha frita',28,'b0d1e2f3a4c50678d0e1f2a3b4c50078',2),(11,'Trozos de carne del cerdo recién beneficiado, frita en aceite de cocina bien caliente, condimentado con un poco de sal.','images-products/chicharron.jpg','Chicharrón',28,'c1e2f3a4b5d61789e1f2a3b4c5d61089',2),(12,'Plato típico de la sierra peruana, sobre todo en la temporada de carnavales.','images-products/puchero.jpg','Puchero',24,'d2f3a4b5c6e72890f2a3b4c5d6e72090',2),(13,'Plato estrella de la ciudad del Cusco. Proviene del quechua Chiri que significa «frío».','images-products/Chiri_uchu.jpg','Chiri Uchu',24,'e3a4b5c6d7f83901a3b4c5d6e7f83001',2),(14,'Preparada con charqui, principalmente de carne de oveja, en una deliciosa receta típica de las serranías.','images-products/ollucos-con-charqui.jpg','Olluco con Charqui',28,'f4b5c6d7e8a94012b4c5d6e7f8a94012',2),(15,'Plato típico delicioso y bueno para aclimatarse en el frío típico de la sierra del Perú.','images-products/rocotorelleno.jpg','Rocoto relleno',24,'a5c6d7e8f9b05123c5d6e7f8a9b05023',2),(16,'Plato típico ariqueño que combina el picante del rocoto con el relleno de carne molida y queso.','images-products/lawa-de-choclo.jpg','Lawa de Choclo',26,'b6d7e8f9a0c16234d6e7f8a9b0c16034',2),(17,'Plato típico de la selva peruana que destaca por la fusión de sabores auténticos y técnicas ancestrales.','images-products/juane.jpg','Juane',32,'c7e8f9a0b1d27345e7f8a9b0c1d27045',3),(18,'Hecha con plátanos asados, y posteriormente machacados y amasados en bolas con manteca y trozos de cecina.','images-products/tacacho.jpeg','Tacacho',28,'d8f9a0b1c2e38456f8a9b0c1d2e38056',3),(19,'Pescado fresco marinado, envuelto en hoja de bijao y cocido a la parrilla. Se acompaña con arroz, yuca frita, arvejas u otros.','images-products/patarashca.jpg','Patarashca',34,'e9a0b1c2d3f49567a9b0c1d2e3f49067',3),(20,'Un arroz chaufa oriental, mezclado con los típicos ingredientes de la selva peruana como la cecina y el chorizo amazónico.','images-products/arrozchaufacharapa.jpg','Arroz Chaufa de Charapa',32,'f0b1c2d3e4a50678b0c1d2e3f4a50078',3),(21,'Una sopa a base de harina de maíz y maní, con una presa de gallina.','images-products/inchicapi.jpg','Inchicapi de gallina',30,'a1c2d3e4f5b61789c1d2e3f4a5b61089',3),(22,'Plato habitual en la Amazonía peruana que se come acompañada de patacones o tostones.','images-products/palometafrita.jpg','Palometa Frita',30,'b2d3e4f5a6c72890d2e3f4a5b6c72090',3),(23,'El majaz o paca es una especie de roedor de carne muy apreciada y de consumo común en la región neotropical.','images-products/Guiso-de-majaz.jpg','Guiso de majaz',32,'c3e4f5a6b7d83901e3f4a5b6c7d83001',3),(24,'Postre peruano espeso hecho de maíz morado y frutas.','images-products/mazamorra-morada.jpg','Mazamorra Morada',15,'d4f5a6b7c8e94012f4a5b6c7d8e94012',4),(25,'Postre peruano hecho con harina de maíz, chancaca y especias, de textura espesa y sabor dulce.','images-products/mazamorra-cochina.jpg','Mazamorra Cochina',15,'e5a6b7c8d9f05123a5b6c7d8e9f05023',4),(26,'Postre cremoso hecho con arroz, leche, azúcar y canela.','images-products/arrozconleche.jpg','Arroz con leche',15,'f6b7c8d9e0a16234b6c7d8e9f0a16034',4),(27,'Postre peruano similar al flan, hecho con leche, huevos y azúcar caramelizada.','images-products/crema-volteada.jpg','Crema volteada',18,'a7c8d9e0f1b27345c7d8e9f0a1b27045',4),(28,'Los piononos de manjar son rollos de bizcocho rellenos de manjar blanco, suaves y dulces.','images-products/pionono-de-manjar.jpg','Piononos de Manjar',14,'b8d9e0f1a2c38456d8e9f0a1b2c38056',4),(29,'Postre de masa crujiente rellena de manzanas dulces y especiadas.','images-products/pie-de-manzana.jpg','Pie de manzana',16,'c9e0f1a2b3d49567e9f0a1b2c3d49067',4),(30,'Postre suave y cremoso, hecho con leche, huevos y azúcar, que se hornea hasta obtener una capa dorada.','images-products/leche-asada.jpg','Leche asada',18,'d0f1a2b3c4e50678f0a1b2c3d4e50078',4),(31,'Postre frito, hechos con una masa de camote y calabaza, servidos con miel de chancaca.','images-products/picarones.jpg','Picarones',18,'e1a2b3c4d5f61789a1b2c3d4e5f61089',4),(32,'Postre tradicional hecho con capas de galletas bañadas en miel de chancaca, decorado con confites de colores.','images-products/turrondoñapepa.jpg','Turrón de Doña Pepa',18,'f2b3c4d5e6a72890b2c3d4e5f6a72090',4);
/*!40000 ALTER TABLE `tbl_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_purchase`
--

DROP TABLE IF EXISTS `tbl_purchase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_purchase` (
  `id_purchase` int NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `date` datetime(6) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `subtotal` double DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `uid` varchar(255) DEFAULT NULL,
  `id_user` int DEFAULT NULL,
  PRIMARY KEY (`id_purchase`),
  UNIQUE KEY `UK7t26cg3ub98j4yij8913js8vd` (`uid`),
  KEY `FK4y8askbrffw3rgwoc4fmrgc6` (`id_user`),
  CONSTRAINT `FK4y8askbrffw3rgwoc4fmrgc6` FOREIGN KEY (`id_user`) REFERENCES `tbl_user` (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_purchase`
--

LOCK TABLES `tbl_purchase` WRITE;
/*!40000 ALTER TABLE `tbl_purchase` DISABLE KEYS */;
INSERT INTO `tbl_purchase` VALUES (2,'AV. SINFUENTES 030','2025-10-28 01:26:12.839000',NULL,85.16,'delivery','a017cb9b32d44de2b684894200fc9846',2),(3,NULL,'2025-10-28 01:41:29.617000','La Molina',122.72,'retiro','bf0ccc38f26c4302bed1cbe935f62478',2),(4,'dfds','2025-10-28 16:33:46.991000',NULL,134.72,'delivery','dcf638606602470f920e2f8088899a4b',1);
/*!40000 ALTER TABLE `tbl_purchase` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_purchase_detail`
--

DROP TABLE IF EXISTS `tbl_purchase_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_purchase_detail` (
  `id_purchase_detail` int NOT NULL AUTO_INCREMENT,
  `quantity` int DEFAULT NULL,
  `uid` varchar(255) DEFAULT NULL,
  `unit_price` double DEFAULT NULL,
  `id_product` int DEFAULT NULL,
  `id_purchase` int DEFAULT NULL,
  PRIMARY KEY (`id_purchase_detail`),
  UNIQUE KEY `UKl9ofyhccygko1uo9t1rnvdchc` (`uid`),
  KEY `FK48wu6jouryv0tw0hk7motvjnc` (`id_product`),
  KEY `FKmpydwk8kfp5dk8t5c5rpqhvma` (`id_purchase`),
  CONSTRAINT `FK48wu6jouryv0tw0hk7motvjnc` FOREIGN KEY (`id_product`) REFERENCES `tbl_product` (`id_product`),
  CONSTRAINT `FKmpydwk8kfp5dk8t5c5rpqhvma` FOREIGN KEY (`id_purchase`) REFERENCES `tbl_purchase` (`id_purchase`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_purchase_detail`
--

LOCK TABLES `tbl_purchase_detail` WRITE;
/*!40000 ALTER TABLE `tbl_purchase_detail` DISABLE KEYS */;
INSERT INTO `tbl_purchase_detail` VALUES (2,2,'05803bae11ac4801aa62cbb389b42765',31,2,2),(3,1,'4ff64213b3db450d9665b80cb0ef51ad',39,6,3),(4,1,'cb5660497acd41e7b8081f1c1163d5fb',32,5,3),(5,1,'feb4aedd00a949f3ba7377de987837a5',33,4,3),(6,1,'af55c61ae1e5458da09bae0121b387a1',39,6,4),(7,1,'66088be04bab44b4b3a7c367466262ad',32,5,4),(8,1,'a0e2677f1f924859bc37ed5903f2ff79',33,4,4);
/*!40000 ALTER TABLE `tbl_purchase_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_reservation`
--

DROP TABLE IF EXISTS `tbl_reservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_reservation` (
  `id_reservation` int NOT NULL AUTO_INCREMENT,
  `date` date DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `persons` int DEFAULT NULL,
  `state` enum('CANCELED','COMPLETED','PENDING') DEFAULT NULL,
  `time` time(6) DEFAULT NULL,
  `uid` varchar(255) DEFAULT NULL,
  `id_user` int DEFAULT NULL,
  PRIMARY KEY (`id_reservation`),
  UNIQUE KEY `UK157nwyc4flq9h1c56a1gx9tct` (`uid`),
  KEY `FK6498trbemnrryvobinrvwoied` (`id_user`),
  CONSTRAINT `FK6498trbemnrryvobinrvwoied` FOREIGN KEY (`id_user`) REFERENCES `tbl_user` (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_reservation`
--

LOCK TABLES `tbl_reservation` WRITE;
/*!40000 ALTER TABLE `tbl_reservation` DISABLE KEYS */;
INSERT INTO `tbl_reservation` VALUES (2,'2025-10-30','Miraflores',5,'PENDING','16:30:00.000000','c331aff8cb194de9a19a366f6a307b23',1),(3,'2025-10-30','Miraflores',3,'PENDING','15:30:00.000000','3e413842f1ac4885bfd0d3cb2c1f3c98',1),(4,'2025-10-30','Pueblo Libre',4,'PENDING','15:30:00.000000','1c5d2f14331440468df3f318d00753ae',2);
/*!40000 ALTER TABLE `tbl_reservation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_user`
--

DROP TABLE IF EXISTS `tbl_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_user` (
  `id_user` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `role` enum('ADMIN','USER') DEFAULT NULL,
  `uid` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id_user`),
  UNIQUE KEY `UKnpn1wf1yu1g5rjohbek375pp1` (`email`),
  UNIQUE KEY `UKr2ipw7av593cb0iunvtfc83ui` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_user`
--

LOCK TABLES `tbl_user` WRITE;
/*!40000 ALTER TABLE `tbl_user` DISABLE KEYS */;
INSERT INTO `tbl_user` VALUES (1,'2025-10-25 01:57:42.487000','diego@gmail.com','Diego Alberto','Sanchez Garcia','$2a$10$MFysOzHaX6JiH9AbGmS6Eu2wZ2Cr.o37vn6XmzfMDTX39m8igy0H6','437364238','USER','1348daccde12488a970a54e56bd7ba67','2025-10-28 16:37:05.606000'),(2,'2025-10-28 01:03:04.472000','fanjian@gmail.com','Fanjian','Xue','$2a$10$k3rd0BT8WMZz118MDKVqLu9xx1wm2EUZmyaODIT6ey8Oyemn5EExO','123456790','USER','2e0cb08dbc394d419fc16573d75363de','2025-10-28 01:03:04.472000');
/*!40000 ALTER TABLE `tbl_user` ENABLE KEYS */;
UNLOCK TABLES;
SET @@SESSION.SQL_LOG_BIN = @MYSQLDUMP_TEMP_LOG_BIN;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-10-28 17:56:13
