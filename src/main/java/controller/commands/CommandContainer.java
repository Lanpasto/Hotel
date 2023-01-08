package controller.commands;

import controller.LoginCommand;
import controller.LogoutCommand;
import controller.RegisterCommand;
import controller.commands.PageCommands.*;
import controller.commands.User.RoomReserveCommand;

import java.util.Map;
import java.util.TreeMap;

public class CommandContainer {
    private static final Map<String, Command> commands = new TreeMap<String, Command>();

    static {
        commands.put("loginpage", new LoginPageCommand());
        commands.put("aboutUsPage", new AboutUsPageCommand());
        commands.put("indexPage", new IndexPageCommand());
        commands.put("registerPage", new RegisterPageCommand());
        commands.put("reservePage", new ReservePageCommand());
        commands.put("orderRoomPage", new OrderRoomCommand());
        commands.put("loginCommand", new LoginCommand());
        commands.put("registerCommand", new RegisterCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("myReservePage",new ReservePageCommand());
        commands.put("reserve",new RoomReserveCommand());
        commands.put("adminReservePage", new AdminReservePageCommand());
        commands.put("orderTypeOfRoomPage", new OrderTypeOfRoomPageCommand());
        commands.put("requestPage", new RequestPageCommand());
    }

    public static Command get(String commandName) {
        return commands.get(commandName);
    }
}