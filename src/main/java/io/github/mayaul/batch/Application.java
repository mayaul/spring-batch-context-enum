package io.github.mayaul.batch;

import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@EnableBatchProcessing
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class Application extends DefaultBatchConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    @Override
    public void setDataSource(@Qualifier("batchDataSource") DataSource batchDataSource) {
        super.setDataSource(batchDataSource);
    }

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "mayaul.batch.datasource")
    public DataSource batchDataSource() {
        return DataSourceBuilder.create()
                .build();
    }

}
