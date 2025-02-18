/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

// THIS CODE IS AUTOMATICALLY GENERATED.  DO NOT EDIT.

package org.apache.kafka.common.message;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import org.apache.kafka.common.Uuid;
import org.apache.kafka.common.errors.UnsupportedVersionException;
import org.apache.kafka.common.protocol.ApiMessage;
import org.apache.kafka.common.protocol.Message;
import org.apache.kafka.common.protocol.MessageSizeAccumulator;
import org.apache.kafka.common.protocol.MessageUtil;
import org.apache.kafka.common.protocol.ObjectSerializationCache;
import org.apache.kafka.common.protocol.Readable;
import org.apache.kafka.common.protocol.Writable;
import org.apache.kafka.common.protocol.types.CompactArrayOf;
import org.apache.kafka.common.protocol.types.Field;
import org.apache.kafka.common.protocol.types.RawTaggedField;
import org.apache.kafka.common.protocol.types.RawTaggedFieldWriter;
import org.apache.kafka.common.protocol.types.Schema;
import org.apache.kafka.common.protocol.types.Type;
import org.apache.kafka.common.record.BaseRecords;
import org.apache.kafka.common.record.MemoryRecords;
import org.apache.kafka.common.utils.ByteUtils;
import org.apache.kafka.common.utils.ImplicitLinkedHashCollection;
import org.apache.kafka.common.utils.ImplicitLinkedHashMultiCollection;

import static org.apache.kafka.common.protocol.types.Field.TaggedFieldsSection;


public class ShareFetchResponseData implements ApiMessage {
    int throttleTimeMs;
    short errorCode;
    String errorMessage;
    List<ShareFetchableTopicResponse> responses;
    NodeEndpointCollection nodeEndpoints;
    private List<RawTaggedField> _unknownTaggedFields;
    
    public static final Schema SCHEMA_0 =
        new Schema(
            new Field("throttle_time_ms", Type.INT32, "The duration in milliseconds for which the request was throttled due to a quota violation, or zero if the request did not violate any quota."),
            new Field("error_code", Type.INT16, "The top-level response error code."),
            new Field("error_message", Type.COMPACT_NULLABLE_STRING, "The top-level error message, or null if there was no error."),
            new Field("responses", new CompactArrayOf(ShareFetchableTopicResponse.SCHEMA_0), "The response topics."),
            new Field("node_endpoints", new CompactArrayOf(NodeEndpoint.SCHEMA_0), "Endpoints for all current leaders enumerated in PartitionData with error NOT_LEADER_OR_FOLLOWER."),
            TaggedFieldsSection.of(
            )
        );
    
    public static final Schema[] SCHEMAS = new Schema[] {
        SCHEMA_0
    };
    
    public static final short LOWEST_SUPPORTED_VERSION = 0;
    public static final short HIGHEST_SUPPORTED_VERSION = 0;
    
    public ShareFetchResponseData(Readable _readable, short _version) {
        read(_readable, _version);
    }
    
    public ShareFetchResponseData() {
        this.throttleTimeMs = 0;
        this.errorCode = (short) 0;
        this.errorMessage = null;
        this.responses = new ArrayList<ShareFetchableTopicResponse>(0);
        this.nodeEndpoints = new NodeEndpointCollection(0);
    }
    
    @Override
    public short apiKey() {
        return 78;
    }
    
    @Override
    public short lowestSupportedVersion() {
        return 0;
    }
    
    @Override
    public short highestSupportedVersion() {
        return 0;
    }
    
    @Override
    public final void read(Readable _readable, short _version) {
        this.throttleTimeMs = _readable.readInt();
        this.errorCode = _readable.readShort();
        {
            int length;
            length = _readable.readUnsignedVarint() - 1;
            if (length < 0) {
                this.errorMessage = null;
            } else if (length > 0x7fff) {
                throw new RuntimeException("string field errorMessage had invalid length " + length);
            } else {
                this.errorMessage = _readable.readString(length);
            }
        }
        {
            int arrayLength;
            arrayLength = _readable.readUnsignedVarint() - 1;
            if (arrayLength < 0) {
                throw new RuntimeException("non-nullable field responses was serialized as null");
            } else {
                if (arrayLength > _readable.remaining()) {
                    throw new RuntimeException("Tried to allocate a collection of size " + arrayLength + ", but there are only " + _readable.remaining() + " bytes remaining.");
                }
                ArrayList<ShareFetchableTopicResponse> newCollection = new ArrayList<>(arrayLength);
                for (int i = 0; i < arrayLength; i++) {
                    newCollection.add(new ShareFetchableTopicResponse(_readable, _version));
                }
                this.responses = newCollection;
            }
        }
        {
            int arrayLength;
            arrayLength = _readable.readUnsignedVarint() - 1;
            if (arrayLength < 0) {
                throw new RuntimeException("non-nullable field nodeEndpoints was serialized as null");
            } else {
                if (arrayLength > _readable.remaining()) {
                    throw new RuntimeException("Tried to allocate a collection of size " + arrayLength + ", but there are only " + _readable.remaining() + " bytes remaining.");
                }
                NodeEndpointCollection newCollection = new NodeEndpointCollection(arrayLength);
                for (int i = 0; i < arrayLength; i++) {
                    newCollection.add(new NodeEndpoint(_readable, _version));
                }
                this.nodeEndpoints = newCollection;
            }
        }
        this._unknownTaggedFields = null;
        int _numTaggedFields = _readable.readUnsignedVarint();
        for (int _i = 0; _i < _numTaggedFields; _i++) {
            int _tag = _readable.readUnsignedVarint();
            int _size = _readable.readUnsignedVarint();
            switch (_tag) {
                default:
                    this._unknownTaggedFields = _readable.readUnknownTaggedField(this._unknownTaggedFields, _tag, _size);
                    break;
            }
        }
    }
    
    @Override
    public void write(Writable _writable, ObjectSerializationCache _cache, short _version) {
        int _numTaggedFields = 0;
        _writable.writeInt(throttleTimeMs);
        _writable.writeShort(errorCode);
        if (errorMessage == null) {
            _writable.writeUnsignedVarint(0);
        } else {
            byte[] _stringBytes = _cache.getSerializedValue(errorMessage);
            _writable.writeUnsignedVarint(_stringBytes.length + 1);
            _writable.writeByteArray(_stringBytes);
        }
        _writable.writeUnsignedVarint(responses.size() + 1);
        for (ShareFetchableTopicResponse responsesElement : responses) {
            responsesElement.write(_writable, _cache, _version);
        }
        _writable.writeUnsignedVarint(nodeEndpoints.size() + 1);
        for (NodeEndpoint nodeEndpointsElement : nodeEndpoints) {
            nodeEndpointsElement.write(_writable, _cache, _version);
        }
        RawTaggedFieldWriter _rawWriter = RawTaggedFieldWriter.forFields(_unknownTaggedFields);
        _numTaggedFields += _rawWriter.numFields();
        _writable.writeUnsignedVarint(_numTaggedFields);
        _rawWriter.writeRawTags(_writable, Integer.MAX_VALUE);
    }
    
