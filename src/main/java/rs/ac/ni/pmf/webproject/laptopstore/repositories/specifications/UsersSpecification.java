package rs.ac.ni.pmf.webproject.laptopstore.repositories.specifications;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;
import rs.ac.ni.pmf.webproject.laptopstore.models.Role;
import rs.ac.ni.pmf.webproject.laptopstore.models.entities.UserEntity;
import rs.ac.ni.pmf.webproject.laptopstore.models.entities.UserEntity_;
import rs.ac.ni.pmf.webproject.laptopstore.searchoptions.UserSearchOption;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class UsersSpecification {

    public static Specification<UserEntity> userSearch(UserSearchOption options)
    {
        return ((root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if(StringUtils.hasText(options.getUsername())){
                Path<String> username = root.get(UserEntity_.username);
                predicates.add(criteriaBuilder.like(username, "%"+ options.getUsername() +"%"));
            }

            if(StringUtils.hasText(options.getEmail())){
                Path<String> email = root.get(UserEntity_.email);
                predicates.add(criteriaBuilder.like(email, "%"+ options.getEmail() +"%"));
            }

            if(options.getRole() != null){
                Path<Integer> role = root.get(UserEntity_.roleId);
                predicates.add(criteriaBuilder.equal(role, Role.getValueByCode(options.getRole())));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]) );
        });
    }
}
