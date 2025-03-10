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


public class AddPartitionsToTxnRequestData implements ApiMessage {
    AddPartitionsToTxnTransactionCollection transactions;
    String v3AndBelowTransactionalId;
    long v3AndBelowProducerId;
    short v3AndBelowProducerEpoch;
    AddPartitionsToTxnTopicCollection v3AndBelowTopics;
    private List<RawTaggedField> _unknownTaggedFields;
    
    public static final Schema SCHEMA_0 =
        new Schema(
            new Field("v3_and_below_transactional_id", Type.STRING, "The transactional id corresponding to the transaction."),
            new Field("v3_and_below_producer_id", Type.INT64, "Current producer id in use by the transactional id."),
            new Field("v3_and_below_producer_epoch", Type.INT16, "Current epoch associated with the producer id."),
            new Field("v3_and_below_topics", new ArrayOf(AddPartitionsToTxnTopic.SCHEMA_0), "The partitions to add to the transaction.")
        );
    
    public static final Schema SCHEMA_1 = SCHEMA_0;
    
    public static final Schema SCHEMA_2 = SCHEMA_1;
    
    public static final Schema SCHEMA_3 =
        new Schema(
            new Field("v3_and_below_transactional_id", Type.COMPACT_STRING, "The transactional id corresponding to the transaction."),
            new Field("v3_and_below_producer_id", Type.INT64, "Current producer id in use by the transactional id."),
            new Field("v3_and_below_producer_epoch", Type.INT16, "Current epoch associated with the producer id."),
            new Field("v3_and_below_topics", new CompactArrayOf(AddPartitionsToTxnTopic.SCHEMA_3), "The partitions to add to the transaction."),
            TaggedFieldsSection.of(
            )
        );
    
