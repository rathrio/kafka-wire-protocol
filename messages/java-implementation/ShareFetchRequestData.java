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
import java.util.List;
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
import org.apache.kafka.common.utils.ByteUtils;

import static org.apache.kafka.common.protocol.types.Field.TaggedFieldsSection;


public class ShareFetchRequestData implements ApiMessage {
    String groupId;
    String memberId;
    int shareSessionEpoch;
    int maxWaitMs;
    int minBytes;
    int maxBytes;
    List<FetchTopic> topics;
    List<ForgottenTopic> forgottenTopicsData;
    private List<RawTaggedField> _unknownTaggedFields;
    
    public static final Schema SCHEMA_0 =
        new Schema(
            new Field("group_id", Type.COMPACT_NULLABLE_STRING, "The group identifier."),
            new Field("member_id", Type.COMPACT_NULLABLE_STRING, "The member ID."),
            new Field("share_session_epoch", Type.INT32, "The current share session epoch: 0 to open a share session; -1 to close it; otherwise increments for consecutive requests."),
            new Field("max_wait_ms", Type.INT32, "The maximum time in milliseconds to wait for the response."),
            new Field("min_bytes", Type.INT32, "The minimum bytes to accumulate in the response."),
            new Field("max_bytes", Type.INT32, "The maximum bytes to fetch.  See KIP-74 for cases where this limit may not be honored."),
            new Field("topics", new CompactArrayOf(FetchTopic.SCHEMA_0), "The topics to fetch."),
            new Field("forgotten_topics_data", new CompactArrayOf(ForgottenTopic.SCHEMA_0), "The partitions to remove from this share session."),
            TaggedFieldsSection.of(
            )
        );
    
    public static final Schema[] SCHEMAS = new Schema[] {
        SCHEMA_0
    };
    
    public static final short LOWEST_SUPPORTED_VERSION = 0;
    public static final short HIGHEST_SUPPORTED_VERSION = 0;
    
    public ShareFetchRequestData(Readable _readable, short _version) {
        read(_readable, _version);
    }
    
