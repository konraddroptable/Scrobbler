package entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity(name = "scrobbler")
public class ScrobblerDatabase implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "Username")
    private String userName;
    @Column(name = "Track")
    private String trackName;
    @Column(name = "Artist")
    private String artist;
    @Column(name = "Duration")
    private int duration;
    @Column(name = "Play_Count")
    private int playCount;
    @Column(name = "Timestamp")
    private String timestamp;
    @Column(name = "Track_Limit")
    private int trackLimit;
    
    public ScrobblerDatabase(){
        
    }
    
    public ScrobblerDatabase(String userName, String trackName, String artist, int duration, int playCount, String timestamp, int trackLimit){
        this.userName = userName;
        this.trackName = trackName;
        this.artist = artist;
        this.duration = duration;
        this.playCount = playCount;
        this.timestamp = timestamp;
        this.trackLimit = trackLimit;
    }
    
//    public Scrobbler(Long id){
//        this.id = id;
//    }

    
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }

//    @Override
//    public int hashCode() {
//        int hash = 0;
//        hash += (id != null ? id.hashCode() : 0);
//        return hash;
//    }
//
//    @Override
//    public boolean equals(Object object) {
//        // TODO: Warning - this method won't work in the case the id fields are not set
//        if (!(object instanceof Scrobbler)) {
//            return false;
//        }
//        Scrobbler other = (Scrobbler) object;
//        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
//            return false;
//        }
//        return true;
//    }

//    @Override
//    public String toString() {
//        return "|" + id + "|" + userName + "|" + trackName + "|" + artist + "|" + duration + "|" + playCount + "|" + timestamp + "|" + trackLimit;
//    }

    @Override
    public String toString() {
        return "Scrobbler [id=" + id + 
                ", Username=" + userName + 
                ", Track=" + trackName + 
                ", Artist=" + artist + 
                ", Duration=" + duration + 
                ", Play_Count=" + playCount + 
                ", Timestamp=" + timestamp + 
                ", Track_Limit=" + trackLimit + "]";
    }
    
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    //Get
    public String getUserName() {
        return this.userName;
    }

    public String getTrackName() {
        return this.trackName;
    }

    public String getArtist() {
        return this.artist;
    }

    public int getDuration() {
        return this.duration;
    }

    public int getPlayCount() {
        return this.playCount;
    }

    public String getTimestamp() {
        return this.timestamp;
    }

    public int getTrackLimit() {
        return this.trackLimit;
    }
}
