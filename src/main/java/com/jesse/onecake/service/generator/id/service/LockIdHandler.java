package com.jesse.onecake.service.generator.id.service;

import com.jesse.onecake.service.generator.id.bean.Id;
import com.jesse.onecake.service.generator.id.bean.IdMeta;
import com.jesse.onecake.service.generator.id.timer.Timer;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockIdHandler extends BaseHandler {
    private Lock lock = new ReentrantLock();

    public LockIdHandler() {
    }

    public void handle(Timer timer, Id id, IdMeta meta) {
        this.lock.lock();

        try {
            super.handle(timer, id, meta);
        } finally {
            this.lock.unlock();
        }

    }
}
