/*
 * LagosFX application framework
 *
 * Copyright(c) 2014 - 2016, Cem Ikta
 */
package com.lagossoftware.lagosfx.service;

import java.util.HashMap;
import java.util.Map;

/**
 * Query Parameter.
 *
 * <pre>
 * // import static com.devsniper.desktop.customers.service.QueryParameter.*;
 * count = categoryService.findWithNamedQuery(Category.FIND_ALL,
 *      with(&quot;name&quot;, "filter").parameters()).size();
 * </pre>
 *
 * @author Cem Ikta
 */
public class QueryParameter {

    private Map<String, Object> parameters = null;

    /**
     * New instance only from <code>with</code>.
     *
     * @param name parameter name
     * @param value parameter value
     */
    private QueryParameter(String name, Object value) {
        this.parameters = new HashMap<>();
        this.parameters.put(name, value);
    }

    /**
     * Create new QueryParameter instance.
     *
     * @param name parameter name
     * @param value parameter value
     * @return query parameter new instance.
     */
    public static QueryParameter with(String name, Object value) {
        return new QueryParameter(name, value);
    }

    /**
     * Adds new parameter.
     *
     * @param name parameter name
     * @param value parameter value
     * @return query parameter instance
     */
    public QueryParameter and(String name, Object value) {
        this.parameters.put(name, value);
        return this;
    }

    /**
     * Gets query parameters.
     *
     * @return parameters map
     */
    public Map<String, Object> parameters() {
        return this.parameters;
    }

}
