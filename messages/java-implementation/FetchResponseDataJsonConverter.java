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
import com.fasterxml.jackson.databind.node.BinaryNode;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.LongNode;
import com.fasterxml.jackson.databind.node.NullNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.ShortNode;
import com.fasterxml.jackson.databind.node.TextNode;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.apache.kafka.common.Uuid;
import org.apache.kafka.common.errors.UnsupportedVersionException;
import org.apache.kafka.common.protocol.MessageUtil;
import org.apache.kafka.common.record.MemoryRecords;

import static org.apache.kafka.common.message.FetchResponseData.*;

public class FetchResponseDataJsonConverter {
    public static FetchResponseData read(JsonNode _node, short _version) {
        FetchResponseData _object = new FetchResponseData();
        JsonNode _throttleTimeMsNode = _node.get("throttleTimeMs");
        if (_throttleTimeMsNode == null) {
            throw new RuntimeException("FetchResponseData: unable to locate field 'throttleTimeMs', which is mandatory in version " + _version);
        } else {
            _object.throttleTimeMs = MessageUtil.jsonNodeToInt(_throttleTimeMsNode, "FetchResponseData");
        }
        JsonNode _errorCodeNode = _node.get("errorCode");
        if (_errorCodeNode == null) {
            if (_version >= 7) {
                throw new RuntimeException("FetchResponseData: unable to locate field 'errorCode', which is mandatory in version " + _version);
            } else {
                _object.errorCode = (short) 0;
            }
        } else {
            _object.errorCode = MessageUtil.jsonNodeToShort(_errorCodeNode, "FetchResponseData");
        }
        JsonNode _sessionIdNode = _node.get("sessionId");
        if (_sessionIdNode == null) {
            if (_version >= 7) {
                throw new RuntimeException("FetchResponseData: unable to locate field 'sessionId', which is mandatory in version " + _version);
            } else {
                _object.sessionId = 0;
            }
        } else {
            _object.sessionId = MessageUtil.jsonNodeToInt(_sessionIdNode, "FetchResponseData");
        }
        JsonNode _responsesNode = _node.get("responses");
        if (_responsesNode == null) {
            throw new RuntimeException("FetchResponseData: unable to locate field 'responses', which is mandatory in version " + _version);
        } else {
            if (!_responsesNode.isArray()) {
                throw new RuntimeException("FetchResponseData expected a JSON array, but got " + _node.getNodeType());
            }
            ArrayList<FetchableTopicResponse> _collection = new ArrayList<FetchableTopicResponse>(_responsesNode.size());
            _object.responses = _collection;
            for (JsonNode _element : _responsesNode) {
                _collection.add(FetchableTopicResponseJsonConverter.read(_element, _version));
            }
        }
        JsonNode _nodeEndpointsNode = _node.get("nodeEndpoints");
        if (_nodeEndpointsNode == null) {
            _object.nodeEndpoints = new NodeEndpointCollection(0);
        } else {
            if (!_nodeEndpointsNode.isArray()) {
                throw new RuntimeException("FetchResponseData expected a JSON array, but got " + _node.getNodeType());
            }
            NodeEndpointCollection _collection = new NodeEndpointCollection(_nodeEndpointsNode.size());
            _object.nodeEndpoints = _collection;
            for (JsonNode _element : _nodeEndpointsNode) {
                _collection.add(NodeEndpointJsonConverter.read(_element, _version));
            }
        }
        return _object;
    }
    public static JsonNode write(FetchResponseData _object, short _version, boolean _serializeRecords) {
        ObjectNode _node = new ObjectNode(JsonNodeFactory.instance);
        _node.set("throttleTimeMs", new IntNode(_object.throttleTimeMs));
        if (_version >= 7) {
            _node.set("errorCode", new ShortNode(_object.errorCode));
        }
        if (_version >= 7) {
            _node.set("sessionId", new IntNode(_object.sessionId));
        } else {
            if (_object.sessionId != 0) {
                throw new UnsupportedVersionException("Attempted to write a non-default sessionId at version " + _version);
            }
        }
        ArrayNode _responsesArray = new ArrayNode(JsonNodeFactory.instance);
        for (FetchableTopicResponse _element : _object.responses) {
            _responsesArray.add(FetchableTopicResponseJsonConverter.write(_element, _version, _serializeRecords));
        }
        _node.set("responses", _responsesArray);
        if (_version >= 16) {
            if (!_object.nodeEndpoints.isEmpty()) {
                ArrayNode _nodeEndpointsArray = new ArrayNode(JsonNodeFactory.instance);
                for (NodeEndpoint _element : _object.nodeEndpoints) {
                    _nodeEndpointsArray.add(NodeEndpointJsonConverter.write(_element, _version, _serializeRecords));
                }
                _node.set("nodeEndpoints", _nodeEndpointsArray);
            }
        } else {
            if (!_object.nodeEndpoints.isEmpty()) {
                throw new UnsupportedVersionException("Attempted to write a non-default nodeEndpoints at version " + _version);
            }
        }
        return _node;
    }
    public static JsonNode write(FetchResponseData _object, short _version) {
        return write(_object, _version, true);
    }
    
