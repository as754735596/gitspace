package com.bingzo.bullet.auth.filter;

import cn.hutool.core.util.StrUtil;
import com.bingzo.bullet.auth.handler.CaptchaException;
import com.bingzo.bullet.auth.handler.ValidateCodeHandler;
import com.bingzo.bullet.common.core.constant.CommonConstants;
import com.bingzo.bullet.common.core.constant.SecurityConstants;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 验证码校验过滤器
 */
@Configuration
@AllArgsConstructor
public class ValidateCodeFilter extends OncePerRequestFilter {
    private RedisTemplate redisTemplate ;
    @Setter
    @Getter
    private ValidateCodeHandler failureHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if (StrUtil.containsAnyIgnoreCase(request.getRequestURI(), SecurityConstants.OAUTH_TOKEN_URL)&&
                StrUtil.equalsIgnoreCase("post",request.getMethod())) {
            try{
                checkCode(request,response);
            }catch (CaptchaException e){
                failureHandler.onAuthenticationFailure(request, response, e);
                return;
            }

        }
        filterChain.doFilter(request, response);

    }

    /**
     * 检查code
     *
     * @param request
     */
    @SneakyThrows
    private void checkCode(HttpServletRequest request,HttpServletResponse response) {
        String code = request.getParameter("code");

        if (StrUtil.isBlank(code)) {
            throw new CaptchaException("验证码不能为空");
        }

        String randomStr = request.getParameter("randomStr");

        //https://gitee.com/log4j/missile/issues/IWA0D
        String mobile = request.getParameter("mobile");
        if (StrUtil.isNotBlank(mobile)) {
            randomStr = mobile;
        }

        String key = CommonConstants.DEFAULT_CODE_KEY + randomStr;
        redisTemplate.setKeySerializer(new StringRedisSerializer());

        if (!redisTemplate.hasKey(key)) {
            throw new CaptchaException("验证码不合法");
        }

        Object codeObj = redisTemplate.opsForValue().get(key);

        if (codeObj == null) {
            throw new CaptchaException("验证码不合法");
        }

        String saveCode = codeObj.toString();
        if (StrUtil.isBlank(saveCode)) {
            redisTemplate.delete(key);
            throw new CaptchaException("验证码不合法");
        }

        if (!StrUtil.equals(saveCode, code)) {
            redisTemplate.delete(key);
            throw new CaptchaException("验证码不合法");
        }

        redisTemplate.delete(key);
    }



}
