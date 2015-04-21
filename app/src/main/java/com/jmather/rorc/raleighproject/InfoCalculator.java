package com.jmather.rorc.raleighproject;

/**
 * Created by Rorc on 4/18/2015.
 */
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.Random;


public class InfoCalculator
{
    /*
     * The contents of this class might end up needing to be split into different classes
     * this class will server as a way to outline the methods to be implemented
     */
    static LinkedList<String> saveData;

    public InfoCalculator(LinkedList<String> saveData)
    {
        //assume the data can be passed as a LinkedList of strings
        //where each index is a different instance of DriveInstance
        this.saveData = saveData;
    }

    public InfoCalculator()
    {
        this.saveData = new LinkedList<String>();

        String date = "04/19/2015"; //format mm/dd/yyyy ex. date = "04/17/2015";
        String day = "sun";
        String startTime = "05:56"; // 00:00 through 24:00 ex. startTime = "22:00";
        String endTime = "11:00"; // 00:00 through 24:00 ex. endTime = "22:01";
        double latStartCoordinate = 45.1324;
        double lonStartCoordinate = 46.1234;
        double latEndCoordinate = 47.1234;
        double lonEndCoordinate = 48.1234;
        double distance = 5.67;

        DriveInstance di = new DriveInstance(date, day, startTime, latStartCoordinate, lonStartCoordinate);
        di.setEndTime(endTime);
        di.setLatEndCoordinate(latEndCoordinate);
        di.setLonEndCoordinate(lonEndCoordinate);
        di.setDistance(distance);
        /*saveData = new LinkedList<String>();
        //the mock method in case we need fake data for demonstration
        int monthCount = 0;
        int dayCount = 0;
        int yearCount = 2015;
        int dayTracker = 0;

        for(int i = 0; i < 365; i++)
        {
            if(monthCount == 0 || dayCount == 23)
            {
                monthCount++;
            }
            if(monthCount == 13)
            {
                monthCount = 1;
                yearCount++;
            }
            if(dayCount == 0)
            {
                dayCount++;
            }
            if(dayCount == 24)
            {
                dayCount = 1;
            }
            String date = monthCount + "/" + dayCount + "/" + yearCount;
            String[] dayArray = {"sun", "mon", "tue", "wed", "thu", "fri", "sat"};
            String day = dayArray[dayCount];
            dayTracker++;

            int hour = 0;
            int minute = 0;
            Random r = new Random();
            hour = r.nextInt(24);
            minute = r.nextInt(60);
            String startTime = hour + ":" + minute;
            hour = r.nextInt(24);
            hour = r.nextInt(60);
            String endTime = hour + ":" + minute;
            double latStartCoordinate = 100;
            double lonStartCoordinate = 50;
            double latEndCoordinate = 100;
            double lonEndCoordinate = 100;
            double distance = r.nextInt(60);
            DriveInstance di = new DriveInstance(date, day, startTime, latStartCoordinate, lonStartCoordinate);
            di.setDistance(distance);
            di.setEndTime(endTime);
            di.setLatEndCoordinate(latEndCoordinate);
            di.setLonEndCoordinate(lonEndCoordinate);
            saveData.add(di.toJson());
        }*/

    }

    /**
     * adds up all the distanes driven in the save file
     * @return
     */
    private static double totalDriveDistance()
    {
        //i'm using this varaible enough that I should write a separate method for it
        double totalDistance = 0;
        for(int i = 0; i < saveData.size(); i++)//for each index of saveData
        {
            //grab the total distance
            JSONObject jObject;
            Double distance = 0.0;
            try{
                jObject = new JSONObject(saveData.get(i));
                distance = jObject.getDouble("distance");
            }
            catch(JSONException e)
            {
                e.printStackTrace();
            }


            totalDistance += distance;
        }
        return totalDistance;
    }

