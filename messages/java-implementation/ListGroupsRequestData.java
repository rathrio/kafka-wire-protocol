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
import org.apache.kafka.common.errors.UnsupportedVersionException;
import org.apache.kafka.common.protocol.ApiMessage;
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


public class ListGroupsRequestData implements ApiMessage {
    List<String> statesFilter;
    List<String> typesFilter;
    private List<RawTaggedField> _unknownTaggedFields;
    
    public static final Schema SCHEMA_0 =
        new Schema(
        );
    
    public static final Schema SCHEMA_1 = SCHEMA_0;
    
    public static final Schema SCHEMA_2 = SCHEMA_1;
    
    public static final Schema SCHEMA_3 =
        new Schema(
            TaggedFieldsSection.of(
            )
        );
    
    public static final Schema SCHEMA_4 =
        new Schema(
            new Field("states_filter", new CompactArrayOf(Type.COMPACT_STRING), "The states of the groups we want to list. If empty, all groups are returned with their state."),
            TaggedFieldsSection.of(
            )
        );
    
    public static final Schema SCHEMA_5 =
        new Schema(
            new Field("states_filter", new CompactArrayOf(Type.COMPACT_STRING), "The states of the groups we want to list. If empty, all groups are returned with their state."),
            new Field("types_filter", new CompactArrayOf(Type.COMPACT_STRING), "The types of the groups we want to list. If empty, all groups are returned with their type."),
            TaggedFieldsSection.of(
            )
        );
    
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
    
    public ListGroupsRequestData(Readable _readable, short _version) {
        read(_readable, _version);
    }
    
    public ListGroupsRequestData() {
        this.statesFilter = new ArrayList<String>(0);
        this.typesFilter = new ArrayList<String>(0);
    }
    
