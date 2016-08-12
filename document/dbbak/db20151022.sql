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
-- Table structure for table `kanas_calendar`
--

DROP TABLE IF EXISTS `kanas_calendar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kanas_calendar` (
  `id` char(32) NOT NULL,
  `affair` varchar(200) DEFAULT NULL,
  `workday_flag` bit(1) NOT NULL,
  `begin_date` date NOT NULL,
  `end_date` date NOT NULL,
  `org_id` char(32) DEFAULT NULL,
  `rule_flag` bit(1) DEFAULT b'0',
  `sunday` bit(1) DEFAULT NULL,
  `monday` bit(1) DEFAULT NULL,
  `tuesday` bit(1) DEFAULT NULL,
  `wednesday` bit(1) DEFAULT NULL,
  `thursday` bit(1) DEFAULT NULL,
  `friday` bit(1) DEFAULT NULL,
  `saturday` bit(1) DEFAULT NULL,
  `sort_number` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 PACK_KEYS=0;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kanas_calendar`
--

LOCK TABLES `kanas_calendar` WRITE;
/*!40000 ALTER TABLE `kanas_calendar` DISABLE KEYS */;
/*!40000 ALTER TABLE `kanas_calendar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kanas_data_dict_type`
--

DROP TABLE IF EXISTS `kanas_data_dict_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kanas_data_dict_type` (
  `id` char(32) NOT NULL,
  `type_code` varchar(100) NOT NULL,
  `label` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `type_code` (`type_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kanas_data_dict_type`
--

LOCK TABLES `kanas_data_dict_type` WRITE;
/*!40000 ALTER TABLE `kanas_data_dict_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `kanas_data_dict_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kanas_data_dict_value`
--

DROP TABLE IF EXISTS `kanas_data_dict_value`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kanas_data_dict_value` (
  `id` char(32) NOT NULL,
  `code` varchar(100) DEFAULT NULL,
  `type_id` char(32) DEFAULT NULL,
  `sort_number` int(11) NOT NULL DEFAULT '0',
  `active_flag` bit(1) NOT NULL DEFAULT b'1',
  `label` varchar(100) DEFAULT NULL,
  `parent_id` char(32) DEFAULT NULL,
  `org_id` char(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unq_profile_dd_v_type_n_code` (`code`,`type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kanas_data_dict_value`
--

LOCK TABLES `kanas_data_dict_value` WRITE;
/*!40000 ALTER TABLE `kanas_data_dict_value` DISABLE KEYS */;
/*!40000 ALTER TABLE `kanas_data_dict_value` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kanas_employee`
--

DROP TABLE IF EXISTS `kanas_employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kanas_employee` (
  `id` char(32) NOT NULL,
  `emp_number` varchar(20) NOT NULL,
  `fullname` varchar(100) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `first_name` varchar(50) DEFAULT NULL,
  `id_type` varchar(100) DEFAULT NULL,
  `id_number` varchar(20) DEFAULT NULL,
  `organization_id` char(32) DEFAULT NULL,
  `active_flag` bit(1) NOT NULL DEFAULT b'1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `emp_number` (`emp_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kanas_employee`
--

LOCK TABLES `kanas_employee` WRITE;
/*!40000 ALTER TABLE `kanas_employee` DISABLE KEYS */;
/*!40000 ALTER TABLE `kanas_employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kanas_i18n_resource`
--

DROP TABLE IF EXISTS `kanas_i18n_resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kanas_i18n_resource` (
  `id` char(32) NOT NULL,
  `code` varchar(100) DEFAULT NULL,
  `lang` varchar(5) DEFAULT NULL,
  `label` varchar(255) DEFAULT NULL,
  `scope` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unq_profile_dd_lang` (`code`,`lang`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kanas_i18n_resource`
--

LOCK TABLES `kanas_i18n_resource` WRITE;
/*!40000 ALTER TABLE `kanas_i18n_resource` DISABLE KEYS */;
/*!40000 ALTER TABLE `kanas_i18n_resource` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kanas_img_link`
--

DROP TABLE IF EXISTS `kanas_img_link`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kanas_img_link` (
  `id` varchar(32) NOT NULL,
  `type` varchar(10) DEFAULT NULL,
  `label` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kanas_img_link`
--

LOCK TABLES `kanas_img_link` WRITE;
/*!40000 ALTER TABLE `kanas_img_link` DISABLE KEYS */;
INSERT INTO `kanas_img_link` VALUES ('dw230a9382ua8212u919iaddue388666','FLOATWIN','首页浮窗'),('dw230a9382ua8212u919iaddue388888','IMGNEWS','图片新闻'),('dw230a9382ua8212u919iaddue388999','IMGLINK','图片链接');
/*!40000 ALTER TABLE `kanas_img_link` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kanas_img_link_data`
--

DROP TABLE IF EXISTS `kanas_img_link_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kanas_img_link_data` (
  `id` char(32) NOT NULL,
  `label` varchar(100) DEFAULT NULL,
  `picture` varchar(100) DEFAULT NULL,
  `url` varchar(100) DEFAULT NULL,
  `img_link_id` char(32) DEFAULT NULL,
  `position` char(20) DEFAULT NULL,
  `is_show` bit(1) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kanas_img_link_data`
--

LOCK TABLES `kanas_img_link_data` WRITE;
/*!40000 ALTER TABLE `kanas_img_link_data` DISABLE KEYS */;
INSERT INTO `kanas_img_link_data` VALUES ('15d34f51e2514cf584ba59be9663ffd3','我军苏27坠机','2013/15d34f51e2514cf584ba59be9663ffd3.jpg','http://news.china.com.cn/live/2013-04/03/content_19357804_2.htm','dw230a9382ua8212u919iaddue388888','1','','2013-04-03 00:00:00'),('337bbaafe7584b77bdf0781cf194628d','交管局政治处','2013/337bbaafe7584b77bdf0781cf194628d.gif','#','dw230a9382ua8212u919iaddue388999','5','','2013-04-03 00:00:00'),('516b1fcb2ce14842a4d64199d3b5fa68','巴西大客车事故','2013/516b1fcb2ce14842a4d64199d3b5fa68.jpg','http://news.dayoo.com/world/57402/201304/03/57402_109955821.htm','dw230a9382ua8212u919iaddue388888','2','','2013-04-03 00:00:00'),('5191a1b606eb42cc9b5606a321e05661','交管局办公室','2013/5191a1b606eb42cc9b5606a321e05661.gif','#','dw230a9382ua8212u919iaddue388999','4','','2013-04-03 00:00:00'),('6564359d5a3e4da09395852eadd82e4c','三项重点工作','2013/6564359d5a3e4da09395852eadd82e4c.gif','#','dw230a9382ua8212u919iaddue388999','3','','2013-04-03 00:00:00'),('8913e60b8d794da88951bd99cf959573','纪律作风教育','2013/8913e60b8d794da88951bd99cf959573.gif','#','dw230a9382ua8212u919iaddue388999','2','','2013-04-03 00:00:00'),('9110abec7b9f4525a4204a43e2f6eabe','党务公开专栏','2013/9110abec7b9f4525a4204a43e2f6eabe.jpg','#','dw230a9382ua8212u919iaddue388999','1','','2013-04-03 00:00:00'),('9cea93b714de47dbaf2d0bea84354233','123','2013/9cea93b714de47dbaf2d0bea84354233.png','http://www.baidu.com','dw230a9382ua8212u919iaddue388666','100','','2013-04-03 00:00:00'),('c1ea408ba50640b6939f59f5780598a0','西藏山体滑坡','2013/c1ea408ba50640b6939f59f5780598a0.jpg','http://native.cnr.cn/pic/201304/t20130403_512282523.html','dw230a9382ua8212u919iaddue388888','3','','2013-04-03 00:00:00'),('d0f9da2502f348778c5b934dd8871fed','333','2013/d0f9da2502f348778c5b934dd8871fed.jpg',NULL,'dw230a9382ua8212u919iaddue388666','333','',NULL);
/*!40000 ALTER TABLE `kanas_img_link_data` ENABLE KEYS */;
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
-- Table structure for table `kanas_properties`
--

DROP TABLE IF EXISTS `kanas_properties`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kanas_properties` (
  `p_key` varchar(20) NOT NULL,
  `p_value` varchar(200) DEFAULT NULL,
  `type` varchar(20) DEFAULT NULL COMMENT 'text,password,number',
  `validator` varchar(100) DEFAULT NULL,
  `group_code` varchar(20) NOT NULL,
  `sort_number` int(11) DEFAULT NULL,
  PRIMARY KEY (`p_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kanas_properties`
--

LOCK TABLES `kanas_properties` WRITE;
/*!40000 ALTER TABLE `kanas_properties` DISABLE KEYS */;
/*!40000 ALTER TABLE `kanas_properties` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kanas_properties_group`
--

DROP TABLE IF EXISTS `kanas_properties_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kanas_properties_group` (
  `code` varchar(20) NOT NULL,
  `sort_number` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kanas_properties_group`
--

LOCK TABLES `kanas_properties_group` WRITE;
/*!40000 ALTER TABLE `kanas_properties_group` DISABLE KEYS */;
/*!40000 ALTER TABLE `kanas_properties_group` ENABLE KEYS */;
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
-- Table structure for table `kanas_rbac_role_inherits`
--

DROP TABLE IF EXISTS `kanas_rbac_role_inherits`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kanas_rbac_role_inherits` (
  `role_id` char(32) NOT NULL,
  `include_role_id` char(32) NOT NULL,
  PRIMARY KEY (`role_id`,`include_role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kanas_rbac_role_inherits`
--

LOCK TABLES `kanas_rbac_role_inherits` WRITE;
/*!40000 ALTER TABLE `kanas_rbac_role_inherits` DISABLE KEYS */;
/*!40000 ALTER TABLE `kanas_rbac_role_inherits` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kanas_rbac_role_mutex`
--

DROP TABLE IF EXISTS `kanas_rbac_role_mutex`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kanas_rbac_role_mutex` (
  `role_id` char(32) NOT NULL,
  `group` char(32) NOT NULL,
  PRIMARY KEY (`role_id`,`group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kanas_rbac_role_mutex`
--

LOCK TABLES `kanas_rbac_role_mutex` WRITE;
/*!40000 ALTER TABLE `kanas_rbac_role_mutex` DISABLE KEYS */;
/*!40000 ALTER TABLE `kanas_rbac_role_mutex` ENABLE KEYS */;
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
  UNIQUE KEY `kanas_rbac_user_role_unq` (`user_id`,`role_id`,`org_id`) USING BTREE,
  KEY `user_id` (`user_id`),
  KEY `role_id` (`role_id`),
  KEY `kanas_rbac_user_role_fk1` (`org_id`),
  CONSTRAINT `kanas_rbac0_user_role_fk` FOREIGN KEY (`user_id`) REFERENCES `kanas_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `kanas_rbac0_user_role_fk1` FOREIGN KEY (`role_id`) REFERENCES `kanas_rbac_role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `kanas_rbac_user_role_fk1` FOREIGN KEY (`org_id`) REFERENCES `kanas_organization` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
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
-- Table structure for table `kanas_sequence`
--

DROP TABLE IF EXISTS `kanas_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kanas_sequence` (
  `id` char(32) NOT NULL,
  `code` varchar(64) NOT NULL,
  `label` varchar(200) DEFAULT NULL,
  `current_value` bigint(20) DEFAULT NULL,
  `reset_interval` char(1) DEFAULT NULL COMMENT 'y,m,d,null',
  `last_update` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 PACK_KEYS=0;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kanas_sequence`
--

LOCK TABLES `kanas_sequence` WRITE;
/*!40000 ALTER TABLE `kanas_sequence` DISABLE KEYS */;
/*!40000 ALTER TABLE `kanas_sequence` ENABLE KEYS */;
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
INSERT INTO `kanas_user` VALUES ('159a97ea39df4444aa64e8b5fa01ebf8','admin','f814e168ac640505d7249e0c1cfbf437197abd77','系统管理员',NULL,NULL,NULL,'','','\0','\0','\0',NULL,NULL,NULL,NULL,NULL,NULL,NULL),('76d591d5ba164ca78a903b14358329cc','user','24947a0041a8260b47f789654b43da631113cf59','马冀',NULL,NULL,NULL,'\0','','\0','','\0','2013-04-03 00:00:00',NULL,NULL,NULL,NULL,NULL,NULL),('885cf97b762045a185fca20c7521f0d0','xiaoxiong','d12843751cd942114a608e7a4f20f2e2d8abfaf0','小熊',NULL,NULL,'','','','\0','\0','\0','2013-04-08 00:00:00',NULL,NULL,NULL,NULL,NULL,NULL);
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
INSERT INTO `spruce_column` VALUES ('ee59bee469724825b8208c677dce30d7','news','新闻中心',NULL,NULL,'',0,9,'',NULL,'');
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
-- Table structure for table `spruce_link`
--

DROP TABLE IF EXISTS `spruce_link`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `spruce_link` (
  `id` char(32) NOT NULL,
  `link_type_id` char(32) NOT NULL,
  `label` varchar(50) DEFAULT NULL,
  `weburl` varchar(200) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  `img_url` varchar(200) DEFAULT NULL,
  `sort_num` int(11) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_link2type_idx` (`link_type_id`),
  CONSTRAINT `fk_link2type` FOREIGN KEY (`link_type_id`) REFERENCES `spruce_link_type` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `spruce_link`
--

LOCK TABLES `spruce_link` WRITE;
/*!40000 ALTER TABLE `spruce_link` DISABLE KEYS */;
/*!40000 ALTER TABLE `spruce_link` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `spruce_link_type`
--

DROP TABLE IF EXISTS `spruce_link_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `spruce_link_type` (
  `ID` char(32) NOT NULL,
  `label` varchar(50) DEFAULT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `img_width` int(11) DEFAULT NULL,
  `img_height` int(11) DEFAULT NULL,
  `img_force_scale` bit(1) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `spruce_link_type`
--

LOCK TABLES `spruce_link_type` WRITE;
/*!40000 ALTER TABLE `spruce_link_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `spruce_link_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `spruce_member`
--

DROP TABLE IF EXISTS `spruce_member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `spruce_member` (
  `id` char(32) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `address_level1` varchar(50) DEFAULT NULL,
  `address_level2` varchar(50) DEFAULT NULL,
  `website` varchar(100) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  `contact_name` varchar(50) DEFAULT NULL,
  `contact_phone` varchar(50) DEFAULT NULL,
  `contact_email` varchar(100) DEFAULT NULL,
  `login_name` varchar(100) DEFAULT NULL,
  `login_passwd` varchar(100) DEFAULT NULL,
  `member_type_id` char(32) DEFAULT NULL,
  `member_level` int(11) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `member_status` varchar(10) DEFAULT NULL COMMENT 'LOCK,ACTIVE,DEACTIVE',
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_name_UNIQUE` (`login_name`),
  KEY `fk_member2type_idx` (`member_type_id`),
  CONSTRAINT `fk_member2type` FOREIGN KEY (`member_type_id`) REFERENCES `spruce_member_type` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `spruce_member`
--

LOCK TABLES `spruce_member` WRITE;
/*!40000 ALTER TABLE `spruce_member` DISABLE KEYS */;
/*!40000 ALTER TABLE `spruce_member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `spruce_member_type`
--

DROP TABLE IF EXISTS `spruce_member_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `spruce_member_type` (
  `id` char(32) NOT NULL,
  `label` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `spruce_member_type`
--

LOCK TABLES `spruce_member_type` WRITE;
/*!40000 ALTER TABLE `spruce_member_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `spruce_member_type` ENABLE KEYS */;
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

-- Dump completed on 2015-10-22 21:05:58
