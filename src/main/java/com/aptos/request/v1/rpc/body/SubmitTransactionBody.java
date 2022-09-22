package com.aptos.request.v1.rpc.body;

import com.aptos.request.v1.model.SubmitTransaction;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liqiang
 */
@Data
@NoArgsConstructor
public class SubmitTransactionBody extends SubmitTransaction implements IAptosRequestBody {

}