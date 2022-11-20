/*
SQLyog Ultimate v13.1.1 (64 bit)
MySQL - 5.7.36 : Database - financialsystem
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`financialsystem` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `financialsystem`;

/*Table structure for table `accelist` */

DROP TABLE IF EXISTS `accelist`;

CREATE TABLE `accelist` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` text,
  `detailsdescript` text,
  `filepath` text,
  `filesize` varchar(20) DEFAULT NULL,
  `languages` varchar(50) DEFAULT NULL,
  `author` varchar(50) DEFAULT NULL,
  `uid` int(11) DEFAULT NULL,
  `createtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `accelist` */

insert  into `accelist`(`id`,`title`,`detailsdescript`,`filepath`,`filesize`,`languages`,`author`,`uid`,`createtime`) values 
(1,'title','desc','https://propertyinfoimage.oss-cn-hangzhou.aliyuncs.com/778ac4b1-72d7-4761-b8a1-0a800556d309/Financial Platform.pdf','419KB','english','author',3,'2022-10-21 11:40:56'),
(2,'test','desc','https://propertyinfoimage.oss-cn-hangzhou.aliyuncs.com/4613229e-7f82-4283-99a4-21ca44578b6e/Financial Platform.pdf','419KB','english','auto',3,'2022-10-22 06:44:12');

/*Table structure for table `historicalrecord` */

DROP TABLE IF EXISTS `historicalrecord`;

CREATE TABLE `historicalrecord` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL,
  `accelistid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `historicalrecord` */

insert  into `historicalrecord`(`id`,`uid`,`accelistid`) values 
(1,3,1),
(2,3,2);

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(20) DEFAULT NULL,
  `PASSWORD` varchar(50) DEFAULT NULL,
  `NAME` varchar(20) DEFAULT NULL,
  `integral` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `users` */

insert  into `users`(`id`,`email`,`PASSWORD`,`NAME`,`integral`) values 
(1,'1907747625@qq.com','123456','adminart',1),
(2,'2278951@qq.com','787878','oppo',0),
(3,'m13261223365@163.com','123456','admin11',2);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
