package de.meisterfuu.animexx.services.helper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.provider.BaseColumns;
import android.provider.CalendarContract;
import android.provider.CalendarContract.Calendars;
import android.provider.CalendarContract.Events;
import android.util.Log;
import de.meisterfuu.animexx.Constants;
import de.meisterfuu.animexx.api.broker.CalendarBroker;
import de.meisterfuu.animexx.objects.CalendarEntryObject;
import de.meisterfuu.animexx.utils.Request;

public class PrivateCalHelper {
	
	private static String CALENDAR_COLUMN_NAME = "animexx_private_cal";
	public static final String TAG = "Animexx Private Cal";
	
	
	public static Uri getAdapterUri(Uri uri) {
        return uri.buildUpon().appendQueryParameter(CalendarContract.CALLER_IS_SYNCADAPTER, "true")
                .appendQueryParameter(Calendars.ACCOUNT_NAME, Constants.ACCOUNT_NAME)
                .appendQueryParameter(Calendars.ACCOUNT_TYPE, Constants.ACCOUNT_TYPE).build();
    }

	/**
     * Gets calendar id, when no calendar is present, create one!
     *
     * @param context
     * @return
     */
    public static long getCalendar(Context context) {
        Log.d("CalSyncDemo", "getCalendar Method...PRIVAT");

        ContentResolver contentResolver = context.getContentResolver();

        // Find the calendar if we've got one
        Uri calenderUri = getAdapterUri(Calendars.CONTENT_URI);

        Cursor cursor = contentResolver.query(calenderUri, new String[]{BaseColumns._ID, Calendars.CAL_SYNC1},
                Calendars.ACCOUNT_NAME + " = ? AND " + Calendars.ACCOUNT_TYPE + " = ? AND "+ Calendars.CAL_SYNC1 + " = ?",
                new String[]{Constants.ACCOUNT_NAME, Constants.ACCOUNT_TYPE, CALENDAR_COLUMN_NAME}, null);

        try {
            if (cursor != null && cursor.moveToNext()) {
            	int colIndex = cursor.getColumnIndex(Calendars.CAL_SYNC1);
            	while(!cursor.getString(colIndex).equals(CALENDAR_COLUMN_NAME)){
            		if(!cursor.moveToNext()){
            			break;
            		}
            	}
                if(cursor.getString(colIndex).equals(CALENDAR_COLUMN_NAME))
            	return cursor.getLong(0);
            } 
            ArrayList<ContentProviderOperation> operationList = new ArrayList<ContentProviderOperation>();

            ContentProviderOperation.Builder builder = ContentProviderOperation
                    .newInsert(calenderUri);
            builder.withValue(Calendars.ACCOUNT_NAME, Constants.ACCOUNT_NAME);
            builder.withValue(Calendars.ACCOUNT_TYPE, Constants.ACCOUNT_TYPE);
            builder.withValue(Calendars.NAME, CALENDAR_COLUMN_NAME);
            builder.withValue(Calendars.CALENDAR_DISPLAY_NAME, "Privat");
            builder.withValue(Calendars.CAL_SYNC1, CALENDAR_COLUMN_NAME);

//                builder.withValue(Calendars._ID, CALENDAR_COLUMN_NAME.hashCode());
            builder.withValue(Calendars.CALENDAR_COLOR, Color.parseColor("#5858FF"));

            //builder.withValue(Calendars.CALENDAR_ACCESS_LEVEL, Calendars.CAL_ACCESS_EDITOR);
            builder.withValue(Calendars.CALENDAR_ACCESS_LEVEL, Calendars.CAL_ACCESS_READ);
 
            builder.withValue(Calendars.OWNER_ACCOUNT, Constants.ACCOUNT_NAME);
            builder.withValue(Calendars.SYNC_EVENTS, 1);
            builder.withValue(Calendars.VISIBLE, 1);
            operationList.add(builder.build());
            try {
                contentResolver.applyBatch(CalendarContract.AUTHORITY, operationList);
            } catch (Exception e) {
            	Log.e(TAG, "getCalendar() failed", e);
            	return -1;
            }
            return getCalendar(context);
        
        } finally {
            if (cursor != null && !cursor.isClosed())
                cursor.close();
        }
    }
	
