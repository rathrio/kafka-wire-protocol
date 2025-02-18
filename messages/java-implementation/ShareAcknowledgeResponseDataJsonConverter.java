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
import com.fasterxml.jackson.databind.node.NullNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.ShortNode;
import com.fasterxml.jackson.databind.node.TextNode;
import java.util.ArrayList;
import org.apache.kafka.common.Uuid;
import org.apache.kafka.common.protocol.MessageUtil;

import static org.apache.kafka.common.message.ShareAcknowledgeResponseData.*;

public class ShareAcknowledgeResponseDataJsonConverter {
    public static ShareAcknowledgeResponseData read(JsonNode _node, short _version) {
        ShareAcknowledgeResponseData _object = new ShareAcknowledgeResponseData();
        JsonNode _throttleTimeMsNode = _node.get("throttleTimeMs");
        if (_throttleTimeMsNode == null) {
            throw new RuntimeException("ShareAcknowledgeResponseData: unable to locate field 'throttleTimeMs', which is mandatory in version " + _version);
        } else {
            _object.throttleTimeMs = MessageUtil.jsonNodeToInt(_throttleTimeMsNode, "ShareAcknowledgeResponseData");
        }
        JsonNode _errorCodeNode = _node.get("errorCode");
        if (_errorCodeNode == null) {
            throw new RuntimeException("ShareAcknowledgeResponseData: unable to locate field 'errorCode', which is mandatory in version " + _version);
        } else {
            _object.errorCode = MessageUtil.jsonNodeToShort(_errorCodeNode, "ShareAcknowledgeResponseData");
        }
        JsonNode _errorMessageNode = _node.get("errorMessage");
        if (_errorMessageNode == null) {
            throw new RuntimeException("ShareAcknowledgeResponseData: unable to locate field 'errorMessage', which is mandatory in version " + _version);
        } else {
            if (_errorMessageNode.isNull()) {
                _object.errorMessage = null;
            } else {
                if (!_errorMessageNode.isTextual()) {
                    throw new RuntimeException("ShareAcknowledgeResponseData expected a string type, but got " + _node.getNodeType());
                }
                _object.errorMessage = _errorMessageNode.asText();
            }
        }
        JsonNode _responsesNode = _node.get("responses");
        if (_responsesNode == null) {
            throw new RuntimeException("ShareAcknowledgeResponseData: unable to locate field 'responses', which is mandatory in version " + _version);
        } else {
            if (!_responsesNode.isArray()) {
                throw new RuntimeException("ShareAcknowledgeResponseData expected a JSON array, but got " + _node.getNodeType());
            }
            ArrayList<ShareAcknowledgeTopicResponse> _collection = new ArrayList<ShareAcknowledgeTopicResponse>(_responsesNode.size());
            _object.responses = _collection;
            for (JsonNode _element : _responsesNode) {
                _collection.add(ShareAcknowledgeTopicResponseJsonConverter.read(_element, _version));
            }
        }
        JsonNode _nodeEndpointsNode = _node.get("nodeEndpoints");
        if (_nodeEndpointsNode == null) {
            throw new RuntimeException("ShareAcknowledgeResponseData: unable to locate field 'nodeEndpoints', which is mandatory in version " + _version);
        } else {
            if (!_nodeEndpointsNode.isArray()) {
                throw new RuntimeException("ShareAcknowledgeResponseData expected a JSON array, but got " + _node.getNodeType());
            }
            NodeEndpointCollection _collection = new NodeEndpointCollection(_nodeEndpointsNode.size());
            _object.nodeEndpoints = _collection;
            for (JsonNode _element : _nodeEndpointsNode) {
                _collection.add(NodeEndpointJsonConverter.read(_element, _version));
            }
        }
        return _object;
    }
    public static JsonNode write(ShareAcknowledgeResponseData _object, short _version, boolean _serializeRecords) {
        ObjectNode _node = new ObjectNode(JsonNodeFactory.instance);
        _node.set("throttleTimeMs", new IntNode(_object.throttleTimeMs));
        _node.set("errorCode", new ShortNode(_object.errorCode));
        if (_object.errorMessage == null) {
            _node.set("errorMessage", NullNode.instance);
        } else {
            _node.set("errorMessage", new TextNode(_object.errorMessage));
        }
        ArrayNode _responsesArray = new ArrayNode(JsonNodeFactory.instance);
        for (ShareAcknowledgeTopicResponse _element : _object.responses) {
            _responsesArray.add(ShareAcknowledgeTopicResponseJsonConverter.write(_element, _version, _serializeRecords));
        }
        _node.set("responses", _responsesArray);
        ArrayNode _nodeEndpointsArray = new ArrayNode(JsonNodeFactory.instance);
        for (NodeEndpoint _element : _object.nodeEndpoints) {
            _nodeEndpointsArray.add(NodeEndpointJsonConverter.write(_element, _version, _serializeRecords));
        }
        _node.set("nodeEndpoints", _nodeEndpointsArray);
        return _node;
    }
    public static JsonNode write(ShareAcknowledgeResponseData _object, short _version) {
        return write(_object, _version, true);
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
            JsonNode _currentLeaderNode = _node.get("currentLeader");
            if (_currentLeaderNode == null) {
                throw new RuntimeException("PartitionData: unable to locate field 'currentLeader', which is mandatory in version " + _version);
            } else {
                _object.currentLeader = LeaderIdAndEpochJsonConverter.read(_currentLeaderNode, _version);
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
            _node.set("currentLeader", LeaderIdAndEpochJsonConverter.write(_object.currentLeader, _version, _serializeRecords));
            return _node;
        }
        public static JsonNode write(PartitionData _object, short _version) {
            return write(_object, _version, true);
        }
    }
    
