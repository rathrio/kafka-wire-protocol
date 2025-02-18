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

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.LongNode;
import com.fasterxml.jackson.databind.node.NullNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.ShortNode;
import com.fasterxml.jackson.databind.node.TextNode;
import java.util.ArrayList;
import org.apache.kafka.common.Uuid;
import org.apache.kafka.common.protocol.MessageUtil;

import static org.apache.kafka.common.message.ShareAcknowledgeRequestData.*;

public class ShareAcknowledgeRequestDataJsonConverter {
    public static ShareAcknowledgeRequestData read(JsonNode _node, short _version) {
        ShareAcknowledgeRequestData _object = new ShareAcknowledgeRequestData();
        JsonNode _groupIdNode = _node.get("groupId");
        if (_groupIdNode == null) {
            throw new RuntimeException("ShareAcknowledgeRequestData: unable to locate field 'groupId', which is mandatory in version " + _version);
        } else {
            if (_groupIdNode.isNull()) {
                _object.groupId = null;
            } else {
                if (!_groupIdNode.isTextual()) {
                    throw new RuntimeException("ShareAcknowledgeRequestData expected a string type, but got " + _node.getNodeType());
                }
                _object.groupId = _groupIdNode.asText();
            }
        }
        JsonNode _memberIdNode = _node.get("memberId");
        if (_memberIdNode == null) {
            throw new RuntimeException("ShareAcknowledgeRequestData: unable to locate field 'memberId', which is mandatory in version " + _version);
        } else {
            if (_memberIdNode.isNull()) {
                _object.memberId = null;
            } else {
                if (!_memberIdNode.isTextual()) {
                    throw new RuntimeException("ShareAcknowledgeRequestData expected a string type, but got " + _node.getNodeType());
                }
                _object.memberId = _memberIdNode.asText();
            }
        }
        JsonNode _shareSessionEpochNode = _node.get("shareSessionEpoch");
        if (_shareSessionEpochNode == null) {
            throw new RuntimeException("ShareAcknowledgeRequestData: unable to locate field 'shareSessionEpoch', which is mandatory in version " + _version);
        } else {
            _object.shareSessionEpoch = MessageUtil.jsonNodeToInt(_shareSessionEpochNode, "ShareAcknowledgeRequestData");
        }
        JsonNode _topicsNode = _node.get("topics");
        if (_topicsNode == null) {
            throw new RuntimeException("ShareAcknowledgeRequestData: unable to locate field 'topics', which is mandatory in version " + _version);
        } else {
            if (!_topicsNode.isArray()) {
                throw new RuntimeException("ShareAcknowledgeRequestData expected a JSON array, but got " + _node.getNodeType());
            }
            ArrayList<AcknowledgeTopic> _collection = new ArrayList<AcknowledgeTopic>(_topicsNode.size());
            _object.topics = _collection;
            for (JsonNode _element : _topicsNode) {
                _collection.add(AcknowledgeTopicJsonConverter.read(_element, _version));
            }
        }
        return _object;
    }
    public static JsonNode write(ShareAcknowledgeRequestData _object, short _version, boolean _serializeRecords) {
        ObjectNode _node = new ObjectNode(JsonNodeFactory.instance);
        if (_object.groupId == null) {
            _node.set("groupId", NullNode.instance);
        } else {
            _node.set("groupId", new TextNode(_object.groupId));
        }
        if (_object.memberId == null) {
            _node.set("memberId", NullNode.instance);
        } else {
            _node.set("memberId", new TextNode(_object.memberId));
        }
        _node.set("shareSessionEpoch", new IntNode(_object.shareSessionEpoch));
        ArrayNode _topicsArray = new ArrayNode(JsonNodeFactory.instance);
        for (AcknowledgeTopic _element : _object.topics) {
            _topicsArray.add(AcknowledgeTopicJsonConverter.write(_element, _version, _serializeRecords));
        }
        _node.set("topics", _topicsArray);
        return _node;
    }
    public static JsonNode write(ShareAcknowledgeRequestData _object, short _version) {
        return write(_object, _version, true);
    }
    
    public static class AcknowledgePartitionJsonConverter {
        public static AcknowledgePartition read(JsonNode _node, short _version) {
            AcknowledgePartition _object = new AcknowledgePartition();
            JsonNode _partitionIndexNode = _node.get("partitionIndex");
            if (_partitionIndexNode == null) {
                throw new RuntimeException("AcknowledgePartition: unable to locate field 'partitionIndex', which is mandatory in version " + _version);
            } else {
                _object.partitionIndex = MessageUtil.jsonNodeToInt(_partitionIndexNode, "AcknowledgePartition");
            }
            JsonNode _acknowledgementBatchesNode = _node.get("acknowledgementBatches");
            if (_acknowledgementBatchesNode == null) {
                throw new RuntimeException("AcknowledgePartition: unable to locate field 'acknowledgementBatches', which is mandatory in version " + _version);
            } else {
                if (!_acknowledgementBatchesNode.isArray()) {
                    throw new RuntimeException("AcknowledgePartition expected a JSON array, but got " + _node.getNodeType());
                }
                ArrayList<AcknowledgementBatch> _collection = new ArrayList<AcknowledgementBatch>(_acknowledgementBatchesNode.size());
                _object.acknowledgementBatches = _collection;
                for (JsonNode _element : _acknowledgementBatchesNode) {
                    _collection.add(AcknowledgementBatchJsonConverter.read(_element, _version));
                }
            }
            return _object;
        }
        public static JsonNode write(AcknowledgePartition _object, short _version, boolean _serializeRecords) {
            ObjectNode _node = new ObjectNode(JsonNodeFactory.instance);
            _node.set("partitionIndex", new IntNode(_object.partitionIndex));
            ArrayNode _acknowledgementBatchesArray = new ArrayNode(JsonNodeFactory.instance);
            for (AcknowledgementBatch _element : _object.acknowledgementBatches) {
                _acknowledgementBatchesArray.add(AcknowledgementBatchJsonConverter.write(_element, _version, _serializeRecords));
            }
            _node.set("acknowledgementBatches", _acknowledgementBatchesArray);
            return _node;
        }
        public static JsonNode write(AcknowledgePartition _object, short _version) {
            return write(_object, _version, true);
        }
    }
    
