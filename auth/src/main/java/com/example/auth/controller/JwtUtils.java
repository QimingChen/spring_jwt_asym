package com.example.auth.controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class JwtUtils {

  private PrivateKey privateKey;

  @Autowired
  public void setPrivateKey() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
    Resource resource = new ClassPathResource("test.key");
    File file = resource.getFile();
    byte[] bytes = Files.readAllBytes(file.toPath());
    PKCS8EncodedKeySpec ks = new PKCS8EncodedKeySpec(bytes);
    KeyFactory kf = KeyFactory.getInstance("RSA");
    privateKey = kf.generatePrivate(ks);
  }

  public String getToken(){
    Map<String, Object> claims = new HashMap<>();
    claims.put("id", "xxx");
    claims.put("role", Arrays.asList("user", "admin"));
    return Jwts.builder().setClaims(claims)
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() +   1000000000))
        .signWith(SignatureAlgorithm.RS256, privateKey).compact();
  }
}