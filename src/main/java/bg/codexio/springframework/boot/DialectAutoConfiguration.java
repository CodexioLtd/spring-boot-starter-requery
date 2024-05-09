package bg.codexio.springframework.boot;

import bg.codexio.springframework.data.jpa.requery.resolver.function.ExtendedMysqlDialect;
import bg.codexio.springframework.data.jpa.requery.resolver.function.ExtendedPostgresqlDialect;
import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.OracleDialect;
import org.hibernate.dialect.SQLServerDialect;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;
import java.util.function.Function;

@Configuration
@AutoConfigureBefore(HibernateJpaAutoConfiguration.class)
public class DialectAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public Function<String, Class<? extends Dialect>> dialectDeterminer() {
        return driverName -> switch (driverName) {
            case "com.microsoft.sqlserver.jdbc.SQLServerDriver" -> SQLServerDialect.class;
            case "oracle.jdbc.driver.OracleDriver" -> OracleDialect.class;
            default -> null;
        };
    }

    @Bean
    public HibernatePropertiesCustomizer customizeDialect(
            DataSourceProperties dataSourceProperties,
            Function<String, Class<? extends Dialect>> dialectDeterminer
    ) {
        var driverName = dataSourceProperties.determineDriverClassName();
        return properties -> properties.put(
                "hibernate.dialect",
                Objects
                        .requireNonNullElse(
                                dialectDeterminer.apply(driverName),
                                switch (driverName) {
                                    case "org.postgresql.Driver" -> ExtendedPostgresqlDialect.class;
                                    case "com.mysql.cj.jdbc.Driver" -> ExtendedMysqlDialect.class;
                                    default -> Dialect.class;
                                }
                        )
                        .getName()
        );
    }
}
