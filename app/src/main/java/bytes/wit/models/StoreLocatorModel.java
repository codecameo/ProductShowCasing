package bytes.wit.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Md. Sifat-Ul Haque on 1/4/2017.
 */

public class StoreLocatorModel implements Serializable {

    @SerializedName("address")
    private String store_address;
    @SerializedName("mobile")
    private String mobile_number;
    private String district;
    private double latitude, longitude;

    public String getStore_address() {
        return store_address;
    }

    public void setStore_address(String store_address) {
        this.store_address = store_address;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
