// SPDX-FileCopyrightText: 2023 Alliander N.V.
//
// SPDX-License-Identifier: Apache-2.0

package org.lfenergy.compas.sitipe.rest.v1;

import io.quarkus.security.Authenticated;
import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Uni;
import org.lfenergy.compas.sitipe.rest.v1.model.BayTypicalResponse;
import org.lfenergy.compas.sitipe.service.BayTypicalService;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.stream.Collectors;

@Authenticated
@RequestScoped
@Path("/v1/baytypicals")
public class BayTypicalResource {
    
    private final BayTypicalService bayTypicalService;

    @Inject
    public BayTypicalResource(final BayTypicalService bayTypicalService) {
        this.bayTypicalService = bayTypicalService;
    }

    @GET
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    @Blocking
    public Uni<BayTypicalResponse> getAssignedBayTypicals() {
        var response = new BayTypicalResponse();

        response.setBayTypicals(
            this.bayTypicalService.getAssignedBayTypicals()
                .stream()
                .map(BayTypicalResponse.BayTypicalItem::new)
                .collect(Collectors.toList())
        );

        return Uni.createFrom().item(response);
    }

}
