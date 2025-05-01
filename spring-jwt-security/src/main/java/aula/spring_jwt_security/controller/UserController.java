package aula.spring_jwt_security.controller;


import aula.spring_jwt_security.model.User;
import aula.spring_jwt_security.security.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserServices service;
    @PostMapping
    public void postUser(@RequestBody User user){
        service.createUser(user);
    }

}
