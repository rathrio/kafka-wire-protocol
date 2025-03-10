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

import java.util.EnumSet;
import org.apache.kafka.common.errors.UnsupportedVersionException;
import org.apache.kafka.common.protocol.ApiMessage;
import org.apache.kafka.common.protocol.types.Schema;

public enum ApiMessageType {
    PRODUCE("Produce", (short) 0, ProduceRequestData.SCHEMAS, ProduceResponseData.SCHEMAS, (short) 3, (short) 12, (short) 0, (short) -1, EnumSet.of(ListenerType.BROKER), false),
    FETCH("Fetch", (short) 1, FetchRequestData.SCHEMAS, FetchResponseData.SCHEMAS, (short) 4, (short) 17, (short) 0, (short) -1, EnumSet.of(ListenerType.BROKER, ListenerType.CONTROLLER), false),
    LIST_OFFSETS("ListOffsets", (short) 2, ListOffsetsRequestData.SCHEMAS, ListOffsetsResponseData.SCHEMAS, (short) 1, (short) 10, (short) 0, (short) -1, EnumSet.of(ListenerType.BROKER), false),
    METADATA("Metadata", (short) 3, MetadataRequestData.SCHEMAS, MetadataResponseData.SCHEMAS, (short) 0, (short) 13, (short) 0, (short) -1, EnumSet.of(ListenerType.BROKER), false),
    LEADER_AND_ISR("LeaderAndIsr", (short) 4, new Schema[0], new Schema[0], (short) 0, (short) -1, (short) 0, (short) -1, EnumSet.noneOf(ListenerType.class), false),
    STOP_REPLICA("StopReplica", (short) 5, new Schema[0], new Schema[0], (short) 0, (short) -1, (short) 0, (short) -1, EnumSet.noneOf(ListenerType.class), false),
    UPDATE_METADATA("UpdateMetadata", (short) 6, new Schema[0], new Schema[0], (short) 0, (short) -1, (short) 0, (short) -1, EnumSet.noneOf(ListenerType.class), false),
    CONTROLLED_SHUTDOWN("ControlledShutdown", (short) 7, new Schema[0], new Schema[0], (short) 0, (short) -1, (short) 0, (short) -1, EnumSet.noneOf(ListenerType.class), false),
    OFFSET_COMMIT("OffsetCommit", (short) 8, OffsetCommitRequestData.SCHEMAS, OffsetCommitResponseData.SCHEMAS, (short) 2, (short) 9, (short) 0, (short) -1, EnumSet.of(ListenerType.BROKER), false),
    OFFSET_FETCH("OffsetFetch", (short) 9, OffsetFetchRequestData.SCHEMAS, OffsetFetchResponseData.SCHEMAS, (short) 1, (short) 9, (short) 0, (short) -1, EnumSet.of(ListenerType.BROKER), false),
    FIND_COORDINATOR("FindCoordinator", (short) 10, FindCoordinatorRequestData.SCHEMAS, FindCoordinatorResponseData.SCHEMAS, (short) 0, (short) 6, (short) 0, (short) -1, EnumSet.of(ListenerType.BROKER), false),
    JOIN_GROUP("JoinGroup", (short) 11, JoinGroupRequestData.SCHEMAS, JoinGroupResponseData.SCHEMAS, (short) 2, (short) 9, (short) 0, (short) -1, EnumSet.of(ListenerType.BROKER), false),
    HEARTBEAT("Heartbeat", (short) 12, HeartbeatRequestData.SCHEMAS, HeartbeatResponseData.SCHEMAS, (short) 0, (short) 4, (short) 0, (short) -1, EnumSet.of(ListenerType.BROKER), false),
    LEAVE_GROUP("LeaveGroup", (short) 13, LeaveGroupRequestData.SCHEMAS, LeaveGroupResponseData.SCHEMAS, (short) 0, (short) 5, (short) 0, (short) -1, EnumSet.of(ListenerType.BROKER), false),
    SYNC_GROUP("SyncGroup", (short) 14, SyncGroupRequestData.SCHEMAS, SyncGroupResponseData.SCHEMAS, (short) 0, (short) 5, (short) 0, (short) -1, EnumSet.of(ListenerType.BROKER), false),
    DESCRIBE_GROUPS("DescribeGroups", (short) 15, DescribeGroupsRequestData.SCHEMAS, DescribeGroupsResponseData.SCHEMAS, (short) 0, (short) 6, (short) 0, (short) -1, EnumSet.of(ListenerType.BROKER), false),
    LIST_GROUPS("ListGroups", (short) 16, ListGroupsRequestData.SCHEMAS, ListGroupsResponseData.SCHEMAS, (short) 0, (short) 5, (short) 0, (short) -1, EnumSet.of(ListenerType.BROKER), false),
    SASL_HANDSHAKE("SaslHandshake", (short) 17, SaslHandshakeRequestData.SCHEMAS, SaslHandshakeResponseData.SCHEMAS, (short) 0, (short) 1, (short) 0, (short) -1, EnumSet.of(ListenerType.BROKER, ListenerType.CONTROLLER), false),
    API_VERSIONS("ApiVersions", (short) 18, ApiVersionsRequestData.SCHEMAS, ApiVersionsResponseData.SCHEMAS, (short) 0, (short) 4, (short) 0, (short) -1, EnumSet.of(ListenerType.BROKER, ListenerType.CONTROLLER), false),
    CREATE_TOPICS("CreateTopics", (short) 19, CreateTopicsRequestData.SCHEMAS, CreateTopicsResponseData.SCHEMAS, (short) 2, (short) 7, (short) 0, (short) -1, EnumSet.of(ListenerType.BROKER, ListenerType.CONTROLLER), false),
    DELETE_TOPICS("DeleteTopics", (short) 20, DeleteTopicsRequestData.SCHEMAS, DeleteTopicsResponseData.SCHEMAS, (short) 1, (short) 6, (short) 0, (short) -1, EnumSet.of(ListenerType.BROKER, ListenerType.CONTROLLER), false),
    DELETE_RECORDS("DeleteRecords", (short) 21, DeleteRecordsRequestData.SCHEMAS, DeleteRecordsResponseData.SCHEMAS, (short) 0, (short) 2, (short) 0, (short) -1, EnumSet.of(ListenerType.BROKER), false),
    INIT_PRODUCER_ID("InitProducerId", (short) 22, InitProducerIdRequestData.SCHEMAS, InitProducerIdResponseData.SCHEMAS, (short) 0, (short) 5, (short) 0, (short) -1, EnumSet.of(ListenerType.BROKER), false),
    OFFSET_FOR_LEADER_EPOCH("OffsetForLeaderEpoch", (short) 23, OffsetForLeaderEpochRequestData.SCHEMAS, OffsetForLeaderEpochResponseData.SCHEMAS, (short) 2, (short) 4, (short) 0, (short) -1, EnumSet.of(ListenerType.BROKER), false),
    ADD_PARTITIONS_TO_TXN("AddPartitionsToTxn", (short) 24, AddPartitionsToTxnRequestData.SCHEMAS, AddPartitionsToTxnResponseData.SCHEMAS, (short) 0, (short) 5, (short) 0, (short) -1, EnumSet.of(ListenerType.BROKER), false),
    ADD_OFFSETS_TO_TXN("AddOffsetsToTxn", (short) 25, AddOffsetsToTxnRequestData.SCHEMAS, AddOffsetsToTxnResponseData.SCHEMAS, (short) 0, (short) 4, (short) 0, (short) -1, EnumSet.of(ListenerType.BROKER), false),
    END_TXN("EndTxn", (short) 26, EndTxnRequestData.SCHEMAS, EndTxnResponseData.SCHEMAS, (short) 0, (short) 5, (short) 0, (short) -1, EnumSet.of(ListenerType.BROKER), false),
    WRITE_TXN_MARKERS("WriteTxnMarkers", (short) 27, WriteTxnMarkersRequestData.SCHEMAS, WriteTxnMarkersResponseData.SCHEMAS, (short) 1, (short) 1, (short) 0, (short) -1, EnumSet.of(ListenerType.BROKER), false),
    TXN_OFFSET_COMMIT("TxnOffsetCommit", (short) 28, TxnOffsetCommitRequestData.SCHEMAS, TxnOffsetCommitResponseData.SCHEMAS, (short) 0, (short) 5, (short) 0, (short) -1, EnumSet.of(ListenerType.BROKER), false),
    DESCRIBE_ACLS("DescribeAcls", (short) 29, DescribeAclsRequestData.SCHEMAS, DescribeAclsResponseData.SCHEMAS, (short) 1, (short) 3, (short) 0, (short) -1, EnumSet.of(ListenerType.BROKER, ListenerType.CONTROLLER), false),
    CREATE_ACLS("CreateAcls", (short) 30, CreateAclsRequestData.SCHEMAS, CreateAclsResponseData.SCHEMAS, (short) 1, (short) 3, (short) 0, (short) -1, EnumSet.of(ListenerType.BROKER, ListenerType.CONTROLLER), false),
    DELETE_ACLS("DeleteAcls", (short) 31, DeleteAclsRequestData.SCHEMAS, DeleteAclsResponseData.SCHEMAS, (short) 1, (short) 3, (short) 0, (short) -1, EnumSet.of(ListenerType.BROKER, ListenerType.CONTROLLER), false),
    DESCRIBE_CONFIGS("DescribeConfigs", (short) 32, DescribeConfigsRequestData.SCHEMAS, DescribeConfigsResponseData.SCHEMAS, (short) 1, (short) 4, (short) 0, (short) -1, EnumSet.of(ListenerType.BROKER, ListenerType.CONTROLLER), false),
    ALTER_CONFIGS("AlterConfigs", (short) 33, AlterConfigsRequestData.SCHEMAS, AlterConfigsResponseData.SCHEMAS, (short) 0, (short) 2, (short) 0, (short) -1, EnumSet.of(ListenerType.BROKER, ListenerType.CONTROLLER), false),
    ALTER_REPLICA_LOG_DIRS("AlterReplicaLogDirs", (short) 34, AlterReplicaLogDirsRequestData.SCHEMAS, AlterReplicaLogDirsResponseData.SCHEMAS, (short) 1, (short) 2, (short) 0, (short) -1, EnumSet.of(ListenerType.BROKER), false),
    DESCRIBE_LOG_DIRS("DescribeLogDirs", (short) 35, DescribeLogDirsRequestData.SCHEMAS, DescribeLogDirsResponseData.SCHEMAS, (short) 1, (short) 4, (short) 0, (short) -1, EnumSet.of(ListenerType.BROKER), false),
    SASL_AUTHENTICATE("SaslAuthenticate", (short) 36, SaslAuthenticateRequestData.SCHEMAS, SaslAuthenticateResponseData.SCHEMAS, (short) 0, (short) 2, (short) 0, (short) -1, EnumSet.of(ListenerType.BROKER, ListenerType.CONTROLLER), false),
    CREATE_PARTITIONS("CreatePartitions", (short) 37, CreatePartitionsRequestData.SCHEMAS, CreatePartitionsResponseData.SCHEMAS, (short) 0, (short) 3, (short) 0, (short) -1, EnumSet.of(ListenerType.BROKER, ListenerType.CONTROLLER), false),
    CREATE_DELEGATION_TOKEN("CreateDelegationToken", (short) 38, CreateDelegationTokenRequestData.SCHEMAS, CreateDelegationTokenResponseData.SCHEMAS, (short) 1, (short) 3, (short) 0, (short) -1, EnumSet.of(ListenerType.BROKER, ListenerType.CONTROLLER), false),
    RENEW_DELEGATION_TOKEN("RenewDelegationToken", (short) 39, RenewDelegationTokenRequestData.SCHEMAS, RenewDelegationTokenResponseData.SCHEMAS, (short) 1, (short) 2, (short) 0, (short) -1, EnumSet.of(ListenerType.BROKER, ListenerType.CONTROLLER), false),
    EXPIRE_DELEGATION_TOKEN("ExpireDelegationToken", (short) 40, ExpireDelegationTokenRequestData.SCHEMAS, ExpireDelegationTokenResponseData.SCHEMAS, (short) 1, (short) 2, (short) 0, (short) -1, EnumSet.of(ListenerType.BROKER, ListenerType.CONTROLLER), false),
    DESCRIBE_DELEGATION_TOKEN("DescribeDelegationToken", (short) 41, DescribeDelegationTokenRequestData.SCHEMAS, DescribeDelegationTokenResponseData.SCHEMAS, (short) 1, (short) 3, (short) 0, (short) -1, EnumSet.of(ListenerType.BROKER, ListenerType.CONTROLLER), false),
    DELETE_GROUPS("DeleteGroups", (short) 42, DeleteGroupsRequestData.SCHEMAS, DeleteGroupsResponseData.SCHEMAS, (short) 0, (short) 2, (short) 0, (short) -1, EnumSet.of(ListenerType.BROKER), false),
    ELECT_LEADERS("ElectLeaders", (short) 43, ElectLeadersRequestData.SCHEMAS, ElectLeadersResponseData.SCHEMAS, (short) 0, (short) 2, (short) 0, (short) -1, EnumSet.of(ListenerType.BROKER, ListenerType.CONTROLLER), false),
    INCREMENTAL_ALTER_CONFIGS("IncrementalAlterConfigs", (short) 44, IncrementalAlterConfigsRequestData.SCHEMAS, IncrementalAlterConfigsResponseData.SCHEMAS, (short) 0, (short) 1, (short) 0, (short) -1, EnumSet.of(ListenerType.BROKER, ListenerType.CONTROLLER), false),
    ALTER_PARTITION_REASSIGNMENTS("AlterPartitionReassignments", (short) 45, AlterPartitionReassignmentsRequestData.SCHEMAS, AlterPartitionReassignmentsResponseData.SCHEMAS, (short) 0, (short) 0, (short) 0, (short) -1, EnumSet.of(ListenerType.BROKER, ListenerType.CONTROLLER), false),
    LIST_PARTITION_REASSIGNMENTS("ListPartitionReassignments", (short) 46, ListPartitionReassignmentsRequestData.SCHEMAS, ListPartitionReassignmentsResponseData.SCHEMAS, (short) 0, (short) 0, (short) 0, (short) -1, EnumSet.of(ListenerType.BROKER, ListenerType.CONTROLLER), false),
    OFFSET_DELETE("OffsetDelete", (short) 47, OffsetDeleteRequestData.SCHEMAS, OffsetDeleteResponseData.SCHEMAS, (short) 0, (short) 0, (short) 0, (short) -1, EnumSet.of(ListenerType.BROKER), false),
    DESCRIBE_CLIENT_QUOTAS("DescribeClientQuotas", (short) 48, DescribeClientQuotasRequestData.SCHEMAS, DescribeClientQuotasResponseData.SCHEMAS, (short) 0, (short) 1, (short) 0, (short) -1, EnumSet.of(ListenerType.BROKER), false),
    ALTER_CLIENT_QUOTAS("AlterClientQuotas", (short) 49, AlterClientQuotasRequestData.SCHEMAS, AlterClientQuotasResponseData.SCHEMAS, (short) 0, (short) 1, (short) 0, (short) -1, EnumSet.of(ListenerType.BROKER, ListenerType.CONTROLLER), false),
    DESCRIBE_USER_SCRAM_CREDENTIALS("DescribeUserScramCredentials", (short) 50, DescribeUserScramCredentialsRequestData.SCHEMAS, DescribeUserScramCredentialsResponseData.SCHEMAS, (short) 0, (short) 0, (short) 0, (short) -1, EnumSet.of(ListenerType.BROKER, ListenerType.CONTROLLER), false),
    ALTER_USER_SCRAM_CREDENTIALS("AlterUserScramCredentials", (short) 51, AlterUserScramCredentialsRequestData.SCHEMAS, AlterUserScramCredentialsResponseData.SCHEMAS, (short) 0, (short) 0, (short) 0, (short) -1, EnumSet.of(ListenerType.BROKER, ListenerType.CONTROLLER), false),
    VOTE("Vote", (short) 52, VoteRequestData.SCHEMAS, VoteResponseData.SCHEMAS, (short) 0, (short) 2, (short) 0, (short) -1, EnumSet.of(ListenerType.CONTROLLER), false),
    BEGIN_QUORUM_EPOCH("BeginQuorumEpoch", (short) 53, BeginQuorumEpochRequestData.SCHEMAS, BeginQuorumEpochResponseData.SCHEMAS, (short) 0, (short) 1, (short) 0, (short) -1, EnumSet.of(ListenerType.CONTROLLER), false),
    END_QUORUM_EPOCH("EndQuorumEpoch", (short) 54, EndQuorumEpochRequestData.SCHEMAS, EndQuorumEpochResponseData.SCHEMAS, (short) 0, (short) 1, (short) 0, (short) -1, EnumSet.of(ListenerType.CONTROLLER), false),
    DESCRIBE_QUORUM("DescribeQuorum", (short) 55, DescribeQuorumRequestData.SCHEMAS, DescribeQuorumResponseData.SCHEMAS, (short) 0, (short) 2, (short) 0, (short) -1, EnumSet.of(ListenerType.BROKER, ListenerType.CONTROLLER), false),
    ALTER_PARTITION("AlterPartition", (short) 56, AlterPartitionRequestData.SCHEMAS, AlterPartitionResponseData.SCHEMAS, (short) 0, (short) 3, (short) 0, (short) -1, EnumSet.of(ListenerType.CONTROLLER), false),
    UPDATE_FEATURES("UpdateFeatures", (short) 57, UpdateFeaturesRequestData.SCHEMAS, UpdateFeaturesResponseData.SCHEMAS, (short) 0, (short) 2, (short) 0, (short) -1, EnumSet.of(ListenerType.BROKER, ListenerType.CONTROLLER), false),
    ENVELOPE("Envelope", (short) 58, EnvelopeRequestData.SCHEMAS, EnvelopeResponseData.SCHEMAS, (short) 0, (short) 0, (short) 0, (short) -1, EnumSet.of(ListenerType.CONTROLLER), false),
    FETCH_SNAPSHOT("FetchSnapshot", (short) 59, FetchSnapshotRequestData.SCHEMAS, FetchSnapshotResponseData.SCHEMAS, (short) 0, (short) 1, (short) 0, (short) -1, EnumSet.of(ListenerType.CONTROLLER), false),
    DESCRIBE_CLUSTER("DescribeCluster", (short) 60, DescribeClusterRequestData.SCHEMAS, DescribeClusterResponseData.SCHEMAS, (short) 0, (short) 2, (short) 0, (short) -1, EnumSet.of(ListenerType.BROKER, ListenerType.CONTROLLER), false),
    DESCRIBE_PRODUCERS("DescribeProducers", (short) 61, DescribeProducersRequestData.SCHEMAS, DescribeProducersResponseData.SCHEMAS, (short) 0, (short) 0, (short) 0, (short) -1, EnumSet.of(ListenerType.BROKER), false),
    BROKER_REGISTRATION("BrokerRegistration", (short) 62, BrokerRegistrationRequestData.SCHEMAS, BrokerRegistrationResponseData.SCHEMAS, (short) 0, (short) 4, (short) 0, (short) -1, EnumSet.of(ListenerType.CONTROLLER), false),
    BROKER_HEARTBEAT("BrokerHeartbeat", (short) 63, BrokerHeartbeatRequestData.SCHEMAS, BrokerHeartbeatResponseData.SCHEMAS, (short) 0, (short) 1, (short) 0, (short) -1, EnumSet.of(ListenerType.CONTROLLER), false),
    UNREGISTER_BROKER("UnregisterBroker", (short) 64, UnregisterBrokerRequestData.SCHEMAS, UnregisterBrokerResponseData.SCHEMAS, (short) 0, (short) 0, (short) 0, (short) -1, EnumSet.of(ListenerType.BROKER, ListenerType.CONTROLLER), false),
    DESCRIBE_TRANSACTIONS("DescribeTransactions", (short) 65, DescribeTransactionsRequestData.SCHEMAS, DescribeTransactionsResponseData.SCHEMAS, (short) 0, (short) 0, (short) 0, (short) -1, EnumSet.of(ListenerType.BROKER), false),
    LIST_TRANSACTIONS("ListTransactions", (short) 66, ListTransactionsRequestData.SCHEMAS, ListTransactionsResponseData.SCHEMAS, (short) 0, (short) 1, (short) 0, (short) -1, EnumSet.of(ListenerType.BROKER), false),
    ALLOCATE_PRODUCER_IDS("AllocateProducerIds", (short) 67, AllocateProducerIdsRequestData.SCHEMAS, AllocateProducerIdsResponseData.SCHEMAS, (short) 0, (short) 0, (short) 0, (short) -1, EnumSet.of(ListenerType.CONTROLLER), false),
    CONSUMER_GROUP_HEARTBEAT("ConsumerGroupHeartbeat", (short) 68, ConsumerGroupHeartbeatRequestData.SCHEMAS, ConsumerGroupHeartbeatResponseData.SCHEMAS, (short) 0, (short) 1, (short) 0, (short) -1, EnumSet.of(ListenerType.BROKER), false),
    CONSUMER_GROUP_DESCRIBE("ConsumerGroupDescribe", (short) 69, ConsumerGroupDescribeRequestData.SCHEMAS, ConsumerGroupDescribeResponseData.SCHEMAS, (short) 0, (short) 1, (short) 0, (short) -1, EnumSet.of(ListenerType.BROKER), false),
    CONTROLLER_REGISTRATION("ControllerRegistration", (short) 70, ControllerRegistrationRequestData.SCHEMAS, ControllerRegistrationResponseData.SCHEMAS, (short) 0, (short) 0, (short) 0, (short) -1, EnumSet.of(ListenerType.CONTROLLER), false),
    GET_TELEMETRY_SUBSCRIPTIONS("GetTelemetrySubscriptions", (short) 71, GetTelemetrySubscriptionsRequestData.SCHEMAS, GetTelemetrySubscriptionsResponseData.SCHEMAS, (short) 0, (short) 0, (short) 0, (short) -1, EnumSet.of(ListenerType.BROKER), false),
    PUSH_TELEMETRY("PushTelemetry", (short) 72, PushTelemetryRequestData.SCHEMAS, PushTelemetryResponseData.SCHEMAS, (short) 0, (short) 0, (short) 0, (short) -1, EnumSet.of(ListenerType.BROKER), false),
    ASSIGN_REPLICAS_TO_DIRS("AssignReplicasToDirs", (short) 73, AssignReplicasToDirsRequestData.SCHEMAS, AssignReplicasToDirsResponseData.SCHEMAS, (short) 0, (short) 0, (short) 0, (short) -1, EnumSet.of(ListenerType.CONTROLLER), false),
    LIST_CLIENT_METRICS_RESOURCES("ListClientMetricsResources", (short) 74, ListClientMetricsResourcesRequestData.SCHEMAS, ListClientMetricsResourcesResponseData.SCHEMAS, (short) 0, (short) 0, (short) 0, (short) -1, EnumSet.of(ListenerType.BROKER), false),
    DESCRIBE_TOPIC_PARTITIONS("DescribeTopicPartitions", (short) 75, DescribeTopicPartitionsRequestData.SCHEMAS, DescribeTopicPartitionsResponseData.SCHEMAS, (short) 0, (short) 0, (short) 0, (short) -1, EnumSet.of(ListenerType.BROKER), false),
    SHARE_GROUP_HEARTBEAT("ShareGroupHeartbeat", (short) 76, ShareGroupHeartbeatRequestData.SCHEMAS, ShareGroupHeartbeatResponseData.SCHEMAS, (short) 0, (short) 0, (short) 0, (short) -1, EnumSet.of(ListenerType.BROKER), true),
    SHARE_GROUP_DESCRIBE("ShareGroupDescribe", (short) 77, ShareGroupDescribeRequestData.SCHEMAS, ShareGroupDescribeResponseData.SCHEMAS, (short) 0, (short) 0, (short) 0, (short) -1, EnumSet.of(ListenerType.BROKER), true),
    SHARE_FETCH("ShareFetch", (short) 78, ShareFetchRequestData.SCHEMAS, ShareFetchResponseData.SCHEMAS, (short) 0, (short) 0, (short) 0, (short) -1, EnumSet.of(ListenerType.BROKER), true),
    SHARE_ACKNOWLEDGE("ShareAcknowledge", (short) 79, ShareAcknowledgeRequestData.SCHEMAS, ShareAcknowledgeResponseData.SCHEMAS, (short) 0, (short) 0, (short) 0, (short) -1, EnumSet.of(ListenerType.BROKER), true),
    ADD_RAFT_VOTER("AddRaftVoter", (short) 80, AddRaftVoterRequestData.SCHEMAS, AddRaftVoterResponseData.SCHEMAS, (short) 0, (short) 0, (short) 0, (short) -1, EnumSet.of(ListenerType.CONTROLLER, ListenerType.BROKER), false),
    REMOVE_RAFT_VOTER("RemoveRaftVoter", (short) 81, RemoveRaftVoterRequestData.SCHEMAS, RemoveRaftVoterResponseData.SCHEMAS, (short) 0, (short) 0, (short) 0, (short) -1, EnumSet.of(ListenerType.CONTROLLER, ListenerType.BROKER), false),
    UPDATE_RAFT_VOTER("UpdateRaftVoter", (short) 82, UpdateRaftVoterRequestData.SCHEMAS, UpdateRaftVoterResponseData.SCHEMAS, (short) 0, (short) 0, (short) 0, (short) -1, EnumSet.of(ListenerType.CONTROLLER), false),
    INITIALIZE_SHARE_GROUP_STATE("InitializeShareGroupState", (short) 83, InitializeShareGroupStateRequestData.SCHEMAS, InitializeShareGroupStateResponseData.SCHEMAS, (short) 0, (short) 0, (short) 0, (short) -1, EnumSet.of(ListenerType.BROKER), true),
    READ_SHARE_GROUP_STATE("ReadShareGroupState", (short) 84, ReadShareGroupStateRequestData.SCHEMAS, ReadShareGroupStateResponseData.SCHEMAS, (short) 0, (short) 0, (short) 0, (short) -1, EnumSet.of(ListenerType.BROKER), true),
    WRITE_SHARE_GROUP_STATE("WriteShareGroupState", (short) 85, WriteShareGroupStateRequestData.SCHEMAS, WriteShareGroupStateResponseData.SCHEMAS, (short) 0, (short) 0, (short) 0, (short) -1, EnumSet.of(ListenerType.BROKER), true),
    DELETE_SHARE_GROUP_STATE("DeleteShareGroupState", (short) 86, DeleteShareGroupStateRequestData.SCHEMAS, DeleteShareGroupStateResponseData.SCHEMAS, (short) 0, (short) 0, (short) 0, (short) -1, EnumSet.of(ListenerType.BROKER), true),
    READ_SHARE_GROUP_STATE_SUMMARY("ReadShareGroupStateSummary", (short) 87, ReadShareGroupStateSummaryRequestData.SCHEMAS, ReadShareGroupStateSummaryResponseData.SCHEMAS, (short) 0, (short) 0, (short) 0, (short) -1, EnumSet.of(ListenerType.BROKER), true);
    
