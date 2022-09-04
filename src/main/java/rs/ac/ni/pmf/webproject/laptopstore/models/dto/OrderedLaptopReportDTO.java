package rs.ac.ni.pmf.webproject.laptopstore.models.dto;

public class OrderedLaptopReportDTO {
    private Boolean purchased;
    private Long count;
    private Double averagePrice;


    public OrderedLaptopReportDTO() {
    }

    public OrderedLaptopReportDTO(Boolean purchased, Long count, Double averagePrice) {
        this.count = count;
        this.averagePrice = averagePrice;
        this.purchased = purchased;
    }

    public Boolean getPurchased() {
        return purchased;
    }

    public void setPurchased(Boolean purchased) {
        this.purchased = purchased;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Double getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(Double averagePrice) {
        this.averagePrice = averagePrice;
    }

}
