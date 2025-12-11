package com.codingShuttle.collegeMangementSystem.collegeManagementSystem.filters;

import com.codingShuttle.collegeMangementSystem.collegeManagementSystem.entity.User;
import com.codingShuttle.collegeMangementSystem.collegeManagementSystem.services.JwtService;
import com.codingShuttle.collegeMangementSystem.collegeManagementSystem.services.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    private final UserService userService;

    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver handlerExceptionResolver;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String requestTokenHeader = request.getHeader("Authorization");
        if (requestTokenHeader == null || !requestTokenHeader.startsWith("Bearer")) {
            filterChain.doFilter(request, response);
            return;
        }
            String token = requestTokenHeader.split("Bearer ")[1];
            Long userId = jwtService.getUserIdFromTokan(token);

            if(userId != null && SecurityContextHolder.getContext().getAuthentication() == null){  //Only do thi if I don't have authentication set inside the authentication holder.
                User user = userService.getUserById(userId);
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(user,null,null);
                authenticationToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().getAuthentication(); //Adding the user in spring security context holder
            }

            filterChain.doFilter(request,response);

    }
}

/*

So the whole concept of creating our new filter is like this
--- Scenario 1 ---
1. We extend the OncePerRequestFilter,tells Spring that this filter must run at most once per HTTP request, even if the request is forwarded or processed asynchronously inside the app
2. Now we call in the initial token that was generated while login to the application and store it at one place.
3. We are checking "Bearer", cause the token store starts with string "Bearer" only.
4. Now we get the token. After this, we will get the userId based of the token, we can also get email and other fields but here we are getting the email id only.
5. Now, our filter is created in the filter bucket, since we need to validate the token and username before UsernamePasswordAuthenticationToken, we call it and now pass the token and
    username.
6. Security context will get the values as authenticate, and then we can return the request and response.
7. Now, we have to add our filter to the webconfig so we add it there using methods like -- addFilterBefore, addFilterAfter, addFilterAt etc...
7. We are calling filterChain.doFilter(request,response), so that after checking out filter it will move to other filter.
--- EO scenario 1 ---




 */
