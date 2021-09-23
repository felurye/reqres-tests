package in.reqres.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPojo {
	private String name;
	private String job;
	private String id;
	private String createdAt;
}
