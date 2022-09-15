package com.aptos.request.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author liqiang
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Resource extends Module {

    String resourceName;

    List<Resource> resources;

    /**
     * resourceTag
     *
     * @return
     */
    public String resourceTag() {
        if (Objects.isNull(this.resources) || this.resources.isEmpty()) {
            return this.moduleAddress + "::" + this.moduleName + "::" + this.resourceName;
        } else {
            List<String> resourceTags = new ArrayList<>(this.resources.size());
            this.resources.forEach(resource -> resourceTags.add(resource.resourceTag()));
            return this.moduleAddress + "::" + this.moduleName + "::" + this.resourceName + "<" + resourceTags.stream().collect(Collectors.joining(",")) + ">";
        }
    }

}