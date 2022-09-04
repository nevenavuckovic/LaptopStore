package rs.ac.ni.pmf.webproject.laptopstore.services;

import rs.ac.ni.pmf.webproject.laptopstore.models.dto.ProducerDTO;

import java.util.List;

public interface ProducerService {

    List<ProducerDTO> findProducers();
    ProducerDTO findProducerById(Integer id);
    ProducerDTO saveProducer(ProducerDTO producer);
}
