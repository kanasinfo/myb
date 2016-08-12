-- MySQL dump 10.13  Distrib 5.6.22, for osx10.8 (x86_64)
--
-- Host: localhost    Database: myb
-- ------------------------------------------------------
-- Server version	5.6.24

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
-- Table structure for table `ACCOUNT`
--

DROP TABLE IF EXISTS `ACCOUNT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACCOUNT` (
  `ID` varchar(32) NOT NULL,
  `LOGIN_EMAIL` varchar(100) NOT NULL,
  `LOGIN_PASS` varchar(100) DEFAULT NULL,
  `EMAIL_CHECK_STRING` varchar(100) DEFAULT NULL,
  `PHONE` varchar(100) DEFAULT NULL,
  `EMAIL_CHECK_STATUS` varchar(100) DEFAULT NULL,
  `REG_DATE` timestamp NULL DEFAULT NULL,
  `CREDIT_AMOUNT` int(11) DEFAULT NULL,
  `CREATED_BY` varchar(100) DEFAULT NULL,
  `CREATED_TIME` timestamp NULL DEFAULT NULL,
  `UPDATED_TIME` timestamp NULL DEFAULT NULL,
  `LOGINPWD_SALT` varchar(20) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACCOUNT`
--

LOCK TABLES `ACCOUNT` WRITE;
/*!40000 ALTER TABLE `ACCOUNT` DISABLE KEYS */;
/*!40000 ALTER TABLE `ACCOUNT` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `INDUSTRY`
--

DROP TABLE IF EXISTS `INDUSTRY`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `INDUSTRY` (
  `ID` varchar(32) NOT NULL,
  `NAME` varchar(100) DEFAULT NULL,
  `PARENT_ID` varchar(32) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `description` varchar(300) DEFAULT NULL,
  `CREATED_BY` varchar(32) DEFAULT NULL,
  `CREATED_TIME` timestamp NULL DEFAULT NULL,
  `UPDATED_TIME` timestamp NULL DEFAULT NULL,
  `isactive` bit(1) NOT NULL,
  `SORTNUMBER` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `INDUSTRY`
--

LOCK TABLES `INDUSTRY` WRITE;
/*!40000 ALTER TABLE `INDUSTRY` DISABLE KEYS */;
INSERT INTO `INDUSTRY` VALUES ('67ad3f291c784952947285ec5e713f19','aaa',NULL,NULL,NULL,NULL,'2015-12-06 14:14:45',NULL,'\0',1);
/*!40000 ALTER TABLE `INDUSTRY` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `OPTIONS`
--

DROP TABLE IF EXISTS `OPTIONS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `OPTIONS` (
  `ID` varchar(32) NOT NULL,
  `NAME` varchar(100) DEFAULT NULL,
  `VALUE` varchar(100) DEFAULT NULL,
  `QUESTION_ID` varchar(32) NOT NULL,
  `SORT_NUM` int(11) DEFAULT NULL,
  `CREATED_BY` varchar(32) DEFAULT NULL,
  `CREATED_TIME` timestamp NULL DEFAULT NULL,
  `UPDATED_TIME` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `OPTIONS`
--

LOCK TABLES `OPTIONS` WRITE;
/*!40000 ALTER TABLE `OPTIONS` DISABLE KEYS */;
/*!40000 ALTER TABLE `OPTIONS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `QUESTION`
--

DROP TABLE IF EXISTS `QUESTION`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `QUESTION` (
  `ID` varchar(32) NOT NULL,
  `NAME` varchar(100) DEFAULT NULL,
  `VALUE` varchar(100) DEFAULT NULL,
  `QUSTN_TYPE` varchar(100) DEFAULT NULL,
  `QUSTNR_GROUP_ID` varchar(32) NOT NULL,
  `QUSTNR_TEMP_ID` varchar(32) NOT NULL,
  `SORT_NUM` int(11) DEFAULT NULL,
  `NORM_CALC_VAL` float DEFAULT NULL,
  `NORM_INPUT_VAL` varchar(100) DEFAULT NULL,
  `STATUS` varchar(20) DEFAULT NULL,
  `CREATED_BY` varchar(32) DEFAULT NULL,
  `CREATED_TIME` timestamp NULL DEFAULT NULL,
  `UPDATED_TIME` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `QUESTION`
--

LOCK TABLES `QUESTION` WRITE;
/*!40000 ALTER TABLE `QUESTION` DISABLE KEYS */;
/*!40000 ALTER TABLE `QUESTION` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `QUSTNR_GROUP`
--

DROP TABLE IF EXISTS `QUSTNR_GROUP`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `QUSTNR_GROUP` (
  `ID` varchar(32) NOT NULL,
  `NAME` varchar(100) DEFAULT NULL,
  `DISPLAY_VAL` varchar(100) DEFAULT NULL,
  `SORT_NUM` int(11) DEFAULT NULL,
  `CREATED_BY` varchar(32) DEFAULT NULL,
  `CREATED_TIME` timestamp NULL DEFAULT NULL,
  `UPDATED_TIME` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `QUSTNR_GROUP`
--

LOCK TABLES `QUSTNR_GROUP` WRITE;
/*!40000 ALTER TABLE `QUSTNR_GROUP` DISABLE KEYS */;
/*!40000 ALTER TABLE `QUSTNR_GROUP` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `QUSTNR_GROUP_NORM`
--

DROP TABLE IF EXISTS `QUSTNR_GROUP_NORM`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `QUSTNR_GROUP_NORM` (
  `ID` varchar(32) NOT NULL,
  `NORM_CALC_VAL` decimal(10,0) DEFAULT NULL,
  `NORM_INPUT_VAL` decimal(10,0) DEFAULT NULL,
  `QUSTNR_TMPLT_ID` varchar(32) DEFAULT NULL,
  `QUSTNR_GROUP_ID` varchar(32) DEFAULT NULL,
  `CREATED_BY` varchar(45) DEFAULT NULL,
  `CREATED_TIME` timestamp NULL DEFAULT NULL,
  `UPDATED_TIME` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `QUSTNR_GROUP_NORM`
--

LOCK TABLES `QUSTNR_GROUP_NORM` WRITE;
/*!40000 ALTER TABLE `QUSTNR_GROUP_NORM` DISABLE KEYS */;
/*!40000 ALTER TABLE `QUSTNR_GROUP_NORM` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `QUSTNR_TMPLT`
--

DROP TABLE IF EXISTS `QUSTNR_TMPLT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `QUSTNR_TMPLT` (
  `ID` varchar(32) NOT NULL,
  `NAME` varchar(100) DEFAULT NULL,
  `INDUSTRY_ID` varchar(32) DEFAULT NULL,
  `ACTIVE_FLAG` varchar(20) DEFAULT NULL,
  `CREATE_BY` varchar(32) DEFAULT NULL,
  `CREATED_TIME` timestamp NULL DEFAULT NULL,
  `UPDATED_TIME` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `QUSTNR_TMPLT`
--

LOCK TABLES `QUSTNR_TMPLT` WRITE;
/*!40000 ALTER TABLE `QUSTNR_TMPLT` DISABLE KEYS */;
/*!40000 ALTER TABLE `QUSTNR_TMPLT` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `REPORT_CHART_MAP`
--

DROP TABLE IF EXISTS `REPORT_CHART_MAP`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `REPORT_CHART_MAP` (
  `ID` varchar(32) NOT NULL,
  `QUSTNR_GROUP_ID` varchar(32) DEFAULT NULL,
  `QUESTION_ID` varchar(32) DEFAULT NULL COMMENT 'NULL IF ALL QUESTIONS ARE SAME UNDER ONE QUESTION GROUP',
  `DIMNSN` varchar(100) DEFAULT NULL COMMENT 'PAGE INFORMATION FOR ANY DIMENSION',
  `OND_DIMNSN` varchar(100) DEFAULT NULL COMMENT 'PAGE INFORMATION FOR ONE DIMENSION',
  `STORE` varchar(100) DEFAULT NULL COMMENT 'PAGE INFORMATION FOR STORE',
  `TIME` varchar(100) DEFAULT NULL COMMENT 'PAGE INFORMATION FOR TIME',
  `TIME_DIMNSN` varchar(100) DEFAULT NULL COMMENT 'PAGE INFORMATION FOR TIME PLUS ANY OTHER DIMENSION',
  `CREATED_BY` varchar(45) DEFAULT NULL,
  `CREATED_TIME` timestamp NULL DEFAULT NULL,
  `UPDATED_TIME` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `REPORT_CHART_MAP`
--

LOCK TABLES `REPORT_CHART_MAP` WRITE;
/*!40000 ALTER TABLE `REPORT_CHART_MAP` DISABLE KEYS */;
/*!40000 ALTER TABLE `REPORT_CHART_MAP` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `STORE`
--

DROP TABLE IF EXISTS `STORE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `STORE` (
  `ID` varchar(32) NOT NULL,
  `NAME` varchar(100) DEFAULT NULL,
  `ACCOUNT_ID` varchar(32) DEFAULT NULL,
  `ADDRESS` varchar(200) DEFAULT NULL,
  `STORESORT` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `STORE`
--

LOCK TABLES `STORE` WRITE;
/*!40000 ALTER TABLE `STORE` DISABLE KEYS */;
/*!40000 ALTER TABLE `STORE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TRANS_HISTORY`
--

DROP TABLE IF EXISTS `TRANS_HISTORY`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TRANS_HISTORY` (
  `ID` varchar(32) NOT NULL,
  `ACCOUNT_ID` varchar(32) DEFAULT NULL,
  `TRANS_TIME` timestamp NULL DEFAULT NULL,
  `TRANS_AMOUNT` float DEFAULT NULL,
  `TRANS_CREDIT_AMOUNT` int(11) DEFAULT NULL,
  `CREDIT_AMOUNT` int(11) DEFAULT NULL,
  `CREATED_TIME` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TRANS_HISTORY`
--

LOCK TABLES `TRANS_HISTORY` WRITE;
/*!40000 ALTER TABLE `TRANS_HISTORY` DISABLE KEYS */;
/*!40000 ALTER TABLE `TRANS_HISTORY` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kanas_organization`
--

DROP TABLE IF EXISTS `kanas_organization`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kanas_organization` (
  `id` char(32) NOT NULL,
  `code` varchar(20) DEFAULT NULL,
  `label` varchar(100) DEFAULT NULL,
  `parent_id` char(32) DEFAULT NULL,
  `type` varchar(100) DEFAULT NULL,
  `active_flag` bit(1) NOT NULL DEFAULT b'1',
  `manager_id` char(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kanas_organization`
--

LOCK TABLES `kanas_organization` WRITE;
/*!40000 ALTER TABLE `kanas_organization` DISABLE KEYS */;
/*!40000 ALTER TABLE `kanas_organization` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kanas_rbac_role`
--

DROP TABLE IF EXISTS `kanas_rbac_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kanas_rbac_role` (
  `id` char(32) NOT NULL,
  `label` varchar(100) NOT NULL,
  `description` varchar(200) DEFAULT NULL,
  `admin_flag` bit(1) NOT NULL DEFAULT b'0',
  `active_flag` bit(1) NOT NULL DEFAULT b'1',
  `enable_flag` bit(1) NOT NULL DEFAULT b'1',
  `sort_number` int(11) DEFAULT '1',
  `export_flag` bit(1) DEFAULT b'1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kanas_rbac_role`
--

LOCK TABLES `kanas_rbac_role` WRITE;
/*!40000 ALTER TABLE `kanas_rbac_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `kanas_rbac_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kanas_rbac_role_permission`
--

DROP TABLE IF EXISTS `kanas_rbac_role_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kanas_rbac_role_permission` (
  `id` char(32) NOT NULL,
  `role_id` char(32) DEFAULT NULL,
  `permission_id` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_function_id` (`role_id`,`permission_id`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `kanas_rbac0_role_function_fk` FOREIGN KEY (`role_id`) REFERENCES `kanas_rbac_role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kanas_rbac_role_permission`
--

LOCK TABLES `kanas_rbac_role_permission` WRITE;
/*!40000 ALTER TABLE `kanas_rbac_role_permission` DISABLE KEYS */;
/*!40000 ALTER TABLE `kanas_rbac_role_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kanas_rbac_user_role`
--

DROP TABLE IF EXISTS `kanas_rbac_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kanas_rbac_user_role` (
  `id` char(32) NOT NULL,
  `user_id` char(32) NOT NULL,
  `role_id` char(32) NOT NULL,
  `org_id` char(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `kanas_rbac_user_role_unq` (`user_id`,`role_id`) USING BTREE,
  KEY `user_id` (`user_id`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `kanas_rbac0_user_role_fk` FOREIGN KEY (`user_id`) REFERENCES `kanas_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `kanas_rbac0_user_role_fk1` FOREIGN KEY (`role_id`) REFERENCES `kanas_rbac_role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kanas_rbac_user_role`
--

LOCK TABLES `kanas_rbac_user_role` WRITE;
/*!40000 ALTER TABLE `kanas_rbac_user_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `kanas_rbac_user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kanas_user`
--

DROP TABLE IF EXISTS `kanas_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kanas_user` (
  `id` char(32) NOT NULL,
  `username` varchar(100) NOT NULL,
  `passwd` varchar(128) DEFAULT NULL,
  `display_name` varchar(100) DEFAULT NULL,
  `default_portal` varchar(100) DEFAULT NULL,
  `user_portal` varchar(100) DEFAULT NULL,
  `editor_flag` bit(1) DEFAULT NULL,
  `admin_flag` bit(1) DEFAULT NULL,
  `active_flag` bit(1) NOT NULL DEFAULT b'1',
  `expired_flag` bit(1) NOT NULL DEFAULT b'0',
  `locked_flag` bit(1) NOT NULL DEFAULT b'0',
  `credentials_expired_flag` bit(1) NOT NULL DEFAULT b'0',
  `create_date` datetime DEFAULT NULL,
  `last_login` datetime DEFAULT NULL,
  `last_change_pswd` datetime DEFAULT NULL,
  `login_ip` varchar(20) DEFAULT NULL,
  `login_failure_count` smallint(6) DEFAULT NULL,
  `latest_passwd` varchar(250) DEFAULT NULL,
  `dept_name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kanas_user`
--

LOCK TABLES `kanas_user` WRITE;
/*!40000 ALTER TABLE `kanas_user` DISABLE KEYS */;
INSERT INTO `kanas_user` VALUES ('159a97ea39df4444aa64e8b5fa01ebf8','admin','f814e168ac640505d7249e0c1cfbf437197abd77','绯荤粺绠＄悊鍛�,NULL,NULL,NULL,'','','\0','\0','\0',NULL,'2015-12-06 00:00:00',NULL,NULL,NULL,NULL,NULL),('76d591d5ba164ca78a903b14358329cc','user','24947a0041a8260b47f789654b43da631113cf59','椹唨',NULL,NULL,NULL,'\0','','\0','','\0','2013-04-03 00:00:00',NULL,NULL,NULL,NULL,NULL,NULL),('885cf97b762045a185fca20c7521f0d0','xiaoxiong','d12843751cd942114a608e7a4f20f2e2d8abfaf0','灏忕唺',NULL,NULL,'','','','\0','\0','\0','2013-04-08 00:00:00',NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `kanas_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `spruce_article_attachment`
--

DROP TABLE IF EXISTS `spruce_article_attachment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `spruce_article_attachment` (
  `id` char(32) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `filename` varchar(100) DEFAULT NULL,
  `article_id` char(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_attachment_article_idx` (`article_id`),
  CONSTRAINT `fk_attachment_article` FOREIGN KEY (`article_id`) REFERENCES `spruce_content_article` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `spruce_article_attachment`
--

LOCK TABLES `spruce_article_attachment` WRITE;
/*!40000 ALTER TABLE `spruce_article_attachment` DISABLE KEYS */;
/*!40000 ALTER TABLE `spruce_article_attachment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `spruce_column`
--

DROP TABLE IF EXISTS `spruce_column`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `spruce_column` (
  `id` char(32) NOT NULL,
  `shortname` varchar(45) NOT NULL,
  `label` varchar(100) NOT NULL,
  `content_article_id` char(32) DEFAULT NULL,
  `parent_id` char(32) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `memberlevel` int(11) DEFAULT NULL,
  `sortnum` int(11) DEFAULT NULL,
  `isactive` bit(1) DEFAULT NULL,
  `template_id` char(32) DEFAULT NULL,
  `description` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `shortname_UNIQUE` (`shortname`),
  KEY `fk_column_template_idx` (`template_id`),
  CONSTRAINT `fk_column_template` FOREIGN KEY (`template_id`) REFERENCES `spruce_template` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `spruce_column`
--

LOCK TABLES `spruce_column` WRITE;
/*!40000 ALTER TABLE `spruce_column` DISABLE KEYS */;
INSERT INTO `spruce_column` VALUES ('ee59bee469724825b8208c677dce30d7','news','鏂伴椈涓績',NULL,NULL,'',0,9,'',NULL,'');
/*!40000 ALTER TABLE `spruce_column` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `spruce_content_article`
--

DROP TABLE IF EXISTS `spruce_content_article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `spruce_content_article` (
  `id` char(32) NOT NULL DEFAULT '',
  `title` varchar(100) DEFAULT NULL,
  `author` varchar(100) DEFAULT NULL,
  `content` longtext,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `create_user` char(32) DEFAULT NULL,
  `update_user` char(32) DEFAULT NULL,
  `column_id` char(32) DEFAULT NULL,
  `cover_picture` varchar(200) DEFAULT NULL,
  `sort_num` int(11) DEFAULT NULL,
  `issue_time` datetime DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `top_flag` bit(1) DEFAULT NULL COMMENT 'top or not',
  `keywords` varchar(200) DEFAULT NULL,
  `summary` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `spruce_content_article`
--

LOCK TABLES `spruce_content_article` WRITE;
/*!40000 ALTER TABLE `spruce_content_article` DISABLE KEYS */;
/*!40000 ALTER TABLE `spruce_content_article` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `spruce_navigator`
--

DROP TABLE IF EXISTS `spruce_navigator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `spruce_navigator` (
  `id` char(32) NOT NULL,
  `label` varchar(100) DEFAULT NULL,
  `parent_id` char(32) DEFAULT NULL,
  `type` varchar(20) DEFAULT NULL,
  `link` varchar(255) DEFAULT NULL,
  `sortnum` int(11) DEFAULT NULL,
  `active_flag` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `spruce_navigator`
--

LOCK TABLES `spruce_navigator` WRITE;
/*!40000 ALTER TABLE `spruce_navigator` DISABLE KEYS */;
/*!40000 ALTER TABLE `spruce_navigator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `spruce_template`
--

DROP TABLE IF EXISTS `spruce_template`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `spruce_template` (
  `id` char(32) NOT NULL,
  `name` varchar(100) NOT NULL,
  `template_path` varchar(100) NOT NULL,
  `sort_number` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `spruce_template`
--

LOCK TABLES `spruce_template` WRITE;
/*!40000 ALTER TABLE `spruce_template` DISABLE KEYS */;
/*!40000 ALTER TABLE `spruce_template` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-12-06 22:35:36
