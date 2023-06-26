// SPDX-FileCopyrightText: 2023 Alliander N.V.
//
// SPDX-License-Identifier: Apache-2.0

package org.lfenergy.compas.sitipe.service;

import jakarta.transaction.Transactional;
import org.lfenergy.compas.sitipe.data.repository.BTComponentRepository;
import org.lfenergy.compas.sitipe.dto.BTComponentDTO;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;

@ApplicationScoped
public class BTComponentService {

    private final BTComponentRepository btComponentRepository;

    @Inject
    public BTComponentService(
        final BTComponentRepository btComponentRepository
    ) {
        this.btComponentRepository = btComponentRepository;
    }

    @Transactional
    public List<BTComponentDTO> getBTComponentsByBayTypicalAccessId(final String accessId) {
        return btComponentRepository.findBayTypicalComponentsByTypicalAccessId(accessId)
            .stream()
            .map(BTComponentDTO::new)
            .toList();
    }

}
