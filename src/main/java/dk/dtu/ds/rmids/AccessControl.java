/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.dtu.ds.rmids;

/**
 *
 * @author Anders, Steen & Christoffer
 */
public class AccessControl 
{
    private String username;
    private boolean print, queue, topQueue, start, stop, restart, status, readConfig, setConfig;
    
    public AccessControl(String username, boolean print, boolean queue, boolean topQueue, boolean start, boolean stop, boolean restart, boolean status, boolean readConfig, boolean setConfig)
    {
        this.username = username;
        this.print = print;
        this.queue = queue;
        this.topQueue = topQueue;
        this.start = start;
        this.stop = stop;
        this.restart = restart;
        this.status = status;
        this.readConfig = readConfig;
        this.setConfig = setConfig;       
    } 

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the print
     */
    public boolean isPrint() {
        return print;
    }

    /**
     * @param print the print to set
     */
    public void setPrint(boolean print) {
        this.print = print;
    }

    /**
     * @return the queue
     */
    public boolean isQueue() {
        return queue;
    }

    /**
     * @param queue the queue to set
     */
    public void setQueue(boolean queue) {
        this.queue = queue;
    }

    /**
     * @return the topQueue
     */
    public boolean isTopQueue() {
        return topQueue;
    }

    /**
     * @param topQueue the topQueue to set
     */
    public void setTopQueue(boolean topQueue) {
        this.topQueue = topQueue;
    }

    /**
     * @return the start
     */
    public boolean isStart() {
        return start;
    }

    /**
     * @param start the start to set
     */
    public void setStart(boolean start) {
        this.start = start;
    }

    /**
     * @return the stop
     */
    public boolean isStop() {
        return stop;
    }

    /**
     * @param stop the stop to set
     */
    public void setStop(boolean stop) {
        this.stop = stop;
    }

    /**
     * @return the restart
     */
    public boolean isRestart() {
        return restart;
    }

    /**
     * @param restart the restart to set
     */
    public void setRestart(boolean restart) {
        this.restart = restart;
    }

    /**
     * @return the status
     */
    public boolean isStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

    /**
     * @return the readConfig
     */
    public boolean isReadConfig() {
        return readConfig;
    }

    /**
     * @param readConfig the readConfig to set
     */
    public void setReadConfig(boolean readConfig) {
        this.readConfig = readConfig;
    }

    /**
     * @return the setConfig
     */
    public boolean isSetConfig() {
        return setConfig;
    }

    /**
     * @param setConfig the setConfig to set
     */
    public void setSetConfig(boolean setConfig) {
        this.setConfig = setConfig;
    }
}
