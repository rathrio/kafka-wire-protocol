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
import org.apache.kafka.common.protocol.MessageUtil;
import org.apache.kafka.common.record.MemoryRecords;

import static org.apache.kafka.common.message.ShareFetchResponseData.*;

public class ShareFetchResponseDataJsonConverter {
    public static ShareFetchResponseData read(JsonNode _node, short _version) {
        ShareFetchResponseData _object = new ShareFetchResponseData();
        JsonNode _throttleTimeMsNode = _node.get("throttleTimeMs");
        if (_throttleTimeMsNode == null) {
            throw new RuntimeException("ShareFetchResponseData: unable to locate field 'throttleTimeMs', which is mandatory in version " + _version);
        } else {
            _object.throttleTimeMs = MessageUtil.jsonNodeToInt(_throttleTimeMsNode, "ShareFetchResponseData");
        }
        JsonNode _errorCodeNode = _node.get("errorCode");
        if (_errorCodeNode == null) {
            throw new RuntimeException("ShareFetchResponseData: unable to locate field 'errorCode', which is mandatory in version " + _version);
        } else {
            _object.errorCode = MessageUtil.jsonNodeToShort(_errorCodeNode, "ShareFetchResponseData");
        }
        JsonNode _errorMessageNode = _node.get("errorMessage");
        if (_errorMessageNode == null) {
            throw new RuntimeException("ShareFetchResponseData: unable to locate field 'errorMessage', which is mandatory in version " + _version);
        } else {
            if (_errorMessageNode.isNull()) {
                _object.errorMessage = null;
            } else {
                if (!_errorMessageNode.isTextual()) {
                    throw new RuntimeException("ShareFetchResponseData expected a string type, but got " + _node.getNodeType());
                }
                _object.errorMessage = _errorMessageNode.asText();
            }
        }
        JsonNode _responsesNode = _node.get("responses");
        if (_responsesNode == null) {
            throw new RuntimeException("ShareFetchResponseData: unable to locate field 'responses', which is mandatory in version " + _version);
        } else {
            if (!_responsesNode.isArray()) {
                throw new RuntimeException("ShareFetchResponseData expected a JSON array, but got " + _node.getNodeType());
            }
            ArrayList<ShareFetchableTopicResponse> _collection = new ArrayList<ShareFetchableTopicResponse>(_responsesNode.size());
            _object.responses = _collection;
            for (JsonNode _element : _responsesNode) {
                _collection.add(ShareFetchableTopicResponseJsonConverter.read(_element, _version));
            }
        }
        JsonNode _nodeEndpointsNode = _node.get("nodeEndpoints");
        if (_nodeEndpointsNode == null) {
            throw new RuntimeException("ShareFetchResponseData: unable to locate field 'nodeEndpoints', which is mandatory in version " + _version);
        } else {
            if (!_nodeEndpointsNode.isArray()) {
                throw new RuntimeException("ShareFetchResponseData expected a JSON array, but got " + _node.getNodeType());
            }
            NodeEndpointCollection _collection = new NodeEndpointCollection(_nodeEndpointsNode.size());
            _object.nodeEndpoints = _collection;
            for (JsonNode _element : _nodeEndpointsNode) {
                _collection.add(NodeEndpointJsonConverter.read(_element, _version));
            }
        }
        return _object;
    }
    public static JsonNode write(ShareFetchResponseData _object, short _version, boolean _serializeRecords) {
        ObjectNode _node = new ObjectNode(JsonNodeFactory.instance);
        _node.set("throttleTimeMs", new IntNode(_object.throttleTimeMs));
        _node.set("errorCode", new ShortNode(_object.errorCode));
        if (_object.errorMessage == null) {
            _node.set("errorMessage", NullNode.instance);
        } else {
            _node.set("errorMessage", new TextNode(_object.errorMessage));
        }
        ArrayNode _responsesArray = new ArrayNode(JsonNodeFactory.instance);
        for (ShareFetchableTopicResponse _element : _object.responses) {
            _responsesArray.add(ShareFetchableTopicResponseJsonConverter.write(_element, _version, _serializeRecords));
        }
        _node.set("responses", _responsesArray);
        ArrayNode _nodeEndpointsArray = new ArrayNode(JsonNodeFactory.instance);
        for (NodeEndpoint _element : _object.nodeEndpoints) {
            _nodeEndpointsArray.add(NodeEndpointJsonConverter.write(_element, _version, _serializeRecords));
        }
        _node.set("nodeEndpoints", _nodeEndpointsArray);
        return _node;
    }
    public static JsonNode write(ShareFetchResponseData _object, short _version) {
        return write(_object, _version, true);
    }
    
