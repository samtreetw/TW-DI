SELECT 'drop table ' || name || ';'
FROM sqlite_master
WHERE type = 'table';