package controller;

import empty.Orderdetail;
import empty.Orders;
import empty.Page;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import service.OrdersService;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zjc on 2018/3/26.
 */
@Controller
@RequestMapping("sys/orders")
public class OrdersController {
    // 获取日志记录器logger,名字为本类类名
    private static Logger log = Logger.getLogger(OrdersController.class);

    @Autowired
    private OrdersService ordersService;

    /**
     * 动态查询订单列表
     * @param page
     * @return
     */
    @RequestMapping("selectPageUseDyc")
    @ResponseBody
    public Object selectPageUseDyc(Page<Orders> page,Orders orders){
        if(page !=null){
            log.info("page不为空："+ page);
        }else {
            log.info("page为空:"+page);
            return -1;
        }
        //因为js中，不支持类名.属性，所以在参数中添加 orders
        page.setParamEntity(orders);

        Page<Orders> p = ordersService.selectPageUseDyc(page);

        log.info("是否成功："+p.getPageMap());

        return p.getPageMap();
    }

    /**
     * 查询订单（包含订单明细）
     * @return
     */
    @RequestMapping("/queryForList")
    @ResponseBody
    public ModelAndView queryForList(){
        Orders orders = ordersService.queryForList();
        log.info("多表查询的结果："+orders.toString());

        ModelAndView view = new ModelAndView();

        view.addObject("orders",orders);

        view.setViewName("app/detail/clientOrderList");

        return view;
    }


    /**
     * 插入订单
     * @param orders
     * @param request
     * @return
     */
    @RequestMapping(value = "/insert")
    @ResponseBody
    public ModelAndView insert(Orders orders, HttpServletRequest request){
        Orderdetail orderdetail = (Orderdetail)request.getSession().getAttribute("orderdetail");

        String tableId = request.getSession().getAttribute("tableId").toString();
        String orderId = request.getSession().getAttribute("orderId").toString();

        log.info("Orders中的控制器："+orderdetail+" 经过跋山涉水的tableID: "+tableId+" :orderId= "+orderId);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String orderdate = dateFormat.format(new Date());


        //将订单明细中的订单ID设置进订单中
        orders.setId(orderdetail.getOrdId());
        //这里查询订单主要是为了查询出订单ID是否存在
        Orders select = ordersService.select(orders);
        if(select!=null){
            log.info("同一个订单!!!");
        }else {
            // 设置餐桌Id
            orders.setDtabId(Integer.parseInt(tableId));
            // 设置订单Id
            orders.setId(orderdetail.getOrdId());
            // 设置订单日期
            orders.setOrderdate(orderdate);
            int i = 0;
            try {
                i = ordersService.insert(orders);
                if (i<=0){
                    log.info("订单插入失败!!!");
                    return null;
                }else {
                    log.info("订单插入成功!!!");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return new ModelAndView("redirect:/sys/food/caidan.action?stylename="+"&&orderId="+orderId);
    }
}
