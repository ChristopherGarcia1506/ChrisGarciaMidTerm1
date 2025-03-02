//Chris Garcia n01371506
package chris.garcia.n01371506.cg;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

public class GarActivity1 extends AppCompatActivity {


    ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        //---Creating Fragment List---
        ArrayList<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new ChrFragment());
        fragmentList.add(new GarFragment());
        fragmentList.add(new n01371506Fragment());

        TabLayout tabLayout = findViewById(R.id.tabsLayout);
        viewPager = findViewById(R.id.viewpager);


        viewPager.setAdapter(new ViewPagerAdapter(this, fragmentList));

        String[] tabnames = {"Chris", "Garcia", "n01371506"};//setting tab names
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> {
                    tab.setText(tabnames[position]);
                }
        ).attach();


        //----Soft BackKey Button---
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {      //action on back button pressed
            @Override
            public void handleOnBackPressed() {
                if (isEnabled()) {
                    alertPopUp();
                }
            }
        };
        getOnBackPressedDispatcher().addCallback(this, callback);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


    private void alertPopUp() {  // alert popup settings
        new AlertDialog.Builder(this)
                .setTitle("Chris Garcia")
                .setMessage("Do you want to leave the app?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    Intent intent = new Intent(Settings.ACTION_SETTINGS);
                    startActivity(intent);
                })
                .setNegativeButton("No", (dialog, which) -> {
                    viewPager.setCurrentItem(2, true);
                })
                .setCancelable(false)
                .show();


    }
}