package org.terasology.rendering;

import org.joml.Matrix3fc;
import org.joml.Matrix4fc;
import org.joml.Vector2fc;
import org.joml.Vector3fc;
import org.joml.Vector4fc;

import java.nio.FloatBuffer;

public interface Material {

    void recompile();

    void enable();

    void setFloat(String name, float f, boolean currentOnly);

    void setFloat1(String name, FloatBuffer buffer, boolean currentOnly);

    void setFloat2(String name, float f1, float f2, boolean currentOnly);

    void setFloat2(String name, FloatBuffer buffer, boolean currentOnly);

    void setFloat3(String name, float f1, float f2, float f3, boolean currentOnly);

    void setFloat3(String name, FloatBuffer buffer, boolean currentOnly);

    void setFloat4(String name, float f1, float f2, float f3, float f4, boolean currentOnly);

    void setFloat4(String name, FloatBuffer buffer, boolean currentOnly);

    void setInt(String name, int i, boolean currentOnly);

    void setBoolean(String name, boolean value, boolean currentOnly);

    void setMatrix3(String name, Matrix3fc matrix, boolean currentOnly);

    void setMatrix3(String name, FloatBuffer buffer, boolean currentOnly);

    void setMatrix4(String name, Matrix4fc matrix, boolean currentOnly);

    void setMatrix4(String name, FloatBuffer buffer, boolean currentOnly);

    void setTexture(String name, Texture texture);

//    boolean supportsFeature(ShaderProgramFeature feature);
//
//    void activateFeature(ShaderProgramFeature feature);
//
//    void deactivateFeature(ShaderProgramFeature feature);
//
//    void deactivateFeatures(ShaderProgramFeature... features);

    void bindTextures();

    void setFloat(String name, float f);

    void setFloat1(String name, FloatBuffer buffer);

    void setFloat2(String name, float f1, float f2);

    void setFloat2(String name, Vector2fc value);

    void setFloat2(String name, Vector2fc value, boolean currentOnly);

    void setFloat2(String name, FloatBuffer buffer);

    void setFloat3(String name, float f1, float f2, float f3);

    void setFloat3(String name, Vector3fc value);

    void setFloat3(String name, Vector3fc value, boolean currentOnly);

    void setFloat3(String name, FloatBuffer buffer);

    void setFloat4(String name, float f1, float f2, float f3, float f4);

    void setFloat4(String name, Vector4fc value);

    void setFloat4(String name, Vector4fc value, boolean currentOnly);

    void setFloat4(String name, FloatBuffer buffer);

     void setInt(String name, int i);

     void setBoolean(String name, boolean value);

     void setMatrix3(String name, Matrix3fc matrix);

    void setMatrix3(String name, FloatBuffer buffer);

    void setMatrix4(String name, Matrix4fc matrix);

    void setMatrix4(String name, FloatBuffer buffer);
}
