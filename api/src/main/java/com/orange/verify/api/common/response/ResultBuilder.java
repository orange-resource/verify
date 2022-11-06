package com.orange.verify.api.common.response;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 结果建造者
 * @author orange
 */
public class ResultBuilder implements Serializable {

    private Map<String, Object> map = new LinkedHashMap();

    public static ResultBuilder create() {
        return new ResultBuilder();
    }

    public ResultBuilder set(String name, Object value) {
        if (null != name) {
            map.put(name, value);
        }
        return this;
    }

    public ResultBuilder setTotal(Object total) {
        map.put("total", total);
        return this;
    }

    public ResultBuilder setPage(Object page) {
        map.put("page", page);
        return this;
    }

    public ResultBuilder setList(Object list) {
        map.put("list", list);
        return this;
    }

    public ResultBuilder setContent(Object content) {
        map.put("content", content);
        return this;
    }

    public ResultBuilder setDetail(Object details) {
        map.put("detail", details);
        return this;
    }

    public ResultBuilder setText(Object text) {
        map.put("text", text);
        return this;
    }

    public ResultBuilder setUrl(Object url) {
        map.put("url", url);
        return this;
    }

    public ResultBuilder setStatus(Object status) {
        map.put("status", status);
        return this;
    }

    public ResultBuilder setCount(Object count) {
        map.put("count", count);
        return this;
    }

    public ResultBuilder setTree(Object tree) {
        map.put("tree", tree);
        return this;
    }

    public ResultBuilder setRelation(Object relation) {
        map.put("relation", relation);
        return this;
    }

    public ResultBuilder setQuantity(Object quantity) {
        map.put("quantity", quantity);
        return this;
    }

    public ResultBuilder setToken(Object token) {
        map.put("token", token);
        return this;
    }

    public ResultBuilder setSign(Object sign) {
        map.put("sign", sign);
        return this;
    }

    public ResultBuilder setIndex(Object index) {
        map.put("index", index);
        return this;
    }

    public ResultBuilder setUser(Object user) {
        map.put("user", user);
        return this;
    }

    public ResultBuilder setType(Object type) {
        map.put("type", type);
        return this;
    }

    public ResultBuilder setTransactionId(Object transactionId) {
        map.put("transactionId", transactionId);
        return this;
    }

    public ResultBuilder setExist(Object exist) {
        map.put("exist", exist);
        return this;
    }

    public ResultBuilder setAddress(Object address) {
        map.put("address", address);
        return this;
    }

    public ResultBuilder setTag(Object tag) {
        map.put("tag", tag);
        return this;
    }

    public ResultBuilder setUri(Object uri) {
        map.put("uri", uri);
        return this;
    }

    public ResultBuilder setTask(Object task) {
        map.put("task", task);
        return this;
    }

    public ResultBuilder setTaskInfo(Object taskInfo) {
        map.put("taskInfo", taskInfo);
        return this;
    }

    public ResultBuilder setTaskId(Object taskId) {
        map.put("taskId", taskId);
        return this;
    }

    public ResultBuilder setResult(Object result) {
        map.put("result", result);
        return this;
    }

    public ResultBuilder setDate(Object date) {
        map.put("date", date);
        return this;
    }

    public ResultBuilder setPort(Object port) {
        map.put("port", port);
        return this;
    }

    public ResultBuilder setId(Object id) {
        map.put("id", id);
        return this;
    }

    public ResultBuilder setInfo(Object info) {
        map.put("info", info);
        return this;
    }

    public ResultBuilder setTimestamp(Object timestamp) {
        map.put("timestamp", timestamp);
        return this;
    }

    public ResultBuilder setCurrent(Object current) {
        map.put("current", current);
        return this;
    }

    public ResultBuilder setProgress(Object progress) {
        map.put("progress", progress);
        return this;
    }

    public Map<String, Object> build() {
        return map;
    }

}
