package services;

import play.libs.F.Promise;
import dto.ChunkInfo;
import dto.Featured;
import dto.GameDataChunk;
import dto.GameMeta;
import dto.GameStats;
import dto.KeyFrame;

public interface RiotSpectator {
    Promise<Featured> featured();

    Promise<String> version();

    Promise<GameMeta> getGameMetaData(String platformId, String gameId);

    Promise<ChunkInfo> getLastChunkInfo(String platformId, String gameId);

    Promise<GameStats> getEndOfGameStats(String platformId, String gameId);

    Promise<GameDataChunk> getGameDataChunk(String platformId, String gameId, String chunkId);

    Promise<KeyFrame> getKeyFrame(String platformId, String gameId, String keyFrameId);

    public enum Server {
        NA("spectator.na.lol.riotgames.com"), EUW("spectator.eu.lol.riotgames.com:8088"), EUNE("spectator.eu.lol.riotgames.com:8088"), BR("spectator.br.lol.riotgames.com:80"), LAN(
                "spectator.br.lol.riotgames.com:80"), RUS("spectator.tr.lol.riotgames.com:80"), TUR("spectator.tr.lol.riotgames.com:80"), PBE("spectator.pbe1.lol.riotgames.com:8088"), SK(
                "QFKR1PROXY.kassad.in:8088"), TW("QFTW1PROXY.kassad.in:8088"), SEA("qfsea1proxy.kassad.in:8088"), ;
        private final String connString;

        Server(String connString) {
            this.connString = connString;
        }

        public String getConnString() {
            return connString;
        }

        @Override
        public String toString() {
            return connString;
        }

    }

    public enum Platform {
        NA("NA"), EUW1("EUW1"), EUN1("EUN1"), ;
        private final String id;

        Platform(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }

        @Override
        public String toString() {
            return id;
        }
    }

}
