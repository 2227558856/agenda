#--------------------用户模块相关SQL操作--------------------#
#session相关的sql操作
#namespace("session")
#include("user/session.sql")
#end

#用户相关的sql操作
#namespace("user")
#include("user/user.sql")
#end

#namespace("department")
#include("user/department.sql")
#end
#--------------------用户模块相关SQL操作--------------------#

#--------------------角色权限相关SQL操作--------------------#
#角色权限相关的sql操作
#namespace("role")
#include("role/role.sql")
#end
#--------------------角色权限相关SQL操作--------------------#

#--------------------日程相关SQL操作--------------------#
#日程相关的sql操作
#namespace("agenda")
#include("agenda/agenda.sql")
#end
#--------------------日程相关SQL操作--------------------#
