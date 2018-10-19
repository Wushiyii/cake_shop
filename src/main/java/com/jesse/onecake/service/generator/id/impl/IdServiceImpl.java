//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jesse.onecake.service.generator.id.impl;

import java.util.Date;

import com.jesse.onecake.service.generator.id.bean.Id;
import com.jesse.onecake.service.generator.id.bean.IdMeta;
import com.jesse.onecake.service.generator.id.bean.IdType;
import com.jesse.onecake.service.generator.id.convertor.IdConverter;
import com.jesse.onecake.service.generator.id.convertor.IdConverterImpl;
import com.jesse.onecake.service.generator.id.provider.IdService;
import com.jesse.onecake.service.generator.id.provider.MachineIdProvider;
import com.jesse.onecake.service.generator.id.service.AtomicIdHandler;
import com.jesse.onecake.service.generator.id.service.IdHandler;
import com.jesse.onecake.service.generator.id.service.LockIdHandler;
import com.jesse.onecake.service.generator.id.service.SyncIdHandler;
import com.jesse.onecake.service.generator.id.timer.SimpleTimer;
import com.jesse.onecake.service.generator.id.timer.Timer;
import com.jesse.onecake.service.generator.id.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IdServiceImpl implements IdService {
    private static final Logger log = LoggerFactory.getLogger(IdServiceImpl.class);
    private static final String SYNC_LOCK_IMPL_KEY = "id.sync.lock.impl.key";
    private static final String ATOMIC_IMPL_KEY = "id.atomic.impl.key";
    protected long machineId = -1L;
    protected long genMethod = 0L;
    protected long version = 0L;
    protected IdType idType;
    protected IdMeta meta;
    protected IdConverter idConverter;
    protected MachineIdProvider machineIdProvider;
    protected Timer timer;
    protected IdHandler idHandler;

    public IdServiceImpl() {
        this.idType = IdType.SECONDS;
    }

    public IdServiceImpl(String type) {
        this.idType = IdType.parse(type);
    }

    public IdServiceImpl(long type) {
        this.idType = IdType.parse(type);
    }

    public IdServiceImpl(IdType idType) {
        this.idType = idType;
    }

    public void init() {
        if (this.meta == null) {
            this.setMeta(IdMeta.getIdMeta(this.idType));
        }

        if (this.idConverter == null) {
            this.setIdConverter(new IdConverterImpl());
        }

        if (this.timer == null) {
            this.setTimer(new SimpleTimer());
        }

        this.timer.init(this.meta, this.idType);
        this.machineId = this.machineIdProvider.getMachineId();
        this.validateMachineId(this.machineId);
        this.initHandler();
    }

    public void validateMachineId(long machineId) {
        if (machineId < 0L) {
            log.error("The machine ID is not configured properly (" + machineId + " < 0) so that Id Service refuses to start.");
            throw new IllegalStateException("The machine ID is not configured properly (" + machineId + " < 0) so that Id Service refuses to start.");
        } else if (machineId >= (long)(1 << this.meta.getMachineBits())) {
            log.error("The machine ID is not configured properly (" + machineId + " >= " + (1 << this.meta.getMachineBits()) + ") so that Id Service refuses to start.");
            throw new IllegalStateException("The machine ID is not configured properly (" + machineId + " >= " + (1 << this.meta.getMachineBits()) + ") so that Id Service refuses to start.");
        }
    }

    public void initHandler() {
        if (this.idHandler != null) {
            log.info("The " + this.idHandler.getClass().getCanonicalName() + " is used.");
        } else if (CommonUtils.isPropKeyOn("id.sync.lock.impl.key")) {
            log.info("The SyncIdHandler is used.");
            this.idHandler = new SyncIdHandler();
        } else if (CommonUtils.isPropKeyOn("id.atomic.impl.key")) {
            log.info("The AtomicIdHandler is used.");
            this.idHandler = new AtomicIdHandler();
        } else {
            log.info("The default LockIdHandler is used.");
            this.idHandler = new LockIdHandler();
        }

    }

    public long[] batchGenId(int count) {
        long[] ret = new long[count];
        Id id = new Id();
        id.setMachine(this.machineId);
        id.setGenMethod(this.genMethod);
        id.setType(this.idType.value());
        id.setVersion(this.version);

        for(int i = 0; i < count; ++i) {
            this.idHandler.handle(this.timer, id, this.meta);
            long idL = this.idConverter.id2long(id, this.meta);
            ret[i] = idL;
        }

        return ret;
    }

    public long genId() {
        Id id = new Id();
        id.setMachine(this.machineId);
        id.setGenMethod(this.genMethod);
        id.setType(this.idType.value());
        id.setVersion(this.version);
        this.idHandler.handle(this.timer, id, this.meta);
        long ret = this.idConverter.id2long(id, this.meta);
        if (log.isTraceEnabled()) {
            log.trace(String.format("Id: %s => %d", id, ret));
        }

        return ret;
    }

    public Id expId(long id) {
        return this.idConverter.long2Id(id, this.meta);
    }

    public long makeId(long time, long seq) {
        return this.makeId(time, seq, this.machineId);
    }

    public long makeId(long time, long seq, long machine) {
        return this.makeId(this.genMethod, time, seq, machine);
    }

    public long makeId(long genMethod, long time, long seq, long machine) {
        return this.makeId(this.idType.value(), genMethod, time, seq, machine);
    }

    public long makeId(long type, long genMethod, long time, long seq, long machine) {
        return this.makeId(this.version, type, genMethod, time, seq, machine);
    }

    public long makeId(long version, long type, long genMethod, long time, long seq, long machine) {
        Id id = new Id(machine, seq, time, genMethod, type, version);
        return this.idConverter.id2long(id, this.meta);
    }

    public Date transTime(long time) {
        return this.timer.transTime(time);
    }

    public long getMachineId() {
        return this.machineId;
    }

    public long getGenMethod() {
        return this.genMethod;
    }

    public long getVersion() {
        return this.version;
    }

    public IdType getIdType() {
        return this.idType;
    }

    public IdMeta getMeta() {
        return this.meta;
    }

    public IdConverter getIdConverter() {
        return this.idConverter;
    }

    public MachineIdProvider getMachineIdProvider() {
        return this.machineIdProvider;
    }

    public Timer getTimer() {
        return this.timer;
    }

    public IdHandler getIdHandler() {
        return this.idHandler;
    }

    public void setMachineId(long machineId) {
        this.machineId = machineId;
    }

    public void setGenMethod(long genMethod) {
        this.genMethod = genMethod;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public void setIdType(IdType idType) {
        this.idType = idType;
    }

    public void setMeta(IdMeta meta) {
        this.meta = meta;
    }

    public void setIdConverter(IdConverter idConverter) {
        this.idConverter = idConverter;
    }

    public void setMachineIdProvider(MachineIdProvider machineIdProvider) {
        this.machineIdProvider = machineIdProvider;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public void setIdHandler(IdHandler idHandler) {
        this.idHandler = idHandler;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof IdServiceImpl)) {
            return false;
        } else {
            IdServiceImpl other = (IdServiceImpl)o;
            if (!other.canEqual(this)) {
                return false;
            } else if (this.getMachineId() != other.getMachineId()) {
                return false;
            } else if (this.getGenMethod() != other.getGenMethod()) {
                return false;
            } else if (this.getVersion() != other.getVersion()) {
                return false;
            } else {
                Object this$idType = this.getIdType();
                Object other$idType = other.getIdType();
                if (this$idType == null) {
                    if (other$idType != null) {
                        return false;
                    }
                } else if (!this$idType.equals(other$idType)) {
                    return false;
                }

                Object this$meta = this.getMeta();
                Object other$meta = other.getMeta();
                if (this$meta == null) {
                    if (other$meta != null) {
                        return false;
                    }
                } else if (!this$meta.equals(other$meta)) {
                    return false;
                }

                label76: {
                    Object this$idConverter = this.getIdConverter();
                    Object other$idConverter = other.getIdConverter();
                    if (this$idConverter == null) {
                        if (other$idConverter == null) {
                            break label76;
                        }
                    } else if (this$idConverter.equals(other$idConverter)) {
                        break label76;
                    }

                    return false;
                }

                Object this$machineIdProvider = this.getMachineIdProvider();
                Object other$machineIdProvider = other.getMachineIdProvider();
                if (this$machineIdProvider == null) {
                    if (other$machineIdProvider != null) {
                        return false;
                    }
                } else if (!this$machineIdProvider.equals(other$machineIdProvider)) {
                    return false;
                }

                Object this$timer = this.getTimer();
                Object other$timer = other.getTimer();
                if (this$timer == null) {
                    if (other$timer != null) {
                        return false;
                    }
                } else if (!this$timer.equals(other$timer)) {
                    return false;
                }

                Object this$idHandler = this.getIdHandler();
                Object other$idHandler = other.getIdHandler();
                if (this$idHandler == null) {
                    if (other$idHandler != null) {
                        return false;
                    }
                } else if (!this$idHandler.equals(other$idHandler)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof IdServiceImpl;
    }

    public int hashCode() {
        int result = 1;
        long $machineId = this.getMachineId();
        result = result * 59 + (int)($machineId >>> 32 ^ $machineId);
        long $genMethod = this.getGenMethod();
        result = result * 59 + (int)($genMethod >>> 32 ^ $genMethod);
        long $version = this.getVersion();
        result = result * 59 + (int)($version >>> 32 ^ $version);
        Object $idType = this.getIdType();
        result = result * 59 + ($idType == null ? 43 : $idType.hashCode());
        Object $meta = this.getMeta();
        result = result * 59 + ($meta == null ? 43 : $meta.hashCode());
        Object $idConverter = this.getIdConverter();
        result = result * 59 + ($idConverter == null ? 43 : $idConverter.hashCode());
        Object $machineIdProvider = this.getMachineIdProvider();
        result = result * 59 + ($machineIdProvider == null ? 43 : $machineIdProvider.hashCode());
        Object $timer = this.getTimer();
        result = result * 59 + ($timer == null ? 43 : $timer.hashCode());
        Object $idHandler = this.getIdHandler();
        result = result * 59 + ($idHandler == null ? 43 : $idHandler.hashCode());
        return result;
    }

    public String toString() {
        return "IdServiceImpl(machineId=" + this.getMachineId() + ", genMethod=" + this.getGenMethod() + ", version=" + this.getVersion() + ", idType=" + this.getIdType() + ", meta=" + this.getMeta() + ", idConverter=" + this.getIdConverter() + ", machineIdProvider=" + this.getMachineIdProvider() + ", timer=" + this.getTimer() + ", idHandler=" + this.getIdHandler() + ")";
    }
}
