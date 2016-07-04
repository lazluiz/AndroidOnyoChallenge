package io.lazluiz.onyochallenge.model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Luiz F. Lazzarin on 13/06/2016.
 * Email: lf.lazzarin@gmail.com
 * Github: /luizfelippe
 */

public class Category extends RealmObject {

    @PrimaryKey
    private int numericalId;
    private String name;
    private int order;
    private RealmList<Image> image;

    public int getNumericalId() {
        return numericalId;
    }

    public void setNumericalId(int numericalId) {
        this.numericalId = numericalId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public RealmList<Image> getImage() {
        return image;
    }

    public void setImage(RealmList<Image> image) {
        this.image = image;
    }
}
