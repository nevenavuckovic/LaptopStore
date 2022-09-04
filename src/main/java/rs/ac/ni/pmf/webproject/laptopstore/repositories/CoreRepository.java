package rs.ac.ni.pmf.webproject.laptopstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.ni.pmf.webproject.laptopstore.models.entities.CoreEntity;

import java.util.Optional;

@Repository
public interface CoreRepository extends JpaRepository<CoreEntity, Integer> {
    CoreEntity findByName(Integer name);
}
