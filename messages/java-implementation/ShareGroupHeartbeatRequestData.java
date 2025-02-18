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


public class ShareGroupHeartbeatRequestData implements ApiMessage {
    String groupId;
    String memberId;
    int memberEpoch;
    String rackId;
    List<String> subscribedTopicNames;
    private List<RawTaggedField> _unknownTaggedFields;
    
    public static final Schema SCHEMA_0 =
        new Schema(
            new Field("group_id", Type.COMPACT_STRING, "The group identifier."),
            new Field("member_id", Type.COMPACT_STRING, "The member id."),
            new Field("member_epoch", Type.INT32, "The current member epoch; 0 to join the group; -1 to leave the group."),
            new Field("rack_id", Type.COMPACT_NULLABLE_STRING, "null if not provided or if it didn't change since the last heartbeat; the rack ID of consumer otherwise."),
            new Field("subscribed_topic_names", CompactArrayOf.nullable(Type.COMPACT_STRING), "null if it didn't change since the last heartbeat; the subscribed topic names otherwise."),
            TaggedFieldsSection.of(
            )
        );
    
    public static final Schema[] SCHEMAS = new Schema[] {
        SCHEMA_0
    };
    
    public static final short LOWEST_SUPPORTED_VERSION = 0;
    public static final short HIGHEST_SUPPORTED_VERSION = 0;
    
    public ShareGroupHeartbeatRequestData(Readable _readable, short _version) {
        read(_readable, _version);
    }
    
    public ShareGroupHeartbeatRequestData() {
        this.groupId = "";
        this.memberId = "";
        this.memberEpoch = 0;
        this.rackId = null;
        this.subscribedTopicNames = null;
    }
    
