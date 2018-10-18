package com.jesse.onecake.service.generator.id.bean;

import java.io.Serializable;

public class Id implements Serializable {
    private static final long serialVersionUID = 6870931236218221183L;
    public static final long INIT_SEQUEUE = 0L;
    public static final long INIT_TIMESTAMP = -1L;
    private long machine;
    private long seq;
    private long time;
    private long genMethod;
    private long type;
    private long version;

    public Id() {
    }

    public Id(long machine, long seq, long time, long genMethod, long type, long version) {
        this.machine = machine;
        this.seq = seq;
        this.time = time;
        this.genMethod = genMethod;
        this.type = type;
        this.version = version;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append("machine=").append(this.machine).append(",");
        sb.append("seq=").append(this.seq).append(",");
        sb.append("time=").append(this.time).append(",");
        sb.append("genMethod=").append(this.genMethod).append(",");
        sb.append("type=").append(this.type).append(",");
        sb.append("version=").append(this.version).append("]");
        return sb.toString();
    }

    public long getMachine() {
        return this.machine;
    }

    public long getSeq() {
        return this.seq;
    }

    public long getTime() {
        return this.time;
    }

    public long getGenMethod() {
        return this.genMethod;
    }

    public long getType() {
        return this.type;
    }

    public long getVersion() {
        return this.version;
    }

    public void setMachine(long machine) {
        this.machine = machine;
    }

    public void setSeq(long seq) {
        this.seq = seq;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public void setGenMethod(long genMethod) {
        this.genMethod = genMethod;
    }

    public void setType(long type) {
        this.type = type;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Id)) {
            return false;
        } else {
            Id other = (Id)o;
            if (!other.canEqual(this)) {
                return false;
            } else if (this.getMachine() != other.getMachine()) {
                return false;
            } else if (this.getSeq() != other.getSeq()) {
                return false;
            } else if (this.getTime() != other.getTime()) {
                return false;
            } else if (this.getGenMethod() != other.getGenMethod()) {
                return false;
            } else if (this.getType() != other.getType()) {
                return false;
            } else {
                return this.getVersion() == other.getVersion();
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof Id;
    }

    public int hashCode() {
        int result = 1;
        long $machine = this.getMachine();
        result = result * 59 + (int)($machine >>> 32 ^ $machine);
        long $seq = this.getSeq();
        result = result * 59 + (int)($seq >>> 32 ^ $seq);
        long $time = this.getTime();
        result = result * 59 + (int)($time >>> 32 ^ $time);
        long $genMethod = this.getGenMethod();
        result = result * 59 + (int)($genMethod >>> 32 ^ $genMethod);
        long $type = this.getType();
        result = result * 59 + (int)($type >>> 32 ^ $type);
        long $version = this.getVersion();
        result = result * 59 + (int)($version >>> 32 ^ $version);
        return result;
    }
}
