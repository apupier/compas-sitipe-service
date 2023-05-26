// SPDX-FileCopyrightText: 2023 Alliander N.V.
//
// SPDX-License-Identifier: Apache-2.0

package org.lfenergy.compas.sitipe.data.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Imported_")
public class ImportedComponent {

    @Id()
    private Integer id;

    @Column(name = "type")
    private String type;

    @Column(name = "data")
    @Basic(fetch = FetchType.LAZY)
    @Lob
    private byte[] data;

    @Column(name = "contentVersion")
    private String contentVersion;

    @Column(name = "componentAccessId", columnDefinition = "UUID")
    private String componentAccessId;

    @Column(name = "accessId", columnDefinition = "UUID")
    private String accessId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getContentVersion() {
        return contentVersion;
    }

    public void setContentVersion(String contentVersion) {
        this.contentVersion = contentVersion;
    }

    public String getComponentAccessId() {
        return componentAccessId;
    }

    public void setComponentAccessId(String componentAccessId) {
        this.componentAccessId = componentAccessId;
    }

    public String getAccessId() {
        return accessId;
    }

    public void setAccessId(String accessId) {
        this.accessId = accessId;
    }
}
