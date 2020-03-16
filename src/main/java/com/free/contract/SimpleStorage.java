package com.free.contract;

/**
 * @ClassNameSimpleStorage
 * @Description
 * @Author Free
 * @Date2020/3/4 21:48
 * @Version V1.0
 **/

import io.nuls.contract.sdk.;
import io.nuls.contract.sdk.annotation.JSONSerializable;
import io.nuls.contract.sdk.annotation.Payable;
import io.nuls.contract.sdk.annotation.Required;
import io.nuls.contract.sdk.annotation.View;
import io.nuls.contract.sdk.Address;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class SimpleStorage implements Contract {

    private String storedData;
    private Address address;

    @Payable
    public void _payable() {

    }

    @Payable
    public void setAddress(@Required String address) {
        Address address1 = new Address(address);
        this.address = address1;
    }
    /**
     * 合约向该地址转账 100
     */
    @Payable
    public void applyforcompensation(@Required String address) {
        Address comp_address = new Address(address);
        BigInteger bi = new BigInteger("100000000");
        comp_address.transfer(bi);
    }
    /**
     * 查询余额  调用之前先绑定
     */
    @View
    public BigInteger getaddressmoney() {
        BigInteger balance = address.balance();
        return balance;
    }
    @View
    public String getstoredData() {
        return this.storedData;
    }



    /**
     * 标记@Payable的方法，才能在调用时候传入NULS金额
     */
    @Payable
    public void setStoredData(@Required String storedData) {
        this.storedData = storedData;
    }



    /**
     * 返回值会被VM自动JSON序列化，以JSON字符串的形式返回
     * 注意：对象层级不得超过3层，超过3层的部分会调用对象的toString方法，不会再继续序列化
     */
    @JSONSerializable
    public Map JsonSerializableMap() {
        Map map = new HashMap();
        map.put("name", "nuls");
        map.put("url", "https://nuls.io");
        return map;
    }

}