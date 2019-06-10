
CREATE DATABASE tasks_query CHARACTER SET utf8 COLLATE utf8_general_ci; 

create table tasks
(
`id` int(10) NOT NULL AUTO_INCREMENT,
`users_name` varchar(500) NOT NULL,
`users_email` varchar(256) NOT NULL,
`description` longtext,
`img` blob NOT NULL,
`name_img` varchar(500) NOT NULL,
`state` int(1) NOT NULL DEFAULT 0,
PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8;

