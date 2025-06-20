package com.project.flutterbackendapi.common.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.flutterbackendapi.common.util.JwtTokenProvider;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        try {
            String token = jwtTokenProvider.resolveToken(request);

            if (token == null) {
                sendErrorResponse(response, "JWT 토큰이 존재하지 않습니다.");
                return;
            }

            if (jwtTokenProvider.validateToken(token)) {
                Authentication authentication = jwtTokenProvider.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                log.info("JWT 인증 성공: {}", authentication.getName());
            }

            filterChain.doFilter(request, response);

        } catch (SecurityException | IllegalArgumentException e) {
            // 토큰 형식이 잘못되었거나 기타 인증 관련 예외
            sendErrorResponse(response, "잘못된 JWT 토큰입니다.");
        } catch (ExpiredJwtException e) {
            sendErrorResponse(response, "만료된 JWT 토큰입니다.");
        } catch (Exception e) {
            sendErrorResponse(response, "JWT 인증에 실패했습니다.");
        }
    }

    private void sendErrorResponse(HttpServletResponse response, String message) throws IOException {

        log.error("songsongsong JWT 인증 오류: {}", message);

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json;charset=UTF-8");

        ApiResponse<?> apiResponse = ApiResponse.createError(500, message);

        ObjectMapper objectMapper = new ObjectMapper();
        String responseBody = objectMapper.writeValueAsString(apiResponse);

        response.getWriter().write(responseBody);
    }

}
