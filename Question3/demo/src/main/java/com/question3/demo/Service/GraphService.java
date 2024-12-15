package com.question3.demo.Service;

import com.question3.demo.Entity.Node;
import com.question3.demo.Entity.Relationship;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class GraphService{

    private final Map<String, Node> nodes = new HashMap<>(); //id and node(id,name nd parentid)
    private final List<Relationship> relationships = new ArrayList<>();// stores rs object contains info about parentid and child id

    public void nodeAdd(Node node) {
        if (nodes.containsKey(node.getId())) {
            throw new IllegalArgumentException("Node with the provided id " + node.getId() + " already exists. please make a node with new id other than this ");
        }
        nodes.put(node.getId(), node);
    }

    public void relationshipAdd(Relationship relationship) {
        if (!nodes.containsKey(relationship.getParentId()) || !nodes.containsKey(relationship.getChildId())) {
            throw new IllegalArgumentException("Parent or child node does not exist. cannot add releationship hence");
        }
        relationships.add(relationship);
    }


    public List<Node> findPath(String startNodeId, String endNodeId) {
        if (!nodes.containsKey(startNodeId) || !nodes.containsKey(endNodeId)) {
            throw new IllegalArgumentException("Start or end node does not exist. cannot find nodes you provided ");
        }

        List<Node> path = new ArrayList<>();
        if (dfs(startNodeId, endNodeId, new HashSet<>(), path)) {
            return path;
        }
        return Collections.emptyList();
    }

    private boolean dfs(String current, String target, Set<String> vis, List<Node> path) {
        vis.add(current); //makrk as visited first current node also add in path
        path.add(nodes.get(current));
        if (current.equals(target)) return true;

        for (Relationship relationship : relationships) {
            if (relationship.getParentId().equals(current) && !vis.contains(relationship.getChildId())) {
                if (dfs(relationship.getChildId(), target, vis, path)) {
                    return true;
                }
            }
        }
        path.remove(path.size() - 1);
        return false;
    }


    public int Depthcalculation(String nodeId) { //upar chalte jao
        if (!nodes.containsKey(nodeId)) {
            throw new IllegalArgumentException("Node does not exist.");
        }
        int depth = 0;
        Node currentNode = nodes.get(nodeId);
        while (currentNode.getParentId()!= null){
            depth++;
            currentNode = nodes.get(currentNode.getParentId());
        }
        return depth;
    }

    public String findCommonAncestor(String nodeId1, String nodeId2) {
        if (!nodes.containsKey(nodeId1) || !nodes.containsKey(nodeId2)) {
            throw new IllegalArgumentException("One or both nodes do not exist.");
        }

        Set<String> ancestors1 = getAncestors(nodeId1);
        Set<String> ancestors2 = getAncestors(nodeId2);

        for (String ancestor : ancestors1) {
            if (ancestors2.contains(ancestor)) {
                return ancestor;
            }
        }
        return null; // No common ancestor found
    }

    private Set<String> getAncestors(String nodeId) {
        Set<String> ancestors = new HashSet<>();
        Node currentNode = nodes.get(nodeId);
        while (currentNode.getParentId() != null) {
            ancestors.add(currentNode.getParentId());
            currentNode = nodes.get(currentNode.getParentId());
        }
        return ancestors;
    }

    public Node getNodeById(String nodeId) {
        return nodes.get(nodeId); // Return the node if it exists, otherwise null
    }

}



