// SPDX-FileCopyrightText: 2023 Alliander N.V.
//
// SPDX-License-Identifier: Apache-2.0

package org.lfenergy.compas.sitipe.rest.v2;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;
import org.lfenergy.compas.sitipe.BaseIntegrationTest;
import org.lfenergy.compas.sitipe.data.entity.ImportedComponent;
import org.lfenergy.compas.sitipe.helper.SystemVersionHelper;

import jakarta.inject.Inject;


import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
@TestHTTPEndpoint(BTComponentResource.class)
class BTComponentResourceTest extends BaseIntegrationTest {

    @Inject()
    SystemVersionHelper systemVersionHelper;

    @Test
    @TestSecurity(user = "test-user", roles = {"USER"})
    void itShouldListImportedComponentsForAccessId() throws IOException {
        final String accessId = UUID.randomUUID().toString();
        final ImportedComponent importedComponent1 = systemVersionHelper.createAndStoreImportedComponent(1, accessId, "IID");
        final ImportedComponent importedComponent2 = systemVersionHelper.createAndStoreImportedComponent(2, accessId, "IED");

        var response = given()
            .when().get("/{accessId}/imported", accessId)
            .then()
            .statusCode(200)
            .extract()
            .response();

        JsonPath jsonPath = response.jsonPath();

        ArrayList<LinkedHashMap<String, Object>> res = jsonPath.get();
        assertEquals(1, res.size());
        assertEquals(importedComponent1.getId(), ((ArrayList<LinkedHashMap<String, ?>>)jsonPath.get()).get(0).get("id"));
    }

    @Test
    @TestSecurity(user = "test-user", roles = {"USER"})
    void itShouldReturnEmptyListWhenNoBTComponentFound() {
        final String accessId = UUID.randomUUID().toString();

        var response = given()
                .when().get("/{accessId}/imported", accessId)
                .then()
                .statusCode(200)
                .extract()
                .response();

        JsonPath jsonPath = response.jsonPath();

        ArrayList<LinkedHashMap<String, Object>> res = jsonPath.get();
        assertEquals(0, res.size());
    }

    @Test
    @TestSecurity(user = "test-user", roles = {"USER"})
    void itShouldReturnImportedComponentData() throws IOException {
        final String accessId = UUID.randomUUID().toString();
        final ImportedComponent importedComponent1 = systemVersionHelper.createAndStoreImportedComponent(1, accessId, "IID");

        var response = given()
            .when().get("/imported/{id}", importedComponent1.getId())
            .then()
            .statusCode(200)
            .extract()
            .response();

        JsonPath jsonPath = response.jsonPath();

        LinkedHashMap<String, String> res = jsonPath.get();
        assertEquals("TEST DATA", res.get("data"));
    }

    @Test
    @TestSecurity(user = "test-user", roles = {"USER"})
    void itShouldThrowErrorWhenImportedComponentNotFound() {
        var response = given()
                .when().get("/imported/{id}", 10)
                .then()
                .statusCode(500)
                .extract()
                .response();

        JsonPath jsonPath = response.jsonPath();

        assertEquals("CORE-9999", ((LinkedHashMap<String, ?>)((ArrayList)((LinkedHashMap<String, ?>)jsonPath.get()).get("errorMessages")).get(0)).get("code"));
    }
}