    public static class ShareAcknowledgeTopicResponseJsonConverter {
        public static ShareAcknowledgeTopicResponse read(JsonNode _node, short _version) {
            ShareAcknowledgeTopicResponse _object = new ShareAcknowledgeTopicResponse();
            JsonNode _topicIdNode = _node.get("topicId");
            if (_topicIdNode == null) {
                throw new RuntimeException("ShareAcknowledgeTopicResponse: unable to locate field 'topicId', which is mandatory in version " + _version);
            } else {
                if (!_topicIdNode.isTextual()) {
                    throw new RuntimeException("ShareAcknowledgeTopicResponse expected a JSON string type, but got " + _node.getNodeType());
                }
                _object.topicId = Uuid.fromString(_topicIdNode.asText());
            }
            JsonNode _partitionsNode = _node.get("partitions");
            if (_partitionsNode == null) {
                throw new RuntimeException("ShareAcknowledgeTopicResponse: unable to locate field 'partitions', which is mandatory in version " + _version);
            } else {
                if (!_partitionsNode.isArray()) {
                    throw new RuntimeException("ShareAcknowledgeTopicResponse expected a JSON array, but got " + _node.getNodeType());
                }
                ArrayList<PartitionData> _collection = new ArrayList<PartitionData>(_partitionsNode.size());
                _object.partitions = _collection;
                for (JsonNode _element : _partitionsNode) {
                    _collection.add(PartitionDataJsonConverter.read(_element, _version));
                }
            }
            return _object;
        }
        public static JsonNode write(ShareAcknowledgeTopicResponse _object, short _version, boolean _serializeRecords) {
            ObjectNode _node = new ObjectNode(JsonNodeFactory.instance);
            _node.set("topicId", new TextNode(_object.topicId.toString()));
            ArrayNode _partitionsArray = new ArrayNode(JsonNodeFactory.instance);
            for (PartitionData _element : _object.partitions) {
                _partitionsArray.add(PartitionDataJsonConverter.write(_element, _version, _serializeRecords));
            }
            _node.set("partitions", _partitionsArray);
            return _node;
        }
        public static JsonNode write(ShareAcknowledgeTopicResponse _object, short _version) {
            return write(_object, _version, true);
        }
    }
}
