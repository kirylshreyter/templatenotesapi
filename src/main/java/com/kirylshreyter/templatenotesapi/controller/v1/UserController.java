package com.kirylshreyter.templatenotesapi.controller.v1;

import com.kirylshreyter.templatenotesapi.assembler.UserModelAssembler;
import com.kirylshreyter.templatenotesapi.model.User;
import com.kirylshreyter.templatenotesapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserModelAssembler assembler;

    @GetMapping("/users")
    public CollectionModel<EntityModel<User>> all() {
        List<EntityModel<User>> users = userService.getAllUsers().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(users, linkTo(methodOn(UserController.class).all()).withSelfRel());
    }

    @PostMapping("/users")
    ResponseEntity<?> newUser(@RequestBody User newUser) {
        EntityModel<User> entityModel = assembler.toModel(userService.saveUser(newUser));
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    @GetMapping("/users/{id}")
    public EntityModel<User> one(@PathVariable Long id) {
        return assembler.toModel(userService.getUserById(id));
    }

    @PutMapping("/users/{id}")
    ResponseEntity<?> replaceUser(@RequestBody User newUser, @PathVariable Long id) {
        EntityModel<User> entityModel = assembler.toModel(userService.updateUserById(id, newUser));
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    @DeleteMapping("/users/{id}")
    ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }
}
