package com.jesse.onecake.service.generator.id.service;

import com.jesse.onecake.service.generator.id.bean.Id;
import com.jesse.onecake.service.generator.id.bean.IdMeta;
import com.jesse.onecake.service.generator.id.timer.Timer;


public class SyncIdHandler extends BaseHandler {
    public SyncIdHandler() {
    }

    public synchronized void handle(Timer timer, Id id, IdMeta meta) {
        super.handle(timer, id, meta);
    }
}
