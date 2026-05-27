package com.taskflow.controller;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;

import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class GoogleTestController {

    @GetMapping("/google/token")
    public String getToken(

            @RegisteredOAuth2AuthorizedClient("google")
            OAuth2AuthorizedClient client

    ) {

        return client.getAccessToken().getTokenValue();
    }
}