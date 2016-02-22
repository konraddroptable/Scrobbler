package data;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScrobblerData implements Serializable {
    private String userName;
    private String trackName;
    private String artistName;
    private int duration;
    private int playCount;
    private String timeStamp;
    private int trackLimit;
    
    
    public ScrobblerData(String userName, String trackName, String artistName, int duration, int playCount, int trackLimit){
        this.userName = userName;
        this.trackName = trackName;
        this.artistName = artistName;
        this.duration = duration;
        this.playCount = playCount;
        this.trackLimit = trackLimit;
        
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.timeStamp = sdf.format(date);
        
    }
    
    public ScrobblerData(String userName, String trackName, String artistName, int duration, int playCount, String timeStamp, int trackLimit){
        this.userName = userName;
        this.trackName = trackName;
        this.artistName = artistName;
        this.duration = duration;
        this.playCount = playCount;
        this.timeStamp = timeStamp;
        this.trackLimit = trackLimit;
        
    }
    
    public String getUserName() { return this.userName; }
    public String getTrackName(){ return this.trackName; }
    public String getArtistName(){ return this.artistName; }
    public int getDuration(){ return this.duration; }
    public int getPlayCount(){ return this.playCount; }
    public String getTimeStamp() { return this.timeStamp; }
    public int getTrackLimit() { return this.trackLimit; }
}
