package rs.ac.ni.pmf.webproject.laptopstore.models.entities;

import javax.persistence.*;

@Entity
@Table(name = "laptopovi", uniqueConstraints = {@UniqueConstraint(columnNames = "naziv")})
public class LaptopEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "naziv")
    String name;

    @Column(name = "cena")
    Integer price;

    @Column(name = "kolicina")
    Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "proizvodjac_id")
    ProducerEntity producer;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "boja_id")
    ColorEntity color;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "jezgra_id")
    CoreEntity core;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "gk_id")
    GraphicCardEntity graphicCard;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "hd_id")
    HardDiskEntity hardDisk;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "memorija_id")
    MemoryCardEntity memoryCard;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "os_id")
    OperatingSystemEntity operatingSystem;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "procesor_id")
    ProcessorEntity processor;

    public LaptopEntity() {
    }

    @PrePersist
    private void prepare(){
        if(quantity == null){
            quantity = 0;
        }
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

    public ProducerEntity getProducer() {
        return producer;
    }

    public void setProducer(ProducerEntity producer) {
        this.producer = producer;
    }

    public ColorEntity getColor() {
        return color;
    }

    public void setColor(ColorEntity color) {
        this.color = color;
    }

    public CoreEntity getCore() {
        return core;
    }

    public void setCore(CoreEntity core) {
        this.core = core;
    }

    public GraphicCardEntity getGraphicCard() {
        return graphicCard;
    }

    public void setGraphicCard(GraphicCardEntity graphicCard) {
        this.graphicCard = graphicCard;
    }

    public HardDiskEntity getHardDisk() {
        return hardDisk;
    }

    public void setHardDisk(HardDiskEntity hardDisk) {
        this.hardDisk = hardDisk;
    }

    public MemoryCardEntity getMemoryCard() {
        return memoryCard;
    }

    public void setMemoryCard(MemoryCardEntity memoryCard) {
        this.memoryCard = memoryCard;
    }

    public OperatingSystemEntity getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(OperatingSystemEntity operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public ProcessorEntity getProcessor() {
        return processor;
    }

    public void setProcessor(ProcessorEntity processor) {
        this.processor = processor;
    }
}
