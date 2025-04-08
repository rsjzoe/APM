import { Routes } from '@angular/router';
import { AdministrationComponent } from './sidebar/administration/administration.component';
import { LayoutComponent } from './sidebar/layout/layout.component';
import { HomeComponent } from './sidebar/home/home.component';
import { LifeCycleTimeComponent } from './sidebar/life-cycle-time/life-cycle-time.component';
import { AppDetailsComponent } from './sidebar/home/app-details/app-details.component';
import { UpdatedApplicationComponent } from './sidebar/updated-application/updated-application.component';
import { LoginComponent } from './auth/login/login.component';
import { PerformanceComponent } from './sidebar/performance/performance.component';
import { NotfoundComponent } from './components/notfound/notfound.component';
import { ConnectedGuard } from './auth/connected.guard';
import { GuestGuard } from './auth/guest.guard';
import { CategoryOdaComponent } from './sidebar/category-oda/category-oda.component';
import { CorbeilleComponent } from './sidebar/corbeille/corbeille.component';
import { RoleGuard } from './auth/role.guard';
import { Role } from './sidebar/administration/user.type';
import { ClassificationComponent } from './sidebar/classification/classification.component';
import { UserProfileComponent } from './user-profile/user-profile.component';

export const routes: Routes = [
  {
    path: 'login',
    component: LoginComponent,
    canActivate: [GuestGuard],
  },
  {
    path: '',
    component: LayoutComponent,
    canActivate: [ConnectedGuard, RoleGuard],
    children: [
      {
        path: 'home',
        component: HomeComponent,
        data: { roles: [Role.admin, Role.editor, Role.visitor] },
      },
      {
        path: 'app-details/:id',
        component: AppDetailsComponent,
        data: { roles: [Role.admin, Role.editor, Role.visitor] },
      },

      {
        path: 'administration',
        component: AdministrationComponent,
        data: { roles: [Role.admin] },
      },
      {
        path: 'categoryODA',
        component: CategoryOdaComponent,
        data: { roles: [Role.admin] },
      },
      {
        path: 'life-cycle',
        component: LifeCycleTimeComponent,
        data: { roles: [Role.admin, Role.editor, Role.visitor] },
      },
      {
        path: 'updateApp',
        component: UpdatedApplicationComponent,
        data: { roles: [Role.admin, Role.editor, Role.visitor] },
      },
      {
        path: 'performance',
        component: PerformanceComponent,
        data: { roles: [Role.admin] },
      },
      {
        path: 'classification',
        component: ClassificationComponent,
        data: { roles: [Role.admin, Role.editor, Role.visitor] },
      },
      {
        path: 'user-profile',
        component: UserProfileComponent,
        data: { roles: [Role.admin, Role.editor, Role.visitor] },
      },
      {
        path: 'corbeille',
        component: CorbeilleComponent,
        data: { roles: [Role.admin] },
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
