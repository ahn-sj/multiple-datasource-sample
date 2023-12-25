package org.tally.multipledatasourcesample.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

@Data
@ConfigurationProperties(prefix = "spring.datasource")
public class DatabaseProperties {
    private One one;
    private Two two;

    @Data
    public static class One {
        private String driverClassName;
        private String url;
        private String username;
        private String password;
        private Hibernate hibernate;

    }

    @Data
    public static class Two {
        private String driverClassName;
        private String url;
        private String username;
        private String password;
        private Hibernate hibernate;

    }

    @Data
    public static class Hibernate {
        private String ddlAuto;
        private String dialect;
        private Naming naming;

        public static Map<String, Object> propertiesToMap(Hibernate hibernateProperties) {
            Map<String, Object> properties = new HashMap<>();

            if(hibernateProperties.getDdlAuto() != null) {
                properties.put("hibernate.hbm2ddl.auto", hibernateProperties.getDdlAuto());
            }
            if(hibernateProperties.getDialect() != null) {
                properties.put("hibernate.dialect", hibernateProperties.getDialect());
            }

            DatabaseProperties.Naming hibernateNaming = hibernateProperties.getNaming();
            if(hibernateNaming != null) {
                if (hibernateNaming.getImplicitStrategy() != null) {
                    properties.put("hibernate.implicit_naming_strategy",  hibernateNaming.getImplicitStrategy());
                }
                if (hibernateNaming.getPhysicalStrategy() != null) {
                    properties.put("hibernate.physical_naming_strategy", hibernateNaming.getPhysicalStrategy());
                }
            }

            return properties;
        }
    }

    @Data
    public static class Naming {
        private String implicitStrategy;
        private String physicalStrategy;
    }
}
