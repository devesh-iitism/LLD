package state.light;

import trafficsystem.TrafficLight;

public interface SignalState {
    void handle(TrafficLight context);
}