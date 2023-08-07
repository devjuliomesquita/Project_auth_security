package com.juliomesquita.clientserver.application.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/home")
public class ClientController {
    @GetMapping(path = "/sign")
    public Mono<String> home(@RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient client, @AuthenticationPrincipal OidcUser user){
        return
                Mono.just("""
                        <h2> Acess Token: %s </h2>
                        <h2> Refrash Token: %s </h2>
                        <h2> Id Token: %s </h2>
                        <h2> Claims: %s </h2>
                        """.formatted(
                                client.getAccessToken().getTokenValue(),
                                client.getRefreshToken().getTokenValue(),
                                user.getIdToken().getTokenValue(),
                                user.getClaims()
                ));
    }
}
