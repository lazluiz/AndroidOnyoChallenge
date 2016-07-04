package io.lazluiz.onyochallenge.data;

import android.test.AndroidTestCase;
import android.util.Log;

import io.lazluiz.onyochallenge.model.Company;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Luiz F. Lazzarin on 13/06/2016.
 * Email: lf.lazzarin@gmail.com
 * Github: /luizfelippe
 */

public class TestFetchOnyoData extends AndroidTestCase {

    private static final String LOG_TAG = TestFetchOnyoData.class.getSimpleName();

    public void testHelloTher(){
        String test = "hello";

        assertEquals("Should be equal ):", test, test);
    }

    public void testFetchData(){
        FetchOnyoData fod = new FetchOnyoData();

        Realm realm = Realm.getDefaultInstance();

        // Check data
        RealmResults<Company> companies = realm.where(Company.class).findAll();
        assertTrue("Should be greater than 0", companies.size() > 0);

        Log.i(LOG_TAG, "Company Size: " + companies.size());
        for(Company c : companies){
            Log.i(LOG_TAG, "Value: " + c.getDisplayName());
        }

        realm.close();
    }

}
