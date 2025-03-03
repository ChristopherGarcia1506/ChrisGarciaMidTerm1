//Chris Garcia n01371506
package chris.garcia.n01371506.cg;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
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
    private static final String Permission = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final int PermissionCode = 1;
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


// --- Menu Creation ---
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return false;
    }


    //--- Checking  Permission---
    private void RequestRuntimePerm() {
        if (ActivityCompat.checkSelfPermission(this, Permission) == PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            startActivity(intent);
        } else {//requests permission if it has not been granted yet.
            ActivityCompat.requestPermissions(this, new String[]{Permission}, PermissionCode);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults, int deviceId) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults, deviceId);

        if (requestCode == PermissionCode) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                startActivity(intent);
            } else {
                Toast.makeText(this, R.string.PermDenied, Toast.LENGTH_SHORT).show();
            }
        }
    }


    private void alertPopUp() {  // alert popup settings
        new AlertDialog.Builder(this)
                .setTitle(R.string.chris_garcia)
                .setMessage(R.string.do_you_want_to_leave_the_app)
                .setPositiveButton(R.string.yes, (dialog, which) -> {
                    Intent intent = new Intent(Settings.ACTION_SETTINGS);
                    startActivity(intent);
                })
                .setNegativeButton(R.string.no, (dialog, which) -> {
                    viewPager.setCurrentItem(2, true);
                })
                .setCancelable(false)
                .show();


    }
}