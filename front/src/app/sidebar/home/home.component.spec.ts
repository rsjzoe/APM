// src/app/home.component.ts

import { Component } from '@angular/core';
import { ButtonComponent } from '../../components/button/button.component';
import { Application } from '../../application/appType';
import { CommonModule } from '@angular/common';
import { IconEditComponent } from '../../components/icons/icon-edit/icon-edit.component';
import { IconDeleteComponent } from '../../components/icons/icon-delete/icon-delete.component';
import { ModalConfirmComponent } from '../../components/modal-confirm/modal-confirm.component';
import { ModalEditAppComponent } from './modal-edit-app/modal-edit-app.component';
import { ModalAddAppComponent } from './modal-add-app/modal-add-app.component';

@Component({
  selector: 'app-home',
  standalone: true,
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
  imports: [
    ButtonComponent,
    CommonModule,
    IconEditComponent,
    IconDeleteComponent,
    ModalConfirmComponent,
    ModalEditAppComponent,
    ModalAddAppComponent,
  ],
})
export class HomeComponent {
userService: any;
canAdd(): any {
throw new Error('Method not implemented.');
}
  onClickAdd = () => {};
  saveIdAppDelete(arg0: any) {
    throw new Error('Method not implemented.');
  }
  onConfirmDelete = () => {};
  saveIdApp(arg0: any) {
    throw new Error('Method not implemented.');
  }
  goToDetail(arg0: any) {
    throw new Error('Method not implemented.');
  }
  getStatusColor(
    arg0: any
  ):
    | string
    | string[]
    | Set<string>
    | { [klass: string]: any }
    | null
    | undefined {
    throw new Error('Method not implemented.');
  }
  deleteById!: (id: number) => void;
  editApp!: (app: Application) => void;
  refresh!: () => void;
  appEditing: Application | null = null;
  apps: any;
  goToPage(arg0: any) {
    throw new Error('Method not implemented.');
  }
  nextPage() {
    throw new Error('Method not implemented.');
  }
  totalPages: any;
  previousPage() {
    throw new Error('Method not implemented.');
  }
  currentPage: any;
  addApplication($event: any) {
    throw new Error('Method not implemented.');
  }
}
