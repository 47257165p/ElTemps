package com.example.poblenou.eltemps;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }
    private ArrayList items;
    private ArrayAdapter <String> adapter;
    private ListView lV1;
    private TextView tVDias;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        String [] fecha = {"Lunes 19/06 Nublado", "Martes 20/06 Nublado", "Miércoles 21/06 Nublado",
                "Jueves 22/06 Nublado","Viernes 23/06 Nublado", "Sábado 24/06 Nublado", "Domingo 25/06 Nublado" };

        lV1=(ListView) rootView.findViewById(R.id.lV1);
        tVDias=(TextView) rootView.findViewById(R.id.tVdias);


        items = new ArrayList(Arrays.asList(fecha));
        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, items);
        lV1.setAdapter(adapter);

        for (int i=0; i<fecha.length; i++)
        {
            adapter.add(fecha[i]);
        }
        return rootView;
    }
}
