package com.aptos.request.v1;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

/**
 * @author liqiang
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseEvent implements Serializable {

    @JSONField(name = "key")
    String key;

    @JSONField(name = "sequence_number")
    String sequenceNumber;

    @JSONField(name = "type")
    String type;

    @JSONField(name = "data")
    Map<String, String> data;

}