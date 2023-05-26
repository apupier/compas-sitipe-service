// SPDX-FileCopyrightText: 2023 Alliander N.V.
//
// SPDX-License-Identifier: Apache-2.0

package org.lfenergy.compas.sitipe.rest.v2;

import io.quarkus.security.Authenticated;
import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Uni;
import org.lfenergy.compas.sitipe.dto.BTComponentDTO;
import org.lfenergy.compas.sitipe.service.BTComponentService;
import org.lfenergy.compas.sitipe.dto.BayTypicalDTO;
import org.lfenergy.compas.sitipe.service.BayTypicalService;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Authenticated
@RequestScoped
@Path("/v2/baytypicals")
public class BayTypicalResource {

    private final BayTypicalService bayTypicalService;
    private final BTComponentService btComponentService;

    @Inject
    public BayTypicalResource(final BayTypicalService bayTypicalService, final BTComponentService btComponentService) {
        this.bayTypicalService = bayTypicalService;
        this.btComponentService = btComponentService;
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Blocking
    public Uni<List<BayTypicalDTO>> getAssignedBayTypicals() {
        return Uni.createFrom().item(this.bayTypicalService.getAssignedBayTypicals());
    }

    @GET
    @Path("/{accessId}/components")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Blocking
    public Uni<List<BTComponentDTO>> getBayTypicalComponentsByAccessId(
        @PathParam("accessId") final String accessId
    ) {
        return Uni.createFrom().item(this.btComponentService.getBTComponentsByBayTypicalAccessId(accessId));
    }
}
