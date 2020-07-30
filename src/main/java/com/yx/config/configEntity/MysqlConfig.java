package com.yx.config.configEntity;

import lombok.Data;

/**
 * @author LiuG
 * @DESCRIPTION
 * @create 2020/7/30
 */
@Data
public class MysqlConfig {
    private String entityPackage;
    private String mapperxmlDir;
    private String mybatiscfg;
    private String url;
    private String username;
    private String password;
    private int minPoolSize;
    private int maxPoolSize;
    private int maxLifetime;
    private int borrowConnectionTimeout;
    private int loginTimeout;
    private int maintenanceInterval;
    private int maxIdleTime;
    private String testQuery;
}
