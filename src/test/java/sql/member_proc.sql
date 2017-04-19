-----------------------------------------------------------------------
-- create procedure
-----------------------------------------------------------------------

DELIMITER //

CREATE PROCEDURE proc_multiply (INOUT inParam INT, INOUT outParam INT)
BEGIN
	SET outParam = inParam * 2;
END //

-----------------------------------------------------------------------
-- test procedure
-----------------------------------------------------------------------

SET @inParam = 10;
SET @outParam = 0;
CALL proc_multiply(@inParam, @outParam);
SELECT @inParam, @outParam;


-----------------------------------------------------------------------
-- create procedure
-----------------------------------------------------------------------

DELIMITER //
CREATE PROCEDURE proc_member_select_by_id (IN _id INT)
BEGIN
  SELECT * FROM member WHERE id = _id;
END //
DELIMITER ;

