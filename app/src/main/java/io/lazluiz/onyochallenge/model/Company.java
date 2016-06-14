package io.lazluiz.onyochallenge.model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Luiz F. Lazzarin on 13/06/2016.
 * Email: lf.lazzarin@gmail.com
 * Github: /luizfelippe
 */

public class Company extends RealmObject {

    @PrimaryKey
    private int numericalId;
    private String displayName;
    private String address;
    private String geoLat;
    private String geoLon;
    private RealmList<Image> image;

    public int getNumericalId() {
        return numericalId;
    }

    public void setNumericalId(int numericalId) {
        this.numericalId = numericalId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGeoLat() {
        return geoLat;
    }

    public void setGeoLat(String geoLat) {
        this.geoLat = geoLat;
    }

    public String getGeoLon() {
        return geoLon;
    }

    public void setGeoLon(String geoLon) {
        this.geoLon = geoLon;
    }

    public RealmList<Image> getImage() {
        return image;
    }

    public void setImage(RealmList<Image> image) {
        this.image = image;
    }
}
