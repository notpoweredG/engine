package engine.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Story {
    private String name;

    private List<Scene> Scens;

    public Story() {
    }

    @JsonCreator
    public Story(@JsonProperty("name") String name, @JsonProperty("scens") List<Scene> scenes) {
        this.name = name;
        Scens = scenes;
    }

    public String getName() {
        return name;
    }

    public List<Scene> getScens() {
        return Scens;
    }

    String scensToString(){
        String str = "";

        for (Scene scene : getScens()){
            str += scene.toString();
        }

        return str;
    }

    @Override
    public String toString() {
        return "Story: " + getName() + "\nScens:" + scensToString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((Scens == null) ? 0 : Scens.hashCode());
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
        Story other = (Story) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (Scens == null) {
            if (other.Scens != null)
                return false;
        } else if (!Scens.equals(other.Scens))
            return false;
        return true;
    }
}