    public static class AcquiredRecordsJsonConverter {
        public static AcquiredRecords read(JsonNode _node, short _version) {
            AcquiredRecords _object = new AcquiredRecords();
            JsonNode _firstOffsetNode = _node.get("firstOffset");
            if (_firstOffsetNode == null) {
                throw new RuntimeException("AcquiredRecords: unable to locate field 'firstOffset', which is mandatory in version " + _version);
            } else {
                _object.firstOffset = MessageUtil.jsonNodeToLong(_firstOffsetNode, "AcquiredRecords");
            }
            JsonNode _lastOffsetNode = _node.get("lastOffset");
            if (_lastOffsetNode == null) {
                throw new RuntimeException("AcquiredRecords: unable to locate field 'lastOffset', which is mandatory in version " + _version);
            } else {
                _object.lastOffset = MessageUtil.jsonNodeToLong(_lastOffsetNode, "AcquiredRecords");
            }
            JsonNode _deliveryCountNode = _node.get("deliveryCount");
            if (_deliveryCountNode == null) {
                throw new RuntimeException("AcquiredRecords: unable to locate field 'deliveryCount', which is mandatory in version " + _version);
            } else {
                _object.deliveryCount = MessageUtil.jsonNodeToShort(_deliveryCountNode, "AcquiredRecords");
            }
            return _object;
        }
        public static JsonNode write(AcquiredRecords _object, short _version, boolean _serializeRecords) {
            ObjectNode _node = new ObjectNode(JsonNodeFactory.instance);
            _node.set("firstOffset", new LongNode(_object.firstOffset));
            _node.set("lastOffset", new LongNode(_object.lastOffset));
            _node.set("deliveryCount", new ShortNode(_object.deliveryCount));
            return _node;
        }
        public static JsonNode write(AcquiredRecords _object, short _version) {
            return write(_object, _version, true);
        }
    }
    
    public static class LeaderIdAndEpochJsonConverter {
        public static LeaderIdAndEpoch read(JsonNode _node, short _version) {
            LeaderIdAndEpoch _object = new LeaderIdAndEpoch();
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
            JsonNode _errorMessageNode = _node.get("errorMessage");
            if (_errorMessageNode == null) {
                throw new RuntimeException("PartitionData: unable to locate field 'errorMessage', which is mandatory in version " + _version);
            } else {
                if (_errorMessageNode.isNull()) {
                    _object.errorMessage = null;
                } else {
                    if (!_errorMessageNode.isTextual()) {
                        throw new RuntimeException("PartitionData expected a string type, but got " + _node.getNodeType());
                    }
                    _object.errorMessage = _errorMessageNode.asText();
                }
            }
            JsonNode _acknowledgeErrorCodeNode = _node.get("acknowledgeErrorCode");
            if (_acknowledgeErrorCodeNode == null) {
                throw new RuntimeException("PartitionData: unable to locate field 'acknowledgeErrorCode', which is mandatory in version " + _version);
            } else {
                _object.acknowledgeErrorCode = MessageUtil.jsonNodeToShort(_acknowledgeErrorCodeNode, "PartitionData");
            }
            JsonNode _acknowledgeErrorMessageNode = _node.get("acknowledgeErrorMessage");
            if (_acknowledgeErrorMessageNode == null) {
                throw new RuntimeException("PartitionData: unable to locate field 'acknowledgeErrorMessage', which is mandatory in version " + _version);
            } else {
                if (_acknowledgeErrorMessageNode.isNull()) {
                    _object.acknowledgeErrorMessage = null;
                } else {
                    if (!_acknowledgeErrorMessageNode.isTextual()) {
                        throw new RuntimeException("PartitionData expected a string type, but got " + _node.getNodeType());
                    }
                    _object.acknowledgeErrorMessage = _acknowledgeErrorMessageNode.asText();
                }
            }
            JsonNode _currentLeaderNode = _node.get("currentLeader");
            if (_currentLeaderNode == null) {
                throw new RuntimeException("PartitionData: unable to locate field 'currentLeader', which is mandatory in version " + _version);
            } else {
                _object.currentLeader = LeaderIdAndEpochJsonConverter.read(_currentLeaderNode, _version);
            }
            JsonNode _recordsNode = _node.get("records");
            if (_recordsNode == null) {
                throw new RuntimeException("PartitionData: unable to locate field 'records', which is mandatory in version " + _version);
            } else {
                if (_recordsNode.isNull()) {
                    _object.records = null;
                } else {
                    _object.records = MemoryRecords.readableRecords(ByteBuffer.wrap(MessageUtil.jsonNodeToBinary(_recordsNode, "PartitionData")));
                }
            }
            JsonNode _acquiredRecordsNode = _node.get("acquiredRecords");
            if (_acquiredRecordsNode == null) {
                throw new RuntimeException("PartitionData: unable to locate field 'acquiredRecords', which is mandatory in version " + _version);
            } else {
                if (!_acquiredRecordsNode.isArray()) {
                    throw new RuntimeException("PartitionData expected a JSON array, but got " + _node.getNodeType());
                }
                ArrayList<AcquiredRecords> _collection = new ArrayList<AcquiredRecords>(_acquiredRecordsNode.size());
                _object.acquiredRecords = _collection;
                for (JsonNode _element : _acquiredRecordsNode) {
                    _collection.add(AcquiredRecordsJsonConverter.read(_element, _version));
                }
            }
            return _object;
        }
        public static JsonNode write(PartitionData _object, short _version, boolean _serializeRecords) {
            ObjectNode _node = new ObjectNode(JsonNodeFactory.instance);
            _node.set("partitionIndex", new IntNode(_object.partitionIndex));
            _node.set("errorCode", new ShortNode(_object.errorCode));
            if (_object.errorMessage == null) {
                _node.set("errorMessage", NullNode.instance);
            } else {
                _node.set("errorMessage", new TextNode(_object.errorMessage));
            }
            _node.set("acknowledgeErrorCode", new ShortNode(_object.acknowledgeErrorCode));
            if (_object.acknowledgeErrorMessage == null) {
                _node.set("acknowledgeErrorMessage", NullNode.instance);
            } else {
                _node.set("acknowledgeErrorMessage", new TextNode(_object.acknowledgeErrorMessage));
            }
            _node.set("currentLeader", LeaderIdAndEpochJsonConverter.write(_object.currentLeader, _version, _serializeRecords));
            if (_object.records == null) {
                _node.set("records", NullNode.instance);
            } else {
                if (_serializeRecords) {
                    _node.set("records", new BinaryNode(new byte[]{}));
                } else {
                    _node.set("recordsSizeInBytes", new IntNode(_object.records.sizeInBytes()));
                }
            }
            ArrayNode _acquiredRecordsArray = new ArrayNode(JsonNodeFactory.instance);
            for (AcquiredRecords _element : _object.acquiredRecords) {
                _acquiredRecordsArray.add(AcquiredRecordsJsonConverter.write(_element, _version, _serializeRecords));
            }
            _node.set("acquiredRecords", _acquiredRecordsArray);
            return _node;
        }
        public static JsonNode write(PartitionData _object, short _version) {
            return write(_object, _version, true);
        }
    }
    
