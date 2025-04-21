package quan.edu.ThiGK_Lamlai_64131905.model;

public class Student {
    private String id;
    private String name;
    private String groupId;

    // Constructor mặc định
    public Student() {}

    // Constructor đầy đủ
    public Student(String id, String name, String groupId) {
        this.id = id;
        this.name = name;
        this.groupId = groupId;
    }

    // Getter và Setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}
