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
import org.apache.kafka.common.utils.ImplicitLinkedHashCollection;
import org.apache.kafka.common.utils.ImplicitLinkedHashMultiCollection;

import static org.apache.kafka.common.protocol.types.Field.TaggedFieldsSection;


public class DescribeTopicPartitionsResponseData implements ApiMessage {
    int throttleTimeMs;
    DescribeTopicPartitionsResponseTopicCollection topics;
    Cursor nextCursor;
    private List<RawTaggedField> _unknownTaggedFields;
    
    public static final Schema SCHEMA_0 =
        new Schema(
            new Field("throttle_time_ms", Type.INT32, "The duration in milliseconds for which the request was throttled due to a quota violation, or zero if the request did not violate any quota."),
            new Field("topics", new CompactArrayOf(DescribeTopicPartitionsResponseTopic.SCHEMA_0), "Each topic in the response."),
            new Field("next_cursor", Cursor.SCHEMA_0, "The next topic and partition index to fetch details for."),
            TaggedFieldsSection.of(
            )
        );
    
    public static final Schema[] SCHEMAS = new Schema[] {
        SCHEMA_0
    };
    
    public static final short LOWEST_SUPPORTED_VERSION = 0;
    public static final short HIGHEST_SUPPORTED_VERSION = 0;
    
    public DescribeTopicPartitionsResponseData(Readable _readable, short _version) {
        read(_readable, _version);
    }
    
    public DescribeTopicPartitionsResponseData() {
        this.throttleTimeMs = 0;
        this.topics = new DescribeTopicPartitionsResponseTopicCollection(0);
        this.nextCursor = null;
    }
    
