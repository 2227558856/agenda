package org.project.agenda.common;

import cn.fabrice.common.constant.BaseConstants;
import cn.fabrice.jfinal.ext.cros.interceptor.CrossInterceptor;
import cn.fabrice.jfinal.ext.render.MyRenderFactory;
import cn.fabrice.jfinal.interceptor.ParaValidateInterceptor;
import cn.fabrice.jfinal.plugin.ValidationPlugin;
import cn.fabrice.kit.json.FJsonFactory;
import com.jfinal.config.*;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.ehcache.EhCachePlugin;
import com.jfinal.server.undertow.UndertowServer;
import com.jfinal.template.Engine;
import org.project.agenda.common.config.ModuleConfig;
import org.project.agenda.common.interceptor.AuthInterceptor;
import org.project.agenda.user.constant.UserConstants;

/**
 * @author fye
 * @email fh941005@163.com
 * @date 2019-09-28 12:01
 * @description 应用配置启动类
 */
public class App extends JFinalConfig {
    private ModuleConfig moduleConfig;

    @Override
    public void configConstant(Constants me) {
        PropKit.use("base_config.properties");
        //设置开发模式
        me.setDevMode(PropKit.getBoolean("devMode"));
        //设置日志
        me.setToSlf4jLogFactory();
        //设置render工厂类
        me.setRenderFactory(new MyRenderFactory());
        //设置json
        me.setJsonFactory(new FJsonFactory());
        //设置允许AOP注入
        me.setInjectDependency(true);
        UserConstants.Result.init();
    }

    @Override
    public void configRoute(Routes me) {
        //扫描路由
        me.scan("org.project.agenda");
    }

    @Override
    public void configEngine(Engine me) {
    }

    @Override
    public void configPlugin(Plugins me) {
        //配置数据库插件
        moduleConfig = new ModuleConfig();
        moduleConfig.setDbPlugin(me);
        //添加规则校验插件
        me.add(new ValidationPlugin());
        //增加缓存插件
        me.add(new EhCachePlugin());
    }

    @Override
    public void configInterceptor(Interceptors me) {
        //添加跨越解决方案
        me.add(new CrossInterceptor(BaseConstants.TOKEN_NAME, true));
        //全局认证拦截器类
        me.add(new AuthInterceptor());
        //参数校验拦截器
        me.add(new ParaValidateInterceptor());
    }

    @Override
    public void configHandler(Handlers me) {
//        me.add(DruidKit.getDruidStatViewHandler()); // druid 统计页面功能
    }

    @Override
    public void onStart() {
        System.out.println("app starting...");
        // 让 druid 允许在 sql 中使用 union
        // https://github.com/alibaba/druid/wiki/%E9%85%8D%E7%BD%AE-wallfilter
        moduleConfig.getWallFilter().getConfig().setSelectUnionCheck(false);
    }

    @Override
    public void onStop() {
        System.out.println("app stopping...");
    }

    public static void main(String[] args) {
        UndertowServer.start(App.class);
    }
}
