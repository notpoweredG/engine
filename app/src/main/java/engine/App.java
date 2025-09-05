package engine;

import engine.utils.*;
import engine.model.*;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;

import static org.lwjgl.glfw.GLFW.*;

import java.io.File;

public class App {
    private long window;
	
	private Story story;
	
	private Scene curentScene;
	
	private Frame curentFrame;
	
    public static void main(String[] args) {
        new App().run();
    }
	
    public void run() {
		try {
			ObjectMapper mapper = new ObjectMapper();
			
			File config = new File("src/main/resources/story.json");
			
			story = mapper.readValue(config, Story.class);
			
			System.out.println(story.toString());
			
			init();
			loop();
			cleanup();
		} catch (Exception e) {
			e.printStackTrace();
		}
        
    }
	
    private void init() {
        GLFWErrorCallback.createPrint(System.err).set();
        if (!GLFW.glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }
		
        // Создание окна
        window = GLFW.glfwCreateWindow(1000, 1000, story.getName(), 0, 0);
        if (window == 0) {
            throw new RuntimeException("Failed to create window");
        }
		
		glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
			System.out.println(window + " " + key + " " + scancode + " " + action + " " + mods);
			
			if ( key == 256 && action == 0 ) {
				glfwSetWindowShouldClose(window, true);
			}
			
			if ( key == 32 && action == 0) {
				System.out.println("Next frame or scene");
				
				if (curentScene.getFrames().indexOf(curentFrame) != curentScene.getFrames().size() - 1) {
					curentFrame = curentScene.getFrames().get(curentScene.getFrames().indexOf(curentFrame) + 1);
					
					curentFrame.getPersons().forEach((persone) -> persone.Init());
					
					System.out.println(curentFrame.toString());
				} else if(story.getScens().indexOf(curentScene) != story.getScens().size() - 1) {
					curentScene = story.getScens().get(story.getScens().indexOf(curentScene) + 1);
					
					curentScene.Init();
					
					curentFrame = curentScene.getFrames().get(0);

					curentFrame.getPersons().forEach((persone) -> persone.Init());
					
					System.out.println(curentScene.toString());
				} else {
					glfwSetWindowShouldClose(window, true);
				}
			}
		});
		
        GLFW.glfwMakeContextCurrent(window);
        GL.createCapabilities(); // Инициализация OpenGL
        GLFW.glfwSwapInterval(1); // VSync
		
        // Настройка OpenGL
        GL11.glClearColor(0.2f, 0.3f, 0.3f, 1.0f);
    }
	
	private void loop() {
		try{
			ShaderProgram shader = new ShaderProgram(
				"src/main/resources/shaders/vertex.glsl",
				"src/main/resources/shaders/fragment.glsl"
			);
			
			curentScene = story.getScens().get(0);
			curentFrame = curentScene.getFrames().get(0);
			
			curentScene.Init();
			curentFrame.getPersons().forEach((persone) -> persone.Init());
			
			System.out.println("Start Loop");
			
			while (!GLFW.glfwWindowShouldClose(window)) {
				GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
				
				shader.Use();
				
				curentScene.Render();
				curentFrame.getPersons().forEach((persone) -> persone.Render());
				
				GLFW.glfwSwapBuffers(window);
				GLFW.glfwPollEvents();
			}
		} catch(Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
    }
	
    private void cleanup() {
		
        GLFW.glfwDestroyWindow(window);
        GLFW.glfwTerminate();
    }
}
