package data;

import java.io.Serializable;

public class ArtistInfoData implements Serializable{
    private Long id;
    private String artist;
    private int trackCount;
    private int totalDuration;
    private int avgDuration;
    private int timesListened;
    private String topTrack;
    private int topTrackListened;

    public ArtistInfoData(Long id, String artist, int trackCount, int totalDuration, int avgDuration, int timesListened, String topTrack, int topTrackListened) {
        this.id = id;
        this.artist = artist;
        this.trackCount = trackCount;
        this.totalDuration = totalDuration;
        this.avgDuration = avgDuration;
        this.timesListened = timesListened;
        this.topTrack = topTrack;
        this.topTrackListened = topTrackListened;
    }

    
    public Long getId() {
        return id;
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
    
    
}
