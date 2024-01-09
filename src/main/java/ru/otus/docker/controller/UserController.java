package ru.otus.docker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.otus.docker.model.User;
import ru.otus.docker.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

//  curl -X POST -i -H "Content-Type:application/json" -H "Host:arch.homework" -d '{"name": "FrodoBaggins"}' http://localhost:8080/api/user
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseEntity<User> newUser(@RequestBody User user) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService
                        .saveUser(user)
                        .orElseThrow(() -> new ResponseStatusException(
                                HttpStatus.BAD_REQUEST, "User can not be saved"
                        ))
                );
    }

//  curl -X GET -i -H "Content-Type:application/json" -H "Host:arch.homework" http://localhost:8080/api/user/1
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> findUserById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(
                userService
                        .findUserById(id)
                        .orElseThrow(() -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND, "No user with specified ID was found"
                        )));
    }

//  curl -X PUT -i -H "Content-Type:application/json" -H "Host:arch.homework" -d '{"name": "BilboBaggins"}' http://localhost:8080/api/user/1
    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public ResponseEntity<User> updateUserById(@PathVariable("id") Long id, @RequestBody User user) {
        return ResponseEntity.ok(
                userService
                        .updateUserById(id, user)
                        .orElseThrow(() -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND, "No user with specified ID was found"
                        )));
    }

//  curl -X DELETE -i -H "Content-Type:application/json" -H "Host:arch.homework" http://localhost:8080/api/user/1
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteUserById(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return ResponseEntity
                .noContent()
                .build();
    }
}
