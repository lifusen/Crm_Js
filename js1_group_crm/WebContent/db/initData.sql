-- ----------------------------
-- 1.菜单
-- ----------------------------
INSERT INTO `menu` VALUES ('1', 'icon-cogs', '市场部', '0', '1', null, null);
INSERT INTO `menu` VALUES ('2', 'icon-cogs', '业务部', '1', '1', null, null);
INSERT INTO `menu` VALUES ('3', 'icon-cogs', '行政部', '2', '1', null, null);
INSERT INTO `menu` VALUES ('4', 'icon-cogs', '系统设置', '3', '1', null, null);
INSERT INTO `menu` VALUES ('5', 'icon-plus', '录入客户信息', '0', '1', 'customer/addCustomerUI.do', '1');
INSERT INTO `menu` VALUES ('6', 'icon-plus', '渠道管理', '1', '1', 'customerSource/getCustomerSources.do', '1');
INSERT INTO `menu` VALUES ('7', 'icon-plus', '分配客户给业务部', '2', '1', 'customer/getAllotCustomerList.do', '1');
INSERT INTO `menu` VALUES ('8', 'icon-plus', '部门新订单', '0', '1', 'customer/getAllotToEmployeeList.do', '2');
INSERT INTO `menu` VALUES ('9', 'icon-plus', '我的新订单', '1', '1', 'customer/getNewCustomerList.do', '2');
INSERT INTO `menu` VALUES ('10', 'icon-plus', '有效客户列表', '2', '1', 'customer/getValidCustomerList.do', '2');
INSERT INTO `menu` VALUES ('11', 'icon-plus', '签单客户列表', '3', '1', 'signCustomer/getSignCustomerList.do', '2');
INSERT INTO `menu` VALUES ('12', 'icon-plus', '放款客户列表', '4', '1', 'outLoanCustomer/getOutLoanCustomerList.do', '2');
INSERT INTO `menu` VALUES ('13', 'icon-plus', '退单退件客户列表', '5', '1', 'rejectCustomer/getRejectCustomerList.do', '2');
INSERT INTO `menu` VALUES ('14', 'icon-plus', '工作提醒', '6', '1', 'customerFollow/getReminderList.do', '2');
INSERT INTO `menu` VALUES ('15', 'icon-plus', '客户移交', '7', '1', 'customer/turnOverList.do', '2');
INSERT INTO `menu` VALUES ('16', 'icon-plus', '已分配订单', '8', '1', 'customer/getAssignedOrderList.do', '2');
INSERT INTO `menu` VALUES ('17', 'icon-plus', '已领单列表', '9', '1', 'customer/getReceiveOrderList.do', '2');
INSERT INTO `menu` VALUES ('18', 'icon-plus', '业务反馈报表', '10', '1', 'page/report/report.jsp', '2');
INSERT INTO `menu` VALUES ('19', 'icon-plus', '公共池', '11', '1', 'customer/getCommonPoolList.do', '2');
INSERT INTO `menu` VALUES ('20', 'icon-plus', '签单客户维护', '0', '1', 'signCustomer/signCustomerMaintainList.do', '3');
INSERT INTO `menu` VALUES ('21', 'icon-plus', '无效客户管理', '1', '1', 'report/report.do', '3');
INSERT INTO `menu` VALUES ('22', 'icon-plus', '公司公告', '2', '1', 'message/getList.do?type=0', '3');
INSERT INTO `menu` VALUES ('23', 'icon-plus', '公司制度', '3', '1', 'message/getList.do?type=1', '3');
INSERT INTO `menu` VALUES ('24', 'icon-plus', '销售说辞', '4', '1', 'message/getList.do?type=2', '3');
INSERT INTO `menu` VALUES ('25', 'icon-plus', '银行产品', '5', '1', 'bankProduct/getList.do', '3');
INSERT INTO `menu` VALUES ('26', 'icon-plus', '日程安排', '6', '1', 'page/administrative/schedule.jsp', '3');
INSERT INTO `menu` VALUES ('27', 'icon-plus', '贷款机构管理', '7', '1', 'lendingInstitution/getList.do', '3');
INSERT INTO `menu` VALUES ('28', 'icon-plus', '员工信息管理', '8', '1', 'employee/getEmployees.do', '3');
INSERT INTO `menu` VALUES ('29', 'icon-plus', '角色管理', '0', '1', 'role/roleList.do', '4');
INSERT INTO `menu` VALUES ('30', 'icon-plus', '部门管理', '1', '1', 'department/departmentList.do', '4');
INSERT INTO `menu` VALUES ('31', 'icon-plus', '修改密码', '2', '1', 'page/employee/updatePassword.jsp', '4');
INSERT INTO `menu` VALUES ('32', 'icon-upload-alt', '批量导入客户', '3', '1', 'page/customer/importCustomer.jsp', '1');
INSERT INTO `menu` VALUES ('33', 'icon-plus', '添加客户', '-1', '1', 'customer/addCustomerOfSalesmanUI.do', '2');
INSERT INTO `menu` VALUES ('34', 'icon-plus', '审核无效客户', '12', '1', 'customer/getApplyNullityList.do', '2');

