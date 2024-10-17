CREATE TABLE `t_users`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT,
    `username` varchar(255) DEFAULT NULL,
    `password`  varchar(255),
    `name`    varchar(255),
    PRIMARY KEY (`id`)
);