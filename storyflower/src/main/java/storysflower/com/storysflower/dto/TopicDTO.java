package storysflower.com.storysflower.dto;

public class TopicDTO {
    private  Long idTopic;
    private  String nameTopic;

    public TopicDTO(Long idTopic, String nameTopic) {
        this.idTopic = idTopic;
        this.nameTopic = nameTopic;
    }

    public Long getIdTopic() {
        return idTopic;
    }

    public void setIdTopic(Long idTopic) {
        this.idTopic = idTopic;
    }

    public String getNameTopic() {
        return nameTopic;
    }

    public void setNameTopic(String nameTopic) {
        this.nameTopic = nameTopic;
    }

    @Override
    public String toString() {
        return "TopicDTO{" +
                "idTopic=" + idTopic +
                ", nameTopic='" + nameTopic + '\'' +
                '}';
    }
}
