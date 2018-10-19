package com.jesse.onecake.service.generator.id.convertor;

import com.jesse.onecake.service.generator.id.bean.Id;
import com.jesse.onecake.service.generator.id.bean.IdMeta;

public class IdConverterImpl implements IdConverter {
    public IdConverterImpl() {
    }

    public long id2long(Id id, IdMeta meta) {
        return this.doConvert(id, meta);
    }

    protected long doConvert(Id id, IdMeta meta) {
        long ret = 0L;
        ret |= id.getMachine();
        ret |= id.getSeq() << (int)meta.getSeqBitsStartPos();
        ret |= id.getTime() << (int)meta.getTimeBitsStartPos();
        ret |= id.getGenMethod() << (int)meta.getGenMethodBitsStartPos();
        ret |= id.getType() << (int)meta.getTypeBitsStartPos();
        ret |= id.getVersion() << (int)meta.getVersionBitsStartPos();
        return ret;
    }

    public Id long2Id(long id, IdMeta meta) {
        return this.doConvert(id, meta);
    }

    protected Id doConvert(long id, IdMeta meta) {
        Id ret = new Id();
        ret.setMachine(id & meta.getMachineBitsMask());
        ret.setSeq(id >>> (int)meta.getSeqBitsStartPos() & meta.getSeqBitsMask());
        ret.setTime(id >>> (int)meta.getTimeBitsStartPos() & meta.getTimeBitsMask());
        ret.setGenMethod(id >>> (int)meta.getGenMethodBitsStartPos() & meta.getGenMethodBitsMask());
        ret.setType(id >>> (int)meta.getTypeBitsStartPos() & meta.getTypeBitsMask());
        ret.setVersion(id >>> (int)meta.getVersionBitsStartPos() & meta.getVersionBitsMask());
        return ret;
    }
}
