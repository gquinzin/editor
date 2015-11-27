package org.ulco;

import java.util.Vector;

public class Group extends GraphicsObject{

    public Group() {
        m_objectList = new Vector<GraphicsObject>();
        m_ID = ID.getInstance().nextId();
    }

    public void initObject(String json){
        m_objectList = new Vector<GraphicsObject>();
        Helpers.initObject(json, m_objectList);
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

    public int size() {
        return Helpers.size(m_objectList);
    }

    public String toJson() {
        return Helpers.toJson("group", m_objectList);
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
                if (str.compareTo("group[[") != 0) {
                    str += ", ";
                }
                str += element.toString();
            }
        }
        str += "],[";
        str += groupStr;
        return str + "]]";
    }

    private Vector<GraphicsObject> m_objectList;
    private int m_ID;
}
