package rs.ac.ni.pmf.webproject.laptopstore.models.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "operativni_sistemi", uniqueConstraints = {@UniqueConstraint(columnNames = "operativni_sistem")})
public class OperatingSystemEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "operativni_sistem")
    String name;

    @OneToMany(mappedBy = "operatingSystem")
    List<LaptopEntity> laptopEntities;

    public OperatingSystemEntity() {
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
