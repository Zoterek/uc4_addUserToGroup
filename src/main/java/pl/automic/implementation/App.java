package pl.automic.implementation;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.uc4.api.UC4ObjectName;
import com.uc4.api.objects.User;
import com.uc4.api.systemoverview.UserListItem;
import com.uc4.communication.requests.UserList;

import pl.automic.communication.requests.OpenObject;

public class App extends pl.automic.App {

	public App(String[] args) throws IOException {
		super(args);
	}

	public static void main(String[] args) throws IOException {
		App app = new App(args);
		
		app.run();
		app.exit();
	}
	
	private void run() throws IOException {
		UserList userList = new UserList();
		automic.send(userList);
		
		StreamSupport.stream(userList.spliterator(), false).filter(e -> {
			if(input.getClient() == 0 || input.getClient() == e.getClient()) {
				String name = e.getName().getName().split("/")[0];
				
				return input.getUsers().contains(name);
			}
			
			return false;
		}).forEach(this::addUserToGroup);
		
	}
	
	private void addUserToGroup(UserListItem userListItem) {
		OpenObject open = new OpenObject(userListItem);
		try {
			automic.send(open);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		User user = (User) open.getUC4Object();
		List<UC4ObjectName> groups = input.getGroups().stream().map(e -> new UC4ObjectName(e)).collect(Collectors.toList());
		
		groups.forEach(user.userGroups()::addUserToGroup);
		
		automic.save(user);
		automic.close(user);
		
	}
}
