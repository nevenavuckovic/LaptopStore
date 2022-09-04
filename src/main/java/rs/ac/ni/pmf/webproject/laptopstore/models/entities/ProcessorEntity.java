package rs.ac.ni.pmf.webproject.laptopstore.models.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "procesori", uniqueConstraints = {@UniqueConstraint(columnNames = "procesor")})
public class ProcessorEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "procesor")
    String name;

    @OneToMany(mappedBy = "processor")
    List<LaptopEntity> laptopEntities;

    public ProcessorEntity() {
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
