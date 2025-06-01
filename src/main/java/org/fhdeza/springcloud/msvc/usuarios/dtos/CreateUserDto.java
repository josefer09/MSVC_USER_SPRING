package org.fhdeza.springcloud.msvc.usuarios.dtos;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CreateUserDto {

    @NotBlank(message = "property name is required.")
    private String name;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$",
            message = "The password must have a Uppercase, lowercase letter and a number"
    )
    private String password;

    @NotNull(message = "Age is required.")
    @Min(value = 1, message = "Age must be greater than to 0.")
    private Long age;
}
