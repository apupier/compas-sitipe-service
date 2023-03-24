// SPDX-FileCopyrightText: 2023 Alliander N.V.
//
// SPDX-License-Identifier: Apache-2.0

package org.lfenergy.compas.sitipe.rest.v1.model;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.lfenergy.compas.sitipe.data.entity.BayTypical;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

import static org.lfenergy.compas.sitipe.Constants.SITIPE_SERVICE_V1_NS_URI;

@Schema(description = "Response from retrieving all BayTypicals for a specific version")
@XmlRootElement(name = "BayTypicalResponse", namespace = SITIPE_SERVICE_V1_NS_URI)
@XmlAccessorType(XmlAccessType.FIELD)
public class BayTypicalResponse {

    @Schema(description = "List of found BayTypicals in the database.")
    @XmlElement(name = "BayTypical", namespace = SITIPE_SERVICE_V1_NS_URI)
    private List<BayTypicalItem> bayTypicals;

    public void setBayTypicals(final List<BayTypicalItem> bayTypicals) {
        this.bayTypicals = bayTypicals;
    }

    public List<BayTypicalItem> getBayTypicals() {
        return bayTypicals;
    }

    @Schema(description = "BayTypical found in the database.")
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class BayTypicalItem {

        @Schema(description = "Id of the BayTypical.", example = "1")
        @XmlElement(name = "Id", namespace =  SITIPE_SERVICE_V1_NS_URI)
        private Integer id;

        @Schema(description = "Access Id of the BayTypical.", example = "c50b3276-81f6-4bc3-82ab-b8adef829136")
        @XmlElement(name = "AccessId", namespace =  SITIPE_SERVICE_V1_NS_URI)
        private String accessId;

        @Schema(description = "Name of the BayTypical.", example = "BT1")
        @XmlElement(name = "Name", namespace =  SITIPE_SERVICE_V1_NS_URI)
        private String name;

        @Schema(description = "Version of the BayTypical.", example = "1")
        @XmlElement(name = "Version", namespace =  SITIPE_SERVICE_V1_NS_URI)
        private String version;

        @Schema(description = "Description of the BayTypical.")
        @XmlElement(name = "Description", namespace =  SITIPE_SERVICE_V1_NS_URI)
        private String description;

        @Schema(description = "Released flag of the BayTypical.", example = "1")
        @XmlElement(name = "Released", namespace =  SITIPE_SERVICE_V1_NS_URI)
        private int released;

        @Schema(description = "Flag if the BayTypical is locked by someone.", example = "1")
        @XmlElement(name = "LockedBy", namespace =  SITIPE_SERVICE_V1_NS_URI)
        private String lockedBy;

        @Schema(description = "Timestamp of when the BayTypical is locked.", example = "18129347234")
        @XmlElement(name = "LockedOn", namespace =  SITIPE_SERVICE_V1_NS_URI)
        private Long lockedOn;

        @Schema(description = "Timestamp of when the BayTypical is modified.", example = "18129347234")
        @XmlElement(name = "ModifiedOn", namespace =  SITIPE_SERVICE_V1_NS_URI)
        private Long modifiedOn;

        @Schema(description = "Name of the SMR File of the BayTypical.", example = "SMR_1_BT_Foo - Amsterdam.smr")
        @XmlElement(name = "SmrFile", namespace =  SITIPE_SERVICE_V1_NS_URI)
        private String smrFile;

        @Schema(description = "Content Version of the BayTypical.", example = "2.0")
        @XmlElement(name = "ContentVersion", namespace =  SITIPE_SERVICE_V1_NS_URI)
        private String contentVersion;

        @Schema(description = "Reference Access Id of the BayTypical.", example = "d7b0ad3a-c0ae-4b8b-8321-71eaf8970ed7")
        @XmlElement(name = "ReferenceAccessId", namespace =  SITIPE_SERVICE_V1_NS_URI)
        private String referenceAccessId;

        public BayTypicalItem(
                final BayTypical bt
        ) {
            this.id = bt.getId();
            this.accessId = bt.getAccessId();
            this.name = bt.getName();
            this.version = bt.getVersion();
            this.description = bt.getDescription();
            this.released = bt.getReleased();
            this.lockedBy = bt.getLockedBy();
            this.lockedOn = bt.getLockedOn();
            this.modifiedOn = bt.getModifiedOn();
            this.smrFile = bt.getSmrFile();
            this.contentVersion = bt.getContentVersion();
            this.referenceAccessId = bt.getReferenceAccessId();
        }
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


}
