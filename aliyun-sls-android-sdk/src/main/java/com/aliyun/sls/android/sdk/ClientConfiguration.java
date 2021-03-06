/**
 * Copyright (C) Alibaba Cloud Computing, 2015
 * All rights reserved.
 * 
 * 版权所有 （C）阿里巴巴云计算，2015
 */

package com.aliyun.sls.android.sdk;

import com.aliyun.sls.android.sdk.utils.VersionInfoUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The client side configuration
 */
public class ClientConfiguration {

    public enum NetworkPolicy{
        /* wifi-only,只有wifi情况下上传 */
        WIFI_ONLY,
        /* 有网,只有wifi情况下上传 */
        WWAN_OR_WIFI
    }

    
    private static final int DEFAULT_MAX_RETRIES = 2;

    private int maxConcurrentRequest = 5;
    private int socketTimeout = 15 * 1000;
    private int connectionTimeout = 15 * 1000;
    private int maxErrorRetry = DEFAULT_MAX_RETRIES;
    private List<String> customCnameExcludeList = new ArrayList<String>();
    private String proxyHost;
    private int proxyPort;

    private Boolean cachable = false;           // 是否开启缓存日志
    private NetworkPolicy connectType = NetworkPolicy.WIFI_ONLY;  // 缓存日志的发送策略

    /**
     * Constructor
     */
    public ClientConfiguration(){

    }

    /**
     * Gets the default configuration instance
     */
    public static ClientConfiguration getDefaultConf() {
        return new ClientConfiguration();
    }

    /**
     * Gets the max concurrent request count
     * @return
     */
    public int getMaxConcurrentRequest() {
        return maxConcurrentRequest;
    }

    /**
     * Sets the max concurrent request count
     * @param maxConcurrentRequest
     *          The max HTTP request count
     */
    public void setMaxConcurrentRequest(int maxConcurrentRequest) {
        this.maxConcurrentRequest = maxConcurrentRequest;
    }

    /**
     * Gets the socket timeout in milliseconds
     * 0 means infinite (not recommended)
     * @return the socket timeout in milliseconds
     */
    public int getSocketTimeout() {
        return socketTimeout;
    }

    /**
     * Gets the socket timeout in milliseconds
     * 0 means infinite (not recommended)
     * @param socketTimeout
     *          the socket timeout in milliseconds
     */
    public void setSocketTimeout(int socketTimeout) {
        this.socketTimeout = socketTimeout;
    }

    /**
     * Gets the connection timeout in milliseconds
     * @return The connection timeout in milliseconds
     */
    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    /**
     * Sets the connection timeout in milliseconds
     * @param connectionTimeout
     *          The connection timeout in milliseconds
     */
    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    /**
     * set max log file size  default 5mb
     * @param max_log_size
     */
//    public void setMaxLogSize(long max_log_size) {
//        this.max_log_size = max_log_size;
//    }
//
//    public long getMaxLogSize() {
//        return max_log_size;
//    }

    /**
     * Gets the max retry count after the recoverable failure. By default it's 2.
     * @return The max retry count after the recoverable failure.
     */
    public int getMaxErrorRetry() {
        return maxErrorRetry;
    }

    /**
     * Sets the max retry count after the recoverable failure. By default it's 2.
     * @param maxErrorRetry
     *          The max retry count after the recoverable failure.
     */
    public void setMaxErrorRetry(int maxErrorRetry) {
        this.maxErrorRetry = maxErrorRetry;
    }

    /**
     * Sets CNAME excluded list
     * @param customCnameExcludeList CNAME excluded list
     */
    public void setCustomCnameExcludeList(List<String> customCnameExcludeList) {
        if (customCnameExcludeList == null || customCnameExcludeList.size()==0) {
            throw new IllegalArgumentException("cname exclude list should not be null.");
        }

        this.customCnameExcludeList.clear();
        for (String host : customCnameExcludeList) {
            if (host.contains("://")) {
                this.customCnameExcludeList.add(host.substring(host.indexOf("://") + 3));
            } else {
                this.customCnameExcludeList.add(host);
            }
        }
    }

    /**
     * Gets the immutable CName excluded list. The element in this list will skip the CName resolution.
     * @return CNAME excluded list.
     */
    public List<String> getCustomCnameExcludeList() {
        return Collections.unmodifiableList(this.customCnameExcludeList);
    }

    public String getProxyHost() {
        return proxyHost;
    }

    public void setProxyHost(String proxyHost) {
        this.proxyHost = proxyHost;
    }

    public int getProxyPort() {
        return proxyPort;
    }

    public void setProxyPort(int proxyPort) {
        this.proxyPort = proxyPort;
    }

    public Boolean getCachable() {
        return cachable;
    }

    public void setCachable(Boolean cachable) {
        this.cachable = cachable;
    }

    public NetworkPolicy getConnectType() {
        return connectType;
    }

    public void setConnectType(NetworkPolicy connectType) {
        this.connectType = connectType;
    }
}