    public static class AbortedTransactionJsonConverter {
        public static AbortedTransaction read(JsonNode _node, short _version) {
            AbortedTransaction _object = new AbortedTransaction();
            JsonNode _producerIdNode = _node.get("producerId");
            if (_producerIdNode == null) {
                throw new RuntimeException("AbortedTransaction: unable to locate field 'producerId', which is mandatory in version " + _version);
            } else {
                _object.producerId = MessageUtil.jsonNodeToLong(_producerIdNode, "AbortedTransaction");
            }
            JsonNode _firstOffsetNode = _node.get("firstOffset");
            if (_firstOffsetNode == null) {
                throw new RuntimeException("AbortedTransaction: unable to locate field 'firstOffset', which is mandatory in version " + _version);
            } else {
                _object.firstOffset = MessageUtil.jsonNodeToLong(_firstOffsetNode, "AbortedTransaction");
            }
            return _object;
        }
        public static JsonNode write(AbortedTransaction _object, short _version, boolean _serializeRecords) {
            ObjectNode _node = new ObjectNode(JsonNodeFactory.instance);
            _node.set("producerId", new LongNode(_object.producerId));
            _node.set("firstOffset", new LongNode(_object.firstOffset));
            return _node;
        }
        public static JsonNode write(AbortedTransaction _object, short _version) {
            return write(_object, _version, true);
        }
    }
    
    public static class EpochEndOffsetJsonConverter {
        public static EpochEndOffset read(JsonNode _node, short _version) {
            EpochEndOffset _object = new EpochEndOffset();
            if (_version < 12) {
                throw new UnsupportedVersionException("Can't read version " + _version + " of EpochEndOffset");
            }
            JsonNode _epochNode = _node.get("epoch");
            if (_epochNode == null) {
                throw new RuntimeException("EpochEndOffset: unable to locate field 'epoch', which is mandatory in version " + _version);
            } else {
                _object.epoch = MessageUtil.jsonNodeToInt(_epochNode, "EpochEndOffset");
            }
            JsonNode _endOffsetNode = _node.get("endOffset");
            if (_endOffsetNode == null) {
                throw new RuntimeException("EpochEndOffset: unable to locate field 'endOffset', which is mandatory in version " + _version);
            } else {
                _object.endOffset = MessageUtil.jsonNodeToLong(_endOffsetNode, "EpochEndOffset");
            }
            return _object;
        }
        public static JsonNode write(EpochEndOffset _object, short _version, boolean _serializeRecords) {
            if (_version < 12) {
                throw new UnsupportedVersionException("Can't write version " + _version + " of EpochEndOffset");
            }
            ObjectNode _node = new ObjectNode(JsonNodeFactory.instance);
            _node.set("epoch", new IntNode(_object.epoch));
            _node.set("endOffset", new LongNode(_object.endOffset));
            return _node;
        }
        public static JsonNode write(EpochEndOffset _object, short _version) {
            return write(_object, _version, true);
        }
    }
    
