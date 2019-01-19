package huya.likai.Service;

import huya.likai.Entity.Page;
import huya.likai.Common.common;
import huya.likai.Entity.log;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;


@Service
public class readFileService {


    public Page readToString(MultipartFile multipartFile) {
        try {
            this.getInfo(multipartFile.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        Page<log> page = new Page<>(1,20,common.logResponses.getLogList().size());
        page.setList(common.logResponses.getLogList());

        return page;

    }

    /**
     * 截取日志中的信息，并且解析归类
     * @param inputStream
     * @return
     */
    public static void getInfo(InputStream inputStream){
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        List<log> logs = new ArrayList<>();
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                log log = new log();
                //定义ip
                log.setIp(line.substring(0,line.indexOf(" ")));

                //定义时间
                String time = line.substring(line.indexOf("[")+1,line.indexOf("]"));
                Date date = common.formatter.parse(time);
                log.setInterviewTime(common.df.format(date));

                String path = line.substring(line.indexOf("] \"")+3,line.indexOf("\" "));

                //定义请求方法和url
                if(line.contains("GET")||line.contains("POST")||line.contains("HEAD")||line.contains("OPTIONS")||line.contains("DELETE")||line.contains("TRACE")||line.contains("CONNECT")||line.contains("PUT")){
                    //System.out.println(path);
                    log.setRequestMethod(path.substring(0,(path.indexOf(" "))));
                    log.setUrl(path.substring(path.indexOf(" "),path.lastIndexOf(" ")));
                }else {
                    log.setRequestMethod(null);
                    log.setUrl(null);
                }

                //状态码
                String code = line.substring(line.indexOf(path)+path.length()+2,line.indexOf(path)+path.length()+5);
                log.setStatusCode(code);

                //浏览器agent
                if(line.contains("\"-\"")){
                    String temp = line.substring(line.indexOf("\"-\"")+5);
                    String browse = temp.substring(0,temp.indexOf("\""));
                    log.setAgent(browse);
                }else {
                    log.setAgent(null);
                }


                //消耗时间
                if(line.contains(", ")){
                    String match = "^\\d{1}$";
                    String pre = line.substring(line.lastIndexOf(",")-1,line.lastIndexOf(","));
                    if(Pattern.matches(match,pre)){
                        String consuming = line.substring(line.lastIndexOf(",")-11,line.lastIndexOf(",")-6);
                        log.setTimeConsuming(consuming);
                    }
                }

                logs.add(log);

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        common.logResponses.setLogList(logs);

    }

}
