package ejb;

import data.ArtistInfoData;
import data.ScrobblerData;
import data.UserInfoData;
import entity.ArtistInfo;
import java.util.ArrayList;
import javax.ejb.Stateless;
import scrobbler.Scrobbler;
import entity.ScrobblerDatabase;
import entity.UserInfo;
import transaction.Transaction;

@Stateless
public class ScrobblerSession implements ScrobblerSessionRemote {
    @Override
    public String hello(String name) {
        return "Hello " + name;
    }

    @Override
    public ArrayList<ScrobblerData> downloadUserData(String userName, int trackLimit) {
        Scrobbler scrobbler = new Scrobbler(userName, trackLimit);
        ArrayList<ScrobblerData> scrobblerData = scrobbler.downloadUserData();
        
        return scrobblerData;
    }

    @Override
    public void saveRecordToDatabase(ArrayList list) {
        ArrayList<ScrobblerData> sd = (ArrayList<ScrobblerData>) list;
        
        if(sd.size() > 0){
            Transaction transaction = new Transaction();
            
            for(int i = 0; i < sd.size(); i++){
                ScrobblerDatabase sdb = new ScrobblerDatabase(
                        sd.get(i).getUserName(), 
                        sd.get(i).getTrackName(),
                        sd.get(i).getArtistName(),
                        sd.get(i).getDuration(),
                        sd.get(i).getPlayCount(),
                        sd.get(i).getTimeStamp(),
                        sd.get(i).getTrackLimit());
                transaction.save(sdb);
            }
            
            System.out.println("Added " + sd.size() + " rows to the database");
            transaction.disconnectDatabase();
        }
    }

    @Override
    public ArrayList<ScrobblerData> loadRecordsFromDatabase() {
        ArrayList<ScrobblerData> sd = new ArrayList<>();
        Transaction transaction = new Transaction();
        ScrobblerDatabase[] sdb = transaction.load();
        
        for (ScrobblerDatabase sdbItem : sdb) {
            ScrobblerData scrobbler = new ScrobblerData(sdbItem.getUserName(), 
                                                        sdbItem.getTrackName(), 
                                                        sdbItem.getArtist(), 
                                                        sdbItem.getDuration(), 
                                                        sdbItem.getPlayCount(), 
                                                        sdbItem.getTimestamp(), 
                                                        sdbItem.getTrackLimit());
            sd.add(scrobbler);
        }
        
        transaction.disconnectDatabase();
        return sd;
    }   

    @Override
    public ArrayList<UserInfoData> loadUserInfo() {
        ArrayList<UserInfoData> userInfoData = new ArrayList<>();
        Transaction transaction = new Transaction();
        UserInfo[] ui = transaction.loadUserInfo();
        
        for(UserInfo item : ui){
            UserInfoData listItem = new UserInfoData(item.getId(), 
                                                item.getUserName(), 
                                                item.getTracksAvailable(), 
                                                item.getTotalTracksListened(), 
                                                item.getAvgPlayCount(), 
                                                item.getAvgTrackDuration(), 
                                                item.getTopArtist(), 
                                                item.getTopArtistListened());
            userInfoData.add(listItem);
        }
        
        transaction.disconnectDatabase();
        return userInfoData;
    }

    @Override
    public ArrayList<ArtistInfoData> loadArtistInfo() {
        ArrayList<ArtistInfoData> artistInfoData = new ArrayList<>();
        Transaction transaction = new Transaction();
        ArtistInfo[] ai = transaction.loadArtistInfo();
        
        for(ArtistInfo item : ai){
            ArtistInfoData listItem = new ArtistInfoData(item.getId(),
                                                        item.getArtist(),
                                                        item.getTrackCount(),
                                                        item.getTotalDuration(),
                                                        item.getAvgDuration(),
                                                        item.getTimesListened(),
                                                        item.getTopTrack(),
                                                        item.getTopTrackListened());
            artistInfoData.add(listItem);
        }
        
        transaction.disconnectDatabase();
        return artistInfoData;
    }
    
    
    
}
