package com.example.task_worker.activities.impl;

import com.demo.proto.reservation.CreateReservationRequest;
import com.demo.proto.reservation.ReservationServiceGrpc;
import com.example.task_worker.activities.ReservationActivity;
import com.example.task_worker.data.ReservationRequest;
import com.example.task_worker.data.ReservationResponse;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Component;

@Component
public class ReservationActivityImpl implements ReservationActivity {
    @GrpcClient("reservation-service")
    private ReservationServiceGrpc.ReservationServiceBlockingStub reservationServiceStub;

    @Override
    public ReservationResponse create(ReservationRequest request) {
        var createRequest = CreateReservationRequest.newBuilder()
                .setHotelId(request.getHotelId())
                .build();

        var reservationData = reservationServiceStub.create(createRequest);

        return new ReservationResponse(
                request.getHotelId(),
                reservationData.getReservationId()
        );
    }
}