    @Override
    public short apiKey() {
        return 76;
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
                throw new RuntimeException("non-nullable field groupId was serialized as null");
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
                throw new RuntimeException("non-nullable field memberId was serialized as null");
            } else if (length > 0x7fff) {
                throw new RuntimeException("string field memberId had invalid length " + length);
            } else {
                this.memberId = _readable.readString(length);
            }
        }
        this.memberEpoch = _readable.readInt();
        {
            int length;
            length = _readable.readUnsignedVarint() - 1;
            if (length < 0) {
                this.rackId = null;
            } else if (length > 0x7fff) {
                throw new RuntimeException("string field rackId had invalid length " + length);
            } else {
                this.rackId = _readable.readString(length);
            }
        }
        {
            int arrayLength;
            arrayLength = _readable.readUnsignedVarint() - 1;
            if (arrayLength < 0) {
                this.subscribedTopicNames = null;
            } else {
                if (arrayLength > _readable.remaining()) {
                    throw new RuntimeException("Tried to allocate a collection of size " + arrayLength + ", but there are only " + _readable.remaining() + " bytes remaining.");
                }
                ArrayList<String> newCollection = new ArrayList<>(arrayLength);
                for (int i = 0; i < arrayLength; i++) {
                    int length;
                    length = _readable.readUnsignedVarint() - 1;
                    if (length < 0) {
                        throw new RuntimeException("non-nullable field subscribedTopicNames element was serialized as null");
                    } else if (length > 0x7fff) {
                        throw new RuntimeException("string field subscribedTopicNames element had invalid length " + length);
                    } else {
                        newCollection.add(_readable.readString(length));
                    }
                }
                this.subscribedTopicNames = newCollection;
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
        {
            byte[] _stringBytes = _cache.getSerializedValue(groupId);
            _writable.writeUnsignedVarint(_stringBytes.length + 1);
            _writable.writeByteArray(_stringBytes);
        }
        {
            byte[] _stringBytes = _cache.getSerializedValue(memberId);
            _writable.writeUnsignedVarint(_stringBytes.length + 1);
            _writable.writeByteArray(_stringBytes);
        }
        _writable.writeInt(memberEpoch);
        if (rackId == null) {
            _writable.writeUnsignedVarint(0);
        } else {
            byte[] _stringBytes = _cache.getSerializedValue(rackId);
            _writable.writeUnsignedVarint(_stringBytes.length + 1);
            _writable.writeByteArray(_stringBytes);
        }
        if (subscribedTopicNames == null) {
            _writable.writeUnsignedVarint(0);
        } else {
            _writable.writeUnsignedVarint(subscribedTopicNames.size() + 1);
            for (String subscribedTopicNamesElement : subscribedTopicNames) {
                {
                    byte[] _stringBytes = _cache.getSerializedValue(subscribedTopicNamesElement);
                    _writable.writeUnsignedVarint(_stringBytes.length + 1);
                    _writable.writeByteArray(_stringBytes);
                }
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
        {
            byte[] _stringBytes = groupId.getBytes(StandardCharsets.UTF_8);
            if (_stringBytes.length > 0x7fff) {
                throw new RuntimeException("'groupId' field is too long to be serialized");
            }
            _cache.cacheSerializedValue(groupId, _stringBytes);
            _size.addBytes(_stringBytes.length + ByteUtils.sizeOfUnsignedVarint(_stringBytes.length + 1));
        }
        {
            byte[] _stringBytes = memberId.getBytes(StandardCharsets.UTF_8);
            if (_stringBytes.length > 0x7fff) {
                throw new RuntimeException("'memberId' field is too long to be serialized");
            }
            _cache.cacheSerializedValue(memberId, _stringBytes);
            _size.addBytes(_stringBytes.length + ByteUtils.sizeOfUnsignedVarint(_stringBytes.length + 1));
        }
        _size.addBytes(4);
        if (rackId == null) {
            _size.addBytes(1);
        } else {
            byte[] _stringBytes = rackId.getBytes(StandardCharsets.UTF_8);
            if (_stringBytes.length > 0x7fff) {
                throw new RuntimeException("'rackId' field is too long to be serialized");
            }
            _cache.cacheSerializedValue(rackId, _stringBytes);
            _size.addBytes(_stringBytes.length + ByteUtils.sizeOfUnsignedVarint(_stringBytes.length + 1));
        }
        if (subscribedTopicNames == null) {
            _size.addBytes(1);
        } else {
            _size.addBytes(ByteUtils.sizeOfUnsignedVarint(subscribedTopicNames.size() + 1));
            for (String subscribedTopicNamesElement : subscribedTopicNames) {
                byte[] _stringBytes = subscribedTopicNamesElement.getBytes(StandardCharsets.UTF_8);
                if (_stringBytes.length > 0x7fff) {
                    throw new RuntimeException("'subscribedTopicNamesElement' field is too long to be serialized");
                }
                _cache.cacheSerializedValue(subscribedTopicNamesElement, _stringBytes);
                _size.addBytes(_stringBytes.length + ByteUtils.sizeOfUnsignedVarint(_stringBytes.length + 1));
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
        if (!(obj instanceof ShareGroupHeartbeatRequestData)) return false;
        ShareGroupHeartbeatRequestData other = (ShareGroupHeartbeatRequestData) obj;
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
        if (memberEpoch != other.memberEpoch) return false;
        if (this.rackId == null) {
            if (other.rackId != null) return false;
        } else {
            if (!this.rackId.equals(other.rackId)) return false;
        }
        if (this.subscribedTopicNames == null) {
            if (other.subscribedTopicNames != null) return false;
        } else {
            if (!this.subscribedTopicNames.equals(other.subscribedTopicNames)) return false;
        }
        return MessageUtil.compareRawTaggedFields(_unknownTaggedFields, other._unknownTaggedFields);
    }
    
    @Override
    public int hashCode() {
        int hashCode = 0;
        hashCode = 31 * hashCode + (groupId == null ? 0 : groupId.hashCode());
        hashCode = 31 * hashCode + (memberId == null ? 0 : memberId.hashCode());
        hashCode = 31 * hashCode + memberEpoch;
        hashCode = 31 * hashCode + (rackId == null ? 0 : rackId.hashCode());
        hashCode = 31 * hashCode + (subscribedTopicNames == null ? 0 : subscribedTopicNames.hashCode());
        return hashCode;
    }
    
    @Override
    public ShareGroupHeartbeatRequestData duplicate() {
        ShareGroupHeartbeatRequestData _duplicate = new ShareGroupHeartbeatRequestData();
        _duplicate.groupId = groupId;
        _duplicate.memberId = memberId;
        _duplicate.memberEpoch = memberEpoch;
        if (rackId == null) {
            _duplicate.rackId = null;
        } else {
            _duplicate.rackId = rackId;
        }
        if (subscribedTopicNames == null) {
            _duplicate.subscribedTopicNames = null;
        } else {
            ArrayList<String> newSubscribedTopicNames = new ArrayList<String>(subscribedTopicNames.size());
            for (String _element : subscribedTopicNames) {
                newSubscribedTopicNames.add(_element);
            }
            _duplicate.subscribedTopicNames = newSubscribedTopicNames;
        }
        return _duplicate;
    }
    
    @Override
    public String toString() {
        return "ShareGroupHeartbeatRequestData("
            + "groupId=" + ((groupId == null) ? "null" : "'" + groupId.toString() + "'")
            + ", memberId=" + ((memberId == null) ? "null" : "'" + memberId.toString() + "'")
            + ", memberEpoch=" + memberEpoch
            + ", rackId=" + ((rackId == null) ? "null" : "'" + rackId.toString() + "'")
            + ", subscribedTopicNames=" + ((subscribedTopicNames == null) ? "null" : MessageUtil.deepToString(subscribedTopicNames.iterator()))
            + ")";
    }
    
    public String groupId() {
        return this.groupId;
    }
    
    public String memberId() {
        return this.memberId;
    }
    
    public int memberEpoch() {
        return this.memberEpoch;
    }
    
    public String rackId() {
        return this.rackId;
    }
    
    public List<String> subscribedTopicNames() {
        return this.subscribedTopicNames;
    }
    
    @Override
    public List<RawTaggedField> unknownTaggedFields() {
        if (_unknownTaggedFields == null) {
            _unknownTaggedFields = new ArrayList<>(0);
        }
        return _unknownTaggedFields;
    }
    
    public ShareGroupHeartbeatRequestData setGroupId(String v) {
        this.groupId = v;
        return this;
    }
    
    public ShareGroupHeartbeatRequestData setMemberId(String v) {
        this.memberId = v;
        return this;
    }
    
    public ShareGroupHeartbeatRequestData setMemberEpoch(int v) {
        this.memberEpoch = v;
        return this;
    }
    
    public ShareGroupHeartbeatRequestData setRackId(String v) {
        this.rackId = v;
        return this;
    }
    
    public ShareGroupHeartbeatRequestData setSubscribedTopicNames(List<String> v) {
        this.subscribedTopicNames = v;
        return this;
    }
}
