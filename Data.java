package a3csci3130.acme.com.assignment3;

import java.io.Serializable;

public class Data implements Serializable {
    public String businessNumber;
    public String name;
    public String primaryBusiness;
    public String address;
    public String province;
    public String id;

    public Data(){

    }

    public Data(String businessNumber, String name, String primaryBusiness, String address, String province,String id) {
        this.businessNumber = businessNumber;
        this.name = name;
        this.primaryBusiness = primaryBusiness;
        this.address = address;
        this.province = province;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String uid) {
        this.id = uid;
    }

    public String getBusinessNumber() {
        return businessNumber;
    }

    public void setBusinessNumber(String businessNumber) {
        this.businessNumber = businessNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrimaryBusiness() {
        return primaryBusiness;
    }

    public void setPrimaryBusiness(String primaryBusiness) {
        this.primaryBusiness = primaryBusiness;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}
