package org.fhdeza.springcloud.msvc.usuarios.repositories;

import org.fhdeza.springcloud.msvc.usuarios.models.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends CrudRepository<User, UUID> {
    Optional<User> findByEmail(String email);

    @Query("select u from User u where u.email=?1")
    Optional<User> porEmail(String email);

    boolean existsByEmail(String email);

}
