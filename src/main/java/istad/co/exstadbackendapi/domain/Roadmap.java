package istad.co.exstadbackendapi.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Roadmap {
    private List<Node> nodes;
    private List<Edge> edges;

}

class Node {
    private String id;
    private NodeData data;
    private Position position;
    private int width;
    private int height;

}

class NodeData {
    private int id_courses;
    private int typer_Courses;
    private List<String> subject;
    private String name_over;
    private String bg;
    private int node_width;
    private int node_height;

}

class Position {
    private double x;
    private double y;

}

class Edge {
    private String id;
    private String source;
    private String target;
    private boolean animated;

}
