package service;

import empty.Orders;

/**
 * Created by zjc on 2018/3/26.
 */
public interface OrdersService extends BaseService<Orders> {
    Orders queryForList();
}
