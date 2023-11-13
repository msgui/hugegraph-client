/*
 * Copyright 2017 HugeGraph Authors
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with this
 * work for additional information regarding copyright ownership. The ASF
 * licenses this file to You under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.baidu.hugegraph.api.traverser;

import com.baidu.hugegraph.api.graph.GraphAPI;
import com.baidu.hugegraph.client.RestClient;
import com.baidu.hugegraph.rest.RestResult;
import com.baidu.hugegraph.structure.graph.Edge;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class EdgeExistenceAPI extends TraversersAPI {

    public EdgeExistenceAPI(RestClient client, String graph) {
        super(client, graph);
    }

    @Override
    protected String type() {
        return "edgeexistence";
    }

    public List<Edge> get(Object sourceId, Object targetId, String edgeLabel,
                          String sortValues, long limit) {
        String source = GraphAPI.formatVertexId(sourceId, false);
        String target = GraphAPI.formatVertexId(targetId, false);

        checkLimit(limit);

        Map<String, Object> params = new LinkedHashMap<>();
        params.put("source", source);
        params.put("target", target);
        params.put("label", edgeLabel);
        params.put("sortValues", sortValues);
        params.put("limit", limit);
        RestResult result = this.client.get(this.path(), params);
        return result.readList(this.type(), Edge.class);
    }
}
