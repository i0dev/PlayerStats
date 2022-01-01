package com.i0dev.PlayerStats.hooks;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.model.user.User;
import net.luckperms.api.model.user.UserManager;
import net.luckperms.api.node.NodeType;
import net.luckperms.api.node.types.PermissionNode;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class LuckPermsHook {

    static LuckPerms lp = null;

    public static void setupAPI() {
        if (lp != null)
            return;
        RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
        if (provider != null)
            lp = provider.getProvider();
    }

    public static User giveMeADamnUser(UUID uniqueId) {
        UserManager userManager = lp.getUserManager();
        CompletableFuture<User> userFuture = userManager.loadUser(uniqueId);
        return userFuture.join();
    }

    public static boolean hasPermission(UUID uuid, String permission) {
        Player player = Bukkit.getPlayer(uuid);
        if (player != null)
            return player.hasPermission(permission);
        setupAPI();
        User user = giveMeADamnUser(uuid);
        System.out.println(user);
        if (user == null) return false;
        Set<String> permissions = user.getNodes().stream()
                .filter(NodeType.PERMISSION::matches)
                .map(NodeType.PERMISSION::cast)
                .map(PermissionNode::getPermission)
                .collect(Collectors.toSet());

        if (permissions.contains(permission))
            System.out.println("user has permissions: " + permission);

        return permissions.contains(permission);
    }
}