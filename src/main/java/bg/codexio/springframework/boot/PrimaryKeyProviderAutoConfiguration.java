package bg.codexio.springframework.boot;

import bg.codexio.springframework.data.jpa.requery.specification.joinColumn.types.enumType.LongTypePrimaryKeyProvider;
import bg.codexio.springframework.data.jpa.requery.specification.joinColumn.types.enumType.PrimaryKeyProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PrimaryKeyProviderAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public PrimaryKeyProvider primaryKeyProvider() {
        return new LongTypePrimaryKeyProvider();
    }
}
