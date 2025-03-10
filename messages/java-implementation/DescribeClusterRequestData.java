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

import java.util.ArrayList;
import java.util.List;
import org.apache.kafka.common.errors.UnsupportedVersionException;
import org.apache.kafka.common.protocol.ApiMessage;
import org.apache.kafka.common.protocol.MessageSizeAccumulator;
import org.apache.kafka.common.protocol.MessageUtil;
import org.apache.kafka.common.protocol.ObjectSerializationCache;
import org.apache.kafka.common.protocol.Readable;
import org.apache.kafka.common.protocol.Writable;
import org.apache.kafka.common.protocol.types.Field;
import org.apache.kafka.common.protocol.types.RawTaggedField;
import org.apache.kafka.common.protocol.types.RawTaggedFieldWriter;
import org.apache.kafka.common.protocol.types.Schema;
import org.apache.kafka.common.protocol.types.Type;
import org.apache.kafka.common.utils.ByteUtils;

import static org.apache.kafka.common.protocol.types.Field.TaggedFieldsSection;


public class DescribeClusterRequestData implements ApiMessage {
    boolean includeClusterAuthorizedOperations;
    byte endpointType;
    boolean includeFencedBrokers;
    private List<RawTaggedField> _unknownTaggedFields;
    
    public static final Schema SCHEMA_0 =
        new Schema(
            new Field("include_cluster_authorized_operations", Type.BOOLEAN, "Whether to include cluster authorized operations."),
            TaggedFieldsSection.of(
            )
        );
    
    public static final Schema SCHEMA_1 =
        new Schema(
            new Field("include_cluster_authorized_operations", Type.BOOLEAN, "Whether to include cluster authorized operations."),
            new Field("endpoint_type", Type.INT8, "The endpoint type to describe. 1=brokers, 2=controllers."),
            TaggedFieldsSection.of(
            )
        );
    
    public static final Schema SCHEMA_2 =
        new Schema(
            new Field("include_cluster_authorized_operations", Type.BOOLEAN, "Whether to include cluster authorized operations."),
            new Field("endpoint_type", Type.INT8, "The endpoint type to describe. 1=brokers, 2=controllers."),
            new Field("include_fenced_brokers", Type.BOOLEAN, "Whether to include fenced brokers when listing brokers."),
            TaggedFieldsSection.of(
            )
        );
    
    public static final Schema[] SCHEMAS = new Schema[] {
        SCHEMA_0,
        SCHEMA_1,
        SCHEMA_2
    };
    
    public static final short LOWEST_SUPPORTED_VERSION = 0;
    public static final short HIGHEST_SUPPORTED_VERSION = 2;
    
    public DescribeClusterRequestData(Readable _readable, short _version) {
        read(_readable, _version);
    }
    
    public DescribeClusterRequestData() {
        this.includeClusterAuthorizedOperations = false;
        this.endpointType = (byte) 1;
        this.includeFencedBrokers = false;
    }
    
    @Override
    public short apiKey() {
        return 60;
    }
    
    @Override
    public short lowestSupportedVersion() {
        return 0;
    }
    
    @Override
    public short highestSupportedVersion() {
        return 2;
    }
    
    @Override
    public final void read(Readable _readable, short _version) {
        this.includeClusterAuthorizedOperations = _readable.readByte() != 0;
        if (_version >= 1) {
            this.endpointType = _readable.readByte();
        } else {
            this.endpointType = (byte) 1;
        }
        if (_version >= 2) {
            this.includeFencedBrokers = _readable.readByte() != 0;
        } else {
            this.includeFencedBrokers = false;
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
        _writable.writeByte(includeClusterAuthorizedOperations ? (byte) 1 : (byte) 0);
        if (_version >= 1) {
            _writable.writeByte(endpointType);
        } else {
            if (this.endpointType != (byte) 1) {
                throw new UnsupportedVersionException("Attempted to write a non-default endpointType at version " + _version);
            }
        }
        if (_version >= 2) {
            _writable.writeByte(includeFencedBrokers ? (byte) 1 : (byte) 0);
        } else {
            if (this.includeFencedBrokers) {
                throw new UnsupportedVersionException("Attempted to write a non-default includeFencedBrokers at version " + _version);
            }
        }
        RawTaggedFieldWriter _rawWriter = RawTaggedFieldWriter.forFields(_unknownTaggedFields);
        _numTaggedFields += _rawWriter.numFields();
        _writable.writeUnsignedVarint(_numTaggedFields);
        _rawWriter.writeRawTags(_writable, Integer.MAX_VALUE);
    }
    
    @Override
    public void addSize(MessageSizeAccumulator _size, ObjectSerializationCache _cache, short _version) {
        int _numTaggedFields = 0;
        _size.addBytes(1);
        if (_version >= 1) {
            _size.addBytes(1);
        }
        if (_version >= 2) {
            _size.addBytes(1);
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
        if (!(obj instanceof DescribeClusterRequestData)) return false;
        DescribeClusterRequestData other = (DescribeClusterRequestData) obj;
        if (includeClusterAuthorizedOperations != other.includeClusterAuthorizedOperations) return false;
        if (endpointType != other.endpointType) return false;
        if (includeFencedBrokers != other.includeFencedBrokers) return false;
        return MessageUtil.compareRawTaggedFields(_unknownTaggedFields, other._unknownTaggedFields);
    }
    
    @Override
    public int hashCode() {
        int hashCode = 0;
        hashCode = 31 * hashCode + (includeClusterAuthorizedOperations ? 1231 : 1237);
        hashCode = 31 * hashCode + endpointType;
        hashCode = 31 * hashCode + (includeFencedBrokers ? 1231 : 1237);
        return hashCode;
    }
    
    @Override
    public DescribeClusterRequestData duplicate() {
        DescribeClusterRequestData _duplicate = new DescribeClusterRequestData();
        _duplicate.includeClusterAuthorizedOperations = includeClusterAuthorizedOperations;
        _duplicate.endpointType = endpointType;
        _duplicate.includeFencedBrokers = includeFencedBrokers;
        return _duplicate;
    }
    
    @Override
    public String toString() {
        return "DescribeClusterRequestData("
            + "includeClusterAuthorizedOperations=" + (includeClusterAuthorizedOperations ? "true" : "false")
            + ", endpointType=" + endpointType
            + ", includeFencedBrokers=" + (includeFencedBrokers ? "true" : "false")
            + ")";
    }
    
    public boolean includeClusterAuthorizedOperations() {
        return this.includeClusterAuthorizedOperations;
    }
    
    public byte endpointType() {
        return this.endpointType;
    }
    
    public boolean includeFencedBrokers() {
        return this.includeFencedBrokers;
    }
    
    @Override
    public List<RawTaggedField> unknownTaggedFields() {
        if (_unknownTaggedFields == null) {
            _unknownTaggedFields = new ArrayList<>(0);
        }
        return _unknownTaggedFields;
    }
    
    public DescribeClusterRequestData setIncludeClusterAuthorizedOperations(boolean v) {
        this.includeClusterAuthorizedOperations = v;
        return this;
    }
    
    public DescribeClusterRequestData setEndpointType(byte v) {
        this.endpointType = v;
        return this;
    }
    
    public DescribeClusterRequestData setIncludeFencedBrokers(boolean v) {
        this.includeFencedBrokers = v;
        return this;
    }
}
