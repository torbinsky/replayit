package services;

import java.io.File;

import play.libs.F.Promise;
import dto.ChunkInfo;
import dto.Featured;
import dto.GameDataChunk;
import dto.GameMeta;
import dto.GameStats;
import dto.KeyFrame;

public interface RiotSpectator {
    /**
     * Lists the featured games for the regions supported by this server.
     */
    Promise<Featured> featured();

    /**
     * Contains the current version for this Region.
     */
    Promise<String> version();

    /**
     * Returns information about the given game. This contains the games type and map, summoners involved, champions picked & banned, start time of the game and the encryption key required to read the replay data.
     */
    Promise<GameMeta> getGameMetaData(String platformId, String gameId);

    /**
     * Return some information about the last avaiable chunk:
     */
    Promise<ChunkInfo> getLastChunkInfo(String platformId, String gameId);

    /**
     * Contains data used for the statistics screen after a game.
     */
    Promise<GameStats> getEndOfGameStats(String platformId, String gameId);

    /**
     * Retrieves a chunk of data for the given game.
     */
    Promise<GameDataChunk> getGameDataChunk(String platformId, String gameId, String chunkId);

    /**
     * Retrieves a key frame for the given game.
     */
    Promise<KeyFrame> getKeyFrame(String platformId, String gameId, String keyFrameId);
    
    /**
     * Records a game in progress
     */
    Promise<File> recordGame(String platformId, String gameId);

    public enum Server {
        NA("North America", "NA1", "spectator.na1.lol.riotgames.com:80"),
        EUW("Europe West", "EUW1", "spectator.eu.lol.riotgames.com:8088"),
        EUNE("Europe Nordic & East", "EUN1", "spectator.eu.lol.riotgames.com:8088"),
        BR("Brazil", "BR1", "spectator.br.lol.riotgames.com:80"), 
        LAN("Latin America North", "LA1", "spectator.br.lol.riotgames.com:80"),
        LAS("Latin America South", "LA2", "spectator.br.lol.riotgames.com:80"), 
        RUS("Russia", "RU", "spectator.tr.lol.riotgames.com:80"), 
        TUR("Turkey", "TR1", "spectator.tr.lol.riotgames.com:80"),
        PBE("Public Beta Env.", "PBE1", "spectator.pbe1.lol.riotgames.com:8088"),
        SK("Korea", "KR", "QFKR1PROXY.kassad.in:80"),
        OCE("Oceania", "OC1", "qfsea1proxy.kassad.in:8088"),
        ;
        private final String connString;
        private final String name;
        private final String platformId;
        Server(String name, String platformId, String connString) {
            this.name = name;
            this.platformId = platformId;
            this.connString = connString;
        }
        
        public String getConnString() {
            return connString;
        }

        public String getName() {
            return name;
        }

        public String getPlatformId() {
            return platformId;
        }
    }

}
