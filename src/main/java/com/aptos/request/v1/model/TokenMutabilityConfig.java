package com.aptos.request.v1.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author liqiang
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenMutabilityConfig implements Serializable {

    boolean maximum;

    boolean uri;

    boolean royalty;

    boolean description;

    boolean properties;

}