package org.immregistries.dqa.validator.report.codes;

import org.apache.commons.lang3.StringUtils;
import org.immregistries.dqa.codebase.client.reference.CodesetType;

import java.util.Objects;

class CollectionBucket {
        CodesetType type;
        String collectionMetadata;
        String metadataDesc;

        public String getLabel() {
            if (StringUtils.isNotBlank(getCollectionMetadata())) {
                if (StringUtils.isNotBlank(getMetadataDesc())) {
                    return type.getType() + " - " + getMetadataDesc() + "[" + getCollectionMetadata() + "]";
                }
                return type.getType() + " - " + getCollectionMetadata();
            }
            return type.getType();
        }
        CollectionBucket(CodesetType type) {
            this.type = type;
        }

        CollectionBucket(CodesetType type, String collectionMetadata) {
            this.type = type;
            this.collectionMetadata = collectionMetadata;
        }
        CollectionBucket(CodesetType type, String collectionMetadata, String metaDataDesc) {
            this(type, collectionMetadata);
            this.metadataDesc = metaDataDesc;
        }

        @Override public String toString() {
            return "CollectionBucket{" + "type=" + type + ", collectionMetadata='" + collectionMetadata + '\'' + ", metadataDesc='" + metadataDesc + '\'' + '}';
        }

        @Override public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;
            CollectionBucket that = (CollectionBucket) o;
            return type == that.type && Objects.equals(collectionMetadata, that.collectionMetadata);
        }

        @Override public int hashCode() {
            return Objects.hash(type, collectionMetadata);
        }

        public CodesetType getType() {
            return type;
        }

        public void setType(CodesetType type) {
            this.type = type;
        }

        public String getCollectionMetadata() {
            return collectionMetadata;
        }

        public void setCollectionMetadata(String collectionMetadata) {
            this.collectionMetadata = collectionMetadata;
        }

        public String getMetadataDesc() {
            return metadataDesc;
        }

        public void setMetadataDesc(String metadataDesc) {
            this.metadataDesc = metadataDesc;
        }
    }
