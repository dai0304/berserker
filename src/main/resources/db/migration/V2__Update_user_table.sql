ALTER TABLE users
	ADD COLUMN `enabled` TINYINT(1) NOT NULL DEFAULT true AFTER `password`,
	ADD COLUMN `authority` VARCHAR(128) DEFAULT 'ROLE_USER' AFTER `enabled`;

UPDATE users
SET password = '$2a$10$sZD7w5TBbYX81BizHA4CTO8fpOXNP3vNxVJK7A2UY.Vzj60WB/NU.'
WHERE username = 'miyamoto';

UPDATE users
SET password = '$2a$10$4vlusNdHDMXY/fxDraVqcutoc4ryiwngZLS0ZLmRsDbIXfIXMUrRW', authority = 'ROLE_ADMIN'
WHERE username = 'yokota';
