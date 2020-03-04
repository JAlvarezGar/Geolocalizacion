package com.example.jjalv.geolocalizacion;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.location.*;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends Activity {

    Button bt1;
    LocationManager locationManager;
    LocationListener locationListener;

    private LocationManager location;
    private String posicion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt1=(Button)findViewById(R.id.button);
        
        
        locationManager=(LocationManager)getSystemService(LOCATION_SERVICE);

        // CRITERIOS QUE DEBE CUMPLIR EL PROVEEDOR DE LOCALIZACION
        Criteria criteria= new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setPowerRequirement(Criteria.POWER_LOW);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setSpeedRequired(false);
        criteria.setCostAllowed(true);
        // SE DEJA QUE ANDROID ELIJA EL MEJOR PROVEEDOR
        String proveedor= locationManager.getBestProvider(criteria,true);
        

        
        


        locationListener= new LocationListener() {

            @Override
            public void onLocationChanged(Location location) {
                
                // COMO HA CAMBIADO LA UBICACION 
                // HAY QUE AVISAR AL USUARIO
                actualizarLocalizacion(location);

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
        // ACTULIZA POSICION CADA 5 SEGUNDOS Y CADA 0 METROS
        locationManager.requestLocationUpdates("gps",5000,0,locationListener);

    }

    private void actualizarLocalizacion(Location location) {

        TextView tvLocalizacion;
        tvLocalizacion=(TextView)findViewById(R.id.textLocalizacion);
        
        if(location!=null){
            double lat=location.getLatitude();
            double alt= location.getAltitude();
            posicion=("Latitud : "+lat+"\nAltitud : "+alt);
        }
        tvLocalizacion.setText(posicion);
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