    /**
     * enter a date and get the total time driven that day
     * @param day
     */
    public static int totalTimeDriven(String day)
    {
        int totalTimeDriven = 0;
        for(int i = 0; i < saveData.size(); i++)
        {
            //search for the current date
            String currentDate = "";
            JSONObject currentDrive;
            try
            {
                currentDrive = new JSONObject(saveData.get(i));
                currentDate = currentDrive.getString("date");
            }
            catch(JSONException e)
            {
                e.printStackTrace();
            }
            if(currentDate.equals(day))
            {
                //get the time and add it to that day
                totalTimeDriven += InfoCalculator.getTime(saveData.get(i));
            }
        }
        return totalTimeDriven;
    }

    /**
     * get total distance driven in the specified day
     * @param day
     * @return
     */
    public static double totalDistanceDriven(String day)
    {
        double totalDistanceDriven = 0.0;
        for(int i = 0; i < saveData.size(); i++)
        {
            String currentDate = "";
            JSONObject currentDrive;
            try
            {
                currentDrive = new JSONObject(saveData.get(i));
                currentDate = currentDrive.getString("date");
            }
            catch(JSONException e)
            {
                e.printStackTrace();
            }
            if(currentDate.equals(day))
            {
                //get the distance and add it to that day
                JSONObject jObject;
                Double distance = 0.0;
                try{
                    jObject = new JSONObject(saveData.get(i));
                    distance = jObject.getDouble("distance");
                }
                catch(JSONException e) {
                    e.printStackTrace();
                }
                totalDistanceDriven += distance;
            }


        }
        return totalDistanceDriven;
    }

    public static double averageDriveDistance()
    {

        //when we have the total for the distance travelled
        double averageDistance = totalDriveDistance()/saveData.size();
        return averageDistance;
    }

    public static double averageDayDriveDistance(String day)
    {
		/*
		 * takes a day of the week and finds the average distance driven on that day of the week
		 */
        int dayCount = 0;  //total days run through
        String currentDate = "";
        double totalDistance = 0;
        for(int i = 0; i < saveData.size(); i++)
        {
            //figures out the number of days, then averages the total by that number
            JSONObject jObject;
            String savedDay = "";
            String date = "";
            double distance = 0.0;

            //get the date for this index
            JSONObject jObject1;
            try{
                jObject1 = new JSONObject(saveData.get(i));
                date = jObject1.getString("date");
            }
            catch(JSONException e)
            {
                e.printStackTrace();
            }

            //get the distance for this instance
            JSONObject jObject2;
            try{
                jObject2 = new JSONObject(saveData.get(i));
                distance = jObject2.getDouble("distance");
            }
            catch(JSONException e)
            {
                e.printStackTrace();
            }

            try{
                jObject = new JSONObject(saveData.get(i));
                savedDay = jObject.getString("day");
            }
            catch(JSONException e)
            {
                e.printStackTrace();
            }
            if(day.equals(savedDay))
            {
                if(currentDate.equals(""))
                {
                    //if there is no current date, we must have just started; set the variable

                    currentDate = date;
                    //get and add the distance

                    totalDistance += distance;
                }
                else if(currentDate.equals(date))
                {
                    //it's the same date, so add this distance to the total distance
                    totalDistance += distance;
                }
                else
                {
                    //it's a different date, so set the currentDate to this one,
                    //increment the days counter;
                    currentDate = date;
                    dayCount++;
                    //add this drive to the total distance
                    totalDistance += distance;
                }
            }


        }
        //after all the indexes of saveData have been run through
        //clean up by increment the days counter
        dayCount++;
        //get the average by dividing the total distance by the days
        double averageDistancePerDay = totalDriveDistance()/dayCount;
        return averageDistancePerDay;
    }

    /**
     * this method takes a String date (formatted mm/dd/yyyy) and returns the
     * average drive distance for that week.  The week is considered to be the Sunday-Saturday
     * selection of seven days that the date is contained in.
     *
     * @param date
     * @return the average distance traveled as a double
     */
    public static double averageWeekDriveDistance(String date)
    {
        int count = 0;
        double totalDistance = 0;

        String beginningOfWeek = "";
        String endOfWeek = "";

        //find the specified week
        for(int i = 0; i < saveData.size(); i++)
        {
            String date1 = "";
            JSONObject jObject2;
            double distance = 0.0;
            try{
                jObject2 = new JSONObject(saveData.get(i));
                distance = jObject2.getDouble("distance");
            }
            catch(JSONException e)
            {
                e.printStackTrace();
            }
            String dateToCheck = date1;
            if(dateToCheck.equals(date1))
            {
                //we have found the date, now to find the beginning and end of the week
            }
        }
        return totalDistance;
    }


