package controller;

import empty.Orderdetail;
import empty.Page;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import service.OrderdetailService;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * Created by zjc on 2018/3/27.
 */
@Controller
@RequestMapping("sys/orderdetail")
public class OrderdetailController {
    // 获取日志记录器logger,名字为本类类名
    private static Logger log = Logger.getLogger(OrderdetailController.class);

    @Autowired
    private OrderdetailService orderdetailService;

    /**
     * 动态查询订单列表
     * @param page
     * @return
     */
    @RequestMapping("selectPageUseDyc")
    @ResponseBody
    public Object selectPageUseDyc(Page<Orderdetail> page, Orderdetail orderdetail){
        if(page !=null){
            log.info("page不为空："+ page);
        }else {
            log.info("page为空:"+page);
            return -1;
        }
        //因为js中，不支持类名.属性，所以在参数中添加 orders
        page.setParamEntity(orderdetail);

        Page<Orderdetail> p = orderdetailService.selectPageUseDyc(page);

        log.info("是否成功："+p.getPageMap());

        return p.getPageMap();
    }

    /**
     * 处理添加订单明细和订单业务
     * @param orderdetail
     * @param fooId
     * @param foodName
     * @param foodcount
     * @param foodPrivice
     * @param totalMoney
     */
    @RequestMapping(value = "/insert")
    @ResponseBody
    public ModelAndView insert(Orderdetail orderdetail, String fooId, String foodName,
                               String foodcount, String foodPrivice, String totalMoney,
                               String tableId,String orderId,HttpServletRequest request){
        log.info("fooId="+fooId+" :foodName= "+foodName+" :foodPrivice= "+foodPrivice+" :foodcount= "+foodcount+" :totalMoney= "+totalMoney+" :orderId= "+orderId);

        UUID uuid = UUID.randomUUID();
        log.info("UUID = "+uuid);
        String detailId = uuid.toString().replace("-", "").substring(0, 8);
        orderdetail.setDetailId(detailId);
        orderdetail.setOrdId(orderId);
        orderdetail.setFooId(Integer.parseInt(fooId));
        orderdetail.setFoodName(foodName);
        orderdetail.setFoodcount(Integer.parseInt(foodcount));
        orderdetail.setFoodPrivice(Double.parseDouble(foodPrivice));
        orderdetail.setTotalMoney(Double.parseDouble(totalMoney));

        int i = 0;
        try {
            i = orderdetailService.insert(orderdetail);
            if (i<=0){
                log.info("订单明细插入失败!!!"+i);
                return null;
            }else {
                log.info("订单明细插入成功!!!"+i);
            }
        } catch (Exception e) {
            log.info("插入异常");
            return null;
        }

        log.info("测试填充订单详细列表："+orderdetail);
        request.getSession().setAttribute("orderdetail",orderdetail);
        request.getSession().setAttribute("tableId",tableId);
        request.getSession().setAttribute("orderId",orderId);

        return new ModelAndView("redirect:/sys/orders/insert.action");
    }
}
