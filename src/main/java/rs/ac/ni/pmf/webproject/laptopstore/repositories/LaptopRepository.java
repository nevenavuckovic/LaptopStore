package rs.ac.ni.pmf.webproject.laptopstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import rs.ac.ni.pmf.webproject.laptopstore.models.entities.LaptopEntity;


@Repository
public interface LaptopRepository extends JpaRepository<LaptopEntity, Integer>, JpaSpecificationExecutor<LaptopEntity> {
    LaptopEntity findByName(String name);
}
