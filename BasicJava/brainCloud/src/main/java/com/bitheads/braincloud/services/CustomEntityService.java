package com.bitheads.braincloud.services;

import com.bitheads.braincloud.client.BrainCloudClient;
import com.bitheads.braincloud.client.IServerCallback;
import com.bitheads.braincloud.client.ServiceName;
import com.bitheads.braincloud.client.ServiceOperation;
import com.bitheads.braincloud.comms.ServerCall;

import org.json.JSONException;
import org.json.JSONObject;

public class CustomEntityService {

    private enum Parameter {
        entityId,
        dataJson,
        acl,
        timeToLive,
        isOwned,
        entityType,
        version,
        whereJson,
        rowsPerPage,
        searchJson,
        sortJson,
        doCount,
        pageOffset, 
        context,
        fieldsJson
    }

    private BrainCloudClient _client;

    public CustomEntityService(BrainCloudClient client) {
        _client = client;
    }

    /**
     * Method creates a new entity on the server.
     *
     * @param entityType The entity type as defined by the user
     * @param dataJson The entity's data as a json String
     * @param acl The entity's access control list as json. A null acl implies
     *            default permissions which make the entity readable/writeable
     *            by only the player.
     * @param timeToLive
     * @param isOwned
     * @param callback Callback.
     */
    public void createEntity(String entityType, String dataJson,
                             String acl, long timeToLive, Boolean isOwned, IServerCallback callback) {

        try {
            JSONObject data = new JSONObject();
            data.put(Parameter.entityType.name(), entityType);

            JSONObject jsonData = new JSONObject(dataJson);
            data.put(Parameter.dataJson.name(), jsonData);

            if (StringUtil.IsOptionalParameterValid(acl)) {
                JSONObject jsonAcl;
                jsonAcl = new JSONObject(acl);
                data.put(Parameter.acl.name(), jsonAcl);
            }

            data.put(Parameter.timeToLive.name(), timeToLive);
            data.put(Parameter.isOwned.name(), isOwned);

            ServerCall serverCall = new ServerCall(ServiceName.customEntity,
                    ServiceOperation.CREATE_ENTITY, data, callback);
            _client.sendRequest(serverCall);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

        /**
     * Deletes the specified custom entity on the server, enforcing ownership/ACL permissions.
     *
     * @param entityType The entity type as defined by the user
     * @param entityId
     * @param version
     * @param callback Callback.
     */
    public void deleteEntity(String entityType, String entityId,
                             int version, IServerCallback callback) {

        try {
            JSONObject data = new JSONObject();
            data.put(Parameter.entityType.name(), entityType);
            data.put(Parameter.entityId.name(), entityId);
            data.put(Parameter.version.name(), version);

            ServerCall serverCall = new ServerCall(ServiceName.customEntity,
                    ServiceOperation.DELETE_ENTITY, data, callback);
            _client.sendRequest(serverCall);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * Counts the number of custom entities meeting the specified where clause, enforcing ownership/ACL permissions.
     *
     * @param entityType The entity type as defined by the user
     * @param entityId
     * @param version
     * @param callback Callback.
     */
    public void getCount(String entityType, String whereJson,
                         IServerCallback callback) {

        try {
            JSONObject data = new JSONObject();
            data.put(Parameter.entityType.name(), entityType);
            JSONObject whereData = new JSONObject(whereJson);
            data.put(Parameter.whereJson.name(), whereData);

            ServerCall serverCall = new ServerCall(ServiceName.customEntity,
                    ServiceOperation.GET_COUNT, data, callback);
            _client.sendRequest(serverCall);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves first page of custom entities from the server based on the custom entity type and specified query context, enforcing ownership/ACL permissions.
     *
     * @param entityType The entity type as defined by the user
     * @param entityId
     * @param version
     * @param callback Callback.
     */
    public void getPage(String entityType, int rowsPerPage, String searchJson, String sortJson, Boolean doCount,
                         IServerCallback callback) {

        try {
            JSONObject data = new JSONObject();
            data.put(Parameter.entityType.name(), entityType);
            data.put(Parameter.rowsPerPage.name(), rowsPerPage);
            JSONObject searchData = new JSONObject(searchJson);
            data.put(Parameter.searchJson.name(), searchData);
            JSONObject sortData = new JSONObject(sortJson);
            data.put(Parameter.sortJson.name(), sortData);
            data.put(Parameter.doCount.name(), doCount);

            ServerCall serverCall = new ServerCall(ServiceName.customEntity,
                    ServiceOperation.GET_PAGE, data, callback);
            _client.sendRequest(serverCall);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets the page of custom entities from the server based on the encoded context and specified page offset, enforcing ownership/ACL permissions.
     *
     * @param entityType The entity type as defined by the user
     * @param context
     * @param pageOffset
     * @param callback Callback.
     */
    public void getPageOffset(String entityType, String context, int pageOffset,
                         IServerCallback callback) {

        try {
            JSONObject data = new JSONObject();
            data.put(Parameter.entityType.name(), entityType);
            data.put(Parameter.context.name(), context);
            data.put(Parameter.pageOffset.name(), pageOffset);

            ServerCall serverCall = new ServerCall(ServiceName.customEntity,
                    ServiceOperation.GET_PAGE_BY_OFFSET, data, callback);
            _client.sendRequest(serverCall);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads a custom entity, enforcing ownership/ACL permissions.
     *
     * @param entityType The entity type as defined by the user
     * @param entityId
     * @param callback Callback.
     */
    public void readEntity(String entityType, String entityId,
                         IServerCallback callback) {

        try {
            JSONObject data = new JSONObject();
            data.put(Parameter.entityType.name(), entityType);
            data.put(Parameter.entityId.name(), entityId);

            ServerCall serverCall = new ServerCall(ServiceName.customEntity,
                    ServiceOperation.READ_ENTITY, data, callback);
            _client.sendRequest(serverCall);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * Replaces the specified custom entity's data, and optionally updates the acl and expiry, on the server, enforcing current ownership/ACL permissions.
     *
     * @param entityType The entity type as defined by the user
     * @param entityId
     * @param version
     * @param dataJson
     * @param acl
     * @param timeToLive
     * @param callback Callback.
     */
    public void updateEntity(String entityType, String entityId, int version, String dataJson, String acl, long timeToLive,
                         IServerCallback callback) {

        try {
            JSONObject data = new JSONObject();
            data.put(Parameter.entityType.name(), entityType);
            data.put(Parameter.entityId.name(), entityId);
            data.put(Parameter.version.name(), version);

            JSONObject jsonData = new JSONObject(dataJson);
            data.put(Parameter.dataJson.name(), jsonData);

            if (StringUtil.IsOptionalParameterValid(acl)) {
                JSONObject jsonAcl;
                jsonAcl = new JSONObject(acl);
                data.put(Parameter.acl.name(), jsonAcl);
            }

            data.put(Parameter.timeToLive.name(), timeToLive);

            ServerCall serverCall = new ServerCall(ServiceName.customEntity,
                    ServiceOperation.UPDATE_ENTITY, data, callback);
            _client.sendRequest(serverCall);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * Replaces the specified custom entity's data, and optionally updates the acl and expiry, on the server, enforcing current ownership/ACL permissions.
     *
     * @param entityType The entity type as defined by the user
     * @param entityId
     * @param version
     * @param dataJson
     * @param acl
     * @param timeToLive
     * @param callback Callback.
     */
    public void updateEntityFields(String entityType, String entityId, int version, String fieldsJson,
                         IServerCallback callback) {

        try {
            JSONObject data = new JSONObject();
            data.put(Parameter.entityType.name(), entityType);
            data.put(Parameter.entityId.name(), entityId);
            data.put(Parameter.version.name(), version);

            JSONObject fieldsData = new JSONObject(fieldsJson);
            data.put(Parameter.fieldsJson.name(), fieldsData);

            ServerCall serverCall = new ServerCall(ServiceName.customEntity,
                    ServiceOperation.UPDATE_ENTITY_FIELDS, data, callback);
            _client.sendRequest(serverCall);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
