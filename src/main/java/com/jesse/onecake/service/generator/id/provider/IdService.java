package com.jesse.onecake.service.generator.id.provider;

import com.jesse.onecake.service.generator.id.bean.Id;

import java.util.Date;

public interface IdService {
    long genId();

    long[] batchGenId(int var1);

    Id expId(long var1);

    long makeId(long var1, long var3);

    long makeId(long var1, long var3, long var5);

    long makeId(long var1, long var3, long var5, long var7);

    long makeId(long var1, long var3, long var5, long var7, long var9);

    long makeId(long var1, long var3, long var5, long var7, long var9, long var11);

    Date transTime(long var1);
}
