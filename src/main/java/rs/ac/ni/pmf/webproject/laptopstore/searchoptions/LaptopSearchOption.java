package rs.ac.ni.pmf.webproject.laptopstore.searchoptions;

public class LaptopSearchOption {

    String name;
    Integer minPrice;
    Integer maxPrice;
    String color;
    Integer core;
    String processor;
    String producer;
    String graphicCard;
    String memoryCard;
    String operatingSystem;
    String hardDisk;
    Boolean ascending;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Integer minPrice) {
        this.minPrice = minPrice;
    }

    public Integer getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Integer maxPrice) {
        this.maxPrice = maxPrice;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getCore() {
        return core;
    }

    public void setCore(Integer core) {
        this.core = core;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getGraphicCard() {
        return graphicCard;
    }

    public void setGraphicCard(String graphicCard) {
        this.graphicCard = graphicCard;
    }

    public String getMemoryCard() {
        return memoryCard;
    }

    public void setMemoryCard(String memoryCard) {
        this.memoryCard = memoryCard;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public String getHardDisk() { return hardDisk; }

    public void setHardDisk(String hardDisk) {
        this.hardDisk = hardDisk;
    }

    public Boolean getAscending() {
        return ascending;
    }

    public void setAscending(Boolean ascending) {
        this.ascending = ascending;
    }
}
