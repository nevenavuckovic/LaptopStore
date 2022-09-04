package rs.ac.ni.pmf.webproject.laptopstore.models.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "boje")
public class ColorEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "boja")
    String name;

    @OneToMany(mappedBy = "color")
    List<LaptopEntity> laptopEntities;

    public ColorEntity() {
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
