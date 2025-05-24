package org.fhdeza.springcloud.msvc.usuarios.repositories;

import org.fhdeza.springcloud.msvc.usuarios.models.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface UserRepository extends CrudRepository<User, UUID> {
}
