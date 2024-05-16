package com.example.Practice23.config;

import com.example.Practice23.service.EntitySaveService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jmx.export.MBeanExporter;
import org.springframework.jmx.support.ObjectNameManager;

import javax.management.ObjectName;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
@Configuration
public class JMXConfig {

    @Bean
    public MBeanExporter exporter(EntitySaveService entitySaveService, DataSource dataSource) {
        MBeanExporter exporter = new MBeanExporter();
        exporter.setAutodetect(true);
        exporter.setExcludedBeans("entityManagerFactory"); // если нужно исключить некоторые бины
        exporter.setBeans(beanMap(entitySaveService, dataSource));
        return exporter;
    }

    private Map<String, Object> beanMap(Object entitySaveService, DataSource dataSource) {
        Map<String, Object> map = new HashMap<>();
        map.put("bean:name=entitySaveService", entitySaveService);
        map.put("bean:name=dataSource", dataSource);
        return map;
    }

    @Bean
    public ObjectName dataSourceObjectName() throws Exception {
        return ObjectNameManager.getInstance("com.example:type=DataSource");
    }
}