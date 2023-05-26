// SPDX-FileCopyrightText: 2023 Alliander N.V.
//
// SPDX-License-Identifier: Apache-2.0

package org.lfenergy.compas.sitipe.rest.v1.model;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.lfenergy.compas.sitipe.dto.BayTypicalDTO;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
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
        private final Integer id;

        @Schema(description = "Access Id of the BayTypical.", example = "c50b3276-81f6-4bc3-82ab-b8adef829136")
        @XmlElement(name = "AccessId", namespace =  SITIPE_SERVICE_V1_NS_URI)
        private final String accessId;

        @Schema(description = "Name of the BayTypical.", example = "BT1")
        @XmlElement(name = "Name", namespace =  SITIPE_SERVICE_V1_NS_URI)
        private final String name;

        @Schema(description = "Version of the BayTypical.", example = "1")
        @XmlElement(name = "Version", namespace =  SITIPE_SERVICE_V1_NS_URI)
        private final String version;

        @Schema(description = "Description of the BayTypical.")
        @XmlElement(name = "Description", namespace =  SITIPE_SERVICE_V1_NS_URI)
        private final String description;

        @Schema(description = "Released flag of the BayTypical.", example = "1")
        @XmlElement(name = "Released", namespace =  SITIPE_SERVICE_V1_NS_URI)
        private final int released;

        @Schema(description = "Flag if the BayTypical is locked by someone.", example = "1")
        @XmlElement(name = "LockedBy", namespace =  SITIPE_SERVICE_V1_NS_URI)
        private final String lockedBy;

        @Schema(description = "Timestamp of when the BayTypical is locked.", example = "18129347234")
        @XmlElement(name = "LockedOn", namespace =  SITIPE_SERVICE_V1_NS_URI)
        private final Long lockedOn;

        @Schema(description = "Timestamp of when the BayTypical is modified.", example = "18129347234")
        @XmlElement(name = "ModifiedOn", namespace =  SITIPE_SERVICE_V1_NS_URI)
        private final Long modifiedOn;

        @Schema(description = "Name of the SMR File of the BayTypical.", example = "SMR_1_BT_Foo - Amsterdam.smr")
        @XmlElement(name = "SmrFile", namespace =  SITIPE_SERVICE_V1_NS_URI)
        private final String smrFile;

        @Schema(description = "Content Version of the BayTypical.", example = "2.0")
        @XmlElement(name = "ContentVersion", namespace =  SITIPE_SERVICE_V1_NS_URI)
        private final String contentVersion;

        @Schema(description = "Reference Access Id of the BayTypical.", example = "d7b0ad3a-c0ae-4b8b-8321-71eaf8970ed7")
        @XmlElement(name = "ReferenceAccessId", namespace =  SITIPE_SERVICE_V1_NS_URI)
        private final String referenceAccessId;

        public BayTypicalItem(
            final BayTypicalDTO dto
        ) {
            this.id = dto.getId();
            this.accessId = dto.getAccessId();
            this.name = dto.getName();
            this.version = dto.getVersion();
            this.description = dto.getDescription();
            this.released = dto.getReleased();
            this.lockedBy = dto.getLockedBy();
            this.lockedOn = dto.getLockedOn();
            this.modifiedOn = dto.getModifiedOn();
            this.smrFile = dto.getSmrFile();
            this.contentVersion = dto.getContentVersion();
            this.referenceAccessId = dto.getReferenceAccessId();
        }
        public Integer getId() {
            return id;
        }

        public String getAccessId() {
            return accessId;
        }

        public String getName() {
            return name;
        }

        public String getVersion() {
            return version;
        }

        public String getDescription() {
            return description;
        }

        public int getReleased() {
            return released;
        }

        public String getLockedBy() {
            return lockedBy;
        }

        public Long getLockedOn() {
            return lockedOn;
        }

        public Long getModifiedOn() {
            return modifiedOn;
        }

        public String getSmrFile() {
            return smrFile;
        }

        public String getContentVersion() {
            return contentVersion;
        }

        public String getReferenceAccessId() {
            return referenceAccessId;
        }

    }


}
