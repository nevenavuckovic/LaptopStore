package rs.ac.ni.pmf.webproject.laptopstore.models.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "graficke_kartice", uniqueConstraints = {@UniqueConstraint(columnNames = "graficka_kartica")})
public class GraphicCardEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "graficka_kartica")
    String name;

    @OneToMany(mappedBy = "graphicCard")
    List<LaptopEntity> laptopEntities;

    public GraphicCardEntity() {
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