    @Override
    public short apiKey() {
        return 75;
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
        {
            int arrayLength;
            arrayLength = _readable.readUnsignedVarint() - 1;
            if (arrayLength < 0) {
                throw new RuntimeException("non-nullable field topics was serialized as null");
            } else {
                if (arrayLength > _readable.remaining()) {
                    throw new RuntimeException("Tried to allocate a collection of size " + arrayLength + ", but there are only " + _readable.remaining() + " bytes remaining.");
                }
                DescribeTopicPartitionsResponseTopicCollection newCollection = new DescribeTopicPartitionsResponseTopicCollection(arrayLength);
                for (int i = 0; i < arrayLength; i++) {
                    newCollection.add(new DescribeTopicPartitionsResponseTopic(_readable, _version));
                }
                this.topics = newCollection;
            }
        }
        {
            if (_readable.readByte() < 0) {
                this.nextCursor = null;
            } else {
                this.nextCursor = new Cursor(_readable, _version);
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
        _writable.writeUnsignedVarint(topics.size() + 1);
        for (DescribeTopicPartitionsResponseTopic topicsElement : topics) {
            topicsElement.write(_writable, _cache, _version);
        }
        if (nextCursor == null) {
            _writable.writeByte((byte) -1);
        } else {
            _writable.writeByte((byte) 1);
            nextCursor.write(_writable, _cache, _version);
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
        {
            _size.addBytes(ByteUtils.sizeOfUnsignedVarint(topics.size() + 1));
            for (DescribeTopicPartitionsResponseTopic topicsElement : topics) {
                topicsElement.addSize(_size, _cache, _version);
            }
        }
        if (nextCursor == null) {
            _size.addBytes(1);
        } else {
            _size.addBytes(1);
            this.nextCursor.addSize(_size, _cache, _version);
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
        if (!(obj instanceof DescribeTopicPartitionsResponseData)) return false;
        DescribeTopicPartitionsResponseData other = (DescribeTopicPartitionsResponseData) obj;
        if (throttleTimeMs != other.throttleTimeMs) return false;
        if (this.topics == null) {
            if (other.topics != null) return false;
        } else {
            if (!this.topics.equals(other.topics)) return false;
        }
        if (this.nextCursor == null) {
            if (other.nextCursor != null) return false;
        } else {
            if (!this.nextCursor.equals(other.nextCursor)) return false;
        }
        return MessageUtil.compareRawTaggedFields(_unknownTaggedFields, other._unknownTaggedFields);
    }
    
    @Override
    public int hashCode() {
        int hashCode = 0;
        hashCode = 31 * hashCode + throttleTimeMs;
        hashCode = 31 * hashCode + (topics == null ? 0 : topics.hashCode());
        hashCode = 31 * hashCode + (nextCursor == null ? 0 : nextCursor.hashCode());
        return hashCode;
    }
    
    @Override
    public DescribeTopicPartitionsResponseData duplicate() {
        DescribeTopicPartitionsResponseData _duplicate = new DescribeTopicPartitionsResponseData();
        _duplicate.throttleTimeMs = throttleTimeMs;
        DescribeTopicPartitionsResponseTopicCollection newTopics = new DescribeTopicPartitionsResponseTopicCollection(topics.size());
        for (DescribeTopicPartitionsResponseTopic _element : topics) {
            newTopics.add(_element.duplicate());
        }
        _duplicate.topics = newTopics;
        if (nextCursor == null) {
            _duplicate.nextCursor = null;
        } else {
            _duplicate.nextCursor = nextCursor.duplicate();
        }
        return _duplicate;
    }
    
    @Override
    public String toString() {
        return "DescribeTopicPartitionsResponseData("
            + "throttleTimeMs=" + throttleTimeMs
            + ", topics=" + MessageUtil.deepToString(topics.iterator())
            + ", nextCursor=" + ((nextCursor == null) ? "null" : nextCursor.toString())
            + ")";
    }
    
    public int throttleTimeMs() {
        return this.throttleTimeMs;
    }
    
    public DescribeTopicPartitionsResponseTopicCollection topics() {
        return this.topics;
    }
    
    public Cursor nextCursor() {
        return this.nextCursor;
    }
    
    @Override
    public List<RawTaggedField> unknownTaggedFields() {
        if (_unknownTaggedFields == null) {
            _unknownTaggedFields = new ArrayList<>(0);
        }
        return _unknownTaggedFields;
    }
    
    public DescribeTopicPartitionsResponseData setThrottleTimeMs(int v) {
        this.throttleTimeMs = v;
        return this;
    }
    
    public DescribeTopicPartitionsResponseData setTopics(DescribeTopicPartitionsResponseTopicCollection v) {
        this.topics = v;
        return this;
    }
    
    public DescribeTopicPartitionsResponseData setNextCursor(Cursor v) {
        this.nextCursor = v;
        return this;
    }
    
    public static class DescribeTopicPartitionsResponseTopic implements Message, ImplicitLinkedHashMultiCollection.Element {
        short errorCode;
        String name;
        Uuid topicId;
        boolean isInternal;
        List<DescribeTopicPartitionsResponsePartition> partitions;
        int topicAuthorizedOperations;
        private List<RawTaggedField> _unknownTaggedFields;
        private int next;
        private int prev;
        
        public static final Schema SCHEMA_0 =
            new Schema(
                new Field("error_code", Type.INT16, "The topic error, or 0 if there was no error."),
                new Field("name", Type.COMPACT_NULLABLE_STRING, "The topic name."),
                new Field("topic_id", Type.UUID, "The topic id."),
                new Field("is_internal", Type.BOOLEAN, "True if the topic is internal."),
                new Field("partitions", new CompactArrayOf(DescribeTopicPartitionsResponsePartition.SCHEMA_0), "Each partition in the topic."),
                new Field("topic_authorized_operations", Type.INT32, "32-bit bitfield to represent authorized operations for this topic."),
                TaggedFieldsSection.of(
                )
            );
        
        public static final Schema[] SCHEMAS = new Schema[] {
            SCHEMA_0
        };
        
        public static final short LOWEST_SUPPORTED_VERSION = 0;
        public static final short HIGHEST_SUPPORTED_VERSION = 0;
        
        public DescribeTopicPartitionsResponseTopic(Readable _readable, short _version) {
            read(_readable, _version);
            this.prev = ImplicitLinkedHashCollection.INVALID_INDEX;
            this.next = ImplicitLinkedHashCollection.INVALID_INDEX;
        }
        
        public DescribeTopicPartitionsResponseTopic() {
            this.errorCode = (short) 0;
            this.name = "";
            this.topicId = Uuid.ZERO_UUID;
            this.isInternal = false;
            this.partitions = new ArrayList<DescribeTopicPartitionsResponsePartition>(0);
            this.topicAuthorizedOperations = -2147483648;
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
                throw new UnsupportedVersionException("Can't read version " + _version + " of DescribeTopicPartitionsResponseTopic");
            }
            this.errorCode = _readable.readShort();
            {
                int length;
                length = _readable.readUnsignedVarint() - 1;
                if (length < 0) {
                    this.name = null;
                } else if (length > 0x7fff) {
                    throw new RuntimeException("string field name had invalid length " + length);
                } else {
                    this.name = _readable.readString(length);
                }
            }
            this.topicId = _readable.readUuid();
            this.isInternal = _readable.readByte() != 0;
            {
                int arrayLength;
                arrayLength = _readable.readUnsignedVarint() - 1;
                if (arrayLength < 0) {
                    throw new RuntimeException("non-nullable field partitions was serialized as null");
                } else {
                    if (arrayLength > _readable.remaining()) {
                        throw new RuntimeException("Tried to allocate a collection of size " + arrayLength + ", but there are only " + _readable.remaining() + " bytes remaining.");
                    }
                    ArrayList<DescribeTopicPartitionsResponsePartition> newCollection = new ArrayList<>(arrayLength);
                    for (int i = 0; i < arrayLength; i++) {
                        newCollection.add(new DescribeTopicPartitionsResponsePartition(_readable, _version));
                    }
                    this.partitions = newCollection;
                }
            }
            this.topicAuthorizedOperations = _readable.readInt();
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
            _writable.writeShort(errorCode);
            if (name == null) {
                _writable.writeUnsignedVarint(0);
            } else {
                byte[] _stringBytes = _cache.getSerializedValue(name);
                _writable.writeUnsignedVarint(_stringBytes.length + 1);
                _writable.writeByteArray(_stringBytes);
            }
            _writable.writeUuid(topicId);
            _writable.writeByte(isInternal ? (byte) 1 : (byte) 0);
            _writable.writeUnsignedVarint(partitions.size() + 1);
            for (DescribeTopicPartitionsResponsePartition partitionsElement : partitions) {
                partitionsElement.write(_writable, _cache, _version);
            }
            _writable.writeInt(topicAuthorizedOperations);
            RawTaggedFieldWriter _rawWriter = RawTaggedFieldWriter.forFields(_unknownTaggedFields);
            _numTaggedFields += _rawWriter.numFields();
            _writable.writeUnsignedVarint(_numTaggedFields);
            _rawWriter.writeRawTags(_writable, Integer.MAX_VALUE);
        }
        
        @Override
        public void addSize(MessageSizeAccumulator _size, ObjectSerializationCache _cache, short _version) {
            int _numTaggedFields = 0;
            if (_version > 0) {
                throw new UnsupportedVersionException("Can't size version " + _version + " of DescribeTopicPartitionsResponseTopic");
            }
            _size.addBytes(2);
            if (name == null) {
                _size.addBytes(1);
            } else {
                byte[] _stringBytes = name.getBytes(StandardCharsets.UTF_8);
                if (_stringBytes.length > 0x7fff) {
                    throw new RuntimeException("'name' field is too long to be serialized");
                }
                _cache.cacheSerializedValue(name, _stringBytes);
                _size.addBytes(_stringBytes.length + ByteUtils.sizeOfUnsignedVarint(_stringBytes.length + 1));
            }
            _size.addBytes(16);
            _size.addBytes(1);
            {
                _size.addBytes(ByteUtils.sizeOfUnsignedVarint(partitions.size() + 1));
                for (DescribeTopicPartitionsResponsePartition partitionsElement : partitions) {
                    partitionsElement.addSize(_size, _cache, _version);
                }
            }
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
        public boolean elementKeysAreEqual(Object obj) {
            if (!(obj instanceof DescribeTopicPartitionsResponseTopic)) return false;
            DescribeTopicPartitionsResponseTopic other = (DescribeTopicPartitionsResponseTopic) obj;
            if (this.name == null) {
                if (other.name != null) return false;
            } else {
                if (!this.name.equals(other.name)) return false;
            }
            return true;
        }
        
        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof DescribeTopicPartitionsResponseTopic)) return false;
            DescribeTopicPartitionsResponseTopic other = (DescribeTopicPartitionsResponseTopic) obj;
            if (errorCode != other.errorCode) return false;
            if (this.name == null) {
                if (other.name != null) return false;
            } else {
                if (!this.name.equals(other.name)) return false;
            }
            if (!this.topicId.equals(other.topicId)) return false;
            if (isInternal != other.isInternal) return false;
            if (this.partitions == null) {
                if (other.partitions != null) return false;
            } else {
                if (!this.partitions.equals(other.partitions)) return false;
            }
            if (topicAuthorizedOperations != other.topicAuthorizedOperations) return false;
            return MessageUtil.compareRawTaggedFields(_unknownTaggedFields, other._unknownTaggedFields);
        }
        
        @Override
        public int hashCode() {
            int hashCode = 0;
            hashCode = 31 * hashCode + (name == null ? 0 : name.hashCode());
            return hashCode;
        }
        
        @Override
        public DescribeTopicPartitionsResponseTopic duplicate() {
            DescribeTopicPartitionsResponseTopic _duplicate = new DescribeTopicPartitionsResponseTopic();
            _duplicate.errorCode = errorCode;
            if (name == null) {
                _duplicate.name = null;
            } else {
                _duplicate.name = name;
            }
            _duplicate.topicId = topicId;
            _duplicate.isInternal = isInternal;
            ArrayList<DescribeTopicPartitionsResponsePartition> newPartitions = new ArrayList<DescribeTopicPartitionsResponsePartition>(partitions.size());
            for (DescribeTopicPartitionsResponsePartition _element : partitions) {
                newPartitions.add(_element.duplicate());
            }
            _duplicate.partitions = newPartitions;
            _duplicate.topicAuthorizedOperations = topicAuthorizedOperations;
            return _duplicate;
        }
        
        @Override
        public String toString() {
            return "DescribeTopicPartitionsResponseTopic("
                + "errorCode=" + errorCode
                + ", name=" + ((name == null) ? "null" : "'" + name.toString() + "'")
                + ", topicId=" + topicId.toString()
                + ", isInternal=" + (isInternal ? "true" : "false")
                + ", partitions=" + MessageUtil.deepToString(partitions.iterator())
                + ", topicAuthorizedOperations=" + topicAuthorizedOperations
                + ")";
        }
        
        public short errorCode() {
            return this.errorCode;
        }
        
        public String name() {
            return this.name;
        }
        
        public Uuid topicId() {
            return this.topicId;
        }
        
        public boolean isInternal() {
            return this.isInternal;
        }
        
        public List<DescribeTopicPartitionsResponsePartition> partitions() {
            return this.partitions;
        }
        
        public int topicAuthorizedOperations() {
            return this.topicAuthorizedOperations;
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
        
        public DescribeTopicPartitionsResponseTopic setErrorCode(short v) {
            this.errorCode = v;
            return this;
        }
        
        public DescribeTopicPartitionsResponseTopic setName(String v) {
            this.name = v;
            return this;
        }
        
        public DescribeTopicPartitionsResponseTopic setTopicId(Uuid v) {
            this.topicId = v;
            return this;
        }
        
        public DescribeTopicPartitionsResponseTopic setIsInternal(boolean v) {
            this.isInternal = v;
            return this;
        }
        
        public DescribeTopicPartitionsResponseTopic setPartitions(List<DescribeTopicPartitionsResponsePartition> v) {
            this.partitions = v;
            return this;
        }
        
        public DescribeTopicPartitionsResponseTopic setTopicAuthorizedOperations(int v) {
            this.topicAuthorizedOperations = v;
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
    
    public static class DescribeTopicPartitionsResponsePartition implements Message {
        short errorCode;
        int partitionIndex;
        int leaderId;
        int leaderEpoch;
        List<Integer> replicaNodes;
        List<Integer> isrNodes;
        List<Integer> eligibleLeaderReplicas;
        List<Integer> lastKnownElr;
        List<Integer> offlineReplicas;
        private List<RawTaggedField> _unknownTaggedFields;
        
        public static final Schema SCHEMA_0 =
            new Schema(
                new Field("error_code", Type.INT16, "The partition error, or 0 if there was no error."),
                new Field("partition_index", Type.INT32, "The partition index."),
                new Field("leader_id", Type.INT32, "The ID of the leader broker."),
                new Field("leader_epoch", Type.INT32, "The leader epoch of this partition."),
                new Field("replica_nodes", new CompactArrayOf(Type.INT32), "The set of all nodes that host this partition."),
                new Field("isr_nodes", new CompactArrayOf(Type.INT32), "The set of nodes that are in sync with the leader for this partition."),
                new Field("eligible_leader_replicas", CompactArrayOf.nullable(Type.INT32), "The new eligible leader replicas otherwise."),
                new Field("last_known_elr", CompactArrayOf.nullable(Type.INT32), "The last known ELR."),
                new Field("offline_replicas", new CompactArrayOf(Type.INT32), "The set of offline replicas of this partition."),
                TaggedFieldsSection.of(
                )
            );
        
        public static final Schema[] SCHEMAS = new Schema[] {
            SCHEMA_0
        };
        
        public static final short LOWEST_SUPPORTED_VERSION = 0;
        public static final short HIGHEST_SUPPORTED_VERSION = 0;
        
        public DescribeTopicPartitionsResponsePartition(Readable _readable, short _version) {
            read(_readable, _version);
        }
        
        public DescribeTopicPartitionsResponsePartition() {
            this.errorCode = (short) 0;
            this.partitionIndex = 0;
            this.leaderId = 0;
            this.leaderEpoch = -1;
            this.replicaNodes = new ArrayList<Integer>(0);
            this.isrNodes = new ArrayList<Integer>(0);
            this.eligibleLeaderReplicas = null;
            this.lastKnownElr = null;
            this.offlineReplicas = new ArrayList<Integer>(0);
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
                throw new UnsupportedVersionException("Can't read version " + _version + " of DescribeTopicPartitionsResponsePartition");
            }
            this.errorCode = _readable.readShort();
            this.partitionIndex = _readable.readInt();
            this.leaderId = _readable.readInt();
            this.leaderEpoch = _readable.readInt();
            {
                int arrayLength;
                arrayLength = _readable.readUnsignedVarint() - 1;
                if (arrayLength < 0) {
                    throw new RuntimeException("non-nullable field replicaNodes was serialized as null");
                } else {
                    if (arrayLength > _readable.remaining()) {
                        throw new RuntimeException("Tried to allocate a collection of size " + arrayLength + ", but there are only " + _readable.remaining() + " bytes remaining.");
                    }
                    ArrayList<Integer> newCollection = new ArrayList<>(arrayLength);
                    for (int i = 0; i < arrayLength; i++) {
                        newCollection.add(_readable.readInt());
                    }
                    this.replicaNodes = newCollection;
                }
            }
            {
                int arrayLength;
                arrayLength = _readable.readUnsignedVarint() - 1;
                if (arrayLength < 0) {
                    throw new RuntimeException("non-nullable field isrNodes was serialized as null");
                } else {
                    if (arrayLength > _readable.remaining()) {
                        throw new RuntimeException("Tried to allocate a collection of size " + arrayLength + ", but there are only " + _readable.remaining() + " bytes remaining.");
                    }
                    ArrayList<Integer> newCollection = new ArrayList<>(arrayLength);
                    for (int i = 0; i < arrayLength; i++) {
                        newCollection.add(_readable.readInt());
                    }
                    this.isrNodes = newCollection;
                }
            }
            {
                int arrayLength;
                arrayLength = _readable.readUnsignedVarint() - 1;
                if (arrayLength < 0) {
                    this.eligibleLeaderReplicas = null;
                } else {
                    if (arrayLength > _readable.remaining()) {
                        throw new RuntimeException("Tried to allocate a collection of size " + arrayLength + ", but there are only " + _readable.remaining() + " bytes remaining.");
                    }
                    ArrayList<Integer> newCollection = new ArrayList<>(arrayLength);
                    for (int i = 0; i < arrayLength; i++) {
                        newCollection.add(_readable.readInt());
                    }
                    this.eligibleLeaderReplicas = newCollection;
                }
            }
            {
                int arrayLength;
                arrayLength = _readable.readUnsignedVarint() - 1;
                if (arrayLength < 0) {
                    this.lastKnownElr = null;
                } else {
                    if (arrayLength > _readable.remaining()) {
                        throw new RuntimeException("Tried to allocate a collection of size " + arrayLength + ", but there are only " + _readable.remaining() + " bytes remaining.");
                    }
                    ArrayList<Integer> newCollection = new ArrayList<>(arrayLength);
                    for (int i = 0; i < arrayLength; i++) {
                        newCollection.add(_readable.readInt());
                    }
                    this.lastKnownElr = newCollection;
                }
            }
            {
                int arrayLength;
                arrayLength = _readable.readUnsignedVarint() - 1;
                if (arrayLength < 0) {
                    throw new RuntimeException("non-nullable field offlineReplicas was serialized as null");
                } else {
                    if (arrayLength > _readable.remaining()) {
                        throw new RuntimeException("Tried to allocate a collection of size " + arrayLength + ", but there are only " + _readable.remaining() + " bytes remaining.");
                    }
                    ArrayList<Integer> newCollection = new ArrayList<>(arrayLength);
                    for (int i = 0; i < arrayLength; i++) {
                        newCollection.add(_readable.readInt());
                    }
                    this.offlineReplicas = newCollection;
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
            _writable.writeShort(errorCode);
            _writable.writeInt(partitionIndex);
            _writable.writeInt(leaderId);
            _writable.writeInt(leaderEpoch);
            _writable.writeUnsignedVarint(replicaNodes.size() + 1);
            for (Integer replicaNodesElement : replicaNodes) {
                _writable.writeInt(replicaNodesElement);
            }
            _writable.writeUnsignedVarint(isrNodes.size() + 1);
            for (Integer isrNodesElement : isrNodes) {
                _writable.writeInt(isrNodesElement);
            }
            if (eligibleLeaderReplicas == null) {
                _writable.writeUnsignedVarint(0);
            } else {
                _writable.writeUnsignedVarint(eligibleLeaderReplicas.size() + 1);
                for (Integer eligibleLeaderReplicasElement : eligibleLeaderReplicas) {
                    _writable.writeInt(eligibleLeaderReplicasElement);
                }
            }
            if (lastKnownElr == null) {
                _writable.writeUnsignedVarint(0);
            } else {
                _writable.writeUnsignedVarint(lastKnownElr.size() + 1);
                for (Integer lastKnownElrElement : lastKnownElr) {
                    _writable.writeInt(lastKnownElrElement);
                }
            }
            _writable.writeUnsignedVarint(offlineReplicas.size() + 1);
            for (Integer offlineReplicasElement : offlineReplicas) {
                _writable.writeInt(offlineReplicasElement);
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
                throw new UnsupportedVersionException("Can't size version " + _version + " of DescribeTopicPartitionsResponsePartition");
            }
            _size.addBytes(2);
            _size.addBytes(4);
            _size.addBytes(4);
            _size.addBytes(4);
            {
                _size.addBytes(ByteUtils.sizeOfUnsignedVarint(replicaNodes.size() + 1));
                _size.addBytes(replicaNodes.size() * 4);
            }
            {
                _size.addBytes(ByteUtils.sizeOfUnsignedVarint(isrNodes.size() + 1));
                _size.addBytes(isrNodes.size() * 4);
            }
            if (eligibleLeaderReplicas == null) {
                _size.addBytes(1);
            } else {
                _size.addBytes(ByteUtils.sizeOfUnsignedVarint(eligibleLeaderReplicas.size() + 1));
                _size.addBytes(eligibleLeaderReplicas.size() * 4);
            }
            if (lastKnownElr == null) {
                _size.addBytes(1);
            } else {
                _size.addBytes(ByteUtils.sizeOfUnsignedVarint(lastKnownElr.size() + 1));
                _size.addBytes(lastKnownElr.size() * 4);
            }
            {
                _size.addBytes(ByteUtils.sizeOfUnsignedVarint(offlineReplicas.size() + 1));
                _size.addBytes(offlineReplicas.size() * 4);
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
            if (!(obj instanceof DescribeTopicPartitionsResponsePartition)) return false;
            DescribeTopicPartitionsResponsePartition other = (DescribeTopicPartitionsResponsePartition) obj;
            if (errorCode != other.errorCode) return false;
            if (partitionIndex != other.partitionIndex) return false;
            if (leaderId != other.leaderId) return false;
            if (leaderEpoch != other.leaderEpoch) return false;
            if (this.replicaNodes == null) {
                if (other.replicaNodes != null) return false;
            } else {
                if (!this.replicaNodes.equals(other.replicaNodes)) return false;
            }
            if (this.isrNodes == null) {
                if (other.isrNodes != null) return false;
            } else {
                if (!this.isrNodes.equals(other.isrNodes)) return false;
            }
            if (this.eligibleLeaderReplicas == null) {
                if (other.eligibleLeaderReplicas != null) return false;
            } else {
                if (!this.eligibleLeaderReplicas.equals(other.eligibleLeaderReplicas)) return false;
            }
            if (this.lastKnownElr == null) {
                if (other.lastKnownElr != null) return false;
            } else {
                if (!this.lastKnownElr.equals(other.lastKnownElr)) return false;
            }
            if (this.offlineReplicas == null) {
                if (other.offlineReplicas != null) return false;
            } else {
                if (!this.offlineReplicas.equals(other.offlineReplicas)) return false;
            }
            return MessageUtil.compareRawTaggedFields(_unknownTaggedFields, other._unknownTaggedFields);
        }
        
        @Override
        public int hashCode() {
            int hashCode = 0;
            hashCode = 31 * hashCode + errorCode;
            hashCode = 31 * hashCode + partitionIndex;
            hashCode = 31 * hashCode + leaderId;
            hashCode = 31 * hashCode + leaderEpoch;
            hashCode = 31 * hashCode + (replicaNodes == null ? 0 : replicaNodes.hashCode());
            hashCode = 31 * hashCode + (isrNodes == null ? 0 : isrNodes.hashCode());
            hashCode = 31 * hashCode + (eligibleLeaderReplicas == null ? 0 : eligibleLeaderReplicas.hashCode());
            hashCode = 31 * hashCode + (lastKnownElr == null ? 0 : lastKnownElr.hashCode());
            hashCode = 31 * hashCode + (offlineReplicas == null ? 0 : offlineReplicas.hashCode());
            return hashCode;
        }
        
        @Override
        public DescribeTopicPartitionsResponsePartition duplicate() {
            DescribeTopicPartitionsResponsePartition _duplicate = new DescribeTopicPartitionsResponsePartition();
            _duplicate.errorCode = errorCode;
            _duplicate.partitionIndex = partitionIndex;
            _duplicate.leaderId = leaderId;
            _duplicate.leaderEpoch = leaderEpoch;
            ArrayList<Integer> newReplicaNodes = new ArrayList<Integer>(replicaNodes.size());
            for (Integer _element : replicaNodes) {
                newReplicaNodes.add(_element);
            }
            _duplicate.replicaNodes = newReplicaNodes;
            ArrayList<Integer> newIsrNodes = new ArrayList<Integer>(isrNodes.size());
            for (Integer _element : isrNodes) {
                newIsrNodes.add(_element);
            }
            _duplicate.isrNodes = newIsrNodes;
            if (eligibleLeaderReplicas == null) {
                _duplicate.eligibleLeaderReplicas = null;
            } else {
                ArrayList<Integer> newEligibleLeaderReplicas = new ArrayList<Integer>(eligibleLeaderReplicas.size());
                for (Integer _element : eligibleLeaderReplicas) {
                    newEligibleLeaderReplicas.add(_element);
                }
                _duplicate.eligibleLeaderReplicas = newEligibleLeaderReplicas;
            }
            if (lastKnownElr == null) {
                _duplicate.lastKnownElr = null;
            } else {
                ArrayList<Integer> newLastKnownElr = new ArrayList<Integer>(lastKnownElr.size());
                for (Integer _element : lastKnownElr) {
                    newLastKnownElr.add(_element);
                }
                _duplicate.lastKnownElr = newLastKnownElr;
            }
            ArrayList<Integer> newOfflineReplicas = new ArrayList<Integer>(offlineReplicas.size());
            for (Integer _element : offlineReplicas) {
                newOfflineReplicas.add(_element);
            }
            _duplicate.offlineReplicas = newOfflineReplicas;
            return _duplicate;
        }
        
        @Override
        public String toString() {
            return "DescribeTopicPartitionsResponsePartition("
                + "errorCode=" + errorCode
                + ", partitionIndex=" + partitionIndex
                + ", leaderId=" + leaderId
                + ", leaderEpoch=" + leaderEpoch
                + ", replicaNodes=" + MessageUtil.deepToString(replicaNodes.iterator())
                + ", isrNodes=" + MessageUtil.deepToString(isrNodes.iterator())
                + ", eligibleLeaderReplicas=" + ((eligibleLeaderReplicas == null) ? "null" : MessageUtil.deepToString(eligibleLeaderReplicas.iterator()))
                + ", lastKnownElr=" + ((lastKnownElr == null) ? "null" : MessageUtil.deepToString(lastKnownElr.iterator()))
                + ", offlineReplicas=" + MessageUtil.deepToString(offlineReplicas.iterator())
                + ")";
        }
        
        public short errorCode() {
            return this.errorCode;
        }
        
        public int partitionIndex() {
            return this.partitionIndex;
        }
        
        public int leaderId() {
            return this.leaderId;
        }
        
        public int leaderEpoch() {
            return this.leaderEpoch;
        }
        
        public List<Integer> replicaNodes() {
            return this.replicaNodes;
        }
        
        public List<Integer> isrNodes() {
            return this.isrNodes;
        }
        
        public List<Integer> eligibleLeaderReplicas() {
            return this.eligibleLeaderReplicas;
        }
        
        public List<Integer> lastKnownElr() {
            return this.lastKnownElr;
        }
        
        public List<Integer> offlineReplicas() {
            return this.offlineReplicas;
        }
        
        @Override
        public List<RawTaggedField> unknownTaggedFields() {
            if (_unknownTaggedFields == null) {
                _unknownTaggedFields = new ArrayList<>(0);
            }
            return _unknownTaggedFields;
        }
        
        public DescribeTopicPartitionsResponsePartition setErrorCode(short v) {
            this.errorCode = v;
            return this;
        }
        
        public DescribeTopicPartitionsResponsePartition setPartitionIndex(int v) {
            this.partitionIndex = v;
            return this;
        }
        
        public DescribeTopicPartitionsResponsePartition setLeaderId(int v) {
            this.leaderId = v;
            return this;
        }
        
        public DescribeTopicPartitionsResponsePartition setLeaderEpoch(int v) {
            this.leaderEpoch = v;
            return this;
        }
        
        public DescribeTopicPartitionsResponsePartition setReplicaNodes(List<Integer> v) {
            this.replicaNodes = v;
            return this;
        }
        
        public DescribeTopicPartitionsResponsePartition setIsrNodes(List<Integer> v) {
            this.isrNodes = v;
            return this;
        }
        
        public DescribeTopicPartitionsResponsePartition setEligibleLeaderReplicas(List<Integer> v) {
            this.eligibleLeaderReplicas = v;
            return this;
        }
        
        public DescribeTopicPartitionsResponsePartition setLastKnownElr(List<Integer> v) {
            this.lastKnownElr = v;
            return this;
        }
        
        public DescribeTopicPartitionsResponsePartition setOfflineReplicas(List<Integer> v) {
            this.offlineReplicas = v;
            return this;
        }
    }
    
    public static class DescribeTopicPartitionsResponseTopicCollection extends ImplicitLinkedHashMultiCollection<DescribeTopicPartitionsResponseTopic> {
        public DescribeTopicPartitionsResponseTopicCollection() {
            super();
        }
        
        public DescribeTopicPartitionsResponseTopicCollection(int expectedNumElements) {
            super(expectedNumElements);
        }
        
        public DescribeTopicPartitionsResponseTopicCollection(Iterator<DescribeTopicPartitionsResponseTopic> iterator) {
            super(iterator);
        }
        
        public DescribeTopicPartitionsResponseTopic find(String name) {
            DescribeTopicPartitionsResponseTopic _key = new DescribeTopicPartitionsResponseTopic();
            _key.setName(name);
            return find(_key);
        }
        
        public List<DescribeTopicPartitionsResponseTopic> findAll(String name) {
            DescribeTopicPartitionsResponseTopic _key = new DescribeTopicPartitionsResponseTopic();
            _key.setName(name);
            return findAll(_key);
        }
        
        public DescribeTopicPartitionsResponseTopicCollection duplicate() {
            DescribeTopicPartitionsResponseTopicCollection _duplicate = new DescribeTopicPartitionsResponseTopicCollection(size());
            for (DescribeTopicPartitionsResponseTopic _element : this) {
                _duplicate.add(_element.duplicate());
            }
            return _duplicate;
        }
    }
    
    public static class Cursor implements Message {
        String topicName;
        int partitionIndex;
        private List<RawTaggedField> _unknownTaggedFields;
        
        public static final Schema SCHEMA_0 =
            new Schema(
                new Field("topic_name", Type.COMPACT_STRING, "The name for the first topic to process."),
                new Field("partition_index", Type.INT32, "The partition index to start with."),
                TaggedFieldsSection.of(
                )
            );
        
        public static final Schema[] SCHEMAS = new Schema[] {
            SCHEMA_0
        };
        
        public static final short LOWEST_SUPPORTED_VERSION = 0;
        public static final short HIGHEST_SUPPORTED_VERSION = 0;
        
        public Cursor(Readable _readable, short _version) {
            read(_readable, _version);
        }
        
        public Cursor() {
            this.topicName = "";
            this.partitionIndex = 0;
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
                throw new UnsupportedVersionException("Can't read version " + _version + " of Cursor");
            }
            {
                int length;
                length = _readable.readUnsignedVarint() - 1;
                if (length < 0) {
                    throw new RuntimeException("non-nullable field topicName was serialized as null");
                } else if (length > 0x7fff) {
                    throw new RuntimeException("string field topicName had invalid length " + length);
                } else {
                    this.topicName = _readable.readString(length);
                }
            }
            this.partitionIndex = _readable.readInt();
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
            {
                byte[] _stringBytes = _cache.getSerializedValue(topicName);
                _writable.writeUnsignedVarint(_stringBytes.length + 1);
                _writable.writeByteArray(_stringBytes);
            }
            _writable.writeInt(partitionIndex);
            RawTaggedFieldWriter _rawWriter = RawTaggedFieldWriter.forFields(_unknownTaggedFields);
            _numTaggedFields += _rawWriter.numFields();
            _writable.writeUnsignedVarint(_numTaggedFields);
            _rawWriter.writeRawTags(_writable, Integer.MAX_VALUE);
        }
        
        @Override
        public void addSize(MessageSizeAccumulator _size, ObjectSerializationCache _cache, short _version) {
            int _numTaggedFields = 0;
            if (_version > 0) {
                throw new UnsupportedVersionException("Can't size version " + _version + " of Cursor");
            }
            {
                byte[] _stringBytes = topicName.getBytes(StandardCharsets.UTF_8);
                if (_stringBytes.length > 0x7fff) {
                    throw new RuntimeException("'topicName' field is too long to be serialized");
                }
                _cache.cacheSerializedValue(topicName, _stringBytes);
                _size.addBytes(_stringBytes.length + ByteUtils.sizeOfUnsignedVarint(_stringBytes.length + 1));
            }
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
            if (!(obj instanceof Cursor)) return false;
            Cursor other = (Cursor) obj;
            if (this.topicName == null) {
                if (other.topicName != null) return false;
            } else {
                if (!this.topicName.equals(other.topicName)) return false;
            }
            if (partitionIndex != other.partitionIndex) return false;
            return MessageUtil.compareRawTaggedFields(_unknownTaggedFields, other._unknownTaggedFields);
        }
        
        @Override
        public int hashCode() {
            int hashCode = 0;
            hashCode = 31 * hashCode + (topicName == null ? 0 : topicName.hashCode());
            hashCode = 31 * hashCode + partitionIndex;
            return hashCode;
        }
        
        @Override
        public Cursor duplicate() {
            Cursor _duplicate = new Cursor();
            _duplicate.topicName = topicName;
            _duplicate.partitionIndex = partitionIndex;
            return _duplicate;
        }
        
        @Override
        public String toString() {
            return "Cursor("
                + "topicName=" + ((topicName == null) ? "null" : "'" + topicName.toString() + "'")
                + ", partitionIndex=" + partitionIndex
                + ")";
        }
        
        public String topicName() {
            return this.topicName;
        }
        
        public int partitionIndex() {
            return this.partitionIndex;
        }
        
        @Override
        public List<RawTaggedField> unknownTaggedFields() {
            if (_unknownTaggedFields == null) {
                _unknownTaggedFields = new ArrayList<>(0);
            }
            return _unknownTaggedFields;
        }
        
        public Cursor setTopicName(String v) {
            this.topicName = v;
            return this;
        }
        
        public Cursor setPartitionIndex(int v) {
            this.partitionIndex = v;
            return this;
        }
    }
}
