package org.fhdeza.springcloud.msvc.usuarios.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.fhdeza.springcloud.msvc.usuarios.dtos.CreateUserDto;
import org.fhdeza.springcloud.msvc.usuarios.dtos.UpdateUserDto;
import org.fhdeza.springcloud.msvc.usuarios.models.entity.ApiResponse;
import org.fhdeza.springcloud.msvc.usuarios.models.entity.User;
import org.fhdeza.springcloud.msvc.usuarios.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@AllArgsConstructor
@RestController
public class UserController {

    private final UserService service;

    @GetMapping
    public List<User> getAll() {
        return service.findAllUser();
    }

    @GetMapping("/{id}")
    // public User getUser(@PathVariable(name = "id") UUID id) {
    public ResponseEntity<?> getUserById(@PathVariable UUID id) {
        Optional<User> user = service.findById((id));
        if (user.isPresent()) return ResponseEntity.ok(user.get());
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserDto createUserDto) {
        User userCreated = service.createUser(createUserDto);
        ApiResponse<User> response = new ApiResponse<>(
                HttpStatus.CREATED.value(),
                "User created successfully",
                userCreated
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@RequestBody UpdateUserDto updateUserDto, @PathVariable UUID id) {
        User update = service.updateUser(updateUserDto, id);
        return ResponseEntity.ok(update);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable UUID id) {
        Optional<?> oUser = service.findById(id);
        if (oUser.isPresent()) {
            service.deleteUser(id);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.notFound().build();
    }
}
