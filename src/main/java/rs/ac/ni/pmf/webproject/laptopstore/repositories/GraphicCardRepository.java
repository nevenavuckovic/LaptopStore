package rs.ac.ni.pmf.webproject.laptopstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.ni.pmf.webproject.laptopstore.models.entities.GraphicCardEntity;

@Repository
public interface GraphicCardRepository extends JpaRepository<GraphicCardEntity, Integer> {
    GraphicCardEntity findByName(String name);
}
