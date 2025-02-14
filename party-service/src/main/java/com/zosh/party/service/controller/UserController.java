package com.zosh.party.service.controller;

import com.zosh.party.service.exception.UserException;
import com.zosh.party.service.modal.User;
import com.zosh.party.service.repository.UserRepository;
import com.zosh.party.service.service.UserService;
import jdk.jshell.spi.ExecutionControl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UserController {

    private UserService userService;
    @PostMapping("/api/users")
    /*User with Response Entity just to get the status code as well */
    public ResponseEntity<User> createUser(@RequestBody@Valid User user){
        User createdUser=userService.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);


    }
    @GetMapping("/api/users")
    public ResponseEntity<List<User>> getUsers() {
        List<User> users=userService.getAllUsers();
        return new ResponseEntity<>(users,HttpStatus.OK);

    }
    @GetMapping("/api/users/{userId}")

    public ResponseEntity<User> getUserById(@PathVariable("userId") Long id) throws Exception {
        User user=userService.getUserBYId(id);
        return new ResponseEntity<>(user,HttpStatus.OK);

    }

       @PutMapping("/api/user/{id}")
               public ResponseEntity<User> updateUser(@RequestBody User user,@PathVariable Long id) throws Exception {
        User updatedUser=userService.updateUser(id,user);
        return new ResponseEntity<>(updatedUser,HttpStatus.OK);

        }
        @DeleteMapping("/api/users/{id}")
         public ResponseEntity<String> deleteUserById(@PathVariable Long id) throws Exception{
          userService.deleteUser(id);
          return new ResponseEntity<>("User deleted",HttpStatus.ACCEPTED);


         }


        }

