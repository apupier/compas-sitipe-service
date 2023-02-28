package org.lfenergy.compas.sitipe.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity()
@Table(name = "SYSTEM_VERSION_LIST_")
public class SystemVersion {

    @Id()
    private int id;

    @Column(name = "accessId", columnDefinition = "uniqueIdentifier")
    private String accessId;

    @Column(name = "version", columnDefinition = "nvarchar")
    private String version;

    @Column(name = "description", columnDefinition = "nvarchar")
    private String description;

    @Column(name = "comment", columnDefinition = "nvarchar")
    private String comment;

    @Column(name = "lockedBy", columnDefinition = "nvarchar")
    private String lockedBy;

    @Column(name = "lockedOn")
    private Long lockedOn;

    @Column(name = "assignedBayTypicals", columnDefinition = "nvarchar")
    private String assignedBayTypicals;

    @Column(name = "modifiedOn")
    private Long modifiedOn;

    @Column(name = "contentVersion", columnDefinition = "nvarchar")
    private String contentVersion;

    @Column(name = "referenceAccessId", columnDefinition = "uniqueidentifier")
    private String referenceAccessId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccessId() {
        return accessId;
    }

    public void setAccessId(String accessId) {
        this.accessId = accessId;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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

    public String getAssignedBayTypicals() {
        return assignedBayTypicals;
    }

    public void setAssignedBayTypicals(String assignedBayTypicals) {
        this.assignedBayTypicals = assignedBayTypicals;
    }

    public Long getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(Long modifiedOn) {
        this.modifiedOn = modifiedOn;
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
