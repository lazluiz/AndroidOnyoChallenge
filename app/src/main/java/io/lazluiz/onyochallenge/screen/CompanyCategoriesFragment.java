package io.lazluiz.onyochallenge.screen;

import android.app.SearchManager;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import io.lazluiz.onyochallenge.R;
import io.lazluiz.onyochallenge.model.Category;
import io.lazluiz.onyochallenge.screen.adapter.CategoriesAdapter;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Luiz F. Lazzarin on 15/06/2016.
 * Email: lf.lazzarin@gmail.com
 * Github: /luizfelippe
 */

public class CompanyCategoriesFragment extends Fragment {

    private final static int GRID_COLUMNS = 2;
    private final static int GRID_SPACING = 25;

    SearchView mSearchView;
    SearchView.OnQueryTextListener mQueryTextListener;

    public CompanyCategoriesFragment() {
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_company_categories, container, false);

        RecyclerView recycler = (RecyclerView) view.findViewById(R.id.recycler);
        recycler.setLayoutManager(getLayoutManager());
        recycler.addItemDecoration(getItemDecoration());
        recycler.setAdapter(getAdapter());

        return view;
    }

    //region Recycler

    private RecyclerView.LayoutManager getLayoutManager() {
        return new StaggeredGridLayoutManager(GRID_COLUMNS, StaggeredGridLayoutManager.VERTICAL);
    }

    private RecyclerView.Adapter getAdapter() {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Category> categories = realm.where(Category.class).findAll();

        CategoriesAdapter categoriesAdapter = new CategoriesAdapter(getActivity());
        categoriesAdapter.setData(categories);
        return categoriesAdapter;
    }

    private RecyclerView.ItemDecoration getItemDecoration() {
        return new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                int position = parent.getChildAdapterPosition(view);
                int column = position % GRID_COLUMNS;
                int spacing = GRID_SPACING;

                // Grid padding
                outRect.left = spacing - column * spacing / GRID_COLUMNS;
                outRect.right = (column + 1) * spacing / GRID_COLUMNS;

                if (position < GRID_COLUMNS) {
                    outRect.top = spacing;
                }
                outRect.bottom = spacing;
            }
        };
    }

    //endregion

    //region Toolbar

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuItem searchItem = menu.findItem(R.id.action_search);

        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);

        if (searchItem != null) {
            mSearchView = (SearchView) searchItem.getActionView();
        }
        if (mSearchView != null) {
            mSearchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));

            mQueryTextListener = new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextChange(String newText) {
                    Log.i("onQueryTextChange", newText);

                    return true;
                }

                @Override
                public boolean onQueryTextSubmit(String query) {
                    Log.i("onQueryTextSubmit", query);

                    return true;
                }
            };
            mSearchView.setOnQueryTextListener(mQueryTextListener);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                return false;
            default:
                break;
        }
        mSearchView.setOnQueryTextListener(mQueryTextListener);
        return super.onOptionsItemSelected(item);
    }
    //endregion
}
