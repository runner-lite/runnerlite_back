USE runner_lite;

CREATE TEMPORARY TABLE IF NOT EXISTS temp
SELECT ID, time(RESULT) AS t, (SELECT (hour(t) * 3600 + minute(t) * 60 + second(t))) AS RESULT FROM running_results;

ALTER TABLE running_results
CHANGE COLUMN RESULT RESULT DATETIME NULL COMMENT 'Результат забега' ;

UPDATE running_results SET RESULT = null;

ALTER TABLE running_results
CHANGE COLUMN RESULT RESULT INT NULL COMMENT 'Результат забега в секундах' ;

UPDATE running_results
JOIN temp ON running_results.ID = temp.ID
SET running_results.RESULT = temp.RESULT;

ALTER TABLE running_results
CHANGE COLUMN RESULT RESULT INT NOT NULL COMMENT 'Результат забега в секундах' ;