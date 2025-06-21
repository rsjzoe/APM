import { Injectable } from '@angular/core';
import * as bootstrap from 'bootstrap';

type ToastType = 'success' | 'error' | 'default' | 'info';

@Injectable({
  providedIn: 'root',
})
export class ToastService {
  private toastType: ToastType | null = null;
  private message = '';

  success = (message: string) => {
    this.showToast(message, 'success');
  };

  error = (message: string) => {
    this.showToast(message, 'error');
  };

  info = (message: string) => {    
    this.showToast(message, 'info');
  };

  showToast = (message: string, type: ToastType) => {
    this.message = message;
    this.toastType = type;
    const toastEl = document.getElementById('liveToast')!;
    const toast = new bootstrap.Toast(toastEl);
    toast.show();
  };

  getMessage = () => {
    return this.message;
  };

  getToastType = () => {
    return this.toastType;
  };
}
