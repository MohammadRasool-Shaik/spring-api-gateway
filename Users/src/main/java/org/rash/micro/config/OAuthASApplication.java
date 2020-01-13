package org.rash.micro.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableAuthorizationServer
public class OAuthASApplication implements AuthorizationServerConfigurer {


    @Bean
    UserDetailsService getUserDeailService() {
        return new InMemoryUserDetailsManager(User.withUsername("user").password("user").roles("ROLE_USER").build());
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer authorizationServerSecurityConfigurer) throws Exception {
        authorizationServerSecurityConfigurer.passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clientDetailsServiceConfigurer) throws Exception {
        clientDetailsServiceConfigurer.inMemory().withClient("internal")
                .secret("internal_secret")
                .scopes("account", "contacts", "internal")
                .resourceIds()
                .authorizedGrantTypes("password", "refresh_token")
                .autoApprove(true)
                .accessTokenValiditySeconds(10 * 60)
                .refreshTokenValiditySeconds(30 * 60);

    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer authorizationServerEndpointsConfigurer) throws Exception {
    authorizationServerEndpointsConfigurer.userDetailsService(getUserDeailService()).reuseRefreshTokens(false);
    }
}
