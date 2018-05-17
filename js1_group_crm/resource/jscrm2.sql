/*
Navicat MySQL Data Transfer

Source Server         : test
Source Server Version : 50527
Source Host           : localhost:3307
Source Database       : jscrm2

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2017-08-09 13:36:25
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `allotcustomer`
-- ----------------------------
DROP TABLE IF EXISTS `allotcustomer`;
CREATE TABLE `allotcustomer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `allotType` int(11) DEFAULT NULL,
  `companyId` int(11) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `customerId` int(11) DEFAULT NULL,
  `departmentId` int(11) DEFAULT NULL,
  `employeeId` int(11) DEFAULT NULL,
  `operationId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKE188112441DB3882` (`customerId`),
  KEY `FKE18811245B1393AA` (`departmentId`),
  KEY `FKE1881124CD1F9222` (`employeeId`),
  KEY `FKE1881124CAD6B69B` (`operationId`),
  CONSTRAINT `FKE188112441DB3882` FOREIGN KEY (`customerId`) REFERENCES `customer` (`id`),
  CONSTRAINT `FKE18811245B1393AA` FOREIGN KEY (`departmentId`) REFERENCES `department` (`id`),
  CONSTRAINT `FKE1881124CAD6B69B` FOREIGN KEY (`operationId`) REFERENCES `employee` (`id`),
  CONSTRAINT `FKE1881124CD1F9222` FOREIGN KEY (`employeeId`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of allotcustomer
-- ----------------------------
INSERT INTO `allotcustomer` VALUES ('1', '0', null, '2017-06-16 18:29:45', '275279', '1', null, '1');
INSERT INTO `allotcustomer` VALUES ('2', '0', null, '2017-06-17 13:11:14', '275280', '1', null, '1');
INSERT INTO `allotcustomer` VALUES ('3', '1', null, '2017-06-17 13:11:28', '275279', '1', '3057', '1');
INSERT INTO `allotcustomer` VALUES ('4', '1', null, '2017-06-17 13:53:40', '275280', '1', '3057', '3056');
INSERT INTO `allotcustomer` VALUES ('5', '0', null, '2017-07-06 13:55:21', '275281', '1', null, '1');

-- ----------------------------
-- Table structure for `amountliability`
-- ----------------------------
DROP TABLE IF EXISTS `amountliability`;
CREATE TABLE `amountliability` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `creditor` int(11) DEFAULT NULL,
  `debtMoney` double DEFAULT NULL,
  `customerId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK775D3B9541DB3882` (`customerId`),
  CONSTRAINT `FK775D3B9541DB3882` FOREIGN KEY (`customerId`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of amountliability
-- ----------------------------

-- ----------------------------
-- Table structure for `applyperson`
-- ----------------------------
DROP TABLE IF EXISTS `applyperson`;
CREATE TABLE `applyperson` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `addPersonId` int(11) DEFAULT NULL,
  `addPersonName` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `createDate` date DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `creattime` datetime DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `jobs` varchar(255) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `otherInfo` longtext,
  `phone` varchar(100) DEFAULT NULL,
  `situation` varchar(255) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `year` varchar(255) DEFAULT NULL,
  `departmentId` int(11) DEFAULT NULL,
  `employeeId` int(11) DEFAULT NULL,
  `updateTime` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKEE3CE8435B1393AA` (`departmentId`),
  KEY `FKEE3CE843CD1F9222` (`employeeId`),
  CONSTRAINT `FKEE3CE8435B1393AA` FOREIGN KEY (`departmentId`) REFERENCES `department` (`id`),
  CONSTRAINT `FKEE3CE843CD1F9222` FOREIGN KEY (`employeeId`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of applyperson
-- ----------------------------
INSERT INTO `applyperson` VALUES ('1', '3060', 'hrmanager1', '22', '2017-06-21', '2017-06-21 15:13:01', null, '男', null, '杨沛鑫', '卡山东矿机', '18302812282', null, '0', '2年', null, null, '2017-06-21');
INSERT INTO `applyperson` VALUES ('2', '3060', 'hrmanager1', '21', '2017-06-21', '2017-06-21 15:15:08', null, '男', null, '张浩', '还可以', '18302812283', null, '0', '2年', null, null, '2017-06-20');
INSERT INTO `applyperson` VALUES ('3', '3060', 'hrmanager1', '22', '2017-06-21', '2017-06-21 17:18:36', null, '女', 'Java开发', '杨菲', '还行', '18302812283', null, '0', '2年', null, null, '2017-06-19');
INSERT INTO `applyperson` VALUES ('4', '3060', 'hrmanager1', '12', '2017-06-28', '2017-06-28 14:41:20', null, '男', 'Java开发', '小李', '还可以', '18311123333', '', '0', '2年', null, null, null);
INSERT INTO `applyperson` VALUES ('5', '3060', 'hrmanager1', '23', '2017-06-28', '2017-06-28 14:50:51', null, '女', 'Java开发', '小七', '后期可以联系一下', '18311123332', '', '0', '3年', null, null, null);
INSERT INTO `applyperson` VALUES ('6', '3060', 'hrmanager1', '13', '2017-06-28', '2017-06-28 14:54:09', null, '男', 'Java开发', '小六', '还行', '18311123334', '', '0', '2年', null, null, null);
INSERT INTO `applyperson` VALUES ('7', '3060', 'hrmanager1', '33', '2017-06-28', '2017-06-28 15:10:54', null, '男', 'Java开发', '小朋友', '就那样吧', '18311123336', 'http://192.168.189.131/group1/M00/00/00/wKi9g1lTVkCAA_3XAAA5Ja9wCqo740.jpg', '0', '12年', null, null, null);
INSERT INTO `applyperson` VALUES ('8', '3060', 'hrmanager1', '22', '2017-06-29', '2017-06-29 16:41:20', null, '男', 'Java开发', '大黑', '可以继续沟通看看', '18111112222', 'http://192.168.189.131/group1/M00/00/00/wKi9g1lUne6AfgDwAAJhx8eOWSM950.jpg', '0', '1年', null, null, null);
INSERT INTO `applyperson` VALUES ('9', '3060', 'hrmanager1', '21', '2017-07-03', '2017-07-03 10:43:24', null, '男', 'PHP开发岗', 'Jim', '总体还算不错', '18111112220', '', '0', '2年', null, null, null);
INSERT INTO `applyperson` VALUES ('10', '3060', 'hrmanager1', null, '2017-07-05', '2017-07-05 15:03:27', null, '男', '', '', '', '18300001115', '', '0', '', null, null, null);

-- ----------------------------
-- Table structure for `bankproduct`
-- ----------------------------
DROP TABLE IF EXISTS `bankproduct`;
CREATE TABLE `bankproduct` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bankproduct
-- ----------------------------

-- ----------------------------
-- Table structure for `car`
-- ----------------------------
DROP TABLE IF EXISTS `car`;
CREATE TABLE `car` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `brand` varchar(255) DEFAULT NULL,
  `buyDate` varchar(255) DEFAULT NULL,
  `loanStatus` varchar(255) DEFAULT NULL,
  `price` varchar(255) DEFAULT NULL,
  `refundDate` varchar(255) DEFAULT NULL,
  `refundMonth` varchar(255) DEFAULT NULL,
  `customerId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK107B441DB3882` (`customerId`),
  CONSTRAINT `FK107B441DB3882` FOREIGN KEY (`customerId`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of car
-- ----------------------------

-- ----------------------------
-- Table structure for `certificate`
-- ----------------------------
DROP TABLE IF EXISTS `certificate`;
CREATE TABLE `certificate` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `createTime` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  `srcfileName` varchar(255) DEFAULT NULL,
  `customerId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKD7C8E97741DB3882` (`customerId`),
  CONSTRAINT `FKD7C8E97741DB3882` FOREIGN KEY (`customerId`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of certificate
-- ----------------------------

-- ----------------------------
-- Table structure for `company`
-- ----------------------------
DROP TABLE IF EXISTS `company`;
CREATE TABLE `company` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `creater` int(11) DEFAULT NULL,
  `creater_name` varchar(255) DEFAULT NULL,
  `editor` int(11) DEFAULT NULL,
  `editor_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of company
-- ----------------------------
INSERT INTO `company` VALUES ('1', '2017-06-14 17:09:36', null, null, '999', 'SuperAdmin', '佳胜公司', null, '1', null);

-- ----------------------------
-- Table structure for `config`
-- ----------------------------
DROP TABLE IF EXISTS `config`;
CREATE TABLE `config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `configKey` varchar(255) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `updaterId` int(11) DEFAULT NULL,
  `updaterName` varchar(255) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `configKey` (`configKey`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of config
-- ----------------------------

-- ----------------------------
-- Table structure for `contacts`
-- ----------------------------
DROP TABLE IF EXISTS `contacts`;
CREATE TABLE `contacts` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `phone` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `customerId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKE207C47341DB3882` (`customerId`),
  CONSTRAINT `FKE207C47341DB3882` FOREIGN KEY (`customerId`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of contacts
-- ----------------------------

-- ----------------------------
-- Table structure for `contractmanager`
-- ----------------------------
DROP TABLE IF EXISTS `contractmanager`;
CREATE TABLE `contractmanager` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `itemNo` varchar(255) DEFAULT NULL,
  `itemName` varchar(255) DEFAULT NULL,
  `custAuthorizedPer` varchar(255) DEFAULT NULL,
  `custContactPer` varchar(255) DEFAULT NULL,
  `custqq` varchar(255) DEFAULT NULL,
  `custEmail` varchar(255) DEFAULT NULL,
  `custAddress` varchar(255) DEFAULT NULL,
  `contactPer` varchar(255) DEFAULT NULL,
  `qq` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `startDate` datetime DEFAULT NULL,
  `endDate` datetime DEFAULT NULL,
  `cycle` varchar(255) DEFAULT NULL,
  `contractAddr` varchar(255) DEFAULT NULL,
  `cpu` varchar(255) DEFAULT NULL,
  `memory` varchar(255) DEFAULT NULL,
  `hardDisk` varchar(255) DEFAULT NULL,
  `bandwidth` varchar(255) DEFAULT NULL,
  `server` varchar(255) DEFAULT NULL,
  `devetools` varchar(255) DEFAULT NULL,
  `dataValue` varchar(255) DEFAULT NULL,
  `codeMana` varchar(255) DEFAULT NULL,
  `envirVarible` varchar(255) DEFAULT NULL,
  `other` varchar(255) DEFAULT NULL,
  `startDatePlan` date DEFAULT NULL,
  `endDatePlan` date DEFAULT NULL,
  `productMana` varchar(255) DEFAULT NULL,
  `projectLeader` varchar(255) DEFAULT NULL,
  `groupLeader` varchar(255) DEFAULT NULL,
  `groupPer` varchar(255) DEFAULT NULL,
  `ExtCooperation` varchar(255) DEFAULT NULL,
  `ExtTeam` varchar(255) DEFAULT NULL,
  `ExtEndTime` date DEFAULT NULL,
  `TestPer` varchar(255) DEFAULT NULL,
  `ExtContent` varchar(255) DEFAULT NULL,
  `ExtPer` varchar(255) DEFAULT NULL,
  `accepData` varchar(255) DEFAULT NULL,
  `accDelivery` varchar(255) DEFAULT NULL,
  `sourceCode` varchar(255) DEFAULT NULL,
  `souDelivery` varchar(255) DEFAULT NULL,
  `serverProvider` varchar(255) DEFAULT NULL,
  `serverVendor` varchar(255) DEFAULT NULL,
  `serverAddr` varchar(255) DEFAULT NULL,
  `serverAccount` varchar(255) DEFAULT NULL,
  `serverPassword` varchar(255) DEFAULT NULL,
  `domainName` varchar(255) DEFAULT NULL,
  `managementAddr` varchar(255) DEFAULT NULL,
  `managementAccount` varchar(255) DEFAULT NULL,
  `managementPassword` varchar(255) DEFAULT NULL,
  `RemServerAddr` varchar(255) DEFAULT NULL,
  `localServerAddr` varchar(255) DEFAULT NULL,
  `localServerBackUp` varchar(255) DEFAULT NULL,
  `hardDiskBackUp` varchar(255) DEFAULT NULL,
  `contractNO` varchar(255) DEFAULT NULL,
  `contractPrice` varchar(255) DEFAULT NULL,
  `photoScan` varchar(255) DEFAULT NULL,
  `signDate` date DEFAULT NULL,
  `completDate` date DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `paymentTerms` varchar(255) DEFAULT NULL,
  `havetopay` int(11) DEFAULT NULL,
  `payDate` date DEFAULT NULL,
  `planProject` date DEFAULT NULL,
  `planCost` int(11) DEFAULT NULL,
  `construction` varchar(255) DEFAULT NULL,
  `beyond` int(11) DEFAULT NULL,
  `wage` int(11) DEFAULT NULL,
  `BusinessFee` int(11) DEFAULT NULL,
  `cooperationFee` int(11) DEFAULT NULL,
  `taxFee` int(11) DEFAULT NULL,
  `travelFee` int(11) DEFAULT NULL,
  `entertainment` int(11) DEFAULT NULL,
  `other1` varchar(255) DEFAULT NULL,
  `other2` varchar(255) DEFAULT NULL,
  `other3` varchar(255) DEFAULT NULL,
  `other4` varchar(255) DEFAULT NULL,
  `age` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `envirVaribel` varchar(255) DEFAULT NULL,
  `departmentId` int(11) DEFAULT NULL,
  `employeeId` int(11) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `addPersonId` int(11) DEFAULT NULL,
  `addPersonName` varchar(255) DEFAULT NULL,
  `createDate` date DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK478F561B5B1393AA` (`departmentId`),
  KEY `FK478F561BCD1F9222` (`employeeId`),
  CONSTRAINT `FK478F561B5B1393AA` FOREIGN KEY (`departmentId`) REFERENCES `department` (`id`),
  CONSTRAINT `FK478F561BCD1F9222` FOREIGN KEY (`employeeId`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of contractmanager
-- ----------------------------
INSERT INTO `contractmanager` VALUES ('1', '', '', '', null, null, null, null, null, null, null, null, null, '', null, '', '', '', '', '', '', null, '', '', '', null, null, '', '', '', '', '', '', null, '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', null, null, null, ',,,', null, null, null, null, '', null, null, null, null, null, null, null, null, null, null, null, '21', '18300001119', null, null, null, '1', '3064', null, '2017-07-06', '2017-07-06 10:10:56');
INSERT INTO `contractmanager` VALUES ('2', 'JS20170809A', 'CRM系统平台项目', '小黑', null, null, null, null, null, null, null, '2017-07-05 00:00:00', '2017-07-06 00:00:00', '3个月', null, '', '', '10T', '', '', '', null, '', '', '', '2017-07-06', '2017-07-07', '', '肖勇', '', '', '', '', null, '', '', '', '', '未交付', '', '未交付', '', '', '', '', '', '', '', '', '', '', '', '', '', 'JS20170809A', '', '未扫描', null, null, null, ',,,,,,,', null, null, null, null, '', null, null, null, null, null, null, null, null, null, null, null, '2', '18300001119', null, null, null, '0', '3064', null, '2017-07-08', '2017-07-08 15:54:40');
INSERT INTO `contractmanager` VALUES ('3', 'JS20170809A', 'CRM系统平台项目', '小黑', null, null, null, null, null, null, null, '2017-07-05 00:00:00', '2017-07-06 00:00:00', '3个月', null, '', '', '10T', '', '', '', null, '', '', '', '2017-07-06', '2017-07-07', '', '肖勇', '', '', '', '', null, '', '', '', '', '', 'asdqw', '', '', '', '', '', '', '', '', '', '', '', '', '', '', 'JS20170809A', '', '', null, null, null, ',,,,,,,,,,,,,,,', null, null, null, null, '', null, null, null, null, null, null, null, null, null, null, null, '2', '18300001119', null, null, null, '0', '3064', null, '2017-07-06', '2017-07-06 10:35:09');
INSERT INTO `contractmanager` VALUES ('4', 'JS20170809A', 'CRM系统平台项目', '小黑', null, null, null, null, null, null, null, '2017-07-05 00:00:00', '2017-07-06 00:00:00', '3个月', null, '', '', '', '', '', '', null, '', '', '', '2017-07-06', '2017-07-07', '', '肖勇', '', '', '', '', null, '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', 'JS20170809A', '', '已交付,', null, null, null, ',,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,', null, null, null, null, '', null, null, null, null, null, null, null, null, null, null, null, '2', '18300001119', null, null, null, '0', '3064', null, '2017-07-07', '2017-07-07 16:26:42');
INSERT INTO `contractmanager` VALUES ('5', '', 'sasdqw', 'asdqw', null, null, null, null, null, null, null, null, null, '', null, '', '', '', '', '', '', null, '', '', '', null, null, '', '', '', '', '', '', null, '', '', '', '', '已交付', '', '已交付', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '已扫描', null, null, null, ',,,,,,,', null, null, null, null, '', null, null, null, null, null, null, null, null, null, null, null, '', '18300001114', null, null, null, '0', '3064', null, '2017-07-07', '2017-07-07 16:38:21');

-- ----------------------------
-- Table structure for `creditcard`
-- ----------------------------
DROP TABLE IF EXISTS `creditcard`;
CREATE TABLE `creditcard` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bank` varchar(255) DEFAULT NULL,
  `cardNumber` varchar(255) DEFAULT NULL,
  `creditLimit` varchar(255) DEFAULT NULL,
  `overdueCase` varchar(255) DEFAULT NULL,
  `publishCardDate` date DEFAULT NULL,
  `customerId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK552751C941DB3882` (`customerId`),
  CONSTRAINT `FK552751C941DB3882` FOREIGN KEY (`customerId`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of creditcard
-- ----------------------------

-- ----------------------------
-- Table structure for `customer`
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `addPersonId` int(11) DEFAULT NULL,
  `addPersonName` varchar(255) DEFAULT NULL,
  `addType` int(11) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `attentionLevel` varchar(255) DEFAULT NULL,
  `bondExpireDate` date DEFAULT NULL,
  `callCount` int(11) DEFAULT NULL,
  `census` varchar(255) DEFAULT NULL,
  `countdown` int(11) DEFAULT NULL,
  `createDate` date DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `credit` varchar(255) DEFAULT NULL,
  `customerAsset` longtext,
  `customerAssetTitile` varchar(500) DEFAULT NULL,
  `customerCharacter` varchar(255) DEFAULT NULL,
  `customerLevel` varchar(255) DEFAULT NULL,
  `dataPercent` double DEFAULT NULL,
  `debtTotal` double DEFAULT NULL,
  `embodiment` varchar(255) DEFAULT NULL,
  `enterpriseNature` varchar(255) DEFAULT NULL,
  `fllowDate` datetime DEFAULT NULL,
  `fundUse` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `isFollow` int(11) DEFAULT NULL,
  `lendingInstitution` varchar(255) DEFAULT NULL,
  `loanType` varchar(255) DEFAULT NULL,
  `marriage` varchar(255) DEFAULT NULL,
  `monthIncome` varchar(255) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `otherInfo` longtext,
  `phone` varchar(100) DEFAULT NULL,
  `phoneRemark` varchar(255) DEFAULT NULL,
  `publicView` int(11) DEFAULT NULL,
  `referrerId` int(11) DEFAULT NULL,
  `repaymentLimit` varchar(255) DEFAULT NULL,
  `repaymentType` varchar(255) DEFAULT NULL,
  `requiredMoney` varchar(255) DEFAULT NULL,
  `signState` varchar(255) DEFAULT NULL,
  `socialInsurance` varchar(255) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `turnDetail` varchar(255) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `useDate` varchar(255) DEFAULT NULL,
  `visibility` int(11) DEFAULT NULL,
  `visitCount` int(11) DEFAULT NULL,
  `workYear` varchar(255) DEFAULT NULL,
  `customerRosterId` int(11) DEFAULT NULL,
  `customerSourceId` int(11) DEFAULT NULL,
  `departmentId` int(11) DEFAULT NULL,
  `employeeId` int(11) DEFAULT NULL,
  `receiveDate` date DEFAULT NULL,
  `releaseDate` date DEFAULT NULL,
  `releaseId` int(11) DEFAULT NULL,
  `releaseTime` datetime DEFAULT NULL,
  `releaseType` int(11) DEFAULT NULL,
  `companyId` int(11) DEFAULT NULL,
  `companyName` varchar(255) DEFAULT NULL,
  `lastFollowId` int(11) DEFAULT NULL,
  `lastFollowType` int(11) DEFAULT NULL,
  `lastRemindContent` varchar(255) DEFAULT NULL,
  `lastRemindTime` date DEFAULT NULL,
  `lastWarrantFollowId` int(11) DEFAULT NULL,
  `lastWarrantFollowType` int(11) DEFAULT NULL,
  `lastWarrantRemindContent` varchar(255) DEFAULT NULL,
  `lastWarrantRemindTime` date DEFAULT NULL,
  `listCourse` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `phone` (`phone`),
  KEY `FK27FBE3FEB559A1B8` (`customerRosterId`),
  KEY `FK27FBE3FE223CF0D8` (`customerSourceId`),
  KEY `FK27FBE3FE5B1393AA` (`departmentId`),
  KEY `FK27FBE3FECD1F9222` (`employeeId`),
  KEY `INDEX_fllowDate` (`fllowDate`),
  KEY `INDEX_loanType` (`loanType`),
  KEY `INDEX_name` (`name`),
  KEY `INDEX_publicView` (`publicView`),
  KEY `INDEX_signState` (`signState`),
  KEY `INDEX_state` (`state`),
  KEY `INDEX_visibility` (`visibility`),
  KEY `INDEX_companyId` (`companyId`),
  KEY `INDEX_lastFollowType` (`lastFollowType`),
  CONSTRAINT `customer_ibfk_1` FOREIGN KEY (`customerSourceId`) REFERENCES `customersource` (`id`),
  CONSTRAINT `customer_ibfk_2` FOREIGN KEY (`departmentId`) REFERENCES `department` (`id`),
  CONSTRAINT `customer_ibfk_3` FOREIGN KEY (`customerRosterId`) REFERENCES `customerroster` (`id`),
  CONSTRAINT `customer_ibfk_4` FOREIGN KEY (`employeeId`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=275296 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES ('275271', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `customer` VALUES ('275272', '3057', '杨沛鑫', '2', null, 'C', null, '0', '', null, '2017-06-15', '2017-06-15 10:22:33', '两年内少数逾期', '', '', '', '中', null, null, '现金', '公务员', '2017-06-15 10:22:33', '', '男', null, '', '', '', '3000以下', 'asdq', 'asdq', '18302812281', 'kjhasd', '1', null, '', '', '123123', '未签单', '无', '9', '由【业务一部-杨沛鑫】移交到【业务一部-张三】2017-07-08', null, null, null, '0', '半年以内', null, '11', '1', '3058', null, '2017-07-08', null, null, null, '1', null, null, null, null, null, null, null, null, null, null);
INSERT INTO `customer` VALUES ('275273', '3057', '杨沛鑫', '2', '12', 'C', null, '0', '', null, '2017-06-15', '2017-06-15 10:26:13', '信用良好', '', '', '', '中', null, null, '自由收入', '公务员', '2017-06-15 10:26:13', '', '男', null, '', null, '', '3000-5000', 'asdqw', '1asdasdq', '13882372682', 'kjhsadk', null, null, '', '', '1231234', '已签单', '公司购买一年内', '4', null, null, null, null, '0', '半年以内', null, '11', '1', '3057', null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null);
INSERT INTO `customer` VALUES ('275274', '3057', '杨沛鑫', '2', '12', 'C', null, '0', '', null, '2017-06-15', '2017-06-15 10:37:16', null, '', '', '', '中', null, null, null, null, '2017-06-15 10:37:16', '', '男', null, '', null, '', null, '卡三等奖', '阿萨', '13302812281', '卡会受到', null, null, '', '', '121212', '已签单', null, '5', null, '2017-07-08 15:48:30', null, null, '0', '20-50人', null, '12', '1', '3057', null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null);
INSERT INTO `customer` VALUES ('275275', '3057', '杨沛鑫', '2', '12', 'C', null, '0', null, null, '2017-06-15', '2017-06-15 10:48:12', null, '', '', '', '中', null, null, null, null, '2017-06-15 10:48:12', '', '男', null, null, null, null, null, '偶记', '阿萨德完全', '13333332222', '卡接收到', null, null, '', '', '', '未签单', null, '8', null, null, null, null, '0', '20-50人', null, '12', '1', '3057', null, '2017-06-17', null, null, null, '1', null, null, null, null, null, null, null, null, null, null);
INSERT INTO `customer` VALUES ('275276', '3057', '杨沛鑫', '2', null, 'C', null, '0', null, null, '2017-06-15', '2017-06-15 10:49:56', null, '', '', '', '中', null, null, null, null, '2017-06-15 10:49:56', '', '男', null, null, null, null, null, '跟他谈谈', '阿萨德群我大', '13211112222', '加速度', null, null, '', '', '', '已签单', null, '4', null, '2017-07-02 23:56:06', null, null, '0', '20人以内', null, '11', '1', '3057', null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null);
INSERT INTO `customer` VALUES ('275277', '3057', '杨沛鑫', '2', '12', 'A', null, '1', null, null, '2017-06-15', '2017-06-15 10:59:10', null, '', '', '', '优', null, null, null, null, '2017-06-17 11:21:42', '', '男', null, null, null, null, null, 'asdq', 'asd', '13111111111', 'sdasdqwe', '1', null, '', '', null, '未签单', null, '9', null, '2017-06-16 18:25:24', null, '0', '0', '50人以上', null, '21', '1', '3057', '2017-06-17', '2017-06-17', null, null, null, '1', null, '13', '1', 'nasdipaskp', '2017-06-17', null, null, null, null, null);
INSERT INTO `customer` VALUES ('275278', '3057', '杨沛鑫', '2', '22', 'C', null, '2', null, null, '2017-06-15', '2017-06-15 11:02:05', null, '', '', '还可以', '中', null, null, null, null, '2017-06-15 16:51:05', '', '男', null, null, null, null, null, '李浩', '客户想要获得更多优惠', '13122222222', '客户还不错', null, null, '酒类批发', '', null, '未签单', null, '8', null, '2017-06-15 22:22:26', null, '0', '0', '50人以上', null, '11', '1', '3057', null, '2017-06-17', null, null, null, '1', null, '8', '1', '记得跟客户沟通', null, null, null, null, null, null);
INSERT INTO `customer` VALUES ('275279', '1', 'admin', '1', null, 'C', null, '0', null, null, '2017-06-16', '2017-06-16 18:29:31', null, null, null, null, '中', null, null, null, null, '2017-06-16 18:29:31', null, null, null, null, null, null, null, '导入客户1', '备注信息', '18898765432', null, null, null, null, null, null, '未签单', null, '2', null, '2017-06-17 13:11:28', null, null, '0', null, '1', null, '1', '3057', null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null);
INSERT INTO `customer` VALUES ('275280', '1', 'admin', '1', null, 'C', null, '0', null, null, '2017-06-16', '2017-06-16 18:29:31', null, null, null, null, '中', null, null, null, null, '2017-06-16 18:29:31', null, null, null, null, null, null, null, '导入客户2', '备注信息', '18898765433', null, null, null, null, null, null, '未签单', null, '2', '由【业务一部-张三】移交到【业务一部-杨沛鑫】2017-07-06', '2017-06-17 13:53:40', null, null, '0', null, '1', null, '1', '3057', null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null);
INSERT INTO `customer` VALUES ('275281', '1', 'admin', '1', null, 'C', null, '0', null, null, '2017-06-16', '2017-06-16 18:29:31', null, null, null, null, '中', null, null, null, null, '2017-06-16 18:29:31', null, null, null, null, null, null, null, '导入客户3', '备注信息', '18898765434', null, null, null, null, null, null, '未签单', null, '1', null, '2017-07-06 13:55:21', null, null, '0', null, '1', null, '1', null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null);
INSERT INTO `customer` VALUES ('275282', '1', 'admin', '1', null, 'C', null, '0', null, null, '2017-06-16', '2017-06-16 18:29:31', null, null, null, null, '中', null, null, null, null, '2017-06-16 18:29:31', null, null, null, null, null, null, null, '导入客户4', '备注信息', '18898765435', null, null, null, null, null, null, '未签单', null, '0', null, null, null, null, '0', null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null);
INSERT INTO `customer` VALUES ('275283', '1', 'admin', '1', null, 'C', null, '0', null, null, '2017-06-16', '2017-06-16 18:29:31', null, null, null, null, '中', null, null, null, null, '2017-06-16 18:29:31', null, null, null, null, null, null, null, '导入客户5', '备注信息', '18898765436', null, null, null, null, null, null, '未签单', null, '0', null, null, null, null, '0', null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null);
INSERT INTO `customer` VALUES ('275284', '1', 'admin', '1', null, 'C', null, '0', null, null, '2017-06-16', '2017-06-16 18:29:31', null, null, null, null, '中', null, null, null, null, '2017-06-16 18:29:31', null, null, null, null, null, null, null, '导入客户6', '备注信息', '18898765437', null, null, null, null, null, null, '未签单', null, '0', null, null, null, null, '0', null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null);
INSERT INTO `customer` VALUES ('275285', '3057', '杨沛鑫', '2', '22', 'C', null, '1', null, null, '2017-06-17', '2017-06-17 10:43:27', null, '', '', '还可以', '中', null, null, null, null, '2017-06-17 11:52:53', '', '男', null, null, null, null, null, '佳胜科技', '客户还是想要更多优惠', '18721113322', '是个好客户', null, null, null, null, '零售批发', '已签单', null, '4', null, '2017-06-17 11:52:53', null, '1', '0', '20人以内', null, '11', '1', '3057', null, null, null, null, null, '1', null, '25', '1', '记得打电话联系一下客户', '2017-06-18', null, null, null, null, null);
INSERT INTO `customer` VALUES ('275286', '3057', '杨沛鑫', '2', '23', 'C', null, '1', null, null, '2017-06-17', '2017-06-17 10:51:04', null, '', '', '还行吧', '中', null, null, null, null, '2017-06-17 11:52:31', '', '男', null, null, null, null, null, '鸿业远图', '没啥好说的', '13882221111', '阿萨德科技回去问', null, null, null, null, '食品批发', '已签单', null, '4', null, '2017-06-17 11:52:31', null, '1', '0', '50人以上', null, '11', '1', '3057', '2017-06-17', '2017-06-17', null, null, null, '1', null, null, null, null, null, null, null, null, null, null);
INSERT INTO `customer` VALUES ('275287', '3057', '杨沛鑫', '2', '23', 'C', null, '0', null, null, '2017-06-17', '2017-06-17 10:57:34', null, '', '', '还行', '中', null, null, null, null, '2017-06-17 10:57:34', '', '男', null, null, null, null, null, '成都佳胜', '啊实打实去', '13113113111', '卡萨丁', null, null, '什么都做', '武侯区,', '各种各样', '未签单', null, '8', null, null, null, null, '0', '50人以上', null, '12', '1', '3057', null, '2017-06-17', null, null, null, '1', null, null, null, null, null, null, null, null, null, null);
INSERT INTO `customer` VALUES ('275288', '3056', '王春梅', '2', '21', 'C', null, '0', null, null, '2017-06-17', '2017-06-17 13:45:29', null, '', '', '打我去', '中', null, null, null, null, '2017-07-08 15:50:28', '', '男', null, null, null, null, null, '卡萨丁', '阿萨德', '13212121221', '捱三顶五', null, null, '阿萨德群翁', '前雾灯阿萨,', '阿萨德群', '未签单', null, '3', null, null, null, null, '0', '50人以上', null, '11', '1', '3058', '2017-07-08', '2017-06-17', null, null, null, '1', null, null, null, null, null, null, null, null, null, null);
INSERT INTO `customer` VALUES ('275289', '1', 'admin', '2', '28', 'C', null, '0', '', null, '2017-07-02', '2017-07-02 12:55:23', null, '', '', '还是不错的', '中', null, null, null, '事业单位', '2017-07-02 12:55:23', '', '男', null, '', ' ', '', '', '杨先生,成都信息科技,微信营销', '还是挺想做好的，还是要做', '18111112221', '性格比较温和', null, null, '食品', '武侯区,,', '餐饮,餐饮', '已签单', null, '4', null, '2017-07-07 17:44:05', null, null, '0', null, null, '52', '8', '1', null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null);
INSERT INTO `customer` VALUES ('275290', '1', 'admin', '2', '32', 'C', null, '0', null, null, '2017-07-02', '2017-07-02 12:57:21', 'credit', 'credit', '', '', '中', null, null, null, null, '2017-07-02 12:57:21', '未婚', '男', null, '成都映像', '微信营销', '', null, '李先生', '', '18111112227', '还行', null, null, '', ',', '', '已签单', null, '4', null, '2017-07-03 23:14:02', 'loanType', null, '0', '', null, '11', '8', '1', null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null);
INSERT INTO `customer` VALUES ('275291', '999', 'SuperAdmin', '2', null, 'C', null, '0', null, null, '2017-07-05', '2017-07-05 10:10:42', null, '', '', '', '中', null, null, null, null, '2017-07-05 10:10:42', '', '男', null, null, '', null, null, 'aasd', '', '18300001111', 'asdasd', null, null, '', ',', '', '未签单', null, '3', null, null, null, null, '0', '', null, '12', null, '999', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `customer` VALUES ('275292', '3061', 'xiaohei', '2', '22', 'C', null, '0', '', null, '2017-07-05', '2017-07-05 10:11:14', null, '', '', '', '中', null, null, null, '事业单位', '2017-07-05 10:11:14', '', '男', null, '', '  ', '', '', 'asdq', '', '18300001113', 'sasdq', null, null, '', ',,,', ',,,', '未签单', null, '3', '由【业务一部-xiaohei】移交到【业务一部-杨沛鑫】2017-07-06', '2017-07-07 17:53:19', null, null, '0', null, null, '11', '1', '3057', null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null);
INSERT INTO `customer` VALUES ('275293', '3062', '大黑', '2', '22', 'C', null, '0', '', null, '2017-07-05', '2017-07-05 10:17:25', null, '', '', '不好', '中', null, null, null, '', '2017-07-05 10:17:25', '', '男', null, '', 'qwd  ', '', '', 'as', '', '18300001112', 'zsda', null, null, '', ',,,', '阿萨德请问多,,', '未签单', null, '3', '由【业务一部-大黑】移交到【业务一部-杨沛鑫】2017-07-06', '2017-07-07 17:40:40', null, null, '0', null, null, '11', '1', '3057', null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null);
INSERT INTO `customer` VALUES ('275294', '1', 'admin', '2', null, 'C', null, '0', '', null, '2017-07-06', '2017-07-06 14:03:45', null, '', '', '', '中', null, null, null, '', '2017-07-06 14:03:45', '', '男', null, '', '  ', '', '', '111', '', '18280049181', '', null, null, '', ',,,', ',,,', '未签单', null, '3', null, '2017-07-07 17:40:20', null, null, '0', null, null, '12', '8', '1', null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null);
INSERT INTO `customer` VALUES ('275295', '1', 'admin', '2', null, 'C', null, '0', null, null, '2017-07-11', '2017-07-11 11:14:15', null, '', '', '', '中', null, null, null, null, '2017-07-11 11:14:15', '', '男', null, null, '', null, null, 'asd', '', '18302812211', 'asd', null, null, '', ',', '', '未签单', null, '3', null, null, null, null, '0', '', null, '11', '8', '1', null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for `customerfollow`
-- ----------------------------
DROP TABLE IF EXISTS `customerfollow`;
CREATE TABLE `customerfollow` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `companyId` int(11) DEFAULT NULL,
  `edtDate` date DEFAULT NULL,
  `edtTime` datetime DEFAULT NULL,
  `feedbackContent` longtext,
  `remindContent` longtext,
  `remindTime` date DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `visibility` int(11) DEFAULT NULL,
  `customerId` int(11) DEFAULT NULL,
  `employeeId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2076954F41DB3882` (`customerId`),
  KEY `FK2076954FCD1F9222` (`employeeId`),
  CONSTRAINT `FK2076954F41DB3882` FOREIGN KEY (`customerId`) REFERENCES `customer` (`id`),
  CONSTRAINT `FK2076954FCD1F9222` FOREIGN KEY (`employeeId`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customerfollow
-- ----------------------------
INSERT INTO `customerfollow` VALUES ('1', null, '2017-06-15', '2017-06-15 10:22:33', null, '业务员新增客户', null, null, null, '275272', '3057');
INSERT INTO `customerfollow` VALUES ('2', null, '2017-06-15', '2017-06-15 10:26:13', null, '业务员新增客户', null, null, null, '275273', '3057');
INSERT INTO `customerfollow` VALUES ('3', null, '2017-06-15', '2017-06-15 10:37:16', null, '业务员新增客户', null, null, null, '275274', '3057');
INSERT INTO `customerfollow` VALUES ('4', null, '2017-06-15', '2017-06-15 10:48:12', null, '业务员新增客户', null, null, null, '275275', '3057');
INSERT INTO `customerfollow` VALUES ('5', null, '2017-06-15', '2017-06-15 10:49:56', null, '业务员新增客户', null, null, null, '275276', '3057');
INSERT INTO `customerfollow` VALUES ('6', null, '2017-06-15', '2017-06-15 10:59:11', null, '业务员新增客户', null, null, null, '275277', '3057');
INSERT INTO `customerfollow` VALUES ('7', null, '2017-06-15', '2017-06-15 11:02:05', null, '业务员新增客户', null, null, null, '275278', '3057');
INSERT INTO `customerfollow` VALUES ('8', '1', '2017-06-15', '2017-06-15 14:24:29', '暂时还没得', '记得跟客户沟通', '2017-06-15', '1', '0', '275278', '3057');
INSERT INTO `customerfollow` VALUES ('9', null, '2017-06-15', '2017-06-15 15:01:46', null, '成功签约', null, '4', null, '275278', '3057');
INSERT INTO `customerfollow` VALUES ('10', null, '2017-06-15', '2017-06-15 15:02:53', null, '成功签约', null, '4', null, '275278', '3057');
INSERT INTO `customerfollow` VALUES ('11', '1', '2017-06-15', '2017-06-15 16:51:05', '', '阿萨', null, '1', '0', '275278', '3057');
INSERT INTO `customerfollow` VALUES ('12', null, '2017-06-16', '2017-06-16 18:24:50', null, '成功签约', null, '4', null, '275277', '3057');
INSERT INTO `customerfollow` VALUES ('13', '1', '2017-06-16', '2017-06-16 18:25:24', '', 'nasdipaskp', '2017-06-17', '1', '0', '275277', '3057');
INSERT INTO `customerfollow` VALUES ('14', null, '2017-06-16', '2017-06-16 18:29:31', null, '批量导入客户', null, null, null, '275279', '1');
INSERT INTO `customerfollow` VALUES ('15', null, '2017-06-16', '2017-06-16 18:29:31', null, '批量导入客户', null, null, null, '275280', '1');
INSERT INTO `customerfollow` VALUES ('16', null, '2017-06-16', '2017-06-16 18:29:31', null, '批量导入客户', null, null, null, '275281', '1');
INSERT INTO `customerfollow` VALUES ('17', null, '2017-06-16', '2017-06-16 18:29:31', null, '批量导入客户', null, null, null, '275282', '1');
INSERT INTO `customerfollow` VALUES ('18', null, '2017-06-16', '2017-06-16 18:29:31', null, '批量导入客户', null, null, null, '275283', '1');
INSERT INTO `customerfollow` VALUES ('19', null, '2017-06-16', '2017-06-16 18:29:31', null, '批量导入客户', null, null, null, '275284', '1');
INSERT INTO `customerfollow` VALUES ('20', null, '2017-06-17', '2017-06-17 10:43:27', null, '业务员新增客户', null, null, null, '275285', '3057');
INSERT INTO `customerfollow` VALUES ('21', null, '2017-06-17', '2017-06-17 10:51:04', null, '业务员新增客户', null, null, null, '275286', '3057');
INSERT INTO `customerfollow` VALUES ('22', null, '2017-06-17', '2017-06-17 10:57:34', null, '业务员新增客户', null, null, null, '275287', '3057');
INSERT INTO `customerfollow` VALUES ('23', null, '2017-06-17', '2017-06-17 11:13:36', null, '成功签约', null, '4', null, '275287', '3057');
INSERT INTO `customerfollow` VALUES ('24', '1', '2017-06-17', '2017-06-17 11:52:31', '', '记得打电话联系一下', null, '1', '1', '275286', '3057');
INSERT INTO `customerfollow` VALUES ('25', '1', '2017-06-17', '2017-06-17 11:52:53', '', '记得打电话联系一下客户', '2017-06-18', '1', '1', '275285', '3057');
INSERT INTO `customerfollow` VALUES ('26', null, '2017-06-17', '2017-06-17 12:24:54', null, '成功签约', null, '4', null, '275285', '3057');
INSERT INTO `customerfollow` VALUES ('27', null, '2017-06-17', '2017-06-17 13:45:29', null, '业务员新增客户', null, null, null, '275288', '3056');
INSERT INTO `customerfollow` VALUES ('28', null, '2017-06-30', '2017-06-30 19:09:09', null, '成功签约', null, '4', null, '275276', '1');
INSERT INTO `customerfollow` VALUES ('29', null, '2017-07-02', '2017-07-02 12:55:23', null, '业务员新增客户', null, null, null, '275289', '1');
INSERT INTO `customerfollow` VALUES ('30', null, '2017-07-02', '2017-07-02 12:57:21', null, '业务员新增客户', null, null, null, '275290', '1');
INSERT INTO `customerfollow` VALUES ('31', null, '2017-07-03', '2017-07-03 15:29:06', null, '成功签约', null, '4', null, '275290', '1');
INSERT INTO `customerfollow` VALUES ('32', null, '2017-07-03', '2017-07-03 15:30:53', null, '成功签约', null, '4', null, '275289', '1');
INSERT INTO `customerfollow` VALUES ('33', null, '2017-07-03', '2017-07-03 16:49:30', null, '成功签约', null, '4', null, '275274', '1');
INSERT INTO `customerfollow` VALUES ('34', null, '2017-07-03', '2017-07-03 16:58:59', null, '成功签约', null, '4', null, '275273', '1');
INSERT INTO `customerfollow` VALUES ('35', null, '2017-07-03', '2017-07-03 17:04:49', null, '成功签约', null, '4', null, '275272', '1');
INSERT INTO `customerfollow` VALUES ('36', null, '2017-07-03', '2017-07-03 17:20:35', null, '成功签约', null, '4', null, '275286', '1');
INSERT INTO `customerfollow` VALUES ('37', null, '2017-07-05', '2017-07-05 10:10:42', null, '业务员新增客户', null, null, null, '275291', '999');
INSERT INTO `customerfollow` VALUES ('38', null, '2017-07-05', '2017-07-05 10:11:14', null, '业务员新增客户', null, null, null, '275292', '3061');
INSERT INTO `customerfollow` VALUES ('39', null, '2017-07-05', '2017-07-05 10:17:25', null, '业务员新增客户', null, null, null, '275293', '3062');
INSERT INTO `customerfollow` VALUES ('40', null, '2017-07-06', '2017-07-06 14:03:45', null, '业务员新增客户', null, null, null, '275294', '1');
INSERT INTO `customerfollow` VALUES ('41', null, '2017-07-11', '2017-07-11 11:14:15', null, '业务员新增客户', null, null, null, '275295', '1');

-- ----------------------------
-- Table structure for `customerroster`
-- ----------------------------
DROP TABLE IF EXISTS `customerroster`;
CREATE TABLE `customerroster` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `companyId` int(11) DEFAULT NULL,
  `count` int(11) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `employeeId` int(11) DEFAULT NULL,
  `employeeName` varchar(255) DEFAULT NULL,
  `failureCount` int(11) DEFAULT NULL,
  `fileName` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `newFileName` varchar(255) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `successCount` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customerroster
-- ----------------------------
INSERT INTO `customerroster` VALUES ('1', null, '6', '2017-06-16 18:29:31', '1', 'admin', '0', '蜀创批量导入客户模板.xls', '佳胜', '20170616182931413-eId-1-eName-admin-蜀创批量导入客户模板.xls', null, '6');

-- ----------------------------
-- Table structure for `customersource`
-- ----------------------------
DROP TABLE IF EXISTS `customersource`;
CREATE TABLE `customersource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `remark` varchar(50) DEFAULT NULL,
  `sourceName` varchar(15) DEFAULT NULL,
  `companyId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `sourceName` (`sourceName`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customersource
-- ----------------------------
INSERT INTO `customersource` VALUES ('11', '', '中介', null);
INSERT INTO `customersource` VALUES ('12', '', '名单', null);
INSERT INTO `customersource` VALUES ('21', '', '介绍', '1');
INSERT INTO `customersource` VALUES ('22', '', '老客户', '1');
INSERT INTO `customersource` VALUES ('26', '', '朋友', '1');
INSERT INTO `customersource` VALUES ('27', '', '市场营销', '1');
INSERT INTO `customersource` VALUES ('28', '', '微信', '1');
INSERT INTO `customersource` VALUES ('32', '', '签单客户名单', null);
INSERT INTO `customersource` VALUES ('44', '', '老名单', null);
INSERT INTO `customersource` VALUES ('52', '', '网站', null);

-- ----------------------------
-- Table structure for `customerturn`
-- ----------------------------
DROP TABLE IF EXISTS `customerturn`;
CREATE TABLE `customerturn` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cause` varchar(255) DEFAULT NULL,
  `companyId` int(11) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `otherCause` varchar(255) DEFAULT NULL,
  `customerId` int(11) DEFAULT NULL,
  `nowEmpId` int(11) DEFAULT NULL,
  `oldEmpId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3F75A99B41DB3882` (`customerId`),
  KEY `FK3F75A99B2683C0A6` (`nowEmpId`),
  KEY `FK3F75A99BCF4627F5` (`oldEmpId`),
  CONSTRAINT `FK3F75A99B2683C0A6` FOREIGN KEY (`nowEmpId`) REFERENCES `employee` (`id`),
  CONSTRAINT `FK3F75A99B41DB3882` FOREIGN KEY (`customerId`) REFERENCES `customer` (`id`),
  CONSTRAINT `FK3F75A99BCF4627F5` FOREIGN KEY (`oldEmpId`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customerturn
-- ----------------------------
INSERT INTO `customerturn` VALUES ('1', '离职', null, '2017-07-03 22:55:17', '', '275272', '3058', '3057');
INSERT INTO `customerturn` VALUES ('2', '离职', null, '2017-07-03 22:55:41', '', '275280', '3058', '3057');
INSERT INTO `customerturn` VALUES ('3', '离职', null, '2017-07-06 15:01:18', '', '275292', '3057', '3061');
INSERT INTO `customerturn` VALUES ('4', '离职', null, '2017-07-06 15:02:07', '', '275293', '3057', '3062');
INSERT INTO `customerturn` VALUES ('5', '其他', null, '2017-07-06 16:39:08', '', '275272', '3057', '3058');
INSERT INTO `customerturn` VALUES ('6', '其他', null, '2017-07-06 16:39:08', '', '275272', '3057', '3058');
INSERT INTO `customerturn` VALUES ('7', '离职', null, '2017-07-08 15:49:11', '', '275272', '3058', '3057');

-- ----------------------------
-- Table structure for `department`
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `companyId` int(11) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES ('1', '业务一部', '', '1', '业务');
INSERT INTO `department` VALUES ('8', '行政部', '', '1', null);
INSERT INTO `department` VALUES ('9', '总经办', null, '1', null);

-- ----------------------------
-- Table structure for `emaillog`
-- ----------------------------
DROP TABLE IF EXISTS `emaillog`;
CREATE TABLE `emaillog` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `receiveEmail` varchar(255) DEFAULT NULL,
  `sendTime` datetime DEFAULT NULL,
  `senderEmail` varchar(255) DEFAULT NULL,
  `senderId` int(11) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of emaillog
-- ----------------------------

-- ----------------------------
-- Table structure for `employee`
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `census` varchar(255) DEFAULT NULL,
  `cornet` varchar(255) DEFAULT NULL,
  `dimissionDate` datetime DEFAULT NULL,
  `entryDate` datetime DEFAULT NULL,
  `entryDatum` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `headImg` varchar(255) DEFAULT NULL,
  `idCard` varchar(255) DEFAULT NULL,
  `jobNumber` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `relation` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `remarkPhone` varchar(255) DEFAULT NULL,
  `remarkPhoneName` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `talks` varchar(255) DEFAULT NULL,
  `tel` varchar(255) DEFAULT NULL,
  `departmentId` int(11) DEFAULT NULL,
  `roleId` int(11) DEFAULT NULL,
  `companyId` int(11) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `customerNO` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `FK4AFD4ACE5B1393AA` (`departmentId`),
  KEY `FK4AFD4ACE9D7C132` (`roleId`),
  CONSTRAINT `employee_ibfk_1` FOREIGN KEY (`roleId`) REFERENCES `role` (`id`),
  CONSTRAINT `employee_ibfk_2` FOREIGN KEY (`departmentId`) REFERENCES `department` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3065 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES ('1', 'admin', '成都', null, null, null, '2017-06-17 14:28:38', null, '女', 'upload/head/0.png', null, '001', '', 'admin', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, null, '1', '否', null, '8', '1', '1', null, null, '0');
INSERT INTO `employee` VALUES ('2', '0001', '', '是', '', null, null, '是', '男', '/crmupload/upload/head_2/张诗伟_副本.jpg', '', 'sc0001', '13980688546', '张诗伟(删除)', '123456', '', '', '', '', '2', '否', '', '9', '7', '1', null, null, '0');
INSERT INTO `employee` VALUES ('999', 'SuperAdmin', null, '是', null, null, null, '是', '男', 'upload/head/0.png', null, 'SuperAdmin', '15928877887', 'SuperAdmin', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, null, '1', '否', null, null, '999', null, 'SUPER_ROLE', '394974877@qq.com', '0');
INSERT INTO `employee` VALUES ('3056', 'wangchunmei', '成都', null, null, null, '2017-06-14 15:16:13', '是', '女', 'upload/head/0.png', null, 'wangchunmei', null, '王春梅(删除)', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, null, '2', '是', null, '1', '13', '1', null, null, '0');
INSERT INTO `employee` VALUES ('3057', 'yangpeixin', '成都', '是', null, null, null, null, '男', '/crmupload/upload/head_3057/QQ截图20170617144501.png', null, 'yangpeixin', null, '杨沛鑫', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, null, '1', '是', null, '1', '11', '1', null, null, '2000');
INSERT INTO `employee` VALUES ('3058', 'zhangsan', '', '是', '', null, null, '否', '男', 'upload/head/0.png', '', '123', '18302812281', '张三', 'E10ADC3949BA59ABBE56E057F20F883E', '', '', '', '', '1', '否', '', '1', '11', '1', 'NORMAL', null, '2000');
INSERT INTO `employee` VALUES ('3060', 'hrmanager1', '', '是', '', null, null, '是', '男', 'upload/head/0.png', '', '0100', '13302112211', 'hrmanager1', 'E10ADC3949BA59ABBE56E057F20F883E', '', '', '', '', '1', '否', '', '8', '14', '1', 'NORMAL', null, null);
INSERT INTO `employee` VALUES ('3061', 'xiaohei', '', '是', '', null, null, '是', '男', 'upload/head/0.png', '', '102', '18302812281', 'xiaohei(删除)', 'E10ADC3949BA59ABBE56E057F20F883E', '', '', '', '', '2', '否', '', '1', '11', '1', 'NORMAL', null, '2000');
INSERT INTO `employee` VALUES ('3062', 'dahei', '', '是', '', null, null, '是', '男', 'upload/head/0.png', '', '103', '18302812282', '大黑(删除)', 'E10ADC3949BA59ABBE56E057F20F883E', '', '', '', '', '2', '否', '', '1', '11', '1', 'NORMAL', null, '2000');
INSERT INTO `employee` VALUES ('3063', 'zhonghei', '', '是', '', null, null, '是', '男', 'upload/head/0.png', '', '102', '18302812281', '中黑(删除)', 'E10ADC3949BA59ABBE56E057F20F883E', '', '', '', '', '2', '否', '', '1', '10', '1', 'NORMAL', null, null);
INSERT INTO `employee` VALUES ('3064', 'projectMana', '', '是', '', null, null, '是', '男', 'upload/head/0.png', '', '0001', '18302812281', '王元', 'E10ADC3949BA59ABBE56E057F20F883E', '', '', '', '', '1', '否', '', '9', '1000', '1', 'NORMAL', null, null);

-- ----------------------------
-- Table structure for `enterprise`
-- ----------------------------
DROP TABLE IF EXISTS `enterprise`;
CREATE TABLE `enterprise` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `annualValue` varchar(255) DEFAULT NULL,
  `cardCustomer` varchar(255) DEFAULT NULL,
  `invoice` varchar(255) DEFAULT NULL,
  `license` varchar(255) DEFAULT NULL,
  `operationPlace` varchar(255) DEFAULT NULL,
  `operationScope` varchar(255) DEFAULT NULL,
  `operationTime` varchar(255) DEFAULT NULL,
  `shareRatio` varchar(255) DEFAULT NULL,
  `showDetail` varchar(255) DEFAULT NULL,
  `showStatute` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `customerId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK46F90F8141DB3882` (`customerId`),
  CONSTRAINT `FK46F90F8141DB3882` FOREIGN KEY (`customerId`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of enterprise
-- ----------------------------

-- ----------------------------
-- Table structure for `failuremessage`
-- ----------------------------
DROP TABLE IF EXISTS `failuremessage`;
CREATE TABLE `failuremessage` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cause` longtext,
  `remark` longtext,
  `time` datetime DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `signCustomerId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5EA7433DD872755C` (`signCustomerId`),
  CONSTRAINT `FK5EA7433DD872755C` FOREIGN KEY (`signCustomerId`) REFERENCES `signcustomer` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of failuremessage
-- ----------------------------
INSERT INTO `failuremessage` VALUES ('1', '客户不做了', '', '2017-06-28 00:00:00', '1', '16');

-- ----------------------------
-- Table structure for `house`
-- ----------------------------
DROP TABLE IF EXISTS `house`;
CREATE TABLE `house` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `area` varchar(255) DEFAULT NULL,
  `constructDate` varchar(255) DEFAULT NULL,
  `decorateCase` varchar(255) DEFAULT NULL,
  `landNature` varchar(255) DEFAULT NULL,
  `placeArea` varchar(255) DEFAULT NULL,
  `placeDetail` varchar(255) DEFAULT NULL,
  `placePremise` varchar(255) DEFAULT NULL,
  `placeStreet` varchar(255) DEFAULT NULL,
  `price` varchar(255) DEFAULT NULL,
  `rental` varchar(255) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `useCase` varchar(255) DEFAULT NULL,
  `customerId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK42AD70041DB3882` (`customerId`),
  CONSTRAINT `FK42AD70041DB3882` FOREIGN KEY (`customerId`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of house
-- ----------------------------

-- ----------------------------
-- Table structure for `land`
-- ----------------------------
DROP TABLE IF EXISTS `land`;
CREATE TABLE `land` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(200) DEFAULT NULL,
  `customerId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK24060B41DB3882` (`customerId`),
  CONSTRAINT `FK24060B41DB3882` FOREIGN KEY (`customerId`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of land
-- ----------------------------

-- ----------------------------
-- Table structure for `lendinginstitution`
-- ----------------------------
DROP TABLE IF EXISTS `lendinginstitution`;
CREATE TABLE `lendinginstitution` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of lendinginstitution
-- ----------------------------

-- ----------------------------
-- Table structure for `loanproduct`
-- ----------------------------
DROP TABLE IF EXISTS `loanproduct`;
CREATE TABLE `loanproduct` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `apr` varchar(255) DEFAULT NULL,
  `bankProduct` varchar(255) DEFAULT NULL,
  `lendingInstitution` int(11) DEFAULT NULL,
  `loanType` varchar(255) DEFAULT NULL,
  `loanYear` varchar(255) DEFAULT NULL,
  `signCustomerId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4132D9BFD872755C` (`signCustomerId`),
  CONSTRAINT `FK4132D9BFD872755C` FOREIGN KEY (`signCustomerId`) REFERENCES `signcustomer` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of loanproduct
-- ----------------------------
INSERT INTO `loanproduct` VALUES ('1', '', '', null, '', '', '1');
INSERT INTO `loanproduct` VALUES ('2', '', '', null, '', '', null);
INSERT INTO `loanproduct` VALUES ('3', '', '', null, '', '', '3');
INSERT INTO `loanproduct` VALUES ('4', '', '', null, '', '', '4');
INSERT INTO `loanproduct` VALUES ('5', '', '', null, '', '', '5');
INSERT INTO `loanproduct` VALUES ('6', '', '', null, '', '', null);
INSERT INTO `loanproduct` VALUES ('7', '', '', null, '', '', null);
INSERT INTO `loanproduct` VALUES ('8', '', '', null, '', '', '8');
INSERT INTO `loanproduct` VALUES ('16', '', '', null, '', '', null);
INSERT INTO `loanproduct` VALUES ('18', '', '', null, '', '', '18');
INSERT INTO `loanproduct` VALUES ('20', '', '', null, '', '', '20');
INSERT INTO `loanproduct` VALUES ('24', '', '', null, '', '', '24');

-- ----------------------------
-- Table structure for `menu`
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `icon` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `showOrder` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `parentId` int(11) DEFAULT NULL,
  `domId` varchar(255) DEFAULT NULL,
  `openNewPage` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK24897F8709A14F` (`parentId`),
  CONSTRAINT `menu_ibfk_1` FOREIGN KEY (`parentId`) REFERENCES `menu` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', 'icon-cogs', '渠道管理', '0', '1', null, null, 'menu-scb', '0', null);
INSERT INTO `menu` VALUES ('2', 'icon-cogs', '业务部', '1', '1', null, null, 'menu-ywb', '0', null);
INSERT INTO `menu` VALUES ('3', 'icon-cogs', '行政部', '3', '1', null, null, 'menu-xzb', '0', null);
INSERT INTO `menu` VALUES ('4', 'icon-cogs', '系统设置', '4', '1', null, null, 'menu-sysConfig', '0', null);
INSERT INTO `menu` VALUES ('5', 'icon-plus', '录入客户信息', '0', '1', 'customer/addCustomerUI.do', '1', 'menu-addCustomer', '0', null);
INSERT INTO `menu` VALUES ('6', 'icon-plus', '渠道管理', '1', '1', 'customerSource/getCustomerSources.do', '1', 'menu-customerSources', '0', null);
INSERT INTO `menu` VALUES ('7', 'icon-plus', '分配客户给业务部', '2', '1', 'customer/getAllotCustomerList.do', '1', 'menu-allotCustomerList', '0', null);
INSERT INTO `menu` VALUES ('8', 'icon-plus', '部门新订单', '0', '1', 'customer/getAllotToEmployeeList.do', '2', 'menu-allotToEmployeeList', '0', null);
INSERT INTO `menu` VALUES ('9', 'icon-plus', '我的新订单', '1', '1', 'customer/getNewCustomerList.do', '2', 'menu-newCustomerList', '0', null);
INSERT INTO `menu` VALUES ('10', 'icon-plus', '有效客户列表', '2', '1', 'customer/getValidCustomerList.do', '2', 'menu-validCustomerList', '0', null);
INSERT INTO `menu` VALUES ('11', 'icon-plus', '签单客户列表', '3', '1', 'signCustomer/getSignCustomerList.do', '2', 'menu-signCustomerList', '0', null);
INSERT INTO `menu` VALUES ('12', 'icon-plus', '放款客户列表', '4', '1', 'outLoanCustomer/getOutLoanCustomerList.do', '2', 'menu-outLoanCustomerList', '0', null);
INSERT INTO `menu` VALUES ('13', 'icon-plus', '退单退件客户列表', '5', '1', 'rejectCustomer/getRejectCustomerList.do', '2', 'menu-rejectCustomerList', '0', null);
INSERT INTO `menu` VALUES ('14', 'icon-plus', '工作提醒', '6', '1', 'customerFollow/getReminderList.do', '2', 'menu-reminderList', '0', null);
INSERT INTO `menu` VALUES ('15', 'icon-plus', '客户移交', '7', '1', 'customer/turnOverList.do', '2', 'menu-turnOverList', '0', null);
INSERT INTO `menu` VALUES ('16', 'icon-plus', '已分配订单', '8', '1', 'customer/getAssignedOrderList.do', '2', 'menu-assignedOrderList', '0', null);
INSERT INTO `menu` VALUES ('17', 'icon-plus', '已领单列表', '9', '1', 'customer/getReceiveOrderList.do', '2', 'menu-receiveOrderList', '0', null);
INSERT INTO `menu` VALUES ('18', 'icon-plus', '业务反馈报表', '10', '1', 'report/report.do?defaultShow=week', '2', 'menu-report', '0', null);
INSERT INTO `menu` VALUES ('19', 'icon-plus', '公共池', '11', '1', 'customer/getCommonPoolList.do', '2', 'menu-commonList', '0', null);
INSERT INTO `menu` VALUES ('20', 'icon-plus', '签单客户维护', '0', '1', 'signCustomer/signCustomerMaintainList.do', '3', 'menu-customerMaintainList', '0', null);
INSERT INTO `menu` VALUES ('21', 'icon-plus', '无效客户管理', '1', '1', 'customer/getNullityList.do', '3', 'menu-nullityList', '0', null);
INSERT INTO `menu` VALUES ('22', 'icon-plus', '公司公告', '2', '1', 'message/getList.do?type=0', '3', 'menu-list01', '0', null);
INSERT INTO `menu` VALUES ('23', 'icon-plus', '销售制度', '3', '1', 'message/getList.do?type=1', '3', 'menu-list02', '0', null);
INSERT INTO `menu` VALUES ('24', 'icon-plus', '销售说辞', '4', '1', 'message/getList.do?type=2', '3', 'menu-list03', '0', null);
INSERT INTO `menu` VALUES ('25', 'icon-plus', '银行产品', '5', '1', 'message/getList.do?type=3', '3', 'menu-list04', '0', null);
INSERT INTO `menu` VALUES ('26', 'icon-plus', '日程安排', '6', '1', 'workday/gotoWorkdayUI.do', '3', 'menu-gotoworkday', '0', null);
INSERT INTO `menu` VALUES ('27', 'icon-plus', '贷款机构管理', '7', '1', 'lendingInstitution/getList.do', '3', 'menu-list05', '0', null);
INSERT INTO `menu` VALUES ('28', 'icon-plus', '员工信息管理', '8', '1', 'employee/getEmployees.do', '3', 'menu-employees', '0', null);
INSERT INTO `menu` VALUES ('29', 'icon-plus', '角色管理', '0', '1', 'role/roleList.do', '4', 'menu-roleList', '0', null);
INSERT INTO `menu` VALUES ('30', 'icon-plus', '部门管理', '1', '1', 'department/departmentList.do', '4', 'menu-departmentList', '0', null);
INSERT INTO `menu` VALUES ('31', 'icon-plus', '修改密码', '2', '1', 'page/employee/updatePassword.jsp', '4', 'menu-updatePassword', '0', null);
INSERT INTO `menu` VALUES ('32', 'icon-upload-alt', '批量导入客户', '3', '1', 'customer/importCustomerUI.do?operationType=1', '1', 'menu-importCustomer', '0', null);
INSERT INTO `menu` VALUES ('33', 'icon-plus', '添加客户', '-1', '1', 'customer/addCustomerOfSalesmanUI.do', '2', 'menu-addcustomerorfsalesman', '0', null);
INSERT INTO `menu` VALUES ('34', 'icon-plus', '审核无效客户', '12', '1', 'customer/getApplyNullityList.do', '2', 'menu-apployNullityList', '0', null);
INSERT INTO `menu` VALUES ('35', 'icon-plus', '银行产品列表', '12', '1', 'message/geBankProductList.do', '2', 'menu-bankproduct-detail', '1', null);
INSERT INTO `menu` VALUES ('36', 'icon-plus', '批量释放客户', '99', '1', 'releaseCommonPool/batchReleaseView.do', '2', 'menu-batchRelease', '0', null);
INSERT INTO `menu` VALUES ('37', 'icon-plus', '批量导入客户到公共池', '4', '1', 'customer/importCustomerUI.do?operationType=2', '1', 'menu-importCustomer-release', '0', null);
INSERT INTO `menu` VALUES ('38', 'icon-plus', '公司管理', '100', '1', 'company/getCompanys.do', '3', null, '0', '1');
INSERT INTO `menu` VALUES ('40', 'icon-plus', '渠道来源统计', '5', '1', 'page/customerSource/customerSourcePie.jsp', '1', null, '0', null);
INSERT INTO `menu` VALUES ('41', 'icon-plus', '系统参数配置', '10', '1', 'config/getConfigInfo.do', '4', null, '0', null);
INSERT INTO `menu` VALUES ('42', 'icon-cogs', '权证部', '2', '1', null, null, null, '0', null);
INSERT INTO `menu` VALUES ('43', 'icon-plus', '中心新订单', '0', '1', 'center/orderList.do', '42', null, '0', null);
INSERT INTO `menu` VALUES ('44', 'icon-plus', '部门新订单', '1', '1', 'warrantdepartment/orderList.do', '42', null, '0', null);
INSERT INTO `menu` VALUES ('45', 'icon-plus', '有效客户列表', '2', '1', 'warranter/orderList.do', '42', null, '0', null);
INSERT INTO `menu` VALUES ('46', 'icon-plus', '放款客户列表', '3', '1', 'warranter/getOutLoanList.do', '42', null, '0', null);
INSERT INTO `menu` VALUES ('47', 'icon-plus', '退单退件客户列表', '4', '1', 'warrant/getRejectList.do', '42', null, '0', null);
INSERT INTO `menu` VALUES ('48', 'icon-plus', '工作提醒', '5', '1', 'warrant/getReminderList.do', '42', null, '0', null);
INSERT INTO `menu` VALUES ('49', 'icon-plus', '客户移交', '6', '1', 'warrant/warranterTurnOverList.do', '42', null, '0', null);
INSERT INTO `menu` VALUES ('50', 'icon-plus', '人事管理', '7', '1', null, null, 'menu-scb', '0', null);
INSERT INTO `menu` VALUES ('51', 'icon-plus', '录入应聘人员信息', '7', '1', 'applyPerson/addPersonInfo.do', '50', 'menu-addperson', '0', null);
INSERT INTO `menu` VALUES ('52', 'icon-plus', '应聘人员信息列表', '7', '1', 'applyPerson/getValidPersonList.do', '50', 'menu-person', '0', null);
INSERT INTO `menu` VALUES ('53', 'icon-plus', '项目管理', '7', '1', null, null, 'menu-scb', '0', null);
INSERT INTO `menu` VALUES ('54', 'icon-plus', '项目信息的录入', '7', '1', 'contractManager/showContractManager.do', '53', 'menu-addinfo', '0', null);
INSERT INTO `menu` VALUES ('55', 'icon-plus', '项目信息列表', '7', '1', 'contractManager/getValidContractManager.do', '53', 'menu-contract', '0', null);

-- ----------------------------
-- Table structure for `message`
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `companyId` int(11) DEFAULT NULL,
  `content` longtext,
  `hits` int(11) DEFAULT NULL,
  `pubdate` datetime DEFAULT NULL,
  `scopeType` int(11) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `publisherId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9C2397E79E80B1F0` (`publisherId`),
  CONSTRAINT `FK9C2397E79E80B1F0` FOREIGN KEY (`publisherId`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES ('1', null, null, null, '2017-06-16 18:28:34', null, '', null, '1');
INSERT INTO `message` VALUES ('2', null, null, null, '2017-06-19 17:14:38', null, '大家晚上一起下班', null, '1');
INSERT INTO `message` VALUES ('3', null, null, null, '2017-06-19 17:18:16', null, '大家晚上一起下班', null, '1');
INSERT INTO `message` VALUES ('4', null, null, null, '2017-07-03 09:12:58', null, '如何找回自信？', null, '1');

-- ----------------------------
-- Table structure for `notification`
-- ----------------------------
DROP TABLE IF EXISTS `notification`;
CREATE TABLE `notification` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `customerId` int(11) DEFAULT NULL,
  `operationType` int(11) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `receiverId` int(11) DEFAULT NULL,
  `sendTime` datetime DEFAULT NULL,
  `senderId` int(11) DEFAULT NULL,
  `signCustomerId` int(11) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `typeName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of notification
-- ----------------------------

-- ----------------------------
-- Table structure for `otherinfo`
-- ----------------------------
DROP TABLE IF EXISTS `otherinfo`;
CREATE TABLE `otherinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of otherinfo
-- ----------------------------

-- ----------------------------
-- Table structure for `outloanbelong`
-- ----------------------------
DROP TABLE IF EXISTS `outloanbelong`;
CREATE TABLE `outloanbelong` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `companyId` int(11) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `departmentId` int(11) DEFAULT NULL,
  `outLoanState` int(11) DEFAULT NULL,
  `signcustomerId` int(11) DEFAULT NULL,
  `turnDetail` varchar(255) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `warranterId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of outloanbelong
-- ----------------------------

-- ----------------------------
-- Table structure for `outloancustomer`
-- ----------------------------
DROP TABLE IF EXISTS `outloancustomer`;
CREATE TABLE `outloancustomer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `aloneNotarizationCharge` double DEFAULT NULL,
  `approveTime` datetime DEFAULT NULL,
  `assessCompany` varchar(255) DEFAULT NULL,
  `assessCost` double DEFAULT NULL,
  `assessMoney` double DEFAULT NULL,
  `certificateString` varchar(5000) DEFAULT NULL,
  `channelCost` double DEFAULT NULL,
  `checkCost` double DEFAULT NULL,
  `collateralString` varchar(5000) DEFAULT NULL,
  `companyId` int(11) DEFAULT NULL,
  `contractNotarizationCharge` double DEFAULT NULL,
  `cost` double DEFAULT NULL,
  `createDate` date DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `creditCost` double DEFAULT NULL,
  `electricCost` double DEFAULT NULL,
  `entrustNotarizationCharge` double DEFAULT NULL,
  `handlePeriod` varchar(255) DEFAULT NULL,
  `handleTime` date DEFAULT NULL,
  `hasCertificate` varchar(255) DEFAULT NULL,
  `hasFullDelegate` varchar(255) DEFAULT NULL,
  `hasLoaning` varchar(255) DEFAULT NULL,
  `houseAge` double DEFAULT NULL,
  `insuranceCost` double DEFAULT NULL,
  `loanAmount` double DEFAULT NULL,
  `loanType` varchar(255) DEFAULT NULL,
  `loaningAmount` varchar(255) DEFAULT NULL,
  `loaningCycle` varchar(255) DEFAULT NULL,
  `loaningDate` datetime DEFAULT NULL,
  `loaningRate` varchar(255) DEFAULT NULL,
  `loaningRisk` varchar(255) DEFAULT NULL,
  `loaningType` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `netEarnings` double DEFAULT NULL,
  `onlyHandle` double DEFAULT NULL,
  `otherCost` double DEFAULT NULL,
  `outLoanNum` varchar(255) DEFAULT NULL,
  `paymentAmount` double DEFAULT NULL,
  `paymenttime` date DEFAULT NULL,
  `phoneCard` double DEFAULT NULL,
  `pledgeCost` double DEFAULT NULL,
  `postage` double DEFAULT NULL,
  `purpose` varchar(255) DEFAULT NULL,
  `realPrice` double DEFAULT NULL,
  `rebate` double DEFAULT NULL,
  `receivedDeposit` double DEFAULT NULL,
  `remark` longtext,
  `repaymentType` varchar(255) DEFAULT NULL,
  `serveRating` varchar(255) DEFAULT NULL,
  `telephoneCost` double DEFAULT NULL,
  `visitCharge` double DEFAULT NULL,
  `warrantId` int(11) DEFAULT NULL,
  `warrantName` varchar(255) DEFAULT NULL,
  `workProve` double DEFAULT NULL,
  `signCustomerId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4D8FEF3CD872755C` (`signCustomerId`),
  CONSTRAINT `FK4D8FEF3CD872755C` FOREIGN KEY (`signCustomerId`) REFERENCES `signcustomer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of outloancustomer
-- ----------------------------

-- ----------------------------
-- Table structure for `outloanproduct`
-- ----------------------------
DROP TABLE IF EXISTS `outloanproduct`;
CREATE TABLE `outloanproduct` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `apr` varchar(255) DEFAULT NULL,
  `bankProduct` varchar(255) DEFAULT NULL,
  `lendingInstitution` int(11) DEFAULT NULL,
  `loanType` varchar(255) DEFAULT NULL,
  `loanYear` varchar(255) DEFAULT NULL,
  `rateType` varchar(255) DEFAULT NULL,
  `outLoanCustomerId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7304F9D1544EB08` (`outLoanCustomerId`),
  CONSTRAINT `FK7304F9D1544EB08` FOREIGN KEY (`outLoanCustomerId`) REFERENCES `outloancustomer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of outloanproduct
-- ----------------------------

-- ----------------------------
-- Table structure for `phonereplaytemp`
-- ----------------------------
DROP TABLE IF EXISTS `phonereplaytemp`;
CREATE TABLE `phonereplaytemp` (
  `id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of phonereplaytemp
-- ----------------------------
INSERT INTO `phonereplaytemp` VALUES ('7492');
INSERT INTO `phonereplaytemp` VALUES ('7806');
INSERT INTO `phonereplaytemp` VALUES ('6785');

-- ----------------------------
-- Table structure for `plan`
-- ----------------------------
DROP TABLE IF EXISTS `plan`;
CREATE TABLE `plan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `companyId` int(11) DEFAULT NULL,
  `createDate` date DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `performance` double DEFAULT NULL,
  `sign` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `visit` int(11) DEFAULT NULL,
  `departmentId` int(11) DEFAULT NULL,
  `employeeId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK25FF495B1393AA` (`departmentId`),
  KEY `FK25FF49CD1F9222` (`employeeId`),
  CONSTRAINT `FK25FF495B1393AA` FOREIGN KEY (`departmentId`) REFERENCES `department` (`id`),
  CONSTRAINT `FK25FF49CD1F9222` FOREIGN KEY (`employeeId`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of plan
-- ----------------------------

-- ----------------------------
-- Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `menus` varchar(255) DEFAULT NULL,
  `readOnly` int(11) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `roleName` varchar(255) DEFAULT NULL,
  `companyId` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `customerNO` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1001 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '1-6-7-32-37-2-33-8-10-11-13-14-15-16-18-19-36-3-20-22-23-24-28-4-31-41-', '1', '', '销售经理', null, null, '2000');
INSERT INTO `role` VALUES ('2', '1-5-6-7-32-37-2-33-8-9-10-11-12-13-14-15-16-17-18-19-34-35-36-3-20-21-22-23-24-25-26-27-28-4-29-30-31-', '1', '', '总经理', null, null, '0');
INSERT INTO `role` VALUES ('7', '2-33-8-9-10-11-13-14-15-18-19-36-3-21-28-4-29-30-31-', '1', '', '销售主管', null, null, '500');
INSERT INTO `role` VALUES ('8', '2-33-9-10-11-12-13-14-17-18-19-35-4-31-', '1', '', '特级客户经理', null, null, '400');
INSERT INTO `role` VALUES ('9', '2-33-9-10-11-12-13-14-17-18-19-35-4-31-', '1', '', '高级客户经理', null, null, '300');
INSERT INTO `role` VALUES ('10', '2-33-9-10-11-12-13-14-17-18-19-35-4-31-', '1', '', '中级客户经理', null, null, '200');
INSERT INTO `role` VALUES ('11', '2-33-21-9-10-11-13-14-18-19-4-31-', '1', '', '初级客户经理', null, null, '2000');
INSERT INTO `role` VALUES ('12', '50-51-52', '1', '', '试用期客户经理', null, null, '50');
INSERT INTO `role` VALUES ('13', '1-6-7-32-37-2-33-8-10-11-13-14-15-16-18-19-36-3-20-22-23-24-28-4-31-', '1', null, '销售经理', null, null, '5000');
INSERT INTO `role` VALUES ('14', '50-51-52', '1', null, '人资主管', null, null, '0');
INSERT INTO `role` VALUES ('999', '0', '1', null, '超级角色', null, '1', '0');
INSERT INTO `role` VALUES ('1000', '53-54-55-', '1', '', '项目管理人员', null, null, '2000');

-- ----------------------------
-- Table structure for `signcontacts`
-- ----------------------------
DROP TABLE IF EXISTS `signcontacts`;
CREATE TABLE `signcontacts` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `age` int(11) DEFAULT NULL,
  `idCardNo` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `signCustomerId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7161E950D872755C` (`signCustomerId`),
  CONSTRAINT `FK7161E950D872755C` FOREIGN KEY (`signCustomerId`) REFERENCES `signcustomer` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of signcontacts
-- ----------------------------
INSERT INTO `signcontacts` VALUES ('1', null, '', '', '', '1');
INSERT INTO `signcontacts` VALUES ('2', '11', '510402199111023412', '小鱼', '没啥好说的', '2');
INSERT INTO `signcontacts` VALUES ('3', null, '', '', '', '3');
INSERT INTO `signcontacts` VALUES ('4', null, '', '', '', '4');
INSERT INTO `signcontacts` VALUES ('5', '32', '510110101010101010', '吕总', '已签单', '5');
INSERT INTO `signcontacts` VALUES ('6', null, '', '', '', '6');
INSERT INTO `signcontacts` VALUES ('7', null, '', '', '', '7');
INSERT INTO `signcontacts` VALUES ('8', null, '', '', '', '8');
INSERT INTO `signcontacts` VALUES ('16', null, '', '', '', '16');
INSERT INTO `signcontacts` VALUES ('18', null, '', '', '', '18');
INSERT INTO `signcontacts` VALUES ('20', null, '', '', '', '20');
INSERT INTO `signcontacts` VALUES ('24', null, '', '', '', '24');

-- ----------------------------
-- Table structure for `signcustomer`
-- ----------------------------
DROP TABLE IF EXISTS `signcustomer`;
CREATE TABLE `signcustomer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `certificateString` varchar(5000) DEFAULT NULL,
  `collateralString` varchar(5000) DEFAULT NULL,
  `companyId` int(11) DEFAULT NULL,
  `contractNO` varchar(255) DEFAULT NULL,
  `costOfContract` double DEFAULT NULL,
  `customerStatus` int(11) DEFAULT NULL,
  `handsel` double DEFAULT NULL,
  `hasLoanWish` varchar(255) DEFAULT NULL,
  `hasLoaning` varchar(255) DEFAULT NULL,
  `intermediaryName` varchar(255) DEFAULT NULL,
  `intermediaryPhone` varchar(255) DEFAULT NULL,
  `intermediaryRebate` varchar(255) DEFAULT NULL,
  `isNewOrder` int(11) DEFAULT NULL,
  `lastFailureMessageCause` varchar(255) DEFAULT NULL,
  `lastFailureMessageRemark` varchar(255) DEFAULT NULL,
  `lastFailureMessageTime` date DEFAULT NULL,
  `lastFailureMessageType` int(11) DEFAULT NULL,
  `listType` int(11) DEFAULT NULL,
  `loanAmount` double DEFAULT NULL,
  `loanType` varchar(255) DEFAULT NULL,
  `loaningAmount` varchar(255) DEFAULT NULL,
  `loaningCycle` varchar(255) DEFAULT NULL,
  `loaningDate` datetime DEFAULT NULL,
  `loaningRate` varchar(255) DEFAULT NULL,
  `loaningRisk` varchar(255) DEFAULT NULL,
  `loaningType` varchar(255) DEFAULT NULL,
  `negotiatorId` int(11) DEFAULT NULL,
  `orderType` int(11) DEFAULT NULL,
  `outLoanCondition` varchar(255) DEFAULT NULL,
  `planNextLoanTime` date DEFAULT NULL,
  `receivedCertificate` varchar(255) DEFAULT NULL,
  `remark` longtext,
  `repaymentType` varchar(255) DEFAULT NULL,
  `serveRating` varchar(255) DEFAULT NULL,
  `signDate` date DEFAULT NULL,
  `signTime` datetime DEFAULT NULL,
  `turnOverWarrantTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `warrantCompanyId` int(11) DEFAULT NULL,
  `warrantDepartmentId` int(11) DEFAULT NULL,
  `warrantEmployeeId` int(11) DEFAULT NULL,
  `customerId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKB75608DB41DB3882` (`customerId`),
  CONSTRAINT `FKB75608DB41DB3882` FOREIGN KEY (`customerId`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of signcustomer
-- ----------------------------
INSERT INTO `signcustomer` VALUES ('1', null, null, null, 'SC000001', null, '0', null, '', '', null, null, null, null, null, null, null, null, null, null, '', null, null, null, null, null, null, null, null, '', null, '', '', '', 'A', '2017-06-15', '2017-06-15 15:01:46', null, '2017-06-15 15:01:46', null, null, null, '275278');
INSERT INTO `signcustomer` VALUES ('2', null, null, null, 'SC000002', '123456', '0', '222222', '', '', null, null, null, null, null, null, null, null, null, null, '', null, null, null, null, null, null, '3057', null, '', null, '身份证', '阿萨德群多哇多', '', 'D', '2017-06-15', '2017-06-15 15:02:53', null, '2017-06-15 22:22:26', null, null, null, '275278');
INSERT INTO `signcustomer` VALUES ('3', null, null, null, 'SC000003', null, '0', null, '', '', null, null, null, null, null, null, null, null, null, null, '', null, null, null, null, null, null, '3057', null, '', null, '', '', '', 'A', '2017-06-16', '2017-06-16 18:24:50', null, '2017-06-16 18:24:50', null, null, null, '275277');
INSERT INTO `signcustomer` VALUES ('4', null, null, null, 'SC000004', null, '0', null, '', '', null, null, null, null, null, null, null, null, null, null, '', null, null, null, null, null, null, null, null, '', null, '', '', '', 'D', '2017-06-17', '2017-06-17 11:13:35', null, '2017-06-17 11:13:35', null, null, null, '275287');
INSERT INTO `signcustomer` VALUES ('5', null, null, null, 'SC000005', '200000', '0', '10000', '', '', null, null, null, null, null, null, null, null, null, null, '', null, null, null, null, null, null, '3057', null, '', null, '身份证', '阿萨德群翁', '', 'B', '2017-06-17', '2017-06-17 12:24:54', null, '2017-06-17 12:24:54', null, null, null, '275285');
INSERT INTO `signcustomer` VALUES ('6', null, null, null, 'SC000006', null, '0', null, '', '', null, null, null, null, null, null, null, null, null, null, '', null, null, null, null, null, null, null, null, '', null, '', '', '', null, '2017-06-30', '2017-06-30 19:09:09', null, '2017-07-02 23:56:06', null, null, null, '275276');
INSERT INTO `signcustomer` VALUES ('7', null, null, null, 'JS20071773A', null, '0', null, '', '', null, null, null, null, null, null, null, null, null, null, '', null, null, null, null, null, null, null, null, '', null, '', '', '', null, '2017-07-03', '2017-07-03 15:29:06', null, '2017-07-03 23:14:02', null, null, null, '275290');
INSERT INTO `signcustomer` VALUES ('8', null, null, null, 'JS20170703A', null, '0', null, '', '', null, null, null, null, null, null, null, null, null, null, '', null, null, null, null, null, null, null, null, '', null, '', '', '', null, '2017-07-03', '2017-07-03 15:30:53', null, '2017-07-03 15:30:53', null, null, null, '275289');
INSERT INTO `signcustomer` VALUES ('16', null, null, null, 'JS20170703B', null, '1', null, '', '', null, null, null, null, '客户不做了', null, '2017-06-28', '1', null, null, '', null, null, null, null, null, null, null, null, '', null, '', '', '', null, '2017-07-03', '2017-07-03 16:49:30', null, '2017-07-08 15:48:30', null, null, null, '275274');
INSERT INTO `signcustomer` VALUES ('18', null, null, null, 'JS20170701A', null, '0', null, '', '', null, null, null, null, null, null, null, null, null, null, '', null, null, null, null, null, null, null, null, '', null, '', '', '', null, '2017-07-03', '2017-07-03 16:58:59', null, '2017-07-03 16:58:59', null, null, null, '275273');
INSERT INTO `signcustomer` VALUES ('20', null, null, null, 'JS20170701B', null, '0', null, '', '', null, null, null, null, null, null, null, null, null, null, '', null, null, null, null, null, null, null, null, '', null, '', '', '', null, '2017-07-03', '2017-07-03 17:04:49', null, '2017-07-03 17:04:49', null, null, null, '275272');
INSERT INTO `signcustomer` VALUES ('24', null, null, null, 'JS20170703C', null, '0', null, '', '', null, null, null, null, null, null, null, null, null, null, '', null, null, null, null, null, null, null, null, '', null, '', '', '', null, '2017-07-03', '2017-07-03 17:20:35', null, '2017-07-03 17:20:35', null, null, null, '275286');

-- ----------------------------
-- Table structure for `signcustomerturn`
-- ----------------------------
DROP TABLE IF EXISTS `signcustomerturn`;
CREATE TABLE `signcustomerturn` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cause` varchar(255) DEFAULT NULL,
  `companyId` int(11) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `otherCause` varchar(255) DEFAULT NULL,
  `nowEmpId` int(11) DEFAULT NULL,
  `oldEmpId` int(11) DEFAULT NULL,
  `signCustomerId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKC1C70B982683C0A6` (`nowEmpId`),
  KEY `FKC1C70B98CF4627F5` (`oldEmpId`),
  KEY `FKC1C70B98D872755C` (`signCustomerId`),
  CONSTRAINT `FKC1C70B982683C0A6` FOREIGN KEY (`nowEmpId`) REFERENCES `employee` (`id`),
  CONSTRAINT `FKC1C70B98CF4627F5` FOREIGN KEY (`oldEmpId`) REFERENCES `employee` (`id`),
  CONSTRAINT `FKC1C70B98D872755C` FOREIGN KEY (`signCustomerId`) REFERENCES `signcustomer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of signcustomerturn
-- ----------------------------

-- ----------------------------
-- Table structure for `tempcustomer`
-- ----------------------------
DROP TABLE IF EXISTS `tempcustomer`;
CREATE TABLE `tempcustomer` (
  `id` int(11) NOT NULL DEFAULT '0',
  `addPersonId` int(11) DEFAULT NULL,
  `addPersonName` varchar(255) DEFAULT NULL,
  `addType` int(11) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `attentionLevel` varchar(255) DEFAULT NULL,
  `bondExpireDate` date DEFAULT NULL,
  `callCount` int(11) DEFAULT NULL,
  `census` varchar(255) DEFAULT NULL,
  `countdown` int(11) DEFAULT NULL,
  `createDate` date DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `credit` varchar(255) DEFAULT NULL,
  `customerAsset` longtext,
  `customerAssetTitile` varchar(500) DEFAULT NULL,
  `customerCharacter` varchar(255) DEFAULT NULL,
  `customerLevel` varchar(255) DEFAULT NULL,
  `dataPercent` double DEFAULT NULL,
  `debtTotal` double DEFAULT NULL,
  `embodiment` varchar(255) DEFAULT NULL,
  `enterpriseNature` varchar(255) DEFAULT NULL,
  `fllowDate` datetime DEFAULT NULL,
  `fundUse` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `isFollow` int(11) DEFAULT NULL,
  `lendingInstitution` varchar(255) DEFAULT NULL,
  `loanType` varchar(255) DEFAULT NULL,
  `marriage` varchar(255) DEFAULT NULL,
  `monthIncome` varchar(255) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `otherInfo` longtext,
  `phone` varchar(100) DEFAULT NULL,
  `phoneRemark` varchar(255) DEFAULT NULL,
  `publicView` int(11) DEFAULT NULL,
  `referrerId` int(11) DEFAULT NULL,
  `repaymentLimit` varchar(255) DEFAULT NULL,
  `repaymentType` varchar(255) DEFAULT NULL,
  `requiredMoney` varchar(255) DEFAULT NULL,
  `signState` varchar(255) DEFAULT NULL,
  `socialInsurance` varchar(255) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `turnDetail` varchar(255) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `useDate` date DEFAULT NULL,
  `visibility` int(11) DEFAULT NULL,
  `visitCount` int(11) DEFAULT NULL,
  `workYear` varchar(255) DEFAULT NULL,
  `customerRosterId` int(11) DEFAULT NULL,
  `customerSourceId` int(11) DEFAULT NULL,
  `departmentId` int(11) DEFAULT NULL,
  `employeeId` int(11) DEFAULT NULL,
  `receiveDate` date DEFAULT NULL,
  `releaseDate` date DEFAULT NULL,
  `releaseId` int(11) DEFAULT NULL,
  `releaseTime` datetime DEFAULT NULL,
  `releaseType` int(11) DEFAULT NULL,
  `companyId` int(11) DEFAULT NULL,
  `companyName` varchar(255) DEFAULT NULL,
  `lastFollowId` int(11) DEFAULT NULL,
  `lastFollowType` int(11) DEFAULT NULL,
  `lastRemindContent` varchar(255) DEFAULT NULL,
  `lastRemindTime` date DEFAULT NULL,
  `lastWarrantFollowId` int(11) DEFAULT NULL,
  `lastWarrantFollowType` int(11) DEFAULT NULL,
  `lastWarrantRemindContent` varchar(255) DEFAULT NULL,
  `lastWarrantRemindTime` date DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tempcustomer
-- ----------------------------
INSERT INTO `tempcustomer` VALUES ('1', '2', '张诗伟', '0', '1', 'B', null, '18', '川内', null, '2015-04-16', '2015-04-16 09:15:01', '两年内少数逾期', '住房:成华区(区) 建设路(街道) 万科金域蓝湾(楼盘) 出让(国土性质) 2009(建筑年代) 124(面积) 12000(均价) <br/><br/>', '住房(1) ', '', '中', null, null, '打卡', '公务员', '2016-07-13 14:50:59', '', '男', null, '小贷', '二抵', '', '3000-5000', '张大彪', '  按揭   （', '13980688546', '', '1', null, '3', '', '250000', '未签单', '公司购买一年内', '9', '由【业务二部-黄贞】移交到【业务二部-黄博】2015-12-17', '2016-07-13 14:50:59', null, '1', '0', '一年以内', null, '11', '3', '2011', '2016-07-13', '2016-09-12', '0', '2016-09-12 00:00:00', '1', '1', null, '522701', '1', '', null, null, null, null, null);

-- ----------------------------
-- Table structure for `tempmenu`
-- ----------------------------
DROP TABLE IF EXISTS `tempmenu`;
CREATE TABLE `tempmenu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `icon` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `showOrder` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `parentId` int(11) DEFAULT NULL,
  `domId` varchar(255) DEFAULT NULL,
  `openNewPage` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=56 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tempmenu
-- ----------------------------
INSERT INTO `tempmenu` VALUES ('1', 'icon-cogs', '市场部', '0', '1', null, null, 'menu-scb', '0', null);
INSERT INTO `tempmenu` VALUES ('2', 'icon-cogs', '业务部', '1', '1', null, null, 'menu-ywb', '0', null);
INSERT INTO `tempmenu` VALUES ('3', 'icon-cogs', '行政部', '2', '1', null, null, 'menu-xzb', '0', null);
INSERT INTO `tempmenu` VALUES ('4', 'icon-cogs', '系统设置', '3', '1', null, null, 'menu-sysConfig', '0', null);
INSERT INTO `tempmenu` VALUES ('5', 'icon-plus', '录入客户信息', '0', '1', 'customer/addCustomerUI.do', '1', 'menu-addCustomer', '0', null);
INSERT INTO `tempmenu` VALUES ('6', 'icon-plus', '渠道管理', '1', '1', 'customerSource/getCustomerSources.do', '1', 'menu-customerSources', '0', null);
INSERT INTO `tempmenu` VALUES ('7', 'icon-plus', '分配客户给业务部', '2', '1', 'customer/getAllotCustomerList.do', '1', 'menu-allotCustomerList', '0', null);
INSERT INTO `tempmenu` VALUES ('8', 'icon-plus', '部门新订单', '0', '1', 'customer/getAllotToEmployeeList.do', '2', 'menu-allotToEmployeeList', '0', null);
INSERT INTO `tempmenu` VALUES ('9', 'icon-plus', '我的新订单', '1', '1', 'customer/getNewCustomerList.do', '2', 'menu-newCustomerList', '0', null);
INSERT INTO `tempmenu` VALUES ('10', 'icon-plus', '有效客户列表', '2', '1', 'customer/getValidCustomerList.do', '2', 'menu-validCustomerList', '0', null);
INSERT INTO `tempmenu` VALUES ('11', 'icon-plus', '签单客户列表', '3', '1', 'signCustomer/getSignCustomerList.do', '2', 'menu-signCustomerList', '0', null);
INSERT INTO `tempmenu` VALUES ('12', 'icon-plus', '放款客户列表', '4', '1', 'outLoanCustomer/getOutLoanCustomerList.do', '2', 'menu-outLoanCustomerList', '0', null);
INSERT INTO `tempmenu` VALUES ('13', 'icon-plus', '退单退件客户列表', '5', '1', 'rejectCustomer/getRejectCustomerList.do', '2', 'menu-rejectCustomerList', '0', null);
INSERT INTO `tempmenu` VALUES ('14', 'icon-plus', '工作提醒', '6', '1', 'customerFollow/getReminderList.do', '2', 'menu-reminderList', '0', null);
INSERT INTO `tempmenu` VALUES ('15', 'icon-plus', '客户移交', '7', '1', 'customer/turnOverList.do', '2', 'menu-turnOverList', '0', null);
INSERT INTO `tempmenu` VALUES ('16', 'icon-plus', '已分配订单', '8', '1', 'customer/getAssignedOrderList.do', '2', 'menu-assignedOrderList', '0', null);
INSERT INTO `tempmenu` VALUES ('17', 'icon-plus', '已领单列表', '9', '1', 'customer/getReceiveOrderList.do', '2', 'menu-receiveOrderList', '0', null);
INSERT INTO `tempmenu` VALUES ('18', 'icon-plus', '业务反馈报表', '10', '1', 'report/report.do?defaultShow=week', '2', 'menu-report', '0', null);
INSERT INTO `tempmenu` VALUES ('19', 'icon-plus', '公共池', '11', '1', 'customer/getCommonPoolList.do', '2', 'menu-commonList', '0', null);
INSERT INTO `tempmenu` VALUES ('20', 'icon-plus', '签单客户维护', '0', '1', 'signCustomer/signCustomerMaintainList.do', '3', 'menu-customerMaintainList', '0', null);
INSERT INTO `tempmenu` VALUES ('21', 'icon-plus', '无效客户管理', '1', '1', 'customer/getNullityList.do', '3', 'menu-nullityList', '0', null);
INSERT INTO `tempmenu` VALUES ('22', 'icon-plus', '公司公告', '2', '1', 'message/getList.do?type=0', '3', 'menu-list01', '0', null);
INSERT INTO `tempmenu` VALUES ('23', 'icon-plus', '公司制度', '3', '1', 'message/getList.do?type=1', '3', 'menu-list02', '0', null);
INSERT INTO `tempmenu` VALUES ('24', 'icon-plus', '销售说辞', '4', '1', 'message/getList.do?type=2', '3', 'menu-list03', '0', null);
INSERT INTO `tempmenu` VALUES ('25', 'icon-plus', '银行产品', '5', '1', 'message/getList.do?type=3', '3', 'menu-list04', '0', null);
INSERT INTO `tempmenu` VALUES ('26', 'icon-plus', '日程安排', '6', '1', 'workday/gotoWorkdayUI.do', '3', 'menu-gotoworkday', '0', null);
INSERT INTO `tempmenu` VALUES ('27', 'icon-plus', '贷款机构管理', '7', '1', 'lendingInstitution/getList.do', '3', 'menu-list05', '0', null);
INSERT INTO `tempmenu` VALUES ('28', 'icon-plus', '员工信息管理', '8', '1', 'employee/getEmployees.do', '3', 'menu-employees', '0', null);
INSERT INTO `tempmenu` VALUES ('29', 'icon-plus', '角色管理', '0', '1', 'role/roleList.do', '4', 'menu-roleList', '0', null);
INSERT INTO `tempmenu` VALUES ('30', 'icon-plus', '部门管理', '1', '1', 'department/departmentList.do', '4', 'menu-departmentList', '0', null);
INSERT INTO `tempmenu` VALUES ('31', 'icon-plus', '修改密码', '2', '1', 'page/employee/updatePassword.jsp', '4', 'menu-updatePassword', '0', null);
INSERT INTO `tempmenu` VALUES ('32', 'icon-upload-alt', '批量导入客户', '3', '1', 'customer/importCustomerUI.do?operationType=1', '1', 'menu-importCustomer', '0', null);
INSERT INTO `tempmenu` VALUES ('33', 'icon-plus', '添加客户', '-1', '1', 'customer/addCustomerOfSalesmanUI.do', '2', 'menu-addcustomerorfsalesman', '0', null);
INSERT INTO `tempmenu` VALUES ('34', 'icon-plus', '审核无效客户', '12', '1', 'customer/getApplyNullityList.do', '2', 'menu-apployNullityList', '0', null);
INSERT INTO `tempmenu` VALUES ('35', 'icon-plus', '银行产品列表', '12', '1', 'message/geBankProductList.do', '2', 'menu-bankproduct-detail', '1', null);
INSERT INTO `tempmenu` VALUES ('36', 'icon-plus', '批量释放客户', '99', '1', 'releaseCommonPool/batchReleaseView.do', '2', 'menu-batchRelease', '0', null);
INSERT INTO `tempmenu` VALUES ('37', 'icon-plus', '批量导入客户到公共池', '4', '1', 'customer/importCustomerUI.do?operationType=2', '1', 'menu-importCustomer-release', '0', null);
INSERT INTO `tempmenu` VALUES ('38', 'icon-plus', '公司管理', '100', '1', 'company/getCompanys.do', '3', null, '0', '1');
INSERT INTO `tempmenu` VALUES ('40', 'icon-plus', '渠道来源统计', '5', '1', 'page/customerSource/customerSourcePie.jsp', '1', null, '0', null);
INSERT INTO `tempmenu` VALUES ('41', 'icon-plus', '系统参数配置', '10', '1', 'config/getConfigInfo.do', '4', null, '0', null);
INSERT INTO `tempmenu` VALUES ('49', 'icon-cogs', '权证部', '4', '1', null, null, null, '0', null);
INSERT INTO `tempmenu` VALUES ('50', 'icon-plus', '中心新订单', '0', '1', 'center/orderList.do', '49', null, '0', null);
INSERT INTO `tempmenu` VALUES ('51', 'icon-plus', '部门新订单', '1', '1', 'warrantdepartment/orderList.do', '49', null, '0', null);
INSERT INTO `tempmenu` VALUES ('52', 'icon-plus', '有效客户列表', '2', '1', 'warranter/orderList.do', '49', null, '0', null);
INSERT INTO `tempmenu` VALUES ('53', 'icon-plus', '放款客户列表', '3', '1', 'warranter/getOutLoanList.do', '49', null, '0', null);
INSERT INTO `tempmenu` VALUES ('54', 'icon-plus', '退单退件客户列表', '4', '1', 'warrant/getRejectList.do', '49', null, '0', null);
INSERT INTO `tempmenu` VALUES ('55', 'icon-plus', '工作提醒', '5', '1', 'warrant/getReminderList.do', '49', null, '0', null);

-- ----------------------------
-- Table structure for `warrantallotrecord`
-- ----------------------------
DROP TABLE IF EXISTS `warrantallotrecord`;
CREATE TABLE `warrantallotrecord` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `allotTime` datetime DEFAULT NULL,
  `allotType` int(11) DEFAULT NULL,
  `companyId` int(11) DEFAULT NULL,
  `customerId` int(11) DEFAULT NULL,
  `departmentId` int(11) DEFAULT NULL,
  `employeeId` int(11) DEFAULT NULL,
  `operateType` int(11) DEFAULT NULL,
  `signCustomerId` int(11) DEFAULT NULL,
  `warrantCompanyId` int(11) DEFAULT NULL,
  `warrantDepartmentId` int(11) DEFAULT NULL,
  `warrantEmployeeId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of warrantallotrecord
-- ----------------------------

-- ----------------------------
-- Table structure for `workday`
-- ----------------------------
DROP TABLE IF EXISTS `workday`;
CREATE TABLE `workday` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `createTime` datetime DEFAULT NULL,
  `date` date DEFAULT NULL,
  `dateString` longtext,
  `day` int(11) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `employeeId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `date` (`date`),
  KEY `FKBE20AECBCD1F9222` (`employeeId`),
  CONSTRAINT `FKBE20AECBCD1F9222` FOREIGN KEY (`employeeId`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of workday
-- ----------------------------
