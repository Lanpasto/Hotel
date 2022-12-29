package controller.commands;

import controller.commands.PageCommands.*;

import java.util.Map;
import java.util.TreeMap;

public class CommandContainer {
    private static final Map<String, Command> commands = new TreeMap<String, Command>();

    static {
        commands.put("loginpage", new LoginPageCommand());
        commands.put("aboutUsPage", new AboutUsPageCommand());
        commands.put("indexPage", new IndexPageCommand());
        commands.put("registerPage", new RegisterPageCommand());
        commands.put("forgotPage",new ForgotPageCommand());
        commands.put("reservePage",new ReservePageCommand());
        commands.put("orderRoomPage",new OrderRoomCommand());
    }

    public static Command get(String commandName) {
        return commands.get(commandName);
    }
}