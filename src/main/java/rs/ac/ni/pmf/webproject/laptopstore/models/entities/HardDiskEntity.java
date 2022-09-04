package rs.ac.ni.pmf.webproject.laptopstore.models.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "hard_diskovi", uniqueConstraints = {@UniqueConstraint(columnNames = "hard_disk")})
public class HardDiskEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "hard_disk")
    String name;

    @OneToMany(mappedBy = "hardDisk")
    List<LaptopEntity> laptopEntities;

    public HardDiskEntity() {
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