    public static final Schema SCHEMA_4 =
        new Schema(
            new Field("transactions", new CompactArrayOf(AddPartitionsToTxnTransaction.SCHEMA_4), "List of transactions to add partitions to."),
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
    
    public AddPartitionsToTxnRequestData(Readable _readable, short _version) {
        read(_readable, _version);
    }
    
    public AddPartitionsToTxnRequestData() {
        this.transactions = new AddPartitionsToTxnTransactionCollection(0);
        this.v3AndBelowTransactionalId = "";
        this.v3AndBelowProducerId = 0L;
        this.v3AndBelowProducerEpoch = (short) 0;
        this.v3AndBelowTopics = new AddPartitionsToTxnTopicCollection(0);
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
        if (_version >= 4) {
            int arrayLength;
            arrayLength = _readable.readUnsignedVarint() - 1;
            if (arrayLength < 0) {
                throw new RuntimeException("non-nullable field transactions was serialized as null");
            } else {
                if (arrayLength > _readable.remaining()) {
                    throw new RuntimeException("Tried to allocate a collection of size " + arrayLength + ", but there are only " + _readable.remaining() + " bytes remaining.");
                }
                AddPartitionsToTxnTransactionCollection newCollection = new AddPartitionsToTxnTransactionCollection(arrayLength);
                for (int i = 0; i < arrayLength; i++) {
                    newCollection.add(new AddPartitionsToTxnTransaction(_readable, _version));
                }
                this.transactions = newCollection;
            }
        } else {
            this.transactions = new AddPartitionsToTxnTransactionCollection(0);
        }
        if (_version <= 3) {
            int length;
            if (_version >= 3) {
                length = _readable.readUnsignedVarint() - 1;
            } else {
                length = _readable.readShort();
            }
            if (length < 0) {
                throw new RuntimeException("non-nullable field v3AndBelowTransactionalId was serialized as null");
            } else if (length > 0x7fff) {
                throw new RuntimeException("string field v3AndBelowTransactionalId had invalid length " + length);
            } else {
                this.v3AndBelowTransactionalId = _readable.readString(length);
            }
        } else {
            this.v3AndBelowTransactionalId = "";
        }
        if (_version <= 3) {
            this.v3AndBelowProducerId = _readable.readLong();
        } else {
            this.v3AndBelowProducerId = 0L;
        }
        if (_version <= 3) {
            this.v3AndBelowProducerEpoch = _readable.readShort();
        } else {
            this.v3AndBelowProducerEpoch = (short) 0;
        }
        if (_version <= 3) {
            if (_version >= 3) {
                int arrayLength;
                arrayLength = _readable.readUnsignedVarint() - 1;
                if (arrayLength < 0) {
                    throw new RuntimeException("non-nullable field v3AndBelowTopics was serialized as null");
                } else {
                    if (arrayLength > _readable.remaining()) {
                        throw new RuntimeException("Tried to allocate a collection of size " + arrayLength + ", but there are only " + _readable.remaining() + " bytes remaining.");
                    }
                    AddPartitionsToTxnTopicCollection newCollection = new AddPartitionsToTxnTopicCollection(arrayLength);
                    for (int i = 0; i < arrayLength; i++) {
                        newCollection.add(new AddPartitionsToTxnTopic(_readable, _version));
                    }
                    this.v3AndBelowTopics = newCollection;
                }
            } else {
                int arrayLength;
                arrayLength = _readable.readInt();
                if (arrayLength < 0) {
                    throw new RuntimeException("non-nullable field v3AndBelowTopics was serialized as null");
                } else {
                    if (arrayLength > _readable.remaining()) {
                        throw new RuntimeException("Tried to allocate a collection of size " + arrayLength + ", but there are only " + _readable.remaining() + " bytes remaining.");
                    }
                    AddPartitionsToTxnTopicCollection newCollection = new AddPartitionsToTxnTopicCollection(arrayLength);
                    for (int i = 0; i < arrayLength; i++) {
                        newCollection.add(new AddPartitionsToTxnTopic(_readable, _version));
                    }
                    this.v3AndBelowTopics = newCollection;
                }
            }
        } else {
            this.v3AndBelowTopics = new AddPartitionsToTxnTopicCollection(0);
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
        if (_version >= 4) {
            _writable.writeUnsignedVarint(transactions.size() + 1);
            for (AddPartitionsToTxnTransaction transactionsElement : transactions) {
                transactionsElement.write(_writable, _cache, _version);
            }
        } else {
            if (!this.transactions.isEmpty()) {
                throw new UnsupportedVersionException("Attempted to write a non-default transactions at version " + _version);
            }
        }
        if (_version <= 3) {
            {
                byte[] _stringBytes = _cache.getSerializedValue(v3AndBelowTransactionalId);
                if (_version >= 3) {
                    _writable.writeUnsignedVarint(_stringBytes.length + 1);
                } else {
                    _writable.writeShort((short) _stringBytes.length);
                }
                _writable.writeByteArray(_stringBytes);
            }
        } else {
            if (!this.v3AndBelowTransactionalId.equals("")) {
                throw new UnsupportedVersionException("Attempted to write a non-default v3AndBelowTransactionalId at version " + _version);
            }
        }
        if (_version <= 3) {
            _writable.writeLong(v3AndBelowProducerId);
        } else {
            if (this.v3AndBelowProducerId != 0L) {
                throw new UnsupportedVersionException("Attempted to write a non-default v3AndBelowProducerId at version " + _version);
            }
        }
        if (_version <= 3) {
            _writable.writeShort(v3AndBelowProducerEpoch);
        } else {
            if (this.v3AndBelowProducerEpoch != (short) 0) {
                throw new UnsupportedVersionException("Attempted to write a non-default v3AndBelowProducerEpoch at version " + _version);
            }
        }
        if (_version <= 3) {
            if (_version >= 3) {
                _writable.writeUnsignedVarint(v3AndBelowTopics.size() + 1);
                for (AddPartitionsToTxnTopic v3AndBelowTopicsElement : v3AndBelowTopics) {
                    v3AndBelowTopicsElement.write(_writable, _cache, _version);
                }
            } else {
                _writable.writeInt(v3AndBelowTopics.size());
                for (AddPartitionsToTxnTopic v3AndBelowTopicsElement : v3AndBelowTopics) {
                    v3AndBelowTopicsElement.write(_writable, _cache, _version);
                }
            }
        } else {
            if (!this.v3AndBelowTopics.isEmpty()) {
                throw new UnsupportedVersionException("Attempted to write a non-default v3AndBelowTopics at version " + _version);
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
        if (_version >= 4) {
            {
                _size.addBytes(ByteUtils.sizeOfUnsignedVarint(transactions.size() + 1));
                for (AddPartitionsToTxnTransaction transactionsElement : transactions) {
                    transactionsElement.addSize(_size, _cache, _version);
                }
            }
        }
        if (_version <= 3) {
            {
                byte[] _stringBytes = v3AndBelowTransactionalId.getBytes(StandardCharsets.UTF_8);
                if (_stringBytes.length > 0x7fff) {
                    throw new RuntimeException("'v3AndBelowTransactionalId' field is too long to be serialized");
                }
                _cache.cacheSerializedValue(v3AndBelowTransactionalId, _stringBytes);
                if (_version >= 3) {
                    _size.addBytes(_stringBytes.length + ByteUtils.sizeOfUnsignedVarint(_stringBytes.length + 1));
                } else {
                    _size.addBytes(_stringBytes.length + 2);
                }
            }
        }
        if (_version <= 3) {
            _size.addBytes(8);
        }
        if (_version <= 3) {
            _size.addBytes(2);
        }
        if (_version <= 3) {
            {
                if (_version >= 3) {
                    _size.addBytes(ByteUtils.sizeOfUnsignedVarint(v3AndBelowTopics.size() + 1));
                } else {
                    _size.addBytes(4);
                }
                for (AddPartitionsToTxnTopic v3AndBelowTopicsElement : v3AndBelowTopics) {
                    v3AndBelowTopicsElement.addSize(_size, _cache, _version);
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
        if (!(obj instanceof AddPartitionsToTxnRequestData)) return false;
        AddPartitionsToTxnRequestData other = (AddPartitionsToTxnRequestData) obj;
        if (this.transactions == null) {
            if (other.transactions != null) return false;
        } else {
            if (!this.transactions.equals(other.transactions)) return false;
        }
        if (this.v3AndBelowTransactionalId == null) {
            if (other.v3AndBelowTransactionalId != null) return false;
        } else {
            if (!this.v3AndBelowTransactionalId.equals(other.v3AndBelowTransactionalId)) return false;
        }
        if (v3AndBelowProducerId != other.v3AndBelowProducerId) return false;
        if (v3AndBelowProducerEpoch != other.v3AndBelowProducerEpoch) return false;
        if (this.v3AndBelowTopics == null) {
            if (other.v3AndBelowTopics != null) return false;
        } else {
            if (!this.v3AndBelowTopics.equals(other.v3AndBelowTopics)) return false;
        }
        return MessageUtil.compareRawTaggedFields(_unknownTaggedFields, other._unknownTaggedFields);
    }
    
    @Override
    public int hashCode() {
        int hashCode = 0;
        hashCode = 31 * hashCode + (transactions == null ? 0 : transactions.hashCode());
        hashCode = 31 * hashCode + (v3AndBelowTransactionalId == null ? 0 : v3AndBelowTransactionalId.hashCode());
        hashCode = 31 * hashCode + ((int) (v3AndBelowProducerId >> 32) ^ (int) v3AndBelowProducerId);
        hashCode = 31 * hashCode + v3AndBelowProducerEpoch;
        hashCode = 31 * hashCode + (v3AndBelowTopics == null ? 0 : v3AndBelowTopics.hashCode());
        return hashCode;
    }
    
    @Override
    public AddPartitionsToTxnRequestData duplicate() {
        AddPartitionsToTxnRequestData _duplicate = new AddPartitionsToTxnRequestData();
        AddPartitionsToTxnTransactionCollection newTransactions = new AddPartitionsToTxnTransactionCollection(transactions.size());
        for (AddPartitionsToTxnTransaction _element : transactions) {
            newTransactions.add(_element.duplicate());
        }
        _duplicate.transactions = newTransactions;
        _duplicate.v3AndBelowTransactionalId = v3AndBelowTransactionalId;
        _duplicate.v3AndBelowProducerId = v3AndBelowProducerId;
        _duplicate.v3AndBelowProducerEpoch = v3AndBelowProducerEpoch;
        AddPartitionsToTxnTopicCollection newV3AndBelowTopics = new AddPartitionsToTxnTopicCollection(v3AndBelowTopics.size());
        for (AddPartitionsToTxnTopic _element : v3AndBelowTopics) {
            newV3AndBelowTopics.add(_element.duplicate());
        }
        _duplicate.v3AndBelowTopics = newV3AndBelowTopics;
        return _duplicate;
    }
    
    @Override
    public String toString() {
        return "AddPartitionsToTxnRequestData("
            + "transactions=" + MessageUtil.deepToString(transactions.iterator())
            + ", v3AndBelowTransactionalId=" + ((v3AndBelowTransactionalId == null) ? "null" : "'" + v3AndBelowTransactionalId.toString() + "'")
            + ", v3AndBelowProducerId=" + v3AndBelowProducerId
            + ", v3AndBelowProducerEpoch=" + v3AndBelowProducerEpoch
            + ", v3AndBelowTopics=" + MessageUtil.deepToString(v3AndBelowTopics.iterator())
            + ")";
    }
    
    public AddPartitionsToTxnTransactionCollection transactions() {
        return this.transactions;
    }
    
    public String v3AndBelowTransactionalId() {
        return this.v3AndBelowTransactionalId;
    }
    
    public long v3AndBelowProducerId() {
        return this.v3AndBelowProducerId;
    }
    
    public short v3AndBelowProducerEpoch() {
        return this.v3AndBelowProducerEpoch;
    }
    
    public AddPartitionsToTxnTopicCollection v3AndBelowTopics() {
        return this.v3AndBelowTopics;
    }
    
    @Override
    public List<RawTaggedField> unknownTaggedFields() {
        if (_unknownTaggedFields == null) {
            _unknownTaggedFields = new ArrayList<>(0);
        }
        return _unknownTaggedFields;
    }
    
    public AddPartitionsToTxnRequestData setTransactions(AddPartitionsToTxnTransactionCollection v) {
        this.transactions = v;
        return this;
    }
    
    public AddPartitionsToTxnRequestData setV3AndBelowTransactionalId(String v) {
        this.v3AndBelowTransactionalId = v;
        return this;
    }
    
    public AddPartitionsToTxnRequestData setV3AndBelowProducerId(long v) {
        this.v3AndBelowProducerId = v;
        return this;
    }
    
    public AddPartitionsToTxnRequestData setV3AndBelowProducerEpoch(short v) {
        this.v3AndBelowProducerEpoch = v;
        return this;
    }
    
    public AddPartitionsToTxnRequestData setV3AndBelowTopics(AddPartitionsToTxnTopicCollection v) {
        this.v3AndBelowTopics = v;
        return this;
    }
    
    public static class AddPartitionsToTxnTransaction implements Message, ImplicitLinkedHashMultiCollection.Element {
        String transactionalId;
        long producerId;
        short producerEpoch;
        boolean verifyOnly;
        AddPartitionsToTxnTopicCollection topics;
        private List<RawTaggedField> _unknownTaggedFields;
        private int next;
        private int prev;
        
        public static final Schema SCHEMA_4 =
            new Schema(
                new Field("transactional_id", Type.COMPACT_STRING, "The transactional id corresponding to the transaction."),
                new Field("producer_id", Type.INT64, "Current producer id in use by the transactional id."),
                new Field("producer_epoch", Type.INT16, "Current epoch associated with the producer id."),
                new Field("verify_only", Type.BOOLEAN, "Boolean to signify if we want to check if the partition is in the transaction rather than add it."),
                new Field("topics", new CompactArrayOf(AddPartitionsToTxnTopic.SCHEMA_3), "The partitions to add to the transaction."),
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
        
        public AddPartitionsToTxnTransaction(Readable _readable, short _version) {
            read(_readable, _version);
            this.prev = ImplicitLinkedHashCollection.INVALID_INDEX;
            this.next = ImplicitLinkedHashCollection.INVALID_INDEX;
        }
        
        public AddPartitionsToTxnTransaction() {
            this.transactionalId = "";
            this.producerId = 0L;
            this.producerEpoch = (short) 0;
            this.verifyOnly = false;
            this.topics = new AddPartitionsToTxnTopicCollection(0);
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
                throw new UnsupportedVersionException("Can't read version " + _version + " of AddPartitionsToTxnTransaction");
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
            this.producerId = _readable.readLong();
            this.producerEpoch = _readable.readShort();
            this.verifyOnly = _readable.readByte() != 0;
            {
                int arrayLength;
                arrayLength = _readable.readUnsignedVarint() - 1;
                if (arrayLength < 0) {
                    throw new RuntimeException("non-nullable field topics was serialized as null");
                } else {
                    if (arrayLength > _readable.remaining()) {
                        throw new RuntimeException("Tried to allocate a collection of size " + arrayLength + ", but there are only " + _readable.remaining() + " bytes remaining.");
                    }
                    AddPartitionsToTxnTopicCollection newCollection = new AddPartitionsToTxnTopicCollection(arrayLength);
                    for (int i = 0; i < arrayLength; i++) {
                        newCollection.add(new AddPartitionsToTxnTopic(_readable, _version));
                    }
                    this.topics = newCollection;
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
                throw new UnsupportedVersionException("Can't write version " + _version + " of AddPartitionsToTxnTransaction");
            }
            int _numTaggedFields = 0;
            {
                byte[] _stringBytes = _cache.getSerializedValue(transactionalId);
                _writable.writeUnsignedVarint(_stringBytes.length + 1);
                _writable.writeByteArray(_stringBytes);
            }
            _writable.writeLong(producerId);
            _writable.writeShort(producerEpoch);
            _writable.writeByte(verifyOnly ? (byte) 1 : (byte) 0);
            _writable.writeUnsignedVarint(topics.size() + 1);
            for (AddPartitionsToTxnTopic topicsElement : topics) {
                topicsElement.write(_writable, _cache, _version);
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
                throw new UnsupportedVersionException("Can't size version " + _version + " of AddPartitionsToTxnTransaction");
            }
            {
                byte[] _stringBytes = transactionalId.getBytes(StandardCharsets.UTF_8);
                if (_stringBytes.length > 0x7fff) {
                    throw new RuntimeException("'transactionalId' field is too long to be serialized");
                }
                _cache.cacheSerializedValue(transactionalId, _stringBytes);
                _size.addBytes(_stringBytes.length + ByteUtils.sizeOfUnsignedVarint(_stringBytes.length + 1));
            }
            _size.addBytes(8);
            _size.addBytes(2);
            _size.addBytes(1);
            {
                _size.addBytes(ByteUtils.sizeOfUnsignedVarint(topics.size() + 1));
                for (AddPartitionsToTxnTopic topicsElement : topics) {
                    topicsElement.addSize(_size, _cache, _version);
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
            if (!(obj instanceof AddPartitionsToTxnTransaction)) return false;
            AddPartitionsToTxnTransaction other = (AddPartitionsToTxnTransaction) obj;
            if (this.transactionalId == null) {
                if (other.transactionalId != null) return false;
            } else {
                if (!this.transactionalId.equals(other.transactionalId)) return false;
            }
            return true;
        }
        
        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof AddPartitionsToTxnTransaction)) return false;
            AddPartitionsToTxnTransaction other = (AddPartitionsToTxnTransaction) obj;
            if (this.transactionalId == null) {
                if (other.transactionalId != null) return false;
            } else {
                if (!this.transactionalId.equals(other.transactionalId)) return false;
            }
            if (producerId != other.producerId) return false;
            if (producerEpoch != other.producerEpoch) return false;
            if (verifyOnly != other.verifyOnly) return false;
            if (this.topics == null) {
                if (other.topics != null) return false;
            } else {
                if (!this.topics.equals(other.topics)) return false;
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
        public AddPartitionsToTxnTransaction duplicate() {
            AddPartitionsToTxnTransaction _duplicate = new AddPartitionsToTxnTransaction();
            _duplicate.transactionalId = transactionalId;
            _duplicate.producerId = producerId;
            _duplicate.producerEpoch = producerEpoch;
            _duplicate.verifyOnly = verifyOnly;
            AddPartitionsToTxnTopicCollection newTopics = new AddPartitionsToTxnTopicCollection(topics.size());
            for (AddPartitionsToTxnTopic _element : topics) {
                newTopics.add(_element.duplicate());
            }
            _duplicate.topics = newTopics;
            return _duplicate;
        }
        
        @Override
        public String toString() {
            return "AddPartitionsToTxnTransaction("
                + "transactionalId=" + ((transactionalId == null) ? "null" : "'" + transactionalId.toString() + "'")
                + ", producerId=" + producerId
                + ", producerEpoch=" + producerEpoch
                + ", verifyOnly=" + (verifyOnly ? "true" : "false")
                + ", topics=" + MessageUtil.deepToString(topics.iterator())
                + ")";
        }
        
        public String transactionalId() {
            return this.transactionalId;
        }
        
        public long producerId() {
            return this.producerId;
        }
        
        public short producerEpoch() {
            return this.producerEpoch;
        }
        
        public boolean verifyOnly() {
            return this.verifyOnly;
        }
        
        public AddPartitionsToTxnTopicCollection topics() {
            return this.topics;
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
        
        public AddPartitionsToTxnTransaction setTransactionalId(String v) {
            this.transactionalId = v;
            return this;
        }
        
        public AddPartitionsToTxnTransaction setProducerId(long v) {
            this.producerId = v;
            return this;
        }
        
        public AddPartitionsToTxnTransaction setProducerEpoch(short v) {
            this.producerEpoch = v;
            return this;
        }
        
        public AddPartitionsToTxnTransaction setVerifyOnly(boolean v) {
            this.verifyOnly = v;
            return this;
        }
        
        public AddPartitionsToTxnTransaction setTopics(AddPartitionsToTxnTopicCollection v) {
            this.topics = v;
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
    
    public static class AddPartitionsToTxnTransactionCollection extends ImplicitLinkedHashMultiCollection<AddPartitionsToTxnTransaction> {
        public AddPartitionsToTxnTransactionCollection() {
            super();
        }
        
        public AddPartitionsToTxnTransactionCollection(int expectedNumElements) {
            super(expectedNumElements);
        }
        
        public AddPartitionsToTxnTransactionCollection(Iterator<AddPartitionsToTxnTransaction> iterator) {
            super(iterator);
        }
        
        public AddPartitionsToTxnTransaction find(String transactionalId) {
            AddPartitionsToTxnTransaction _key = new AddPartitionsToTxnTransaction();
            _key.setTransactionalId(transactionalId);
            return find(_key);
        }
        
        public List<AddPartitionsToTxnTransaction> findAll(String transactionalId) {
            AddPartitionsToTxnTransaction _key = new AddPartitionsToTxnTransaction();
            _key.setTransactionalId(transactionalId);
            return findAll(_key);
        }
        
        public AddPartitionsToTxnTransactionCollection duplicate() {
            AddPartitionsToTxnTransactionCollection _duplicate = new AddPartitionsToTxnTransactionCollection(size());
            for (AddPartitionsToTxnTransaction _element : this) {
                _duplicate.add(_element.duplicate());
            }
            return _duplicate;
        }
    }
    
    public static class AddPartitionsToTxnTopic implements Message, ImplicitLinkedHashMultiCollection.Element {
        String name;
        List<Integer> partitions;
        private List<RawTaggedField> _unknownTaggedFields;
        private int next;
        private int prev;
        
        public static final Schema SCHEMA_0 =
            new Schema(
                new Field("name", Type.STRING, "The name of the topic."),
                new Field("partitions", new ArrayOf(Type.INT32), "The partition indexes to add to the transaction.")
            );
        
        public static final Schema SCHEMA_1 = SCHEMA_0;
        
        public static final Schema SCHEMA_2 = SCHEMA_1;
        
        public static final Schema SCHEMA_3 =
            new Schema(
                new Field("name", Type.COMPACT_STRING, "The name of the topic."),
                new Field("partitions", new CompactArrayOf(Type.INT32), "The partition indexes to add to the transaction."),
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
        
        public AddPartitionsToTxnTopic(Readable _readable, short _version) {
            read(_readable, _version);
            this.prev = ImplicitLinkedHashCollection.INVALID_INDEX;
            this.next = ImplicitLinkedHashCollection.INVALID_INDEX;
        }
        
        public AddPartitionsToTxnTopic() {
            this.name = "";
            this.partitions = new ArrayList<Integer>(0);
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
                int arrayLength;
                if (_version >= 3) {
                    arrayLength = _readable.readUnsignedVarint() - 1;
                } else {
                    arrayLength = _readable.readInt();
                }
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
                _writable.writeUnsignedVarint(partitions.size() + 1);
            } else {
                _writable.writeInt(partitions.size());
            }
            for (Integer partitionsElement : partitions) {
                _writable.writeInt(partitionsElement);
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
                    _size.addBytes(ByteUtils.sizeOfUnsignedVarint(partitions.size() + 1));
                } else {
                    _size.addBytes(4);
                }
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
            if (!(obj instanceof AddPartitionsToTxnTopic)) return false;
            AddPartitionsToTxnTopic other = (AddPartitionsToTxnTopic) obj;
            if (this.name == null) {
                if (other.name != null) return false;
            } else {
                if (!this.name.equals(other.name)) return false;
            }
            return true;
        }
        
        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof AddPartitionsToTxnTopic)) return false;
            AddPartitionsToTxnTopic other = (AddPartitionsToTxnTopic) obj;
            if (this.name == null) {
                if (other.name != null) return false;
            } else {
                if (!this.name.equals(other.name)) return false;
            }
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
            hashCode = 31 * hashCode + (name == null ? 0 : name.hashCode());
            return hashCode;
        }
        
        @Override
        public AddPartitionsToTxnTopic duplicate() {
            AddPartitionsToTxnTopic _duplicate = new AddPartitionsToTxnTopic();
            _duplicate.name = name;
            ArrayList<Integer> newPartitions = new ArrayList<Integer>(partitions.size());
            for (Integer _element : partitions) {
                newPartitions.add(_element);
            }
            _duplicate.partitions = newPartitions;
            return _duplicate;
        }
        
        @Override
        public String toString() {
            return "AddPartitionsToTxnTopic("
                + "name=" + ((name == null) ? "null" : "'" + name.toString() + "'")
                + ", partitions=" + MessageUtil.deepToString(partitions.iterator())
                + ")";
        }
        
        public String name() {
            return this.name;
        }
        
        public List<Integer> partitions() {
            return this.partitions;
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
        
        public AddPartitionsToTxnTopic setName(String v) {
            this.name = v;
            return this;
        }
        
        public AddPartitionsToTxnTopic setPartitions(List<Integer> v) {
            this.partitions = v;
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
    
    public static class AddPartitionsToTxnTopicCollection extends ImplicitLinkedHashMultiCollection<AddPartitionsToTxnTopic> {
        public AddPartitionsToTxnTopicCollection() {
            super();
        }
        
        public AddPartitionsToTxnTopicCollection(int expectedNumElements) {
            super(expectedNumElements);
        }
        
        public AddPartitionsToTxnTopicCollection(Iterator<AddPartitionsToTxnTopic> iterator) {
            super(iterator);
        }
        
        public AddPartitionsToTxnTopic find(String name) {
            AddPartitionsToTxnTopic _key = new AddPartitionsToTxnTopic();
            _key.setName(name);
            return find(_key);
        }
        
        public List<AddPartitionsToTxnTopic> findAll(String name) {
            AddPartitionsToTxnTopic _key = new AddPartitionsToTxnTopic();
            _key.setName(name);
            return findAll(_key);
        }
        
        public AddPartitionsToTxnTopicCollection duplicate() {
            AddPartitionsToTxnTopicCollection _duplicate = new AddPartitionsToTxnTopicCollection(size());
            for (AddPartitionsToTxnTopic _element : this) {
                _duplicate.add(_element.duplicate());
            }
            return _duplicate;
        }
    }
}
