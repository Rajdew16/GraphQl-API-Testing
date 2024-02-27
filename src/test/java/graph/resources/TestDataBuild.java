package graph.resources;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuild {

	public static String mutationPayload() {
		return "{\r\n"
				+ "  \"query\": \"mutation($locationId:Int!,$editLocationName:String){\\n  createLocation(location:{name:\\\"Pochinki\\\" type:\\\"CenterZone\\\" dimension:\\\"8x8\\\"}){\\n    id\\n  }\\n  editLocation(locationId:$locationId, newLocationData:{name:$editLocationName} ){\\n    status\\n  }\\n  createEpisode(episode:{name:\\\"Pubg\\\" air_date:\\\"10/10/2023\\\" episode:\\\"Team got AWM\\\"}){\\n    id\\n  }\\n  createCharacter(character:{name:\\\"Asta\\\" type:\\\"Strong\\\" status:\\\"alive\\\" species:\\\"white boy\\\" gender:\\\"male\\\" image:\\\"na\\\" originId:$locationId locationId:$locationId}){\\n    id\\n  }\\n}\",\r\n"
				+ "  \"variables\": {\r\n"
				+ "    \"locationId\": 7095,\r\n"
				+ "    \"editLocationName\": \"Yasnaya Poliyana\"\r\n"
				+ "  }\r\n"
				+ "}";
	}
	
	public static String queryPayload(int locId , int charId , List<Integer> epiIds) {
		return "{\r\n"
				+ "  \"query\": \"query($locationId:Int!, $characterId:Int!, $multiEpiIds:[Int!]!){\\n  location(locationId:$locationId){\\n    name\\n    type\\n    dimension\\n    created\\n  }\\n  character(characterId:$characterId){\\n    name\\n    type\\n    status\\n    species\\n  }\\n  episodesByIds(ids:$multiEpiIds){\\n    name\\n    air_date\\n    episode\\n  }\\n}\",\r\n"
				+ "  \"variables\": {\r\n"
				+ "    \"locationId\": "+locId+",\r\n"
				+ "    \"characterId\": "+charId+",\r\n"
				+ "    \"multiEpiIds\": "+epiIds+" \r\n"
				+ " }\r\n"
				+ "}";
	}
}
