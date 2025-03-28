package com.crud.youtube.controllers;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.crud.youtube.entities.User;
import com.crud.youtube.repositories.UserRepository;
import com.crud.youtube.repositories.UserRequest;

import jakarta.transaction.Transactional;

@Controller
@RequestMapping("/users")
public class UserController {
  
  @Autowired
  private UserRepository userRepository;

  //Criando usuário
  @PostMapping
  public ResponseEntity create(@RequestBody UserRequest userRequest){
    User user = new User(userRequest);
    userRepository.save(user);

    return ResponseEntity.ok( "Criado com sucesso!");
  } 

  //Listando usuário
  @GetMapping
  public ResponseEntity listUser(){
    var user = userRepository.findAll();

    return ResponseEntity.ok(user);
  }

  //Deletando usuário
  @DeleteMapping("{id}")
  public ResponseEntity deleteUser(@PathVariable("id") UUID id){
    Optional<User> userOptional = userRepository.findById(id);
    if(userOptional.isPresent()){
      userRepository.deleteById(id);

      return ResponseEntity.ok("Usuário deletado!");
    } 
    else{
      return ResponseEntity.notFound().build();
    }
  }

  @Transactional
  @PutMapping
  public ResponseEntity updateUser(@RequestBody UserRequest userRequest){
    Optional<User> userOptional = userRepository.findById(userRequest.id());
    if(userOptional.isPresent()){
      User user = userOptional.get();
      user.setName(userRequest.name());
      user.setEmail(userRequest.email());

      return ResponseEntity.ok(user);
    }
    else{
      return ResponseEntity.ok("Não encontrado...");
    }
  }

}
