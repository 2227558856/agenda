package org.project.agenda.role;

import cn.fabrice.jfinal.annotation.ValidateParam;
import com.jfinal.aop.Clear;
import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;
import com.jfinal.core.Path;
import org.project.agenda.common.interceptor.AuthInterceptor;

/**
 * @author Administrator
 */
@Path("/role")
@ValidateParam
@Clear({AuthInterceptor.class})
public class RoleController extends Controller {
    @Inject
    RoleService roleService;

    public void addRole(String name){
        renderJson(roleService.addRole(name));
    }
}
