//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jesse.onecake.service.generator.id.provider;

import java.net.InetAddress;

import com.jesse.onecake.service.generator.id.util.IpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

public class DbMachineIdProvider implements MachineIdProvider {
    private static final Logger log = LoggerFactory.getLogger(DbMachineIdProvider.class);
    private long machineId;
    private JdbcTemplate jdbcTemplate;

    public DbMachineIdProvider() {
        log.info("IpConfigurableMachineIdProvider constructed.");
    }

    public void init() {
        InetAddress ipInfo = IpUtils.getHostInfo();
        if (ipInfo == null) {
            String msg = "Fail to get host IP address. Stop to initialize the DbMachineIdProvider provider.";
            log.error(msg);
            throw new IllegalStateException(msg);
        } else {
            Long id = null;

            try {
                id = (Long)this.jdbcTemplate.queryForObject("SELECT ID FROM DB_MACHINE_ID_PROVIDER WHERE IP = ?", new Object[]{ipInfo.getHostAddress()}, Long.class);
            } catch (EmptyResultDataAccessException var6) {
                log.error("No allocation before for ip {}.", ipInfo.getHostAddress());
            }

            if (id != null) {
                this.machineId = id;
            } else {
                log.info("Fail to get ID from DB for host IP address {}. Next step try to allocate one." + ipInfo.getHostAddress());
                int count = this.jdbcTemplate.update("UPDATE DB_MACHINE_ID_PROVIDER SET IP = ? where IP is null limit 1", new Object[]{ipInfo.getHostAddress()});
                String msg;
                if (count > 0 && count <= 1) {
                    try {
                        id = (Long)this.jdbcTemplate.queryForObject("SELECT ID FROM DB_MACHINE_ID_PROVIDER WHERE IP = ?", new Object[]{ipInfo.getHostAddress()}, Long.class);
                    } catch (EmptyResultDataAccessException var5) {
                        log.error("No allocation before for ip {}.", ipInfo.getHostAddress());
                    }

                    if (id == null) {
                        msg = String.format("Fail to get ID from DB for host IP address {} after allocation. Stop to initialize the DbMachineIdProvider provider.", ipInfo.getHostAddress());
                        log.error(msg);
                        throw new IllegalStateException(msg);
                    } else {
                        this.machineId = id;
                    }
                } else {
                    msg = String.format("Fail to allocte ID for host IP address {}. The {} records are updated. Stop to initialize the DbMachineIdProvider provider.", ipInfo.getHostAddress(), count);
                    log.error(msg);
                    throw new IllegalStateException(msg);
                }
            }
        }
    }

    public long getMachineId() {
        return this.machineId;
    }

    public void setMachineId(long machineId) {
        this.machineId = machineId;
    }

    public JdbcTemplate getJdbcTemplate() {
        return this.jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
