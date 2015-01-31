package services;

import java.io.File;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.annotation.Nullable;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import play.libs.F.Function;
import play.libs.F.Promise;
import play.libs.Json;
import play.libs.WS;
import play.libs.WS.Response;

import com.fasterxml.jackson.databind.JsonNode;
import com.ning.http.util.Base64;

import dto.ChunkInfo;
import dto.Featured;
import dto.Featured.FeaturedGame;
import dto.GameDataChunk;
import dto.GameMeta;
import dto.GameStats;
import dto.KeyFrame;

public class RiotSpectatorImpl implements RiotSpectator {
    private Server server;

    public RiotSpectatorImpl(Server server){
        this.server = server;        
    }

    @Override
    public Promise<Featured> featured() {
        final String method = "featured";
        String url = buildUrl(server, false, method, null, null, null, null);
        return callMethod(url, Featured.class);
    }

    @Override
    public Promise<String> version() {
        final String method = "version";
        String url = buildUrl(server, true, method, null, null, null, null);
        System.out.println(url);
        return callMethod(url, String.class);
    }

    @Override
    public Promise<GameMeta> getGameMetaData(String platformId, String gameId) {
        final String method = "getGameMetaData";
        String url = buildUrl(server, true, method, platformId, gameId, "1", "token");
        return callMethod(url, GameMeta.class);
    }

    @Override
    public Promise<ChunkInfo> getLastChunkInfo(String platformId, String gameId) {
        final String method = "getLastChunkInfo";
        String url = buildUrl(server, true, method, platformId, gameId, "1", "token");
        return callMethod(url, ChunkInfo.class);
    }

    @Override
    public Promise<GameStats> getEndOfGameStats(String platformId, String gameId) {
        final String method = "endOfGameStats";
        String url = buildUrl(server, true, method, platformId, gameId, "null", null);
        return callMethod(url, GameStats.class);
    }

    @Override
    public Promise<GameDataChunk> getGameDataChunk(String platformId, String gameId, String chunkId) {
        final String method = "getGameDataChunk";
        String url = buildUrl(server, true, method, platformId, gameId, chunkId, "token");
        return callMethod(url).map(new Function<Response, GameDataChunk>(){
            @Override
            public GameDataChunk apply(Response a) throws Throwable {
                if(a.getStatus() < 300 && a.getStatus() > 199){
                    GameDataChunk gdc = new GameDataChunk(a.asByteArray());
                    return gdc;
                }
                System.out.println("Encountered error: " + a.getBody());
                // TODO: Handle ERRORS
                return null;
            }            
        });
    }

    @Override
    public Promise<KeyFrame> getKeyFrame(String platformId, String gameId, String keyFrameId) {
        final String method = "getKeyFrame";
        String url = buildUrl(server, true, method, platformId, gameId, keyFrameId, "token");
        return callMethod(url).map(new Function<Response, KeyFrame>(){
            @Override
            public KeyFrame apply(Response a) throws Throwable {
                if(a.getStatus() < 300 && a.getStatus() > 199){
                    KeyFrame kf = new KeyFrame(a.asByteArray());
                    return kf;
                }
                System.out.println("Encountered error: " + a.getBody());
                // TODO: Handle ERRORS
                return null;
            }            
        });
    }

    @Override
    public Promise<File> recordGame(String platformId, String gameId) {
        return getGameMetaData(platformId, gameId).flatMap(new Function<GameMeta, Promise<File>>(){
            @Override
            public Promise<File> apply(GameMeta gameMeta) throws Throwable {
                System.out.println("KEY: " + getEncryptionKey(gameMeta));
                return null;
            }
        });
    }
    
    private static String getEncryptionKey(GameMeta meta) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException{
        if(meta.encryptionKey == null || meta.encryptionKey.isEmpty()){
            return "";
        }
        
        byte[] keyBytes = Base64.decode(meta.encryptionKey);
        Cipher cipher = Cipher.getInstance("Blowfish");
        SecretKeySpec key = new SecretKeySpec(keyBytes, "Blowfish");
        cipher.init(Cipher.DECRYPT_MODE, key);
        
        return Base64.encode(cipher.doFinal(meta.gameKey.gameId.getBytes()));
    }
    
    private static Promise<Response> callMethod(String url){
        System.out.println("DEBUG CALL: \n" + url);
        return WS.url(url).get();
    }
    
    private static <T> Promise<T> callMethod(String url, final Class<T> clazz){
        return callMethod(url).map(new Function<Response, T>(){
            @SuppressWarnings("unchecked")
            @Override
            public T apply(Response a) throws Throwable {
                System.out.println("DEBUG RESPONSE: \n" + a.getBody());
                // Handle OK
                if(a.getStatus() > 199 && a.getStatus() < 300){                    
                    if(clazz.equals(String.class)){
                        return (T)a.getBody();
                    }else{
                        final JsonNode json = a.asJson();
                        return Json.fromJson(json, clazz);
                    }
                }
                // ERROR
                // TODO: Handle this case
                System.out.println("\n\nERROR!!!!!!!!!!!!!\n\n");
                return null;
            }            
        });
    }
    
    private static String buildUrl(Server server, boolean consumer, String method, @Nullable String platformId, @Nullable String gameId, @Nullable String param, String token){
        StringBuilder sb = new StringBuilder("http://");
        sb.append(server.getConnString());
        sb.append("/observer-mode/rest/");
        if(consumer){
            sb.append("consumer/");
        }
        sb.append(method).append("/");
        
        if(platformId != null){
            sb.append(platformId).append("/");
        }
        
        if(gameId != null){
            sb.append(gameId).append("/");
        }
        
        if(param != null){
            sb.append(param).append("/");
        }
        
        if(token != null){
            sb.append(token);
        }
        
        return sb.toString();
    }
    
    @SuppressWarnings("deprecation")
    public static void main(String[] args) throws InterruptedException{
//        for(Server server : Server.values()){
//            final RiotSpectatorImpl client = new RiotSpectatorImpl(server);
//            System.out.println("Server " + server.getName() + " is at version: " + client.version().get());
//        }
        final RiotSpectatorImpl client = new RiotSpectatorImpl(Server.EUW);
        Promise<Featured> result = client.featured();
        final Featured featured = result.get();
        final FeaturedGame featuredGame = featured.gameList.get(0);
        client.recordGame(featuredGame.platformId, featuredGame.gameId).get();
//        GameMeta meta = client.getGameMetaData(featuredGame.platformId, featuredGame.gameId).get();
//        System.out.println(Json.toJson(meta).toString());
        System.exit(0);
    }
}
