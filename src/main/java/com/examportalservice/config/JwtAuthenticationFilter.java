package com.examportalservice.config;

import com.examportalservice.service.impl.UserDetailServiceImpl;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final UserDetailServiceImpl userDetailService;
    private final JwtUtils jwtUtil;

    public JwtAuthenticationFilter(UserDetailServiceImpl userDetailService, JwtUtils jwtUtil) {
        this.userDetailService = userDetailService;
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
      final  String requestTokenHeader = request.getHeader("Authorization");

      String username = null;
      String jwtToken = null;

      if(requestTokenHeader!=null && requestTokenHeader.startsWith("Bearer ")){

          jwtToken = requestTokenHeader.substring(7);
          try {
              username = this.jwtUtil.extractUsername(jwtToken);
          } catch (ExpiredJwtException e){
              e.printStackTrace();
              System.out.println("Token has been expired!!");
          }catch (Exception e){
              e.printStackTrace();
              System.out.println("errorr");
          }
        }else {
          System.out.println("Invalid Token, Not start with Bearer String");
      }

      //validate
        if(username!=null && SecurityContextHolder.getContext().getAuthentication() == null){
            final UserDetails userDetails = this.userDetailService.loadUserByUsername(username);

            if(this.jwtUtil.validateToken(jwtToken,userDetails)){

                //token is valid
                UsernamePasswordAuthenticationToken usernamePasswordAuthentication = new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
                usernamePasswordAuthentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthentication);
            }

        }else {
            System.out.println("Token is not valid!!");
        }
        filterChain.doFilter(request,response);

    }
}
