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
import com.fasterxml.jackson.databind.node.TextNode;
import org.apache.kafka.common.Uuid;
import org.apache.kafka.common.protocol.MessageUtil;

import static org.apache.kafka.common.message.AddRaftVoterRequestData.*;

public class AddRaftVoterRequestDataJsonConverter {
    public static AddRaftVoterRequestData read(JsonNode _node, short _version) {
        AddRaftVoterRequestData _object = new AddRaftVoterRequestData();
        JsonNode _clusterIdNode = _node.get("clusterId");
        if (_clusterIdNode == null) {
            throw new RuntimeException("AddRaftVoterRequestData: unable to locate field 'clusterId', which is mandatory in version " + _version);
        } else {
            if (_clusterIdNode.isNull()) {
                _object.clusterId = null;
            } else {
                if (!_clusterIdNode.isTextual()) {
                    throw new RuntimeException("AddRaftVoterRequestData expected a string type, but got " + _node.getNodeType());
                }
                _object.clusterId = _clusterIdNode.asText();
            }
        }
        JsonNode _timeoutMsNode = _node.get("timeoutMs");
        if (_timeoutMsNode == null) {
            throw new RuntimeException("AddRaftVoterRequestData: unable to locate field 'timeoutMs', which is mandatory in version " + _version);
        } else {
            _object.timeoutMs = MessageUtil.jsonNodeToInt(_timeoutMsNode, "AddRaftVoterRequestData");
        }
        JsonNode _voterIdNode = _node.get("voterId");
        if (_voterIdNode == null) {
            throw new RuntimeException("AddRaftVoterRequestData: unable to locate field 'voterId', which is mandatory in version " + _version);
        } else {
            _object.voterId = MessageUtil.jsonNodeToInt(_voterIdNode, "AddRaftVoterRequestData");
        }
        JsonNode _voterDirectoryIdNode = _node.get("voterDirectoryId");
        if (_voterDirectoryIdNode == null) {
            throw new RuntimeException("AddRaftVoterRequestData: unable to locate field 'voterDirectoryId', which is mandatory in version " + _version);
        } else {
            if (!_voterDirectoryIdNode.isTextual()) {
                throw new RuntimeException("AddRaftVoterRequestData expected a JSON string type, but got " + _node.getNodeType());
            }
            _object.voterDirectoryId = Uuid.fromString(_voterDirectoryIdNode.asText());
        }
        JsonNode _listenersNode = _node.get("listeners");
        if (_listenersNode == null) {
            throw new RuntimeException("AddRaftVoterRequestData: unable to locate field 'listeners', which is mandatory in version " + _version);
        } else {
            if (!_listenersNode.isArray()) {
                throw new RuntimeException("AddRaftVoterRequestData expected a JSON array, but got " + _node.getNodeType());
            }
            ListenerCollection _collection = new ListenerCollection(_listenersNode.size());
            _object.listeners = _collection;
            for (JsonNode _element : _listenersNode) {
                _collection.add(ListenerJsonConverter.read(_element, _version));
            }
        }
        return _object;
    }
    public static JsonNode write(AddRaftVoterRequestData _object, short _version, boolean _serializeRecords) {
        ObjectNode _node = new ObjectNode(JsonNodeFactory.instance);
        if (_object.clusterId == null) {
            _node.set("clusterId", NullNode.instance);
        } else {
            _node.set("clusterId", new TextNode(_object.clusterId));
        }
        _node.set("timeoutMs", new IntNode(_object.timeoutMs));
        _node.set("voterId", new IntNode(_object.voterId));
        _node.set("voterDirectoryId", new TextNode(_object.voterDirectoryId.toString()));
        ArrayNode _listenersArray = new ArrayNode(JsonNodeFactory.instance);
        for (Listener _element : _object.listeners) {
            _listenersArray.add(ListenerJsonConverter.write(_element, _version, _serializeRecords));
        }
        _node.set("listeners", _listenersArray);
        return _node;
    }
    public static JsonNode write(AddRaftVoterRequestData _object, short _version) {
        return write(_object, _version, true);
    }
    
    public static class ListenerJsonConverter {
        public static Listener read(JsonNode _node, short _version) {
            Listener _object = new Listener();
            JsonNode _nameNode = _node.get("name");
            if (_nameNode == null) {
                throw new RuntimeException("Listener: unable to locate field 'name', which is mandatory in version " + _version);
            } else {
                if (!_nameNode.isTextual()) {
                    throw new RuntimeException("Listener expected a string type, but got " + _node.getNodeType());
                }
                _object.name = _nameNode.asText();
            }
            JsonNode _hostNode = _node.get("host");
            if (_hostNode == null) {
                throw new RuntimeException("Listener: unable to locate field 'host', which is mandatory in version " + _version);
            } else {
                if (!_hostNode.isTextual()) {
                    throw new RuntimeException("Listener expected a string type, but got " + _node.getNodeType());
                }
                _object.host = _hostNode.asText();
            }
            JsonNode _portNode = _node.get("port");
            if (_portNode == null) {
                throw new RuntimeException("Listener: unable to locate field 'port', which is mandatory in version " + _version);
            } else {
                _object.port = MessageUtil.jsonNodeToUnsignedShort(_portNode, "Listener");
            }
            return _object;
        }
        public static JsonNode write(Listener _object, short _version, boolean _serializeRecords) {
            ObjectNode _node = new ObjectNode(JsonNodeFactory.instance);
            _node.set("name", new TextNode(_object.name));
            _node.set("host", new TextNode(_object.host));
            _node.set("port", new IntNode(_object.port));
            return _node;
        }
        public static JsonNode write(Listener _object, short _version) {
            return write(_object, _version, true);
        }
    }
}