    public final String name;
    private final short apiKey;
    private final Schema[] requestSchemas;
    private final Schema[] responseSchemas;
    private final short lowestSupportedVersion;
    private final short highestSupportedVersion;
    private final short lowestDeprecatedVersion;
    private final short highestDeprecatedVersion;
    private final EnumSet<ListenerType> listeners;
    private final boolean latestVersionUnstable;
    
    ApiMessageType(String name, short apiKey, Schema[] requestSchemas, Schema[] responseSchemas, short lowestSupportedVersion, short highestSupportedVersion, short lowestDeprecatedVersion, short highestDeprecatedVersion, EnumSet<ListenerType> listeners, boolean latestVersionUnstable) {
        this.name = name;
        this.apiKey = apiKey;
        this.requestSchemas = requestSchemas;
        this.responseSchemas = responseSchemas;
        this.lowestSupportedVersion = lowestSupportedVersion;
        this.highestSupportedVersion = highestSupportedVersion;
        this.lowestDeprecatedVersion = lowestDeprecatedVersion;
        this.highestDeprecatedVersion = highestDeprecatedVersion;
        this.listeners = listeners;
        this.latestVersionUnstable = latestVersionUnstable;
    }
    
    public static ApiMessageType fromApiKey(short apiKey) {
        switch (apiKey) {
            case 0:
                return PRODUCE;
            case 1:
                return FETCH;
            case 2:
                return LIST_OFFSETS;
            case 3:
                return METADATA;
            case 4:
                return LEADER_AND_ISR;
            case 5:
                return STOP_REPLICA;
            case 6:
                return UPDATE_METADATA;
            case 7:
                return CONTROLLED_SHUTDOWN;
            case 8:
                return OFFSET_COMMIT;
            case 9:
                return OFFSET_FETCH;
            case 10:
                return FIND_COORDINATOR;
            case 11:
                return JOIN_GROUP;
            case 12:
                return HEARTBEAT;
            case 13:
                return LEAVE_GROUP;
            case 14:
                return SYNC_GROUP;
            case 15:
                return DESCRIBE_GROUPS;
            case 16:
                return LIST_GROUPS;
            case 17:
                return SASL_HANDSHAKE;
            case 18:
                return API_VERSIONS;
            case 19:
                return CREATE_TOPICS;
            case 20:
                return DELETE_TOPICS;
            case 21:
                return DELETE_RECORDS;
            case 22:
                return INIT_PRODUCER_ID;
            case 23:
                return OFFSET_FOR_LEADER_EPOCH;
            case 24:
                return ADD_PARTITIONS_TO_TXN;
            case 25:
                return ADD_OFFSETS_TO_TXN;
            case 26:
                return END_TXN;
            case 27:
                return WRITE_TXN_MARKERS;
            case 28:
                return TXN_OFFSET_COMMIT;
            case 29:
                return DESCRIBE_ACLS;
            case 30:
                return CREATE_ACLS;
            case 31:
                return DELETE_ACLS;
            case 32:
                return DESCRIBE_CONFIGS;
            case 33:
                return ALTER_CONFIGS;
            case 34:
                return ALTER_REPLICA_LOG_DIRS;
            case 35:
                return DESCRIBE_LOG_DIRS;
            case 36:
                return SASL_AUTHENTICATE;
            case 37:
                return CREATE_PARTITIONS;
            case 38:
                return CREATE_DELEGATION_TOKEN;
            case 39:
                return RENEW_DELEGATION_TOKEN;
            case 40:
                return EXPIRE_DELEGATION_TOKEN;
            case 41:
                return DESCRIBE_DELEGATION_TOKEN;
            case 42:
                return DELETE_GROUPS;
            case 43:
                return ELECT_LEADERS;
            case 44:
                return INCREMENTAL_ALTER_CONFIGS;
            case 45:
                return ALTER_PARTITION_REASSIGNMENTS;
            case 46:
                return LIST_PARTITION_REASSIGNMENTS;
            case 47:
                return OFFSET_DELETE;
            case 48:
                return DESCRIBE_CLIENT_QUOTAS;
            case 49:
                return ALTER_CLIENT_QUOTAS;
            case 50:
                return DESCRIBE_USER_SCRAM_CREDENTIALS;
            case 51:
                return ALTER_USER_SCRAM_CREDENTIALS;
            case 52:
                return VOTE;
            case 53:
                return BEGIN_QUORUM_EPOCH;
            case 54:
                return END_QUORUM_EPOCH;
            case 55:
                return DESCRIBE_QUORUM;
            case 56:
                return ALTER_PARTITION;
            case 57:
                return UPDATE_FEATURES;
            case 58:
                return ENVELOPE;
            case 59:
                return FETCH_SNAPSHOT;
            case 60:
                return DESCRIBE_CLUSTER;
            case 61:
                return DESCRIBE_PRODUCERS;
            case 62:
                return BROKER_REGISTRATION;
            case 63:
                return BROKER_HEARTBEAT;
            case 64:
                return UNREGISTER_BROKER;
            case 65:
                return DESCRIBE_TRANSACTIONS;
            case 66:
                return LIST_TRANSACTIONS;
            case 67:
                return ALLOCATE_PRODUCER_IDS;
            case 68:
                return CONSUMER_GROUP_HEARTBEAT;
            case 69:
                return CONSUMER_GROUP_DESCRIBE;
            case 70:
                return CONTROLLER_REGISTRATION;
            case 71:
                return GET_TELEMETRY_SUBSCRIPTIONS;
            case 72:
                return PUSH_TELEMETRY;
            case 73:
                return ASSIGN_REPLICAS_TO_DIRS;
            case 74:
                return LIST_CLIENT_METRICS_RESOURCES;
            case 75:
                return DESCRIBE_TOPIC_PARTITIONS;
            case 76:
                return SHARE_GROUP_HEARTBEAT;
            case 77:
                return SHARE_GROUP_DESCRIBE;
            case 78:
                return SHARE_FETCH;
            case 79:
                return SHARE_ACKNOWLEDGE;
            case 80:
                return ADD_RAFT_VOTER;
            case 81:
                return REMOVE_RAFT_VOTER;
            case 82:
                return UPDATE_RAFT_VOTER;
            case 83:
                return INITIALIZE_SHARE_GROUP_STATE;
            case 84:
                return READ_SHARE_GROUP_STATE;
            case 85:
                return WRITE_SHARE_GROUP_STATE;
            case 86:
                return DELETE_SHARE_GROUP_STATE;
            case 87:
                return READ_SHARE_GROUP_STATE_SUMMARY;
            default:
                throw new UnsupportedVersionException("Unsupported API key " + apiKey);
        }
    }
    
