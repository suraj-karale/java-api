package com.javahelps.restservice.controller;

import com.javahelps.restservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.javahelps.restservice.entity.User;
import javassist.tools.web.BadHttpRequest;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    @Autowired
    private UserRepository repository;

    @GetMapping
    public Iterable<User> findAll(){
        return repository.findAll();
    }

    @GetMapping(path = "/{username}")
    public User find(@PathVariable("username") String username) {
        return repository.findOne(username);
    }

    @PostMapping(consumes = "application/json")
    public User create(@RequestBody User user) {
        return repository.save(user);
    }

    @DeleteMapping(path = "/{username}")
    public void delete(@PathVariable("username") String username) {
        repository.delete(username);
    }

    @PutMapping(path = "/{username}")
    public User update(@PathVariable("username") String username, @RequestBody User user) throws BadHttpRequest {
        if (repository.exists(username)) {
            user.setUsername(username);
            return repository.save(user);
        } else {
            throw new BadHttpRequest();
        }
    }
}
