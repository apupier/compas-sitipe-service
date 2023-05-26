// SPDX-FileCopyrightText: 2023 Alliander N.V.
//
// SPDX-License-Identifier: Apache-2.0

package org.lfenergy.compas.sitipe.service;

import org.lfenergy.compas.sitipe.SitipeProperties;
import org.lfenergy.compas.sitipe.data.repository.BayTypicalRepository;
import org.lfenergy.compas.sitipe.data.repository.SystemVersionRepository;
import org.lfenergy.compas.sitipe.dto.BayTypicalDTO;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ApplicationScoped
public class BayTypicalService {

    private final SystemVersionRepository systemVersionRepository;

    private final BayTypicalRepository bayTypicalRepository;

    private final SitipeProperties sitipeProperties;

    @Inject
    public BayTypicalService(final SystemVersionRepository systemVersionRepository, final BayTypicalRepository bayTypicalRepository, final SitipeProperties sitipeProperties) {
        this.systemVersionRepository = systemVersionRepository;
        this.bayTypicalRepository = bayTypicalRepository;

        this.sitipeProperties = sitipeProperties;
    }

    public List<BayTypicalDTO> getAssignedBayTypicals() {
        return this.systemVersionRepository.findByVersion(sitipeProperties.version())
            .stream()
            .flatMap(systemVersion -> systemVersion.getAssignedBayTypicals() != null ? Arrays.stream(systemVersion.getAssignedBayTypicals().split(",")) : Stream.empty())
            .flatMap(accessId -> bayTypicalRepository.findByAccessId(accessId).stream())
            .map(BayTypicalDTO::new)
            .collect(Collectors.toList());
    }
}
