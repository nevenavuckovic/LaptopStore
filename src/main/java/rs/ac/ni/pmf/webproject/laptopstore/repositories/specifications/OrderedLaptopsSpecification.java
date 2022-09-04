package rs.ac.ni.pmf.webproject.laptopstore.repositories.specifications;


import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;
import rs.ac.ni.pmf.webproject.laptopstore.models.entities.OrderedLaptopEntity;
import rs.ac.ni.pmf.webproject.laptopstore.models.entities.OrderedLaptopEntity_;
import rs.ac.ni.pmf.webproject.laptopstore.searchoptions.OrderedLaptopSearchOption;

import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class OrderedLaptopsSpecification {


    public static Specification<OrderedLaptopEntity> orderedLaptopSearch(OrderedLaptopSearchOption options)
    {
        return ((root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasText(options.getLaptop())){
                Path<String> laptop = root.get(OrderedLaptopEntity_.laptop);
                predicates.add(criteriaBuilder.like(laptop, "%"+ options.getLaptop() +"%"));
            }

            if(StringUtils.hasText(options.getBuyersEmail())){
                Path<String> buyer = root.get(OrderedLaptopEntity_.buyersEmail);
                predicates.add(criteriaBuilder.like(buyer, "%"+ options.getBuyersEmail() +"%"));
            }

            if(options.getDateOfOrder() != null){
                Path<Date> dateOfOrder = root.get(OrderedLaptopEntity_.dateOfOrder);
                predicates.add(criteriaBuilder.equal(dateOfOrder, options.getDateOfOrder()));
            }

            if(options.getPurchased() != null){
                Path<Boolean> purchased = root.get(OrderedLaptopEntity_.purchased);
                predicates.add(criteriaBuilder.equal(purchased, options.getPurchased()));
            }

            if(options.getDateOfPurchase() != null){
                Path<Date> dateOfPurchase = root.get(OrderedLaptopEntity_.dateOfPurchase);
                predicates.add(criteriaBuilder.equal(dateOfPurchase, options.getDateOfPurchase()));
            }

            Order order;
            if(options.getAscending() == null || options.getAscending()){
                order = criteriaBuilder.asc(root.get(OrderedLaptopEntity_.dateOfOrder));
            } else {
                order = criteriaBuilder.desc(root.get(OrderedLaptopEntity_.dateOfOrder));
            }

            query.orderBy(order);
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]) );
        });
    }

}
