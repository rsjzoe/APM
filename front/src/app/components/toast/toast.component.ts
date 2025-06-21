import { Component } from '@angular/core';
import { ToastService } from './service/toast.service';
import { IconSuccessComponent } from "../icons/icon-success/icon-success.component";
import { CommonModule } from '@angular/common';
import { IconErrorComponent } from "../icons/icon-error/icon-error.component";
import { IconInfoComponent } from "../icons/icon-info/icon-info.component";

@Component({
  selector: 'app-toast',
  imports: [IconSuccessComponent, CommonModule, IconErrorComponent, IconInfoComponent],
  templateUrl: './toast.component.html',
  styleUrl: './toast.component.scss',
})
export class ToastComponent {
  constructor(public toastService: ToastService) {}
}