    public static ContentProviderOperation insertSingleEvent(Context context, long calendarId, CalendarEntryObject event) {
        ContentValues values = new ContentValues();
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.GERMANY);
//		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        
        Calendar sc = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        Calendar ec = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
//        Calendar sc = Calendar.getInstance(TimeZone.getTimeZone("Europe/Berlin"));
//        Calendar ec = Calendar.getInstance(TimeZone.getTimeZone("Europe/Berlin"));
//        Calendar sc = Calendar.getInstance();
//        Calendar ec = Calendar.getInstance();        
        
//        if(sc.get(Calendar.SECOND) == 0){
//	      sc.add(Calendar.SECOND, 1);
//        }
        

        
        sc.setTime(event.getStartTS());
        ec.setTime(event.getEndTS());
        
        int day =  sc.get(Calendar.DAY_OF_MONTH)+1;
        int daye = ec.get(Calendar.DAY_OF_MONTH)+1;
        if(event.isFullday()){
            sc.set(Calendar.DAY_OF_MONTH, day);
            sc.set(Calendar.HOUR_OF_DAY, 0);
            sc.set(Calendar.SECOND, 0);
            sc.set(Calendar.MINUTE, 0);
            ec.set(Calendar.DAY_OF_MONTH, daye);
            ec.set(Calendar.HOUR_OF_DAY, 0);
            ec.set(Calendar.SECOND, 0);
            ec.set(Calendar.MINUTE, 0);
          }

        
//        Log.e("EVENT", "offest "+TimeZone.getTimeZone("Europe/Berlin").getRawOffset());

 
        
//        sc.set(Calendar.HOUR, 0);

//        ec.set(Calendar.MINUTE, 59);
//        ec.set(Calendar.HOUR, 23);
        
        long dtstart = sc.getTimeInMillis();//+TimeZone.getTimeZone("Europe/Berlin").getRawOffset();
        long dtend = ec.getTimeInMillis();//+TimeZone.getTimeZone("Europe/Berlin").getRawOffset();
        

        
        values.put(Events.CALENDAR_ID, calendarId);        
        if(event.isFullday()){
        	values.put(Events.ALL_DAY, 1);
        }
        if(event.getId().startsWith("geb_")){
        	values.put(Events.RRULE, "FREQ=YEARLY;INTERVAL=1;COUNT=10;");
        }
        values.put(Events.DTSTART, dtstart);
        values.put(Events.DTEND, dtend);
        values.put(Events.EVENT_TIMEZONE, "Europe/Berlin");

//        values.put(Events., value)
        
        values.put(Events.TITLE, event.getTitle());
        values.put(Events.DESCRIPTION, event.getPlace());
        
        values.put(Events.STATUS, Events.STATUS_CONFIRMED);
        values.put(Events.HAS_ALARM, true);
        values.put(Events.SYNC_DATA1, event.getId());

        ContentProviderOperation.Builder builder;
        builder = ContentProviderOperation.newInsert(getAdapterUri(Events.CONTENT_URI));
        builder.withValues(values);
        
        return builder.build();

//        long eventID = Long.parseLong(uri.getLastPathSegment());
//        
//        ContentValues reminders = new ContentValues();
//        reminders.put(Reminders.EVENT_ID, eventID);
//        reminders.put(Reminders.METHOD, Reminders.METHOD_ALERT);
//        reminders.put(Reminders.MINUTES, 1);
//
//        cr.insert(Reminders.CONTENT_URI, reminders);

    }
    
    
    public static ContentProviderOperation updateSingleEvent(Context context, long calendarId, CalendarEntryObject event) {
        ContentValues values = new ContentValues();
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.GERMANY);
//		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        
        Calendar sc = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        Calendar ec = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
//        Calendar sc = Calendar.getInstance(TimeZone.getTimeZone("Europe/Berlin"));
//        Calendar ec = Calendar.getInstance(TimeZone.getTimeZone("Europe/Berlin"));
//        Calendar sc = Calendar.getInstance();
//        Calendar ec = Calendar.getInstance();        
        
