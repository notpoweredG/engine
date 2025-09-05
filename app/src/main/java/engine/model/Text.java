package engine.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Text {
    private String Author;
    
    private String Massege;
    
    private String FontUrl;
    
    @Override
    public String toString() {
        return "\n\t\tAuthor: " + getAuthor() + "\n\t\tMessege: " + getMassege() + "\n\t\tFontUrl: " + getFontUrl();
    }

    @JsonCreator
    public Text(@JsonProperty("author") String author, @JsonProperty("messege") String massege, @JsonProperty("fonturl") String fontUrl) {
        Author = author;
        Massege = massege;
        FontUrl = "app/src/main/resources/fonts/" + fontUrl;
    }

    public String getAuthor() {
        return Author;
    }

    public String getMassege() {
        return Massege;
    }

    public String getFontUrl() {
        return FontUrl;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((Author == null) ? 0 : Author.hashCode());
        result = prime * result + ((Massege == null) ? 0 : Massege.hashCode());
        result = prime * result + ((FontUrl == null) ? 0 : FontUrl.hashCode());
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
        Text other = (Text) obj;
        if (Author == null) {
            if (other.Author != null)
                return false;
        } else if (!Author.equals(other.Author))
            return false;
        if (Massege == null) {
            if (other.Massege != null)
                return false;
        } else if (!Massege.equals(other.Massege))
            return false;
        if (FontUrl == null) {
            if (other.FontUrl != null)
                return false;
        } else if (!FontUrl.equals(other.FontUrl))
            return false;
        return true;
    }
    
    
}
