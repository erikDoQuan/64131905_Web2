package quan.edu.ThiGK_Lamlai_64131905.controller;

import java.util.ArrayList;
import java.util.List;

import quan.edu.ThiGK_Lamlai_64131905.model.Topic;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class topiccontroller {
    private final List<Topic> topics = new ArrayList<>();

    public topiccontroller() {
        try {
            topics.add(new Topic("1", "Phân tích hệ thống", "Phân tích yêu cầu và thiết kế hệ thống phần mềm", "GV01", "Học phần"));
            topics.add(new Topic("2", "Lập trình Java", "Lập trình hướng đối tượng với Java", "GV02", "Chuyên ngành"));
            topics.add(new Topic("3", "Cơ sở dữ liệu", "Thiết kế và quản lý cơ sở dữ liệu", "GV01", "Học phần"));
            topics.add(new Topic("4", "Mạng máy tính", "Cấu trúc và nguyên lý hoạt động của mạng", "GV03", "Học phần"));
            topics.add(new Topic("5", "Khoa học dữ liệu", "Xử lý và phân tích dữ liệu", "GV04", "Chuyên ngành"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/topic/all")
    public String getAllTopics(ModelMap model) {
        model.addAttribute("topicList", topics);
        return "/topiclist";
    }

    @GetMapping("/topic/new")
    public String getNewTopic(ModelMap model) {
        model.addAttribute("topic", new Topic());
        return "topicnew";
    }

    @PostMapping("/topic/new")
    public String postNewTopic(@ModelAttribute Topic topic) {
        topics.add(topic);
        return "redirect:/topic/all";
    }

    @GetMapping("/topic/view/{id}")
    public String getTopicById(@PathVariable String id, ModelMap model) {
        Topic getTopic = topics.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .orElse(null);
        model.addAttribute("getTopicView", getTopic);
        return "topicview";
    }

    @GetMapping("/topic/edit/{id}")
    public String getEditTopic(@PathVariable String id, ModelMap model) {
        Topic getTopic = topics.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .orElse(null);
        model.addAttribute("getTopicEdit", getTopic);
        return "topicedit";
    }

    @PostMapping("/topic/edit/{id}")
    public String postEditTopic(@PathVariable String id, @ModelAttribute Topic topic) {
        Topic getTopic = topics.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .orElse(null);
        if (getTopic != null) {
            getTopic.setTopicName(topic.getTopicName());
            getTopic.setTopicDescription(topic.getTopicDescription());
            getTopic.setSupervisorId(topic.getSupervisorId());
            getTopic.setTopicType(topic.getTopicType());
        }
        return "redirect:/topic/all";
    }

    @GetMapping("/topic/delete/{id}")
    public String getDeleteTopic(@PathVariable String id) {
        topics.removeIf(t -> t.getId().equals(id));
        return "redirect:/topic/all";
    }
}
