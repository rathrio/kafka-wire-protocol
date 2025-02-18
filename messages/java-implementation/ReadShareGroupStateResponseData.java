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


public class ReadShareGroupStateResponseData implements ApiMessage {
    List<ReadStateResult> results;
    private List<RawTaggedField> _unknownTaggedFields;
    
    public static final Schema SCHEMA_0 =
        new Schema(
            new Field("results", new CompactArrayOf(ReadStateResult.SCHEMA_0), "The read results."),
            TaggedFieldsSection.of(
            )
        );
    
    public static final Schema[] SCHEMAS = new Schema[] {
        SCHEMA_0
    };
    
    public static final short LOWEST_SUPPORTED_VERSION = 0;
    public static final short HIGHEST_SUPPORTED_VERSION = 0;
    
    public ReadShareGroupStateResponseData(Readable _readable, short _version) {
        read(_readable, _version);
    }
    
    public ReadShareGroupStateResponseData() {
        this.results = new ArrayList<ReadStateResult>(0);
    }
    
    @Override
    public short apiKey() {
        return 84;
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
            int arrayLength;
            arrayLength = _readable.readUnsignedVarint() - 1;
            if (arrayLength < 0) {
                throw new RuntimeException("non-nullable field results was serialized as null");
            } else {
                if (arrayLength > _readable.remaining()) {
                    throw new RuntimeException("Tried to allocate a collection of size " + arrayLength + ", but there are only " + _readable.remaining() + " bytes remaining.");
                }
                ArrayList<ReadStateResult> newCollection = new ArrayList<>(arrayLength);
                for (int i = 0; i < arrayLength; i++) {
                    newCollection.add(new ReadStateResult(_readable, _version));
                }
                this.results = newCollection;
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
        _writable.writeUnsignedVarint(results.size() + 1);
        for (ReadStateResult resultsElement : results) {
            resultsElement.write(_writable, _cache, _version);
        }
        RawTaggedFieldWriter _rawWriter = RawTaggedFieldWriter.forFields(_unknownTaggedFields);
        _numTaggedFields += _rawWriter.numFields();
        _writable.writeUnsignedVarint(_numTaggedFields);
        _rawWriter.writeRawTags(_writable, Integer.MAX_VALUE);
    }
    
    @Override
    public void addSize(MessageSizeAccumulator _size, ObjectSerializationCache _cache, short _version) {
        int _numTaggedFields = 0;
        {
            _size.addBytes(ByteUtils.sizeOfUnsignedVarint(results.size() + 1));
            for (ReadStateResult resultsElement : results) {
                resultsElement.addSize(_size, _cache, _version);
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
        if (!(obj instanceof ReadShareGroupStateResponseData)) return false;
        ReadShareGroupStateResponseData other = (ReadShareGroupStateResponseData) obj;
        if (this.results == null) {
            if (other.results != null) return false;
        } else {
            if (!this.results.equals(other.results)) return false;
        }
        return MessageUtil.compareRawTaggedFields(_unknownTaggedFields, other._unknownTaggedFields);
    }
    
    @Override
    public int hashCode() {
        int hashCode = 0;
        hashCode = 31 * hashCode + (results == null ? 0 : results.hashCode());
        return hashCode;
    }
    
    @Override
    public ReadShareGroupStateResponseData duplicate() {
        ReadShareGroupStateResponseData _duplicate = new ReadShareGroupStateResponseData();
        ArrayList<ReadStateResult> newResults = new ArrayList<ReadStateResult>(results.size());
        for (ReadStateResult _element : results) {
            newResults.add(_element.duplicate());
        }
        _duplicate.results = newResults;
        return _duplicate;
    }
    
    @Override
    public String toString() {
        return "ReadShareGroupStateResponseData("
            + "results=" + MessageUtil.deepToString(results.iterator())
            + ")";
    }
    
    public List<ReadStateResult> results() {
        return this.results;
    }
    
    @Override
    public List<RawTaggedField> unknownTaggedFields() {
        if (_unknownTaggedFields == null) {
            _unknownTaggedFields = new ArrayList<>(0);
        }
        return _unknownTaggedFields;
    }
    
    public ReadShareGroupStateResponseData setResults(List<ReadStateResult> v) {
        this.results = v;
        return this;
    }
    
    public static class ReadStateResult implements Message {
        Uuid topicId;
        List<PartitionResult> partitions;
        private List<RawTaggedField> _unknownTaggedFields;
        
        public static final Schema SCHEMA_0 =
            new Schema(
                new Field("topic_id", Type.UUID, "The topic identifier."),
                new Field("partitions", new CompactArrayOf(PartitionResult.SCHEMA_0), "The results for the partitions."),
                TaggedFieldsSection.of(
                )
            );
        
        public static final Schema[] SCHEMAS = new Schema[] {
            SCHEMA_0
        };
        
        public static final short LOWEST_SUPPORTED_VERSION = 0;
        public static final short HIGHEST_SUPPORTED_VERSION = 0;
        
        public ReadStateResult(Readable _readable, short _version) {
            read(_readable, _version);
        }
        
        public ReadStateResult() {
            this.topicId = Uuid.ZERO_UUID;
            this.partitions = new ArrayList<PartitionResult>(0);
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
                throw new UnsupportedVersionException("Can't read version " + _version + " of ReadStateResult");
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
                    ArrayList<PartitionResult> newCollection = new ArrayList<>(arrayLength);
                    for (int i = 0; i < arrayLength; i++) {
                        newCollection.add(new PartitionResult(_readable, _version));
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
            for (PartitionResult partitionsElement : partitions) {
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
                throw new UnsupportedVersionException("Can't size version " + _version + " of ReadStateResult");
            }
            _size.addBytes(16);
            {
                _size.addBytes(ByteUtils.sizeOfUnsignedVarint(partitions.size() + 1));
                for (PartitionResult partitionsElement : partitions) {
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
            if (!(obj instanceof ReadStateResult)) return false;
            ReadStateResult other = (ReadStateResult) obj;
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
        public ReadStateResult duplicate() {
            ReadStateResult _duplicate = new ReadStateResult();
            _duplicate.topicId = topicId;
            ArrayList<PartitionResult> newPartitions = new ArrayList<PartitionResult>(partitions.size());
            for (PartitionResult _element : partitions) {
                newPartitions.add(_element.duplicate());
            }
            _duplicate.partitions = newPartitions;
            return _duplicate;
        }
        
        @Override
        public String toString() {
            return "ReadStateResult("
                + "topicId=" + topicId.toString()
                + ", partitions=" + MessageUtil.deepToString(partitions.iterator())
                + ")";
        }
        
        public Uuid topicId() {
            return this.topicId;
        }
        
        public List<PartitionResult> partitions() {
            return this.partitions;
        }
        
        @Override
        public List<RawTaggedField> unknownTaggedFields() {
            if (_unknownTaggedFields == null) {
                _unknownTaggedFields = new ArrayList<>(0);
            }
            return _unknownTaggedFields;
        }
        
        public ReadStateResult setTopicId(Uuid v) {
            this.topicId = v;
            return this;
        }
        
        public ReadStateResult setPartitions(List<PartitionResult> v) {
            this.partitions = v;
            return this;
        }
    }
    
    public static class PartitionResult implements Message {
        int partition;
        short errorCode;
        String errorMessage;
        int stateEpoch;
        long startOffset;
        List<StateBatch> stateBatches;
        private List<RawTaggedField> _unknownTaggedFields;
        
        public static final Schema SCHEMA_0 =
            new Schema(
                new Field("partition", Type.INT32, "The partition index."),
                new Field("error_code", Type.INT16, "The error code, or 0 if there was no error."),
                new Field("error_message", Type.COMPACT_NULLABLE_STRING, "The error message, or null if there was no error."),
                new Field("state_epoch", Type.INT32, "The state epoch for this share-partition."),
                new Field("start_offset", Type.INT64, "The share-partition start offset, which can be -1 if it is not yet initialized."),
                new Field("state_batches", new CompactArrayOf(StateBatch.SCHEMA_0), "The state batches for this share-partition."),
                TaggedFieldsSection.of(
                )
            );
        
        public static final Schema[] SCHEMAS = new Schema[] {
            SCHEMA_0
        };
        
        public static final short LOWEST_SUPPORTED_VERSION = 0;
        public static final short HIGHEST_SUPPORTED_VERSION = 0;
        
        public PartitionResult(Readable _readable, short _version) {
            read(_readable, _version);
        }
        
        public PartitionResult() {
            this.partition = 0;
            this.errorCode = (short) 0;
            this.errorMessage = null;
            this.stateEpoch = 0;
            this.startOffset = 0L;
            this.stateBatches = new ArrayList<StateBatch>(0);
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
                throw new UnsupportedVersionException("Can't read version " + _version + " of PartitionResult");
            }
            this.partition = _readable.readInt();
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
            this.stateEpoch = _readable.readInt();
            this.startOffset = _readable.readLong();
            {
                int arrayLength;
                arrayLength = _readable.readUnsignedVarint() - 1;
                if (arrayLength < 0) {
                    throw new RuntimeException("non-nullable field stateBatches was serialized as null");
                } else {
                    if (arrayLength > _readable.remaining()) {
                        throw new RuntimeException("Tried to allocate a collection of size " + arrayLength + ", but there are only " + _readable.remaining() + " bytes remaining.");
                    }
                    ArrayList<StateBatch> newCollection = new ArrayList<>(arrayLength);
                    for (int i = 0; i < arrayLength; i++) {
                        newCollection.add(new StateBatch(_readable, _version));
                    }
                    this.stateBatches = newCollection;
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
            _writable.writeInt(partition);
            _writable.writeShort(errorCode);
            if (errorMessage == null) {
                _writable.writeUnsignedVarint(0);
            } else {
                byte[] _stringBytes = _cache.getSerializedValue(errorMessage);
                _writable.writeUnsignedVarint(_stringBytes.length + 1);
                _writable.writeByteArray(_stringBytes);
            }
            _writable.writeInt(stateEpoch);
            _writable.writeLong(startOffset);
            _writable.writeUnsignedVarint(stateBatches.size() + 1);
            for (StateBatch stateBatchesElement : stateBatches) {
                stateBatchesElement.write(_writable, _cache, _version);
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
                throw new UnsupportedVersionException("Can't size version " + _version + " of PartitionResult");
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
            _size.addBytes(4);
            _size.addBytes(8);
            {
                _size.addBytes(ByteUtils.sizeOfUnsignedVarint(stateBatches.size() + 1));
                for (StateBatch stateBatchesElement : stateBatches) {
                    stateBatchesElement.addSize(_size, _cache, _version);
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
            if (!(obj instanceof PartitionResult)) return false;
            PartitionResult other = (PartitionResult) obj;
            if (partition != other.partition) return false;
            if (errorCode != other.errorCode) return false;
            if (this.errorMessage == null) {
                if (other.errorMessage != null) return false;
            } else {
                if (!this.errorMessage.equals(other.errorMessage)) return false;
            }
            if (stateEpoch != other.stateEpoch) return false;
            if (startOffset != other.startOffset) return false;
            if (this.stateBatches == null) {
                if (other.stateBatches != null) return false;
            } else {
                if (!this.stateBatches.equals(other.stateBatches)) return false;
            }
            return MessageUtil.compareRawTaggedFields(_unknownTaggedFields, other._unknownTaggedFields);
        }
        
        @Override
        public int hashCode() {
            int hashCode = 0;
            hashCode = 31 * hashCode + partition;
            hashCode = 31 * hashCode + errorCode;
            hashCode = 31 * hashCode + (errorMessage == null ? 0 : errorMessage.hashCode());
            hashCode = 31 * hashCode + stateEpoch;
            hashCode = 31 * hashCode + ((int) (startOffset >> 32) ^ (int) startOffset);
            hashCode = 31 * hashCode + (stateBatches == null ? 0 : stateBatches.hashCode());
            return hashCode;
        }
        
        @Override
        public PartitionResult duplicate() {
            PartitionResult _duplicate = new PartitionResult();
            _duplicate.partition = partition;
            _duplicate.errorCode = errorCode;
            if (errorMessage == null) {
                _duplicate.errorMessage = null;
            } else {
                _duplicate.errorMessage = errorMessage;
            }
            _duplicate.stateEpoch = stateEpoch;
            _duplicate.startOffset = startOffset;
            ArrayList<StateBatch> newStateBatches = new ArrayList<StateBatch>(stateBatches.size());
            for (StateBatch _element : stateBatches) {
                newStateBatches.add(_element.duplicate());
            }
            _duplicate.stateBatches = newStateBatches;
            return _duplicate;
        }
        
        @Override
        public String toString() {
            return "PartitionResult("
                + "partition=" + partition
                + ", errorCode=" + errorCode
                + ", errorMessage=" + ((errorMessage == null) ? "null" : "'" + errorMessage.toString() + "'")
                + ", stateEpoch=" + stateEpoch
                + ", startOffset=" + startOffset
                + ", stateBatches=" + MessageUtil.deepToString(stateBatches.iterator())
                + ")";
        }
        
        public int partition() {
            return this.partition;
        }
        
        public short errorCode() {
            return this.errorCode;
        }
        
        public String errorMessage() {
            return this.errorMessage;
        }
        
        public int stateEpoch() {
            return this.stateEpoch;
        }
        
        public long startOffset() {
            return this.startOffset;
        }
        
        public List<StateBatch> stateBatches() {
            return this.stateBatches;
        }
        
        @Override
        public List<RawTaggedField> unknownTaggedFields() {
            if (_unknownTaggedFields == null) {
                _unknownTaggedFields = new ArrayList<>(0);
            }
            return _unknownTaggedFields;
        }
        
        public PartitionResult setPartition(int v) {
            this.partition = v;
            return this;
        }
        
        public PartitionResult setErrorCode(short v) {
            this.errorCode = v;
            return this;
        }
        
        public PartitionResult setErrorMessage(String v) {
            this.errorMessage = v;
            return this;
        }
        
        public PartitionResult setStateEpoch(int v) {
            this.stateEpoch = v;
            return this;
        }
        
        public PartitionResult setStartOffset(long v) {
            this.startOffset = v;
            return this;
        }
        
        public PartitionResult setStateBatches(List<StateBatch> v) {
            this.stateBatches = v;
            return this;
        }
    }
    
    public static class StateBatch implements Message {
        long firstOffset;
        long lastOffset;
        byte deliveryState;
        short deliveryCount;
        private List<RawTaggedField> _unknownTaggedFields;
        
        public static final Schema SCHEMA_0 =
            new Schema(
                new Field("first_offset", Type.INT64, "The base offset of this state batch."),
                new Field("last_offset", Type.INT64, "The last offset of this state batch."),
                new Field("delivery_state", Type.INT8, "The state - 0:Available,2:Acked,4:Archived."),
                new Field("delivery_count", Type.INT16, "The delivery count."),
                TaggedFieldsSection.of(
                )
            );
        
        public static final Schema[] SCHEMAS = new Schema[] {
            SCHEMA_0
        };
        
        public static final short LOWEST_SUPPORTED_VERSION = 0;
        public static final short HIGHEST_SUPPORTED_VERSION = 0;
        
        public StateBatch(Readable _readable, short _version) {
            read(_readable, _version);
        }
        
        public StateBatch() {
            this.firstOffset = 0L;
            this.lastOffset = 0L;
            this.deliveryState = (byte) 0;
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
                throw new UnsupportedVersionException("Can't read version " + _version + " of StateBatch");
            }
            this.firstOffset = _readable.readLong();
            this.lastOffset = _readable.readLong();
            this.deliveryState = _readable.readByte();
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
            _writable.writeByte(deliveryState);
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
                throw new UnsupportedVersionException("Can't size version " + _version + " of StateBatch");
            }
            _size.addBytes(8);
            _size.addBytes(8);
            _size.addBytes(1);
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
            if (!(obj instanceof StateBatch)) return false;
            StateBatch other = (StateBatch) obj;
            if (firstOffset != other.firstOffset) return false;
            if (lastOffset != other.lastOffset) return false;
            if (deliveryState != other.deliveryState) return false;
            if (deliveryCount != other.deliveryCount) return false;
            return MessageUtil.compareRawTaggedFields(_unknownTaggedFields, other._unknownTaggedFields);
        }
        
        @Override
        public int hashCode() {
            int hashCode = 0;
            hashCode = 31 * hashCode + ((int) (firstOffset >> 32) ^ (int) firstOffset);
            hashCode = 31 * hashCode + ((int) (lastOffset >> 32) ^ (int) lastOffset);
            hashCode = 31 * hashCode + deliveryState;
            hashCode = 31 * hashCode + deliveryCount;
            return hashCode;
        }
        
        @Override
        public StateBatch duplicate() {
            StateBatch _duplicate = new StateBatch();
            _duplicate.firstOffset = firstOffset;
            _duplicate.lastOffset = lastOffset;
            _duplicate.deliveryState = deliveryState;
            _duplicate.deliveryCount = deliveryCount;
            return _duplicate;
        }
        
        @Override
        public String toString() {
            return "StateBatch("
                + "firstOffset=" + firstOffset
                + ", lastOffset=" + lastOffset
                + ", deliveryState=" + deliveryState
                + ", deliveryCount=" + deliveryCount
                + ")";
        }
        
        public long firstOffset() {
            return this.firstOffset;
        }
        
        public long lastOffset() {
            return this.lastOffset;
        }
        
        public byte deliveryState() {
            return this.deliveryState;
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
        
        public StateBatch setFirstOffset(long v) {
            this.firstOffset = v;
            return this;
        }
        
        public StateBatch setLastOffset(long v) {
            this.lastOffset = v;
            return this;
        }
        
        public StateBatch setDeliveryState(byte v) {
            this.deliveryState = v;
            return this;
        }
        
        public StateBatch setDeliveryCount(short v) {
            this.deliveryCount = v;
            return this;
        }
    }
}
