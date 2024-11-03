package org.example.LogInSystem.EXCEPTIONS;
import javafx.scene.control.TextField;
import java.util.List;

public class InputCantBeEmptyException  extends Exception{

    public InputCantBeEmptyException(String e, List<TextField> inputLists){
        super(e);

        for(TextField  inputs : inputLists){
            inputs.setPromptText( inputs.getId() + " values cannot be empty");
        }

    }
}
