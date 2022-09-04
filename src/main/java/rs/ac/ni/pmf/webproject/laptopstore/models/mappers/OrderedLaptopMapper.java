package rs.ac.ni.pmf.webproject.laptopstore.models.mappers;

import org.springframework.stereotype.Component;
import rs.ac.ni.pmf.webproject.laptopstore.models.dto.OrderedLaptopDTO;
import rs.ac.ni.pmf.webproject.laptopstore.models.entities.OrderedLaptopEntity;

@Component
public class OrderedLaptopMapper {

    public OrderedLaptopDTO toDto(OrderedLaptopEntity entity){
        OrderedLaptopDTO dto = new OrderedLaptopDTO();
        dto.setId(entity.getId());
        dto.setLaptop(entity.getLaptop());
        dto.setPrice(entity.getPrice());
        dto.setBuyersEmail(entity.getBuyersEmail());
        dto.setDateOfOrder(entity.getDateOfOrder());
        dto.setPurchased(entity.getPurchased());
        dto.setDateOfPurchase(entity.getDateOfPurchase());
        return dto;
    }

    public OrderedLaptopEntity toEntity(OrderedLaptopDTO dto){
        OrderedLaptopEntity entity = new OrderedLaptopEntity();
        entity.setId(dto.getId());
        entity.setLaptop(dto.getLaptop());
        entity.setPrice(dto.getPrice());
        entity.setBuyersEmail(dto.getBuyersEmail());
        entity.setDateOfOrder(dto.getDateOfOrder());
        entity.setPurchased(dto.getPurchased());
        entity.setDateOfPurchase(dto.getDateOfPurchase());
        return  entity;
    }
}
