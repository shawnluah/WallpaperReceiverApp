package sg.edu.rp.c346.wallpaperreceiverapp;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    BroadcastReceiver br = new WallPaperReceiver();

    RadioGroup radioGroup;

    final String KEY_SAVED_RADIO_BUTTON_INDEX = "SAVED_RADIO_BUTTON_INDEX";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroup = (RadioGroup)findViewById(R.id.radiogroup);





        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        filter.addAction("android.intent.action.WALLPAPER_CHANGED");
        this.registerReceiver(br, filter);



    }


    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("id", radioGroup.getCheckedRadioButtonId());
        editor.commit();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);

        int id = preferences.getInt("id", R.id.radioButtonAnswer1);
        radioGroup.check(id);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.unregisterReceiver(br);
    }

}