    public static class ShareFetchableTopicResponseJsonConverter {
        public static ShareFetchableTopicResponse read(JsonNode _node, short _version) {
            ShareFetchableTopicResponse _object = new ShareFetchableTopicResponse();
            JsonNode _topicIdNode = _node.get("topicId");
            if (_topicIdNode == null) {
                throw new RuntimeException("ShareFetchableTopicResponse: unable to locate field 'topicId', which is mandatory in version " + _version);
            } else {
                if (!_topicIdNode.isTextual()) {
                    throw new RuntimeException("ShareFetchableTopicResponse expected a JSON string type, but got " + _node.getNodeType());
                }
                _object.topicId = Uuid.fromString(_topicIdNode.asText());
            }
            JsonNode _partitionsNode = _node.get("partitions");
            if (_partitionsNode == null) {
                throw new RuntimeException("ShareFetchableTopicResponse: unable to locate field 'partitions', which is mandatory in version " + _version);
            } else {
                if (!_partitionsNode.isArray()) {
                    throw new RuntimeException("ShareFetchableTopicResponse expected a JSON array, but got " + _node.getNodeType());
                }
                ArrayList<PartitionData> _collection = new ArrayList<PartitionData>(_partitionsNode.size());
                _object.partitions = _collection;
                for (JsonNode _element : _partitionsNode) {
                    _collection.add(PartitionDataJsonConverter.read(_element, _version));
                }
            }
            return _object;
        }
        public static JsonNode write(ShareFetchableTopicResponse _object, short _version, boolean _serializeRecords) {
            ObjectNode _node = new ObjectNode(JsonNodeFactory.instance);
            _node.set("topicId", new TextNode(_object.topicId.toString()));
            ArrayNode _partitionsArray = new ArrayNode(JsonNodeFactory.instance);
            for (PartitionData _element : _object.partitions) {
                _partitionsArray.add(PartitionDataJsonConverter.write(_element, _version, _serializeRecords));
            }
            _node.set("partitions", _partitionsArray);
            return _node;
        }
        public static JsonNode write(ShareFetchableTopicResponse _object, short _version) {
            return write(_object, _version, true);
        }
    }
}
