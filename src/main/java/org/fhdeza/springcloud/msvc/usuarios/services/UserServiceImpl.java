package org.fhdeza.springcloud.msvc.usuarios.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.fhdeza.springcloud.msvc.usuarios.dtos.CreateUserDto;
import org.fhdeza.springcloud.msvc.usuarios.exceptions.CustomHttpException;
import org.fhdeza.springcloud.msvc.usuarios.exceptions.NotFoundException;
import org.fhdeza.springcloud.msvc.usuarios.models.entity.User;
import org.fhdeza.springcloud.msvc.usuarios.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{

    private static final Logger log = LogManager.getLogger(UserServiceImpl.class);
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = false)
    public List<User> findAllUser() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findById(UUID id) {
        Optional<User> userFound = userRepository.findById(id);
        if (userFound.isEmpty()) throw new NotFoundException("User with id: " + id + " not found.");
        return userFound;
    }

    @Override
    @Transactional
    public User save(CreateUserDto createUserDto) {
        Optional<User> userExist = userRepository.findByEmail(createUserDto.getEmail());
        if (userExist.isPresent()) throw new CustomHttpException("User already exist", HttpStatus.CONFLICT);
        User user = new User();
        user.setName(createUserDto.getName());
        user.setEmail(createUserDto.getEmail());
        user.setPassword(createUserDto.getPassword());
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteUser(UUID id) {
        try {
            userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            log.warn("Error trying delete user with ID {} ", id, e);
            throw new NotFoundException("User with id: " + id + "not found");
        }
    }
}
