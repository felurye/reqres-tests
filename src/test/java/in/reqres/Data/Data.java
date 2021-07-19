package in.reqres.Data;

import java.util.HashMap;
import java.util.Map;

public class Data {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map createUser() {
		Map<String, Object> params = new HashMap();

		params.put("name", "Daniele");
		params.put("job", "QA");

		return params;
	}
}
