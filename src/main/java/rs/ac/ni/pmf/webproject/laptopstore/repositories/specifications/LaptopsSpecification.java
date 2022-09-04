package rs.ac.ni.pmf.webproject.laptopstore.repositories.specifications;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;
import rs.ac.ni.pmf.webproject.laptopstore.models.entities.*;
import rs.ac.ni.pmf.webproject.laptopstore.searchoptions.LaptopSearchOption;

import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class LaptopsSpecification {

    public static Specification<LaptopEntity> laptopSearch(LaptopSearchOption options)
    {
        return ((root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasText(options.getName())){
                Path<String> name = root.get(LaptopEntity_.name);
                predicates.add(criteriaBuilder.like(name, "%"+ options.getName() +"%"));
            }

            if(options.getMaxPrice() != null && options.getMinPrice() != null){
                Path<Integer> price = root.get(LaptopEntity_.price);
                predicates.add(criteriaBuilder.between(price, options.getMinPrice(), options.getMaxPrice()));
            } else if(options.getMaxPrice() != null){
                Path<Integer> price = root.get(LaptopEntity_.price);
                predicates.add(criteriaBuilder.lessThanOrEqualTo(price, options.getMaxPrice()));
            } else if(options.getMinPrice() != null){
                Path<Integer> price = root.get(LaptopEntity_.price);
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(price, options.getMinPrice()));
            }

            if(StringUtils.hasText(options.getColor())){
                Path<String> colorName = root.join(LaptopEntity_.color).get(ColorEntity_.name);
                predicates.add(criteriaBuilder.equal(colorName, options.getColor()));
            }

            if(options.getCore() != null){
                Path<Integer> coreName = root.join(LaptopEntity_.core).get(CoreEntity_.name);
                predicates.add(criteriaBuilder.equal(coreName, options.getCore()));
            }

            if(StringUtils.hasText(options.getMemoryCard())){
                Path<String> memoryCardName = root.join(LaptopEntity_.memoryCard).get(MemoryCardEntity_.name);
                predicates.add(criteriaBuilder.equal(memoryCardName, options.getMemoryCard()));
            }

            if(StringUtils.hasText(options.getGraphicCard())){
                Path<String> graphicCardName = root.join(LaptopEntity_.graphicCard).get(GraphicCardEntity_.name);
                predicates.add(criteriaBuilder.equal(graphicCardName, options.getGraphicCard()));
            }

            if(StringUtils.hasText(options.getProcessor())){
                Path<String> processorName = root.join(LaptopEntity_.processor).get(ProcessorEntity_.name);
                predicates.add(criteriaBuilder.equal(processorName, options.getProcessor()));
            }

            if(StringUtils.hasText(options.getProducer())){
                Path<String> producerName = root.join(LaptopEntity_.producer).get(ProducerEntity_.name);
                predicates.add(criteriaBuilder.equal(producerName, options.getProducer()));
            }

            if(StringUtils.hasText(options.getHardDisk())){
                Path<String> hardDiskName = root.join(LaptopEntity_.hardDisk).get(HardDiskEntity_.name);
                predicates.add(criteriaBuilder.equal(hardDiskName, options.getHardDisk()));
            }

            if(StringUtils.hasText(options.getOperatingSystem())){
                Path<String> operatingSystemName = root.join(LaptopEntity_.operatingSystem).get(OperatingSystemEntity_.name);
                predicates.add(criteriaBuilder.equal(operatingSystemName, options.getOperatingSystem()));
            }

            Order order;
            if(options.getAscending() == null || options.getAscending()){
                order = criteriaBuilder.asc(root.get(LaptopEntity_.price));
            } else {
                order = criteriaBuilder.desc(root.get(LaptopEntity_.price));
            }

            query.orderBy(order);
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]) );
        });
    }

}
