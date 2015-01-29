package dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public final class PendingAvailableChunkInfo {
    public String id;
    public long duration;
    public String receivedTime;
}