// SPDX-FileCopyrightText: 2023 Alliander N.V.
//
// SPDX-License-Identifier: Apache-2.0

package org.lfenergy.compas.sitipe.helper;

import org.lfenergy.compas.sitipe.data.repository.BTComponentRepository;
import org.lfenergy.compas.sitipe.data.repository.BayTypicalRepository;
import org.lfenergy.compas.sitipe.data.repository.ImportedComponentRepository;
import org.lfenergy.compas.sitipe.data.repository.SystemVersionRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class DatabaseCleaner {

    @Inject
    SystemVersionRepository systemVersionRepository;

    @Inject
    BayTypicalRepository bayTypicalRepository;

    @Inject
    BTComponentRepository btComponentRepository;

    @Inject
    ImportedComponentRepository importedComponentRepository;

    @Transactional
    public void cleanUp() {
        bayTypicalRepository.deleteAll();
        systemVersionRepository.deleteAll();
        btComponentRepository.deleteAll();
        importedComponentRepository.deleteAll();
    }
}
