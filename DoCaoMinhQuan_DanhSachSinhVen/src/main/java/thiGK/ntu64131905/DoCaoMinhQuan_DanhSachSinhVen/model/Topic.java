package thiGK.ntu64131905.DoCaoMinhQuan_DanhSachSinhVen.model;

public class Topic {
	public String id;
	public String topicName;
	public String supervisorID;
	public String topicType; 
	
	public Topic(String id, String topicName, String supervisorID, String topicType) {
        this.id = id;
        this.topicName = topicName;
        this.supervisorID = supervisorID;
        this.topicType = topicType;
    }
}
