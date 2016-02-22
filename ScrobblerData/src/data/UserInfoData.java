package data;

import java.io.Serializable;


public class UserInfoData implements Serializable {
    private Long id;
    private String userName;
    private int tracksAvailable;
    private int totalTracksListened;
    private int avgPlayCount;
    private int avgTrackDuration;
    private String topArtist;
    private int topArtistListened;

    public UserInfoData(Long id, String userName, int tracksAvailable, int totalTracksListened, int avgPlayCount, int avgTrackDuration, String topArtist, int topArtistListened) {
        this.id = id;
        this.userName = userName;
        this.tracksAvailable = tracksAvailable;
        this.totalTracksListened = totalTracksListened;
        this.avgPlayCount = avgPlayCount;
        this.avgTrackDuration = avgTrackDuration;
        this.topArtist = topArtist;
        this.topArtistListened = topArtistListened;
    } 
    
    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public int getTracksAvailable() {
        return tracksAvailable;
    }

    public int getTotalTracksListened() {
        return totalTracksListened;
    }

    public int getAvgPlayCount() {
        return avgPlayCount;
    }

    public int getAvgTrackDuration() {
        return avgTrackDuration;
    }

    public String getTopArtist() {
        return topArtist;
    }

    public int getTopArtistListened() {
        return topArtistListened;
    }
}