    public static double averageMonthDriveDistance(int twoDigitMonth, int fourDigitYear)
    {
        //Setup variables
        int count = 0;
        double totalDistance = 0;
        //find the total amount driven during the given month, then divide by the number of drives
        for(int i = 0; i < saveData.size(); i++)
        {

            JSONObject jObject2;
            String date1 = "";
            try{
                jObject2 = new JSONObject(saveData.get(i));
                date1 = jObject2.getString("date");
            }
            catch(JSONException e)
            {
                e.printStackTrace();
            }

            //get the distance for this instance
            JSONObject jObject3;
            double distance1 = 0.0;
            try{
                jObject3 = new JSONObject(saveData.get(i));
                distance1 = jObject3.getDouble("distance");
            }
            catch(JSONException e)
            {
                e.printStackTrace();
            }
            //determine if the date is in the correct month
            String date = date1;
            String[]checkDate = date.split("/"); //the month will be at index 0, the year at index 2
            if(Integer.parseInt(checkDate[0]) == twoDigitMonth &&
                    Integer.parseInt(checkDate[2]) == fourDigitYear)
            {
                //this is the correct month and year, continue
                //each time, increment the count and add to the totalDistance
                count++;

                totalDistance += distance1;
            }
            else
            {
                //check for break conditions
                if(Integer.parseInt(checkDate[2]) > fourDigitYear ||
                        (Integer.parseInt(checkDate[2]) == fourDigitYear
                                && Integer.parseInt(checkDate[0]) > twoDigitMonth))
                {
                    //we passed the month, no point in continuing checking
                    break;
                }

            }
        }
        //we have the total distance
        double averageDistance = totalDistance/count;
        return averageDistance;

    }

    public static double averageYearDriveDistance(int fourDigitYear)
    {
        //Setup variables
        int count = 0;
        double totalDistance = 0;
        //find the total amount driven during the given month, then divide by the number of drives
        for(int i = 0; i < saveData.size(); i++)
        {
            //get date
            JSONObject jObject;
            String date1 = "";
            try{
                jObject = new JSONObject(saveData.get(i));
                date1 = jObject.getString("date");
            }
            catch(JSONException e)
            {
                e.printStackTrace();
            }

            //get distance
            JSONObject jObject1;
            double distance1 = 0.0;
            try{
                jObject = new JSONObject(saveData.get(i));
                distance1 = jObject.getDouble("distance");
            }
            catch(JSONException e)
            {
                e.printStackTrace();
            }

            //determine if the date is in the correct month
            String date = date1;
            String[]checkDate = date.split("/"); //the month will be at index 0, the year at index 2
            if(Integer.parseInt(checkDate[2]) == fourDigitYear)
            {
                //this is the correct year, continue
                //each time, increment the count and add to the totalDistance
                count++;
                totalDistance += distance1;
            }
            else
            {
                //check for break conditions
                if(Integer.parseInt(checkDate[2]) > fourDigitYear)
                {
                    //we passed the year, no point in continuing checking
                    break;
                }

            }
        }
        //we have the total distance
        double averageDistance = totalDistance/count;
        return averageDistance;

    }

    public static int averageTimeDriven()
    {
        //when we have the total for the distance travelled
        int averageTime = totalTime()/saveData.size();
        return averageTime;
    }

    /**
     * returns the total time driven in the save file in minutes
     * @return
     */
    private static int totalTime()
    {
        //i'm using this varaible enough that I should write a separate method for it
        int totalTime = 0;
        for(int i = 0; i < saveData.size(); i++)//for each index of saveData
        {
            getTime(saveData.get(i));

        }
        return totalTime;
    }

