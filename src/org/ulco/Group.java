package org.ulco;

import java.util.Vector;

public class Group extends GraphicsObject{

    public Group() {
        m_objectList = new Vector<GraphicsObject>();
        m_ID = ++ID.ID;
    }

    public Group(String json) {
        m_objectList = new Vector<GraphicsObject>();
        String str = json.replaceAll("\\s+","");
        int objectsIndex = str.indexOf("objects");
        int groupsIndex = str.indexOf("groups");
        int endIndex = str.lastIndexOf("}");

        parseObjects(str.substring(objectsIndex + 9, groupsIndex - 2));
    }

    public void add(GraphicsObject object) {
        m_objectList.add(object);
    }

    public Group copy() {
        Group g = new Group();

        for (Object o : m_objectList) {
            if(o instanceof  Group){
                Group element = (Group) (o);
                g.add(element.copy());
            } else {
                GraphicsObject element = (GraphicsObject) (o);
                g.add(element.copy());
            }
        }

        return g;
    }

    public int getID() {
        return m_ID;
    }

    public void move(Point delta) {
        for (Object o : m_objectList) {
            if(o instanceof Group){
                Group element = (Group) (o);
                element.move(delta);
            } else {
                GraphicsObject element = (GraphicsObject) (o);
                element.move(delta);
            }
        }
    }

    public boolean isClosed(Point pt, double distance){
        return false;
    }

    private int searchSeparator(String str) {
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



    private void parseObjects(String objectsStr) {
        while (!objectsStr.isEmpty()) {
            int separatorIndex = searchSeparator(objectsStr);
            String objectStr;

            if (separatorIndex == -1) {
                objectStr = objectsStr;
            } else {
                objectStr = objectsStr.substring(0, separatorIndex);
            }
            m_objectList.add(JSON.parse(objectStr));
            if (separatorIndex == -1) {
                objectsStr = "";
            } else {
                objectsStr = objectsStr.substring(separatorIndex + 1);
            }
        }
    }

    public int size() {
        int size = 0;

        for (int i = 0; i < m_objectList.size(); ++i) {
            if(m_objectList.elementAt(i) instanceof Group) {
                Group element = (Group) m_objectList.elementAt(i);
                size += element.size();
            } else {
                size++;
            }
        }
        return size;
    }

    public String toJson() {
        String str = "{ type: group, objects : { ";
        String groupStr = "";

        for (int i = 0; i < m_objectList.size(); ++i) {
            if(m_objectList.elementAt(i) instanceof Group){
                Group element = (Group) m_objectList.elementAt(i);
                groupStr += element.toJson();
            } else {
                GraphicsObject element = m_objectList.elementAt(i);

                str += element.toJson();
                if (i < m_objectList.size() - 1) {
                    str += ", ";
                }
            }
        }
        str += " }, groups : { ";
        str += groupStr;
        return str + " } }";
    }

    public String toString() {
        String str = "group[[";
        String groupStr = "";

        for (int i = 0; i < m_objectList.size(); ++i) {
            if(m_objectList.elementAt(i) instanceof Group){
                Group element = (Group) m_objectList.elementAt(i);
                groupStr += element.toString();
            } else {
                GraphicsObject element = m_objectList.elementAt(i);

                str += element.toString();
                if (i < m_objectList.size() - 1) {
                    str += ", ";
                }
            }
        }
        str += "],[";
        str += groupStr;
        return str + "]]";
    }

    private Vector<GraphicsObject> m_objectList;
    private int m_ID;
}
