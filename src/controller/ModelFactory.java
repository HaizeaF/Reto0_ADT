/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ResourceBundle;

/**
 *
 * @author 2dam
 */
public class ModelFactory {

    private ModelInterface data;
    private ResourceBundle configfile;
    private String model;

    public ModelInterface getModel() {
        configfile = ResourceBundle.getBundle("model.config");
        model = configfile.getString("MODEL");
        if (model.equalsIgnoreCase("db")) {
            if (data == null) {
                data = new ModelDBImplementation();
            }
        } else if (model.equalsIgnoreCase("file")) {
            if (data == null) {
                data = new ModelFileImplementation();
            }
        }
        return data;
    }
}