    private static String getDate(String driveInstance)
    {
        JSONObject jObject;
        String getDate = "";
        try{
            jObject = new JSONObject(driveInstance);
            getDate = jObject.getString("Date");
        }
        catch(JSONException e)
        {
            e.printStackTrace();
        }
        return getDate;
    }

    private static String getDay(String driveInstance)
    {
        JSONObject jObject;
        String getDay = "";
        try{
            jObject = new JSONObject(driveInstance);
            getDay = jObject.getString("Day");
        }
        catch(JSONException e)
        {
            e.printStackTrace();
        }
        return getDay;
    }

    private static double getDistance(String driveInstance)
    {
        JSONObject jObject;
        double getDistance = 0.0;
        try{
            jObject = new JSONObject(driveInstance);
            getDistance = jObject.getDouble("Distance");
        }
        catch(JSONException e)
        {
            e.printStackTrace();
        }
        return getDistance;
    }

    /**
     * returns the total time from the given record
     * @param driveInstance
     * @return
     */
    private static int getTime(String driveInstance)
    {
        int totalTime;
        //get the start time
        JSONObject jObject;
        String getStartTime = "";
        try{
            jObject = new JSONObject(driveInstance);
            JSONObject getTimeObject = new JSONObject(jObject.getString("time"));
            getStartTime = getTimeObject.getString("startTime");
        }
        catch(JSONException e)
        {
            e.printStackTrace();
        }

        //get the end time
        JSONObject jObject1;
        String getEndTime = "";
        try{
            jObject = new JSONObject(driveInstance);
            JSONObject getTimeObject = new JSONObject(jObject.getString("time"));
            getEndTime = getTimeObject.getString("endTime");
        }
        catch(JSONException e)
        {
            e.printStackTrace();
        }
        String startTime = getStartTime;
        //get the end time
        String endTime = getEndTime;
        //****CONTINUE
        //total time in minutes
        String[] startComponents = startTime.split(":");
        String[] endComponents = endTime.split(":");

        if(Integer.parseInt(startComponents[0]) > Integer.parseInt(endComponents[0]))
        {
            totalTime = (24-(Integer.parseInt(startComponents[0])
                    -Integer.parseInt(endComponents[0]))*60);
        }
        else
        {
            totalTime = (Integer.parseInt(endComponents[0])
                    -(Integer.parseInt(startComponents[0]))*60);
        }
        return totalTime;
    }

