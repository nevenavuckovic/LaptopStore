package rs.ac.ni.pmf.webproject.laptopstore.models.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "proizvodjaci", uniqueConstraints = {@UniqueConstraint(columnNames = "proizvodjac")})
public class ProducerEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "proizvodjac")
    String name;

    @OneToMany(mappedBy = "producer")
    List<LaptopEntity> laptopEntities;

    public ProducerEntity() {
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
}
