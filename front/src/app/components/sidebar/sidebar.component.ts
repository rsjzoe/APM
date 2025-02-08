import { Component, Input, ViewEncapsulation } from '@angular/core';
import { RouterLink } from '@angular/router';
import { ButtonComponent } from '../button/button.component';
import { SidebarItemComponent } from './sidebar-item/sidebar-item.component';
import { SidebarLinkComponent } from './sidebar-link/sidebar-link.component';

@Component({
  selector: 'app-sidebar',
  imports: [ButtonComponent, SidebarItemComponent, SidebarLinkComponent],
  templateUrl: './sidebar.component.html',
  styleUrl: './sidebar.component.scss',
  encapsulation: ViewEncapsulation.None,
})
export class SidebarComponent {
  showCategory = false;
  @Input() toggleSidebar = () => {
  };

  toggleShowCategory() {
    this.showCategory = !this.showCategory;
  }
}