    public static int totalTimeWeek(String date)
    {
        //search for date
        int totalTime = 0;
        int index = 0;
        for(int i = 0; i < saveData.size(); i++)
        {
            InfoCalculator.getDate(saveData.get(i));
            if(getDate(saveData.get(i)).equals(date))
            {
                while(getDate(saveData.get(i)).equals(date))
                {
                    i++;
                }
                index = i-1;//go back to last valid driveInstance of the given day
                break;
            }
        }
        //get the day we need to head back to
        String day = InfoCalculator.getDay(saveData.get(index));//get the day of the week from our current day
        if(day.equals("fri"))
        {
            boolean currentFri = true;
            while(!day.equals("fri") || currentFri)
            {
                totalTime += InfoCalculator.getTime(saveData.get(index));
                index--;
                day = InfoCalculator.getDay(saveData.get(index));
                if(!day.equals("fri") && currentFri)
                {
                    //we just left the current friday we were in
                    currentFri = false;
                    //continue until we hit friday again
                }

            }
            //we must've hit the next friday
            while(day.equals("fri"))
            {
                totalTime += InfoCalculator.getTime(saveData.get(index));
                day = InfoCalculator.getDay(saveData.get(index));
                index--;
            }
            //we're past the end of the week, we should have the totalMinutes
            totalTime = totalTime/7;

        }
        else if(day.equals("thu"))
        {
            while(!day.equals("fri"))
            {
                totalTime += InfoCalculator.getTime(saveData.get(index));
                index--;
                day = InfoCalculator.getDay(saveData.get(index));

            }
            //we must've hit the next friday
            while(day.equals("fri"))
            {
                totalTime += InfoCalculator.getTime(saveData.get(index));
                day = InfoCalculator.getDay(saveData.get(index));
                index--;
            }
            //we're past the end of the week, we should have the totalMinutes
            totalTime = totalTime/6;
        }
        else if(day.equals("wed"))
        {
            while(!day.equals("fri"))
            {
                totalTime += InfoCalculator.getTime(saveData.get(index));
                index--;
                day = InfoCalculator.getDay(saveData.get(index));

            }
            //we must've hit the next friday
            while(day.equals("fri"))
            {
                totalTime += InfoCalculator.getTime(saveData.get(index));
                day = InfoCalculator.getDay(saveData.get(index));
                index--;
            }
            //we're past the end of the week, we should have the totalMinutes
            totalTime = totalTime/5;
        }
        else if(day.equals("tue"))
        {
            while(!day.equals("fri"))
            {
                totalTime += InfoCalculator.getTime(saveData.get(index));
                index--;
                day = InfoCalculator.getDay(saveData.get(index));

            }
            //we must've hit the next friday
            while(day.equals("fri"))
            {
                totalTime += InfoCalculator.getTime(saveData.get(index));
                day = InfoCalculator.getDay(saveData.get(index));
                index--;
            }
            //we're past the end of the week, we should have the totalMinutes
            totalTime = totalTime/4;
        }
        else if(day.equals("mon"))
        {
            while(!day.equals("fri"))
            {
                totalTime += InfoCalculator.getTime(saveData.get(index));
                index--;
                day = InfoCalculator.getDay(saveData.get(index));

            }
            //we must've hit the next friday
            while(day.equals("fri"))
            {
                totalTime += InfoCalculator.getTime(saveData.get(index));
                day = InfoCalculator.getDay(saveData.get(index));
                index--;
            }
            //we're past the end of the week, we should have the totalMinutes
            totalTime = totalTime/3;
        }
        else if(day.equals("sun"))
        {
            while(!day.equals("fri"))
            {
                totalTime += InfoCalculator.getTime(saveData.get(index));
                index--;
                day = InfoCalculator.getDay(saveData.get(index));

            }
            //we must've hit the next friday
            while(day.equals("fri"))
            {
                totalTime += InfoCalculator.getTime(saveData.get(index));
                day = InfoCalculator.getDay(saveData.get(index));
                index--;
            }
            //we're past the end of the week, we should have the totalMinutes
            totalTime = totalTime/2;
        }
        else if(day.equals("sat"))
        {
            while(!day.equals("fri"))
            {
                totalTime += InfoCalculator.getTime(saveData.get(index));
                index--;
                day = InfoCalculator.getDay(saveData.get(index));

            }
            //we must've hit the next friday
            while(day.equals("fri"))
            {
                totalTime += InfoCalculator.getTime(saveData.get(index));
                day = InfoCalculator.getDay(saveData.get(index));
                index--;
            }
            //we're past the end of the week, we should have the totalMinutes
            totalTime = totalTime/1;
        }
        return totalTime;
    }

