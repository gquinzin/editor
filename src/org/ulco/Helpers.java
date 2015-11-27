package org.ulco;

import java.util.Vector;

/**
 * Created by gilles on 24/11/15.
 */
public class Helpers {

    public static String toJson(String type, Vector<GraphicsObject> list) {
        String str = "{ type: " + type + ", objects : { ";
        String groupStr = "";

        for (int i = 0; i < list.size(); ++i) {
            if(list.elementAt(i) instanceof Group){
                Group element = (Group) list.elementAt(i);
                groupStr += element.toJson();
            } else {
                GraphicsObject element = list.elementAt(i);
                str += element.toJson();
                if (i < list.size() - 1) {
                    str += ", ";
                }
            }
        }
        str += " }, groups : { ";
        str += groupStr;
        return str + " } }";
    }

    public static int searchSeparator(String str) {
        int index = 0;
        int level = 0;
        boolean found = false;

        while (!found && index < str.length()) {
            if (str.charAt(index) == '{') {
                ++level;
                ++index;
            } else if (str.charAt(index) == '}') {
                --level;
                ++index;
            } else if (str.charAt(index) == ',' && level == 0) {
                found = true;
            } else {
                ++index;
            }
        }
        if (found) {
            return index;
        } else {
            return -1;
        }
    }

    public static Point getCenterPoint(Point orig, double length){
        Point center = new Point(orig.getX() + length / 2, orig.getY() + length / 2);
        return  center;
    }

    public static boolean isClosed(Point center, Point pt, double distance) {

        return Math.sqrt(Math.pow(center.getX() - pt.getX(),2) +
                Math.pow((center.getY() - pt.getY()), 2)) <= distance;
    }

    public static void parseObjects(String objectsStr, Vector<GraphicsObject> list) {
        while (!objectsStr.isEmpty()) {
            int separatorIndex = Helpers.searchSeparator(objectsStr);
            String objectStr;

            if (separatorIndex == -1) {
                objectStr = objectsStr;
            } else {
                objectStr = objectsStr.substring(0, separatorIndex);
            }
            list.add(JSON.parse(objectStr));
            if (separatorIndex == -1) {
                objectsStr = "";
            } else {
                objectsStr = objectsStr.substring(separatorIndex + 1);
            }
        }
    }

    public static void initObject(String json, Vector<GraphicsObject> list){
        String str = json.replaceAll("\\s+","");
        int objectsIndex = str.indexOf("objects");
        int groupsIndex = str.indexOf("groups");
        int endIndex = str.lastIndexOf("}");

        parseObjects(str.substring(objectsIndex + 9, groupsIndex - 2), list);
        parseObjects(str.substring(groupsIndex + 8, endIndex - 1), list);
    }

    public static int size(Vector<GraphicsObject> list) {
        int size = 0;

        for (int i = 0; i < list.size(); ++i) {
            if(list.elementAt(i) instanceof Group) {
                Group element = (Group) list.elementAt(i);
                size += element.size();
            } else {
                size++;
            }
        }
        return size;
    }


}
