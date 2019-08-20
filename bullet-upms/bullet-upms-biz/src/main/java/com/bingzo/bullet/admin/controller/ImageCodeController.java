package com.bingzo.bullet.admin.controller;


import com.bingzo.bullet.common.core.constant.CommonConstants;
import com.bingzo.bullet.common.core.constant.SecurityConstants;
import com.google.code.kaptcha.Producer;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@AllArgsConstructor
@Api(value = "file", tags = "验证码模块")
public class ImageCodeController {
    private static final String JPEG = "jpeg";
    private final Producer producer;
    private final RedisTemplate redisTemplate;

    @RequestMapping("/code")
    public ByteArrayResource createImage(String randomStr) {
        //生成验证码
        String text = producer.createText();
        BufferedImage image = producer.createImage(text);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.opsForValue().set(CommonConstants.DEFAULT_CODE_KEY + randomStr, text
                , SecurityConstants.CODE_TIME, TimeUnit.SECONDS);
        // 转换流信息写出
        FastByteArrayOutputStream os = new FastByteArrayOutputStream();
        try {
            ImageIO.write(image, JPEG, os);
        } catch (IOException e) {
            log.error("ImageIO write err", e);
            return null;
        }
        return new ByteArrayResource(os.toByteArray());

    }
}
