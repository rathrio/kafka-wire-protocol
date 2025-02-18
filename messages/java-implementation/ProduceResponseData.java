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


public class ProduceResponseData implements ApiMessage {
    TopicProduceResponseCollection responses;
    int throttleTimeMs;
    NodeEndpointCollection nodeEndpoints;
    private List<RawTaggedField> _unknownTaggedFields;
    
    public static final Schema SCHEMA_3 =
        new Schema(
            new Field("responses", new ArrayOf(TopicProduceResponse.SCHEMA_3), "Each produce response."),
            new Field("throttle_time_ms", Type.INT32, "The duration in milliseconds for which the request was throttled due to a quota violation, or zero if the request did not violate any quota.")
        );
    
    public static final Schema SCHEMA_4 = SCHEMA_3;
    
    public static final Schema SCHEMA_5 =
        new Schema(
            new Field("responses", new ArrayOf(TopicProduceResponse.SCHEMA_5), "Each produce response."),
            new Field("throttle_time_ms", Type.INT32, "The duration in milliseconds for which the request was throttled due to a quota violation, or zero if the request did not violate any quota.")
        );
    
    public static final Schema SCHEMA_6 = SCHEMA_5;
    
    public static final Schema SCHEMA_7 = SCHEMA_6;
    
    public static final Schema SCHEMA_8 =
        new Schema(
            new Field("responses", new ArrayOf(TopicProduceResponse.SCHEMA_8), "Each produce response."),
            new Field("throttle_time_ms", Type.INT32, "The duration in milliseconds for which the request was throttled due to a quota violation, or zero if the request did not violate any quota.")
        );
    
    public static final Schema SCHEMA_9 =
        new Schema(
            new Field("responses", new CompactArrayOf(TopicProduceResponse.SCHEMA_9), "Each produce response."),
            new Field("throttle_time_ms", Type.INT32, "The duration in milliseconds for which the request was throttled due to a quota violation, or zero if the request did not violate any quota."),
            TaggedFieldsSection.of(
            )
        );
    
    public static final Schema SCHEMA_10 =
        new Schema(
            new Field("responses", new CompactArrayOf(TopicProduceResponse.SCHEMA_10), "Each produce response."),
            new Field("throttle_time_ms", Type.INT32, "The duration in milliseconds for which the request was throttled due to a quota violation, or zero if the request did not violate any quota."),
            TaggedFieldsSection.of(
                0, new Field("node_endpoints", new CompactArrayOf(NodeEndpoint.SCHEMA_10), "Endpoints for all current-leaders enumerated in PartitionProduceResponses, with errors NOT_LEADER_OR_FOLLOWER.")
            )
        );
    
    public static final Schema SCHEMA_11 = SCHEMA_10;
    
    public static final Schema SCHEMA_12 = SCHEMA_11;
    
    public static final Schema[] SCHEMAS = new Schema[] {
        null,
        null,
        null,
        SCHEMA_3,
        SCHEMA_4,
        SCHEMA_5,
        SCHEMA_6,
        SCHEMA_7,
        SCHEMA_8,
        SCHEMA_9,
        SCHEMA_10,
        SCHEMA_11,
        SCHEMA_12
    };
    
    public static final short LOWEST_SUPPORTED_VERSION = 3;
    public static final short HIGHEST_SUPPORTED_VERSION = 12;
    
    public ProduceResponseData(Readable _readable, short _version) {
        read(_readable, _version);
    }
    
    public ProduceResponseData() {
        this.responses = new TopicProduceResponseCollection(0);
        this.throttleTimeMs = 0;
        this.nodeEndpoints = new NodeEndpointCollection(0);
    }
    
    @Override
    public short apiKey() {
        return 0;
    }
    
    @Override
    public short lowestSupportedVersion() {
        return 3;
    }
    
    @Override
    public short highestSupportedVersion() {
        return 12;
    }
    
