package org.project.agenda.user.role;

import cn.fabrice.common.pojo.BaseResult;
import cn.fabrice.jfinal.service.BaseService;
import org.project.agenda.common.module.UserRole;

import java.math.BigInteger;

/**
 * @author Administrator
 * 处理用户与角色之间的关系
 */
public class UserRoleService extends BaseService<UserRole> {

    /**
     * 添加用户与角色之间的关联
     *
     * @param userId 用户id
     * @param roleId 角色id
     * @return 返回添加结果
     */
    public boolean addUserRole(long userId, long roleId){
        UserRole userRole=new UserRole();
        userRole.setUserId(BigInteger.valueOf(userId));
        userRole.setRoleId(BigInteger.valueOf(roleId));
        return userRole.save();
    }
}
