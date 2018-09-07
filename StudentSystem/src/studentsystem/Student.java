/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentsystem;

/**
 *
 * @author cstuser
 */
class Student {
    private final String _id;

    @Override
    public String toString() {
        return _id+", "+_name;
    }

    public String getId() {
        return _id;
    }

    public String getName() {
        return _name;
    }
    private final String _name;

    Student(String stId, String stName) {
        this._id = stId;
        this._name = stName;
    }
    
}
