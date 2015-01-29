package dto;

import java.util.List;

public class Featured {
    public List<FeaturedGame> gameList;
    public long clientRefreshInterval;
    
    public static final class FeaturedGame {
        public String gameId;
        public String mapId;
        public String gameMode;
        public String gameType;        
        public String platformId;
        public long gameStartTime;
        public int gameLength;
        public List<Participant> participants;
        public List<Observer> observers;
        public List<BannedChampion> bannedChampions;
    }
    
    public static final class Participant {
        public String teamId;
        public String spell1Id;
        public String spell2Id;
        public String teamParticipantId;
        public String championId;
        public int skinIndex;
        public String profileIconId;
        public String summonerName;
        public boolean bot;
    }
    
    public static final class Observer {
        public String encryptionKey;
    }
    
    public static final class BannedChampion {
        public String championId;
        public String teamId;
        public int pickTurn;
    }
}
