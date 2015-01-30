package dto;


public class KeyFrame {
    public byte[] frameBytes;
    public KeyFrame(byte[] chunkBytes) {
        this.frameBytes = chunkBytes;
    }
}
