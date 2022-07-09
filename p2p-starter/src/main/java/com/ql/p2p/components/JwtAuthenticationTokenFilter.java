package com.ql.p2p.components;

import cn.hutool.core.util.StrUtil;
import com.ql.p2p.utils.JwtProperties;
import com.ql.p2p.utils.JwtTokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author wanqiuli
 * @date 2022/7/10 01:11
 */
@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Resource
    private JwtProperties jwtProperties;
    @Resource
    private JwtTokenUtils jwtTokenUtils;
    @Resource
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // 获取header
        String authHeader = request.getHeader(jwtProperties.getTokenHeader());
        // 校验header
        if (StrUtil.isNotBlank(authHeader) && authHeader.startsWith(jwtProperties.getTokenHead())) {
            // 获取token
            String authToken = authHeader.substring(jwtProperties.getTokenHead().length());
            // 解析username
            String username = jwtTokenUtils.getUserNameFromToken(authToken);
            if (StrUtil.isNotBlank(username) && SecurityContextHolder.getContext().getAuthentication() == null) {
                // 查询信息
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                // 校验token
                if (userDetails != null && jwtTokenUtils.validateToken(authToken, userDetails)) {
                    // 设置上下文
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    log.info("authenticated user " + username + ", setting security context");
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
