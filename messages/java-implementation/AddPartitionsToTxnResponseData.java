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
import org.apache.kafka.common.errors.UnsupportedVersionException;
import org.apache.kafka.common.protocol.ApiMessage;
import org.apache.kafka.common.protocol.Message;
import org.apache.kafka.common.protocol.MessageSizeAccumulator;
import org.apache.kafka.common.protocol.MessageUtil;
import org.apache.kafka.common.protocol.ObjectSerializationCache;
import org.apache.kafka.common.protocol.Readable;
import org.apache.kafka.common.protocol.Writable;
import org.apache.kafka.common.protocol.types.ArrayOf;
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


public class AddPartitionsToTxnResponseData implements ApiMessage {
    int throttleTimeMs;
    short errorCode;
    AddPartitionsToTxnResultCollection resultsByTransaction;
    AddPartitionsToTxnTopicResultCollection resultsByTopicV3AndBelow;
    private List<RawTaggedField> _unknownTaggedFields;
    
    public static final Schema SCHEMA_0 =
        new Schema(
            new Field("throttle_time_ms", Type.INT32, "Duration in milliseconds for which the request was throttled due to a quota violation, or zero if the request did not violate any quota."),
            new Field("results_by_topic_v3_and_below", new ArrayOf(AddPartitionsToTxnTopicResult.SCHEMA_0), "The results for each topic.")
        );
    
    public static final Schema SCHEMA_1 = SCHEMA_0;
    
    public static final Schema SCHEMA_2 = SCHEMA_1;
    
    public static final Schema SCHEMA_3 =
        new Schema(
            new Field("throttle_time_ms", Type.INT32, "Duration in milliseconds for which the request was throttled due to a quota violation, or zero if the request did not violate any quota."),
            new Field("results_by_topic_v3_and_below", new CompactArrayOf(AddPartitionsToTxnTopicResult.SCHEMA_3), "The results for each topic."),
            TaggedFieldsSection.of(
            )
        );
    
    public static final Schema SCHEMA_4 =
        new Schema(
            new Field("throttle_time_ms", Type.INT32, "Duration in milliseconds for which the request was throttled due to a quota violation, or zero if the request did not violate any quota."),
            new Field("error_code", Type.INT16, "The response top level error code."),
            new Field("results_by_transaction", new CompactArrayOf(AddPartitionsToTxnResult.SCHEMA_4), "Results categorized by transactional ID."),
            TaggedFieldsSection.of(
            )
        );
    
    public static final Schema SCHEMA_5 = SCHEMA_4;
    
    public static final Schema[] SCHEMAS = new Schema[] {
        SCHEMA_0,
        SCHEMA_1,
        SCHEMA_2,
        SCHEMA_3,
        SCHEMA_4,
        SCHEMA_5
    };
    
    public static final short LOWEST_SUPPORTED_VERSION = 0;
    public static final short HIGHEST_SUPPORTED_VERSION = 5;
    
    public AddPartitionsToTxnResponseData(Readable _readable, short _version) {
        read(_readable, _version);
    }
    
    public AddPartitionsToTxnResponseData() {
        this.throttleTimeMs = 0;
        this.errorCode = (short) 0;
        this.resultsByTransaction = new AddPartitionsToTxnResultCollection(0);
        this.resultsByTopicV3AndBelow = new AddPartitionsToTxnTopicResultCollection(0);
    }
    
    @Override
    public short apiKey() {
        return 24;
    }
    
    @Override
    public short lowestSupportedVersion() {
        return 0;
    }
    
    @Override
    public short highestSupportedVersion() {
        return 5;
    }
    