    public static class FetchableTopicResponseJsonConverter {
        public static FetchableTopicResponse read(JsonNode _node, short _version) {
            FetchableTopicResponse _object = new FetchableTopicResponse();
            JsonNode _topicNode = _node.get("topic");
            if (_topicNode == null) {
                if (_version <= 12) {
                    throw new RuntimeException("FetchableTopicResponse: unable to locate field 'topic', which is mandatory in version " + _version);
                } else {
                    _object.topic = "";
                }
            } else {
                if (!_topicNode.isTextual()) {
                    throw new RuntimeException("FetchableTopicResponse expected a string type, but got " + _node.getNodeType());
                }
                _object.topic = _topicNode.asText();
            }
            JsonNode _topicIdNode = _node.get("topicId");
            if (_topicIdNode == null) {
                if (_version >= 13) {
                    throw new RuntimeException("FetchableTopicResponse: unable to locate field 'topicId', which is mandatory in version " + _version);
                } else {
                    _object.topicId = Uuid.ZERO_UUID;
                }
            } else {
                if (!_topicIdNode.isTextual()) {
                    throw new RuntimeException("FetchableTopicResponse expected a JSON string type, but got " + _node.getNodeType());
                }
                _object.topicId = Uuid.fromString(_topicIdNode.asText());
            }
            JsonNode _partitionsNode = _node.get("partitions");
            if (_partitionsNode == null) {
                throw new RuntimeException("FetchableTopicResponse: unable to locate field 'partitions', which is mandatory in version " + _version);
            } else {
                if (!_partitionsNode.isArray()) {
                    throw new RuntimeException("FetchableTopicResponse expected a JSON array, but got " + _node.getNodeType());
                }
                ArrayList<PartitionData> _collection = new ArrayList<PartitionData>(_partitionsNode.size());
                _object.partitions = _collection;
                for (JsonNode _element : _partitionsNode) {
                    _collection.add(PartitionDataJsonConverter.read(_element, _version));
                }
            }
            return _object;
        }
        public static JsonNode write(FetchableTopicResponse _object, short _version, boolean _serializeRecords) {
            ObjectNode _node = new ObjectNode(JsonNodeFactory.instance);
            if (_version <= 12) {
                _node.set("topic", new TextNode(_object.topic));
            }
            if (_version >= 13) {
                _node.set("topicId", new TextNode(_object.topicId.toString()));
            }
            ArrayNode _partitionsArray = new ArrayNode(JsonNodeFactory.instance);
            for (PartitionData _element : _object.partitions) {
                _partitionsArray.add(PartitionDataJsonConverter.write(_element, _version, _serializeRecords));
            }
            _node.set("partitions", _partitionsArray);
            return _node;
        }
        public static JsonNode write(FetchableTopicResponse _object, short _version) {
            return write(_object, _version, true);
        }
    }
    
    public static class LeaderIdAndEpochJsonConverter {
        public static LeaderIdAndEpoch read(JsonNode _node, short _version) {
            LeaderIdAndEpoch _object = new LeaderIdAndEpoch();
            if (_version < 12) {
                throw new UnsupportedVersionException("Can't read version " + _version + " of LeaderIdAndEpoch");
            }
            JsonNode _leaderIdNode = _node.get("leaderId");
            if (_leaderIdNode == null) {
                throw new RuntimeException("LeaderIdAndEpoch: unable to locate field 'leaderId', which is mandatory in version " + _version);
            } else {
                _object.leaderId = MessageUtil.jsonNodeToInt(_leaderIdNode, "LeaderIdAndEpoch");
            }
            JsonNode _leaderEpochNode = _node.get("leaderEpoch");
            if (_leaderEpochNode == null) {
                throw new RuntimeException("LeaderIdAndEpoch: unable to locate field 'leaderEpoch', which is mandatory in version " + _version);
            } else {
                _object.leaderEpoch = MessageUtil.jsonNodeToInt(_leaderEpochNode, "LeaderIdAndEpoch");
            }
            return _object;
        }
        public static JsonNode write(LeaderIdAndEpoch _object, short _version, boolean _serializeRecords) {
            if (_version < 12) {
                throw new UnsupportedVersionException("Can't write version " + _version + " of LeaderIdAndEpoch");
            }
            ObjectNode _node = new ObjectNode(JsonNodeFactory.instance);
            _node.set("leaderId", new IntNode(_object.leaderId));
            _node.set("leaderEpoch", new IntNode(_object.leaderEpoch));
            return _node;
        }
        public static JsonNode write(LeaderIdAndEpoch _object, short _version) {
            return write(_object, _version, true);
        }
    }
    
