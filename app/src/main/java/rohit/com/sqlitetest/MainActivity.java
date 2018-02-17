package rohit.com.sqlitetest;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        SQLiteDatabase sqLiteDatabase = getBaseContext().openOrCreateDatabase("sqlite-test-1.db", MODE_PRIVATE, null);
        String sql = "DROP TABLE IF EXISTS contacts";
        Log.d(TAG, "onCreate: " + sql);
        sqLiteDatabase.execSQL(sql);
        sql = "CREATE TABLE IF NOT EXISTS contacts(name TEXT, phone INTEGER , email TEXT);";
        Log.d(TAG, "onCreate: " + sql);
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO contacts VALUES('ROHIT',9075,'ROHIT9075@GMAIL.COM');";
        Log.d(TAG, "onCreate: " + sql);
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO contacts VALUES('Mohit',9075,'ROHIT9075@GMAIL.COM');";
        Log.d(TAG, "onCreate: " + sql);
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO contacts VALUES('Suresh',9075,'ROHIT9075@GMAIL.COM');";
        Log.d(TAG, "onCreate: " + sql);
        sqLiteDatabase.execSQL(sql);

        Cursor query = sqLiteDatabase.rawQuery("select * from contacts;", null);
        if (query.moveToFirst()) {
            do {
                String name = query.getString(0);
                int phone = query.getInt(1);
                String email = query.getString(2);
                Toast.makeText(getApplicationContext(), "Name = " + name + "phone = " + phone + "email = " + email, Toast.LENGTH_LONG).show();
            } while (query.moveToNext());

        }
        query.close();
        sqLiteDatabase.close();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
