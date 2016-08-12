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

 Date: 04/13/2016 08:24:57 AM
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
--  Records of `ACCOUNT`
-- ----------------------------
BEGIN;
INSERT INTO `ACCOUNT` VALUES ('03421a2a7ebf4bde9b93efb436c55a0e', 'admin', '920d5414a3e5b746c4ad7bed604d084d608f77a1', 'admin', 'admin', null, null, null, null, null, null, '94a97e0aa7f3e378'), ('24182a7a72c94a42bef97dbf16c67613', 'wd', 'db8402962ace82e7315f918d507ea63855c523ca', 'william_d@126.com', '13810053407', null, null, null, null, null, null, 'cf2687dc3fba8e84'), ('e61efbb626fe4ed6a2ab25c4706056f6', 'testtest', 'e94021518b23971e99e13fa47d86468e75afafd9', null, null, null, null, null, null, null, null, 'fc98371af6db22e5');
COMMIT;

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
--  Records of `INDUSTRY`
-- ----------------------------
BEGIN;
INSERT INTO `INDUSTRY` VALUES ('146a2061c9964e4badceef54b825c429', '西点房', '14f9997d735b4f84a82965b22fac5fb4', null, null, null, '2015-12-21 15:40:03', '2015-12-21 15:40:03', '6', '1'), ('14f9997d735b4f84a82965b22fac5fb4', '餐饮', null, null, null, null, '2015-12-21 15:38:53', '2015-12-21 15:38:53', '0', '1'), ('222', '金融', null, null, null, null, '2016-01-23 19:45:43', '2016-01-23 19:45:47', '0', '1'), ('3fc056a6681446c49bc2aec2758b5cd9', '西式快餐', '14f9997d735b4f84a82965b22fac5fb4', null, null, null, '2015-12-21 15:40:00', '2015-12-21 15:40:00', '4', '1'), ('47d3fb5938ad453a99e9d75c4ce4ba8b', '中式正餐', '14f9997d735b4f84a82965b22fac5fb4', null, null, null, '2015-12-21 15:38:57', '2015-12-21 15:38:58', '1', '1'), ('86e2bff6c2424412b516c864e0e4b0c5', '中式快餐', '14f9997d735b4f84a82965b22fac5fb4', null, null, null, '2015-12-21 15:39:58', '2015-12-21 15:39:58', '3', '1'), ('99d14cad8b2944c4ae880f6260d0059f', '银行', '14f9997d735b4f84a82965b22fac5fb4', null, null, null, '2015-12-21 15:40:06', '2015-12-21 15:40:06', '8', '1'), ('d72b9f73db044af0b50e52dc6c7a4bb2', '西式正餐', '14f9997d735b4f84a82965b22fac5fb4', null, null, null, '2015-12-21 15:39:01', '2015-12-21 15:39:01', '2', '1'), ('d8845ebabc624e7e99131f04c8da3909', '咖啡厅', '14f9997d735b4f84a82965b22fac5fb4', null, null, null, '2015-12-21 15:40:01', '2015-12-21 15:40:01', '5', '1'), ('fbdd34ee4e6c4671a38802d6d95a0416', '外卖/订餐服务', '14f9997d735b4f84a82965b22fac5fb4', null, null, null, '2015-12-21 15:40:04', '2015-12-21 15:40:05', '7', '1');
COMMIT;

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
  `QUESTION_TYPE` varchar(100) NOT NULL COMMENT '问题类型',
  `QUESTION_GROUP_ID` varchar(32) NOT NULL,
  `QUSTNR_TEMP_ID` varchar(32) NOT NULL,
  `SORT_NUM` int(11) DEFAULT NULL,
  `NORM_CALC_VAL` float DEFAULT NULL,
  `NORM_INPUT_VAL` varchar(100) DEFAULT NULL,
  `STATUS` varchar(20) DEFAULT NULL,
  `CREATED_BY` varchar(32) DEFAULT NULL,
  `CREATED_TIME` timestamp NULL DEFAULT NULL,
  `UPDATED_TIME` timestamp NULL DEFAULT NULL,
  `CHART_ID` varchar(32) DEFAULT NULL,
  `top_flag` bit(1) NOT NULL COMMENT '是否显示置顶按钮',
  `edit_flag` bit(1) NOT NULL COMMENT '该模板问题是否用户是否可以编辑，0：可以编辑 1不可以编辑，默认值为1',
  `chart_one_dimnsn` varchar(100) DEFAULT NULL COMMENT '单维度图表ID',
  `chart_multi_dimnsn` varchar(100) DEFAULT NULL COMMENT '多维度图表',
  `chart_store` varchar(100) DEFAULT NULL COMMENT '门店维度图表',
  `chart_time` varchar(100) DEFAULT NULL COMMENT '时间维度图表',
  `chart_time_dimnsn` varchar(100) DEFAULT NULL COMMENT '时间多维度图表',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `QUESTION_GROUP`
