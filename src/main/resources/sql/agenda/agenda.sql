#sql("getDayAgenda")
select name,begin,end
from agenda
where user_id=#para(user_id) and year=#(year) and term=#para(term) and week=#para(week) and day=#para(day) and is_deleted=0
#end


#sql("getUserAgenda")
select *
from agenda
where user_id=#para(user_id) and is_deleted=0
#end