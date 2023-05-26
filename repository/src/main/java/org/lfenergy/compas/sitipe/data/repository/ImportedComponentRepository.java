// SPDX-FileCopyrightText: 2023 Alliander N.V.
//
// SPDX-License-Identifier: Apache-2.0

package org.lfenergy.compas.sitipe.data.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import org.lfenergy.compas.sitipe.data.entity.ImportedComponent;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.LockModeType;
import java.util.List;

@ApplicationScoped
public class ImportedComponentRepository implements PanacheRepositoryBase<ImportedComponent, Integer> {

    public List<ImportedComponent> getByAccessId(final String accessId) {
        return this.list("componentAccessId = ?1 AND type = ?2", accessId, "IID");
    }

    public ImportedComponent getById(final Integer id) {
        return this.findById(id, LockModeType.NONE);
    }
}
