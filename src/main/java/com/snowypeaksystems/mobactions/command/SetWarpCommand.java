package com.snowypeaksystems.mobactions.command;

import com.snowypeaksystems.mobactions.player.PlayerException;
import java.util.List;
import org.bukkit.command.CommandSender;

public class SetWarpCommand implements ISetWarpCommand {
  @Override
  public String getHelpMessage() {
    return null;
  }

  @Override
  public List<String> getCompletions(String[] args) {
    return null;
  }

  @Override
  public void run(CommandSender sender, String[] args) throws PlayerException {

  }
}
