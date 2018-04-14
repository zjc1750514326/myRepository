package controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import empty.Food;
import empty.FoodCustom;
import empty.Page;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import service.FoodService;
import javax.servlet.http.HttpServletRequest;
/**
 * 菜单列表控制器
 * @author zjc
 *
 */
@Controller
@RequestMapping("sys/food")
public class FoodController {
	// 获取日志记录器logger,名字为本类类名
	private static Logger log = Logger.getLogger(FoodController.class);
	
	@Autowired
	private FoodService foodService;
	
	@RequestMapping("/insert")
	@ResponseBody     //如果返回json格式需要这个注解
	public Object insert(FoodCustom food,
						 MultipartFile foodPic//接收商品图片
			) {
		log.info("查看新增的数据："+food.toString());

//		image(foodPic,food);
		
		int i=0;
		
		try {
			i = foodService.insert(food);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}
	
	//根据主键批量删除菜单
	@RequestMapping("/deleteList")
	@ResponseBody     //如果返回json格式需要这个注解
	public Object deleteList(String[] pks) {
		
		System.out.println("pks="+pks);
		int i=0;
		
		try {
			i = foodService.deleteList(pks);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}
	
	//根据主键批量删除菜单
	@RequestMapping("/update")
	@ResponseBody     //如果返回json格式需要这个注解
	public Object update(FoodCustom food,MultipartFile foodPic,HttpServletRequest request) {
		image(foodPic,food,request);
		int i=0;
		try {
			i = foodService.update(food);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}
	
	
	//通过多条件查询分页
	@RequestMapping("/selectPageUseDyc")
	@ResponseBody     //如果返回json格式需要这个注解
	public Object selectPageUseDyc(Page<Food> page,FoodCustom food) throws Exception{

		//因为js中，不支持类名.属性，所以在参数中添加FoodCustom
		page.setParamEntity(food);
		
		//设置到service层中
		Page<Food> p = foodService.selectPageUseDyc(page);
		System.out.println("后台："+p.toString());

		return p.getPageMap();
	}

	/**
	 * 前台：前台首页进入菜单跳转的，默认为粤菜
	 * @param food
	 * @param page
	 * @param stylename
	 * @return
	 */
	@RequestMapping("/caidan")
	@ResponseBody
	public ModelAndView caidan(FoodCustom food,Page<Food> page,String stylename,String tableId,String orderId){

		log.info("stylename："+stylename+", tableid："+tableId+" ,orderID: "+orderId);

		food.setFoodname("%%");
		if(stylename != null || stylename !=""){
			food.setStylename("%"+stylename+"%");
		}else {
			food.setStylename("%%");
		}
		page.setPage(1);
		page.setRows(6);
		page.setParamEntity(food);
		Page<Food> p = foodService.selectPageUseDyc(page);
		System.out.println("p.getList():  --->"+p.getList()+"------"+p.getPageMap()+"start"+page.toString());

		ModelAndView view = new ModelAndView();
		view.addObject("foodList",p.getList());
		// 此处传入tableId是为了给后面的提交订单获取值
		view.addObject("tableId",tableId);
		view.addObject("orderId",orderId);

		// 给caidan.jsp页面分页使用
		// 当前页
		view.addObject("page",p.getPage());
		// 页大小
		int totalPage = p.getTotalRecord()%p.getRows()== 0 ? (p.getTotalRecord()/p.getRows()) : (p.getTotalRecord()/p.getRows()+1);
		log.info("totalPage:"+totalPage+"; p.getTotalRecord()/p.getRows() = "+p.getTotalRecord()/p.getRows());
		view.addObject("totalPage",totalPage);

		view.setViewName("app/detail/caidan");
		return view;
	}

	/**
	 * 前台的菜单异步刷新方法（包含：菜系选择查询，菜品查询，分页查询）
	 * @param food
	 * @param page
	 * @param stylename
	 * @param foodname
	 * @return
	 */
	@RequestMapping("/caidanAJAX")
	@ResponseBody
	public Page caidanAJAX(FoodCustom food, Page<Food> page, String stylename,String foodname){
		log.info("foodname："+foodname+" ;stylename"+stylename);
		if(foodname!= null && foodname != ""){
			log.info("foodname不等于空: "+foodname);
			food.setFoodname("%"+foodname+"%");
		}else{
			food.setFoodname("%%");
		}
		if(stylename != null && stylename !=""){
			log.info("stylename不等于空: "+stylename);
			food.setStylename("%"+stylename+"%");
		}else {
			food.setStylename("%%");
		}
		if(page.getPage() == null){
			page.setPage(1);
		}
		page.setRows(6);
		page.setParamEntity(food);
		Page<Food> p = foodService.selectPageUseDyc(page);
		System.out.println("总记录数：:  --->"+p.getTotalRecord()+"------"+p.getPageMap()+"start"+page.toString());
		return  p;
	}

	@RequestMapping("/clientCart")
	@ResponseBody
	public ModelAndView clientCart(FoodCustom food,String foodname,String tableId,String orderId){
		log.info("foodname: "+foodname+" ; tableId"+tableId+" :orderId "+orderId);

		//设置菜品名称
		food.setFoodname(foodname);

		FoodCustom selectFood = (FoodCustom) foodService.select(food);

		log.info("查询的菜品："+selectFood);

		ModelAndView view = new ModelAndView();

		// 查询的菜品对象
		view.addObject("food",selectFood);
		// 餐桌的ID
		view.addObject("tableId",tableId);
		view.addObject("orderId",orderId);
		view.setViewName("app/detail/clientCart");
		return view;
	}



	/**
	 * 接受图片
	 * @param foodPic
	 * @param food
	 * @param request
	 */
	public void image(MultipartFile foodPic, Food food, HttpServletRequest request){
		//图片原始名称
		String originalFilename = foodPic.getOriginalFilename();

		if(foodPic!=null && originalFilename!=null && originalFilename.length()>0) {
			//物理存储路径
			String pic_path = "D:\\GD\\src\\main\\webapp\\upload\\";


			//新图片名称
			String newFileName = UUID.randomUUID()+originalFilename.substring(originalFilename.lastIndexOf("."));

			//新图片
			File newFile = new File(pic_path+File.separator+originalFilename);

			try {
				//将内存中的数据写入磁盘
				foodPic.transferTo(newFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
			//将新图片写入到food
			food.setFoodimage(originalFilename);
		}
	}
}