    @Override
    public final void read(Readable _readable, short _version) {
        this.throttleTimeMs = _readable.readInt();
        if (_version >= 4) {
            this.errorCode = _readable.readShort();
        } else {
            this.errorCode = (short) 0;
        }
        if (_version >= 4) {
            int arrayLength;
            arrayLength = _readable.readUnsignedVarint() - 1;
            if (arrayLength < 0) {
                throw new RuntimeException("non-nullable field resultsByTransaction was serialized as null");
            } else {
                if (arrayLength > _readable.remaining()) {
                    throw new RuntimeException("Tried to allocate a collection of size " + arrayLength + ", but there are only " + _readable.remaining() + " bytes remaining.");
                }
                AddPartitionsToTxnResultCollection newCollection = new AddPartitionsToTxnResultCollection(arrayLength);
                for (int i = 0; i < arrayLength; i++) {
                    newCollection.add(new AddPartitionsToTxnResult(_readable, _version));
                }
                this.resultsByTransaction = newCollection;
            }
        } else {
            this.resultsByTransaction = new AddPartitionsToTxnResultCollection(0);
        }
        if (_version <= 3) {
            if (_version >= 3) {
                int arrayLength;
                arrayLength = _readable.readUnsignedVarint() - 1;
                if (arrayLength < 0) {
                    throw new RuntimeException("non-nullable field resultsByTopicV3AndBelow was serialized as null");
                } else {
                    if (arrayLength > _readable.remaining()) {
                        throw new RuntimeException("Tried to allocate a collection of size " + arrayLength + ", but there are only " + _readable.remaining() + " bytes remaining.");
                    }
                    AddPartitionsToTxnTopicResultCollection newCollection = new AddPartitionsToTxnTopicResultCollection(arrayLength);
                    for (int i = 0; i < arrayLength; i++) {
                        newCollection.add(new AddPartitionsToTxnTopicResult(_readable, _version));
                    }
                    this.resultsByTopicV3AndBelow = newCollection;
                }
            } else {
                int arrayLength;
                arrayLength = _readable.readInt();
                if (arrayLength < 0) {
                    throw new RuntimeException("non-nullable field resultsByTopicV3AndBelow was serialized as null");
                } else {
                    if (arrayLength > _readable.remaining()) {
                        throw new RuntimeException("Tried to allocate a collection of size " + arrayLength + ", but there are only " + _readable.remaining() + " bytes remaining.");
                    }
                    AddPartitionsToTxnTopicResultCollection newCollection = new AddPartitionsToTxnTopicResultCollection(arrayLength);
                    for (int i = 0; i < arrayLength; i++) {
                        newCollection.add(new AddPartitionsToTxnTopicResult(_readable, _version));
                    }
                    this.resultsByTopicV3AndBelow = newCollection;
                }
            }
        } else {
            this.resultsByTopicV3AndBelow = new AddPartitionsToTxnTopicResultCollection(0);
        }
        this._unknownTaggedFields = null;
        if (_version >= 3) {
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
    }
    
    @Override
    public void write(Writable _writable, ObjectSerializationCache _cache, short _version) {
        int _numTaggedFields = 0;
        _writable.writeInt(throttleTimeMs);
        if (_version >= 4) {
            _writable.writeShort(errorCode);
        }
        if (_version >= 4) {
            _writable.writeUnsignedVarint(resultsByTransaction.size() + 1);
            for (AddPartitionsToTxnResult resultsByTransactionElement : resultsByTransaction) {
                resultsByTransactionElement.write(_writable, _cache, _version);
            }
        } else {
            if (!this.resultsByTransaction.isEmpty()) {
                throw new UnsupportedVersionException("Attempted to write a non-default resultsByTransaction at version " + _version);
            }
        }
        if (_version <= 3) {
            if (_version >= 3) {
                _writable.writeUnsignedVarint(resultsByTopicV3AndBelow.size() + 1);
                for (AddPartitionsToTxnTopicResult resultsByTopicV3AndBelowElement : resultsByTopicV3AndBelow) {
                    resultsByTopicV3AndBelowElement.write(_writable, _cache, _version);
                }
            } else {
                _writable.writeInt(resultsByTopicV3AndBelow.size());
                for (AddPartitionsToTxnTopicResult resultsByTopicV3AndBelowElement : resultsByTopicV3AndBelow) {
                    resultsByTopicV3AndBelowElement.write(_writable, _cache, _version);
                }
            }
        } else {
            if (!this.resultsByTopicV3AndBelow.isEmpty()) {
                throw new UnsupportedVersionException("Attempted to write a non-default resultsByTopicV3AndBelow at version " + _version);
            }
        }
        RawTaggedFieldWriter _rawWriter = RawTaggedFieldWriter.forFields(_unknownTaggedFields);
        _numTaggedFields += _rawWriter.numFields();
        if (_version >= 3) {
            _writable.writeUnsignedVarint(_numTaggedFields);
            _rawWriter.writeRawTags(_writable, Integer.MAX_VALUE);
        } else {
            if (_numTaggedFields > 0) {
                throw new UnsupportedVersionException("Tagged fields were set, but version " + _version + " of this message does not support them.");
            }
        }
    }
    
    @Override
    public void addSize(MessageSizeAccumulator _size, ObjectSerializationCache _cache, short _version) {
        int _numTaggedFields = 0;
        _size.addBytes(4);
        if (_version >= 4) {
            _size.addBytes(2);
        }
        if (_version >= 4) {
            {
                _size.addBytes(ByteUtils.sizeOfUnsignedVarint(resultsByTransaction.size() + 1));
                for (AddPartitionsToTxnResult resultsByTransactionElement : resultsByTransaction) {
                    resultsByTransactionElement.addSize(_size, _cache, _version);
                }
            }
        }
        if (_version <= 3) {
            {
                if (_version >= 3) {
                    _size.addBytes(ByteUtils.sizeOfUnsignedVarint(resultsByTopicV3AndBelow.size() + 1));
                } else {
                    _size.addBytes(4);
                }
                for (AddPartitionsToTxnTopicResult resultsByTopicV3AndBelowElement : resultsByTopicV3AndBelow) {
                    resultsByTopicV3AndBelowElement.addSize(_size, _cache, _version);
                }
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
        if (_version >= 3) {
            _size.addBytes(ByteUtils.sizeOfUnsignedVarint(_numTaggedFields));
        } else {
            if (_numTaggedFields > 0) {
                throw new UnsupportedVersionException("Tagged fields were set, but version " + _version + " of this message does not support them.");
            }
        }
    }
    
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof AddPartitionsToTxnResponseData)) return false;
        AddPartitionsToTxnResponseData other = (AddPartitionsToTxnResponseData) obj;
        if (throttleTimeMs != other.throttleTimeMs) return false;
        if (errorCode != other.errorCode) return false;
        if (this.resultsByTransaction == null) {
            if (other.resultsByTransaction != null) return false;
        } else {
            if (!this.resultsByTransaction.equals(other.resultsByTransaction)) return false;
        }
        if (this.resultsByTopicV3AndBelow == null) {
            if (other.resultsByTopicV3AndBelow != null) return false;
        } else {
            if (!this.resultsByTopicV3AndBelow.equals(other.resultsByTopicV3AndBelow)) return false;
        }
        return MessageUtil.compareRawTaggedFields(_unknownTaggedFields, other._unknownTaggedFields);
    }
    
