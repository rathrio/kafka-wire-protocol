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
import java.util.Arrays;
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
import org.apache.kafka.common.utils.Bytes;

import static org.apache.kafka.common.protocol.types.Field.TaggedFieldsSection;


public class RenewDelegationTokenRequestData implements ApiMessage {
    byte[] hmac;
    long renewPeriodMs;
    private List<RawTaggedField> _unknownTaggedFields;
    
    public static final Schema SCHEMA_1 =
        new Schema(
            new Field("hmac", Type.BYTES, "The HMAC of the delegation token to be renewed."),
            new Field("renew_period_ms", Type.INT64, "The renewal time period in milliseconds.")
        );
    
    public static final Schema SCHEMA_2 =
        new Schema(
            new Field("hmac", Type.COMPACT_BYTES, "The HMAC of the delegation token to be renewed."),
            new Field("renew_period_ms", Type.INT64, "The renewal time period in milliseconds."),
            TaggedFieldsSection.of(
            )
        );
    
    public static final Schema[] SCHEMAS = new Schema[] {
        null,
        SCHEMA_1,
        SCHEMA_2
    };
    
    public static final short LOWEST_SUPPORTED_VERSION = 1;
    public static final short HIGHEST_SUPPORTED_VERSION = 2;
    
    public RenewDelegationTokenRequestData(Readable _readable, short _version) {
        read(_readable, _version);
    }
    
    public RenewDelegationTokenRequestData() {
        this.hmac = Bytes.EMPTY;
        this.renewPeriodMs = 0L;
    }
    
    @Override
    public short apiKey() {
        return 39;
    }
    
    @Override
    public short lowestSupportedVersion() {
        return 1;
    }
    
    @Override
    public short highestSupportedVersion() {
        return 2;
    }
    
    @Override
    public final void read(Readable _readable, short _version) {
        {
            int length;
            if (_version >= 2) {
                length = _readable.readUnsignedVarint() - 1;
            } else {
                length = _readable.readInt();
            }
            if (length < 0) {
                throw new RuntimeException("non-nullable field hmac was serialized as null");
            } else {
                byte[] newBytes = _readable.readArray(length);
                this.hmac = newBytes;
            }
        }
        this.renewPeriodMs = _readable.readLong();
        this._unknownTaggedFields = null;
        if (_version >= 2) {
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
        if (_version >= 2) {
            _writable.writeUnsignedVarint(hmac.length + 1);
        } else {
            _writable.writeInt(hmac.length);
        }
        _writable.writeByteArray(hmac);
        _writable.writeLong(renewPeriodMs);
        RawTaggedFieldWriter _rawWriter = RawTaggedFieldWriter.forFields(_unknownTaggedFields);
        _numTaggedFields += _rawWriter.numFields();
        if (_version >= 2) {
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
            _size.addBytes(hmac.length);
            if (_version >= 2) {
                _size.addBytes(ByteUtils.sizeOfUnsignedVarint(hmac.length + 1));
            } else {
                _size.addBytes(4);
            }
        }
        _size.addBytes(8);
        if (_unknownTaggedFields != null) {
            _numTaggedFields += _unknownTaggedFields.size();
            for (RawTaggedField _field : _unknownTaggedFields) {
                _size.addBytes(ByteUtils.sizeOfUnsignedVarint(_field.tag()));
                _size.addBytes(ByteUtils.sizeOfUnsignedVarint(_field.size()));
                _size.addBytes(_field.size());
            }
        }
        if (_version >= 2) {
            _size.addBytes(ByteUtils.sizeOfUnsignedVarint(_numTaggedFields));
        } else {
            if (_numTaggedFields > 0) {
                throw new UnsupportedVersionException("Tagged fields were set, but version " + _version + " of this message does not support them.");
            }
        }
    }
    
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof RenewDelegationTokenRequestData)) return false;
        RenewDelegationTokenRequestData other = (RenewDelegationTokenRequestData) obj;
        if (!Arrays.equals(this.hmac, other.hmac)) return false;
        if (renewPeriodMs != other.renewPeriodMs) return false;
        return MessageUtil.compareRawTaggedFields(_unknownTaggedFields, other._unknownTaggedFields);
    }
    
    @Override
    public int hashCode() {
        int hashCode = 0;
        hashCode = 31 * hashCode + Arrays.hashCode(hmac);
        hashCode = 31 * hashCode + ((int) (renewPeriodMs >> 32) ^ (int) renewPeriodMs);
        return hashCode;
    }
    
    @Override
    public RenewDelegationTokenRequestData duplicate() {
        RenewDelegationTokenRequestData _duplicate = new RenewDelegationTokenRequestData();
        _duplicate.hmac = MessageUtil.duplicate(hmac);
        _duplicate.renewPeriodMs = renewPeriodMs;
        return _duplicate;
    }
    
    @Override
    public String toString() {
        return "RenewDelegationTokenRequestData("
            + "hmac=" + Arrays.toString(hmac)
            + ", renewPeriodMs=" + renewPeriodMs
            + ")";
    }
    
    public byte[] hmac() {
        return this.hmac;
    }
    
    public long renewPeriodMs() {
        return this.renewPeriodMs;
    }
    
    @Override
    public List<RawTaggedField> unknownTaggedFields() {
        if (_unknownTaggedFields == null) {
            _unknownTaggedFields = new ArrayList<>(0);
        }
        return _unknownTaggedFields;
    }
    
    public RenewDelegationTokenRequestData setHmac(byte[] v) {
        this.hmac = v;
        return this;
    }
    
    public RenewDelegationTokenRequestData setRenewPeriodMs(long v) {
        this.renewPeriodMs = v;
        return this;
    }
}
