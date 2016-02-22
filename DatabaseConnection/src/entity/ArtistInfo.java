package entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity(name = "artist_info")
public class ArtistInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "artist_id")
    private Long id;
    
    @Column(name = "artist")
    private String artist;
    @Column(name = "tracks_count")
    private int trackCount;
    @Column(name = "total_duration")
    private int totalDuration;
    @Column(name = "avg_duration")
    private int avgDuration;
    @Column(name = "times_listened")
    private int timesListened;
    @Column(name = "top_track")
    private String topTrack;
    @Column(name = "top_track_listened")
    private int topTrackListened;
    
    public ArtistInfo(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getArtist() {
        return artist;
    }

    public int getTrackCount() {
        return trackCount;
    }

    public int getTotalDuration() {
        return totalDuration;
    }

    public int getAvgDuration() {
        return avgDuration;
    }

    public int getTimesListened() {
        return timesListened;
    }

    public String getTopTrack() {
        return topTrack;
    }

    public int getTopTrackListened() {
        return topTrackListened;
    }
    
    @Override
    public String toString() {
        return "|" + id +
                "|" + artist +
                "|" + trackCount +
                "|" + totalDuration +
                "|" + avgDuration +
                "|" + timesListened +
                "|" + topTrack +
                "|" + topTrackListened;
    }
    
}
