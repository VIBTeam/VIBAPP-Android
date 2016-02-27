package com.makers.vibapp.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.makers.vibapp.R;
import com.makers.vibapp.data.DataManager;
import com.makers.vibapp.data.model.VibSoundType;
import com.makers.vibapp.util.adapter.SoundAdapter;

public class NotificationFragment extends Fragment implements SoundAdapter.OnSoundClickListener {

    private InteractionListener mListener;

    public NotificationFragment() {
        // Required empty public constructor
    }

    public static NotificationFragment newInstance() {
        NotificationFragment fragment = new NotificationFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notification, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewNotification);
        FloatingActionButton floatingActionButton = (FloatingActionButton) view.findViewById(R.id.fabNotificationFragmentAdd);
        SoundAdapter adapter = new SoundAdapter(DataManager.getInstance().getSoundTypes());
        adapter.setOnSoundClickListener(this);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNotification();
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        return view;
    }

    private void addNotification() {
        mListener.showNotificationAdder();
    }

    @Override
    public void onSoundClickListener(VibSoundType vibSoundType) {
        mListener.showNotificationEditor(vibSoundType);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof InteractionListener) {
            mListener = (InteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface InteractionListener {
        void showNotificationAdder();
        void showNotificationEditor(VibSoundType vibSoundType);
    }
}
