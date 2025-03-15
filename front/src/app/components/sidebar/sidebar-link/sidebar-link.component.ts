import { Component, Input } from '@angular/core';
import { Router, RouterLink, RouterLinkActive } from '@angular/router';
import { SidebarItemComponent } from '../sidebar-item/sidebar-item.component';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-sidebar-link',
  imports: [RouterLink, SidebarItemComponent,CommonModule],
  templateUrl: './sidebar-link.component.html',
  styleUrl: './sidebar-link.component.scss',
})
export class SidebarLinkComponent {
  @Input() route!: string;
  @Input() iconPath! : string ;
  @Input() label! : string ;
 
}
