// SPDX-FileCopyrightText: 2023 Alliander N.V.
//
// SPDX-License-Identifier: Apache-2.0

package org.lfenergy.compas.sitipe.data.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.lfenergy.compas.sitipe.data.entity.BayTypical;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class BayTypicalRepository implements PanacheRepository<BayTypical> {

    public List<BayTypical> findByAccessId(final String accessId) {
        return this.list("accessId", accessId);
    }

}
