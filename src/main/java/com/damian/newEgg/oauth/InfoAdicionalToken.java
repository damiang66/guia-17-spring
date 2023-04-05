package com.damian.newEgg.oauth;


import com.damian.newEgg.entity.Usuario;
import com.damian.newEgg.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
@Component
public class InfoAdicionalToken implements TokenEnhancer {
    @Autowired
    private UsuarioService usuarioService;
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
Usuario usuario = usuarioService.findBYUsername(oAuth2Authentication.getName());
        Map<String,Object> info = new HashMap<>();
        info.put("Nombre_usuario", usuario.getUsername());
info.put("info adicional", "hola que tal".concat(oAuth2Authentication.getName()));
        ((DefaultOAuth2AccessToken) oAuth2AccessToken).setAdditionalInformation(info);
        return oAuth2AccessToken;
    }
}
