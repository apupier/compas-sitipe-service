// SPDX-FileCopyrightText: 2023 Alliander N.V.
//
// SPDX-License-Identifier: Apache-2.0

package org.lfenergy.compas.sitipe.rest.v2;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.restassured.path.json.JsonPath;
import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Uni;
import org.junit.jupiter.api.Test;
import org.lfenergy.compas.sitipe.BaseIntegrationTest;
import org.lfenergy.compas.sitipe.SitipeProperties;
import org.lfenergy.compas.sitipe.data.entity.BTComponent;
import org.lfenergy.compas.sitipe.data.entity.BayTypical;
import org.lfenergy.compas.sitipe.data.entity.SystemVersion;
import org.lfenergy.compas.sitipe.dto.BTComponentDTO;
import org.lfenergy.compas.sitipe.helper.SystemVersionHelper;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
@TestHTTPEndpoint(BayTypicalResource.class)
class BayTypicalResourceTest extends BaseIntegrationTest {

    @Inject()
    SitipeProperties sitipeProperties;

    @Inject()
    SystemVersionHelper systemVersionHelper;

    @Test
    @TestSecurity(user = "test-user", roles = {"USER"})
    void itShouldListBayTypicalsForVersion() {
        final SystemVersion systemVersion = systemVersionHelper.createAndStoreSystemVersion(1L, sitipeProperties.version());
        final BayTypical bayTypical1 = systemVersionHelper.createAndStoreBayTypical(1);
        final BayTypical bayTypical2 = systemVersionHelper.createAndStoreBayTypical(2);

        final SystemVersion systemVersion2 = systemVersionHelper.createAndStoreSystemVersion(2L, "2.00");
        final BayTypical bayTypicalForVersion2 = systemVersionHelper.createAndStoreBayTypical(3);

        systemVersionHelper.assignBayTypicalToSystemVersion(systemVersion.getId(), bayTypical1);
        systemVersionHelper.assignBayTypicalToSystemVersion(systemVersion.getId(), bayTypical2);

        systemVersionHelper.assignBayTypicalToSystemVersion(systemVersion2.getId(), bayTypicalForVersion2);

        var response = given()
            .when().get("/")
            .then()
            .statusCode(200)
            .extract()
            .response();

        JsonPath jsonPath = response.jsonPath();

        ArrayList<LinkedHashMap<String, Object>> res = jsonPath.get();
        assertEquals(2, res.size());

        assertTrue(
            asList(bayTypical1.getId(), bayTypical2.getId())
                .contains((Integer)res.get(0).get("id"))
        );

        assertTrue(
            asList(bayTypical1.getId(), bayTypical2.getId())
                .contains((Integer)res.get(1).get("id"))
        );
    }


    @Test
    @TestSecurity(user = "test-user", roles = {"USER"})
    void itShouldReturnEmptyListWhenVersionNotFound() {
        var response = given()
            .when().get("/")
            .then()
            .statusCode( 200)
            .extract()
            .response();

        JsonPath jsonPath = response.jsonPath();
        assertEquals(0, jsonPath.<ArrayList<Object>>get().size());
    }

    @Test
    @TestSecurity(user = "test-user", roles = {"USER"})
    void itShouldReturnEmptyListWhenNoBayTypicalsAreFound() {
        final SystemVersion systemVersion = systemVersionHelper.createAndStoreSystemVersion(1L, sitipeProperties.version());

        var response = given()
            .when().get("/")
            .then()
            .statusCode(200)
            .extract()
            .response();

        JsonPath jsonPath = response.jsonPath();
        assertEquals(0, jsonPath.<ArrayList<Object>>get().size());
    }

    @Test
    @TestSecurity(user = "test-user", roles = {"USER"})
    void itShouldReturnBTComponentsForDigsiForBayTypicals() {
        final BayTypical bayTypical = systemVersionHelper.createAndStoreBayTypical(1);

        final BTComponent btComponent1 = systemVersionHelper.createAndStoreBTComponent(2, bayTypical, "DIGSI 5");
        final BTComponent btComponent2 = systemVersionHelper.createAndStoreBTComponent(3, bayTypical, "DIGSI 6");

        var response = given()
            .when().get("/{accessId}/components", bayTypical.getAccessId())
            .then()
            .statusCode(200)
            .extract()
            .response();

        JsonPath jsonPath = response.jsonPath();
        assertEquals(1, jsonPath.<ArrayList<Object>>get().size());
        assertEquals(btComponent1.getId(), ((ArrayList<LinkedHashMap<String, ?>>)jsonPath.get()).get(0).get("id"));
    }
    @Test
    @TestSecurity(user = "test-user", roles = {"USER"})
    void itShouldReturnEmptyListForBTComponentsWhenNoBayTypicalFound() {
        var response = given()
            .when().get("/{accessId}/components", UUID.randomUUID().toString())
            .then()
            .statusCode(200)
            .extract()
            .response();

        JsonPath jsonPath = response.jsonPath();
        assertEquals(0, jsonPath.<ArrayList<Object>>get().size());
    }

    @Test
    @TestSecurity(user = "test-user", roles = {"USER"})
    void itShouldReturnEmptyListForBTComponentsWhenNoComponentsFoundFound() {
        final BayTypical bayTypical = systemVersionHelper.createAndStoreBayTypical(1);

        final BTComponent btComponent1 = systemVersionHelper.createAndStoreBTComponent(2, bayTypical, "DIGSI 6");
        final BTComponent btComponent2 = systemVersionHelper.createAndStoreBTComponent(3, bayTypical, "DIGSI 6");

        var response = given()
            .when().get("/{accessId}/components", UUID.randomUUID().toString())
            .then()
            .statusCode(200)
            .extract()
            .response();

        JsonPath jsonPath = response.jsonPath();
        assertEquals(0, jsonPath.<ArrayList<Object>>get().size());
    }

}

