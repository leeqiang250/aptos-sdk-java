package com.aptos.utils;

import com.alibaba.fastjson2.JSONObject;
import com.aptos.request.v1.model.Signature;
import com.aptos.request.v1.model.TransactionPayload;
import com.aptos.request.v1.model.Transaction;
import com.aptos.request.v1.rpc.body.EncodeSubmitBody;
import com.aptos.request.v1.rpc.body.SubmitTransactionBody;

import java.util.List;

public class NodeApiUtils {

    private static final long DEFAULT_MAX_GAS_AMOUNT = 2000L;

    public static void newEncodeSubmissionRequest(AptosClient aptosClient) {
        String publicKey = "0xa76e9dd1a2d9101de47e69e52e0232060b95cd7d80265d61c3fa25e406389b75";
        String privateKey = "0x09cc77f21e471431df54280da75749069b54bfe42e3cd2b532a1024262339090";

        String sender = "0x2b490841c230a31fe012f3b2a3f3d146316be073e599eb7d7e5074838073ef14";
        String sequenceNumber = aptosClient.requestAccount(sender).getSequenceNumber();
        String maxGasAmount = "4003243";
        String gasUnitPrice = String.valueOf(aptosClient.requestGasEstimate().getGasEstimate());
        String expirationTimestampSecs = String.valueOf(System.currentTimeMillis() / 1000L + 600L);
        String function = "0x2b490841c230a31fe012f3b2a3f3d146316be073e599eb7d7e5074838073ef14::message::set_message";

        TransactionPayload transactionPayload = TransactionPayload.builder()
                .type(TransactionPayload.ENTRY_FUNCTION_PAYLOAD)
                .function(function)
                .arguments(List.of("hello"))
                .typeArguments(List.of())
                .build();

        EncodeSubmitBody requestEncodeSubmitBody = new EncodeSubmitBody();
        requestEncodeSubmitBody.setSender(sender);
        requestEncodeSubmitBody.setSequenceNumber(sequenceNumber);
        requestEncodeSubmitBody.setMaxGasAmount(maxGasAmount);
        requestEncodeSubmitBody.setGasUnitPrice(gasUnitPrice);
        requestEncodeSubmitBody.setExpirationTimestampSecs(expirationTimestampSecs);
        requestEncodeSubmitBody.setPayload(transactionPayload);

        String unSign = aptosClient.requestEncodeSubmit(requestEncodeSubmitBody);

        byte[] signed = SignatureUtils.ed25519Sign(HexUtils.hexToByteArray(privateKey), HexUtils.hexToByteArray(unSign));

        Signature signature = Signature.builder()
                .type(Signature.ED25519_SIGNATURE)
                .publicKey(publicKey)
                .signature(HexUtils.byteArrayToHexWithPrefix(signed))
                .build();

        SubmitTransactionBody submitTransactionBody = JSONObject.parseObject(JSONObject.toJSONString(requestEncodeSubmitBody), SubmitTransactionBody.class);
        submitTransactionBody.setSignature(signature);

        Transaction transaction = aptosClient.requestSubmitTransaction(submitTransactionBody);
        System.out.println(transaction.toString());

    }

}