    public ApiMessage newRequest() {
        switch (apiKey) {
            case 0:
                return new ProduceRequestData();
            case 1:
                return new FetchRequestData();
            case 2:
                return new ListOffsetsRequestData();
            case 3:
                return new MetadataRequestData();
            case 8:
                return new OffsetCommitRequestData();
            case 9:
                return new OffsetFetchRequestData();
            case 10:
                return new FindCoordinatorRequestData();
            case 11:
                return new JoinGroupRequestData();
            case 12:
                return new HeartbeatRequestData();
            case 13:
                return new LeaveGroupRequestData();
            case 14:
                return new SyncGroupRequestData();
            case 15:
                return new DescribeGroupsRequestData();
            case 16:
                return new ListGroupsRequestData();
            case 17:
                return new SaslHandshakeRequestData();
            case 18:
                return new ApiVersionsRequestData();
            case 19:
                return new CreateTopicsRequestData();
            case 20:
                return new DeleteTopicsRequestData();
            case 21:
                return new DeleteRecordsRequestData();
            case 22:
                return new InitProducerIdRequestData();
            case 23:
                return new OffsetForLeaderEpochRequestData();
            case 24:
                return new AddPartitionsToTxnRequestData();
            case 25:
                return new AddOffsetsToTxnRequestData();
            case 26:
                return new EndTxnRequestData();
            case 27:
                return new WriteTxnMarkersRequestData();
            case 28:
                return new TxnOffsetCommitRequestData();
            case 29:
                return new DescribeAclsRequestData();
            case 30:
                return new CreateAclsRequestData();
            case 31:
                return new DeleteAclsRequestData();
            case 32:
                return new DescribeConfigsRequestData();
            case 33:
                return new AlterConfigsRequestData();
            case 34:
                return new AlterReplicaLogDirsRequestData();
            case 35:
                return new DescribeLogDirsRequestData();
            case 36:
                return new SaslAuthenticateRequestData();
            case 37:
                return new CreatePartitionsRequestData();
            case 38:
                return new CreateDelegationTokenRequestData();
            case 39:
                return new RenewDelegationTokenRequestData();
            case 40:
                return new ExpireDelegationTokenRequestData();
            case 41:
                return new DescribeDelegationTokenRequestData();
            case 42:
                return new DeleteGroupsRequestData();
            case 43:
                return new ElectLeadersRequestData();
            case 44:
                return new IncrementalAlterConfigsRequestData();
            case 45:
                return new AlterPartitionReassignmentsRequestData();
            case 46:
                return new ListPartitionReassignmentsRequestData();
            case 47:
                return new OffsetDeleteRequestData();
            case 48:
                return new DescribeClientQuotasRequestData();
            case 49:
                return new AlterClientQuotasRequestData();
            case 50:
                return new DescribeUserScramCredentialsRequestData();
            case 51:
                return new AlterUserScramCredentialsRequestData();
            case 52:
                return new VoteRequestData();
            case 53:
                return new BeginQuorumEpochRequestData();
            case 54:
                return new EndQuorumEpochRequestData();
            case 55:
                return new DescribeQuorumRequestData();
            case 56:
                return new AlterPartitionRequestData();
            case 57:
                return new UpdateFeaturesRequestData();
            case 58:
                return new EnvelopeRequestData();
            case 59:
                return new FetchSnapshotRequestData();
            case 60:
                return new DescribeClusterRequestData();
            case 61:
                return new DescribeProducersRequestData();
            case 62:
                return new BrokerRegistrationRequestData();
            case 63:
                return new BrokerHeartbeatRequestData();
            case 64:
                return new UnregisterBrokerRequestData();
            case 65:
                return new DescribeTransactionsRequestData();
            case 66:
                return new ListTransactionsRequestData();
            case 67:
                return new AllocateProducerIdsRequestData();
            case 68:
                return new ConsumerGroupHeartbeatRequestData();
            case 69:
                return new ConsumerGroupDescribeRequestData();
            case 70:
                return new ControllerRegistrationRequestData();
            case 71:
                return new GetTelemetrySubscriptionsRequestData();
            case 72:
                return new PushTelemetryRequestData();
            case 73:
                return new AssignReplicasToDirsRequestData();
            case 74:
                return new ListClientMetricsResourcesRequestData();
            case 75:
                return new DescribeTopicPartitionsRequestData();
            case 76:
                return new ShareGroupHeartbeatRequestData();
            case 77:
                return new ShareGroupDescribeRequestData();
            case 78:
                return new ShareFetchRequestData();
            case 79:
                return new ShareAcknowledgeRequestData();
            case 80:
                return new AddRaftVoterRequestData();
            case 81:
                return new RemoveRaftVoterRequestData();
            case 82:
                return new UpdateRaftVoterRequestData();
            case 83:
                return new InitializeShareGroupStateRequestData();
            case 84:
                return new ReadShareGroupStateRequestData();
            case 85:
                return new WriteShareGroupStateRequestData();
            case 86:
                return new DeleteShareGroupStateRequestData();
            case 87:
                return new ReadShareGroupStateSummaryRequestData();
            default:
                throw new UnsupportedVersionException("Unsupported request API key " + apiKey);
        }
    }
    