//        if(sc.get(Calendar.SECOND) == 0){
//	      sc.add(Calendar.SECOND, 1);
//        }
        

        
        sc.setTime(event.getStartTS());
        ec.setTime(event.getEndTS());
        
        int day =  sc.get(Calendar.DAY_OF_MONTH)+1;
        int daye = ec.get(Calendar.DAY_OF_MONTH)+1;
        if(event.isFullday()){
          sc.set(Calendar.DAY_OF_MONTH, day);
          sc.set(Calendar.HOUR_OF_DAY, 0);
          sc.set(Calendar.SECOND, 0);
          sc.set(Calendar.MINUTE, 0);
          ec.set(Calendar.DAY_OF_MONTH, daye);
          ec.set(Calendar.HOUR_OF_DAY, 0);
          ec.set(Calendar.SECOND, 0);
          ec.set(Calendar.MINUTE, 0);
        }

        
//        Log.e("EVENT", "offest "+TimeZone.getTimeZone("Europe/Berlin").getRawOffset());

 
        
//        sc.set(Calendar.HOUR, 0);

//        ec.set(Calendar.MINUTE, 59);
//        ec.set(Calendar.HOUR, 23);
        
        long dtstart = sc.getTimeInMillis();//+TimeZone.getTimeZone("Europe/Berlin").getRawOffset();
        long dtend = ec.getTimeInMillis();//+TimeZone.getTimeZone("Europe/Berlin").getRawOffset();
        

        values.put(Events.CALENDAR_ID, calendarId);        
        if(event.isFullday()){
        	values.put(Events.ALL_DAY, 1);
        }
        if(event.getId().startsWith("geb_")){
        	values.put(Events.RRULE, "FREQ=YEARLY;INTERVAL=1;COUNT=10;");
        }
        values.put(Events.DTSTART, dtstart);
        values.put(Events.DTEND, dtend);
        values.put(Events.EVENT_TIMEZONE, "Europe/Berlin");

//        values.put(Events., value)
        
        values.put(Events.TITLE, event.getTitle());
        values.put(Events.DESCRIPTION, event.getPlace());
        
        values.put(Events.STATUS, Events.STATUS_CONFIRMED);
        values.put(Events.HAS_ALARM, true);
        values.put(Events.SYNC_DATA1, event.getId());

        ContentProviderOperation.Builder builder;
        builder = ContentProviderOperation.newUpdate(getAdapterUri(Events.CONTENT_URI)).withSelection(Events._ID + " = ?", new String[]{""+event.internal_id});
        builder.withValues(values);
        
        return builder.build();

