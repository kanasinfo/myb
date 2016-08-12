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

 Date: 03/17/2016 00:06:58 AM
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
--  Records of `OPTIONS`
-- ----------------------------
BEGIN;
INSERT INTO `OPTIONS` VALUES ('020b3b1fdc564039ba45ff0aa65a8d3c', '', '9', 'dd410c33519a4c8c827565bee8fcbf0d', '9', null, '2016-02-20 17:49:17', '2016-02-20 17:49:17'), ('0228abdd37c34dd3be57c1a5985b0c49', '一般', '6', '106b28d409334b758e9fbe057e76a66e', '6', null, '2016-02-20 17:52:09', '2016-02-20 17:52:09'), ('030aa7891f15439abd83a84e21036283', '', '2', '106b28d409334b758e9fbe057e76a66e', '2', null, '2016-02-20 17:52:09', '2016-02-20 17:52:09'), ('040827c2394843b6b3d8a4e628e1e9ca', '和其他人', '和其他人', '0dd238b4fd7747e7910adc5115f3fca8', '6', null, '2016-02-20 18:07:12', '2016-02-20 18:07:12'), ('044fe52237af44db9a517ece80bc396e', '非常不满意', '1', '106b28d409334b758e9fbe057e76a66e', '1', null, '2016-02-20 17:52:09', '2016-02-20 17:52:09'), ('05a8847aada145948d3d1d4835d8e8dd', '', '8', '106b28d409334b758e9fbe057e76a66e', '8', null, '2016-02-20 17:52:09', '2016-02-20 17:52:09'), ('05ff9bf5be0a450a924814fa978b2021', '21-25岁', '21-25岁', '46098a2fca774be8aa38359cadc8282b', '2', null, '2016-02-27 20:20:11', '2016-02-27 20:20:11'), ('099123e4eb1d40aa951bd44e35e538c5', '', '4', '8f138714ff4442cf862005532df7a15d', '4', null, '2016-02-20 17:49:55', '2016-02-20 17:49:55'), ('0f5ea082d9fc4afd83abff82c57b22b5', '', '6', '5058d0b8d5944ac1bea5a059eb3f6d7c', '6', null, '2016-03-05 11:07:51', '2016-03-05 11:07:51'), ('1', '非常不满意', '1', '1', '1', null, '2015-12-30 11:40:50', '2015-12-30 11:40:59'), ('10', '不常来，但也不是第一次', '不常来，但也不是第一次', '4', '2', null, '2015-12-30 11:45:11', '2015-12-30 11:45:18'), ('11', '经常来', '经常来', '4', '3', null, '2015-12-30 11:45:14', '2015-12-30 11:45:21'), ('12', '多选', '多选', '5', '1', null, '2016-02-18 16:19:50', '2016-02-18 16:19:52'), ('16bafdc09c5c4a0c89f6591dac46f1ca', '一般', '6', '17841a0255014490905ecf3fe8308232', '6', null, '2016-02-20 17:53:05', '2016-02-20 17:53:05'), ('192c2652f67e4d7d839dcf382e09defd', '', '5', '89f6202c86b64441b297ee92b230eeef', '5', null, '2016-02-20 17:51:24', '2016-02-20 17:51:24'), ('19501d5228a143b992fdd3cef1fcd417', '', '3', '5058d0b8d5944ac1bea5a059eb3f6d7c', '3', null, '2016-03-05 11:07:51', '2016-03-05 11:07:51'), ('1a0c088c4658465a88b3b5b633636cb4', '', '7', 'b7534ce2fa014341988b9f7025584e18', '7', null, '2016-02-27 20:37:00', '2016-02-27 20:37:00'), ('1a842590e85b4f5d86c62beb083554f0', '是', '1', '986312c916b94728ab09810d8f5da4b8', '2', null, '2016-02-20 17:56:03', '2016-02-20 17:56:03'), ('1cc61cb85cb74ece8819c506a659744f', '', '3', '26bd78065f254bd4a8d4b4e16c72990b', '3', null, '2016-02-20 17:53:58', '2016-02-20 17:53:58'), ('1eae7b5469924531974fa1e17f302ab2', '非常满意', '10', '79a100802e7a4c3e98f72ad6a621b59c', '10', null, '2016-02-20 17:55:22', '2016-02-20 17:55:22'), ('2', '一般', '5', '1', '2', null, '2015-12-30 11:40:53', '2015-12-30 11:41:02'), ('20d83ad1f7944d058b776fa7452b8cd2', '间餐', '间餐', '3c77a9cb363f48c3ad6edcf9518371c3', '4', null, '2016-02-20 18:08:41', '2016-02-20 18:08:41'), ('21d25646e7c94453b67ffae653886fe5', '先生', '先生', '24ad65ae8b49495a80205a2e472fcda3', '1', null, '2016-02-20 17:59:55', '2016-02-20 17:59:55'), ('265073dd41f746a3a28c6bac6c81a61e', '', '8', '89f6202c86b64441b297ee92b230eeef', '8', null, '2016-02-20 17:51:24', '2016-02-20 17:51:24'), ('2747092e718c4aa69c65190365f3e920', '', '4', '5058d0b8d5944ac1bea5a059eb3f6d7c', '4', null, '2016-03-05 11:07:51', '2016-03-05 11:07:51'), ('27c99401b5e54b7c93a81116f7a4d05b', '', '4', '26bd78065f254bd4a8d4b4e16c72990b', '4', null, '2016-02-20 17:53:58', '2016-02-20 17:53:58'), ('280b11d4ad224fa0bffd953f2144620a', '', '8', '79a100802e7a4c3e98f72ad6a621b59c', '8', null, '2016-02-20 17:55:22', '2016-02-20 17:55:22'), ('284b1c462efd412fa7e9999481f73578', '非常满意', '10', 'e04ae9d048c948539d9342d01ae3a09b', '10', null, '2016-02-20 17:53:32', '2016-02-20 17:53:32'), ('291ff34859d34f46a00a8fb331e88dc5', '', '9', '5058d0b8d5944ac1bea5a059eb3f6d7c', '9', null, '2016-03-05 11:07:51', '2016-03-05 11:07:51'), ('2a4d4c65267a4220b196ca86033cfb49', '', '5', '26bd78065f254bd4a8d4b4e16c72990b', '5', null, '2016-02-20 17:53:58', '2016-02-20 17:53:58'), ('2ab2a87998574c98a0dd206fa68cef09', '非常不满意', '1', '8f138714ff4442cf862005532df7a15d', '1', null, '2016-02-20 17:49:55', '2016-02-20 17:49:55'), ('2b0a1d30125f4fbcb74de54a67d03870', '', '8', 'b7534ce2fa014341988b9f7025584e18', '8', null, '2016-02-27 20:37:00', '2016-02-27 20:37:00'), ('3', '满意', '10', '1', '3', null, '2015-12-30 11:40:57', '2015-12-30 11:41:07'), ('30daf3bd26af41b79fa9e057712cd337', '', '7', '26bd78065f254bd4a8d4b4e16c72990b', '7', null, '2016-02-20 17:53:58', '2016-02-20 17:53:58'), ('312460b6f4a24787ba674ca4ad88afdf', '非常满意', '10', 'dd410c33519a4c8c827565bee8fcbf0d', '10', null, '2016-02-20 17:49:17', '2016-02-20 17:49:17'), ('32fd508e71de47aa939df4488d8aeff8', '', '2', '26bd78065f254bd4a8d4b4e16c72990b', '2', null, '2016-02-20 17:53:58', '2016-02-20 17:53:58'), ('34575494a2cb4c0fa88ce0ce07576dbe', '一般', '6', '89f6202c86b64441b297ee92b230eeef', '6', null, '2016-02-20 17:51:24', '2016-02-20 17:51:24'), ('351a9f9561c4466ea755b246597e8f6a', '', '2', 'ff08b42de95a4ca2b7dd67a58043f3b9', '2', null, '2016-02-20 17:54:52', '2016-02-20 17:54:52'), ('36d645aa039d484fb0d78733c63b07f3', '', '2', '79a100802e7a4c3e98f72ad6a621b59c', '2', null, '2016-02-20 17:55:22', '2016-02-20 17:55:22'), ('39ba6d811ced4ca8b0143cf2801d8c13', '', '5', '5058d0b8d5944ac1bea5a059eb3f6d7c', '5', null, '2016-03-05 11:07:51', '2016-03-05 11:07:51'), ('3be95220ac3a4010a03c0284dd77a42e', '', '7', '106b28d409334b758e9fbe057e76a66e', '7', null, '2016-02-20 17:52:09', '2016-02-20 17:52:09'), ('3f0cb1a835914051bf583a75fbcf9ff3', '', '5', '79a100802e7a4c3e98f72ad6a621b59c', '5', null, '2016-02-20 17:55:22', '2016-02-20 17:55:22'), ('4', '非常不愿意', '1', '2', '1', null, '2015-12-30 11:42:21', '2015-12-30 11:42:30'), ('40ab101fd9e547a59ed479b337b44e05', '', '7', '5058d0b8d5944ac1bea5a059eb3f6d7c', '7', null, '2016-03-05 11:07:51', '2016-03-05 11:07:51'), ('40d5c09172a7438a9cf795b0992e7018', '', '4', '17841a0255014490905ecf3fe8308232', '4', null, '2016-02-20 17:53:05', '2016-02-20 17:53:05'), ('40f05e64532443d9911a21aaa1b61624', '是', '1', '051485bcb8204ad2b2de896e0387c686', '2', null, '2016-02-20 17:58:48', '2016-02-20 17:58:48'), ('438d2f6863644ddc9027556f7efe631f', '非常满意', '10', 'ff08b42de95a4ca2b7dd67a58043f3b9', '10', null, '2016-02-20 17:54:52', '2016-02-20 17:54:52'), ('444a102835034d88a8b2a65e64b19609', '一般', '6', 'dd410c33519a4c8c827565bee8fcbf0d', '6', null, '2016-02-20 17:49:17', '2016-02-20 17:49:17'), ('44ca803d9188447c841327beb11ff70d', '是', '1', 'c3ae7a64408f420387ed45f419d688e6', '2', null, '2016-02-20 17:58:20', '2016-02-20 17:58:20'), ('4659afcf07cc4213a2d692ee1eeda497', '早餐', '早餐', '3c77a9cb363f48c3ad6edcf9518371c3', '1', null, '2016-02-20 18:08:41', '2016-02-20 18:08:41'), ('47b7ad89f85f44c69710925d563e2d53', '', '7', 'e04ae9d048c948539d9342d01ae3a09b', '7', null, '2016-02-20 17:53:32', '2016-02-20 17:53:32'), ('4941a84b21ce4a039c89b8304984d34c', '', '9', 'e95bff3d091d46c4886844986d13ab18', '9', null, '2016-02-20 17:54:23', '2016-02-20 17:54:23'), ('49d5e95beb6c4dd4a4b06c8f6501a660', '', '9', '17841a0255014490905ecf3fe8308232', '9', null, '2016-02-20 17:53:05', '2016-02-20 17:53:05'), ('4a7b2f7be4d14c26a0e27eff35253f22', '非常不满意', '1', 'ff08b42de95a4ca2b7dd67a58043f3b9', '1', null, '2016-02-20 17:54:52', '2016-02-20 17:54:52'), ('4acea7d7a975481eaae38621d8c32c8c', '一般', '6', 'e95bff3d091d46c4886844986d13ab18', '6', null, '2016-02-20 17:54:23', '2016-02-20 17:54:23'), ('4b5fd3b108bc4cd883382d392bca36d2', '', '7', 'e95bff3d091d46c4886844986d13ab18', '7', null, '2016-02-20 17:54:23', '2016-02-20 17:54:23'), ('4b968faf568447908d87e00d43d829c3', '否', '0', 'decc04e9977e44e4b1105429cde99908', '1', null, '2016-02-20 17:58:30', '2016-02-20 17:58:30'), ('4def8e0fc15a4f73a627947bf4b87a61', '是', '1', '1080649e7e944c98892abf870c805546', '2', null, '2016-02-20 17:59:00', '2016-02-20 17:59:00'), ('5', '一般', '5', '2', '2', null, '2015-12-30 11:42:25', '2015-12-30 11:42:32'), ('5044d3b8413e41478763b968be1c6639', '非常不满意', '1', '26bd78065f254bd4a8d4b4e16c72990b', '1', null, '2016-02-20 17:53:58', '2016-02-20 17:53:58'), ('522b44a1e6ae48aea62669a3c0d88867', '', '2', 'dd410c33519a4c8c827565bee8fcbf0d', '2', null, '2016-02-20 17:49:17', '2016-02-20 17:49:17'), ('5318fe1364f44369ba4aece313edd03d', '', '5', 'ff08b42de95a4ca2b7dd67a58043f3b9', '5', null, '2016-02-20 17:54:52', '2016-02-20 17:54:52'), ('531abcbab02e44f090e65dd1ce75d583', '', '8', 'e04ae9d048c948539d9342d01ae3a09b', '8', null, '2016-02-20 17:53:32', '2016-02-20 17:53:32'), ('540301043c964560a0d120a18e815bf2', '一般', '6', '79a100802e7a4c3e98f72ad6a621b59c', '6', null, '2016-02-20 17:55:22', '2016-02-20 17:55:22'), ('5504487b9b014437a9bcee315f0df7ca', '', '5', '8f138714ff4442cf862005532df7a15d', '5', null, '2016-02-20 17:49:55', '2016-02-20 17:49:55'), ('551bc3d066d840738c1a7baa9c79e626', '', '5', 'e95bff3d091d46c4886844986d13ab18', '5', null, '2016-02-20 17:54:23', '2016-02-20 17:54:23'), ('574f720e64224c68b688d8761e8cbb16', '', '2', '8f138714ff4442cf862005532df7a15d', '2', null, '2016-02-20 17:49:55', '2016-02-20 17:49:55'), ('57749f40e81c49b7b7fe2d573c37288d', '', '3', 'e04ae9d048c948539d9342d01ae3a09b', '3', null, '2016-02-20 17:53:32', '2016-02-20 17:53:32'), ('581dd551b6df475f901b484c32f314fb', '是', '1', '96856dcd87c54882b7a5c6c891283fbc', '2', null, '2016-02-20 17:59:12', '2016-02-20 17:59:12'), ('5824119aac4d4336bbd97ccec66065b5', '', '7', '89f6202c86b64441b297ee92b230eeef', '7', null, '2016-02-20 17:51:24', '2016-02-20 17:51:24'), ('59fd597ef6d4458487b765a861ae446b', '', '7', 'ff08b42de95a4ca2b7dd67a58043f3b9', '7', null, '2016-02-20 17:54:52', '2016-02-20 17:54:52'), ('5b48ee2fd9f14316b96b4758388ed990', '自己', '自己', '0dd238b4fd7747e7910adc5115f3fca8', '1', null, '2016-02-20 18:07:12', '2016-02-20 18:07:12'), ('5bd7d1e7b6174962b8aa7d255aa60712', '非常不满意', '1', 'e04ae9d048c948539d9342d01ae3a09b', '1', null, '2016-02-20 17:53:32', '2016-02-20 17:53:32'), ('5f55a88860664037a9e93a29add95b92', '女士', '女士', '24ad65ae8b49495a80205a2e472fcda3', '2', null, '2016-02-20 17:59:55', '2016-02-20 17:59:55'), ('6', '愿意', '10', '2', '3', null, '2015-12-30 11:42:28', '2015-12-30 11:42:34'), ('6011943ef37f460db7907b7daa29dc69', '', '8', 'dd410c33519a4c8c827565bee8fcbf0d', '8', null, '2016-02-20 17:49:17', '2016-02-20 17:49:17'), ('6037428800ab45d59330d43e8e9d5e52', '是', '1', 'decc04e9977e44e4b1105429cde99908', '2', null, '2016-02-20 17:58:30', '2016-02-20 17:58:30'), ('62095c71c1744b1caacb7a7b3ea70e13', '', '3', 'e95bff3d091d46c4886844986d13ab18', '3', null, '2016-02-20 17:54:23', '2016-02-20 17:54:23'), ('62ee0927d3e84bf7b58d15059ccf0fca', '', '3', 'ff08b42de95a4ca2b7dd67a58043f3b9', '3', null, '2016-02-20 17:54:52', '2016-02-20 17:54:52'), ('6339cd81d4164b5ea98b0d16ba730f3f', '', '5', 'b7534ce2fa014341988b9f7025584e18', '5', null, '2016-02-27 20:37:00', '2016-02-27 20:37:00'), ('639ad25e28c049d5b78eca4e5d8c4b1f', '一般', '6', '8f138714ff4442cf862005532df7a15d', '6', null, '2016-02-20 17:49:55', '2016-02-20 17:49:55'), ('63c2dd2a1d56450eaf68e68917a307dd', '和客户或商业伙伴', '和客户或商业伙伴', '0dd238b4fd7747e7910adc5115f3fca8', '5', null, '2016-02-20 18:07:12', '2016-02-20 18:07:12'), ('64d36ce3072e49839254e2aeb8523dd2', '非常满意', '10', '89f6202c86b64441b297ee92b230eeef', '10', null, '2016-02-20 17:51:24', '2016-02-20 17:51:24'), ('657c7e067a6743a7b68c47ee71ee77ce', '', '4', 'dd410c33519a4c8c827565bee8fcbf0d', '4', null, '2016-02-20 17:49:17', '2016-02-20 17:49:17'), ('6625f2c5bd694172a7f7ffba637a3b19', '', '7', 'dd410c33519a4c8c827565bee8fcbf0d', '7', null, '2016-02-20 17:49:17', '2016-02-20 17:49:17'), ('6b19ac4da2504feab0161154e7e71453', '非常满意', '10', 'e95bff3d091d46c4886844986d13ab18', '10', null, '2016-02-20 17:54:23', '2016-02-20 17:54:23'), ('6d8d7590d67146dab282b81defc85c55', '否', '0', '51d0c24eab0b4dd78f9b652121b84ac6', '1', null, '2016-02-20 17:56:32', '2016-02-20 17:56:32'), ('6e020f908022474181509bda08823db0', '否', '0', 'c910a4ec1ac244ed8420db65199d1f68', '1', null, '2016-02-20 17:55:51', '2016-02-20 17:55:51'), ('6f1eb57a94b143acb9fdf3dbe0ec791e', '非常不满意', '1', 'e95bff3d091d46c4886844986d13ab18', '1', null, '2016-02-20 17:54:23', '2016-02-20 17:54:23'), ('70ab5e2cce904abc93ec9ca7ef7a1fa1', 'asd', '4', 'b7534ce2fa014341988b9f7025584e18', '4', null, '2016-02-27 20:37:00', '2016-02-27 20:37:00'), ('70d309092dfd4227bcc9a7daa0db2f12', '', '6', 'b7534ce2fa014341988b9f7025584e18', '6', null, '2016-02-27 20:37:00', '2016-02-27 20:37:00'), ('71c6a9eadfee4d5e946814f0430acf63', '46-50岁', '46-50岁', '46098a2fca774be8aa38359cadc8282b', '7', null, '2016-02-27 20:20:11', '2016-02-27 20:20:11'), ('7237b71c933e445f91bc900d48896acd', '非常不满意', '1', '79a100802e7a4c3e98f72ad6a621b59c', '1', null, '2016-02-20 17:55:22', '2016-02-20 17:55:22'), ('79ac8084b7f54672ac7d5311f5c4dd47', '', '10', 'b7534ce2fa014341988b9f7025584e18', '10', null, '2016-02-27 20:37:00', '2016-02-27 20:37:00'), ('79ea95b306bc4f03ab439b7e9368f1d9', '', '5', 'e04ae9d048c948539d9342d01ae3a09b', '5', null, '2016-02-20 17:53:32', '2016-02-20 17:53:32'), ('81c5f926163f4891ade2a1a7c39b253d', '一般', '6', 'e04ae9d048c948539d9342d01ae3a09b', '6', null, '2016-02-20 17:53:32', '2016-02-20 17:53:32'), ('8377d0003a144c62b4982a16d2a5975f', '', '4', 'e95bff3d091d46c4886844986d13ab18', '4', null, '2016-02-20 17:54:23', '2016-02-20 17:54:23'), ('853d804c59b249d79f663454eb804812', '', '2', '17841a0255014490905ecf3fe8308232', '2', null, '2016-02-20 17:53:05', '2016-02-20 17:53:05'), ('877754ed83d5461bb512b6439493510b', '', '4', '89f6202c86b64441b297ee92b230eeef', '4', null, '2016-02-20 17:51:24', '2016-02-20 17:51:24'), ('88fbb70440894a0ea9a78f240120a132', '第一次', '第一次', 'f7672d2100984780ad43310d641b4ca4', '1', null, '2016-02-20 18:09:14', '2016-02-20 18:09:14'), ('8a08cc54fc8e47118c4b8afa8f2fc395', '是', '1', '4ebb3e6ed281485094a4a4e1823ab2f5', '2', null, '2016-02-20 17:56:17', '2016-02-20 17:56:17'), ('8a96505367204a0eb2e7744a1174698f', '否', '0', '96856dcd87c54882b7a5c6c891283fbc', '1', null, '2016-02-20 17:59:12', '2016-02-20 17:59:12'), ('8b9f2801c5a14707b3a8a659a3004946', '中餐', '中餐', '3c77a9cb363f48c3ad6edcf9518371c3', '2', null, '2016-02-20 18:08:41', '2016-02-20 18:08:41'), ('8d4d025676254eedb3a57bea295256da', '否', '0', '4ebb3e6ed281485094a4a4e1823ab2f5', '1', null, '2016-02-20 17:56:17', '2016-02-20 17:56:17'), ('8e0371a5e79e44138179e5f941b965fb', '26-30岁', '26-30岁', '46098a2fca774be8aa38359cadc8282b', '3', null, '2016-02-27 20:20:11', '2016-02-27 20:20:11'), ('8e50e748db694dd190d1c6d5d52c3bd8', '31-35岁', '31-35岁', '46098a2fca774be8aa38359cadc8282b', '4', null, '2016-02-27 20:20:11', '2016-02-27 20:20:11'), ('9', '第一次', '第一次', '4', '1', null, '2015-12-30 11:45:06', '2015-12-30 11:45:08'), ('9053ab0cea0842b5b85d51c24e6be866', '', '2', '5058d0b8d5944ac1bea5a059eb3f6d7c', '2', null, '2016-03-05 11:07:51', '2016-03-05 11:07:51'), ('90d606844f0c4f0eacfcc6b4a12b0caf', '', '5', '106b28d409334b758e9fbe057e76a66e', '5', null, '2016-02-20 17:52:09', '2016-02-20 17:52:09'), ('91e0159e079046dd803806d17535d359', '', '8', '8f138714ff4442cf862005532df7a15d', '8', null, '2016-02-20 17:49:55', '2016-02-20 17:49:55'), ('9377f014577440bbbdc3697407ad3fa9', '否', '0', '051485bcb8204ad2b2de896e0387c686', '1', null, '2016-02-20 17:58:48', '2016-02-20 17:58:48'), ('94bded9edea44fe98cc1ceda60d5e6ee', '', '3', '8f138714ff4442cf862005532df7a15d', '3', null, '2016-02-20 17:49:55', '2016-02-20 17:49:55'), ('975d283c08d5402d94321c6877b008db', '', '9', 'b7534ce2fa014341988b9f7025584e18', '9', null, '2016-02-27 20:37:00', '2016-02-27 20:37:00'), ('97eee35668ee44a8ade68b0d3988ec48', '经常来', '经常来', 'f7672d2100984780ad43310d641b4ca4', '3', null, '2016-02-20 18:09:14', '2016-02-20 18:09:14'), ('9b072ab969084511a30181557c53f1dc', '', '8', 'e95bff3d091d46c4886844986d13ab18', '8', null, '2016-02-20 17:54:23', '2016-02-20 17:54:23'), ('9c5c0d09ea2d4c9cafcc636bf1eaf1e6', '', '8', '5058d0b8d5944ac1bea5a059eb3f6d7c', '8', null, '2016-03-05 11:07:51', '2016-03-05 11:07:51'), ('9f08df4ae8a741319585108041aa96b0', '非常满意', '10', '17841a0255014490905ecf3fe8308232', '10', null, '2016-02-20 17:53:05', '2016-02-20 17:53:05'), ('a04da445302b4cf6bd066e83423d77d9', '非常不满意', '1', '17841a0255014490905ecf3fe8308232', '1', null, '2016-02-20 17:53:05', '2016-02-20 17:53:05'), ('a38804cf7e6449d9bff663e2d2479acc', '和家人或亲戚', '和家人或亲戚', '0dd238b4fd7747e7910adc5115f3fca8', '3', null, '2016-02-20 18:07:12', '2016-02-20 18:07:12'), ('a69c743c2ca742b9b165de4fe94e3757', '晚餐', '晚餐', '3c77a9cb363f48c3ad6edcf9518371c3', '3', null, '2016-02-20 18:08:41', '2016-02-20 18:08:41'), ('a8d6681432224b8c9ce16f762345f125', '否', '0', '1080649e7e944c98892abf870c805546', '1', null, '2016-02-20 17:59:00', '2016-02-20 17:59:00'), ('ac980be88d604b7a8fc15f5b13527985', '不常来，但也不是第一次', '不常来，但也不是第一次', 'f7672d2100984780ad43310d641b4ca4', '2', null, '2016-02-20 18:09:14', '2016-02-20 18:09:14'), ('acc0eddb760b4a3a8420877d200c9a45', '和同事', '和同事', '0dd238b4fd7747e7910adc5115f3fca8', '2', null, '2016-02-20 18:07:12', '2016-02-20 18:07:12'), ('ae8da557bfd14b9bb61db0f681d601a3', '一般', '6', '26bd78065f254bd4a8d4b4e16c72990b', '6', null, '2016-02-20 17:53:58', '2016-02-20 17:53:58'), ('af672896e18c4b85b16f88c89db41289', '非常满意', '10', '26bd78065f254bd4a8d4b4e16c72990b', '10', null, '2016-02-20 17:53:58', '2016-02-20 17:53:58'), ('afdfec6aaf9b4429aed089d2cd8c392f', '', '3', '17841a0255014490905ecf3fe8308232', '3', null, '2016-02-20 17:53:05', '2016-02-20 17:53:05'), ('b2c4740be24d45459a894cbcfd2d6d38', '', '9', 'ff08b42de95a4ca2b7dd67a58043f3b9', '9', null, '2016-02-20 17:54:52', '2016-02-20 17:54:52'), ('b3f8b3bbb55a4cce97ee84050cf47663', '', '8', 'ff08b42de95a4ca2b7dd67a58043f3b9', '8', null, '2016-02-20 17:54:52', '2016-02-20 17:54:52'), ('b558f18e977c4a01aaf94a182a0ed994', '', '4', '106b28d409334b758e9fbe057e76a66e', '4', null, '2016-02-20 17:52:09', '2016-02-20 17:52:09'), ('b7f11152a88846fe8de1c59fd1987d3d', '', '4', 'e04ae9d048c948539d9342d01ae3a09b', '4', null, '2016-02-20 17:53:32', '2016-02-20 17:53:32'), ('b85cd3a529d0433a8849e7f564a826b5', '', '3', '106b28d409334b758e9fbe057e76a66e', '3', null, '2016-02-20 17:52:09', '2016-02-20 17:52:09'), ('ba17fdca8ab54cf09fba2d8ea75eb232', '非常不满意', '1', '89f6202c86b64441b297ee92b230eeef', '1', null, '2016-02-20 17:51:24', '2016-02-20 17:51:24'), ('ba4b9670315242c1bf1dc38c569f988e', '非常满意', '10', '8f138714ff4442cf862005532df7a15d', '10', null, '2016-02-20 17:49:55', '2016-02-20 17:49:55'), ('bbe4d14b24a5440bbe865c1bdf5e9fa4', '', '3', 'b7534ce2fa014341988b9f7025584e18', '3', null, '2016-02-27 20:37:00', '2016-02-27 20:37:00'), ('bc53291c902a49f8a83442932cd8664d', '是', '1', 'c910a4ec1ac244ed8420db65199d1f68', '2', null, '2016-02-20 17:55:51', '2016-02-20 17:55:51'), ('bdac12d4ef024db8b084393585642672', '一般', '6', 'ff08b42de95a4ca2b7dd67a58043f3b9', '6', null, '2016-02-20 17:54:52', '2016-02-20 17:54:52'), ('bff410c40c2e4a38b69e4c2857c1fb0f', '是', '1', '51d0c24eab0b4dd78f9b652121b84ac6', '2', null, '2016-02-20 17:56:32', '2016-02-20 17:56:32'), ('c158bf89452346a69e4b77a5e4970c65', '', '3', '79a100802e7a4c3e98f72ad6a621b59c', '3', null, '2016-02-20 17:55:22', '2016-02-20 17:55:22'), ('c2ee6b1cbda442a6af60cd77a8f637bd', '', '2', 'e04ae9d048c948539d9342d01ae3a09b', '2', null, '2016-02-20 17:53:32', '2016-02-20 17:53:32'), ('c48ed8e4215841bbace79cbcc4e3788d', '非常不满意', '1', 'dd410c33519a4c8c827565bee8fcbf0d', '1', null, '2016-02-20 17:49:17', '2016-02-20 17:49:17'), ('c825d4b735a84e7d8626355ae68cef3c', '', '9', 'e04ae9d048c948539d9342d01ae3a09b', '9', null, '2016-02-20 17:53:32', '2016-02-20 17:53:32'), ('c8a19704287d494d9815533f934911da', '', '5', 'dd410c33519a4c8c827565bee8fcbf0d', '5', null, '2016-02-20 17:49:17', '2016-02-20 17:49:17'), ('cbbaf56794784e458524a35af5de7a98', '', '9', '106b28d409334b758e9fbe057e76a66e', '9', null, '2016-02-20 17:52:09', '2016-02-20 17:52:09'), ('cf976a5bc26940d29cee7c056b2bf00d', '和朋友或同学', '和朋友或同学', '0dd238b4fd7747e7910adc5115f3fca8', '4', null, '2016-02-20 18:07:12', '2016-02-20 18:07:12'), ('cf99b863e40940bbb20373184bbb63af', '', '5', '17841a0255014490905ecf3fe8308232', '5', null, '2016-02-20 17:53:05', '2016-02-20 17:53:05'), ('d005a55b0bf940b1938c3d202425a7b3', '20岁以下', '20岁以下', '46098a2fca774be8aa38359cadc8282b', '1', null, '2016-02-27 20:20:11', '2016-02-27 20:20:11'), ('d2569262382345cb9ba2483205cddb91', '非常不满意', '1', '5058d0b8d5944ac1bea5a059eb3f6d7c', '1', null, '2016-03-05 11:07:51', '2016-03-05 11:07:51'), ('d4f2c5efac33480fa5359716465cd35a', '非常满意', '10', '5058d0b8d5944ac1bea5a059eb3f6d7c', '10', null, '2016-03-05 11:07:51', '2016-03-05 11:07:51'), ('d7f6879b670e4ffeb368fb8861cf20f5', '', '3', '89f6202c86b64441b297ee92b230eeef', '3', null, '2016-02-20 17:51:24', '2016-02-20 17:51:24'), ('d8df9c73fed84102a4f482d734eb61f4', '', '8', '26bd78065f254bd4a8d4b4e16c72990b', '8', null, '2016-02-20 17:53:58', '2016-02-20 17:53:58'), ('d9f3e36da3f54758a7fd6aa217b1e048', '41-45岁', '41-45岁', '46098a2fca774be8aa38359cadc8282b', '6', null, '2016-02-27 20:20:11', '2016-02-27 20:20:11'), ('db0f652661a547898fa6803d132247f0', '', '3', 'dd410c33519a4c8c827565bee8fcbf0d', '3', null, '2016-02-20 17:49:17', '2016-02-20 17:49:17'), ('db31166e83c94fc59895c1c6717cbf18', '否', '0', 'c3ae7a64408f420387ed45f419d688e6', '1', null, '2016-02-20 17:58:20', '2016-02-20 17:58:20'), ('dd3ce2aff27a4a4ba3a380932109178e', '', '9', '26bd78065f254bd4a8d4b4e16c72990b', '9', null, '2016-02-20 17:53:58', '2016-02-20 17:53:58'), ('de6506f7a2af4a828ba6fd51e4add27e', '', '2', 'e95bff3d091d46c4886844986d13ab18', '2', null, '2016-02-20 17:54:23', '2016-02-20 17:54:23'), ('de9fef8e461a4ca58325dea0c9a482fa', '', '7', '79a100802e7a4c3e98f72ad6a621b59c', '7', null, '2016-02-20 17:55:22', '2016-02-20 17:55:22'), ('e0edf06cd9394525831f3943b83beb65', '', '7', '8f138714ff4442cf862005532df7a15d', '7', null, '2016-02-20 17:49:55', '2016-02-20 17:49:55'), ('e1213946cfc442e480d49280205991ea', '', '9', '89f6202c86b64441b297ee92b230eeef', '9', null, '2016-02-20 17:51:24', '2016-02-20 17:51:24'), ('e14aa92d4f6a463eb4bef8bba6de408d', '否', '0', '986312c916b94728ab09810d8f5da4b8', '1', null, '2016-02-20 17:56:03', '2016-02-20 17:56:03'), ('e285d80cf22b4f2985c66630d16f6521', '', '4', 'ff08b42de95a4ca2b7dd67a58043f3b9', '4', null, '2016-02-20 17:54:52', '2016-02-20 17:54:52'), ('e417a7198800476994854103d1883aad', 'asd', '2', 'b7534ce2fa014341988b9f7025584e18', '2', null, '2016-02-27 20:37:00', '2016-02-27 20:37:00'), ('e8d0ba4d0a624771a211c1c1e95a6874', '', '9', '79a100802e7a4c3e98f72ad6a621b59c', '9', null, '2016-02-20 17:55:22', '2016-02-20 17:55:22'), ('ea5612d3236e4d0d8b9d55986b25d294', '', '2', '89f6202c86b64441b297ee92b230eeef', '2', null, '2016-02-20 17:51:24', '2016-02-20 17:51:24'), ('f0c7e7d4ed854c60a31f9fa94951c329', '', '9', '8f138714ff4442cf862005532df7a15d', '9', null, '2016-02-20 17:49:55', '2016-02-20 17:49:55'), ('f11b064c17174a52adbe1c59fb082570', '', '7', '17841a0255014490905ecf3fe8308232', '7', null, '2016-02-20 17:53:05', '2016-02-20 17:53:05'), ('f6104e5fb21c4a8b8f541e837fdc9bfc', '36-40岁', '36-40岁', '46098a2fca774be8aa38359cadc8282b', '5', null, '2016-02-27 20:20:11', '2016-02-27 20:20:11'), ('f7b1f7c3a89f4e74b8fb181d5fb80cbb', '', '8', '17841a0255014490905ecf3fe8308232', '8', null, '2016-02-20 17:53:05', '2016-02-20 17:53:05'), ('fa61f4828c00444a871919ff790d78ab', '非常满意', '10', '106b28d409334b758e9fbe057e76a66e', '10', null, '2016-02-20 17:52:09', '2016-02-20 17:52:09'), ('faecf179216d4136b8388cb360ce7250', '', '1', 'b7534ce2fa014341988b9f7025584e18', '1', null, '2016-02-27 20:37:00', '2016-02-27 20:37:00'), ('fb797391f66f46db89f77d91ea535342', '', '4', '79a100802e7a4c3e98f72ad6a621b59c', '4', null, '2016-02-20 17:55:22', '2016-02-20 17:55:22');
COMMIT;

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
  `top_flag` bit(1) DEFAULT NULL,
  `special_falg` bit(1) NOT NULL,
  `chart_one_dimnsn` varchar(100) DEFAULT NULL COMMENT '单维度图表ID',
  `chart_multi_dimnsn` varchar(100) DEFAULT NULL COMMENT '多维度图表',
  `chart_store` varchar(100) DEFAULT NULL COMMENT '门店维度图表',
  `chart_time` varchar(100) DEFAULT NULL COMMENT '时间维度图表',
  `chart_time_dimnsn` varchar(100) DEFAULT NULL COMMENT '时间多维度图表',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `QUESTION`
