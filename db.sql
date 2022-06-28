/*
 Navicat Premium Data Transfer

 Source Server         : bin
 Source Server Type    : MySQL
 Source Server Version : 80029
 Source Host           : localhost:3306
 Source Schema         : db

 Target Server Type    : MySQL
 Target Server Version : 80029
 File Encoding         : 65001

 Date: 28/06/2022 12:41:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for administrator
-- ----------------------------
DROP TABLE IF EXISTS `administrator`;
CREATE TABLE `administrator` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of administrator
-- ----------------------------
BEGIN;
INSERT INTO `administrator` (`id`, `username`, `password`) VALUES (1, 'bin', '123');
COMMIT;

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `id` int NOT NULL AUTO_INCREMENT,
  `bookname` varchar(20) DEFAULT NULL,
  `PressName` varchar(20) DEFAULT '未知出版社',
  `price` double(8,2) DEFAULT NULL,
  `state` int DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of book
-- ----------------------------
BEGIN;
INSERT INTO `book` (`id`, `bookname`, `PressName`, `price`, `state`) VALUES (3, '梦的解析', '未知', 123.00, 0);
INSERT INTO `book` (`id`, `bookname`, `PressName`, `price`, `state`) VALUES (5, '从你的全世界路过', '213', 123.00, 1);
INSERT INTO `book` (`id`, `bookname`, `PressName`, `price`, `state`) VALUES (6, '易经', '未知', 32.80, 1);
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `E_mail` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` (`id`, `username`, `password`, `E_mail`) VALUES (6, '231777', '123', '23123221@qq.com');
INSERT INTO `user` (`id`, `username`, `password`, `E_mail`) VALUES (7, '333', '333', '213334@qq.com');
INSERT INTO `user` (`id`, `username`, `password`, `E_mail`) VALUES (8, '111', '111', '111111@qq.com');
INSERT INTO `user` (`id`, `username`, `password`, `E_mail`) VALUES (9, '333', '333', '333333@qq.com');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
