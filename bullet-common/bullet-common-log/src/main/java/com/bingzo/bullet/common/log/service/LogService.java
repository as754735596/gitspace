package com.bingzo.bullet.common.log.service;

import com.bingzo.bullet.common.core.util.R;

public interface LogService<T> {

    R<Boolean> saveLog(T t);

}
