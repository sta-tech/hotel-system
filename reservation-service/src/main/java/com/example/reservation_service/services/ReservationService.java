package com.example.reservation_service.services;

import com.demo.proto.reservation.CreateReservationRequest;
import com.demo.proto.reservation.CreateReservationResponse;
import com.demo.proto.reservation.ReservationServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.Random;

@GrpcService
public class ReservationService extends ReservationServiceGrpc.ReservationServiceImplBase {
    private Random rnd = new Random();

    @Override
    public void create(CreateReservationRequest request, StreamObserver<CreateReservationResponse> responseObserver) {
        var response = CreateReservationResponse.newBuilder()
                .setHotelId(request.getHotelId())
                .setReservationId(rnd.nextInt(1, 1000))
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
