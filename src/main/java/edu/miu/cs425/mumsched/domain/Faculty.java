package edu.miu.cs425.mumsched.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

/**
 * @author Demisew Mokonnen
 * @2020
 */


//@Entity
//@DiscriminatorValue("faculty")
public class Faculty extends User{
    public Faculty(Integer id, @Length(min = 5, message = "*Your user name must have at least 5 characters") @NotEmpty(message = "*Please provide a user name") String userName, @Email(message = "*Please provide a valid Email") @NotEmpty(message = "*Please provide an email") String email, @Length(min = 5, message = "*Your password must have at least 5 characters") @NotEmpty(message = "*Please provide your password") String password, @NotEmpty(message = "*Please provide your name") String name, @NotEmpty(message = "*Please provide your last name") String lastName, Boolean active, Set<Role> roles) {
        super(id, userName, email, password, name, lastName, active, roles);
    }
    public Faculty() {
    }
}
