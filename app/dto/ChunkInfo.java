package dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ChunkInfo {
    public long availableSince;
    
    public String chunkId;
    
    public long duration;
    
    public String endGameChunkId;
    
    public String endStartupChunkId;
    
    public String keyFrameId;
    
    public long nextAvailableChunk;
    
    public String nextChunkId;
    
    public String startGameChunkId;    
}
