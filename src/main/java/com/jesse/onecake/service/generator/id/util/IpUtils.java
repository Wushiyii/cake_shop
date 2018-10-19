
package com.jesse.onecake.service.generator.id.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IpUtils {
    private static final Logger log = LoggerFactory.getLogger(IpUtils.class);

    public IpUtils() {
    }

    public static InetAddress getHostInfo() {
        try {
            Enumeration networkIntfItator = NetworkInterface.getNetworkInterfaces();

            while(networkIntfItator.hasMoreElements()) {
                NetworkInterface intf = (NetworkInterface)networkIntfItator.nextElement();
                Enumeration ipAddrs = intf.getInetAddresses();

                while(ipAddrs.hasMoreElements()) {
                    InetAddress inetAddress = (InetAddress)ipAddrs.nextElement();
                    if (!inetAddress.isLoopbackAddress() && !inetAddress.isLinkLocalAddress() && inetAddress.isSiteLocalAddress()) {
                        return inetAddress;
                    }
                }
            }
        } catch (SocketException var4) {
            log.error("Fail to get Host info.", var4);
        }

        return null;
    }
}
