package com.example.inventory_service.services;

import com.demo.proto.inventory.GetAvailableRoomsRequest;
import com.demo.proto.inventory.GetAvailableRoomsResponse;
import com.demo.proto.inventory.InventoryServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class InventoryService extends InventoryServiceGrpc.InventoryServiceImplBase {
    @Override
    public void getAvailableRooms(GetAvailableRoomsRequest request, StreamObserver<GetAvailableRoomsResponse> responseObserver) {
        responseObserver.onNext(GetAvailableRoomsResponse.newBuilder().build());
    }
}
