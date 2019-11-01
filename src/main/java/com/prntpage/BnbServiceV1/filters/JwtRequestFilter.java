package com.prntpage.BnbServiceV1.filters;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.prntpage.BnbServiceV1.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");

        DecodedJWT jwtToken = null;
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            jwtToken = jwtTokenUtil.verify(authHeader.substring(7));
        } else {
            logger.warn("JWT Token does not begin with Bearer String");
        }

        if(jwtToken != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            Collection<? extends GrantedAuthority> authorities = Arrays.asList(
                    jwtToken.getClaim("auth").toString().split(":")
            ).stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());

            User principal = new User(jwtToken.getSubject(), "test", authorities);

            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(principal, null, principal.getAuthorities());
            token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(token);
        }

        filterChain.doFilter(request, response);
    }

}