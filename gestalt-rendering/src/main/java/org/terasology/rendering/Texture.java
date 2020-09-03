package org.terasology.rendering;

public interface Texture {
    enum WrapMode {
        CLAMP,
        REPEAT
    }

    enum FilterMode {
        NEAREST,
        LINEAR
    }

    enum Type {
        TEXTURE2D,
        TEXTURE3D
    }

    WrapMode getWrapMode();

    FilterMode getFilterMod();

}
