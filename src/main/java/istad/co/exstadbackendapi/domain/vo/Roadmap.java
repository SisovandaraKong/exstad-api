package istad.co.exstadbackendapi.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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
    private String id;
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
    private String id;
    private String source;
    private String target;
    private boolean animated;

}
