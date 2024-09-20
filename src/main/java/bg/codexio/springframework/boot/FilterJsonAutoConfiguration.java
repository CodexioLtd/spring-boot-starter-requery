package bg.codexio.springframework.boot;

import bg.codexio.springframework.data.jpa.requery.adapter.HttpFilterAdapter;
import bg.codexio.springframework.data.jpa.requery.config.FilterJsonTypeConverter;
import bg.codexio.springframework.data.jpa.requery.config.FilterJsonTypeConverterImpl;
import bg.codexio.springframework.data.jpa.requery.resolver.FilterJsonArgumentResolver;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@EnableConfigurationProperties
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
    public AdapterProperties adapterProperties() {
        return new AdapterProperties();
    }

    @Bean
    public RequeryProperties requeryProperties(List<HttpFilterAdapter> availableAdapters) {
        return new RequeryProperties(
                adapterProperties(),
                availableAdapters
        );
    }

    @Bean
    public FilterJsonArgumentResolver filterJsonArgumentResolver(
            RequeryProperties requeryProperties,
            FilterJsonTypeConverter converter
    ) {
        return new FilterJsonArgumentResolver(
                converter,
                requeryProperties.getActiveAdapters()
        );
    }
}