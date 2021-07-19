package in.reqres.Model;

public class UserModel {

	private String name;
	private String job;
	private String id;
	private String createdAt;

	public UserModel() {
	}
	
	public UserModel(String name, String email, String id, String createdAt) {
		this.name = name;
		this.job = email;
		this.id = id;
		this.createdAt = createdAt;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

}
