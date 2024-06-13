package com.example.inventory_service.services;

import com.demo.proto.inventory.*;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class InventoryService extends InventoryServiceGrpc.InventoryServiceImplBase {
    @Override
    public void getAvailableRooms(GetAvailableRoomsRequest request, StreamObserver<GetAvailableRoomsResponse> responseObserver) {
        responseObserver.onNext(GetAvailableRoomsResponse.newBuilder().build());
    }

    @Override
    public void reserveRooms(ReserveRoomsRequest request, StreamObserver<ReserveRoomsResponse> responseObserver) {
        var response = ReserveRoomsResponse.newBuilder()
                .setHotelId(request.getHotelId())
                .setType(request.getType())
                .setStartDate(request.getStartDate())
                .setEndDate(request.getEndDate())
                .setReserved(1)
                .setAvailable(22)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void undoReservation(UndoReserveRequest request, StreamObserver<UndoReserveResponse> responseObserver) {
        var response = UndoReserveResponse.newBuilder()
                .setHotelId(request.getHotelId())
                .setType(request.getType())
                .setStartDate(request.getStartDate())
                .setEndDate(request.getEndDate())
                .setAvailable(23)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
