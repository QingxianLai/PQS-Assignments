package edu.nyu.pqs.stopwatch.impl;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import edu.nyu.pqs.stopwatch.api.IStopwatch;

/**
 * Thread Safe StopWatch implementing the IStopwatch interface
 *
 */
public class StopWatch implements IStopwatch {
  
  // clock time when the stop watch start, equals to -1 when the stop watch is not running.
  private long startTime;
  
  // record the time duration of each lap
  private LinkedList<Long> lapDuration;
  
  // begin with 0, then record the stop time of every lap
  private LinkedList<Long> lapStopTime;
  
  // Id that can be used as key to access stop watch instance.
  private final String uniqueID;
  
  // lock used to deal with multi-thread situation.
  private final Object lock;
  
  /**
   * constructor of the stopwatch
   * @param id a string used to represent this instance.
   */
  public StopWatch(String id) {
    uniqueID = id;
    startTime = -1;
    lock = new Object();
  }

  @Override
  public String getId() {
    return uniqueID;
  }

  @Override
  public void start() {
    synchronized(lock) {
      if (startTime!=-1) {
        throw new IllegalStateException();
      }
      lapDuration = new LinkedList<>();
      lapStopTime = new LinkedList<>();
      startTime = System.currentTimeMillis();
      lapStopTime.add(0L);
    }
  }

  @Override
  public void lap() {
    synchronized(lock) {
      if (startTime==-1) {
        throw new IllegalStateException();
      }
      long currentLapStop = System.currentTimeMillis() - startTime;
      long currentLapDuration = currentLapStop - lapStopTime.getLast();
      lapStopTime.add(currentLapStop);
      lapDuration.add(currentLapDuration);
    }
  }

  @Override
  public void stop() {
    synchronized(lock) {
      if (startTime==-1) {
        throw new IllegalStateException();
      }
      long stopTime = System.currentTimeMillis() - startTime;
      long lastLapDuration = stopTime - lapStopTime.getLast();
      lapStopTime.add(stopTime);
      lapDuration.add(lastLapDuration);
      startTime = -1;
    }
  }

  @Override
  public void reset() {
    synchronized(lock) {
      startTime = -1;
      lapDuration = new LinkedList<>();
      lapStopTime = new LinkedList<>();
    }
  }

  @Override
  public List<Long> getLapTimes() {
    synchronized(lock) {
      if (lapDuration.size()!=0) {
        return lapDuration;
      } else {
        return Collections.<Long>emptyList();
      }
    }
  }
  
  @Override
  public boolean equals(Object sw2) {
    if (this == sw2) {
      return true;
    }
    if (!(sw2 instanceof StopWatch)) {
      return false;
    }
    StopWatch stopWatch2 = (StopWatch) sw2;
    return this.getId()==stopWatch2.getId();
  }
  
  @Override
  public int hashCode() {
    int result = 17;
    for (int i=0; i < uniqueID.length(); i++) {
      result = 31*result + (int)uniqueID.charAt(i);
    }
    return result;
  }
  
  @Override
  public String toString() {
    return String.format("StopWatch: %s", uniqueID);
  }
}
