package com.aptos.request;

import java.io.Serializable;
import java.util.List;

/**
 * @author liqiang
 */
public interface IAptosRequest extends Serializable {

    /**
     * method
     *
     * @return
     */
    default AptosMethod method() {
        return AptosMethod.GET;
    }

    /**
     * path
     *
     * @return
     */
    String path();

    /**
     * body
     *
     * @return
     */
    default List<Object> body() {
        return List.of();
    }

    /**
     * query
     *
     * @return
     */
    default IAptosRequestQuery query() {
        return null;
    }

}