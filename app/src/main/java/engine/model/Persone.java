package engine.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import engine.utils.Quad;
import engine.utils.Texture;

public class Persone {
    private final String name;

    private final String TextureUrl;

    private final float TransX;
    private final float TransY;

    private final float PosX;
    private final float PosY;

    private Quad Quad;
    private Texture Texture;

    public void Render() {
        this.Texture.Bind();
    this.Quad.Render();
    }

    @JsonCreator
    public Persone(@JsonProperty("name") String name, @JsonProperty("textureurl") String texture, @JsonProperty("transx") float transX, @JsonProperty("transy") float transY, @JsonProperty("posx") float posX, @JsonProperty("posy") float posY) {
        this.name = name;
        this.TextureUrl = "src/main/resources/textures/" + texture;
        this.TransX = transX;
        this.TransY = transY;
        this.PosX = posX;
        this.PosY = posY;
    }

    public void Init() {
        this.Texture = new Texture(this.TextureUrl);

        this.Quad = new Quad(this.PosX, this.PosY, this.TransX, this.TransY);
    }

    @Override
    public String toString () {
        return "\n\t\t\tName: " + getName() + "\n\t\t\tTexture: " + getTextureUrl() + "\n\t\t\tTranslation: x " + getTransX() + " : y " + getTransY() + "\n\t\t\tPosion: x " + getPosX() + " : y " + getPosY() + "\n";
    }
    
    

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((TextureUrl == null) ? 0 : TextureUrl.hashCode());
        result = prime * result + Float.floatToIntBits(TransX);
        result = prime * result + Float.floatToIntBits(TransY);
        result = prime * result + Float.floatToIntBits(PosX);
        result = prime * result + Float.floatToIntBits(PosY);
        result = prime * result + ((Quad == null) ? 0 : Quad.hashCode());
        result = prime * result + ((Texture == null) ? 0 : Texture.hashCode());
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
        Persone other = (Persone) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (TextureUrl == null) {
            if (other.TextureUrl != null)
                return false;
        } else if (!TextureUrl.equals(other.TextureUrl))
            return false;
        if (Float.floatToIntBits(TransX) != Float.floatToIntBits(other.TransX))
            return false;
        if (Float.floatToIntBits(TransY) != Float.floatToIntBits(other.TransY))
            return false;
        if (Float.floatToIntBits(PosX) != Float.floatToIntBits(other.PosX))
            return false;
        if (Float.floatToIntBits(PosY) != Float.floatToIntBits(other.PosY))
            return false;
        if (Quad == null) {
            if (other.Quad != null)
                return false;
        } else if (!Quad.equals(other.Quad))
            return false;
        if (Texture == null) {
            if (other.Texture != null)
                return false;
        } else if (!Texture.equals(other.Texture))
            return false;
        return true;
    }

    public String getName() {
        return name;
    }

    public String getTextureUrl() {
        return TextureUrl;
    }

    public float getTransX() {
        return TransX;
    }

    public float getTransY() {
        return TransY;
    }

    public float getPosX() {
        return PosX;
    }

    public float getPosY() {
        return PosY;
    }
}
