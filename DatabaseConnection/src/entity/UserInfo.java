package entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity(name = "user_info")
public class UserInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "user_id")
    private Long id;
    
    @Column(name = "username")
    private String userName;
    @Column(name = "tracks_available")
    private int tracksAvailable;
    @Column(name = "total_tracks_listened")
    private int totalTracksListened;
    @Column(name = "avg_play_count")
    private int avgPlayCount;
    @Column(name = "avg_track_duration")
    private int avgTrackDuration;
    @Column(name = "top_artist")
    private String topArtist;
    @Column(name = "top_artist_listened")
    private int topArtistListened;

    public UserInfo(){
    }
    
    @Override
    public String toString() {
        return "|" + id + 
                "|" + userName + 
                "|" + tracksAvailable + 
                "|" + totalTracksListened + 
                "|" + avgPlayCount + 
                "|" + avgTrackDuration + 
                "|" + topArtist + 
                "|" + topArtistListened;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    //Get
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