    public ShareFetchRequestData() {
        this.groupId = null;
        this.memberId = "";
        this.shareSessionEpoch = 0;
        this.maxWaitMs = 0;
        this.minBytes = 0;
        this.maxBytes = 0x7fffffff;
        this.topics = new ArrayList<FetchTopic>(0);
        this.forgottenTopicsData = new ArrayList<ForgottenTopic>(0);
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
        {
            int length;
            length = _readable.readUnsignedVarint() - 1;
            if (length < 0) {
                this.groupId = null;
            } else if (length > 0x7fff) {
                throw new RuntimeException("string field groupId had invalid length " + length);
            } else {
                this.groupId = _readable.readString(length);
            }
        }
        {
            int length;
            length = _readable.readUnsignedVarint() - 1;
            if (length < 0) {
                this.memberId = null;
            } else if (length > 0x7fff) {
                throw new RuntimeException("string field memberId had invalid length " + length);
            } else {
                this.memberId = _readable.readString(length);
            }
        }
        this.shareSessionEpoch = _readable.readInt();
        this.maxWaitMs = _readable.readInt();
        this.minBytes = _readable.readInt();
        this.maxBytes = _readable.readInt();
        {
            int arrayLength;
            arrayLength = _readable.readUnsignedVarint() - 1;
            if (arrayLength < 0) {
                throw new RuntimeException("non-nullable field topics was serialized as null");
            } else {
                if (arrayLength > _readable.remaining()) {
                    throw new RuntimeException("Tried to allocate a collection of size " + arrayLength + ", but there are only " + _readable.remaining() + " bytes remaining.");
                }
                ArrayList<FetchTopic> newCollection = new ArrayList<>(arrayLength);
                for (int i = 0; i < arrayLength; i++) {
                    newCollection.add(new FetchTopic(_readable, _version));
                }
                this.topics = newCollection;
            }
        }
        {
            int arrayLength;
            arrayLength = _readable.readUnsignedVarint() - 1;
            if (arrayLength < 0) {
                throw new RuntimeException("non-nullable field forgottenTopicsData was serialized as null");
            } else {
                if (arrayLength > _readable.remaining()) {
                    throw new RuntimeException("Tried to allocate a collection of size " + arrayLength + ", but there are only " + _readable.remaining() + " bytes remaining.");
                }
                ArrayList<ForgottenTopic> newCollection = new ArrayList<>(arrayLength);
                for (int i = 0; i < arrayLength; i++) {
                    newCollection.add(new ForgottenTopic(_readable, _version));
                }
                this.forgottenTopicsData = newCollection;
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
        if (groupId == null) {
            _writable.writeUnsignedVarint(0);
        } else {
            byte[] _stringBytes = _cache.getSerializedValue(groupId);
            _writable.writeUnsignedVarint(_stringBytes.length + 1);
            _writable.writeByteArray(_stringBytes);
        }
        if (memberId == null) {
            _writable.writeUnsignedVarint(0);
        } else {
            byte[] _stringBytes = _cache.getSerializedValue(memberId);
            _writable.writeUnsignedVarint(_stringBytes.length + 1);
            _writable.writeByteArray(_stringBytes);
        }
        _writable.writeInt(shareSessionEpoch);
        _writable.writeInt(maxWaitMs);
        _writable.writeInt(minBytes);
        _writable.writeInt(maxBytes);
        _writable.writeUnsignedVarint(topics.size() + 1);
        for (FetchTopic topicsElement : topics) {
            topicsElement.write(_writable, _cache, _version);
        }
        _writable.writeUnsignedVarint(forgottenTopicsData.size() + 1);
        for (ForgottenTopic forgottenTopicsDataElement : forgottenTopicsData) {
            forgottenTopicsDataElement.write(_writable, _cache, _version);
        }
        RawTaggedFieldWriter _rawWriter = RawTaggedFieldWriter.forFields(_unknownTaggedFields);
        _numTaggedFields += _rawWriter.numFields();
        _writable.writeUnsignedVarint(_numTaggedFields);
        _rawWriter.writeRawTags(_writable, Integer.MAX_VALUE);
    }
    
    @Override
    public void addSize(MessageSizeAccumulator _size, ObjectSerializationCache _cache, short _version) {
        int _numTaggedFields = 0;
        if (groupId == null) {
            _size.addBytes(1);
        } else {
            byte[] _stringBytes = groupId.getBytes(StandardCharsets.UTF_8);
            if (_stringBytes.length > 0x7fff) {
                throw new RuntimeException("'groupId' field is too long to be serialized");
            }
            _cache.cacheSerializedValue(groupId, _stringBytes);
            _size.addBytes(_stringBytes.length + ByteUtils.sizeOfUnsignedVarint(_stringBytes.length + 1));
        }
        if (memberId == null) {
            _size.addBytes(1);
        } else {
            byte[] _stringBytes = memberId.getBytes(StandardCharsets.UTF_8);
            if (_stringBytes.length > 0x7fff) {
                throw new RuntimeException("'memberId' field is too long to be serialized");
            }
            _cache.cacheSerializedValue(memberId, _stringBytes);
            _size.addBytes(_stringBytes.length + ByteUtils.sizeOfUnsignedVarint(_stringBytes.length + 1));
        }
        _size.addBytes(4);
        _size.addBytes(4);
        _size.addBytes(4);
        _size.addBytes(4);
        {
            _size.addBytes(ByteUtils.sizeOfUnsignedVarint(topics.size() + 1));
            for (FetchTopic topicsElement : topics) {
                topicsElement.addSize(_size, _cache, _version);
            }
        }
        {
            _size.addBytes(ByteUtils.sizeOfUnsignedVarint(forgottenTopicsData.size() + 1));
            for (ForgottenTopic forgottenTopicsDataElement : forgottenTopicsData) {
                forgottenTopicsDataElement.addSize(_size, _cache, _version);
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
        if (!(obj instanceof ShareFetchRequestData)) return false;
        ShareFetchRequestData other = (ShareFetchRequestData) obj;
        if (this.groupId == null) {
            if (other.groupId != null) return false;
        } else {
            if (!this.groupId.equals(other.groupId)) return false;
        }
        if (this.memberId == null) {
            if (other.memberId != null) return false;
        } else {
            if (!this.memberId.equals(other.memberId)) return false;
        }
        if (shareSessionEpoch != other.shareSessionEpoch) return false;
        if (maxWaitMs != other.maxWaitMs) return false;
        if (minBytes != other.minBytes) return false;
        if (maxBytes != other.maxBytes) return false;
        if (this.topics == null) {
            if (other.topics != null) return false;
        } else {
            if (!this.topics.equals(other.topics)) return false;
        }
        if (this.forgottenTopicsData == null) {
            if (other.forgottenTopicsData != null) return false;
        } else {
            if (!this.forgottenTopicsData.equals(other.forgottenTopicsData)) return false;
        }
        return MessageUtil.compareRawTaggedFields(_unknownTaggedFields, other._unknownTaggedFields);
    }
    
    @Override
    public int hashCode() {
        int hashCode = 0;
        hashCode = 31 * hashCode + (groupId == null ? 0 : groupId.hashCode());
        hashCode = 31 * hashCode + (memberId == null ? 0 : memberId.hashCode());
        hashCode = 31 * hashCode + shareSessionEpoch;
        hashCode = 31 * hashCode + maxWaitMs;
        hashCode = 31 * hashCode + minBytes;
        hashCode = 31 * hashCode + maxBytes;
        hashCode = 31 * hashCode + (topics == null ? 0 : topics.hashCode());
        hashCode = 31 * hashCode + (forgottenTopicsData == null ? 0 : forgottenTopicsData.hashCode());
        return hashCode;
    }
    
    @Override
    public ShareFetchRequestData duplicate() {
        ShareFetchRequestData _duplicate = new ShareFetchRequestData();
        if (groupId == null) {
            _duplicate.groupId = null;
        } else {
            _duplicate.groupId = groupId;
        }
        if (memberId == null) {
            _duplicate.memberId = null;
        } else {
            _duplicate.memberId = memberId;
        }
        _duplicate.shareSessionEpoch = shareSessionEpoch;
        _duplicate.maxWaitMs = maxWaitMs;
        _duplicate.minBytes = minBytes;
        _duplicate.maxBytes = maxBytes;
        ArrayList<FetchTopic> newTopics = new ArrayList<FetchTopic>(topics.size());
        for (FetchTopic _element : topics) {
            newTopics.add(_element.duplicate());
        }
        _duplicate.topics = newTopics;
        ArrayList<ForgottenTopic> newForgottenTopicsData = new ArrayList<ForgottenTopic>(forgottenTopicsData.size());
        for (ForgottenTopic _element : forgottenTopicsData) {
            newForgottenTopicsData.add(_element.duplicate());
        }
        _duplicate.forgottenTopicsData = newForgottenTopicsData;
        return _duplicate;
    }
    
    @Override
    public String toString() {
        return "ShareFetchRequestData("
            + "groupId=" + ((groupId == null) ? "null" : "'" + groupId.toString() + "'")
            + ", memberId=" + ((memberId == null) ? "null" : "'" + memberId.toString() + "'")
            + ", shareSessionEpoch=" + shareSessionEpoch
            + ", maxWaitMs=" + maxWaitMs
            + ", minBytes=" + minBytes
            + ", maxBytes=" + maxBytes
            + ", topics=" + MessageUtil.deepToString(topics.iterator())
            + ", forgottenTopicsData=" + MessageUtil.deepToString(forgottenTopicsData.iterator())
            + ")";
    }
    
    public String groupId() {
        return this.groupId;
    }
    
    public String memberId() {
        return this.memberId;
    }
    
    public int shareSessionEpoch() {
        return this.shareSessionEpoch;
    }
    
    public int maxWaitMs() {
        return this.maxWaitMs;
    }
    
    public int minBytes() {
        return this.minBytes;
    }
    
    public int maxBytes() {
        return this.maxBytes;
    }
    
    public List<FetchTopic> topics() {
        return this.topics;
    }
    
    public List<ForgottenTopic> forgottenTopicsData() {
        return this.forgottenTopicsData;
    }
    
    @Override
    public List<RawTaggedField> unknownTaggedFields() {
        if (_unknownTaggedFields == null) {
            _unknownTaggedFields = new ArrayList<>(0);
        }
        return _unknownTaggedFields;
    }
    
    public ShareFetchRequestData setGroupId(String v) {
        this.groupId = v;
        return this;
    }
    
    public ShareFetchRequestData setMemberId(String v) {
        this.memberId = v;
        return this;
    }
    
    public ShareFetchRequestData setShareSessionEpoch(int v) {
        this.shareSessionEpoch = v;
        return this;
    }
    
    public ShareFetchRequestData setMaxWaitMs(int v) {
        this.maxWaitMs = v;
        return this;
    }
    
    public ShareFetchRequestData setMinBytes(int v) {
        this.minBytes = v;
        return this;
    }
    
    public ShareFetchRequestData setMaxBytes(int v) {
        this.maxBytes = v;
        return this;
    }
    
    public ShareFetchRequestData setTopics(List<FetchTopic> v) {
        this.topics = v;
        return this;
    }
    
    public ShareFetchRequestData setForgottenTopicsData(List<ForgottenTopic> v) {
        this.forgottenTopicsData = v;
        return this;
    }
    
    public static class FetchTopic implements Message {
        Uuid topicId;
        List<FetchPartition> partitions;
        private List<RawTaggedField> _unknownTaggedFields;
        
        public static final Schema SCHEMA_0 =
            new Schema(
                new Field("topic_id", Type.UUID, "The unique topic ID."),
                new Field("partitions", new CompactArrayOf(FetchPartition.SCHEMA_0), "The partitions to fetch."),
                TaggedFieldsSection.of(
                )
            );
        
        public static final Schema[] SCHEMAS = new Schema[] {
            SCHEMA_0
        };
        
        public static final short LOWEST_SUPPORTED_VERSION = 0;
        public static final short HIGHEST_SUPPORTED_VERSION = 0;
        
        public FetchTopic(Readable _readable, short _version) {
            read(_readable, _version);
        }
        
        public FetchTopic() {
            this.topicId = Uuid.ZERO_UUID;
            this.partitions = new ArrayList<FetchPartition>(0);
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
                throw new UnsupportedVersionException("Can't read version " + _version + " of FetchTopic");
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
                    ArrayList<FetchPartition> newCollection = new ArrayList<>(arrayLength);
                    for (int i = 0; i < arrayLength; i++) {
                        newCollection.add(new FetchPartition(_readable, _version));
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
            for (FetchPartition partitionsElement : partitions) {
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
                throw new UnsupportedVersionException("Can't size version " + _version + " of FetchTopic");
            }
            _size.addBytes(16);
            {
                _size.addBytes(ByteUtils.sizeOfUnsignedVarint(partitions.size() + 1));
                for (FetchPartition partitionsElement : partitions) {
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
            if (!(obj instanceof FetchTopic)) return false;
            FetchTopic other = (FetchTopic) obj;
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
        public FetchTopic duplicate() {
            FetchTopic _duplicate = new FetchTopic();
            _duplicate.topicId = topicId;
            ArrayList<FetchPartition> newPartitions = new ArrayList<FetchPartition>(partitions.size());
            for (FetchPartition _element : partitions) {
                newPartitions.add(_element.duplicate());
            }
            _duplicate.partitions = newPartitions;
            return _duplicate;
        }
        
        @Override
        public String toString() {
            return "FetchTopic("
                + "topicId=" + topicId.toString()
                + ", partitions=" + MessageUtil.deepToString(partitions.iterator())
                + ")";
        }
        
        public Uuid topicId() {
            return this.topicId;
        }
        
        public List<FetchPartition> partitions() {
            return this.partitions;
        }
        
        @Override
        public List<RawTaggedField> unknownTaggedFields() {
            if (_unknownTaggedFields == null) {
                _unknownTaggedFields = new ArrayList<>(0);
            }
            return _unknownTaggedFields;
        }
        
        public FetchTopic setTopicId(Uuid v) {
            this.topicId = v;
            return this;
        }
        
        public FetchTopic setPartitions(List<FetchPartition> v) {
            this.partitions = v;
            return this;
        }
    }
    
    public static class FetchPartition implements Message {
        int partitionIndex;
        int partitionMaxBytes;
        List<AcknowledgementBatch> acknowledgementBatches;
        private List<RawTaggedField> _unknownTaggedFields;
        
        public static final Schema SCHEMA_0 =
            new Schema(
                new Field("partition_index", Type.INT32, "The partition index."),
                new Field("partition_max_bytes", Type.INT32, "The maximum bytes to fetch from this partition. 0 when only acknowledgement with no fetching is required. See KIP-74 for cases where this limit may not be honored."),
                new Field("acknowledgement_batches", new CompactArrayOf(AcknowledgementBatch.SCHEMA_0), "Record batches to acknowledge."),
                TaggedFieldsSection.of(
                )
            );
        
        public static final Schema[] SCHEMAS = new Schema[] {
            SCHEMA_0
        };
        
        public static final short LOWEST_SUPPORTED_VERSION = 0;
        public static final short HIGHEST_SUPPORTED_VERSION = 0;
        
        public FetchPartition(Readable _readable, short _version) {
            read(_readable, _version);
        }
        
        public FetchPartition() {
            this.partitionIndex = 0;
            this.partitionMaxBytes = 0;
            this.acknowledgementBatches = new ArrayList<AcknowledgementBatch>(0);
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
                throw new UnsupportedVersionException("Can't read version " + _version + " of FetchPartition");
            }
            this.partitionIndex = _readable.readInt();
            this.partitionMaxBytes = _readable.readInt();
            {
                int arrayLength;
                arrayLength = _readable.readUnsignedVarint() - 1;
                if (arrayLength < 0) {
                    throw new RuntimeException("non-nullable field acknowledgementBatches was serialized as null");
                } else {
                    if (arrayLength > _readable.remaining()) {
                        throw new RuntimeException("Tried to allocate a collection of size " + arrayLength + ", but there are only " + _readable.remaining() + " bytes remaining.");
                    }
                    ArrayList<AcknowledgementBatch> newCollection = new ArrayList<>(arrayLength);
                    for (int i = 0; i < arrayLength; i++) {
                        newCollection.add(new AcknowledgementBatch(_readable, _version));
                    }
                    this.acknowledgementBatches = newCollection;
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
            _writable.writeInt(partitionMaxBytes);
            _writable.writeUnsignedVarint(acknowledgementBatches.size() + 1);
            for (AcknowledgementBatch acknowledgementBatchesElement : acknowledgementBatches) {
                acknowledgementBatchesElement.write(_writable, _cache, _version);
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
                throw new UnsupportedVersionException("Can't size version " + _version + " of FetchPartition");
            }
            _size.addBytes(4);
            _size.addBytes(4);
            {
                _size.addBytes(ByteUtils.sizeOfUnsignedVarint(acknowledgementBatches.size() + 1));
                for (AcknowledgementBatch acknowledgementBatchesElement : acknowledgementBatches) {
                    acknowledgementBatchesElement.addSize(_size, _cache, _version);
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
            if (!(obj instanceof FetchPartition)) return false;
            FetchPartition other = (FetchPartition) obj;
            if (partitionIndex != other.partitionIndex) return false;
            if (partitionMaxBytes != other.partitionMaxBytes) return false;
            if (this.acknowledgementBatches == null) {
                if (other.acknowledgementBatches != null) return false;
            } else {
                if (!this.acknowledgementBatches.equals(other.acknowledgementBatches)) return false;
            }
            return MessageUtil.compareRawTaggedFields(_unknownTaggedFields, other._unknownTaggedFields);
        }
        
        @Override
        public int hashCode() {
            int hashCode = 0;
            hashCode = 31 * hashCode + partitionIndex;
            hashCode = 31 * hashCode + partitionMaxBytes;
            hashCode = 31 * hashCode + (acknowledgementBatches == null ? 0 : acknowledgementBatches.hashCode());
            return hashCode;
        }
        
        @Override
        public FetchPartition duplicate() {
            FetchPartition _duplicate = new FetchPartition();
            _duplicate.partitionIndex = partitionIndex;
            _duplicate.partitionMaxBytes = partitionMaxBytes;
            ArrayList<AcknowledgementBatch> newAcknowledgementBatches = new ArrayList<AcknowledgementBatch>(acknowledgementBatches.size());
            for (AcknowledgementBatch _element : acknowledgementBatches) {
                newAcknowledgementBatches.add(_element.duplicate());
            }
            _duplicate.acknowledgementBatches = newAcknowledgementBatches;
            return _duplicate;
        }
        
        @Override
        public String toString() {
            return "FetchPartition("
                + "partitionIndex=" + partitionIndex
                + ", partitionMaxBytes=" + partitionMaxBytes
                + ", acknowledgementBatches=" + MessageUtil.deepToString(acknowledgementBatches.iterator())
                + ")";
        }
        
        public int partitionIndex() {
            return this.partitionIndex;
        }
        
        public int partitionMaxBytes() {
            return this.partitionMaxBytes;
        }
        
        public List<AcknowledgementBatch> acknowledgementBatches() {
            return this.acknowledgementBatches;
        }
        
        @Override
        public List<RawTaggedField> unknownTaggedFields() {
            if (_unknownTaggedFields == null) {
                _unknownTaggedFields = new ArrayList<>(0);
            }
            return _unknownTaggedFields;
        }
        
        public FetchPartition setPartitionIndex(int v) {
            this.partitionIndex = v;
            return this;
        }
        
        public FetchPartition setPartitionMaxBytes(int v) {
            this.partitionMaxBytes = v;
            return this;
        }
        
        public FetchPartition setAcknowledgementBatches(List<AcknowledgementBatch> v) {
            this.acknowledgementBatches = v;
            return this;
        }
    }
    
    public static class AcknowledgementBatch implements Message {
        long firstOffset;
        long lastOffset;
        List<Byte> acknowledgeTypes;
        private List<RawTaggedField> _unknownTaggedFields;
        
        public static final Schema SCHEMA_0 =
            new Schema(
                new Field("first_offset", Type.INT64, "First offset of batch of records to acknowledge."),
                new Field("last_offset", Type.INT64, "Last offset (inclusive) of batch of records to acknowledge."),
                new Field("acknowledge_types", new CompactArrayOf(Type.INT8), "Array of acknowledge types - 0:Gap,1:Accept,2:Release,3:Reject."),
                TaggedFieldsSection.of(
                )
            );
        
        public static final Schema[] SCHEMAS = new Schema[] {
            SCHEMA_0
        };
        
        public static final short LOWEST_SUPPORTED_VERSION = 0;
        public static final short HIGHEST_SUPPORTED_VERSION = 0;
        
        public AcknowledgementBatch(Readable _readable, short _version) {
            read(_readable, _version);
        }
        
        public AcknowledgementBatch() {
            this.firstOffset = 0L;
            this.lastOffset = 0L;
            this.acknowledgeTypes = new ArrayList<Byte>(0);
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
                throw new UnsupportedVersionException("Can't read version " + _version + " of AcknowledgementBatch");
            }
            this.firstOffset = _readable.readLong();
            this.lastOffset = _readable.readLong();
            {
                int arrayLength;
                arrayLength = _readable.readUnsignedVarint() - 1;
                if (arrayLength < 0) {
                    throw new RuntimeException("non-nullable field acknowledgeTypes was serialized as null");
                } else {
                    if (arrayLength > _readable.remaining()) {
                        throw new RuntimeException("Tried to allocate a collection of size " + arrayLength + ", but there are only " + _readable.remaining() + " bytes remaining.");
                    }
                    ArrayList<Byte> newCollection = new ArrayList<>(arrayLength);
                    for (int i = 0; i < arrayLength; i++) {
                        newCollection.add(_readable.readByte());
                    }
                    this.acknowledgeTypes = newCollection;
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
            _writable.writeLong(firstOffset);
            _writable.writeLong(lastOffset);
            _writable.writeUnsignedVarint(acknowledgeTypes.size() + 1);
            for (Byte acknowledgeTypesElement : acknowledgeTypes) {
                _writable.writeByte(acknowledgeTypesElement);
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
                throw new UnsupportedVersionException("Can't size version " + _version + " of AcknowledgementBatch");
            }
            _size.addBytes(8);
            _size.addBytes(8);
            {
                _size.addBytes(ByteUtils.sizeOfUnsignedVarint(acknowledgeTypes.size() + 1));
                _size.addBytes(acknowledgeTypes.size() * 1);
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
            if (!(obj instanceof AcknowledgementBatch)) return false;
            AcknowledgementBatch other = (AcknowledgementBatch) obj;
            if (firstOffset != other.firstOffset) return false;
            if (lastOffset != other.lastOffset) return false;
            if (this.acknowledgeTypes == null) {
                if (other.acknowledgeTypes != null) return false;
            } else {
                if (!this.acknowledgeTypes.equals(other.acknowledgeTypes)) return false;
            }
            return MessageUtil.compareRawTaggedFields(_unknownTaggedFields, other._unknownTaggedFields);
        }
        
        @Override
        public int hashCode() {
            int hashCode = 0;
            hashCode = 31 * hashCode + ((int) (firstOffset >> 32) ^ (int) firstOffset);
            hashCode = 31 * hashCode + ((int) (lastOffset >> 32) ^ (int) lastOffset);
            hashCode = 31 * hashCode + (acknowledgeTypes == null ? 0 : acknowledgeTypes.hashCode());
            return hashCode;
        }
        
        @Override
        public AcknowledgementBatch duplicate() {
            AcknowledgementBatch _duplicate = new AcknowledgementBatch();
            _duplicate.firstOffset = firstOffset;
            _duplicate.lastOffset = lastOffset;
            ArrayList<Byte> newAcknowledgeTypes = new ArrayList<Byte>(acknowledgeTypes.size());
            for (Byte _element : acknowledgeTypes) {
                newAcknowledgeTypes.add(_element);
            }
            _duplicate.acknowledgeTypes = newAcknowledgeTypes;
            return _duplicate;
        }
        
        @Override
        public String toString() {
            return "AcknowledgementBatch("
                + "firstOffset=" + firstOffset
                + ", lastOffset=" + lastOffset
                + ", acknowledgeTypes=" + MessageUtil.deepToString(acknowledgeTypes.iterator())
                + ")";
        }
        
        public long firstOffset() {
            return this.firstOffset;
        }
        
        public long lastOffset() {
            return this.lastOffset;
        }
        
        public List<Byte> acknowledgeTypes() {
            return this.acknowledgeTypes;
        }
        
        @Override
        public List<RawTaggedField> unknownTaggedFields() {
            if (_unknownTaggedFields == null) {
                _unknownTaggedFields = new ArrayList<>(0);
            }
            return _unknownTaggedFields;
        }
        
        public AcknowledgementBatch setFirstOffset(long v) {
            this.firstOffset = v;
            return this;
        }
        
        public AcknowledgementBatch setLastOffset(long v) {
            this.lastOffset = v;
            return this;
        }
        
        public AcknowledgementBatch setAcknowledgeTypes(List<Byte> v) {
            this.acknowledgeTypes = v;
            return this;
        }
    }
    
    public static class ForgottenTopic implements Message {
        Uuid topicId;
        List<Integer> partitions;
        private List<RawTaggedField> _unknownTaggedFields;
        
        public static final Schema SCHEMA_0 =
            new Schema(
                new Field("topic_id", Type.UUID, "The unique topic ID."),
                new Field("partitions", new CompactArrayOf(Type.INT32), "The partitions indexes to forget."),
                TaggedFieldsSection.of(
                )
            );
        
        public static final Schema[] SCHEMAS = new Schema[] {
            SCHEMA_0
        };
        
        public static final short LOWEST_SUPPORTED_VERSION = 0;
        public static final short HIGHEST_SUPPORTED_VERSION = 0;
        
        public ForgottenTopic(Readable _readable, short _version) {
            read(_readable, _version);
        }
        
        public ForgottenTopic() {
            this.topicId = Uuid.ZERO_UUID;
            this.partitions = new ArrayList<Integer>(0);
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
                throw new UnsupportedVersionException("Can't read version " + _version + " of ForgottenTopic");
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
                    ArrayList<Integer> newCollection = new ArrayList<>(arrayLength);
                    for (int i = 0; i < arrayLength; i++) {
                        newCollection.add(_readable.readInt());
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
            for (Integer partitionsElement : partitions) {
                _writable.writeInt(partitionsElement);
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
                throw new UnsupportedVersionException("Can't size version " + _version + " of ForgottenTopic");
            }
            _size.addBytes(16);
            {
                _size.addBytes(ByteUtils.sizeOfUnsignedVarint(partitions.size() + 1));
                _size.addBytes(partitions.size() * 4);
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
            if (!(obj instanceof ForgottenTopic)) return false;
            ForgottenTopic other = (ForgottenTopic) obj;
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
        public ForgottenTopic duplicate() {
            ForgottenTopic _duplicate = new ForgottenTopic();
            _duplicate.topicId = topicId;
            ArrayList<Integer> newPartitions = new ArrayList<Integer>(partitions.size());
            for (Integer _element : partitions) {
                newPartitions.add(_element);
            }
            _duplicate.partitions = newPartitions;
            return _duplicate;
        }
        
        @Override
        public String toString() {
            return "ForgottenTopic("
                + "topicId=" + topicId.toString()
                + ", partitions=" + MessageUtil.deepToString(partitions.iterator())
                + ")";
        }
        
        public Uuid topicId() {
            return this.topicId;
        }
        
        public List<Integer> partitions() {
            return this.partitions;
        }
        
        @Override
        public List<RawTaggedField> unknownTaggedFields() {
            if (_unknownTaggedFields == null) {
                _unknownTaggedFields = new ArrayList<>(0);
            }
            return _unknownTaggedFields;
        }
        
        public ForgottenTopic setTopicId(Uuid v) {
            this.topicId = v;
            return this;
        }
        
        public ForgottenTopic setPartitions(List<Integer> v) {
            this.partitions = v;
            return this;
        }
    }
}
