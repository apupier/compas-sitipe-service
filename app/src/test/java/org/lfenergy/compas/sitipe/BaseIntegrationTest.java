// SPDX-FileCopyrightText: 2023 Alliander N.V.
//
// SPDX-License-Identifier: Apache-2.0

package org.lfenergy.compas.sitipe;

import org.junit.jupiter.api.AfterEach;
import org.lfenergy.compas.sitipe.helper.DatabaseCleaner;

import jakarta.inject.Inject;

public class BaseIntegrationTest {

    @Inject()
    DatabaseCleaner databaseCleaner;

    @AfterEach()
    public void tearDown() {
        databaseCleaner.cleanUp();
    }
}
