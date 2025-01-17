package org.igooo.grpc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import io.grpc.stub.StreamObserver;

import org.springframework.grpc.server.service.GrpcService;

@GrpcService
public class UserService extends UserManagementGrpc.UserManagementImplBase {
	private final AtomicInteger counter = new AtomicInteger(0);

	private final List<UserInfo> users = new ArrayList<>();

	@Override
	public void createUser(User.UserRequest request, StreamObserver<User.UserResponse> responseObserver) {
		var userInfo = new UserInfo(this.counter.incrementAndGet(), request.getName(), request.getAge());
		this.users.add(userInfo);

		responseObserver.onNext(User.UserResponse.newBuilder().setId(userInfo.id()).setName(userInfo.name()).setAge(userInfo.age()).build());
		responseObserver.onCompleted();
	}

	@Override
	public void findUsers(User.Empty request, StreamObserver<User.UserResponse> responseObserver) {
		users.forEach(u -> responseObserver.onNext(User.UserResponse.newBuilder().setId(u.id()).setName(u.name()).setAge(u.age()).build()));
		responseObserver.onCompleted();
	}

	record UserInfo(int id, String name, int age) {

	}
}
