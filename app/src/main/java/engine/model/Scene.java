package engine.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import engine.utils.Quad;
import engine.utils.Texture;

public class Scene {
    private String name;

    private String BackgroundUrl;

    private List<Frame> Frames;
    
    private Texture Texture;
    
    private Quad Quad;

    public Scene() {
    }

    @JsonCreator
    public Scene(@JsonProperty("name") String name, @JsonProperty("backgroundurl") String background, @JsonProperty("frames") List<Frame> frames) {
        this.name = name;
        this.BackgroundUrl = "src/main/resources/textures/" + background;
        Frames = frames;
    }
    
    public void Init() {
        Texture = new Texture(BackgroundUrl);
        
        Quad = new Quad(0, 0, 1.0f, 1.0f);
    }
    
    public void Render() {
        Texture.Bind();
        
        Quad.Render();
    }

    public String framesToString() {
        String str = "";

        for (Frame frame : getFrames()) {
            str += frame.toString();
        }

        return str;
    }

    @Override
    public String toString(){
        return "\n\tName: " + getName() + "\n\tBackground: " + getBackgroundUrl() + "\n\tFrames: " + framesToString() + "\n";
    }

    public String getName() {
        return name;
    }

    public String getBackgroundUrl() {
        return BackgroundUrl;
    }

    public List<Frame> getFrames() {
        return Frames;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((BackgroundUrl == null) ? 0 : BackgroundUrl.hashCode());
        result = prime * result + ((Frames == null) ? 0 : Frames.hashCode());
        result = prime * result + ((Texture == null) ? 0 : Texture.hashCode());
        result = prime * result + ((Quad == null) ? 0 : Quad.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Scene other = (Scene) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (BackgroundUrl == null) {
            if (other.BackgroundUrl != null)
                return false;
        } else if (!BackgroundUrl.equals(other.BackgroundUrl))
            return false;
        if (Frames == null) {
            if (other.Frames != null)
                return false;
        } else if (!Frames.equals(other.Frames))
            return false;
        if (Texture == null) {
            if (other.Texture != null)
                return false;
        } else if (!Texture.equals(other.Texture))
            return false;
        if (Quad == null) {
            if (other.Quad != null)
                return false;
        } else if (!Quad.equals(other.Quad))
            return false;
        return true;
    }
    
    
}
