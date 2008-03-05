

package net.adrianromero.data.user;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ObjectContainer<T> {
    
    private PropertyChangeSupport changeSupport;
    private T m_value;
    
    /** Creates a new instance of ObjectContainer */
    public ObjectContainer() {
        m_value = null;
        changeSupport = null;
    }
    public synchronized void addPropertyChangeListener(PropertyChangeListener listener) {
        if (listener == null) {
            return;
        }
        if (changeSupport == null) {
            changeSupport = new PropertyChangeSupport(this);
        }
        changeSupport.addPropertyChangeListener(listener);
    }
    
    public synchronized void removePropertyChangeListener(PropertyChangeListener listener) {
        if (listener == null || changeSupport == null) {
            return;
        }
        changeSupport.removePropertyChangeListener(listener);
    }
    
    protected void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
        PropertyChangeSupport changeSupport = this.changeSupport;
        if (changeSupport == null ||
            (oldValue != null && newValue != null && oldValue.equals(newValue))) {
            return;
        }
        changeSupport.firePropertyChange(propertyName, oldValue, newValue);
    }
    
    public void setObject(T value) {
        T oldvalue = m_value;
        m_value = value;
        firePropertyChange("object", oldvalue, value);
    }
    
    public T getObject() {
        return m_value;
    } 
}