    @Override
    public int hashCode() {
        int hashCode = 0;
        hashCode = 31 * hashCode + throttleTimeMs;
        hashCode = 31 * hashCode + errorCode;
        hashCode = 31 * hashCode + (resultsByTransaction == null ? 0 : resultsByTransaction.hashCode());
        hashCode = 31 * hashCode + (resultsByTopicV3AndBelow == null ? 0 : resultsByTopicV3AndBelow.hashCode());
        return hashCode;
    }
    
    @Override
    public AddPartitionsToTxnResponseData duplicate() {
        AddPartitionsToTxnResponseData _duplicate = new AddPartitionsToTxnResponseData();
        _duplicate.throttleTimeMs = throttleTimeMs;
        _duplicate.errorCode = errorCode;
        AddPartitionsToTxnResultCollection newResultsByTransaction = new AddPartitionsToTxnResultCollection(resultsByTransaction.size());
        for (AddPartitionsToTxnResult _element : resultsByTransaction) {
            newResultsByTransaction.add(_element.duplicate());
        }
        _duplicate.resultsByTransaction = newResultsByTransaction;
        AddPartitionsToTxnTopicResultCollection newResultsByTopicV3AndBelow = new AddPartitionsToTxnTopicResultCollection(resultsByTopicV3AndBelow.size());
        for (AddPartitionsToTxnTopicResult _element : resultsByTopicV3AndBelow) {
            newResultsByTopicV3AndBelow.add(_element.duplicate());
        }
        _duplicate.resultsByTopicV3AndBelow = newResultsByTopicV3AndBelow;
        return _duplicate;
    }
    
    @Override
    public String toString() {
        return "AddPartitionsToTxnResponseData("
            + "throttleTimeMs=" + throttleTimeMs
            + ", errorCode=" + errorCode
            + ", resultsByTransaction=" + MessageUtil.deepToString(resultsByTransaction.iterator())
            + ", resultsByTopicV3AndBelow=" + MessageUtil.deepToString(resultsByTopicV3AndBelow.iterator())
            + ")";
    }
    
    public int throttleTimeMs() {
        return this.throttleTimeMs;
    }
    
    public short errorCode() {
        return this.errorCode;
    }
    
    public AddPartitionsToTxnResultCollection resultsByTransaction() {
        return this.resultsByTransaction;
    }
    
    public AddPartitionsToTxnTopicResultCollection resultsByTopicV3AndBelow() {
        return this.resultsByTopicV3AndBelow;
    }
    
    @Override
    public List<RawTaggedField> unknownTaggedFields() {
        if (_unknownTaggedFields == null) {
            _unknownTaggedFields = new ArrayList<>(0);
        }
        return _unknownTaggedFields;
    }
    
    public AddPartitionsToTxnResponseData setThrottleTimeMs(int v) {
        this.throttleTimeMs = v;
        return this;
    }
    
    public AddPartitionsToTxnResponseData setErrorCode(short v) {
        this.errorCode = v;
        return this;
    }
    
    public AddPartitionsToTxnResponseData setResultsByTransaction(AddPartitionsToTxnResultCollection v) {
        this.resultsByTransaction = v;
        return this;
    }
    
    public AddPartitionsToTxnResponseData setResultsByTopicV3AndBelow(AddPartitionsToTxnTopicResultCollection v) {
        this.resultsByTopicV3AndBelow = v;
        return this;
    }
    
    public static class AddPartitionsToTxnResult implements Message, ImplicitLinkedHashMultiCollection.Element {
        String transactionalId;
        AddPartitionsToTxnTopicResultCollection topicResults;
        private List<RawTaggedField> _unknownTaggedFields;
        private int next;
        private int prev;
        
        public static final Schema SCHEMA_4 =
            new Schema(
                new Field("transactional_id", Type.COMPACT_STRING, "The transactional id corresponding to the transaction."),
                new Field("topic_results", new CompactArrayOf(AddPartitionsToTxnTopicResult.SCHEMA_3), "The results for each topic."),
                TaggedFieldsSection.of(
                )
            );
        
        public static final Schema SCHEMA_5 = SCHEMA_4;
        
        public static final Schema[] SCHEMAS = new Schema[] {
            null,
            null,
            null,
            null,
            SCHEMA_4,
            SCHEMA_5
        };
        
        public static final short LOWEST_SUPPORTED_VERSION = 4;
        public static final short HIGHEST_SUPPORTED_VERSION = 5;
        
        public AddPartitionsToTxnResult(Readable _readable, short _version) {
            read(_readable, _version);
            this.prev = ImplicitLinkedHashCollection.INVALID_INDEX;
            this.next = ImplicitLinkedHashCollection.INVALID_INDEX;
        }
        
