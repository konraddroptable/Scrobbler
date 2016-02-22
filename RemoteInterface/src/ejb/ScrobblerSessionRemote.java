package ejb;

//import data.UserInfoData;
import java.util.ArrayList;
import javax.ejb.Remote;

@Remote
public interface ScrobblerSessionRemote {
    String hello(String name);
    ArrayList downloadUserData(String userName, int trackLimit);
    
    void saveRecordToDatabase(ArrayList list);
    ArrayList loadRecordsFromDatabase();
    ArrayList loadUserInfo();
    ArrayList loadArtistInfo();
}