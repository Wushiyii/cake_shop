package com.jesse.onecake.service.generator.id.bean;

import java.beans.ConstructorProperties;

public class IdMeta {
    private byte machineBits;
    private byte seqBits;
    private byte timeBits;
    private byte genMethodBits;
    private byte typeBits;
    private byte versionBits;
    private static IdMeta maxGranularity = new IdMeta((byte)10, (byte)20, (byte)30, (byte)2, (byte)1, (byte)1);
    private static IdMeta minGranularity = new IdMeta((byte)10, (byte)10, (byte)40, (byte)2, (byte)1, (byte)1);

    public static IdMeta getIdMeta(IdType type) {
        if (IdType.SECONDS.equals(type)) {
            return maxGranularity;
        } else {
            return IdType.MILLISECONDS.equals(type) ? minGranularity : null;
        }
    }

    public byte getMachineBits() {
        return this.machineBits;
    }

    public void setMachineBits(byte machineBits) {
        this.machineBits = machineBits;
    }

    public long getMachineBitsMask() {
        return ~(-1L << this.machineBits);
    }

    public byte getSeqBits() {
        return this.seqBits;
    }

    public void setSeqBits(byte seqBits) {
        this.seqBits = seqBits;
    }

    public long getSeqBitsStartPos() {
        return (long)this.machineBits;
    }

    public long getSeqBitsMask() {
        return ~(-1L << this.seqBits);
    }

    public byte getTimeBits() {
        return this.timeBits;
    }

    public void setTimeBits(byte timeBits) {
        this.timeBits = timeBits;
    }

    public long getTimeBitsStartPos() {
        return (long)(this.machineBits + this.seqBits);
    }

    public long getTimeBitsMask() {
        return ~(-1L << this.timeBits);
    }

    public byte getGenMethodBits() {
        return this.genMethodBits;
    }

    public void setGenMethodBits(byte genMethodBits) {
        this.genMethodBits = genMethodBits;
    }

    public long getGenMethodBitsStartPos() {
        return (long)(this.machineBits + this.seqBits + this.timeBits);
    }

    public long getGenMethodBitsMask() {
        return ~(-1L << this.genMethodBits);
    }

    public byte getTypeBits() {
        return this.typeBits;
    }

    public void setTypeBits(byte typeBits) {
        this.typeBits = typeBits;
    }

    public long getTypeBitsStartPos() {
        return (long)(this.machineBits + this.seqBits + this.timeBits + this.genMethodBits);
    }

    public long getTypeBitsMask() {
        return ~(-1L << this.typeBits);
    }

    public byte getVersionBits() {
        return this.versionBits;
    }

    public void setVersionBits(byte versionBits) {
        this.versionBits = versionBits;
    }

    public long getVersionBitsStartPos() {
        return (long)(this.machineBits + this.seqBits + this.timeBits + this.genMethodBits + this.typeBits);
    }

    public long getVersionBitsMask() {
        return ~(-1L << this.versionBits);
    }

    @ConstructorProperties({"machineBits", "seqBits", "timeBits", "genMethodBits", "typeBits", "versionBits"})
    public IdMeta(byte machineBits, byte seqBits, byte timeBits, byte genMethodBits, byte typeBits, byte versionBits) {
        this.machineBits = machineBits;
        this.seqBits = seqBits;
        this.timeBits = timeBits;
        this.genMethodBits = genMethodBits;
        this.typeBits = typeBits;
        this.versionBits = versionBits;
    }
}