    public ApiMessage newResponse() {
        switch (apiKey) {
            case 0:
                return new ProduceResponseData();
            case 1:
                return new FetchResponseData();
            case 2:
                return new ListOffsetsResponseData();
            case 3:
                return new MetadataResponseData();
            case 8:
                return new OffsetCommitResponseData();
            case 9:
                return new OffsetFetchResponseData();
            case 10:
                return new FindCoordinatorResponseData();
            case 11:
                return new JoinGroupResponseData();
            case 12:
                return new HeartbeatResponseData();
            case 13:
                return new LeaveGroupResponseData();
            case 14:
                return new SyncGroupResponseData();
            case 15:
                return new DescribeGroupsResponseData();
            case 16:
                return new ListGroupsResponseData();
            case 17:
                return new SaslHandshakeResponseData();
            case 18:
                return new ApiVersionsResponseData();
            case 19:
                return new CreateTopicsResponseData();
            case 20:
                return new DeleteTopicsResponseData();
            case 21:
                return new DeleteRecordsResponseData();
            case 22:
                return new InitProducerIdResponseData();
            case 23:
                return new OffsetForLeaderEpochResponseData();
            case 24:
                return new AddPartitionsToTxnResponseData();
            case 25:
                return new AddOffsetsToTxnResponseData();
            case 26:
                return new EndTxnResponseData();
            case 27:
                return new WriteTxnMarkersResponseData();
            case 28:
                return new TxnOffsetCommitResponseData();
            case 29:
                return new DescribeAclsResponseData();
            case 30:
                return new CreateAclsResponseData();
            case 31:
                return new DeleteAclsResponseData();
            case 32:
                return new DescribeConfigsResponseData();
            case 33:
                return new AlterConfigsResponseData();
            case 34:
                return new AlterReplicaLogDirsResponseData();
            case 35:
                return new DescribeLogDirsResponseData();
            case 36:
                return new SaslAuthenticateResponseData();
            case 37:
                return new CreatePartitionsResponseData();
            case 38:
                return new CreateDelegationTokenResponseData();
            case 39:
                return new RenewDelegationTokenResponseData();
            case 40:
                return new ExpireDelegationTokenResponseData();
            case 41:
                return new DescribeDelegationTokenResponseData();
            case 42:
                return new DeleteGroupsResponseData();
            case 43:
                return new ElectLeadersResponseData();
            case 44:
                return new IncrementalAlterConfigsResponseData();
            case 45:
                return new AlterPartitionReassignmentsResponseData();
            case 46:
                return new ListPartitionReassignmentsResponseData();
            case 47:
                return new OffsetDeleteResponseData();
            case 48:
                return new DescribeClientQuotasResponseData();
            case 49:
                return new AlterClientQuotasResponseData();
            case 50:
                return new DescribeUserScramCredentialsResponseData();
            case 51:
                return new AlterUserScramCredentialsResponseData();
            case 52:
                return new VoteResponseData();
            case 53:
                return new BeginQuorumEpochResponseData();
            case 54:
                return new EndQuorumEpochResponseData();
            case 55:
                return new DescribeQuorumResponseData();
            case 56:
                return new AlterPartitionResponseData();
            case 57:
                return new UpdateFeaturesResponseData();
            case 58:
                return new EnvelopeResponseData();
            case 59:
                return new FetchSnapshotResponseData();
            case 60:
                return new DescribeClusterResponseData();
            case 61:
                return new DescribeProducersResponseData();
            case 62:
                return new BrokerRegistrationResponseData();
            case 63:
                return new BrokerHeartbeatResponseData();
            case 64:
                return new UnregisterBrokerResponseData();
            case 65:
                return new DescribeTransactionsResponseData();
            case 66:
                return new ListTransactionsResponseData();
            case 67:
                return new AllocateProducerIdsResponseData();
            case 68:
                return new ConsumerGroupHeartbeatResponseData();
            case 69:
                return new ConsumerGroupDescribeResponseData();
            case 70:
                return new ControllerRegistrationResponseData();
            case 71:
                return new GetTelemetrySubscriptionsResponseData();
            case 72:
                return new PushTelemetryResponseData();
            case 73:
                return new AssignReplicasToDirsResponseData();
            case 74:
                return new ListClientMetricsResourcesResponseData();
            case 75:
                return new DescribeTopicPartitionsResponseData();
            case 76:
                return new ShareGroupHeartbeatResponseData();
            case 77:
                return new ShareGroupDescribeResponseData();
            case 78:
                return new ShareFetchResponseData();
            case 79:
                return new ShareAcknowledgeResponseData();
            case 80:
                return new AddRaftVoterResponseData();
            case 81:
                return new RemoveRaftVoterResponseData();
            case 82:
                return new UpdateRaftVoterResponseData();
            case 83:
                return new InitializeShareGroupStateResponseData();
            case 84:
                return new ReadShareGroupStateResponseData();
            case 85:
                return new WriteShareGroupStateResponseData();
            case 86:
                return new DeleteShareGroupStateResponseData();
            case 87:
                return new ReadShareGroupStateSummaryResponseData();
            default:
                throw new UnsupportedVersionException("Unsupported response API key " + apiKey);
        }
    }
    
