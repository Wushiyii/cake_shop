//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jesse.onecake.service.generator.id.service;

import com.jesse.onecake.service.generator.id.bean.Id;
import com.jesse.onecake.service.generator.id.bean.IdMeta;
import com.jesse.onecake.service.generator.id.timer.Timer;

public interface IdHandler {
    void handle(Timer var1, Id var2, IdMeta var3);

    void reset();
}