-- ----------------------------
BEGIN;
INSERT INTO `QUESTION` VALUES ('051485bcb8204ad2b2de896e0387c686', '未报菜名', '您在消费过程中，是否遇到下面情况?', null, '3', '14831289084643edaf2f50ef598fc65a', '6', null, null, null, null, '2016-02-20 17:58:48', '2016-02-20 17:58:48', null, null, b'0', null, null, null, null, null), ('0dd238b4fd7747e7910adc5115f3fca8', '和谁一起消费', '本次消费是一个人，还是和其他人？', null, '5', '14831289084643edaf2f50ef598fc65a', '3', null, null, null, null, '2016-02-20 18:07:12', '2016-02-20 18:07:12', null, null, b'0', null, null, null, null, null), ('1', '总体满意度', '您对本次消费的满意程度如何？', null, '1', '2', '1', '1', '1', null, null, '2015-12-30 11:36:07', '2015-12-30 11:36:11', null, null, b'0', null, null, null, null, null), ('106b28d409334b758e9fbe057e76a66e', '菜品价格', '请您对以下各个方面的表现给予评价？', null, '2', '14831289084643edaf2f50ef598fc65a', '1', null, null, null, null, '2016-02-20 17:52:09', '2016-02-20 17:52:09', null, null, b'0', null, null, null, null, null), ('1080649e7e944c98892abf870c805546', '未及时响应', '您在消费过程中，是否遇到下面情况?', null, '3', '14831289084643edaf2f50ef598fc65a', '7', null, null, null, null, '2016-02-20 17:59:00', '2016-02-20 17:59:00', null, null, b'0', null, null, null, null, null), ('17841a0255014490905ecf3fe8308232', '菜品种类', '请您对以下各个方面的表现给予评价？', null, '2', '14831289084643edaf2f50ef598fc65a', '2', null, null, null, null, '2016-02-20 17:53:05', '2016-02-20 17:53:05', null, null, b'0', null, null, null, null, null), ('2', '卫生程度', '卫生程度？', null, '2', '2', '1', '1', '1', null, null, '2015-12-30 11:36:13', '2015-12-30 11:36:25', null, null, b'0', null, null, null, null, null), ('24ad65ae8b49495a80205a2e472fcda3', '性别', '请问您是？', null, '5', '14831289084643edaf2f50ef598fc65a', '1', null, null, null, null, '2016-02-20 17:59:55', '2016-02-20 17:59:55', null, null, b'0', null, null, null, null, null), ('26bd78065f254bd4a8d4b4e16c72990b', '卫生程度', '请您对以下各个方面的表现给予评价？', null, '2', '14831289084643edaf2f50ef598fc65a', '4', null, null, null, null, '2016-02-20 17:53:58', '2016-02-20 17:53:58', null, null, b'0', null, null, null, null, null), ('3c77a9cb363f48c3ad6edcf9518371c3', '就餐时间', '本次就餐是早餐、午餐，还是晚餐？', null, '5', '14831289084643edaf2f50ef598fc65a', '4', null, null, null, null, '2016-02-20 18:08:22', '2016-02-20 18:08:41', null, null, b'0', null, null, null, null, null), ('4', '新老顾客', '请问您是否是经常来本店消费？', null, '4', '2', '1', '1', '1', null, null, '2015-12-30 11:36:18', '2015-12-30 11:36:21', null, null, b'0', null, null, null, null, null), ('46098a2fca774be8aa38359cadc8282b', '年龄', '请问您的年龄段？', null, '5', '14831289084643edaf2f50ef598fc65a', '2', null, null, null, null, '2016-02-20 18:06:06', '2016-02-27 20:20:15', null, null, b'0', null, null, null, null, null), ('4ebb3e6ed281485094a4a4e1823ab2f5', '未倒水', '您在消费过程中，是否遇到下面情况?', null, '3', '14831289084643edaf2f50ef598fc65a', '2', null, null, null, null, '2016-02-20 17:56:17', '2016-02-20 17:56:17', null, null, b'0', null, null, null, null, null), ('5', '是否满足需求', '是否满足需求', null, '5', '2', '1', '1', '1', null, null, '2016-02-18 16:16:38', '2016-02-18 16:16:40', null, null, b'0', null, null, null, null, null), ('5058d0b8d5944ac1bea5a059eb3f6d7c', '新的指标', '新的指标新的指标新的指标新的指标新的指标新的指标？', null, '1', '14831289084643edaf2f50ef598fc65a', '3', null, null, null, null, '2016-03-05 11:07:50', '2016-03-05 11:07:50', null, null, b'0', null, null, null, null, null), ('51d0c24eab0b4dd78f9b652121b84ac6', '未准备好餐具', '您在消费过程中，是否遇到下面情况?', null, '3', '14831289084643edaf2f50ef598fc65a', '3', null, null, null, null, '2016-02-20 17:56:32', '2016-02-20 17:56:32', null, null, b'0', null, null, null, null, null), ('79a100802e7a4c3e98f72ad6a621b59c', '结账速度', '请您对以下各个方面的表现给予评价？', null, '2', '14831289084643edaf2f50ef598fc65a', '7', null, null, null, null, '2016-02-20 17:55:22', '2016-02-20 17:55:22', null, null, b'0', null, null, null, null, null), ('89f6202c86b64441b297ee92b230eeef', '菜品口味', '请您对以下各个方面的表现给予评价？', null, '2', '14831289084643edaf2f50ef598fc65a', '0', null, null, null, null, '2016-02-20 17:51:24', '2016-02-20 17:51:24', null, null, b'0', null, null, null, null, null), ('8f138714ff4442cf862005532df7a15d', '推荐意愿', '您是否愿意推荐本店给身边的人？', null, '1', '14831289084643edaf2f50ef598fc65a', '0', null, null, null, null, '2016-02-20 17:49:55', '2016-02-20 17:49:55', null, null, b'0', null, null, null, null, null), ('96856dcd87c54882b7a5c6c891283fbc', '未道别', '您在消费过程中，是否遇到下面情况?', null, '3', '14831289084643edaf2f50ef598fc65a', '8', null, null, null, null, '2016-02-20 17:59:12', '2016-02-20 17:59:12', null, null, b'0', null, null, null, null, null), ('986312c916b94728ab09810d8f5da4b8', '未及时送菜单', '您在消费过程中，是否遇到下面情况?', null, '3', '14831289084643edaf2f50ef598fc65a', '1', null, null, null, null, '2016-02-20 17:56:03', '2016-02-20 17:56:03', null, null, b'0', null, null, null, null, null), ('b7534ce2fa014341988b9f7025584e18', 'asd', 'asd', null, '6', '14831289084643edaf2f50ef598fc65a', '1', null, null, null, null, '2016-02-27 20:37:00', '2016-02-27 20:37:00', null, null, b'0', null, null, null, null, null), ('c3ae7a64408f420387ed45f419d688e6', '未确认点单', '您在消费过程中，是否遇到下面情况?', null, '3', '14831289084643edaf2f50ef598fc65a', '4', null, null, null, null, '2016-02-20 17:58:20', '2016-02-20 17:58:20', null, null, b'0', null, null, null, null, null), ('c910a4ec1ac244ed8420db65199d1f68', '未招呼领座', '您在消费过程中，是否遇到下面情况?', null, '3', '14831289084643edaf2f50ef598fc65a', '0', null, null, null, null, '2016-02-20 17:55:51', '2016-02-20 17:55:51', null, null, b'0', null, null, null, null, null), ('dd410c33519a4c8c827565bee8fcbf0d', '再购意愿', '您今后是否会再次光顾本店？', null, '1', '14831289084643edaf2f50ef598fc65a', '2', null, null, null, null, '2016-02-20 17:49:17', '2016-02-20 17:49:17', null, null, b'0', null, null, null, null, null), ('decc04e9977e44e4b1105429cde99908', '未提示上餐时间', '您在消费过程中，是否遇到下面情况?', null, '3', '14831289084643edaf2f50ef598fc65a', '5', null, null, null, null, '2016-02-20 17:58:30', '2016-02-20 17:58:30', null, null, b'0', null, null, null, null, null), ('e04ae9d048c948539d9342d01ae3a09b', '就餐环境', '请您对以下各个方面的表现给予评价？', null, '2', '14831289084643edaf2f50ef598fc65a', '3', null, null, null, null, '2016-02-20 17:53:32', '2016-02-20 17:53:32', null, null, b'0', null, null, null, null, null), ('e95bff3d091d46c4886844986d13ab18', '服务态度', '请您对以下各个方面的表现给予评价？', null, '2', '14831289084643edaf2f50ef598fc65a', '5', null, null, null, null, '2016-02-20 17:54:23', '2016-02-20 17:54:23', null, null, b'0', null, null, null, null, null), ('eb8df0404b9e44aaa3cf1d642ba8688d', '顾客之声', '您对本店有什么建议和意见', null, '4', '14831289084643edaf2f50ef598fc65a', '1', null, null, null, null, '2016-02-25 14:00:39', '2016-02-25 14:00:39', null, null, b'0', null, null, null, null, null), ('f7672d2100984780ad43310d641b4ca4', '新来顾客', '请问您是否是经常来本店消费？', null, '5', '14831289084643edaf2f50ef598fc65a', '5', null, null, null, null, '2016-02-20 18:09:14', '2016-02-20 18:09:14', null, null, b'0', null, null, null, null, null), ('ff08b42de95a4ca2b7dd67a58043f3b9', '备餐速度', '请您对以下各个方面的表现给予评价？', null, '2', '14831289084643edaf2f50ef598fc65a', '6', null, null, null, null, '2016-02-20 17:54:52', '2016-02-20 17:54:52', null, null, b'0', null, null, null, null, null);
COMMIT;

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
  `TYPE` varchar(100) DEFAULT NULL,
  `qustnr_templt_id` varchar(32) NOT NULL,
  `active_flag` bit(1) DEFAULT NULL,
  `norm_calc_val` float DEFAULT NULL,
  `norm_input_val` float DEFAULT NULL,
  `question_type` bit(1) DEFAULT NULL,
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
INSERT INTO `QUESTION_GROUP` VALUES ('1', '顾客总体健康度', '顾客与店家的整体关系是否健康?', '1', null, '2015-12-30 11:32:39', '2015-12-30 11:32:42', 'com.myb.grouptype.MultiSelect', null, '', b'1', null, null, b'1', null, null, null, null, null), ('2', '顾客满意度细项表现', '您对该大酒店的满意地方为？', '2', null, '2015-12-30 11:32:44', '2015-12-30 11:32:46', 'com.myb.grouptype.Degree', null, '', b'1', null, null, b'0', null, null, null, null, null), ('3', '服务规范评价', null, '3', null, '2015-12-30 11:32:49', '2015-12-30 11:32:51', 'com.myb.grouptype.Degree', null, '', b'1', null, null, b'1', null, null, null, null, null), ('4', '顾客之声', null, '4', null, '2015-12-30 11:32:53', '2015-12-30 11:32:55', 'com.myb.grouptype.TextAnswer', null, '', b'1', null, null, b'1', null, null, null, null, null), ('5', '顾客消费习惯及背景调查', null, '5', null, '2016-01-26 16:18:39', '2016-01-26 16:18:41', 'com.myb.grouptype.MultiSelect', null, '', b'1', null, null, b'1', null, null, null, null, null);
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
INSERT INTO `kanas_user` VALUES ('159a97ea39df4444aa64e8b5fa01ebf8', 'admin', 'f814e168ac640505d7249e0c1cfbf437197abd77', '系统管理员', null, null, null, b'1', b'1', b'0', b'0', b'0', null, '2016-03-05 00:00:00', null, null, null, null, null);
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
