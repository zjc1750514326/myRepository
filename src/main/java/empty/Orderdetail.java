package empty;

public class Orderdetail {
    private String detailId;

    private Integer fooId;

    private String ordId;

    private String foodName;

    private Integer foodcount;

    private double foodPrivice;

    private double totalMoney;

    // 多对一的关系，添加一个订单
    private Orders orders;

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public String getDetailId() {
        return detailId;
    }

    public void setDetailId(String detailId) {
        this.detailId = detailId;
    }

    public Integer getFooId() {
        return fooId;
    }

    public void setFooId(Integer fooId) {
        this.fooId = fooId;
    }

    public String getOrdId() {
        return ordId;
    }

    public void setOrdId(String ordId) {
        this.ordId = ordId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName == null ? null : foodName.trim();
    }

    public Integer getFoodcount() {
        return foodcount;
    }

    public void setFoodcount(Integer foodcount) {
        this.foodcount = foodcount;
    }

    public double getFoodPrivice() {
        return foodPrivice;
    }

    public void setFoodPrivice(double foodPrivice) {
        this.foodPrivice = foodPrivice;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    @Override
    public String toString() {
        return "Orderdetail{" +
                "detailId=" + detailId +
                ", fooId=" + fooId +
                ", ordId=" + ordId +
                ", foodName='" + foodName + '\'' +
                ", foodcount=" + foodcount +
                ", foodPrivice=" + foodPrivice +
                ", totalMoney=" + totalMoney +
                ", orders=" + orders +
                '}';
    }
}