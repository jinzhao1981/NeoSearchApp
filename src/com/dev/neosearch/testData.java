package com.dev.neosearch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class testData {
	List<String> testJson = new ArrayList<String>();
	public int index =0;
	public static testData instance=null;
	
	public static testData getInstance() {
		if(instance ==null) {
            synchronized (testData.class) {
                if (instance == null) {
                    instance = new testData();
                }
            }
		}
		return instance;
	}
	
	public void init() {
		index = 0;

		testJson.add("{\"links\" :{\"next\":\"https://api.nasa.gov/neo/rest/v1/neo/browse?page=1&size=20&api_key=DEMO_KEY\"},\"page\":{\"size\":20,\"total_elements\":20113,\"total_pages\":1006,\"number\":0},\"near_earth_objects\":[{\"links\":{\"self\":\"https://api.nasa.gov/neo/rest/v1/neo/2021271?api_key=DEMO_KEY\"},\"neo_reference_id\":\"2021271\",\"name\":\"21271 (1996 TO5)\",\"designation\":\"21271\",\"estimated_diameter\":{\"miles\":{\"estimated_diameter_min\":0.9951898937,\"estimated_diameter_max\":1.2253122528}},\"close_approach_data\":[{\"miss_distance\":{\"miles\":\"1107023.25\"}},{\"miss_distance\":{\"miles\":\"1207023.25\"}}]}]}\n");  
		//testJson.add("{\"link\" :{\"next\":\"https://api.nasa.gov/neo/rest/v1/neo/browse?page=1&size=20&api_key=DEMO_KEY\"},\"page\":{\"size\":20,\"total_elements\":20113,\"total_pages\":1006,\"number\":0},\"near_earth_objects\":[{\"links\":{\"self\":\"https://api.nasa.gov/neo/rest/v1/neo/2021271?api_key=DEMO_KEY\"},\"neo_reference_id\":\"2021271\",\"name\":\"21271 (1996 TO5)\",\"designation\":\"21271\",\"estimated_diameter\":{\"miles\":{\"estimated_diameter_min\":0.9951898937,\"estimated_diameter_max\":1.2253122528}},\"close_approach_data\":[{\"miss_distance\":{\"miles\":\"1107023.25\"}},{\"miss_distance\":{\"miles\":\"1207023.25\"}}]}]}\n");  
		//testJson.add("{\"links\" :{\"next\"\"https://api.nasa.gov/neo/rest/v1/neo/browse?page=1&size=20&api_key=DEMO_KEY\"},\"page\":{\"size\":20,\"total_elements\":20113,\"total_pages\":1006,\"number\":0},\"near_earth_objects\":[{\"links\":{\"self\":\"https://api.nasa.gov/neo/rest/v1/neo/2021271?api_key=DEMO_KEY\"},\"neo_reference_id\":\"2021271\",\"name\":\"21271 (1996 TO5)\",\"designation\":\"21271\",\"estimated_diameter\":{\"miles\":{\"estimated_diameter_min\":0.9951898937,\"estimated_diameter_max\":1.2253122528}},\"close_approach_data\":[{\"miss_distance\":{\"miles\":\"1107023.25\"}},{\"miss_distance\":{\"miles\":\"1207023.25\"}}]}]}\n");  
		//testJson.add("\"links\" :{\"next\"\"https://api.nasa.gov/neo/rest/v1/neo/browse?page=1&size=20&api_key=DEMO_KEY\"},\"page\":{\"size\":20,\"total_elements\":20113,\"total_pages\":1006,\"number\":0},\"near_earth_objects\":[{\"links\":{\"self\":\"https://api.nasa.gov/neo/rest/v1/neo/2021271?api_key=DEMO_KEY\"},\"neo_reference_id\":\"2021271\",\"name\":\"21271 (1996 TO5)\",\"designation\":\"21271\",\"estimated_diameter\":{\"miles\":{\"estimated_diameter_min\":0.9951898937,\"estimated_diameter_max\":1.2253122528}},\"close_approach_data\":[{\"miss_distance\":{\"miles\":\"1107023.25\"}},{\"miss_distance\":{\"miles\":\"1207023.25\"}}]}]}\n");  
		
		//testJson.add("\"links\" :{\"next\":\"https://api.nasa.gov/neo/rest/v1/neo/browse?page=1&size=20&api_key=DEMO_KEY\"},\"page\":{\"size\":20,\"total_elements\":20113,\"total_pages\":1006,\"number\":0},\"near_earth_objects\":[{\"links\":{\"self\":\"https://api.nasa.gov/neo/rest/v1/neo/2021271?api_key=DEMO_KEY\"},\"neo_reference_id\":\"2021271\",\"name\":\"21271 (1996 TO5)\",\"designation\":\"21271\",\"estimated_diameter\":{\"miles\":{\"estimated_diameter_min\":0.9951898937,\"estimated_diameter_max\":1.2253122528}},\"close_approach_data\":[{\"miss_distance\":{\"miles\":\"1107023.25\"}},{\"miss_distance\":{\"miles\":\"1207023.25\"}}]}]}\n");  
		//testJson.add("{\"links\" :{\"next\":\"https://api.nasa.gov/neo/rest/v1/neo/browse?page=1&size=20&api_key=DEMO_KEY\"},\"page\"{\"size\":20,\"total_elements\":20113,\"total_pages\":1006,\"number\":0},\"near_earth_objects\":[{\"links\":{\"self\":\"https://api.nasa.gov/neo/rest/v1/neo/2021271?api_key=DEMO_KEY\"},\"neo_reference_id\":\"2021271\",\"name\":\"21271 (1996 TO5)\",\"designation\":\"21271\",\"estimated_diameter\":{\"miles\":{\"estimated_diameter_min\":0.9951898937,\"estimated_diameter_max\":1.2253122528}},\"close_approach_data\":[{\"miss_distance\":{\"miles\":\"1107023.25\"}},{\"miss_distance\":{\"miles\":\"1207023.25\"}}]}]}\n");  
		//testJson.add("{\"links\" :{\"next\":\"https://api.nasa.gov/neo/rest/v1/neo/browse?page=1&size=20&api_key=DEMO_KEY\"},\"page\":{\"size\":20,\"total_elements\":,\"total_pages\":1006,\"number\":0},\"near_earth_objects\":[{\"links\":{\"self\":\"https://api.nasa.gov/neo/rest/v1/neo/2021271?api_key=DEMO_KEY\"},\"neo_reference_id\":\"2021271\",\"name\":\"21271 (1996 TO5)\",\"designation\":\"21271\",\"estimated_diameter\":{\"miles\":{\"estimated_diameter_min\":0.9951898937,\"estimated_diameter_max\":1.2253122528}},\"close_approach_data\":[{\"miss_distance\":{\"miles\":\"1107023.25\"}},{\"miss_distance\":{\"miles\":\"1207023.25\"}}]}]}\n");  
		//testJson.add("{\"links\" :{\"next\":\"https://api.nasa.gov/neo/rest/v1/neo/browse?page=2&size=20&api_key=DEMO_KEY\"},\"page\":{\"size\":20,\"total_elements\":20113,\"total_pages\":1006,\"number\":0},\"near_earth_objects\":[{\"links\":{\"self\":\"https://api.nasa.gov/neo/rest/v1/neo/2021272?api_key=DEMO_KEY\"},\"neo_reference_id\":\"2021272\",\"name\":\"21272 (1996 TO5)\",\"designation\":\"21272\",\"estimated_diameter\":{\"miles\":{\"estimated_diameter_min\":1.9951898937,\"estimated_diameter_max\":2.2253122528}},\"close_approach_data\":[{\"miss_distance\":{\"miles\":\"2107023.25\"}},{\"miss_distance\":{\"miles\":\"2207023.25\"}}]}]}\n");  
		
		testJson.add("{\"links\" :{\"next\":\"https://api.nasa.gov/neo/rest/v1/neo/browse?page=2&size=20&api_key=DEMO_KEY\"},\"page\":{\"size\":20,\"total_elements\":20113,\"total_pages\":1006,\"number\":0},\"near_earth_objects\":[{\"links\":{\"self\":\"https://api.nasa.gov/neo/rest/v1/neo/2021272?api_key=DEMO_KEY\"},\"neo_reference_id\":\"2021272\",\"name\":\"21272 (1996 TO5)\",\"designation\":\"21272\",\"estimated_diameter\":{\"miles\":{\"estimated_diameter_min\":1.9951898937,\"estimated_diameter_max\":2.2253122528}},\"close_approach_data\":[{\"miss_distance\":{\"miles\":\"2107023.25\"}},{\"miss_distance\":{\"miles\":\"2207023.25\"}}]}]}\n");  
		testJson.add("{\"links\" :{\"next\":\"https://api.nasa.gov/neo/rest/v1/neo/browse?page=3&size=20&api_key=DEMO_KEY\"},\"page\":{\"size\":20,\"total_elements\":20113,\"total_pages\":1006,\"number\":0},\"near_earth_objects\":[{\"links\":{\"self\":\"https://api.nasa.gov/neo/rest/v1/neo/2021273?api_key=DEMO_KEY\"},\"neo_reference_id\":\"2021273\",\"name\":\"21273 (1996 TO5)\",\"designation\":\"21273\",\"estimated_diameter\":{\"miles\":{\"estimated_diameter_min\":2.9951898937,\"estimated_diameter_max\":3.2253122528}},\"close_approach_data\":[{\"miss_distance\":{\"miles\":\"3107023.25\"}},{\"miss_distance\":{\"miles\":\"3207023.25\"}}]}]}\n");  
		testJson.add("{\"links\" :{\"next\":\"https://api.nasa.gov/neo/rest/v1/neo/browse?page=4&size=20&api_key=DEMO_KEY\"},\"page\":{\"size\":20,\"total_elements\":20113,\"total_pages\":1006,\"number\":0},\"near_earth_objects\":[{\"links\":{\"self\":\"https://api.nasa.gov/neo/rest/v1/neo/2021274?api_key=DEMO_KEY\"},\"neo_reference_id\":\"2021274\",\"name\":\"21274 (1996 TO5)\",\"designation\":\"21274\",\"estimated_diameter\":{\"miles\":{\"estimated_diameter_min\":3.9951898937,\"estimated_diameter_max\":4.2253122528}},\"close_approach_data\":[{\"miss_distance\":{\"miles\":\"4107023.25\"}},{\"miss_distance\":{\"miles\":\"4207023.25\"}}]}]}\n");  
		//testJson.add("Retry");
		Collections.shuffle(testJson);
		testJson.add("{\"links\" :{\"self\":\"https://api.nasa.gov/neo/rest/v1/neo/browse?page=5&size=20&api_key=DEMO_KEY\"},\"page\":{\"size\":20,\"total_elements\":20113,\"total_pages\":1006,\"number\":0},\"near_earth_objects\":[{\"links\":{\"self\":\"https://api.nasa.gov/neo/rest/v1/neo/2021275?api_key=DEMO_KEY\"},\"neo_reference_id\":\"2021275\",\"name\":\"21275 (1996 TO5)\",\"designation\":\"21275\",\"estimated_diameter\":{\"miles\":{\"estimated_diameter_min\":4.9951898937,\"estimated_diameter_max\":5.2253122528}},\"close_approach_data\":[{\"miss_distance\":{\"miles\":\"5107023.25\"}},{\"miss_distance\":{\"miles\":\"5207023.25\"}}]}]}\n");  

	} 

	public String getData() {
		return testJson.get(index++%testJson.size());
	}
}
