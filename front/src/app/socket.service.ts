// src/app/services/socket.service.ts
import { Injectable } from '@angular/core';
import { io, Socket } from "socket.io-client";

@Injectable({
  providedIn: 'root'
})
export class SocketService {
  private socket: Socket;

  constructor() {
    this.socket = io('http://localhost:9092', {
      transports: ['websocket'] // important sinon fallback polling
    });

    this.socket.on('connect', () => {
      console.log('Connecté au serveur Socket.IO');
    });

    this.socket.on('disconnect', () => {
      console.log('Déconnecté du serveur');
    });

  }

  sendMessage(message: string) {
    this.socket.emit('chat_message', message);
  }

  onEvent(name : string , cb : ()=> void){
    this.socket.on(name, cb)
  }

  onMessage(callback: (msg: string) => void) {
    this.socket.on('chat_message', callback);
  }
}