//        long eventID = Long.parseLong(uri.getLastPathSegment());
//        
//        ContentValues reminders = new ContentValues();
//        reminders.put(Reminders.EVENT_ID, eventID);
//        reminders.put(Reminders.METHOD, Reminders.METHOD_ALERT);
//        reminders.put(Reminders.MINUTES, 1);
//
//        cr.insert(Reminders.CONTENT_URI, reminders);

    }
    
    public static ArrayList<CompareObject> getCurrentEvents(Context pContext, long pCalendarId){
    	
    	ContentResolver contentResolver = pContext.getContentResolver();
    	
    	String[] projection = new String[] { Events._ID, Events.SYNC_DATA1, Events.DTEND};    	
    	Cursor cursor = contentResolver.query(	getAdapterUri(Events.CONTENT_URI), 
    							projection, 
    							Events.CALENDAR_ID + " = ? ",
    							new String[]{""+pCalendarId},
    							null);
    	
    	ArrayList<CompareObject> ret = new ArrayList<CompareObject>();
    	CompareObject temp;
        if (cursor != null) {
        	while(cursor.moveToNext()){
            		temp = new CompareObject();
            		temp._id = cursor.getLong(0);
            		temp.animexx_id = cursor.getString(1);
            		temp.dtend = cursor.getLong(2);
            		ret.add(temp);
        	}
        	cursor.close();
        } 
    	
    	return ret;
    }
    
    static class CompareObject{
    	
    	String animexx_id;
    	long _id;
    	long dtend;
    	
    	
    	public CompareObject(String str){
    		animexx_id = str;
    	}
    	
    	public CompareObject(){
    	}
    	
		@Override
		public String toString() {
			return animexx_id;
		}
    	
    }
    
    public static void sync(Context pContext){
        Log.d(TAG, "Starting sync...");
        Request.config = PreferenceManager.getDefaultSharedPreferences(pContext);

        ContentResolver contentResolver = pContext.getContentResolver();

        if (contentResolver == null) {
            Log.e(TAG, "Unable to get content resolver!");
            return;
        }

        long calendarId = getCalendar(pContext);
        if (calendarId == -1) {
            Log.e("TAG", "Unable to create calendar");
            return;
        }
        
//        contentResolver.delete(getAdapterUri(Events.CONTENT_URI), Events.CALENDAR_ID + " = ?", new String[]{String.valueOf(calendarId)});

        
        CalendarBroker API = new CalendarBroker(pContext);
        
        ArrayList<CalendarEntryObject> events = new ArrayList<CalendarEntryObject>();
        ArrayList<CalendarEntryObject> _new = new ArrayList<CalendarEntryObject>();
        ArrayList<CalendarEntryObject> _update = new ArrayList<CalendarEntryObject>();
        ArrayList<CompareObject> not_delete = new ArrayList<CompareObject>();
        
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.GERMANY);
		try {
	        for(int i = 0; i < 12; i++){
				events.addAll(API.getEventListNT(sdf.format(c.getTime())));
				c.add(Calendar.MONTH, 1);
	        }
	        ArrayList<CompareObject> _arr = getCurrentEvents(pContext, calendarId);


	        
	        for(CalendarEntryObject obj: events){	  
	        	boolean cont = false;
	        	for(CompareObject obj_inner: _arr){
//	        		Log.d(TAG, obj.getId() + " = " + obj_inner.animexx_id);
	        		 if(obj.getId().equals(obj_inner.animexx_id)){
	        			 cont = true;
		 	        	 obj.internal_id = obj_inner._id;
	        			 _update.add(obj);
	        			 not_delete.add(obj_inner);
	        		 }
	        	}
	        	if(cont){
	        		continue;
	        	} else {
		        		_new.add(obj);
	        	}
	        }
        	for(CompareObject obj: not_delete){
        		_arr.remove(obj);
        	}
        	for(CompareObject obj: _arr){
        	    contentResolver.delete(getAdapterUri(Events.CONTENT_URI), Events.CALENDAR_ID + " = ? AND "+ Events._ID + " = ?", new String[]{String.valueOf(calendarId), String.valueOf(obj._id)});
        	}
      
		} catch (Exception e1) {
			e1.printStackTrace();
			return;
		}
        
        ArrayList<ContentProviderOperation> operationList = new ArrayList<ContentProviderOperation>();

  
        for(CalendarEntryObject event: _new){
        	operationList.add(insertSingleEvent(pContext, calendarId, event));
        }
        
        for(CalendarEntryObject event: _update){
        	operationList.add(updateSingleEvent(pContext, calendarId, event));
        }
        
        
   List<ArrayList<ContentProviderOperation>> parts = chopped(operationList, 100);
        
        /* Create events */
        for(ArrayList<ContentProviderOperation> list: parts){
            if (list.size() > 0) {
                try {
                    Log.d(TAG, "Start applying a batch...");
                    contentResolver.applyBatch(CalendarContract.AUTHORITY, list);
                    Log.d(TAG, "Applying the batch was successful!");
                } catch (Exception e) {
                    Log.e(TAG, "Applying batch error!", e);
                }
            }
        }

    }
    
    static <T> List<ArrayList<T>> chopped(List<T> list, final int L) {
        List<ArrayList<T>> parts = new ArrayList<ArrayList<T>>();
        final int N = list.size();
        for (int i = 0; i < N; i += L) {
            parts.add(new ArrayList<T>(
                list.subList(i, Math.min(N, i + L)))
            );
        }
        return parts;
    }
}
