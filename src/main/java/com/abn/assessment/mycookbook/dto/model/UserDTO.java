package com.abn.assessment.mycookbook.dto.model;

import com.abn.assessment.mycookbook.domain.User;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

/**
 * Class that implements User data transfer object (DTO)
 *
 * @author Maryam Tavakoli on 24/08/2022
 */
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class UserDTO extends RepresentationModel<UserDTO> {
    @Getter
    private Long id;

    @Getter
    @NotNull(message = "Username cannot be null.")
    @Length(min=3, max=255, message="Username must contain between 3 and 255 characters.")
    private String username;

    @NotNull(message = "Password cannot be null.")
    @Length(min=6, message="Password must contain at least 6 characters.")
    private String password;


    @Getter
    private Set<RecipeDTO> recipes = new HashSet<>();

    public UserDTO (Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public User convertDTOToEntity(){
        return new ModelMapper().map(this,User.class);
    }
}
