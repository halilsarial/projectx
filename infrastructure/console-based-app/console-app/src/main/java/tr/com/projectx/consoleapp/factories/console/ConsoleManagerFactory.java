package tr.com.projectx.consoleapp.factories.console;

public class ConsoleManagerFactory {

    private static ConsoleManager consoleManager = null;

    public static ConsoleManager getInstance() {
        if (consoleManager == null) {
            consoleManager = new ConsoleManager();
        }
        return consoleManager;
    }
}
