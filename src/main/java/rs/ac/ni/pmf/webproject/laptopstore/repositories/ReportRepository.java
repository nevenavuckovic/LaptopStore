package rs.ac.ni.pmf.webproject.laptopstore.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import rs.ac.ni.pmf.webproject.laptopstore.models.dto.LaptopReportDTO;
import rs.ac.ni.pmf.webproject.laptopstore.models.dto.OrderedLaptopReportDTO;
import rs.ac.ni.pmf.webproject.laptopstore.models.dto.UserReportDTO;
import rs.ac.ni.pmf.webproject.laptopstore.models.entities.*;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class ReportRepository {
    @Autowired
    EntityManager entityManager;

    //count and quantity of laptops by producers
    public List<LaptopReportDTO> reportLaptops() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<LaptopReportDTO> query = criteriaBuilder.createQuery(LaptopReportDTO.class);
        Root<LaptopEntity> root = query.from(LaptopEntity.class);

        query.multiselect(
                root.get(LaptopEntity_.producer),
                criteriaBuilder.count(root.get(LaptopEntity_.id)),
                criteriaBuilder.sum(root.get(LaptopEntity_.quantity)));

        query.groupBy(root.get(LaptopEntity_.producer));

        return entityManager.createQuery(query).getResultList();
    }

    //count and average price of ordered and purchased laptops
    public List<OrderedLaptopReportDTO> reportOrderedLaptops() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<OrderedLaptopReportDTO> query = criteriaBuilder.createQuery(OrderedLaptopReportDTO.class);
        Root<OrderedLaptopEntity> root = query.from(OrderedLaptopEntity.class);

        query.multiselect(
                root.get(OrderedLaptopEntity_.purchased),
                criteriaBuilder.count(root.get(OrderedLaptopEntity_.id)),
                criteriaBuilder.avg(root.get(OrderedLaptopEntity_.price)));

        query.groupBy(root.get(OrderedLaptopEntity_.purchased));

        return entityManager.createQuery(query).getResultList();
    }

    //count of users by role
    public List<UserReportDTO> reportUsers() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<UserReportDTO> query = criteriaBuilder.createQuery(UserReportDTO.class);
        Root<UserEntity> root = query.from(UserEntity.class);

        query.multiselect(
                root.get(UserEntity_.roleId),
                criteriaBuilder.count(root.get(UserEntity_.id)));

        query.groupBy(root.get(UserEntity_.roleId));

        return entityManager.createQuery(query).getResultList();
    }

}
