package org.sentillo.gepard.jumps;

import java.util.HashMap;
import java.util.Optional;

class JumpService {

    private HashMap<String, Jump> jumps = new HashMap<>();

    public Optional<Jump> giveJump(String jump) {
        return Optional.ofNullable(jumps.get(jump));
    }

    public void addJump(Jump jump) {
        jumps.put(jump.getName(), jump);
    }

}
