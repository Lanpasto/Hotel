package controller.commands;

import controller.commands.Admin.ConfirmOrderAdminCommand;
import controller.commands.Admin.UpdateRoomStatusCommand;
import controller.commands.PageCommands.*;
import controller.commands.User.*;

import java.util.Map;
import java.util.TreeMap;

public class CommandContainer {
    private static final Map<String, Command> commands = new TreeMap<String, Command>();

    static {
        commands.put("loginpage", new LoginPageCommand());
        commands.put("aboutUsPage", new AboutUsPageCommand());
        commands.put("indexPage", new IndexPageCommand());
        commands.put("registerPage", new RegisterPageCommand());
        commands.put("orderRoomPage", new OrderRoomPageCommand());
        commands.put("myReservePage",new LIstOfReserveRoomPageCommand());
        commands.put("ListOfRequest",new ListOfRequestRoomPageCommand());
        commands.put("adminReservePage", new AdminReservePageCommand());
        commands.put("adminRequestPage", new AdminRequestPageCommand());
        commands.put("requestPage", new RequestPageCommand());
        commands.put("selectRoomReq", new SelectRoomForRequestPageCommand());



        commands.put("loginCommand", new LoginCommand());
        commands.put("registerCommand", new RegisterCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("reserve",new RoomReserveCommand());
        commands.put("makeOrder", new MakeOrderCommand());
        commands.put("changeLang", new ChangeLanguageCommand());
        commands.put("makeRequest",new MakeOrderRequestCommand());
        commands.put("confirmRequestUser",new ConfirmOrderCommand());
        commands.put("rejectRequestUser",new RejectOrderCommand());
        commands.put("payForm", new PayFormPageCommand());
        commands.put("payment",new PaymentCommand());


        commands.put("confirmCommand", new ConfirmOrderAdminCommand());
        commands.put("updateStatus", new UpdateRoomStatusCommand());

    }

    public static Command get(String commandName) {
        return commands.get(commandName);
    }
}