CREATE
DATABASE  IF NOT EXISTS `school_directory`;
USE
`school_directory`;



DROP TABLE IF EXISTS `enrollment`;
DROP TABLE IF EXISTS `course`;
DROP TABLE IF EXISTS `student`;
DROP TABLE IF EXISTS `teacher`;
DROP TABLE IF EXISTS `admin`;
DROP TABLE IF EXISTS `member`;



CREATE TABLE `member`
(
    `email`    varchar(255) NOT NULL,
    `password` char(68)     NOT NULL,
    `role`     varchar(50)  NOT NULL,
    `active`   tinyint      NOT NULL DEFAULT 1,
    PRIMARY KEY (`email`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;



CREATE TABLE `student`
(
    `id`         int AUTO_INCREMENT NOT NULL,
    `first_name` varchar(45)  NOT NULL,
    `last_name`  varchar(45)  NOT NULL,
    `year`       int          NOT NULL,
    `email`      varchar(255) NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `student_email` FOREIGN KEY (`email`) REFERENCES `member` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



CREATE TABLE `teacher`
(
    `id`         int AUTO_INCREMENT NOT NULL,
    `first_name` varchar(45)  NOT NULL,
    `last_name`  varchar(45)  NOT NULL,
    `email`      varchar(255) NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `teacher_email` FOREIGN KEY (`email`) REFERENCES `member` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



CREATE TABLE `admin`
(
    `id`         int AUTO_INCREMENT NOT NULL,
    `first_name` varchar(45)  NOT NULL,
    `last_name`  varchar(45)  NOT NULL,
    `email`      varchar(255) NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `admin_email` FOREIGN KEY (`email`) REFERENCES `member` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



CREATE TABLE `course`
(
    `code`       varchar(45) NOT NULL,
    `name`       varchar(45) NOT NULL,
    `year`       int         NOT NULL,
    `teacher_id` int DEFAULT NULL,

    PRIMARY KEY (`code`),
    CONSTRAINT `c_teacher_id` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`)

) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `enrollment`
(
    `id`         int AUTO_INCREMENT NOT NULL,
    `code`       varchar(45) NOT NULL,
    `student_id` int         NOT NULL,
    `grade`      int DEFAULT NULL,

    PRIMARY KEY (`id`),

    CONSTRAINT `stud_id` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`),
    CONSTRAINT `s_course_code` FOREIGN KEY (`code`) REFERENCES `course` (`code`)

) ENGINE=InnoDB DEFAULT CHARSET=latin1;







