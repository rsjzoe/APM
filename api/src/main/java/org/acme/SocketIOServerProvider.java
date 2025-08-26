package org.acme;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import io.quarkus.runtime.StartupEvent;
import io.quarkus.runtime.ShutdownEvent;

@ApplicationScoped
public class SocketIOServerProvider {

    private SocketIOServer server;

    public SocketIOServer getServer() {
        return server;
    }

    public void sendEvent(String name) {
        server.getBroadcastOperations().sendEvent(name);
    }

    void onStart(@Observes StartupEvent ev) {
        Configuration config = new Configuration();
        config.setHostname("0.0.0.0");
        config.setPort(9092);

        server = new SocketIOServer(config);

        // Exemple : écoute d’événements
        server.addConnectListener(client -> {
            System.out.println("Client connecté: " + client.getSessionId());
        });

        server.addDisconnectListener(client -> {
            System.out.println("Client déconnecté: " + client.getSessionId());
        });

        server.addEventListener("chat_message", String.class, (client, data, ackSender) -> {
            System.out.println("Message reçu: " + data);
            // broadcast à tous les clients
            server.getBroadcastOperations().sendEvent("chat_message", data);
        });

        server.start();
        System.out.println("Socket.IO server démarré sur port 9092");
    }

    void onStop(@Observes ShutdownEvent ev) {
        if (server != null) {
            server.stop();
        }
    }
}
