package com.jmather.rorc.raleighproject;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Josh on 4/18/2015.
 */
public class DriveInstance
{
    /*
	 *This class sets up the structure for tracking all the information needed by the program.
	 *This includes:
	 * String: date
	 * String: startTime
	 * String: endTime
	 * String: day
	 * double: latStartCoordinate
	 * double: lonStartCoordinate
	 * double: latEndCoordinate
	 * double: lonEndCoordinate
	 * double: distance
	 *
	 */



    private String date = ""; //format mm/dd/yyyy ex. date = "04/17/2015";
    private String day = ""; // mon, tue, wed, thu, fri, sat, sun ex. day = "mon";
    private String startTime = ""; // 00:00 through 24:00 ex. startTime = "22:00";
    private String endTime = ""; // 00:00 through 24:00 ex. endTime = "22:01";
    private double latStartCoordinate;
    private double lonStartCoordinate;
    private double latEndCoordinate;
    private double lonEndCoordinate;
    private double distance;


    public DriveInstance(String date, String day, String startTime, double latStartCoordinate, double lonStartCoordinates)
    {
        //when an instance of this class is made, set the date, startTime, and the StartCoordinates
        this.date = date;
        this.day = day;
        this.startTime = startTime;
        this.latStartCoordinate = latStartCoordinate;
        this.lonStartCoordinate = lonStartCoordinates;
    }


    public String getDate() {
        return date;
    }


    public void setDate(String date) {
        this.date = date;
    }


    public String getStartTime() {
        return startTime;
    }


    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }


    public String getEndTime() {
        return endTime;
    }


    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }


    public double getLatStartCoordinate() {
        return latStartCoordinate;
    }


    public void setLatStartCoordinate(double latStartCoordinate) {
        this.latStartCoordinate = latStartCoordinate;
    }


    public double getLonStartCoordinate() {
        return lonStartCoordinate;
    }


    public void setLonStartCoordinate(double lonStartCoordinate) {
        this.lonStartCoordinate = lonStartCoordinate;
    }


    public double getLatEndCoordinate() {
        return latEndCoordinate;
    }


    public void setLatEndCoordinate(double latEndCoordinate) {
        this.latEndCoordinate = latEndCoordinate;
    }


    public double getLonEndCoordinate() {
        return lonEndCoordinate;
    }


    public void setLonEndCoordinate(double lonEndCoordinate) {
        this.lonEndCoordinate = lonEndCoordinate;
    }


    public double getDistance() {
        return distance;
    }


    public void setDistance(double distance) {
        this.distance = distance;
    }


    public String toJson() {
        //hopefully we can use a json implementation from someone else to write to and read
        //from json instead of doing it ourselves, which is why I only put the general
        //format here.

        String json = "";
        JSONObject jObject = new JSONObject();
        try
        {
            jObject.put("date", this.date);
            jObject.put("day", this.day);
            //make a time object to add to the json
            JSONObject jTime = new JSONObject();
            jTime.put("startTime", this.startTime);
            jTime.put("endTime", this.endTime);
            //put the jTime object in the jObject
            jObject.put("time", jTime);
            //make a startCoordinates object
            JSONObject startCoordinatesObject = new JSONObject();
            startCoordinatesObject.put("latitude", this.latStartCoordinate);
            startCoordinatesObject.put("longitude", this.lonStartCoordinate);
            //add startCoordinatesObject to jObject\
            jObject.put("startCoordinates", startCoordinatesObject);
            //made an endCoordinates object
            JSONObject endCoordinatesObject = new JSONObject();
            endCoordinatesObject.put("latitude", this.latEndCoordinate);
            endCoordinatesObject.put("longitude", this.lonEndCoordinate);
            //add endCoordinatesObject to jObject
            jObject.put("endCoordinates", endCoordinatesObject);
            //add distance to the jObject
            jObject.put("distance", this.distance);
        }
        catch(JSONException e)
        {
            e.printStackTrace();
        }

        return jObject.toString();

        //returns a json representation of this object
        //the structure should be something like this:
		/*
		 * DriveInstance:
		 * {
		 * 	"date":"this.date";
		 * 	"day":"this.day";
		 * 	"time":
		 * 		{
		 * 			"startTime":"this.startTime";
		 * 			"endTime":"this.endTime";
		 * 		}
		 * 	"startCoordinates":
		 * 		{
		 * 			"latitude":this.latStartCoordinate;
		 * 			"longitude":this.lonStartCoordiate;
		 * 		}
		 * 	"endCoordinates":
		 * 		{
		 * 			"latitude":this.latEndCoordiante;
		 * 			"longitude:this.lonEndCoordinate;
		 * 		}
		 * 	"distance":this.distance;
		 * }
		 */

    }
}

