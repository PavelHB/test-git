package com.example.testing.controller;

import com.example.testing.exceptions.UserNotFoundException;
import com.example.testing.response.ErrorResponse;
import com.example.testing.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String getUser(@RequestParam("name") String name) {
        return String.format("This is %s", name);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable("id") UUID id) throws Exception {
        var user = userService.getUser(id);

        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/users")
    public String getAllUsers() {
        return "Get all users";
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handle(UserNotFoundException exception){
        var errorResponse = new ErrorResponse(exception.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

}
