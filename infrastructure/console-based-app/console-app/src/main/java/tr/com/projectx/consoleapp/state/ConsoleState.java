package tr.com.projectx.consoleapp.state;

public interface ConsoleState {
    void process() throws Exception;

    ConsoleState nextState();
}