    public static class NodeEndpointJsonConverter {
        public static NodeEndpoint read(JsonNode _node, short _version) {
            NodeEndpoint _object = new NodeEndpoint();
            if (_version < 16) {
                throw new UnsupportedVersionException("Can't read version " + _version + " of NodeEndpoint");
            }
            JsonNode _nodeIdNode = _node.get("nodeId");
            if (_nodeIdNode == null) {
                throw new RuntimeException("NodeEndpoint: unable to locate field 'nodeId', which is mandatory in version " + _version);
            } else {
                _object.nodeId = MessageUtil.jsonNodeToInt(_nodeIdNode, "NodeEndpoint");
            }
            JsonNode _hostNode = _node.get("host");
            if (_hostNode == null) {
                throw new RuntimeException("NodeEndpoint: unable to locate field 'host', which is mandatory in version " + _version);
            } else {
                if (!_hostNode.isTextual()) {
                    throw new RuntimeException("NodeEndpoint expected a string type, but got " + _node.getNodeType());
                }
                _object.host = _hostNode.asText();
            }
            JsonNode _portNode = _node.get("port");
            if (_portNode == null) {
                throw new RuntimeException("NodeEndpoint: unable to locate field 'port', which is mandatory in version " + _version);
            } else {
                _object.port = MessageUtil.jsonNodeToInt(_portNode, "NodeEndpoint");
            }
            JsonNode _rackNode = _node.get("rack");
            if (_rackNode == null) {
                throw new RuntimeException("NodeEndpoint: unable to locate field 'rack', which is mandatory in version " + _version);
            } else {
                if (_rackNode.isNull()) {
                    _object.rack = null;
                } else {
                    if (!_rackNode.isTextual()) {
                        throw new RuntimeException("NodeEndpoint expected a string type, but got " + _node.getNodeType());
                    }
                    _object.rack = _rackNode.asText();
                }
            }
            return _object;
        }
        public static JsonNode write(NodeEndpoint _object, short _version, boolean _serializeRecords) {
            if (_version < 16) {
                throw new UnsupportedVersionException("Can't write version " + _version + " of NodeEndpoint");
            }
            ObjectNode _node = new ObjectNode(JsonNodeFactory.instance);
            _node.set("nodeId", new IntNode(_object.nodeId));
            _node.set("host", new TextNode(_object.host));
            _node.set("port", new IntNode(_object.port));
            if (_object.rack == null) {
                _node.set("rack", NullNode.instance);
            } else {
                _node.set("rack", new TextNode(_object.rack));
            }
            return _node;
        }
        public static JsonNode write(NodeEndpoint _object, short _version) {
            return write(_object, _version, true);
        }
    }
    
