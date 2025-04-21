package quan.edu.ThiGK_Lamlai_64131905.model;

public class Topic {
    private String id;
    private String topicName;
    private String topicDescription;
    private String supervisorId;
    private String topicType;

    
    public Topic() {
        // TODO Auto-generated constructor stub
    }


    public Topic(String id, String topicName, String topicDescription, String supervisorId, String topicType) {
        this.id = id;
        this.topicName = topicName;
        this.topicDescription = topicDescription;
        this.supervisorId = supervisorId;
        this.topicType = topicType;
    }


    public String getId() {
        return id;
    }

    public String getTopicName() {
        return topicName;
    }

    public String getTopicDescription() {
        return topicDescription;
    }

    public String getSupervisorId() {
        return supervisorId;
    }

    public String getTopicType() {
        return topicType;
    }

    // Setter methods
    public void setId(String id) {
        this.id = id;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public void setTopicDescription(String topicDescription) {
        this.topicDescription = topicDescription;
    }

    public void setSupervisorId(String supervisorId) {
        this.supervisorId = supervisorId;
    }

    public void setTopicType(String topicType) {
        this.topicType = topicType;
    }
}
