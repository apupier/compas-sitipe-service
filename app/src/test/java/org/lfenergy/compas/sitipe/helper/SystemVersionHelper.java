// SPDX-FileCopyrightText: 2023 Alliander N.V.
//
// SPDX-License-Identifier: Apache-2.0

package org.lfenergy.compas.sitipe.helper;

import org.lfenergy.compas.sitipe.SitipeProperties;
import org.lfenergy.compas.sitipe.data.entity.BTComponent;
import org.lfenergy.compas.sitipe.data.entity.BayTypical;
import org.lfenergy.compas.sitipe.data.entity.ImportedComponent;
import org.lfenergy.compas.sitipe.data.entity.SystemVersion;
import org.lfenergy.compas.sitipe.data.repository.BTComponentRepository;
import org.lfenergy.compas.sitipe.data.repository.BayTypicalRepository;
import org.lfenergy.compas.sitipe.data.repository.ImportedComponentRepository;
import org.lfenergy.compas.sitipe.data.repository.SystemVersionRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.io.IOException;
import java.util.*;

import static java.util.Arrays.asList;

@ApplicationScoped
@Transactional
public class SystemVersionHelper {

    private final SystemVersionRepository systemVersionRepository;
    private final BayTypicalRepository bayTypicalRepository;

    private final BTComponentRepository btComponentRepository;

    private final ImportedComponentRepository importedComponentRepository;

    private final SitipeProperties sitipeProperties;

    private final CompressionUtils compressionUtils;
    @Inject
    public SystemVersionHelper(
        final SystemVersionRepository systemVersionRepository,
        final BayTypicalRepository bayTypicalRepository,
        final BTComponentRepository btComponentRepository,
        final ImportedComponentRepository importedComponentRepository,
        final SitipeProperties sitipeProperties,
        final CompressionUtils compressionUtils
    ) {
        this.systemVersionRepository = systemVersionRepository;
        this.bayTypicalRepository = bayTypicalRepository;
        this.btComponentRepository = btComponentRepository;
        this.importedComponentRepository = importedComponentRepository;

        this.sitipeProperties = sitipeProperties;

        this.compressionUtils = compressionUtils;
    }

    public SystemVersion createAndStoreSystemVersion(final Long id, final String version) {
        final SystemVersion systemVersion = new SystemVersion();

        systemVersion.setId(id);
        systemVersion.setVersion(version);

        systemVersion.setComment("TEST");
        systemVersion.setDescription("TEST");

        systemVersion.setAccessId(UUID.randomUUID().toString());

        systemVersion.setReferenceAccessId(UUID.randomUUID().toString());

        systemVersion.setLockedBy("");
        systemVersion.setLockedOn(0L);
        systemVersion.setModifiedOn(0L);

        systemVersionRepository.persistAndFlush(systemVersion);

        return systemVersion;
    }

    public SystemVersion assignBayTypicalToSystemVersion(final Long systemVersionId, final BayTypical bayTypical) {
        final List<String> assignedBayTypicals = new ArrayList<>();
        assignedBayTypicals.add(bayTypical.getAccessId());
        final SystemVersion systemVersion = systemVersionRepository.findById(systemVersionId);

        Optional
            .ofNullable(systemVersion.getAssignedBayTypicals())
            .ifPresent((a) -> assignedBayTypicals.addAll(asList(a.split(","))));

        systemVersion.setAssignedBayTypicals(
            String.join(
                ",",
                assignedBayTypicals
            )
        );


        systemVersionRepository.persistAndFlush(systemVersion);

        return systemVersion;
    }

    public BayTypical createAndStoreBayTypical(final Integer id) {
        final BayTypical bayTypical = new BayTypical();

        bayTypical.setId(id);
        bayTypical.setAccessId(UUID.randomUUID().toString());
        bayTypical.setContentVersion("1.0");
        bayTypical.setVersion(sitipeProperties.version());
        bayTypical.setDescription("TEST");

        bayTypicalRepository.persistAndFlush(bayTypical);

        return bayTypical;
    }

    public BTComponent createAndStoreBTComponent(final Integer id, final BayTypical bayTypical, final String toolName) {
        final BTComponent btComponent = new BTComponent();

        btComponent.setId(id);

        btComponent.setTypicalAccessId(bayTypical.getAccessId());
        btComponent.setToolName(toolName);
        btComponent.setIniVersion(1);
        btComponent.setToolVersion("1.00");
        btComponent.setLanguage("NL");
        btComponent.setName("BT Component For Test");
        btComponent.setHasIecInformation(1);
        btComponent.setImportedOn(0L);
        btComponent.setReferenceAccessId(UUID.randomUUID().toString());
        btComponent.setComponentGuid(UUID.randomUUID().toString());

        btComponentRepository.persistAndFlush(btComponent);

        return btComponent;
    }

    public ImportedComponent createAndStoreImportedComponent(final Integer id, final String componentAccessId, final String type) throws IOException {
        final ImportedComponent importedComponent = new ImportedComponent();

        importedComponent.setId(id);
        importedComponent.setComponentAccessId(componentAccessId);
        importedComponent.setContentVersion("1.00");
        importedComponent.setType(type);

        final String data = "TEST DATA";

        byte[] input = data.getBytes();

        byte[] compressedInput = this.compressionUtils.compress(input);

        importedComponent.setData(compressedInput);

        importedComponentRepository.persistAndFlush(importedComponent);

        return importedComponent;
    }
}
