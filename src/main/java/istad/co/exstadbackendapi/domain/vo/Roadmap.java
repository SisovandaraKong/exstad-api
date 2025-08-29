package istad.co.exstadbackendapi.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Roadmap {
    private List<Node> nodes;
    private List<Edge> edges;

}
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
class Node {
    private String uuid = UUID.randomUUID().toString();
    private NodeData data;
    private Position position;
    private int width;
    private int height;
}

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
class NodeData {
    private int id_courses;
    private int typer_Courses;
    private List<String> subject;
    private String name_over;
    private String bg;
    private int node_width;
    private int node_height;

}

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
class Position {
    private double x;
    private double y;

}
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
class Edge {
    private String uuid = UUID.randomUUID().toString();
    private String source;
    private String target;
    private boolean animated;
}
