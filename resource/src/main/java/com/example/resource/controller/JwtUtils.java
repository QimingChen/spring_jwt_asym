package com.example.resource.controller;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class JwtUtils {
  private PublicKey publicKey;

  @Autowired
  public void setPublicKey() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
    Resource resource = new ClassPathResource("test.pub");
    File file = resource.getFile();
    byte[] bytes = Files.readAllBytes(file.toPath());
    X509EncodedKeySpec ks = new X509EncodedKeySpec(bytes);
    KeyFactory kf = KeyFactory.getInstance("RSA");
    publicKey = kf.generatePublic(ks);
  }

  public String parseToken(String token){
    Claims claims;
		try {
			claims = Jwts.parser().setSigningKey(publicKey).parseClaimsJws(token).getBody();

			System.out.println(claims.get("id"));
			System.out.println(claims.get("role"));

		} catch (Exception e) {
			claims = null;
		}
    return claims.get("id")+" " +claims.get("role");
  }
}
