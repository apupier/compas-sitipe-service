// SPDX-FileCopyrightText: 2023 Alliander N.V.
//
// SPDX-License-Identifier: Apache-2.0

package org.lfenergy.compas.sitipe.data.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.lfenergy.compas.sitipe.data.entity.BayTypical;
import org.lfenergy.compas.sitipe.data.entity.ImportedComponent;
import org.mockito.ArgumentCaptor;
import org.mockito.junit.jupiter.MockitoExtension;

import jakarta.persistence.LockModeType;
import java.util.List;
import java.util.UUID;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

@ExtendWith(MockitoExtension.class)
class ImportedComponentRepositoryTest {

    private ImportedComponentRepository sut;

    @BeforeEach
    public void setUp() {
        sut = spy(ImportedComponentRepository.class);
    }

    @Test
    void itShouldGetImportedComponents() {
        final ArgumentCaptor<String> causeArgumentCaptor = ArgumentCaptor.forClass(String.class);
        final ArgumentCaptor<String> typeArgumentCaptor = ArgumentCaptor.forClass(String.class);
        final ArgumentCaptor<String> iidArgumentCaptor = ArgumentCaptor.forClass(String.class);

        doReturn(emptyList()).when(sut).list(causeArgumentCaptor.capture(), typeArgumentCaptor.capture(), iidArgumentCaptor.capture());

        final String accessId = UUID.randomUUID().toString();

        final List<ImportedComponent> result = sut.getByAccessId(accessId);

        assertEquals("IID", iidArgumentCaptor.getValue());
        assertEquals("componentAccessId = ?1 AND type = ?2", causeArgumentCaptor.getValue());
        assertEquals(accessId, typeArgumentCaptor.getValue());
        assertEquals(0, result.size());
    }

    @Test
    void itShouldGetById() {
        final ArgumentCaptor<Integer> idArgumentCaptor = ArgumentCaptor.forClass(Integer.class);
        final ArgumentCaptor<LockModeType> lockModeTypeArgumentCaptor = ArgumentCaptor.forClass(LockModeType.class);

        final ImportedComponent importedComponent = new ImportedComponent();

        doReturn(importedComponent).when(sut).findById(idArgumentCaptor.capture(), lockModeTypeArgumentCaptor.capture());

        final Integer id = 1;

        final ImportedComponent result = sut.getById(id);

        assertEquals(importedComponent, result);
        assertEquals(id, idArgumentCaptor.getValue());
        assertEquals(LockModeType.NONE, lockModeTypeArgumentCaptor.getValue());
    }
}
