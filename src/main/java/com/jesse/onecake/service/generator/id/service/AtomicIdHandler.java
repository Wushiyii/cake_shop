//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jesse.onecake.service.generator.id.service;

import com.jesse.onecake.service.generator.id.bean.Id;
import com.jesse.onecake.service.generator.id.bean.IdMeta;
import com.jesse.onecake.service.generator.id.timer.Timer;
import java.util.concurrent.atomic.AtomicReference;

public class AtomicIdHandler implements IdHandler {
    private AtomicReference<AtomicIdHandler.Variant> variant = new AtomicReference(new AtomicIdHandler.Variant());

    public AtomicIdHandler() {
    }

    public void handle(Timer timer, Id id, IdMeta meta) {
        AtomicIdHandler.Variant varOld;
        AtomicIdHandler.Variant varNew;
        do {
            varOld = (AtomicIdHandler.Variant)this.variant.get();
            long timestamp = timer.genTime();
            timer.validateTimestamp(varOld.lastTimestamp, timestamp);
            long sequence = varOld.sequence;
            if (timestamp == varOld.lastTimestamp) {
                sequence = sequence + 1L & meta.getSeqBitsMask();
                if (sequence == 0L) {
                    timestamp = timer.tillNextTimeUnit(varOld.lastTimestamp);
                }
            } else {
                sequence = 0L;
            }

            varNew = new AtomicIdHandler.Variant();
            varNew.sequence = sequence;
            varNew.lastTimestamp = timestamp;
        } while(!this.variant.compareAndSet(varOld, varNew));

    }

    public void reset() {
        this.variant = new AtomicReference(new AtomicIdHandler.Variant());
    }

    class Variant {
        private long sequence = 0L;
        private long lastTimestamp = -1L;

        Variant() {
        }
    }
}
