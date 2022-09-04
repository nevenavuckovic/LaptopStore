package rs.ac.ni.pmf.webproject.laptopstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import rs.ac.ni.pmf.webproject.laptopstore.models.entities.OrderedLaptopEntity;

import java.util.Optional;

@Repository
public interface OrderedLaptopRepository extends JpaRepository<OrderedLaptopEntity, Integer>, JpaSpecificationExecutor<OrderedLaptopEntity> {
    Optional<OrderedLaptopEntity> findByLaptop(String laptop);
}
