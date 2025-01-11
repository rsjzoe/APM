import { Component, Input, ViewEncapsulation } from '@angular/core';
import { Router, RouterLink, RouterLinkActive } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-sidebar-item',
  imports: [ CommonModule, RouterLinkActive],
  templateUrl: './sidebar-item.component.html',
  styleUrl: './sidebar-item.component.scss',
  encapsulation: ViewEncapsulation.None
})
export class SidebarItemComponent {
  @Input() iconPath!: string;
  @Input() label!: string;
  @Input() iconRight?: string;
  @Input() route!: string;
  isActive = false;


  constructor(private router: Router) {}

  ngOnInit(): void {
    this.router.events.subscribe(() => {
      this.isActive = this.router.url === this.route;
    });
  }
 
}
