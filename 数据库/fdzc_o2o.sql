/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80020
 Source Host           : localhost:3306
 Source Schema         : fdzc_o2o

 Target Server Type    : MySQL
 Target Server Version : 80020
 File Encoding         : 65001

 Date: 10/05/2022 20:52:11
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address`  (
  `address_id` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '地址唯一标识',
  `address_details` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '地址名称',
  `address_linkman` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系人',
  `address_phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `address_gender` int NULL DEFAULT NULL COMMENT '联系人性别',
  `user_id` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户唯一标识',
  PRIMARY KEY (`address_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of address
-- ----------------------------

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `admin_id` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员唯一标识',
  `admin_account` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员账号',
  `admin_password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员密码',
  `admin_name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员用户名',
  `admin_portrait` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '管理员头像路径',
  `admin_gender` int NULL DEFAULT NULL COMMENT '管理员性别',
  `admin_email` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '管理员邮箱',
  `admin_phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '管理员电话',
  `admin_permissions` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '管理员权限',
  `admin_enableStatus` int NULL DEFAULT NULL COMMENT '管理员启用状态',
  `admin_deleteStatus` int NULL DEFAULT NULL COMMENT '管理员删除状态',
  PRIMARY KEY (`admin_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('123456', 'admin', 'admin', '管理员1', NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for advertising
-- ----------------------------
DROP TABLE IF EXISTS `advertising`;
CREATE TABLE `advertising`  (
  `advertising_id` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '广告唯一标识',
  `advertising_source` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '广告来源',
  `advertising_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '广告标题',
  `advertising_link` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '广告连接',
  `advertising_picture` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '广告图片',
  `advertising_createTime` datetime(0) NULL DEFAULT NULL COMMENT '广告创建时间',
  `advertising_expireTime` datetime(0) NULL DEFAULT NULL COMMENT '广告结束时间',
  `advertising_status` int NULL DEFAULT NULL COMMENT '广告状态',
  PRIMARY KEY (`advertising_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of advertising
-- ----------------------------

-- ----------------------------
-- Table structure for comments
-- ----------------------------
DROP TABLE IF EXISTS `comments`;
CREATE TABLE `comments`  (
  `comments_id` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '评论id',
  `comments_contentJson` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '评论json内容',
  `comments_createTime` datetime(0) NULL DEFAULT NULL COMMENT '评论时间',
  `user_id` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评论人',
  `forum_id` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '回复帖子id',
  PRIMARY KEY (`comments_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comments
-- ----------------------------

-- ----------------------------
-- Table structure for dictionary
-- ----------------------------
DROP TABLE IF EXISTS `dictionary`;
CREATE TABLE `dictionary`  (
  `dictionary_id` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '业务字典字典唯一标识',
  `dictionary_name` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '业务字典名称',
  PRIMARY KEY (`dictionary_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dictionary
-- ----------------------------

-- ----------------------------
-- Table structure for dictionarycontent
-- ----------------------------
DROP TABLE IF EXISTS `dictionarycontent`;
CREATE TABLE `dictionarycontent`  (
  `dictionaryContent_id` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字典内容唯一标识',
  `dictionaryContent_text` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字典内容文本',
  `dictionaryContent_value` int NULL DEFAULT NULL COMMENT '字典内容值',
  `dictionary_id` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '业务字典唯一标识',
  PRIMARY KEY (`dictionaryContent_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dictionarycontent
-- ----------------------------

-- ----------------------------
-- Table structure for forum
-- ----------------------------
DROP TABLE IF EXISTS `forum`;
CREATE TABLE `forum`  (
  `forum_id` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '帖子id',
  `forum_contentJson` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '帖子json内容',
  `forum_createTime` datetime(0) NULL DEFAULT NULL COMMENT '帖子创建时间',
  `forum_classify` int NULL DEFAULT NULL COMMENT '帖子分类',
  `user_id` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发布人id',
  PRIMARY KEY (`forum_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of forum
-- ----------------------------

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`  (
  `goods_id` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '产品唯一标识',
  `goods_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品名称',
  `goods_portrait` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '产品图片',
  `goods_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '产品价格',
  `goods_abstract` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '产品简介',
  `goods_status` int NULL DEFAULT NULL COMMENT '产品状态',
  `goods_inventory` int NULL DEFAULT NULL COMMENT '产品库存',
  `merchant_id` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商家id',
  `goodsClassify_id` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品类型id',
  PRIMARY KEY (`goods_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goods
-- ----------------------------

-- ----------------------------
-- Table structure for goodsclassify
-- ----------------------------
DROP TABLE IF EXISTS `goodsclassify`;
CREATE TABLE `goodsclassify`  (
  `goodsClassify_id` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '产品类型唯一标识',
  `goodsClassify_name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型名称',
  `merchant_id` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商家id',
  PRIMARY KEY (`goodsClassify_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goodsclassify
-- ----------------------------

-- ----------------------------
-- Table structure for idle
-- ----------------------------
DROP TABLE IF EXISTS `idle`;
CREATE TABLE `idle`  (
  `idle_id` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '闲置物品id',
  `idle_title` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '闲置物品标题',
  `idle_contentJson` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '闲置物品json数据',
  `idle_price` double(10, 2) NULL DEFAULT NULL COMMENT '闲置物品价格',
  `idle_status` int NULL DEFAULT NULL COMMENT '闲置物品交易状态',
  `user_id` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发布人id',
  PRIMARY KEY (`idle_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of idle
-- ----------------------------

-- ----------------------------
-- Table structure for like
-- ----------------------------
DROP TABLE IF EXISTS `like`;
CREATE TABLE `like`  (
  `forum_id` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '帖子id',
  `user_id` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '点赞用户id'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of like
-- ----------------------------

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `menu_id` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单唯一标识',
  `menu_name` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `menu_parent` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父菜单id',
  `menu_priority` int NULL DEFAULT NULL COMMENT '菜单优先级',
  `menu_link` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '链接地址',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu
-- ----------------------------

-- ----------------------------
-- Table structure for merchant
-- ----------------------------
DROP TABLE IF EXISTS `merchant`;
CREATE TABLE `merchant`  (
  `merchant_id` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商家唯一标识',
  `merchant_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商家名称',
  `merchant_type` int NULL DEFAULT NULL COMMENT '商家类型',
  `merchant_portrait` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '商家图标',
  `merchant_address` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '商家地址',
  `merchant_notice` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '商家公告',
  `merchant_createTime` datetime(0) NULL DEFAULT NULL COMMENT '商家入驻时间',
  `merchant_enbleStatus` int NULL DEFAULT NULL COMMENT '商家启用状态',
  `merchant_deleteStatus` int NULL DEFAULT NULL COMMENT '商家删除状态',
  `user_id` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '负责人唯一标识',
  PRIMARY KEY (`merchant_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of merchant
-- ----------------------------

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `order_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单唯一标识',
  `order_number` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单编号',
  `order_type` int NULL DEFAULT NULL COMMENT '订单类型',
  `order_goods` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '订单商品',
  `order_status` int NULL DEFAULT NULL COMMENT '订单状态',
  `order_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '订单价格',
  `order_createTime` datetime(0) NULL DEFAULT NULL COMMENT '订单创建时间',
  `order_successTime` datetime(0) NULL DEFAULT NULL COMMENT '订单完成事件',
  `order_deleteStatus` int NULL DEFAULT NULL COMMENT '订单删除状态',
  `address_id` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址唯一标识',
  `merchant_id` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商家id',
  `user_id` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '兼职人员id',
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order
-- ----------------------------

-- ----------------------------
-- Table structure for organization
-- ----------------------------
DROP TABLE IF EXISTS `organization`;
CREATE TABLE `organization`  (
  `organization_id` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '组织社团id',
  `organization_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组织社团名称',
  `organization_logo` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '组织社团logo',
  `organization_contentJson` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '组织社团风采',
  `organization_quantity` int NULL DEFAULT NULL COMMENT '组织人数',
  `organization_type` int NULL DEFAULT NULL COMMENT '组织类型',
  PRIMARY KEY (`organization_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of organization
-- ----------------------------

-- ----------------------------
-- Table structure for phonebook
-- ----------------------------
DROP TABLE IF EXISTS `phonebook`;
CREATE TABLE `phonebook`  (
  `phoneBook_id` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '电话簿id',
  `phoneBook_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话簿名称',
  `phoneBook_phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话簿号码',
  `phoneBook_type` int NULL DEFAULT NULL COMMENT '电话簿类型',
  PRIMARY KEY (`phoneBook_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of phonebook
-- ----------------------------

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `role_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色唯一标识',
  `role_name` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `role_deleteStatus` int NULL DEFAULT NULL COMMENT '角色删除状态',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------

-- ----------------------------
-- Table structure for role_menu
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu`  (
  `role_id` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色唯一标识',
  `menu_id` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单唯一标识'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_menu
-- ----------------------------

-- ----------------------------
-- Table structure for slide
-- ----------------------------
DROP TABLE IF EXISTS `slide`;
CREATE TABLE `slide`  (
  `slide_id` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '轮播图唯一标识',
  `slide_picture` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '轮播图图片',
  `slide_link` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '轮播图地址',
  `slide_note` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '轮播图备注',
  `slide_createTime` datetime(0) NULL DEFAULT NULL COMMENT '轮播图创建时间',
  `slide_priority` int NULL DEFAULT NULL COMMENT '轮播图优先级',
  `slide_status` int NULL DEFAULT NULL COMMENT '轮播图状态',
  `slide_module` int NULL DEFAULT NULL COMMENT '轮播图所属模块',
  PRIMARY KEY (`slide_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of slide
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户唯一标识',
  `user_account` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户账号',
  `user_password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户密码',
  `user_name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `user_portrait` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '用户头像地址',
  `user_gender` int NULL DEFAULT NULL COMMENT '用户性别',
  `user_email` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户邮箱',
  `user_phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户手机号',
  `user_stuID` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学号绑定',
  `user_wxID` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '微信绑定',
  `user_isStudent` int NULL DEFAULT NULL COMMENT '学生认证状态',
  `user_isMerchant` int NULL DEFAULT NULL COMMENT '商家认证状态',
  `user_isPartTime` int NULL DEFAULT NULL COMMENT '兼职认证状态',
  `user_balance` double(9, 2) NULL DEFAULT NULL COMMENT '用户余额',
  `user_status` int NULL DEFAULT NULL COMMENT '用户状态',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
