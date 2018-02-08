package io.attendant;

import java.util.concurrent.Executor;

public class SameThreadAttendant extends Attendant {
    public SameThreadAttendant() {
        super(command -> command.run());
    }
}
