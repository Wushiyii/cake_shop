//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jesse.onecake.service.generator.id.provider;

import com.jesse.onecake.service.generator.id.util.IpUtils;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

public class IpConfigurableMachineIdProvider implements MachineIdProvider {
    private static final Logger log = LoggerFactory.getLogger(IpConfigurableMachineIdProvider.class);
    private long machineId;
    private Map<String, Long> ipsMap = new HashMap();

    public IpConfigurableMachineIdProvider(String ips) {
        this.setIps(ips);
        this.init();
    }

    public void init() {
        InetAddress ipInfo = IpUtils.getHostInfo();
        String msg;
        if (ipInfo == null) {
            msg = "Fail to get host IP address. Stop to initialize the IpConfigurableMachineIdProvider provider.";
            log.error(msg);
            throw new IllegalStateException(msg);
        } else if (!this.ipsMap.containsKey(ipInfo.getHostAddress())) {
            msg = String.format("Fail to configure ID for host IP address %s. Stop to initialize the IpConfigurableMachineIdProvider provider.", ipInfo.getHostAddress());
            log.error(msg);
            throw new IllegalStateException(msg);
        } else {
            this.machineId = (Long)this.ipsMap.get(ipInfo.getHostAddress());
            log.info("IpConfigurableMachineIdProvider.init ip {} id {}", ipInfo.getHostAddress(), this.machineId);
        }
    }

    public void setIps(String ips) {
        if (!StringUtils.isEmpty(ips)) {
            String[] ipArray = ips.split(",");

            for(int i = 0; i < ipArray.length; ++i) {
                this.ipsMap.put(ipArray[i], (long)i);
            }
        }

    }

    public long getMachineId() {
        return this.machineId;
    }

    public void setMachineId(long machineId) {
        this.machineId = machineId;
    }
}
