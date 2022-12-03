package org.sentillo.gepard.jumps.specifications;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.sentillo.gepard.jumps.jump.Jump;
import org.sentillo.gepard.jumps.jump.JumpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JumpsMapParser {

    private JumpService jumpService;
    private Logger logger = LoggerFactory.getLogger(JumpsMapParser.class);

    public JumpsMapParser(JumpService jumpService){
        this.jumpService = jumpService;
    }

    public List<JumpsMap> parse(String code){
        String[] codes = code.trim().split("newspec.");
        List<JumpsMap> specifications = new ArrayList<>();

        for(String c : codes){
            c = c.trim();
            if(!c.equals(""))
            specifications.add(parseOne("newspec " + c));
        }

        return specifications;
    }

    public JumpsMap parseOne(String code){
        JumpsMap.JumpsMapBuilder jumpsMap = JumpsMap.builder();
        List<JumpSpecification> jumps = new ArrayList<>();

        for(String codeLine : code.split("\n")){
            String[] args = codeLine.trim().split(" ");

            if(args[0].equals("newspec")){
                jumpsMap.name(args[1]);
            }
            if(args[0].equals("add")){
                Optional<Jump> jump = jumpService.giveJump(args[1]);
                if(!jump.isPresent()){
                    logger.error("Jump with name \""+args[1]+"\" was not found!");
                    continue;
                }

                jumps.add(
                    JumpSpecification.builder()
                    .jump(jump.get())
                    .build()
                    );
            }
        }

        jumpsMap.jumpsSpecifications(jumps);
        return jumpsMap.build();
    }
}