    @Override
    public void addSize(MessageSizeAccumulator _size, ObjectSerializationCache _cache, short _version) {
        int _numTaggedFields = 0;
        _size.addBytes(4);
        _size.addBytes(2);
        if (errorMessage == null) {
            _size.addBytes(1);
        } else {
            byte[] _stringBytes = errorMessage.getBytes(StandardCharsets.UTF_8);
            if (_stringBytes.length > 0x7fff) {
                throw new RuntimeException("'errorMessage' field is too long to be serialized");
            }
            _cache.cacheSerializedValue(errorMessage, _stringBytes);
            _size.addBytes(_stringBytes.length + ByteUtils.sizeOfUnsignedVarint(_stringBytes.length + 1));
        }
        {
            _size.addBytes(ByteUtils.sizeOfUnsignedVarint(responses.size() + 1));
            for (ShareFetchableTopicResponse responsesElement : responses) {
                responsesElement.addSize(_size, _cache, _version);
            }
        }
        {
            _size.addBytes(ByteUtils.sizeOfUnsignedVarint(nodeEndpoints.size() + 1));
            for (NodeEndpoint nodeEndpointsElement : nodeEndpoints) {
                nodeEndpointsElement.addSize(_size, _cache, _version);
            }
        }
        if (_unknownTaggedFields != null) {
            _numTaggedFields += _unknownTaggedFields.size();
            for (RawTaggedField _field : _unknownTaggedFields) {
                _size.addBytes(ByteUtils.sizeOfUnsignedVarint(_field.tag()));
                _size.addBytes(ByteUtils.sizeOfUnsignedVarint(_field.size()));
                _size.addBytes(_field.size());
            }
        }
        _size.addBytes(ByteUtils.sizeOfUnsignedVarint(_numTaggedFields));
    }
    
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ShareFetchResponseData)) return false;
        ShareFetchResponseData other = (ShareFetchResponseData) obj;
        if (throttleTimeMs != other.throttleTimeMs) return false;
        if (errorCode != other.errorCode) return false;
        if (this.errorMessage == null) {
            if (other.errorMessage != null) return false;
        } else {
            if (!this.errorMessage.equals(other.errorMessage)) return false;
        }
        if (this.responses == null) {
            if (other.responses != null) return false;
        } else {
            if (!this.responses.equals(other.responses)) return false;
        }
        if (this.nodeEndpoints == null) {
            if (other.nodeEndpoints != null) return false;
        } else {
            if (!this.nodeEndpoints.equals(other.nodeEndpoints)) return false;
        }
        return MessageUtil.compareRawTaggedFields(_unknownTaggedFields, other._unknownTaggedFields);
    }
    
    @Override
    public int hashCode() {
        int hashCode = 0;
        hashCode = 31 * hashCode + throttleTimeMs;
        hashCode = 31 * hashCode + errorCode;
        hashCode = 31 * hashCode + (errorMessage == null ? 0 : errorMessage.hashCode());
        hashCode = 31 * hashCode + (responses == null ? 0 : responses.hashCode());
        hashCode = 31 * hashCode + (nodeEndpoints == null ? 0 : nodeEndpoints.hashCode());
        return hashCode;
    }
    
    @Override
    public ShareFetchResponseData duplicate() {
        ShareFetchResponseData _duplicate = new ShareFetchResponseData();
        _duplicate.throttleTimeMs = throttleTimeMs;
        _duplicate.errorCode = errorCode;
        if (errorMessage == null) {
            _duplicate.errorMessage = null;
        } else {
            _duplicate.errorMessage = errorMessage;
        }
        ArrayList<ShareFetchableTopicResponse> newResponses = new ArrayList<ShareFetchableTopicResponse>(responses.size());
        for (ShareFetchableTopicResponse _element : responses) {
            newResponses.add(_element.duplicate());
        }
        _duplicate.responses = newResponses;
        NodeEndpointCollection newNodeEndpoints = new NodeEndpointCollection(nodeEndpoints.size());
        for (NodeEndpoint _element : nodeEndpoints) {
            newNodeEndpoints.add(_element.duplicate());
        }
        _duplicate.nodeEndpoints = newNodeEndpoints;
        return _duplicate;
    }
    
    @Override
    public String toString() {
        return "ShareFetchResponseData("
            + "throttleTimeMs=" + throttleTimeMs
            + ", errorCode=" + errorCode
            + ", errorMessage=" + ((errorMessage == null) ? "null" : "'" + errorMessage.toString() + "'")
            + ", responses=" + MessageUtil.deepToString(responses.iterator())
            + ", nodeEndpoints=" + MessageUtil.deepToString(nodeEndpoints.iterator())
            + ")";
    }
    
    public int throttleTimeMs() {
        return this.throttleTimeMs;
    }
    
    public short errorCode() {
        return this.errorCode;
    }
    
    public String errorMessage() {
        return this.errorMessage;
    }
    
    public List<ShareFetchableTopicResponse> responses() {
        return this.responses;
    }
    
    public NodeEndpointCollection nodeEndpoints() {
        return this.nodeEndpoints;
    }
    
    @Override
    public List<RawTaggedField> unknownTaggedFields() {
        if (_unknownTaggedFields == null) {
            _unknownTaggedFields = new ArrayList<>(0);
        }
        return _unknownTaggedFields;
    }
    
    public ShareFetchResponseData setThrottleTimeMs(int v) {
        this.throttleTimeMs = v;
        return this;
    }
    
    public ShareFetchResponseData setErrorCode(short v) {
        this.errorCode = v;
        return this;
    }
    
    public ShareFetchResponseData setErrorMessage(String v) {
        this.errorMessage = v;
        return this;
    }
    
    public ShareFetchResponseData setResponses(List<ShareFetchableTopicResponse> v) {
        this.responses = v;
        return this;
    }
    
    public ShareFetchResponseData setNodeEndpoints(NodeEndpointCollection v) {
        this.nodeEndpoints = v;
        return this;
    }
    
    public static class ShareFetchableTopicResponse implements Message {
        Uuid topicId;
        List<PartitionData> partitions;
        private List<RawTaggedField> _unknownTaggedFields;
        
        public static final Schema SCHEMA_0 =
            new Schema(
                new Field("topic_id", Type.UUID, "The unique topic ID."),
                new Field("partitions", new CompactArrayOf(PartitionData.SCHEMA_0), "The topic partitions."),
                TaggedFieldsSection.of(
                )
            );
        
        public static final Schema[] SCHEMAS = new Schema[] {
            SCHEMA_0
        };
        
        public static final short LOWEST_SUPPORTED_VERSION = 0;
        public static final short HIGHEST_SUPPORTED_VERSION = 0;
        
        public ShareFetchableTopicResponse(Readable _readable, short _version) {
            read(_readable, _version);
        }
        
        public ShareFetchableTopicResponse() {
            this.topicId = Uuid.ZERO_UUID;
            this.partitions = new ArrayList<PartitionData>(0);
        }
        
        
        @Override
        public short lowestSupportedVersion() {
            return 0;
        }
        
        @Override
        public short highestSupportedVersion() {
            return 0;
        }
        
        @Override
        public final void read(Readable _readable, short _version) {
            if (_version > 0) {
                throw new UnsupportedVersionException("Can't read version " + _version + " of ShareFetchableTopicResponse");
            }
            this.topicId = _readable.readUuid();
            {
                int arrayLength;
                arrayLength = _readable.readUnsignedVarint() - 1;
                if (arrayLength < 0) {
                    throw new RuntimeException("non-nullable field partitions was serialized as null");
                } else {
                    if (arrayLength > _readable.remaining()) {
                        throw new RuntimeException("Tried to allocate a collection of size " + arrayLength + ", but there are only " + _readable.remaining() + " bytes remaining.");
                    }
                    ArrayList<PartitionData> newCollection = new ArrayList<>(arrayLength);
                    for (int i = 0; i < arrayLength; i++) {
                        newCollection.add(new PartitionData(_readable, _version));
                    }
                    this.partitions = newCollection;
                }
            }
            this._unknownTaggedFields = null;
            int _numTaggedFields = _readable.readUnsignedVarint();
            for (int _i = 0; _i < _numTaggedFields; _i++) {
                int _tag = _readable.readUnsignedVarint();
                int _size = _readable.readUnsignedVarint();
                switch (_tag) {
                    default:
                        this._unknownTaggedFields = _readable.readUnknownTaggedField(this._unknownTaggedFields, _tag, _size);
                        break;
                }
            }
        }
        
        @Override
        public void write(Writable _writable, ObjectSerializationCache _cache, short _version) {
            int _numTaggedFields = 0;
            _writable.writeUuid(topicId);
            _writable.writeUnsignedVarint(partitions.size() + 1);
            for (PartitionData partitionsElement : partitions) {
                partitionsElement.write(_writable, _cache, _version);
            }
            RawTaggedFieldWriter _rawWriter = RawTaggedFieldWriter.forFields(_unknownTaggedFields);
            _numTaggedFields += _rawWriter.numFields();
            _writable.writeUnsignedVarint(_numTaggedFields);
            _rawWriter.writeRawTags(_writable, Integer.MAX_VALUE);
        }
        
        @Override
        public void addSize(MessageSizeAccumulator _size, ObjectSerializationCache _cache, short _version) {
            int _numTaggedFields = 0;
            if (_version > 0) {
                throw new UnsupportedVersionException("Can't size version " + _version + " of ShareFetchableTopicResponse");
            }
            _size.addBytes(16);
            {
                _size.addBytes(ByteUtils.sizeOfUnsignedVarint(partitions.size() + 1));
                for (PartitionData partitionsElement : partitions) {
                    partitionsElement.addSize(_size, _cache, _version);
                }
            }
            if (_unknownTaggedFields != null) {
                _numTaggedFields += _unknownTaggedFields.size();
                for (RawTaggedField _field : _unknownTaggedFields) {
                    _size.addBytes(ByteUtils.sizeOfUnsignedVarint(_field.tag()));
                    _size.addBytes(ByteUtils.sizeOfUnsignedVarint(_field.size()));
                    _size.addBytes(_field.size());
                }
            }
            _size.addBytes(ByteUtils.sizeOfUnsignedVarint(_numTaggedFields));
        }
        
        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof ShareFetchableTopicResponse)) return false;
            ShareFetchableTopicResponse other = (ShareFetchableTopicResponse) obj;
            if (!this.topicId.equals(other.topicId)) return false;
            if (this.partitions == null) {
                if (other.partitions != null) return false;
            } else {
                if (!this.partitions.equals(other.partitions)) return false;
            }
            return MessageUtil.compareRawTaggedFields(_unknownTaggedFields, other._unknownTaggedFields);
        }
        
        @Override
        public int hashCode() {
            int hashCode = 0;
            hashCode = 31 * hashCode + topicId.hashCode();
            hashCode = 31 * hashCode + (partitions == null ? 0 : partitions.hashCode());
            return hashCode;
        }
        
        @Override
        public ShareFetchableTopicResponse duplicate() {
            ShareFetchableTopicResponse _duplicate = new ShareFetchableTopicResponse();
            _duplicate.topicId = topicId;
            ArrayList<PartitionData> newPartitions = new ArrayList<PartitionData>(partitions.size());
            for (PartitionData _element : partitions) {
                newPartitions.add(_element.duplicate());
            }
            _duplicate.partitions = newPartitions;
            return _duplicate;
        }
        
        @Override
        public String toString() {
            return "ShareFetchableTopicResponse("
                + "topicId=" + topicId.toString()
                + ", partitions=" + MessageUtil.deepToString(partitions.iterator())
                + ")";
        }
        
        public Uuid topicId() {
            return this.topicId;
        }
        
        public List<PartitionData> partitions() {
            return this.partitions;
        }
        
        @Override
        public List<RawTaggedField> unknownTaggedFields() {
            if (_unknownTaggedFields == null) {
                _unknownTaggedFields = new ArrayList<>(0);
            }
            return _unknownTaggedFields;
        }
        
        public ShareFetchableTopicResponse setTopicId(Uuid v) {
            this.topicId = v;
            return this;
        }
        
        public ShareFetchableTopicResponse setPartitions(List<PartitionData> v) {
            this.partitions = v;
            return this;
        }
    }
    
    public static class PartitionData implements Message {
        int partitionIndex;
        short errorCode;
        String errorMessage;
        short acknowledgeErrorCode;
        String acknowledgeErrorMessage;
        LeaderIdAndEpoch currentLeader;
        BaseRecords records;
        List<AcquiredRecords> acquiredRecords;
        private List<RawTaggedField> _unknownTaggedFields;
        
        public static final Schema SCHEMA_0 =
            new Schema(
                new Field("partition_index", Type.INT32, "The partition index."),
                new Field("error_code", Type.INT16, "The fetch error code, or 0 if there was no fetch error."),
                new Field("error_message", Type.COMPACT_NULLABLE_STRING, "The fetch error message, or null if there was no fetch error."),
                new Field("acknowledge_error_code", Type.INT16, "The acknowledge error code, or 0 if there was no acknowledge error."),
                new Field("acknowledge_error_message", Type.COMPACT_NULLABLE_STRING, "The acknowledge error message, or null if there was no acknowledge error."),
                new Field("current_leader", LeaderIdAndEpoch.SCHEMA_0, "The current leader of the partition."),
                new Field("records", Type.COMPACT_RECORDS, "The record data."),
                new Field("acquired_records", new CompactArrayOf(AcquiredRecords.SCHEMA_0), "The acquired records."),
                TaggedFieldsSection.of(
                )
            );
        
        public static final Schema[] SCHEMAS = new Schema[] {
            SCHEMA_0
        };
        
        public static final short LOWEST_SUPPORTED_VERSION = 0;
        public static final short HIGHEST_SUPPORTED_VERSION = 0;
        
        public PartitionData(Readable _readable, short _version) {
            read(_readable, _version);
        }
        
        public PartitionData() {
            this.partitionIndex = 0;
            this.errorCode = (short) 0;
            this.errorMessage = null;
            this.acknowledgeErrorCode = (short) 0;
            this.acknowledgeErrorMessage = null;
            this.currentLeader = new LeaderIdAndEpoch();
            this.records = null;
            this.acquiredRecords = new ArrayList<AcquiredRecords>(0);
        }
        
        
        @Override
        public short lowestSupportedVersion() {
            return 0;
        }
        
        @Override
        public short highestSupportedVersion() {
            return 0;
        }
        
        @Override
        public final void read(Readable _readable, short _version) {
            if (_version > 0) {
                throw new UnsupportedVersionException("Can't read version " + _version + " of PartitionData");
            }
            this.partitionIndex = _readable.readInt();
            this.errorCode = _readable.readShort();
            {
                int length;
                length = _readable.readUnsignedVarint() - 1;
                if (length < 0) {
                    this.errorMessage = null;
                } else if (length > 0x7fff) {
                    throw new RuntimeException("string field errorMessage had invalid length " + length);
                } else {
                    this.errorMessage = _readable.readString(length);
                }
            }
            this.acknowledgeErrorCode = _readable.readShort();
            {
                int length;
                length = _readable.readUnsignedVarint() - 1;
                if (length < 0) {
                    this.acknowledgeErrorMessage = null;
                } else if (length > 0x7fff) {
                    throw new RuntimeException("string field acknowledgeErrorMessage had invalid length " + length);
                } else {
                    this.acknowledgeErrorMessage = _readable.readString(length);
                }
            }
            {
                this.currentLeader = new LeaderIdAndEpoch(_readable, _version);
            }
            {
                int length;
                length = _readable.readUnsignedVarint() - 1;
                if (length < 0) {
                    this.records = null;
                } else {
                    this.records = _readable.readRecords(length);
                }
            }
            {
                int arrayLength;
                arrayLength = _readable.readUnsignedVarint() - 1;
                if (arrayLength < 0) {
                    throw new RuntimeException("non-nullable field acquiredRecords was serialized as null");
                } else {
                    if (arrayLength > _readable.remaining()) {
                        throw new RuntimeException("Tried to allocate a collection of size " + arrayLength + ", but there are only " + _readable.remaining() + " bytes remaining.");
                    }
                    ArrayList<AcquiredRecords> newCollection = new ArrayList<>(arrayLength);
                    for (int i = 0; i < arrayLength; i++) {
                        newCollection.add(new AcquiredRecords(_readable, _version));
                    }
                    this.acquiredRecords = newCollection;
                }
            }
            this._unknownTaggedFields = null;
            int _numTaggedFields = _readable.readUnsignedVarint();
            for (int _i = 0; _i < _numTaggedFields; _i++) {
                int _tag = _readable.readUnsignedVarint();
                int _size = _readable.readUnsignedVarint();
                switch (_tag) {
                    default:
                        this._unknownTaggedFields = _readable.readUnknownTaggedField(this._unknownTaggedFields, _tag, _size);
                        break;
                }
            }
        }
        
        @Override
        public void write(Writable _writable, ObjectSerializationCache _cache, short _version) {
            int _numTaggedFields = 0;
            _writable.writeInt(partitionIndex);
            _writable.writeShort(errorCode);
            if (errorMessage == null) {
                _writable.writeUnsignedVarint(0);
            } else {
                byte[] _stringBytes = _cache.getSerializedValue(errorMessage);
                _writable.writeUnsignedVarint(_stringBytes.length + 1);
                _writable.writeByteArray(_stringBytes);
            }
            _writable.writeShort(acknowledgeErrorCode);
            if (acknowledgeErrorMessage == null) {
                _writable.writeUnsignedVarint(0);
            } else {
                byte[] _stringBytes = _cache.getSerializedValue(acknowledgeErrorMessage);
                _writable.writeUnsignedVarint(_stringBytes.length + 1);
                _writable.writeByteArray(_stringBytes);
            }
            currentLeader.write(_writable, _cache, _version);
            if (records == null) {
                _writable.writeUnsignedVarint(0);
            } else {
                _writable.writeUnsignedVarint(records.sizeInBytes() + 1);
                _writable.writeRecords(records);
            }
            _writable.writeUnsignedVarint(acquiredRecords.size() + 1);
            for (AcquiredRecords acquiredRecordsElement : acquiredRecords) {
                acquiredRecordsElement.write(_writable, _cache, _version);
            }
            RawTaggedFieldWriter _rawWriter = RawTaggedFieldWriter.forFields(_unknownTaggedFields);
            _numTaggedFields += _rawWriter.numFields();
            _writable.writeUnsignedVarint(_numTaggedFields);
            _rawWriter.writeRawTags(_writable, Integer.MAX_VALUE);
        }
        
        @Override
        public void addSize(MessageSizeAccumulator _size, ObjectSerializationCache _cache, short _version) {
            int _numTaggedFields = 0;
            if (_version > 0) {
                throw new UnsupportedVersionException("Can't size version " + _version + " of PartitionData");
            }
            _size.addBytes(4);
            _size.addBytes(2);
            if (errorMessage == null) {
                _size.addBytes(1);
            } else {
                byte[] _stringBytes = errorMessage.getBytes(StandardCharsets.UTF_8);
                if (_stringBytes.length > 0x7fff) {
                    throw new RuntimeException("'errorMessage' field is too long to be serialized");
                }
                _cache.cacheSerializedValue(errorMessage, _stringBytes);
                _size.addBytes(_stringBytes.length + ByteUtils.sizeOfUnsignedVarint(_stringBytes.length + 1));
            }
            _size.addBytes(2);
            if (acknowledgeErrorMessage == null) {
                _size.addBytes(1);
            } else {
                byte[] _stringBytes = acknowledgeErrorMessage.getBytes(StandardCharsets.UTF_8);
                if (_stringBytes.length > 0x7fff) {
                    throw new RuntimeException("'acknowledgeErrorMessage' field is too long to be serialized");
                }
                _cache.cacheSerializedValue(acknowledgeErrorMessage, _stringBytes);
                _size.addBytes(_stringBytes.length + ByteUtils.sizeOfUnsignedVarint(_stringBytes.length + 1));
            }
            {
                this.currentLeader.addSize(_size, _cache, _version);
            }
            if (records == null) {
                _size.addBytes(1);
            } else {
                _size.addZeroCopyBytes(records.sizeInBytes());
                _size.addBytes(ByteUtils.sizeOfUnsignedVarint(records.sizeInBytes() + 1));
            }
            {
                _size.addBytes(ByteUtils.sizeOfUnsignedVarint(acquiredRecords.size() + 1));
                for (AcquiredRecords acquiredRecordsElement : acquiredRecords) {
                    acquiredRecordsElement.addSize(_size, _cache, _version);
                }
            }
            if (_unknownTaggedFields != null) {
                _numTaggedFields += _unknownTaggedFields.size();
                for (RawTaggedField _field : _unknownTaggedFields) {
                    _size.addBytes(ByteUtils.sizeOfUnsignedVarint(_field.tag()));
                    _size.addBytes(ByteUtils.sizeOfUnsignedVarint(_field.size()));
                    _size.addBytes(_field.size());
                }
            }
            _size.addBytes(ByteUtils.sizeOfUnsignedVarint(_numTaggedFields));
        }
        
        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof PartitionData)) return false;
            PartitionData other = (PartitionData) obj;
            if (partitionIndex != other.partitionIndex) return false;
            if (errorCode != other.errorCode) return false;
            if (this.errorMessage == null) {
                if (other.errorMessage != null) return false;
            } else {
                if (!this.errorMessage.equals(other.errorMessage)) return false;
            }
            if (acknowledgeErrorCode != other.acknowledgeErrorCode) return false;
            if (this.acknowledgeErrorMessage == null) {
                if (other.acknowledgeErrorMessage != null) return false;
            } else {
                if (!this.acknowledgeErrorMessage.equals(other.acknowledgeErrorMessage)) return false;
            }
            if (this.currentLeader == null) {
                if (other.currentLeader != null) return false;
            } else {
                if (!this.currentLeader.equals(other.currentLeader)) return false;
            }
            if (!Objects.equals(this.records, other.records)) return false;
            if (this.acquiredRecords == null) {
                if (other.acquiredRecords != null) return false;
            } else {
                if (!this.acquiredRecords.equals(other.acquiredRecords)) return false;
            }
            return MessageUtil.compareRawTaggedFields(_unknownTaggedFields, other._unknownTaggedFields);
        }
        
        @Override
        public int hashCode() {
            int hashCode = 0;
            hashCode = 31 * hashCode + partitionIndex;
            hashCode = 31 * hashCode + errorCode;
            hashCode = 31 * hashCode + (errorMessage == null ? 0 : errorMessage.hashCode());
            hashCode = 31 * hashCode + acknowledgeErrorCode;
            hashCode = 31 * hashCode + (acknowledgeErrorMessage == null ? 0 : acknowledgeErrorMessage.hashCode());
            hashCode = 31 * hashCode + (currentLeader == null ? 0 : currentLeader.hashCode());
            hashCode = 31 * hashCode + Objects.hashCode(records);
            hashCode = 31 * hashCode + (acquiredRecords == null ? 0 : acquiredRecords.hashCode());
            return hashCode;
        }
        
        @Override
        public PartitionData duplicate() {
            PartitionData _duplicate = new PartitionData();
            _duplicate.partitionIndex = partitionIndex;
            _duplicate.errorCode = errorCode;
            if (errorMessage == null) {
                _duplicate.errorMessage = null;
            } else {
                _duplicate.errorMessage = errorMessage;
            }
            _duplicate.acknowledgeErrorCode = acknowledgeErrorCode;
            if (acknowledgeErrorMessage == null) {
                _duplicate.acknowledgeErrorMessage = null;
            } else {
                _duplicate.acknowledgeErrorMessage = acknowledgeErrorMessage;
            }
            _duplicate.currentLeader = currentLeader.duplicate();
            if (records == null) {
                _duplicate.records = null;
            } else {
                _duplicate.records = MemoryRecords.readableRecords(((MemoryRecords) records).buffer().duplicate());
            }
            ArrayList<AcquiredRecords> newAcquiredRecords = new ArrayList<AcquiredRecords>(acquiredRecords.size());
            for (AcquiredRecords _element : acquiredRecords) {
                newAcquiredRecords.add(_element.duplicate());
            }
            _duplicate.acquiredRecords = newAcquiredRecords;
            return _duplicate;
        }
        
        @Override
        public String toString() {
            return "PartitionData("
                + "partitionIndex=" + partitionIndex
                + ", errorCode=" + errorCode
                + ", errorMessage=" + ((errorMessage == null) ? "null" : "'" + errorMessage.toString() + "'")
                + ", acknowledgeErrorCode=" + acknowledgeErrorCode
                + ", acknowledgeErrorMessage=" + ((acknowledgeErrorMessage == null) ? "null" : "'" + acknowledgeErrorMessage.toString() + "'")
                + ", currentLeader=" + currentLeader.toString()
                + ", records=" + records
                + ", acquiredRecords=" + MessageUtil.deepToString(acquiredRecords.iterator())
                + ")";
        }
        
        public int partitionIndex() {
            return this.partitionIndex;
        }
        
        public short errorCode() {
            return this.errorCode;
        }
        
        public String errorMessage() {
            return this.errorMessage;
        }
        
        public short acknowledgeErrorCode() {
            return this.acknowledgeErrorCode;
        }
        
        public String acknowledgeErrorMessage() {
            return this.acknowledgeErrorMessage;
        }
        
        public LeaderIdAndEpoch currentLeader() {
            return this.currentLeader;
        }
        
        public BaseRecords records() {
            return this.records;
        }
        
        public List<AcquiredRecords> acquiredRecords() {
            return this.acquiredRecords;
        }
        
        @Override
        public List<RawTaggedField> unknownTaggedFields() {
            if (_unknownTaggedFields == null) {
                _unknownTaggedFields = new ArrayList<>(0);
            }
            return _unknownTaggedFields;
        }
        
        public PartitionData setPartitionIndex(int v) {
            this.partitionIndex = v;
            return this;
        }
        
        public PartitionData setErrorCode(short v) {
            this.errorCode = v;
            return this;
        }
        
        public PartitionData setErrorMessage(String v) {
            this.errorMessage = v;
            return this;
        }
        
        public PartitionData setAcknowledgeErrorCode(short v) {
            this.acknowledgeErrorCode = v;
            return this;
        }
        
        public PartitionData setAcknowledgeErrorMessage(String v) {
            this.acknowledgeErrorMessage = v;
            return this;
        }
        
        public PartitionData setCurrentLeader(LeaderIdAndEpoch v) {
            this.currentLeader = v;
            return this;
        }
        
        public PartitionData setRecords(BaseRecords v) {
            this.records = v;
            return this;
        }
        
        public PartitionData setAcquiredRecords(List<AcquiredRecords> v) {
            this.acquiredRecords = v;
            return this;
        }
    }
    
    public static class LeaderIdAndEpoch implements Message {
        int leaderId;
        int leaderEpoch;
        private List<RawTaggedField> _unknownTaggedFields;
        
        public static final Schema SCHEMA_0 =
            new Schema(
                new Field("leader_id", Type.INT32, "The ID of the current leader or -1 if the leader is unknown."),
                new Field("leader_epoch", Type.INT32, "The latest known leader epoch."),
                TaggedFieldsSection.of(
                )
            );
        
        public static final Schema[] SCHEMAS = new Schema[] {
            SCHEMA_0
        };
        
        public static final short LOWEST_SUPPORTED_VERSION = 0;
        public static final short HIGHEST_SUPPORTED_VERSION = 0;
        
        public LeaderIdAndEpoch(Readable _readable, short _version) {
            read(_readable, _version);
        }
        
        public LeaderIdAndEpoch() {
            this.leaderId = 0;
            this.leaderEpoch = 0;
        }
        
        
        @Override
        public short lowestSupportedVersion() {
            return 0;
        }
        
        @Override
        public short highestSupportedVersion() {
            return 0;
        }
        
        @Override
        public final void read(Readable _readable, short _version) {
            if (_version > 0) {
                throw new UnsupportedVersionException("Can't read version " + _version + " of LeaderIdAndEpoch");
            }
            this.leaderId = _readable.readInt();
            this.leaderEpoch = _readable.readInt();
            this._unknownTaggedFields = null;
            int _numTaggedFields = _readable.readUnsignedVarint();
            for (int _i = 0; _i < _numTaggedFields; _i++) {
                int _tag = _readable.readUnsignedVarint();
                int _size = _readable.readUnsignedVarint();
                switch (_tag) {
                    default:
                        this._unknownTaggedFields = _readable.readUnknownTaggedField(this._unknownTaggedFields, _tag, _size);
                        break;
                }
            }
        }
        
        @Override
        public void write(Writable _writable, ObjectSerializationCache _cache, short _version) {
            int _numTaggedFields = 0;
            _writable.writeInt(leaderId);
            _writable.writeInt(leaderEpoch);
            RawTaggedFieldWriter _rawWriter = RawTaggedFieldWriter.forFields(_unknownTaggedFields);
            _numTaggedFields += _rawWriter.numFields();
            _writable.writeUnsignedVarint(_numTaggedFields);
            _rawWriter.writeRawTags(_writable, Integer.MAX_VALUE);
        }
        
        @Override
        public void addSize(MessageSizeAccumulator _size, ObjectSerializationCache _cache, short _version) {
            int _numTaggedFields = 0;
            if (_version > 0) {
                throw new UnsupportedVersionException("Can't size version " + _version + " of LeaderIdAndEpoch");
            }
            _size.addBytes(4);
            _size.addBytes(4);
            if (_unknownTaggedFields != null) {
                _numTaggedFields += _unknownTaggedFields.size();
                for (RawTaggedField _field : _unknownTaggedFields) {
                    _size.addBytes(ByteUtils.sizeOfUnsignedVarint(_field.tag()));
                    _size.addBytes(ByteUtils.sizeOfUnsignedVarint(_field.size()));
                    _size.addBytes(_field.size());
                }
            }
            _size.addBytes(ByteUtils.sizeOfUnsignedVarint(_numTaggedFields));
        }
        
        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof LeaderIdAndEpoch)) return false;
            LeaderIdAndEpoch other = (LeaderIdAndEpoch) obj;
            if (leaderId != other.leaderId) return false;
            if (leaderEpoch != other.leaderEpoch) return false;
            return MessageUtil.compareRawTaggedFields(_unknownTaggedFields, other._unknownTaggedFields);
        }
        
        @Override
        public int hashCode() {
            int hashCode = 0;
            hashCode = 31 * hashCode + leaderId;
            hashCode = 31 * hashCode + leaderEpoch;
            return hashCode;
        }
        
        @Override
        public LeaderIdAndEpoch duplicate() {
            LeaderIdAndEpoch _duplicate = new LeaderIdAndEpoch();
            _duplicate.leaderId = leaderId;
            _duplicate.leaderEpoch = leaderEpoch;
            return _duplicate;
        }
        
        @Override
        public String toString() {
            return "LeaderIdAndEpoch("
                + "leaderId=" + leaderId
                + ", leaderEpoch=" + leaderEpoch
                + ")";
        }
        
        public int leaderId() {
            return this.leaderId;
        }
        
        public int leaderEpoch() {
            return this.leaderEpoch;
        }
        
        @Override
        public List<RawTaggedField> unknownTaggedFields() {
            if (_unknownTaggedFields == null) {
                _unknownTaggedFields = new ArrayList<>(0);
            }
            return _unknownTaggedFields;
        }
        
        public LeaderIdAndEpoch setLeaderId(int v) {
            this.leaderId = v;
            return this;
        }
        
        public LeaderIdAndEpoch setLeaderEpoch(int v) {
            this.leaderEpoch = v;
            return this;
        }
    }
    
    public static class AcquiredRecords implements Message {
        long firstOffset;
        long lastOffset;
        short deliveryCount;
        private List<RawTaggedField> _unknownTaggedFields;
        
        public static final Schema SCHEMA_0 =
            new Schema(
                new Field("first_offset", Type.INT64, "The earliest offset in this batch of acquired records."),
                new Field("last_offset", Type.INT64, "The last offset of this batch of acquired records."),
                new Field("delivery_count", Type.INT16, "The delivery count of this batch of acquired records."),
                TaggedFieldsSection.of(
                )
            );
        
        public static final Schema[] SCHEMAS = new Schema[] {
            SCHEMA_0
        };
        
        public static final short LOWEST_SUPPORTED_VERSION = 0;
        public static final short HIGHEST_SUPPORTED_VERSION = 0;
        
        public AcquiredRecords(Readable _readable, short _version) {
            read(_readable, _version);
        }
        
        public AcquiredRecords() {
            this.firstOffset = 0L;
            this.lastOffset = 0L;
            this.deliveryCount = (short) 0;
        }
        
        
        @Override
        public short lowestSupportedVersion() {
            return 0;
        }
        
        @Override
        public short highestSupportedVersion() {
            return 0;
        }
        
        @Override
        public final void read(Readable _readable, short _version) {
            if (_version > 0) {
                throw new UnsupportedVersionException("Can't read version " + _version + " of AcquiredRecords");
            }
            this.firstOffset = _readable.readLong();
            this.lastOffset = _readable.readLong();
            this.deliveryCount = _readable.readShort();
            this._unknownTaggedFields = null;
            int _numTaggedFields = _readable.readUnsignedVarint();
            for (int _i = 0; _i < _numTaggedFields; _i++) {
                int _tag = _readable.readUnsignedVarint();
                int _size = _readable.readUnsignedVarint();
                switch (_tag) {
                    default:
                        this._unknownTaggedFields = _readable.readUnknownTaggedField(this._unknownTaggedFields, _tag, _size);
                        break;
                }
            }
        }
        
        @Override
        public void write(Writable _writable, ObjectSerializationCache _cache, short _version) {
            int _numTaggedFields = 0;
            _writable.writeLong(firstOffset);
            _writable.writeLong(lastOffset);
            _writable.writeShort(deliveryCount);
            RawTaggedFieldWriter _rawWriter = RawTaggedFieldWriter.forFields(_unknownTaggedFields);
            _numTaggedFields += _rawWriter.numFields();
            _writable.writeUnsignedVarint(_numTaggedFields);
            _rawWriter.writeRawTags(_writable, Integer.MAX_VALUE);
        }
        
        @Override
        public void addSize(MessageSizeAccumulator _size, ObjectSerializationCache _cache, short _version) {
            int _numTaggedFields = 0;
            if (_version > 0) {
                throw new UnsupportedVersionException("Can't size version " + _version + " of AcquiredRecords");
            }
            _size.addBytes(8);
            _size.addBytes(8);
            _size.addBytes(2);
            if (_unknownTaggedFields != null) {
                _numTaggedFields += _unknownTaggedFields.size();
                for (RawTaggedField _field : _unknownTaggedFields) {
                    _size.addBytes(ByteUtils.sizeOfUnsignedVarint(_field.tag()));
                    _size.addBytes(ByteUtils.sizeOfUnsignedVarint(_field.size()));
                    _size.addBytes(_field.size());
                }
            }
            _size.addBytes(ByteUtils.sizeOfUnsignedVarint(_numTaggedFields));
        }
        
        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof AcquiredRecords)) return false;
            AcquiredRecords other = (AcquiredRecords) obj;
            if (firstOffset != other.firstOffset) return false;
            if (lastOffset != other.lastOffset) return false;
            if (deliveryCount != other.deliveryCount) return false;
            return MessageUtil.compareRawTaggedFields(_unknownTaggedFields, other._unknownTaggedFields);
        }
        
        @Override
        public int hashCode() {
            int hashCode = 0;
            hashCode = 31 * hashCode + ((int) (firstOffset >> 32) ^ (int) firstOffset);
            hashCode = 31 * hashCode + ((int) (lastOffset >> 32) ^ (int) lastOffset);
            hashCode = 31 * hashCode + deliveryCount;
            return hashCode;
        }
        
        @Override
        public AcquiredRecords duplicate() {
            AcquiredRecords _duplicate = new AcquiredRecords();
            _duplicate.firstOffset = firstOffset;
            _duplicate.lastOffset = lastOffset;
            _duplicate.deliveryCount = deliveryCount;
            return _duplicate;
        }
        
        @Override
        public String toString() {
            return "AcquiredRecords("
                + "firstOffset=" + firstOffset
                + ", lastOffset=" + lastOffset
                + ", deliveryCount=" + deliveryCount
                + ")";
        }
        
        public long firstOffset() {
            return this.firstOffset;
        }
        
        public long lastOffset() {
            return this.lastOffset;
        }
        
        public short deliveryCount() {
            return this.deliveryCount;
        }
        
        @Override
        public List<RawTaggedField> unknownTaggedFields() {
            if (_unknownTaggedFields == null) {
                _unknownTaggedFields = new ArrayList<>(0);
            }
            return _unknownTaggedFields;
        }
        
        public AcquiredRecords setFirstOffset(long v) {
            this.firstOffset = v;
            return this;
        }
        
        public AcquiredRecords setLastOffset(long v) {
            this.lastOffset = v;
            return this;
        }
        
        public AcquiredRecords setDeliveryCount(short v) {
            this.deliveryCount = v;
            return this;
        }
    }
    
    public static class NodeEndpoint implements Message, ImplicitLinkedHashMultiCollection.Element {
        int nodeId;
        String host;
        int port;
        String rack;
        private List<RawTaggedField> _unknownTaggedFields;
        private int next;
        private int prev;
        
        public static final Schema SCHEMA_0 =
            new Schema(
                new Field("node_id", Type.INT32, "The ID of the associated node."),
                new Field("host", Type.COMPACT_STRING, "The node's hostname."),
                new Field("port", Type.INT32, "The node's port."),
                new Field("rack", Type.COMPACT_NULLABLE_STRING, "The rack of the node, or null if it has not been assigned to a rack."),
                TaggedFieldsSection.of(
                )
            );
        
        public static final Schema[] SCHEMAS = new Schema[] {
            SCHEMA_0
        };
        
        public static final short LOWEST_SUPPORTED_VERSION = 0;
        public static final short HIGHEST_SUPPORTED_VERSION = 0;
        
        public NodeEndpoint(Readable _readable, short _version) {
            read(_readable, _version);
            this.prev = ImplicitLinkedHashCollection.INVALID_INDEX;
            this.next = ImplicitLinkedHashCollection.INVALID_INDEX;
        }
        
        public NodeEndpoint() {
            this.nodeId = 0;
            this.host = "";
            this.port = 0;
            this.rack = null;
            this.prev = ImplicitLinkedHashCollection.INVALID_INDEX;
            this.next = ImplicitLinkedHashCollection.INVALID_INDEX;
        }
        
        
        @Override
        public short lowestSupportedVersion() {
            return 0;
        }
        
        @Override
        public short highestSupportedVersion() {
            return 0;
        }
        
        @Override
        public final void read(Readable _readable, short _version) {
            if (_version > 0) {
                throw new UnsupportedVersionException("Can't read version " + _version + " of NodeEndpoint");
            }
            this.nodeId = _readable.readInt();
            {
                int length;
                length = _readable.readUnsignedVarint() - 1;
                if (length < 0) {
                    throw new RuntimeException("non-nullable field host was serialized as null");
                } else if (length > 0x7fff) {
                    throw new RuntimeException("string field host had invalid length " + length);
                } else {
                    this.host = _readable.readString(length);
                }
            }
            this.port = _readable.readInt();
            {
                int length;
                length = _readable.readUnsignedVarint() - 1;
                if (length < 0) {
                    this.rack = null;
                } else if (length > 0x7fff) {
                    throw new RuntimeException("string field rack had invalid length " + length);
                } else {
                    this.rack = _readable.readString(length);
                }
            }
            this._unknownTaggedFields = null;
            int _numTaggedFields = _readable.readUnsignedVarint();
            for (int _i = 0; _i < _numTaggedFields; _i++) {
                int _tag = _readable.readUnsignedVarint();
                int _size = _readable.readUnsignedVarint();
                switch (_tag) {
                    default:
                        this._unknownTaggedFields = _readable.readUnknownTaggedField(this._unknownTaggedFields, _tag, _size);
                        break;
                }
            }
        }
        
        @Override
        public void write(Writable _writable, ObjectSerializationCache _cache, short _version) {
            int _numTaggedFields = 0;
            _writable.writeInt(nodeId);
            {
                byte[] _stringBytes = _cache.getSerializedValue(host);
                _writable.writeUnsignedVarint(_stringBytes.length + 1);
                _writable.writeByteArray(_stringBytes);
            }
            _writable.writeInt(port);
            if (rack == null) {
                _writable.writeUnsignedVarint(0);
            } else {
                byte[] _stringBytes = _cache.getSerializedValue(rack);
                _writable.writeUnsignedVarint(_stringBytes.length + 1);
                _writable.writeByteArray(_stringBytes);
            }
            RawTaggedFieldWriter _rawWriter = RawTaggedFieldWriter.forFields(_unknownTaggedFields);
            _numTaggedFields += _rawWriter.numFields();
            _writable.writeUnsignedVarint(_numTaggedFields);
            _rawWriter.writeRawTags(_writable, Integer.MAX_VALUE);
        }
        
        @Override
        public void addSize(MessageSizeAccumulator _size, ObjectSerializationCache _cache, short _version) {
            int _numTaggedFields = 0;
            if (_version > 0) {
                throw new UnsupportedVersionException("Can't size version " + _version + " of NodeEndpoint");
            }
            _size.addBytes(4);
            {
                byte[] _stringBytes = host.getBytes(StandardCharsets.UTF_8);
                if (_stringBytes.length > 0x7fff) {
                    throw new RuntimeException("'host' field is too long to be serialized");
                }
                _cache.cacheSerializedValue(host, _stringBytes);
                _size.addBytes(_stringBytes.length + ByteUtils.sizeOfUnsignedVarint(_stringBytes.length + 1));
            }
            _size.addBytes(4);
            if (rack == null) {
                _size.addBytes(1);
            } else {
                byte[] _stringBytes = rack.getBytes(StandardCharsets.UTF_8);
                if (_stringBytes.length > 0x7fff) {
                    throw new RuntimeException("'rack' field is too long to be serialized");
                }
                _cache.cacheSerializedValue(rack, _stringBytes);
                _size.addBytes(_stringBytes.length + ByteUtils.sizeOfUnsignedVarint(_stringBytes.length + 1));
            }
            if (_unknownTaggedFields != null) {
                _numTaggedFields += _unknownTaggedFields.size();
                for (RawTaggedField _field : _unknownTaggedFields) {
                    _size.addBytes(ByteUtils.sizeOfUnsignedVarint(_field.tag()));
                    _size.addBytes(ByteUtils.sizeOfUnsignedVarint(_field.size()));
                    _size.addBytes(_field.size());
                }
            }
            _size.addBytes(ByteUtils.sizeOfUnsignedVarint(_numTaggedFields));
        }
        
        @Override
        public boolean elementKeysAreEqual(Object obj) {
            if (!(obj instanceof NodeEndpoint)) return false;
            NodeEndpoint other = (NodeEndpoint) obj;
            if (nodeId != other.nodeId) return false;
            return true;
        }
        
        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof NodeEndpoint)) return false;
            NodeEndpoint other = (NodeEndpoint) obj;
            if (nodeId != other.nodeId) return false;
            if (this.host == null) {
                if (other.host != null) return false;
            } else {
                if (!this.host.equals(other.host)) return false;
            }
            if (port != other.port) return false;
            if (this.rack == null) {
                if (other.rack != null) return false;
            } else {
                if (!this.rack.equals(other.rack)) return false;
            }
            return MessageUtil.compareRawTaggedFields(_unknownTaggedFields, other._unknownTaggedFields);
        }
        
        @Override
        public int hashCode() {
            int hashCode = 0;
            hashCode = 31 * hashCode + nodeId;
            return hashCode;
        }
        
        @Override
        public NodeEndpoint duplicate() {
            NodeEndpoint _duplicate = new NodeEndpoint();
            _duplicate.nodeId = nodeId;
            _duplicate.host = host;
            _duplicate.port = port;
            if (rack == null) {
                _duplicate.rack = null;
            } else {
                _duplicate.rack = rack;
            }
            return _duplicate;
        }
        
        @Override
        public String toString() {
            return "NodeEndpoint("
                + "nodeId=" + nodeId
                + ", host=" + ((host == null) ? "null" : "'" + host.toString() + "'")
                + ", port=" + port
                + ", rack=" + ((rack == null) ? "null" : "'" + rack.toString() + "'")
                + ")";
        }
        
        public int nodeId() {
            return this.nodeId;
        }
        
        public String host() {
            return this.host;
        }
        
        public int port() {
            return this.port;
        }
        
        public String rack() {
            return this.rack;
        }
        
        @Override
        public int next() {
            return this.next;
        }
        
        @Override
        public int prev() {
            return this.prev;
        }
        
        @Override
        public List<RawTaggedField> unknownTaggedFields() {
            if (_unknownTaggedFields == null) {
                _unknownTaggedFields = new ArrayList<>(0);
            }
            return _unknownTaggedFields;
        }
        
        public NodeEndpoint setNodeId(int v) {
            this.nodeId = v;
            return this;
        }
        
        public NodeEndpoint setHost(String v) {
            this.host = v;
            return this;
        }
        
        public NodeEndpoint setPort(int v) {
            this.port = v;
            return this;
        }
        
        public NodeEndpoint setRack(String v) {
            this.rack = v;
            return this;
        }
        
        @Override
        public void setNext(int v) {
            this.next = v;
        }
        
        @Override
        public void setPrev(int v) {
            this.prev = v;
        }
    }
    
    public static class NodeEndpointCollection extends ImplicitLinkedHashMultiCollection<NodeEndpoint> {
        public NodeEndpointCollection() {
            super();
        }
        
        public NodeEndpointCollection(int expectedNumElements) {
            super(expectedNumElements);
        }
        
        public NodeEndpointCollection(Iterator<NodeEndpoint> iterator) {
            super(iterator);
        }
        
        public NodeEndpoint find(int nodeId) {
            NodeEndpoint _key = new NodeEndpoint();
            _key.setNodeId(nodeId);
            return find(_key);
        }
        
        public List<NodeEndpoint> findAll(int nodeId) {
            NodeEndpoint _key = new NodeEndpoint();
            _key.setNodeId(nodeId);
            return findAll(_key);
        }
        
        public NodeEndpointCollection duplicate() {
            NodeEndpointCollection _duplicate = new NodeEndpointCollection(size());
            for (NodeEndpoint _element : this) {
                _duplicate.add(_element.duplicate());
            }
            return _duplicate;
        }
    }
}
