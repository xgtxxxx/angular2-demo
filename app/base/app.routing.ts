import AppConstants from './app.constants';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from '../login/login.component';
import { UsersComponent } from '../users/users.component';

const appRoutes:Routes = [
    {
        path: AppConstants.ROUTER_LOGIN,
        component: LoginComponent
    },
    {
        path: AppConstants.ROUTER_USERS,
        component: UsersComponent
    }
];

export const Router = RouterModule.forRoot(appRoutes);
