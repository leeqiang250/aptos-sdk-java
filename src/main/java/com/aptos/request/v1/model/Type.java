package com.aptos.request.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Objects;

/**
 * @author liqiang
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Type extends Module {

    String name;

    @Override
    public String type() {
        if (Objects.isNull(this.moduleAddress) || "".equals(this.moduleAddress)) {
            throw new RuntimeException("Invalid Type ModuleAddress");
        }
        if (Objects.isNull(this.moduleName) || "".equals(this.moduleName)) {
            throw new RuntimeException("Invalid Type ModuleName");
        }
        if (Objects.isNull(this.name) || "".equals(this.name)) {
            throw new RuntimeException("Invalid Type Name");
        }

        return this.moduleAddress + "::" + this.moduleName + "::" + this.name;
    }

}