        public AddPartitionsToTxnResult() {
            this.transactionalId = "";
            this.topicResults = new AddPartitionsToTxnTopicResultCollection(0);
            this.prev = ImplicitLinkedHashCollection.INVALID_INDEX;
            this.next = ImplicitLinkedHashCollection.INVALID_INDEX;
        }
        
        
        @Override
        public short lowestSupportedVersion() {
            return 0;
        }
        
        @Override
        public short highestSupportedVersion() {
            return 5;
        }
        
        @Override
        public final void read(Readable _readable, short _version) {
            if (_version > 5) {
                throw new UnsupportedVersionException("Can't read version " + _version + " of AddPartitionsToTxnResult");
            }
            {
                int length;
                length = _readable.readUnsignedVarint() - 1;
                if (length < 0) {
                    throw new RuntimeException("non-nullable field transactionalId was serialized as null");
                } else if (length > 0x7fff) {
                    throw new RuntimeException("string field transactionalId had invalid length " + length);
                } else {
                    this.transactionalId = _readable.readString(length);
                }
            }
            {
                int arrayLength;
                arrayLength = _readable.readUnsignedVarint() - 1;
                if (arrayLength < 0) {
                    throw new RuntimeException("non-nullable field topicResults was serialized as null");
                } else {
                    if (arrayLength > _readable.remaining()) {
                        throw new RuntimeException("Tried to allocate a collection of size " + arrayLength + ", but there are only " + _readable.remaining() + " bytes remaining.");
                    }
                    AddPartitionsToTxnTopicResultCollection newCollection = new AddPartitionsToTxnTopicResultCollection(arrayLength);
                    for (int i = 0; i < arrayLength; i++) {
                        newCollection.add(new AddPartitionsToTxnTopicResult(_readable, _version));
                    }
                    this.topicResults = newCollection;
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
            if (_version < 4) {
                throw new UnsupportedVersionException("Can't write version " + _version + " of AddPartitionsToTxnResult");
            }
            int _numTaggedFields = 0;
            {
                byte[] _stringBytes = _cache.getSerializedValue(transactionalId);
                _writable.writeUnsignedVarint(_stringBytes.length + 1);
                _writable.writeByteArray(_stringBytes);
            }
            _writable.writeUnsignedVarint(topicResults.size() + 1);
            for (AddPartitionsToTxnTopicResult topicResultsElement : topicResults) {
                topicResultsElement.write(_writable, _cache, _version);
            }
            RawTaggedFieldWriter _rawWriter = RawTaggedFieldWriter.forFields(_unknownTaggedFields);
            _numTaggedFields += _rawWriter.numFields();
            _writable.writeUnsignedVarint(_numTaggedFields);
            _rawWriter.writeRawTags(_writable, Integer.MAX_VALUE);
        }
        
        @Override
        public void addSize(MessageSizeAccumulator _size, ObjectSerializationCache _cache, short _version) {
            int _numTaggedFields = 0;
            if (_version > 5) {
                throw new UnsupportedVersionException("Can't size version " + _version + " of AddPartitionsToTxnResult");
            }
            {
                byte[] _stringBytes = transactionalId.getBytes(StandardCharsets.UTF_8);
                if (_stringBytes.length > 0x7fff) {
                    throw new RuntimeException("'transactionalId' field is too long to be serialized");
                }
                _cache.cacheSerializedValue(transactionalId, _stringBytes);
                _size.addBytes(_stringBytes.length + ByteUtils.sizeOfUnsignedVarint(_stringBytes.length + 1));
            }
            {
                _size.addBytes(ByteUtils.sizeOfUnsignedVarint(topicResults.size() + 1));
                for (AddPartitionsToTxnTopicResult topicResultsElement : topicResults) {
                    topicResultsElement.addSize(_size, _cache, _version);
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
        public boolean elementKeysAreEqual(Object obj) {
            if (!(obj instanceof AddPartitionsToTxnResult)) return false;
            AddPartitionsToTxnResult other = (AddPartitionsToTxnResult) obj;
            if (this.transactionalId == null) {
                if (other.transactionalId != null) return false;
            } else {
                if (!this.transactionalId.equals(other.transactionalId)) return false;
            }
            return true;
        }
        
        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof AddPartitionsToTxnResult)) return false;
            AddPartitionsToTxnResult other = (AddPartitionsToTxnResult) obj;
            if (this.transactionalId == null) {
                if (other.transactionalId != null) return false;
            } else {
                if (!this.transactionalId.equals(other.transactionalId)) return false;
            }
            if (this.topicResults == null) {
                if (other.topicResults != null) return false;
            } else {
                if (!this.topicResults.equals(other.topicResults)) return false;
            }
            return MessageUtil.compareRawTaggedFields(_unknownTaggedFields, other._unknownTaggedFields);
        }
        
        @Override
        public int hashCode() {
            int hashCode = 0;
            hashCode = 31 * hashCode + (transactionalId == null ? 0 : transactionalId.hashCode());
            return hashCode;
        }
        
        @Override
        public AddPartitionsToTxnResult duplicate() {
            AddPartitionsToTxnResult _duplicate = new AddPartitionsToTxnResult();
            _duplicate.transactionalId = transactionalId;
            AddPartitionsToTxnTopicResultCollection newTopicResults = new AddPartitionsToTxnTopicResultCollection(topicResults.size());
            for (AddPartitionsToTxnTopicResult _element : topicResults) {
                newTopicResults.add(_element.duplicate());
            }
            _duplicate.topicResults = newTopicResults;
            return _duplicate;
        }
        
        @Override
        public String toString() {
            return "AddPartitionsToTxnResult("
                + "transactionalId=" + ((transactionalId == null) ? "null" : "'" + transactionalId.toString() + "'")
                + ", topicResults=" + MessageUtil.deepToString(topicResults.iterator())
                + ")";
        }
        
        public String transactionalId() {
            return this.transactionalId;
        }
        
        public AddPartitionsToTxnTopicResultCollection topicResults() {
            return this.topicResults;
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
        
        public AddPartitionsToTxnResult setTransactionalId(String v) {
            this.transactionalId = v;
            return this;
        }
        
        public AddPartitionsToTxnResult setTopicResults(AddPartitionsToTxnTopicResultCollection v) {
            this.topicResults = v;
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
    
    public static class AddPartitionsToTxnResultCollection extends ImplicitLinkedHashMultiCollection<AddPartitionsToTxnResult> {
        public AddPartitionsToTxnResultCollection() {
            super();
        }
        
        public AddPartitionsToTxnResultCollection(int expectedNumElements) {
            super(expectedNumElements);
        }
        
        public AddPartitionsToTxnResultCollection(Iterator<AddPartitionsToTxnResult> iterator) {
            super(iterator);
        }
        
        public AddPartitionsToTxnResult find(String transactionalId) {
            AddPartitionsToTxnResult _key = new AddPartitionsToTxnResult();
            _key.setTransactionalId(transactionalId);
            return find(_key);
        }
        
        public List<AddPartitionsToTxnResult> findAll(String transactionalId) {
            AddPartitionsToTxnResult _key = new AddPartitionsToTxnResult();
            _key.setTransactionalId(transactionalId);
            return findAll(_key);
        }
        
        public AddPartitionsToTxnResultCollection duplicate() {
            AddPartitionsToTxnResultCollection _duplicate = new AddPartitionsToTxnResultCollection(size());
            for (AddPartitionsToTxnResult _element : this) {
                _duplicate.add(_element.duplicate());
            }
            return _duplicate;
        }
    }
    
    public static class AddPartitionsToTxnPartitionResult implements Message, ImplicitLinkedHashMultiCollection.Element {
        int partitionIndex;
        short partitionErrorCode;
        private List<RawTaggedField> _unknownTaggedFields;
        private int next;
        private int prev;
        
        public static final Schema SCHEMA_0 =
            new Schema(
                new Field("partition_index", Type.INT32, "The partition indexes."),
                new Field("partition_error_code", Type.INT16, "The response error code.")
            );
        
        public static final Schema SCHEMA_1 = SCHEMA_0;
        
        public static final Schema SCHEMA_2 = SCHEMA_1;
        
        public static final Schema SCHEMA_3 =
            new Schema(
                new Field("partition_index", Type.INT32, "The partition indexes."),
                new Field("partition_error_code", Type.INT16, "The response error code."),
                TaggedFieldsSection.of(
                )
            );
        
        public static final Schema SCHEMA_4 = SCHEMA_3;
        
        public static final Schema SCHEMA_5 = SCHEMA_4;
        
        public static final Schema[] SCHEMAS = new Schema[] {
            SCHEMA_0,
            SCHEMA_1,
            SCHEMA_2,
            SCHEMA_3,
            SCHEMA_4,
            SCHEMA_5
        };
        
        public static final short LOWEST_SUPPORTED_VERSION = 0;
        public static final short HIGHEST_SUPPORTED_VERSION = 5;
        
        public AddPartitionsToTxnPartitionResult(Readable _readable, short _version) {
            read(_readable, _version);
            this.prev = ImplicitLinkedHashCollection.INVALID_INDEX;
            this.next = ImplicitLinkedHashCollection.INVALID_INDEX;
        }
        
        public AddPartitionsToTxnPartitionResult() {
            this.partitionIndex = 0;
            this.partitionErrorCode = (short) 0;
            this.prev = ImplicitLinkedHashCollection.INVALID_INDEX;
            this.next = ImplicitLinkedHashCollection.INVALID_INDEX;
        }
        
        
        @Override
        public short lowestSupportedVersion() {
            return 0;
        }
        
        @Override
        public short highestSupportedVersion() {
            return 32767;
        }
        
        @Override
        public final void read(Readable _readable, short _version) {
            this.partitionIndex = _readable.readInt();
            this.partitionErrorCode = _readable.readShort();
            this._unknownTaggedFields = null;
            if (_version >= 3) {
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
        }
        
        @Override
        public void write(Writable _writable, ObjectSerializationCache _cache, short _version) {
            int _numTaggedFields = 0;
            _writable.writeInt(partitionIndex);
            _writable.writeShort(partitionErrorCode);
            RawTaggedFieldWriter _rawWriter = RawTaggedFieldWriter.forFields(_unknownTaggedFields);
            _numTaggedFields += _rawWriter.numFields();
            if (_version >= 3) {
                _writable.writeUnsignedVarint(_numTaggedFields);
                _rawWriter.writeRawTags(_writable, Integer.MAX_VALUE);
            } else {
                if (_numTaggedFields > 0) {
                    throw new UnsupportedVersionException("Tagged fields were set, but version " + _version + " of this message does not support them.");
                }
            }
        }
        
        @Override
        public void addSize(MessageSizeAccumulator _size, ObjectSerializationCache _cache, short _version) {
            int _numTaggedFields = 0;
            _size.addBytes(4);
            _size.addBytes(2);
            if (_unknownTaggedFields != null) {
                _numTaggedFields += _unknownTaggedFields.size();
                for (RawTaggedField _field : _unknownTaggedFields) {
                    _size.addBytes(ByteUtils.sizeOfUnsignedVarint(_field.tag()));
                    _size.addBytes(ByteUtils.sizeOfUnsignedVarint(_field.size()));
                    _size.addBytes(_field.size());
                }
            }
            if (_version >= 3) {
                _size.addBytes(ByteUtils.sizeOfUnsignedVarint(_numTaggedFields));
            } else {
                if (_numTaggedFields > 0) {
                    throw new UnsupportedVersionException("Tagged fields were set, but version " + _version + " of this message does not support them.");
                }
            }
        }
        
        @Override
        public boolean elementKeysAreEqual(Object obj) {
            if (!(obj instanceof AddPartitionsToTxnPartitionResult)) return false;
            AddPartitionsToTxnPartitionResult other = (AddPartitionsToTxnPartitionResult) obj;
            if (partitionIndex != other.partitionIndex) return false;
            return true;
        }
        
        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof AddPartitionsToTxnPartitionResult)) return false;
            AddPartitionsToTxnPartitionResult other = (AddPartitionsToTxnPartitionResult) obj;
            if (partitionIndex != other.partitionIndex) return false;
            if (partitionErrorCode != other.partitionErrorCode) return false;
            return MessageUtil.compareRawTaggedFields(_unknownTaggedFields, other._unknownTaggedFields);
        }
        
        @Override
        public int hashCode() {
            int hashCode = 0;
            hashCode = 31 * hashCode + partitionIndex;
            return hashCode;
        }
        
        @Override
        public AddPartitionsToTxnPartitionResult duplicate() {
            AddPartitionsToTxnPartitionResult _duplicate = new AddPartitionsToTxnPartitionResult();
            _duplicate.partitionIndex = partitionIndex;
            _duplicate.partitionErrorCode = partitionErrorCode;
            return _duplicate;
        }
        
        @Override
        public String toString() {
            return "AddPartitionsToTxnPartitionResult("
                + "partitionIndex=" + partitionIndex
                + ", partitionErrorCode=" + partitionErrorCode
                + ")";
        }
        
        public int partitionIndex() {
            return this.partitionIndex;
        }
        
        public short partitionErrorCode() {
            return this.partitionErrorCode;
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
        
        public AddPartitionsToTxnPartitionResult setPartitionIndex(int v) {
            this.partitionIndex = v;
            return this;
        }
        
        public AddPartitionsToTxnPartitionResult setPartitionErrorCode(short v) {
            this.partitionErrorCode = v;
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
    
    public static class AddPartitionsToTxnPartitionResultCollection extends ImplicitLinkedHashMultiCollection<AddPartitionsToTxnPartitionResult> {
        public AddPartitionsToTxnPartitionResultCollection() {
            super();
        }
        
        public AddPartitionsToTxnPartitionResultCollection(int expectedNumElements) {
            super(expectedNumElements);
        }
        
        public AddPartitionsToTxnPartitionResultCollection(Iterator<AddPartitionsToTxnPartitionResult> iterator) {
            super(iterator);
        }
        
        public AddPartitionsToTxnPartitionResult find(int partitionIndex) {
            AddPartitionsToTxnPartitionResult _key = new AddPartitionsToTxnPartitionResult();
            _key.setPartitionIndex(partitionIndex);
            return find(_key);
        }
        
        public List<AddPartitionsToTxnPartitionResult> findAll(int partitionIndex) {
            AddPartitionsToTxnPartitionResult _key = new AddPartitionsToTxnPartitionResult();
            _key.setPartitionIndex(partitionIndex);
            return findAll(_key);
        }
        
        public AddPartitionsToTxnPartitionResultCollection duplicate() {
            AddPartitionsToTxnPartitionResultCollection _duplicate = new AddPartitionsToTxnPartitionResultCollection(size());
            for (AddPartitionsToTxnPartitionResult _element : this) {
                _duplicate.add(_element.duplicate());
            }
            return _duplicate;
        }
    }
    
    public static class AddPartitionsToTxnTopicResult implements Message, ImplicitLinkedHashMultiCollection.Element {
        String name;
        AddPartitionsToTxnPartitionResultCollection resultsByPartition;
        private List<RawTaggedField> _unknownTaggedFields;
        private int next;
        private int prev;
        
        public static final Schema SCHEMA_0 =
            new Schema(
                new Field("name", Type.STRING, "The topic name."),
                new Field("results_by_partition", new ArrayOf(AddPartitionsToTxnPartitionResult.SCHEMA_0), "The results for each partition.")
            );
        
        public static final Schema SCHEMA_1 = SCHEMA_0;
        
        public static final Schema SCHEMA_2 = SCHEMA_1;
        
        public static final Schema SCHEMA_3 =
            new Schema(
                new Field("name", Type.COMPACT_STRING, "The topic name."),
                new Field("results_by_partition", new CompactArrayOf(AddPartitionsToTxnPartitionResult.SCHEMA_3), "The results for each partition."),
                TaggedFieldsSection.of(
                )
            );
        
        public static final Schema SCHEMA_4 = SCHEMA_3;
        
        public static final Schema SCHEMA_5 = SCHEMA_4;
        
        public static final Schema[] SCHEMAS = new Schema[] {
            SCHEMA_0,
            SCHEMA_1,
            SCHEMA_2,
            SCHEMA_3,
            SCHEMA_4,
            SCHEMA_5
        };
        
        public static final short LOWEST_SUPPORTED_VERSION = 0;
        public static final short HIGHEST_SUPPORTED_VERSION = 5;
        
        public AddPartitionsToTxnTopicResult(Readable _readable, short _version) {
            read(_readable, _version);
            this.prev = ImplicitLinkedHashCollection.INVALID_INDEX;
            this.next = ImplicitLinkedHashCollection.INVALID_INDEX;
        }
        
        public AddPartitionsToTxnTopicResult() {
            this.name = "";
            this.resultsByPartition = new AddPartitionsToTxnPartitionResultCollection(0);
            this.prev = ImplicitLinkedHashCollection.INVALID_INDEX;
            this.next = ImplicitLinkedHashCollection.INVALID_INDEX;
        }
        
        
        @Override
        public short lowestSupportedVersion() {
            return 0;
        }
        
        @Override
        public short highestSupportedVersion() {
            return 32767;
        }
        
        @Override
        public final void read(Readable _readable, short _version) {
            {
                int length;
                if (_version >= 3) {
                    length = _readable.readUnsignedVarint() - 1;
                } else {
                    length = _readable.readShort();
                }
                if (length < 0) {
                    throw new RuntimeException("non-nullable field name was serialized as null");
                } else if (length > 0x7fff) {
                    throw new RuntimeException("string field name had invalid length " + length);
                } else {
                    this.name = _readable.readString(length);
                }
            }
            {
                if (_version >= 3) {
                    int arrayLength;
                    arrayLength = _readable.readUnsignedVarint() - 1;
                    if (arrayLength < 0) {
                        throw new RuntimeException("non-nullable field resultsByPartition was serialized as null");
                    } else {
                        if (arrayLength > _readable.remaining()) {
                            throw new RuntimeException("Tried to allocate a collection of size " + arrayLength + ", but there are only " + _readable.remaining() + " bytes remaining.");
                        }
                        AddPartitionsToTxnPartitionResultCollection newCollection = new AddPartitionsToTxnPartitionResultCollection(arrayLength);
                        for (int i = 0; i < arrayLength; i++) {
                            newCollection.add(new AddPartitionsToTxnPartitionResult(_readable, _version));
                        }
                        this.resultsByPartition = newCollection;
                    }
                } else {
                    int arrayLength;
                    arrayLength = _readable.readInt();
                    if (arrayLength < 0) {
                        throw new RuntimeException("non-nullable field resultsByPartition was serialized as null");
                    } else {
                        if (arrayLength > _readable.remaining()) {
                            throw new RuntimeException("Tried to allocate a collection of size " + arrayLength + ", but there are only " + _readable.remaining() + " bytes remaining.");
                        }
                        AddPartitionsToTxnPartitionResultCollection newCollection = new AddPartitionsToTxnPartitionResultCollection(arrayLength);
                        for (int i = 0; i < arrayLength; i++) {
                            newCollection.add(new AddPartitionsToTxnPartitionResult(_readable, _version));
                        }
                        this.resultsByPartition = newCollection;
                    }
                }
            }
            this._unknownTaggedFields = null;
            if (_version >= 3) {
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
        }
        
        @Override
        public void write(Writable _writable, ObjectSerializationCache _cache, short _version) {
            int _numTaggedFields = 0;
            {
                byte[] _stringBytes = _cache.getSerializedValue(name);
                if (_version >= 3) {
                    _writable.writeUnsignedVarint(_stringBytes.length + 1);
                } else {
                    _writable.writeShort((short) _stringBytes.length);
                }
                _writable.writeByteArray(_stringBytes);
            }
            if (_version >= 3) {
                _writable.writeUnsignedVarint(resultsByPartition.size() + 1);
                for (AddPartitionsToTxnPartitionResult resultsByPartitionElement : resultsByPartition) {
                    resultsByPartitionElement.write(_writable, _cache, _version);
                }
            } else {
                _writable.writeInt(resultsByPartition.size());
                for (AddPartitionsToTxnPartitionResult resultsByPartitionElement : resultsByPartition) {
                    resultsByPartitionElement.write(_writable, _cache, _version);
                }
            }
            RawTaggedFieldWriter _rawWriter = RawTaggedFieldWriter.forFields(_unknownTaggedFields);
            _numTaggedFields += _rawWriter.numFields();
            if (_version >= 3) {
                _writable.writeUnsignedVarint(_numTaggedFields);
                _rawWriter.writeRawTags(_writable, Integer.MAX_VALUE);
            } else {
                if (_numTaggedFields > 0) {
                    throw new UnsupportedVersionException("Tagged fields were set, but version " + _version + " of this message does not support them.");
                }
            }
        }
        
        @Override
        public void addSize(MessageSizeAccumulator _size, ObjectSerializationCache _cache, short _version) {
            int _numTaggedFields = 0;
            {
                byte[] _stringBytes = name.getBytes(StandardCharsets.UTF_8);
                if (_stringBytes.length > 0x7fff) {
                    throw new RuntimeException("'name' field is too long to be serialized");
                }
                _cache.cacheSerializedValue(name, _stringBytes);
                if (_version >= 3) {
                    _size.addBytes(_stringBytes.length + ByteUtils.sizeOfUnsignedVarint(_stringBytes.length + 1));
                } else {
                    _size.addBytes(_stringBytes.length + 2);
                }
            }
            {
                if (_version >= 3) {
                    _size.addBytes(ByteUtils.sizeOfUnsignedVarint(resultsByPartition.size() + 1));
                } else {
                    _size.addBytes(4);
                }
                for (AddPartitionsToTxnPartitionResult resultsByPartitionElement : resultsByPartition) {
                    resultsByPartitionElement.addSize(_size, _cache, _version);
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
            if (_version >= 3) {
                _size.addBytes(ByteUtils.sizeOfUnsignedVarint(_numTaggedFields));
            } else {
                if (_numTaggedFields > 0) {
                    throw new UnsupportedVersionException("Tagged fields were set, but version " + _version + " of this message does not support them.");
                }
            }
        }
        
        @Override
        public boolean elementKeysAreEqual(Object obj) {
            if (!(obj instanceof AddPartitionsToTxnTopicResult)) return false;
            AddPartitionsToTxnTopicResult other = (AddPartitionsToTxnTopicResult) obj;
            if (this.name == null) {
                if (other.name != null) return false;
            } else {
                if (!this.name.equals(other.name)) return false;
            }
            return true;
        }
        
        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof AddPartitionsToTxnTopicResult)) return false;
            AddPartitionsToTxnTopicResult other = (AddPartitionsToTxnTopicResult) obj;
            if (this.name == null) {
                if (other.name != null) return false;
            } else {
                if (!this.name.equals(other.name)) return false;
            }
            if (this.resultsByPartition == null) {
                if (other.resultsByPartition != null) return false;
            } else {
                if (!this.resultsByPartition.equals(other.resultsByPartition)) return false;
            }
            return MessageUtil.compareRawTaggedFields(_unknownTaggedFields, other._unknownTaggedFields);
        }
        
        @Override
        public int hashCode() {
            int hashCode = 0;
            hashCode = 31 * hashCode + (name == null ? 0 : name.hashCode());
            return hashCode;
        }
        
        @Override
        public AddPartitionsToTxnTopicResult duplicate() {
            AddPartitionsToTxnTopicResult _duplicate = new AddPartitionsToTxnTopicResult();
            _duplicate.name = name;
            AddPartitionsToTxnPartitionResultCollection newResultsByPartition = new AddPartitionsToTxnPartitionResultCollection(resultsByPartition.size());
            for (AddPartitionsToTxnPartitionResult _element : resultsByPartition) {
                newResultsByPartition.add(_element.duplicate());
            }
            _duplicate.resultsByPartition = newResultsByPartition;
            return _duplicate;
        }
        
        @Override
        public String toString() {
            return "AddPartitionsToTxnTopicResult("
                + "name=" + ((name == null) ? "null" : "'" + name.toString() + "'")
                + ", resultsByPartition=" + MessageUtil.deepToString(resultsByPartition.iterator())
                + ")";
        }
        
        public String name() {
            return this.name;
        }
        
        public AddPartitionsToTxnPartitionResultCollection resultsByPartition() {
            return this.resultsByPartition;
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
        
        public AddPartitionsToTxnTopicResult setName(String v) {
            this.name = v;
            return this;
        }
        
        public AddPartitionsToTxnTopicResult setResultsByPartition(AddPartitionsToTxnPartitionResultCollection v) {
            this.resultsByPartition = v;
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
    
    public static class AddPartitionsToTxnTopicResultCollection extends ImplicitLinkedHashMultiCollection<AddPartitionsToTxnTopicResult> {
        public AddPartitionsToTxnTopicResultCollection() {
            super();
        }
        
        public AddPartitionsToTxnTopicResultCollection(int expectedNumElements) {
            super(expectedNumElements);
        }
        
        public AddPartitionsToTxnTopicResultCollection(Iterator<AddPartitionsToTxnTopicResult> iterator) {
            super(iterator);
        }
        
        public AddPartitionsToTxnTopicResult find(String name) {
            AddPartitionsToTxnTopicResult _key = new AddPartitionsToTxnTopicResult();
            _key.setName(name);
            return find(_key);
        }
        
        public List<AddPartitionsToTxnTopicResult> findAll(String name) {
            AddPartitionsToTxnTopicResult _key = new AddPartitionsToTxnTopicResult();
            _key.setName(name);
            return findAll(_key);
        }
        
        public AddPartitionsToTxnTopicResultCollection duplicate() {
            AddPartitionsToTxnTopicResultCollection _duplicate = new AddPartitionsToTxnTopicResultCollection(size());
            for (AddPartitionsToTxnTopicResult _element : this) {
                _duplicate.add(_element.duplicate());
            }
            return _duplicate;
        }
    }
}
