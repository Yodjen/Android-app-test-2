package com.android.retrofitjson.retrofit_json_app.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.retrofitjson.retrofit_json_app.adapter.ContactAdapter;
import com.android.retrofitjson.retrofit_json_app.utils.InternetConnection;
import com.android.retrofitjson.retrofit_json_app.R;
import com.android.retrofitjson.retrofit_json_app.api.ApiService;
import com.android.retrofitjson.retrofit_json_app.api.RetroClient;
import com.android.retrofitjson.retrofit_json_app.model.Contact;
import com.android.retrofitjson.retrofit_json_app.model.ContactList;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private View parentView;
    private ArrayList<Contact> contactList;
    private ContactAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        contactList = new ArrayList<>();
        parentView = findViewById(R.id.parentLayout);

        listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Snackbar.make(parentView, contactList.get(position).getName() + " => " + contactList.get(position).getPhone().getHome(), Snackbar.LENGTH_LONG).show();
                Snackbar.make(parentView, contactList.get(position).getName() + " => "
                        + contactList.get(position).getPhone().getMobile(), Snackbar.LENGTH_LONG).show();
            }
        });

        Toast toast = Toast.makeText(getApplicationContext(), R.string.string_click_to_load, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(@NonNull final View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                /**
                 * Checking internet connection
                 */
                if (InternetConnection.ckeckConnection(getApplicationContext())) {
                    final ProgressDialog dialog;
                    /**
                     * Progress Dialog for User Interaction
                     */
                    dialog = new ProgressDialog(MainActivity.this);
                    dialog.setTitle(getString(R.string.string_getting_gson_title));
                    dialog.setMessage(getString(R.string.string_getting_gson_massage));
                    dialog.show();

                    //Creating an object of our api interface
                    ApiService api = RetroClient.getApiService();

                    /**
                     * Calling JSON
                     */
                    Call<ContactList> call = api.getMyJSON();

                    /**
                     * Enqueue Callback will be call when get response...
                     */
                    call.enqueue(new Callback<ContactList>() {
                        @Override
                        public void onResponse(Call<ContactList> call, Response<ContactList> response) {
                            //Dismiss Dialog
                            dialog.dismiss();

                            if (response.isSuccessful()) {
                                /**
                                 * Got Successfully
                                 */
                                contactList = response.body().getContacts();

                                /**
                                 * Binding that List to Adapter
                                 */
                                adapter = new ContactAdapter(MainActivity.this, contactList);
                                listView.setAdapter(adapter);

                            } else {
                                Snackbar.make(parentView, R.string.string_some_thing_wrong, Snackbar.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ContactList> call, Throwable t) {
                            dialog.dismiss();
                        }
                    });

                } else {
                    Snackbar.make(parentView, R.string.string_internet_connection_not_available, Snackbar.LENGTH_LONG).show();
                }
            }
        });







    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

//
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri
                    .parse("https://luxuri.000webhostapp.com/luxuri"));
            startActivity(browserIntent);
            return true;
        }
        if (id == R.id.action_sourse){
            Snackbar.make(parentView, R.string.string_about, Snackbar.LENGTH_LONG).show();
            return true;
//            android.os.Process.killProcess(android.os.Process.myPid());
        }

        item.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem menuItem) {
                return false;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem menuItem) {
                return false;
            }
        });
        return super.onOptionsItemSelected(item);
    }
}
