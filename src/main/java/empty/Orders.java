package empty;

import java.util.List;

public class Orders {
    private String id;

    private Integer dtabId;


    private Double ordertotaleprice;

    private String orderdate;

    private Integer orderstate;

    // 一对多的关系，添加订单明细集合
    private List<Orderdetail> orderdetailList;

    public List<Orderdetail> getOrderdetailList() {
        return orderdetailList;
    }

    public void setOrderdetailList(List<Orderdetail> orderdetailList) {
        this.orderdetailList = orderdetailList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getDtabId() {
        return dtabId;
    }

    public void setDtabId(Integer dtabId) {
        this.dtabId = dtabId;
    }

    public Double getOrdertotaleprice() {
        return ordertotaleprice;
    }

    public void setOrdertotaleprice(Double ordertotaleprice) {
        this.ordertotaleprice = ordertotaleprice;
    }

    public String getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(String orderdate) {

        String sub = orderdate.substring(0, orderdate.length()-2);
        this.orderdate = sub;
    }

    public Integer getOrderstate() {
        return orderstate;
    }

    public void setOrderstate(Integer orderstate) {
        this.orderstate = orderstate;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", dtabId=" + dtabId +
                ", ordertotaleprice=" + ordertotaleprice +
                ", orderdate='" + orderdate + '\'' +
                ", orderstate=" + orderstate +
                ", orderdetailList=" + orderdetailList +
                '}';
    }
}