-- ----------------------------
-- 2.角色
-- ----------------------------
INSERT INTO `role` VALUES ('1', '1-2-3-4-5-6-7-8-9-10-11-12-13-14-15-16-17-18-19-20-21-22-23-24-25-26-27-28-29-30-31-32-33-34', '0', null, '超级管理员');
INSERT INTO `role` VALUES ('2', '1-2-3-4-5-6-7-8-9-10-11-12-13-14-15-16-17-18-19-20-21-22-23-24-25-26-27-28-30-31-32-33-34', '0', null, '总经理');
INSERT INTO `role` VALUES ('3', '2-4-8-10-11-12-13-14-16-18-19-31', '0', null, '业务部经理');
INSERT INTO `role` VALUES ('4', '3-4-20-21-22-23-24-25-26-27-28-31', '0', null, '行政专员');
INSERT INTO `role` VALUES ('5', '1-4-5-6-7-8-9-10-11-12-13-14-15-16-17-18-19-31-32', '0', null, '市场部经理');
INSERT INTO `role` VALUES ('6', '1-4-5-7-32', '0', null, '市场部录入员');
INSERT INTO `role` VALUES ('7', '1-2-3-4-5-6-7-8-9-10-11-12-13-14-15-16-17-18-19-20-21-22-23-24-25-26-27-28-29-30-31-32-33-34', '0', null, '销售总监');
INSERT INTO `role` VALUES ('8', '2-4-9-10-11-12-13-14-17-18-19-31-33', '0', null, '特级客户经理');
INSERT INTO `role` VALUES ('9', '2-4-9-10-11-12-13-14-17-18-19-31-33', '0', null, '高级客户经理');
INSERT INTO `role` VALUES ('10', '2-4-9-10-11-12-13-14-17-18-19-31-33', '0', null, '中级客户经理');
INSERT INTO `role` VALUES ('11', '2-4-9-10-11-12-13-14-17-18-19-31-33', '0', null, '初级客户经理');
INSERT INTO `role` VALUES ('12', '2-4-9-10-11-12-13-14-17-18-19-31-33', '0', null, '试用期客户经理');

-- ----------------------------
-- 3.部门
-- ----------------------------
INSERT INTO `department` VALUES ('1', '业务一部', null);
INSERT INTO `department` VALUES ('2', '业务二部', null);
INSERT INTO `department` VALUES ('3', '业务三部', null);
INSERT INTO `department` VALUES ('4', '业务四部', null);
INSERT INTO `department` VALUES ('5', '业务五部', null);
INSERT INTO `department` VALUES ('6', '业务六部', null);
INSERT INTO `department` VALUES ('7', '市场部', null);
INSERT INTO `department` VALUES ('8', '行政部', null);
INSERT INTO `department` VALUES ('9', '总经办', null);


