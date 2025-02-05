import { Component, OnInit } from '@angular/core';
import { CardComponent } from './card/card.component';
import { applications } from '../../application-APM/data';
import { CommonModule } from '@angular/common';
import { ButtonComponent } from '../../components/button/button.component';
import { ModalAddAppComponent } from './modal-add-app/modal-add-app.component';
import { ApplicationService } from './home.service';
import { Application } from '../../application-APM/appType';

@Component({
  selector: 'app-home',
  imports: [CardComponent, CommonModule, ButtonComponent, ModalAddAppComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss',
})
export class HomeComponent implements OnInit {
  apps = applications;
  appEditing: Application | null = null;

  constructor(private appService: ApplicationService) {}

  deleteById = (id: number) => {
    this.appService.delete(id).subscribe({
      next: () => {
        this.apps = this.apps.filter((app) => app.id !== id);
      },
      error: (error) => {
        console.log('erreur de la suppresssion : ' + error);
      },
    });
  };

  editApp = (app: Application) => {
    this.appEditing = app;
  };



  findAll = () => {
    this.appService.findAll().subscribe({
      next: (data) => {
        this.apps = data;
        console.log(data);
      },
      error: (error) => {
        console.error('Erreur lors de la récupération des tâches :', error);
      },
    });
  };

  refresh = () => {
    this.findAll();
  };

  ngOnInit() {
    this.findAll();
  }
}