    public static class PartitionDataJsonConverter {
        public static PartitionData read(JsonNode _node, short _version) {
            PartitionData _object = new PartitionData();
            JsonNode _partitionIndexNode = _node.get("partitionIndex");
            if (_partitionIndexNode == null) {
                throw new RuntimeException("PartitionData: unable to locate field 'partitionIndex', which is mandatory in version " + _version);
            } else {
                _object.partitionIndex = MessageUtil.jsonNodeToInt(_partitionIndexNode, "PartitionData");
            }
            JsonNode _errorCodeNode = _node.get("errorCode");
            if (_errorCodeNode == null) {
                throw new RuntimeException("PartitionData: unable to locate field 'errorCode', which is mandatory in version " + _version);
            } else {
                _object.errorCode = MessageUtil.jsonNodeToShort(_errorCodeNode, "PartitionData");
            }
            JsonNode _highWatermarkNode = _node.get("highWatermark");
            if (_highWatermarkNode == null) {
                throw new RuntimeException("PartitionData: unable to locate field 'highWatermark', which is mandatory in version " + _version);
            } else {
                _object.highWatermark = MessageUtil.jsonNodeToLong(_highWatermarkNode, "PartitionData");
            }
            JsonNode _lastStableOffsetNode = _node.get("lastStableOffset");
            if (_lastStableOffsetNode == null) {
                throw new RuntimeException("PartitionData: unable to locate field 'lastStableOffset', which is mandatory in version " + _version);
            } else {
                _object.lastStableOffset = MessageUtil.jsonNodeToLong(_lastStableOffsetNode, "PartitionData");
            }
            JsonNode _logStartOffsetNode = _node.get("logStartOffset");
            if (_logStartOffsetNode == null) {
                if (_version >= 5) {
                    throw new RuntimeException("PartitionData: unable to locate field 'logStartOffset', which is mandatory in version " + _version);
                } else {
                    _object.logStartOffset = -1L;
                }
            } else {
                _object.logStartOffset = MessageUtil.jsonNodeToLong(_logStartOffsetNode, "PartitionData");
            }
            JsonNode _divergingEpochNode = _node.get("divergingEpoch");
            if (_divergingEpochNode == null) {
                _object.divergingEpoch = new EpochEndOffset();
            } else {
                _object.divergingEpoch = EpochEndOffsetJsonConverter.read(_divergingEpochNode, _version);
            }
            JsonNode _currentLeaderNode = _node.get("currentLeader");
            if (_currentLeaderNode == null) {
                _object.currentLeader = new LeaderIdAndEpoch();
            } else {
                _object.currentLeader = LeaderIdAndEpochJsonConverter.read(_currentLeaderNode, _version);
            }
            JsonNode _snapshotIdNode = _node.get("snapshotId");
            if (_snapshotIdNode == null) {
                _object.snapshotId = new SnapshotId();
            } else {
                _object.snapshotId = SnapshotIdJsonConverter.read(_snapshotIdNode, _version);
            }
            JsonNode _abortedTransactionsNode = _node.get("abortedTransactions");
            if (_abortedTransactionsNode == null) {
                throw new RuntimeException("PartitionData: unable to locate field 'abortedTransactions', which is mandatory in version " + _version);
            } else {
                if (_abortedTransactionsNode.isNull()) {
                    _object.abortedTransactions = null;
                } else {
                    if (!_abortedTransactionsNode.isArray()) {
                        throw new RuntimeException("PartitionData expected a JSON array, but got " + _node.getNodeType());
                    }
                    ArrayList<AbortedTransaction> _collection = new ArrayList<AbortedTransaction>(_abortedTransactionsNode.size());
                    _object.abortedTransactions = _collection;
                    for (JsonNode _element : _abortedTransactionsNode) {
                        _collection.add(AbortedTransactionJsonConverter.read(_element, _version));
                    }
                }
            }
            JsonNode _preferredReadReplicaNode = _node.get("preferredReadReplica");
            if (_preferredReadReplicaNode == null) {
                if (_version >= 11) {
                    throw new RuntimeException("PartitionData: unable to locate field 'preferredReadReplica', which is mandatory in version " + _version);
                } else {
                    _object.preferredReadReplica = -1;
                }
            } else {
                _object.preferredReadReplica = MessageUtil.jsonNodeToInt(_preferredReadReplicaNode, "PartitionData");
            }
            JsonNode _recordsNode = _node.get("records");
            if (_recordsNode == null) {
                throw new RuntimeException("PartitionData: unable to locate field 'records', which is mandatory in version " + _version);
            } else {
                _object.records = MemoryRecords.readableRecords(ByteBuffer.wrap(MessageUtil.jsonNodeToBinary(_recordsNode, "PartitionData")));
            }
            return _object;
        }
        public static JsonNode write(PartitionData _object, short _version, boolean _serializeRecords) {
            ObjectNode _node = new ObjectNode(JsonNodeFactory.instance);
            _node.set("partitionIndex", new IntNode(_object.partitionIndex));
            _node.set("errorCode", new ShortNode(_object.errorCode));
            _node.set("highWatermark", new LongNode(_object.highWatermark));
            _node.set("lastStableOffset", new LongNode(_object.lastStableOffset));
            if (_version >= 5) {
                _node.set("logStartOffset", new LongNode(_object.logStartOffset));
            }
            if (_version >= 12) {
                if (!_object.divergingEpoch.equals(new EpochEndOffset())) {
                    _node.set("divergingEpoch", EpochEndOffsetJsonConverter.write(_object.divergingEpoch, _version, _serializeRecords));
                }
            } else {
                if (!_object.divergingEpoch.equals(new EpochEndOffset())) {
                    throw new UnsupportedVersionException("Attempted to write a non-default divergingEpoch at version " + _version);
                }
            }
            if (_version >= 12) {
                if (!_object.currentLeader.equals(new LeaderIdAndEpoch())) {
                    _node.set("currentLeader", LeaderIdAndEpochJsonConverter.write(_object.currentLeader, _version, _serializeRecords));
                }
            } else {
                if (!_object.currentLeader.equals(new LeaderIdAndEpoch())) {
                    throw new UnsupportedVersionException("Attempted to write a non-default currentLeader at version " + _version);
                }
            }
            if (_version >= 12) {
                if (!_object.snapshotId.equals(new SnapshotId())) {
                    _node.set("snapshotId", SnapshotIdJsonConverter.write(_object.snapshotId, _version, _serializeRecords));
                }
            } else {
                if (!_object.snapshotId.equals(new SnapshotId())) {
                    throw new UnsupportedVersionException("Attempted to write a non-default snapshotId at version " + _version);
                }
            }
            if (_object.abortedTransactions == null) {
                _node.set("abortedTransactions", NullNode.instance);
            } else {
                ArrayNode _abortedTransactionsArray = new ArrayNode(JsonNodeFactory.instance);
                for (AbortedTransaction _element : _object.abortedTransactions) {
                    _abortedTransactionsArray.add(AbortedTransactionJsonConverter.write(_element, _version, _serializeRecords));
                }
                _node.set("abortedTransactions", _abortedTransactionsArray);
            }
            if (_version >= 11) {
                _node.set("preferredReadReplica", new IntNode(_object.preferredReadReplica));
            } else {
                if (_object.preferredReadReplica != -1) {
                    throw new UnsupportedVersionException("Attempted to write a non-default preferredReadReplica at version " + _version);
                }
            }
            if (_serializeRecords) {
                _node.set("records", new BinaryNode(new byte[]{}));
            } else {
                _node.set("recordsSizeInBytes", new IntNode(_object.records.sizeInBytes()));
            }
            return _node;
        }
        public static JsonNode write(PartitionData _object, short _version) {
            return write(_object, _version, true);
        }
    }
    
