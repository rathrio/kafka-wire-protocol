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
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.ShortNode;
import com.fasterxml.jackson.databind.node.TextNode;
import java.util.ArrayList;
import org.apache.kafka.common.Uuid;
import org.apache.kafka.common.protocol.MessageUtil;

import static org.apache.kafka.common.message.WriteShareGroupStateRequestData.*;

public class WriteShareGroupStateRequestDataJsonConverter {
    public static WriteShareGroupStateRequestData read(JsonNode _node, short _version) {
        WriteShareGroupStateRequestData _object = new WriteShareGroupStateRequestData();
        JsonNode _groupIdNode = _node.get("groupId");
        if (_groupIdNode == null) {
            throw new RuntimeException("WriteShareGroupStateRequestData: unable to locate field 'groupId', which is mandatory in version " + _version);
        } else {
            if (!_groupIdNode.isTextual()) {
                throw new RuntimeException("WriteShareGroupStateRequestData expected a string type, but got " + _node.getNodeType());
            }
            _object.groupId = _groupIdNode.asText();
        }
        JsonNode _topicsNode = _node.get("topics");
        if (_topicsNode == null) {
            throw new RuntimeException("WriteShareGroupStateRequestData: unable to locate field 'topics', which is mandatory in version " + _version);
        } else {
            if (!_topicsNode.isArray()) {
                throw new RuntimeException("WriteShareGroupStateRequestData expected a JSON array, but got " + _node.getNodeType());
            }
            ArrayList<WriteStateData> _collection = new ArrayList<WriteStateData>(_topicsNode.size());
            _object.topics = _collection;
            for (JsonNode _element : _topicsNode) {
                _collection.add(WriteStateDataJsonConverter.read(_element, _version));
            }
        }
        return _object;
    }
    public static JsonNode write(WriteShareGroupStateRequestData _object, short _version, boolean _serializeRecords) {
        ObjectNode _node = new ObjectNode(JsonNodeFactory.instance);
        _node.set("groupId", new TextNode(_object.groupId));
        ArrayNode _topicsArray = new ArrayNode(JsonNodeFactory.instance);
        for (WriteStateData _element : _object.topics) {
            _topicsArray.add(WriteStateDataJsonConverter.write(_element, _version, _serializeRecords));
        }
        _node.set("topics", _topicsArray);
        return _node;
    }
    public static JsonNode write(WriteShareGroupStateRequestData _object, short _version) {
        return write(_object, _version, true);
    }
    
    public static class PartitionDataJsonConverter {
        public static PartitionData read(JsonNode _node, short _version) {
            PartitionData _object = new PartitionData();
            JsonNode _partitionNode = _node.get("partition");
            if (_partitionNode == null) {
                throw new RuntimeException("PartitionData: unable to locate field 'partition', which is mandatory in version " + _version);
            } else {
                _object.partition = MessageUtil.jsonNodeToInt(_partitionNode, "PartitionData");
            }
            JsonNode _stateEpochNode = _node.get("stateEpoch");
            if (_stateEpochNode == null) {
                throw new RuntimeException("PartitionData: unable to locate field 'stateEpoch', which is mandatory in version " + _version);
            } else {
                _object.stateEpoch = MessageUtil.jsonNodeToInt(_stateEpochNode, "PartitionData");
            }
            JsonNode _leaderEpochNode = _node.get("leaderEpoch");
            if (_leaderEpochNode == null) {
                throw new RuntimeException("PartitionData: unable to locate field 'leaderEpoch', which is mandatory in version " + _version);
            } else {
                _object.leaderEpoch = MessageUtil.jsonNodeToInt(_leaderEpochNode, "PartitionData");
            }
            JsonNode _startOffsetNode = _node.get("startOffset");
            if (_startOffsetNode == null) {
                throw new RuntimeException("PartitionData: unable to locate field 'startOffset', which is mandatory in version " + _version);
            } else {
                _object.startOffset = MessageUtil.jsonNodeToLong(_startOffsetNode, "PartitionData");
            }
            JsonNode _stateBatchesNode = _node.get("stateBatches");
            if (_stateBatchesNode == null) {
                throw new RuntimeException("PartitionData: unable to locate field 'stateBatches', which is mandatory in version " + _version);
            } else {
                if (!_stateBatchesNode.isArray()) {
                    throw new RuntimeException("PartitionData expected a JSON array, but got " + _node.getNodeType());
                }
                ArrayList<StateBatch> _collection = new ArrayList<StateBatch>(_stateBatchesNode.size());
                _object.stateBatches = _collection;
                for (JsonNode _element : _stateBatchesNode) {
                    _collection.add(StateBatchJsonConverter.read(_element, _version));
                }
            }
            return _object;
        }
        public static JsonNode write(PartitionData _object, short _version, boolean _serializeRecords) {
            ObjectNode _node = new ObjectNode(JsonNodeFactory.instance);
            _node.set("partition", new IntNode(_object.partition));
            _node.set("stateEpoch", new IntNode(_object.stateEpoch));
            _node.set("leaderEpoch", new IntNode(_object.leaderEpoch));
            _node.set("startOffset", new LongNode(_object.startOffset));
            ArrayNode _stateBatchesArray = new ArrayNode(JsonNodeFactory.instance);
            for (StateBatch _element : _object.stateBatches) {
                _stateBatchesArray.add(StateBatchJsonConverter.write(_element, _version, _serializeRecords));
            }
            _node.set("stateBatches", _stateBatchesArray);
            return _node;
        }
        public static JsonNode write(PartitionData _object, short _version) {
            return write(_object, _version, true);
        }
    }
    
