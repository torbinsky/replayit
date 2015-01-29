package services;

import javax.annotation.Nullable;

import play.libs.F.Function;
import play.libs.F.Promise;
import play.libs.Json;
import play.libs.WS;
import play.libs.WS.Response;

import com.fasterxml.jackson.databind.JsonNode;

import dto.ChunkInfo;
import dto.Featured;
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
        String url = buildUrl(server, false, method, null, null, null, null);
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
        final String method = "getEndOfGameStats";
        String url = buildUrl(server, true, method, platformId, gameId, "null", null);
        return callMethod(url, GameStats.class);
    }

    @Override
    public Promise<GameDataChunk> getGameDataChunk(String platformId, String gameId, String chunkId) {
        final String method = "getGameMetaData";
        String url = buildUrl(server, true, method, platformId, gameId, chunkId, "token");
        return callMethod(url, GameDataChunk.class);
    }

    @Override
    public Promise<KeyFrame> getKeyFrame(String platformId, String gameId, String keyFrameId) {
        final String method = "getGameMetaData";
        String url = buildUrl(server, true, method, platformId, gameId, keyFrameId, "token");
        return callMethod(url, KeyFrame.class);
    }
    
    private static <T> Promise<T> callMethod(String url, final Class<T> clazz){
        System.out.println("DEBUG CALL: \n" + url);
        return WS.url(url).get().map(new Function<Response, T>(){
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
    
    public static void main(String[] args){
//        System.out.println(buildUrl(Server.NA, "featured", null, null, null));
        final RiotSpectatorImpl client = new RiotSpectatorImpl(Server.EUW);
//        Promise<Featured> result = client.featured();
//        final Featured featured = result.get();
//        final FeaturedGame featuredGame = featured.gameList.get(0);
//        GameMeta meta = client.getGameMetaData(featuredGame.platformId, featuredGame.gameId).get();
//        client.getKeyFrame(featuredGame.platformId, featuredGame.gameId, meta.lastKeyFrameId).get();
//        client.getGameDataChunk(featuredGame.platformId, featuredGame.gameId, meta.lastChunkId).get();
//        client.getLastChunkInfo(featuredGame.platformId, featuredGame.gameId).get();
        new RiotSpectatorImpl(Server.EUNE).getGameMetaData(Platform.EUN1.toString(), "1088444063").get();
        new RiotSpectatorImpl(Server.EUNE).getEndOfGameStats(Platform.EUN1.toString(), "1088444063").get();
        System.exit(0);
    }
    
}
