package craftout.components.paddle;

import craftout.components.base.Component;
import craftout.di.DependencyContainer;

public class PaddleInput extends Component {

    public PaddleInput(DependencyContainer dependencyContainer, PaddleInputData inputData){

        super(dependencyContainer);

        _inputData = inputData;
    }

    public void update(){
        updateInput();
    }

    private void updateInput(){
        float xInput = 0;
        if (Input.isButtonPressed(Input.Button.LEFT))
            xInput = -1;
        else if (Input.isButtonPressed(Input.Button.RIGHT))
            xInput = 1;

        _inputData.setXInput(xInput);
    }

    private PaddleInputData _inputData;
}
