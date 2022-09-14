package com.aptos.request.v1;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author liqiang
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseAccountResources implements Serializable {

    @JSONField(name = "block_height")
    List<ResponseAccountResource> blockHeight;

}