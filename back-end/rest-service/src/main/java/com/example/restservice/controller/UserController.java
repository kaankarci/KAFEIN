package com.example.restservice.controller;

import com.example.restservice.model.UserInfo;
import com.example.restservice.model.User;

import com.example.restservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping
    public List<UserInfo> getAllUsers() {
        return userRepository.getUser();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getUser(@PathVariable("id") Long id) {
        UserInfo user = userRepository.findById(id);
        if (user == null) {
            return new ResponseEntity<String>("Bu id'ye ait kullanıcı bulunamadı, aranan ID: " + id, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<UserInfo>(user, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody User user) throws SQLIntegrityConstraintViolationException {
        if (userRepository.findByIdAll(user.getId()) != null) {
            return new ResponseEntity<String>("Girilen Id'ye ait bir kullanıcı zaten var! Girilen Id:" + user.getId(), HttpStatus.IM_USED);
        }
        userRepository.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

/*
     @PostMapping
     public ResponseEntity<?> updateUser(@RequestBody User user) {

         if (userRepository.findByIdAll(user.getId()) == null)
         {

             return new ResponseEntity<String>("Kullanıcı bulunamadı!", HttpStatus.NOT_FOUND);
         }
         userRepository.updateUser(user);
         return new ResponseEntity<User>(user, HttpStatus.OK).build();

     }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        User user =  userRepository.findById(id);
        if (user == null) {
            return new ResponseEntity<String>("Silmek istediğiniz id'ye ait bir kullanıcı bulunamadı", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }
*/

}
