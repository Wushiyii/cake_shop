package com.jesse.onecake.service.generator.id.convertor;


import com.jesse.onecake.service.generator.id.bean.Id;
import com.jesse.onecake.service.generator.id.bean.IdMeta;

public interface IdConverter {
    long id2long(Id var1, IdMeta var2);

    Id long2Id(long var1, IdMeta var3);
}
