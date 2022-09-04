package rs.ac.ni.pmf.webproject.laptopstore.models.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "jezgra", uniqueConstraints = {@UniqueConstraint(columnNames = "jezgro")})
public class CoreEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "jezgro")
    Integer name;

    @OneToMany(mappedBy = "core")
    List<LaptopEntity> laptopEntities;

    public CoreEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getName() {
        return name;
    }

    public void setName(Integer name) {
        this.name = name;
    }
}
