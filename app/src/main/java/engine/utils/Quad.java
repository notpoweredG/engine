package engine.utils;

import org.lwjgl.opengl.*;

public class Quad {
    private int vaoId;
    private int vboId;

    public Quad(float posX, float posY, float transX, float transY) {
        // Вершины: X, Y, Z, S, T
        float[] vertices = {
            // Позиции       // Текстурные координаты
            posX - transX,  posY - transY, 0f, 0f, 1f, // Верхний левый
            posX + transX,  posY - transY, 0f, 1f, 1f, // Верхний правый
            posX + transX, posY + transY, 0f, 1f, 0f, // Нижний правый
            posX - transX, posY + transY, 0f, 0f, 0f  // Нижний левый
        };

        int[] indices = {
            0, 1, 2, // Первый треугольник
            2, 3, 0  // Второй треугольник
        };

        vaoId = GL30.glGenVertexArrays();
        GL30.glBindVertexArray(vaoId);

        // VBO для вершин
        vboId = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboId);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, vertices, GL15.GL_STATIC_DRAW);

        // EBO для индексов
        int eboId = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, eboId);
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, indices, GL15.GL_STATIC_DRAW);

        // Атрибуты вершин
        GL20.glVertexAttribPointer(0, 3, GL11.GL_FLOAT, false, 5 * Float.BYTES, 0);
        GL20.glEnableVertexAttribArray(0);
        
        // Атрибуты текстурных координат
        GL20.glVertexAttribPointer(1, 2, GL11.GL_FLOAT, false, 5 * Float.BYTES, 3 * Float.BYTES);
        GL20.glEnableVertexAttribArray(1);

        GL30.glBindVertexArray(0); // Отвязка VAO
    }

    public void Render() {
        GL30.glBindVertexArray(vaoId);
        GL11.glDrawElements(GL11.GL_TRIANGLES, 6, GL11.GL_UNSIGNED_INT, 0);
        GL30.glBindVertexArray(0);
    }
}