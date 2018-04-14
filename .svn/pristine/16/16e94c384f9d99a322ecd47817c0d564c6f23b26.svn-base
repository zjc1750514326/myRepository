package controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("sys")
public class SysControl {

	// 获取日志记录器logger,名字为本类类名
	private static Logger log = Logger.getLogger(SysControl.class);

	/**
	 * 进入后台系统的主页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("main")
	public ModelAndView sysMain() throws Exception{
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("WEB-INF/sys/main/main");

		log.info("登录成功");
		
		return modelAndView;
	}
}
