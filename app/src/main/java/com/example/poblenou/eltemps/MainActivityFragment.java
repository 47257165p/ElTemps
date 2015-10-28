package com.example.poblenou.eltemps;

import android.os.Bundle;
import android.preference.Preference;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import org.w3c.dom.Text;
import java.util.ArrayList;
import java.util.Arrays;

import retrofit.Retrofit;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }
    private ArrayList items;
    private ArrayAdapter <String> adapter;
    private ListView lV1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        String [] fecha = {"Lunes 19/06 Nublado", "Martes 20/06 Nublado", "Miércoles 21/06 Nublado",
                "Jueves 22/06 Nublado","Viernes 23/06 Nublado", "Sábado 24/06 Nublado", "Domingo 25/06 Nublado" };

        lV1=(ListView) rootView.findViewById(R.id.lV1);


        items = new ArrayList(Arrays.asList(fecha));
        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, items);
        lV1.setAdapter(adapter);

        return rootView;
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_fragment, menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_refresh) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void refresh ()
    {
        final String FORECAST_BASE_URL = "api.openweathermap.org/data/2.5 /forecast?q=";
        final String CITY = SettingsActivity.;
        final String COUNTRY_CODE = "es";
        final String APPID = "2d16371b18d582695a502b8396b9673d";
        Retrofit retrofit = new Retrofit.Builder().baseUrl(FORECAST_BASE_URL+"q="+CITY+","+COUNTRY_CODE).build();

        Call<List<Repo>> repos = service.listRepos("octocat");
    }
    public interface OpenWeatherMapService {
        @GET("/users/{user}/repos")
        Call<List<Repo>> listRepos(@Path("user") String user);
    }
}
