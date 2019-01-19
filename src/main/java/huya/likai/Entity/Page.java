package huya.likai.Entity;


import java.util.ArrayList;
import java.util.List;

public class Page<T> {
    //已知数据
    private int pageNum;    //当前页,从请求那边传过来。
    private int pageSize;    //每页显示的数据条数。
    private int totalRecord;    //总的记录条数。查询数据库得到的数据
    private int totalPage;    //总页数，通过totalRecord和pageSize计算可以得来
    private int startIndex;    //起始索引
    public List<T> list;        //返回list
    private int start;          //起始页
    private int end;            //尾页

    public Page() {
    }

    public Page(int pageNum, int pageSize, int totalRecord) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.totalRecord = totalRecord;
    }
    //get、set方法。
    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        List<T> temp = new ArrayList<>();
        for(int i = this.startIndex;i<this.startIndex+20;i++){
            temp.add(list.get(i));
        }
        this.list = temp;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }


}