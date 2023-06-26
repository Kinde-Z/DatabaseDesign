/*
 Navicat Premium Data Transfer

 Source Server         : MySQL
 Source Server Type    : MySQL
 Source Server Version : 50739
 Source Host           : localhost:3306
 Source Schema         : dbdesign

 Target Server Type    : MySQL
 Target Server Version : 50739
 File Encoding         : 65001

 Date: 26/06/2023 14:02:12
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_suppliers
-- ----------------------------
DROP TABLE IF EXISTS `sys_suppliers`;
CREATE TABLE `sys_suppliers`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '供应商编号',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '供应商名称',
  `phone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '供应商电话',
  `address` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '供应商地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `nickname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `address` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_check
-- ----------------------------
DROP TABLE IF EXISTS `sys_check`;
CREATE TABLE `sys_check`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '盘点单据号',
  `drug_id` int(11) NOT NULL COMMENT '药品编号',
  `user_id` int(11) NOT NULL COMMENT '操作人编号',
  `check_num` int(11) NULL DEFAULT NULL COMMENT '盘点数量',
  `profit` int(11) NULL DEFAULT NULL COMMENT '盈亏数量',
  `check_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '盘点时间',
  `message` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `sys_check_ibfk_1`(`drug_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  CONSTRAINT `sys_check_ibfk_1` FOREIGN KEY (`drug_id`) REFERENCES `sys_drug` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `sys_check_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_in
-- ----------------------------
DROP TABLE IF EXISTS `sys_in`;
CREATE TABLE `sys_in`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '入库单据号',
  `drug_id` int(11) NOT NULL COMMENT '药品编号',
  `supplier_id` int(11) NOT NULL COMMENT '供应商编号',
  `number` int(11) NULL DEFAULT NULL COMMENT '入库数量',
  `in_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '入库时间',
  `message` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `sys_in_ibfk_1`(`drug_id`) USING BTREE,
  INDEX `sys_in_ibfk_2`(`supplier_id`) USING BTREE,
  CONSTRAINT `sys_in_ibfk_1` FOREIGN KEY (`drug_id`) REFERENCES `sys_drug` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `sys_in_ibfk_2` FOREIGN KEY (`supplier_id`) REFERENCES `sys_suppliers` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_drug
-- ----------------------------
DROP TABLE IF EXISTS `sys_drug`;
CREATE TABLE `sys_drug`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '药品编号',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '药品名称',
  `guarantee` int(11) NULL DEFAULT NULL COMMENT '有效期限',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '药品单价',
  `number` int(11) NULL DEFAULT NULL COMMENT '药品数量',
  `amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '总金额',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_out
-- ----------------------------
DROP TABLE IF EXISTS `sys_out`;
CREATE TABLE `sys_out`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '出库单据号',
  `drug_id` int(11) NOT NULL COMMENT '药品编号',
  `user_id` int(11) NOT NULL COMMENT '操作人编号',
  `number` int(11) NULL DEFAULT NULL COMMENT '出库数量',
  `out_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '出库时间',
  `message` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `sys_out_ibfk_1`(`drug_id`) USING BTREE,
  INDEX `sys_out_ibfk_2`(`user_id`) USING BTREE,
  CONSTRAINT `sys_out_ibfk_1` FOREIGN KEY (`drug_id`) REFERENCES `sys_drug` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `sys_out_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 46 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Triggers structure for table sys_check
-- ----------------------------
DROP TRIGGER IF EXISTS `tri_c1`;
delimiter ;;
CREATE TRIGGER `tri_c1` BEFORE INSERT ON `sys_check` FOR EACH ROW BEGIN
	SET new.profit=new.check_num-(SELECT number from sys_drug WHERE id=new.drug_id);
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table sys_in
-- ----------------------------
DROP TRIGGER IF EXISTS `tri_i1`;
delimiter ;;
CREATE TRIGGER `tri_i1` AFTER INSERT ON `sys_in` FOR EACH ROW BEGIN
	UPDATE sys_drug
	SET sys_drug.number=sys_drug.number+new.number
	WHERE sys_drug.id = new.drug_id;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table sys_drug
-- ----------------------------
DROP TRIGGER IF EXISTS `tri_d1`;
delimiter ;;
CREATE TRIGGER `tri_d1` BEFORE INSERT ON `sys_drug` FOR EACH ROW BEGIN
	SET new.amount=new.number*new.price;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table sys_drug
-- ----------------------------
DROP TRIGGER IF EXISTS `tri_d2`;
delimiter ;;
CREATE TRIGGER `tri_d2` BEFORE UPDATE ON `sys_drug` FOR EACH ROW BEGIN
	SET new.amount=new.number*new.price;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table sys_out
-- ----------------------------
DROP TRIGGER IF EXISTS `tri_u1`;
delimiter ;;
CREATE TRIGGER `tri_u1` AFTER INSERT ON `sys_out` FOR EACH ROW BEGIN
	UPDATE sys_drug
	SET sys_drug.number=sys_drug.number-new.number
	WHERE sys_drug.id = new.drug_id;
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
