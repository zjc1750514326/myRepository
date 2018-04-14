package mapper;

import empty.Orders;

public interface OrdersMapper extends BaseMapper<Orders>{

    Orders queryForList();

}