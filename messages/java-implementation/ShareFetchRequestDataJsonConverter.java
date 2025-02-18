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

import static org.apache.kafka.common.message.ShareFetchRequestData.*;

public class ShareFetchRequestDataJsonConverter {
    public static ShareFetchRequestData read(JsonNode _node, short _version) {
        ShareFetchRequestData _object = new ShareFetchRequestData();
        JsonNode _groupIdNode = _node.get("groupId");
        if (_groupIdNode == null) {
            throw new RuntimeException("ShareFetchRequestData: unable to locate field 'groupId', which is mandatory in version " + _version);
        } else {
            if (_groupIdNode.isNull()) {
                _object.groupId = null;
            } else {
                if (!_groupIdNode.isTextual()) {
                    throw new RuntimeException("ShareFetchRequestData expected a string type, but got " + _node.getNodeType());
                }
                _object.groupId = _groupIdNode.asText();
            }
        }
        JsonNode _memberIdNode = _node.get("memberId");
        if (_memberIdNode == null) {
            throw new RuntimeException("ShareFetchRequestData: unable to locate field 'memberId', which is mandatory in version " + _version);
        } else {
            if (_memberIdNode.isNull()) {
                _object.memberId = null;
            } else {
                if (!_memberIdNode.isTextual()) {
                    throw new RuntimeException("ShareFetchRequestData expected a string type, but got " + _node.getNodeType());
                }
                _object.memberId = _memberIdNode.asText();
            }
        }
        JsonNode _shareSessionEpochNode = _node.get("shareSessionEpoch");
        if (_shareSessionEpochNode == null) {
            throw new RuntimeException("ShareFetchRequestData: unable to locate field 'shareSessionEpoch', which is mandatory in version " + _version);
        } else {
            _object.shareSessionEpoch = MessageUtil.jsonNodeToInt(_shareSessionEpochNode, "ShareFetchRequestData");
        }
        JsonNode _maxWaitMsNode = _node.get("maxWaitMs");
        if (_maxWaitMsNode == null) {
            throw new RuntimeException("ShareFetchRequestData: unable to locate field 'maxWaitMs', which is mandatory in version " + _version);
        } else {
            _object.maxWaitMs = MessageUtil.jsonNodeToInt(_maxWaitMsNode, "ShareFetchRequestData");
        }
        JsonNode _minBytesNode = _node.get("minBytes");
        if (_minBytesNode == null) {
            throw new RuntimeException("ShareFetchRequestData: unable to locate field 'minBytes', which is mandatory in version " + _version);
        } else {
            _object.minBytes = MessageUtil.jsonNodeToInt(_minBytesNode, "ShareFetchRequestData");
        }
        JsonNode _maxBytesNode = _node.get("maxBytes");
        if (_maxBytesNode == null) {
            throw new RuntimeException("ShareFetchRequestData: unable to locate field 'maxBytes', which is mandatory in version " + _version);
        } else {
            _object.maxBytes = MessageUtil.jsonNodeToInt(_maxBytesNode, "ShareFetchRequestData");
        }
        JsonNode _topicsNode = _node.get("topics");
        if (_topicsNode == null) {
            throw new RuntimeException("ShareFetchRequestData: unable to locate field 'topics', which is mandatory in version " + _version);
        } else {
            if (!_topicsNode.isArray()) {
                throw new RuntimeException("ShareFetchRequestData expected a JSON array, but got " + _node.getNodeType());
            }
            ArrayList<FetchTopic> _collection = new ArrayList<FetchTopic>(_topicsNode.size());
            _object.topics = _collection;
            for (JsonNode _element : _topicsNode) {
                _collection.add(FetchTopicJsonConverter.read(_element, _version));
            }
        }
        JsonNode _forgottenTopicsDataNode = _node.get("forgottenTopicsData");
        if (_forgottenTopicsDataNode == null) {
            throw new RuntimeException("ShareFetchRequestData: unable to locate field 'forgottenTopicsData', which is mandatory in version " + _version);
        } else {
            if (!_forgottenTopicsDataNode.isArray()) {
                throw new RuntimeException("ShareFetchRequestData expected a JSON array, but got " + _node.getNodeType());
            }
            ArrayList<ForgottenTopic> _collection = new ArrayList<ForgottenTopic>(_forgottenTopicsDataNode.size());
            _object.forgottenTopicsData = _collection;
            for (JsonNode _element : _forgottenTopicsDataNode) {
                _collection.add(ForgottenTopicJsonConverter.read(_element, _version));
            }
        }
        return _object;
    }
    public static JsonNode write(ShareFetchRequestData _object, short _version, boolean _serializeRecords) {
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
        _node.set("maxWaitMs", new IntNode(_object.maxWaitMs));
        _node.set("minBytes", new IntNode(_object.minBytes));
        _node.set("maxBytes", new IntNode(_object.maxBytes));
        ArrayNode _topicsArray = new ArrayNode(JsonNodeFactory.instance);
        for (FetchTopic _element : _object.topics) {
            _topicsArray.add(FetchTopicJsonConverter.write(_element, _version, _serializeRecords));
        }
        _node.set("topics", _topicsArray);
        ArrayNode _forgottenTopicsDataArray = new ArrayNode(JsonNodeFactory.instance);
        for (ForgottenTopic _element : _object.forgottenTopicsData) {
            _forgottenTopicsDataArray.add(ForgottenTopicJsonConverter.write(_element, _version, _serializeRecords));
        }
        _node.set("forgottenTopicsData", _forgottenTopicsDataArray);
        return _node;
    }
    public static JsonNode write(ShareFetchRequestData _object, short _version) {
        return write(_object, _version, true);
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
    
    public static class FetchPartitionJsonConverter {
        public static FetchPartition read(JsonNode _node, short _version) {
            FetchPartition _object = new FetchPartition();
            JsonNode _partitionIndexNode = _node.get("partitionIndex");
            if (_partitionIndexNode == null) {
                throw new RuntimeException("FetchPartition: unable to locate field 'partitionIndex', which is mandatory in version " + _version);
            } else {
                _object.partitionIndex = MessageUtil.jsonNodeToInt(_partitionIndexNode, "FetchPartition");
            }
            JsonNode _partitionMaxBytesNode = _node.get("partitionMaxBytes");
            if (_partitionMaxBytesNode == null) {
                throw new RuntimeException("FetchPartition: unable to locate field 'partitionMaxBytes', which is mandatory in version " + _version);
            } else {
                _object.partitionMaxBytes = MessageUtil.jsonNodeToInt(_partitionMaxBytesNode, "FetchPartition");
            }
            JsonNode _acknowledgementBatchesNode = _node.get("acknowledgementBatches");
            if (_acknowledgementBatchesNode == null) {
                throw new RuntimeException("FetchPartition: unable to locate field 'acknowledgementBatches', which is mandatory in version " + _version);
            } else {
                if (!_acknowledgementBatchesNode.isArray()) {
                    throw new RuntimeException("FetchPartition expected a JSON array, but got " + _node.getNodeType());
                }
                ArrayList<AcknowledgementBatch> _collection = new ArrayList<AcknowledgementBatch>(_acknowledgementBatchesNode.size());
                _object.acknowledgementBatches = _collection;
                for (JsonNode _element : _acknowledgementBatchesNode) {
                    _collection.add(AcknowledgementBatchJsonConverter.read(_element, _version));
                }
            }
            return _object;
        }
        public static JsonNode write(FetchPartition _object, short _version, boolean _serializeRecords) {
            ObjectNode _node = new ObjectNode(JsonNodeFactory.instance);
            _node.set("partitionIndex", new IntNode(_object.partitionIndex));
            _node.set("partitionMaxBytes", new IntNode(_object.partitionMaxBytes));
            ArrayNode _acknowledgementBatchesArray = new ArrayNode(JsonNodeFactory.instance);
            for (AcknowledgementBatch _element : _object.acknowledgementBatches) {
                _acknowledgementBatchesArray.add(AcknowledgementBatchJsonConverter.write(_element, _version, _serializeRecords));
            }
            _node.set("acknowledgementBatches", _acknowledgementBatchesArray);
            return _node;
        }
        public static JsonNode write(FetchPartition _object, short _version) {
            return write(_object, _version, true);
        }
    }
    
    public static class FetchTopicJsonConverter {
        public static FetchTopic read(JsonNode _node, short _version) {
            FetchTopic _object = new FetchTopic();
            JsonNode _topicIdNode = _node.get("topicId");
            if (_topicIdNode == null) {
                throw new RuntimeException("FetchTopic: unable to locate field 'topicId', which is mandatory in version " + _version);
            } else {
                if (!_topicIdNode.isTextual()) {
                    throw new RuntimeException("FetchTopic expected a JSON string type, but got " + _node.getNodeType());
                }
                _object.topicId = Uuid.fromString(_topicIdNode.asText());
            }
            JsonNode _partitionsNode = _node.get("partitions");
            if (_partitionsNode == null) {
                throw new RuntimeException("FetchTopic: unable to locate field 'partitions', which is mandatory in version " + _version);
            } else {
                if (!_partitionsNode.isArray()) {
                    throw new RuntimeException("FetchTopic expected a JSON array, but got " + _node.getNodeType());
                }
                ArrayList<FetchPartition> _collection = new ArrayList<FetchPartition>(_partitionsNode.size());
                _object.partitions = _collection;
                for (JsonNode _element : _partitionsNode) {
                    _collection.add(FetchPartitionJsonConverter.read(_element, _version));
                }
            }
            return _object;
        }
        public static JsonNode write(FetchTopic _object, short _version, boolean _serializeRecords) {
            ObjectNode _node = new ObjectNode(JsonNodeFactory.instance);
            _node.set("topicId", new TextNode(_object.topicId.toString()));
            ArrayNode _partitionsArray = new ArrayNode(JsonNodeFactory.instance);
            for (FetchPartition _element : _object.partitions) {
                _partitionsArray.add(FetchPartitionJsonConverter.write(_element, _version, _serializeRecords));
            }
            _node.set("partitions", _partitionsArray);
            return _node;
        }
        public static JsonNode write(FetchTopic _object, short _version) {
            return write(_object, _version, true);
        }
    }
    
    public static class ForgottenTopicJsonConverter {
        public static ForgottenTopic read(JsonNode _node, short _version) {
            ForgottenTopic _object = new ForgottenTopic();
            JsonNode _topicIdNode = _node.get("topicId");
            if (_topicIdNode == null) {
                throw new RuntimeException("ForgottenTopic: unable to locate field 'topicId', which is mandatory in version " + _version);
            } else {
                if (!_topicIdNode.isTextual()) {
                    throw new RuntimeException("ForgottenTopic expected a JSON string type, but got " + _node.getNodeType());
                }
                _object.topicId = Uuid.fromString(_topicIdNode.asText());
            }
            JsonNode _partitionsNode = _node.get("partitions");
            if (_partitionsNode == null) {
                throw new RuntimeException("ForgottenTopic: unable to locate field 'partitions', which is mandatory in version " + _version);
            } else {
                if (!_partitionsNode.isArray()) {
                    throw new RuntimeException("ForgottenTopic expected a JSON array, but got " + _node.getNodeType());
                }
                ArrayList<Integer> _collection = new ArrayList<Integer>(_partitionsNode.size());
                _object.partitions = _collection;
                for (JsonNode _element : _partitionsNode) {
                    _collection.add(MessageUtil.jsonNodeToInt(_element, "ForgottenTopic element"));
                }
            }
            return _object;
        }
        public static JsonNode write(ForgottenTopic _object, short _version, boolean _serializeRecords) {
            ObjectNode _node = new ObjectNode(JsonNodeFactory.instance);
            _node.set("topicId", new TextNode(_object.topicId.toString()));
            ArrayNode _partitionsArray = new ArrayNode(JsonNodeFactory.instance);
            for (Integer _element : _object.partitions) {
                _partitionsArray.add(new IntNode(_element));
            }
            _node.set("partitions", _partitionsArray);
            return _node;
        }
        public static JsonNode write(ForgottenTopic _object, short _version) {
            return write(_object, _version, true);
        }
    }
}
