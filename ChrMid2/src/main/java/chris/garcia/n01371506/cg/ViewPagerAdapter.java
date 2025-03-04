//Chris Garcia n01371506
package chris.garcia.n01371506.cg;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

public class ViewPagerAdapter extends FragmentStateAdapter {

    private final ArrayList<Fragment> fragmentList;

    public ViewPagerAdapter(FragmentActivity fragmentActivity, ArrayList<Fragment> fragmentList) {
        super(fragmentActivity);
        this.fragmentList = fragmentList;
    }


    @Override
    public Fragment createFragment(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getItemCount() {
        return fragmentList.size();
    }
}