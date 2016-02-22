package transaction;

import entity.ArtistInfo;
import entity.ScrobblerDatabase;
import entity.UserInfo;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class Transaction {
    private EntityManagerFactory emf;
    private EntityManager em;
        
    
    
    public void disconnectDatabase(){
        if(em != null){
            this.em.close();
            System.out.println("Disconnected from the database");
        }
    }
    
    public void connectDatabase(){
        if(this.em == null || this.emf == null){
            this.emf = Persistence.createEntityManagerFactory("LastFmDatabaseConnection");
            this.em = emf.createEntityManager();
            System.out.println("Connected to the database");
        }
    }
    
    public Transaction(){
        this.emf = Persistence.createEntityManagerFactory("LastFmDatabaseConnection");
        this.em = emf.createEntityManager();
        System.out.println("Connected to the database"); 
    }
    
    public void save(ScrobblerDatabase scrobbler){
        EntityTransaction tx = em.getTransaction();

        try{
            tx.begin();
            em.persist(scrobbler);
            tx.commit();
        } catch(Exception e){
            System.out.println("Error during save(): " + e.getMessage() + "\n\nRollback transaction");
            tx.rollback();
        }
    }
    
    public ScrobblerDatabase[] load(){
        ScrobblerDatabase[] scrobbler = new ScrobblerDatabase[0];
        
        try{
            Query q = em.createQuery("SELECT DISTINCT s FROM Scrobbler as s");
            scrobbler = (ScrobblerDatabase[]) q.getResultList().toArray(new ScrobblerDatabase[0]);
            System.out.println("Loaded " + scrobbler.length + " rows from the database");
        } catch(Exception e){
            System.out.println("Error during load(): " + e.getMessage());
        }
        
        return scrobbler;
    }
    
    public UserInfo[] loadUserInfo(){
        UserInfo[] userInfo = new UserInfo[0];
        
        try{
            Query q = em.createQuery("SELECT s FROM user_info as s");
            userInfo = (UserInfo[]) q.getResultList().toArray(new UserInfo[0]);
            System.out.println("Loaded " + userInfo.length + " rows from the user_info view");
        } catch(Exception e){
            System.out.println("Error during loadUserInfo(): " + e.getMessage());
        }
        
        return userInfo;
    }
    
    public ArtistInfo[] loadArtistInfo(){
        ArtistInfo[] artistInfo = new ArtistInfo[0];
        
        try{
            Query q = em.createQuery("SELECT s FROM artist_info as s");
            artistInfo = (ArtistInfo[]) q.getResultList().toArray(new ArtistInfo[0]);
            System.out.println("Loaded " + artistInfo.length + " rows from the artist_info view");
        } catch(Exception e){
            System.out.println("Error during loadArtistInfo(): " + e.getMessage());
        }
        
        return artistInfo;
    }
}
