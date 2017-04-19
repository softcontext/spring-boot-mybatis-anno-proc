DROP TABLE IF EXISTS member;

CREATE TABLE IF NOT EXISTS `member` (
	`id` INT(9) NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(40) NOT NULL,
	`active` BOOLEAN NOT NULL,
	PRIMARY KEY (`id`)
) ENGINE=InnoDB;

------------------------------------------------------------

-- This is not used.

--DROP TABLE IF EXISTS BOOKS_SELL;
--DROP TABLE IF EXISTS BOOKS;
--
--CREATE TABLE BOOKS (
--	bookID CHAR(5) NOT NULL,
--	bookName VARCHAR(20) NOT NULL,
--	bookOriginPrice DOUBLE NOT NULL,
--	bookType VARCHAR(10) NOT NULL,
--	PRIMARY KEY(bookID)
--) ENGINE=InnoDB;
--
--CREATE TABLE BOOKS_SELL (
--	bookID CHAR(5) NOT NULL,
--	bookSellPrice DOUBLE NOT NULL,
--	bookType VARCHAR(10) NOT NULL,
--	PRIMARY KEY(bookID)
--) ENGINE=InnoDB;