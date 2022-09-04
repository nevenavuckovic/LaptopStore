package rs.ac.ni.pmf.webproject.laptopstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.ni.pmf.webproject.laptopstore.models.entities.HardDiskEntity;

@Repository
public interface HardDiskRepository extends JpaRepository<HardDiskEntity, Integer> {
    HardDiskEntity findByName(String name);
}
