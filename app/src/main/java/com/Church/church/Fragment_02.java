package com.Church.church;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.github.chrisbanes.photoview.PhotoView;

public class Fragment_02 extends Fragment {

    public static Fragment_02 newInstance() {
        return new Fragment_02();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_02, container,false);
        PhotoView photoView = view.findViewById(R.id.photo_view_02);
        photoView.setImageResource(R.drawable.introimage_02);

        return view;
    }
}