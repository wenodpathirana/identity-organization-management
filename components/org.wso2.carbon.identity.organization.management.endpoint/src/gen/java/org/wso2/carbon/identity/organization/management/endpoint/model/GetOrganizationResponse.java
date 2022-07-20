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

package org.wso2.carbon.identity.organization.management.endpoint.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.wso2.carbon.identity.organization.management.endpoint.model.Attribute;
import org.wso2.carbon.identity.organization.management.endpoint.model.ParentOrganization;
import javax.validation.constraints.*;


import io.swagger.annotations.*;
import java.util.Objects;
import javax.validation.Valid;
import javax.xml.bind.annotation.*;

public class GetOrganizationResponse  {
  
    private String id;
    private String name;
    private String description;

@XmlType(name="StatusEnum")
@XmlEnum(String.class)
public enum StatusEnum {

    @XmlEnumValue("ACTIVE") ACTIVE(String.valueOf("ACTIVE")), @XmlEnumValue("DISABLED") DISABLED(String.valueOf("DISABLED"));


    private String value;

    StatusEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static StatusEnum fromValue(String value) {
        for (StatusEnum b : StatusEnum.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}

    private StatusEnum status;
    private String created;
    private String lastModified;

@XmlType(name="TypeEnum")
@XmlEnum(String.class)
public enum TypeEnum {

    @XmlEnumValue("TENANT") TENANT(String.valueOf("TENANT")), @XmlEnumValue("STRUCTURAL") STRUCTURAL(String.valueOf("STRUCTURAL"));


    private String value;

    TypeEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static TypeEnum fromValue(String value) {
        for (TypeEnum b : TypeEnum.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}

    private TypeEnum type;
    private ParentOrganization parent;
    private List<Attribute> attributes = null;

    private List<String> permissions = null;


    /**
    **/
    public GetOrganizationResponse id(String id) {

        this.id = id;
        return this;
    }
    
    @ApiModelProperty(example = "06c1f4e2-3339-44e4-a825-96585e3653b1", required = true, value = "")
    @JsonProperty("id")
    @Valid
    @NotNull(message = "Property id cannot be null.")

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    /**
    **/
    public GetOrganizationResponse name(String name) {

        this.name = name;
        return this;
    }
    
    @ApiModelProperty(example = "ABC Builders", required = true, value = "")
    @JsonProperty("name")
    @Valid
    @NotNull(message = "Property name cannot be null.")

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    /**
    **/
    public GetOrganizationResponse description(String description) {

        this.description = description;
        return this;
    }
    
    @ApiModelProperty(example = "Building constructions", value = "")
    @JsonProperty("description")
    @Valid
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    /**
    **/
    public GetOrganizationResponse status(StatusEnum status) {

        this.status = status;
        return this;
    }
    
    @ApiModelProperty(example = "ACTIVE", required = true, value = "")
    @JsonProperty("status")
    @Valid
    @NotNull(message = "Property status cannot be null.")

    public StatusEnum getStatus() {
        return status;
    }
    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    /**
    **/
    public GetOrganizationResponse created(String created) {

        this.created = created;
        return this;
    }
    
    @ApiModelProperty(example = "2021-10-25T12:31:53.406Z", required = true, value = "")
    @JsonProperty("created")
    @Valid
    @NotNull(message = "Property created cannot be null.")

    public String getCreated() {
        return created;
    }
    public void setCreated(String created) {
        this.created = created;
    }

    /**
    **/
    public GetOrganizationResponse lastModified(String lastModified) {

        this.lastModified = lastModified;
        return this;
    }
    
    @ApiModelProperty(example = "2021-10-25T12:31:53.406Z", required = true, value = "")
    @JsonProperty("lastModified")
    @Valid
    @NotNull(message = "Property lastModified cannot be null.")

    public String getLastModified() {
        return lastModified;
    }
    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    /**
    **/
    public GetOrganizationResponse type(TypeEnum type) {

        this.type = type;
        return this;
    }
    
    @ApiModelProperty(example = "TENANT", required = true, value = "")
    @JsonProperty("type")
    @Valid
    @NotNull(message = "Property type cannot be null.")

    public TypeEnum getType() {
        return type;
    }
    public void setType(TypeEnum type) {
        this.type = type;
    }

    /**
    **/
    public GetOrganizationResponse parent(ParentOrganization parent) {

        this.parent = parent;
        return this;
    }
    
    @ApiModelProperty(value = "")
    @JsonProperty("parent")
    @Valid
    public ParentOrganization getParent() {
        return parent;
    }
    public void setParent(ParentOrganization parent) {
        this.parent = parent;
    }

    /**
    **/
    public GetOrganizationResponse attributes(List<Attribute> attributes) {

        this.attributes = attributes;
        return this;
    }
    
    @ApiModelProperty(value = "")
    @JsonProperty("attributes")
    @Valid
    public List<Attribute> getAttributes() {
        return attributes;
    }
    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    public GetOrganizationResponse addAttributesItem(Attribute attributesItem) {
        if (this.attributes == null) {
            this.attributes = new ArrayList<>();
        }
        this.attributes.add(attributesItem);
        return this;
    }

        /**
    **/
    public GetOrganizationResponse permissions(List<String> permissions) {

        this.permissions = permissions;
        return this;
    }
    
    @ApiModelProperty(value = "")
    @JsonProperty("permissions")
    @Valid
    public List<String> getPermissions() {
        return permissions;
    }
    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }

    public GetOrganizationResponse addPermissionsItem(String permissionsItem) {
        if (this.permissions == null) {
            this.permissions = new ArrayList<>();
        }
        this.permissions.add(permissionsItem);
        return this;
    }

    

    @Override
    public boolean equals(java.lang.Object o) {

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GetOrganizationResponse getOrganizationResponse = (GetOrganizationResponse) o;
        return Objects.equals(this.id, getOrganizationResponse.id) &&
            Objects.equals(this.name, getOrganizationResponse.name) &&
            Objects.equals(this.description, getOrganizationResponse.description) &&
            Objects.equals(this.status, getOrganizationResponse.status) &&
            Objects.equals(this.created, getOrganizationResponse.created) &&
            Objects.equals(this.lastModified, getOrganizationResponse.lastModified) &&
            Objects.equals(this.type, getOrganizationResponse.type) &&
            Objects.equals(this.parent, getOrganizationResponse.parent) &&
            Objects.equals(this.attributes, getOrganizationResponse.attributes) &&
            Objects.equals(this.permissions, getOrganizationResponse.permissions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, status, created, lastModified, type, parent, attributes, permissions);
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("class GetOrganizationResponse {\n");
        
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    description: ").append(toIndentedString(description)).append("\n");
        sb.append("    status: ").append(toIndentedString(status)).append("\n");
        sb.append("    created: ").append(toIndentedString(created)).append("\n");
        sb.append("    lastModified: ").append(toIndentedString(lastModified)).append("\n");
        sb.append("    type: ").append(toIndentedString(type)).append("\n");
        sb.append("    parent: ").append(toIndentedString(parent)).append("\n");
        sb.append("    attributes: ").append(toIndentedString(attributes)).append("\n");
        sb.append("    permissions: ").append(toIndentedString(permissions)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
    * Convert the given object to string with each line indented by 4 spaces
    * (except the first line).
    */
    private String toIndentedString(java.lang.Object o) {

        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n");
    }
}

