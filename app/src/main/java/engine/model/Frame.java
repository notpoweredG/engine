package engine.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Frame {
    private String Name;

    private Text Text;

    private List<Persone> Persons;

    public Frame() {
    }

    @JsonCreator
    public Frame(@JsonProperty("name") String name, @JsonProperty("text") Text text, @JsonProperty("persons") List<Persone> persons) {
        this.Name = name;
        Text = text;
        Persons = persons;
    }

    public String personsToString () {
        String str = "";

        for (Persone character : getPersons()) {
            str += character.toString();
        }

        return str;
    }

    @Override
    public String toString() {
        return "\n\t\tName: " + getName() + "\n\t\tText: " + getText().toString() + "\n\t\tCharacters: " + personsToString();
    }

    public String getName() {
        return Name;
    }

    public Text getText() {
        return Text;
    }

    public List<Persone> getPersons() {
        return Persons;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((Name == null) ? 0 : Name.hashCode());
        result = prime * result + ((Text == null) ? 0 : Text.hashCode());
        result = prime * result + ((Persons == null) ? 0 : Persons.hashCode());
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
        Frame other = (Frame) obj;
        if (Name == null) {
            if (other.Name != null)
                return false;
        } else if (!Name.equals(other.Name))
            return false;
        if (Text == null) {
            if (other.Text != null)
                return false;
        } else if (!Text.equals(other.Text))
            return false;
        if (Persons == null) {
            if (other.Persons != null)
                return false;
        } else if (!Persons.equals(other.Persons))
            return false;
        return true;
    }

    
}