    public static class SnapshotIdJsonConverter {
        public static SnapshotId read(JsonNode _node, short _version) {
            SnapshotId _object = new SnapshotId();
            if (_version < 12) {
                throw new UnsupportedVersionException("Can't read version " + _version + " of SnapshotId");
            }
            JsonNode _endOffsetNode = _node.get("endOffset");
            if (_endOffsetNode == null) {
                throw new RuntimeException("SnapshotId: unable to locate field 'endOffset', which is mandatory in version " + _version);
            } else {
                _object.endOffset = MessageUtil.jsonNodeToLong(_endOffsetNode, "SnapshotId");
            }
            JsonNode _epochNode = _node.get("epoch");
            if (_epochNode == null) {
                throw new RuntimeException("SnapshotId: unable to locate field 'epoch', which is mandatory in version " + _version);
            } else {
                _object.epoch = MessageUtil.jsonNodeToInt(_epochNode, "SnapshotId");
            }
            return _object;
        }
        public static JsonNode write(SnapshotId _object, short _version, boolean _serializeRecords) {
            if (_version < 12) {
                throw new UnsupportedVersionException("Can't write version " + _version + " of SnapshotId");
            }
            ObjectNode _node = new ObjectNode(JsonNodeFactory.instance);
            _node.set("endOffset", new LongNode(_object.endOffset));
            _node.set("epoch", new IntNode(_object.epoch));
            return _node;
        }
        public static JsonNode write(SnapshotId _object, short _version) {
            return write(_object, _version, true);
        }
    }
}
