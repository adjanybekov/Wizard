public class getTheCoordinates{
      
     public static void main(String []args){
         double R = 6371.0;
         double d = 0.100;//radius in km 
         double lat1 = 42.873, lon1 = 74.568;
         double Radlat1 = Math.toRadians(42.873);
         double Radlon1 = Math.toRadians(74.568);
         double brng = Math.toRadians(225);
         double lat2,lon2;
         
        System.out.println("Hello World");
        //lat2 = Math.sin();
        
        lat2 = Math.asin(Math.sin(Radlat1)*Math.cos(d/R) + 
                      Math.cos(Radlat1)*Math.sin(d/R)*Math.cos(brng));
                      
        
       
         System.out.println("Lat2degree"+"="+Math.toDegrees(lat2)+"\n"+"Lat2="+lat2);
         
        double NewRadLat2 = Math.toRadians(Math.toDegrees(lat2));
  
        //the second one comes false Becase of the first One/
        lon2 = Radlon1+Math.atan2(Math.sin(brng)*Math.sin(d/R)*Math.cos(Radlat1), 
                             Math.cos(d/R)-Math.sin(Radlat1)*Math.sin(lat2));
                                
        System.out.println("Lon2"+"="+Math.toDegrees(lon2));                        
     }
}
}

