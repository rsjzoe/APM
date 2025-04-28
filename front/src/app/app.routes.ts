import { Routes } from '@angular/router';
import { AdministrationComponent } from './sidebar/administration/administration.component';
import { LayoutComponent } from './sidebar/layout/layout.component';
import { HomeComponent } from './sidebar/home/home.component';
import { LifeCycleTimeComponent } from './sidebar/life-cycle-time/life-cycle-time.component';
import { AppDetailsComponent } from './sidebar/home/app-details/app-details.component';
import { LoginComponent } from './auth/login/login.component';
import { PerformanceComponent } from './sidebar/performance/performance.component';
import { NotfoundComponent } from './components/notfound/notfound.component';
import { ConnectedGuard } from './auth/connected.guard';
import { GuestGuard } from './auth/guest.guard';
import { CategoryOdaComponent } from './sidebar/category-oda/category-oda.component';
import { CorbeilleComponent } from './sidebar/corbeille/corbeille.component';
import { RoleGuard } from './auth/role.guard';
import { ClassificationComponent } from './sidebar/classification/classification.component';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { RoleComponent } from './sidebar/role/role.component';
import { AddRoleComponent } from './sidebar/role/add-role/add-role.component';
import { EditRoleComponent } from './sidebar/role/edit-role/edit-role.component';

export const routes: Routes = [
  {
    path: 'login',
    component: LoginComponent,
    canActivate: [GuestGuard],
  },
  {
    path: '',
    component: LayoutComponent,
    canActivate: [ConnectedGuard],
    children: [
      {
        path: 'home',
        component: HomeComponent,
        data: { action: 'canRead', serviceName: 'application' },
        canActivate: [RoleGuard],
      },
      {
        path: 'app-details/:id',
        component: AppDetailsComponent,
      },

      {
        path: 'administration',
        component: AdministrationComponent,
        data: { action: 'canRead', serviceName: 'admin' },
        canActivate: [RoleGuard],
      },
      {
        path: 'categoryODA',
        component: CategoryOdaComponent,
        canActivate: [RoleGuard],
        data: { action: 'canRead', serviceName: 'category' },
      },
      {
        path: 'life-cycle',
        component: LifeCycleTimeComponent,
      },
      {
        path: 'performance',
        component: PerformanceComponent,
        data: { action: 'canRead', serviceName: 'performance' },
        canActivate: [RoleGuard],
      },
      {
        path: 'classification',
        component: ClassificationComponent,
        canActivate: [RoleGuard],
        data: { action: 'canRead', serviceName: 'classification' },
      },
      {
        path: 'user-profile',
        component: UserProfileComponent,
      },
      {
        path: 'corbeille',
        component: CorbeilleComponent,
        canActivate: [RoleGuard],
        data: { action: 'canRead', serviceName: 'corbeille' },
      },
      {
        path: 'roles',
        component: RoleComponent,
        canActivate: [RoleGuard],
        data: { action: 'canRead', serviceName: 'roles' },
      },
      {
        path: 'roles/add',
        component: AddRoleComponent,
        canActivate: [RoleGuard],
        data: { action: 'canCreate', serviceName: 'roles' },
      },
      {
        path: 'roles/edit/:name',
        component: EditRoleComponent,
        canActivate: [RoleGuard],
        data: { action: 'canUpdate', serviceName: 'roles' },
      },
      {
        path: '404',
        component: NotfoundComponent,
      },
      {
        path: '**',
        component: NotfoundComponent,
      },
    ],
  },
];
