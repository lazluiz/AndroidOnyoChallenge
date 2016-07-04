package io.lazluiz.onyochallenge.screen;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import io.lazluiz.onyochallenge.R;

/**
 * Created by Luiz F. Lazzarin on 15/06/2016.
 * Email: lf.lazzarin@gmail.com
 * Github: /luizfelippe
 */

public class CompanyHomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_company_home, container, false);

        return view;
    }
}
