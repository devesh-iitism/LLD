package observer;

import elevator.Elevator;

public interface ElevatorObserver {
    void update(Elevator elevator);
}