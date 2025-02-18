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
import org.apache.kafka.common.errors.UnsupportedVersionException;
import org.apache.kafka.common.protocol.MessageUtil;

import static org.apache.kafka.common.message.ProduceResponseData.*;

public class ProduceResponseDataJsonConverter {
    public static ProduceResponseData read(JsonNode _node, short _version) {
        ProduceResponseData _object = new ProduceResponseData();
        JsonNode _responsesNode = _node.get("responses");
        if (_responsesNode == null) {
            throw new RuntimeException("ProduceResponseData: unable to locate field 'responses', which is mandatory in version " + _version);
        } else {
            if (!_responsesNode.isArray()) {
                throw new RuntimeException("ProduceResponseData expected a JSON array, but got " + _node.getNodeType());
            }
            TopicProduceResponseCollection _collection = new TopicProduceResponseCollection(_responsesNode.size());
            _object.responses = _collection;
            for (JsonNode _element : _responsesNode) {
                _collection.add(TopicProduceResponseJsonConverter.read(_element, _version));
            }
        }
        JsonNode _throttleTimeMsNode = _node.get("throttleTimeMs");
        if (_throttleTimeMsNode == null) {
            throw new RuntimeException("ProduceResponseData: unable to locate field 'throttleTimeMs', which is mandatory in version " + _version);
        } else {
            _object.throttleTimeMs = MessageUtil.jsonNodeToInt(_throttleTimeMsNode, "ProduceResponseData");
        }
        JsonNode _nodeEndpointsNode = _node.get("nodeEndpoints");
        if (_nodeEndpointsNode == null) {
            _object.nodeEndpoints = new NodeEndpointCollection(0);
        } else {
            if (!_nodeEndpointsNode.isArray()) {
                throw new RuntimeException("ProduceResponseData expected a JSON array, but got " + _node.getNodeType());
            }
            NodeEndpointCollection _collection = new NodeEndpointCollection(_nodeEndpointsNode.size());
            _object.nodeEndpoints = _collection;
            for (JsonNode _element : _nodeEndpointsNode) {
                _collection.add(NodeEndpointJsonConverter.read(_element, _version));
            }
        }
        return _object;
    }
    public static JsonNode write(ProduceResponseData _object, short _version, boolean _serializeRecords) {
        ObjectNode _node = new ObjectNode(JsonNodeFactory.instance);
        ArrayNode _responsesArray = new ArrayNode(JsonNodeFactory.instance);
        for (TopicProduceResponse _element : _object.responses) {
            _responsesArray.add(TopicProduceResponseJsonConverter.write(_element, _version, _serializeRecords));
        }
        _node.set("responses", _responsesArray);
        _node.set("throttleTimeMs", new IntNode(_object.throttleTimeMs));
        if (_version >= 10) {
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
    public static JsonNode write(ProduceResponseData _object, short _version) {
        return write(_object, _version, true);
    }
    
    public static class BatchIndexAndErrorMessageJsonConverter {
        public static BatchIndexAndErrorMessage read(JsonNode _node, short _version) {
            BatchIndexAndErrorMessage _object = new BatchIndexAndErrorMessage();
            if (_version < 8) {
                throw new UnsupportedVersionException("Can't read version " + _version + " of BatchIndexAndErrorMessage");
            }
            JsonNode _batchIndexNode = _node.get("batchIndex");
            if (_batchIndexNode == null) {
                throw new RuntimeException("BatchIndexAndErrorMessage: unable to locate field 'batchIndex', which is mandatory in version " + _version);
            } else {
                _object.batchIndex = MessageUtil.jsonNodeToInt(_batchIndexNode, "BatchIndexAndErrorMessage");
            }
            JsonNode _batchIndexErrorMessageNode = _node.get("batchIndexErrorMessage");
            if (_batchIndexErrorMessageNode == null) {
                throw new RuntimeException("BatchIndexAndErrorMessage: unable to locate field 'batchIndexErrorMessage', which is mandatory in version " + _version);
            } else {
                if (_batchIndexErrorMessageNode.isNull()) {
                    _object.batchIndexErrorMessage = null;
                } else {
                    if (!_batchIndexErrorMessageNode.isTextual()) {
                        throw new RuntimeException("BatchIndexAndErrorMessage expected a string type, but got " + _node.getNodeType());
                    }
                    _object.batchIndexErrorMessage = _batchIndexErrorMessageNode.asText();
                }
            }
            return _object;
        }
        public static JsonNode write(BatchIndexAndErrorMessage _object, short _version, boolean _serializeRecords) {
            if (_version < 8) {
                throw new UnsupportedVersionException("Can't write version " + _version + " of BatchIndexAndErrorMessage");
            }
            ObjectNode _node = new ObjectNode(JsonNodeFactory.instance);
            _node.set("batchIndex", new IntNode(_object.batchIndex));
            if (_object.batchIndexErrorMessage == null) {
                _node.set("batchIndexErrorMessage", NullNode.instance);
            } else {
                _node.set("batchIndexErrorMessage", new TextNode(_object.batchIndexErrorMessage));
            }
            return _node;
        }
        public static JsonNode write(BatchIndexAndErrorMessage _object, short _version) {
            return write(_object, _version, true);
        }
    }
    
    public static class LeaderIdAndEpochJsonConverter {
        public static LeaderIdAndEpoch read(JsonNode _node, short _version) {
            LeaderIdAndEpoch _object = new LeaderIdAndEpoch();
            if (_version < 10) {
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
            if (_version < 10) {
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
            if (_version < 10) {
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
            if (_version < 10) {
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
    
    public static class PartitionProduceResponseJsonConverter {
        public static PartitionProduceResponse read(JsonNode _node, short _version) {
            PartitionProduceResponse _object = new PartitionProduceResponse();
            JsonNode _indexNode = _node.get("index");
            if (_indexNode == null) {
                throw new RuntimeException("PartitionProduceResponse: unable to locate field 'index', which is mandatory in version " + _version);
            } else {
                _object.index = MessageUtil.jsonNodeToInt(_indexNode, "PartitionProduceResponse");
            }
            JsonNode _errorCodeNode = _node.get("errorCode");
            if (_errorCodeNode == null) {
                throw new RuntimeException("PartitionProduceResponse: unable to locate field 'errorCode', which is mandatory in version " + _version);
            } else {
                _object.errorCode = MessageUtil.jsonNodeToShort(_errorCodeNode, "PartitionProduceResponse");
            }
            JsonNode _baseOffsetNode = _node.get("baseOffset");
            if (_baseOffsetNode == null) {
                throw new RuntimeException("PartitionProduceResponse: unable to locate field 'baseOffset', which is mandatory in version " + _version);
            } else {
                _object.baseOffset = MessageUtil.jsonNodeToLong(_baseOffsetNode, "PartitionProduceResponse");
            }
            JsonNode _logAppendTimeMsNode = _node.get("logAppendTimeMs");
            if (_logAppendTimeMsNode == null) {
                throw new RuntimeException("PartitionProduceResponse: unable to locate field 'logAppendTimeMs', which is mandatory in version " + _version);
            } else {
                _object.logAppendTimeMs = MessageUtil.jsonNodeToLong(_logAppendTimeMsNode, "PartitionProduceResponse");
            }
            JsonNode _logStartOffsetNode = _node.get("logStartOffset");
            if (_logStartOffsetNode == null) {
                if (_version >= 5) {
                    throw new RuntimeException("PartitionProduceResponse: unable to locate field 'logStartOffset', which is mandatory in version " + _version);
                } else {
                    _object.logStartOffset = -1L;
                }
            } else {
                _object.logStartOffset = MessageUtil.jsonNodeToLong(_logStartOffsetNode, "PartitionProduceResponse");
            }
            JsonNode _recordErrorsNode = _node.get("recordErrors");
            if (_recordErrorsNode == null) {
                if (_version >= 8) {
                    throw new RuntimeException("PartitionProduceResponse: unable to locate field 'recordErrors', which is mandatory in version " + _version);
                } else {
                    _object.recordErrors = new ArrayList<BatchIndexAndErrorMessage>(0);
                }
            } else {
                if (!_recordErrorsNode.isArray()) {
                    throw new RuntimeException("PartitionProduceResponse expected a JSON array, but got " + _node.getNodeType());
                }
                ArrayList<BatchIndexAndErrorMessage> _collection = new ArrayList<BatchIndexAndErrorMessage>(_recordErrorsNode.size());
                _object.recordErrors = _collection;
                for (JsonNode _element : _recordErrorsNode) {
                    _collection.add(BatchIndexAndErrorMessageJsonConverter.read(_element, _version));
                }
            }
            JsonNode _errorMessageNode = _node.get("errorMessage");
            if (_errorMessageNode == null) {
                if (_version >= 8) {
                    throw new RuntimeException("PartitionProduceResponse: unable to locate field 'errorMessage', which is mandatory in version " + _version);
                } else {
                    _object.errorMessage = null;
                }
            } else {
                if (_errorMessageNode.isNull()) {
                    _object.errorMessage = null;
                } else {
                    if (!_errorMessageNode.isTextual()) {
                        throw new RuntimeException("PartitionProduceResponse expected a string type, but got " + _node.getNodeType());
                    }
                    _object.errorMessage = _errorMessageNode.asText();
                }
            }
            JsonNode _currentLeaderNode = _node.get("currentLeader");
            if (_currentLeaderNode == null) {
                _object.currentLeader = new LeaderIdAndEpoch();
            } else {
                _object.currentLeader = LeaderIdAndEpochJsonConverter.read(_currentLeaderNode, _version);
            }
            return _object;
        }
        public static JsonNode write(PartitionProduceResponse _object, short _version, boolean _serializeRecords) {
            ObjectNode _node = new ObjectNode(JsonNodeFactory.instance);
            _node.set("index", new IntNode(_object.index));
            _node.set("errorCode", new ShortNode(_object.errorCode));
            _node.set("baseOffset", new LongNode(_object.baseOffset));
            _node.set("logAppendTimeMs", new LongNode(_object.logAppendTimeMs));
            if (_version >= 5) {
                _node.set("logStartOffset", new LongNode(_object.logStartOffset));
            }
            if (_version >= 8) {
                ArrayNode _recordErrorsArray = new ArrayNode(JsonNodeFactory.instance);
                for (BatchIndexAndErrorMessage _element : _object.recordErrors) {
                    _recordErrorsArray.add(BatchIndexAndErrorMessageJsonConverter.write(_element, _version, _serializeRecords));
                }
                _node.set("recordErrors", _recordErrorsArray);
            }
            if (_version >= 8) {
                if (_object.errorMessage == null) {
                    _node.set("errorMessage", NullNode.instance);
                } else {
                    _node.set("errorMessage", new TextNode(_object.errorMessage));
                }
            }
            if (_version >= 10) {
                if (!_object.currentLeader.equals(new LeaderIdAndEpoch())) {
                    _node.set("currentLeader", LeaderIdAndEpochJsonConverter.write(_object.currentLeader, _version, _serializeRecords));
                }
            } else {
                if (!_object.currentLeader.equals(new LeaderIdAndEpoch())) {
                    throw new UnsupportedVersionException("Attempted to write a non-default currentLeader at version " + _version);
                }
            }
            return _node;
        }
        public static JsonNode write(PartitionProduceResponse _object, short _version) {
            return write(_object, _version, true);
        }
    }
    
    public static class TopicProduceResponseJsonConverter {
        public static TopicProduceResponse read(JsonNode _node, short _version) {
            TopicProduceResponse _object = new TopicProduceResponse();
            JsonNode _nameNode = _node.get("name");
            if (_nameNode == null) {
                throw new RuntimeException("TopicProduceResponse: unable to locate field 'name', which is mandatory in version " + _version);
            } else {
                if (!_nameNode.isTextual()) {
                    throw new RuntimeException("TopicProduceResponse expected a string type, but got " + _node.getNodeType());
                }
                _object.name = _nameNode.asText();
            }
            JsonNode _partitionResponsesNode = _node.get("partitionResponses");
            if (_partitionResponsesNode == null) {
                throw new RuntimeException("TopicProduceResponse: unable to locate field 'partitionResponses', which is mandatory in version " + _version);
            } else {
                if (!_partitionResponsesNode.isArray()) {
                    throw new RuntimeException("TopicProduceResponse expected a JSON array, but got " + _node.getNodeType());
                }
                ArrayList<PartitionProduceResponse> _collection = new ArrayList<PartitionProduceResponse>(_partitionResponsesNode.size());
                _object.partitionResponses = _collection;
                for (JsonNode _element : _partitionResponsesNode) {
                    _collection.add(PartitionProduceResponseJsonConverter.read(_element, _version));
                }
            }
            return _object;
        }
        public static JsonNode write(TopicProduceResponse _object, short _version, boolean _serializeRecords) {
            ObjectNode _node = new ObjectNode(JsonNodeFactory.instance);
            _node.set("name", new TextNode(_object.name));
            ArrayNode _partitionResponsesArray = new ArrayNode(JsonNodeFactory.instance);
            for (PartitionProduceResponse _element : _object.partitionResponses) {
                _partitionResponsesArray.add(PartitionProduceResponseJsonConverter.write(_element, _version, _serializeRecords));
            }
            _node.set("partitionResponses", _partitionResponsesArray);
            return _node;
        }
        public static JsonNode write(TopicProduceResponse _object, short _version) {
            return write(_object, _version, true);
        }
    }
}
