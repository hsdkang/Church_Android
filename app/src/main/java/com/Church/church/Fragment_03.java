package com.Church.church;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.github.chrisbanes.photoview.PhotoView;

public class Fragment_03 extends Fragment {

    public static Fragment_03 newInstance() {
        return new Fragment_03();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_03, container,false);
        PhotoView photoView = view.findViewById(R.id.photo_view_03);
        photoView.setImageResource(R.drawable.introimage_03);

        return view;
    }
}