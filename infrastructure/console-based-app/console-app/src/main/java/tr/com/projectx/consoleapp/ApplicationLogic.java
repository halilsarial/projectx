package tr.com.projectx.consoleapp;

import tr.com.projectx.consoleapp.state.ConsoleState;
import tr.com.projectx.consoleapp.state.main.MainState;

public class ApplicationLogic {

    private ConsoleState currentState;

    public ApplicationLogic() {
        this.currentState = new MainState(); //initial state
    }

    public void process() {
        while (true) {
            try {
                currentState.process();
                if (currentState.nextState() != null) {
                    ConsoleState nextState = currentState.nextState();
                    currentState = nextState;
                }else {
                    break;
                }
            } catch (Exception e) {
                System.err.println("Problem with input: " + e.getMessage());
                System.exit(1);
            }
        }
    }
}