-- ----------------------------
-- 4.客户来源
-- ----------------------------
INSERT INTO `customersource` VALUES (1, null, '中介');
INSERT INTO `customersource` VALUES (2, null, '续贷客户');
INSERT INTO `customersource` VALUES (3, null, '口碑客户');
INSERT INTO `customersource` VALUES (4, null, '权证客户');
INSERT INTO `customersource` VALUES (5, null, '名单');
INSERT INTO `customersource` VALUES (6, null, '网络1');
INSERT INTO `customersource` VALUES (7, null, '网络2');
INSERT INTO `customersource` VALUES (8, null, '网络3');
INSERT INTO `customersource` VALUES (9, null, '网络4');
INSERT INTO `customersource` VALUES (10, null, '网络5');

-- 5.贷款机构
INSERT INTO `lendinginstitution` VALUES (1, null, '3');
INSERT INTO `lendinginstitution` VALUES (2, '私人', '2');
INSERT INTO `lendinginstitution` VALUES (3, '中国银行', '0');
INSERT INTO `lendinginstitution` VALUES (4, '农业银行', '0');
INSERT INTO `lendinginstitution` VALUES (5, '工商银行', '0');
INSERT INTO `lendinginstitution` VALUES (6, '建设银行', '0');
INSERT INTO `lendinginstitution` VALUES (7, '招商银行', '0');
INSERT INTO `lendinginstitution` VALUES (8, '光大银行', '0');
INSERT INTO `lendinginstitution` VALUES (9, '中信银行', '0');
INSERT INTO `lendinginstitution` VALUES (10, '邮政储蓄', '0');
INSERT INTO `lendinginstitution` VALUES (11, '农商银行', '0');
INSERT INTO `lendinginstitution` VALUES (12, '浙江民泰', '0');
INSERT INTO `lendinginstitution` VALUES (13, '浙江稠州', '0');
INSERT INTO `lendinginstitution` VALUES (14, '大连银行', '0');
INSERT INTO `lendinginstitution` VALUES (15, '重庆银行', '0');
INSERT INTO `lendinginstitution` VALUES (16, '包商银行', '0');
INSERT INTO `lendinginstitution` VALUES (17, '乐山银行', '0');
INSERT INTO `lendinginstitution` VALUES (18, '成都银行', '0');
INSERT INTO `lendinginstitution` VALUES (19, '渣打银行', '0');
INSERT INTO `lendinginstitution` VALUES (20, '平安银行', '0');
INSERT INTO `lendinginstitution` VALUES (21, '浙江商业', '0');
INSERT INTO `lendinginstitution` VALUES (22, '广发银行', '0');
INSERT INTO `lendinginstitution` VALUES (23, '浦发银行', '0');
INSERT INTO `lendinginstitution` VALUES (24, '渤海银行', '0');
INSERT INTO `lendinginstitution` VALUES (25, '兴业银行', '0');
INSERT INTO `lendinginstitution` VALUES (26, '上海银行', '0');
INSERT INTO `lendinginstitution` VALUES (27, '贵阳银行', '0');
INSERT INTO `lendinginstitution` VALUES (28, '德阳银行', '0');
INSERT INTO `lendinginstitution` VALUES (29, '攀枝花银行', '0');
INSERT INTO `lendinginstitution` VALUES (30, '华夏银行', '0');
INSERT INTO `lendinginstitution` VALUES (31, '富登', '1');
INSERT INTO `lendinginstitution` VALUES (32, '宜信', '1');
INSERT INTO `lendinginstitution` VALUES (33, '容易宝', '1');
INSERT INTO `lendinginstitution` VALUES (34, '恒昌', '1');
INSERT INTO `lendinginstitution` VALUES (35, '普罗米斯', '1');
INSERT INTO `lendinginstitution` VALUES (36, '锦城', '1');
INSERT INTO `lendinginstitution` VALUES (37, '久富', '1');
INSERT INTO `lendinginstitution` VALUES (38, '信而富', '1');
INSERT INTO `lendinginstitution` VALUES (39, '泛华', '1');
INSERT INTO `lendinginstitution` VALUES (40, '维士', '1');
INSERT INTO `lendinginstitution` VALUES (41, '惠信', '1');
INSERT INTO `lendinginstitution` VALUES (42, '宝鼎', '1');
INSERT INTO `lendinginstitution` VALUES (43, '银谷普惠', '1');
INSERT INTO `lendinginstitution` VALUES (44, '满程', '1');
INSERT INTO `lendinginstitution` VALUES (45, '陆金所', '1');
INSERT INTO `lendinginstitution` VALUES (46, '捷越', '1');
INSERT INTO `lendinginstitution` VALUES (47, '后河', '1');
INSERT INTO `lendinginstitution` VALUES (48, '阳光', '1');
INSERT INTO `lendinginstitution` VALUES (49, '平安易贷', '1');
INSERT INTO `lendinginstitution` VALUES (50, '民信', '1');
INSERT INTO `lendinginstitution` VALUES (51, '中腾信', '1');
INSERT INTO `lendinginstitution` VALUES (52, '海距', '1');
INSERT INTO `lendinginstitution` VALUES (53, '中兴微贷', '1');
INSERT INTO `lendinginstitution` VALUES (54, '正大速贷', '1');
INSERT INTO `lendinginstitution` VALUES (55, '中联慧财', '1');
INSERT INTO `lendinginstitution` VALUES (56, '亚联财', '1');
INSERT INTO `lendinginstitution` VALUES (57, '友信', '1');
INSERT INTO `lendinginstitution` VALUES (58, '四达', '1');
INSERT INTO `lendinginstitution` VALUES (59, '合合年', '1');
INSERT INTO `lendinginstitution` VALUES (60, '点融网', '1');
INSERT INTO `lendinginstitution` VALUES (61, '望洲财富', '1');
INSERT INTO `lendinginstitution` VALUES (62, '富德', '1');

