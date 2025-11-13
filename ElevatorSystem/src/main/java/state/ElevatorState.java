package state;

import elevator.Elevator;
import enums.Direction;
import entities.Request;

public interface ElevatorState {
    void move(Elevator elevator);
    void addRequest(Elevator elevator, Request request);
    Direction getDirection();
}