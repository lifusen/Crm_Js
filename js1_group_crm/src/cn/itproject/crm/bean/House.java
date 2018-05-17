package cn.itproject.crm.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 房产
 * @author ham
 *
 */

@Entity
@Table(name="House")
public class House implements Serializable{
	private static final long serialVersionUID = -2094736881318249012L;
	public static final Integer HOUSE = 1; 
	public static final Integer STORE = 2; 
	public static final Integer OFFICE = 3; 
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private String placeArea;			// 住房位置（区）
	@Column
	private String placeStreet;			// 住房位置（街道）
	@Column
	private String placeDetail;			// 住房位置（详情）
	@Column
	private String placePremise;		// 住房位置（楼盘）
	@Column
	private String landNature;			// 国土性质
	@Column
	private String constructDate;		// 建造年代
	@Column
	private String area;				// 面积
	@Column
	private String price;				// 价格
	@Column
	private String decorateCase;		// 装修情况
	@Column
	private String useCase;				// 使用情况
	@Column
	private String rental;				// 租金/月
	@Column
	private Integer type;				// 类型
	
	@ManyToOne
	@JoinColumn(name = "customerId")
	private Customer customer;

	public House() {
		super();
	}

	public House(String placeArea, String placeStreet, String placeDetail,
			String placePremise, String landNature, String constructDate,
			String area, String price, String decorateCase, String useCase,
			String rental, Integer type) {
		super();
		this.placeArea = placeArea;
		this.placeStreet = placeStreet;
		this.placeDetail = placeDetail;
		this.placePremise = placePremise;
		this.landNature = landNature;
		this.constructDate = constructDate;
		this.area = area;
		this.price = price;
		this.decorateCase = decorateCase;
		this.useCase = useCase;
		this.rental = rental;
		this.type = type;
	}
	//用于初始化空的房产对象
	public House(Integer id, String placeArea, String placeStreet,
			String placeDetail, String placePremise, String landNature,
			String constructDate, String area, String price,
			String decorateCase, String useCase, String rental) {
		super();
		this.id = id;
		this.placeArea = placeArea;
		this.placeStreet = placeStreet;
		this.placeDetail = placeDetail;
		this.placePremise = placePremise;
		this.landNature = landNature;
		this.constructDate = constructDate;
		this.area = area;
		this.price = price;
		this.decorateCase = decorateCase;
		this.useCase = useCase;
		this.rental = rental;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPlaceArea() {
		return placeArea;
	}

	public void setPlaceArea(String placeArea) {
		this.placeArea = placeArea;
	}

	public String getPlaceStreet() {
		return placeStreet;
	}

	public void setPlaceStreet(String placeStreet) {
		this.placeStreet = placeStreet;
	}

	public String getPlaceDetail() {
		return placeDetail;
	}

	public void setPlaceDetail(String placeDetail) {
		this.placeDetail = placeDetail;
	}

	public String getPlacePremise() {
		return placePremise;
	}

	public void setPlacePremise(String placePremise) {
		this.placePremise = placePremise;
	}

	public String getLandNature() {
		return landNature;
	}

	public void setLandNature(String landNature) {
		this.landNature = landNature;
	}

	public String getConstructDate() {
		return constructDate;
	}

	public void setConstructDate(String constructDate) {
		this.constructDate = constructDate;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getDecorateCase() {
		return decorateCase;
	}

	public void setDecorateCase(String decorateCase) {
		this.decorateCase = decorateCase;
	}

	public String getUseCase() {
		return useCase;
	}

	public void setUseCase(String useCase) {
		this.useCase = useCase;
	}

	public String getRental() {
		return rental;
	}

	public void setRental(String rental) {
		this.rental = rental;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	//重写hashCode和equals方法,用于清除空对象
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((area == null) ? 0 : area.hashCode());
		result = prime * result
				+ ((constructDate == null) ? 0 : constructDate.hashCode());
		result = prime * result
				+ ((customer == null) ? 0 : customer.hashCode());
		result = prime * result
				+ ((decorateCase == null) ? 0 : decorateCase.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((landNature == null) ? 0 : landNature.hashCode());
		result = prime * result
				+ ((placeArea == null) ? 0 : placeArea.hashCode());
		result = prime * result
				+ ((placeDetail == null) ? 0 : placeDetail.hashCode());
		result = prime * result
				+ ((placePremise == null) ? 0 : placePremise.hashCode());
		result = prime * result
				+ ((placeStreet == null) ? 0 : placeStreet.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((rental == null) ? 0 : rental.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		House other = (House) obj;
		if (area == null) {
			if (other.area != null)
				return false;
		} else if (!area.equals(other.area))
			return false;
		if (constructDate == null) {
			if (other.constructDate != null)
				return false;
		} else if (!constructDate.equals(other.constructDate))
			return false;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (decorateCase == null) {
			if (other.decorateCase != null)
				return false;
		} else if (!decorateCase.equals(other.decorateCase))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (landNature == null) {
			if (other.landNature != null)
				return false;
		} else if (!landNature.equals(other.landNature))
			return false;
		if (placeArea == null) {
			if (other.placeArea != null)
				return false;
		} else if (!placeArea.equals(other.placeArea))
			return false;
		if (placeDetail == null) {
			if (other.placeDetail != null)
				return false;
		} else if (!placeDetail.equals(other.placeDetail))
			return false;
		if (placePremise == null) {
			if (other.placePremise != null)
				return false;
		} else if (!placePremise.equals(other.placePremise))
			return false;
		if (placeStreet == null) {
			if (other.placeStreet != null)
				return false;
		} else if (!placeStreet.equals(other.placeStreet))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (rental == null) {
			if (other.rental != null)
				return false;
		} else if (!rental.equals(other.rental))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "House [id=" + id + ", placeArea=" + placeArea
				+ ", placeStreet=" + placeStreet + ", placeDetail="
				+ placeDetail + ", placePremise=" + placePremise
				+ ", landNature=" + landNature + ", constructDate="
				+ constructDate + ", area=" + area + ", price=" + price
				+ ", decorateCase=" + decorateCase + ", useCase=" + useCase
				+ ", rental=" + rental + ", type=" + type + "]";
	}
	
	public String toTagString() {
		
		return  formatNull("区", placeArea)+
				formatNull("街道", placeStreet)+
				formatNull("详细", placeDetail)+
				formatNull("楼盘", placePremise)+
				formatNull("国土性质", landNature)+
				formatNull("建筑年代", constructDate)+
				formatNull("面积", area)+
				formatNull("均价", price)+
				formatNull("装修情况", decorateCase)+
				formatNull("使用情况", useCase)+
				formatNull("租金/月", rental);
	}
	private String formatNull(String name,String value){
		if (value==null||value.trim().equals("")) {
			return "";
		}
		return value+"("+name+") ";
	}
}
