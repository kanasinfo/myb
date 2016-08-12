/*
 Navicat Premium Data Transfer

 Source Server         : myb
 Source Server Type    : MySQL
 Source Server Version : 50628
 Source Host           : 115.29.41.183
 Source Database       : mybdb

 Target Server Type    : MySQL
 Target Server Version : 50628
 File Encoding         : utf-8

 Date: 03/05/2016 16:59:44 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `ACCOUNT`
-- ----------------------------
DROP TABLE IF EXISTS `ACCOUNT`;
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

-- ----------------------------
--  Table structure for `INDUSTRY`
-- ----------------------------
DROP TABLE IF EXISTS `INDUSTRY`;
CREATE TABLE `INDUSTRY` (
  `ID` varchar(32) NOT NULL,
  `NAME` varchar(100) DEFAULT NULL,
  `PARENT_ID` varchar(32) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `description` varchar(300) DEFAULT NULL,
  `CREATED_BY` varchar(32) DEFAULT NULL,
  `CREATED_TIME` timestamp NULL DEFAULT NULL,
  `UPDATED_TIME` timestamp NULL DEFAULT NULL,
  `SORTNUMBER` int(11) DEFAULT NULL,
  `isactive` int(1) DEFAULT '1',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `kanas_organization`
-- ----------------------------
DROP TABLE IF EXISTS `kanas_organization`;
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

-- ----------------------------
--  Table structure for `kanas_rbac_role`
-- ----------------------------
DROP TABLE IF EXISTS `kanas_rbac_role`;
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

-- ----------------------------
--  Table structure for `kanas_rbac_role_permission`
-- ----------------------------
DROP TABLE IF EXISTS `kanas_rbac_role_permission`;
CREATE TABLE `kanas_rbac_role_permission` (
  `id` char(32) NOT NULL,
  `role_id` char(32) DEFAULT NULL,
  `permission_id` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_function_id` (`role_id`,`permission_id`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `kanas_rbac0_role_function_fk` FOREIGN KEY (`role_id`) REFERENCES `kanas_rbac_role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `kanas_rbac_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `kanas_rbac_user_role`;
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

-- ----------------------------
--  Table structure for `kanas_user`
-- ----------------------------
DROP TABLE IF EXISTS `kanas_user`;
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

-- ----------------------------
--  Table structure for `OPTIONS`
-- ----------------------------
DROP TABLE IF EXISTS `OPTIONS`;
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

-- ----------------------------
--  Table structure for `QUESTION`
-- ----------------------------
DROP TABLE IF EXISTS `QUESTION`;
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
  `CHART_ID` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `QUESTION_STORE_CODE`
-- ----------------------------
DROP TABLE IF EXISTS `QUESTION_STORE_CODE`;
CREATE TABLE `QUESTION_STORE_CODE` (
  `id` varchar(32) NOT NULL,
  `question_id` varchar(32) NOT NULL,
  `store_id` varchar(32) DEFAULT NULL,
  `store_name` varchar(100) DEFAULT NULL,
  `path` varchar(1000) DEFAULT NULL,
  `code_path` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `QUSTNR_GROUP`
-- ----------------------------
DROP TABLE IF EXISTS `QUSTNR_GROUP`;
CREATE TABLE `QUSTNR_GROUP` (
  `ID` varchar(32) NOT NULL,
  `NAME` varchar(100) DEFAULT NULL,
  `DISPLAY_VAL` varchar(100) DEFAULT NULL,
  `SORT_NUM` int(11) DEFAULT NULL,
  `CREATED_BY` varchar(32) DEFAULT NULL,
  `CREATED_TIME` timestamp NULL DEFAULT NULL,
  `UPDATED_TIME` timestamp NULL DEFAULT NULL,
  `TYPE` varchar(100) DEFAULT NULL,
  `CHART_ID` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `QUSTNR_GROUP_NORM`
-- ----------------------------
DROP TABLE IF EXISTS `QUSTNR_GROUP_NORM`;
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

-- ----------------------------
--  Table structure for `QUSTNR_TMPLT`
-- ----------------------------
DROP TABLE IF EXISTS `QUSTNR_TMPLT`;
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

-- ----------------------------
--  Table structure for `REPORT_CHART_MAP`
-- ----------------------------
DROP TABLE IF EXISTS `REPORT_CHART_MAP`;
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

-- ----------------------------
--  Table structure for `spruce_article_attachment`
-- ----------------------------
DROP TABLE IF EXISTS `spruce_article_attachment`;
CREATE TABLE `spruce_article_attachment` (
  `id` char(32) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `filename` varchar(100) DEFAULT NULL,
  `article_id` char(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_attachment_article_idx` (`article_id`),
  CONSTRAINT `fk_attachment_article` FOREIGN KEY (`article_id`) REFERENCES `spruce_content_article` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `spruce_column`
-- ----------------------------
DROP TABLE IF EXISTS `spruce_column`;
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

-- ----------------------------
--  Table structure for `spruce_content_article`
-- ----------------------------
DROP TABLE IF EXISTS `spruce_content_article`;
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

-- ----------------------------
--  Table structure for `spruce_member`
-- ----------------------------
DROP TABLE IF EXISTS `spruce_member`;
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

-- ----------------------------
--  Table structure for `spruce_member_type`
-- ----------------------------
DROP TABLE IF EXISTS `spruce_member_type`;
CREATE TABLE `spruce_member_type` (
  `id` char(32) NOT NULL,
  `label` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `spruce_navigator`
-- ----------------------------
DROP TABLE IF EXISTS `spruce_navigator`;
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

-- ----------------------------
--  Table structure for `spruce_template`
-- ----------------------------
DROP TABLE IF EXISTS `spruce_template`;
CREATE TABLE `spruce_template` (
  `id` char(32) NOT NULL,
  `name` varchar(100) NOT NULL,
  `template_path` varchar(100) NOT NULL,
  `sort_number` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `STORE`
-- ----------------------------
DROP TABLE IF EXISTS `STORE`;
CREATE TABLE `STORE` (
  `ID` varchar(32) NOT NULL,
  `NAME` varchar(100) DEFAULT NULL,
  `ACCOUNT_ID` varchar(32) DEFAULT NULL,
  `ADDRESS` varchar(200) DEFAULT NULL,
  `STORESORT` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `STOREGROUP`
-- ----------------------------
DROP TABLE IF EXISTS `STOREGROUP`;
CREATE TABLE `STOREGROUP` (
  `ID` varchar(32) NOT NULL,
  `NAME` varchar(100) NOT NULL,
  `ACCOUNT_ID` varchar(32) DEFAULT NULL,
  `SORTNUMBER` int(10) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `STOREGROUP_MAP`
-- ----------------------------
DROP TABLE IF EXISTS `STOREGROUP_MAP`;
CREATE TABLE `STOREGROUP_MAP` (
  `STORE_ID` varchar(32) NOT NULL,
  `STOREGROUP_ID` varchar(32) NOT NULL,
  KEY `STOREGROUP_ID` (`STOREGROUP_ID`),
  KEY `STORE_ID` (`STORE_ID`),
  CONSTRAINT `FK_STORE` FOREIGN KEY (`STORE_ID`) REFERENCES `STORE` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_StoreGroup_id` FOREIGN KEY (`STOREGROUP_ID`) REFERENCES `STOREGROUP` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `TRANS_HISTORY`
-- ----------------------------
DROP TABLE IF EXISTS `TRANS_HISTORY`;
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

SET FOREIGN_KEY_CHECKS = 1;
