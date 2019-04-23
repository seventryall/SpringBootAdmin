
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_function
-- ----------------------------
DROP TABLE IF EXISTS `sys_function`;
CREATE TABLE `sys_function` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `name` varchar(20) DEFAULT NULL COMMENT '功能名称',
  `code` varchar(20) DEFAULT NULL COMMENT '功能编码',
  `order_id` smallint(6) DEFAULT NULL COMMENT '排序编号',
  `is_deleted` bit(1) DEFAULT NULL COMMENT '是否删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user_id` int(11) DEFAULT NULL COMMENT '创建用户id',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_time_id` int(11) DEFAULT NULL COMMENT '更新用户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_function
-- ----------------------------
INSERT INTO `sys_function` VALUES ('1', '显示', 'View', '1', null, '2019-03-30 00:06:45', null, null, null);
INSERT INTO `sys_function` VALUES ('2', '新增', 'Insert', '2', null, '2019-03-30 00:06:48', null, null, null);
INSERT INTO `sys_function` VALUES ('3', '修改', 'Update', '3', null, '2019-03-30 00:06:52', null, null, null);
INSERT INTO `sys_function` VALUES ('4', '删除', 'Delete', '4', null, '2019-03-30 00:06:55', null, null, null);
INSERT INTO `sys_function` VALUES ('5', '详情', 'Detail', '5', null, '2019-03-30 00:06:57', null, null, null);

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `name` varchar(20) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(50) DEFAULT NULL COMMENT '菜单地址',
  `icon` varchar(20) DEFAULT NULL COMMENT '菜单图标',
  `parent_id` int(11) DEFAULT NULL COMMENT '父级id',
  `order_id` smallint(6) DEFAULT NULL COMMENT '排序编号',
  `is_parent` bit(1) DEFAULT NULL COMMENT '是否父级',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `is_deleted` bit(1) DEFAULT NULL COMMENT '是否删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user_id` int(11) DEFAULT NULL COMMENT '创建用户id',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_user_id` int(11) DEFAULT NULL COMMENT '更新用户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '权限管理', null, null, '0', '1', '', null, null, '2019-03-23 10:13:40', null, null, null);
INSERT INTO `sys_menu` VALUES ('2', '用户管理', '/user/index', null, '1', '2', '\0', null, null, '2019-03-23 10:13:40', null, null, null);
INSERT INTO `sys_menu` VALUES ('3', '角色管理', '/role/index', null, '1', '3', '\0', null, null, '2019-03-23 10:13:40', null, null, null);
INSERT INTO `sys_menu` VALUES ('4', '菜单管理', '/menu/index', null, '1', '4', '\0', null, null, '2019-03-23 10:13:40', null, null, null);
INSERT INTO `sys_menu` VALUES ('5', '功能管理', '/function/index', null, '1', '5', '\0', null, null, '2019-03-23 10:13:40', null, null, null);
INSERT INTO `sys_menu` VALUES ('6', '权限分配', '/auth/index', null, '1', '6', '\0', null, null, '2019-03-23 10:13:40', null, null, null);
INSERT INTO `sys_menu` VALUES ('7', '测试页面', null, null, '0', '7', '', null, null, '2019-03-23 10:13:40', null, null, null);
INSERT INTO `sys_menu` VALUES ('8', '管理员权限', '/auth/adminTest', null, '7', '8', '\0', null, null, '2019-03-23 10:13:40', null, null, null);
INSERT INTO `sys_menu` VALUES ('9', '普通用户权限', '/auth/userTest', null, '7', '9', '\0', null, null, '2019-03-23 10:13:40', null, null, null);
INSERT INTO `sys_menu` VALUES ('10', '授权测试', '/auth/authTest', null, '7', '10', '\0', null, null, '2019-03-23 10:13:40', null, null, null);
INSERT INTO `sys_menu` VALUES ('11', '其他', null, null, '0', '11', '', null, null, '2019-03-23 10:13:40', null, null, null);
INSERT INTO `sys_menu` VALUES ('12', '组件', null, null, '11', '12', '', null, null, '2019-03-23 10:13:40', null, null, null);
INSERT INTO `sys_menu` VALUES ('13', '评分', '/other/score', null, '12', '13', '\0', null, null, '2019-03-23 10:13:40', null, null, null);
INSERT INTO `sys_menu` VALUES ('14', '接口文档', '/apidoc/index.html', null, '0', '14', '\0', null, null, '2019-04-08 00:05:21', null, null, null);

