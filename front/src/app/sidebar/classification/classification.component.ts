import { Component } from '@angular/core';
import { Classe } from '../../application/classe/classe.type';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ClasseService } from '../../application/classe/classe.service';
import { Observable } from 'rxjs';
import { UserService } from '../administration/user.service';

@Component({
  selector: 'app-classification',
  imports: [FormsModule, CommonModule],
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

  constructor(
    private classeService: ClasseService,
    private userService: UserService
  ) {}

  ngOnInit() {
    this.findAll();
    this.canAddClasse$ = this.userService.canCreateService('classification');
    this.canEditClasse$ = this.userService.canEditService('classification');
    this.canDeleteClasse$ = this.userService.canDeleteService('classification');
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
        .subscribe((classe) => {
          this.findAll();
          this.newClasse = this.getEmptyClasse();
        });
    }
  }

  deleteClasse(id: number) {
    this.classeService.delete(id).subscribe(() => {
      this.findAll();
    });
  }

  startEditing(classe: Classe) {
    this.editingClasse = { ...classe };
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
          this.editingClasse = null;
        },
        error: (error) => {
          console.error('Error updating class:', error);
        },
      });
  }

  cancelEdit() {
    this.editingClasse = null;
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
