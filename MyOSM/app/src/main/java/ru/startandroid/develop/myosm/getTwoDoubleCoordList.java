package ru.startandroid.develop.myosm;

/**
 * Created by e211104 on 09.09.2015.
 */
import java.util.ArrayList;
import java.util.List;

/**
 * Created by e211104 on 06.09.2015.
 */
public class getTwoDoubleCoordList {
    //Initialization of the list

    //Initialization of the two double object
    ArrayList<twoDoublesList> coords = new ArrayList<twoDoublesList>();



    public ArrayList<twoDoublesList> getDoubleCoordList(List<Double> LatList, List<Double> LongList){



        for( double i: LatList){   //li stands for loopIteration
            for(double j:LongList){//I think that the problem comes from the redeclaration of the variables
                twoDoublesList doublecord = new twoDoublesList();
                doublecord.lat = i;
                doublecord.lng = j;


                //Now the broblem is in the adding the doublecord  objects to the coords List.
                //I just have made an array List instead of a simple list

                coords.add(doublecord);
            }
        }


        /*for(i=0;i<10;i++){
            streetListStr.car.add(coords.get(i).lat+","+coords.get(i).lng);
            double z = coords.get(i).lng;
            double l = coords.get(i).lat;}*/



        return coords;
    }
}
