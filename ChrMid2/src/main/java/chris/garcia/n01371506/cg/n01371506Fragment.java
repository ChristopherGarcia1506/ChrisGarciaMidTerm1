//Chris Garcia n01371506
package chris.garcia.n01371506.cg;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link n01371506Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class n01371506Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public n01371506Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment n01371506Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static n01371506Fragment newInstance(String param1, String param2) {
        n01371506Fragment fragment = new n01371506Fragment();
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
        View view = inflater.inflate(R.layout.fragment_n01371506, container, false);


        //--- Auto complete logic ---
        String[] cities =   getResources().getStringArray(R.array.cities); // assigning array
        AutoCompleteTextView autoCompleteTextView = view.findViewById(R.id.ChrautoCompleteTextView); // assigning textview
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.select_dialog_item,cities);//displaying items
        autoCompleteTextView.setAdapter(adapter);//setting adapter

        //--- Button logic ---

        Button button2 = view.findViewById(R.id.Chrbutton2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String UserInput = autoCompleteTextView.getText().toString();
                boolean validCityCheck = false;
                int indexOfCity =-1;

                //---if textview is empty
                if(UserInput.length()<1){
                    autoCompleteTextView.setError(getString(R.string.can_not_be_empty));
                }
                //--Checks if user input is in array
                for(String i : cities){
                    indexOfCity += 1;
                    if(i.equals(UserInput)){
                        validCityCheck = true;

                        Fragment Garfragment = new GarFragment();
                        Bundle bundle = new Bundle();
                        bundle.putInt(getString(R.string.key),indexOfCity);
                        Garfragment.setArguments(bundle);
                        break;
                    }
                }
                //---if user input is not valid
                if(validCityCheck != true){
                    autoCompleteTextView.setError(UserInput+getString(R.string.is_not_a_valid_capital));
                }


            }
        });




        return view;
    }
}