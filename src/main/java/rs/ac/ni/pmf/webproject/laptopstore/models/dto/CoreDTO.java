package rs.ac.ni.pmf.webproject.laptopstore.models.dto;

import javax.persistence.criteria.CriteriaBuilder;

public class CoreDTO {

    private Integer id;
    private Integer name;

    public CoreDTO() {
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
