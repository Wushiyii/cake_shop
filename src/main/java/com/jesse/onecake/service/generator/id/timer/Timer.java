//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jesse.onecake.service.generator.id.timer;

import com.jesse.onecake.service.generator.id.bean.IdMeta;
import com.jesse.onecake.service.generator.id.bean.IdType;

import java.util.Date;

public interface Timer {
    long EPOCH = 1514736000000L;

    void init(IdMeta var1, IdType var2);

    Date transTime(long var1);

    void validateTimestamp(long var1, long var3);

    long tillNextTimeUnit(long var1);

    long genTime();
}
