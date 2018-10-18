package com.jesse.onecake.common.utils;

import java.util.HashMap;
import java.util.Map;

public class BaseContextHandler {
    public static ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal();

    public BaseContextHandler() {
    }

    public static void set(String key, Object value) {
        Map<String, Object> map = (Map)threadLocal.get();
        if (map == null) {
            map = new HashMap();
            threadLocal.set(map);
        }

        ((Map)map).put(key, value);
    }

    public static Object get(String key) {
        Map<String, Object> map = (Map)threadLocal.get();
        if (map == null) {
            map = new HashMap();
            threadLocal.set(map);
            return null;
        } else {
            return map.get(key);
        }
    }

    public static String getUserID() {
        Object value = get("currentUserId");
        return returnObjectValue(value);
    }

    public static String getUsername() {
        Object value = get("currentUserName");
        return returnObjectValue(value);
    }

    public static String getName() {
        Object value = get("currentUser");
        return returnObjectValue(value);
    }

    public static String getToken() {
        Object value = get("currentUserToken");
        return returnObjectValue(value);
    }

    public static String getDepartId() {
        Object value = get("currentDepartId");
        return returnObjectValue(value);
    }

    public static String getTenantId() {
        Object value = get("currentTenantId");
        return returnObjectValue(value);
    }

    public static String getAppCode() {
        Object value = get("currentAppKey");
        return returnObjectValue(value);
    }

    public static void setAppCode(String appCode) {
        set("currentAppKey", appCode);
    }

    public static String getAppChild() {
        Object value = get("currentAppIsChild");
        return returnObjectValue(value);
    }

    public static String getAppManager() {
        Object value = get("currentAppManager");
        return returnObjectValue(value);
    }

    public static void setAppManager(String flag) {
        set("currentAppManager", flag);
    }

    public static void setAppChild(String appChild) {
        set("currentAppIsChild", appChild);
    }

    public static void setTenantId(String tenantId) {
        set("currentTenantId", tenantId);
    }

    public static void setDepartId(String departId) {
        set("currentDepartId", departId);
    }

    public static void setToken(String token) {
        set("currentUserToken", token);
    }

    public static void setName(String name) {
        set("currentUser", name);
    }

    public static void setUserID(String userID) {
        set("currentUserId", userID);
    }

    public static void setUsername(String username) {
        set("currentUserName", username);
    }

    private static String returnObjectValue(Object value) {
        return value == null ? "" : value.toString();
    }

    public static void remove() {
        threadLocal.remove();
    }
}
