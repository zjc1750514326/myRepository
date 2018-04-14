package controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import empty.Employee;
import service.EmployeeService;

/**
 * 注册控制器
 * @author zjc
 *
 */
@Controller
@RequestMapping("sys")
public class RegisterController {
	
	@Autowired
	private EmployeeService employeeService;

	/**
	 * 进入注册页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("registerUI")
	public ModelAndView registerUI() throws Exception{
		System.out.println("注册页面！！！");
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("WEB-INF/sys/register");
		
		return modelAndView;
	}
	
	/**
	 * 注册
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("register")
	public ModelAndView register(HttpServletRequest request , Employee employee) throws Exception {
		
		ModelAndView modelAndView =  new ModelAndView();
		
		if(employee.getEmpname()==null || employee.getEmpps()==null) {
			request.setAttribute("error2", "账号或者密码不能为空!");
			modelAndView.setViewName("register");
			return modelAndView;
		}
		
		
		
		String verifyCode = request.getParameter("verifyCode");
		
		String code = request.getSession().getAttribute("uri").toString();
       if (verifyCode.equalsIgnoreCase(code)) {
            //验证码正确
    	   request.getSession().setAttribute("employee", employee);

		   Employee exit = employeeService.select(employee);

		   if(exit != null) {
	   			modelAndView.setViewName("login");
	   		}else {
	   			request.setAttribute("error1", "账号已经存在!");
	   			modelAndView.setViewName("register");
	   		}
    	   
        } else {
        	request.getSession().setAttribute("error2", "验证码错误");
            //如果验证码错误了，让session过期，重定向到登录页面
    		//session.invalidate();
        	modelAndView.setViewName("register");
        	return modelAndView;
        }
		return modelAndView;
	}
}