-- ----------------------------
DROP TABLE IF EXISTS `QUESTION_GROUP`;
CREATE TABLE `QUESTION_GROUP` (
  `ID` varchar(32) NOT NULL,
  `NAME` varchar(100) DEFAULT NULL,
  `DISPLAY_VAL` varchar(100) DEFAULT NULL,
  `SORT_NUM` int(11) DEFAULT NULL,
  `CREATED_BY` varchar(32) DEFAULT NULL,
  `CREATED_TIME` timestamp NULL DEFAULT NULL,
  `UPDATED_TIME` timestamp NULL DEFAULT NULL,
  `TYPE` bit(1) DEFAULT NULL,
  `qustnr_templt_id` varchar(32) NOT NULL,
  `active_flag` bit(1) DEFAULT NULL,
  `norm_calc_val` float DEFAULT NULL,
  `norm_input_val` float DEFAULT NULL,
  `custom_question_type` varchar(100) DEFAULT NULL,
  `chart_one_dimnsn` varchar(100) DEFAULT NULL COMMENT '单维度图表ID',
  `chart_multi_dimnsn` varchar(100) DEFAULT NULL COMMENT '多维度图表',
  `chart_store` varchar(100) DEFAULT NULL COMMENT '门店维度图表',
  `chart_time` varchar(100) DEFAULT NULL COMMENT '时间维度图表',
  `chart_time_dimnsn` varchar(100) DEFAULT NULL COMMENT '时间多维度图表',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `QUESTION_GROUP`
-- ----------------------------
BEGIN;
INSERT INTO `QUESTION_GROUP` VALUES ('08801b49177c455ebdc938a3ab7c2001', 'asda', 'DDDDDASD', null, null, '2016-04-04 22:15:14', '2016-04-04 22:15:14', b'1', '2', b'1', null, null, 'com.myb.questiontype.Degree', 'P1', '123', '123', '123', '123'), ('1', '顾客总体健康度', '顾客与店家的整体关系是否健康?', '1', null, '2015-12-30 11:32:39', '2015-12-30 11:32:42', b'1', '', b'1', null, null, 'com.myb.grouptype.MultiSelect', null, null, null, null, null), ('10', '顾客消费习惯及背景调查', null, '5', null, '2016-01-26 16:18:39', '2016-01-26 16:18:41', b'1', '14831289084643edaf2f50ef598fc65a', b'1', null, null, 'com.myb.grouptype.MultiSelect', null, null, null, null, null), ('2', '顾客满意度细项表现', '您对该大酒?', '2', null, '2015-12-30 11:32:44', '2015-12-30 11:32:46', b'0', '', b'1', null, null, 'com.myb.grouptype.Degree', null, null, null, null, null), ('3', '服务规范评价', null, '3', null, '2015-12-30 11:32:49', '2015-12-30 11:32:51', b'1', '', b'1', null, null, 'com.myb.grouptype.Judge', null, null, null, null, null), ('4', '顾客之声', null, '4', null, '2015-12-30 11:32:53', '2015-12-30 11:32:55', b'1', '', b'1', null, null, 'com.myb.grouptype.TextAnswer', null, null, null, null, null), ('5', '顾客消费习惯及背景调查', null, '5', null, '2016-01-26 16:18:39', '2016-01-26 16:18:41', b'1', '', b'1', null, null, 'com.myb.grouptype.MultiSelect', null, null, null, null, null), ('503efa74b68041a5b27fcc031ee5599b', 'ASDASD', 'DDDA', null, null, '2016-04-04 23:50:37', '2016-04-04 23:50:37', b'1', '2', b'1', null, null, 'com.myb.questiontype.TextAnswer', '', '', '', '', ''), ('6', '顾客总体健康度', '顾客与店家的整体关系是否健康?', '1', null, '2015-12-30 11:32:39', '2015-12-30 11:32:42', b'1', '14831289084643edaf2f50ef598fc65a', b'1', null, null, 'com.myb.grouptype.MultiSelect', null, null, null, null, null), ('7', '顾客满意度细项表现', '您对该大酒?', '2', null, '2015-12-30 11:32:44', '2015-12-30 11:32:46', b'0', '14831289084643edaf2f50ef598fc65a', b'1', null, null, 'com.myb.grouptype.Degree', null, null, null, null, null), ('8', '服务规范评价', null, '3', null, '2015-12-30 11:32:49', '2015-12-30 11:32:51', b'1', '14831289084643edaf2f50ef598fc65a', b'1', null, null, 'com.myb.grouptype.Judge', null, null, null, null, null), ('8d2e01a9db774fc6813d73baa8e70055', '阿斯达按时aaa', null, null, null, '2016-04-04 23:29:11', '2016-04-04 23:29:11', b'1', '2', b'1', null, null, 'com.myb.questiontype.TextAnswer', '', '', '', '', ''), ('9', '顾客之声', null, '4', null, '2015-12-30 11:32:53', '2015-12-30 11:32:55', b'1', '14831289084643edaf2f50ef598fc65a', b'1', null, null, 'com.myb.grouptype.TextAnswer', null, null, null, null, null);
COMMIT;

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
--  Records of `QUSTNR_TMPLT`
-- ----------------------------
BEGIN;
INSERT INTO `QUSTNR_TMPLT` VALUES ('1', '中式快餐模板', '86e2bff6c2424412b516c864e0e4b0c5', 'Y', null, '2015-12-30 11:30:36', '2015-12-30 11:30:38'), ('14831289084643edaf2f50ef598fc65a', '中式正餐模板', '47d3fb5938ad453a99e9d75c4ce4ba8b', 'Y', null, '2016-02-20 17:34:43', '2016-02-20 17:34:43'), ('2', '金融模板', '222', 'Y', null, '2016-01-23 19:44:43', '2016-01-23 19:44:47'), ('d900bf3eeebf4e0aa219cfa01204e421', 'asda', 'd72b9f73db044af0b50e52dc6c7a4bb2', 'N', null, '2016-02-27 20:14:15', '2016-02-27 20:14:15');
COMMIT;

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
--  Records of `STORE`
-- ----------------------------
BEGIN;
INSERT INTO `STORE` VALUES ('123', '十里堡', '03421a2a7ebf4bde9b93efb436c55a0e', '地址1', '1'), ('2', '三里屯', '03421a2a7ebf4bde9b93efb436c55a0e', '地址2', '2');
COMMIT;

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
--  Records of `STOREGROUP`
-- ----------------------------
BEGIN;
INSERT INTO `STOREGROUP` VALUES ('16c88f573abe4cd69b8cc4ae2741ae7c', '分组1', '03421a2a7ebf4bde9b93efb436c55a0e', '0'), ('f17c54eb044646dea7ec3be3b315c54f', '分组2', '03421a2a7ebf4bde9b93efb436c55a0e', '0');
COMMIT;

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
--  Records of `STOREGROUP_MAP`
-- ----------------------------
BEGIN;
INSERT INTO `STOREGROUP_MAP` VALUES ('123', '16c88f573abe4cd69b8cc4ae2741ae7c'), ('123', 'f17c54eb044646dea7ec3be3b315c54f');
COMMIT;

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
--  Records of `kanas_user`
-- ----------------------------
BEGIN;
INSERT INTO `kanas_user` VALUES ('159a97ea39df4444aa64e8b5fa01ebf8', 'admin', 'f814e168ac640505d7249e0c1cfbf437197abd77', '系统管理员', null, null, null, b'1', b'1', b'0', b'0', b'0', null, '2016-04-05 00:00:00', null, null, null, null, null);
COMMIT;

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
--  Records of `spruce_column`
-- ----------------------------
BEGIN;
INSERT INTO `spruce_column` VALUES ('60a4098a71654a4ba974f97c06647497', 'pns', '产品与服务', null, null, null, '0', '1', null, null, 'aasdf asd fsa d');
COMMIT;

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
--  Records of `spruce_content_article`
-- ----------------------------
BEGIN;
INSERT INTO `spruce_content_article` VALUES ('61dfdfef6808bdd42020dec29837abb3', 'test', '', 's fsf asd fsad fsad ', '2016-03-05 10:17:13', '2016-03-05 10:17:13', '159a97ea39df4444aa64e8b5fa01ebf8', '159a97ea39df4444aa64e8b5fa01ebf8', '60a4098a71654a4ba974f97c06647497', null, null, '2016-03-05 00:00:00', null, null, '', '');
COMMIT;

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
--  Records of `spruce_template`
-- ----------------------------
BEGIN;
INSERT INTO `spruce_template` VALUES ('c43bd097eba3401394be8091e590b3f9', 'fdsdsdsds', 'business_group/article', '0');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
