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
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.ShortNode;
import com.fasterxml.jackson.databind.node.TextNode;
import java.util.ArrayList;
import org.apache.kafka.common.protocol.MessageUtil;

import static org.apache.kafka.common.message.ListClientMetricsResourcesResponseData.*;

public class ListClientMetricsResourcesResponseDataJsonConverter {
    public static ListClientMetricsResourcesResponseData read(JsonNode _node, short _version) {
        ListClientMetricsResourcesResponseData _object = new ListClientMetricsResourcesResponseData();
        JsonNode _throttleTimeMsNode = _node.get("throttleTimeMs");
        if (_throttleTimeMsNode == null) {
            throw new RuntimeException("ListClientMetricsResourcesResponseData: unable to locate field 'throttleTimeMs', which is mandatory in version " + _version);
        } else {
            _object.throttleTimeMs = MessageUtil.jsonNodeToInt(_throttleTimeMsNode, "ListClientMetricsResourcesResponseData");
        }
        JsonNode _errorCodeNode = _node.get("errorCode");
        if (_errorCodeNode == null) {
            throw new RuntimeException("ListClientMetricsResourcesResponseData: unable to locate field 'errorCode', which is mandatory in version " + _version);
        } else {
            _object.errorCode = MessageUtil.jsonNodeToShort(_errorCodeNode, "ListClientMetricsResourcesResponseData");
        }
        JsonNode _clientMetricsResourcesNode = _node.get("clientMetricsResources");
        if (_clientMetricsResourcesNode == null) {
            throw new RuntimeException("ListClientMetricsResourcesResponseData: unable to locate field 'clientMetricsResources', which is mandatory in version " + _version);
        } else {
            if (!_clientMetricsResourcesNode.isArray()) {
                throw new RuntimeException("ListClientMetricsResourcesResponseData expected a JSON array, but got " + _node.getNodeType());
            }
            ArrayList<ClientMetricsResource> _collection = new ArrayList<ClientMetricsResource>(_clientMetricsResourcesNode.size());
            _object.clientMetricsResources = _collection;
            for (JsonNode _element : _clientMetricsResourcesNode) {
                _collection.add(ClientMetricsResourceJsonConverter.read(_element, _version));
            }
        }
        return _object;
    }
    public static JsonNode write(ListClientMetricsResourcesResponseData _object, short _version, boolean _serializeRecords) {
        ObjectNode _node = new ObjectNode(JsonNodeFactory.instance);
        _node.set("throttleTimeMs", new IntNode(_object.throttleTimeMs));
        _node.set("errorCode", new ShortNode(_object.errorCode));
        ArrayNode _clientMetricsResourcesArray = new ArrayNode(JsonNodeFactory.instance);
        for (ClientMetricsResource _element : _object.clientMetricsResources) {
            _clientMetricsResourcesArray.add(ClientMetricsResourceJsonConverter.write(_element, _version, _serializeRecords));
        }
        _node.set("clientMetricsResources", _clientMetricsResourcesArray);
        return _node;
    }
    public static JsonNode write(ListClientMetricsResourcesResponseData _object, short _version) {
        return write(_object, _version, true);
    }
    
    public static class ClientMetricsResourceJsonConverter {
        public static ClientMetricsResource read(JsonNode _node, short _version) {
            ClientMetricsResource _object = new ClientMetricsResource();
            JsonNode _nameNode = _node.get("name");
            if (_nameNode == null) {
                throw new RuntimeException("ClientMetricsResource: unable to locate field 'name', which is mandatory in version " + _version);
            } else {
                if (!_nameNode.isTextual()) {
                    throw new RuntimeException("ClientMetricsResource expected a string type, but got " + _node.getNodeType());
                }
                _object.name = _nameNode.asText();
            }
            return _object;
        }
        public static JsonNode write(ClientMetricsResource _object, short _version, boolean _serializeRecords) {
            ObjectNode _node = new ObjectNode(JsonNodeFactory.instance);
            _node.set("name", new TextNode(_object.name));
            return _node;
        }
        public static JsonNode write(ClientMetricsResource _object, short _version) {
            return write(_object, _version, true);
        }
    }
}
