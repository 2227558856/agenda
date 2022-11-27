package org.project.agenda.role;

import cn.fabrice.common.pojo.BaseResult;
import cn.fabrice.jfinal.service.BaseService;
import com.jfinal.kit.Kv;
import org.project.agenda.common.module.Role;
import org.project.agenda.common.module.User;
import org.project.agenda.role.constant.RoleConstants;

/**
 * @author Administrator
 *
 * 管理角色以及每个角色对应的功能权限(菜单)
 */
public class RoleService extends BaseService<Role> {
    public RoleService() {
        super("role.", Role.class, "role");
    }

    /**
     * 添加角色
     *
     * 根据角色名称设置新的角色
     * @param name 角色名称,例如赛事管理员,资料管理员等等
     * @return 返回添加结果
     */
    public BaseResult addRole(String name){
        Kv cond= Kv.by("name",name);
        Role result=get(cond,"getByName");
        if(result!=null){
            return BaseResult.res(RoleConstants.Result.ROLE_IS_EXIST);
        }
        Role role=new Role();
        role.setName(name);
        //角色创建时默认设置为非用户默认角色,状态正常
        role.setIsDefault(2);
        role.setStatus(1);
        if(role.save()){
            return BaseResult.ok();
        }
        return BaseResult.fail();
    }
}
