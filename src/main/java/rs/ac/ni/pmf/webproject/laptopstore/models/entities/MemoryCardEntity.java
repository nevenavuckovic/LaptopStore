package rs.ac.ni.pmf.webproject.laptopstore.models.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "memorije", uniqueConstraints = {@UniqueConstraint(columnNames = "memorija")})
public class MemoryCardEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "memorija")
    String name;

    @OneToMany(mappedBy = "memoryCard")
    List<LaptopEntity> laptopEntities;

    public MemoryCardEntity() {
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