-- ----------------------------
-- Table structure for sys_menu_function
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu_function`;
CREATE TABLE `sys_menu_function` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `menu_id` int(11) DEFAULT NULL COMMENT '菜单id',
  `function_id` int(11) DEFAULT NULL COMMENT '功能id',
  `is_deleted` bit(1) DEFAULT NULL COMMENT '是否删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user_id` int(11) DEFAULT NULL COMMENT '创建用户id',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_user_id` int(11) DEFAULT NULL COMMENT '更新用户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_menu_function
-- ----------------------------
INSERT INTO `sys_menu_function` VALUES ('1', '2', '1', null, '2019-03-31 15:44:09', null, null, null);
INSERT INTO `sys_menu_function` VALUES ('2', '2', '2', null, '2019-03-31 15:44:12', null, null, null);
INSERT INTO `sys_menu_function` VALUES ('3', '2', '3', null, '2019-03-31 15:44:14', null, null, null);
INSERT INTO `sys_menu_function` VALUES ('4', '2', '4', null, '2019-03-31 15:44:17', null, null, null);
INSERT INTO `sys_menu_function` VALUES ('5', '2', '5', null, '2019-03-31 15:44:21', null, null, null);
INSERT INTO `sys_menu_function` VALUES ('6', '3', '1', null, '2019-03-31 15:44:24', null, null, null);
INSERT INTO `sys_menu_function` VALUES ('7', '3', '2', null, '2019-03-31 15:44:27', null, null, null);
INSERT INTO `sys_menu_function` VALUES ('8', '3', '3', null, '2019-03-31 15:44:31', null, null, null);
INSERT INTO `sys_menu_function` VALUES ('9', '3', '4', null, '2019-03-31 15:44:35', null, null, null);
INSERT INTO `sys_menu_function` VALUES ('10', '3', '5', null, '2019-03-31 15:44:38', null, null, null);
INSERT INTO `sys_menu_function` VALUES ('11', '4', '1', null, '2019-03-31 15:44:44', null, null, null);
INSERT INTO `sys_menu_function` VALUES ('12', '4', '2', null, '2019-03-31 15:44:47', null, null, null);
INSERT INTO `sys_menu_function` VALUES ('13', '4', '3', null, '2019-03-31 15:44:49', null, null, null);
INSERT INTO `sys_menu_function` VALUES ('14', '4', '4', null, '2019-03-31 15:44:52', null, null, null);
INSERT INTO `sys_menu_function` VALUES ('15', '4', '5', null, '2019-03-31 15:44:54', null, null, null);
INSERT INTO `sys_menu_function` VALUES ('16', '5', '1', null, '2019-03-31 15:44:56', null, null, null);
INSERT INTO `sys_menu_function` VALUES ('17', '5', '2', null, '2019-03-31 15:45:02', null, null, null);
INSERT INTO `sys_menu_function` VALUES ('18', '5', '3', null, '2019-03-31 15:45:05', null, null, null);
INSERT INTO `sys_menu_function` VALUES ('19', '5', '4', null, '2019-03-31 15:45:10', null, null, null);
INSERT INTO `sys_menu_function` VALUES ('20', '5', '5', null, '2019-03-31 15:45:14', null, null, null);
INSERT INTO `sys_menu_function` VALUES ('21', '6', '1', null, '2019-03-31 15:45:17', null, null, null);
INSERT INTO `sys_menu_function` VALUES ('22', '8', '1', null, '2019-03-31 15:45:20', null, null, null);
INSERT INTO `sys_menu_function` VALUES ('23', '9', '1', null, '2019-03-31 15:45:23', null, null, null);
INSERT INTO `sys_menu_function` VALUES ('24', '10', '1', null, '2019-03-31 15:45:27', null, null, null);
INSERT INTO `sys_menu_function` VALUES ('25', '13', '1', null, '2019-03-31 15:45:30', null, null, null);
INSERT INTO `sys_menu_function` VALUES ('26', '14', '1', null, '2019-04-08 00:06:17', null, null, null);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `name` varchar(20) DEFAULT NULL COMMENT '角色名称',
  `code` varchar(20) DEFAULT NULL COMMENT '角色编码',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `is_deleted` bit(1) DEFAULT NULL COMMENT '是否删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user_id` int(11) DEFAULT NULL COMMENT '创建用户id',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_user_id` int(11) DEFAULT NULL COMMENT '更新用户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '管理员', 'admin', '拥有所有权限', null, '2019-03-29 23:30:05', null, null, null);
INSERT INTO `sys_role` VALUES ('2', '普通用户', 'user', null, null, '2019-03-29 23:30:20', null, null, null);

-- ----------------------------
-- Table structure for sys_role_menu_function
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu_function`;
CREATE TABLE `sys_role_menu_function` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `role_id` int(11) DEFAULT NULL COMMENT '角色id',
  `menu_id` int(11) DEFAULT NULL COMMENT '菜单id',
  `function_id` int(11) DEFAULT NULL COMMENT '功能id',
  `is_deleted` bit(1) DEFAULT NULL COMMENT '是否删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user_id` int(11) DEFAULT NULL COMMENT '创建用户id',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_user_id` int(11) DEFAULT NULL COMMENT '更新用户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_menu_function
