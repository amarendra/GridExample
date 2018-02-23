package im.amar.gridexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by amarendra.kumar on 22 Jan.
 */

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerGridView;
    private int[] mNumbers;
    private GridAdapter mAdapter;

    private static final String TAG = "GridTestExample";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        mRecyclerGridView = (RecyclerView) findViewById(R.id.rv_grids);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, columnsToFit());
        mRecyclerGridView.setLayoutManager(gridLayoutManager);
        mRecyclerGridView.setFocusable(false);

        mNumbers = new int[100];
        for (int i = 0; i < mNumbers.length; i++) {
            mNumbers[i] = i;
        }

        mRecyclerGridView.setAdapter(getAdapter());
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private int columnsToFit() {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int rvMargin = (int) getResources().getDimension(R.dimen.rv_margin) * 2;
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density - (float) rvMargin;
        int cardMargin = (int) getResources().getDimension(R.dimen.card_margin);
        int cardWidth = (int) getResources().getDimension(R.dimen.card_width);
        int cardTotalWidth = cardMargin * 2 + cardWidth;
        Log.d(TAG, "" + cardTotalWidth);

        int columns = (int) (dpWidth / cardTotalWidth);
        Log.d( TAG, "" + columns);
        return columns;
    }

    private GridAdapter getAdapter() {
        if (mAdapter == null) {
            mAdapter = new GridAdapter();
        }

        return mAdapter;
    }

    public class GridAdapter extends RecyclerView.Adapter<GridAdapter.ViewHolder> {
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.card, parent, false));
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.tvId.setText("" + mNumbers[position]);
        }

        @Override
        public int getItemCount() {
            return mNumbers.length;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView tvId;
            public ViewHolder(View itemView) {
                super(itemView);

                tvId = (TextView) itemView.findViewById(R.id.tvId);
            }
        }
    }
}