    public short lowestSupportedVersion() {
        return this.lowestSupportedVersion;
    }
    
    public short highestSupportedVersion(boolean enableUnstableLastVersion) {
        if (!this.latestVersionUnstable || enableUnstableLastVersion) {
            return this.highestSupportedVersion;
        } else {
            // A negative value means that the API has no enabled versions.
            return (short) (this.highestSupportedVersion - 1);
        }
    }
    
    public short lowestDeprecatedVersion() {
        return this.lowestDeprecatedVersion;
    }
    
    public short highestDeprecatedVersion() {
        return this.highestDeprecatedVersion;
    }
    
    public EnumSet<ListenerType> listeners() {
        return this.listeners;
    }
    
    public boolean latestVersionUnstable() {
        return this.latestVersionUnstable;
    }
    
    public short apiKey() {
        return this.apiKey;
    }
    
    public Schema[] requestSchemas() {
        return this.requestSchemas;
    }
    
    public Schema[] responseSchemas() {
        return this.responseSchemas;
    }
    
    @Override
    public String toString() {
        return this.name();
    }
    
    public short requestHeaderVersion(short _version) {
        switch (apiKey) {
            case 0: // Produce
                if (_version >= 9) {
                    return (short) 2;
                } else {
                    return (short) 1;
                }
            case 1: // Fetch
                if (_version >= 12) {
                    return (short) 2;
                } else {
                    return (short) 1;
                }
            case 2: // ListOffsets
                if (_version >= 6) {
                    return (short) 2;
                } else {
                    return (short) 1;
                }
            case 3: // Metadata
                if (_version >= 9) {
                    return (short) 2;
                } else {
                    return (short) 1;
                }
            case 8: // OffsetCommit
                if (_version >= 8) {
                    return (short) 2;
                } else {
                    return (short) 1;
                }
            case 9: // OffsetFetch
                if (_version >= 6) {
                    return (short) 2;
                } else {
                    return (short) 1;
                }
            case 10: // FindCoordinator
                if (_version >= 3) {
                    return (short) 2;
                } else {
                    return (short) 1;
                }
            case 11: // JoinGroup
                if (_version >= 6) {
                    return (short) 2;
                } else {
                    return (short) 1;
                }
            case 12: // Heartbeat
                if (_version >= 4) {
                    return (short) 2;
                } else {
                    return (short) 1;
                }
            case 13: // LeaveGroup
                if (_version >= 4) {
                    return (short) 2;
                } else {
                    return (short) 1;
                }
            case 14: // SyncGroup
                if (_version >= 4) {
                    return (short) 2;
                } else {
                    return (short) 1;
                }
            case 15: // DescribeGroups
                if (_version >= 5) {
                    return (short) 2;
                } else {
                    return (short) 1;
                }
            case 16: // ListGroups
                if (_version >= 3) {
                    return (short) 2;
                } else {
                    return (short) 1;
                }
            case 17: // SaslHandshake
                return (short) 1;
            case 18: // ApiVersions
                if (_version >= 3) {
                    return (short) 2;
                } else {
                    return (short) 1;
                }
            case 19: // CreateTopics
                if (_version >= 5) {
                    return (short) 2;
                } else {
                    return (short) 1;
                }
            case 20: // DeleteTopics
                if (_version >= 4) {
                    return (short) 2;
                } else {
                    return (short) 1;
                }
            case 21: // DeleteRecords
                if (_version >= 2) {
                    return (short) 2;
                } else {
                    return (short) 1;
                }
            case 22: // InitProducerId
                if (_version >= 2) {
                    return (short) 2;
                } else {
                    return (short) 1;
                }
            case 23: // OffsetForLeaderEpoch
                if (_version >= 4) {
                    return (short) 2;
                } else {
                    return (short) 1;
                }
            case 24: // AddPartitionsToTxn
                if (_version >= 3) {
                    return (short) 2;
                } else {
                    return (short) 1;
                }
            case 25: // AddOffsetsToTxn
                if (_version >= 3) {
                    return (short) 2;
                } else {
                    return (short) 1;
                }
            case 26: // EndTxn
                if (_version >= 3) {
                    return (short) 2;
                } else {
                    return (short) 1;
                }
            case 27: // WriteTxnMarkers
                return (short) 2;
            case 28: // TxnOffsetCommit
                if (_version >= 3) {
                    return (short) 2;
                } else {
                    return (short) 1;
                }
            case 29: // DescribeAcls
                if (_version >= 2) {
                    return (short) 2;
                } else {
                    return (short) 1;
                }
            case 30: // CreateAcls
                if (_version >= 2) {
                    return (short) 2;
                } else {
                    return (short) 1;
                }
            case 31: // DeleteAcls
                if (_version >= 2) {
                    return (short) 2;
                } else {
                    return (short) 1;
                }
            case 32: // DescribeConfigs
                if (_version >= 4) {
                    return (short) 2;
                } else {
                    return (short) 1;
                }
            case 33: // AlterConfigs
                if (_version >= 2) {
                    return (short) 2;
                } else {
                    return (short) 1;
                }
            case 34: // AlterReplicaLogDirs
                if (_version >= 2) {
                    return (short) 2;
                } else {
                    return (short) 1;
                }
            case 35: // DescribeLogDirs
                if (_version >= 2) {
                    return (short) 2;
                } else {
                    return (short) 1;
                }
            case 36: // SaslAuthenticate
                if (_version >= 2) {
                    return (short) 2;
                } else {
                    return (short) 1;
                }
            case 37: // CreatePartitions
                if (_version >= 2) {
                    return (short) 2;
                } else {
                    return (short) 1;
                }
            case 38: // CreateDelegationToken
                if (_version >= 2) {
                    return (short) 2;
                } else {
                    return (short) 1;
                }
            case 39: // RenewDelegationToken
                if (_version >= 2) {
                    return (short) 2;
                } else {
                    return (short) 1;
                }
            case 40: // ExpireDelegationToken
                if (_version >= 2) {
                    return (short) 2;
                } else {
                    return (short) 1;
                }
            case 41: // DescribeDelegationToken
                if (_version >= 2) {
                    return (short) 2;
                } else {
                    return (short) 1;
                }
            case 42: // DeleteGroups
                if (_version >= 2) {
                    return (short) 2;
                } else {
                    return (short) 1;
                }
            case 43: // ElectLeaders
                if (_version >= 2) {
                    return (short) 2;
                } else {
                    return (short) 1;
                }
            case 44: // IncrementalAlterConfigs
                if (_version >= 1) {
                    return (short) 2;
                } else {
                    return (short) 1;
                }
            case 45: // AlterPartitionReassignments
                return (short) 2;
            case 46: // ListPartitionReassignments
                return (short) 2;
            case 47: // OffsetDelete
                return (short) 1;
            case 48: // DescribeClientQuotas
                if (_version >= 1) {
                    return (short) 2;
                } else {
                    return (short) 1;
                }
            case 49: // AlterClientQuotas
                if (_version >= 1) {
                    return (short) 2;
                } else {
                    return (short) 1;
                }
            case 50: // DescribeUserScramCredentials
                return (short) 2;
            case 51: // AlterUserScramCredentials
                return (short) 2;
            case 52: // Vote
                return (short) 2;
            case 53: // BeginQuorumEpoch
                if (_version >= 1) {
                    return (short) 2;
                } else {
                    return (short) 1;
                }
            case 54: // EndQuorumEpoch
                if (_version >= 1) {
                    return (short) 2;
                } else {
                    return (short) 1;
                }
            case 55: // DescribeQuorum
                return (short) 2;
            case 56: // AlterPartition
                return (short) 2;
            case 57: // UpdateFeatures
                return (short) 2;
            case 58: // Envelope
                return (short) 2;
            case 59: // FetchSnapshot
                return (short) 2;
            case 60: // DescribeCluster
                return (short) 2;
            case 61: // DescribeProducers
                return (short) 2;
            case 62: // BrokerRegistration
                return (short) 2;
            case 63: // BrokerHeartbeat
                return (short) 2;
            case 64: // UnregisterBroker
                return (short) 2;
            case 65: // DescribeTransactions
                return (short) 2;
            case 66: // ListTransactions
                return (short) 2;
            case 67: // AllocateProducerIds
                return (short) 2;
            case 68: // ConsumerGroupHeartbeat
                return (short) 2;
            case 69: // ConsumerGroupDescribe
                return (short) 2;
            case 70: // ControllerRegistration
                return (short) 2;
            case 71: // GetTelemetrySubscriptions
                return (short) 2;
            case 72: // PushTelemetry
                return (short) 2;
            case 73: // AssignReplicasToDirs
                return (short) 2;
            case 74: // ListClientMetricsResources
                return (short) 2;
            case 75: // DescribeTopicPartitions
                return (short) 2;
            case 76: // ShareGroupHeartbeat
                return (short) 2;
            case 77: // ShareGroupDescribe
                return (short) 2;
            case 78: // ShareFetch
                return (short) 2;
            case 79: // ShareAcknowledge
                return (short) 2;
            case 80: // AddRaftVoter
                return (short) 2;
            case 81: // RemoveRaftVoter
                return (short) 2;
            case 82: // UpdateRaftVoter
                return (short) 2;
            case 83: // InitializeShareGroupState
                return (short) 2;
            case 84: // ReadShareGroupState
                return (short) 2;
            case 85: // WriteShareGroupState
                return (short) 2;
            case 86: // DeleteShareGroupState
                return (short) 2;
            case 87: // ReadShareGroupStateSummary
                return (short) 2;
            default:
                throw new UnsupportedVersionException("Unsupported API key " + apiKey);
        }
    }
    
