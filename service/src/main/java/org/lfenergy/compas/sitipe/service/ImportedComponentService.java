// SPDX-FileCopyrightText: 2023 Alliander N.V.
//
// SPDX-License-Identifier: Apache-2.0

package org.lfenergy.compas.sitipe.service;

import org.lfenergy.compas.sitipe.data.entity.ImportedComponent;
import org.lfenergy.compas.sitipe.data.repository.ImportedComponentRepository;
import org.lfenergy.compas.sitipe.dto.ImportedComponentDTO;
import org.lfenergy.compas.sitipe.dto.ImportedDataDTO;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.InternalServerErrorException;
import jakarta.ws.rs.NotFoundException;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;
import java.util.zip.ZipInputStream;

@ApplicationScoped
public class ImportedComponentService {

    private final ImportedComponentRepository importedComponentRepository;

    @Inject
    public ImportedComponentService(
        final ImportedComponentRepository importedComponentRepository
    ) {
        this.importedComponentRepository = importedComponentRepository;
    }

    public List<ImportedComponentDTO> getByAccessId(final String accessId) {
        return importedComponentRepository.getByAccessId(accessId)
            .stream()
            .map(ImportedComponentDTO::new)
            .toList();
    }

    public ImportedDataDTO getImportedComponentData(final Integer id) {
        final ImportedComponent importedComponent = this.getEntity(id);

        if (importedComponent == null) {
            throw new NotFoundException("Imported BT Component not found");
        }

        final ByteArrayInputStream bais = new ByteArrayInputStream(importedComponent.getData());
        final InflaterInputStream iis = new InflaterInputStream(bais);

        StringBuilder result = new StringBuilder();
        byte[] buffer = new byte[5];

        int rlen = -1;

        try {
            while ((rlen = iis.read(buffer)) != -1) {
                result.append(new String(Arrays.copyOf(buffer, rlen), StandardCharsets.ISO_8859_1));
            }
        } catch (IOException e) {
            throw new InternalServerErrorException(e);
        }

        return new ImportedDataDTO(
            new String(result.toString().getBytes(), StandardCharsets.UTF_8)
                .replaceAll("[^\\x00-\\x7F]", "")
        );


    }

    @Transactional
    public ImportedComponent getEntity(final Integer id) {
        return importedComponentRepository.getById(id);
    }
}
