
package com.jesse.onecake.service.generator.id.factory;

import java.beans.PropertyVetoException;

import com.jesse.onecake.service.generator.id.bean.IdType;
import com.jesse.onecake.service.generator.id.impl.IdServiceImpl;
import com.jesse.onecake.service.generator.id.provider.DbMachineIdProvider;
import com.jesse.onecake.service.generator.id.provider.IdService;
import com.jesse.onecake.service.generator.id.provider.IpConfigurableMachineIdProvider;
import com.jesse.onecake.service.generator.id.provider.PropertyMachineIdProvider;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class IdServiceFactoryBean {
    public IdServiceFactoryBean() {
    }

    public static IdService idService(long machineId, IdType type, long genMethod, long version) {
        IdService idService = constructPropertyIdService(machineId, type.value(), genMethod, version);
        return idService;
    }

    public static IdService idService(String ips, IdType type, long genMethod, long version) {
        IdService idService = constructIpConfigurableIdService(ips, type.value(), genMethod, version);
        return idService;
    }

    public static IdService idService(String dbUrl, String dbName, String dbUser, String dbPassword, IdType type, long genMethod, long version) {
        IdService idService = constructDbIdService(dbUrl, dbName, dbUser, dbPassword, type.value(), genMethod, version);
        return idService;
    }

    private static IdService constructPropertyIdService(long machineId, long type, long genMethod, long version) {
        PropertyMachineIdProvider propertyMachineIdProvider = new PropertyMachineIdProvider();
        propertyMachineIdProvider.setMachineId(machineId);
        IdServiceImpl idServiceImpl = new IdServiceImpl(type);
        idServiceImpl.setMachineIdProvider(propertyMachineIdProvider);
        idServiceImpl.setGenMethod(genMethod);
        idServiceImpl.setVersion(version);
        idServiceImpl.init();
        return idServiceImpl;
    }

    private static IdService constructIpConfigurableIdService(String ips, long type, long genMethod, long version) {
        IpConfigurableMachineIdProvider ipConfigurableMachineIdProvider = new IpConfigurableMachineIdProvider(ips);
        IdServiceImpl idServiceImpl = new IdServiceImpl(type);
        idServiceImpl.setMachineIdProvider(ipConfigurableMachineIdProvider);
        idServiceImpl.setGenMethod(genMethod);
        idServiceImpl.setVersion(version);
        idServiceImpl.init();
        return idServiceImpl;
    }

    private static IdService constructDbIdService(String dbUrl, String dbName, String dbUser, String dbPassword, long type, long genMethod, long version) {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        String jdbcDriver = "com.mysql.jdbc.Driver";

        try {
            comboPooledDataSource.setDriverClass(jdbcDriver);
        } catch (PropertyVetoException var16) {
            throw new IllegalStateException("Wrong JDBC driver ", var16);
        }

        comboPooledDataSource.setMinPoolSize(5);
        comboPooledDataSource.setMaxPoolSize(30);
        comboPooledDataSource.setIdleConnectionTestPeriod(20);
        comboPooledDataSource.setMaxIdleTime(25);
        comboPooledDataSource.setBreakAfterAcquireFailure(false);
        comboPooledDataSource.setCheckoutTimeout(3000);
        comboPooledDataSource.setAcquireRetryAttempts(50);
        comboPooledDataSource.setAcquireRetryDelay(1000);
        String url = String.format("jdbc:mysql://%s/%s?useUnicode=true&amp;characterEncoding=UTF-8&amp;autoReconnect=true", dbUrl, dbName);
        comboPooledDataSource.setJdbcUrl(url);
        comboPooledDataSource.setUser(dbUser);
        comboPooledDataSource.setPassword(dbPassword);
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setLazyInit(false);
        jdbcTemplate.setDataSource(comboPooledDataSource);
        DbMachineIdProvider dbMachineIdProvider = new DbMachineIdProvider();
        dbMachineIdProvider.setJdbcTemplate(jdbcTemplate);
        dbMachineIdProvider.init();
        IdServiceImpl idServiceImpl = new IdServiceImpl(type);
        idServiceImpl.setMachineIdProvider(dbMachineIdProvider);
        idServiceImpl.setGenMethod(genMethod);
        idServiceImpl.setVersion(version);
        idServiceImpl.init();
        return idServiceImpl;
    }
}
