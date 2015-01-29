package dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public final class PendingAvailableKeyFrameInfo {
    public String id;
    public String nextChunkId;
    public String receivedTime;
}