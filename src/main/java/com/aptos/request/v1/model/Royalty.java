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
public class Royalty implements Serializable {

    @JSONField(name = "payee_address")
    String payeeAddress;

    @JSONField(name = "royalty_points_denominator")
    String royaltyPointsDenominator;

    @JSONField(name = "royalty_points_numerator")
    String royaltyPointsNumerator;

}
