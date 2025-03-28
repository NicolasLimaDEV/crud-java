package com.crud.youtube.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.crud.youtube.entities.User;
import com.crud.youtube.repositories.UserRepository;
import com.crud.youtube.repositories.UserRequest;

@Controller
@RequestMapping("/users")
public class UserController {
  
  @Autowired
  private UserRepository userRepository;

  @PostMapping
  public ResponseEntity create(@RequestBody UserRequest userRequest){
    User user = new User(userRequest);
    userRepository.save(user);

    return ResponseEntity.ok( "salvo");
  } 

  @GetMapping
  public ResponseEntity listUser(){
    var user = userRepository.findAll();

    return ResponseEntity.ok(user);
  }
}
