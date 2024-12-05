import java.util.TreeMap;

/**
 * class to hold the completed sketches in a TreeMap
 * @author Lydia Jin
 * @author Sudi Zhao
 */
public class Sketch {

    private TreeMap<Integer, Shape> shapeIds;

    /**
     * constructor to instantiate the TreeMap
     */
    public Sketch() {
        shapeIds = new TreeMap<>();
    }

    /**
     * add to the map
     * @param int id        the id number of the shape to be added to shapeIds
     * @param Shape shape   the shape to be added to shapeIds
     */
    public void addToMap(int id, Shape shape) {
        shapeIds.put(id, shape);
    }

    /**
     * add to the map
     * @return shapeIds
     */
    public TreeMap<Integer, Shape> getMap() {
        return shapeIds;
    }

    /**
     * remove the given shape from the map
     * @param Shape shape
     */
    public void removeFromMap (Shape shape) {
        if (shapeIds.containsValue(shape)) {
            for(Integer elem : shapeIds.keySet()) {
                if (shapeIds.get(elem).equals(shape)) {
                    shapeIds.remove(elem);
                    break;
                }
            }
        }
    }


    /**
     * get the integer id for the given shape in the map
     * @param Shape shape
     * @return int the id number, -1 if not found
     */
    public int retrieveId (Shape shape) {
        if (shapeIds.containsValue(shape)) {
            for(Integer elem : shapeIds.keySet()) {
                if (shapeIds.get(elem).equals(shape)) {
                    return elem;
                }
            }
        }
        return -2;
    }

    /**
     * remove the shape with the given id from the map
     * @param int id
     */
    public void removeFromMap (int id) {
        if (shapeIds.containsKey(id)) {
            shapeIds.remove(id);
        }
    }

    /**
     * get the type of the shape using the first word of the toString of shape
     * @param int id
     * @return String shape type
     */
    public String getShapeType(int id) {
        return shapeIds.get(id).toString().split(" ")[0];
    }

}
