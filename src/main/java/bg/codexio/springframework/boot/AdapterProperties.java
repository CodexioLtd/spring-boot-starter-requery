package bg.codexio.springframework.boot;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "codexio.requery.adapters")
public class AdapterProperties {

    private List<String> active;

    public List<String> getActive() {
        return active;
    }

    public void setActive(List<String> active) {
        this.active = active;
    }
}