package sk.hyll.dominik.exercise5_dialogs;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.ArrayList;

import static android.R.id.list;

public class MainActivity extends AppCompatActivity implements NewItem.NewItemListener{

    private ArrayList<String> list;
    private ListView listView;
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NewItem ni = new NewItem();
                ni.show(getSupportFragmentManager(),"new_item");
            }
        });

        String[] array = new String[]{"one","apple","cat"};

         this.list = new ArrayList<>();
        for (int i = 0; i < array.length; ++i) {
            list.add(array[i]);
        }

        this.adapter =  new ArrayAdapter(this, android.R.layout.simple_list_item_1,list);

        this.listView = ((ListView)findViewById(R.id.listView));
        this.listView.setAdapter(adapter);

        this.listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(final AdapterView<?> adapterView, View view, final  int i, long l) {


                PopupMenu popup = new PopupMenu(getApplicationContext(), view);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        String item = (String)adapter.getItem(i);
                        adapter.remove(item);
                        Toast.makeText(MainActivity.this, getString(R.string.remove_item) + item, Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });
                MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.menu_main, popup.getMenu());
                popup.show();

                return false;
            }
        });

    }

    @Override
    public void onNewItemCreated(String newItem) {
        this.adapter.add(newItem);
        Toast.makeText(this, getString(R.string.item_added) + newItem , Toast.LENGTH_SHORT).show();
    }
}
