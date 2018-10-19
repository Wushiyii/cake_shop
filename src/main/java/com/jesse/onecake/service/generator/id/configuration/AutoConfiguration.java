//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jesse.onecake.service.generator.id.configuration;

import com.jesse.onecake.service.generator.id.bean.IdType;
import com.jesse.onecake.service.generator.id.factory.IdServiceFactoryBean;
import com.jesse.onecake.service.generator.id.provider.IdService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

@Configuration
public class AutoConfiguration {
    @Value("${id.providerType:property}")
    private String providerType;
    @Value("${id.type:seconds}")
    private String type;
    @Value("${id.method:0}")
    private Long genMethod;
    @Value("${id.version:0}")
    private Long version;
    @Value("${id.machine:-1}")
    private Long machineId;
    @Value("${id.ips:}")
    private String ips;
    @Value("${id.dbUrl:}")
    private String dbUrl;
    @Value("${id.dbUser:}")
    private String dbUser;
    @Value("${id.dbPwd:}")
    private String dbPwd;
    @Value("${id.dbName:}")
    private String dbName;

    public AutoConfiguration() {
    }

    @Bean
    public IdService idService() {
        if (!StringUtils.isEmpty(this.providerType) && !"property".equals(this.providerType)) {
            if ("ip".equals(this.providerType)) {
                return IdServiceFactoryBean.idService(this.ips, IdType.parse(this.type), this.genMethod, this.version);
            } else {
                return "db".equals(this.providerType) ? IdServiceFactoryBean.idService(this.dbUrl, this.dbName, this.dbUser, this.dbPwd, IdType.parse(this.type), this.genMethod, this.version) : IdServiceFactoryBean.idService(this.machineId, IdType.parse(this.type), this.genMethod, this.version);
            }
        } else {
            return IdServiceFactoryBean.idService(this.machineId, IdType.parse(this.type), this.genMethod, this.version);
        }
    }
}
