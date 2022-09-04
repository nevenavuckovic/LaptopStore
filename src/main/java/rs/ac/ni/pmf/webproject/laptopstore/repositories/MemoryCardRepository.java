package rs.ac.ni.pmf.webproject.laptopstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.ni.pmf.webproject.laptopstore.models.entities.MemoryCardEntity;

@Repository
public interface MemoryCardRepository extends JpaRepository<MemoryCardEntity, Integer> {
    MemoryCardEntity findByName(String name);
}
