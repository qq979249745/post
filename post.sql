/*
 Navicat Premium Data Transfer

 Source Server         : Mysql
 Source Server Type    : MySQL
 Source Server Version : 80013
 Source Host           : 127.0.0.1:3306
 Source Schema         : post

 Target Server Type    : MySQL
 Target Server Version : 80013
 File Encoding         : 65001

 Date: 17/06/2019 19:20:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_cart
-- ----------------------------
DROP TABLE IF EXISTS `tb_cart`;
CREATE TABLE `tb_cart`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '购物车id',
  `customer_id` int(11) NOT NULL COMMENT '消费者id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `frk_c_c`(`customer_id`) USING BTREE,
  CONSTRAINT `frk_c_c` FOREIGN KEY (`customer_id`) REFERENCES `tb_customer` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_cart
-- ----------------------------
INSERT INTO `tb_cart` VALUES (1, 3);

-- ----------------------------
-- Table structure for tb_cart_item
-- ----------------------------
DROP TABLE IF EXISTS `tb_cart_item`;
CREATE TABLE `tb_cart_item`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '购物车明细id',
  `cart_id` int(11) NOT NULL COMMENT '购物车id',
  `product_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '产品id',
  `quanity` int(11) NOT NULL COMMENT '产品数量',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `frk_ci_c`(`cart_id`) USING BTREE,
  INDEX `frk_ci_p`(`product_id`) USING BTREE,
  CONSTRAINT `frk_ci_c` FOREIGN KEY (`cart_id`) REFERENCES `tb_cart` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `frk_ci_p` FOREIGN KEY (`product_id`) REFERENCES `tb_product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_cart_item
-- ----------------------------
INSERT INTO `tb_cart_item` VALUES (4, 1, 'd38ad7cee1624faab71c21bd6b8881bf', 1);
INSERT INTO `tb_cart_item` VALUES (5, 1, 'b1fcd029c43c494d9c7bd82dc1c6f30c', 1);
INSERT INTO `tb_cart_item` VALUES (6, 1, 'f058fc8608f144c3a3dcf8a0a9925976', 1);
INSERT INTO `tb_cart_item` VALUES (7, 1, '843ca42977f4447fbf144ee14d49c394', 1);

-- ----------------------------
-- Table structure for tb_category
-- ----------------------------
DROP TABLE IF EXISTS `tb_category`;
CREATE TABLE `tb_category`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '产品分类id',
  `category_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '产品分类名字',
  `product_num` int(11) NULL DEFAULT 0 COMMENT '产品的数量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_category
-- ----------------------------
INSERT INTO `tb_category` VALUES (2, '衣服配饰', 1);
INSERT INTO `tb_category` VALUES (3, '手机电脑', 2);
INSERT INTO `tb_category` VALUES (4, '饮料牛奶', 1);
INSERT INTO `tb_category` VALUES (5, '运动球鞋', 2);

-- ----------------------------
-- Table structure for tb_customer
-- ----------------------------
DROP TABLE IF EXISTS `tb_customer`;
CREATE TABLE `tb_customer`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '消费者id',
  `account` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '消费者账号',
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '消费者姓名',
  `gender` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '男' COMMENT '性别',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '消费者表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_customer
-- ----------------------------
INSERT INTO `tb_customer` VALUES (1, 'qq979249745', '4HibwrKyMmuLZ6af/4GKWw==', 'qq979249745', '男');
INSERT INTO `tb_customer` VALUES (2, '1111', 'AWEQkOTpHNo=', '李真帅', '男');
INSERT INTO `tb_customer` VALUES (3, '123456', 'x16YjoF1LNE=', '王八', '男');

-- ----------------------------
-- Table structure for tb_manager
-- ----------------------------
DROP TABLE IF EXISTS `tb_manager`;
CREATE TABLE `tb_manager`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '管理员id',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '管理员姓名',
  `account` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员账号',
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员密码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_manager
-- ----------------------------
INSERT INTO `tb_manager` VALUES (1, 'XXX超市管理员', 'admin', 'nbgYr0DpdB8=');

-- ----------------------------
-- Table structure for tb_product
-- ----------------------------
DROP TABLE IF EXISTS `tb_product`;
CREATE TABLE `tb_product`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '产品id',
  `product_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '产品名称',
  `category_id` int(11) NOT NULL COMMENT '产品类别id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `frk_p_c`(`category_id`) USING BTREE,
  CONSTRAINT `frk_p_c` FOREIGN KEY (`category_id`) REFERENCES `tb_category` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '产品' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_product
-- ----------------------------
INSERT INTO `tb_product` VALUES ('843ca42977f4447fbf144ee14d49c394', 'Huawei/华为 P30全面屏超感光徕卡三摄变焦双景录像980芯片智能手机p30', 3);
INSERT INTO `tb_product` VALUES ('a5bf37b93d6042238feeac69fa2a42f7', 'Xiaomi/小米 Redmi K20 Pro 新品4800万三摄骁龙855手机9官方旗舰店正品红米k20pro', 3);
INSERT INTO `tb_product` VALUES ('b1fcd029c43c494d9c7bd82dc1c6f30c', 'Nike耐克官方SUPERFLY 6 ELITE FG男/女天然硬质草地足球鞋AJ3547', 5);
INSERT INTO `tb_product` VALUES ('cd1bd1441c9f41219923c362361bac02', '阿迪达斯官方 三叶草 YEEZY BOOST 700 V2 男女经典鞋FU6684', 5);
INSERT INTO `tb_product` VALUES ('d38ad7cee1624faab71c21bd6b8881bf', '百事可乐爱心罐碳酸汽水饮料拉罐330ml*6聚会分享装百事出品', 4);
INSERT INTO `tb_product` VALUES ('f058fc8608f144c3a3dcf8a0a9925976', '阿迪达斯官方 adidas BOS LOGO FILLED 男子篮球短袖DQ0928', 2);

-- ----------------------------
-- Table structure for tb_product_specification
-- ----------------------------
DROP TABLE IF EXISTS `tb_product_specification`;
CREATE TABLE `tb_product_specification`  (
  `product_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '产品id',
  `specification` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品描述',
  `price` int(11) NULL DEFAULT 0 COMMENT '产品价格',
  `discount` int(11) NULL DEFAULT 1 COMMENT '促销价',
  `inventory` int(11) NULL DEFAULT 0 COMMENT '库存',
  `product_img` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品图片',
  INDEX `frk_ps_p`(`product_id`) USING BTREE,
  CONSTRAINT `frk_ps_p` FOREIGN KEY (`product_id`) REFERENCES `tb_product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '产品详情' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_product_specification
-- ----------------------------
INSERT INTO `tb_product_specification` VALUES ('843ca42977f4447fbf144ee14d49c394', '我爱华为，支持国产', 398800, 389900, 99, 'd1952f8e964648c2977dcb2b7189aa7e.jpg');
INSERT INTO `tb_product_specification` VALUES ('a5bf37b93d6042238feeac69fa2a42f7', '正面全是屏幕的手机', 249900, 239900, 100, 'a5bf37b93d6042238feeac69fa2a42f7.jpg');
INSERT INTO `tb_product_specification` VALUES ('b1fcd029c43c494d9c7bd82dc1c6f30c', '非常耐克的鞋子', 249900, 229900, 32, 'b1fcd029c43c494d9c7bd82dc1c6f30c.jpg');
INSERT INTO `tb_product_specification` VALUES ('d38ad7cee1624faab71c21bd6b8881bf', '肥宅快乐水', 1290, 1190, 9999, 'd38ad7cee1624faab71c21bd6b8881bf.jpg');
INSERT INTO `tb_product_specification` VALUES ('f058fc8608f144c3a3dcf8a0a9925976', '阿迪达斯', 19900, 18900, 999, 'f058fc8608f144c3a3dcf8a0a9925976.jpg');
INSERT INTO `tb_product_specification` VALUES ('cd1bd1441c9f41219923c362361bac02', '三叶草男鞋', 259900, 249900, 899, 'cd1bd1441c9f41219923c362361bac02.jpg');

-- ----------------------------
-- Table structure for tb_sale
-- ----------------------------
DROP TABLE IF EXISTS `tb_sale`;
CREATE TABLE `tb_sale`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '销售id',
  `customer_id` int(11) NOT NULL COMMENT '消费者id',
  `create_time` datetime(0) NOT NULL COMMENT '提交订单时间',
  `pay_time` datetime(0) NULL DEFAULT NULL COMMENT '支付订单时间',
  `total_price` int(11) NULL DEFAULT 0 COMMENT '订单总价格',
  `status` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单状态',
  `payment` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '支付方式',
  `pay_no` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '支付订单流水号',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `frk_s_c`(`customer_id`) USING BTREE,
  CONSTRAINT `frk_s_c` FOREIGN KEY (`customer_id`) REFERENCES `tb_customer` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '销售表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_sale
-- ----------------------------
INSERT INTO `tb_sale` VALUES ('1e99c4accf714fcfaa86c89cbf5db50d', 3, '2019-06-17 10:42:26', '2019-06-17 10:44:14', 1190, '已支付', '微信支付', '1560768254302');
INSERT INTO `tb_sale` VALUES ('357cc6c661d94538b955535c43bb5883', 3, '2019-06-17 07:32:56', NULL, 248800, '待付款', '支付宝支付', NULL);
INSERT INTO `tb_sale` VALUES ('770479df845849b68a1a06b03dab0b17', 3, '2019-06-04 14:02:21', '2019-06-06 08:55:12', 920790, '已支付', '支付宝支付', '2019060622001420061000040797');
INSERT INTO `tb_sale` VALUES ('b37b074cd42140d6aee31492033a6ac1', 3, '2019-06-17 10:33:47', '2019-06-17 10:40:06', 248800, '已支付', '支付宝支付', '2019061722001420061000045435');
INSERT INTO `tb_sale` VALUES ('c5b54d9c11dd4c7a90637185802df3ff', 3, '2019-06-06 09:32:31', NULL, 229900, '待付款', NULL, NULL);
INSERT INTO `tb_sale` VALUES ('f01221433fee461a8bde60fc151387d9', 3, '2019-06-06 10:25:36', '2019-06-17 10:44:37', 229900, '已支付', '现金支付', '1560768277104');

-- ----------------------------
-- Table structure for tb_sale_item
-- ----------------------------
DROP TABLE IF EXISTS `tb_sale_item`;
CREATE TABLE `tb_sale_item`  (
  `sale_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '销售id',
  `product_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '产品id',
  `quanity` int(11) NOT NULL COMMENT '产品的数量',
  INDEX `frk_si_p`(`product_id`) USING BTREE,
  INDEX `frk_si_s`(`sale_id`) USING BTREE,
  CONSTRAINT `frk_si_p` FOREIGN KEY (`product_id`) REFERENCES `tb_product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `frk_si_s` FOREIGN KEY (`sale_id`) REFERENCES `tb_sale` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '销售订单详情表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_sale_item
-- ----------------------------
INSERT INTO `tb_sale_item` VALUES ('770479df845849b68a1a06b03dab0b17', 'b1fcd029c43c494d9c7bd82dc1c6f30c', 4);
INSERT INTO `tb_sale_item` VALUES ('770479df845849b68a1a06b03dab0b17', 'd38ad7cee1624faab71c21bd6b8881bf', 1);
INSERT INTO `tb_sale_item` VALUES ('c5b54d9c11dd4c7a90637185802df3ff', 'b1fcd029c43c494d9c7bd82dc1c6f30c', 1);
INSERT INTO `tb_sale_item` VALUES ('f01221433fee461a8bde60fc151387d9', 'b1fcd029c43c494d9c7bd82dc1c6f30c', 1);
INSERT INTO `tb_sale_item` VALUES ('357cc6c661d94538b955535c43bb5883', 'b1fcd029c43c494d9c7bd82dc1c6f30c', 1);
INSERT INTO `tb_sale_item` VALUES ('357cc6c661d94538b955535c43bb5883', 'f058fc8608f144c3a3dcf8a0a9925976', 1);
INSERT INTO `tb_sale_item` VALUES ('b37b074cd42140d6aee31492033a6ac1', 'b1fcd029c43c494d9c7bd82dc1c6f30c', 1);
INSERT INTO `tb_sale_item` VALUES ('b37b074cd42140d6aee31492033a6ac1', 'f058fc8608f144c3a3dcf8a0a9925976', 1);
INSERT INTO `tb_sale_item` VALUES ('1e99c4accf714fcfaa86c89cbf5db50d', 'd38ad7cee1624faab71c21bd6b8881bf', 1);

SET FOREIGN_KEY_CHECKS = 1;
