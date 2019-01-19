package huya.likai.Controller;

import huya.likai.Entity.Page;
import huya.likai.Common.common;
import huya.likai.Entity.log;
import huya.likai.Param.statisticsResponses;
import huya.likai.Service.pageHelper;
import huya.likai.Service.readFileService;
import huya.likai.Service.statisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
public class logController {

    @Autowired
    private readFileService readFileService;

    @Autowired
    private statisticsService statisticsService;

    @RequestMapping(value = "readFile")
    public Page read(@RequestParam MultipartFile file){
        return readFileService.readToString(file);

    }

    @RequestMapping(value = "readFile/{page}")
    public Page page(@PathVariable("page") int page){
        pageHelper pageHelper = new pageHelper();
        Page<log> logPage = pageHelper.page(page,20,common.logResponses.getLogList().size());
        logPage.setList(common.logResponses.getLogList());
        return logPage;
    }

    @RequestMapping(value = "sort")
    public statisticsResponses read() throws Exception {
        return statisticsService.statistics();
    }


}
