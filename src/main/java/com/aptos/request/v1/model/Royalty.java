package com.aptos.request.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty("payee_address")
    String payeeAddress;

    @JsonProperty("royalty_points_denominator")
    String royaltyPointsDenominator;

    @JsonProperty("royalty_points_numerator")
    String royaltyPointsNumerator;

}