    public static double totalDistanceWeek(String date)
    {
        //search for date
        double totalDistanceDriven = 0.0;
        int index = 0;
        for(int i = 0; i < saveData.size(); i++)
        {
            InfoCalculator.getDate(saveData.get(i));
            if(getDate(saveData.get(i)).equals(date))
            {
                while(getDate(saveData.get(i)).equals(date))
                {
                    i++;
                }
                index = i-1;//go back to last valid driveInstance of the given day
                break;
            }
        }
        //get the day we need to head back to
        String day = InfoCalculator.getDay(saveData.get(index));//get the day of the week from our current day
        if(day.equals("fri"))
        {
            boolean currentFri = true;
            while(!day.equals("fri") || currentFri)
            {
                totalDistanceDriven += InfoCalculator.getDistance(saveData.get(index));
                index--;
                day = InfoCalculator.getDay(saveData.get(index));
                if(!day.equals("fri") && currentFri)
                {
                    //we just left the current friday we were in
                    currentFri = false;
                    //continue until we hit friday again
                }

            }
            //we must've hit the next friday
            while(day.equals("fri"))
            {
                totalDistanceDriven += InfoCalculator.getDistance(saveData.get(index));
                day = InfoCalculator.getDay(saveData.get(index));
                index--;
            }
            //we're past the end of the week, we should have the totalMinutes
            totalDistanceDriven = totalDistanceDriven/7;

        }
        else if(day.equals("thu"))
        {
            while(!day.equals("fri"))
            {
                totalDistanceDriven += InfoCalculator.getDistance(saveData.get(index));
                index--;
                day = InfoCalculator.getDay(saveData.get(index));

            }
            //we must've hit the next friday
            while(day.equals("fri"))
            {
                totalDistanceDriven += InfoCalculator.getDistance(saveData.get(index));
                day = InfoCalculator.getDay(saveData.get(index));
                index--;
            }
            //we're past the end of the week, we should have the totalMinutes
            totalDistanceDriven = totalDistanceDriven/6;
        }
        else if(day.equals("wed"))
        {
            while(!day.equals("fri"))
            {
                totalDistanceDriven += InfoCalculator.getDistance(saveData.get(index));
                index--;
                day = InfoCalculator.getDay(saveData.get(index));

            }
            //we must've hit the next friday
            while(day.equals("fri"))
            {
                totalDistanceDriven += InfoCalculator.getDistance(saveData.get(index));
                day = InfoCalculator.getDay(saveData.get(index));
                index--;
            }
            //we're past the end of the week, we should have the totalMinutes
            totalDistanceDriven = totalDistanceDriven/5;
        }
        else if(day.equals("tue"))
        {
            while(!day.equals("fri"))
            {
                totalDistanceDriven += InfoCalculator.getDistance(saveData.get(index));
                index--;
                day = InfoCalculator.getDay(saveData.get(index));

            }
            //we must've hit the next friday
            while(day.equals("fri"))
            {
                totalDistanceDriven += InfoCalculator.getDistance(saveData.get(index));
                day = InfoCalculator.getDay(saveData.get(index));
                index--;
            }
            //we're past the end of the week, we should have the totalMinutes
            totalDistanceDriven = totalDistanceDriven/4;
        }
        else if(day.equals("mon"))
        {
            while(!day.equals("fri"))
            {
                totalDistanceDriven += InfoCalculator.getDistance(saveData.get(index));
                index--;
                day = InfoCalculator.getDay(saveData.get(index));

            }
            //we must've hit the next friday
            while(day.equals("fri"))
            {
                totalDistanceDriven += InfoCalculator.getDistance(saveData.get(index));
                day = InfoCalculator.getDay(saveData.get(index));
                index--;
            }
            //we're past the end of the week, we should have the totalMinutes
            totalDistanceDriven = totalDistanceDriven/3;
        }
        else if(day.equals("sun"))
        {
            while(!day.equals("fri"))
            {
                totalDistanceDriven += InfoCalculator.getDistance(saveData.get(index));
                index--;
                day = InfoCalculator.getDay(saveData.get(index));

            }
            //we must've hit the next friday
            while(day.equals("fri"))
            {
                totalDistanceDriven += InfoCalculator.getTime(saveData.get(index));
                day = InfoCalculator.getDay(saveData.get(index));
                index--;
            }
            //we're past the end of the week, we should have the totalMinutes
            totalDistanceDriven = totalDistanceDriven/2;
        }
        else if(day.equals("sat"))
        {
            while(!day.equals("fri"))
            {
                totalDistanceDriven += InfoCalculator.getDistance(saveData.get(index));
                index--;
                day = InfoCalculator.getDay(saveData.get(index));

            }
            //we must've hit the next friday
            while(day.equals("fri"))
            {
                totalDistanceDriven += InfoCalculator.getDistance(saveData.get(index));
                day = InfoCalculator.getDay(saveData.get(index));
                index--;
            }
            //we're past the end of the week, we should have the totalMinutes
            totalDistanceDriven = totalDistanceDriven/1;
        }
        return totalDistanceDriven;
    }

}