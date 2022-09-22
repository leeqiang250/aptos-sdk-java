package com.aptos.request.v1.model;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * @author liqiang
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionPayload implements Serializable {

    public static final String ENTRY_FUNCTION_PAYLOAD = "entry_function_payload";

    @JSONField(name = "type")
    String type;

    @JSONField(name = "function")
    String function;

    @JSONField(name = "arguments")
    List arguments = List.of();

    @JSONField(name = "type_arguments")
    List typeArguments = List.of();

}