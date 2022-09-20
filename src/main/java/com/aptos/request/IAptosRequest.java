package com.aptos.request;

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