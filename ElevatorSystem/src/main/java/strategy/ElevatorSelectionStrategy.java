package strategy;

import elevator.Elevator;
import entities.Request;

import java.util.List;
import java.util.Optional;

public interface ElevatorSelectionStrategy {
    Optional<Elevator> selectElevator(List<Elevator> elevators, Request request);
}