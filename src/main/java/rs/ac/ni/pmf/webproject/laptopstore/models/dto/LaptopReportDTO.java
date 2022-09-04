package rs.ac.ni.pmf.webproject.laptopstore.models.dto;

import rs.ac.ni.pmf.webproject.laptopstore.models.entities.ProducerEntity;

public class LaptopReportDTO {
    private Long count;
    private Long quantity;
    private String name;

    public LaptopReportDTO() {
    }

    public LaptopReportDTO(ProducerEntity producerEntity, Long count, Long quantity) {
        this.count = count;
        this.quantity = quantity;
        this.name = producerEntity.getName();
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
