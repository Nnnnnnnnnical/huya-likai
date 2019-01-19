package huya.likai.Service;

import huya.likai.Common.common;
import huya.likai.Entity.code;
import huya.likai.Entity.log;
import huya.likai.Param.statisticsResponses;
import huya.likai.Entity.time;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;


@Service
public class statisticsService {

    public statisticsResponses statistics() throws ParseException {

        statisticsResponses statisticsResponses = new statisticsResponses();
        code code = new code();
        time time = new time();
        for(log log:common.logResponses.getLogList()){
            //统计状态码
            if(Integer.parseInt(log.getStatusCode())>=100&&Integer.parseInt(log.getStatusCode())<=199){
                code.setCode1(code.getCode1()+1);
            }else if(Integer.parseInt(log.getStatusCode())>=200&&Integer.parseInt(log.getStatusCode())<=299){
                code.setCode2(code.getCode2()+1);
            }else if(Integer.parseInt(log.getStatusCode())>=300&&Integer.parseInt(log.getStatusCode())<=399){
                code.setCode3(code.getCode3()+1);
            }else if(Integer.parseInt(log.getStatusCode())>=400&&Integer.parseInt(log.getStatusCode())<=499){
                code.setCode4(code.getCode4()+1);
            }else if(Integer.parseInt(log.getStatusCode())>=500&&Integer.parseInt(log.getStatusCode())<=599){
                code.setCode5(code.getCode5()+1);
            }

            //统计时间
            long dTime = new Date().getTime()-common.df.parse(log.getInterviewTime()).getTime();
            if(dTime<=86400000){
                time.setTime1(time.getTime1()+1);
            }else if(dTime>86400000&&dTime<604800000){
                time.setTime2(time.getTime2()+1);
            }else{
                time.setTime3(time.getTime3()+1);
            }

        }

        statisticsResponses.setCode(code);
        statisticsResponses.setTime(time);
        return statisticsResponses;
    }
}
