package com.aptos.request.v1.rpc.request;

import com.aptos.request.v1.rpc.AptosMethod;
import com.aptos.request.v1.rpc.body.IAptosRequestBody;
import com.aptos.request.v1.rpc.query.IAptosRequestQuery;

import java.io.Serializable;

/**
 * @author liqiang
 */
public interface IAptosRequest extends Serializable {

    /**
     * method
     *
     * @return AptosMethod
     */
    default AptosMethod method() {
        return AptosMethod.GET;
    }

    /**
     * path
     *
     * @return String
     */
    String path();

    /**
     * query
     *
     * @return IAptosRequestQuery
     */
    default IAptosRequestQuery query() {
        return null;
    }

    /**
     * body
     *
     * @return IAptosRequestBody
     */
    default IAptosRequestBody body() {
        return null;
    }

}