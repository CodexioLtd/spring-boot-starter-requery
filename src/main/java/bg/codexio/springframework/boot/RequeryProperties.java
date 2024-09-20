package bg.codexio.springframework.boot;

import bg.codexio.springframework.data.jpa.requery.adapter.HttpFilterAdapter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RequeryProperties {
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
    }

    public List<HttpFilterAdapter> getActiveAdapters() {
        return this.activeAdapters;
    }

}

