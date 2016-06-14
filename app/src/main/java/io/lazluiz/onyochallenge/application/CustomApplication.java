package io.lazluiz.onyochallenge.application;

import android.app.Application;

import com.android.volley.toolbox.Volley;

import io.lazluiz.onyochallenge.data.NetworkQueue;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Luiz F. Lazzarin on 13/06/2016.
 * Email: lf.lazzarin@gmail.com
 * Github: /luizfelippe
 */

public class CustomApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // Volley
        NetworkQueue.getInstance().init(this);

        // Realm
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this)
                .name("realm-onyochallenge.realm")
                .deleteRealmIfMigrationNeeded()
                .build();

        Realm.setDefaultConfiguration(realmConfiguration);
    }
}
