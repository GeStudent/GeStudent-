/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gestudent.gui;

import com.codename1.ui.Component;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author Ayadi
 */
public class Club extends Form {
    static Form  currentForm;

    public Club(Form previous, Resources theme) {
        currentForm=this;
        setTitle("Club");
        setLayout(BoxLayout.y());
        
        getToolbar().addCommandToOverflowMenu("Add Club", null, ev->{
                AddClub().show();
        });

        getToolbar().addMaterialCommandToLeftBar("back", FontImage.MATERIAL_ARROW_BACK, ev -> {
            previous.showBack();
        });
    }
    
    public Form AddClub()
    {    
        
        
        Form AddClub = new Form("ADD", new FlowLayout(Component.CENTER, Component.CENTER));
        AddClub.getToolbar().addMaterialCommandToLeftBar("back", FontImage.MATERIAL_ARROW_BACK, ev -> {
             currentForm.showBack();
        });

        return AddClub;
    }
}
