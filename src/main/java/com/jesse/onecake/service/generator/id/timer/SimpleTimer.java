//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jesse.onecake.service.generator.id.timer;

import java.util.Date;

import com.jesse.onecake.service.generator.id.bean.IdMeta;
import com.jesse.onecake.service.generator.id.bean.IdType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleTimer implements Timer {
    private static final Logger log = LoggerFactory.getLogger(SimpleTimer.class);
    protected IdMeta meta;
    protected IdType idType;
    protected long maxTime;
    protected long epoch = 1514736000000L;

    public void init(IdMeta meta, IdType idType) {
        this.meta = meta;
        this.maxTime = (long)((1 << meta.getTimeBits()) - 1);
        this.idType = idType;
        this.genTime();
        this.timerUsedlog();
    }

    public void timerUsedlog() {
        Date expirationDate = this.transTime(this.maxTime);
        long days = (expirationDate.getTime() - System.currentTimeMillis()) / 86400000L;
        log.info("The current time bit length is {}, the expiration date is {}, this can be used for {} days.", new Object[]{this.meta.getTimeBits(), expirationDate, days});
    }

    public Date transTime(long time) {
        return this.idType == IdType.MILLISECONDS ? new Date(time + this.epoch) : new Date(time * 1000L + this.epoch);
    }

    public void validateTimestamp(long lastTimestamp, long timestamp) {
        if (timestamp < lastTimestamp) {
            if (log.isErrorEnabled()) {
                log.error(String.format("Clock moved backwards.  Refusing to generate id for %d second/milisecond.", lastTimestamp - timestamp));
            }

            throw new IllegalStateException(String.format("Clock moved backwards.  Refusing to generate id for %d second/milisecond.", lastTimestamp - timestamp));
        }
    }

    public long tillNextTimeUnit(long lastTimestamp) {
        if (log.isInfoEnabled()) {
            log.info(String.format("Ids are used out during %d. Waiting till next second/milisencond.", lastTimestamp));
        }

        long timestamp;
        for(timestamp = this.genTime(); timestamp <= lastTimestamp; timestamp = this.genTime()) {
            ;
        }

        if (log.isInfoEnabled()) {
            log.info(String.format("Next second/milisencond %d is up.", timestamp));
        }

        return timestamp;
    }

    public long genTime() {
        long time;
        if (this.idType == IdType.MILLISECONDS) {
            time = System.currentTimeMillis() - this.epoch;
        } else {
            time = (System.currentTimeMillis() - this.epoch) / 1000L;
        }

        this.validateTimestamp(time);
        return time;
    }

    protected void validateTimestamp(long timestamp) {
        if (timestamp > this.maxTime) {
            String error = String.format("The current timestamp (%s >= %s) has overflowed, Id Service will be terminate.", timestamp, this.maxTime);
            log.error(error);
            throw new RuntimeException(error);
        }
    }

    public SimpleTimer() {
    }

    public IdMeta getMeta() {
        return this.meta;
    }

    public IdType getIdType() {
        return this.idType;
    }

    public long getMaxTime() {
        return this.maxTime;
    }

    public long getEpoch() {
        return this.epoch;
    }

    public void setMeta(IdMeta meta) {
        this.meta = meta;
    }

    public void setIdType(IdType idType) {
        this.idType = idType;
    }

    public void setMaxTime(long maxTime) {
        this.maxTime = maxTime;
    }

    public void setEpoch(long epoch) {
        this.epoch = epoch;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof SimpleTimer)) {
            return false;
        } else {
            SimpleTimer other = (SimpleTimer)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label43: {
                    Object this$meta = this.getMeta();
                    Object other$meta = other.getMeta();
                    if (this$meta == null) {
                        if (other$meta == null) {
                            break label43;
                        }
                    } else if (this$meta.equals(other$meta)) {
                        break label43;
                    }

                    return false;
                }

                Object this$idType = this.getIdType();
                Object other$idType = other.getIdType();
                if (this$idType == null) {
                    if (other$idType != null) {
                        return false;
                    }
                } else if (!this$idType.equals(other$idType)) {
                    return false;
                }

                if (this.getMaxTime() != other.getMaxTime()) {
                    return false;
                } else if (this.getEpoch() != other.getEpoch()) {
                    return false;
                } else {
                    return true;
                }
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof SimpleTimer;
    }

    public int hashCode() {
        int result = 1;
        Object $meta = this.getMeta();
        result = result * 59 + ($meta == null ? 43 : $meta.hashCode());
        Object $idType = this.getIdType();
        result = result * 59 + ($idType == null ? 43 : $idType.hashCode());
        long $maxTime = this.getMaxTime();
        result = result * 59 + (int)($maxTime >>> 32 ^ $maxTime);
        long $epoch = this.getEpoch();
        result = result * 59 + (int)($epoch >>> 32 ^ $epoch);
        return result;
    }

    public String toString() {
        return "SimpleTimer(meta=" + this.getMeta() + ", idType=" + this.getIdType() + ", maxTime=" + this.getMaxTime() + ", epoch=" + this.getEpoch() + ")";
    }
}
