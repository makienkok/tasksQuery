
CREATE DATABASE tasks_query CHARACTER SET utf8 COLLATE utf8_general_ci; 

create table tasks
(
`id` int(10) NOT NULL AUTO_INCREMENT,
`users_name` varchar(500) NOT NULL,
`users_email` varchar(256) NOT NULL,
`description` longtext,
`img` mediumblob NOT NULL,
PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8;


create table tasks
(
`id` int(10) NOT NULL AUTO_INCREMENT,
`users_name` varchar(500) NOT NULL,
`users_email` varchar(256) NOT NULL,
`description` longtext,
`img` mediumblob NOT NULL,
PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8;