/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banks;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import javafx.application.Application;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openstreetmap.gui.jmapviewer.Demo; //to create map object 


/**
 *
 * @author User
 */
public class PlaceInfo {
        String venueName = "";
        String venueCity = "";
        
        public PlaceInfo(String n, String c) throws Exception{
        venueName = n;
        venueCity = c;
        
        String url = "https://api.foursquare.com/v2/venues/search?client_id=SP4SMXO3FLVX52BAEXQQ13CNWMJM1I4IZUBUF1O4QEA4OMPV&client_secret=QJGOAT2HKCLUL3BK1BWRGXF40SPR5AXKXDCAH1CNBKFSWRKZ&v=20151203&near=" + venueCity + "&query=" + venueName ;
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
         for(int i = 0; i < venues.length(); i++){
             JSONObject arr1 = venues.getJSONObject(i); // convert to array
             JSONObject herenow = arr1.getJSONObject("hereNow");
             sb.append(herenow.get("count"));
             sb.append(',');
             JSONObject location = arr1.getJSONObject("location");
             sb.append(location.get("lng"));
             sb.append(',');
             sb.append(location.get("lat"));
             sb.append('\n');
         }

        pw.write(sb.toString());
        pw.close();
        System.out.println("done!");
		
         //Create map
     Demo d = new Demo();  
     d.setVisible(true);
     }
        }
     /*public static void main(String[] args) throws Exception{
        /*String venueName = "AL Rajhi Bank";
        String venueCity = "Riyadh";*/
        

