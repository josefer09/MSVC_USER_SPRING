package org.fhdeza.springcloud.msvc.usuarios.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "users") // Es opcional, pero por convension se define en plural
@Data // Genera getter, setters, toString, equals, hashCode
@NoArgsConstructor // Constructor vacio
@AllArgsConstructor // Constructor con todos los campos
public class User {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(unique = true)
    private String email;

    private String password;
}