-- ----------------------------
-- 6.员工
-- ----------------------------
INSERT INTO `employee` VALUES ('1', 'admin', '成都', null, null, null, '2015-04-01 14:28:38', null, '男', 'upload/head/0.png', null, '001', '15928868725', 'admin', 'E10ADC3949BA59ABBE56E057F20F883E', null, null, null, null, '1', '否', null, '7', '1');
INSERT INTO `employee` VALUES ('2', '0001', '', '是', '', null, null, '是', '男', 'upload/head/0.png', '', 'sc0001', '13980688546', '张诗伟', 'E10ADC3949BA59ABBE56E057F20F883E', '', '', '', '', '1', '否', '', '9', '7');
INSERT INTO `employee` VALUES ('3', 'sc01002', '', '是', '', null, null, '是', '女', 'upload/head/1.png', '', 'sc01002', '13608090231', '谢文娟', 'E10ADC3949BA59ABBE56E057F20F883E', '', '', '', '', '1', '是', '', '1', '9');
INSERT INTO `employee` VALUES ('4', 'sc01001', '', '是', '687', null, null, '是', '男', 'upload/head/0.png', '', 'sc01001', '13540117032', '李开拓', 'E10ADC3949BA59ABBE56E057F20F883E', '', '', '', '', '1', '是', '0', '1', '3');
INSERT INTO `employee` VALUES ('5', 'sc01003', '', '是', '', null, null, '是', '女', 'upload/head/1.png', '', 'sc01003', '18328410936', '谢雨萍', 'E10ADC3949BA59ABBE56E057F20F883E', '', '', '', '', '1', '是', '', '1', '9');
INSERT INTO `employee` VALUES ('6', 'sc01004', '', '是', '', null, null, '是', '女', 'upload/head/1.png', '', 'sc01004', '15213169131', '蒋雨林', 'E10ADC3949BA59ABBE56E057F20F883E', '', '', '', '', '1', '否', '', '1', '12');
INSERT INTO `employee` VALUES ('7', 'sc01005', '', '是', '', null, null, '是', '男', 'upload/head/0.png', '', 'sc01005', '18382267230', '刘芋材', 'E10ADC3949BA59ABBE56E057F20F883E', '', '', '', '', '1', '否', '', '1', '11');
INSERT INTO `employee` VALUES ('8', 'sc01006', '', '是', '', null, null, '是', '男', 'upload/head/0.png', '', 'sc01006', '18681221187', '胡健', 'E10ADC3949BA59ABBE56E057F20F883E', '', '', '', '', '1', '否', '', '1', '11');
INSERT INTO `employee` VALUES ('9', 'sc02001', '', '是', '', null, null, '是', '男', 'upload/head/0.png', '', 'sc02001', '18161227710', '郭红军', 'E10ADC3949BA59ABBE56E057F20F883E', '', '', '', '', '1', '否', '', '2', '3');
INSERT INTO `employee` VALUES ('10', 'sc02002', '', '是', '', null, null, '是', '女', 'upload/head/1.png', '', 'sc02002', '13982191698', '杨静', 'E10ADC3949BA59ABBE56E057F20F883E', '', '', '', '', '1', '是', '', '2', '9');
INSERT INTO `employee` VALUES ('11', 'sc02003', '', '是', '', null, null, '是', '女', 'upload/head/1.png', '', 'sc02003', '18284537927', '王岚', 'E10ADC3949BA59ABBE56E057F20F883E', '', '', '', '', '1', '是', '', '2', '10');
INSERT INTO `employee` VALUES ('12', 'sc02004', '', '是', '', null, null, '是', '男', 'upload/head/0.png', '', 'sc02004', '15008252584', '李朕', 'E10ADC3949BA59ABBE56E057F20F883E', '', '', '', '', '1', '否', '', '2', '11');
INSERT INTO `employee` VALUES ('13', 'sc03001', '', '是', '', null, null, '是', '男', 'upload/head/0.png', '', 'sc03001', '13679048521', '潘冬', 'B221AAB8BF658C687417362B3ACCE9B4', '', '', '', '', '1', '是', '', '3', '3');
INSERT INTO `employee` VALUES ('14', 'sc04001', '', '是', '', null, null, '是', '男', 'upload/head/0.png', '', 'sc04001', '18908196176', '曾凡熙', 'E10ADC3949BA59ABBE56E057F20F883E', '', '', '', '', '1', '是', '', '4', '3');
INSERT INTO `employee` VALUES ('15', 'sc02007', '', '是', '', null, null, '是', '女', 'upload/head/1.png', '', 'sc02007', '18180821167', '江琼', 'E10ADC3949BA59ABBE56E057F20F883E', '', '', '', '', '1', '否', '', '2', '12');
INSERT INTO `employee` VALUES ('16', 'sc04002', '', '是', '', null, null, '是', '男', 'upload/head/0.png', '', 'sc04002', '18215525853', '陈诚', 'E10ADC3949BA59ABBE56E057F20F883E', '', '', '', '', '1', '是', '', '4', '11');
INSERT INTO `employee` VALUES ('17', 'sc01007', '', '是', '', null, null, '是', '女', 'upload/head/1.png', '', 'sc01007', '18682691140', '经竹', 'E10ADC3949BA59ABBE56E057F20F883E', '', '', '', '', '1', '否', '18111629690', '1', '12');
INSERT INTO `employee` VALUES ('18', 'sc04003', '', '是', '', null, null, '是', '女', 'upload/head/1.png', '', 'sc04003', '18202866765', '袁智', 'E10ADC3949BA59ABBE56E057F20F883E', '', '', '', '', '1', '否', '', '4', '11');
INSERT INTO `employee` VALUES ('19', 'sc03002', '', '是', '667', null, null, '是', '女', 'upload/head/1.png', '', 'sc03002', '13668241721', '王爱好', 'E10ADC3949BA59ABBE56E057F20F883E', '', '', '', '', '1', '是', '', '3', '11');
INSERT INTO `employee` VALUES ('20', 'sc04004', '', '是', '', null, null, '是', '男', 'upload/head/0.png', '', 'sc04004', '15828082154', '吴磊', 'E10ADC3949BA59ABBE56E057F20F883E', '', '', '', '', '1', '否', '', '4', '11');
INSERT INTO `employee` VALUES ('21', 'sc03003', '', '是', '', null, null, '是', '女', 'upload/head/1.png', '', 'sc03003', '13402855132', '邹敏', 'E10ADC3949BA59ABBE56E057F20F883E', '', '', '', '', '1', '否', '', '3', '11');
INSERT INTO `employee` VALUES ('22', 'sc03004', '', '是', '', null, null, '是', '女', 'upload/head/1.png', '', 'sc03004', '15108280821', '张欢', 'E10ADC3949BA59ABBE56E057F20F883E', '', '', '', '', '1', '否', '', '3', '11');
INSERT INTO `employee` VALUES ('23', 'sc04005', '', '是', '', null, null, '是', '女', 'upload/head/1.png', '', 'sc04005', '15881155510', '鲜彩桥', 'E10ADC3949BA59ABBE56E057F20F883E', '', '', '', '', '1', '否', '', '4', '12');
INSERT INTO `employee` VALUES ('24', 'sc02005', '', '是', '', null, null, '是', '男', 'upload/head/0.png', '', 'sc02005', '15882112812', '周中志', 'E10ADC3949BA59ABBE56E057F20F883E', '', '', '', '', '1', '否', '', '2', '12');
INSERT INTO `employee` VALUES ('25', 'sc03005', '', '是', '', null, null, '是', '女', 'upload/head/1.png', '', 'sc03005', '18681222019', '范雪莲', 'E10ADC3949BA59ABBE56E057F20F883E', '', '', '', '', '1', '否', '', '3', '11');
INSERT INTO `employee` VALUES ('26', 'sc04006', '', '是', '', null, null, '是', '女', 'upload/head/1.png', '', 'sc04006', '18200234741', '宋方琼', 'E10ADC3949BA59ABBE56E057F20F883E', '', '', '', '', '1', '否', '', '4', '12');
INSERT INTO `employee` VALUES ('27', 'sc04007', '', '是', '', null, null, '是', '女', 'upload/head/1.png', '', 'sc04007', '15284048354', '胡静', 'E10ADC3949BA59ABBE56E057F20F883E', '', '', '', '', '1', '否', '', '4', '12');
INSERT INTO `employee` VALUES ('28', 'sc03006', '', '是', '', null, null, '是', '女', 'upload/head/0.png', '', 'sc03006', '18768531542', '王茜芝', 'E10ADC3949BA59ABBE56E057F20F883E', '', '', '', '', '1', '否', '', '3', '12');
INSERT INTO `employee` VALUES ('29', 'sc03007', '', '是', '', null, null, '是', '女', 'upload/head/0.png', '', 'sc03007', '18215584544', '徐琴', 'E10ADC3949BA59ABBE56E057F20F883E', '', '', '', '', '1', '否', '', '3', '12');
INSERT INTO `employee` VALUES ('30', 'sc02006', '', '是', '', null, null, '是', '女', 'upload/head/1.png', '', 'sc02006', '15184358427', '吴美玲', 'E10ADC3949BA59ABBE56E057F20F883E', '', '', '', '', '1', '否', '', '2', '12');
