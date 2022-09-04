package rs.ac.ni.pmf.webproject.laptopstore.services;

import rs.ac.ni.pmf.webproject.laptopstore.models.dto.MemoryCardDTO;

import java.util.List;

public interface MemoryCardService {

    List<MemoryCardDTO> findMemoryCards();
    MemoryCardDTO findMemoryCardById(Integer id);
    MemoryCardDTO saveMemoryCard(MemoryCardDTO memoryCard);
}
