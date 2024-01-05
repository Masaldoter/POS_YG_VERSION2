/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SOCKETS;

/**
 *
 * @author MASALDOTER_GT
 */
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;

public class VentasWebSocketServidor extends WebSocketClient {

    public VentasWebSocketServidor(String serverUri) throws URISyntaxException {
        super(new URI(serverUri));
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        System.out.println("Conectado al servidor WebSocket");
    }

    @Override
    public void onMessage(String message) {
        System.out.println("Nueva venta: " + message);
        // Aquí puedes actualizar tu interfaz de usuario con la nueva venta
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("Desconectado del servidor WebSocket");
    }

    @Override
    public void onError(Exception ex) {
        ex.printStackTrace();
    }

    public static void main(String[] args) throws URISyntaxException {
        String serverUri = "ws://localhost:3000"; // Reemplaza con la dirección de tu servidor WebSocket
        VentasWebSocketServidor client = new VentasWebSocketServidor(serverUri);
        client.connect();
    }
}

