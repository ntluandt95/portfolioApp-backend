package com.revature.portfolio.models;

import lombok.Data;

@Data
public class jwtTokenModel {
    String jwt;
    public jwtTokenModel(String token){
        jwt = token;
    }

}
