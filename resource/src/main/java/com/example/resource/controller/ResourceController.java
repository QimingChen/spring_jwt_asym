package com.example.resource.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceController {

  @Autowired
  private JwtUtils jwtUtils;

  @GetMapping("/parsetoken={token}")
  public String parseToken(@PathVariable String token){
    return jwtUtils.parseToken(token);
  }

}