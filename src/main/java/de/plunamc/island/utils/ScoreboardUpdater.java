package de.plunamc.island.utils;

import de.plunaapi.netty.PacketGetter;
import de.plunaapi.scoreboard.PlayerScore;
import de.plunamc.packets.PacketType;
import de.plunamc.packets.types.Coins_Update_Packet;
import de.plunamc.packets.types.PacketHolder;
import de.plunamc.packets.types.Rank_Update_Packet;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ScoreboardUpdater extends PacketGetter {
    @Override
    public void receivePacket(PacketHolder packetHolder) {
        if(packetHolder.getKey().equals(PacketType.RANK_UPDATE_PACKET.getKey())){
            Rank_Update_Packet packet = (Rank_Update_Packet) packetHolder.getValue();
            Player player = Bukkit.getPlayer(packet.getName());
            PlayerScore playerScore = PlayerScore.getScores().get(player);
            playerScore.setScore("§7➥ "+packet.getRank().getUnicode(), 6);

        }
        if(packetHolder.getKey().equals(PacketType.COINS_UPDATE_PACKET.getKey())){
            Coins_Update_Packet packet = (Coins_Update_Packet) packetHolder.getValue();
            Player player = Bukkit.getPlayer(packet.getName());
            PlayerScore playerScore = PlayerScore.getScores().get(player);
            playerScore.setScore("§7➥ §f"+packet.getCoins(), 3);
        }
    }
}