    public static class StateBatchJsonConverter {
        public static StateBatch read(JsonNode _node, short _version) {
            StateBatch _object = new StateBatch();
            JsonNode _firstOffsetNode = _node.get("firstOffset");
            if (_firstOffsetNode == null) {
                throw new RuntimeException("StateBatch: unable to locate field 'firstOffset', which is mandatory in version " + _version);
            } else {
                _object.firstOffset = MessageUtil.jsonNodeToLong(_firstOffsetNode, "StateBatch");
            }
            JsonNode _lastOffsetNode = _node.get("lastOffset");
            if (_lastOffsetNode == null) {
                throw new RuntimeException("StateBatch: unable to locate field 'lastOffset', which is mandatory in version " + _version);
            } else {
                _object.lastOffset = MessageUtil.jsonNodeToLong(_lastOffsetNode, "StateBatch");
            }
            JsonNode _deliveryStateNode = _node.get("deliveryState");
            if (_deliveryStateNode == null) {
                throw new RuntimeException("StateBatch: unable to locate field 'deliveryState', which is mandatory in version " + _version);
            } else {
                _object.deliveryState = MessageUtil.jsonNodeToByte(_deliveryStateNode, "StateBatch");
            }
            JsonNode _deliveryCountNode = _node.get("deliveryCount");
            if (_deliveryCountNode == null) {
                throw new RuntimeException("StateBatch: unable to locate field 'deliveryCount', which is mandatory in version " + _version);
            } else {
                _object.deliveryCount = MessageUtil.jsonNodeToShort(_deliveryCountNode, "StateBatch");
            }
            return _object;
        }
        public static JsonNode write(StateBatch _object, short _version, boolean _serializeRecords) {
            ObjectNode _node = new ObjectNode(JsonNodeFactory.instance);
            _node.set("firstOffset", new LongNode(_object.firstOffset));
            _node.set("lastOffset", new LongNode(_object.lastOffset));
            _node.set("deliveryState", new ShortNode(_object.deliveryState));
            _node.set("deliveryCount", new ShortNode(_object.deliveryCount));
            return _node;
        }
        public static JsonNode write(StateBatch _object, short _version) {
            return write(_object, _version, true);
        }
    }
    
    public static class WriteStateDataJsonConverter {
        public static WriteStateData read(JsonNode _node, short _version) {
            WriteStateData _object = new WriteStateData();
            JsonNode _topicIdNode = _node.get("topicId");
            if (_topicIdNode == null) {
                throw new RuntimeException("WriteStateData: unable to locate field 'topicId', which is mandatory in version " + _version);
            } else {
                if (!_topicIdNode.isTextual()) {
                    throw new RuntimeException("WriteStateData expected a JSON string type, but got " + _node.getNodeType());
                }
                _object.topicId = Uuid.fromString(_topicIdNode.asText());
            }
            JsonNode _partitionsNode = _node.get("partitions");
            if (_partitionsNode == null) {
                throw new RuntimeException("WriteStateData: unable to locate field 'partitions', which is mandatory in version " + _version);
            } else {
                if (!_partitionsNode.isArray()) {
                    throw new RuntimeException("WriteStateData expected a JSON array, but got " + _node.getNodeType());
                }
                ArrayList<PartitionData> _collection = new ArrayList<PartitionData>(_partitionsNode.size());
                _object.partitions = _collection;
                for (JsonNode _element : _partitionsNode) {
                    _collection.add(PartitionDataJsonConverter.read(_element, _version));
                }
            }
            return _object;
        }
        public static JsonNode write(WriteStateData _object, short _version, boolean _serializeRecords) {
            ObjectNode _node = new ObjectNode(JsonNodeFactory.instance);
            _node.set("topicId", new TextNode(_object.topicId.toString()));
            ArrayNode _partitionsArray = new ArrayNode(JsonNodeFactory.instance);
            for (PartitionData _element : _object.partitions) {
                _partitionsArray.add(PartitionDataJsonConverter.write(_element, _version, _serializeRecords));
            }
            _node.set("partitions", _partitionsArray);
            return _node;
        }
        public static JsonNode write(WriteStateData _object, short _version) {
            return write(_object, _version, true);
        }
    }
}
