
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

INSERT INTO `tasks_query`.`tasks` (`users_name`, `users_email`, `description`, `img`) 
VALUES ('test', 'test@gmail.com', 'description', ?)
,('test1', 'test1@gmail.com', 'description1', ?)
,('test2', 'test2@gmail.com', 'description2', ?)
,('test3', 'test3@gmail.com', 'description3', ?)
,('test4', 'test4@gmail.com', 'description4', ?);
