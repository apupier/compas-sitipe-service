// SPDX-FileCopyrightText: 2023 Alliander N.V.
//
// SPDX-License-Identifier: Apache-2.0

package org.lfenergy.compas.sitipe.data.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.lfenergy.compas.sitipe.data.entity.BTComponent;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class BTComponentRepository implements PanacheRepository<BTComponent> {

    public List<BTComponent> findBayTypicalComponentsByTypicalAccessId(final String accessId) {
        return this.list("typicalAccessId = ?1 AND toolName = ?2", accessId, "DIGSI 5");
    }
}