    @Override
    public final void read(Readable _readable, short _version) {
        {
            if (_version >= 9) {
                int arrayLength;
                arrayLength = _readable.readUnsignedVarint() - 1;
                if (arrayLength < 0) {
                    throw new RuntimeException("non-nullable field responses was serialized as null");
                } else {
                    if (arrayLength > _readable.remaining()) {
                        throw new RuntimeException("Tried to allocate a collection of size " + arrayLength + ", but there are only " + _readable.remaining() + " bytes remaining.");
                    }
                    TopicProduceResponseCollection newCollection = new TopicProduceResponseCollection(arrayLength);
                    for (int i = 0; i < arrayLength; i++) {
                        newCollection.add(new TopicProduceResponse(_readable, _version));
                    }
                    this.responses = newCollection;
                }
            } else {
                int arrayLength;
                arrayLength = _readable.readInt();
                if (arrayLength < 0) {
                    throw new RuntimeException("non-nullable field responses was serialized as null");
                } else {
                    if (arrayLength > _readable.remaining()) {
                        throw new RuntimeException("Tried to allocate a collection of size " + arrayLength + ", but there are only " + _readable.remaining() + " bytes remaining.");
                    }
                    TopicProduceResponseCollection newCollection = new TopicProduceResponseCollection(arrayLength);
                    for (int i = 0; i < arrayLength; i++) {
                        newCollection.add(new TopicProduceResponse(_readable, _version));
                    }
                    this.responses = newCollection;
                }
            }
        }
        this.throttleTimeMs = _readable.readInt();
        {
            this.nodeEndpoints = new NodeEndpointCollection(0);
        }
        this._unknownTaggedFields = null;
        if (_version >= 9) {
            int _numTaggedFields = _readable.readUnsignedVarint();
            for (int _i = 0; _i < _numTaggedFields; _i++) {
                int _tag = _readable.readUnsignedVarint();
                int _size = _readable.readUnsignedVarint();
                switch (_tag) {
                    case 0: {
                        if (_version >= 10) {
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
                            break;
                        } else {
                            throw new RuntimeException("Tag 0 is not valid for version " + _version);
                        }
                    }
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
        if (_version >= 9) {
            _writable.writeUnsignedVarint(responses.size() + 1);
            for (TopicProduceResponse responsesElement : responses) {
                responsesElement.write(_writable, _cache, _version);
            }
        } else {
            _writable.writeInt(responses.size());
            for (TopicProduceResponse responsesElement : responses) {
                responsesElement.write(_writable, _cache, _version);
            }
        }
        _writable.writeInt(throttleTimeMs);
        if (_version >= 10) {
            if (!this.nodeEndpoints.isEmpty()) {
                _numTaggedFields++;
            }
        } else {
            if (!this.nodeEndpoints.isEmpty()) {
                throw new UnsupportedVersionException("Attempted to write a non-default nodeEndpoints at version " + _version);
            }
        }
        RawTaggedFieldWriter _rawWriter = RawTaggedFieldWriter.forFields(_unknownTaggedFields);
        _numTaggedFields += _rawWriter.numFields();
        if (_version >= 9) {
            _writable.writeUnsignedVarint(_numTaggedFields);
            if (_version >= 10) {
                {
                    if (!this.nodeEndpoints.isEmpty()) {
                        _writable.writeUnsignedVarint(0);
                        _writable.writeUnsignedVarint(_cache.getArraySizeInBytes(this.nodeEndpoints));
                        _writable.writeUnsignedVarint(nodeEndpoints.size() + 1);
                        for (NodeEndpoint nodeEndpointsElement : nodeEndpoints) {
                            nodeEndpointsElement.write(_writable, _cache, _version);
                        }
                    }
                }
            }
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
            if (_version >= 9) {
                _size.addBytes(ByteUtils.sizeOfUnsignedVarint(responses.size() + 1));
            } else {
                _size.addBytes(4);
            }
            for (TopicProduceResponse responsesElement : responses) {
                responsesElement.addSize(_size, _cache, _version);
            }
        }
        _size.addBytes(4);
        if (_version >= 10) {
            {
                if (!this.nodeEndpoints.isEmpty()) {
                    _numTaggedFields++;
                    _size.addBytes(1);
                    int _sizeBeforeArray = _size.totalSize();
                    _size.addBytes(ByteUtils.sizeOfUnsignedVarint(nodeEndpoints.size() + 1));
                    for (NodeEndpoint nodeEndpointsElement : nodeEndpoints) {
                        nodeEndpointsElement.addSize(_size, _cache, _version);
                    }
                    int _arraySize = _size.totalSize() - _sizeBeforeArray;
                    _cache.setArraySizeInBytes(nodeEndpoints, _arraySize);
                    _size.addBytes(ByteUtils.sizeOfUnsignedVarint(_arraySize));
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
        if (_version >= 9) {
            _size.addBytes(ByteUtils.sizeOfUnsignedVarint(_numTaggedFields));
        } else {
            if (_numTaggedFields > 0) {
                throw new UnsupportedVersionException("Tagged fields were set, but version " + _version + " of this message does not support them.");
            }
        }
    }
    
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ProduceResponseData)) return false;
        ProduceResponseData other = (ProduceResponseData) obj;
        if (this.responses == null) {
            if (other.responses != null) return false;
        } else {
            if (!this.responses.equals(other.responses)) return false;
        }
        if (throttleTimeMs != other.throttleTimeMs) return false;
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
        hashCode = 31 * hashCode + (responses == null ? 0 : responses.hashCode());
        hashCode = 31 * hashCode + throttleTimeMs;
        hashCode = 31 * hashCode + (nodeEndpoints == null ? 0 : nodeEndpoints.hashCode());
        return hashCode;
    }
    
    @Override
    public ProduceResponseData duplicate() {
        ProduceResponseData _duplicate = new ProduceResponseData();
        TopicProduceResponseCollection newResponses = new TopicProduceResponseCollection(responses.size());
        for (TopicProduceResponse _element : responses) {
            newResponses.add(_element.duplicate());
        }
        _duplicate.responses = newResponses;
        _duplicate.throttleTimeMs = throttleTimeMs;
        NodeEndpointCollection newNodeEndpoints = new NodeEndpointCollection(nodeEndpoints.size());
        for (NodeEndpoint _element : nodeEndpoints) {
            newNodeEndpoints.add(_element.duplicate());
        }
        _duplicate.nodeEndpoints = newNodeEndpoints;
        return _duplicate;
    }
    
    @Override
    public String toString() {
        return "ProduceResponseData("
            + "responses=" + MessageUtil.deepToString(responses.iterator())
            + ", throttleTimeMs=" + throttleTimeMs
            + ", nodeEndpoints=" + MessageUtil.deepToString(nodeEndpoints.iterator())
            + ")";
    }
    
    public TopicProduceResponseCollection responses() {
        return this.responses;
    }
    
    public int throttleTimeMs() {
        return this.throttleTimeMs;
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
    
    public ProduceResponseData setResponses(TopicProduceResponseCollection v) {
        this.responses = v;
        return this;
    }
    
    public ProduceResponseData setThrottleTimeMs(int v) {
        this.throttleTimeMs = v;
        return this;
    }
    
    public ProduceResponseData setNodeEndpoints(NodeEndpointCollection v) {
        this.nodeEndpoints = v;
        return this;
    }
    
    public static class TopicProduceResponse implements Message, ImplicitLinkedHashMultiCollection.Element {
        String name;
        List<PartitionProduceResponse> partitionResponses;
        private List<RawTaggedField> _unknownTaggedFields;
        private int next;
        private int prev;
        
        public static final Schema SCHEMA_3 =
            new Schema(
                new Field("name", Type.STRING, "The topic name."),
                new Field("partition_responses", new ArrayOf(PartitionProduceResponse.SCHEMA_3), "Each partition that we produced to within the topic.")
            );
        
        public static final Schema SCHEMA_4 = SCHEMA_3;
        
        public static final Schema SCHEMA_5 =
            new Schema(
                new Field("name", Type.STRING, "The topic name."),
                new Field("partition_responses", new ArrayOf(PartitionProduceResponse.SCHEMA_5), "Each partition that we produced to within the topic.")
            );
        
        public static final Schema SCHEMA_6 = SCHEMA_5;
        
        public static final Schema SCHEMA_7 = SCHEMA_6;
        
        public static final Schema SCHEMA_8 =
            new Schema(
                new Field("name", Type.STRING, "The topic name."),
                new Field("partition_responses", new ArrayOf(PartitionProduceResponse.SCHEMA_8), "Each partition that we produced to within the topic.")
            );
        
        public static final Schema SCHEMA_9 =
            new Schema(
                new Field("name", Type.COMPACT_STRING, "The topic name."),
                new Field("partition_responses", new CompactArrayOf(PartitionProduceResponse.SCHEMA_9), "Each partition that we produced to within the topic."),
                TaggedFieldsSection.of(
                )
            );
        
        public static final Schema SCHEMA_10 =
            new Schema(
                new Field("name", Type.COMPACT_STRING, "The topic name."),
                new Field("partition_responses", new CompactArrayOf(PartitionProduceResponse.SCHEMA_10), "Each partition that we produced to within the topic."),
                TaggedFieldsSection.of(
                )
            );
        
        public static final Schema SCHEMA_11 = SCHEMA_10;
        
        public static final Schema SCHEMA_12 = SCHEMA_11;
        
        public static final Schema[] SCHEMAS = new Schema[] {
            null,
            null,
            null,
            SCHEMA_3,
            SCHEMA_4,
            SCHEMA_5,
            SCHEMA_6,
            SCHEMA_7,
            SCHEMA_8,
            SCHEMA_9,
            SCHEMA_10,
            SCHEMA_11,
            SCHEMA_12
        };
        
        public static final short LOWEST_SUPPORTED_VERSION = 3;
        public static final short HIGHEST_SUPPORTED_VERSION = 12;
        
        public TopicProduceResponse(Readable _readable, short _version) {
            read(_readable, _version);
            this.prev = ImplicitLinkedHashCollection.INVALID_INDEX;
            this.next = ImplicitLinkedHashCollection.INVALID_INDEX;
        }
        
        public TopicProduceResponse() {
            this.name = "";
            this.partitionResponses = new ArrayList<PartitionProduceResponse>(0);
            this.prev = ImplicitLinkedHashCollection.INVALID_INDEX;
            this.next = ImplicitLinkedHashCollection.INVALID_INDEX;
        }
        
        
        @Override
        public short lowestSupportedVersion() {
            return 3;
        }
        
        @Override
        public short highestSupportedVersion() {
            return 12;
        }
        
        @Override
        public final void read(Readable _readable, short _version) {
            if ((_version < 3) || (_version > 12)) {
                throw new UnsupportedVersionException("Can't read version " + _version + " of TopicProduceResponse");
            }
            {
                int length;
                if (_version >= 9) {
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
                if (_version >= 9) {
                    int arrayLength;
                    arrayLength = _readable.readUnsignedVarint() - 1;
                    if (arrayLength < 0) {
                        throw new RuntimeException("non-nullable field partitionResponses was serialized as null");
                    } else {
                        if (arrayLength > _readable.remaining()) {
                            throw new RuntimeException("Tried to allocate a collection of size " + arrayLength + ", but there are only " + _readable.remaining() + " bytes remaining.");
                        }
                        ArrayList<PartitionProduceResponse> newCollection = new ArrayList<>(arrayLength);
                        for (int i = 0; i < arrayLength; i++) {
                            newCollection.add(new PartitionProduceResponse(_readable, _version));
                        }
                        this.partitionResponses = newCollection;
                    }
                } else {
                    int arrayLength;
                    arrayLength = _readable.readInt();
                    if (arrayLength < 0) {
                        throw new RuntimeException("non-nullable field partitionResponses was serialized as null");
                    } else {
                        if (arrayLength > _readable.remaining()) {
                            throw new RuntimeException("Tried to allocate a collection of size " + arrayLength + ", but there are only " + _readable.remaining() + " bytes remaining.");
                        }
                        ArrayList<PartitionProduceResponse> newCollection = new ArrayList<>(arrayLength);
                        for (int i = 0; i < arrayLength; i++) {
                            newCollection.add(new PartitionProduceResponse(_readable, _version));
                        }
                        this.partitionResponses = newCollection;
                    }
                }
            }
            this._unknownTaggedFields = null;
            if (_version >= 9) {
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
                if (_version >= 9) {
                    _writable.writeUnsignedVarint(_stringBytes.length + 1);
                } else {
                    _writable.writeShort((short) _stringBytes.length);
                }
                _writable.writeByteArray(_stringBytes);
            }
            if (_version >= 9) {
                _writable.writeUnsignedVarint(partitionResponses.size() + 1);
                for (PartitionProduceResponse partitionResponsesElement : partitionResponses) {
                    partitionResponsesElement.write(_writable, _cache, _version);
                }
            } else {
                _writable.writeInt(partitionResponses.size());
                for (PartitionProduceResponse partitionResponsesElement : partitionResponses) {
                    partitionResponsesElement.write(_writable, _cache, _version);
                }
            }
            RawTaggedFieldWriter _rawWriter = RawTaggedFieldWriter.forFields(_unknownTaggedFields);
            _numTaggedFields += _rawWriter.numFields();
            if (_version >= 9) {
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
            if ((_version < 3) || (_version > 12)) {
                throw new UnsupportedVersionException("Can't size version " + _version + " of TopicProduceResponse");
            }
            {
                byte[] _stringBytes = name.getBytes(StandardCharsets.UTF_8);
                if (_stringBytes.length > 0x7fff) {
                    throw new RuntimeException("'name' field is too long to be serialized");
                }
                _cache.cacheSerializedValue(name, _stringBytes);
                if (_version >= 9) {
                    _size.addBytes(_stringBytes.length + ByteUtils.sizeOfUnsignedVarint(_stringBytes.length + 1));
                } else {
                    _size.addBytes(_stringBytes.length + 2);
                }
            }
            {
                if (_version >= 9) {
                    _size.addBytes(ByteUtils.sizeOfUnsignedVarint(partitionResponses.size() + 1));
                } else {
                    _size.addBytes(4);
                }
                for (PartitionProduceResponse partitionResponsesElement : partitionResponses) {
                    partitionResponsesElement.addSize(_size, _cache, _version);
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
            if (_version >= 9) {
                _size.addBytes(ByteUtils.sizeOfUnsignedVarint(_numTaggedFields));
            } else {
                if (_numTaggedFields > 0) {
                    throw new UnsupportedVersionException("Tagged fields were set, but version " + _version + " of this message does not support them.");
                }
            }
        }
        
        @Override
        public boolean elementKeysAreEqual(Object obj) {
            if (!(obj instanceof TopicProduceResponse)) return false;
            TopicProduceResponse other = (TopicProduceResponse) obj;
            if (this.name == null) {
                if (other.name != null) return false;
            } else {
                if (!this.name.equals(other.name)) return false;
            }
            return true;
        }
        
        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof TopicProduceResponse)) return false;
            TopicProduceResponse other = (TopicProduceResponse) obj;
            if (this.name == null) {
                if (other.name != null) return false;
            } else {
                if (!this.name.equals(other.name)) return false;
            }
            if (this.partitionResponses == null) {
                if (other.partitionResponses != null) return false;
            } else {
                if (!this.partitionResponses.equals(other.partitionResponses)) return false;
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
        public TopicProduceResponse duplicate() {
            TopicProduceResponse _duplicate = new TopicProduceResponse();
            _duplicate.name = name;
            ArrayList<PartitionProduceResponse> newPartitionResponses = new ArrayList<PartitionProduceResponse>(partitionResponses.size());
            for (PartitionProduceResponse _element : partitionResponses) {
                newPartitionResponses.add(_element.duplicate());
            }
            _duplicate.partitionResponses = newPartitionResponses;
            return _duplicate;
        }
        
        @Override
        public String toString() {
            return "TopicProduceResponse("
                + "name=" + ((name == null) ? "null" : "'" + name.toString() + "'")
                + ", partitionResponses=" + MessageUtil.deepToString(partitionResponses.iterator())
                + ")";
        }
        
        public String name() {
            return this.name;
        }
        
        public List<PartitionProduceResponse> partitionResponses() {
            return this.partitionResponses;
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
        
        public TopicProduceResponse setName(String v) {
            this.name = v;
            return this;
        }
        
        public TopicProduceResponse setPartitionResponses(List<PartitionProduceResponse> v) {
            this.partitionResponses = v;
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
    
    public static class PartitionProduceResponse implements Message {
        int index;
        short errorCode;
        long baseOffset;
        long logAppendTimeMs;
        long logStartOffset;
        List<BatchIndexAndErrorMessage> recordErrors;
        String errorMessage;
        LeaderIdAndEpoch currentLeader;
        private List<RawTaggedField> _unknownTaggedFields;
        
        public static final Schema SCHEMA_3 =
            new Schema(
                new Field("index", Type.INT32, "The partition index."),
                new Field("error_code", Type.INT16, "The error code, or 0 if there was no error."),
                new Field("base_offset", Type.INT64, "The base offset."),
                new Field("log_append_time_ms", Type.INT64, "The timestamp returned by broker after appending the messages. If CreateTime is used for the topic, the timestamp will be -1.  If LogAppendTime is used for the topic, the timestamp will be the broker local time when the messages are appended.")
            );
        
        public static final Schema SCHEMA_4 = SCHEMA_3;
        
        public static final Schema SCHEMA_5 =
            new Schema(
                new Field("index", Type.INT32, "The partition index."),
                new Field("error_code", Type.INT16, "The error code, or 0 if there was no error."),
                new Field("base_offset", Type.INT64, "The base offset."),
                new Field("log_append_time_ms", Type.INT64, "The timestamp returned by broker after appending the messages. If CreateTime is used for the topic, the timestamp will be -1.  If LogAppendTime is used for the topic, the timestamp will be the broker local time when the messages are appended."),
                new Field("log_start_offset", Type.INT64, "The log start offset.")
            );
        
        public static final Schema SCHEMA_6 = SCHEMA_5;
        
        public static final Schema SCHEMA_7 = SCHEMA_6;
        
        public static final Schema SCHEMA_8 =
            new Schema(
                new Field("index", Type.INT32, "The partition index."),
                new Field("error_code", Type.INT16, "The error code, or 0 if there was no error."),
                new Field("base_offset", Type.INT64, "The base offset."),
                new Field("log_append_time_ms", Type.INT64, "The timestamp returned by broker after appending the messages. If CreateTime is used for the topic, the timestamp will be -1.  If LogAppendTime is used for the topic, the timestamp will be the broker local time when the messages are appended."),
                new Field("log_start_offset", Type.INT64, "The log start offset."),
                new Field("record_errors", new ArrayOf(BatchIndexAndErrorMessage.SCHEMA_8), "The batch indices of records that caused the batch to be dropped."),
                new Field("error_message", Type.NULLABLE_STRING, "The global error message summarizing the common root cause of the records that caused the batch to be dropped.")
            );
        
        public static final Schema SCHEMA_9 =
            new Schema(
                new Field("index", Type.INT32, "The partition index."),
                new Field("error_code", Type.INT16, "The error code, or 0 if there was no error."),
                new Field("base_offset", Type.INT64, "The base offset."),
                new Field("log_append_time_ms", Type.INT64, "The timestamp returned by broker after appending the messages. If CreateTime is used for the topic, the timestamp will be -1.  If LogAppendTime is used for the topic, the timestamp will be the broker local time when the messages are appended."),
                new Field("log_start_offset", Type.INT64, "The log start offset."),
                new Field("record_errors", new CompactArrayOf(BatchIndexAndErrorMessage.SCHEMA_9), "The batch indices of records that caused the batch to be dropped."),
                new Field("error_message", Type.COMPACT_NULLABLE_STRING, "The global error message summarizing the common root cause of the records that caused the batch to be dropped."),
                TaggedFieldsSection.of(
                )
            );
        
        public static final Schema SCHEMA_10 =
            new Schema(
                new Field("index", Type.INT32, "The partition index."),
                new Field("error_code", Type.INT16, "The error code, or 0 if there was no error."),
                new Field("base_offset", Type.INT64, "The base offset."),
                new Field("log_append_time_ms", Type.INT64, "The timestamp returned by broker after appending the messages. If CreateTime is used for the topic, the timestamp will be -1.  If LogAppendTime is used for the topic, the timestamp will be the broker local time when the messages are appended."),
                new Field("log_start_offset", Type.INT64, "The log start offset."),
                new Field("record_errors", new CompactArrayOf(BatchIndexAndErrorMessage.SCHEMA_9), "The batch indices of records that caused the batch to be dropped."),
                new Field("error_message", Type.COMPACT_NULLABLE_STRING, "The global error message summarizing the common root cause of the records that caused the batch to be dropped."),
                TaggedFieldsSection.of(
                    0, new Field("current_leader", LeaderIdAndEpoch.SCHEMA_10, "The leader broker that the producer should use for future requests.")
                )
            );
        
        public static final Schema SCHEMA_11 = SCHEMA_10;
        
        public static final Schema SCHEMA_12 = SCHEMA_11;
        
        public static final Schema[] SCHEMAS = new Schema[] {
            null,
            null,
            null,
            SCHEMA_3,
            SCHEMA_4,
            SCHEMA_5,
            SCHEMA_6,
            SCHEMA_7,
            SCHEMA_8,
            SCHEMA_9,
            SCHEMA_10,
            SCHEMA_11,
            SCHEMA_12
        };
        
        public static final short LOWEST_SUPPORTED_VERSION = 3;
        public static final short HIGHEST_SUPPORTED_VERSION = 12;
        
        public PartitionProduceResponse(Readable _readable, short _version) {
            read(_readable, _version);
        }
        
        public PartitionProduceResponse() {
            this.index = 0;
            this.errorCode = (short) 0;
            this.baseOffset = 0L;
            this.logAppendTimeMs = -1L;
            this.logStartOffset = -1L;
            this.recordErrors = new ArrayList<BatchIndexAndErrorMessage>(0);
            this.errorMessage = null;
            this.currentLeader = new LeaderIdAndEpoch();
        }
        
        
        @Override
        public short lowestSupportedVersion() {
            return 3;
        }
        
        @Override
        public short highestSupportedVersion() {
            return 12;
        }
        
        @Override
        public final void read(Readable _readable, short _version) {
            if ((_version < 3) || (_version > 12)) {
                throw new UnsupportedVersionException("Can't read version " + _version + " of PartitionProduceResponse");
            }
            this.index = _readable.readInt();
            this.errorCode = _readable.readShort();
            this.baseOffset = _readable.readLong();
            this.logAppendTimeMs = _readable.readLong();
            if (_version >= 5) {
                this.logStartOffset = _readable.readLong();
            } else {
                this.logStartOffset = -1L;
            }
            if (_version >= 8) {
                if (_version >= 9) {
                    int arrayLength;
                    arrayLength = _readable.readUnsignedVarint() - 1;
                    if (arrayLength < 0) {
                        throw new RuntimeException("non-nullable field recordErrors was serialized as null");
                    } else {
                        if (arrayLength > _readable.remaining()) {
                            throw new RuntimeException("Tried to allocate a collection of size " + arrayLength + ", but there are only " + _readable.remaining() + " bytes remaining.");
                        }
                        ArrayList<BatchIndexAndErrorMessage> newCollection = new ArrayList<>(arrayLength);
                        for (int i = 0; i < arrayLength; i++) {
                            newCollection.add(new BatchIndexAndErrorMessage(_readable, _version));
                        }
                        this.recordErrors = newCollection;
                    }
                } else {
                    int arrayLength;
                    arrayLength = _readable.readInt();
                    if (arrayLength < 0) {
                        throw new RuntimeException("non-nullable field recordErrors was serialized as null");
                    } else {
                        if (arrayLength > _readable.remaining()) {
                            throw new RuntimeException("Tried to allocate a collection of size " + arrayLength + ", but there are only " + _readable.remaining() + " bytes remaining.");
                        }
                        ArrayList<BatchIndexAndErrorMessage> newCollection = new ArrayList<>(arrayLength);
                        for (int i = 0; i < arrayLength; i++) {
                            newCollection.add(new BatchIndexAndErrorMessage(_readable, _version));
                        }
                        this.recordErrors = newCollection;
                    }
                }
            } else {
                this.recordErrors = new ArrayList<BatchIndexAndErrorMessage>(0);
            }
            if (_version >= 8) {
                int length;
                if (_version >= 9) {
                    length = _readable.readUnsignedVarint() - 1;
                } else {
                    length = _readable.readShort();
                }
                if (length < 0) {
                    this.errorMessage = null;
                } else if (length > 0x7fff) {
                    throw new RuntimeException("string field errorMessage had invalid length " + length);
                } else {
                    this.errorMessage = _readable.readString(length);
                }
            } else {
                this.errorMessage = null;
            }
            {
                this.currentLeader = new LeaderIdAndEpoch();
            }
            this._unknownTaggedFields = null;
            if (_version >= 9) {
                int _numTaggedFields = _readable.readUnsignedVarint();
                for (int _i = 0; _i < _numTaggedFields; _i++) {
                    int _tag = _readable.readUnsignedVarint();
                    int _size = _readable.readUnsignedVarint();
                    switch (_tag) {
                        case 0: {
                            if (_version >= 10) {
                                this.currentLeader = new LeaderIdAndEpoch(_readable, _version);
                                break;
                            } else {
                                throw new RuntimeException("Tag 0 is not valid for version " + _version);
                            }
                        }
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
            _writable.writeInt(index);
            _writable.writeShort(errorCode);
            _writable.writeLong(baseOffset);
            _writable.writeLong(logAppendTimeMs);
            if (_version >= 5) {
                _writable.writeLong(logStartOffset);
            }
            if (_version >= 8) {
                if (_version >= 9) {
                    _writable.writeUnsignedVarint(recordErrors.size() + 1);
                    for (BatchIndexAndErrorMessage recordErrorsElement : recordErrors) {
                        recordErrorsElement.write(_writable, _cache, _version);
                    }
                } else {
                    _writable.writeInt(recordErrors.size());
                    for (BatchIndexAndErrorMessage recordErrorsElement : recordErrors) {
                        recordErrorsElement.write(_writable, _cache, _version);
                    }
                }
            }
            if (_version >= 8) {
                if (errorMessage == null) {
                    if (_version >= 9) {
                        _writable.writeUnsignedVarint(0);
                    } else {
                        _writable.writeShort((short) -1);
                    }
                } else {
                    byte[] _stringBytes = _cache.getSerializedValue(errorMessage);
                    if (_version >= 9) {
                        _writable.writeUnsignedVarint(_stringBytes.length + 1);
                    } else {
                        _writable.writeShort((short) _stringBytes.length);
                    }
                    _writable.writeByteArray(_stringBytes);
                }
            }
            if (_version >= 10) {
                if (!this.currentLeader.equals(new LeaderIdAndEpoch())) {
                    _numTaggedFields++;
                }
            } else {
                if (!this.currentLeader.equals(new LeaderIdAndEpoch())) {
                    throw new UnsupportedVersionException("Attempted to write a non-default currentLeader at version " + _version);
                }
            }
            RawTaggedFieldWriter _rawWriter = RawTaggedFieldWriter.forFields(_unknownTaggedFields);
            _numTaggedFields += _rawWriter.numFields();
            if (_version >= 9) {
                _writable.writeUnsignedVarint(_numTaggedFields);
                if (_version >= 10) {
                    {
                        if (!this.currentLeader.equals(new LeaderIdAndEpoch())) {
                            _writable.writeUnsignedVarint(0);
                            _writable.writeUnsignedVarint(this.currentLeader.size(_cache, _version));
                            currentLeader.write(_writable, _cache, _version);
                        }
                    }
                }
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
            if ((_version < 3) || (_version > 12)) {
                throw new UnsupportedVersionException("Can't size version " + _version + " of PartitionProduceResponse");
            }
            _size.addBytes(4);
            _size.addBytes(2);
            _size.addBytes(8);
            _size.addBytes(8);
            if (_version >= 5) {
                _size.addBytes(8);
            }
            if (_version >= 8) {
                {
                    if (_version >= 9) {
                        _size.addBytes(ByteUtils.sizeOfUnsignedVarint(recordErrors.size() + 1));
                    } else {
                        _size.addBytes(4);
                    }
                    for (BatchIndexAndErrorMessage recordErrorsElement : recordErrors) {
                        recordErrorsElement.addSize(_size, _cache, _version);
                    }
                }
            }
            if (_version >= 8) {
                if (errorMessage == null) {
                    if (_version >= 9) {
                        _size.addBytes(1);
                    } else {
                        _size.addBytes(2);
                    }
                } else {
                    byte[] _stringBytes = errorMessage.getBytes(StandardCharsets.UTF_8);
                    if (_stringBytes.length > 0x7fff) {
                        throw new RuntimeException("'errorMessage' field is too long to be serialized");
                    }
                    _cache.cacheSerializedValue(errorMessage, _stringBytes);
                    if (_version >= 9) {
                        _size.addBytes(_stringBytes.length + ByteUtils.sizeOfUnsignedVarint(_stringBytes.length + 1));
                    } else {
                        _size.addBytes(_stringBytes.length + 2);
                    }
                }
            }
            if (_version >= 10) {
                {
                    if (!this.currentLeader.equals(new LeaderIdAndEpoch())) {
                        _numTaggedFields++;
                        _size.addBytes(1);
                        int _sizeBeforeStruct = _size.totalSize();
                        this.currentLeader.addSize(_size, _cache, _version);
                        int _structSize = _size.totalSize() - _sizeBeforeStruct;
                        _size.addBytes(ByteUtils.sizeOfUnsignedVarint(_structSize));
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
            if (_version >= 9) {
                _size.addBytes(ByteUtils.sizeOfUnsignedVarint(_numTaggedFields));
            } else {
                if (_numTaggedFields > 0) {
                    throw new UnsupportedVersionException("Tagged fields were set, but version " + _version + " of this message does not support them.");
                }
            }
        }
        
        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof PartitionProduceResponse)) return false;
            PartitionProduceResponse other = (PartitionProduceResponse) obj;
            if (index != other.index) return false;
            if (errorCode != other.errorCode) return false;
            if (baseOffset != other.baseOffset) return false;
            if (logAppendTimeMs != other.logAppendTimeMs) return false;
            if (logStartOffset != other.logStartOffset) return false;
            if (this.recordErrors == null) {
                if (other.recordErrors != null) return false;
            } else {
                if (!this.recordErrors.equals(other.recordErrors)) return false;
            }
            if (this.errorMessage == null) {
                if (other.errorMessage != null) return false;
            } else {
                if (!this.errorMessage.equals(other.errorMessage)) return false;
            }
            if (this.currentLeader == null) {
                if (other.currentLeader != null) return false;
            } else {
                if (!this.currentLeader.equals(other.currentLeader)) return false;
            }
            return MessageUtil.compareRawTaggedFields(_unknownTaggedFields, other._unknownTaggedFields);
        }
        
        @Override
        public int hashCode() {
            int hashCode = 0;
            hashCode = 31 * hashCode + index;
            hashCode = 31 * hashCode + errorCode;
            hashCode = 31 * hashCode + ((int) (baseOffset >> 32) ^ (int) baseOffset);
            hashCode = 31 * hashCode + ((int) (logAppendTimeMs >> 32) ^ (int) logAppendTimeMs);
            hashCode = 31 * hashCode + ((int) (logStartOffset >> 32) ^ (int) logStartOffset);
            hashCode = 31 * hashCode + (recordErrors == null ? 0 : recordErrors.hashCode());
            hashCode = 31 * hashCode + (errorMessage == null ? 0 : errorMessage.hashCode());
            hashCode = 31 * hashCode + (currentLeader == null ? 0 : currentLeader.hashCode());
            return hashCode;
        }
        
        @Override
        public PartitionProduceResponse duplicate() {
            PartitionProduceResponse _duplicate = new PartitionProduceResponse();
            _duplicate.index = index;
            _duplicate.errorCode = errorCode;
            _duplicate.baseOffset = baseOffset;
            _duplicate.logAppendTimeMs = logAppendTimeMs;
            _duplicate.logStartOffset = logStartOffset;
            ArrayList<BatchIndexAndErrorMessage> newRecordErrors = new ArrayList<BatchIndexAndErrorMessage>(recordErrors.size());
            for (BatchIndexAndErrorMessage _element : recordErrors) {
                newRecordErrors.add(_element.duplicate());
            }
            _duplicate.recordErrors = newRecordErrors;
            if (errorMessage == null) {
                _duplicate.errorMessage = null;
            } else {
                _duplicate.errorMessage = errorMessage;
            }
            _duplicate.currentLeader = currentLeader.duplicate();
            return _duplicate;
        }
        
        @Override
        public String toString() {
            return "PartitionProduceResponse("
                + "index=" + index
                + ", errorCode=" + errorCode
                + ", baseOffset=" + baseOffset
                + ", logAppendTimeMs=" + logAppendTimeMs
                + ", logStartOffset=" + logStartOffset
                + ", recordErrors=" + MessageUtil.deepToString(recordErrors.iterator())
                + ", errorMessage=" + ((errorMessage == null) ? "null" : "'" + errorMessage.toString() + "'")
                + ", currentLeader=" + currentLeader.toString()
                + ")";
        }
        
        public int index() {
            return this.index;
        }
        
        public short errorCode() {
            return this.errorCode;
        }
        
        public long baseOffset() {
            return this.baseOffset;
        }
        
        public long logAppendTimeMs() {
            return this.logAppendTimeMs;
        }
        
        public long logStartOffset() {
            return this.logStartOffset;
        }
        
        public List<BatchIndexAndErrorMessage> recordErrors() {
            return this.recordErrors;
        }
        
        public String errorMessage() {
            return this.errorMessage;
        }
        
        public LeaderIdAndEpoch currentLeader() {
            return this.currentLeader;
        }
        
        @Override
        public List<RawTaggedField> unknownTaggedFields() {
            if (_unknownTaggedFields == null) {
                _unknownTaggedFields = new ArrayList<>(0);
            }
            return _unknownTaggedFields;
        }
        
        public PartitionProduceResponse setIndex(int v) {
            this.index = v;
            return this;
        }
        
        public PartitionProduceResponse setErrorCode(short v) {
            this.errorCode = v;
            return this;
        }
        
        public PartitionProduceResponse setBaseOffset(long v) {
            this.baseOffset = v;
            return this;
        }
        
        public PartitionProduceResponse setLogAppendTimeMs(long v) {
            this.logAppendTimeMs = v;
            return this;
        }
        
        public PartitionProduceResponse setLogStartOffset(long v) {
            this.logStartOffset = v;
            return this;
        }
        
        public PartitionProduceResponse setRecordErrors(List<BatchIndexAndErrorMessage> v) {
            this.recordErrors = v;
            return this;
        }
        
        public PartitionProduceResponse setErrorMessage(String v) {
            this.errorMessage = v;
            return this;
        }
        
        public PartitionProduceResponse setCurrentLeader(LeaderIdAndEpoch v) {
            this.currentLeader = v;
            return this;
        }
    }
    
    public static class BatchIndexAndErrorMessage implements Message {
        int batchIndex;
        String batchIndexErrorMessage;
        private List<RawTaggedField> _unknownTaggedFields;
        
        public static final Schema SCHEMA_8 =
            new Schema(
                new Field("batch_index", Type.INT32, "The batch index of the record that caused the batch to be dropped."),
                new Field("batch_index_error_message", Type.NULLABLE_STRING, "The error message of the record that caused the batch to be dropped.")
            );
        
        public static final Schema SCHEMA_9 =
            new Schema(
                new Field("batch_index", Type.INT32, "The batch index of the record that caused the batch to be dropped."),
                new Field("batch_index_error_message", Type.COMPACT_NULLABLE_STRING, "The error message of the record that caused the batch to be dropped."),
                TaggedFieldsSection.of(
                )
            );
        
        public static final Schema SCHEMA_10 = SCHEMA_9;
        
        public static final Schema SCHEMA_11 = SCHEMA_10;
        
        public static final Schema SCHEMA_12 = SCHEMA_11;
        
        public static final Schema[] SCHEMAS = new Schema[] {
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            SCHEMA_8,
            SCHEMA_9,
            SCHEMA_10,
            SCHEMA_11,
            SCHEMA_12
        };
        
        public static final short LOWEST_SUPPORTED_VERSION = 8;
        public static final short HIGHEST_SUPPORTED_VERSION = 12;
        
        public BatchIndexAndErrorMessage(Readable _readable, short _version) {
            read(_readable, _version);
        }
        
        public BatchIndexAndErrorMessage() {
            this.batchIndex = 0;
            this.batchIndexErrorMessage = null;
        }
        
        
        @Override
        public short lowestSupportedVersion() {
            return 3;
        }
        
        @Override
        public short highestSupportedVersion() {
            return 12;
        }
        
        @Override
        public final void read(Readable _readable, short _version) {
            if (_version > 12) {
                throw new UnsupportedVersionException("Can't read version " + _version + " of BatchIndexAndErrorMessage");
            }
            this.batchIndex = _readable.readInt();
            {
                int length;
                if (_version >= 9) {
                    length = _readable.readUnsignedVarint() - 1;
                } else {
                    length = _readable.readShort();
                }
                if (length < 0) {
                    this.batchIndexErrorMessage = null;
                } else if (length > 0x7fff) {
                    throw new RuntimeException("string field batchIndexErrorMessage had invalid length " + length);
                } else {
                    this.batchIndexErrorMessage = _readable.readString(length);
                }
            }
            this._unknownTaggedFields = null;
            if (_version >= 9) {
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
            if (_version < 8) {
                throw new UnsupportedVersionException("Can't write version " + _version + " of BatchIndexAndErrorMessage");
            }
            int _numTaggedFields = 0;
            _writable.writeInt(batchIndex);
            if (batchIndexErrorMessage == null) {
                if (_version >= 9) {
                    _writable.writeUnsignedVarint(0);
                } else {
                    _writable.writeShort((short) -1);
                }
            } else {
                byte[] _stringBytes = _cache.getSerializedValue(batchIndexErrorMessage);
                if (_version >= 9) {
                    _writable.writeUnsignedVarint(_stringBytes.length + 1);
                } else {
                    _writable.writeShort((short) _stringBytes.length);
                }
                _writable.writeByteArray(_stringBytes);
            }
            RawTaggedFieldWriter _rawWriter = RawTaggedFieldWriter.forFields(_unknownTaggedFields);
            _numTaggedFields += _rawWriter.numFields();
            if (_version >= 9) {
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
            if (_version > 12) {
                throw new UnsupportedVersionException("Can't size version " + _version + " of BatchIndexAndErrorMessage");
            }
            _size.addBytes(4);
            if (batchIndexErrorMessage == null) {
                if (_version >= 9) {
                    _size.addBytes(1);
                } else {
                    _size.addBytes(2);
                }
            } else {
                byte[] _stringBytes = batchIndexErrorMessage.getBytes(StandardCharsets.UTF_8);
                if (_stringBytes.length > 0x7fff) {
                    throw new RuntimeException("'batchIndexErrorMessage' field is too long to be serialized");
                }
                _cache.cacheSerializedValue(batchIndexErrorMessage, _stringBytes);
                if (_version >= 9) {
                    _size.addBytes(_stringBytes.length + ByteUtils.sizeOfUnsignedVarint(_stringBytes.length + 1));
                } else {
                    _size.addBytes(_stringBytes.length + 2);
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
            if (_version >= 9) {
                _size.addBytes(ByteUtils.sizeOfUnsignedVarint(_numTaggedFields));
            } else {
                if (_numTaggedFields > 0) {
                    throw new UnsupportedVersionException("Tagged fields were set, but version " + _version + " of this message does not support them.");
                }
            }
        }
        
        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof BatchIndexAndErrorMessage)) return false;
            BatchIndexAndErrorMessage other = (BatchIndexAndErrorMessage) obj;
            if (batchIndex != other.batchIndex) return false;
            if (this.batchIndexErrorMessage == null) {
                if (other.batchIndexErrorMessage != null) return false;
            } else {
                if (!this.batchIndexErrorMessage.equals(other.batchIndexErrorMessage)) return false;
            }
            return MessageUtil.compareRawTaggedFields(_unknownTaggedFields, other._unknownTaggedFields);
        }
        
        @Override
        public int hashCode() {
            int hashCode = 0;
            hashCode = 31 * hashCode + batchIndex;
            hashCode = 31 * hashCode + (batchIndexErrorMessage == null ? 0 : batchIndexErrorMessage.hashCode());
            return hashCode;
        }
        
        @Override
        public BatchIndexAndErrorMessage duplicate() {
            BatchIndexAndErrorMessage _duplicate = new BatchIndexAndErrorMessage();
            _duplicate.batchIndex = batchIndex;
            if (batchIndexErrorMessage == null) {
                _duplicate.batchIndexErrorMessage = null;
            } else {
                _duplicate.batchIndexErrorMessage = batchIndexErrorMessage;
            }
            return _duplicate;
        }
        
        @Override
        public String toString() {
            return "BatchIndexAndErrorMessage("
                + "batchIndex=" + batchIndex
                + ", batchIndexErrorMessage=" + ((batchIndexErrorMessage == null) ? "null" : "'" + batchIndexErrorMessage.toString() + "'")
                + ")";
        }
        
        public int batchIndex() {
            return this.batchIndex;
        }
        
        public String batchIndexErrorMessage() {
            return this.batchIndexErrorMessage;
        }
        
        @Override
        public List<RawTaggedField> unknownTaggedFields() {
            if (_unknownTaggedFields == null) {
                _unknownTaggedFields = new ArrayList<>(0);
            }
            return _unknownTaggedFields;
        }
        
        public BatchIndexAndErrorMessage setBatchIndex(int v) {
            this.batchIndex = v;
            return this;
        }
        
        public BatchIndexAndErrorMessage setBatchIndexErrorMessage(String v) {
            this.batchIndexErrorMessage = v;
            return this;
        }
    }
    
    public static class LeaderIdAndEpoch implements Message {
        int leaderId;
        int leaderEpoch;
        private List<RawTaggedField> _unknownTaggedFields;
        
        public static final Schema SCHEMA_10 =
            new Schema(
                new Field("leader_id", Type.INT32, "The ID of the current leader or -1 if the leader is unknown."),
                new Field("leader_epoch", Type.INT32, "The latest known leader epoch."),
                TaggedFieldsSection.of(
                )
            );
        
        public static final Schema SCHEMA_11 = SCHEMA_10;
        
        public static final Schema SCHEMA_12 = SCHEMA_11;
        
        public static final Schema[] SCHEMAS = new Schema[] {
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            SCHEMA_10,
            SCHEMA_11,
            SCHEMA_12
        };
        
        public static final short LOWEST_SUPPORTED_VERSION = 10;
        public static final short HIGHEST_SUPPORTED_VERSION = 12;
        
        public LeaderIdAndEpoch(Readable _readable, short _version) {
            read(_readable, _version);
        }
        
        public LeaderIdAndEpoch() {
            this.leaderId = -1;
            this.leaderEpoch = -1;
        }
        
        
        @Override
        public short lowestSupportedVersion() {
            return 3;
        }
        
        @Override
        public short highestSupportedVersion() {
            return 12;
        }
        
        @Override
        public final void read(Readable _readable, short _version) {
            if (_version > 12) {
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
            if (_version < 10) {
                throw new UnsupportedVersionException("Can't write version " + _version + " of LeaderIdAndEpoch");
            }
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
            if (_version > 12) {
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
    
    public static class TopicProduceResponseCollection extends ImplicitLinkedHashMultiCollection<TopicProduceResponse> {
        public TopicProduceResponseCollection() {
            super();
        }
        
        public TopicProduceResponseCollection(int expectedNumElements) {
            super(expectedNumElements);
        }
        
        public TopicProduceResponseCollection(Iterator<TopicProduceResponse> iterator) {
            super(iterator);
        }
        
        public TopicProduceResponse find(String name) {
            TopicProduceResponse _key = new TopicProduceResponse();
            _key.setName(name);
            return find(_key);
        }
        
        public List<TopicProduceResponse> findAll(String name) {
            TopicProduceResponse _key = new TopicProduceResponse();
            _key.setName(name);
            return findAll(_key);
        }
        
        public TopicProduceResponseCollection duplicate() {
            TopicProduceResponseCollection _duplicate = new TopicProduceResponseCollection(size());
            for (TopicProduceResponse _element : this) {
                _duplicate.add(_element.duplicate());
            }
            return _duplicate;
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
        
        public static final Schema SCHEMA_10 =
            new Schema(
                new Field("node_id", Type.INT32, "The ID of the associated node."),
                new Field("host", Type.COMPACT_STRING, "The node's hostname."),
                new Field("port", Type.INT32, "The node's port."),
                new Field("rack", Type.COMPACT_NULLABLE_STRING, "The rack of the node, or null if it has not been assigned to a rack."),
                TaggedFieldsSection.of(
                )
            );
        
        public static final Schema SCHEMA_11 = SCHEMA_10;
        
        public static final Schema SCHEMA_12 = SCHEMA_11;
        
        public static final Schema[] SCHEMAS = new Schema[] {
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            SCHEMA_10,
            SCHEMA_11,
            SCHEMA_12
        };
        
        public static final short LOWEST_SUPPORTED_VERSION = 10;
        public static final short HIGHEST_SUPPORTED_VERSION = 12;
        
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
            return 3;
        }
        
        @Override
        public short highestSupportedVersion() {
            return 12;
        }
        
        @Override
        public final void read(Readable _readable, short _version) {
            if (_version > 12) {
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
            if (_version < 10) {
                throw new UnsupportedVersionException("Can't write version " + _version + " of NodeEndpoint");
            }
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
            if (_version > 12) {
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
