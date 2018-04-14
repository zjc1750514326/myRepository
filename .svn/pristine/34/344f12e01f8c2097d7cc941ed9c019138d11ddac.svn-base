package controller;

import empty.Page;
import empty.Style;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.StyleService;

/**
 * 菜系控制器
 * Created by zjc on 2018/3/25.
 */
@Controller()
@RequestMapping("sys/style")
public class StyleController {

    // 获取日志记录器logger,名字为本类类名
    private static Logger log = Logger.getLogger(StyleController.class);

    @Autowired
    private StyleService styleService;

    /**
     * 查询菜系
     * @param page
     * @return
     */
    @RequestMapping("selectPageUseDyc")
    @ResponseBody
    public Object selectPageUseDyc(Page<Style> page){
        if (page != null){
            log.info("page不为空!!!");
        }else {
            log.info("page为空!!!"+ page);
            return -1;
        }
        Page<Style> p = styleService.selectPageUseDyc(page);

        if (p.getPageMap()!=null){
            log.info("查询成功;"+p.getPageMap());
        }else {
            log.info("查询失败!!!");
            return -1;
        }
        return p.getPageMap();
    }

    /**
     * 新增菜系
     * @param style
     * @return
     */
    @RequestMapping("insert")
    @ResponseBody
    public Object insert(Style style){
        if (style!=null){
            log.info("style不为空!!!");
        }else {
            log.info("style为空:"+style);
            return -1;
        }
        int i=0;
        try {
            i = styleService.insert(style);
            if (i <= 0){
                log.info("插入失败!!!");
                return -1;
            }else {
                log.info("插入成功!!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }
    /**
     * 修改菜系
     * @param style
     * @return
     */
    @RequestMapping("update")
    @ResponseBody
    public Object update(Style style){
        if (style!=null){
            log.info("style不为空!!!");
        }else {
            log.info("style为空:"+style);
            return -1;
        }
        int i = 0;
        try {
            i = styleService.update(style);
            if (i <= 0){
                log.info("修改失败!!!");
                return -1;
            }else {
                log.info("修改成功!!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    /**
     * 删除菜系
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
            i = styleService.deleteList(pks);
            if (i <= 0){
                log.info("删除失败!!!");
                return -1;
            }else {
                log.info("删除成功!!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }
}