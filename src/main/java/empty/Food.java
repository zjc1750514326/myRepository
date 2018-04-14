package empty;

public class Food {
    private Integer id;

    private Integer styId;

    private String foodname;

    private Double foodprice;

    private Double foodvprice;

    private String foodremark;

    private String foodimage;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStyId() {
        return styId;
    }

    public void setStyId(Integer styId) {
        this.styId = styId;
    }

    public String getFoodname() {
        return foodname;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname == null ? null : foodname.trim();
    }

    public Double getFoodprice() {
        return foodprice;
    }

    public void setFoodprice(Double foodprice) {
        this.foodprice = foodprice;
    }

    public Double getFoodvprice() {
        return foodvprice;
    }

    public void setFoodvprice(Double foodvprice) {
        this.foodvprice = foodvprice;
    }

    public String getFoodremark() {
        return foodremark;
    }

    public void setFoodremark(String foodremark) {
        this.foodremark = foodremark == null ? null : foodremark.trim();
    }

    public String getFoodimage() {
        return foodimage;
    }

    public void setFoodimage(String foodimage) {
        this.foodimage = foodimage == null ? null : foodimage.trim();
    }

	@Override
	public String toString() {
		return "Food [id=" + id + ", styId=" + styId + ", foodname=" + foodname + ", foodprice=" + foodprice
				+ ", foodvprice=" + foodvprice + ", foodremark=" + foodremark + ", foodimage=" + foodimage + "]";
	}
    
    
}