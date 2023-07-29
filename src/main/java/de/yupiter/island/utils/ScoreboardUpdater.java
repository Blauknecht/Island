package de.yupiter.island.utils;

import de.kevloe.packets.PacketType;
import de.kevloe.packets.types.Coins_Update_Packet;
import de.kevloe.packets.types.PacketHolder;
import de.kevloe.packets.types.Rank_Update_Packet;
import de.yupiterapi.netty.PacketGetter;
import de.yupiterapi.scoreboard.PlayerScore;
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
