package com.cx_project.kiiun.global.auth.oauth.handler;

import com.cx_project.kiiun.global.auth.dto.CustomOAuth2User;
import com.cx_project.kiiun.global.auth.service.JwtService;
import com.cx_project.kiiun.global.service.RedisService;
import com.cx_project.kiiun.global.util.enums.cookie.CookieService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URLDecoder;

import static java.nio.charset.StandardCharsets.UTF_8;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtService jwtService;
    private final CookieService cookieService;
    private final RedisService redisService;
    private final String REDIRECT_URI_PARAM_COOKIE_NAME = "redirect_uri";



    @Override
    @Transactional
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        CustomOAuth2User oAuth2User = (CustomOAuth2User)authentication.getPrincipal();

        String accessToken = jwtService.createAccessToken(oAuth2User.getUserInfo().getId());
        String refreshToken = jwtService.createRefreshToken();
        // RefreshToken 쿠키, Redis에 저장
        setRefreshTokenInCookie(response, refreshToken);
        redisService.setRefreshToken(String.valueOf(oAuth2User.getUserInfo().getId()), refreshToken);

        String redirect_uri = CookieService.getCookie(request, REDIRECT_URI_PARAM_COOKIE_NAME)
                .map(cookie -> cookie.getValue())
                .map(cookie -> URLDecoder.decode(cookie, UTF_8))
                .orElse(getDefaultTargetUrl());


        String targetUrl = UriComponentsBuilder.fromUriString(redirect_uri)
                .queryParam("accessToken", accessToken)
                .build().toUriString();


        getRedirectStrategy().sendRedirect(request, response, targetUrl);

    }

    private void setRefreshTokenInCookie(HttpServletResponse response, String refreshToken) {
        cookieService.addCookie(response, "refreshToken", refreshToken, jwtService.getRefreshTokenExpire());
    }
}