    public short responseHeaderVersion(short _version) {
        switch (apiKey) {
            case 0: // Produce
                if (_version >= 9) {
                    return (short) 1;
                } else {
                    return (short) 0;
                }
            case 1: // Fetch
                if (_version >= 12) {
                    return (short) 1;
                } else {
                    return (short) 0;
                }
            case 2: // ListOffsets
                if (_version >= 6) {
                    return (short) 1;
                } else {
                    return (short) 0;
                }
            case 3: // Metadata
                if (_version >= 9) {
                    return (short) 1;
                } else {
                    return (short) 0;
                }
            case 8: // OffsetCommit
                if (_version >= 8) {
                    return (short) 1;
                } else {
                    return (short) 0;
                }
            case 9: // OffsetFetch
                if (_version >= 6) {
                    return (short) 1;
                } else {
                    return (short) 0;
                }
            case 10: // FindCoordinator
                if (_version >= 3) {
                    return (short) 1;
                } else {
                    return (short) 0;
                }
            case 11: // JoinGroup
                if (_version >= 6) {
                    return (short) 1;
                } else {
                    return (short) 0;
                }
            case 12: // Heartbeat
                if (_version >= 4) {
                    return (short) 1;
                } else {
                    return (short) 0;
                }
            case 13: // LeaveGroup
                if (_version >= 4) {
                    return (short) 1;
                } else {
                    return (short) 0;
                }
            case 14: // SyncGroup
                if (_version >= 4) {
                    return (short) 1;
                } else {
                    return (short) 0;
                }
            case 15: // DescribeGroups
                if (_version >= 5) {
                    return (short) 1;
                } else {
                    return (short) 0;
                }
            case 16: // ListGroups
                if (_version >= 3) {
                    return (short) 1;
                } else {
                    return (short) 0;
                }
            case 17: // SaslHandshake
                return (short) 0;
            case 18: // ApiVersions
                // ApiVersionsResponse always includes a v0 header.
                // See KIP-511 for details.
                return (short) 0;
            case 19: // CreateTopics
                if (_version >= 5) {
                    return (short) 1;
                } else {
                    return (short) 0;
                }
            case 20: // DeleteTopics
                if (_version >= 4) {
                    return (short) 1;
                } else {
                    return (short) 0;
                }
            case 21: // DeleteRecords
                if (_version >= 2) {
                    return (short) 1;
                } else {
                    return (short) 0;
                }
            case 22: // InitProducerId
                if (_version >= 2) {
                    return (short) 1;
                } else {
                    return (short) 0;
                }
            case 23: // OffsetForLeaderEpoch
                if (_version >= 4) {
                    return (short) 1;
                } else {
                    return (short) 0;
                }
            case 24: // AddPartitionsToTxn
                if (_version >= 3) {
                    return (short) 1;
                } else {
                    return (short) 0;
                }
            case 25: // AddOffsetsToTxn
                if (_version >= 3) {
                    return (short) 1;
                } else {
                    return (short) 0;
                }
            case 26: // EndTxn
                if (_version >= 3) {
                    return (short) 1;
                } else {
                    return (short) 0;
                }
            case 27: // WriteTxnMarkers
                return (short) 1;
            case 28: // TxnOffsetCommit
                if (_version >= 3) {
                    return (short) 1;
                } else {
                    return (short) 0;
                }
            case 29: // DescribeAcls
                if (_version >= 2) {
                    return (short) 1;
                } else {
                    return (short) 0;
                }
            case 30: // CreateAcls
                if (_version >= 2) {
                    return (short) 1;
                } else {
                    return (short) 0;
                }
            case 31: // DeleteAcls
                if (_version >= 2) {
                    return (short) 1;
                } else {
                    return (short) 0;
                }
            case 32: // DescribeConfigs
                if (_version >= 4) {
                    return (short) 1;
                } else {
                    return (short) 0;
                }
            case 33: // AlterConfigs
                if (_version >= 2) {
                    return (short) 1;
                } else {
                    return (short) 0;
                }
            case 34: // AlterReplicaLogDirs
                if (_version >= 2) {
                    return (short) 1;
                } else {
                    return (short) 0;
                }
            case 35: // DescribeLogDirs
                if (_version >= 2) {
                    return (short) 1;
                } else {
                    return (short) 0;
                }
            case 36: // SaslAuthenticate
                if (_version >= 2) {
                    return (short) 1;
                } else {
                    return (short) 0;
                }
            case 37: // CreatePartitions
                if (_version >= 2) {
                    return (short) 1;
                } else {
                    return (short) 0;
                }
            case 38: // CreateDelegationToken
                if (_version >= 2) {
                    return (short) 1;
                } else {
                    return (short) 0;
                }
            case 39: // RenewDelegationToken
                if (_version >= 2) {
                    return (short) 1;
                } else {
                    return (short) 0;
                }
            case 40: // ExpireDelegationToken
                if (_version >= 2) {
                    return (short) 1;
                } else {
                    return (short) 0;
                }
            case 41: // DescribeDelegationToken
                if (_version >= 2) {
                    return (short) 1;
                } else {
                    return (short) 0;
                }
            case 42: // DeleteGroups
                if (_version >= 2) {
                    return (short) 1;
                } else {
                    return (short) 0;
                }
            case 43: // ElectLeaders
                if (_version >= 2) {
                    return (short) 1;
                } else {
                    return (short) 0;
                }
            case 44: // IncrementalAlterConfigs
                if (_version >= 1) {
                    return (short) 1;
                } else {
                    return (short) 0;
                }
            case 45: // AlterPartitionReassignments
                return (short) 1;
            case 46: // ListPartitionReassignments
                return (short) 1;
            case 47: // OffsetDelete
                return (short) 0;
            case 48: // DescribeClientQuotas
                if (_version >= 1) {
                    return (short) 1;
                } else {
                    return (short) 0;
                }
            case 49: // AlterClientQuotas
                if (_version >= 1) {
                    return (short) 1;
                } else {
                    return (short) 0;
                }
            case 50: // DescribeUserScramCredentials
                return (short) 1;
            case 51: // AlterUserScramCredentials
                return (short) 1;
            case 52: // Vote
                return (short) 1;
            case 53: // BeginQuorumEpoch
                if (_version >= 1) {
                    return (short) 1;
                } else {
                    return (short) 0;
                }
            case 54: // EndQuorumEpoch
                if (_version >= 1) {
                    return (short) 1;
                } else {
                    return (short) 0;
                }
            case 55: // DescribeQuorum
                return (short) 1;
            case 56: // AlterPartition
                return (short) 1;
            case 57: // UpdateFeatures
                return (short) 1;
            case 58: // Envelope
                return (short) 1;
            case 59: // FetchSnapshot
                return (short) 1;
            case 60: // DescribeCluster
                return (short) 1;
            case 61: // DescribeProducers
                return (short) 1;
            case 62: // BrokerRegistration
                return (short) 1;
            case 63: // BrokerHeartbeat
                return (short) 1;
            case 64: // UnregisterBroker
                return (short) 1;
            case 65: // DescribeTransactions
                return (short) 1;
            case 66: // ListTransactions
                return (short) 1;
            case 67: // AllocateProducerIds
                return (short) 1;
            case 68: // ConsumerGroupHeartbeat
                return (short) 1;
            case 69: // ConsumerGroupDescribe
                return (short) 1;
            case 70: // ControllerRegistration
                return (short) 1;
            case 71: // GetTelemetrySubscriptions
                return (short) 1;
            case 72: // PushTelemetry
                return (short) 1;
            case 73: // AssignReplicasToDirs
                return (short) 1;
            case 74: // ListClientMetricsResources
                return (short) 1;
            case 75: // DescribeTopicPartitions
                return (short) 1;
            case 76: // ShareGroupHeartbeat
                return (short) 1;
            case 77: // ShareGroupDescribe
                return (short) 1;
            case 78: // ShareFetch
                return (short) 1;
            case 79: // ShareAcknowledge
                return (short) 1;
            case 80: // AddRaftVoter
                return (short) 1;
            case 81: // RemoveRaftVoter
                return (short) 1;
            case 82: // UpdateRaftVoter
                return (short) 1;
            case 83: // InitializeShareGroupState
                return (short) 1;
            case 84: // ReadShareGroupState
                return (short) 1;
            case 85: // WriteShareGroupState
                return (short) 1;
            case 86: // DeleteShareGroupState
                return (short) 1;
            case 87: // ReadShareGroupStateSummary
                return (short) 1;
            default:
                throw new UnsupportedVersionException("Unsupported API key " + apiKey);
        }
    }
    
    public enum ListenerType {
        BROKER,
        CONTROLLER;
    }
    
}
