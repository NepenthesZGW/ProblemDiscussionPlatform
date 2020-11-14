-- mysql 生成唯一ID
SELECT CONCAT(DATE_FORMAT(SYSDATE(),'%Y%m%d%H%i%s') ,right( concat('0000000' ,CAST(nextval('resource_seq') AS CHAR)),8)) as NEXTVAL
