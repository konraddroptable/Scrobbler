package scrobbler;

import data.ScrobblerData;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class Scrobbler {
    private final String key = "";
    private final String apiKey = "http://ws.audioscrobbler.com/2.0/?method=user.getTopTracks&api_key=" + key;
    private String userName;
    private int trackLimit;
    
    public Scrobbler(String userName, int trackLimit){
        this.userName = userName;
        this.trackLimit = trackLimit;
    }
    
    public Scrobbler(String userName){
        this.userName = userName;
        this.trackLimit = 10;
    }
    
    private String prepareUrl(String apiKey, String userName, int trackLimit){
        if(trackLimit < 10)
            trackLimit = 10;
        if(trackLimit > 3000)
            trackLimit = 3000;
        String url = apiKey + "&user=" + userName + "&limit=" + trackLimit;
        
        return url;
    }
    
    private Document downloadXmlFromWeb(String url){
        Document doc = null;
        try{
          doc = (Document) Jsoup.connect(url).timeout(20000).get(); 
        } catch(Exception e){
            e.printStackTrace();
        }
        
        return doc;
    }
    
    public ArrayList<ScrobblerData> downloadUserData(){
        ArrayList<ScrobblerData> result = new ArrayList<ScrobblerData>();
        
        try{
            String parsedUrl = prepareUrl(this.apiKey, this.userName, this.trackLimit);
            Document doc = downloadXmlFromWeb(parsedUrl);
            Elements elements = doc.select("track");

            for(int i = 0; i < elements.size(); i++){
                Element element = elements.get(i);

                String trackName = element.select("name").first().text();
                String artistName = element.select("artist").get(0).select("name").first().text();
                int duration = (int) Integer.parseInt(element.select("duration").first().text());
                int playCount = (int) Integer.parseInt(element.select("playcount").first().text());

                ScrobblerData data = new ScrobblerData(this.userName, trackName, artistName, duration, playCount, this.trackLimit);
                result.add(data);
            }  
        } catch(Exception e){
            e.printStackTrace();
        }
        
        return result;
    }
}
