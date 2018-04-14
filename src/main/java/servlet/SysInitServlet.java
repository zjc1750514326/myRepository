package servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SysInitServlet
 */
public class SysInitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SysInitServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	@Override
	public void init() throws ServletException {
		Map<String,Object> sysParamMap = new HashMap<String,Object>();
		Map<String,Object> styleParamMap = new HashMap<String,Object>();
		styleParamMap.put("1", "川菜");
		styleParamMap.put("2", "闽菜");
		styleParamMap.put("3", "粤菜");
		styleParamMap.put("4", "浙菜");
		styleParamMap.put("5", "湘菜");
		styleParamMap.put("6", "徽菜");
		styleParamMap.put("7", "鲁菜");
		styleParamMap.put("8", "苏菜");
		
		sysParamMap.put("styleParamMap", styleParamMap);
		
		this.getServletContext().setAttribute("sysParam", sysParamMap);
		System.out.println("-------------------系统参数加载完成！--------------------");
	}

}
