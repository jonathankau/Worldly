package com.jonathankau.worldly.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.jonathankau.worldly.R;
import com.jonathankau.worldly.activities.ArticleActivity;
import com.jonathankau.worldly.adapters.FeedItemAdapter;
import com.jonathankau.worldly.model.ArticleEntry;
import com.nirhart.parallaxscroll.views.ParallaxListView;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class FeedItemFragment extends Fragment implements AbsListView.OnItemClickListener {
    @InjectView(R.id.list) ParallaxListView mListView;

    private static final String ARG_PARAM1 = "DATA_ENTRIES";
    private ArrayList<ArticleEntry> data;

    private OnFragmentInteractionListener mListener;

    /**
     * The Adapter which will be used to populate the ListView/GridView with
     * Views.
     */
    private ListAdapter mAdapter;

    // TODO: Rename and change types of parameters
    public static FeedItemFragment newInstance(ArrayList<ArticleEntry> data) {
        FeedItemFragment fragment = new FeedItemFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList("DATA_ENTRIES", data);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public FeedItemFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            data = getArguments().getParcelableArrayList(ARG_PARAM1);
        }

        mAdapter = new FeedItemAdapter(getActivity(), data);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feeditem, container, false);

        // Set the adapter
        ButterKnife.inject(this, view);
        TextView v = new TextView(this.getActivity());
        v.setText("Streak: 0");
        v.setGravity(Gravity.CENTER);
        v.setTextSize(40);
        v.setHeight(350);
        mListView.addParallaxedHeaderView(v);
        (mListView).setAdapter(mAdapter);

        // Set OnItemClickListener so we can be notified on item clicks
        mListView.setOnItemClickListener(this);

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
//        try {
//            mListener = (OnFragmentInteractionListener) activity;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(activity.toString()
//                + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this.getActivity(), ArticleActivity.class);
        ArticleEntry selectedEntry = (ArticleEntry) mAdapter.getItem(position - 1);

        Bundle args = new Bundle();
        args.putParcelable("ARTICLE_ENTRY", selectedEntry);
        intent.putExtras(args);

        startActivity(intent);

    }

    /**
     * The default content for this Fragment has a TextView that is shown when
     * the list is empty. If you would like to change the text, call this method
     * to supply the text it should use.
     */
    public void setEmptyText(CharSequence emptyText) {
        View emptyView = mListView.getEmptyView();

        if (emptyText instanceof TextView) {
            ((TextView) emptyView).setText(emptyText);
        }
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
        public void onFragmentInteraction(String id);
    }

}
