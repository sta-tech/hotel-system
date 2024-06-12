package com.example.task_worker.activities.impl;

import com.demo.proto.inventory.InventoryServiceGrpc;
import com.demo.proto.inventory.ReserveRoomsRequest;
import com.example.task_worker.activities.InventoryActivity;
import com.example.task_worker.data.InventoryRequest;
import com.example.task_worker.data.InventoryResponse;
import com.google.protobuf.Timestamp;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

@Component
public class InventoryActivityImpl implements InventoryActivity {
    @GrpcClient("inventory-service")
    private InventoryServiceGrpc.InventoryServiceBlockingStub inventoryServiceStub;

    @Override
    public InventoryResponse reserve(InventoryRequest reservationRequest) {
        var reserveRequest = ReserveRoomsRequest.newBuilder()
                .setHotelId(reservationRequest.getHotelId())
                .setType(reservationRequest.getRoomTypeId())
                .setStartDate(getTimestamp(reservationRequest.getStartDate()))
                .setEndDate(getTimestamp(reservationRequest.getEndDate()))
                .build();

        var inventoryData = inventoryServiceStub.reserveRooms(reserveRequest);

        return new InventoryResponse(
                reservationRequest.getHotelId(),
                reservationRequest.getRoomTypeId(),
                reservationRequest.getStartDate(),
                reservationRequest.getEndDate(),
                inventoryData.getReserved(),
                inventoryData.getAvailable()
        );
    }

    private static Timestamp getTimestamp(OffsetDateTime date) {
        var i = date.toInstant();

        return Timestamp.newBuilder()
                .setSeconds(i.getEpochSecond())
                .setNanos(i.getNano())
                .build();
    }
}
