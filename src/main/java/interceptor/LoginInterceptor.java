package interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import empty.Employee;

/**
 * 登录拦截器
 * @author Administrator
 *
 */
public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		//获取url
		String url = request.getRequestURI();

		System.out.println("查看地址：1"+url);
		//这里公开地址是登陆提交的地址，放行登录地址和验证码获取地址
		if(url.indexOf("login.action")>=0 || url.indexOf("code.action")>=0 
				|| url.indexOf("registerUI.action")>0 || url.indexOf("register.action")>0){
			//如果进行登陆提交，放行
			return true;
		}
		
		
		//如果不是登录页面，判断session中是否为空
		HttpSession session = request.getSession();
		//从session中取出用户身份信息
		Employee username = (Employee) session.getAttribute("employee");
		
		if(username != null){
			//身份存在，放行
			return true;
		}
		
		//执行这里表示用户身份需要认证，跳转登陆页面
		request.getRequestDispatcher("/WEB-INF/sys/login.jsp").forward(request, response);
		return false;
	}
	
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
			throws Exception {
		
		
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exec)
			throws Exception {
		
		
	}

	

	

}
