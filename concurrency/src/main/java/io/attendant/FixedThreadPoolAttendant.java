package io.attendant;

import java.util.concurrent.Executors;

public class FixedThreadPoolAttendant extends Attendant {

    public FixedThreadPoolAttendant() {
        super(Executors.newFixedThreadPool(2));
    }

}
