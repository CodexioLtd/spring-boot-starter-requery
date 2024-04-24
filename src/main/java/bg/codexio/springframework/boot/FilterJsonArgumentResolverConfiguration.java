package bg.codexio.springframework.boot;

import bg.codexio.springframework.data.jpa.requery.resolver.FilterJsonArgumentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class FilterJsonArgumentResolverConfiguration
        implements WebMvcConfigurer {
    private final FilterJsonArgumentResolver filterJsonArgumentResolver;

    public FilterJsonArgumentResolverConfiguration(FilterJsonArgumentResolver filterJsonArgumentResolver) {
        this.filterJsonArgumentResolver = filterJsonArgumentResolver;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(this.filterJsonArgumentResolver);
    }
}