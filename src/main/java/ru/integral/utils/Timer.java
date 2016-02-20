package ru.integral.utils;

/**
 * ========== Timer.java ==========
 * <p/>
 * $Revision: 19 $<br/>
 * $Author: NullPointer $<br/>
 * $HeadURL: file:///D:/work/local_repository/SAP_PROXY/trunk/src/main/java/ru/integral/utils/Timer.java $<br/>
 * $Id: Timer.java 19 2013-08-30 02:34:47Z NullPointer $
 * <p/>
 * 30.08.13 6:19: Original version (OOBUKHOV)<br/>
 */
public class Timer {
    private long start;

    private Timer() {
        reset();
    }

    /**
     * register start point for this timeCounter
     *
     * @return - timeCounter
     */
    public static Timer registerStart() {
        return new Timer();
    }

    /**
     * stop current Time Counter
     *
     * @return time in seconds
     */
    public double registerStop() {
        return ((System.currentTimeMillis() - this.start)/1000 );
    }

    /**
     * get start point for this timeCounter
     *
     * @return - start point timeCounter
     */
    public long getStart() {
        return start;
    }

    public void reset(){
        this.start = System.currentTimeMillis();
    }
}
