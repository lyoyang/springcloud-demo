package com.lyoyang.lab.oauth.properties;

import lombok.Data;

@Data
public class LabClientsProperties {

    private String client;
    private String secret;
    private String grantType = "password,authorization_code,refresh_token";
    private String scope = "all";
}
