SET @booking_review_booking_id_exists := (
    SELECT COUNT(*)
    FROM information_schema.COLUMNS
    WHERE TABLE_SCHEMA = DATABASE()
      AND TABLE_NAME = 'booking_review'
      AND COLUMN_NAME = 'booking_id'
);

SET @sql := IF(@booking_review_booking_id_exists = 0,
    'ALTER TABLE booking_review ADD COLUMN booking_id BIGINT NULL',
    'SELECT 1'
);
PREPARE stmt FROM @sql;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;

UPDATE booking_review br
INNER JOIN booking b ON b.review_id = br.id
SET br.booking_id = b.id
WHERE br.booking_id IS NULL;

SET @booking_review_fk_exists := (
    SELECT COUNT(*)
    FROM information_schema.TABLE_CONSTRAINTS
    WHERE CONSTRAINT_SCHEMA = DATABASE()
      AND TABLE_NAME = 'booking_review'
      AND CONSTRAINT_NAME = 'FK_BOOKING_REVIEW_ON_BOOKING'
      AND CONSTRAINT_TYPE = 'FOREIGN KEY'
);

SET @sql := IF(@booking_review_fk_exists = 0,
    'ALTER TABLE booking_review ADD CONSTRAINT FK_BOOKING_REVIEW_ON_BOOKING FOREIGN KEY (booking_id) REFERENCES booking (id)',
    'SELECT 1'
);
PREPARE stmt FROM @sql;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;

SET @booking_review_fk_name := (
    SELECT CONSTRAINT_NAME
    FROM information_schema.KEY_COLUMN_USAGE
    WHERE TABLE_SCHEMA = DATABASE()
      AND TABLE_NAME = 'booking'
      AND COLUMN_NAME = 'review_id'
      AND REFERENCED_TABLE_NAME IS NOT NULL
    LIMIT 1
);

SET @sql := IF(@booking_review_fk_name IS NOT NULL,
    CONCAT('ALTER TABLE booking DROP FOREIGN KEY `', @booking_review_fk_name, '`'),
    'SELECT 1'
);
PREPARE stmt FROM @sql;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;

SET @booking_review_column_exists := (
    SELECT COUNT(*)
    FROM information_schema.COLUMNS
    WHERE TABLE_SCHEMA = DATABASE()
      AND TABLE_NAME = 'booking'
      AND COLUMN_NAME = 'review_id'
);

SET @sql := IF(@booking_review_column_exists > 0,
    'ALTER TABLE booking DROP COLUMN review_id',
    'SELECT 1'
);
PREPARE stmt FROM @sql;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;
