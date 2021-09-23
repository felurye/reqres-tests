package in.reqres.factories;

import com.github.javafaker.Faker;

import in.reqres.pojo.UserPojo;

public class UserDataFactory {
	final Faker faker = new Faker();

	public UserPojo user() {
		UserPojo user = new UserPojo();
		user.setName(faker.funnyName().name());
		user.setJob(faker.job().title());
		return user;
	}

	public UserPojo userWithCaracter() {
		UserPojo user = new UserPojo();
		user.setName(faker.name().name() + "*&$%'");
		user.setJob(faker.job().title() + "*&$%'");
		return user;
	}

	public UserPojo userWithoutName() {
		UserPojo user = new UserPojo();
		user.setJob(faker.job().title());
		return user;
	}

	public UserPojo userWithoutJob() {
		UserPojo user = new UserPojo();
		user.setJob(faker.job().title());
		return user;
	}
}
