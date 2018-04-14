package service.impl;

import empty.Orders;
import mapper.OrdersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import service.OrdersService;

/**
 * Created by zjc on 2018/3/26.
 */
public class OrdersServiceImpl extends BaseServiceImpl<Orders> implements OrdersService {

    @Autowired
    private OrdersMapper ordersMapper;

    @Override
    public Orders queryForList() {
        return ordersMapper.queryForList();
    }
}
