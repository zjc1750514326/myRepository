package controller;

import empty.Employee;
import empty.Page;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.EmployeeService;

/**
 * 用户列表控制器
 * @author zjc
 *
 */
@Controller
@RequestMapping("sys/employee")
public class EmployeeController {
    // 获取日志记录器logger,名字为本类类名
    private static Logger log = Logger.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;

    /**
     * 多条件查询分页
     * @param page  分页对象
     * @param employee  员工实体类
     * @return
     */
    @RequestMapping("selectPageUseDyc")
    @ResponseBody  // 如果返回json数据需要这个注释
    public Object selectPageUseDyc(Page<Employee> page,Employee employee){
        if(employee.getEmpname()!=null || employee.getEmpjob()!=null){
            log.info("员工姓名："+employee.getEmpname()+" ; 员工职位："+employee.getEmpjob());
        }

        // 1、 将需要条件查询的参数设置进去
        page.setParamEntity(employee);

        // 2、封装page对象
        Page<Employee> p = employeeService.selectPageUseDyc(page);
        if (p == null){
            log.debug("page对象查询异常");
        }else{
            log.debug("查询成功");
        }
        return p.getPageMap();
    }

    /**
     * 添加员工
     * @param employee
     * @return
     */
    @RequestMapping("insert")
    @ResponseBody
    public Object insert(Employee employee){

        if (employee != null){
            log.info("empolyee 不为空!");
        }else {
            log.info("employee: "+employee);
            return -1;
        }
        int i = 0;
        try {
            i = employeeService.insert(employee);
            if (i<=0){
                log.info("员工插入失败!!!"+i);
                return -1;
            }else {
                log.info("员工插入成功!!!"+i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    /**
     * 根据主键批量删除员工
     * @param pks 主键
     * @return
     */
    @RequestMapping("deleteList")
    @ResponseBody
    public Object deleteList(String[] pks){
        if (pks != null){
            log.info("删除的员工id不为空："+ pks.toString());
        }else {
            log.info("删除的员工id为空："+pks);
            return -1;
        }
        int i = 0;
        try {
            i = employeeService.deleteList(pks);
            if ( i<= 0 ){
                log.info("删除失败!!!"+i);
                return -1;
            }else {

                log.info("删除成功!!!"+i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1;
    }

    /**
     * 修改员工信息
     * @param employee
     * @return
     */
    @RequestMapping("update")
    @ResponseBody
    public Object update(Employee employee){
        if(employee != null){
            log.info("employee 不为空.");
        }else {
            log.info("employee :" +employee);
            return -1;
        }
        int i = 0;
        try {
            i = employeeService.update(employee);
            if(i<=0){
                log.info("修改失败!"+i);
                return -1;
            }else {
                log.info("修改成功!"+i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }
}