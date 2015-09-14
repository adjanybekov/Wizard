package ru.startandroid.develop.myosm;

/**
 * Created by e211104 on 09.09.2015.
 */
import java.util.ArrayList;
import java.util.List;

/**
 * Created by e211104 on 04.09.2015.
 */

public class GetNewLatLong {
    streetListDouble streetListDouble;// = new streetListDouble();
    // double RANGE = 0.01;//meters away from another street;CHANGE value if your Entereed value are in kolometers or miles
    // int li;

    public  twoDoublesList getEdgeCoordinate(double radius, double latit,double longit){
        //twoDoublesList edgeCords;
        double R = 6371.0;//chbge to r
        double d = radius;//radius in km
        //double lat1 = 42.873, lon1 = 74.568;
        double Radlat1 = Math.toRadians(latit);
        double Radlon1 = Math.toRadians(longit);
        double brng = Math.toRadians(225);//bearing of 225 degrees to the edge from the north
        double lat2,lon2;

        lat2 = Math.asin(Math.sin(Radlat1)*Math.cos(d/R) +
                Math.cos(Radlat1)*Math.sin(d/R)*Math.cos(brng));


        // double NewRadLat2 = Math.toRadians(Math.toDegrees(lat2));

        //the second one comes false Becase of the first One/
        lon2 = Radlon1+Math.atan2(Math.sin(brng)*Math.sin(d/R)*Math.cos(Radlat1),
                Math.cos(d/R)-Math.sin(Radlat1)*Math.sin(lat2));
        //I wanna try to get both values at once but that didnt worked for me

        twoDoublesList edgeCord = new twoDoublesList();

        edgeCord.lat = Math.toDegrees(lat2);
        edgeCord.lng = Math.toDegrees(lon2);
        System.out.println("" + lat2 + "," + lon2);
        //Keep in mind to convert it back into degrees


        streetListDouble.cordList.add(Math.toDegrees(lat2));
        streetListDouble.cordList.add(Math.toDegrees(lon2));
        return edgeCord;

    }

    //Lattitude changes as I go to North Ub down

    public   List<Double>   getLatPointCoordinates(double radius , double Plat,double Plong ){
        List<Double> LatList = new ArrayList<Double>();
        int i;
        int LI =  (int) Math.round(radius/0.01);

        for(i=0;i<LI;i++){
            LatList.add(Plat+0.0090017*i);//I am adding a double Object which I have found so far change in Latitude every 10 meters=0.0090017
        }
        return LatList;
    }



    public  List<Double>  getLonPointCoordinates(double radius , double Plat,double Plong ){
        List<Double> LongList = new ArrayList<Double>();
        int i;
        int LI =  (int) Math.round(radius/0.01);

        for(i=0;i<LI;i++){
            LongList.add(Plong+0.0090017*i);//I am adding a double Object which I have found so far change in Latitude every 10 meters=0.0090017
        };

        return LongList;

    }
    //It craches before this line

    /*This function is supposed to create me a string containing two doubles.and it will be used to store lat lng
    * coordinates in it, There will be used two more lists with doubles also, and two iterations*/

}
