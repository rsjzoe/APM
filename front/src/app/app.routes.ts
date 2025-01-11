import { Routes } from '@angular/router';
import { LayoutComponent } from './sidebar/layout/layout.component';
import { HomeComponent } from './sidebar/home/home.component';
import { CategoryComponent } from './sidebar/category/category.component';
import { UpdatedApplicationComponent } from './sidebar/updated-application/updated-application.component';
import { CategoryOdaComponent } from './sidebar/category/category-oda/category-oda.component';
import { CategorySiComponent } from './sidebar/category/category-si/category-si.component';
import { AdministrationComponent } from './sidebar/administration/administration.component';
import { LifeCycleTimeComponent } from './sidebar/life-cycle-time/life-cycle-time.component';

export const routes: Routes = [
  {
    path: '',
    component: LayoutComponent,
    children: [
      {
        path: 'home',
        component: HomeComponent,
      },
      {
        path: 'administration',
        component: AdministrationComponent,
      },
      {
        path: 'category',
        component: CategoryComponent,
        children: [
          {
            path: 'categorySI',
            component: CategorySiComponent,
          },
          {
            path: 'categoryODA',
            component: CategoryOdaComponent,
          },
        ],
      },
      {
        path: 'life-cycle',
        component: LifeCycleTimeComponent,
      },
      {
        path: 'updateApp',
        component: UpdatedApplicationComponent,
      },
    ],
  },
];
