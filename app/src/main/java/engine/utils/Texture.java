package engine.utils;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import org.lwjgl.opengl.*;
import org.lwjgl.stb.*;
import org.lwjgl.system.*;

public class Texture {
    private int textureId;

    public Texture(String path) {
        textureId = GL11.glGenTextures();
        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureId);

        // Настройка параметров текстуры
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL11.GL_REPEAT);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_REPEAT);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);

        // Загрузка изображения через STB
        try (MemoryStack stack = MemoryStack.stackPush()) {
            IntBuffer width = stack.mallocInt(1);
            IntBuffer height = stack.mallocInt(1);
            IntBuffer channels = stack.mallocInt(1);
            
            ByteBuffer image = STBImage.stbi_load(path, width, height, channels, 4);
            if (image == null) {
                throw new RuntimeException("Failed to load texture: " + STBImage.stbi_failure_reason());
            }

            GL11.glTexImage2D(
                GL11.GL_TEXTURE_2D, 
                0, 
                GL11.GL_RGBA, 
                width.get(), 
                height.get(), 
                0, 
                GL11.GL_RGBA, 
                GL11.GL_UNSIGNED_BYTE, 
                image
            );
            
            STBImage.stbi_image_free(image);
        }
    }

    public void Bind() {
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureId);
    }
}