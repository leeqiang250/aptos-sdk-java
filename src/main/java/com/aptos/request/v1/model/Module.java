package com.aptos.request.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * @author liqiang
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class Module implements Serializable {

    String moduleAddress;

    String moduleName;

    /**
     * type
     *
     * @return
     */
    public abstract String type();

}