-- ----------------------------
INSERT INTO `sys_role_menu_function` VALUES ('1', '2', '2', '1', null, null, null, null, null);
INSERT INTO `sys_role_menu_function` VALUES ('2', '2', '2', '5', null, null, null, null, null);
INSERT INTO `sys_role_menu_function` VALUES ('3', '2', '3', '1', null, null, null, null, null);
INSERT INTO `sys_role_menu_function` VALUES ('4', '2', '3', '5', null, null, null, null, null);
INSERT INTO `sys_role_menu_function` VALUES ('5', '2', '4', '1', null, null, null, null, null);
INSERT INTO `sys_role_menu_function` VALUES ('6', '2', '4', '5', null, null, null, null, null);
INSERT INTO `sys_role_menu_function` VALUES ('7', '2', '5', '1', null, null, null, null, null);
INSERT INTO `sys_role_menu_function` VALUES ('8', '2', '5', '5', null, null, null, null, null);
INSERT INTO `sys_role_menu_function` VALUES ('9', '2', '9', '1', null, null, null, null, null);
INSERT INTO `sys_role_menu_function` VALUES ('10', '2', '13', '1', null, null, null, null, null);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `user_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户名',
  `real_name` varchar(20) DEFAULT NULL COMMENT '真实姓名',
  `user_pwd` varchar(50) DEFAULT NULL COMMENT '用户密码',
  `age` smallint(6) DEFAULT NULL COMMENT '年龄',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `is_admin` bit(1) DEFAULT NULL COMMENT '是否管理员',
  `is_deleted` bit(1) DEFAULT NULL COMMENT '是否删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user_id` int(11) DEFAULT NULL COMMENT '创建用户id',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_user_id` int(11) DEFAULT NULL COMMENT '更新用户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '管理员', '123', '30', '13367974546', 'admin134@qq.com', '', '\0', '2019-03-24 10:20:40', null, null, null);
INSERT INTO `sys_user` VALUES ('2', 'user', '普通用户', '123', '20', '18888888888', 'user345@163.com', '\0', '\0', '2019-03-24 10:20:40', null, null, null);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `role_id` int(11) DEFAULT NULL COMMENT '角色id',
  `is_deleted` bit(1) DEFAULT NULL COMMENT '是否删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user_id` int(11) DEFAULT NULL COMMENT '创建用户id',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_user_id` int(11) DEFAULT NULL COMMENT '更新用户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '2', '2', null, '2019-03-31 16:13:33', null, null, null);



-- ----------------------------
-- Function structure for getUserAllAuthMenu
-- ----------------------------
DROP FUNCTION IF EXISTS `getUserAllAuthMenu`;
DELIMITER ;;
SET GLOBAL log_bin_trust_function_creators = 1;
CREATE DEFINER=`root`@`localhost` FUNCTION `getUserAllAuthMenu`(userID INT) RETURNS varchar(1000) CHARSET utf8
BEGIN
    DECLARE sTemp VARCHAR(1000);
    DECLARE sTempPar VARCHAR(1000); 
    SET sTemp = ''; 
    select GROUP_CONCAT(cast(`id` as char(10)) SEPARATOR ',') into sTempPar from sys_menu where 
                         id in (select distinct(menu_id) from sys_role_menu_function where  role_id in (select role_id from
                                 sys_user_role where user_id=userID)) ;
 
    #循环递归
    WHILE sTempPar is not null DO 
        #判断是否是第一个，不加的话第一个会为空
        IF sTemp != '' THEN
            SET sTemp = concat(sTemp,',',sTempPar);
        ELSE
            SET sTemp = sTempPar;
        END IF;
        SET sTemp = concat(sTemp,',',sTempPar); 
        SELECT group_concat(parent_id) INTO sTempPar FROM sys_menu where parent_id<>id and FIND_IN_SET(id,sTempPar)>0; 
    END WHILE; 
 
RETURN sTemp; 
END
;;
DELIMITER ;
