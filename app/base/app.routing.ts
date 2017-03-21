import AppConstants from './app.constants';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from '../login/login.component';
import { UsersComponent } from '../users/users.component';
import {UsersAddFormComponent} from "../users/users.add-form.component";
import {UsersEditFormComponent} from "../users/users.edit-form.component";

const appRoutes:Routes = [
    {
        path: AppConstants.ROUTER_LOGIN,
        component: LoginComponent
    },
    {
        path: AppConstants.ROUTER_USERS,
        component: UsersComponent
    },
    {
        path: AppConstants.ROUTER_ADD_USER,
        component: UsersAddFormComponent
    },
    {
        path: AppConstants.ROUTER_EDIT_USER + '/:id',
        component: UsersEditFormComponent
    }
];

export const Router = RouterModule.forRoot(appRoutes);
