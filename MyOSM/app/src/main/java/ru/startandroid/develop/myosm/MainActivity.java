package ru.startandroid.develop.myosm;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import org.geonames.*;

import org.osmdroid.bonuspack.overlays.Marker;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;



import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements LocationListener {
    //my global declarations

    public Intersection intersection = new Intersection();

    private MapView osm;
    private MapController mc;
    private LocationManager locationManager;
    private EditText edlocation,radius;
    private  static final float DEFAULTZOOM=15;
    private getTwoDoubleCoordList getTwoDoubleCoordList = new getTwoDoubleCoordList();
    streetListStr strlist = new streetListStr();
    double dlat,dlng;
    streetListDouble streetListDouble = new streetListDouble();
    GetNewLatLong getNewLatLong = new GetNewLatLong();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ///Allow to Enter the Text!
        edlocation = (EditText)findViewById(R.id.locationText);
        radius = (EditText)findViewById(R.id.radiusText);


        osm = (MapView) findViewById(R.id.mapView);
        osm.setTileSource(TileSourceFactory.MAPNIK);
        osm.setBuiltInZoomControls(true);
        osm.setMultiTouchControls(true);



        mc = (MapController) osm.getController();
        mc.setZoom(12);

        GeoPoint center = new GeoPoint(42.8483, 74.6103);
        mc.animateTo(center);
        addMarker(center);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        //this is fine
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
    }


    //I wanna Imblement A google Search Function

    public void initMap(View view) throws IOException {

//Most Brobably My code DIdnt worked Just Because Of the Lack Of the Internet Connection
        String location;
        location = edlocation.getText().toString();
        if(location.isEmpty())
        {
            Toast.makeText(this, "You did not enter a username", Toast.LENGTH_SHORT).show();
            edlocation =  (EditText)findViewById(R.id.locationText);

            //editText.getText();
        }
        //it finds my current Location locality Name and Toasts it then it zooms to the chosen coordinate
        Geocoder gc = new Geocoder(this);
        List<Address> list = gc.getFromLocationName(location, 1);
        Address add = list.get(0);
        String locality = add.getLocality();
        Toast.makeText(this,locality,Toast.LENGTH_LONG).show();

        double lat = add.getLatitude();
        double lng = add.getLongitude();

        dlat = lat;
        dlng = lng;

        Toast.makeText(this,dlat+","+dlng,Toast.LENGTH_LONG).show();

        GeoPoint center = new GeoPoint(lat, lng);

        addMarker(center);
        mc.animateTo(center);

        //I just Take Off the Marker btions for a while
/*
        if(marker != null){
            marker.remove();
        }*/
//Thislines of code put a marker at the locality position
        /*MarkerOptions options = new MarkerOptions()
                .title(locality)
                .position(new LatLng(lat,lng))
                .draggable(true);

        marker = mMap.addMarker(options);*/
//isReponsible for showing the street names after the button is pressed

        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(this, Locale.getDefault());
        addresses = geocoder.getFromLocation(lat, lng, 1);

        String address = addresses.get(0).getAddressLine(0);
        org.geonames.Address address1 = new org.geonames.Address();
       
        String street =  address1.getStreet();

        Toast.makeText(this,address,Toast.LENGTH_LONG).show();

        //I want to try a Intersection

        this.intersection.setLongitude(dlng);


        String ll = this.intersection.getStreet1();

        GeoPoint intersect = new GeoPoint(intersection.getLatitude(), intersection.getLongitude());


        addMarker(intersect);

    }


    public void addMarker(GeoPoint center){
        Marker marker  = new Marker(osm);
        marker.setPosition(center);
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        marker.isDraggable();
        
        osm.getOverlays().clear();
        osm.getOverlays().add(marker);
        osm.invalidate();
    }

    //Here I want to Install A very Good Button for reading a street Names! Hope itll work

    public void buttonClick(View v){

        String strRADIUS;
        // CurrentLatLong currentLatLong = new CurrentLatLong();
        strRADIUS=radius.getText().toString();
        //RDS = radius.getText();try to catch the error if it will get me one
        if(strRADIUS == ""){
            Toast.makeText(this,"Enter the radius",Toast.LENGTH_LONG).show();
        }
        else {
        }

        double radius = Double.parseDouble(strRADIUS)/1000.0; //convert meters in kilometers



        //GetNewLatLong.getEdgeCoordinate(radius,42.856 , 74.608);
        //I am getting the List with the Lattitude coordinates of the area

        List<Double> LatList ,LongList = new List<Double>() {
            @Override
            public void add(int i, Double aDouble) {

            }

            @Override
            public boolean add(Double aDouble) {
                return false;
            }

            @Override
            public boolean addAll(int i, Collection<? extends Double> collection) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends Double> collection) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> collection) {
                return false;
            }

            @Override
            public Double get(int i) {
                return null;
            }

            @Override
            public int indexOf(Object o) {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @NonNull
            @Override
            public Iterator<Double> iterator() {
                return null;
            }

            @Override
            public int lastIndexOf(Object o) {
                return 0;
            }

            @Override
            public ListIterator<Double> listIterator() {
                return null;
            }

            @NonNull
            @Override
            public ListIterator<Double> listIterator(int i) {
                return null;
            }

            @Override
            public Double remove(int i) {
                return null;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> collection) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> collection) {
                return false;
            }

            @Override
            public Double set(int i, Double aDouble) {
                return null;
            }

            @Override
            public int size() {
                return 0;
            }

            @NonNull
            @Override
            public List<Double> subList(int i, int i1) {
                return null;
            }

            @NonNull
            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @NonNull
            @Override
            public <T> T[] toArray(T[] ts) {
                return null;
            }
        };

        LatList= getNewLatLong.getLatPointCoordinates(radius, dlat, dlng);



        LongList= getNewLatLong.getLonPointCoordinates(radius, dlat, dlng);


        /*for(double lat: LatList){
            streetListDouble.cordList.add(lat); }
        for(double lon: LongList){
            streetListStr.cordList.add(lon);}*/

        //I wanna Try my reverse Geocoder Tight here

        ArrayList<twoDoublesList> coords = new ArrayList<twoDoublesList>(); // = new List<twoDoublesList>();

        //THe broblem is Here!


        coords  = getTwoDoubleCoordList.getDoubleCoordList(LatList,LongList);

        //double y = coords.get(1).lat;

        Geocoder gc = new Geocoder(MainActivity.this);
        List<Address> list = new List<Address>() {
            @Override
            public void add(int location, Address object) {

            }

            @Override
            public boolean add(Address object) {
                return false;
            }

            @Override
            public boolean addAll(int location, Collection<? extends Address> collection) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends Address> collection) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public boolean contains(Object object) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> collection) {
                return false;
            }

            @Override
            public Address get(int location) {
                return null;
            }

            @Override
            public int indexOf(Object object) {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @NonNull
            @Override
            public Iterator<Address> iterator() {
                return null;
            }

            @Override
            public int lastIndexOf(Object object) {
                return 0;
            }

            @Override
            public ListIterator<Address> listIterator() {
                return null;
            }

            @NonNull
            @Override
            public ListIterator<Address> listIterator(int location) {
                return null;
            }

            @Override
            public Address remove(int location) {
                return null;
            }

            @Override
            public boolean remove(Object object) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> collection) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> collection) {
                return false;
            }

            @Override
            public Address set(int location, Address object) {
                return null;
            }

            @Override
            public int size() {
                return 0;
            }

            @NonNull
            @Override
            public List<Address> subList(int start, int end) {
                return null;
            }

            @NonNull
            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @NonNull
            @Override
            public <T> T[] toArray(T[] array) {
                return null;
            }
        };

        String str;
        int LI = (int) Math.round(radius/0.01);
        int i;

        //System.out.println(coords.get(1).lat);

        //THe  code is fine u to this Line

        for(i=0; i< LI;i++) //Ddefine How many Dots are there in the mab
        {try {
            //For an OSM map There is   no stable  geocooding .Logically I have to do STH else to get them Out! I can send different zapros !
            list = gc.getFromLocation(coords.get(i).lat, coords.get(i).lng, 1);
            str = list.get(0).getAddressLine(0);
            //It adds Nothing here

            strlist.car.add(str);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        }

        Intent intent = new Intent(this, streetListStr.class);
        startActivity(intent);
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

    @Override
    public void onLocationChanged(Location location) {
        GeoPoint center = new GeoPoint(location.getLatitude(),location.getLongitude());
        mc.animateTo(center);
        addMarker(center);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        if(locationManager != null){
            locationManager.removeUpdates(this);
        }

    }

}
