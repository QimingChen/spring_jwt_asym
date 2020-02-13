package com.example.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

  @Autowired
  private JwtUtils jwtUtils;

  @GetMapping("/gettoken")
  public String getToken(){
    return jwtUtils.getToken();
  }

}
