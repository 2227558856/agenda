package org.project.agenda.agenda;

import cn.fabrice.common.pojo.BaseResult;
import cn.fabrice.common.pojo.DataResult;
import cn.fabrice.jfinal.service.BaseService;
import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.SqlPara;
import org.project.agenda.common.module.Agenda;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @author Administrator
 */
public class AgendaService extends BaseService<Agenda> {
    public AgendaService(){
        super("agenda.",Agenda.class,"agenda");
    }

    /**
     * 添加日程
     *
     * 校验日程是否存在冲突
     * @param agenda 日程信息
     * @return 返回添加结果
     */
    public BaseResult addAgenda(Agenda agenda,String remindTime){
        //1.是否提醒
        if(agenda.getIsRemind()==2){
            double addBegin=timeToNum(agenda.getBegin());
            double r=timeToNum(remindTime);
            //这里需要完成时间的减法:初步思路-将时间转换成秒数,用秒数相减后转时间??
            String remind= String.valueOf(addBegin-r);
            agenda.setRemind(remind);
            //2.提醒方式
            if(agenda.getWay()==1){
                //邮件
            }
            else if(agenda.getWay()==2){
                //短信
            }
        }

        if(!isConflict(agenda)){
            agenda.save();
            return BaseResult.ok();
        }
        return BaseResult.fail();
    }

    public DataResult getUserAgenda(long userId){
        Kv cond=Kv.by("user_id",userId);
        SqlPara sqlPara= Db.getSqlPara("agenda.getUserAgenda",cond);
        return DataResult.data(Db.find(sqlPara));
    }


    /**
     * 校验日程是否存在冲突
     * 根据某个人-某一学年-某一学期-某一周次-某一天的日程信息进行判断
     * 对于课表等多次添加,在add方法中进行处理,分解成单次添加后调用本方法
     * @param agenda
     * @return 返回是否冲突 -true存在冲突
     */
    public boolean isConflict(Agenda agenda){
        //获取某一天的日程信息
        Kv cond=Kv.by("user_id",agenda.getUserId()).set("year",agenda.getYear()).
                set("term",agenda.getTerm()).set("week",agenda.getWeek()).set("day",agenda.getDay());
        SqlPara sqlPara= Db.getSqlPara("agenda.getDayAgenda",cond);
        List<Record> dayAgenda=Db.find(sqlPara);
        //遍历日程信息,判断已存在的日程是否与即将添加的日程时间冲突
        for (Record oneAgenda : dayAgenda) {
            double beginTime = timeToNum(oneAgenda.getStr("begin"));
            double endTime = timeToNum(oneAgenda.getStr("end"));
            double addBegin = timeToNum(agenda.getBegin());
            double addEnd = timeToNum(agenda.getEnd());

            //冲突的情况
            //1.相交
            if (addBegin > beginTime && addBegin < endTime) {
                return true;
            } else if (addEnd > beginTime && addEnd < endTime) {
                return true;
            }
            //2.包含
            else if(addBegin<beginTime && addEnd>endTime) {
                return true;
            }
        }
        System.out.println("时间无冲突,可添加该日程");
        return false;
    }

    /**
     * 将数据库中存储的字符串日程时间转换为数字,便于时间的比较
     * 例:12:00,14:30可转换成12.00,14.30,直接通过比较大小即可完成时间冲突的判断
     * @param time
     * @return 返回转换后的数字时间
     */
    public double timeToNum(String time){
        String[] str = time.split(":");
        return Double.parseDouble(str[0])+Double.parseDouble(str[1]);
    }

    /**
     * 根据相关数据定位到某一天,建立日期时间+时间的时间戳
     * @param year
     * @param term
     * @param week
     * @param day
     * @param time
     * @return 返回时间戳
     */
    public Timestamp strToStamp(String year,String term,String week,String day,String time){
        return null;
    }

    /**
     * 根据用户id得到其填写的联系email,根据提醒时间来调用send方法
     */
    public void sendEmail(){

    }

    public void sendLetter(){

    }
}
