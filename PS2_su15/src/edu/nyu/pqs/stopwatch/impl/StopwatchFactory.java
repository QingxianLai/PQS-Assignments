package edu.nyu.pqs.stopwatch.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import edu.nyu.pqs.stopwatch.api.IStopwatch;

/**
 * The StopwatchFactory is a thread-safe factory class for IStopwatch objects.
 * It maintains references to all created IStopwatch objects and provides a
 * convenient method for getting a list of those objects.
 *
 */
public class StopwatchFactory {
  private static final ConcurrentHashMap<String,IStopwatch> stopWatchMap = 
      new ConcurrentHashMap<String, IStopwatch>();
  
	/**
	 * Creates and returns a new IStopwatch object
	 * @param id The identifier of the new object
	 * @return The new IStopwatch object
	 * @throws IllegalArgumentException if <code>id</code> is empty, null, or already
   *     taken.
	 */
	public static IStopwatch getStopwatch(String id) {
	  if (stopWatchMap.contains(id) || id==null || id.length()==0) {
	    throw new IllegalArgumentException();
	  }
		StopWatch stopWatchInstance = new StopWatch(id);
		stopWatchMap.put(id,stopWatchInstance);
		return stopWatchInstance;
	}

	/**
	 * Returns a list of all created stopwatches
	 * @return a List of all creates IStopwatch objects.  Returns an empty
	 * list if no IStopwatches have been created.
	 */
	public static List<IStopwatch> getStopwatches() {
		if (stopWatchMap.isEmpty()) {
		  return Collections.<IStopwatch>emptyList();
		} else {
		  List<IStopwatch> stopWatchList = new ArrayList<IStopwatch>();
		  Enumeration<IStopwatch> enumWatch = stopWatchMap.elements();
		  for (; enumWatch.hasMoreElements(); ) {
		    stopWatchList.add(enumWatch.nextElement());
		  }
		  return stopWatchList;
		}
	}

}
