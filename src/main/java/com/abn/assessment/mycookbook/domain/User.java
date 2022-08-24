package com.abn.assessment.mycookbook.domain;

import com.abn.assessment.mycookbook.dto.model.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    private String password;

    @ManyToMany
    @JoinTable(name = "user_recipe",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "recipe_id"))
    private Set<Recipe> recipes = new HashSet<>();

    public User(Long id,String username,String password){
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public UserDTO convertEntityToDTO(){
        return new ModelMapper().map(this, UserDTO.class);
    }
}
