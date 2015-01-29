package dto;

import java.util.List;

public class GameDataChunk {
    public long chunkTimeInterval;
    
    public long clientAddedLag;
    
    public boolean clientBackFetchingEnabled;
    
    public int clientBackFetchingFreq;
    
    public String createTime;
    
    public String decodedEncryptionKey;
    
    public long delayTime;
    
    public String encryptionKey;
    
    public String endGameChunkId;
    
    public String endGameKeyFrameId;
    
    public String endStartupChunkId;
    
    public boolean featuredGame;
    
    public boolean gameEnded;
    
    public GameKey gameKey;
    
    public long gameLength;
    
    public String gameServerAddress;
    
    public int interestScore;
    
    public long keyFrameTimeInterval;
    
    public String lastChunkId;
    
    public String lastKeyFrameId;
    
    public List<PendingAvailableChunkInfo> pendingAvailableChunkInfo;
    
    public List<PendingAvailableKeyFrameInfo> pendingAvailableKeyFrameInfo;
    
    public int port;
    
    public String startGameChunkId;
    
    public String startTime;
}
