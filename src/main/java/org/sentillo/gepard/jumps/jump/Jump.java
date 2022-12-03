package org.sentillo.gepard.jumps.jump;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.sentillo.gepard.utils.BlockMatrix3d;
import org.sentillo.gepard.utils.Matrix3d;
import org.sentillo.gepard.utils.Vector3d;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Jump {
    @Getter
    private String name;

    @Getter
    @Builder.Default
    private Vector3d start = new Vector3d(0, 0, 0);

    @Getter
    private Vector3d stop;

    @Getter
    @Builder.Default
    private BlockMatrix3d visibleLayer = new BlockMatrix3d();

    @Getter
    @Builder.Default
    private Matrix3d<Boolean> mustEmptyLayer = new Matrix3d<>();
    
    @Getter
    @Builder.Default
    private Matrix3d<Boolean> couldEmptyLayer = new Matrix3d<>();

}
