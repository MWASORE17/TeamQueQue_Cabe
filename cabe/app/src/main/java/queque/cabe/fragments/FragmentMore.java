package queque.cabe.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import queque.cabe.R;

/**
 * Created by Willy Laurents on 3/8/2017.
 */

public class FragmentMore extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frag_more, container, false);

        return rootView;
    }
}
