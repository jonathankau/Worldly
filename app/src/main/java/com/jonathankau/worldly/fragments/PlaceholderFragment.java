package com.jonathankau.worldly.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.contentful.java.api.CDACallback;
import com.contentful.java.api.CDAClient;
import com.contentful.java.model.CDAArray;
import com.contentful.java.model.CDAEntry;
import com.contentful.java.model.CDAResource;
import com.jonathankau.worldly.R;

import retrofit.RetrofitError;
import retrofit.client.Response;

public class PlaceholderFragment extends Fragment {

    private OnFragmentInteractionListener mListener;


    public static PlaceholderFragment newInstance() {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    public PlaceholderFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CDAClient client = new CDAClient.Builder()
                .setSpaceKey("zhvvoct54c0z")
                .setAccessToken("d76a8349541f51bfd3ae2d29f24d339d04576cc9d453c989e95ddae258b1664e")
                .build();

        client.fetchEntries(new CDACallback<CDAArray>() {
            @Override
            protected void onSuccess(CDAArray array, Response response) {
                // success
                for(CDAResource entry: array.getItems()) {
                    Toast.makeText(PlaceholderFragment.this.getActivity(),
                            ((CDAEntry) entry).getFields().toString(), Toast.LENGTH_LONG).show();
                    Log.d("JKAU", ((CDAEntry) entry).getFields().toString());
                }
            }

            @Override
            protected void onFailure(RetrofitError retrofitError) {
                // failure
                Toast.makeText(PlaceholderFragment.this.getActivity(), retrofitError.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_placeholder, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
