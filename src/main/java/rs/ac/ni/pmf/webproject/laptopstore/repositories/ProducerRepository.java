package rs.ac.ni.pmf.webproject.laptopstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.ni.pmf.webproject.laptopstore.models.entities.ProducerEntity;


@Repository
public interface ProducerRepository extends JpaRepository<ProducerEntity, Integer> {
    ProducerEntity findByName(String name);
}
