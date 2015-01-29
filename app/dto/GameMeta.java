package dto;

import java.util.List;

public class GameMeta {
    public GameKey gameKey;
    public String gameServerAddress;
    public int port;
    public String encryptionKey;
    public long chunkTimeInterval;
    public String startTime;
    public boolean gameEnded;
    public String lastChunkId;
    public String lastKeyFrameId;
    public String endStartupChunkId;
    public long delayTime;
    public long keyFrameTimeInterval;
    public String decodedEncryptionKey;
    public String startGameChunkId;
    public long gameLength;
    public long clientAddedLag;
    public boolean clientBackFetchingEnabled;
    public long clientBackFetchingFreq;
    public int interestScore;
    public boolean featuredGame;
    public String createTime;
    public String endGameChunkId;
    public String endGameKeyFrameId;
    public List<PendingAvailableChunkInfo> pendingAvailableChunkInfo;
    public List<PendingAvailableKeyFrameInfo> pendingAvailableKeyFrameInfo;
}
