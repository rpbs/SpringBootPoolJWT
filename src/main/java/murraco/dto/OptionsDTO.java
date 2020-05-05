package murraco.dto;

public class OptionsDTO {
    Integer id;
    String description;
    Boolean awnsered;

    public Boolean getAwnsered() {
        return awnsered;
    }

    public void setAwnsered(Boolean awnsered) {
        this.awnsered = awnsered;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public OptionsDTO(Integer id, String description, Boolean awnsered) {
        this.id = id;
        this.description = description;
        this.setAwnsered(awnsered);
    }

    public OptionsDTO(String description) {
        this.description = description;
    }
}
