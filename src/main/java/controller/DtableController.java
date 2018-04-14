package controller;

import empty.Dtable;
import empty.Page;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.DtableService;

/**
 * Created by zjc on 2018/3/26.
 */
@Controller
@RequestMapping("sys/dtable")
public class DtableController{
    // 获取日志记录器logger,名字为本类类名
    private static Logger log = Logger.getLogger(EmployeeController.class);

    @Autowired
    private DtableService dtableService;

    /**
     * 动态查询餐桌列表
     * @param page
     * @return
     */
    @RequestMapping("selectPageUseDyc")
    @ResponseBody
    public Object selectPageUseDyc(Page<Dtable> page){
        if(page !=null){
            log.info("page不为空："+ page);
        }else {
            log.info("page为空:"+page);
            return -1;
        }
        Page<Dtable> p = dtableService.selectPageUseDyc(page);

        return p.getPageMap();
    }

    /**
     * 新增餐桌
     * @param dtable
     * @return
     */
    @RequestMapping("insert")
    @ResponseBody
    public Object insert(Dtable dtable){

        if(dtable != null){
            log.info("dtable不为空："+dtable);
        }else {
            log.info("dtable为空："+dtable);
            return -1;
        }
        int i = 0;
        try {
            i = dtableService.insert(dtable);
            if(i <= 0){
                log.info("新增失败!!!");
                return -1;
            }else {
                log.info("新增成功!!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    /**
     * 修改餐桌
     * @param dtable
     * @return
     */
    @RequestMapping("update")
    @ResponseBody
    public Object update(Dtable dtable){
        if (dtable!=null){
            log.info("dtable 不为空!!!");
        }else {
            log.info("dtable 为空:"+dtable);
            return -1;
        }
        int i = 0;

        try {
            i = dtableService.update(dtable);
            if(i <= 0){
                log.info("修改失败!!!");
                return -1;
            }else {
                log.info("修改成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    /**
     * 删除餐桌
     * @param pks
     * @return
     */
    @RequestMapping("deleteList")
    @ResponseBody
    public Object deleteList(String[] pks){
        if (pks!=null){
            log.info("pks不为空!!!");
        }else {
            log.info("pks为空:"+pks);
            return -1;
        }
        int i = 0;
        try {
            i = dtableService.deleteList(pks);
            if(i <= 0){
                log.info("删除失败!!!");
                return -1;
            }else {
                log.info("删除成功!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }
}