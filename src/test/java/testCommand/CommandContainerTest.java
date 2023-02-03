package testCommand;

import controller.commands.Command;
import controller.commands.CommandContainer;
import controller.commands.LoginCommand;
import controller.commands.PageCommands.AboutUsPageCommand;
import controller.commands.PageCommands.LoginPageCommand;
import controller.commands.User.RoomReserveCommand;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandContainerTest {

    @Test
    public void testGetCommand() {
        Command loginPageCommand = CommandContainer.get("loginpage");
        assertEquals(LoginPageCommand.class, loginPageCommand.getClass());

        Command aboutUsPageCommand = CommandContainer.get("aboutUsPage");
        assertEquals(AboutUsPageCommand.class, aboutUsPageCommand.getClass());

        Command loginCommand = CommandContainer.get("loginCommand");
        assertEquals(LoginCommand.class, loginCommand.getClass());

        Command reserveCommand = CommandContainer.get("reserve");
        assertEquals(RoomReserveCommand.class, reserveCommand.getClass());
    }
}
