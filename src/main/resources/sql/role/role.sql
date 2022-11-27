#sql("getByName")
select id
from role
where name = #para(name) and is_deleted = 0
    #end