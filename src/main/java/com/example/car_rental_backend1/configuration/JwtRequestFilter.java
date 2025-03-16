package com.example.car_rental_backend1.configuration;

import com.example.car_rental_backend1.service.JwtService;
import com.example.car_rental_backend1.service.impl.JwtServiceIMPL;
import com.example.car_rental_backend1.util.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private JwtServiceIMPL jwtServiceIMPL;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String requestTokenHeader = request.getHeader("Authorization");

        // Bypass filter for public endpoints
        String requestURI = request.getRequestURI();
        if (requestURI.equals("/api/v1/user/authenticate") || requestURI.equals("/api/v1/user/register-new-user")){
            filterChain.doFilter(request, response);
            return;
        }

//        String requestURI = request.getRequestURI();
//        if (requestURI.equals("/api/v1/user/authenticate") || requestURI.equals("/api/v1/user/register-new-user")
//                || requestURI.equals("/api/v1/car_type/add-new-type")
//                || requestURI.equals("/api/v1/car_type/get-all-car-types")
//                || requestURI.equals("/api/v1/car_type/delete-car-type-by-id")
//                || requestURI.equals("/api/v1/car_type/get-car-type-by-id")
//                || requestURI.equals("/api/v1/car_type/update-car-type")
//        ){
//            filterChain.doFilter(request, response);
//            return;
//        }

        String userName = null;
        String jwtToken = null;

        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer")) {
            jwtToken = requestTokenHeader.substring(7);

            try {
                userName = jwtUtil.getUsernameFromToken(jwtToken);

            } catch (IllegalArgumentException e) {
                System.out.println("Unable to get jwt token");
                throw new RuntimeException("Unable to get jwt token");

            } catch (ExpiredJwtException e ) {
                System.out.println("Jwt Token is expired");
                throw new RuntimeException("Jwt Token is expired");
            }
        } else {
            System.out.println("Jwt Token is not start with Bearer");
            throw new RuntimeException("Jwt Token is not start with Bearer");
        }

        if (userName !=null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = jwtServiceIMPL.loadUserByUsername(userName);

            if (jwtUtil.validateToken(jwtToken,userDetails)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails,null,userDetails.getAuthorities()
                );

                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

            } else {
                System.out.println("Jwt Token is not valid");
                throw new RuntimeException("Jwt Token is not valid");

            }
            filterChain.doFilter(request,response);
        }
    }
}
