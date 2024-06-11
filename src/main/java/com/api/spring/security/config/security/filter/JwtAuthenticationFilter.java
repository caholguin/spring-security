package com.api.spring.security.config.security.filter;

import com.api.spring.security.exception.ObjectNotFoundException;
import com.api.spring.security.model.security.User;
import com.api.spring.security.service.UserService;
import com.api.spring.security.service.auth.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {


    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    public JwtAuthenticationFilter(JwtService jwtService){
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException{

        System.out.println("************ entro al filtro JWT ************");

        //1. obtener encabezado http llamado authorization
        String authorizationHeader = request.getHeader("Authorization");
        if (!StringUtils.hasText(authorizationHeader) || !authorizationHeader.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }

        //2. Obtener token JWT desde el encabezado
        String jwt = authorizationHeader.split(" ")[1];

        //3. Obtener el subject/username desde el token
        // esta accion a su vez valida el formato del token, firma y fecha de expiracion
        String username = jwtService.extractUsername(jwt);

        //4. Setear objeto authentication frntro de cecurity context holder
        User user = userService.findByUsername(username).
                orElseThrow(() -> new ObjectNotFoundException("User  not found. Username: "+ username));

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, null,user.getAuthorities());
        authToken.setDetails(new WebAuthenticationDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authToken);

        //5. Ejecutar el registro de filtros
        filterChain.doFilter(request, response);
    }

}
