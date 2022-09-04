package rs.ac.ni.pmf.webproject.laptopstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.ni.pmf.webproject.laptopstore.models.entities.ColorEntity;

import java.util.Optional;

@Repository
public interface ColorRepository extends JpaRepository<ColorEntity, Integer> {
    ColorEntity findByName(String name);
}
