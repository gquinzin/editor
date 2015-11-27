package org.ulco;

import java.util.Vector;

public class Layer {
    public Layer() {
        m_list = new Vector<GraphicsObject>();
        m_ID = ID.getInstance().nextId();
    }

    public Layer(String json) {
        m_list= new Vector<GraphicsObject>();
        Helpers.initObject(json, m_list);
    }

    public void add(GraphicsObject o) {
        m_list.add(o);
    }

    public GraphicsObject get(int index) {
        return m_list.elementAt(index);
    }

    public int getObjectNumber() {
        return Helpers.size(m_list);
    }

    public int getID() {
        return m_ID;
    }

    public String toJson() {
        return Helpers.toJson("layer", m_list);
    }

    public Vector<GraphicsObject> getList(){
        return m_list;
    }

    private Vector<GraphicsObject> m_list;
    private int m_ID;
}
