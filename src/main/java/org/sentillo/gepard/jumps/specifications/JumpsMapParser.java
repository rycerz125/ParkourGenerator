package org.sentillo.gepard.jumps.specifications;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.sentillo.gepard.jumps.jump.Jump;
import org.sentillo.gepard.jumps.jump.JumpService;
import org.sentillo.gepard.utils.TextParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JumpsMapParser extends TextParser<JumpsMap>{

    private JumpService jumpService;
    private Logger logger = LoggerFactory.getLogger(JumpsMapParser.class);

    public JumpsMapParser(JumpService jumpService){
        super("newspec");
        this.jumpService = jumpService;
    }

    @Override
    public JumpsMap parseOne(String code){
        JumpsMap.JumpsMapBuilder jumpsMap = JumpsMap.builder();
        List<JumpSpecification> jumps = new ArrayList<>();

        for(String codeLine : code.split("\n")){
            String[] args = codeLine.trim().split(" ");

            if(args[0].equals("newspec")){
                jumpsMap.name(args[1]);
            }
            if(args[0].equals("add")){
                Optional<Jump> jump = jumpService.get(args[1]);
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
