package bg.codexio.springframework.boot;

import bg.codexio.springframework.data.jpa.requery.adapter.HttpFilterAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RequeryProperties {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final List<HttpFilterAdapter> activeAdapters;

    public RequeryProperties(
            AdapterProperties adapters,
            List<HttpFilterAdapter> availableAdapters
    ) {
        if (adapters.getActive() == null || adapters.getActive()
                                                    .isEmpty()) {
            this.activeAdapters = availableAdapters;
        } else {
            this.activeAdapters = availableAdapters.stream()
                                                   .filter(adapter -> adapters.getActive()
                                                                              .contains(adapter.getClass()
                                                                                               .getSimpleName()))
                                                   .collect(Collectors.toList());
        }
        for (var activeAdapter : this.activeAdapters) {
            this.logger.info(
                    "{} has been registered as an active adapter",
                    activeAdapter.getClass()
                                 .getSimpleName()
            );
        }
    }

    public List<HttpFilterAdapter> getActiveAdapters() {
        return this.activeAdapters;
    }

}

