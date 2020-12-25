package com.bankoncube;

import java.io.IOException;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;


public class TravelTime {

	public static void main(String[] args) throws Exception {
		
		//Input Data
		String str="Sr. No.,Delivery Person,Product,Pick up time,Delivered time\r\n" + 
				"1,Alice,GoPro Camera,2020-12-21T23:40:00Z,2020-12-22T00:05:00Z\r\n" + 
				"2,Alice,Tennis Shoes,2020-12-22T00:00:00Z,2020-12-22T00:25:00Z\r\n" + 
				"3,Tony,Football,2020-12-22T01:25:00Z,2020-12-22T02:15:00Z\r\n" + 
				"4,Alice,Table Lamp,2020-12-22T02:25:00Z,2020-12-22T02:55:00Z\r\n" + 
				"5,Tony,Frisbee,2020-12-22T03:00:00Z,2020-12-22T03:15:00Z\r\n" + 
				"6,Tony,Telescope,2020-12-22T02:50:00Z,2020-12-22T03:40:00Z\r\n" + 
				"7,Tony,Pizza,2020-12-22T03:35:00Z,2020-12-22T04:10:00Z\r\n" + 
				"8,Tony,Drone,2020-12-22T05:25:00Z,2020-12-22T06:05:00Z";
		 //Converting input to String Reader for reading CSV values
		 StringReader csvBodyReader = new StringReader(str);
		 Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
		 
		 //Creating a map for storing time travelled by each Delivery Person
		 Map<String,Integer> mp=new HashMap<>();
		 
		 //Iterating through record
		 for (CSVRecord record : records) {
			 	
			    String name=record.get("Delivery Person");
			    int time=0;
			    if(mp.containsKey(name))
			    {
			    	time=mp.get(name);
			    }
			    
			    String pickUpTime=record.get("Pick up time");
			    String deliveredTime=record.get("Delivered time");
			    
			    //converting to suitable date format for using java classes
			    DateTimeFormatter sourceFormat=DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
			    DateTimeFormatter targetFormat=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

			    LocalDateTime pickUp= LocalDateTime.parse(pickUpTime, sourceFormat);
			    String formatedDateTime1= pickUp.format(targetFormat);
			    
			    LocalDateTime delivered= LocalDateTime.parse(deliveredTime, sourceFormat);
			    String formatedDateTime2= delivered.format(targetFormat);
			    
			    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			    Date date1 = format.parse(formatedDateTime1);
			    Date date2 = format.parse(formatedDateTime2);
			    //System.out.println(date2+" "+date1);
			    
			  int difference = (int) ((date2.getTime() - date1.getTime())/(1000*60)); 
			  
			  time+=difference;
			  mp.put(name,time);
			    
	            
	        }
		 	for(Map.Entry<String, Integer> hm:mp.entrySet())
		 	{
		 		System.out.println(hm.getKey()+" spent "+hm.getValue() +" minutes in travelling");
		 	}
	}

}
