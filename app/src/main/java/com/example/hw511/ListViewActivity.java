package com.example.hw511;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ListViewActivity extends AppCompatActivity {


    private Random random = new Random();
    private ItemsDataAdapter adapter;
    private List<Drawable> images = new ArrayList<>();
    private List<String> citates = new ArrayList<>();
    private ExternalFile externalFile = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);


        Toolbar toolbar = findViewById(R.id.toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        ListView listView = findViewById(R.id.listView);

        setSupportActionBar(toolbar);

        fillres();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                generateRandomItemData();
                externalFile.saveStringList(adapter.getAdapterStrings());

            }
        });

        externalFile = new ExternalFile(ListViewActivity.this, "strings.txt");
        adapter = new ItemsDataAdapter(this, null, externalFile);
        listView.setAdapter(adapter);

        generateLoadedItemData();


        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ListViewActivity.this, String.format(getString(R.string.toast_text), adapter.getItem(position).getTitle().length()), Toast.LENGTH_SHORT).show();
                return true;
            }
        });

    }

    private void fillres() {
        images.add(ContextCompat.getDrawable(ListViewActivity.this,
                R.drawable.i1));
        images.add(ContextCompat.getDrawable(ListViewActivity.this,
                R.drawable.i2));
        images.add(ContextCompat.getDrawable(ListViewActivity.this,
                R.drawable.i3));
        images.add(ContextCompat.getDrawable(ListViewActivity.this,
                R.drawable.i4));
        images.add(ContextCompat.getDrawable(ListViewActivity.this,
                R.drawable.i5));
        images.add(ContextCompat.getDrawable(ListViewActivity.this,
                R.drawable.i6));
        images.add(ContextCompat.getDrawable(ListViewActivity.this,
                R.drawable.i7));
        images.add(ContextCompat.getDrawable(ListViewActivity.this,
                R.drawable.i8));
        citates = Arrays.asList(getResources().getStringArray(R.array.citates));
    }

    private void generateRandomItemData() {
        adapter.addItem(new ItemData(
                images.get(random.nextInt(images.size())),
                citates.get(random.nextInt(citates.size())),
                getString(R.string.citates_from)));
    }

    private void generateLoadedItemData() {
        List<String> list = externalFile.loadStringList();
        if (list == null) return;
        for (String s : list) {
            adapter.addItem(new ItemData(
                    images.get(random.nextInt(images.size())),
                    s,
                    getString(R.string.citates_from)));
        }
        Toast.makeText(ListViewActivity.this, getString(R.string.loaded, list.size()), Toast.LENGTH_SHORT).show();
    }


}