    public static class AcknowledgeTopicJsonConverter {
        public static AcknowledgeTopic read(JsonNode _node, short _version) {
            AcknowledgeTopic _object = new AcknowledgeTopic();
            JsonNode _topicIdNode = _node.get("topicId");
            if (_topicIdNode == null) {
                throw new RuntimeException("AcknowledgeTopic: unable to locate field 'topicId', which is mandatory in version " + _version);
            } else {
                if (!_topicIdNode.isTextual()) {
                    throw new RuntimeException("AcknowledgeTopic expected a JSON string type, but got " + _node.getNodeType());
                }
                _object.topicId = Uuid.fromString(_topicIdNode.asText());
            }
            JsonNode _partitionsNode = _node.get("partitions");
            if (_partitionsNode == null) {
                throw new RuntimeException("AcknowledgeTopic: unable to locate field 'partitions', which is mandatory in version " + _version);
            } else {
                if (!_partitionsNode.isArray()) {
                    throw new RuntimeException("AcknowledgeTopic expected a JSON array, but got " + _node.getNodeType());
                }
                ArrayList<AcknowledgePartition> _collection = new ArrayList<AcknowledgePartition>(_partitionsNode.size());
                _object.partitions = _collection;
                for (JsonNode _element : _partitionsNode) {
                    _collection.add(AcknowledgePartitionJsonConverter.read(_element, _version));
                }
            }
            return _object;
        }
        public static JsonNode write(AcknowledgeTopic _object, short _version, boolean _serializeRecords) {
            ObjectNode _node = new ObjectNode(JsonNodeFactory.instance);
            _node.set("topicId", new TextNode(_object.topicId.toString()));
            ArrayNode _partitionsArray = new ArrayNode(JsonNodeFactory.instance);
            for (AcknowledgePartition _element : _object.partitions) {
                _partitionsArray.add(AcknowledgePartitionJsonConverter.write(_element, _version, _serializeRecords));
            }
            _node.set("partitions", _partitionsArray);
            return _node;
        }
        public static JsonNode write(AcknowledgeTopic _object, short _version) {
            return write(_object, _version, true);
        }
    }
    
    public static class AcknowledgementBatchJsonConverter {
        public static AcknowledgementBatch read(JsonNode _node, short _version) {
            AcknowledgementBatch _object = new AcknowledgementBatch();
            JsonNode _firstOffsetNode = _node.get("firstOffset");
            if (_firstOffsetNode == null) {
                throw new RuntimeException("AcknowledgementBatch: unable to locate field 'firstOffset', which is mandatory in version " + _version);
            } else {
                _object.firstOffset = MessageUtil.jsonNodeToLong(_firstOffsetNode, "AcknowledgementBatch");
            }
            JsonNode _lastOffsetNode = _node.get("lastOffset");
            if (_lastOffsetNode == null) {
                throw new RuntimeException("AcknowledgementBatch: unable to locate field 'lastOffset', which is mandatory in version " + _version);
            } else {
                _object.lastOffset = MessageUtil.jsonNodeToLong(_lastOffsetNode, "AcknowledgementBatch");
            }
            JsonNode _acknowledgeTypesNode = _node.get("acknowledgeTypes");
            if (_acknowledgeTypesNode == null) {
                throw new RuntimeException("AcknowledgementBatch: unable to locate field 'acknowledgeTypes', which is mandatory in version " + _version);
            } else {
                if (!_acknowledgeTypesNode.isArray()) {
                    throw new RuntimeException("AcknowledgementBatch expected a JSON array, but got " + _node.getNodeType());
                }
                ArrayList<Byte> _collection = new ArrayList<Byte>(_acknowledgeTypesNode.size());
                _object.acknowledgeTypes = _collection;
                for (JsonNode _element : _acknowledgeTypesNode) {
                    _collection.add(MessageUtil.jsonNodeToByte(_element, "AcknowledgementBatch element"));
                }
            }
            return _object;
        }
        public static JsonNode write(AcknowledgementBatch _object, short _version, boolean _serializeRecords) {
            ObjectNode _node = new ObjectNode(JsonNodeFactory.instance);
            _node.set("firstOffset", new LongNode(_object.firstOffset));
            _node.set("lastOffset", new LongNode(_object.lastOffset));
            ArrayNode _acknowledgeTypesArray = new ArrayNode(JsonNodeFactory.instance);
            for (Byte _element : _object.acknowledgeTypes) {
                _acknowledgeTypesArray.add(new ShortNode(_element));
            }
            _node.set("acknowledgeTypes", _acknowledgeTypesArray);
            return _node;
        }
        public static JsonNode write(AcknowledgementBatch _object, short _version) {
            return write(_object, _version, true);
        }
    }
}
