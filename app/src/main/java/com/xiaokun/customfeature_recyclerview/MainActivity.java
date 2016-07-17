package com.xiaokun.customfeature_recyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.xiaokun.animator.LeftInRightOutAnimator;
import com.xiaokun.divider.SelectedGridItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ExampleGridAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 4);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new SelectedGridItemDecoration(4));
        List<String> datas = new ArrayList<>();
        for (int i = 0; i < 103; i++) {
            datas.add(i + "");
        }
        adapter = new ExampleGridAdapter(this, datas);
        recyclerView.setItemAnimator(new LeftInRightOutAnimator());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
//                adapter.add(1,"999");
                break;
            case R.id.remove:
//                adapter.remove(1);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
