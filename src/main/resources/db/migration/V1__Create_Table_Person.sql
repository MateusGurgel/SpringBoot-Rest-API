CREATE TABLE `people` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(80) DEFAULT NULL,
  `first_name` varchar(80) NOT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `last_name` varchar(80) NOT NULL,
  PRIMARY KEY (`id`)
);