package co.micia.projects.restapi.pricelist.daljpa.model;


import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.*;


import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "prices")
public class Price {
 /*
  * Se genera el identificador unico de la clase de manera automática
  */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private Long id;
  
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "brand_id", nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JsonIgnore
  private Brand brand;
  
  @Temporal(value = TemporalType.TIMESTAMP)
  @Column(name = "START_DATE")
  @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
  private Date startDate;	  
  
  @Temporal(value = TemporalType.TIMESTAMP)
  @Column(name = "END_DATE")
  @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
  private Date endDate;	
  
  @Column(name = "PRICE_LIST")
  private Long priceList;
  
  @Column(name = "PRODUCT_ID")
  private String producId;
  
  @Column(name = "PRIORITY")
  private Byte priority;
  
  @Column(name = "PRICE")
  private Double finalPrice;
  
  @Column(name = "CURR")
  private String curr;
  
  
  public Price() {

  }
  
/*
 * Metodo constructor con propiedades  
 */
public Price(Brand brand, Date startDate, Date endDate, Long priceList, String producId, Byte priority,
		Double finalPrice, String curr) {
	this.brand = brand;
	this.startDate = startDate;
	this.endDate = endDate;
	this.priceList = priceList;
	this.producId = producId;
	this.priority = priority;
	this.finalPrice = finalPrice;
	this.curr = curr;
}


	public Long getId() {
			return id;
	}
	
	

	public void setId(Long id) {
		this.id = id;
	}

	public Long getBrandId() {
		return brand.getId();
	}
	
	public Brand getBrand() {
		return brand;
	}
	
	public void setBrand(Brand brand) {
		this.brand = brand;
	}
	
	public Date getStartDate() {
		return startDate;
	}
	
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	public Date getEndDate() {
		return endDate;
	}
	
	public void setEndDate( Date endDate) {
		this.endDate = endDate;
	}
	
	public Long getPriceList() {
		return priceList;
	}
	
	public void setPriceList(Long priceList) {
		this.priceList = priceList;
	}
	
	public String getProducId() {
		return producId;
	}
	
	public void setProducId(String producId) {
		this.producId = producId;
	}
	
	public Byte getPriority() {
		return priority;
	}
	
	public void setPriority(Byte priority) {
		this.priority = priority;
	}
	
	public Double getFinalPrice() {
		return finalPrice;
	}
	
	public void setFinalPrice(Double finalPrice) {
		this.finalPrice = finalPrice;
	}
	
	public String getCurr() {
		return curr;
	}
	
	public void setCurr(String curr) {
		this.curr = curr;
	}

	

	@Override
	public String toString() {
        String strDateFormat = "yyyy-MM-dd HH:mm:ss"; // El formato de fecha está especificado  
        SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat); // La cadena de 
		return "Price [id=" + id + ", brand=" + brand + ", startDate=" + objSDF.format(startDate)   + ", endDate=" + objSDF.format(endDate)
				+ ", priceList=" + priceList + ", producId=" + producId + ", priority=" + priority + ", finalPrice="
				+ finalPrice + ", curr=" + curr + "]";
	}

 
}
