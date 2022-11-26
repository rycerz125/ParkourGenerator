package org.sentillo.gepard.jumps;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.sentillo.gepard.utils.BlockMatrix3d;
import org.sentillo.gepard.utils.Vector3d;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Jump extends BlockMatrix3d {
    @Getter
    private String name;
    @Getter
    private Vector3d start;
    @Getter
    private Vector3d stop;
}
