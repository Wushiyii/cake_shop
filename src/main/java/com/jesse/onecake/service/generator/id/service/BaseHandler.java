//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jesse.onecake.service.generator.id.service;

import com.jesse.onecake.service.generator.id.bean.Id;
import com.jesse.onecake.service.generator.id.bean.IdMeta;
import com.jesse.onecake.service.generator.id.timer.Timer;


public abstract class BaseHandler implements IdHandler {
    protected long sequence = 0L;
    protected long lastTimestamp = -1L;

    public BaseHandler() {
    }

    public void handle(Timer timer, Id id, IdMeta meta) {
        long timestamp = timer.genTime();
        timer.validateTimestamp(this.lastTimestamp, timestamp);
        if (timestamp == this.lastTimestamp) {
            this.sequence = this.sequence + 1L & meta.getSeqBitsMask();
            if (this.sequence == 0L) {
                timestamp = timer.tillNextTimeUnit(this.lastTimestamp);
            }
        } else {
            this.sequence = 0L;
        }

        this.lastTimestamp = timestamp;
        id.setSeq(this.sequence);
        id.setTime(timestamp);
    }

    public void reset() {
        this.sequence = 0L;
        this.lastTimestamp = -1L;
    }
}
