import { Component } from '@angular/core';
import { Classe } from '../../application/classe/classe.type';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ClasseService } from '../../application/classe/classe.service';
import { combineLatest, map, Observable } from 'rxjs';
import { UserService } from '../administration/user.service';
import { IconDeleteComponent } from '../../components/icons/icon-delete/icon-delete.component';
import { IconEditComponent } from '../../components/icons/icon-edit/icon-edit.component';
import { ToastService } from '../../components/toast/service/toast.service';
import { SocketService } from '../../socket.service';

@Component({
  selector: 'app-classification',
  imports: [FormsModule, CommonModule, IconDeleteComponent, IconEditComponent],
  templateUrl: './classification.component.html',
  styleUrl: './classification.component.scss',
})
export class ClassificationComponent {
  classes: Classe[] = [];
  newClasse: Classe = this.getEmptyClasse();
  editingClasse: Classe | null = null;
  expandedId: number | null = null;

  canAddClasse$!: Observable<boolean>;
  canEditClasse$!: Observable<boolean>;
  canDeleteClasse$!: Observable<boolean>;
  canEditOrDelete$!: Observable<boolean>;

  constructor(
    private classeService: ClasseService,
    private userService: UserService,
    private toastService: ToastService,
    private socketService: SocketService
  ) {
    this.socketService.onEvent('refetch_classe', () => {
      this.init();
    });
  }

  init() {
    this.findAll();
    this.canAddClasse$ = this.userService.canCreateService('classification');
    this.canEditClasse$ = this.userService.canEditService('classification');
    this.canDeleteClasse$ = this.userService.canDeleteService('classification');
    this.canEditOrDelete$ = combineLatest([
      this.canDeleteClasse$,
      this.canEditClasse$,
    ]).pipe(map(([canDelete, canEdit]) => canDelete || canEdit));
  }

  ngOnInit() {
    this.init();
  }

  findAll() {
    this.classeService.findAll().subscribe((classes) => {
      this.classes = classes;
    });
  }

  getEmptyClasse(): Classe {
    return { id: 0, name: '', description: '', isDeleted: false };
  }

  addClass() {
    if (this.newClasse.name.trim()) {
      this.classeService
        .add({
          name: this.newClasse.name,
          description: this.newClasse.description,
        })
        .subscribe({
          next: () => {
            this.findAll();
            this.newClasse = this.getEmptyClasse();
            this.toastService.success('Classe ajoutée avec succès');
          },
          error: (err) => {
            this.toastService.error("Erreur lors de l'ajout de la Classe");
            console.error(err);
          },
        });
    }
  }

  deleteClasse(id: number) {
    this.classeService.delete(id).subscribe({
      next: () => {
        this.findAll();
        this.toastService.success('Classe supprimée avec succès');
      },
      error: (err) => {
        this.toastService.error('Erreur lors de la suppression de la Classe');
        console.error(err);
      },
    });
  }

  startEditing(classe: Classe) {
    this.editingClasse = { ...classe };
    if (this.editingClasse) {
      this.newClasse = this.editingClasse;
      window.scrollTo(0, 0);
    }
  }

  updateEditingName(name: string) {
    if (this.editingClasse) {
      this.editingClasse = { ...this.editingClasse, name };
    }
  }

  updateEditingDescription(description: string) {
    if (this.editingClasse) {
      this.editingClasse = { ...this.editingClasse, description };
    }
  }

  saveEdit() {
    if (!this.editingClasse) return;

    this.classeService
      .update(this.editingClasse.id, this.editingClasse)
      .subscribe({
        next: () => {
          this.findAll();
          this.cancelEdit();
          this.toastService.success('Classe modifiée avec succès');
        },
        error: (error) => {
          console.log('erreur de la modification : ' + error);
          this.toastService.error(
            'Erreur lors de la modification de la Classe'
          );
        },
      });
  }

  cancelEdit() {
    this.editingClasse = null;
    this.newClasse = this.getEmptyClasse();
  }

  isEditing(id: number): boolean {
    return this.editingClasse?.id === id;
  }

  toggleAccordion(id: number) {
    this.expandedId = this.expandedId === id ? null : id;
  }

  isExpanded(id: number): boolean {
    return this.expandedId === id;
  }
}
