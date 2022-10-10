package com.aptos.request.v1.model;

import com.alibaba.fastjson2.annotation.JSONField;
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
public class Event<T> implements Serializable {

    @JSONField(name = "key")
    String key;

    @JSONField(name = "guid")
    Guid guid;

    @JSONField(name = "sequence_number")
    String sequenceNumber;

    @JSONField(name = "type")
    String type;

    @JSONField(name = "data")
    T data;

}