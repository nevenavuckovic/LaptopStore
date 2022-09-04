package rs.ac.ni.pmf.webproject.laptopstore.models.dto;

public class LaptopDTO {

    private Integer id;
    private String name;
    private Integer price;
    private Integer quantity;
    private Integer colorId;
    private Integer coreId;
    private Integer processorId;
    private Integer producerId;
    private Integer memoryCardId;
    private Integer graphicCardId;
    private Integer operatingSystemId;
    private Integer hardDiskId;

    public LaptopDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getColorId() {
        return colorId;
    }

    public void setColorId(Integer colorId) {
        this.colorId = colorId;
    }

    public Integer getCoreId() {
        return coreId;
    }

    public void setCoreId(Integer coreId) {
        this.coreId = coreId;
    }

    public Integer getProcessorId() {
        return processorId;
    }

    public void setProcessorId(Integer processorId) {
        this.processorId = processorId;
    }

    public Integer getProducerId() {
        return producerId;
    }

    public void setProducerId(Integer producerId) {
        this.producerId = producerId;
    }

    public Integer getMemoryCardId() {
        return memoryCardId;
    }

    public void setMemoryCardId(Integer memoryCardId) {
        this.memoryCardId = memoryCardId;
    }

    public Integer getGraphicCardId() {
        return graphicCardId;
    }

    public void setGraphicCardId(Integer graphicCardId) {
        this.graphicCardId = graphicCardId;
    }

    public Integer getOperatingSystemId() {
        return operatingSystemId;
    }

    public void setOperatingSystemId(Integer operatingSystemId) {
        this.operatingSystemId = operatingSystemId;
    }

    public Integer getHardDiskId() {
        return hardDiskId;
    }

    public void setHardDiskId(Integer hardDiskId) {
        this.hardDiskId = hardDiskId;
    }

}
