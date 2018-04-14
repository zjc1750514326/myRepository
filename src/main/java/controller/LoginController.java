package controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import empty.Employee;
import service.EmployeeService;

/**
 * 登录控制器，餐厅内部员工登录后台
 * @author Administrator
 */
@Controller
@RequestMapping("sys")
public class LoginController {

	// 获取日志记录器logger,名字为本类类名
	private static Logger log = Logger.getLogger(LoginController.class);
	
	@Autowired
	private EmployeeService employeeService;
	
	@Resource
    HttpServletRequest servletRequest;

	@RequestMapping("login")
	public String login(HttpSession session,Employee employee)throws Exception{
		// 判断用户名或者
		if(employee.getEmpname()==null|| employee.getEmpps()==null) {
			servletRequest.getSession().setAttribute("error2", "用户名或者密码不能为空!");
	    	 return "redirect:/sys/main.action";
		}


		Employee employeeExist = employeeService.select(employee);

		if(employeeExist != null) {
			String verifyCode = servletRequest.getParameter("verifyCode");
			log.info("验证码 verifyCode:"+verifyCode);
			String code = session.getAttribute("code").toString();

			log.info("验证码code:"+code);
	       if (verifyCode.equalsIgnoreCase(code)) {
	            //验证码正确
	    	   servletRequest.getSession().setAttribute("employee", employee);
	    	   return "redirect:/sys/main.action";
	    	   
	        } else {
	            servletRequest.getSession().setAttribute("error1", "验证码错误");
	            //如果验证码错误了，让session过期，重定向到登录页面
	    		//session.invalidate();
	    		return "redirect:/sys/main.action";
	        }
		}else {
			servletRequest.getSession().setAttribute("error2", "用户名或者密码或者职位错误!");
			return "redirect:/sys/main.action";
		}
	}
	
	
	
	
	@RequestMapping("loginOut")
	public String loginOut(HttpSession session) throws Exception{
		//让session过期
		session.invalidate();
		
		return "redirect:/sys/main.action";
	}
	
}