package rs.ac.ni.pmf.webproject.laptopstore.models.mappers;

import org.springframework.stereotype.Component;
import rs.ac.ni.pmf.webproject.laptopstore.models.dto.UserDTO;
import rs.ac.ni.pmf.webproject.laptopstore.models.entities.UserEntity;

@Component
public class UserMapper {
    public UserDTO toDto(UserEntity entity)
    {
        UserDTO dto = new UserDTO();
        dto.setId(entity.getId());
        dto.setUsername(entity.getUsername());
        dto.setEmail(entity.getEmail());
        dto.setPassword(entity.getPassword());
        dto.setRoleId(entity.getRoleId());
        dto.setActive(entity.getActive());

        return dto;
    }

    public UserEntity toEntity(UserDTO dto)
    {
        UserEntity entity = new UserEntity();
        entity.setId(dto.getId());
        entity.setUsername(dto.getUsername());
        entity.setEmail(dto.getEmail());
        entity.setPassword((dto.getPassword()));
        entity.setRoleId(dto.getRoleId());
        entity.setActive(dto.getActive());

        return entity;
    }
}
