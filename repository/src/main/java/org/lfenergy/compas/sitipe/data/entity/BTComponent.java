// SPDX-FileCopyrightText: 2023 Alliander N.V.
//
// SPDX-License-Identifier: Apache-2.0

package org.lfenergy.compas.sitipe.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "BTComponents_")
public class BTComponent {

    @Id()
    @Column(columnDefinition = "BIGINT")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "typicalAccessId", columnDefinition = "UUID")
    private String typicalAccessId;

    @Column(name = "importedOn")
    private Long importedOn;

    @Column(name = "toolName")
    private String toolName;

    @Column(name = "toolVersion")
    private String toolVersion;

    @Column(name = "hasIecInformation", columnDefinition = "bit")
    private int hasIecInformation;

    @Column(name = "componentGuid", columnDefinition = "UUID")
    private String componentGuid;

    @Column(name = "accessId", columnDefinition = "UUID")
    private String accessId;

    @Column(name = "language")
    private String language;

    @Column(name = "IniVersion")
    private int iniVersion;

    @Column(name = "referenceAccessId", columnDefinition = "UUID")
    private String referenceAccessId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypicalAccessId() {
        return typicalAccessId;
    }

    public void setTypicalAccessId(String typicalAccessId) {
        this.typicalAccessId = typicalAccessId;
    }

    public Long getImportedOn() {
        return importedOn;
    }

    public void setImportedOn(Long importedOn) {
        this.importedOn = importedOn;
    }

    public String getToolName() {
        return toolName;
    }

    public void setToolName(String toolName) {
        this.toolName = toolName;
    }

    public String getToolVersion() {
        return toolVersion;
    }

    public void setToolVersion(String toolVersion) {
        this.toolVersion = toolVersion;
    }

    public int getHasIecInformation() {
        return hasIecInformation;
    }

    public void setHasIecInformation(int hasIecInformation) {
        this.hasIecInformation = hasIecInformation;
    }

    public String getComponentGuid() {
        return componentGuid;
    }

    public void setComponentGuid(String componentGuid) {
        this.componentGuid = componentGuid;
    }

    public String getAccessId() {
        return accessId;
    }

    public void setAccessId(String accessId) {
        this.accessId = accessId;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getIniVersion() {
        return iniVersion;
    }

    public void setIniVersion(int iniVersion) {
        this.iniVersion = iniVersion;
    }

    public String getReferenceAccessId() {
        return referenceAccessId;
    }

    public void setReferenceAccessId(String referenceAccessId) {
        this.referenceAccessId = referenceAccessId;
    }
}
