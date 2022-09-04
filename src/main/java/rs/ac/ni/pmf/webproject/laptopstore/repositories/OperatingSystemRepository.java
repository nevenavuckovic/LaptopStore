package rs.ac.ni.pmf.webproject.laptopstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.ni.pmf.webproject.laptopstore.models.entities.OperatingSystemEntity;

@Repository
public interface OperatingSystemRepository extends JpaRepository<OperatingSystemEntity, Integer> {
    OperatingSystemEntity findByName(String name);
}
