/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banks;


import org.openstreetmap.gui.jmapviewer.Demo;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.record.Location;
import java.io.File;
import java.io.PrintWriter;
import org.json.JSONObject;
import org.json.JSONArray;

public class LowestCrwodBranch2 {
    
        String venueName = "";
        String venueCity = "";
        
        public LowestCrwodBranch2(String n, String c) throws Exception{
        venueName = n;
        venueCity = c;
        URL connection = new URL("http://checkip.amazonaws.com/");
    URLConnection con1 = connection.openConnection();
    String str = null;
    BufferedReader r = new BufferedReader(new InputStreamReader(con1.getInputStream()));
    str = r.readLine();
    //GEOIP taks the external ip and get the location
    File dbFile = new File("src\\banks\\GeoLite2-City.mmdb");
       DatabaseReader reader = new DatabaseReader.Builder(dbFile).build();
       InetAddress ipAddress = InetAddress.getByName(str);
       CityResponse responseGeo = reader.city(ipAddress);
       Location locationGeo = responseGeo.getLocation();
       // Latitude
       // Longitude
       String loc=locationGeo.getLatitude()+","+locationGeo.getLongitude();
       
       String url = "https://api.foursquare.com/v2/venues/search?client_id=SP4SMXO3FLVX52BAEXQQ13CNWMJM1I4IZUBUF1O4QEA4OMPV&client_secret=QJGOAT2HKCLUL3BK1BWRGXF40SPR5AXKXDCAH1CNBKFSWRKZ&v=20151203&ll=" + loc + "&near=" + venueCity + "&query="+venueName+"&limit=7&radius=1000" ;
        URL obj = new URL(url);
	HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        
        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String readAPIResponse = "";
        StringBuilder jsonString = new StringBuilder();
        while((readAPIResponse = br.readLine()) != null){
            jsonString.append(readAPIResponse);
}
        //Writing CSV File
        PrintWriter pw = new PrintWriter(new File("Places.csv"));
        StringBuilder sb = new StringBuilder();
        sb.append("#of people");
        sb.append(',');
        sb.append("Lng");
        sb.append(',');
        sb.append("Lat");
        sb.append('\n');
        //Writing CSV File
        
        
         String jsonS = new String(jsonString);
         JSONObject json = new JSONObject(jsonS);
         JSONObject response = json.getJSONObject("response"); // Contain venues
         JSONArray venues = response.getJSONArray("venues"); // venues contain number of people and location
         JSONObject arr1 = venues.getJSONObject(0); // convert to array
         JSONObject herenow = arr1.getJSONObject("hereNow");
       int count1= Integer.parseInt( herenow.get("count").toString());
       int count2=0;
        JSONObject location;
         for(int i = 0; i < venues.length(); i++){
              arr1 = venues.getJSONObject(i); // convert to array
              herenow = arr1.getJSONObject("hereNow");
              count2= Integer.parseInt(herenow.get("count").toString());
             if (count2 < count1)
             {count1= count2;
             location = arr1.getJSONObject("location");
             String sb2= herenow.get("count") +","+location.get("lng")+","+ location.get("lat")+'\n';
             sb.insert(0, sb2);
             
             }  
             else{
             sb.append(herenow.get("count"));
             sb.append(',');
             location = arr1.getJSONObject("location");
             sb.append(location.get("lng"));
             sb.append(',');
             sb.append(location.get("lat"));
             sb.append('\n');
             }
            
         }

        pw.write(sb.toString());
        pw.close();
        System.out.println("done!");
     Demo d = new Demo();  
     d.setVisible(true);
     }
}
