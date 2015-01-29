package replayit;

import org.junit.Test;

import dto.GameMeta;
import play.libs.Json;

public class GameMetaTests {
	@Test
	public void testGameMetaDeserialization(){
		String jsonString = "{\"gameKey\":{\"gameId\":1088444063,\"platformId\":\"EUN1\"},\"gameServerAddress\":\"\",\"port\":0,\"encryptionKey\":\"\",\"chunkTimeInterval\":30000,\"startTime\":\"Jan 28, 2015 9:12:13 PM\",\"endTime\":\"Jan 28, 2015 9:44:57 PM\",\"gameEnded\":true,\"lastChunkId\":71,\"lastKeyFrameId\":33,\"endStartupChunkId\":4,\"delayTime\":150000,\"pendingAvailableChunkInfo\":[{\"id\":64,\"duration\":29982,\"receivedTime\":\"Jan 28, 2015 9:41:43 PM\"},{\"id\":65,\"duration\":30016,\"receivedTime\":\"Jan 28, 2015 9:42:13 PM\"},{\"id\":66,\"duration\":29988,\"receivedTime\":\"Jan 28, 2015 9:42:43 PM\"},{\"id\":67,\"duration\":30003,\"receivedTime\":\"Jan 28, 2015 9:43:13 PM\"},{\"id\":68,\"duration\":29993,\"receivedTime\":\"Jan 28, 2015 9:43:43 PM\"},{\"id\":69,\"duration\":30019,\"receivedTime\":\"Jan 28, 2015 9:44:13 PM\"},{\"id\":70,\"duration\":29980,\"receivedTime\":\"Jan 28, 2015 9:44:43 PM\"},{\"id\":71,\"duration\":14028,\"receivedTime\":\"Jan 28, 2015 9:44:57 PM\"}],\"pendingAvailableKeyFrameInfo\":[{\"id\":30,\"receivedTime\":\"Jan 28, 2015 9:41:43 PM\",\"nextChunkId\":64},{\"id\":31,\"receivedTime\":\"Jan 28, 2015 9:42:43 PM\",\"nextChunkId\":66},{\"id\":32,\"receivedTime\":\"Jan 28, 2015 9:43:43 PM\",\"nextChunkId\":68},{\"id\":33,\"receivedTime\":\"Jan 28, 2015 9:44:43 PM\",\"nextChunkId\":70}],\"keyFrameTimeInterval\":60000,\"decodedEncryptionKey\":\"\",\"startGameChunkId\":6,\"gameLength\":1964023,\"clientAddedLag\":30000,\"clientBackFetchingEnabled\":false,\"clientBackFetchingFreq\":1000,\"interestScore\":2456,\"featuredGame\":true,\"createTime\":\"Jan 28, 2015 9:10:24 PM\",\"endGameChunkId\":71,\"endGameKeyFrameId\":33}";
		Json.fromJson(Json.parse(jsonString), GameMeta.class);
	}
}
