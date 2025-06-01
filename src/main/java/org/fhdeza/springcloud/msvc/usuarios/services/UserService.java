package org.fhdeza.springcloud.msvc.usuarios.services;

import org.fhdeza.springcloud.msvc.usuarios.dtos.CreateUserDto;
import org.fhdeza.springcloud.msvc.usuarios.dtos.UpdateUserDto;
import org.fhdeza.springcloud.msvc.usuarios.models.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    List<User> findAllUser();
    Optional<User> findById(UUID id);
    User createUser(CreateUserDto createUserDto);
    User updateUser(UpdateUserDto updateUserDto, UUID id);
    void deleteUser(UUID id);
}
