package com.jeorigagye.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.http.HttpServletRequest;

import static lombok.extern.slf4j.Slf4j.*;

@Slf4j
public class AuthToken {

    @Value("${security.jwtKey}")
    private static String jwtKey;

   public static boolean tokenCheck(HttpServletRequest request){

       String token = request.getHeader("Authorization");

       if(!token.equals("null")){
           return true;
       }

       return false;
   }

   public static Long tokenParse(HttpServletRequest request){

       String token = request.getHeader("Authorization").replace("Bearer ", "").trim();

       Long memberId = Long.valueOf(JWT.require(Algorithm.HMAC512("bbung")).build().verify(token).getClaim("id").asLong());

       return memberId;
   }

}
