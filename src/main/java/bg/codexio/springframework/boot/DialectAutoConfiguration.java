package bg.codexio.springframework.boot;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AutoConfigureBefore(HibernateJpaAutoConfiguration.class)
public class DialectAutoConfiguration {
    @Bean
    @ConditionalOnProperty(name = "app.use-mysql", havingValue = "true")
    public HibernatePropertiesCustomizer mysqlHibernatePropertiesCustomizer() {
        return properties -> properties.put(
                "hibernate.dialect",
                "bg.codexio.springframework.data.jpa.requery.resolver"
                        + ".function.ExtendedMysqlDialect"
        );
    }

    @Bean
    @ConditionalOnProperty(
            name = "app.use-mysql",
            havingValue = "false",
            matchIfMissing = true
    )
    public HibernatePropertiesCustomizer postgresqlHibernatePropertiesCustomizer() {
        return properties -> properties.put(
                "hibernate.dialect",
                "bg.codexio.springframework.data.jpa.requery.resolver"
                        + ".function.ExtendedPostgresqlDialect"
        );
    }
}
