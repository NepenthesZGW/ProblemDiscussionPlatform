
-- drop table if exists common_sequence;

create table common_sequence(  -- 创建序列表
   name varchar(20) not null primary key,
   current_value int not null default 0,
   increment int not null default 1,
   max_value int not null default 99999999,  -- 最大值
   initial_value int  not null default 0, -- 初始值，当当前值大于最大值，回到初始值
   key (name)
);

-- 为了解决开启biglog后 function不支持 Contain sql类型
set global log_bin_trust_function_creators=TRUE;

-- drop function if exists nextval;

CREATE FUNCTION nextval (seq_name VARCHAR(20))
RETURNS BIGINT
CONTAINS SQL
BEGIN
   UPDATE common_sequence
   SET current_value = current_value + increment
   WHERE name = seq_name;
   RETURN currval(seq_name);
END;

-- drop function if exists currval;
CREATE FUNCTION currval (seq_name VARCHAR(20))
RETURNS INTEGER
CONTAINS SQL
BEGIN
  SELECT current_value,max_value,initial_value INTO @current_value, @max_value, @initial_value
  FROM common_sequence
  WHERE name = seq_name;
  if(@current_value>@max_value) then
     UPDATE sequence
     SET current_value = initial_value
     WHERE name = seq_name;
     set @current_value=@initial_value;
  end if;
  RETURN @current_value;
END;

-- 添加一条默认数据
insert into common_sequence(name) values('resource_seq')

-- 查询所有序列
SELECT * from common_sequence;
-- 使用序列
SELECT nextval('resource_seq');
