package com.question3.demo.controller;

import com.question3.demo.Entity.Node;
import com.question3.demo.Entity.Relationship;
import com.question3.demo.Service.GraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;
@RestController
@RequestMapping("/graph")
public class GraphController{
    private final GraphService graphService;

    public GraphController(GraphService graphService) {
        this.graphService = graphService;
    }

    @PostMapping("/node")
    public void addNode(@RequestBody Node node) {
        graphService.nodeAdd(node);
    }


    @PostMapping("/nodes")
    public void addNodes(@RequestBody List<Node> nodes) {
        for (Node node : nodes) {
            graphService.nodeAdd(node);
        }
    }


    @PostMapping("/relationship")
    public void addRelationship(@RequestBody Relationship relationship) {
        graphService.relationshipAdd(relationship);
    }

    @PostMapping("/relationships")
    public void addRelationships(@RequestBody List<Relationship> relationships) {
        for (Relationship relationship : relationships) {
            graphService.relationshipAdd(relationship); // Add each relationship individually
        }
    }


    @GetMapping("/paths")
    public List<Node> findPath(@RequestParam String startNodeId, @RequestParam String endNodeId) {
        return graphService.findPath(startNodeId, endNodeId);
    }

    @GetMapping("/nodes/{id}/depth")
    public int calculateDepth(@PathVariable String id) {
        return graphService.Depthcalculation(id);
    }

    @GetMapping("/common-ancestor")
    public String findCommonAncestor(@RequestParam String nodeId1, @RequestParam String nodeId2) {
        return graphService.findCommonAncestor(nodeId1, nodeId2);
    }

    @GetMapping("/nodes/{id}")
    public ResponseEntity<Node> getNodeById(@PathVariable String id) {
        Node node = graphService.getNodeById(id); //
        if (node != null) {
            return new ResponseEntity<>(node, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
