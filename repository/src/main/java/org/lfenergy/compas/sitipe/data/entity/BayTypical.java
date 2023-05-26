// SPDX-FileCopyrightText: 2023 Alliander N.V.
//
// SPDX-License-Identifier: Apache-2.0

package org.lfenergy.compas.sitipe.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "BayTypicals_LIST_")
public class BayTypical {

    @Id()
    @Column(columnDefinition = "BIGINT")
    private Integer id;

    @Column(name = "accessId", columnDefinition = "UUID")
    private String accessId;

    @Column(name = "name", columnDefinition = "nvarchar")
    private String name;

    @Column(name = "version", columnDefinition = "nvarchar")
    private String version;

    @Column(name = "description", columnDefinition = "nvarchar")
    private String description;

    @Column(name = "released", columnDefinition = "bit")
    private int released;

    @Column(name = "lockedBy", columnDefinition = "nvarchar")
    private String lockedBy;

    @Column(name = "lockedOn")
    private Long lockedOn;

    @Column(name = "modifiedOn")
    private Long modifiedOn;

    @Column(name = "smrFile", columnDefinition = "nvarchar")
    private String smrFile;

    @Column(name = "contentVersion", columnDefinition = "nvarchar")
    private String contentVersion;

    @Column(name = "referenceAccessId", columnDefinition = "UUID")
    private String referenceAccessId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccessId() {
        return accessId;
    }

    public void setAccessId(String accessId) {
        this.accessId = accessId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getReleased() {
        return released;
    }

    public void setReleased(int released) {
        this.released = released;
    }

    public String getLockedBy() {
        return lockedBy;
    }

    public void setLockedBy(String lockedBy) {
        this.lockedBy = lockedBy;
    }

    public Long getLockedOn() {
        return lockedOn;
    }

    public void setLockedOn(Long lockedOn) {
        this.lockedOn = lockedOn;
    }

    public Long getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(Long modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public String getSmrFile() {
        return smrFile;
    }

    public void setSmrFile(String smrFile) {
        this.smrFile = smrFile;
    }

    public String getContentVersion() {
        return contentVersion;
    }

    public void setContentVersion(String contentVersion) {
        this.contentVersion = contentVersion;
    }

    public String getReferenceAccessId() {
        return referenceAccessId;
    }

    public void setReferenceAccessId(String referenceAccessId) {
        this.referenceAccessId = referenceAccessId;
    }
}
