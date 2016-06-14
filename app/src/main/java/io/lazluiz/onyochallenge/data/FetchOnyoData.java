package io.lazluiz.onyochallenge.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import io.lazluiz.onyochallenge.model.Company;
import io.realm.Realm;

/**
 * Created by Luiz F. Lazzarin on 13/06/2016.
 * Email: lf.lazzarin@gmail.com
 * Github: /luizfelippe
 */

public class FetchOnyoData {

    private static final String LOG_TAG = FetchOnyoData.class.getSimpleName();

    // I couldn't access the data from https://api.staging.onyo.com/v1/mobile/brand/1/company
    // so I put static JSON data at GitHub to simulate the API access.
    private static final String URL_DATA = "https://raw.githubusercontent.com/luizfelippe/AndroidOnyoChallenge/master/api_data";

    // Time for not getting data
    private static final String SP_FETCH_TIME = LOG_TAG + ".FetchTime";
    private static final int TIME_OFFSET_IN_MS = (1000 * 60 * 60) * 3; // 3 hours

    private Context mContext;
    private NetworkQueue mNetworkQueue;

    public FetchOnyoData(Context c) {
        mContext = c;
        mNetworkQueue = NetworkQueue.getInstance();
    }

    public void getData() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(mContext);
        long lastInsert = sp.getLong(SP_FETCH_TIME, 0);
        long diff = System.currentTimeMillis() - lastInsert;

        // Let's not update every time we launch our app
        if (diff > TIME_OFFSET_IN_MS) {
            mNetworkQueue.doGet(URL_DATA, LOG_TAG, new NetworkQueue.NetworkRequestCallback<JSONObject>() {

                @Override
                public void onRequestResponse(JSONObject response) {
                    persistData(response);
                }

                @Override
                public void onRequestError(Exception error) {
                    Log.e(LOG_TAG, error.getMessage());
                }
            });
        }
    }

    private void persistData(JSONObject o) {
        try {
            final String ONYO_COMPANIES = "companies";
            final String ONYO_CATEGORIES = "categories";

            final JSONArray companies = o.getJSONArray(ONYO_COMPANIES);
            JSONArray categories = o.getJSONArray(ONYO_CATEGORIES);

            Log.i(LOG_TAG, companies.toString());
            Log.i(LOG_TAG, categories.toString());

            Realm.getDefaultInstance().executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    // Persist
                    realm.createOrUpdateAllFromJson(Company.class, companies);

                    // Set time to SharedPreferences
                    SharedPreferences.Editor spe = PreferenceManager.getDefaultSharedPreferences(mContext).edit();
                    spe.putLong(SP_FETCH_TIME, System.currentTimeMillis());
                    spe.apply();
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