    @Override
    public short apiKey() {
        return 16;
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
                throw new RuntimeException("non-nullable field statesFilter was serialized as null");
            } else {
                if (arrayLength > _readable.remaining()) {
                    throw new RuntimeException("Tried to allocate a collection of size " + arrayLength + ", but there are only " + _readable.remaining() + " bytes remaining.");
                }
                ArrayList<String> newCollection = new ArrayList<>(arrayLength);
                for (int i = 0; i < arrayLength; i++) {
                    int length;
                    length = _readable.readUnsignedVarint() - 1;
                    if (length < 0) {
                        throw new RuntimeException("non-nullable field statesFilter element was serialized as null");
                    } else if (length > 0x7fff) {
                        throw new RuntimeException("string field statesFilter element had invalid length " + length);
                    } else {
                        newCollection.add(_readable.readString(length));
                    }
                }
                this.statesFilter = newCollection;
            }
        } else {
            this.statesFilter = new ArrayList<String>(0);
        }
        if (_version >= 5) {
            int arrayLength;
            arrayLength = _readable.readUnsignedVarint() - 1;
            if (arrayLength < 0) {
                throw new RuntimeException("non-nullable field typesFilter was serialized as null");
            } else {
                if (arrayLength > _readable.remaining()) {
                    throw new RuntimeException("Tried to allocate a collection of size " + arrayLength + ", but there are only " + _readable.remaining() + " bytes remaining.");
                }
                ArrayList<String> newCollection = new ArrayList<>(arrayLength);
                for (int i = 0; i < arrayLength; i++) {
                    int length;
                    length = _readable.readUnsignedVarint() - 1;
                    if (length < 0) {
                        throw new RuntimeException("non-nullable field typesFilter element was serialized as null");
                    } else if (length > 0x7fff) {
                        throw new RuntimeException("string field typesFilter element had invalid length " + length);
                    } else {
                        newCollection.add(_readable.readString(length));
                    }
                }
                this.typesFilter = newCollection;
            }
        } else {
            this.typesFilter = new ArrayList<String>(0);
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
            _writable.writeUnsignedVarint(statesFilter.size() + 1);
            for (String statesFilterElement : statesFilter) {
                {
                    byte[] _stringBytes = _cache.getSerializedValue(statesFilterElement);
                    _writable.writeUnsignedVarint(_stringBytes.length + 1);
                    _writable.writeByteArray(_stringBytes);
                }
            }
        } else {
            if (!this.statesFilter.isEmpty()) {
                throw new UnsupportedVersionException("Attempted to write a non-default statesFilter at version " + _version);
            }
        }
        if (_version >= 5) {
            _writable.writeUnsignedVarint(typesFilter.size() + 1);
            for (String typesFilterElement : typesFilter) {
                {
                    byte[] _stringBytes = _cache.getSerializedValue(typesFilterElement);
                    _writable.writeUnsignedVarint(_stringBytes.length + 1);
                    _writable.writeByteArray(_stringBytes);
                }
            }
        } else {
            if (!this.typesFilter.isEmpty()) {
                throw new UnsupportedVersionException("Attempted to write a non-default typesFilter at version " + _version);
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
                _size.addBytes(ByteUtils.sizeOfUnsignedVarint(statesFilter.size() + 1));
                for (String statesFilterElement : statesFilter) {
                    byte[] _stringBytes = statesFilterElement.getBytes(StandardCharsets.UTF_8);
                    if (_stringBytes.length > 0x7fff) {
                        throw new RuntimeException("'statesFilterElement' field is too long to be serialized");
                    }
                    _cache.cacheSerializedValue(statesFilterElement, _stringBytes);
                    _size.addBytes(_stringBytes.length + ByteUtils.sizeOfUnsignedVarint(_stringBytes.length + 1));
                }
            }
        }
        if (_version >= 5) {
            {
                _size.addBytes(ByteUtils.sizeOfUnsignedVarint(typesFilter.size() + 1));
                for (String typesFilterElement : typesFilter) {
                    byte[] _stringBytes = typesFilterElement.getBytes(StandardCharsets.UTF_8);
                    if (_stringBytes.length > 0x7fff) {
                        throw new RuntimeException("'typesFilterElement' field is too long to be serialized");
                    }
                    _cache.cacheSerializedValue(typesFilterElement, _stringBytes);
                    _size.addBytes(_stringBytes.length + ByteUtils.sizeOfUnsignedVarint(_stringBytes.length + 1));
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
        if (!(obj instanceof ListGroupsRequestData)) return false;
        ListGroupsRequestData other = (ListGroupsRequestData) obj;
        if (this.statesFilter == null) {
            if (other.statesFilter != null) return false;
        } else {
            if (!this.statesFilter.equals(other.statesFilter)) return false;
        }
        if (this.typesFilter == null) {
            if (other.typesFilter != null) return false;
        } else {
            if (!this.typesFilter.equals(other.typesFilter)) return false;
        }
        return MessageUtil.compareRawTaggedFields(_unknownTaggedFields, other._unknownTaggedFields);
    }
    
    @Override
    public int hashCode() {
        int hashCode = 0;
        hashCode = 31 * hashCode + (statesFilter == null ? 0 : statesFilter.hashCode());
        hashCode = 31 * hashCode + (typesFilter == null ? 0 : typesFilter.hashCode());
        return hashCode;
    }
    
    @Override
    public ListGroupsRequestData duplicate() {
        ListGroupsRequestData _duplicate = new ListGroupsRequestData();
        ArrayList<String> newStatesFilter = new ArrayList<String>(statesFilter.size());
        for (String _element : statesFilter) {
            newStatesFilter.add(_element);
        }
        _duplicate.statesFilter = newStatesFilter;
        ArrayList<String> newTypesFilter = new ArrayList<String>(typesFilter.size());
        for (String _element : typesFilter) {
            newTypesFilter.add(_element);
        }
        _duplicate.typesFilter = newTypesFilter;
        return _duplicate;
    }
    
    @Override
    public String toString() {
        return "ListGroupsRequestData("
            + "statesFilter=" + MessageUtil.deepToString(statesFilter.iterator())
            + ", typesFilter=" + MessageUtil.deepToString(typesFilter.iterator())
            + ")";
    }
    
    public List<String> statesFilter() {
        return this.statesFilter;
    }
    
    public List<String> typesFilter() {
        return this.typesFilter;
    }
    
    @Override
    public List<RawTaggedField> unknownTaggedFields() {
        if (_unknownTaggedFields == null) {
            _unknownTaggedFields = new ArrayList<>(0);
        }
        return _unknownTaggedFields;
    }
    
    public ListGroupsRequestData setStatesFilter(List<String> v) {
        this.statesFilter = v;
        return this;
    }
    
    public ListGroupsRequestData setTypesFilter(List<String> v) {
        this.typesFilter = v;
        return this;
    }
}
