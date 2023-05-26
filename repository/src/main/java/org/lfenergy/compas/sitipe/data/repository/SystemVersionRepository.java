// SPDX-FileCopyrightText: 2023 Alliander N.V.
//
// SPDX-License-Identifier: Apache-2.0

package org.lfenergy.compas.sitipe.data.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.lfenergy.compas.sitipe.data.entity.SystemVersion;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class SystemVersionRepository implements PanacheRepository<SystemVersion> {

    public List<SystemVersion> findByVersion(final String version) {
        return this.list("version", version);
    }
}
