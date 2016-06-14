package io.lazluiz.onyochallenge.screen;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.lazluiz.onyochallenge.R;
import io.lazluiz.onyochallenge.data.FetchOnyoData;
import io.lazluiz.onyochallenge.model.Company;
import io.lazluiz.onyochallenge.screen.adapter.CompaniesAdapter;
import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    private int mEntityCounter = 0;
    private List<Company> mData = new ArrayList<>();
    private CompaniesAdapter mCompaniesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recycler = (RecyclerView) findViewById(R.id.recycler);
        recycler.setLayoutManager(getLayoutManager());
        recycler.setAdapter(getAdapter());

        // Check for new data
        FetchOnyoData fod = new FetchOnyoData(this);
        fod.getData();
    }

    private RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(this);
    }

    private RecyclerView.Adapter getAdapter() {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Company> companies = realm.where(Company.class).findAll();

        mCompaniesAdapter = new CompaniesAdapter(this);
        mCompaniesAdapter.setData(companies);
        mCompaniesAdapter.setOnItemClickListener(new CompaniesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Company r) {
                Toast.makeText(MainActivity.this, "JUST DO IT", Toast.LENGTH_SHORT).show();
            }
        });
        mCompaniesAdapter.setOnMapClickListener(new CompaniesAdapter.OnMapClickListener() {
            @Override
            public void onItemClick(Company c) {
                String lat = c.getGeoLat();
                String lon = c.getGeoLon();
                String title = c.getDisplayName();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(
                        String.format("geo:%s,%s?q=%s,%s(%s)", lat, lon, lat, lon, title)));
                startActivity(intent);
            }
        });
        return mCompaniesAdapter;
    }
}
