package org.project.agenda.agenda;

import cn.fabrice.common.constant.BaseConstants;
import cn.fabrice.common.pojo.DataResult;
import cn.fabrice.jfinal.annotation.ValidateParam;
import com.jfinal.aop.Clear;
import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;
import com.jfinal.core.Path;
import com.jfinal.core.paragetter.Para;
import org.project.agenda.common.interceptor.AuthInterceptor;
import org.project.agenda.common.module.Agenda;
import org.project.agenda.common.module.UserSession;
import org.project.agenda.user.session.SessionService;

import java.math.BigInteger;


/**
 * @author Administrator
 */
@Path("/agenda")
@ValidateParam
public class AgendaController extends Controller {
    @Inject
    AgendaService agendaService;
    @Inject
    SessionService sessionService;


    /**
     * @ Para("") 自动接参:将传来的参数中,Agenda对应的字段都存入agenda
     * @param agenda
     * @param remindTime 用户在日程添加选择页面时填写的提前多少分钟提醒该日程,可传可不传(根据is_remind的选择)
     */
    public void addAgenda(@Para("") Agenda agenda,String remindTime){
        /*
         * loginId根据token获取
         * 1.token在全局登录拦截器AuthInterceptor.class中的intercept方法中进行查询得到session(token=session_id)
         * 2.将session中的信息通过controller.setAttr(BaseConstants.ACCOUNT_ID, userSession.getUserId().longValue());放到controller中
         * 3.直接用getAttr(BaseConstants.ACCOUNT_ID);就可以得到其中的user_id
         */
        long loginId = getAttr(BaseConstants.ACCOUNT_ID);
        agenda.setUserId(BigInteger.valueOf(loginId));
        renderJson(agendaService.addAgenda(agenda,remindTime));
    }

    public void getUserAgenda(){
        long loginId = getAttr(BaseConstants.ACCOUNT_ID);
        renderJson(agendaService.getUserAgenda(loginId));
    }
}
