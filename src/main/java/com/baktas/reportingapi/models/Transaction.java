package com.baktas.reportingapi.models;

import java.util.HashMap;
import java.util.Map;

public class Transaction {

    private MerchantTransaction merchant;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public MerchantTransaction getMerchant() {
        return merchant;
    }

    public void setMerchant(MerchantTransaction merchant) {
        this.merchant = merchant;
    }

    public Transaction withMerchant(MerchantTransaction merchant) {
        this.merchant = merchant;
        return this;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Transaction withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }
}