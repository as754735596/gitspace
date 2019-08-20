package com.bingzo.bullet.common.security.service;

import org.w3c.dom.Entity;

public interface UserService<T> {

    T loadUserByUsername(String username);
}
