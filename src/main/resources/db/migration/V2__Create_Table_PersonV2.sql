CREATE TABLE `people_v2` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(80) DEFAULT NULL,
  `first_name` varchar(80) NOT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `last_name` varchar(80) NOT NULL,
  `birth_date` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
);