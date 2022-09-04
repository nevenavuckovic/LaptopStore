package rs.ac.ni.pmf.webproject.laptopstore.models.entities;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;

@Entity
@Table(name = "narucenilaptopovi")
public class OrderedLaptopEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "laptop")
    String laptop;

    @Column(name = "cena")
    Integer price;

    @Column(name = "kupac")
    String buyersEmail;

    @Column(name = "datum_narudzbine")
    Date dateOfOrder;

    @Column(name = "purchased")
    Boolean purchased;

    @Column(name = "datum_prodaje")
    Date dateOfPurchase;

    public OrderedLaptopEntity() {
    }

    @PrePersist
    private void prepare() {
        if(purchased == null){
            purchased = false;
        }
        if(dateOfOrder == null){
            dateOfOrder = new Date(System.currentTimeMillis());
        }
    }

    @PreUpdate
    private void update(){
        if(purchased){
            dateOfPurchase = new Date(System.currentTimeMillis());
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLaptop() {
        return laptop;
    }

    public void setLaptop(String laptop) {
        this.laptop = laptop;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getBuyersEmail() {
        return buyersEmail;
    }

    public void setBuyersEmail(String buyersEmail) {
        this.buyersEmail = buyersEmail;
    }

    public Date getDateOfOrder() {
        return dateOfOrder;
    }

    public void setDateOfOrder(Date dateOfOrder) {
        this.dateOfOrder = dateOfOrder;
    }

    public Boolean getPurchased() {
        return purchased;
    }

    public void setPurchased(Boolean purchased) {
        this.purchased = purchased;
    }

    public Date getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(Date dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }
}
