package com.makers.vibapp.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.makers.vibapp.R;
import com.makers.vibapp.data.DataManager;
import com.makers.vibapp.data.model.VibNotification;
import com.makers.vibapp.data.model.VibSoundType;
import com.makers.vibapp.util.adapter.SoundAdapter;

public class NotificationEditorFragment extends Fragment {

    private InteractionListener mListener;

    public NotificationEditorFragment() {
        // Required empty public constructor
    }

    public static NotificationEditorFragment newInstance() {
        return new NotificationEditorFragment();
    }

    public static NotificationEditorFragment newInstance(VibSoundType vibSoundType) {

        NotificationEditorFragment notificationEditorFragment = new NotificationEditorFragment();
        return notificationEditorFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notification_editor, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewNotificationSound);
        FloatingActionButton floatingActionButton = (FloatingActionButton) view.findViewById(R.id.fabNotificationEditorFragmentSave);
        SoundAdapter adapter = new SoundAdapter(DataManager.getInstance().getSoundTypes());
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveNotification();
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        return view;
    }


    private void saveNotification() {
        /*VibNotification vibNotification = new VibNotification();
        vibNotification
        mListener.onSaveNotification();*/   }

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
        void onSaveNotification(VibNotification vibNotification);
    }
}
