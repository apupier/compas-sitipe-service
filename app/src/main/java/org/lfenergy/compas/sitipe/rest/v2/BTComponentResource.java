// SPDX-FileCopyrightText: 2023 Alliander N.V.
//
// SPDX-License-Identifier: Apache-2.0

package org.lfenergy.compas.sitipe.rest.v2;

import io.quarkus.security.Authenticated;
import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Uni;
import org.lfenergy.compas.sitipe.dto.ImportedComponentDTO;
import org.lfenergy.compas.sitipe.service.ImportedComponentService;
import org.lfenergy.compas.sitipe.dto.ImportedDataDTO;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Authenticated
@RequestScoped
@Path("/v2/btcomponents")
public class BTComponentResource {

    private final ImportedComponentService importedComponentService;

    @Inject
    public BTComponentResource(
        final ImportedComponentService importedComponentService
    ) {
        this.importedComponentService = importedComponentService;
    }

    @GET
    @Path("/{accessId}/imported")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Blocking
    public Uni<List<ImportedComponentDTO>> getImportedComponents(
        @PathParam("accessId") final String accessId
    ) {
        return Uni.createFrom().item(this.importedComponentService.getByAccessId(accessId));
    }

    @GET
    @Path("/imported/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Blocking
    public Uni<ImportedDataDTO> getImportedComponentData(
        @PathParam("id") final Integer id
    ) {
        final ImportedDataDTO data = this.importedComponentService.getImportedComponentData(id);

        return Uni.createFrom().item(data);
    }
}
