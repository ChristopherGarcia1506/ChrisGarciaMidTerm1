package chris.garcia.n01371506.cg;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GarFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public GarFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GarciaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GarFragment newInstance(String param1, String param2) {
        GarFragment fragment = new GarFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_garcia, container, false);

        TextView textView = view.findViewById(R.id.textView2);//assigning textview

        //--- Setting Button functionality
        Button button = view.findViewById(R.id.button);
        RatingBar ratingBar = view.findViewById(R.id.ratingBar);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              float rating = ratingBar.getRating();//assigning rating
              DisplaySnackBar(view,getString(R.string.rating)+rating+getString(R.string.stars));
            }
        });

        //---Retrieving index ---
        Bundle bundle = getArguments();
        if(bundle != null){
            int index = bundle.getInt(getString(R.string.key));
            textView.setText(index);
        }

        return view;
    }

    //---Snack bar function---
    private void DisplaySnackBar(View view,String msg){
        Snackbar snackbar = Snackbar.make(view,msg,Snackbar.LENGTH_SHORT);
        snackbar.show();
    }
}