package com.baktas.reportingapi.models;


import java.util.HashMap;
import java.util.Map;

public class MerchantTransaction {

    private Integer id;
    private String referenceNo;
    private Integer merchantId;
    private Integer fxTransactionId;
    private Integer acquirerTransactionId;
    private String chainId;
    private Integer agentInfoId;
    private Object returnUrl;
    private String status;
    private String operation;
    private String createdAt;
    private String updatedAt;
    private Object deletedAt;
    private String code;
    private String message;
    private String channel;
    private Object customData;
    private Object parentId;
    private String type;
    private String transactionId;
    private Agent agent;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public MerchantTransaction withId(Integer id) {
        this.id = id;
        return this;
    }

    public String getReferenceNo() {
        return referenceNo;
    }

    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
    }

    public MerchantTransaction withReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
        return this;
    }

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public MerchantTransaction withMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
        return this;
    }

    public Integer getFxTransactionId() {
        return fxTransactionId;
    }

    public void setFxTransactionId(Integer fxTransactionId) {
        this.fxTransactionId = fxTransactionId;
    }

    public MerchantTransaction withFxTransactionId(Integer fxTransactionId) {
        this.fxTransactionId = fxTransactionId;
        return this;
    }

    public Integer getAcquirerTransactionId() {
        return acquirerTransactionId;
    }

    public void setAcquirerTransactionId(Integer acquirerTransactionId) {
        this.acquirerTransactionId = acquirerTransactionId;
    }

    public MerchantTransaction withAcquirerTransactionId(Integer acquirerTransactionId) {
        this.acquirerTransactionId = acquirerTransactionId;
        return this;
    }

    public String getChainId() {
        return chainId;
    }

    public void setChainId(String chainId) {
        this.chainId = chainId;
    }

    public MerchantTransaction withChainId(String chainId) {
        this.chainId = chainId;
        return this;
    }

    public Integer getAgentInfoId() {
        return agentInfoId;
    }

    public void setAgentInfoId(Integer agentInfoId) {
        this.agentInfoId = agentInfoId;
    }

    public MerchantTransaction withAgentInfoId(Integer agentInfoId) {
        this.agentInfoId = agentInfoId;
        return this;
    }

    public Object getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(Object returnUrl) {
        this.returnUrl = returnUrl;
    }

    public MerchantTransaction withReturnUrl(Object returnUrl) {
        this.returnUrl = returnUrl;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public MerchantTransaction withStatus(String status) {
        this.status = status;
        return this;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public MerchantTransaction withOperation(String operation) {
        this.operation = operation;
        return this;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public MerchantTransaction withCreatedAt(String createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public MerchantTransaction withUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public Object getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Object deletedAt) {
        this.deletedAt = deletedAt;
    }

    public MerchantTransaction withDeletedAt(Object deletedAt) {
        this.deletedAt = deletedAt;
        return this;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public MerchantTransaction withCode(String code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MerchantTransaction withMessage(String message) {
        this.message = message;
        return this;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public MerchantTransaction withChannel(String channel) {
        this.channel = channel;
        return this;
    }

    public Object getCustomData() {
        return customData;
    }

    public void setCustomData(Object customData) {
        this.customData = customData;
    }

    public MerchantTransaction withCustomData(Object customData) {
        this.customData = customData;
        return this;
    }

    public Object getParentId() {
        return parentId;
    }

    public void setParentId(Object parentId) {
        this.parentId = parentId;
    }

    public MerchantTransaction withParentId(Object parentId) {
        this.parentId = parentId;
        return this;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public MerchantTransaction withType(String type) {
        this.type = type;
        return this;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public MerchantTransaction withTransactionId(String transactionId) {
        this.transactionId = transactionId;
        return this;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public MerchantTransaction withAgent(Agent agent) {
        this.agent = agent;
        return this;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public MerchantTransaction withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }
}