package org.lfenergy.compas.sitipe;

import io.smallrye.config.ConfigMapping;
import io.smallrye.config.WithName;

@ConfigMapping(prefix = "sitipe")
public interface SitipeProperties {

    @WithName("version")
    String version();

}
