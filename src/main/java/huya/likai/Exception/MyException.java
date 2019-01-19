package huya.likai.Exception;


import org.springframework.stereotype.Service;

@Service
public class MyException extends Exception {

    public void noData() throws Exception {
        throw new Exception("暂无数据，请先上传文件");
    }

}
