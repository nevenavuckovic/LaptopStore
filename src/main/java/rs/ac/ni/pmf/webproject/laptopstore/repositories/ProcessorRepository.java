package rs.ac.ni.pmf.webproject.laptopstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.ni.pmf.webproject.laptopstore.models.entities.ProcessorEntity;

@Repository
public interface ProcessorRepository extends JpaRepository<ProcessorEntity, Integer> {
    ProcessorEntity findByName(String name);
}
