package engine.utils;

import org.lwjgl.opengl.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ShaderProgram {
    private int programId;

    public ShaderProgram(String vertexPath, String fragmentPath) throws IOException {
        int vertexShader = loadShader(vertexPath, GL20.GL_VERTEX_SHADER);
        int fragmentShader = loadShader(fragmentPath, GL20.GL_FRAGMENT_SHADER);
        
        programId = GL20.glCreateProgram();
        GL20.glAttachShader(programId, vertexShader);
        GL20.glAttachShader(programId, fragmentShader);
        GL20.glLinkProgram(programId);
        
        // Проверка ошибок
        if (GL20.glGetProgrami(programId, GL20.GL_LINK_STATUS) == GL11.GL_FALSE) {
            throw new RuntimeException("Shader linking failed: " + GL20.glGetProgramInfoLog(programId));
        }
        
        GL20.glDeleteShader(vertexShader);
        GL20.glDeleteShader(fragmentShader);
    }

    private int loadShader(String path, int type) throws IOException {
        String source = new String(Files.readAllBytes(Paths.get(path)));
        int shaderId = GL20.glCreateShader(type);
        GL20.glShaderSource(shaderId, source);
        GL20.glCompileShader(shaderId);
        
        if (GL20.glGetShaderi(shaderId, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {
            throw new RuntimeException("Shader compilation failed: " + GL20.glGetShaderInfoLog(shaderId));
        }
        return shaderId;
    }

    public void Use() {
        GL20.glUseProgram(programId);
    }
}