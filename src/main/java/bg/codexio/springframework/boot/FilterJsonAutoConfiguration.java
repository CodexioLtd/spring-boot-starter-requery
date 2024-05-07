package bg.codexio.springframework.boot;

import bg.codexio.springframework.data.jpa.requery.config.FilterJsonTypeConverter;
import bg.codexio.springframework.data.jpa.requery.config.FilterJsonTypeConverterImpl;
import bg.codexio.springframework.data.jpa.requery.resolver.FilterJsonArgumentResolver;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterJsonAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public FilterJsonTypeConverter filterJsonTypeConverter() {
        return new FilterJsonTypeConverterImpl();
    }

    @Bean
    public FilterJsonArgumentResolver filterJsonArgumentResolver(
            ObjectMapper objectMapper,
            FilterJsonTypeConverter converter
    ) {
        return new FilterJsonArgumentResolver(
                objectMapper,
                converter
        );
    }
}