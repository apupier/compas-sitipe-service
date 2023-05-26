// SPDX-FileCopyrightText: 2023 Alliander N.V.
//
// SPDX-License-Identifier: Apache-2.0

package org.lfenergy.compas.sitipe.rest.v1;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.restassured.path.xml.XmlPath;
import org.junit.jupiter.api.Test;
import org.lfenergy.compas.sitipe.BaseIntegrationTest;
import org.lfenergy.compas.sitipe.SitipeProperties;
import org.lfenergy.compas.sitipe.data.entity.BayTypical;
import org.lfenergy.compas.sitipe.data.entity.SystemVersion;
import org.lfenergy.compas.sitipe.helper.SystemVersionHelper;

import jakarta.inject.Inject;

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

        XmlPath xmlPath = response.xmlPath();
        assertEquals(2, xmlPath.getList("BayTypicalResponse.BayTypical").size());
        assertTrue(
            asList(bayTypical2.getId(), bayTypical1.getId())
                .contains(xmlPath.getInt("BayTypicalResponse.BayTypical[0].Id"))
        );
        assertTrue(
            asList(bayTypical2.getId(), bayTypical1.getId())
                .contains(xmlPath.getInt("BayTypicalResponse.BayTypical[1].Id"))
        );
    }

    @Test
    @TestSecurity(user = "test-user", roles = {"USER"})
    void itShouldReturnEmptyListWhenVersionNotFound() {
        var response = given()
                .when().get("/")
                .then()
                .statusCode(200)
                .extract()
                .response();

        XmlPath xmlPath = response.xmlPath();
        assertEquals(0, xmlPath.getList("BayTypicalResponse.BayTypical").size());
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

        XmlPath xmlPath = response.xmlPath();
        assertEquals(0, xmlPath.getList("BayTypicalResponse.BayTypical").size());
    }

}
