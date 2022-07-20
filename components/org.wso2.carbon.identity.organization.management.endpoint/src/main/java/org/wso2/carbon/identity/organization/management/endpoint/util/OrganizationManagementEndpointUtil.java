/*
 * Copyright (c) 2021, WSO2 Inc. (http://www.wso2.com).
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.carbon.identity.organization.management.endpoint.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.identity.core.ServiceURLBuilder;
import org.wso2.carbon.identity.core.URLBuilderException;
import org.wso2.carbon.identity.organization.management.endpoint.exceptions.OrganizationManagementEndpointException;
import org.wso2.carbon.identity.organization.management.endpoint.model.Error;
import org.wso2.carbon.identity.organization.management.service.exception.OrganizationManagementClientException;
import org.wso2.carbon.identity.organization.management.service.exception.OrganizationManagementException;

import java.net.URI;

import javax.ws.rs.core.Response;

import static org.wso2.carbon.identity.organization.management.service.constant.OrganizationManagementConstants.ErrorMessages.ERROR_CODE_ERROR_BUILDING_PAGINATED_RESPONSE_URL;
import static org.wso2.carbon.identity.organization.management.service.constant.OrganizationManagementConstants.ErrorMessages.ERROR_CODE_ERROR_BUILDING_RESPONSE_HEADER_URL;
import static org.wso2.carbon.identity.organization.management.service.constant.OrganizationManagementConstants.ErrorMessages.ERROR_CODE_INVALID_ORGANIZATION;
import static org.wso2.carbon.identity.organization.management.service.constant.OrganizationManagementConstants.ErrorMessages.ERROR_CODE_USER_NOT_AUTHORIZED_TO_CREATE_ORGANIZATION;
import static org.wso2.carbon.identity.organization.management.service.constant.OrganizationManagementConstants.ORGANIZATION_PATH;
import static org.wso2.carbon.identity.organization.management.service.constant.OrganizationManagementConstants.PATH_SEPARATOR;
import static org.wso2.carbon.identity.organization.management.service.constant.OrganizationManagementConstants.V1_API_PATH_COMPONENT;
import static org.wso2.carbon.identity.organization.management.service.util.Utils.getContext;

/**
 * This class provides util functions to the organization management endpoint.
 */
public class OrganizationManagementEndpointUtil {

    private static final Log LOG = LogFactory.getLog(OrganizationManagementEndpointUtil.class);

    /**
     * Handles the response for client errors.
     *
     * @param e   The client exception thrown.
     * @param log The logger.
     * @return The response for the client error.
     */
    public static Response handleClientErrorResponse(OrganizationManagementClientException e, Log log) {

        if (isNotFoundError(e)) {
            throw buildException(Response.Status.NOT_FOUND, log, e);
        }

        if (isForbiddenError(e)) {
            throw buildException(Response.Status.FORBIDDEN, log, e);
        }

        throw buildException(Response.Status.BAD_REQUEST, log, e);
    }

    /**
     * Handles the response for server errors.
     *
     * @param e   The server exception thrown.
     * @param log The logger.
     * @return The response for the server error.
     */
    public static Response handleServerErrorResponse(OrganizationManagementException e, Log log) {

        throw buildException(Response.Status.INTERNAL_SERVER_ERROR, log, e);
    }

    private static boolean isNotFoundError(OrganizationManagementClientException e) {

        return ERROR_CODE_INVALID_ORGANIZATION.getCode().equals(e.getErrorCode());

    }

    private static boolean isForbiddenError(OrganizationManagementClientException e) {

        return ERROR_CODE_USER_NOT_AUTHORIZED_TO_CREATE_ORGANIZATION.getCode().equals(e.getErrorCode());
    }

    private static OrganizationManagementEndpointException buildException(Response.Status status, Log log,
                                                                          OrganizationManagementException e) {

        if (e instanceof OrganizationManagementClientException) {
            logDebug(log, e);
        } else {
            logError(log, e);
        }
        return new OrganizationManagementEndpointException(status, getError(e.getErrorCode(), e.getMessage(),
                e.getDescription()));
    }

    /**
     * Returns a generic error object.
     *
     * @param errorCode        The error code.
     * @param errorMessage     The error message.
     * @param errorDescription The error description.
     * @return A generic error with the specified details.
     */
    public static Error getError(String errorCode, String errorMessage, String errorDescription) {

        Error error = new Error();
        error.setCode(errorCode);
        error.setMessage(errorMessage);
        error.setDescription(errorDescription);
        return error;
    }

    private static void logDebug(Log log, OrganizationManagementException e) {

        if (log.isDebugEnabled()) {
            String errorMessageFormat = "errorCode: %s | message: %s";
            String errorMessage = String.format(errorMessageFormat, e.getErrorCode(), e.getDescription());
            log.debug(errorMessage, e);
        }
    }

    private static void logError(Log log, OrganizationManagementException e) {

        String errorMessageFormat = "errorCode: %s | message: %s";
        String errorMessage = String.format(errorMessageFormat, e.getErrorCode(), e.getDescription());
        log.error(errorMessage, e);
    }

    /**
     * Get location of the created organization.
     *
     * @param organizationId The unique identifier of the created organization.
     * @return URI
     */
    public static URI getResourceLocation(String organizationId) {

        return buildURIForHeader(V1_API_PATH_COMPONENT + PATH_SEPARATOR + ORGANIZATION_PATH + PATH_SEPARATOR
                + organizationId);
    }

    private static URI buildURIForHeader(String endpoint) {

        String context = getContext(endpoint);
        try {
            String url = ServiceURLBuilder.create().addPath(context).build().getAbsolutePublicURL();
            return URI.create(url);
        } catch (URLBuilderException e) {
            LOG.error("Server encountered an error while building URL for response header.");
            Error error = getError(ERROR_CODE_ERROR_BUILDING_RESPONSE_HEADER_URL.getCode(),
                    ERROR_CODE_ERROR_BUILDING_RESPONSE_HEADER_URL.getMessage(),
                    ERROR_CODE_ERROR_BUILDING_RESPONSE_HEADER_URL.getDescription());
            throw new OrganizationManagementEndpointException(Response.Status.INTERNAL_SERVER_ERROR, error);
        }
    }

    public static String buildURIForPagination(String paginationURL) {

        String context = getContext(V1_API_PATH_COMPONENT + PATH_SEPARATOR + ORGANIZATION_PATH + paginationURL);

        try {
            return ServiceURLBuilder.create().addPath(context).build().getRelativePublicURL();
        } catch (URLBuilderException e) {
            LOG.error("Server encountered an error while building paginated URL for the response.", e);
            Error error = getError(ERROR_CODE_ERROR_BUILDING_PAGINATED_RESPONSE_URL.getCode(),
                    ERROR_CODE_ERROR_BUILDING_PAGINATED_RESPONSE_URL.getMessage(),
                    ERROR_CODE_ERROR_BUILDING_PAGINATED_RESPONSE_URL.getDescription());
            throw new OrganizationManagementEndpointException(Response.Status.INTERNAL_SERVER_ERROR, error);
        }
    }

}
