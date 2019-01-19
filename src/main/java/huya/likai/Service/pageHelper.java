package huya.likai.Service;

import huya.likai.Entity.Page;
import org.springframework.stereotype.Service;

@Service
public class pageHelper<T> extends Page{
    private Page page = new Page();

    public Page page(int pageNum, int pageSize, int totalRecord){
        page.setPageNum(pageNum);
        page.setPageSize(pageSize);
        page.setTotalRecord(totalRecord);
        //totalPage 总页数
        if(totalRecord%pageSize==0){
            //说明整除，正好每页显示pageSize条数据，没有多余一页要显示少于pageSize条数据的
            page.setTotalPage(totalRecord / pageSize);
        }else{
            //不整除，就要在加一页，来显示多余的数据。
            page.setTotalPage(totalRecord / pageSize+1);;
        }
        //开始索引
        page.setStartIndex((pageNum-1)*pageSize);
        //显示5页，这里自己可以设置，想显示几页就自己通过下面算法修改
        page.setStart(1);
        page.setEnd(5);
        //显示页数的算法

        if(page.getTotalPage() <=5){
            //总页数都小于5，那么end就为总页数的值了。
            page.setEnd(page.getTotalPage());
        }else{
            //总页数大于5，那么就要根据当前是第几页，来判断start和end为多少了，
            page.setStart(pageNum-2);
            page.setEnd(pageNum+2);

            if(page.getStart() < 0){
                //比如当前页是第1页，或者第2页，那么就不如和这个规则，
                page.setStart(1);
                page.setEnd(5);
            }
            if(page.getEnd() > page.getTotalPage()){
                //比如当前页是倒数第2页或者最后一页，也同样不符合上面这个规则
                page.setEnd(page.getTotalPage());
                page.setStart(page.getEnd()-5);
            }
        }
        return page;
    }
}
