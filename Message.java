import java.awt.*;
import java.util.ArrayList;

/**
 * Class that handles the messages
 * takes a String-based command (sent via writer to reader) and parses it to determine what action to perform and update the sketch accordingly
 * @author Lydia Jin, Sudi Zhao, Fall 2022
 */
public class Message {
    String[] command; //string array, which is the original string command split up
    Sketch sketch; //the sketch that must be modified

    //constructor
    public Message (String c, Sketch s) {
        command = c.split(" "); //split the string-based command by spaces and put them into the string array
        sketch = s;
    }

    /**
     * update message based on command
     * format of message: first element A/D/M/R (add/delete/move/recolor)
     *
     * if add (A) ellipse, second element will be ID, third element will be "ellipse", fourth element will be x1, fifth element will be y1, sixth element will be color RGB
     * if add (A) rectangle, second element will be ID, third element will be "rectangle", fourth element will be x1, fifth element will be y1, sixth element will be color RGB
     * if add (A) segment, second element will be ID, third element will be "segment", fourth element will be x1, fifth element will be y1, sixth element will be color RGB
     * if add (A) polyline, second element will be ID, third element will be "polyline", fourth element will be color rgb, the rest of the elements will be the list of points with each element being x value of point followed by y value of points
     *
     * if delete (D), second element will be ID
     *
     * if move (M), second element will be ID, third element will be moveFrom x, fourth element will be moveFrom y, fifth element will be moveTo x, sixth element will be moveTo y
     *
     * if recolor (R), second element will be ID, third element will be color RGB
     */
    public void handleMessage() {
        if (command[0].equals("A")) { //if the message requests to add
            if (command[2].equals("ellipse")) { //if the shape is an ellipse
                sketch.addToMap(Integer.parseInt(command[1]), new Ellipse(Integer.parseInt(command[3]), Integer.parseInt(command[4]), Integer.parseInt(command[5]), Integer.parseInt(command[6]), new Color(Integer.parseInt(command[7]))));
            }
            else if (command[2].equals("rectangle")) {  //if the shape is an rectangle
                sketch.addToMap(Integer.parseInt(command[1]), new Rectangle(Integer.parseInt(command[3]), Integer.parseInt(command[4]), Integer.parseInt(command[5]), Integer.parseInt(command[6]), new Color(Integer.parseInt(command[7]))));
            }
            else if (command[2].equals("segment")) { //if the shape is an segment
                sketch.addToMap(Integer.parseInt(command[1]), new Segment(Integer.parseInt(command[3]), Integer.parseInt(command[4]), Integer.parseInt(command[5]), Integer.parseInt(command[6]), new Color(Integer.parseInt(command[7]))));
            }
            else if (command[2].equals("polyline")) { //if the shape is an polyline
                ArrayList<Point> points = new ArrayList<>();
                for (int i = 4; i <= command.length - 2; i += 2) { //loop through command coordinates and add to points list
                    points.add(new Point(Integer.parseInt(command[i]), Integer.parseInt(command[i+1])));
                }
                sketch.addToMap(Integer.parseInt(command[1]),new Polyline(points, new Color(Integer.parseInt(command[3]))));
            }
        }

        else if (command[0].equals("D")) { //if the message requests to delete
            sketch.removeFromMap(Integer.parseInt(command[1]));
        }

        else if (command[0].equals("M")) { //if the message requests to move
            if (sketch.getMap().containsKey(Integer.parseInt(command[1]))) { //if the id is in the map already
                sketch.getMap().get(Integer.parseInt(command[1])).moveBy(Integer.parseInt(command[4]) - Integer.parseInt(command[2]), Integer.parseInt(command[5]) - Integer.parseInt(command[3]));
            }
        }

        else if (command[0].equals("R")) { //if the message requests to remove
            if (sketch.getMap().containsKey(Integer.parseInt(command[1]))) { //if the id is in the map already
                sketch.getMap().get(Integer.parseInt(command[1])).setColor(new Color(Integer.parseInt(command[2])));
            }
        }
    }


}
